package geneticalgo;

import java.util.Map;

/**
 * Utility class for doing set based operations.
 * TODO: make a distinct Hypothesis class? which encapsulates a HashMap and all of these functions??? 
 *
 */
public class SetUtilClass {
	
	/**
	 * @returns the value of the highest fitness present in the set
	 */
	
	public static Integer maxFitness(Map<String, Integer> hypothesises) {
        int highest = hypothesises.values().iterator().next(); // start with random element

        for (Integer i : hypothesises.values()) {
            if (i > highest) {
                highest = i;
            }
        }
        return highest;
	}

	public static String maxHypothesis(Map<String, Integer> hypothesises) {
        String best = hypothesises.keySet().iterator().next(); // start with random element

        for (String s : hypothesises.keySet()) {
            if (hypothesises.get(s) > hypothesises.get(best)) {
                best = s;
            }
        }
        return best;
	}

}
