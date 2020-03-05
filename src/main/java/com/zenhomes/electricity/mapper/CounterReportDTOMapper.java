package com.zenhomes.electricity.mapper;

/**
 * DTO Mapper class for CounterReportDTO .
 *
 * @author Deepak Srinivas
 */
import java.util.List;
import java.util.stream.Collectors;

import com.zenhomes.electricity.dto.CounterReportDTO;
import com.zenhomes.electricity.model.ElectricityCounters;

public class CounterReportDTOMapper
{

    /**
     * Method convert the list of  ElectricityCounters DOs to list of Counter Report DTO's
     * @param counters
     * @return list of counters
     */
    public static List<CounterReportDTO> makeListCounterReportDTO(List<ElectricityCounters> counters)
    {
        return counters.stream().map(counter -> makeCounterReportDTO(counter)).collect(Collectors.toList());

    }


    /**
     * Method to map ElectricityCounters DO with ElectricityCounters DTO object
     * @param electricityCounters
     * @return CounterReportDTO
     */
    public static CounterReportDTO makeCounterReportDTO(ElectricityCounters electricityCounters)
    {
        CounterReportDTO.CounterReportDTOBuilder counterReportDTOBuilder =
            CounterReportDTO
                .newBuilder()
                .setVillageName(electricityCounters.getVillage().getName())
                .setConsumption(electricityCounters.getAmount());
        return counterReportDTOBuilder.createCounterREportDTO();

    }

}
