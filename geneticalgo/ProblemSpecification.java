package geneticalgo;

import java.util.Map;

/**
 * Interface which algorithms must implement in order to use the GeneticAlgorithm class.
 * This will include things like how the bitstring represents the hypothesis, how the fitness function works, etc.
 * basically it will include all key parts of the algorithm that are non generic.
 *
 */
public interface ProblemSpecification {
    
    FitnessFunction getFitnessFunction();
    
    Map<String, Integer> generateHypotheses (int p);
    
}
