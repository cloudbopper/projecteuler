package euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem91 {

	/**
	 * @param args
	 */
	
	private static int N = 50;
	
	public static void main(String[] args) {
		int count = 0;
		for (int i=0; i<=N; i++) {
			for (int j=0; j<=N; j++) {
				if (i==0 && j==0) continue;
				long side1sqr = i*i + j*j;
				for (int x=0; x<=N; x++) {
					for (int y=0; y<=N; y++) {
						if (x==0 && y==0) continue;
						if (x==i && y==j) continue;
						long side2sqr = x*x + y*y;
						long side3sqr = (x-i)*(x-i) + (y-j)*(y-j);
						List<Long> sidesqrs = new ArrayList<Long>();
						sidesqrs.add(side1sqr);
						sidesqrs.add(side2sqr);
						sidesqrs.add(side3sqr);
						Collections.sort(sidesqrs);
						if (sidesqrs.get(2) == sidesqrs.get(0) + sidesqrs.get(1)) {
							count++;
						}
					}
				}
			}
		}
		System.out.println(count/2);
	}

}
