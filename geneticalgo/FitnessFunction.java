package geneticalgo;

/**
 * Interface for the fitness function
 */
public interface FitnessFunction {
	
	public int getFitness(String hypothesis);
	
    public StringBuffer hypothesisToGrepString(String hypothesis);

}
