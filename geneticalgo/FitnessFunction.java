package geneticalgo;

/**
 * Single class to compute the fitness function 
 * TODO: perhaps inject this into the GA, and not have it statically called.
 * TODO: factory method?
 * TODO: can then specify the particlar fitness function to use from cmd-line?
 * TODO: note the different fitness functions possible in the spec. {roulette wheel, tournament, rank} selection
 */
public class FitnessFunction {
	
	// TODO: implement fitness
	public static int fitness(String hypothesis) {
        int count = 0;
        for (int i = 0; i < hypothesis.length(); i++) {
            count += (hypothesis.charAt(i) - '0');
        }
        System.out.println(count);
		return count;
	}

}
