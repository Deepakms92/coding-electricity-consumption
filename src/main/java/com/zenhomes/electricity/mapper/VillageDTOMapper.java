package com.zenhomes.electricity.mapper;
/**
 * DTO Mapper class for VillageDTO .
 *
 * @author Deepak Srinivas
 */

import com.zenhomes.electricity.dto.VillageDTO;
import com.zenhomes.electricity.model.Village;

public class VillageDTOMapper
{

    /**
     * Method to map village DO with VillageDTO DTO object
     * @param village
     * @return VillageDTO
     */
    public static VillageDTO makeVillageDTO(Village village)
    {
        VillageDTO.VillageDTOBuilder villageDTOBuilder =
            VillageDTO
                .newBuilder()
                .setId(village.getId())
                .setVillageName(village.getName());

        return villageDTOBuilder.createVillageDTO();
    }
}
