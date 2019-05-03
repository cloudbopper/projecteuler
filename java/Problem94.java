package euler;

import java.math.BigInteger;

public class Problem94 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		
		System.out.println(func());

		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " ms");
	}
	
	private static long N = 1000000000;
	
	private static long func() {
		long sum = 0;
		for (long i=2;;i++) {
			if (i%10000 == 0) System.out.println("Now at " + i);
			long j = i-1;
			long k = i+1;
			long perimeter1 = 2*i+j;
			if (perimeter1 > N) break;
			if (isintarea(i,i,j, perimeter1)) {
				sum += perimeter1;
//				System.out.println(i+","+i+","+j);
			}
			
			long perimeter2 = 2*i+k;
			if (perimeter2 > N) break;
			if (isintarea(i,i,k, perimeter2)) {
				sum += perimeter2;
//				System.out.println(i+","+i+","+k);
			}
		}
		return sum;
	}

	private static boolean isintarea(long a, long b, long c, long perimeter) {
		BigInteger biginta = BigInteger.valueOf(a);
		BigInteger bigintb = BigInteger.valueOf(b);
		BigInteger bigintc = BigInteger.valueOf(c);
//		BigInteger semiperimeter = BigInteger.valueOf(perimeter).divide(BigInteger.valueOf(2));
//		BigInteger sqr = semiperimeter.multiply(semiperimeter.subtract(biginta))
//				.multiply(semiperimeter.subtract(bigintb)).multiply(semiperimeter.subtract(bigintc));
		BigInteger sqr = (biginta.add(bigintb).subtract(bigintc)).multiply(biginta.add(bigintc).subtract(bigintb))
				.multiply(bigintb.add(bigintc).subtract(biginta)).multiply(biginta.add(bigintb).add(bigintc));
		BigInteger sqrt = sqrt(sqr);
		if (sqrt.multiply(sqrt).equals(sqr)) {
			if (sqrt.mod(BigInteger.valueOf(4)).equals(BigInteger.ZERO)) {
				System.out.println(a+","+b+","+c+": "+sqrt.divide(BigInteger.valueOf(4)) + ": " + perimeter);
				return true;
			}
		}
		return false;
		
	}
	
	
	private static final BigInteger TWO = BigInteger.valueOf(2);

	/**
	 * Computes the integer square root of a number.
	 *
	 * @param n  The number.
	 *
	 * @return  The integer square root, i.e. the largest number whose square
	 *     doesn't exceed n.
	 */
	public static BigInteger sqrt(BigInteger n)
	{
	    if (n.signum() >= 0)
	    {
	        final int bitLength = n.bitLength();
	        BigInteger root = BigInteger.ONE.shiftLeft(bitLength / 2);

	        while (!isSqrt(n, root))
	        {
	            root = root.add(n.divide(root)).divide(TWO);
	        }
	        return root;
	    }
	    else
	    {
//	        throw new ArithmeticException("square root of negative number");
	    	return BigInteger.ZERO;
	    }
	}


	private static boolean isSqrt(BigInteger n, BigInteger root)
	{
	    final BigInteger lowerBound = root.pow(2);
	    final BigInteger upperBound = root.add(BigInteger.ONE).pow(2);
	    return lowerBound.compareTo(n) <= 0
	        && n.compareTo(upperBound) < 0;
	}
}
