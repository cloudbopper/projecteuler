package euler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Problem88 {

	private static int N = 12000;
	private static boolean[] composites = generatePrimes(new boolean[N+1]);
	
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		
		Map<Long, Boolean> numbers = new HashMap<Long, Boolean>();
		for (int k=2; k<=N; k++) {
//			System.out.println(k + ": " +minimalProductSumNumber(k));
			int n = minimalProductSumNumber(k);
			numbers.put((long) n, true);
		}
		
		long sum = 0;
		for (long key : numbers.keySet()) {
			sum += key;
		}
		System.out.println(sum);
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " ms");
	}
	
	public static int minimalProductSumNumber(int k) {
//		List<Integer> numbers = new ArrayList<Integer>();
		Map<Integer, String> numbersMap = new HashMap<Integer, String>();
		
		int[] arr = new int[k];
		Arrays.fill(arr, 1);
		arr[0] = 2;
		arr[1] = 2;
		
		boolean doneReset = false;
		int lastIndex = k-1;
		int indexLastUpdated = 0;
		
		while (true) {
			int sum = sum(arr, lastIndex);
			int product = product(arr, lastIndex);
			if (sum < product) {
//				System.out.println(Arrays.toString(arr));
				
				if (indexLastUpdated == lastIndex) {
					if (doneReset) {
						break;
					} else
						doneReset = true;

					lastIndex++;
					if (lastIndex == k)
						break;
					indexLastUpdated = 1;
					for (int i=0; i<= lastIndex; i++) {
						arr[i] = 2;
					}
				} else {
					int val = 1 + arr[indexLastUpdated + 1];
					indexLastUpdated++;
					for (int i=0; i<= indexLastUpdated; i++) {
						arr[i] = val;
					}	
				}
			} else {
				doneReset = false;
//				System.out.println(Arrays.toString(arr));
				arr[0]++;
				indexLastUpdated = 0;
				if (sum == product) {
//					numbers.add(sum);
					numbersMap.put(sum, Arrays.toString(arr));
				}
					
			}/* else {
//				System.out.println(Arrays.toString(arr));
				return sum;
			}*/
		}
		
		int min = Integer.MAX_VALUE;
		String minVal = "";
		for (Entry<Integer, String> entry : numbersMap.entrySet()) {
			if (entry.getKey() < min) {
				min = entry.getKey();
				minVal = entry.getValue();
			}
		}
		System.out.println(k + ": " + min/* + ": " + minVal*/);
		return min;
//		return Collections.min(numbers);
	}
	
	public static int sum(int[] array, int lastNonOneIndex) {
		int retVal = 0;
		for (int i=0; i<=lastNonOneIndex; i++) {
			retVal += array[i];
		}
		retVal += array.length - lastNonOneIndex - 1;
		return retVal;
	}
	
	public static int product(int[] array, int lastNonOneIndex) {
		int retVal = 1;
		for (int i=0; i<=lastNonOneIndex; i++) {
			retVal *= array[i];
			if (retVal <= 0) return Integer.MAX_VALUE;
		}
		return retVal;
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