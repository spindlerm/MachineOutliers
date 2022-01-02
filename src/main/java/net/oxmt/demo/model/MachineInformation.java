package net.oxmt.demo.model;

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

        if(element[1].equals("days"))
            this.ageInDays = num;
        else if (element[1].equals("weeks")){
            this.ageInDays = num*7;
        }
        else if (element[1].equals("months")){
            this.ageInDays = num*30;
        }
        else if (element[1].equals("years")){
            this.ageInDays = num*365;
        }
        else
            throw new InvalidAgeUnitsException(element[1]) ;

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