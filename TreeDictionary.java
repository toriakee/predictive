
package predictive;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Class TreeDictionary is another improved implementation of the Dictionary
 * interface, using a tree structure instead of Map and List structures as we
 * have done previously. This implementation allows words or parts of words that
 * match partial signatures (prefixes), so users will be able to see the parts
 * of the words they are typing as they type. TreeDictionary stores the
 * dictionary as a TreeDictionary implementation of 8 branches (corresponding to
 * numbers/buttons 2-9 on a phone keypad).
 * 
 * At each node of the tree a collection of all words that can match the partial
 * signature are stored. Every word that has a prefix corresponding to the
 * partial signature appears in the collection.
 * 
 * Private variables TreeDictionary 'nodes', Set of type String 'dictionary',
 * and Set of type String 'charMatching' are declared.
 * 
 * @author Tori Keegan
 * @version 14th Feb
 *
 */

public class TreeDictionary implements Dictionary {

	private TreeDictionary[] nodes = new TreeDictionary[8];
	private Set<String> dictionary = new HashSet<>();
	private Set<String> charMatching = new HashSet<>();

	/**
	 * Constructor for TreeDictionary which populates the nodes of the tree and
	 * initializes them to 'null'.
	 */

	public TreeDictionary() {
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = null;
		}
	}

	/**
	 * Constructor for TreeDictionary takes a path of type String for the dictionary
	 * file to be read. Buffered Reader is used to read the file. While there are
	 * still valid words in the dictionary, the method converts them into signatures
	 * and extracts the individual numbers to locate the position in the array (we
	 * use the actual value (which could be between 2-9) minus 2 as the array
	 * indices are (0-7).
	 *
	 * @param path
	 *            of type String which is the path to the dictionary file
	 */
	public TreeDictionary(String path) {

		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new TreeDictionary();
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String word = br.readLine();

			while (word != null) {

				if (isValidWord(word)) {
					String signature = wordToSignature(word);

					// characterVal = converting the first character from the signature to an int
					int characterVal = Integer.parseInt(signature.substring(0, 1));
					// adding the corresponding characterVal to the tree
					nodes[characterVal - 2].addToNode(word.toLowerCase(), 1);

				}
				word = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file to open.");
		} catch (IOException ex) {
			System.out.println("IOException");
		}
	}

	/**
	 * String method 'wordToSignature' takes a word and returns a numeric signature.
	 * Non-alphabetic characters return a space. The StringBuffer class is used
	 * rather than String because for simple concatenation (in this instance
	 * appending a String to another String) is significantly faster than using a
	 * String. Strings are read only and immutable (can't be changed), whereas the
	 * StringBuffer class is used to represent characters that can be modified.
	 * 
	 * @param word
	 *            of type String is the word to be converted to numeric signature
	 * @return numeric signature as a String type
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
	 * Recursive method 'addToNode' extracts each individual signature value/number and
	 * creates respective nodes to hold the prefixes. If the node is empty, a new
	 * node is created. Words are added to the dictionary Set of type String for
	 * matching prefixes to be returned through the signatureToWords method.
	 *
	 * @param word
	 *            of type String is the word to be added to the node
	 * @param val
	 *            the respective signature value to be added to the node
	 */

	public void addToNode(String word, int val) {
		String signature = wordToSignature(word);
		dictionary.add(word); // add word to my dictionary

		// base case - terminate when the value exceeds the length of the word
		if (val < word.length()) {

			int characterVal = Integer.parseInt(signature.substring(val, val + 1)); // retrieve signature value
			val++;

			// if the node is empty populate it with a new TreeDictionary
			if (nodes[characterVal - 2] == null) {
				nodes[characterVal - 2] = new TreeDictionary();
			}

			nodes[characterVal - 2].addToNode(word, val);
		}

	}

	/**
	 * Recursive method signatureToWordsHelper returns a Set of type String
	 * 'charMatching' which contains all of the words with the correct prefixes
	 * (matching characters). The method on its own will return all potential words
	 * that match the prefix (regardless of size).
	 *
	 * @param signature
	 *            of type String which is the signature to find all potential
	 *            matching words
	 * @return returns 'charMatching' a Set of type String which contains all
	 *         potential words.
	 */

	public Set<String> signatureToWordsHelper(String signature) {

		if (signature.isEmpty()) {
			return dictionary;
		} else {
			int characterVal = Integer.parseInt(signature.substring(0, 1));
			charMatching = nodes[characterVal - 2].signatureToWordsHelper(signature.substring(1));
			return charMatching;

		}
	}

	/**
	 * Method signatureToWords is overridden from the Dictionary interface
	 * implementation. It trims all the potential words down to size and returns the
	 * matching prefixes of correct length.
	 *
	 * @param signature
	 *            of type String which is the signature to find all potential
	 *            matching words
	 * @return returns 'trimMatching' a Set of type String which contains all
	 *         matching prefixes.
	 */

	@Override
	public Set<String> signatureToWords(String signature) {
		Set<String> trimMatching = new HashSet<>();
		int max = signature.length();

		signatureToWordsHelper(signature).forEach(el -> trimMatching.add(el.substring(0, max)));
		return trimMatching;

	}

	/**
	 * Method 'isValidWord' is a helper method to ignore lines with non-alphabetic
	 * characters. It doesn't accept any inputs outside of [a-z]
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
}