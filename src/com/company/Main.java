package com.company;

import com.company.experiment.Experiment;
import com.company.experiment.ExperimentResults;
import com.company.simulation.*;

public class Main {

    public static void main(String[] args) {

        Game game = Game.createInstance(3);
        Simulation simulation = new Simulation(game);

        Experiment experiment = new Experiment.ExperimentBuilder(simulation)
                .numberOfGames(10000)
                .numberOfSimulations(100)
                .stickToChoice(false).build();

        ExperimentResults results = experiment.runExperiment();

        System.out.println(results.getAverageWins());
        System.out.println(results.getMaxWin());
        System.out.println(results.getMinWin());

    }

}
