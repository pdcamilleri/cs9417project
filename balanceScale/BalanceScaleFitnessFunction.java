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
		char[][] tarparls = {{'1','5','1','1','L'},{'2','5','1','1','L'},{'3','2','1','1','L'},
							 {'1','4','1','4','B'},{'1','2','3','4','R'}};
		String hypartharsarse = "11111111111111110000001";
		BalanceScaleFitnessFunction bsff = new BalanceScaleFitnessFunction();
		for (int i = 0; i < 5; i++) {
			if (bsff.balanceScaleJudge(tarparls[i], hypartharsarse)) {
				System.out.println("right");
			} else {
				System.out.println("wrong");
			}
		}
	}
	
	private boolean balanceScaleJudge(char[] tuple, String hypothesis) {
		
		boolean judgement = true;
		
		int wrongRules = 0;
		// need to look at each rule in hypothesis
		for (int i = 0; i < hypothesis.length()/23; i++ ) { 
			// look at each attribute in the rule
			int matchCount = 0;
			boolean classMatch = false;
			String attr = hypothesis.substring(23*i+20, 23*i+20+3); // the class of each rule: 100|010|001
			if (tuple[4] == 'L') {
				if (attr.equals("100")) {
					classMatch = true;
				}
			} else if (tuple[4] == 'B') {
				if (attr.equals("010")) {
					classMatch = true;
				}
			} else if (tuple[4] == 'R') {
				if (attr.equals("001")) {
					classMatch = true;
				}
			}
			for (int j = 0; j < 4; j++) { // 4 = NUM_ATTRIBUTES
				// look at the values for each attribute in the rule
				int index = 23*i+5*j;
				attr = hypothesis.substring(index, index+5); // aka for i = 1 (second rule) and j = 0 
															 // (first attribute), get 01011 = (a1 = 2/4/5)
				int posInAttr = (int)tuple[j] - '0' - 1;
				if (attr.charAt(posInAttr) == '1') {
					matchCount++;
				}
			}
			if (matchCount == 4) { // if the rule matches for all the attributes
				if (!classMatch) { // but the classification is wrong
					wrongRules++;  // then its an incorrect rule.
				} 
			} 
		}
		// if there are ANY incorrect rules in the hypothesis, then the entire hypothesis is wrong
		if (wrongRules > 0) {
			judgement = false;
		}
		return judgement;
	}
}