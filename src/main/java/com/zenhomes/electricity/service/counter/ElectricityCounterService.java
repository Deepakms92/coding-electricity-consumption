package com.zenhomes.electricity.service.counter;

import java.math.BigInteger;
import java.util.List;

import com.zenhomes.electricity.exception.ConstraintsViolationException;
import com.zenhomes.electricity.model.ElectricityCounters;

public interface ElectricityCounterService
{

    ElectricityCounters createCounter(ElectricityCounters electricityCounters,BigInteger villageId) throws ConstraintsViolationException;

    ElectricityCounters counterCallback(ElectricityCounters electricityCounters) throws ConstraintsViolationException;

    ElectricityCounters getCounterInformation(BigInteger id);

    List<ElectricityCounters> getReport(String duration);



}
