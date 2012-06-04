package geneticalgo;

import mushroom.MushroomSpecification;
import balanceScale.BalanceScaleSpecification;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Starting program...");

		// 1) read input data from file into some data structure
		
		// 2) parse the input data into a usable format
		// (perhaps combine with step 1 at later stage)
		
		// 3) Do Genetic Algo Magic
		
		ProblemSpecification problemSpecification;
		
		if (true) { //put in some condition, like command line arguments?
		    problemSpecification = new BalanceScaleSpecification();
//			problemSpecification = new MushroomSpecification();
//			problemSpecification = new ProblemSpecification();
		}
		
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(problemSpecification);
		//                       fT  pop   xo   mut
		//                       fT    p    r     m
		geneticAlgorithm.execute(600, 100, 0.1, 0.01);
		
		System.out.println("Closing program...");
	}

}
