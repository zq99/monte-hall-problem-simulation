# Monte Hall Problem Simulation

This is a java simulation built to answer the following question:

>Suppose you're on a game show, and you're given the choice of three doors: Behind one door is a car; behind the others, goats. You pick a door, say No. 1, and the host, who knows what's behind the doors, opens another door, say No. 3, which has a goat. He then says to you, "Do you want to pick door No. 2?" Is it to your advantage to switch your choice?

The solution allows you to configure the following parameters:

- Number of doors in game
- Number of games to run in the simulation
- Whether the contestant sticks with original choice
- Number of times to run simulation to get aggregate statistics

## Example

This is an example of running a 3 door game 10,000 times, where the contestant re-picks after being shown a door by the host.
The experiment is repeated 100 times, and the results are aggregated.

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

## Answer

The following code is a simple example of how to determine the answer to the original problem statement. This code uses the default settings which is to run 10,000 games for each simulation, and then aggregate the results for 100 simulations.

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

It should be noted that the above code works, because the probabilities between switching and sticking for the contestant are wide in a 3 door game.
If the difference was negligible then this 'answer' would probably need modifying.


## Further Info

- https://en.wikipedia.org/wiki/Monty_Hall_problem


