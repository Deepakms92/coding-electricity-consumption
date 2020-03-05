package com.zenhomes.electricity.service.counter;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some electric counter specific things.
 * @author Deepak Srinivas
 */

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zenhomes.electricity.exception.ConstraintsViolationException;
import com.zenhomes.electricity.exception.CounterNotFoundException;
import com.zenhomes.electricity.exception.VillageNotFoundException;
import com.zenhomes.electricity.model.ElectricityCounters;
import com.zenhomes.electricity.model.Village;
import com.zenhomes.electricity.repository.ElectricityCounterRepository;
import com.zenhomes.electricity.repository.VillageRepository;
import com.zenhomes.electricity.service.village.VillageServiceImpl;
import com.zenhomes.electricity.util.TimeUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ElectricityCounterServiceImpl implements ElectricityCounterService
{

    private final ElectricityCounterRepository electricityCounterRepository;

    private final TimeUtils timeUtils;

    private final VillageRepository villageRepository;

    private final VillageServiceImpl villageService;

    @Autowired
    public ElectricityCounterServiceImpl(
        final ElectricityCounterRepository electricityCounterRepository, final TimeUtils timeUtils, final VillageRepository villageRepository,
        final VillageServiceImpl villageService)
    {
        this.electricityCounterRepository = electricityCounterRepository;
        this.timeUtils = timeUtils;
        this.villageRepository = villageRepository;
        this.villageService = villageService;
    }


    @Override
    public ElectricityCounters createCounter(ElectricityCounters electricityCounters, BigInteger villageId) throws ConstraintsViolationException
    {
        ElectricityCounters counterToSave;
        Village village = villageRepository.findById(villageId).orElseThrow(() -> new VillageNotFoundException("Village Not found"));
        electricityCounters.setVillage(village);
        try
        {
            counterToSave = electricityCounterRepository.save(electricityCounters);

        }
        catch (DataIntegrityViolationException e)
        {
            throw new ConstraintsViolationException(e.getMessage());
        }
        return counterToSave;
    }


    /**Method to save the counters with amount of the electricity consumption.,and save the village for the corresponding counterid
     * by making a call to the given API
     * @param electricityCounters ElectricityCounters that will be updated with the amount
     * @return ElectricityCounters.
     * @throws ConstraintsViolationException
     */
    @Override
    @Transactional
    public ElectricityCounters counterCallback(ElectricityCounters electricityCounters) throws ConstraintsViolationException
    {
        ElectricityCounters counterToSave;
        //Hit the given to fetch the Village
        Village village = villageService.getVillageDetails(electricityCounters.getCounterId().toString()).getVillage();
        if (village != null)
        {
            log.info("village value is " + village);
            electricityCounters.setVillage(village);
            electricityCounters.setAmount(electricityCounters.getAmount());
            try
            {
                counterToSave = electricityCounterRepository.save(electricityCounters);
            }
            catch (DataIntegrityViolationException e)
            {
                throw new ConstraintsViolationException(e.getMessage());
            }
            return counterToSave;
        }
        else
        {
            throw new VillageNotFoundException("The village is not found for the specific id");
        }

    }


    /**Method to find the electricity counters by counter id to get more information on the counter .
     * @param id id of the electricity counter
     * @return ElectricityCounters
     */
    @Override
    public ElectricityCounters getCounterInformation(BigInteger id)
    {
        return electricityCounterRepository.findById(id).orElseThrow(() -> new CounterNotFoundException("The counter trying to find out is not found"));
    }


    /**Method to get the electricity consumption of all villages in last 24hrs.
     * @param duration duration for which the report is needed.
     * @return list of electricity counters
     */
    @Override
    public List<ElectricityCounters> getReport(String duration)
    {
        long hoursAgoInMillis = timeUtils.convertTimeIntoMillis(duration);
        Instant instant = Instant.ofEpochMilli(hoursAgoInMillis);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        List<ElectricityCounters> counters = electricityCounterRepository.findByTimestampLargerThanorEqual(date);
        return counters;

    }

}
