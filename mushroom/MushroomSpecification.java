package mushroom;

import geneticalgo.FitnessFunction;
import geneticalgo.ProblemSpecification;

public class MushroomSpecification implements ProblemSpecification {

    public FitnessFunction getFitnessFunction() {
        return new MushoomFitnessFunction();
    }
    
    public Map<String, Integer> generateHypotheses(int p, int lengthOfBitStrings) {
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


}
