package com.home.rudziak.usecase.impl;

public class RecommendatorImpl implements Recommendator {

    @InjectProperty
    private String hotel;

    @Override
    public void recommend() {
        System.out.println("Stay and have the best night in " + hotel);
    }
}
