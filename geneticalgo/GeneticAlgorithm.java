package geneticalgo;

import java.util.HashMap;
import java.util.Map;

/**
 * Genetic Algorithm implementation
 * 
 * Do this by generating a set of random hypothesis and testing them to see if
 * they are any good (using fitness function) if they are good (ie, above the
 * threshold), keep; if they are bad (below threshold), throw them away. replace
 * the bad guys with some better guys produced by mutating the good guys,
 * getting the good guys to breed, etc repeat the algorithm using this new set
 * of hypothesis which should be a better group than the previous eventually
 * should be able to get to a really good set of hypothesis after repeating a
 * number of times may need to repeat entire algo to avoid local maxima as the
 * initial set of hypothesis are generated at random
 * 
 */

public class GeneticAlgorithm {


    /**
     * 
     * @param fitnessThreshold - point at which the algorithm will stop execution
     * @param p - number of initial hypothesis to generate
     * @param r - some value ???
     * @param m - some value ???
     */
    public void execute(int fitnessThreshold, int p, int r, int m) {

        // initialise: P <- p random hypothesis
        // hypothesis: maps bitStrings to fitness
        Map<String, Integer> hypothesises = new HashMap<String, Integer>();
        generateRandomBitStrings(hypothesises);

        // evaluate: for each h in P, compute fitness(h)
        for (String h : hypothesises.keySet()) {
            hypothesises.put(h, FitnessFunction.fitness(h));
        }
        // TODO: combine above steps for efficiency. i.e. get the fitness value
        // as bitStrings are put into the map

        // while [max,,h,, fitness(h)] < fitness_threshold
        while (SetUtilClass.maxFitness(hypothesises) < fitnessThreshold) {
            
            // the set Ps in the comments
            Map<String, Integer> nextHypothesises = new HashMap<String, Integer>();
            
            // select: probabilistically select (1-r)p members of P to add to Ps
            // Prob(h,,i,,) = Fitness(h,,i,,) / sum from j=1 to p of (Fitness
            // h,,j,,)
            // so the probability that hypothesis h,,i,, will be selected is
            // the fitness of h,,i,, divided by the sum of all fitnesses of al
            // hypothesis???
            // TODO: need to check the above formula, what does it mean?
            selectNewGeneration();

            // crossover: probabilistically select r-p/2 pairs of hypos from P.
            // produce two offspring for each pair using crossover operator, add
            // to Ps
            crossover(hypothesises, nextHypothesises);

            // mutate: invert a randomly selected bit in m * p random hypos from
            // set Ps
            mutate();

            // update: P <- Ps

            // evaluate for each h in P, compute fitness(h)

            // done - end of while loop
        }
        // at this point, atleast one one hypothesis in P is above the threshold

        // return the hypo with highest fitness in P
        String best = SetUtilClass.maxHypothesis(hypothesises);

        System.out.println("Best hypo: " + best);

    }

    /**
     *    // mutate: invert a randomly selected bit in m * p random hypos from
            // set Ps
     */
    private void mutate() {
        // TODO Auto-generated method stub
        
    }

    /**
     * implementation of all of this.
     * basically selecting new hypothesises for the next generation of the algorithm
     * 
            // select: probabilistically select (1-r)p members of P to add to Ps
            // Prob(h,,i,,) = Fitness(h,,i,,) / sum from j=1 to p of (Fitness
            // h,,j,,)
            // so the probability that hypothesis h,,i,, will be selected is
            // the fitness of h,,i,, divided by the sum of all fitnesses of al
            // hypothesis???
            // TODO: need to check the above formula, what does it mean?
     */
    private void selectNewGeneration() {
        // TODO: implement 
    }

    /**
     * TODO should this go here? or in some sort of util class?
     */
    private void generateRandomBitStrings(Map<String, Integer> hypothesis) {
        hypothesis.put("000000000000000000000000000", null);
    }

    /**
     *             // crossover: probabilistically select r-p/2 pairs of hypos from P.
            // produce two offspring for each pair using crossover operator, add
            // to Ps
     * @param hypothesises
     * @param nextHypothesises
     */
    private void crossover(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises) {
        // TODO Auto-generated method stub
        
    }
}
