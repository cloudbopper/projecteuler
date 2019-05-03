package euler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Problem83 {

	private static int N = 80;
//	private static int N = 5;
	private static int[][] weight = new int[N][N];
/*	private static int[][] weight = { {131, 673, 234, 103,18},
		{201,96,342,965,150},
		{630,803,746,422,111},
		{537,699,497,121,956},
		{805,732,524,37,331} };*/
	
	private static boolean[][] visited = new boolean[N][N];
	private static int[][] distance = new int[N][N];

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		initialize(args[0]);
		
		System.out.println(shortestPath(0,0,N-1,N-1));
		
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
	
	// Djikstra's algorithm
	private static int shortestPath(int initialX, int initialY, int destinationX, int destinationY) {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		distance[initialX][initialY] = weight[initialX][initialY];
		visit(initialX, initialY);
		while (true) {
			int minDistance = Integer.MAX_VALUE;
			int x = -1;
			int y = -1;
			for (int ii=0; ii<N; ii++) {
				for (int jj=0; jj<N; jj++) {
					if (!visited[ii][jj]) {
						if (distance[ii][jj] <= minDistance) {
							minDistance = distance[ii][jj];
							x = ii;
							y = jj;
						}
					}
				}
			}
			
			if (x != -1 && y != -1) visit(x,y);
			else break;
		}
		
		return distance[destinationX][destinationY];
	}
	
	private static void visit(int i, int j) {
		
		if (i != N-1 && !visited[i+1][j]) distance[i+1][j] = Math.min(distance[i+1][j], distance[i][j] + weight[i+1][j]);
		if (i != 0 && !visited[i-1][j]) distance[i-1][j] = Math.min(distance[i-1][j], distance[i][j] + weight[i-1][j]);
		if (j != N-1 && !visited[i][j+1]) distance[i][j+1] = Math.min(distance[i][j+1], distance[i][j] + weight[i][j+1]);
		if (j != 0 && !visited[i][j-1]) distance[i][j-1] = Math.min(distance[i][j-1], distance[i][j] + weight[i][j-1]);	

		visited[i][j] = true;
	}
}
