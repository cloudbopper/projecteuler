package euler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Problem81 {

	private static int N = 80;
	private static int[][] weight = new int[N][N];
/*	private static int[][] weight = { {131, 673, 234, 103,18},
		{201,96,342,965,150},
		{630,803,746,422,111},
		{537,699,497,121,956},
		{805,732,524,37,331} };*/
	private static int[][] cache = new int[N][N];

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		initialize(args[0]);
		System.out.println(minimalPathSum(0,0));
		
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " ms");
	}
	
	private static void initialize(String fileName) {
		try {
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			for (int i=0; (strLine = br.readLine()) != null; i++) {
				String[] strarr = strLine.split(",");
//				System.out.print("Line " + i + ": ");
				for (int j=0;j<N;j++) {
					weight[i][j] = Integer.parseInt(strarr[j]);
//					System.out.print(weight[i][j]+",");
				}
//				System.out.println();
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	// Finds minimal path sum going right/down from point (i,j) to (N-1,N-1);
	public static int minimalPathSum(int i, int j) {
		if (cache[i][j] != 0) {
			return cache[i][j];
		}
		
		if (i == N-1 && j == N-1) {
			int retVal = weight[i][j];
			cache[i][j] = retVal;
			return retVal;
		} else if (i == N-1) {
			int retVal = weight[i][j] + minimalPathSum(i,j+1);
			cache[i][j] = retVal;
			return retVal;
		} else if (j == N-1) {
			int retVal = weight[i][j] + minimalPathSum(i+1,j);
			cache[i][j] = retVal;
			return retVal;
		}
		
		int retVal = weight[i][j] + Math.min(minimalPathSum(i+1,j), minimalPathSum(i,j+1));
		cache[i][j] = retVal;
		return retVal;
	}

}
