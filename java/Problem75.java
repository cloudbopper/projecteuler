package euler;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem75 {

	private static int L = 1500000;
	private static int L2 = 10000000;
	// opcode:
	// 0: unidentified
	// 1: invalid sum
	// 2: unique valid sum
	// 3: non-unique valid sum
	private static int[] sum_array = new int[L2];
	private static Map<Triplet,Integer> tripletToSum = new HashMap<Triplet,Integer>();
	
	public static void main(String[] args) {
		
		System.out.println("start");
//		Triplet t = new Triplet(3,4,5);
//		System.out.println(t.equals(new Triplet(5,3,4)));
//		tripletToSum.put(new Triplet(3,4,5), 12);
//		System.out.println(tripletToSum.get(new Triplet(4,3,5)));
//		tripletToSum.put(new Triplet(4,3,5), 15);
		generateTriples(2000);
		for (Triplet t : tripletToSum.keySet()) {
			int sum = tripletToSum.get(t);
			switch (sum_array[sum]) {
			case 0:
				sum_array[sum] = 2;
				break;
			case 1:
				break;
			case 2:
			case 3:
				sum_array[sum] = 3;
				break;
			}
			System.out.println(t.x + "," + t.y + "," + t.z + ": " + sum);
		}
		
		int count = 0;
		for (int i=0; i<=L; i++) {
			if (sum_array[i] == 2)
				count++;
		}
		System.out.println("Count: " + count);
		System.out.println("end");
	}
	
	public static void generateTriples(long N) {
		for (int m=2; m<=N; m++) {
			for (int n=1;n<m; n++) {
				for (int k=1;;k++) {
					int a = k*(m*m - n*n);
					int b = k*(2*m*n);
					int c = k*(m*m + n*n);
					if (a + b + c > L) break;
					Triplet t = new Triplet(a,b,c);
					if (tripletToSum.get(t) == null) {
						tripletToSum.put(t, a+b+c);
					} else {
						continue;
					}
				}
			}
		}
	}
}

class Triplet {
	long x = 0;
	long y = 0;
	long z = 0;
	
	public Triplet(long aa, long bb, long cc) throws RuntimeException {
		long[] array = {aa, bb, cc};
		Arrays.sort(array);
		long a = array[0];
		long b = array[1];
		long c = array[2];
		if (c*c != a*a + b*b) throw new RuntimeException("Not a pythagorean triplet: " + a + "," + b + "," + c);
		x = a;
		y = b;
		z = c;
	}
	
	@Override
	public boolean equals(Object o) {
		Triplet triplet = (Triplet) o;
		return (this.x == triplet.x && this.y == triplet.y && this.z == triplet.z) ? true : false; 
	}
	
	@Override
	public int hashCode() {
//		Long concat = new Long(new String(Long.toString(x)+Long.toString(y)+Long.toString(z)));
//		int retVal = (int) (concat%Integer.MAX_VALUE);
		BigInteger bigint = new BigInteger(new String(new String(Long.toString(x)+Long.toString(y)+Long.toString(z))));
		int retVal = bigint.mod(BigInteger.valueOf(Integer.MAX_VALUE)).intValue();
		return retVal;
	}
}
