package balanceScale;

import geneticalgo.GeneticOperators;

import java.util.Map;
import java.util.Random;

public class BalanceScaleOperators extends GeneticOperators {
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
            int crossoverPoint = 1 + random.nextInt(mother.length() - 4); // -3 so we dont produce invalid classifications like 101

            // let the mating begin!
            String boy = father.substring(0, crossoverPoint) + mother.substring(crossoverPoint);
            String girl = mother.substring(0, crossoverPoint) + father.substring(crossoverPoint);
            nextHypothesises.put(boy, null);
            nextHypothesises.put(girl, null);
        }
    }
    
    public void mutate(Map<String, Integer> nextHypothesises, double m, int p) {
        double numberOfHypothesisesToMutate = m * p;

        // welcome to Xavier's school for gifted hypotheses.
        String[] genePool = new String[nextHypothesises.keySet().size()];
        nextHypothesises.keySet().toArray(genePool);
        nextHypothesises.clear();
        Random random = new Random();
        for (int i = 0; i < numberOfHypothesisesToMutate; i++) {
            int mutantIndex = random.nextInt(genePool.length);
            int randomInt = random.nextInt(genePool[0].length()-3); // -3 so we dont produce invalid classifications like 101
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
}
