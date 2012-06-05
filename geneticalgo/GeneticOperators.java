package geneticalgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneticOperators {
    /**
     * mutate: invert a randomly selected bit in m * p random hypotheses from set Ps/nextHypotheses
     * @param p
     * @param m
     * @param nextHypothesises
     */
    public void mutate(Map<String, Integer> nextHypothesises, double m, int p) {
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
    public void selectNewGeneration(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises,
                                     int mean, double crossoverRate, int populationSize) {

        ArrayList<String> rouletteWheel = new ArrayList<String>();
        int p = 0;
        for (String key : hypothesises.keySet()) {
            p++;
        	int fitness = hypothesises.get(key).intValue();
	        for (int i = 0; i < fitness; i++) {
	            rouletteWheel.add(key);
	        }
	        rouletteWheel.add(key); // for the hypothesis with 0 fitness
        } 

        // how many hypothesises make it through to the next generation?
        // A: select (1 - r)p members of P to add to PS
        double size = (1 - crossoverRate) * populationSize;
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            int posOnWheel = r.nextInt(rouletteWheel.size()); // spin the wheel
            String selectedHypo = rouletteWheel.get(posOnWheel);
            int j = posOnWheel;
            
            Integer shFitness = hypothesises.get(selectedHypo) +1;
            if (j > 0) {
            	while (rouletteWheel.get(j).equals(selectedHypo)) {
            		if (j == 0) {
            			break;
            		}
            		j--;
            	}
            }
            if (j < rouletteWheel.size() -1 && j != 0 ) {
            	j++;
            }
            int k = 0;
            while (k < shFitness) {
            	rouletteWheel.remove(j);
            	k++;
            	if (j == rouletteWheel.size()) {
            		break;
            	}
            }
            
            nextHypothesises.put(selectedHypo, hypothesises.get(selectedHypo));
            
        }
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
    public void singlePointCrossover(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int p, double r) {
    	double numPairs = (r*p) / 2;
        numPairs = (int) Math.ceil(numPairs);
        Random random = new Random();
        String[] genePool = new String[hypothesises.keySet().size()];
        hypothesises.keySet().toArray(genePool);

        // perform crossovers
        for (int i = 0; i < numPairs; i++) {
            // select two random parents (note, mother == father is possible)
            String mother = genePool[random.nextInt(genePool.length)];
            String father = genePool[random.nextInt(genePool.length)];
            int crossoverPoint = 1 + random.nextInt(mother.length() - 1 ); // no crossoverpoint 0

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
    public void uniformCrossover(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int p, double r) {
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
    public void twoPointCrossover(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int p, double r) {
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
