package net.oxmt.machineoutliers.controllers;
import net.oxmt.machineoutliers.Exceptions.InvalidAgeStringException;
import net.oxmt.machineoutliers.Exceptions.InvalidAgeUnitsException;
import net.oxmt.machineoutliers.model.MachineInformation;
import net.oxmt.machineoutliers.model.dtos.MachineInformationDto;
import net.oxmt.machineoutliers.Exceptions.EmptyMachineInformationException;
import net.oxmt.machineoutliers.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/inventory")
public class InventoryValidatorController {
    @Autowired()
    MachineService _machineService;

    @PostMapping("/validate")
    List<MachineInformation> validate( @RequestBody @NotEmpty List<@Valid  MachineInformationDto> machineInformationDtoList) throws EmptyMachineInformationException {


    List<MachineInformation> z = machineInformationDtoList.stream().map(x -> {
        try {
            return x.toMachineInformation();
        } catch (InvalidAgeUnitsException | InvalidAgeStringException e) {
            e.printStackTrace();
        }
        return null;
    }).collect(Collectors.toList());



        return _machineService.getOutliers(z);

    }
}
