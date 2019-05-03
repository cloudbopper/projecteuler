package euler;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Problem78 {

//	private static Map<String, BigInteger> cache = new HashMap<String, BigInteger>();
	private static Map<Integer, Map<Integer, BigInteger>> cacheMap = new HashMap<Integer, Map<Integer, BigInteger>>();
	private static Map<Integer, BigInteger> pCache = new HashMap<Integer, BigInteger>();
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		BigInteger N = BigInteger.valueOf(1000000);
		
/*		for (int i=1;i<10;i++) {
			System.out.println(i + ": " + pentagonal(i));
		}*/
		
		for (int i=1;; i++) {
			BigInteger pn = ways2(i);
			System.out.println(i + ": " + pn/* + ": " + ways(i).subtract(ways(i-1))*//* + "; cache size: " + cache.size()*/);
			if (pn.mod(N) == BigInteger.ZERO) {
				System.out.println(i);
				break;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " milliseconds.");
	}
	
	// Get partition function using generating function:
	// p(k) = p(k-1) + p(k-2) - p(k-5) - p(k-7) + p(k-12) + p(k-15) - p(k-22) -...
	
	public static BigInteger ways2(int n) {
		if (n < 0) return BigInteger.ZERO;
		if (n == 0) return BigInteger.ONE;
		
		BigInteger bigint = pCache.get(n);
		if (bigint != null) return bigint;
		
		BigInteger retVal = BigInteger.ZERO;
		for (int i=1;; i++) {
			int pentagonal = pentagonal(i);
			if (pentagonal > n) break;
			int m = (i%2==0) ? -i/2 : i/2+1;
			BigInteger sign = (Math.abs(m) % 2 == 0) ? BigInteger.ZERO.subtract(BigInteger.ONE) : BigInteger.ONE;
			retVal = retVal.add(sign.multiply(ways2(n-pentagonal)));
		}
		
		pCache.put(n, retVal);
		return retVal;
	}
	
	public static BigInteger pentagonalBI(int n) {
		int m = (n%2==0) ? -n/2 : n/2+1;
		return (BigInteger.valueOf(m).multiply(((BigInteger.valueOf(3).multiply(BigInteger.valueOf(m))).subtract(BigInteger.ONE)))).divide(BigInteger.valueOf(2));
	}
	
	public static int pentagonal(int n) {
		int m = (n%2==0) ? -n/2 : n/2+1;
		return (m*(3*m-1))/2;
	}
	
	
	// Get partition function using intermediate function:

	public static BigInteger ways (int n) {
		if (n==1) return BigInteger.ZERO;
		return ways(n, 1);
	}
	
	// returns no. of ways to express n as sum of lesser no.s with min being the minimum
	public static BigInteger ways (int n, int min) {

/*		BigInteger cached = cache.get(n + "," + min);
		if (cached != null) return cached;*/
		
		Map<Integer, BigInteger> map = cacheMap.get(n);
		if (map != null) {
			BigInteger cached = map.get(min);
			if (cached != null) return cached;
		} else {
			map = new HashMap<Integer, BigInteger>();
			cacheMap.put(n, map);
		}
		
		BigInteger retVal = BigInteger.ZERO;
		for (int i=min; i <= n/2; i++) {
			retVal = retVal.add(BigInteger.ONE);
			retVal = retVal.add(ways(n-i,i));
		}
		
//		cache.put(n + "," + min, retVal);
		map.put(min, retVal);
		return retVal;
		
	}
}
