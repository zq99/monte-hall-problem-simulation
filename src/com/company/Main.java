package com.company;

import com.company.simulation.Game;
import com.company.simulation.Simulation;
import com.company.simulation.SimulationStats;

public class Main {

    public static void main(String[] args) {

        System.out.println(">User sticks with choice");
        runSimulation(3,10000,100,true);

        System.out.println(">User always changes");
        runSimulation(3,10000,100,false);

    }

    public static void runSimulation(int numberOfDoors,int totalNumberOfGames,int totalNumberOfSimulations,boolean stickToChoice){
        float totalWins=0f;
        float minWin=100f;
        float maxWin=0f;

        Game game = Game.createInstance(numberOfDoors);
        Simulation simulation = new Simulation(game);

        for (int i = 0; i <= totalNumberOfSimulations; i++) {
            SimulationStats simulationStats = simulation.runSimulation(totalNumberOfGames, stickToChoice);
            totalWins += simulationStats.getPercentageWins();
            minWin = Math.min(simulationStats.getPercentageWins(),minWin);
            maxWin = Math.max(simulationStats.getPercentageWins(), maxWin);
        }

        float averagePercent = (totalWins/totalNumberOfSimulations);
        System.out.println("Average Win Percent: " + averagePercent);
        System.out.println("Max Win Percent: " + maxWin);
        System.out.println("Min Win Percent: " + minWin);
    }



}
