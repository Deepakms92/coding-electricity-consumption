package com.zenhomes.electricity.repository;

/**
 * Repository class for Electricity Counters .
 *
 * @author Deepak Srinivas
 */
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zenhomes.electricity.model.ElectricityCounters;

@Repository
public interface ElectricityCounterRepository extends JpaRepository<ElectricityCounters, BigInteger>
{

    /**
     * Method to get the list of Electricity counters amount in last given time.
     * @param updateDateTime
     * @return list of Electricity counters.
     */
    @Query("SELECT ec FROM ElectricityCounters ec where ec.updateDateTime >= :updateDateTime")
    List<ElectricityCounters> findByTimestampLargerThanorEqual(@Param("updateDateTime") LocalDateTime updateDateTime);

}
