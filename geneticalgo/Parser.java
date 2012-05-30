package geneticalgo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

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
        // File.separator may be useful
        
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
    
    /**
     * returns the number of lines in a given file.
     * (code snippet taken from stack overflow)
     */
    private int numberOfLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
            }
            return count;
        } finally {
            is.close();
        }
    }
    
}
