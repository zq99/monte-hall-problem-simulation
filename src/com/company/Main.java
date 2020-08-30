package com.company;

import com.company.experiment.ComparisonResult;
import com.company.experiment.Experiment;
import com.company.experiment.ExperimentResults;
import com.company.simulation.*;

public class Main {

    public static void main(String[] args) {
        basicExample();
        answerProblem();
    }

    private static void basicExample(){
        /*
        This is how to run the simulation for a 3 door game
        where each simulation is for 10,000 games and the contestant switches door.
        The experiment is repeated 100 times, and the aggregated results are printed
        to the console.
        */

        Game game = Game.createInstance(3);
        Simulation simulation = new Simulation(game);

        Experiment experiment = new Experiment.ExperimentBuilder(simulation)
                .numberOfGameRuns(10000)
                .numberOfSimulationRuns(100)
                .switchDoor(true)
                .build();

        ExperimentResults results = experiment.runExperiment();

        System.out.println(results.getAverageWins());
        System.out.println(results.getMaxWin());
        System.out.println(results.getMinWin());
    }

    private static void answerProblem(){
        /*
        Suppose you're on a game show, and you're given the choice of three doors:
        Behind one door is a car; behind the others, goats. You pick a door, say No. 1, and the host,
        who knows what's behind the doors, opens another door, say No. 3, which has a goat.
        He then says to you, "Do you want to pick door No. 2?" Is it to your advantage to switch your choice?
        */

        Game game = Game.createInstance(3);
        Simulation simulation = new Simulation(game);

        Experiment experiment1 = new Experiment.ExperimentBuilder(simulation)
                .switchDoor(true).setExperimentName("Switch door")
                .build();

        Experiment experiment2 = new Experiment.ExperimentBuilder(simulation)
                .switchDoor(false).setExperimentName("Stick to first choice")
                .build();

        ComparisonResult result = Experiment.compare(experiment1,experiment2);

        if(result.getBestExperiment() == null){
            System.out.println("results are inconclusive");
        }else{
            System.out.println("The contestant should: " + result.getBestExperiment().getExperimentName());
            System.out.println("Experiment Average win %: " + result.getBestResults().getAverageWins());
            System.out.println("The margin % was: " + result.getMargin());
        }


    }

}
