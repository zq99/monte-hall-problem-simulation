package com.company.model;

@SuppressWarnings("ALL")
public class Car implements Prize {
    @Override
    public String description() {
        return "I am a Car!";
    }
}
