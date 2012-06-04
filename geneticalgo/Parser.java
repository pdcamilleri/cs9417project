package geneticalgo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Takes an ARFF file (or similar) and parses stores the training examples,
 * ready to be used used. 
 * TODO: should make this an interface
 */
public abstract class Parser {
    
    private char[][] trainingExamples;
    
    /**
     * parses the input file, and returns the resulting training examples as 2d array of characters
     * (the file reading based off a stack overflow snippet)
     * TODO: this is not at all efficient, some work can be put into it if its too slow.
     * @param filename - full filename, including extension. eg "mushroom.arff"
     * @throws IOException 
     */
    public char[][] parse(String filename) throws IOException {
        
        // read the entire file into fileContents (this is taken from StackOverflow)
        String fileContents;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            fileContents = sb.toString();
        } finally {
            br.close();
        }
        
        String[] split = fileContents.split("\n");
        int lengthOfExample = split[0].split(",").length;
        trainingExamples = new char[split.length][lengthOfExample];
        for (int i = 0; i < split.length; i++) {
            trainingExamples[i] = split[i].replaceAll("'", "").replaceAll(",", "").toCharArray();
        }
        
        return trainingExamples;
    }
    
}
