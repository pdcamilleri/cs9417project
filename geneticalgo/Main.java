package geneticalgo;

import balanceScale.BalanceScaleSpecification;
import mushroom.MushroomSpecification;

public class Main {

    public static void main(String[] args) {

        ProblemSpecification problemSpecification = null;

        if (args.length == 0) {
            System.out
                    .println("Must have command line argument of \"balanceScale\" or \"mushroom\"");
            return;
        }

        int threshold = 0;
        int populationSize = 0;
        double crossoverRate = 0;
        double mutationRate = 0;

        if (args[0].startsWith("b")) {
            problemSpecification = new BalanceScaleSpecification();
            threshold = 600;
            populationSize = 50;
            crossoverRate = 0.1;
            mutationRate = 0.01;
        } else if (args[0].startsWith("m")) {
            problemSpecification = new MushroomSpecification();
            threshold = 55;
            populationSize = 50;
            crossoverRate = 0.2;
            mutationRate = 0.05;
        }

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(problemSpecification);
        geneticAlgorithm.execute(threshold, populationSize, crossoverRate, mutationRate);

        System.out.println("Closing program...");
    }

}
