package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Problem93 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<List<Integer>> top = numbers();
		List<int[]> top = numbers();
		func(combos(top));
	}
	
//	public static List<List<Integer>> numbers() {
//		List<List<Integer>> top = new ArrayList<List<Integer>>();
	private static List<int[]> numbers() {
		List<int[]> top = new ArrayList<int[]>();
		for (int i=1;i<10;i++) {
			for (int j=i+1;j<10;j++) {
				for (int k=j+1;k<10;k++) {
					for (int l=k+1;l<10;l++) {
//						List<Integer> list = new ArrayList<Integer>();
//						top.add(list);
						int[] arr = new int[4];
						top.add(arr);
						arr[0] = i;
						arr[1] = j;
						arr[2] = k;
						arr[3] = l;
					}
				}
			}
		}
		return top;
	}
	
//	public static void func(List<List<Integer>> top) {
	private static Map<String, List<int[]>> combos(List<int[]> top) {
		Map<String, List<int[]>> map = new HashMap<String, List<int[]>>();
//		for (List<Integer> list : top) {
		for (int[] numbers : top) {
			List<int[]> list = new ArrayList<int[]>();
			map.put(Arrays.toString(numbers), list);
			for (int i=0;i<4;i++) {
//				arr[0] = list.get(i);
//				arr[0] = numbers[i];
				for (int j=0; j<4; j++) {
					if (j==i) continue;
//					arr[1] = list.get(j);
//					arr[1] = numbers[j];
					for (int k=0; k<4; k++) {
						if (k==j || k==i) continue;
//						arr[2] = list.get(k);
//						arr[2] = numbers[k];
						for (int l=0; l<4; l++) {
							if (l==i || l==j || l==k) continue;
//							arr[3] = list.get(l);
//							arr[3] = numbers[l];

							int[] arr = new int[4];
							arr[0] = numbers[i];
							arr[1] = numbers[j];
							arr[2] = numbers[k];
							arr[3] = numbers[l];
							System.out.println(Arrays.toString(arr));
							list.add(arr);
						}
					}
				}
			}
		}
		return map;
	}
	
	private static void func(Map<String, List<int[]>> map) {
		int max = 0;
		for (String key : map.keySet()) {
			boolean[] is = new boolean[100000];
			List<int[]> list = map.get(key);
//			if (key.equals("[1, 2, 3, 4]"))
//				System.out.println();
			for (int[] arr : list) {
				for (int op1=0;op1<4;op1++) {
					for (int op2=0;op2<4;op2++) {
						for (int op3=0;op3<4;op3++) {
//							if (op1==0 && op2==2 && op3==2 && arr[0]==4 && arr[0] == 4 && arr[1] == 1 && arr[2] == 3 && arr[3] == 2) {
//								System.out.println();
//							}
							for (int i=0; i<6; i++) {
								// 6 cases:
								double number = 0;
								if (i==0) {
									// op1 > op2 > op3
									number = operate(arr[0], arr[1], op1);
									number = operate(number, arr[2], op2);
									number = operate(number, arr[3], op3);
								} else if (i==1) {
									// op1 > op2, op1 > op3, op3> op2
									number = operate(arr[0], arr[1], op1);
									double number2 = operate(arr[2], arr[3], op3);
									number = operate(number, number2, op2);
								} else if (i==2) {
									// op1 > op2, op1 < op3
									number = operate(arr[2], arr[3], op3);
									double number2 = operate(arr[0], arr[1], op1);
									number = operate(number, number2, op2);
								} else if (i==3) {
									// op1 < op2, op1 > op3
									number = operate(arr[1], arr[2], op2);
									number = operate(arr[0], number, op1);
									number = operate(number, arr[3], op3);
								} else if (i==4) {
									// op1 < op2, op1 < op3, op2 > op3
									number = operate(arr[1], arr[2], op2);
									number = operate(number, arr[3], op3);
									number = operate(arr[0], number, op1);
								} else if (i==5) {
									// op1 < op2, op1 < op3, op2 < op3
									number = operate(arr[2], arr[3], op3);
									number = operate(arr[1], number, op2);
									number = operate(arr[0], number, op1);
								}
								if (isint(number) && number > 0)	is[(int)number] = true;
							}
						}
					}
				}
			}

			for (int i=1; i<is.length;i++) {
//				if (key.equals("[1, 2, 3, 4]"))
//					System.out.println();
				if (!is[i]) {
					if (i-1 > max) {
						max = i-1;
						System.out.println(key + ": " + max);
					}
					break;
				}
			}
		}
	}
	
	private static boolean isint(double d) {
		int i = (int) d;
		if (d - i < 0.01) return true;
		return false;
	}
							
							
							
/*							
							
							
							
							
							
//							Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
							for (int p1=1; p1<=3; p1++) {
//								map2.put(p1, op1);
								for (int p2=1; p2<=3; p2++) {
									if (p2==p1) continue;
//									map2.put(p2, op2);
									for (int p3=1; p3<=3; p3++) {
										if (p3==p2 || p3==p1) continue;
//										map2.put(p3, op3);
										SortedMap<Integer, String> map3 = new TreeMap<Integer, String>();
										map3.put(p1, "op1");
										map3.put(p2, "op2");
										map3.put(p3, "op3");
										int number = 0;
										int operand1 = 0;
										int operand2 = 0;
										for (int priority : map3.keySet()) {
											String val = map3.get(priority);
											if (val.equals("op1")) {
												if (priority == 1) {
													operand1 = arr[0];
													operand2 = arr[1];
												} else if (map3.get(priority-1).equals("op2")) {
													operand1 = arr[0];
													operand2 = number;
												} else {
													operand1 = arr[0];
													operand2 = arr[2];
													
												}
											}
											case "op1":
												break;
											case "op2":
												break;
											case "op3":
												break;
											}
										}
										int[] priorities = new int[3];
										priorities[0] = p1;
										priorities[1] = p2;
										priorities[2] = p3;
										Arrays.sort(priorities);
										int number = Integer.MAX_VALUE;
										for (int i=0; i<priorities.length; i++) {
											int opcode = map2.get(priorities[i]);
											switch (opcode) {
											case 0: // +
												if (number == Integer.MAX_VALUE) number = 
												break;
											case 1: // -
												break;
											case 2: // /
												break;
											case 3:// *
												break;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
		}

	}
	*/

	private static double operate(double i, double j, int opcode) {
		switch (opcode) {
		case 0:
			return i+j;
		case 1:
			return i-j;
		case 2:
			return i*j;
		case 3:
//			if (j==0) return -1; 
			return i/j;
		}
		return 0;
	}

}