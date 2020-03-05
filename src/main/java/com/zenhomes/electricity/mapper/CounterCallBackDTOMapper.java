package com.zenhomes.electricity.mapper;

/**
 * DTO Mapper class for CounterCallBackDTO .
 *
 * @author Deepak Srinivas
 */
import com.zenhomes.electricity.dto.CounterCallBackDTO;
import com.zenhomes.electricity.model.ElectricityCounters;

public class CounterCallBackDTOMapper
{

    /**
     * Method to map the DO class to a DTO class.
     * @param electricityDO
     * @return CounterCallBackDTO.
     */
    public static CounterCallBackDTO makeCounterCallBackDTO(ElectricityCounters electricityDO)
    {
        CounterCallBackDTO.CounterCallBackDTOBuilder counterCallBackDTOBuilder =
            CounterCallBackDTO
                .newBuilder()
                .setCounterId(electricityDO.getCounterId())
                .setAmount(electricityDO.getAmount());

        return counterCallBackDTOBuilder.createCounterCallBackDTO();
    }

}
