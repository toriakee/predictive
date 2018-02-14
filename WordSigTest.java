package predictive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author Tori Keegan
 * @version 5th Feb 2018
 */

public class WordSigTest {
	
	//Testing getWords()
	@Test
	public void test1() {
		
		WordSig ws1 = new WordSig("home", "4663");
		WordSig ws2 = new WordSig("february");
		WordSig ws3 = new WordSig("march", "62724");
		WordSig ws4 = new WordSig("april");

		String expected1 = "home";
		String actual1 = ws1.getWords();

		String expected2 = "february";
		String actual2 = ws2.getWords();
		
		String expected3 = "march";
		String actual3 = ws3.getWords();

		String expected4 = "april";
		String actual4 = ws4.getWords();
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
	}
	
	//Testing getSignature()
	@Test
	public void test2() {
		
		WordSig ws1 = new WordSig("home", "4663");
		WordSig ws2 = new WordSig("february", "33278279");
		WordSig ws3 = new WordSig("march", "62724");
		WordSig ws4 = new WordSig("april", "27745");

		String expected1 = "4663";
		String actual1 = ws1.getSignature();

		String expected2 = "33278279";
		String actual2 = ws2.getSignature();
		
		String expected3 = "62724";
		String actual3 = ws3.getSignature();

		String expected4 = "27745";
		String actual4 = ws4.getSignature();
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
	}

	//Testing compareTo()
	@Test
	public void test3() {
		
		WordSig ws1 = new WordSig("home", "4663");
		WordSig ws2 = new WordSig("february", "33278279");
		WordSig ws3 = new WordSig("march", "62724");
		WordSig ws4 = new WordSig("goof", "4663");
		WordSig ws5 = new WordSig("gone", "4663");

		int expected1 = 1;
		int actual1 = ws1.compareTo(ws2);

		int expected2 = -2;
		int actual2 = ws1.compareTo(ws3);
		
		int expected3 = 0;
		int actual3 = ws1.compareTo(ws4);
		
		int expected4 = 0;
		int actual4 = ws1.compareTo(ws5);
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
	}
	
	//Testing toString()
	@Test
	public void test4() {
		
		WordSig ws1 = new WordSig("home", "4663");
		WordSig ws2 = new WordSig("february", "33278279");
		WordSig ws3 = new WordSig("goof", "4663");
		WordSig ws4 = new WordSig("gone", "4663");

		String expected1 = "Word = home Signature = 4663" + "\n";
		String actual1 = ws1.toString();

		String expected2 = "Word = february Signature = 33278279" + "\n";
		String actual2 = ws2.toString();
		
		String expected3 = "Word = goof Signature = 4663" + "\n";
		String actual3 = ws3.toString();

		String expected4 = "Word = gone Signature = 4663" + "\n";
		String actual4 = ws4.toString();
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
	}
}
