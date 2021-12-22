package net.oxmt.demo.model;

public class MachineInformation {
    private int id;
    private MachineAge age;

    public MachineInformation(int id, MachineAge age){
        setId(id);
        setAge(age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MachineAge getAge() {
        return age;
    }

    public void setAge(MachineAge age) {
        this.age = age;
    }
}
