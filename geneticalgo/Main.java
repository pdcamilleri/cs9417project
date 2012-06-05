package geneticalgo;

import java.util.HashMap;
import java.util.Map;

import balanceScale.BalanceScaleOperators;
import balanceScale.BalanceScaleSpecification;
import mushroom.MushroomOperators;
import mushroom.MushroomSpecification;

public class Main {

    public static void main(String[] args) {

        ProblemSpecification problemSpecification = null;
        
         Map<String, Integer> bestHypotheses = new HashMap<String, Integer>();

        if (args.length < 2) {
            System.out.println("Must have command line argument of \"balanceScale\" or \"mushroom\" then # times to run the GA");
            return;
        }

        int threshold = 0;
        int populationSize = 0;
        double crossoverRate = 0;
        double mutationRate = 0;
        double trainingDataSize = 1;

        if (args[0].startsWith("b")) {
            problemSpecification = new BalanceScaleSpecification();
            threshold = 400;
            populationSize = 50;
            crossoverRate = 0.1;
            mutationRate = 0.01;
            trainingDataSize = 625;
        } else if (args[0].startsWith("m")) {
            problemSpecification = new MushroomSpecification();
            threshold = 55;
            populationSize = 50;
            crossoverRate = 0.2;
            mutationRate = 0.05;
            trainingDataSize = 8124;
        }

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(problemSpecification);
        
        for (int i = 0; i < Integer.parseInt(args[1]); i++ ) {
            geneticAlgorithm.execute(threshold, populationSize, crossoverRate, mutationRate, problemSpecification.getOperators(), bestHypotheses);
        }
        
        System.out.println("\nBest hypothesis from each run of the algorithm: ");
        for (String h : bestHypotheses.keySet()) {
            System.out.println(h + " fitness: " + ((double)bestHypotheses.get(h) / trainingDataSize * 100) + "%" );
        }
        
        System.out.println("Closing program...");
    }

}
