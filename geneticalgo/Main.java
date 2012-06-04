package geneticalgo;

import balanceScale.BalanceScaleSpecification;
import mushroom.MushroomSpecification;

public class Main {
	
	public static void main(String[] args) {

		// 1) read input data from file into some data structure
		
		// 2) parse the input data into a usable format
		// (perhaps combine with step 1 at later stage)
		
		// 3) Do Genetic Algo Magic
		
		ProblemSpecification problemSpecification = null;
	
		if (args.length == 0) {
		    System.out.println("Must have command line argument of \"balanceScale\" or \"mushroom\"");
		    return;
		}
		
		if (args[0].startsWith("b")) {
		    problemSpecification = new BalanceScaleSpecification();
		} else if (args[0].startsWith("m")) {
			problemSpecification = new MushroomSpecification();
		}
		
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(problemSpecification);
		//                       fT  pop   xo   mut
		//                       fT    p    r     m
		geneticAlgorithm.execute(600, 100, 0.1, 0.01);
		
		System.out.println("Closing program...");
	}

}
