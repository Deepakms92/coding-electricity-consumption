package com.zenhomes.electricity.controller;

/**
 * Electricity Counter controller , all operations with electricity counter goes here
 *
 * @author Deepak Srinivas
 */

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.zenhomes.electricity.dto.CounterCallBackDTO;
import com.zenhomes.electricity.dto.CounterReportDTO;
import com.zenhomes.electricity.dto.CreateCounterDTO;
import com.zenhomes.electricity.exception.ConstraintsViolationException;
import com.zenhomes.electricity.mapper.CounterCallBackDTOMapper;
import com.zenhomes.electricity.mapper.CounterReportDTOMapper;
import com.zenhomes.electricity.mapper.CreateCounterMapper;
import com.zenhomes.electricity.model.ElectricityCounters;
import com.zenhomes.electricity.service.counter.ElectricityCounterService;

@RestController
public class ElectricityCounterController
{

    private final ElectricityCounterService electricityCounterService;

    @Autowired
    public ElectricityCounterController(final ElectricityCounterService electricityCounterService)
    {
        this.electricityCounterService = electricityCounterService;
    }


    @PostMapping(path = "/counter_callback")
    @ResponseStatus(HttpStatus.CREATED)
    public CounterCallBackDTO counterCallBack(@Valid @RequestBody ElectricityCounters electricityCounters) throws ConstraintsViolationException
    {
        return CounterCallBackDTOMapper.makeCounterCallBackDTO(electricityCounterService.counterCallback(electricityCounters));
    }


    @PostMapping(path = "/create_counter/{villageId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCounterDTO createCounter(@Valid @RequestBody ElectricityCounters electricityCounters, @PathVariable("villageId") BigInteger villageId)
        throws ConstraintsViolationException
    {
        return CreateCounterMapper.makeCreateCounterDTO(electricityCounterService.createCounter(electricityCounters, villageId));
    }


    @GetMapping(path = "/consumption_report")
    public List<CounterReportDTO> getConsumptionReport(@RequestParam("duration") String duration)
    {
        return CounterReportDTOMapper.makeListCounterReportDTO(electricityCounterService.getReport(duration));
    }
}
