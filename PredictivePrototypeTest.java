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

public class PredictivePrototypeTest {

	// Testing wordToSignature method

	@Test
	public void test1() {

		String expected1 = "4663";
		String actual1 = PredictivePrototype.wordToSignature("home");

		String expected2 = "43   556 ";
		String actual2 = PredictivePrototype.wordToSignature("he!-!llo!");

		String expected3 = "5282";
		String actual3 = PredictivePrototype.wordToSignature("java");

		String expected4 = "33278279";
		String actual4 = PredictivePrototype.wordToSignature("february");

		String expected5 = "22233344";
		String actual5 = PredictivePrototype.wordToSignature("abcdefgh");

		String expected6 = "5268279";
		String actual6 = PredictivePrototype.wordToSignature("JANUARY");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
	}

	// Testing signatureToWords method using the .words file provided

	@Test
	public void test2() {

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

		Set<String> expected1 = s;
		Set<String> actual1 = PredictivePrototype.signatureToWords("4663");

		Set<String> s1 = new HashSet<String>();
		s1.add("im");
		s1.add("hm");
		s1.add("in");
		s1.add("gm");
		s1.add("io");
		s1.add("gn");
		s1.add("ho");
		s1.add("go");

		Set<String> expected2 = s1;
		Set<String> actual2 = PredictivePrototype.signatureToWords("46");

		Set<String> s2 = new HashSet<String>();

		Set<String> expected3 = s2;
		Set<String> actual3 = PredictivePrototype.signatureToWords("43   556 ");

		Set<String> s3 = new HashSet<String>();
		s3.add("january");

		Set<String> expected4 = s3;
		Set<String> actual4 = PredictivePrototype.signatureToWords("5268279");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);

	}

	// Testing isValidWord method

	@Test
	public void test4() {

		boolean expected1 = false;
		boolean actual1 = PredictivePrototype.isValidWord("home!!!");

		boolean expected2 = true;
		boolean actual2 = PredictivePrototype.isValidWord("home");

		boolean expected3 = false;
		boolean actual3 = PredictivePrototype.isValidWord("   java");

		boolean expected4 = true;
		boolean actual4 = PredictivePrototype.isValidWord("abcdefhijklmnop");

		boolean expected5 = true;
		boolean actual5 = PredictivePrototype.isValidWord("JANUARY");

		boolean expected6 = false;
		boolean actual6 = PredictivePrototype.isValidWord("/';['");

		boolean expected7 = false;
		boolean actual7 = PredictivePrototype.isValidWord("]\'jki[]");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertEquals(expected7, actual7);
	}

}
