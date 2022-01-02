package net.oxmt.demo.service;
import net.oxmt.demo.Exceptions.EmptyMachineInformationException;
import net.oxmt.demo.Exceptions.InvalidAgeStringException;
import net.oxmt.demo.Exceptions.InvalidAgeUnitsException;
import net.oxmt.demo.model.MachineInformation;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MachineServiceTest {

    @Test
    void GivenNullMachineInformation_WhenGetOutliersCalled_ThenThrowIllegalArgumentException() {
        MachineService machineService = new MachineService();
        List<MachineInformation> machineInformation = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            machineService.getOutliers(machineInformation);
        }, "llegalArgumentException was expected");
    }

    @Test
    void GivenEmptyMachineInformation_WhenGetOutliersCalled_ThenThrowEmptyMachineInformationException() {
        MachineService machineService = new MachineService();
        List<MachineInformation> machineInformation = new ArrayList<>();

        EmptyMachineInformationException thrown = assertThrows(EmptyMachineInformationException.class, () -> {
            machineService.getOutliers(machineInformation);
        }, "EmptyMachineInformationException was expected");


    }

    @Test
    void GivenListValidMachineInformation_WhenGetOutliersCalled_ReturnOutliers() throws EmptyMachineInformationException, InvalidAgeUnitsException, InvalidAgeStringException {
        MachineService machineService = new MachineService();
        List<MachineInformation> machineInformation = new ArrayList<MachineInformation>(){{
            add(new MachineInformation(1,"10 weeks"));
            add(new MachineInformation(2,"20 weeks"));
            add(new MachineInformation(3, "15 weeks"));
            add(new MachineInformation(4, "1 weeks"));
            add(new MachineInformation(5, "5 weeks"));
            add(new MachineInformation(6, "11 weeks"));
            add(new MachineInformation(7, "99 years"));
            add(new MachineInformation(8, "21 weeks"));
            add(new MachineInformation(9, "18 weeks"));
            add(new MachineInformation(10,"10 weeks"));
            add(new MachineInformation(11,"20 weeks"));
            add(new MachineInformation(12, "15 weeks"));
            add(new MachineInformation(13, "1 weeks"));
            add(new MachineInformation(14, "5 weeks"));
            add(new MachineInformation(15, "11 weeks"));
            add(new MachineInformation(16, "15 years"));
            add(new MachineInformation(17, "4 weeks"));
            add(new MachineInformation(18, "18 weeks"));
            add(new MachineInformation(19, "18 weeks"));
            add(new MachineInformation(20, "100 weeks"));}};

        List<MachineInformation> assumedOutlierResults = new ArrayList<MachineInformation>(){{
            add(new MachineInformation(7, "99 years"));}};


        List<MachineInformation>  outliers = machineService.getOutliers(machineInformation);

        assertEquals(outliers, assumedOutlierResults);
    }
}