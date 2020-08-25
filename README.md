# Monte Hall Problem Simulation

The is a java simulation built to answer the following question:

>Suppose you're on a game show, and you're given the choice of three doors: Behind one door is a car; behind the others, goats. You pick a door, say No. 1, and the host, who knows what's behind the doors, opens another door, say No. 3, which has a goat. He then says to you, "Do you want to pick door No. 2?" Is it to your advantage to switch your choice?

The solution allows you to configure the following parameters:

- Number of doors in game
- Number of games to run in the simulation
- Whether the contestant sticks with original choice
- Number of times to run simulation to get aggregate statistics

## Example

This is an example of running a 3 door game where the contestant repicks after being shown a door by the host:

        Game game = Game.createInstance(3);
        Simulation simulation = new Simulation(game);

        Experiment experiment = new Experiment.ExperimentBuilder(simulation)
                .numberOfGameRuns(10000)
                .numberOfSimulationRuns(100)
                .stickToChoice(false).build();

        ExperimentResults results = experiment.runExperiment();

        System.out.println(results.getAverageWins());
        System.out.println(results.getMaxWin());
        System.out.println(results.getMinWin());


## Further Info

- https://en.wikipedia.org/wiki/Monty_Hall_problem


