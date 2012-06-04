package geneticalgo;

import java.util.Map;

/**
 * Single class to compute the fitness function 
 * TODO: perhaps inject this into the GA, and not have it statically called.
 * TODO: factory method?
 * TODO: can then specify the particlar fitness function to use from cmd-line?
 * TODO: note the different fitness functions possible in the spec. {roulette wheel, tournament, rank} selection
 */
public interface FitnessFunction {
	
	public int getFitness(String hypothesis);
	
    public StringBuffer hypothesisToGrepString(String hypothesis);

}
