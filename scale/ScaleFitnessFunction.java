package scale;

import geneticalgo.FitnessFunction;
import geneticalgo.Parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScaleFitnessFunction implements FitnessFunction {

    private Map<String, Character> values;
    private Map<String, Character> operators;
    private Map<Character, Integer> index;
    private char[][] allExamples;
    
    private static final Character LW = 'a';
    private static final Character LD = 'b';
    private static final Character RW = 'c';
    private static final Character RD = 'd';
    
    private static final Character ADD = '+';
    private static final Character SUBTRACT = '-';
    private static final Character DIVIDE = '/';
    private static final Character MULTIPLY = '*';
    
    public ScaleFitnessFunction() {
        
        Parser parser = new ScaleParser();
        try {
            allExamples = parser.parse("datasets/balancescale.super.cleaned");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        values = new HashMap<String, Character>();
        operators = new HashMap<String, Character>();
        index = new HashMap<Character, Integer>();
        
        values.put("00", LW);
        values.put("01", LD);
        values.put("10", RW);
        values.put("11", RD);
        operators.put("00", ADD);
        operators.put("01", SUBTRACT);
        operators.put("10", DIVIDE);
        operators.put("11", MULTIPLY);
        index.put(LW, 0);
        index.put(LD, 1);
        index.put(RW, 2);
        index.put(RD, 3);
    }
    
    /**
     * given a bitString of length 2 + 4n (in our case 14), it will return the value of that bitstring
     * when interpreted as a string of values and operators
     * 01234567890123
     * vvoovvoovvoovv
     * 00 = LW = +
     * 01 = LD = -
     * 10 = RW = /
     * 11 = RD = *
     */
    @Override
    public int getFitness(String hypothesis) {
        char[] function = convertHypothesisToFunction(hypothesis);
//        System.out.println(function);
        int correct = computeValue(function);
        // TODO Auto-generated method stub
        System.out.println(correct + " - " + hypothesisToGrepString(hypothesis));
        return correct;
    }

    /**
     * converts a bitstring hypothesis into its mathematical form
     * @param hypothesis
     * @return
     */
    private char[] convertHypothesisToFunction(String hypothesis) {
        char[] convertedHypothesis = new char[hypothesis.length()/2];
        for (int i = 0; i < hypothesis.length(); i+=2) {
            if (i % 4 == 0) { // converting a value value
                convertedHypothesis[i/2] = values.get(hypothesis.substring(i, i + 2));
            } else if (i % 2 == 0) { // converting an operator
                convertedHypothesis[i/2] = operators.get(hypothesis.substring(i, i + 2));
            }
        }
        return convertedHypothesis;
    }
    
    
    private int computeValue(char[] functionChar) {
        int correct = 0;
        for (int i = 0; i < allExamples.length; i++) {
            if (test(functionChar, allExamples[i])) {
                correct++;
            }
        }
        return correct;
    }
    
    private boolean test (char[]function, char[] example) {
        int[] intFunction = new int[function.length];
        for (int i = 0; i < function.length; i++) {
            if (function[i] == LW) {
                intFunction[i] = example[0] - '0';
            } else if (function[i] == LD) {
                intFunction[i] = example[1] - '0';
            } else if (function[i] == RW) {
                intFunction[i] = example[2] - '0';
            } else if (function[i] == RD) {
                intFunction[i] = example[3] - '0';
            } else {
                intFunction[i] = function[i];
            }
        }
//        for (int a = 0; a < function.length; a++) {
//            System.out.print(" " + intFunction[a]);
//        }
//        System.out.println();
        while (true) {
            int[] newFunction;
            for (int i = 0; i < intFunction.length; i++) {
//                for (int a = 0; a < intFunction.length; a++) {
//                    System.out.print(" " + intFunction[a]);
//                }
//                System.out.println();
//                System.out.println(new String(function));
                if (intFunction[i] == MULTIPLY) {
                    int before = intFunction[i-1];
                    int after = intFunction[i+1];
                    intFunction[i-1] = (before * after);
                    newFunction = new int[intFunction.length - 2];
                    for (int j = i; j < intFunction.length - 2; j++) {
                        intFunction[j] = intFunction[j + 2];
                    }
                    for (int j = 0; j < newFunction.length; j++) {
                        newFunction[j] = intFunction[j];
                    }
                    intFunction = newFunction;
                } else if (intFunction[i] == ADD) {
                    int before = intFunction[i-1];
                    int after = intFunction[i+1];
                    intFunction[i-1] = (before + after);
                    newFunction = new int[intFunction.length - 2];
                    for (int j = i; j < intFunction.length - 2; j++) {
                        intFunction[j] = intFunction[j + 2];
                    }
                    for (int j = 0; j < newFunction.length; j++) {
                        newFunction[j] = intFunction[j];
                    }
                    intFunction = newFunction;
               } else if (intFunction[i] == SUBTRACT) {
                    int before = intFunction[i-1] - '0';
                    int after = intFunction[i+1] - '0';
                    intFunction[i-1] = (before - after);
                    newFunction = new int[intFunction.length - 2];
                    for (int j = i; j < intFunction.length - 2; j++) {
                        intFunction[j] = intFunction[j + 2];
                    }
                    for (int j = 0; j < newFunction.length; j++) {
                        newFunction[j] = intFunction[j];
                    }
                    intFunction = newFunction;
                } else if (intFunction[i] == DIVIDE) {
//                    for (int a = 0; a < function.length; a++) {
//                        System.out.print(function[a]);
//                    }
//                    System.out.println();
                    int before = intFunction[i-1] - '0';
                    int after = intFunction[i+1] - '0';
                    if (before == 0) {
                        return false;
                    }
                    intFunction[i-1] = (before / after);
                    newFunction = new int[intFunction.length - 2];
                    for (int j = i; j < intFunction.length - 2; j++) {
                        intFunction[j] = intFunction[j + 2];
                    }
                    for (int j = 0; j < newFunction.length; j++) {
                        newFunction[j] = intFunction[j];
                    }
                    intFunction = newFunction;
                }
                if (intFunction.length == 1) {
//                    System.out.println("int " + intFunction[0]);
//                    System.out.println("example " + example[4]);
                    if (intFunction[0] == 0) { // balanced
                        return example[4] == 'B';
                    } else if (intFunction[0] > 0) { // balanced
                        return example[4] == 'R';
                    } else if (intFunction[0] < 0) { // balanced
                        return example[4] == 'L';
                    }
                }
            }
        }
        
    }

    @Override
    public StringBuffer hypothesisToGrepString(String hypothesis) {
        return new StringBuffer(new String(convertHypothesisToFunction(hypothesis)));
    }
    
    public static void main (String[] args) {
        // solutions produced by running Main.java with arguments "-s"
        System.out.println(new ScaleFitnessFunction().hypothesisToGrepString("10111101001101"));
        System.out.println(new ScaleFitnessFunction().hypothesisToGrepString("10111101011100"));
        System.out.println(new ScaleFitnessFunction().hypothesisToGrepString("10111101001101"));
        System.out.println(new ScaleFitnessFunction().hypothesisToGrepString("11111001001101"));
        System.out.println(new ScaleFitnessFunction().hypothesisToGrepString("11111001001101"));
        System.out.println(new ScaleFitnessFunction().hypothesisToGrepString("11111001011100"));
        return;
    }

}
