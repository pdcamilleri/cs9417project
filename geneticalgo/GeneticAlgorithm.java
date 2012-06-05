package geneticalgo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *  Implementation of the core Genetic Algorithm.
 */

public class GeneticAlgorithm {
    
    ProblemSpecification problemSpecification;
    
    private final int MAX_RUNTIME = 100;
    
    public GeneticAlgorithm(ProblemSpecification problemSpecification) {
        this.problemSpecification = problemSpecification;
    }

    /**
     * @param fitnessThreshold - point at which the algorithm will stop execution
     * @param populationSize - number of initial hypothesis to generate
     * @param crossoverRate - crossover rate
     * @param mutationRate - mutation rate
     */
    public void execute(int fitnessThreshold, int populationSize, double crossoverRate, double mutationRate, GeneticOperators operators, Map<String, Integer> bestHypotheses) {
        
        // initialise: P <- p random hypothesis
        Map<String, Integer> hypotheses = problemSpecification.generateHypotheses(populationSize);
    	
        // for each h in P, compute fitness(h)
        for (String h : hypotheses.keySet()) {
            hypotheses.put(h, problemSpecification.getFitnessFunction().getFitness(h));
        }

        int numberOfGenerations = 0;

        // while [max,,h,, fitness(h)] < fitness_threshold
        while (SetUtilClass.maxFitness(hypotheses) < fitnessThreshold && numberOfGenerations < MAX_RUNTIME) {
        	if (numberOfGenerations % 10 == 0) {
        		System.out.println("running...");
        	}
            numberOfGenerations++;

            // set containing the next generation of hypotheses
            Map<String, Integer> nextHypotheses = new HashMap<String, Integer>(populationSize);

            int mean = getMean(hypotheses);
            System.out.println("mean - " + mean + " --- " + "size - " + hypotheses.size());

            operators.selectNewGeneration(hypotheses, nextHypotheses, mean, crossoverRate, populationSize);

            while (nextHypotheses.size() < populationSize) {
            	operators.singlePointCrossover(hypotheses, nextHypotheses, populationSize, crossoverRate);
            }
            //uniformCrossover(hypotheses, nextHypotheses, populationSize, crossoverRate);
            //twoPointCrossover(hypotheses, nextHypotheses, populationSize, crossoverRate);

            operators.mutate(nextHypotheses, mutationRate, populationSize);

            // update: P <- Ps
            hypotheses = nextHypotheses;

            // evaluate for each h in P, compute fitness(h)
            for (String h : hypotheses.keySet()) {
                hypotheses.put(h, problemSpecification.getFitnessFunction().getFitness(h));
            }
            
        }
        // at this point, atleast one one hypothesis in P is above the threshold

        // return the hypothesis with highest fitness in P
        String best = SetUtilClass.maxHypothesis(hypotheses);
        System.out.println("Took " + numberOfGenerations + " generations");
        System.out.println("Best hypo: " + best + " - " + hypotheses.get(best));
        bestHypotheses.put(best, hypotheses.get(best));

    }

    private int getMean(Map<String, Integer> hypothesises) {
        int mean = 0;
        for (Integer i : hypothesises.values()) {
            mean += i;
        }
        mean /= hypothesises.values().size();
        return mean;
    }

}
