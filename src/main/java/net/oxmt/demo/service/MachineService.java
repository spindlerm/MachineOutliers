package net.oxmt.demo.service;

import net.oxmt.demo.model.MachineAge;
import net.oxmt.demo.model.MachineInformation;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineService {
    public MachineService()
    {

    }
    public List<MachineInformation> validate(List<MachineInformation> machineInformation){
        List<MachineInformation> retMachineInformation = new ArrayList<>();

        net.oxmt.demo.model.MachineInformation a = new net.oxmt.demo.model.MachineInformation(1, 22, MachineAge.Units.weeks);
        net.oxmt.demo.model.MachineInformation b = new net.oxmt.demo.model.MachineInformation(2, 10, MachineAge.Units.years);

        retMachineInformation.add(a);
        retMachineInformation.add(b);
        return retMachineInformation;
    }
}
