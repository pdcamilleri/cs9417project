package mushroom;

import geneticalgo.FitnessFunction;
import geneticalgo.ProblemSpecification;

public class MushroomSpecification implements ProblemSpecification {

    public FitnessFunction getFitnessFunction() {
        return new MushoomFitnessFunction();
    }

}
