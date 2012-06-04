package mushroom;

import geneticalgo.FitnessFunction;
import geneticalgo.ProblemSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MushroomSpecification implements ProblemSpecification {

    public FitnessFunction getFitnessFunction() {
        return new MushoomFitnessFunction();
    }

    @Override
    public Map<String, Integer> generateHypotheses(int p) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < p; i++) {
            //            String bitString = getRandomBitString();
            Random r = new Random();
            String s = "";
            for (int j = 0; j < 127; j++) { // TODO what is the length of each hypo? needs to be a variable
                if (r.nextInt(25) == 0) {
                    s += "1";
                } else {
                    s += "0";
                }
            }
            map.put(s, null);
        }
        return map;
    }
}

