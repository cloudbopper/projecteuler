package euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Problem95 {

	/**
	 * @param args
	 */

	private static int N = 1000000;
	private static List<Integer> primes = new ArrayList<Integer>();
	private static boolean[] composites = generatePrimes(new boolean[N+1]);
	
	private static int[] factorSums = new int[N+1];
	
	public static void main(String[] args) {
		for (int i=1; i<=N; i++) {
			Map<Integer, Integer> primeToCount = new HashMap<Integer, Integer>();

			int n = i;
			for (int p : primes) {
				if (p*p > n) break;
				while (n%p == 0)  {
					Integer count = primeToCount.get(p);
					if (count == null)
						primeToCount.put(p, 1);
					else
						primeToCount.put(p, count+1);
					n /= p;
				}
			}
			if (n > 1) {
				Integer count = primeToCount.get(n);
				if (count == null)
					primeToCount.put(n, 1);
				else
					primeToCount.put(n, count+1);
			}
			
			if (primeToCount.size() == 0)
				factorSums[i] = 1;
			else {
				long sum = 1;
				for (Entry<Integer, Integer> entry : primeToCount.entrySet()) {
					int multiplicand = 0;
					for (int j=0; j<=entry.getValue(); j++) {
						multiplicand += Math.pow(entry.getKey(), j);
					}
					sum *= multiplicand;
				}
				sum -= i;
				if (sum > Integer.MAX_VALUE)
					factorSums[i] = Integer.MAX_VALUE;
				else
					factorSums[i] = (int) sum;
			}
		}
		System.out.println("Computed sums");

		int maxChainLength = 0;
		for (int i=1; i<=N; i++) {
			int chainLength = 0;
			long current = i;
			long last = 0;
			int count = 0;
			Map<Long, Integer> map = new HashMap<Long, Integer>();
			while (true) {
				if (map.get(current) == null) {
					map.put(current, count);
					count++;
					last = current;
					current = factorSums[(int) current];
					if (current == 1 || current > N)
						break;
					chainLength++;
				} else {
					chainLength = count - map.get(current);
					if (last == current)
						;
					else if (chainLength > maxChainLength) {
						long min = Integer.MAX_VALUE;
						for (long key : map.keySet()) {
							if (map.get(key) < map.get(current)) continue;
							if (key < min) min = key;
							System.out.print(key + ",");
						}
						System.out.println(": " + chainLength + ": " + min);
						maxChainLength = chainLength;
					}
					break;
				}
			}
		}
	}
	
	// Prime number generation
		
	public static boolean[] generatePrimes(boolean[] composites) {
		System.out.println("Start generating primes");
		long startTime = System.currentTimeMillis();
		composites[0] = true;
		composites[1] = true;
		for (int i=2; i<composites.length; i++) {
			for (int j=2; j*i<composites.length; j++) {
				composites[i*j] = true;
			}
		}
		for (int i=0; i<=N; i++) {
			if (!composites[i])
				primes.add(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken to generate primes: " + (endTime - startTime) + " ms");
		return composites;
	}
	
	public static boolean isPrime(int n) {
		return (!composites[n]);
	}
	

}
