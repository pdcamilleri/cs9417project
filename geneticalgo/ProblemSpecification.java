package geneticalgo;

import java.util.Map;
/**
 * Interface which algorithms must implement in order to use the GeneticAlgorithm class.
 * This includes things like getting the fitness of a particular hypothesis and generating a hypothesis.
 *
 */
public interface ProblemSpecification {
    
    FitnessFunction getFitnessFunction();
    
    Map<String, Integer> generateHypotheses (int p);
    
}
