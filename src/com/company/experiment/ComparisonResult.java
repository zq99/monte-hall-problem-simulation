package com.company.experiment;

public class ComparisonResult {

    private Experiment experiment1;
    private Experiment experiment2;
    private Experiment bestExperiment;
    private ExperimentResults bestResults;
    private float margin = 0f;

    public ExperimentResults getBestResults() {
        return bestResults;
    }

    public void setBestResults(ExperimentResults bestResults) {
        this.bestResults = bestResults;
    }

    public Experiment getExperiment1() {
        return experiment1;
    }

    public void setExperiment1(Experiment experiment1) {
        this.experiment1 = experiment1;
    }

    public Experiment getExperiment2() {
        return experiment2;
    }

    public void setExperiment2(Experiment experiment2) {
        this.experiment2 = experiment2;
    }

    public Experiment getBestExperiment() {
        return bestExperiment;
    }

    public void setBestExperiment(Experiment bestExperiment) {
        this.bestExperiment = bestExperiment;
    }

    public float getMargin() {
        return margin;
    }

    public void setMargin(float margin) {
        this.margin = margin;
    }


}
