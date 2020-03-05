package com.zenhomes.electricity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Time format  not found..")
public class TimeFormatException extends RuntimeException
{
    public TimeFormatException(String message)
    {
        super(message);
    }

}
