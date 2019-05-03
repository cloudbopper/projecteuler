package euler;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Problem99 {

	/**
	 * @param args
	 */
	private static List<Point> pairs = new ArrayList<Point>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initialize(args[0]);
		int maxval = 0;
		int maxindex = 0;
		for (int i=0; i<pairs.size(); i++) {
			int base = pairs.get(i).x;
			int exp = pairs.get(i).y;
			int n = (int) (exp*Math.log(base));
			if (n > maxval) {
				maxindex = i+1;
				maxval = n;
			}
		}
		System.out.println(maxindex);
	}
	
	private static void initialize(String fileName) {
		try {
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			for (int i=0; (strLine = br.readLine()) != null; i++) {
				String[] strarr = strLine.split(",");
				Point pair = new Point(Integer.parseInt(strarr[0]), Integer.parseInt(strarr[1]));
				pairs.add(pair);
			}
			in.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

}
