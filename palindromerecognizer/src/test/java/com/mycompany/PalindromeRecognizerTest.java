package com.mycompany;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeRecognizerTest {
	
	@Test
	public void testPalindrome() {
		Map<String, Boolean> testSetup = new HashMap<>();
		testSetup.put("ABBA", true);
		testSetup.put("AbBa", true);
		testSetup.put("ABBAF", false);
		testSetup.put("kayak", true);
		testSetup.put("KaYak", true);
		testSetup.put("kalak", true);
		testSetup.put("mamma", false);
		testSetup.put("madam", true);
		
		for(String s: testSetup.keySet()) {
			assertEquals(testSetup.get(s),  PalindromeRecognizer.isPalindromeUsingRecursive(s));
			assertEquals(testSetup.get(s),  PalindromeRecognizer.isPalindromeUsingJdk(s));
		}
	}
}
