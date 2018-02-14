package predictive;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * 
 * @author Tori Keegan
 * @version 14th Feb 2018
 */

public class TreeDictionaryTest {

	// Testing Sigs2Words method

	@Test
	public void test1() {

		TreeDictionary t = new TreeDictionary("/Users/toriakee/eclipse-workspace/S2W3/src/predictive/words");

		Set<String> s = new TreeSet<String>();
		s.add("good");
		s.add("gnof");
		s.add("imme");
		s.add("inme");
		s.add("inod");
		s.add("inof");
		s.add("inoe");
		s.add("hooe");
		s.add("gooe");
		s.add("gonf");
		s.add("honf");
		s.add("gone");
		s.add("home");
		s.add("hone");
		s.add("hood");
		s.add("hoof");
		s.add("ioof");
		s.add("ione");
		s.add("inne");
		s.add("gome");
		s.add("gond");
		s.add("hond");
		s.add("goof");

		Set<String> expected1 = s;
		Set<String> actual1 = t.signatureToWords("4663");

		assertEquals(expected1, actual1);

	}

	// Testing wordToSignature method

	@Test
	public void test2() {

		String expected1 = "4663";
		String actual1 = TreeDictionary.wordToSignature("home");

		String expected2 = "4663";
		String actual2 = TreeDictionary.wordToSignature("home");

		String expected3 = "5282";
		String actual3 = TreeDictionary.wordToSignature("java");

		String expected4 = "33278279";
		String actual4 = TreeDictionary.wordToSignature("february");

		String expected5 = "22233344";
		String actual5 = TreeDictionary.wordToSignature("abcdefgh");

		String expected6 = "5268279";
		String actual6 = TreeDictionary.wordToSignature("JANUARY");

		String expected7 = "43   556 ";
		String actual7 = TreeDictionary.wordToSignature("he!-!llo!");

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
		boolean actual1 = TreeDictionary.isValidWord("home!!!");

		boolean expected2 = true;
		boolean actual2 = TreeDictionary.isValidWord("home");

		boolean expected3 = false;
		boolean actual3 = TreeDictionary.isValidWord("   java");

		boolean expected4 = true;
		boolean actual4 = TreeDictionary.isValidWord("abcdefhijklmnop");

		boolean expected5 = true;
		boolean actual5 = TreeDictionary.isValidWord("JANUARY");

		boolean expected6 = false;
		boolean actual6 = TreeDictionary.isValidWord("/';['");

		boolean expected7 = false;
		boolean actual7 = TreeDictionary.isValidWord("]\'jki[]");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertEquals(expected7, actual7);
	}

}
