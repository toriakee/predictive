package predictive;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Class 'MapDictionary' takes a String path to a dictionary file, reads it and
 * stores it in a HashMap. The signature is the 'key', and each pair of words and
 * signatures is stored as a 'value' against each distinct key in a Set of type
 * String. The Dictionary interface is implemented to ensure we have a method
 * signatureToWords that converts the input signature to relevant matching
 * words.
 * 
 * A HashMap is a Map based collection that is used to store pairs of keys & values. 
 * It has been chosen instead of a TreeMap to store the dictionary as the
 * time complexity should be constant O(1) regardless of input size for methods
 * such as .contains() which is used below. This means it will take the same
 * time to run using a small text file or a large dictionary so is more
 * efficient for the program. We also do not need our signatures to be ordered
 * as the .contains() method will efficiently locate if our signature exists in
 * the dictionary.
 * 
 * @author Tori Keegan
 * @version 14th Feb
 *
 */

public class MapDictionary implements Dictionary {
	private HashMap<String, Set<String>> dictionary = new HashMap<>();

	/**
	 * Constructor for MapDictionary takes a path of type String for the
	 * dictionary file to be read. Buffered Reader is used to read the file.
	 * While there are still valid words in the dictionary, the method converts
	 * them into signatures to store these as unique keys. The matchingWords
	 * method is then called to store the relevant matching words in the
	 * 'dictionary'.
	 * 
	 * @param path
	 *            is the String path to the dictionary file
	 */

	public MapDictionary(String path) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String word = br.readLine();

			while (word != null) {

				if (isValidWord(word))

					dictionary.put(wordToSignature(word.toLowerCase()),
							matchingWords(word.toLowerCase(), wordToSignature(word)));

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
	 * String method 'wordToSignature' takes a word and returns a numeric
	 * signature. Non-alphabetic characters return a space. The StringBuffer
	 * class is used rather than String because for simple concatenation (in
	 * this instance appending a String to another String) is significantly
	 * faster than using a String. Strings are read only and immutable (can't be
	 * changed), whereas the StringBuffer class is used to represent characters
	 * that can be modified.
	 * 
	 * @param word
	 *            of type String is the word to be converted to numeric
	 *            signature
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
	 * Method 'matchingWords' is a helper method that adds words to a Set of
	 * type String that will then be stored in the HashMap dictionary. If the
	 * dictionary does not contain the signature already, the word is added to
	 * the 'matching' Set of type String and then placed in the dictionary with
	 * the signature. Otherwise if the signature does exist, the Set retrieves
	 * the matching words and places these in the dictionary. The matching set
	 * is then returned populated with all matching words.
	 *
	 * @param word
	 *            of type String is the word to be added to the HashSet
	 * @param signature
	 *            of type String is the signature to be compared with
	 * @return 'matching' a Set of type String with the matching words
	 */

	public Set<String> matchingWords(String word, String signature) {
		Set<String> matching = new HashSet<>();

		if (!dictionary.containsKey(signature)) {
			matching.add(word);
			dictionary.put(signature, matching);
		} else { // otherwise the key (sig) already exists
			matching = dictionary.get(signature);
			matching.add(word);
			dictionary.put(signature, matching);
		}
		return matching;
	}

	/**
	 * 
	 * Method signatureToWords is implemented from the Dictionary interface. The
	 * method finds the possible words that could correspond to a given
	 * signature in the dictionary and returns them as a Set.
	 * 
	 * If the dictionary contains the signature then the respective words are
	 * returned from the dictionary.
	 * 
	 * @param signature
	 *            of type String is the signature of the word to be searched for
	 * @return the Set of matching words of type String if the signature exists
	 *         in the dictionary, otherwise a new empty HashSet is returned.
	 */

	@Override
	public Set<String> signatureToWords(String signature) {
		if (dictionary.containsKey(signature)) {
			return dictionary.get(signature);
		} else {
			return new HashSet<>();
		}
	}

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
