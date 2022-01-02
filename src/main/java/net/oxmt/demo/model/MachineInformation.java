package net.oxmt.demo.model;

import net.oxmt.demo.Exceptions.InvalidAgeStringException;
import net.oxmt.demo.Exceptions.InvalidAgeUnitsException;

import java.util.Locale;
import java.util.Objects;

public class MachineInformation {

    private int id;
    private double ageInDays;

    public MachineInformation(int id, String age) throws InvalidAgeUnitsException, InvalidAgeStringException {
        setId(id);
        setAge(age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double ageInDays() {
        return ageInDays;
    }

    public void setAge(String age) throws InvalidAgeStringException, InvalidAgeUnitsException {

        String[] element = age.trim().toLowerCase(Locale.ROOT).split(" ");
        double num;
        try {
            num = Double.parseDouble(element[0]);
        }
        catch (Exception e)
        {
            throw new InvalidAgeStringException(age);
        }

        switch (element[1]) {
            case "days":
                this.ageInDays = num;
                break;
            case "weeks":
                this.ageInDays = num * 7;
                break;
            case "months":
                this.ageInDays = num * 30;
                break;
            case "years":
                this.ageInDays = num * 365;
                break;
            default:
                throw new InvalidAgeUnitsException(element[1]);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MachineInformation)) return false;
        MachineInformation that = (MachineInformation) o;
        return id == that.id && Objects.equals(ageInDays, that.ageInDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ageInDays);
    }
}