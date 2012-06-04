package geneticalgo;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class DefaultSpecification implements ProblemSpecification {

	private static final int LENGTH_OF_HYPOTHESIS = 100;
	
	public FitnessFunction getFitnessFunction() {
		return new DefaultFitnessFunction();
	}

	public Map<String, Integer> generateHypotheses (int p) {
		 Map<String, Integer> map = new HashMap<String, Integer>();
	        for (int i = 0; i < p; i++) {
	            Random r = new Random();
	            String s = "";
	            for (int j = 0; j < LENGTH_OF_HYPOTHESIS; j++) {
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