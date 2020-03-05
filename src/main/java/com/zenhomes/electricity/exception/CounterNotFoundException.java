package com.zenhomes.electricity.exception;

/**
 * Exception class for throwing a counter not found onesa.
 *
 * @author Deepak Srinivas
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Counter not found..")
public class CounterNotFoundException extends RuntimeException
{
    public CounterNotFoundException(String message)
    {
        super(message);
    }
}
