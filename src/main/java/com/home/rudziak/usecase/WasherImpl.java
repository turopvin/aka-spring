package com.home.rudziak.usecase;

public class WasherImpl implements Washer {

    @Override
    public void wash(Car car) {
        System.out.println("I am washing that car " + car.getModel());
    }
}
