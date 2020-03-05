package com.zenhomes.electricity.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigInteger;

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
import com.zenhomes.electricity.dto.VillageDTO;
import com.zenhomes.electricity.model.Village;
import com.zenhomes.electricity.service.village.VillageService;

@RunWith(MockitoJUnitRunner.class)
public class VillageControllerTest
{

    private MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private VillageService villageService;

    @InjectMocks
    private VillageController villageController;

    private Village village;

    @Before
    public void setUp()
    {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(villageController).dispatchOptions(true).build();
        village = new Village();
        village.setId(BigInteger.ONE);
        village.setName("New Village");
    }


    @Test
    public void testCreateVillageRoute() throws Exception
    {
        VillageDTO villageDTO;
        VillageDTO.VillageDTOBuilder createVillageDTOBuilder =
            VillageDTO
                .newBuilder()
                .setCounterId(BigInteger.ONE)
                .setVillageName("New Village");
        villageDTO = createVillageDTOBuilder.createVillageDTO();
        Mockito.when(villageService.createVillage(any(Village.class))).thenReturn(village);
        MockHttpServletResponse response =
            mockMvc
                .perform(
                    post("/create_village")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(mapper.writeValueAsString(villageDTO))
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))

                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse();

        Assert.assertTrue(response.getContentAsString().contains("1"));
        Assert.assertTrue(response.getContentAsString().contains("New Village"));
    }

}
