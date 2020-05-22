package com.home.rudziak.usecase.interfaces.impl;

import com.home.rudziak.core.ObjectFactory;
import com.home.rudziak.usecase.interfaces.Administrator;
import com.home.rudziak.usecase.interfaces.Recommendator;
import com.home.rudziak.usecase.model.Car;

public class AdministratorImpl implements Administrator {

    private Recommendator recommendator = ObjectFactory.getInstance().createObject(Recommendator.class);

    @Override
    public void register(Car car) {
        System.out.println(car.getModel() + " is registered");
    }

    @Override
    public void giveBack(Car car) {
        System.out.println("Get your fucking car back " + car.getModel());
        recommendator.recommend();
    }
}
