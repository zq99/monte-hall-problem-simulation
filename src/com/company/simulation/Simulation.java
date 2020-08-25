package com.company.simulation;

import com.company.common.Util;

import java.util.ArrayList;

public class Simulation {
    private final Game game;

    public Simulation(Game game) {
        this.game = game;
    }

    public SimulationStats runSimulation(int total, boolean stickToFirst) {
        /*
           this is the main part determines the result of running the game N times
           the return value is the percentage of wins
         */

        SimulationStats simulationStats = new SimulationStats();
        int wins = 0;
        int loses = 0;
        for (int i = 0; i < total; i++) {
            SimulationResult simulationResult = getSimulationResult(stickToFirst, this.game);
            wins += (simulationResult.isUserWin()) ? 1 : 0;
            loses += (!simulationResult.isUserWin()) ? 1 : 0;
        }
        simulationStats.setCorrect(wins);
        simulationStats.setIncorrect(loses);
        simulationStats.setPercentageWins(((float) wins / total) * 100);
        return simulationStats;
    }

    private SimulationResult getSimulationResult(boolean stickToFirstGuess, Game game) {

        //records the results here
        SimulationResult simulationResult = new SimulationResult();

        // contestant chooses the door
        int contestantPick = Util.getRandomNumberInRange(1, game.getDoorCount());
        simulationResult.setFirstPick(contestantPick);

        // host picks a door that is not another door to show user that is not the car
        RandomChoice hostPick = chooseRandomFromChoices(getOtherOptionsForHostToShowUser(contestantPick));
        simulationResult.setHostOptions(hostPick.options);
        simulationResult.setHostPick(hostPick.choice);

        boolean userWin;
        if (stickToFirstGuess) {

            // user has to stick with first guess
            userWin = game.getDoor(contestantPick).hasCar();

        } else {

            // user chooses another door this can not be the same as the first
            // also is cannot be the open door that the host has picked and shown to be a goat

            RandomChoice userPickAgain = chooseRandomFromChoices(getRePickOptions(contestantPick, hostPick.choice));
            simulationResult.setHostOptions(userPickAgain.options);
            simulationResult.setHostPick(userPickAgain.choice);
            userWin = game.getDoor(userPickAgain.choice).hasCar();
            simulationResult.setSecondPick(userPickAgain.choice);

        }
        simulationResult.setUserWin(userWin);
        return simulationResult;
    }


    private RandomChoice chooseRandomFromChoices(ArrayList<Integer> options) {

        // helper function to pick random from a list of door numbers
        int randomOptionIndex = (options.size() == 1) ? 0 : Util.getRandomNumberInRange(0, options.size() - 1);
        int choice = options.get(randomOptionIndex);
        return new RandomChoice(choice, options);
    }

    private static class RandomChoice {

        // custom class to return random choice
        // this is so the choices the program chooses from can be tracked for testing

        private final int choice;
        private final ArrayList<Integer> options;

        public RandomChoice(int choice, ArrayList<Integer> options) {
            this.choice = choice;
            this.options = options;
        }
    }

    private ArrayList<Integer> getOtherOptionsForHostToShowUser(int excludeUserChoice) {

        // return a list of possible doors for the host to choose from that the
        // user has did not pick. The host will only select a door they know to have
        // a goat behind

        ArrayList<Integer> hostOptions = new ArrayList<>();
        for (int i = 0; i < game.getGoatDoorIndex().size(); i++) {
            int option = game.getGoatDoorIndex().get(i);
            if (option != excludeUserChoice && !game.getDoors().get(option).hasCar()) {
                hostOptions.add(option);
            }
        }
        return hostOptions;
    }

    private ArrayList<Integer> getRePickOptions(int userPick, int hostPick) {

        // return a list of doors the user can choose from that have not been opened
        // exclude the users first choice and the host's choice

        ArrayList<Integer> rePickOptions = new ArrayList<>();
        for (int i = 1; i <= game.getDoors().size(); i++) {
            if (i != userPick && i != hostPick) {
                rePickOptions.add(i);
            }
        }
        return rePickOptions;
    }
}
