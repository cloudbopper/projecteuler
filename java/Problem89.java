package euler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Problem89 {

	/**
	 * @param args
	 */
	private static int N = 1000;
	private static String[] romans = new String[N];
	
	public static void main(String[] args) {
		initialize(args[0]);
		int count = 0;
		for (int i=0; i<N; i++) {
			int n = romanToArabic(romans[i]);
			String minimalRoman = arabicToRoman(n);
			count += romans[i].length() - minimalRoman.length();
		}
		System.out.println(count);
	}
	
	private static int romanToArabic(String str) {
		int retVal = 0;
		int lastNumber = 0;
		int equivalent = 0;
		for (int i=0; i<str.length(); i++) {
			switch (str.charAt(i)) {
			case 'I':
				equivalent = 1;
				equivalent = (equivalent > lastNumber) ? equivalent - 2*lastNumber : equivalent;
				lastNumber = equivalent;
				break;
			case 'V':
				equivalent = 5;
				break;
			case 'X':
				equivalent = 10;
				break;
			case 'L':
				equivalent = 50;
				break;
			case 'C':
				equivalent = 100;
				break;
			case 'D':
				equivalent = 500;
				break;
			case 'M':
				equivalent = 1000;
				break;
			default:
				throw new RuntimeException();
			}
			
			equivalent = (equivalent > lastNumber) ? equivalent - 2*lastNumber : equivalent;
			lastNumber = equivalent;
			retVal += equivalent;
//			System.out.println(str.charAt(i));
		}
		return retVal;
	}
	
	private static String arabicToRoman(int n) {
		String retVal = "";
		int d = (int) Math.pow(10, Integer.toString(n).length());
		
		while (true) {
			int r = n%d - n%(d/10);
			if (r >= 1000) {
				for (int i=0;i<r/1000;i++)
					retVal += "M";
			} else if (r == 900) {
				retVal += "CM";
			} else if (r >= 500) {
				retVal += "D";
				for (int i=0; i<(r-500)/100;i++)
					retVal += "C";
			} else if (r == 400) {
				retVal += "CD";
			} else if (r >= 100) {
				for (int i=0; i<(r/100);i++)
					retVal += "C";
			} else if (r == 90) {
				retVal += "XC";
			} else if (r >= 50) {
				retVal += "L";
				for (int i=0; i<(r-50)/10;i++)
					retVal += "X";
			} else if (r == 40) {
				retVal += "XL";
			} else if (r >= 10) {
				for (int i=0; i<(r/10);i++)
					retVal += "X";
			} else if (r == 9) {
				retVal += "IX";
			} else if (r >= 5) {
				retVal += "V";
				for (int i=0; i<(r-5);i++)
					retVal += "I";
			} else if (r == 4) {
				retVal += "IV";
			} else {
				for (int i=0; i<r; i++)
					retVal += "I";
			}

			d /= 10;
			if (d == 1) break;
		}
		
		return retVal;
		
	}
	
	private static void initialize(String fileName) {
		try {
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			for (int i=0; (strLine = br.readLine()) != null; i++) {
				romans[i] = strLine;
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}

}
