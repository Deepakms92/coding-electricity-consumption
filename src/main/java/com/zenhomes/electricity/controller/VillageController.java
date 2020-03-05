package com.zenhomes.electricity.controller;

/**
 * Village controller , all operations with village goes here
 *
 * @author Deepak Srinivas
 */

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zenhomes.electricity.dto.VillageDTO;
import com.zenhomes.electricity.exception.ConstraintsViolationException;
import com.zenhomes.electricity.mapper.VillageDTOMapper;
import com.zenhomes.electricity.model.Village;
import com.zenhomes.electricity.service.village.VillageService;

@RestController
public class VillageController
{
    private final VillageService villageService;

    @Autowired
    public VillageController(final VillageService villageService)
    {
        this.villageService = villageService;
    }


    @PostMapping(path = "/create_village")
    @ResponseStatus(HttpStatus.CREATED)
    public VillageDTO createVillage(@Valid @RequestBody Village village) throws ConstraintsViolationException
    {
        return VillageDTOMapper.makeVillageDTO(villageService.createVillage(village));
    }
}
