package Neural_Network;

import java.util.ArrayList;
import java.util.List;
import Neural_Network.*;
import Objects.*;


public class Split_Data {
	
	public static Case[][] splitData(ArrayList<Case> cases, int k) {
		int[] permutation = getPermutation(cases.size());
		int groupSize = cases.size()/k; // largest size such that each of the k groups has same size
		Case[][] splitCases = new Case[k][groupSize];
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < groupSize; j++) {
				splitCases[i][j] = cases.get(permutation[i * groupSize + j]);
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
