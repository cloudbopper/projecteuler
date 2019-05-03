package euler;

public class Problem101 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (long i=1; i<=11;i++) {
//			double seq = 1 - Math.pow(i, 1) + Math.pow(i, 2) - Math.pow(i, 3) + Math.pow(i, 4) - Math.pow(a, b);
			long seq = 0;
			for (int j=0;j<=10;j++) {
				seq += Math.pow(-i, j);
			}
			System.out.println(i + ": " + seq);
		}
	}

}
