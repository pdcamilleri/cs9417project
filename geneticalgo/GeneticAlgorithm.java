package geneticalgo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *  Implementation of the core Genetic Algorithm.
 *
 */

public class GeneticAlgorithm {
    
    ProblemSpecification problemSpecification;
    
    private int fitnessThreshold;
    private int populationSize;
    private double crossoverRate;
    private double mutationRate;
    
    private final int MAX_RUNTIME = 10000;
    
    public GeneticAlgorithm(ProblemSpecification problemSpecification) {
        this.problemSpecification = problemSpecification;
    }

    /**
     * @param fitnessThreshold - point at which the algorithm will stop execution
     * @param populationSize - number of initial hypothesis to generate
     * @param crossoverRate - crossover rate
     * @param mutationRate - mutation rate
     */
    public void execute(int fitnessThresholdIn, int pIn, double rIn, double mIn) {
        
    	this.fitnessThreshold = fitnessThresholdIn;
    	this.populationSize = pIn;
    	this.crossoverRate = rIn;
    	this.mutationRate = mIn;

        // initialise: P <- p random hypothesis
    	Map<String, Integer> hypotheses = problemSpecification.generateHypotheses(populationSize);
    	
        // for each h in P, compute fitness(h)
        for (String h : hypotheses.keySet()) {
            hypotheses.put(h, problemSpecification.getFitnessFunction().getFitness(h));
        }

        int numberOfGenerations = 0;

        // while [max,,h,, fitness(h)] < fitness_threshold
        while (SetUtilClass.maxFitness(hypotheses) < fitnessThreshold && numberOfGenerations < MAX_RUNTIME) {
            numberOfGenerations++;

            // set containing the next generation of hypotheses
            Map<String, Integer> nextHypotheses = new HashMap<String, Integer>(populationSize);

            int mean = getMean(hypotheses);
            System.out.println("mean - " + mean + " --- " + "size - " + hypotheses.size());
            
            selectNewGeneration(hypotheses, nextHypotheses, mean);

            singlePointCrossover(hypotheses, nextHypotheses, populationSize, crossoverRate);
            //uniformCrossover(hypotheses, nextHypotheses, populationSize, crossoverRate);
            //twoPointCrossover(hypotheses, nextHypotheses, populationSize, crossoverRate);

            mutate(nextHypotheses, mutationRate, populationSize);

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

    }

    /**
     * mutate: invert a randomly selected bit in m * p random hypotheses from set Ps/nextHypotheses
     * @param p 
     * @param m 
     * @param nextHypothesises 
     */
    private void mutate(Map<String, Integer> nextHypothesises, double m, int p) {
        double numberOfHypothesisesToMutate = m * p;

        // welcome to Xavier's school for gifted hypotheses.
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
            }
            genePool[mutantIndex] = new String(mutant);
        }
        
        // add bitStrings back into the set
        for (int i = 0; i < genePool.length; i++) {
            nextHypothesises.put(genePool[i], null);
        }
    }

    /**
     * roulette wheel algorithm for selecting the next generation
     * @param hypothesises
     * @param nextHypothesises
     * @param mean
     */
    private void selectNewGeneration(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int mean) {

    	Map<Integer, String> rouletteWheel = new HashMap<Integer, String>();
        int tF = 0;
        int currentTicket = 0;
        for (String key : hypothesises.keySet()) {
        	int fitness = hypothesises.get(key).intValue();
        	if (hypothesises.get(key) != null) {
        		tF = tF + hypothesises.get(key).intValue();
        	}
        	for (int i = 0; i < hypothesises.get(key).intValue(); i++) {
        		rouletteWheel.put(currentTicket + i, key);
    		}
        	currentTicket += fitness;
        }

        // how many hypothesises make it through to the next generation?
        // A: select (1 - r)p members of P to add to PS
        double size = (1 - crossoverRate) * populationSize;
        Random r = new Random();
        for (int i = 0; i < size; i++) {
        	int posOnWheel = r.nextInt(tF); // spin the wheel
        	String selectedHypo = rouletteWheel.get(Integer.valueOf(posOnWheel));
        	if (!nextHypothesises.containsKey(selectedHypo)) {
        		nextHypothesises.put(selectedHypo, hypothesises.get(selectedHypo));
        	} else {
        		i--;
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
     * singlePointCrossover: probabilistically select r*p/2 pairs of hypotheses from P.
     * produce two offspring for each pair using singlePointCrossover operator, add
     * to Ps/nextHypotheses
     * 
     * @param hypothesises
     * @param nextHypothesises
     * @param r 
     * @param p 
     */
    private void singlePointCrossover(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int p, double r) {
        int numPairs = (int) (r*p) / 2;
        Random random = new Random();
        String[] genePool = new String[hypothesises.keySet().size()];
        hypothesises.keySet().toArray(genePool);
        
        // perform crossovers
        for (int i = 0; i < numPairs; i++) {
            // select two random parents (note, mother == father is possible)
            String mother = genePool[random.nextInt(genePool.length)]; 
            String father = genePool[random.nextInt(genePool.length)];
            int crossoverPoint = random.nextInt(mother.length());
            
            // let the mating begin!
            String boy = father.substring(0, crossoverPoint) + mother.substring(crossoverPoint);
            String girl = mother.substring(0, crossoverPoint) + father.substring(crossoverPoint);
            nextHypothesises.put(boy, null);
            nextHypothesises.put(girl, null);
        }
    }


    /**
     * select non-contiguous bits from parents.
     * 
     * @param hypothesises
     * @param nextHypothesises
     * @param p
     * @param r
     */
    @SuppressWarnings("unused")
    private void uniformCrossover(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int p, double r) {
        int numPairs = (int) (r*p) / 2;
        Random random = new Random();
        String[] genePool = new String[hypothesises.keySet().size()];
        hypothesises.keySet().toArray(genePool);
        
        // perform crossovers
        for (int i = 0; i < numPairs; i++) {
            String mother = genePool[random.nextInt(genePool.length)];
            String father = genePool[random.nextInt(genePool.length)];
            String boy  = "";
            String girl = "";

            for (int j = 0; j < mother.length(); j++) {
                if (random.nextBoolean()) {
                    boy  += mother.charAt(j);
                    girl += father.charAt(j);
                } else {
                    girl += mother.charAt(j);
                    boy  += father.charAt(j);
                }
            }
            nextHypothesises.put(boy, null);
            nextHypothesises.put(girl, null);
        }
    }

    @SuppressWarnings("unused")
    private void twoPointCrossover(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int p, double r) {
        int numPairs = (int)  (r*p) / 2;
        Random random = new Random();
        String[] genePool = new String[hypothesises.keySet().size()];
        hypothesises.keySet().toArray(genePool);
        
        // perform crossovers
        for (int i = 0; i < numPairs; i++) {
            String mother = genePool[random.nextInt(genePool.length)];
            String father = genePool[random.nextInt(genePool.length)];

            int firstCrossoverPoint = 0, secondCrossoverPoint = 0;
            // find two distinct
            int a = random.nextInt(mother.length());
            int b = random.nextInt(mother.length());
            if (a > b) {
                firstCrossoverPoint = b;
                secondCrossoverPoint = a;
            } else if (a < b) {
                firstCrossoverPoint = a;
                secondCrossoverPoint = b;
            }

            // let the mating begin!
            String boy = father.substring(0, firstCrossoverPoint) + mother.substring(firstCrossoverPoint, secondCrossoverPoint) + father.substring(secondCrossoverPoint);
            String girl = mother.substring(0, firstCrossoverPoint) + father.substring(firstCrossoverPoint, secondCrossoverPoint) + mother.substring(secondCrossoverPoint);
            nextHypothesises.put(boy, null);
            nextHypothesises.put(girl, null);
        }
    }
}
