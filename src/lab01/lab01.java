package lab01;
import java.util.Random;


public class lab01
{
	public static void main(String[] args)
	{
		Random random = new Random();
		String bases = "ATCG";
		String codonOI = "AAA"; 
		int count = 0;
		for (int i=0; i<1000;i++)
		{
			String codon = "";
			for (int j=0;j<3;j++)
			{
				int index = random.nextInt(bases.length());
				char base = bases.charAt(index);
				codon += base;
				
				
			}
			if (codon.equals(codonOI))
					count+=1;
			System.out.println(codon);
			
			
		}
		
		System.out.println("Frequency of codon AAA is : " + count);
		System.out.println("The Expected Frequency of codon AAA is : " + Math.round((Math.pow(.25,3)*1000)));
//This expected frequency is approximately the average number of AAA codons if done infinite number of times
// Expected Frequency rounded to nearest whole number. 
		
	}
}

