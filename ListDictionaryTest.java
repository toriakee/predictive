package predictive;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author Tori Keegan
 * @version 5th Feb 2018
 */

public class ListDictionaryTest {

	// Testing signatureToWords using two instances of ListDictionary 'dict' and
	// 'usr' - NOTE: pathways to be changed below as needed.

	@Test
	public void test1() {

		// ListDictionary dict = new
		// ListDictionary("/Users/toriakee/eclipse-workspace/S2W3/src/predictive/dict.txt");
		ListDictionary usr = new ListDictionary("/usr/share/dict/words");

		Set<String> s1 = new HashSet<String>();
		s1.add("inne");
		s1.add("hone");
		s1.add("gone");
		s1.add("home");

		Set<String> s = new HashSet<String>();
		s.add("hood");
		s.add("ione");
		s.add("ioof");
		s.add("good");
		s.add("hond");
		s.add("inne");
		s.add("gond");
		s.add("hone");
		s.add("hoof");
		s.add("gone");
		s.add("goof");
		s.add("home");
		s.add("gome");

		// Set<String> expected1 = s1;
		// Set<String> actual1 = dict.signatureToWords("4663");

		Set<String> expected2 = s;
		Set<String> actual2 = usr.signatureToWords("4663");

		Set<String> s2 = new HashSet<String>();

		Set<String> expected3 = s2;
		Set<String> actual3 = usr.signatureToWords("855225669772");

		Set<String> s3 = new HashSet<String>();
		s3.add("january");

		Set<String> expected4 = s3;
		Set<String> actual4 = usr.signatureToWords("5268279");

		// invalid signature
		Set<String> s4 = new HashSet<String>();

		Set<String> expected5 = s4;
		Set<String> actual5 = usr.signatureToWords("1234567891011");

		// assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);

	}

	// Testing wordToSignature method

	@Test
	public void test2() {

		String expected1 = "4663";
		String actual1 = ListDictionary.wordToSignature("home");

		String expected2 = "4663";
		String actual2 = ListDictionary.wordToSignature("home");

		String expected3 = "5282";
		String actual3 = ListDictionary.wordToSignature("java");

		String expected4 = "33278279";
		String actual4 = ListDictionary.wordToSignature("february");

		String expected5 = "22233344";
		String actual5 = ListDictionary.wordToSignature("abcdefgh");

		String expected6 = "5268279";
		String actual6 = ListDictionary.wordToSignature("JANUARY");

		String expected7 = "43   556 ";
		String actual7 = ListDictionary.wordToSignature("he!-!llo!");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertEquals(expected7, actual7);
	}

	// Testing isValidWord() method

	@Test
	public void test3() {

		boolean expected1 = false;
		boolean actual1 = ListDictionary.isValidWord("home!!!");

		boolean expected2 = true;
		boolean actual2 = ListDictionary.isValidWord("home");

		boolean expected3 = false;
		boolean actual3 = ListDictionary.isValidWord("   java");

		boolean expected4 = true;
		boolean actual4 = ListDictionary.isValidWord("abcdefhijklmnop");

		boolean expected5 = true;
		boolean actual5 = ListDictionary.isValidWord("JANUARY");

		boolean expected6 = false;
		boolean actual6 = ListDictionary.isValidWord("/';['");

		boolean expected7 = false;
		boolean actual7 = ListDictionary.isValidWord("]\'jki[]");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertEquals(expected7, actual7);
	}

}
