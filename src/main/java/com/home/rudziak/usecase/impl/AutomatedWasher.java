package com.home.rudziak.usecase.impl;

import com.home.rudziak.usecase.Washer;
import com.home.rudziak.usecase.model.Car;

public class AutomatedWasher implements Washer {

    @Override
    public void wash(Car car) {
        System.out.println("Robots washed everything!");
    }
}
