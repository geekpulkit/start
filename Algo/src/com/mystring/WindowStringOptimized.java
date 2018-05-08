package com.mystring;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class WindowStringOptimized {

	HashMap<Character, Counter> hm = new HashMap<>();
	

	class Counter {
		int count;

		@Override
		public String toString() {
			return "count=" + count + "";
		}

	}

	public static void main(String[] args) {
		/*
		 * String s =
		 * "j67yluuhZeBQ5WCYpPz0ff39V92gJaaGHb3onlwjh3SH2mllTHGLQ9W8QsDGVH1lbLqxgF1LssG2pkIpyUhE3XUrnnNvLVFthuVVjB0r1BnP4G1HgXTcWlEMf34yaqhiEtjydTweHwIcVTN2z4uOgIC0oluZf4TiF19Amliay9BVu9U1V9cE4VB453fFAEk08DgqDVERGhKXWpmY99De07dEyCjyfj8KoGpuEOOLAZFiykrwcXcB59NPc4sLTxDgrbSoUMQ2Uj5WIxFVE1wHBEy2szh8BqGSY1RqXnU2yXeFzQiHck0aEhkDiZqv5H6EEjjFPLye15hedHL89g1nvP4OAI7l28KeV7K4sV9CtNbHswHh9ZgXYxgGl9RdnFHwHznOYsaGv2A29xPJ31eGFqzufugkGIBcBynLYXJoJkST3nCDongOkPg9gBtmj5cmR5rpxu2MNL6soZu3UnoMeM19OzDZvV7Z989bpBZQTGL8qZ5Mom2yJUkDi11GS0DKQl2Gq1hAOXdje3qKnypbLVm3SUOo4kJsgnP2DuVhotnxMgOT0N8oayTrgGD0AHZiHfAV85alLTePLVItmtjQQQ5VhWRXbQODRklscZTYeL24t3JogADEN8YxPeFZgDhfnKWPebdL0g9mNpbH3gSyzIGIEV0B03nHZqnaARXWyhLRzeqIAAwpYc70Nd4vwzGSF1xjQNZT5yVp5KpBZPbGGd3xMD7QLLV6B2pDSDXdREFDpzfxjLuCnm45FziDlJtDFIj0y6oMRT0XLjtG25HG4gDqVxCRxIwfGxbCyByVeQBQGvMcbMmxWGXuJolneC8pxjvVvAnXi";
		 * int i; for (i = 0; i < s.length(); i++) { if (s.charAt(i) == 'E' &&
		 * s.charAt(i + 1) == 'E') break; } System.out.println(i);
		 * System.out.println(s.length());
		 */
		System.out.println(new WindowStringOptimized().minWindow("ADOBECODEBANC", "ABC"));
	}

	public void addCharToHashMap(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (hm.containsKey(s.charAt(i))) {
				Counter c = hm.get(s.charAt(i));
				c.count += 1;
			} else {
				Counter c = new Counter();
				c.count = 1;
				hm.put(s.charAt(i), c);
			}
		}
	}

	public String minWindow(String A, String B) {
		if (A == null || B == null || A.length() == 0 || B.length() == 0)
			return "";
		if (B.length() > A.length()) {
			return "";
		}

		addCharToHashMap(B);
		int len = B.length();
		int count = 0;
		int start = 0;
		int end = -1;
		int min = Integer.MAX_VALUE;
		String minStringSoFar = "";
		int minStart = 0;
		int minEnd = -1;
		int nextStart = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		HashMap<Character, Counter> localMap = new HashMap<Character, Counter>();
		for (int i = start; i < A.length(); i++) {
			if (hm.containsKey(A.charAt(i))) {
				queue.add(i);
				if (localMap.containsKey(A.charAt(i))) {
					Counter c = localMap.get(A.charAt(i));
					c.count += 1;
				} else {
					Counter c = new Counter();
					c.count = 1;
					localMap.put(A.charAt(i), c);
				}
				if (localMap.get(A.charAt(i)).count <= hm.get(A.charAt(i)).count) {
					count++;
				}
				if (count == len) {
					end = i;
					do {
						Counter c = localMap.get(A.charAt(queue.peek()));
						c.count -= 1;
						start = queue.poll();
					} while (localMap.get(A.charAt(start)).count >= hm.get(A.charAt(start)).count);
					if (min > (end - start + 1)) {
						min = end - start + 1;
						minStart = start;
						minEnd = end;
					}
					count--;
				}

			}
		}
		
		if (minEnd >= minStart) {
			minStringSoFar = A.substring(minStart, minEnd + 1);
		}
		return minStringSoFar;
	}
}
