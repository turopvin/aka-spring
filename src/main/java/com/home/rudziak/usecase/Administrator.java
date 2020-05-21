package com.home.rudziak.usecase;

import com.home.rudziak.usecase.model.Car;

public interface Administrator {

    void register(Car car);

    void giveBack(Car car);
}
