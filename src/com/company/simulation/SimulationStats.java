package com.company.simulation;

@SuppressWarnings("ALL")
public class SimulationStats {
    private float percentageWins;
    private int correct;
    private int incorrect;

    public float getPercentageWins() {
        return percentageWins;
    }

    public void setPercentageWins(float percentageWins) {
        this.percentageWins = percentageWins;
    }

    public int getCorrectCount() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrectCount() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }
}
