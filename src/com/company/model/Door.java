package com.company.model;

public class Door {
    private final Prize prize;

    public Door(Prize prize){
        this.prize = prize;
    }

    public boolean hasCar(){
        return prize instanceof Car;
    }

}
