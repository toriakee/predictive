package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * Class PredictivePrototype is a prototype for the predictive text system.
 * Methods are implemented to change words into their respective 'signatures'
 * and vice versa.
 * 
 * @author Tori Keegan
 * @version 5th Feb 2018
 *
 */

public class PredictivePrototype implements Comparator<String> {

	/**
	 * String method 'wordToSignature' takes a word and returns a numeric
	 * signature. Non-alphabetic characters return a space.
	 * 
	 * The StringBuffer class is used rather than String because for simple
	 * concatenation (in this instance appending a String to another String) is
	 * significantly faster than using a String. Strings are read only and
	 * immutable (can't be changed), whereas the StringBuffer class is used to
	 * represent characters that can be modified.
	 * 
	 * // KEY CODE FOR REFERENCE // non-alphabetic = "" // abc = 2 // def = 3 //
	 * ghi = 4 // jkl = 5 // mno = 6 // pqrs = 7 // tuv = 8 // wxyz = 9
	 * 
	 * @param word
	 *            of type String is the word to be converted to numeric
	 *            signature
	 * 
	 * @return the numeric signature as type String
	 */

	public static String wordToSignature(String word) {

		word = word.toLowerCase();

		int characterVal = 0;
		String sig = "";

		StringBuffer signature = new StringBuffer("");

		for (int i = 0; i < word.length(); i++) {
			characterVal = word.charAt(i); // get the integer value of the
											// character
			if ((characterVal >= 97) && (characterVal <= 99))
				sig = "2";
			else if ((characterVal >= 100) && (characterVal <= 102))
				sig = "3";
			else if ((characterVal >= 103) && (characterVal <= 105))
				sig = "4";
			else if ((characterVal >= 106) && (characterVal <= 108))
				sig = "5";
			else if ((characterVal >= 109) && (characterVal <= 111))
				sig = "6";
			else if ((characterVal >= 112) && (characterVal <= 115))
				sig = "7";
			else if ((characterVal >= 116) && (characterVal <= 118))
				sig = "8";
			else if ((characterVal >= 119) && (characterVal <= 122))
				sig = "9";
			else
				sig = " ";

			signature.append(sig);
		}

		return signature.toString();
	}

	/**
	 * Method 'signatureToWords' takes the numeric signature and returns a set
	 * of possible matching words from the dictionary file 'words'. The returned
	 * list does not have duplicates and each word is in lowercase. The method
	 * reads the dictionary file to find words that match the string signature
	 * and returns all the matching words, without storing the dictionary in
	 * memory.
	 * 
	 * The dictionary is not stored in my Java program because this
	 * implementation is inefficient. The Scanner function parses the file line
	 * by line into the program - my if statement then checks if the word is a
	 * valid word, and compares the two signatures. Having these pre-conditions
	 * mean only relevant words are stored in my ArrayList. This means that the
	 * program is much faster as the time complexity is must simpler.
	 * 
	 * @param signature
	 *            of type String, the signature to be matched against words in
	 *            the dictionary
	 * @return a Set of type String containing all matching, valid words from
	 *         the dictionary.
	 */

	public static Set<String> signatureToWords(String signature) {
		Set<String> matching = new HashSet<String>();
		ArrayList<String> dictionary = new ArrayList<>();

		try {
			Scanner scanner = new Scanner(new File("/usr/share/dict/words"));
			while (scanner.hasNext()) {
				String dict = scanner.next();
				if (isValidWord(dict)
						&& ((dict.length() == signature.length()) && (wordToSignature(dict).equals(signature)))) {
					dict = dict.toLowerCase();
					dictionary.add(dict);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (String word : dictionary) {
			matching.add(word.toLowerCase());
		}
		return matching;
	}

	/**
	 * Method 'isValidWord' is a boolean helper method to ignore lines with
	 * non-alphabetic characters. It returns false for any inputs outside of
	 * [a-z].
	 * 
	 * @param word
	 *            of type String which is the word to be checked
	 */

	public static boolean isValidWord(String word) {
		word = word.toLowerCase();
		char characterVal;

		for (int i = 0; i < word.length(); i++) {
			characterVal = word.charAt(i);
			if ((characterVal < 97) || (characterVal > 122)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
	}

	/**
	 * The compare method is overridden from the Comparator interface, comparing
	 * two Strings o1 and o2 against each other. The compareTo method is then
	 * used within the compare method to check for equality. If they are equal a
	 * zero is returned.
	 * 
	 * @param String
	 *            o1 and String o2
	 * @return an int indicating if the two values are equal to each other. If
	 *         they are equal a zero is returned.
	 */

	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}

}
