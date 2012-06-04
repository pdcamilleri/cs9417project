package balanceScale;

import java.io.IOException;
import java.util.Map;

import geneticalgo.FitnessFunction;
import geneticalgo.Parser;

public class BalanceScaleFitnessFunction implements FitnessFunction {

    char[][] tuples = {
            {'1','5','1','1','L'},
            {'2','5','1','1','L'},
            {'3','2','1','1','L'},
            {'1','4','1','4','B'},
            {'1','2','3','4','R'},
    };

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

        double scr = (double) score;
        int dataSetSize = dataSet.length;
        System.out.printf("%.2f%%", (scr / (double) dataSetSize * 100));
        System.out.print(" -->> " + (hypothesisToGrepString(hypothesis)));
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + hypothesis);

        return score;
    }

    private void testFitness() {
        BalanceScaleSpecification bss = new BalanceScaleSpecification();
        BalanceScaleFitnessFunction bsff = new BalanceScaleFitnessFunction();
        Map<String, Integer> hypos = bss.generateHypotheses(100);
        for (String hypo : hypos.keySet()) {
            int fitness = bsff.getFitness(hypo);
            System.out.println(fitness);
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
                // check the predicted class against actual class
                if (!classMatch) { // but the classification is wrong
                    wrongRules++;
                }
            } else {
                // if all attributes dont match, then the rule predicts either of the two classes it doesnt specify.
                // if the actual class is NOT one of these two classes, aka if
                // if actual class = predicted class, rule is wrong
                if (classMatch) {
                    wrongRules++;
                }
            }
        }
        // if there are ANY incorrect rules in the hypothesis, then the entire hypothesis is wrong
        if (wrongRules > 0) {
            judgement = false;
        }
        return judgement;
    }

    public static void main(String[] args) throws IOException {

        BalanceScaleFitnessFunction bsff = new BalanceScaleFitnessFunction();
        //		bsff.bigTest();
        //		bsff.testNoValAllowed();
        //		bsff.testParser();
        bsff.testFitness();
    }

    @SuppressWarnings("unused")
    private void bigTest() {

        String[] hypartharsarses = {
                "01100100110110101110010011000001110100010010010000010111101011110110010100111101110110000010",
                "1011010001100000000000100110101101001011010010",
                "0010010110111011101000101111111111001010010010011110010011100101100101110010111101101011100101001110001010110010010",
                "01111010100001000001001010011000100010111010100100010110101101100001011000001011010100101100",
                "11001000010000110111001100000011000101000010100000100011010001011110010100001011101100100010",
                "01101110001101001111001",
                "101010001101110110100010100001010001000001001010011001111101010001010",
                "0010010000111010110010000000101010011011001100001010010110010010000011011110100110010100001000000000010110111111010",
                "10110101010000100001010000100100000101110010101100101100011101100001001101011100001100110010",
                "111011100110101111010100100001100110011100000100100001001101111101010",
                "110111110011110110010100110101011000100000010010101101111001001110100101100110010100000010101101010100011011001110001101111100000000100010",
                "0011101111010111000001010000110011000011111100",
                "001101100111111110011000101001001110110011101000001101010110001001010010011101001111110001001111100001011100011110001001100000010011101010",
                "0011110000110101001000111001010111111100100100",
                "10011101101010001011001",
                "00000110000000100110001",
                "01110000000010011100001",
                "1001010100110010110001000100001111101001000001",
                "000110101001110011110011010100111001011011010011001101111010010110010",
                "001011011011111110111001100010000100110111010001001100111110001111100",
        };
        BalanceScaleFitnessFunction bsff = new BalanceScaleFitnessFunction();
        for (String hypo : hypartharsarses) {
            System.out.println(hypo);
            for (int i = 0; i < 5; i++) {
                if (bsff.balanceScaleJudge(tuples[i], hypo)) {
                    System.out.println("right");
                } else {
                    System.out.println("wrong");
                }
            }	
        }
    }

    @SuppressWarnings("unused")
    private void testNoValAllowed() {
        BalanceScaleFitnessFunction bsff = new BalanceScaleFitnessFunction();
        String test = "1011010001100000000000100110101101001011010010";
        System.out.println(test);
        for (int i = 0; i < 5; i++) {
            if (bsff.balanceScaleJudge(tuples[i], test)) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }

    @SuppressWarnings("unused")
    private void testParser() {
        Parser parser = new BalanceScaleParser();
        char[][] dataSet = null;
        try {
            dataSet = parser.parse("datasets/balancescale.cleaned");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (char[] tuple : dataSet) {
            System.out.println(tuple);
        }
    }

    @Override
    public StringBuffer hypothesisToGrepString(String hypothesis) {

        char[] vals = {'1','2','3','4','5'};

        int position = 0;
        StringBuffer toPrint = new StringBuffer("");
        for (int i = 0; i < 4; i++) { // num attributes
            toPrint.append("");
            boolean addedSomething = false;
            for (int j = 0; j < 5; j++) { // num vals
                if (hypothesis.charAt(position + j) == '1') {
                    if (!addedSomething) {
                        toPrint.append("[");
                    }
                    toPrint.append(vals[j]);
                    addedSomething = true;
                }
            }
            position += 5;
            if (addedSomething) {
                toPrint.append("]");
            } else {
                toPrint.append('.');
            }
        }
        String attr = hypothesis.substring(23*0+20, 23*0+20+3); // replace 0 with i if adding multiple rules
        if (attr.equals("100")) {
            toPrint.append('L');
        }
        if (attr.equals("010")) {
            toPrint.append('B');
        }
        if (attr.equals("001")) {
            toPrint.append('R');
        }

        // handle last attribute p/e separately
        return toPrint;
    }
}