package com.home.rudziak.usecase;

import com.home.rudziak.core.ObjectFactory;
import com.home.rudziak.usecase.model.Car;

public class AutoTechnicalService {

    private Administrator administrator = ObjectFactory.getInstance().createObject(Administrator.class);
    private Washer washer = ObjectFactory.getInstance().createObject(Washer.class);

    public void serve(Car car) {
        administrator.register(car);
        service(car);
        washer.wash(car);
        administrator.giveBack(car);
    }

    private void service(Car car) {
        System.out.println(car.getModel() + " is served!");
    }
}
