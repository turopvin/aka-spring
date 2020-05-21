package com.home.rudziak;

import com.home.rudziak.usecase.AutoTechnicalService;
import com.home.rudziak.usecase.Car;

/**
 * Usecase example
 *
 */
public class App
{
    public static void main( String[] args ) {

        AutoTechnicalService service = new AutoTechnicalService();
        service.serve(new Car("BMW M5 1994"));
    }
}
