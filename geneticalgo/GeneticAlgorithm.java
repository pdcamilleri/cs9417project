package geneticalgo;

import java.util.HashMap;
import java.util.Map;

/**
 * Genetic Algorithm implementation
 * 
 * Do this by generating a set of random hypothesis and testing them to see if they are any good (using fitness function)
 * if they are good (ie, above the threshold), keep;
 * if they are bad (below threshold), throw them away.
 * replace the bad guys with some better guys produced by mutating the good guys, getting the good guys to breed, etc
 * repeat the algorithm using this new set of hypothesis which should be a better group than the previous
 * eventually should be able to get to a really good set of hypothesis after repeating a number of times
 * may need to repeat entire algo to avoid local maxima as the initial set of hypothesis are generated at random
 *
 */

public class GeneticAlgorithm {
	
	/**
	 * GA(Fitness, Fitness_Threshold, p, r, m) 
	 */
	public void execute(int fitnessThreshold) {
		
		// initialise: P <- p random hypothesis
		// hypothesis: maps bitStrings to fitness
		Map<String, Integer> hypothesis = new HashMap<String, Integer>();
		generateRandomBitStrings(hypothesis);
		
		// evaluate: for each h in P, compute fitness(h)
		for (String s : hypothesis.keySet()) {
			hypothesis.put(s, FitnessFunction.fitness(s));
		}
		// TODO: combine above steps for efficiency. i.e. get the fitness value as bitStrings are put into the map
		
		while (SetUtilClass.maxFitness(hypothesis) < fitnessThreshold) {
		// while [max,,h,, fitness(h)] < fitness_threshold
			// do
			//    select: probabilistically select (1-r)p members of P to add to Ps
			//    Prob(h,,i,,) = Fitness(h,,i,,) / sum from j=1 to p of (Fitness h,,j,,)
			//    so the probability that hypothesis h,,i,, will be selected is 
			//    the fitness of h,,i,, divided by the sum of all fitnesses of al hypothesis???
			//    TODO: need to check the above formula
			//
			
			//    crossover: probabilistically select r-p/2 pairs of hypos from P.
			//    produce two offspring for each pair using crossover operator, add to Ps
			
			//    mutate: invert a randomly selected bit in m * p random hypos from set Ps
			
			//    update: P <- Ps
			
			//    evaluate for each h in P, compute fitness(h)
			
			// done - end of while loop
		}
		// at this point, atleast one one hypothesis in P is above the threshold
		
		// return the hypo with highest fitness in P
		String best = SetUtilClass.maxHypothesis(hypothesis);
		
		System.out.println("Best hypo:" + best);
		
	}

	/**
	 * TODO should this go here? or in some sort of util class?
	 */
	private void generateRandomBitStrings(Map<String, Integer> hypothesis) {
		hypothesis.put("1001110001", null);
	}

}
