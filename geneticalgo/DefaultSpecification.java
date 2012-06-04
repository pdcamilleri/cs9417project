package geneticalgo;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class defaultSpecification implements ProblemSpecification {

	private static final int LENGTH_OF_HYPOTHESIS = 100;
	
	public FitnessFunction getFitnessFunction() {
		return new defaultFitnessFunction();
	}

	public Map<String, Integer> generateHypotheses (int p) {
		 Map<String, Integer> map = new HashMap<String, Integer>();
	        for (int i = 0; i < p; i++) {
//	            String bitString = getRandomBitString();
	            Random r = new Random();
	            String s = "";
	            for (int j = 0; j < LENGTH_OF_HYPOTHESIS; j++) { // TODO what is the length of each hypo? needs to be a variable
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