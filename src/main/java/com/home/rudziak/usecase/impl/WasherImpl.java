package com.home.rudziak.usecase.impl;

import com.home.rudziak.usecase.Washer;
import com.home.rudziak.usecase.model.Car;

public class WasherImpl implements Washer {

    @Override
    public void wash(Car car) {
        System.out.println("I am washing that car " + car.getModel());
    }
}
