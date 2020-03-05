package com.zenhomes.electricity.dto;

import java.math.BigInteger;

import com.zenhomes.electricity.model.Village;

import lombok.Data;

@Data
public class CounterDetailsResponse
{

    private BigInteger id;
    private Village village;
}
