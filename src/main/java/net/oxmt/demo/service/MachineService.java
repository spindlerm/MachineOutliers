package net.oxmt.demo.service;

import net.oxmt.demo.model.AgeUnits;
import net.oxmt.demo.model.MachineAge;
import net.oxmt.demo.model.MachineInformation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineService {
    public MachineService()
    {

    }
    public List<MachineInformation> validate(List<MachineInformation> machineInformation){
        List<MachineInformation> retMachineInformation = new ArrayList<>();

        net.oxmt.demo.model.MachineInformation a = new net.oxmt.demo.model.MachineInformation(1, new MachineAge(22, AgeUnits.weeks));
        net.oxmt.demo.model.MachineInformation b = new net.oxmt.demo.model.MachineInformation(2, new MachineAge( 2, AgeUnits.years));

        retMachineInformation.add(a);
        retMachineInformation.add(b);
        return retMachineInformation;
    }
}
