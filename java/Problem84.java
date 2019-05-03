package euler;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Problem84 {

	private static int D = 4;
	private static int N = 1000000;
//	private static Map<Integer, Double>dieProbabilityMap = new HashMap<Integer, Double>();
	private static Map<Integer, Double>probabilityMap = new HashMap<Integer, Double>();
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		/*for (int i=0; i<N; i++) {
			int moves = roll();
			Double d = dieProbabilityMap.get(moves);
			if (d == null) {
				d = new Double(0);
			}
			d += 1/(double)N;
			dieProbabilityMap.put(moves, d);
		}
		for (int key : dieProbabilityMap.keySet()) {
			Double d = dieProbabilityMap.get(key);
//			d /= N;
			System.out.println("Probability of " + key + ": " + d);
		}*/
		
		monopoly();
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " ms");
	}
	
	public static void monopoly() {
		int currentPosition = 0;
		for (int i=0; i<N; i++) {
			int moves = roll();
			int newPosition = move(currentPosition, moves);
			if (newPosition == CH1 || newPosition == CH2 || newPosition == CH3) {
				newPosition = move(newPosition, chance(newPosition));
			} else if (newPosition == CC1 || newPosition == CC2 || newPosition == CC3) {
				newPosition = move(newPosition, communityChest(newPosition));
			} else if (newPosition == G2J) {
				newPosition = move(newPosition, goToJail());
			}
			currentPosition = newPosition;
			
			Double d = probabilityMap.get(currentPosition);
			if (d == null)
				d = new Double(0);
			d += 100d/(double)N;
			probabilityMap.put(currentPosition, d);
//			System.out.println("Now at: " + currentPosition);
		}
		
		SortedMap<Double, Integer> probabilities = new TreeMap<Double, Integer>();
		for (int i=0; i<totalSquares; i++) {
//			System.out.println("Probability of " + i + ": " + probabilityMap.get(i));
			Double d = probabilityMap.get(i);
			if (d == null)
				System.out.println("Zero probability for " + i);
			else
				probabilities.put(d, i);
		}
		for (double d : probabilities.keySet()) {
			System.out.println("Probability: " + d + " for " + probabilities.get(d));
		}
	}
	
	public static int roll() {
		double d = Math.random()*D;
		int i = (int) Math.ceil(d);
		double d2 = Math.random()*D;
		int i2 = (int) Math.ceil(d2);
		return (i+i2);		
		
	}
	
	private static final int GO = 0;
	private static final int A1 = 1;
	private static final int CC1 = 2;
	private static final int A2 = 3;
	private static final int T1 = 4;
	private static final int R1 = 5;
	private static final int B1 = 6;
	private static final int CH1 = 7;
	private static final int B2 = 8;
	private static final int B3 = 9;
	private static final int JAIL = 10;
	private static final int C1 = 11;
	private static final int U1 = 12;
	private static final int C2 = 13;
	private static final int C3 = 14;
	private static final int R2 = 15;
	private static final int D1 = 16;
	private static final int CC2 = 17;
	private static final int D2 = 18;
	private static final int D3 = 19;
	private static final int FP = 20;
	private static final int E1 = 21;
	private static final int CH2 = 22;
	private static final int E2 = 23;
	private static final int E3 = 24;
	private static final int R3 = 25;
	private static final int F1 = 26;
	private static final int F2 = 27;
	private static final int U2 = 28;
	private static final int F3 = 29;
	private static final int G2J = 30;
	private static final int G1 = 31;
	private static final int G2 = 32;
	private static final int CC3 = 33;
	private static final int G3 = 34;
	private static final int R4 = 35;
	private static final int CH3 = 36;
	private static final int H1 = 37;
	private static final int T2 = 38;
	private static final int H2 = 39;
	private static final int totalSquares = 40;
	
	private static int diff(int initialSquare, int finalSquare) {
		int difference = Math.abs(finalSquare - initialSquare);
		return (finalSquare > initialSquare) ? difference : totalSquares - difference;
	}
	
	private static int move(int initialPosition, int moves) {
		return ((initialPosition + moves) % totalSquares);
	}
	
	public static int communityChest(int currentSquare) {
		double unit = 1/16d;
		double rand = Math.random();
		if (rand < unit) {
			return diff(currentSquare, JAIL);
		} else if (rand < 2d*unit) {
			return diff(currentSquare, GO);
		} else
			return 0;
	}
	
	public static int chance(int currentSquare) {
		double unit = 1/16d;
		double rand = Math.random();
		if (rand < unit) {
			return diff(currentSquare, JAIL);
		} else if (rand < 2d*unit) {
			return diff(currentSquare, GO);
		} else if (rand < 3d*unit) {
			return diff(currentSquare, C1);
		} else if (rand < 4d*unit) {
			return diff(currentSquare, E3);
		} else if (rand < 5d*unit) {
			return diff(currentSquare, H2);
		} else if (rand < 6d*unit) {
			return diff(currentSquare, R1);
		} else if (rand < 8d*unit) {
			if (currentSquare == CH1)
				return diff(currentSquare, R2);
			else if (currentSquare == CH2)
				return diff(currentSquare, R3);
			else
				return diff(currentSquare, R1);
		} else if (rand < 9d*unit) {
			if (currentSquare == CH1)
				return diff(currentSquare, U1);
			else if (currentSquare == CH2)
				return diff(currentSquare, U2);
			else
				return diff(currentSquare, U1);
		} else if (rand < 10d*unit) {
			return -3;
		} else
			return 0;
	}
	
	private static int goToJail() {
		return diff(G2J, JAIL);
	}

}
