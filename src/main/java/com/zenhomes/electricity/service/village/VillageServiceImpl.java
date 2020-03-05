package com.zenhomes.electricity.service.village;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some Village specific things.
 * @author Deepak Srinivas
 */

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.zenhomes.electricity.dto.CounterDetailsResponse;
import com.zenhomes.electricity.exception.ConstraintsViolationException;
import com.zenhomes.electricity.model.Village;
import com.zenhomes.electricity.repository.VillageRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VillageServiceImpl implements VillageService
{

    private final RestTemplate client;

    @Value("${village.url}")
    private String villageDetailsUrl;

    private final VillageRepository villageRepository;

    @Autowired
    public VillageServiceImpl(final VillageRepository villageRepository, final RestTemplate restTemplate)
    {
        this.villageRepository = villageRepository;
        this.client = restTemplate;
    }


    /**Method to create a village .
     * @param village village which is to be created
     * @return Village
     * @throws ConstraintsViolationException
     */
    @Override
    @Transactional
    public Village createVillage(Village village) throws ConstraintsViolationException
    {

        Village villagetoSave;
        try
        {

            villagetoSave = villageRepository.save(village);

        }
        catch (DataIntegrityViolationException e)
        {
            throw new ConstraintsViolationException(e.getMessage());
        }
        return villagetoSave;
    }


    /**
     * method to fetch the VillageDetails from the given URL.
     * @param counterId
     * @return
     */
    @Override
    public CounterDetailsResponse getVillageDetails(String counterId)
    {
        CounterDetailsResponse response;
        if (counterId != null)
        {
            Map<String, String> map = new HashMap<>();
            map.put("id", counterId);
            response = client.getForObject(villageDetailsUrl, CounterDetailsResponse.class, map);
            return response;
        }
        return null;
    }
}
