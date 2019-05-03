package euler;

public class Problem85 {

	/**
	 * @param args
	 */
	
	private static int REF = 2000000;
	private static int MAX = 3600;
	
	public static void main(String[] args) {
		int diff = REF;
		int minL = 1;
		int minW = 1;
		
		System.out.println(subRectangles(36,77));
		for (int L=1; L<=MAX; L++) {
//			System.out.println("Now at L: " + L);
			for (int W=1; W<=MAX/L;W++) {
				int count = subRectangles(L,W);
				if (Math.abs(REF - count) < diff) {
					diff = Math.abs(REF - count);
					minL = L;
					minW = W;
				}
			}
		}

		System.out.println(minL + "," + minW);
		System.out.println(minL*minW);
	}
	
	public static int subRectangles(int L, int W) {
		int count = 0;
		for (int i=1; i<=L; i++) {
			for (int j=1; j<=W; j++) {
				count += (L-i+1)*(W-j+1);
			}
		}
		return count;
	}

}
