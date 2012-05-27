package mushroom;

import geneticalgo.FitnessFunction;

public class MushoomFitnessFunction implements FitnessFunction {

    public int getFitness(String hypothesis) {
        int count = 0;
        for (int i = 0; i < hypothesis.length(); i++) {
            count += (hypothesis.charAt(i) - '0');
        }
        return count;
    }

}
