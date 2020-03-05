package com.zenhomes.electricity.model;

/**
 * Domain class for Electricity Counters .
 *
 * @author Deepak Srinivas
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "electricity_counter")
@Getter
@Setter
@NoArgsConstructor
public class ElectricityCounters implements Serializable
{

    @Id
    @Column(name = "id")
    private BigInteger counterId;

    @Column(precision = 10, scale = 3)
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "village_id", referencedColumnName = "id")
    private Village village;

    @CreationTimestamp
    @Column
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column
    private LocalDateTime updateDateTime;

    public ElectricityCounters(BigInteger counterId, BigDecimal amount)
    {
        this.counterId = counterId;
        this.amount = amount;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ElectricityCounters that = (ElectricityCounters) o;
        return Objects.equals(counterId, that.counterId)
            &&
            Objects.equals(amount, that.amount);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(counterId, amount);
    }
}
