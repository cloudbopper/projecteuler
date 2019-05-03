package euler;

import java.util.HashMap;
import java.util.Map;

public class Problem76 {

	/**
	 * @param args
	 */
	
	private static Map<String,Long> cache = new HashMap<String,Long>();
	
	public static void main(String[] args) {
		int N = 100;
		for (int i=1; i<=N; i++) {
			System.out.println(i + ": " + ways3(i));
		}
	}
	
	public static long ways3 (int n) {
		return ways3(n, 1);
	}
	
	// returns no. of ways to express n as sum of lesser no.s with min being the minimum
	public static long ways3 (int n, int min) {
		Long cached = cache.get(n + "," + min);
		if (cached != null) return cached;
		
		long retVal = 0;
		for (int i=min; i <= n/2; i++) {
			retVal += 1 + ways3(n-i,i);
		}
		cache.put(n + "," + min, retVal);
		return retVal;
		
	}
	
	public static long ways2 (int n) {
		if (n == 1) return 0;
		else {
			long retVal = 0;
			for (int i=2;i<=n;i++) {
				retVal += i/2;
			}
			return retVal;
		}
	}
	
	public static long ways (int n) {
		if (n == 1) return 0;
		return (long) (ways(n-1) + gap(n));
	}
	
	public static long gap (int n) {
		if (n == 2) return 1;
		else if (n % 2 == 1) return gap(n-1);
		else return gap(n-1) + (n-1)/2;
	}

}
