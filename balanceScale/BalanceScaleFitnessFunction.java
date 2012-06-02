package balanceScale;

import java.io.IOException;
import java.util.Map;

import geneticalgo.FitnessFunction;
import geneticalgo.Parser;

public class BalanceScaleFitnessFunction implements FitnessFunction {

	public int getFitness(String hypothesis) {
		//increment score if the hypothesis correctly identifies a tuple
		int score = 0;
		Parser parser = new BalanceScaleParser();
		char[][] dataSet = null;
		try {
			dataSet = parser.parse("src/datasets/balancescale.cleaned");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Parser returns training examples as list like:
		// 1111B 
		// 2211L
		// etc

		// fitness is how well it classifies training examples.
		for (char[] tuple : dataSet) {
			if (balanceScaleJudge(tuple, hypothesis)) {
				score++;
			}
		}

		return score;
	}

	public static void main(String[] args) throws IOException {
		char[] tarparl = {'1','2','3','4','R'};
		String hypartharsarse = "1000001000001000001000111111111111111111111001";
		BalanceScaleFitnessFunction bsff = new BalanceScaleFitnessFunction();
		
		if (bsff.balanceScaleJudge(tarparl, hypartharsarse)) {
			System.out.println("judged guilty");
		}
	}
	
	private boolean balanceScaleJudge(char[] tuple, String hypothesis) {

		boolean judgement = false;
	
		// [1, 2, 1, 1, L]
	
		// example hypothesis:
		// 11111100101110111011100 01011111110000000100001
		// LD = 1/4 AND RW = 1/2/3/5 AND RD = 1/2/4/5 THEN class = L +
		// LW = 2/4/5 AND RW = noValueAllowed THEN class = R
		// example tuple:
		// 1,2,1,1,L

		// for each rule, see if ALL values in tuple are covered under the hypothesis by ALL rules
		
		// need to look at each rule in hypothesis
		int ruleCount = 0;
		for (int i = 0; i < hypothesis.length()/23; i++ ) { 
			// look at each attribute in the tuple
			int matchCount = 0;
			for (int j = 0; j < 5; j++) { // 4 = NUM_ATTRIBUTES + 1 for class
				
				// look at the values for each attribute in the rule
				int index = 23*i+5*j;
				if (j == 4) {
					// check class
					String attr = hypothesis.substring(index, index+3); // 100|010|001
					if (tuple[j] == 'L') {
						if (attr.equals("100")) {
							matchCount++;
						}
					} else if (tuple[j] == 'B') {
						if (attr.equals("010")) {
							matchCount++;
						}
					} else if (tuple[j] == 'R') {
						if (attr.equals("001")) {
							matchCount++;
						}
					}
					break;
				}
				String attr = hypothesis.substring(index, index+5); // aka for i = 1 (second rule) and j = 0 
																	// (first attribute), get 01011
				int posInAttr = (int)tuple[j] - '0' - 1;
				if (attr.charAt(posInAttr) == '1') {
					matchCount++;
				}
			}
			if (matchCount == 5) {
				// then this rule passes
				ruleCount++;
			}
		}
		if (hypothesis.length()/23 == ruleCount) {
			// then all rules matched.
			judgement = true;
		}
			
		return judgement;
	}

}