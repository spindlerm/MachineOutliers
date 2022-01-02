package net.oxmt.demo.controllers;
import net.oxmt.demo.model.InvalidAgeStringException;
import net.oxmt.demo.model.InvalidAgeUnitsException;
import net.oxmt.demo.model.MachineInformation;
import net.oxmt.demo.model.dtos.MachineInformationDto;
import net.oxmt.demo.service.EmptyMachineInformationException;
import net.oxmt.demo.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    List<MachineInformation> validate( @RequestBody @NotEmpty List<@Valid  MachineInformationDto> machineInformationDtoList) throws EmptyMachineInformationException, InvalidAgeUnitsException, InvalidAgeStringException {


    List<MachineInformation> z = machineInformationDtoList.stream().map(x -> {
        try {
            return x.toMachineInformation();
        } catch (InvalidAgeUnitsException e) {
            e.printStackTrace();
        } catch (InvalidAgeStringException e) {
            e.printStackTrace();
        }
        return null;
    }).collect(Collectors.toList());



        return _machineService.getOutliers(z);

    }
}
