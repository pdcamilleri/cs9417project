package geneticalgo;

import balanceScale.BalanceScaleSpecification;
import mushroom.MushroomSpecification;

public class Main {
	
	public static void main(String[] args) {

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
		geneticAlgorithm.execute(9000, 100, 0.2, 0.05);
		
		System.out.println("Closing program...");
	}

}
