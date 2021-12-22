package net.oxmt.demo.model;

public class MachineAge{
    public enum Units {
        undefined,
        seconds,
        minutes,
        hours,
        days,
        weeks,
        months,
        years
    }
    private int number;
    private Units units = Units.undefined;

    public MachineAge(int number, Units units){
        this.setNumber(number);
        this.setUnits(units);
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }
}
