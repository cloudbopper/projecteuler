package euler;

import java.math.BigDecimal;

public class Problem80 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int count = 0;
		for (int i=0; i<=N; i++) {
			if (isImperfectSquare(i)) {
//				System.out.println(i + ": " + Math.sqrt(i));
				count += getSumOfDecimalDigits(getSquareRoot(i));
			} else
				continue;
		}
		System.out.println(count);
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " ms");
	}
	
	public static int getSumOfDecimalDigits(BigDecimal bigdec) {
		int retVal = 0;
		String str = bigdec.toString();
		str = str.replaceAll("\\.","");
		for (int i=0; i<100; i++) {
			System.out.print(str.charAt(i));
			retVal += Integer.parseInt(String.valueOf(str.charAt(i)));
		}
		System.out.println(": " + retVal);
		return retVal;
	}
	
	public static BigDecimal getSquareRoot(int n) {
		double dsqrt = Math.sqrt(n);
		double dless = dsqrt - 0.0001d;
		double dmore = dsqrt + 0.0001d;
		BigDecimal retVal = converge(BigDecimal.valueOf(dless), BigDecimal.valueOf(dsqrt), BigDecimal.valueOf(dmore), n);
		return retVal;
	}
	
	
	private static BigDecimal ref = BigDecimal.valueOf(0.1).pow(101);
	public static BigDecimal converge(BigDecimal min, BigDecimal mid, BigDecimal max, int n) {
		if (max.subtract(min).compareTo(ref) == -1) {
			System.out.println(min);
			System.out.println(mid);
			System.out.println(max);
			return mid;
		}
		BigDecimal esquare = mid.multiply(mid);
		BigDecimal bigdec = null;
		switch (esquare.compareTo(BigDecimal.valueOf(n))) {
		case -1:
			bigdec = mid.add(max).divide(BigDecimal.valueOf(2));
			return converge(mid, bigdec, max, n);
		case 0:
			throw new RuntimeException();
		case 1:
			bigdec = mid.add(min).divide(BigDecimal.valueOf(2));
			return converge(min, bigdec, mid, n);
		}
		return null;
	}


	private static int N=100;
	private static boolean[] perfectSquares = getPerfectSquares(new boolean[N+1]);
	
	private static boolean[] getPerfectSquares(boolean[] array) {
		for (int i=0; i*i <= N; i++) {
			array[i*i] = true;
		}
		return array;
	}

	private static boolean isImperfectSquare(int n) {
		return (!perfectSquares[n]);
	}
}
