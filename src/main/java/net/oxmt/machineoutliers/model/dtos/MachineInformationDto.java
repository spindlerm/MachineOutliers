package net.oxmt.machineoutliers.model.dtos;
import net.oxmt.machineoutliers.Exceptions.InvalidAgeStringException;
import net.oxmt.machineoutliers.Exceptions.InvalidAgeUnitsException;
import net.oxmt.machineoutliers.model.MachineInformation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class MachineInformationDto {
    private int id;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[0-9]*.[0-9]*\\s*(years|months|weeks|days)$")
    private String age;

    public MachineInformationDto(int id, String age) {
        setId(id);
        setAge(age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MachineInformationDto)) return false;
        MachineInformationDto that = (MachineInformationDto) o;
        return id == that.id && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age);
    }

    public MachineInformation toMachineInformation() throws InvalidAgeUnitsException, InvalidAgeStringException {
        return new MachineInformation(id, age);

    }
}