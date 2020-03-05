package com.zenhomes.electricity;

/**
 * Main class for start of the application.
 *
 * @author Deepak Srinivas
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CodingElectricityConsumptionApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CodingElectricityConsumptionApplication.class, args);
    }

}
