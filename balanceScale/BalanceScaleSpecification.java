package balanceScale;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import geneticalgo.FitnessFunction;
import geneticalgo.ProblemSpecification;

public class BalanceScaleSpecification implements ProblemSpecification{

	private static final int ARBITRARY_MAX_RULE_LENGTH = 6;
	private static final int NUM_ATTRIBUTES = 4;
	private static final int VALS_PER_ATTRIBUTE = 5;
	
	public FitnessFunction getFitnessFunction() {
		return new BalanceScaleFitnessFunction();
	}
	
	public Map<String, Integer> generateHypothesis(int p) {
		
		Map<String, Integer> hypos = new HashMap<String, Integer>();
		
		// a hypothesis looks like:
		// LW    LD    RW    RD    B
		// 00111 10000 11111 10101 010
		// 12345 12345 12345 12345 LBR
		
		//foreach hypothesis
		for (int i = 0; i < p; i++) {
			
			//make a string
			String s = new String();
			Random r = new Random();
			int numRulesInHypo = r.nextInt(NUM_ATTRIBUTES)+1;
			for (int k = 0; k < numRulesInHypo; k++) {
				//filled with random booleans to make rule
				for (int j = 0; j < NUM_ATTRIBUTES*VALS_PER_ATTRIBUTE; j++) {
					s += r.nextInt(2);
				}
				//append class
				int classRoll = r.nextInt(3);
				if (classRoll == 0) {
					s += "100";
				} else if (classRoll == 1) {
					s += "010";
				} else if (classRoll == 2) {
					s += "001";
				}
			}
			hypos.put(s, null);
		}
		return hypos;
	}

	public static void main(String[] args) throws IOException {
		BalanceScaleSpecification bss = new BalanceScaleSpecification();
		Map<String, Integer> hypos = bss.generateHypothesis(20); // <- num to display
		for (String s : hypos.keySet()) { 
			System.out.print(s + " length = " + s.length());
			/*for (int i = 0; i < s.length(); i+=4) {
				System.out.print(bss.conversion.get(s.substring(i, i+4)));
			}*/
			System.out.println();
		}
	}
}
