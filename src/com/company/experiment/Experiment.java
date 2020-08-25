package com.company.experiment;

import com.company.simulation.Simulation;
import com.company.simulation.SimulationStats;

public class Experiment {

    private int totalNumberOfGames;
    private int totalNumberOfSimulations;
    private boolean stickToChoice;
    private Simulation simulation;

    public Experiment(ExperimentBuilder experimentBuilder) {
        this.totalNumberOfGames = experimentBuilder.totalNumberOfGames;
        this.totalNumberOfSimulations = experimentBuilder.totalNumberOfSimulations;
        this.stickToChoice = experimentBuilder.stickToChoice;
        this.simulation = experimentBuilder.simulation;
    }

    public int getTotalNumberOfGames() {
        return totalNumberOfGames;
    }

    public int getTotalNumberOfSimulations() {
        return totalNumberOfSimulations;
    }

    public boolean isStickToChoice() {
        return stickToChoice;
    }

    public ExperimentResults runExperiment() {
        float totalWins = 0f;
        float minWin = 100f;
        float maxWin = 0f;

        for (int i = 0; i <= totalNumberOfSimulations; i++) {
            SimulationStats simulationStats = simulation.runSimulation(totalNumberOfGames, stickToChoice);
            totalWins += simulationStats.getPercentageWins();
            minWin = Math.min(simulationStats.getPercentageWins(), minWin);
            maxWin = Math.max(simulationStats.getPercentageWins(), maxWin);
        }

        float averagePercent = (totalWins / totalNumberOfSimulations);
        ExperimentResults experimentResults = new ExperimentResults();
        experimentResults.setAverageWins(averagePercent);
        experimentResults.setMaxWin(maxWin);
        experimentResults.setMinWin(minWin);
        return experimentResults;
    }

    public static class ExperimentBuilder {

        // default values
        private int totalNumberOfGames = 10000;
        private int totalNumberOfSimulations = 100;
        private boolean stickToChoice = false;
        private final Simulation simulation;

        public ExperimentBuilder(Simulation simulation) {
            this.simulation = simulation;
        }

        public ExperimentBuilder numberOfGameRuns(int numberOfGames) {
            this.totalNumberOfGames = numberOfGames;
            return this;
        }

        public ExperimentBuilder numberOfSimulationRuns(int numberOfSimulations) {
            this.totalNumberOfSimulations = numberOfSimulations;
            return this;
        }

        public ExperimentBuilder stickToChoice(boolean stickToChoice) {
            this.stickToChoice = stickToChoice;
            return this;
        }

        public Experiment build() {
            return new Experiment(this);
        }

    }
}
