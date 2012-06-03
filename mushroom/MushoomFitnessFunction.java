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
        
        int factor = 100;
        
        // count up the number of examples correctly classified
        // do poisonous and edible examples separately so they contribute equally to the fitness
        // that is. since there are more edible examples, anything that predicts poisonous has an advantage
        // since it will usually classify all edible examples correctly. and since there are more, it will
        // be preferred over a similar hypothesis that predicts all p examples correctly, but doesnt do so 
        // well on e examples.
        int poisonousCorrect = testHypothesisAgainstExamples(hypothesis, poisonousExamples);
        int edibleCorrect    = testHypothesisAgainstExamples(hypothesis, edibleExamples);
        
        int returnValue = (poisonousCorrect * 100 * factor / poisonousExamples.length) + 
                             (edibleCorrect * 100 * factor / edibleExamples.length);
        returnValue /= 2;
        
        
        
        
//        if (poisonousCorrect > 1) {
        System.out.print(((double) (((returnValue) / factor))) + "%");
        System.out.print(" (" + (edibleCorrect + poisonousCorrect) + "/" + examples.length + ") -->> " +
                                                (hypothesisToGrepString(hypothesis)));
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + hypothesis);
//        }
        
//        return ((correct) * 10000) / examples.length;
        return returnValue;
    }
    
    private int testHypothesisAgainstExamples(String hypothesis, char[][] validExamples) {
        int correct = 0;
        for (int i = 0; i < validExamples.length; i++) {
            if (test(hypothesis, validExamples[i])) {
                correct++;
            }
        }
        return correct;
    }

    /*
     * just so i can test out the test function
     */
    public static void main(String[] args) throws IOException {
        
        testTest();
        
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

//        String specificStringa =
//"00000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001";
//        String specificStringb =
//"00000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
//        String specificStringc =
//"00000000000000000000000110101110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001";
        
        // first complete run of GA
        String[] runs = {
                "0000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000100100000000001000101000000000000001",
                "0000000000000000000000000101010000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "0000000000000000000000001010111111110000000000000000000000000000000000000000000000000000000100000000000000000000000000000000001",
                "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000100100000000001000001000000000000001",
                "0000000000000000000000000101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "0000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
        };
        
        for (int i = 0; i < runs.length; i++) {
            mff.displayRunInfo(runs[i]);
        }
        System.out.println(runs[4]);

        
        String[] badRuns = {
                "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000001",
                "00000010000000000000010000000000010100010000000000000000010000000000000000010000000010000000000000000010000100000000000000100001",
                "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011000000000000000000000000000000000001",
                "00000000000000000000001011011110000000000000000000000011110010000000000000000000000000000000000000000000000000000000000000000000",
                "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011000000000000000000000000000000000000",
        };
        for (int i = 0; i < badRuns.length; i++) {
//            mff.displayRunInfo(badRuns[i]);
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
    
    /**
     * method to test the fitness function
     */
    private static void testTest() {
        
        
        // have to find training examples in the actual dataset
        MushoomFitnessFunction mff = new MushoomFitnessFunction();
        
        {
            /* 
             * idea behind this test
             * 2 attributes, 2 values per attribute, 2 classes.
             * A = { a1, a2 }
             * B = { b1, b2 }
             * C = { c1, c2 }
             * 
             * BitString representation as follows
             * 
             * AABBC - where C = 0 for c1, 1 for c2
             * 
             * makes for 4 different training examples, which are as follows
             * 1) a1 & b1 => c1
             * 2) a1 & b2 => c2
             * 3) a2 & b2 => c1
             * 4) a2 & b2 => c2
             * 
             * so if we had a hypothesis which was
             * 10000
             * that is, if a1 then c1, else c2. we should get
             * 1) a1 & b1 => c1 - correct
             * 2) a1 & b2 => c2 - wrong
             * 3) a2 & b2 => c1 - wrong
             * 4) a2 & b2 => c2 - correct
             * 
             * it is those last two in particular that I need to account for
             */
            String[] testExamples = {
                    //k = a1, f = a2
                    //d = b1, l = b2
                    //e = c1, p = c2
                    "ksefyfcnbt?kkwppwoewvde",
                    "ksefyfcnbt?kkwppwoewvlp",
                    "fsefyfcnbt?kkwppwoewvde",
                    "fsefyfcnbt?kkwppwoewvlp",
            };
            // if a1 and anything, then c1
            String hypothesis = 
"00010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
            System.out.println(hypothesis);
            System.out.println(mff.hypothesisToGrepString(hypothesis));
            assert(mff.test(hypothesis, testExamples[0].toCharArray()) == true);
            assert(mff.test(hypothesis, testExamples[1].toCharArray()) == false);
            assert(mff.test(hypothesis, testExamples[2].toCharArray()) == false);
            assert(mff.test(hypothesis, testExamples[3].toCharArray()) == true);
        }
        
        {
            /*
             * for the veil-type attribute, every test case has the value 'p'.
             * but for some reason, when a hypothesis returns 
             *          if veil-type = p then p
             * it comes back with perfect accurary, which should not be the case
             * because if p => p, then it should be predicting p for all of the e classes
             * and so should only be ~50% correct.
             */
            String[] testExamples = {
                    "ksefyfcnbt?kkwwpwoewvlp",
                    "ksefyfcnbt?kkwwpwoewvde",
            };
            // if a1 and anything, then c1
            String hypothesis = 
"00000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000001";
            System.out.println(hypothesis);
            System.out.println(mff.hypothesisToGrepString(hypothesis));
            assert(mff.test(hypothesis, testExamples[0].toCharArray()) == true);
            assert(mff.test(hypothesis, testExamples[1].toCharArray()) == false);
        }
        
        
    }

    private void displayRunInfo(String completeRun_b) {
        System.out.println("*******************************************************************");
        System.out.println("fitness - " + ((double) (getFitness(completeRun_b) / 100)) + "%");
        System.out.println(hypothesisToGrepString(completeRun_b));
        System.out.println(hypothesisToReadableString(completeRun_b));
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
        
        boolean attributesMatch = true;;
        int position = 0;
        // for each attribute in the training example
        for (int i = 0; i < example.length - 1; i++) {
            // grab out the value for that attribute
            char value = example[i];
            
            // and find its index in attribute array
            int j;
            for (j = 0; j < attributes[i].length; j++) { // note the last attribute, p/e is a special case, hence the length-1
                if (attributes[i][j] == value) {
                    break;
                    // once we have found the value (and hence the index) break out
                }
            }
            // we now have the index (j) of the particular value in the training example, i.e. position + j
            // (where position is the position of the end of the last set of attributes in the bitString)
            // if the hypothesis has a 1 at this position, all good (1st condition)
            // if the hypothesis as a 0 at this position, then we have to check the other values in the bitString
            // for the same attributes. if they are all 0, it means this attribute is not relevant to this hypothesis
            // so all good. if we find a 1 however, hypothesis doesn't match.
            if (hypothesis.charAt(position + j) != '1' && existsAnotherOne(hypothesis, position, j, i)) {
                // hypothesis doesn't match, as explained in comment above this one
                attributesMatch = false;
            }
            position += attributes[i].length; // update our position to skip over this attribute on the next iteration
        }
        
        // check the last bit which is special (2 values, only 1 bit)
        char lastValue = example[example.length - 1];
        
        // now, if hypothesisFits at this point, just need to check the last bit agrees
        // but if the hypothesis doesn't fit at this point, we can still return true,
        // provided the class of the hypothesis and training example disagree too
        if (lastValue == 'p') {
            lastValue = '1';
        } else {
            lastValue = '0';
        }
        
        // class off training example and hypothesis disagree
        if (hypothesis.charAt(hypothesis.length() - 1) != lastValue) {
            /* now if we are in here, only 2 things could have happened
             * 1) the attributes of the hypothesis agrees with the training example, but the classes are in disagreement.
             *    since the attributes are in agreement, attributesMatch = true at this point. But we want to return false,
             *    since the classes are wrong. so
             *    attributesMatch (true) == false ==>> is false, which is what we want to return
             *    
             * 2) the attributes of the hypothesis disagree with the training examples AND the classes are in disagreement.
             *    the attributes disagree, so attributesMatch = false, but we want to return true, because the attributes
             *    are not covered by the hypothesis, and if that is the case, then the opposite class is predicted. so
             *    in this case, our hypothesis is correct since it doesnt match with the attributes or the class of the 
             *    training example, so
             *    attributesMatch (false) == false ==>> is true, which is what we want to return
             */
            return attributesMatch == false;
        }
        return attributesMatch;
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

        if (hypothesis.charAt(hypothesis.length() - 1) == '1') {
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

        if (hypothesis.charAt(hypothesis.length() - 1) == '1') {
            toPrint.append("[p]");
        } else {
            toPrint.append("[e]");
        }

        // handle last attribute p/e separately
        return toPrint;
    }

}
