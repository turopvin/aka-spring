package com.home.rudziak.usecase;

public class AutoTechnicalService {

    private Administrator administrator = new AdministratorImpl();
    private Washer washer = new WasherImpl();

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
