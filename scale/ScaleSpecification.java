package scale;

import geneticalgo.FitnessFunction;
import geneticalgo.GeneticOperators;
import geneticalgo.ProblemSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ScaleSpecification implements ProblemSpecification {

    private final int LENGTH_OF_BITSTRING = 14;
    
    public FitnessFunction getFitnessFunction() {
        return new ScaleFitnessFunction();
    }

    @Override
    public Map<String, Integer> generateHypotheses(int p) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < p; i++) {
            Random r = new Random();
            String s = "";
            for (int j = 0; j < LENGTH_OF_BITSTRING; j++) {
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

    @Override
    public GeneticOperators getOperators() {
        return new ScaleOperators();
    }

}
