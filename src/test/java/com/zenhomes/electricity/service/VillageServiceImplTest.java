package com.zenhomes.electricity.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.zenhomes.electricity.dto.CounterDetailsResponse;
import com.zenhomes.electricity.exception.ConstraintsViolationException;
import com.zenhomes.electricity.model.Village;
import com.zenhomes.electricity.repository.VillageRepository;
import com.zenhomes.electricity.service.village.VillageServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class VillageServiceImplTest
{

    @Mock
    private VillageRepository villageRepository;

    @InjectMocks
    private VillageServiceImpl villageService;

    private Village village;

    @Mock
    private RestTemplate restTemplate;

    private CounterDetailsResponse counterDetailsResponse;

    @Before
    public void setUp()
    {
        ReflectionTestUtils.setField(villageService, "villageDetailsUrl", "https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/{id}");
        village = new Village();
        village.setName("Mysore");
    }


    @Test
    public void testCreateVillage() throws Exception
    {
        Mockito.when(villageRepository.save(village)).thenReturn(village);
        villageService.createVillage(village);
        Mockito.verify(villageRepository, Mockito.times(1)).save(any());
    }


    @Test(expected = ConstraintsViolationException.class)
    public void testCreateVillageWhenViolation() throws Exception
    {
        Mockito.when(villageRepository.save(village)).thenThrow(DataIntegrityViolationException.class);
        villageService.createVillage(village);
        Mockito.verify(villageRepository, Mockito.times(1)).save(any());
    }


    @Test
    public void testGetDetails() throws Exception
    {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        Assert
            .assertEquals(
                villageService.getVillageDetails("1"),
                restTemplate.getForObject("https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/{id}", CounterDetailsResponse.class, map));
    }


    @Test
    public void testGetDetailsWhenNull() throws Exception
    {
        Assert.assertNull(villageService.getVillageDetails("100"));
    }

}
