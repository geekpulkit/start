package com.myarray;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DisArray {

	public static void main(String[] args) {
		int[] arr = { 1, 1, 2, 2, 1 };
		int output = getOutput(5,arr);
		System.out.println(output);

	}

	private static int getOutput(int n, int[] arr) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < n; i++)
			set.add(arr[i]);

		int disCount = set.size();
		int start = 0, end = 0;
		int output = 0;
		Map<Integer, Integer> map = new HashMap<>();

		while (start < n && end < n) {
			int endEle = arr[end];
			if (map.containsKey(endEle)) {
				int count = map.get(endEle);
				count += 1;
				map.put(endEle, count);
			} else {
				map.put(endEle, 1);
			}
			end++;
			while (map.size() == disCount) {
				output += (n - end + 1);
				int startEle = arr[start];
				int startCount = map.get(startEle);
				if (startCount == 1) {
					map.remove(startEle);
				} else {
					map.put(startEle, startCount - 1);
				}
				start++;
			}
		}

		return output;
	}

}
