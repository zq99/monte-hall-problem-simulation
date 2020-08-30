package com.company.experiment;

import com.company.simulation.Simulation;
import com.company.simulation.SimulationStats;

public class Experiment {

    private final int totalNumberOfGames;
    private final int totalNumberOfSimulations;
    private final boolean switchDoor;
    private final Simulation simulation;
    private final String experimentName;

    public Experiment(ExperimentBuilder experimentBuilder) {
        this.totalNumberOfGames = experimentBuilder.totalNumberOfGames;
        this.totalNumberOfSimulations = experimentBuilder.totalNumberOfSimulations;
        this.switchDoor = experimentBuilder.switchDoor;
        this.simulation = experimentBuilder.simulation;
        this.experimentName = experimentBuilder.experimentName;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public int getTotalNumberOfGames() {
        return totalNumberOfGames;
    }

    public int getTotalNumberOfSimulations() {
        return totalNumberOfSimulations;
    }

    public boolean isSwitchDoor() {
        return switchDoor;
    }

    public ExperimentResults runExperiment() {
        float totalWins = 0f;
        float minWin = 100f;
        float maxWin = 0f;

        for (int i = 0; i <= totalNumberOfSimulations; i++) {
            SimulationStats simulationStats = simulation.runSimulation(totalNumberOfGames, switchDoor);
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

    public static ComparisonResult compare(Experiment experiment1, Experiment experiment2){

        ComparisonResult result = new ComparisonResult();
        result.setExperiment1(experiment1);
        result.setExperiment2(experiment2);
        ExperimentResults results1 = experiment1.runExperiment();
        ExperimentResults results2 = experiment2.runExperiment();
        float margin = Math.abs(results1.getAverageWins() - results2.getAverageWins());
        result.setMargin(margin);

        if(results1.getAverageWins() > results2.getAverageWins()){
            result.setBestExperiment(experiment1);
            result.setBestResults(results1);
        }else if(results2.getAverageWins() > results1.getAverageWins()){
            result.setBestExperiment(experiment2);
            result.setBestResults(results2);
        }else{
            result.setBestExperiment(null);
        }
        return result;
    }

    public static class ExperimentBuilder {

        // default values
        private int totalNumberOfGames = 10000;
        private int totalNumberOfSimulations = 100;
        private boolean switchDoor = false;
        private final Simulation simulation;
        private String experimentName;

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

        public ExperimentBuilder switchDoor(boolean switchDoor) {
            this.switchDoor = switchDoor;
            return this;
        }

        public ExperimentBuilder setExperimentName(String experimentName) {
            this.experimentName = experimentName;
            return this;
        }


        public Experiment build() {
            return new Experiment(this);
        }



    }
}
