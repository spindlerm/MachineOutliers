package net.oxmt.demo.model;

public class MachineAge{

    private int number;
    private AgeUnits units = AgeUnits.undefined;

    public MachineAge(int number, AgeUnits units){
        this.setNumber(number);
        this.setUnits(units);
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public AgeUnits getUnits() {
        return units;
    }

    public void setUnits(AgeUnits units) {
        this.units = units;
    }
}
