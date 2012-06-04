package geneticalgo;

public class DefaultFitnessFunction implements FitnessFunction {

  public int getFitness(String hypothesis) {
		  int count = 0;
	      for (int i = 0; i < hypothesis.length(); i++) {
	              count += (hypothesis.charAt(i) - '0');
	      }
	      return count;
	}

	public StringBuffer hypothesisToGrepString(String hypothesis) {
		return new StringBuffer("");
	}

}