package com.home.rudziak.usecase.interfaces.impl;

import com.home.rudziak.core.configurator.annotations.InjectProperty;
import com.home.rudziak.usecase.interfaces.Recommendator;

public class RecommendatorImpl implements Recommendator {

    @InjectProperty
    private String hotel;

    public RecommendatorImpl() {
        System.out.println("Recommendator was created");    }

    @Override
    public void recommend() {
        System.out.println("Stay and have the best night in " + hotel);
    }
}
