package geneticalgo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
        Map<String, Integer> hypothesises = getRandomBitStrings(p, 100);

        // evaluate: for each h in P, compute fitness(h)
        for (String h : hypothesises.keySet()) {
            hypothesises.put(h, FitnessFunction.fitness(h));
//            System.out.println(hypothesises.get(h) + " - " + h);
        }
        // TODO: combine above steps for efficiency. i.e. get the fitness value
        // as bitStrings are put into the map

        int numberOfGenerations = 0;

        // while [max,,h,, fitness(h)] < fitness_threshold
        while (SetUtilClass.maxFitness(hypothesises) < fitnessThreshold) {
            numberOfGenerations++;
//            System.out.println("The next generation - " + numberOfGenerations);

            // the set Ps in the comments
            Map<String, Integer> nextHypothesises = new HashMap<String, Integer>(p);

            // TODO: idea, set the mean here, move selectNewGeneration to under mutate method, so that only good hypothesises are in the next generation.
            // so always moving forward. will need to do some testing of this later to see if the idea is sound.
            int mean = getMean(hypothesises);
            System.out.println("mean - " + mean);
            // select: probabilistically select (1-r)p members of P to add to Ps
            // Prob(h,,i,,) = Fitness(h,,i,,) / sum from j=1 to p of (Fitness
            // h,,j,,)
            // so the probability that hypothesis h,,i,, will be selected is
            // the fitness of h,,i,, divided by the sum of all fitnesses of all
            // hypothesis???
            // TODO: need to check the above formula, what does it mean?
            selectNewGeneration(hypothesises, nextHypothesises, mean); // weed out the weak, keep the strong

            // crossover: probabilistically select r-p/2 pairs of hypos from P.
            // produce two offspring for each pair using crossover operator, add
            // to Ps
            crossover(hypothesises, nextHypothesises, p, r);

            // mutate: invert a randomly selected bit in m * p random hypos from
            // set Ps
            mutate(nextHypothesises, m, p);

            // update: P <- Ps
            hypothesises = nextHypothesises;

            // evaluate for each h in P, compute fitness(h)
            // TODO: functionize this more since we use it twice
            for (String h : hypothesises.keySet()) {
                hypothesises.put(h, FitnessFunction.fitness(h));
//                System.out.println(hypothesises.get(h) + " - " + h);
            }

            System.out.println("improved mean - " + getMean(nextHypothesises));

            // done - end of while loop
        }
        // at this point, atleast one one hypothesis in P is above the threshold

        // return the hypo with highest fitness in P
        String best = SetUtilClass.maxHypothesis(hypothesises);

        System.out.println("Best hypo: " + best + " - " + hypothesises.get(best));
        System.out.println("Took " + numberOfGenerations + " generations");

    }

    /**
     * mutate: invert a randomly selected bit in m * p random hypos from set Ps
     * @param p 
     * @param m 
     * @param nextHypothesises 
     */
    private void mutate(Map<String, Integer> nextHypothesises, int m, int p) {
        // int numberOfHypothesisesToMutate = m * p;
        int numberOfHypothesisesToMutate = 1 + nextHypothesises.keySet().size() / 4;
        // TODO: just mutate quarter, naive implementation, until meaning of m is
        // revealed.
        // m == mutation rate? a small value like 0.1?

        // welcome to xaviers school for gifted hypothesises.
        String[] genePool = new String[nextHypothesises.keySet().size()];
        nextHypothesises.keySet().toArray(genePool);
        nextHypothesises.clear();
        Random random = new Random();
        for (int i = 0; i < numberOfHypothesisesToMutate; i++) {
            int mutantIndex = random.nextInt(genePool.length);
            int randomInt = random.nextInt(genePool[0].length());
            char[] mutant = genePool[mutantIndex].toCharArray();
            if (mutant[randomInt] == '1') {
                mutant[randomInt] = '0';
            } else if (mutant[randomInt] == '0') {
                mutant[randomInt] = '1';
            } else {
                // should not get here
                String s = null;
                s.length();
            }
            genePool[mutantIndex] = new String(mutant);
        }
        // add bitStrings back into the set
        for (int i = 0; i < genePool.length; i++) {
            nextHypothesises.put(genePool[i], null);
        }
    }

    /**
     * implementation of all of this.
     * basically selecting new hypothesises for the next generation of the algorithm
     * 
     * select: probabilistically select (1-r)p members of P to add to Ps
     * Prob(h,,i,,) = Fitness(h,,i,,) / sum from j=1 to p of (Fitness h,,j,,)
     * so the probability that hypothesis h,,i,, will be selected is
     * the fitness of h,,i,, divided by the sum of all fitnesses of all
     * hypothesis???
     * TODO: need to check the above formula, what does it mean?
     * @param nextHypothesises 
     * @param hypothesises 
     */
    private void selectNewGeneration(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int mean) {
        // only adding the best 50% to the next generation of super soldiers.
        for (String s : hypothesises.keySet()) {
            if (hypothesises.get(s) >= mean) { // we have a strong hypothesis
                nextHypothesises.put(s, hypothesises.get(s)); // it gets to live on in the new world
            }
        }
    }

    private int getMean(Map<String, Integer> hypothesises) {
        int mean = 0;
        for (Integer i : hypothesises.values()) {
            mean += i;
        }
        mean /= hypothesises.values().size();
        return mean;
    }

    /**
     * TODO should this go here? or in some sort of util class?
     */
    private Map<String, Integer> getRandomBitStrings(int p, int lengthOfBitStrings) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < p; i++) {
//            String bitString = getRandomBitString();
            Random r = new Random();
            String s = "";
            for (int j = 0; j < lengthOfBitStrings; j++) { // TODO what is the length of each hypo? needs to be a variable
                if (r.nextBoolean()) {
                    s += "1";
                } else {
                    s += "0";
                }
            }
            map.put(s, null);
        }
        return map;
    }

    /**
     * crossover: probabilistically select r-p/2 pairs of hypos from P.
     * produce two offspring for each pair using crossover operator, add
     * to Ps
     * @param hypothesises
     * @param nextHypothesises
     * @param r 
     * @param p 
     */
    private void crossover(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int p, int r) {
        int numPairs = (r-p) / 2; // TODO: how to do this "probabilistically"?
        Random random = new Random();
        String[] genePool = new String[hypothesises.keySet().size()];
        hypothesises.keySet().toArray(genePool);
        // perform crossovers
        for (int i = 0; i < numPairs; i++) {
            // select two random parents (note, mother == father is possible)
            // TODO: check whether or not the above is a problem. probably will be, since overpopulating pool with the same gene.
            String mother = genePool[random.nextInt(genePool.length)]; 
            String father = genePool[random.nextInt(genePool.length)];
            int crossoverPoint = random.nextInt(mother.length());
            
            // let the mating begin!
            // TODOls wrong tho
            String boy = father.substring(0, crossoverPoint) + mother.substring(crossoverPoint);
            String girl = mother.substring(0, crossoverPoint) + father.substring(crossoverPoint);
            nextHypothesises.put(boy, null);
            nextHypothesises.put(girl, null);
        }
    }
}




















