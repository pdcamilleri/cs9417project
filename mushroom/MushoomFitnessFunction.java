package mushroom;

import geneticalgo.FitnessFunction;
import geneticalgo.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MushoomFitnessFunction implements FitnessFunction {

    // this array holds the number of values for each attribute.
    // so for mushroom, first attribute is cap-shape, which can take on 6 values. hence first value in the array is 6.
    private int[] lengthsOfAttributes = {
            6, 4, 10, 2, 9, 4, 3, 2, 12, 2, 6, 4, 4, 9, 9, 2, 4, 3, 8, 6, 7, 1
    };

    private char[][] trainingExamplesRigged = {
            {'b','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'c','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'f','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'k','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'s','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'x','s','y','t','a','f','c','b','k','e','c','s','s','w','w','p','w','o','p','n','n','g','e'}
    };
    
    // TODO maybe parse this from arff file??? 
    private char[][] attributes = {
        { 'b', 'c', 'f', 'k', 's', 'x'},
        { 'f', 'g', 's', 'y'},
        { 'b', 'c', 'e', 'g', 'n', 'p', 'r', 'u', 'w', 'y'},
        { 'f', 't'},
        { 'a', 'c', 'f', 'l', 'm', 'n', 'p', 's', 'y'},
        { 'a', 'd', 'f', 'n'},
        { 'c', 'd', 'w'},
        { 'b', 'n'},
        { 'b', 'e', 'g', 'h', 'k', 'n', 'o', 'p', 'r', 'u', 'w', 'y'},
        { 'e', 't'},
        { 'b', 'c', 'e', 'r', 'u', 'z', '?'}, // added one for ? TODO remove it?
        { 'f', 'k', 's', 'y'},
        { 'f', 'k', 's', 'y'},
        { 'b', 'c', 'e', 'g', 'n', 'o', 'p', 'w', 'y'},
        { 'b', 'c', 'e', 'g', 'n', 'o', 'p', 'w', 'y'},
        { 'p', 'u'},
        { 'n', 'o', 'w', 'y'},
        { 'n', 'o', 't'},
        { 'c', 'e', 'f', 'l', 'n', 'p', 's', 'z'},
        { 'b', 'h', 'k', 'n', 'o', 'r', 'u', 'w', 'y'},
        { 'a', 'c', 'n', 's', 'v', 'y'},
        { 'd', 'g', 'l', 'm', 'p', 'u', 'w'},
        { 'e', 'p'},
    };

    private char[][] examples;
    private char[][] poisonousExamples;
    private char[][] edibleExamples;
    
    private final int LENGTH_OF_BITSTRING = 128; // 117, 127 without p/non-p, 128 inc ?
    private final int NUMBER_OF_ATTRIBUTES = 22;

    public MushoomFitnessFunction() {

        Parser parser = new MushroomParser();
        try {
            examples = parser.parse("src/datasets/mushroom.cleaned");
            sortExamples(examples);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sorts training examples into poisonous and edible sets.
     */
    private void sortExamples(char[][] examples) {
        // find the number of instances in each set
        // so we can create the array
        int numberOfPoisonousExamples = 0;
        for (int i = 0; i < examples.length; i++) {
            if (examples[i][examples[i].length - 1] == 'p') {
                numberOfPoisonousExamples++;
            }
        }
        poisonousExamples = new char[numberOfPoisonousExamples][examples[0].length];
        edibleExamples = new char[examples.length - numberOfPoisonousExamples][examples[0].length];
        
        int poisonousPosition = 0;
        int ediblePosition = 0;
        for (int i = 0; i < examples.length; i++) {
            if (examples[i][examples[i].length - 1] == 'p') {
                poisonousExamples[poisonousPosition] = examples[i];
                poisonousPosition++;
            } else {
                edibleExamples[ediblePosition] = examples[i];
                ediblePosition++;
            }
        }
        
    }

    public int getFitness(String hypothesis) {
        char[][] validExamples = examples; // just setting it so eclipse wont complain
        int correct = 0;
        
        char lastChar = hypothesis.charAt(hypothesis.length() - 1);
        if (lastChar == '1') {
            validExamples = poisonousExamples;
//            correct += edibleExamples.length;
        } else if (lastChar == '0') {
            validExamples = edibleExamples;
//            correct += poisonousExamples.length;
        } else {
            ((String) null).length(); // crash program, shouldnt get here
        }
        
        for (int i = 0; i < validExamples.length; i++) {
            if (test(hypothesis, validExamples[i])) {
                correct++;
            }
        }
//        System.out.println((correct) + " / " + tested + " - " + wrong + " - " + hypothesis);
        if (correct > 1) System.out.println((correct * 10000) / validExamples.length + " - " + (hypothesisToGrepString(hypothesis)));
        return (correct * 10000) / validExamples.length;
    }
    
    /*
     * just so i can test out the test function
     */
    public static void main(String[] args) throws IOException {
        
//        Parser parser = new MushroomParser();
//        char[][] examples = parser.parse("src/datasets/mushroom.cleaned");
//
        MushoomFitnessFunction mff = new MushoomFitnessFunction();
//        String mostGeneralHypoPossible =
//"11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
//        System.out.println(mff.test(mostGeneralHypoPossible,
//                new char[]{'b','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','e'}));
//        int count = 0, countz = 0;
//        for (int i = 0; i < examples.length; i++) {
//            if (mff.test(mostGeneralHypoPossible, examples[i])) {
//                count++;
//            } else {
//                countz++;
//            }
//        }
//        System.out.println(count + " - " + countz);

        String specificStringa =
"00000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001";
        String specificStringb =
"00000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        String specificStringc =
"00000000000000000000000110101110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001";
        
        // first complete run of GA
        String[] runs = {
                "00000000000000000000001011011110000000000000000000000011110010000000000000000000000000000000000000000000000000000000000000000000",
                "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011000000000000000000000000000000000001",
                "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000001",
        };
        
        for (int i = 0; i < runs.length; i++) {
            mff.displayRunInfo(runs[i]);
        }
        
//        { 'a', 'c', 'f', 'l', 'm', 'n', 'p', 's', 'y'}, "aflnpsy" ==>> if odor = {c or m} and stalk-root = { u or z } => edible
//        { 'b', 'c', 'e', 'r', 'u', 'z', '?'}, "bcer?" ==>> 
        
//        System.out.println(mff.hypothesisToReadableString(specificStringa));
//        System.out.println(mff.hypothesisToGrepString(specificStringa));
//        System.out.println("fitnessa - " + mff.getFitness(specificStringa));
//        System.out.println(mff.hypothesisToGrepString(specificStringb));
//        System.out.println(mff.hypothesisToReadableString(specificStringb));
//        System.out.println("fitnessb - " + mff.getFitness(specificStringb));
//        System.out.println(mff.hypothesisToGrepString(specificStringc));
//        System.out.println("c" + mff.hypothesisToReadableString(specificStringc));
//        System.out.println("fitnessc - " + mff.getFitness(specificStringc));
    }
    
    private void displayRunInfo(String completeRun_b) {
        System.out.println("fitness - " + getFitness(completeRun_b));
        System.out.println(hypothesisToGrepString(completeRun_b));
        System.out.println(hypothesisToReadableString(completeRun_b));
        System.out.println("*******************************************************************");
    }

    /**
     * tests whether or not a particular hypothesis is consistent with a particular training example
     * @param hypothesis
     */
    private boolean test(String hypothesis, char[] example) {
        // create a blank hypothesis
        char[] mask = new char[example.length];
        for (int i = 0; i < mask.length; i++) {
            mask[i] = '0';
        }
        
        // create a bitstring from the training example
        int position = 0;
        for (int i = 0; i < example.length; i++) { // example length will always be the same....can optimize this
            char value = example[i];
            int j;
            for (j = 0; j < attributes[i].length - 1; j++) { // the last attribute, p/e is a special case, hence the -1
                if (attributes[i][j] == value) break;
            }
            if (hypothesis.charAt(position + j) != '1' && existsAnotherOne(hypothesis, position, j, i)) {
                return false;
            }
            position += attributes[i].length; // update our position to skip over this attribute
        }
        // check the last bit which is special
        char lastValue = example[example.length - 1];
        position--; // undo the last add
        if (lastValue == 'p') {
            lastValue = '1';
        } else {
            lastValue = '0';
        }
        if (hypothesis.charAt(position) != lastValue) {
            return false;
        }
        return true;
    }

    /**
     * we only want to return false for a hypothesis with respect to a training example
     * if we have a 1 in the training example (in bitString format) and a 1 in the hypothesis
     * that belong to the same attribute section, but the 1's dont line up. In other words, the hypothesis says
     * we should have value v1 for attribute A but the training examples say we should have value v2 for A.
     * BUT in the case where the hypothesis suggest no value for A, that is, that attribute section contains
     * all 0's, (ie, this hypo is not dependent on that) then we should not return false, since we dont care
     * that the training example requires v2.
     * @param hypothesis
     * @param position
     * @param j
     * @param i
     * @return
     */
    private boolean existsAnotherOne(String hypothesis, int position, int j,
            int i) {
        for (int a = 0; a < attributes[i].length; a++) {
            if (hypothesis.charAt(position + a) == '1' && j != a) return true; // except j
        }
        return false;
    }

    /**
     * takes a bitString representing a hypothesis and decomposes it down so its fitness can be calculated. 
     */
    private void decode(String hypothesis) {
        
    }

//    if you want to get the basic algo to work (finding bitstrings with many 1s), use this
//    public int getFitness(String hypothesis) {
//
//        int count = 0;
//        for (int i = 0; i < hypothesis.length(); i++) {
//                count += (hypothesis.charAt(i) - '0');
//        }
//        return count;
//    }

    /*
    write a function that takes in a String hypothesis and outputs a readable representation of that rule like

    (a || b) && (c) && (d) && (e || f || g) ...

    also generate a grepable format

    cat mushroom.cleaned | sed -e "s/[^a-zA-Z?]//g" | grep "[ab][c][d][efg]..."

    which can be used in combination with " | wc" to see the % correct (just to confirm the GA's % correct is accurate
     */
    private StringBuffer hypothesisToReadableString(String hypothesis) {
        int position = 0;
        StringBuffer toPrint = new StringBuffer("(");
        for (int i = 0; i < attributes.length - 1; i++) {
            toPrint.append("");
            boolean addedSomething = false;
            for (int j = 0; j < attributes[i].length; j++) {
                if (hypothesis.charAt(position + j) == '1') {
                    toPrint.append(attributes[i][j] + " || ");
                    addedSomething = true;
                }
            }
            position += attributes[i].length;
            if (addedSomething) {
                toPrint.delete(toPrint.length() - 4, toPrint.length());
                toPrint.append(") && (");
            }
            // if # attributes == number of attributes going to be printed then omit printing (its too general)
        }

        toPrint.delete(toPrint.length() - 4, toPrint.length());

        if (hypothesis.charAt(position + 1) == '1') {
            toPrint.append(" => " + 'p');
        } else {
            toPrint.append(" => " + 'e');
        }

        // handle last attribute p/e separately
        return toPrint;
    }

    public StringBuffer hypothesisToGrepString(String hypothesis) {
        int position = 0;
        StringBuffer toPrint = new StringBuffer("");
        for (int i = 0; i < attributes.length - 1; i++) {
            toPrint.append("");
            boolean addedSomething = false;
            for (int j = 0; j < attributes[i].length; j++) {
                if (hypothesis.charAt(position + j) == '1') {
                    if (!addedSomething) {
                        toPrint.append("[");
                    }
                    toPrint.append(attributes[i][j]);
                    addedSomething = true;
                }
            }
            position += attributes[i].length;
            if (addedSomething) {
                toPrint.append("]");
            } else {
                toPrint.append('.');
            }
        }

        if (hypothesis.charAt(position + 1) == '1') {
            toPrint.append("[p]");
        } else {
            toPrint.append("[e]");
        }

        // handle last attribute p/e separately
        return toPrint;
    }

}
