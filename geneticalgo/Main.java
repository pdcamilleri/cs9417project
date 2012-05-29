package geneticalgo;

import mushroom.MushroomSpecification;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Starting program...");

		// 1) read input data from file into some data structure
		
		// 2) parse the input data into a usable format
		// (perhaps combine with step 1 at later stage)
		
		// 3) Do Genetic Algo Magic
		
		ProblemSpecification problemSpecification;
		
		if (true) { //put in some condition, like command line arguments?
		    problemSpecification = new MushroomSpecification();
		}
		
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(problemSpecification);
		geneticAlgorithm.execute(117, 100, 200, 0);
		
		System.out.println("Closing program...");
	}

}
