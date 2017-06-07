package com.mycompany;

public class PalindromeRecognizer {

	public static boolean isPalindromeUsingRecursive(String s) {
		if (s == null || s.length() <= 1) {
			return true;
		}

		String top = s.substring(0, 1);
		String tail = s.substring(s.length() - 1);
		String middle = s.substring(1, s.length() - 1);
		if (!top.equalsIgnoreCase(tail)) {
			return false;
		}

		return isPalindromeUsingRecursive(middle);
	}

	public static boolean isPalindromeUsingJdk(String s) {
		String reverse = new StringBuilder(s).reverse().toString();
		return s.equalsIgnoreCase(reverse);
	}
}
