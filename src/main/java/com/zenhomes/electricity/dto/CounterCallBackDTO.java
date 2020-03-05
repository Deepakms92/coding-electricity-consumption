package com.zenhomes.electricity.dto;

/**
 * DTO class for Counter Back
 *
 * @author Deepak Srinivas
 */
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CounterCallBackDTO
{

    @Getter
    private BigInteger counterId;

    @Getter
    @NotNull(message = "consumption cannot be null")
    private BigDecimal amount;

    public static CounterCallBackDTOBuilder newBuilder()
    {
        return new CounterCallBackDTOBuilder();
    }

    public static class CounterCallBackDTOBuilder
    {
        private BigInteger counterId;
        private BigDecimal amount;

        public CounterCallBackDTOBuilder setCounterId(BigInteger counterId)
        {
            this.counterId = counterId;
            return this;
        }


        public CounterCallBackDTOBuilder setAmount(BigDecimal amount)
        {
            this.amount = amount;
            return this;
        }


        public CounterCallBackDTO createCounterCallBackDTO()
        {
            return new CounterCallBackDTO(counterId, amount);
        }
    }
}
