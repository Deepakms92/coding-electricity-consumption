package com.zenhomes.electricity.dto;

/**
 * DTO class for Counter Consumption Report
 *
 * @author Deepak Srinivas
 */
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CounterReportDTO
{

    @Getter
    private String villageName;

    @Getter
    private BigDecimal consumption;

    public static CounterReportDTOBuilder newBuilder()
    {
        return new CounterReportDTOBuilder();
    }

    public static class CounterReportDTOBuilder
    {
        private String villageName;
        private BigDecimal consumption;

        public CounterReportDTOBuilder setVillageName(String villageName)
        {
            this.villageName = villageName;
            return this;
        }


        public CounterReportDTOBuilder setConsumption(BigDecimal consumption)
        {
            this.consumption = consumption;
            return this;
        }


        public CounterReportDTO createCounterREportDTO()
        {
            return new CounterReportDTO(villageName, consumption);
        }
    }
}
