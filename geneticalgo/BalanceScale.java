package geneticalgo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BalanceScale {

	private final int LENGTH_OF_HYPOTHESIS = 3;
	
	public Map<String, String> generateHypothesis(int p) {

		//what can be in a Balance Scale hypothesis?
		
		// Operators:     +,     -,     /,     *,   sqrt,    ^2
		//			   0000   0001   0010   0011    0100   0101 
		// Class:     	  L,     B,     R
		//			   0110   0111   1000
		
		// Key thought:	A Hypothesis need not cover EVERY case. Its ok for it to not be 
		// the entire solution. We are looking for patterns, not the exact answer. Duh =\
		
		// A strong hypothesis (100% correct) would be:
		//    *    -    * |    R
		// 0011 0001 0011 | 1000
		
		// Note: could be expanded to longer + shorter hypothesis, but not sure there's a point.
		// Is it cheating to use knowledge of the real solution to get it closer to a proper answer?
		// 		It seems that having a hypothesis of length 3 fits the data very well. If we were to 
		//		include other lengths, then need to add values to potential hypothesis representations too
		
		Map<String, String>hypos = new HashMap<String, String>();
		
		for (int i = 0; i < p; i++) {
			
			Random r = new Random();
			String s = "";
			for (int j = 0; j < LENGTH_OF_HYPOTHESIS; j++) {
				int luckyDip = r.nextInt(6);
				if (luckyDip == 0) {
					// +
					s += "0000";
				} else if (luckyDip == 1) {
					// -
					s += "0001";
				} else if (luckyDip == 2) {
					// / 
					s += "0010";
				} else if (luckyDip == 3) {
					// * 
					s += "0011";
				} else if (luckyDip == 4) {
					// sqrt 
					s += "0100";
				} else if (luckyDip == 5) {
					// ^2 
					s += "0101";
				} 
			}
			// roll a class
			String c = "";
			int luckyDip = r.nextInt(3);
			if (luckyDip == 0) {
				// L
				c = "0110";
			} else if (luckyDip == 1) {
				// B
				c = "0111";
			} else if (luckyDip == 2) {
				// R 
				c = "1000";
			}
			hypos.put(s, c);
		}
		
		return null;
	}
	
	public Map<String, Integer> getRandomBitStrings(int p, int lengthOfBitStrings) {
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
