package euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem100 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long limit = 1000000000000l;
		int N=100;
//		long[][] solutions = new long[4][N];
		/*solutions[0][0] = 0;
		solutions[1][0] = 0;
		solutions[2][0] = 1;
		solutions[3][0] = 0;*/
		
		int P1 = 5;
		int Q1 = 2;
		int K1 = -2;
		int R1 = 2;
		int S1 = 1;
		int L1 = -1;
		
		int P2 = -1;
		int Q2 = 2;
		int K2 = 1;
		int R2 = 2;
		int S2 = -5;
		int L2 = -1;
		
		Map<Integer, List<Pair>> map = new HashMap<Integer, List<Pair>>();
		List<Pair> list = new ArrayList<Pair>();
//		list.add(new Pair(0,0));
		list.add(new Pair(3,1));
		map.put(0, list);
		
		boolean found = false;
		for (int j=1;j<N;j++) {
			for (Pair pair : map.get(j-1)) {
				long x = P1*pair.x + Q1*pair.y + K1;
				long y = R1*pair.x + S1*pair.y + L1;
				if (x >= 0 && y >= 0) {
					List<Pair> alist = map.get(j);
					if (alist == null) {
						alist = new ArrayList<Pair>();
					}
					alist.add(new Pair(x,y));
					map.put(j, alist);
					System.out.print("(" + x + "," + y + ": " + (x+y) + "); ");
					if (x+y > limit) {
						found = true;
						break;
					}
				}
				x = P2*pair.x + Q2*pair.y + K2;
				y = R2*pair.x + S2*pair.y + L2;
				if (x >= 0 && y >= 0) {
					List<Pair> alist = map.get(j);
					if (alist == null) {
						alist = new ArrayList<Pair>();
					}
					alist.add(new Pair(x,y));
					map.put(j, alist);
					System.out.print("(" + x + "," + y + ": " + (x+y) + "); ");
					if (x+y > limit) {
						found = true;
						break;
					}
				}
			}
			System.out.println();
			if (found)
				break;
			/*long x = P1*solutions[0][j-1] + Q1*solutions[1][j-1] + K1;
			long y = R1*solutions[0][j-1] + S1*solutions[1][j-1] + L1;
			if (x >= 0 && y >= 0) {
				if ()
				map.put(j, new Pair(x,y));
				System.out.print("(" + x + "," + y + ": " + (x+y) + "); ");
				if (x+y > limit) {
					break;
				}
			}
			x = P2*solutions[2][j-1] + Q2*solutions[3][j-1] + K2;
			y = R2*solutions[2][j-1] + S2*solutions[3][j-1] + L2;
			if (x >= 0 && y >= 0) {
				solutions[2][j] = x;
				solutions[3][j] = y;
				System.out.println("(" + x + "," + y + ": " + (x+y) + "); ");
				if (x>0 && y>0 && x+y > limit) {
					break;
				}
			}*/
		}
		
	}
}

class Pair {
	long x;
	long y;
	
	public Pair(long a, long b) {
		x = a;
		y = b;
	}
}
