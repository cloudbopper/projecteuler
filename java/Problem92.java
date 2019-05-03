package euler;

import java.util.Arrays;

public class Problem92 {
	
	
	private static int N = 10000000;
	public static void main(String[] args) {

		int count = 0;
		for (int i=1; i<N; i++) {
			/*if (i%10000 == 0) */System.out.println("Now at " + i);
			int n = i;
			while (n != 1 && n != 89) {
				int[] arr = digitise(n);
				n = 0;
				for (int j=0; j<arr.length; j++) {
					n += arr[j]*arr[j];
				}
			}
			if (n == 89) count++;
		}
		System.out.println(count);
	}
	
	public static int[] digitise(int n) {
		int d = 10;
		int[] arr = new int[Integer.toString(n).length()];
		for (int i=0;;i++) {
			int r = n%d;
			arr[i] = r;
			n /= d;
			if (n==0) break;
		}
		return arr;
	}

}
