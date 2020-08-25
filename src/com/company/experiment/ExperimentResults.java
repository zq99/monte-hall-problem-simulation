package com.company.experiment;

public class ExperimentResults {

    private float AverageWins=0f;
    private float minWin=100f;
    private float maxWin=0f;

    public float getAverageWins() {
        return AverageWins;
    }

    public void setAverageWins(float averageWins) {
        AverageWins = averageWins;
    }

    public float getMinWin() {
        return minWin;
    }

    public void setMinWin(float minWin) {
        this.minWin = minWin;
    }

    public float getMaxWin() {
        return maxWin;
    }

    public void setMaxWin(float maxWin) {
        this.maxWin = maxWin;
    }
}
