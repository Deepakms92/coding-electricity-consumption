package com.zenhomes.electricity.service.village;

import com.zenhomes.electricity.dto.CounterDetailsResponse;
import com.zenhomes.electricity.exception.ConstraintsViolationException;
import com.zenhomes.electricity.model.Village;

public interface VillageService
{
    Village createVillage(Village village) throws ConstraintsViolationException;

    CounterDetailsResponse getVillageDetails(String counterId);

}
