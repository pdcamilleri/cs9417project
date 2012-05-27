package mushroom;

import geneticalgo.FitnessFunction;

public class MushoomFitnessFunction implements FitnessFunction {

    // this array holds the number of values for each attribute.
    // so for mushroom, first attribute is cap-shape, which can take on 6 values. hence first value in the array is 6.
    private int[] lengthsOfAttributes = {
            6, 4, 10, 2, 9, 4, 3, 2, 12, 2, 6, 4, 4, 9, 9, 2, 4, 3, 8, 6, 7, 1
    };
    
    private final int LENGTH_OF_BITSTRING = 128;
    private final int NUMBER_OF_ATTRIBUTES = 22;
    
    
    // 1010101101011010101010101001011010101010110101010...
    // AAAAAABBBBCCCCCCCCCCDDEEEEEEEEEFFFFGGGHHIIIIIIIII...
    public int getFitness(String hypothesis) {
        char[] attributeDecoding = new char[NUMBER_OF_ATTRIBUTES];
        
        for (int i = 0, position = 0; i < NUMBER_OF_ATTRIBUTES; i++) {
            String attribute = hypothesis.substring(position, position + lengthsOfAttributes[i]);
            
            // move position along, ready for the next attribute
            position += lengthsOfAttributes[i];
            
            // pass bitstring of this attribute to some decoder that will map bitstrings to the correct value. hardcoded ofcourse
            attributeDecoding[i] = attribute.charAt(0); // need the mapping function in here
            
        }
        
        return 0;
    }
    
    /**
     * takes a bitString representing a hypothesis and decomposes it down so its fitness can be calculated. 
     */
    private void decode(String hypothesis) {
        
    }

}
