package net.oxmt.demo.Controllers;
import net.oxmt.demo.model.MachineInformation;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.oxmt.demo.controllers.InventoryValidatorController;
import net.oxmt.demo.model.dtos.MachineInformationDto;
import net.oxmt.demo.service.MachineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = InventoryValidatorController.class)
class InventoryValidatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MachineService machineService;

    @Test
    void whenInvalidUnitsInput_thenReturns400() throws Exception {

        List<MachineInformationDto> machineInformationDtoList = new ArrayList<MachineInformationDto>(){{
            add(new MachineInformationDto (3, "15 monhs"));}};


        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/inventory/validate/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(machineInformationDtoList)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenInvalidNumberInput_thenReturns400() throws Exception {
        List<MachineInformationDto> machineInformationDtoList = new ArrayList<MachineInformationDto>(){{
            add(new MachineInformationDto (3, "months"));}};

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/inventory/validate/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(machineInformationDtoList)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenInvalidEmptyAgeInput_thenReturns400() throws Exception {
        List<MachineInformationDto> machineInformationDtoList = new ArrayList<MachineInformationDto>(){{
            add(new MachineInformationDto (3, ""));}};


        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/inventory/validate/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(machineInformationDtoList)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenEmptyInputList_thenReturns400() throws Exception {

        // when(machineService.getOutliers(isA(MachineInformation.class))).thenReturn(List<MachineInformation>new MachineInformation(3, "15 months");

        //when(todoServiceMock.findAll()).thenReturn(Arrays.asList(first, second));
        List<MachineInformationDto> machineInformationDtoList = new ArrayList<MachineInformationDto>();



        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/inventory/validate/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(machineInformationDtoList)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenValidInput_thenReturnsOutliers200() throws Exception {


        List<MachineInformation> inputMachineInformationList = new ArrayList<MachineInformation>(){{
            add(new MachineInformation (1, "1 months"));
            add(new MachineInformation (2, "10 months"));
            add(new MachineInformation (3, "3 years"));
            add(new MachineInformation (4, "3 months"));
        }};

        List<MachineInformation> returnMachineInformationList = new ArrayList<MachineInformation>(){{
            add(new MachineInformation (3, "3 years"));}};

        when(machineService.getOutliers(inputMachineInformationList)).thenReturn(returnMachineInformationList);


        List<MachineInformationDto> machineInformationDtoList = new ArrayList<MachineInformationDto>(){{
            add(new MachineInformationDto (1, "1 months"));
            add(new MachineInformationDto (2, "10 months"));
            add(new MachineInformationDto (3, "3 years"));
            add(new MachineInformationDto (4, "3 months"));
        }};



        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/inventory/validate/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(machineInformationDtoList)))
                .andExpect(status().isOk());

    }
}

