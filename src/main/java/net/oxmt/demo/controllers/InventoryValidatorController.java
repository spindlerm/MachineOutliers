package net.oxmt.demo.controllers;


import net.oxmt.demo.model.MachineAge;
import net.oxmt.demo.model.MachineInformation;
import net.oxmt.demo.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryValidatorController {
    @Autowired()
    MachineService _machineService;

    @PostMapping("/validate")
    List<MachineInformation> validate(@RequestBody List<MachineInformation> machineInformation) {
        return _machineService.validate(machineInformation);
    }

}