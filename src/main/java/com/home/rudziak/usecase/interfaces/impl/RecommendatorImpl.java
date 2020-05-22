package com.home.rudziak.usecase.interfaces.impl;

import com.home.rudziak.core.configurator.annotations.InjectProperty;
import com.home.rudziak.usecase.interfaces.Recommendator;

public class RecommendatorImpl implements Recommendator {

    @InjectProperty
    private String hotel;

    @Override
    public void recommend() {
        System.out.println("Stay and have the best night in " + hotel);
    }
}
