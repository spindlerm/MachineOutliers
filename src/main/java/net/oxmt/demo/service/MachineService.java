package net.oxmt.demo.service;

import com.github.chen0040.data.frame.BasicDataFrame;
import com.github.chen0040.data.frame.DataFrame;
import com.github.chen0040.data.frame.DataRow;
import com.github.chen0040.lof.LOF;
import net.oxmt.demo.Exceptions.EmptyMachineInformationException;
import net.oxmt.demo.Exceptions.InvalidAgeStringException;
import net.oxmt.demo.Exceptions.InvalidAgeUnitsException;
import net.oxmt.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineService {
    public MachineService()
    {

    }
    public List<MachineInformation> getOutliers(List<MachineInformation> machineInformation) throws EmptyMachineInformationException {

        if(machineInformation == null)
            throw new IllegalArgumentException("machineInformation");
        if(machineInformation.isEmpty())
            throw new EmptyMachineInformationException("machineInformation");

        List<MachineInformation> anomalies = new ArrayList<>();

        DataFrame dataFrame = new BasicDataFrame();

        for (MachineInformation m : machineInformation) {
            DataRow row = dataFrame.newRow();
            row.setCell("machine Id", m.getId());
            row.setCell("age", m.ageInDays());
            dataFrame.addRow(row);
        }

        // call lock to perform aggregation and prevent further addition of new rows
        dataFrame.lock();

        LOF method = new LOF();
        method.setMinPtsLB(3);
        method.setMinPtsUB(15);
        method.setThreshold(0.2);
        DataFrame trainedData = method.fitAndTransform(dataFrame);

        int count =0;
        for(DataRow r: trainedData){
            if(method.isAnomaly(r)) {
               anomalies.add(machineInformation.get(count));
            }
            count++;
        }

        return anomalies;
    }
}
