package Neural_Network;

import java.util.ArrayList;
import java.util.List;
import Objects.*;


public class Split_Data {
	
	public static ArrayList<ArrayList<Case>> splitData(ArrayList<Case> cases, int k) {
		int[] permutation = getPermutation(cases.size());
		int groupSize = cases.size()/k; // largest size such that each of the k groups has same size
		ArrayList<ArrayList<Case>> splitCases = new ArrayList<ArrayList<Case>>();
		for (int i = 0; i < k; i++) {
			splitCases.add(new ArrayList<Case>());
			for (int j = 0; j < groupSize; j++) {
				splitCases.get(i).add(cases.get(permutation[i * groupSize + j]));
			}
		}
		return splitCases;
	}
	
	public static int[] getPermutation(int size) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		java.util.Collections.shuffle(list);
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = list.get(i);
		}
		return array;
	}
}