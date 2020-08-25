package com.company.simulation;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class SimulationResult {

    private boolean userWin;
    private int firstPick;
    private int secondPick;
    private int hostPick;
    private ArrayList<Integer> hostOptions;
    private ArrayList<Integer> rePickOptions;

    public SimulationResult(){
    }

    public boolean isUserWin() {
        return userWin;
    }

    public void setUserWin(boolean userWin) {
        this.userWin = userWin;
    }

    public int getFirstPick() {
        return firstPick;
    }

    public void setFirstPick(int firstPick) {
        this.firstPick = firstPick;
    }

    public int getSecondPick() {
        return secondPick;
    }

    public void setSecondPick(int secondPick) {
        this.secondPick = secondPick;
    }

    public int getHostPick() {
        return hostPick;
    }

    public void setHostPick(int hostPick) {
        this.hostPick = hostPick;
    }

    public ArrayList<Integer> getHostOptions() {
        return hostOptions;
    }

    public void setHostOptions(ArrayList<Integer> hostOptions) {
        this.hostOptions = hostOptions;
    }

    public ArrayList<Integer> getRePickOptions() {
        return rePickOptions;
    }

    public void setRePickOptions(ArrayList<Integer> rePickOptions) {
        this.rePickOptions = rePickOptions;
    }


}