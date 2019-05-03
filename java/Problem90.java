package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Problem90 {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<int[]> cubes = getCubes();
		
//		cubes = cull(cubes);
		/*int[] a = {0, 5, 6, 7, 8, 9};
		int[] b = {1, 2, 3, 5, 8, 9};
		System.out.println(isValidPair(a,b));*/
		int count = 0;
		for (int i=0; i<cubes.size(); i++) {
			for (int j=i+1; j<cubes.size(); j++) {
				if (isValidPair(cubes.get(i),cubes.get(j)))
					count++;	
			}
		}
		System.out.println(count);
	}
	
	private static List<int[]> cull(List<int[]> cubes) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i=0; i<cubes.size(); i++) {
			int[] cube = cubes.get(i);
			List<Integer> list = new ArrayList<Integer>();
			for (int j=0; j<cube.length; j++) {
				if (cube[j] == 9)
					cube[j] = 6;
				list.add(cube[j]);
			}
			Collections.sort(list);
			String str = "";
			for (int index : list) {
				str += Integer.toString(index);
			}
			if (map.get(str) == null) {
				map.put(str, i);
			}
		}
		
		List<int[]> retVal = new ArrayList<int[]>();
		for (int i : map.values()) {
			retVal.add(cubes.get(i));
		}
		return retVal;
	}
	
	public static boolean isValidPair(int[] cube1, int[] cube2) {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
/*		for (int i=1; i<=9; i++) {
			map.put(i*i, false);
		}*/
		map.put(1, false);
		map.put(4, false);
		map.put(6, false);
		map.put(16, false);
		map.put(25, false);
		map.put(36, false);
		map.put(46, false);
		map.put(64, false);
		map.put(81, false);
					
		for (int i=0; i<cube1.length; i++) {
			for (int j=0; j<cube2.length; j++) {
				int pair1 = Integer.parseInt((Integer.toString(cube1[i])+Integer.toString(cube2[j])).replaceAll("9", "6"));
				int pair2 = Integer.parseInt((Integer.toString(cube2[j])+Integer.toString(cube1[i])).replaceAll("9", "6"));
				if (map.get(pair1) != null)
					map.put(pair1, true);
				if (map.get(pair2) != null)
					map.put(pair2, true);
			}
		}
		for (boolean bool : map.values()) {
			if (!bool) return false;
		}
/*		for (Entry<Integer, Boolean> entry : map.entrySet()) {
			
		}*/
//		if (Arrays.toString(cube1).equals("[0, 5, 6, 7, 8, 9]") || Arrays.toString(cube1).equals("[0, 5, 6, 7, 8, 9]"))
//			System.out.println(Arrays.toString(cube1)+","+Arrays.toString(cube2));
		return true;
	}

	public static List<int[]> getCubes() {
		List<int[]> list = new ArrayList<int[]>();
		int[] cube = new int[6];
		Arrays.toString(cube);
		resetCube(cube, false);
		
		int indexLastUpdated = 5;
		int count = 1;
		list.add(Arrays.copyOf(cube, cube.length));
		
		while (true) {
			if (indexLastUpdated == 5){
				if (cube[indexLastUpdated] == 9) {
					indexLastUpdated--;
				} else {
					cube[indexLastUpdated]++;
					count++;
					list.add(Arrays.copyOf(cube, cube.length));
				}
			} else if (cube[indexLastUpdated] == cube[indexLastUpdated+1]-1) {
				indexLastUpdated--;
				if (indexLastUpdated < 0)
					break;
			} else {
				cube[indexLastUpdated]++;
				for (int i=indexLastUpdated+1; i<cube.length; i++) {
					cube[i] = cube[i-1]+1;
				}
				if (!validate(cube))
					break;
				count++;
				list.add(Arrays.copyOf(cube, cube.length));
				indexLastUpdated = 5;
			}
		}
		System.out.println(count);
		return list;
	}
	
	private static boolean validate(int[] cube) {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		for (int i=0; i<cube.length; i++) {
			if (map.get(cube[i]) != null)
				return false;
			else if (cube[i] > 9)
				return false;
			else
				map.put(cube[i], true);
		}
		return true;
	}

	public static boolean resetCube(int[] array, boolean increment) {
		int min = array[0];
		min = (increment) ? min+1: min;
		for (int i=0; i<array.length; i++)
			array[i] = min+i;
		if (array[array.length-1] > 9)
			return false;
		return true;
	}
}
