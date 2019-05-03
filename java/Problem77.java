package euler;

import java.util.HashMap;
import java.util.Map;

public class Problem77 {


	private static Map<String,Integer> cache = new HashMap<String,Integer>();
	
	public static void main(String[] args) {
		/*for (int i=0;i<100;i++) {
			if (isPrime(i)) System.out.println(i);
		}*/
		int MAX = 5000;
		for (int i=1;; i++) {
			int ways = ways(i);
			System.out.println(i + ": " + ways);
			if (ways > MAX)
				break;
		}
		
	}
	
	public static int ways(int n) {
		return ways(n, 2);
	}
	
	// returns no. of ways to break n into lesser primes with min being the minimum
	public static int ways(int n, int min) {
		Integer cached = cache.get(n + "," + min);
		if (cached != null) return cached;
		
		int retVal = 0;
		for (int i=min; i<=n/2; i++) {
			if (!isPrime(i)) continue;
			int increment = (isPrime(n-i)) ? 1 : 0;
			retVal += increment + ways(n-i,i);
		}
		
		cache.put(n + "," + min, retVal);
		return retVal;
	}
	
	// Prime number generation
	private static int N = 100000;
	private static boolean[] composites = generatePrimes(new boolean[N+1]); 
	
	public static boolean[] generatePrimes(boolean[] composites) {
		composites[0] = true;
		composites[1] = true;
		for (int i=2; i<composites.length; i++) {
			for (int j=2; j*i<composites.length; j++) {
				composites[i*j] = true;
			}
		}
		return composites;
	}
	
	public static boolean isPrime(int n) {
		return (!composites[n]);
	}

}
