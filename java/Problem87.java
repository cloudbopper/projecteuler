package euler;

import java.math.BigInteger;

public class Problem87 {

	/**
	 * @param args
	 */

	private static int N = 50000000;
	private static boolean[] expressibles = new boolean[N+1];
	private static boolean[] composites = generatePrimes(new boolean[N+1]);
	
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		BigInteger biN = BigInteger.valueOf(N);
		int limit = (int) Math.ceil(Math.sqrt(N));
		for (int i=2; i<limit; i++) {
			if (!isPrime(i))
				continue;
			
			System.out.println("Now at " + i);
			
			BigInteger sum = BigInteger.ZERO;
			BigInteger bii = BigInteger.valueOf(i);
			bii = bii.pow(4);
			if (bii.compareTo(biN) == 1)
				break;
			
			for (int j=2; j<limit; j++) {
				if (!isPrime(j))
					continue;
				
				BigInteger bij = BigInteger.valueOf(j);
				bij = bij.pow(3);
				if (bij.compareTo(biN) == 1)
					break;
				
				sum = bii.add(bij);
				if (sum.compareTo(biN) == 1)
					break;
				
				for (int k=2; k<limit; k++) {
					if (!isPrime(k))
						continue;
					BigInteger bik = BigInteger.valueOf(k);
					
					bik = bik.pow(2);
					if (bik.compareTo(biN) == 1)
						break;
					
					BigInteger finalSum = sum.add(bik);
					if (finalSum.compareTo(biN) == 1)
						break;
					
					expressibles[finalSum.intValue()] = true;
				}
			}
		}
		
		int count = 0;
		for (int i=0; i<expressibles.length; i++) {
			if (expressibles[i]) count++;
		}
		System.out.println("Expressibles: " + count);
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " ms");
	}

	// Prime number generation
	public static boolean[] generatePrimes(boolean[] composites) {
		long startTime = System.currentTimeMillis();
		System.out.println("Start prime no. generation");
		composites[0] = true;
		composites[1] = true;
		for (int i=2; i<composites.length; i++) {
			for (int j=2; j*i<composites.length; j++) {
				composites[i*j] = true;
			}
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Time taken to generate primes: " + (endTime - startTime) + " ms");
		return composites;
		
	}
	
	public static boolean isPrime(int n) {
		return (!composites[n]);
	}
}
