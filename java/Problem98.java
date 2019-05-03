package euler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Problem98 {

	/**
	 * @param args
	 */
	private static int N = 10;
	private static List<String> words = new ArrayList<String>();
	public static void main(String[] args) {
		initialize(args[0]);
		List<String> anagrams = getAnagrams();
		long max = 0;
		for (String pair : anagrams) {
			String[] strarr = pair.split(",");
			if (strarr[0].equals("CREATION")) break;
			long square = identifySquareAnagramWordPair(strarr[0], strarr[1]);
			if (square > max)
				max = square;
		}
		System.out.println(max);
	}
	
	private static long identifySquareAnagramWordPair(String word1, String word2) {
		System.out.println(word1 + ": " + word2);
		List<Integer> allowed = new ArrayList<Integer>();
		for (int i=0; i<N; i++) {
			allowed.add(i);
		}
		List<Map<String, Integer>> list = getCombinations(word1, allowed, false);
		for (Map<String, Integer> map : list) {
			String str = "";
			for (int i=0; i<word1.length(); i++) {
				String s = Character.toString(word1.charAt(i));
				int val = map.get(s);
				str += Integer.toString(val);
			}
			int m = Integer.parseInt(str);
			if (!isPerfectSquare(m)) continue;
			boolean cont = false;
			str = "";
			for (int i=0; i<word2.length(); i++) {
				String s = Character.toString(word2.charAt(i));
				int val = map.get(s);
				if (i==0 && val==0) {
					cont = true;
					break;
				}
				str += Integer.toString(val);
			}
			if (cont) continue;
			int n = Integer.parseInt(str);
			if (!isPerfectSquare(n)) continue;
			System.out.println(m + ": " + n);
			return Math.max(m, n);
		}
		return 0;
	}
	
	private static boolean isPerfectSquare(long n) {
		double sqrt = Math.sqrt(n);
		long sqrtl = (long) sqrt;
		long sqr = sqrtl*sqrtl;
		if (sqr == n) return true;
		return false;
	}
	
	private static List<Map<String, Integer>> getCombinations(String str, List<Integer> allowed, boolean zeroAllowed) {
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		if (str.length() == 1) {
			for (int i : allowed) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put(str, i);
				list.add(map);
			}
		} else {
			String thisStr = str.substring(0,1);
			String newStr = str.substring(1,str.length());
			for (int index=0; index<allowed.size(); index++) {
				int val = allowed.get(index);
				if (val==0 && !zeroAllowed) continue;
				List<Integer> newAllowed = new ArrayList<Integer>(allowed);
				newAllowed.remove(index);
				List<Map<String, Integer>> alist = getCombinations(newStr, newAllowed, true);
//				if (!zeroAllowed)
//					System.out.println();
				for (Map<String, Integer> map : alist) {
					map.put(thisStr, val);
					list.add(map);
//					for (Entry<String, Integer> entry : map.entrySet()) {
//						String newEntry = entry.getKey();
//						
//					}
				}
			}
		}
		return list;
	}

	private static List<String> getAnagrams() {
		List<String> anagrams = new ArrayList<String>();
		for (int i=0; i<words.size(); i++) {
//			boolean found = false;
			String word1 = words.get(i);
			for (int j=0; j<words.size(); j++) {
				if (i==j) continue;
				String word2 = words.get(j);
				if (areAnagrams(word1, word2)){
/*					if (found) throw new RuntimeException(); // same word has multiple anagrams
					found = true;*/
					String pair = (word1.compareTo(word2) < 0) ? word1+","+word2 : word2+","+word1;
					if (anagrams.contains(pair)) {
//						System.out.println();
					} else {
						anagrams.add(pair);
					}
				}
			}
		}
		return anagrams;
	}
	
	private static boolean areAnagrams(String word1, String word2) {
		if (word1.length() != word2.length()) return false;
		String word1sorted = stringSort(word1);
		String word2sorted = stringSort(word2);
		if (word1sorted.equals(word2sorted))
			return true;
		return false;
	}
	
	private static String stringSort(String word) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<word.length(); i++) {
			char c = word.charAt(i);
			String s = Character.toString(c);
			list.add(s);
		}
		Collections.sort(list);
		String retVal = "";
		for (int i=0; i<list.size(); i++) {
			retVal += list.get(i);
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
				String[] strarr = strLine.split(",");
				for (int j=0; j<strarr.length; j++) {
					words.add(strarr[j].substring(1,strarr[j].length()-1));
				}
			}
			in.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

}
