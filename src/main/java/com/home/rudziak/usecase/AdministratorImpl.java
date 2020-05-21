package com.home.rudziak.usecase;

public class AdministratorImpl implements Administrator {

    @Override
    public void register(Car car) {
        System.out.println(car.getModel() + " is registered");
    }

    @Override
    public void giveBack(Car car) {
        System.out.println("Get your fucking car back " + car.getModel());
    }
}
