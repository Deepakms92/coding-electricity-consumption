package com.zenhomes.electricity.dto;

/**
 * DTO class for Village.
 *
 * @author Deepak Srinivas
 */
import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class VillageDTO
{

    @Getter
    private BigInteger id;

    @Getter
    @NotNull(message = "village name cannot be null")
    private String villageName;

    @Getter
    private BigInteger counterId;

    public static VillageDTO.VillageDTOBuilder newBuilder()
    {
        return new VillageDTO.VillageDTOBuilder();
    }

    public static class VillageDTOBuilder
    {
        private BigInteger id;
        private String villageName;
        private BigInteger counterId;

        public VillageDTOBuilder setId(BigInteger id)
        {
            this.id = id;
            return this;
        }


        public VillageDTOBuilder setVillageName(String villageName)
        {
            this.villageName = villageName;
            return this;
        }


        public VillageDTOBuilder setCounterId(BigInteger counterId)
        {
            this.counterId = counterId;
            return this;
        }


        public VillageDTO createVillageDTO()
        {
            return new VillageDTO(id, villageName, counterId);
        }
    }
}
