package euler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem96 {

	/**
	 * @param args
	 */
	private static List<int[][]> puzzles = new ArrayList<int[][]>();
	
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		
		initialize(args[0]);
		int count = 0;
		for (int i=0; i<puzzles.size(); i++) {
			int[][] puzzle = puzzles.get(i);
			int[][] solvedPuzzle = solveSudoku(puzzle/*, 0*/);
			String str = "";
			for (int j=0; j<3; j++) {
				str += Integer.toString(solvedPuzzle[0][j]);
			}
			count += Integer.parseInt(str);
			
			String solved = "";
			System.out.print(i + ": ");
			for (int[] arr : solvedPuzzle) {
				System.out.print(Arrays.toString(arr) + ";");
			}
			System.out.println();
		}
		System.out.println(count);
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " ms");
	}
	
	private static int[][] solveSudoku(int[][] puzzle/*, int recurseCount*/) {
		int sudoku[][] = new int[9][9];
		int x = 0;
		int y = 0;
		int count = 0;
		List<Integer> possibles = null;
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				sudoku[i][j] = puzzle[i][j];
				if (sudoku[i][j] != 0) {
					count++;
					continue;
				}
				List<Integer> list = getValidValues(puzzle, i, j);
				if (possibles == null || list.size() < possibles.size()) {
					possibles = list;
					x = i;
					y = j;
				}
			}
		}
		
		if (count == 80)
			return sudoku; // sudoku has been filled
		
		if (possibles == null)
			return null; // no solution: previous possible was invalid
		
		for (int possible : possibles) {
			sudoku[x][y] = possible;
			int[][] updatedSudoku = solveSudoku(sudoku);
			if (updatedSudoku == null) continue; // this possible leads to an invalid state
			return updatedSudoku;
		}
		return null; // none of the possibles were valid
	}
	
	private static List<Integer> getValidValues(int[][] sudoku, int x, int y) {
		boolean[] impossibles = new boolean[10];
		for (int i=0; i<9; i++) {
			impossibles[sudoku[i][y]] = true;
		}
		for (int j=0; j<9; j++) {
			impossibles[sudoku[x][j]] = true;
		}
		int istart = (x/3)*3;
		int jstart = (y/3)*3;
		for (int i=istart; i<istart+3; i++) {
			for (int j=jstart; j<jstart+3; j++) {
				if (i==x && j==y) continue;
				impossibles[sudoku[i][j]] = true;
			}
		}
		List<Integer> possibles = new ArrayList<Integer>();
		for (int i=0; i<10; i++) {
			if (impossibles[i]) continue;
			possibles.add(i);
		}
		return possibles;
	}

	private static void initialize(String fileName) {
		try {
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int[][] puzzle = null;
			for (int i=0; (strLine = br.readLine()) != null; i++) {
				if (i%10==0) {
					puzzle = new int[9][9];
					continue;
				}
				for (int j=0; j<9; j++) {
					puzzle[i%10-1][j] = Integer.parseInt(String.valueOf(strLine.charAt(j)));
				}
				if ((i+1)%10==0)
					puzzles.add(puzzle);
			}
			in.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

}
