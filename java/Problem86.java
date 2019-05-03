package euler;


public class Problem86 {

	/**
	 * @param args
	 */
	private static double maxDeviation = 0.001d;
	private static int maxM = 100000;
	private static int[] counts = new int[maxM];
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int N = 1000000;
		
		int M=1;
		int count = 0;
		for (M=1; count < N; M++) {
/*			if (isPrime(M) && M != 3) {
				counts[M] = counts[M-1];
				continue;
			}*/
			count = 0;
			for (int i=1; i<=M; i++) {
				for (int j=i; j<=M; j++) {
					for (int k=M; k<=M; k++) {
						if (isShortestPathIntegral(k,j,i))
							count++;
					}
				}
			}
//			if (count == 0)
//				System.out.print(M + ",");
//			if (count != 0)
//				System.out.print(count + ",");
			System.out.print("Counts computed for M " + M + ": " + count + "; ");
			count += counts[M-1];
			counts[M] = count;
			System.out.println(count);
		}
		M--;
		System.out.println(M + ": " + M + ", count: " + count);
		
//		System.out.println();
//		System.out.println(shortestPath(7,6,1));
//		System.out.println();
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " ms");
	}
	
	public static boolean isShortestPathIntegral(int a, int b, int c) {
		/*double minD = shortestPath(a,b,c);
		int minI = (int) minD;
		if (minD - minI < maxDeviation) {
			return true;
		}
		return false;*/
//		if (a==7 || b==7 || c==7)
//			System.out.println();
		long l = shortestIntegralPath(a,b,c);
		if (l == Long.MAX_VALUE)
			return false;
		return true;
	}

	public static double shortestPath(int a, int b, int c) {
		int[] sides = {a, b, c};
		return Math.min(shortestPath(sides, 0), Math.min(shortestPath(sides, 1), shortestPath(sides,2)));
	}
	
	private static double minStepSize = 0.001d;
	private static double currentX;
	private static double currentDistance;
	private static double nextStepSize;
	
	public static double shortestPath(int[] sides, int dimension) {
		currentX = 0;
		currentDistance = distance(sides, dimension, currentX);
		nextStepSize = 10d;
		
		minimize(sides, dimension, currentX, currentDistance, nextStepSize);
		while(true) {
			if (minStepSize == Math.abs(nextStepSize))
				break;
			else
				minimize(sides, dimension, currentX, currentDistance, nextStepSize);
		}
		return currentDistance;
	}
	
	private static double shortPath(int a, int b, int c) {
		int[] sides = {a, b, c};
		return shortestPath(sides, 0);
	}
	
	private static void minimize(int[] sides, int dimension, double old, double oldDistance, double stepSize) {
		currentX = old + stepSize;
		currentDistance = distance(sides, dimension, currentX);
		if (currentDistance >= oldDistance) {
			stepSize = -stepSize;
			stepSize /= 10d;
			nextStepSize = stepSize;
		}
	}
	
	public static double distance(int[] sides, int dimension, double x) {
		int a = sides[dimension];
		int b = sides[(dimension+1)%3];
		int c = sides[(dimension+2)%3];
		return Math.sqrt(x*x + b*b) + Math.sqrt((a-x)*(a-x) + c*c);
	}
	
	public static long shortestIntegralPath(long a, long b, long c) {
//		int[] sides = {a, b, c};
//		for (int dimension=0; dimension<3; dimension++) {
//			a = sides[dimension];
//			b = sides[(dimension+1)%3];
//			c = sides[(dimension+2)%3];
			double minDistance = Math.sqrt(a*a + b*b + c*c);
			double maxDistance = Math.sqrt(a*a + b*b) + c;
//			for (long k = maxDistance; k > (long) Math.ceil(minDistance); k--) {
			for (long k = (long) Math.ceil(minDistance); k < maxDistance; k++) {
				long t1 = (a*a + c*c - b*b - k*k);
				long t2 = t1*t1 + 4*a*a*b*b - 4*b*b*k*k;
				if (t2 == 0) {
					double x = x(a,b,c,k/*,0,true*/);
					if (givesMinimum(x,a,b,c,k))
						return k;
				}
				/*if (isPerfectSquare(t2)) {
					int sqrt = (int) Math.sqrt(t2);
					double x1 = x(a,b,c,k,sqrt, true);
					double x2 = x(a,b,c,k,sqrt, false);
					if (givesMinimum(x1, a, b, c, k) || givesMinimum(x2, a, b, c, k))
						return k;
//					if (givesMinimum(x2))
//						return k;
				}*/
			}
//		}
		return Long.MAX_VALUE;
	}
	
	private static double x(long a, long b, long c, long k/*, long sqrt, boolean positive*/) {
		long num = -a*(a*a + c*c - b*b - k*k);
//		num += (positive) ? k*sqrt : -k*sqrt;
		double retVal = (double) num;
		retVal /= 2*(k*k - a*a);
		return retVal;
	}
	
	private static boolean givesMinimum(double x, long a, long b, long c, long k) {
		if (x < 0 || x > a) return false;
		double stepSize = 0.1d;
		int[] sides = {(int)a, (int)b, (int)c};
		double less = distance(sides, 0, x-stepSize);
		if (less < k) return false;
		double more = distance(sides, 0, x+stepSize);
		if (more < k) return false;
		return true;
	}
	
	private static boolean isPerfectSquare(long n) {
		if (n < 0) return false;
		double d = Math.sqrt(n);
		long l = (long) d;
		if (d > l)
			return false;
		return true;
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
