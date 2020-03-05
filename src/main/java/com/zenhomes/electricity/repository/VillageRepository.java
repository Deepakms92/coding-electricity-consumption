package com.zenhomes.electricity.repository;

/**
 * Repository class for Village .
 *
 * @author Deepak Srinivas
 */

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenhomes.electricity.model.Village;

public interface VillageRepository extends JpaRepository<Village, BigInteger>
{

}
