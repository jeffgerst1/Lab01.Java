package lab01;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;



// With the help of chat gpt i was able to transform the code to run 10000 runs of 10000 codon runs at the specified base frequencies. This ran 10000 runs to test the average frequency of codon 'AAA' compared to the
//expected frequency. This demonstrates that the code is working correctly 
public class CustomFreq{
	public static void main(String[] args) {
		Random random = new Random();
		Map<Character, Double> baseProbabilities = new HashMap<>();
		baseProbabilities.put('A', 0.12);
	    baseProbabilities.put('C', 0.38);
	    baseProbabilities.put('G', 0.39);
	    baseProbabilities.put('T', 0.11);
		
	    String codonOI = "AAA";
	    int numRuns = 10000;
	    int codonsPerRun = 10000;
	    int totalAAA = 0;
	    
	    for (int sim = 0; sim < numRuns; sim++) {
	    	int countAAA = 0;
	    	for (int i = 0; i < codonsPerRun; i++) {
	    		String codon = generateCodon(baseProbabilities, random);
	    		if (codon.equals(codonOI)) {
	    			countAAA++;
	    		}
	    	}
	    	totalAAA += countAAA;
	    	System.out.println("Run " + (sim + 1) + ": Count of codon 'AAA' = " + countAAA);
	    }

	    // Calculate the expected frequency of 'AAA'
	    double expectedFrequency = Math.pow(baseProbabilities.get('A'), 3) * codonsPerRun;
	    double averageCountAAA = (double) totalAAA / numRuns;

	    // Display overall results
	    System.out.println("\nOverall Results:");
	    System.out.println("Total Count of 'AAA' across all simulations: " + totalAAA);
	    System.out.println("Average Count of 'AAA' per simulation: " + averageCountAAA);
	    System.out.println("Expected Frequency of 'AAA' per simulation: " + expectedFrequency);
	    
	    // Display base frequencies
	    System.out.println("\nBase Frequencies:");
	    System.out.println("Frequency of Base A: " + baseProbabilities.get('A'));
	    System.out.println("Frequency of Base C: " + baseProbabilities.get('C'));
	    System.out.println("Frequency of Base G: " + baseProbabilities.get('G'));
	    System.out.println("Frequency of Base T: " + baseProbabilities.get('T'));
	}

	private static String generateCodon(Map<Character, Double> baseProbabilities, Random rand) {
		StringBuilder codon = new StringBuilder();
		for (int j = 0; j < 3; j++) {
			codon.append(generateBase(baseProbabilities, rand));
		}
		return codon.toString();
	}

	private static char generateBase(Map<Character, Double> baseProbabilities, Random rand) {
	    double r = rand.nextDouble(); // Generate a random number between 0.0 and 1.0
	    double cumulativeProbability = 0.0;
	    
	    for (Map.Entry<Character, Double> entry : baseProbabilities.entrySet()) {
	        cumulativeProbability += entry.getValue();
	        if (r <= cumulativeProbability) {
	            return entry.getKey(); // Return the base character based on the probability
	        }
	    }
	    
	    // If we reach here, something went wrong with the probabilities
	    throw new IllegalStateException("Random number did not map to any base. Check the probabilities.");
	}
}


