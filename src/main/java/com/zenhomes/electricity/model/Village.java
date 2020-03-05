package com.zenhomes.electricity.model;
/**
 * Domain class for Village .
 *
 * @author Deepak Srinivas
 */

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "village")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Village implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(nullable = false)
    private String name;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Village village = (Village) o;
        return Objects.equals(id, village.id)
            &&
            Objects.equals(name, village.name);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }
}
