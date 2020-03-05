package com.zenhomes.electricity.service;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import com.zenhomes.electricity.dto.CounterDetailsResponse;
import com.zenhomes.electricity.exception.ConstraintsViolationException;
import com.zenhomes.electricity.exception.CounterNotFoundException;
import com.zenhomes.electricity.exception.VillageNotFoundException;
import com.zenhomes.electricity.model.ElectricityCounters;
import com.zenhomes.electricity.model.Village;
import com.zenhomes.electricity.repository.ElectricityCounterRepository;
import com.zenhomes.electricity.repository.VillageRepository;
import com.zenhomes.electricity.service.counter.ElectricityCounterServiceImpl;
import com.zenhomes.electricity.service.village.VillageServiceImpl;
import com.zenhomes.electricity.util.TimeUtils;

@RunWith(MockitoJUnitRunner.class)
public class ElectricityCounterServiceImplTest
{

    @Mock
    private ElectricityCounterRepository electricityCounterRepository;

    @InjectMocks
    private ElectricityCounterServiceImpl electricityCounterService;

    @Mock
    private VillageRepository villageRepository;

    @Mock
    private VillageServiceImpl villageService;

    private ElectricityCounters electricityCounters;

    private Village village;

    private CounterDetailsResponse counterDetailsResponse;

    @Mock
    private TimeUtils timeUtils;

    @Before
    public void setUp()
    {
        electricityCounters = new ElectricityCounters();
        electricityCounters.setCounterId(BigInteger.ONE);
        counterDetailsResponse = new CounterDetailsResponse();
        counterDetailsResponse.setId(BigInteger.ONE);
        village = new Village();
        village.setId(BigInteger.ONE);
        village.setName("New Village");
        counterDetailsResponse.setVillage(village);
        electricityCounters.setVillage(village);
        electricityCounters.setAmount(BigDecimal.valueOf(122154.233));

    }


    @Test(expected = ConstraintsViolationException.class)
    public void testCreateCounter_WhenDataViolation() throws Exception
    {
        when(villageRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(village));
        when(electricityCounterRepository.save(electricityCounters)).thenThrow(DataIntegrityViolationException.class);
        electricityCounterService.createCounter(electricityCounters, BigInteger.ONE);
    }


    @Test
    public void testCreateCounter() throws Exception
    {
        when(villageRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(village));
        electricityCounterService.createCounter(electricityCounters, BigInteger.ONE);
        verify(electricityCounterRepository, times(1)).save(electricityCounters);
    }


    @Test(expected = VillageNotFoundException.class)
    public void testCreateCounter_WhenException() throws Exception
    {
        when(villageRepository.findById(BigInteger.ONE)).thenThrow(new VillageNotFoundException("EXception"));
        electricityCounterService.createCounter(electricityCounters, BigInteger.ONE);
    }


    @Test
    public void testCreateCounterCallBack() throws Exception
    {
        when(villageService.getVillageDetails(anyString())).thenReturn(counterDetailsResponse);
        electricityCounterService.counterCallback(electricityCounters);
        verify(electricityCounterRepository, times(1)).save(electricityCounters);
    }


    @Test(expected = VillageNotFoundException.class)
    public void testCreateCounterCallBack_WhenException() throws Exception
    {
        when(villageService.getVillageDetails(anyString())).thenThrow(new VillageNotFoundException("EXception"));
        electricityCounterService.counterCallback(electricityCounters);
    }


    @Test(expected = ConstraintsViolationException.class)
    public void testCreateCounterCallBack_WhenViolation() throws Exception
    {
        when(villageService.getVillageDetails(anyString())).thenReturn(counterDetailsResponse);
        when(electricityCounterRepository.save(electricityCounters)).thenThrow(DataIntegrityViolationException.class);
        electricityCounterService.counterCallback(electricityCounters);
    }


    @Test
    public void testGetInformation() throws Exception
    {
        when(electricityCounterRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(electricityCounters));
        electricityCounterService.getCounterInformation(BigInteger.ONE);
        verify(electricityCounterRepository, times(1)).findById(BigInteger.ONE);
    }


    @Test(expected = CounterNotFoundException.class)
    public void testGetInformation_givesException() throws Exception
    {
        when(electricityCounterRepository.findById(BigInteger.ONE)).thenThrow(new CounterNotFoundException("EXception"));
        electricityCounterService.getCounterInformation(BigInteger.ONE);
    }


    @Test
    public void testGetReportWhenProper() throws Exception
    {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime fromDateAndTime = LocalDateTime.of(currentDate, currentTime);
        List<ElectricityCounters> list = new ArrayList<>();
        list.add(electricityCounters);
        when(timeUtils.convertTimeIntoMillis(any())).thenReturn(Long.valueOf(1236487845));
        when(electricityCounterRepository.findByTimestampLargerThanorEqual(any())).thenReturn(list);
        electricityCounterService.getReport("24h");
        verify(electricityCounterRepository, times(1)).findByTimestampLargerThanorEqual(any());
    }

}
