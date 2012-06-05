package balanceScale;

import geneticalgo.GeneticOperators;

import java.util.Map;
import java.util.Random;

public class BalanceScaleOperators extends GeneticOperators {
    public void singlePointCrossover(Map<String, Integer> hypothesises, Map<String, Integer> nextHypothesises, int p, double r) {
        int numPairs = (int) (r*p) / 2;
        Random random = new Random();
        String[] genePool = new String[hypothesises.keySet().size()];
        hypothesises.keySet().toArray(genePool);

        // perform crossovers
        for (int i = 0; i < numPairs; i++) {
            // select two random parents (note, mother == father is possible)
            String mother = genePool[random.nextInt(genePool.length)];
            String father = genePool[random.nextInt(genePool.length)];
            int crossoverPoint = random.nextInt(mother.length() - 3); // -3 so we dont produce invalid classifications like 101

            // let the mating begin!
            String boy = father.substring(0, crossoverPoint) + mother.substring(crossoverPoint);
            String girl = mother.substring(0, crossoverPoint) + father.substring(crossoverPoint);
            nextHypothesises.put(boy, null);
            nextHypothesises.put(girl, null);
        }
    }
}
