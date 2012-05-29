package mushroom;

import geneticalgo.FitnessFunction;

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
    public static void main(String[] args) {
        MushoomFitnessFunction mff = new MushoomFitnessFunction();
        String mostGeneralHypoPossible = 
"11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        System.out.println(mff.test(mostGeneralHypoPossible,
                new char[]{'b','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','e'}));
        int count = 0, countz = 0;
        for (int i = 0; i < trainingExamples.length; i++) {
            if (mff.test(mostGeneralHypoPossible, trainingExamples[i])) {
                count++;
            } else {
                countz++;
            }
        }
        System.out.println(count + " - " + countz);
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
    
    // 29 poison, 430 non
    private static char[][] trainingExamples = {
            {'x','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'x','s','y','t','a','f','c','b','k','e','c','s','s','w','w','p','w','o','p','n','n','g','e'},
            {'b','s','w','t','l','f','c','b','n','e','c','s','s','w','w','p','w','o','p','n','n','m','e'},
            {'x','y','w','t','p','f','c','n','n','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'x','s','g','f','n','f','w','b','k','t','e','s','s','w','w','p','w','o','e','n','a','g','e'},
            {'x','y','y','t','a','f','c','b','n','e','c','s','s','w','w','p','w','o','p','k','n','g','e'},
            {'b','s','w','t','a','f','c','b','g','e','c','s','s','w','w','p','w','o','p','k','n','m','e'},
            {'b','y','w','t','l','f','c','b','n','e','c','s','s','w','w','p','w','o','p','n','s','m','e'},
            {'x','y','w','t','p','f','c','n','p','e','e','s','s','w','w','p','w','o','p','k','v','g','p'},
            {'b','s','y','t','a','f','c','b','g','e','c','s','s','w','w','p','w','o','p','k','s','m','e'},
            {'x','y','y','t','l','f','c','b','g','e','c','s','s','w','w','p','w','o','p','n','n','g','e'},
            {'x','y','y','t','a','f','c','b','n','e','c','s','s','w','w','p','w','o','p','k','s','m','e'},
            {'b','s','y','t','a','f','c','b','w','e','c','s','s','w','w','p','w','o','p','n','s','g','e'},
            {'x','y','w','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','n','v','u','p'},
            {'x','f','n','f','n','f','w','b','n','t','e','s','f','w','w','p','w','o','e','k','a','g','e'},
            {'s','f','g','f','n','f','c','n','k','e','e','s','s','w','w','p','w','o','p','n','y','u','e'},
            {'f','f','w','f','n','f','w','b','k','t','e','s','s','w','w','p','w','o','e','n','a','g','e'},
            {'x','s','n','t','p','f','c','n','n','e','e','s','s','w','w','p','w','o','p','k','s','g','p'},
            {'x','y','w','t','p','f','c','n','n','e','e','s','s','w','w','p','w','o','p','n','s','u','p'},
            {'x','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','n','s','u','p'},
            {'b','s','y','t','a','f','c','b','k','e','c','s','s','w','w','p','w','o','p','n','s','m','e'},
            {'x','y','n','t','p','f','c','n','n','e','e','s','s','w','w','p','w','o','p','n','v','g','p'},
            {'b','y','y','t','l','f','c','b','k','e','c','s','s','w','w','p','w','o','p','n','s','m','e'},
            {'b','y','w','t','a','f','c','b','w','e','c','s','s','w','w','p','w','o','p','n','n','m','e'},
            {'b','s','w','t','l','f','c','b','g','e','c','s','s','w','w','p','w','o','p','k','s','m','e'},
            {'f','s','w','t','p','f','c','n','n','e','e','s','s','w','w','p','w','o','p','n','v','g','p'},
            {'x','y','y','t','a','f','c','b','n','e','c','s','s','w','w','p','w','o','p','n','n','m','e'},
            {'x','y','w','t','l','f','c','b','w','e','c','s','s','w','w','p','w','o','p','n','n','m','e'},
            {'f','f','n','f','n','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','y','u','e'},
            {'x','s','y','t','a','f','w','n','n','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','w','p','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','g','p','p','w','o','p','k','v','d','e'},
            {'x','f','w','f','n','f','w','b','h','t','e','s','f','w','w','p','w','o','e','n','s','g','e'},
            {'f','y','g','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'f','s','g','f','n','f','w','b','n','t','e','f','f','w','w','p','w','o','e','n','s','g','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'f','y','w','t','p','f','c','n','w','e','e','s','s','w','w','p','w','o','p','n','s','u','p'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','w','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'x','f','g','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','w','p','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'f','s','w','f','n','f','w','b','k','t','e','s','s','w','w','p','w','o','e','n','a','g','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','k','v','d','e'},
            {'x','f','g','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','f','n','t','n','f','c','b','u','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'f','s','w','t','p','f','c','n','w','e','e','s','s','w','w','p','w','o','p','n','v','u','p'},
            {'x','f','g','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'f','s','w','f','n','f','w','b','k','t','e','s','s','w','w','p','w','o','e','k','s','g','e'},
            {'x','s','g','f','n','f','w','b','p','t','e','s','f','w','w','p','w','o','e','n','s','g','e'},
            {'x','f','g','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','w','g','p','w','o','p','k','y','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'x','f','g','f','f','f','c','b','h','e','b','k','k','n','n','p','w','o','l','h','y','d','p'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','f','n','f','n','f','w','b','h','t','e','s','s','w','w','p','w','o','e','k','a','g','e'},
            {'x','s','w','t','p','f','c','n','n','e','e','s','s','w','w','p','w','o','p','n','v','g','p'},
            {'x','y','g','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','f','n','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','k','v','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','w','g','p','w','o','p','k','v','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','u','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'x','s','w','t','p','f','c','n','n','e','e','s','s','w','w','p','w','o','p','k','v','g','p'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'x','f','g','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','k','v','d','e'},
            {'f','y','e','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'f','y','g','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','k','v','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'x','f','n','t','n','f','c','b','u','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'x','f','n','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'x','s','p','f','c','f','c','n','g','e','b','s','s','w','w','p','w','o','p','k','v','d','p'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'f','f','e','t','n','f','c','b','u','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','w','g','p','w','o','p','k','y','d','e'},
            {'x','f','g','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','k','v','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','w','g','p','w','o','p','n','v','d','e'},
            {'f','y','n','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'f','y','e','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','k','v','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'x','f','g','f','f','f','c','b','h','e','b','k','k','b','b','p','w','o','l','h','y','g','p'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','k','v','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'x','f','g','f','n','f','w','b','k','t','e','s','f','w','w','p','w','o','e','n','a','g','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','w','p','p','w','o','p','k','y','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','k','v','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','f','n','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','k','v','d','e'},
            {'x','f','n','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','k','v','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','w','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','p','w','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'x','f','g','f','f','f','c','b','g','e','b','k','k','b','n','p','w','o','l','h','v','p','p'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','g','w','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','n','v','d','e'},
            {'x','f','g','f','f','f','c','b','g','e','b','k','k','p','n','p','w','o','l','h','v','p','p'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','w','p','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','w','g','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'f','y','g','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'x','f','n','t','n','f','c','b','n','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'x','f','g','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','f','g','f','c','f','c','n','g','e','b','s','s','w','w','p','w','o','p','n','v','d','p'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'f','s','n','f','n','f','w','b','p','t','e','s','f','w','w','p','w','o','e','k','a','g','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','g','w','p','w','o','p','n','v','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'f','y','g','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'f','y','e','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'f','y','n','t','n','f','c','b','u','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'f','f','w','f','n','f','w','b','h','t','e','s','f','w','w','p','w','o','e','k','s','g','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','w','g','p','w','o','p','n','v','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'f','y','e','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'f','y','n','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'x','y','e','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','k','v','d','e'},
            {'x','f','g','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','k','v','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','w','g','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','k','v','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'x','f','p','f','c','f','c','n','p','e','b','s','s','w','w','p','w','o','p','n','v','d','p'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','k','v','d','e'},
            {'f','s','n','f','n','f','w','b','n','t','e','s','s','w','w','p','w','o','e','n','a','g','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','k','v','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','w','g','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','f','g','f','f','f','c','b','h','e','b','k','k','b','p','p','w','o','l','h','y','g','p'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','p','w','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','g','p','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','k','v','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','w','p','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','w','p','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','w','g','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'f','f','e','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'x','f','n','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','n','v','d','e'},
            {'f','y','e','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'f','y','g','t','n','f','c','b','p','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'x','s','g','f','n','f','w','b','h','t','e','f','s','w','w','p','w','o','e','k','s','g','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','w','p','p','w','o','p','k','y','d','e'},
            {'x','f','n','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'f','y','n','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'x','f','g','f','c','f','c','n','p','e','b','s','s','w','w','p','w','o','p','k','v','d','p'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','w','g','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'f','y','g','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','w','g','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'x','f','g','f','f','f','c','b','g','e','b','k','k','b','p','p','w','o','l','h','v','d','p'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','n','v','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'x','f','g','f','f','f','c','b','g','e','b','k','k','b','p','p','w','o','l','h','y','d','p'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'x','f','g','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'f','s','g','f','n','f','w','b','p','t','e','f','f','w','w','p','w','o','e','k','s','g','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','n','v','d','e'},
            {'x','f','g','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','k','v','d','e'},
            {'x','f','w','f','c','f','w','n','g','e','b','s','s','w','w','p','w','o','p','n','s','d','p'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'f','f','w','f','n','f','w','b','h','t','e','s','s','w','w','p','w','o','e','n','s','g','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'x','f','g','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'f','y','e','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'x','y','n','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'f','y','e','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','y','g','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'x','y','g','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'x','f','n','t','n','f','c','b','w','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'f','f','n','f','n','f','w','b','n','t','e','f','f','w','w','p','w','o','e','k','s','g','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','w','g','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','k','v','d','e'},
            {'x','f','g','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','n','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','k','v','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','k','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','k','v','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','w','g','p','w','o','p','k','v','d','e'},
            {'x','y','g','t','n','f','c','b','p','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','w','p','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'x','f','n','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','n','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','u','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','f','g','t','n','f','c','b','u','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','f','p','f','c','f','w','n','u','e','b','s','s','w','w','p','w','o','p','k','v','d','p'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','w','p','p','w','o','p','n','v','d','e'},
            {'f','s','n','f','n','f','w','b','p','t','e','f','f','w','w','p','w','o','e','n','s','g','e'},
            {'x','y','g','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','k','y','d','e'},
            {'x','f','n','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','f','g','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','w','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','f','n','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','n','v','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','n','v','d','e'},
            {'f','f','n','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','n','v','d','e'},
            {'x','y','e','t','n','f','c','b','u','t','b','s','s','w','w','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','k','v','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','s','p','f','c','f','w','n','u','e','b','s','s','w','w','p','w','o','p','k','s','d','p'},
            {'f','y','n','t','n','f','c','b','w','t','b','s','s','p','p','p','w','o','p','k','v','d','e'},
            {'x','y','e','t','n','f','c','b','w','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','y','g','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'x','y','g','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','k','v','d','e'},
            {'x','f','g','t','n','f','c','b','n','t','b','s','s','g','g','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','n','t','b','s','s','g','p','p','w','o','p','n','y','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','p','p','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','p','t','b','s','s','p','g','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','u','t','b','s','s','g','w','p','w','o','p','k','v','d','e'},
            {'x','y','n','t','n','f','c','b','p','t','b','s','s','w','w','p','w','o','p','k','v','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','p','g','p','w','o','p','n','v','d','e'},
            {'x','y','n','t','n','f','c','b','u','t','b','s','s','p','w','p','w','o','p','n','y','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','p','w','p','w','o','p','k','y','d','e'},
            {'x','f','e','t','n','f','c','b','p','t','b','s','s','w','p','p','w','o','p','n','y','d','e'},
            {'x','y','g','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','n','y','d','e'},
            {'f','f','g','t','n','f','c','b','n','t','b','s','s','g','w','p','w','o','p','k','y','d','e'},
            {'f','f','e','t','n','f','c','b','w','t','b','s','s','w','p','p','w','o','p','n','y','d','e'},
            {'f','f','g','t','n','f','c','b','p','t','b','s','s','g','g','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','n','y','d','e'},
            {'x','y','e','t','n','f','c','b','p','t','b','s','s','g','w','p','w','o','p','n','v','d','e'},
            {'k','y','n','f','f','f','c','n','b','t','?','k','s','p','w','p','w','o','e','w','v','d','p'},
            {'k','s','n','f','n','a','c','b','y','e','?','s','s','o','o','p','o','o','p','b','c','l','e'},
            {'x','s','n','f','n','a','c','b','y','e','?','s','s','o','o','p','n','o','p','b','v','l','e'},
            {'f','s','n','f','n','a','c','b','n','e','?','s','s','o','o','p','o','o','p','b','c','l','e'},
            {'k','y','n','f','y','f','c','n','b','t','?','s','k','w','w','p','w','o','e','w','v','l','p'},
            {'x','s','n','f','n','a','c','b','y','e','?','s','s','o','o','p','o','o','p','o','c','l','e'},

    };


}
