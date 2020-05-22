package com.home.rudziak.usecase.interfaces;

import com.home.rudziak.usecase.model.Car;

public interface Administrator {

    void register(Car car);

    void giveBack(Car car);
}
