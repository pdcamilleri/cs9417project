package geneticalgo;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Starting program...");

		// 1) read input data from file into some data structure
		
		// 2) parse the input data into a usable format
		// (perhaps combine with step 1 at later stage)
		
		// 3) Do Genetic Algo Magic
		
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
		geneticAlgorithm.execute(0, 0, 0, 0);
		
		System.out.println("Closing program...");
	}

}
