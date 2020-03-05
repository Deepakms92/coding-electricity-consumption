package com.zenhomes.electricity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Village not found..")
public class VillageNotFoundException extends RuntimeException
{
    public VillageNotFoundException(String message)
    {
        super(message);
    }
}
