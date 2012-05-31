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

    // write a parsers to read in and construct this array?
//    private char[] attributes = {
//            { 'b', 'c', 'f', 'k', 's', 'x'},
//            ...
//            { 'e', 'p'}
//    };

//    private String[][] capShape = {
//            { "b", "100000",},
//            { "c", "010000" },
//            { "f", "001000" },
//            { "k", "000100" },
//            { "s", "000010" },
//            { "x", "000001" }
//    };
//
////    private Map countryCapitals = ArrayUtils.toMap(countries);
////    import org.apache.commons.lang.ArrayUtils;

    private final int LENGTH_OF_BITSTRING = 128; // 117, 127 without p/non-p, 128 inc ?
    private final int NUMBER_OF_ATTRIBUTES = 22;


    // 1010101101011010101010101001011010101010110101010...
    // AAAAAABBBBCCCCCCCCCCDDEEEEEEEEEFFFFGGGHHIIIIIIIII...
    public int getFitness(String hypothesis) {
        


//        for (int k = 0; k < trainingExamples.length; k++) {
//            for (int i = 0; i < trainingExamples[k].length; i++) { // trainingexamples[k] will always be the same....can optimize this
//                char value = trainingExamples[k][i];
//                int j;
//                for (j = 0; j < attributes[i].length; j++) {
//                    if (attributes[i][j] == value) break;
//                }
//                System.out.println("position is " + j);
//                break; // have to break here since ive only put one value inside of the attributes array
//            }
//        }


//        char[] attributeDecoding = new char[NUMBER_OF_ATTRIBUTES];
//
//        for (int i = 0, position = 0; i < NUMBER_OF_ATTRIBUTES; i++) {
//            String attribute = hypothesis.substring(position, position + lengthsOfAttributes[i]);
//
//            System.out.println(i + ": " + attribute);
//            // move position along, ready for the next attribute
//            position += lengthsOfAttributes[i];
//
//            // pass bitstring of this attribute to some decoder that will map bitstrings to the correct value. hardcoded ofcourse
//            attributeDecoding[i] = attribute.charAt(0); // need the mapping function in here
//
//        }

        //
      int count = 0;
      for (int i = 0; i < hypothesis.length(); i++) {
              count += (hypothesis.charAt(i) - '0');
      }
      return count;
//        return 0;
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

        String specificString =
"11111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001";

        System.out.println(mff.hypothesisToReadableString(specificString));
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
            for (j = 0; j < attributes[i].length - 1; j++) { // the last attribute, p/e is a special case. 2 attributes, 1 bit
                if (attributes[i][j] == value) break;
            }
            if (hypothesis.charAt(position + j) != '1') {
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

}
