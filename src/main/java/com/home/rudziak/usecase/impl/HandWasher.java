package com.home.rudziak.usecase.impl;

import com.home.rudziak.usecase.Washer;
import com.home.rudziak.usecase.model.Car;

public class HandWasher implements Washer {

    @Override
    public void wash(Car car) {
        System.out.println("We are washing the car " + car.getModel() + " only using hands");
    }
}
