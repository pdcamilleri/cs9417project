package mushroom;

import geneticalgo.FitnessFunction;

import java.util.Map;

public class MushoomFitnessFunction implements FitnessFunction {

    // this array holds the number of values for each attribute.
    // so for mushroom, first attribute is cap-shape, which can take on 6 values. hence first value in the array is 6.
    private int[] lengthsOfAttributes = {
            6, 4, 10, 2, 9, 4, 3, 2, 12, 2, 6, 4, 4, 9, 9, 2, 4, 3, 8, 6, 7, 1
    };

    private char[][] trainingExamples = {
            {'b','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'c','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'f','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'k','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'s','s','n','t','p','f','c','n','k','e','e','s','s','w','w','p','w','o','p','k','s','u','p'},
            {'x','s','y','t','a','f','c','b','k','e','c','s','s','w','w','p','w','o','p','n','n','g','e'}
    };

    private char[][] attributes = {
        { 'b', 'c', 'f', 'k', 's', 'x'}
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

    private final int LENGTH_OF_BITSTRING = 117;
    private final int NUMBER_OF_ATTRIBUTES = 22;


    // 1010101101011010101010101001011010101010110101010...
    // AAAAAABBBBCCCCCCCCCCDDEEEEEEEEEFFFFGGGHHIIIIIIIII...
    public int getFitness(String hypothesis) {

        for (int k = 0; k < trainingExamples.length; k++) {
            for (int i = 0; i < trainingExamples[k].length; i++) { // trainingexamples[k] will always be the same....can optimize this
                char value = trainingExamples[k][i];
                int j;
                for (j = 0; j < attributes[i].length; j++) {
                    if (attributes[i][j] == value) break;
                }
                System.out.println("position is " + j);
                break; // have to break here since ive only put one value inside of the attributes array
            }
        }



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

        return 0;
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

}
