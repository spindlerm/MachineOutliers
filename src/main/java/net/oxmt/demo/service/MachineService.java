package net.oxmt.demo.service;

import com.github.chen0040.data.frame.BasicDataFrame;
import com.github.chen0040.data.frame.DataFrame;
import com.github.chen0040.data.frame.DataRow;
import com.github.chen0040.lof.LOF;
import net.oxmt.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MachineService {
    public MachineService()
    {

    }
    public List<MachineInformation> getOutliers(List<MachineInformation> machineInformation) throws EmptyMachineInformationException, InvalidAgeUnitsException, InvalidAgeStringException {

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


        for(DataRow r: trainedData){
            if(method.isAnomaly(r)) {
               anomalies.add(new MachineInformation((int)r.getCell("machine Id"), String.format("%f days", r.getCell("age"))));
            }
        }

        return anomalies;
    }
}
