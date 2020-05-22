package com.home.rudziak;

import com.home.rudziak.core.ApplicationContext;
import com.home.rudziak.core.ApplicationRunner;
import com.home.rudziak.core.ObjectFactory;
import com.home.rudziak.usecase.AutoTechnicalService;
import com.home.rudziak.usecase.interfaces.Washer;
import com.home.rudziak.usecase.interfaces.impl.AutomatedWasher;
import com.home.rudziak.usecase.model.Car;

import java.util.HashMap;
import java.util.Map;

/**
 * Usecase example
 *
 */
public class App
{
    public static void main( String[] args ) {
        final ApplicationContext context = ApplicationRunner
                .run("com.home", new HashMap<>(Map.of(Washer.class, AutomatedWasher.class)));
        AutoTechnicalService service = context.getObject(AutoTechnicalService.class);
        service.serve(new Car("BMW M5 1994"));
    }
}
