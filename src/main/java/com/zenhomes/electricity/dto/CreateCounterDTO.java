package com.zenhomes.electricity.dto;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CreateCounterDTO
{

    @Getter
    private BigInteger counterId;

    @Getter
    private BigInteger villageId;

    public static CreateCounterDTO.CreateCounterDTOBuilder newBuilder()
    {
        return new CreateCounterDTO.CreateCounterDTOBuilder();
    }

    public static class CreateCounterDTOBuilder
    {
        private BigInteger counterId;
        private BigInteger villageId;

        public CreateCounterDTOBuilder setCounterId(BigInteger counterId)
        {
            this.counterId = counterId;
            return this;
        }


        public CreateCounterDTOBuilder setVillageId(BigInteger villageId)
        {
            this.villageId = villageId;
            return this;
        }


        public CreateCounterDTO createCounterDTO()
        {
            return new CreateCounterDTO(counterId, villageId);
        }
    }
}
