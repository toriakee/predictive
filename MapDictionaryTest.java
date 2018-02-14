package predictive;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author Tori Keegan
 * @version 14th Feb 2018
 */

public class MapDictionaryTest {

	// Testing signatureToWords using two instances of MapDictionary 'dict' and
	// 'usr' - NOTE: pathways to be changed below as needed.

	@Test
	public void test1() {

//		MapDictionary dict = new
//		MapDictionary("/Users/toriakee/eclipse-workspace/S2W3/src/predictive/dict.txt");
		MapDictionary usr = new MapDictionary("/usr/share/dict/words");

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
		String actual1 = MapDictionary.wordToSignature("home");

		String expected2 = "4663";
		String actual2 = MapDictionary.wordToSignature("home");

		String expected3 = "5282";
		String actual3 = MapDictionary.wordToSignature("java");

		String expected4 = "33278279";
		String actual4 = MapDictionary.wordToSignature("february");

		String expected5 = "22233344";
		String actual5 = MapDictionary.wordToSignature("abcdefgh");

		String expected6 = "5268279";
		String actual6 = MapDictionary.wordToSignature("JANUARY");

		String expected7 = "43   556 ";
		String actual7 = MapDictionary.wordToSignature("he!-!llo!");

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
		boolean actual1 = MapDictionary.isValidWord("home!!!");

		boolean expected2 = true;
		boolean actual2 = MapDictionary.isValidWord("home");

		boolean expected3 = false;
		boolean actual3 = MapDictionary.isValidWord("   java");

		boolean expected4 = true;
		boolean actual4 = MapDictionary.isValidWord("abcdefhijklmnop");

		boolean expected5 = true;
		boolean actual5 = MapDictionary.isValidWord("JANUARY");

		boolean expected6 = false;
		boolean actual6 = MapDictionary.isValidWord("/';['");

		boolean expected7 = false;
		boolean actual7 = MapDictionary.isValidWord("]\'jki[]");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertEquals(expected7, actual7);
	}

}
