package com.zenhomes.electricity.mapper;

/**
 * DTO Mapper class for CreateCounterDTO .
 *
 * @author Deepak Srinivas
 */
import com.zenhomes.electricity.dto.CreateCounterDTO;
import com.zenhomes.electricity.model.ElectricityCounters;

public class CreateCounterMapper
{

    /**
     * Method to map ElectricityCounters DO with CreateCounterDTO DTO object
     * @param electricityDO
     * @return CreateCounterDTO
     */
    public static CreateCounterDTO makeCreateCounterDTO(ElectricityCounters electricityDO)
    {
        CreateCounterDTO.CreateCounterDTOBuilder createCounterDTOBuilder =
            CreateCounterDTO
                .newBuilder()
                .setCounterId(electricityDO.getCounterId())
                .setVillageId(electricityDO.getVillage().getId());

        return createCounterDTOBuilder.createCounterDTO();
    }
}
