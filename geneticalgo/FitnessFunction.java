package geneticalgo;

import java.util.Map;

/**
 * Single class to compute the fitness function 
 * TODO: perhaps inject this into the GA, and not have it statically called.
 * TODO: factory method?
 * TODO: can then specify the particlar fitness function to use from cmd-line?
 * TODO: note the different fitness functions possible in the spec. {roulette wheel, tournament, rank} selection
 */
public interface FitnessFunction {
    
    public int getFitness(String hypothesis);
    
}
/*
	
	// TODO: implement fitness
	public static int fitness(String hypothesis) {
        int count = 0;
        for (int i = 0; i < hypothesis.length(); i++) {
            count += (hypothesis.charAt(i) - '0');
        }
		return count;
	}

	
	public static int bsFitness(Map<String, String> hypothesis, String[] dataSet) {
		
		//increment score if the hypothesis correctly identifies a tuple
		int score = 0;
		
		for (String tuple : dataSet) {
			
			// does the hypothesis correctly classify this tuple?
			if (balanceScaleJudge(tuple, hypothesis)) {
				score++;				
			}
		}
		
		
		return score;
	}
	
	// Operators:     +,     -,     /,     *,   sqrt,    ^2
	//			   0000   0001   0010   0011    0100   0101 
	// Class:     	  L,     B,     R
	//			   0110   0111   1000
	
	private static boolean balanceScaleJudge(String tuple, Map<String, String> hypothesis) {
		
		boolean judgement = false;
		
		String[] data = tuple.split(",");
		// [1, 1, 5, 5, R]
		
		// example hypothesis: 
		//    +    *    / |    B
		// 0000 0011 0010 | 0111
		
		for (String value : data) {
			
			
		}
		
		return judgement;
	}
}
*/
