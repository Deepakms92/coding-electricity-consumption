package com.zenhomes.electricity.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenhomes.electricity.dto.CounterCallBackDTO;
import com.zenhomes.electricity.dto.CreateCounterDTO;
import com.zenhomes.electricity.model.ElectricityCounters;
import com.zenhomes.electricity.model.Village;
import com.zenhomes.electricity.service.counter.ElectricityCounterService;

@RunWith(MockitoJUnitRunner.class)
public class ElectricityCounterControllerTest
{
    private MockMvc mockMvc;

    @Mock
    private ElectricityCounterService electricityCounterService;

    @InjectMocks
    private ElectricityCounterController electricityCounterController;

    private ElectricityCounters electricityCounters;

    private static final ObjectMapper mapper = new ObjectMapper();

    private Village village;

    @Before
    public void setUp()
    {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(electricityCounterController).dispatchOptions(true).build();
        electricityCounters = new ElectricityCounters();
        village = new Village();
        village.setId(BigInteger.ONE);
        village.setName("New Village");
        electricityCounters.setCounterId(BigInteger.ONE);
        electricityCounters.setAmount(BigDecimal.valueOf(23232.235));
        electricityCounters.setVillage(village);

    }


    @Test
    public void testCreateCounterRoute() throws Exception
    {
        CreateCounterDTO createCounterDTO;
        CreateCounterDTO.CreateCounterDTOBuilder createCounterDTOBuilder =
            CreateCounterDTO
                .newBuilder()
                .setCounterId(BigInteger.ONE)
                .setVillageId(BigInteger.ONE);
        createCounterDTO = createCounterDTOBuilder.createCounterDTO();
        Mockito.when(electricityCounterService.createCounter(any(ElectricityCounters.class), any())).thenReturn(electricityCounters);
        MockHttpServletResponse response =
            mockMvc
                .perform(
                    post("/create_counter/{villageId}", BigInteger.ONE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(mapper.writeValueAsString(createCounterDTO))
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))

                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse();

        Assert.assertTrue(response.getContentAsString().contains("1"));
        Assert.assertTrue(response.getContentAsString().contains("1"));
    }


    @Test
    public void testCounterCallBackRoute() throws Exception
    {
        CounterCallBackDTO counterCallBackDTO;
        CounterCallBackDTO.CounterCallBackDTOBuilder counterCallBackDTOBuilder =
            CounterCallBackDTO
                .newBuilder()
                .setCounterId(BigInteger.ONE)
                .setAmount(BigDecimal.valueOf(23232.235));
        counterCallBackDTO = counterCallBackDTOBuilder.createCounterCallBackDTO();
        Mockito.when(electricityCounterService.counterCallback(any(ElectricityCounters.class))).thenReturn(electricityCounters);
        MockHttpServletResponse response =
            mockMvc
                .perform(
                    post("/counter_callback")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(mapper.writeValueAsString(counterCallBackDTO)))

                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse();

        Assert.assertTrue(response.getContentAsString().contains("1"));
        Assert.assertTrue(response.getContentAsString().contains("23232.235"));

    }


    @Test
    public void testGetReportRoute() throws Exception
    {
        List<ElectricityCounters> list = new ArrayList<>();
        list.add(electricityCounters);
        Mockito.when(electricityCounterService.getReport(any())).thenReturn(list);
        MockHttpServletResponse response =
            mockMvc
                .perform(
                    get("/consumption_report?duration=", "24h")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        Assert.assertTrue(response.getContentAsString().contains("New Village"));
        Assert.assertTrue(response.getContentAsString().contains("23232.235"));
    }
}
