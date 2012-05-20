package geneticalgo;

import java.util.Map;

/**
 * Utility class for doing set based operations.
 * TODO: make a distinct Hypothesis class? which encapsulates a HashMap and all of these functions??? 
 *
 */
public class SetUtilClass {
	
	/**
	 * 
	 * @returns the value of the highest fitness present in the set
	 */
	
	public static Integer maxFitness(Map<String, Integer> hypothesises) {
		// TODO: just returns some random element in the map
		return hypothesises.values().iterator().next();
	}

	public static String maxHypothesis(Map<String, Integer> hypothesises) {
		// TODO
		return hypothesises.keySet().iterator().next();
	}

}
