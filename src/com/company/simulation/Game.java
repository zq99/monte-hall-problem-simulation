package com.company.simulation;

import com.company.model.Car;
import com.company.model.Door;
import com.company.model.Goat;
import com.company.model.Prize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private final Random random = new Random();
    private final HashMap<Integer, Door> doorMap = new HashMap<>();
    private final ArrayList<Integer> goatDoorIndex = new ArrayList<>();
    private boolean hasCarBeenAssigned = false;
    private final int doorCount;
    private int counter=1;

    private Game(int doorCount){
        this.doorCount = doorCount;
    }

    public Door getDoor(int index){
        return this.getDoors().get(index);
    }

    public ArrayList<Integer> getGoatDoorIndex(){
        return this.goatDoorIndex;
    }

    public int getDoorCount(){
        return this.doorMap.size();
    }

    public HashMap<Integer,Door> getDoors(){
        return this.doorMap;
    }


    public boolean isLastSpaceLeft(){
        return this.getUnassignedDoors() == 1;
    }

    public int getUnassignedDoors(){
        return doorCount - doorMap.size();
    }

    public void addDoor(Door door){
        this.doorMap.put(counter,door);
        if(door.hasCar()) {
            hasCarBeenAssigned = true;
        }else{
            goatDoorIndex.add(counter);
        }
        this.counter++;
    }

    public boolean isCarAllocated(){
        return hasCarBeenAssigned;
    }

    public static Game createInstance(int doorCount){
        Game game = new Game(doorCount);
        for(int i=0;i<doorCount;i++){
            if(game.isCarAllocated()){
                game.addDoor(new Door(new Goat()));
            }else{
                if(game.isLastSpaceLeft()){
                    game.addDoor(new Door(new Car()));
                }else{
                    Prize prize = game.getRandomPrize();
                    game.addDoor(new Door(prize));
                }
            }
        }
        return game;
    }

    private Prize getRandomPrize(){
        return random.nextBoolean() ? new Car() : new Goat();
    }

}
