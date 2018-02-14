package predictive;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Class ListDictionary is an improved version of 'Predictive Prototype' for the
 * predictive text system. Methods are implemented to change words into their
 * respective 'signatures' and vice versa.
 * 
 * In this class the dictionary is read and stored in an ArrayList of type
 * WordSig called 'dictionary'. Each entry of the ArrayList is a pair (of type
 * WordSig), consisting of the word that has been read in and its signature.
 * (Class 'WordSig' pairs words and signatures). 
 * 
 * @author Tori Keegan
 * @version 5th Feb 2018
 */

public class ListDictionary implements Dictionary {
	private ArrayList<WordSig> dictionary = new ArrayList<>();

	/**
	 * 
	 * List Dictionary constructor takes a String path to the dictionary file as
	 * the file to be read, reads the file via BufferedReader and then sorts the
	 * collection in order of signature.
	 * 
	 * @param path
	 *            is the String path to the dictionary file
	 */

	public ListDictionary(String path) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String word = br.readLine();

			while (word != null) {
				if (isValidWord(word))
					dictionary.add(new WordSig(word.toLowerCase()));
				word = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file to open.");
		} catch (IOException ex) {
			System.out.println("IOException");
		}
		Collections.sort(dictionary);
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
	 * Getter for the dictionary ArrayList
	 * 
	 * @return dictionary ArrayList of type WordSig
	 */

	public ArrayList<WordSig> getDictionary() {
		return dictionary;
	}

	/**
	 * Method 'signatureToWords' overrides that from the Dictionary interface.
	 * The method finds the possible words that could correspond to a given
	 * signature in the dictionary and returns them as a Set of type String.
	 * This is similar to the PredictivePrototype method but re-written as an
	 * instance method to use the stored dictionary.The ArrayList<WordSig> is
	 * stored in sorted order (as we've already sorted the dictionary) and the
	 * method uses binary search to perform the look ups, searching above and
	 * below the found position to find all the matching signatures.
	 * 
	 * @param 'signature'
	 *            is the signature to find the matching words, of type String
	 * @return 'matching' is a Set of type String of all the matching words
	 */

	@Override
	public Set<String> signatureToWords(String signature) {

		Set<String> matching = new HashSet<String>(); // To be returned 
		ArrayList<WordSig> words = new ArrayList<>(); 
		WordSig ws = new WordSig("", signature); // new WordSig object

		int position = Collections.binarySearch(dictionary, ws);

		// if the position is greater than one (ws exists in words), get the
		// word at that position in the dictionary and the signature, add to my
		// 'words' ArrayList.
		if (position >= 0) {
			ws = new WordSig(dictionary.get(position).getWords(), signature);
			words.add(ws);

			// once found check above for matching signatures
			int temp = position;
			while ((dictionary.get(temp).getSignature().equals(signature)) && (temp >= 0)) {
				if (!(words.contains(dictionary.get(temp).getWords()))) {
					words.add(new WordSig(dictionary.get(temp).getWords()));
				}
				temp--;

			}
			// check below
			temp = position;
			while ((dictionary.get(temp).getSignature().equals(signature)) && (temp < dictionary.size())) {
				if (!(words.contains(dictionary.get(temp).getWords()))) {
					words.add(new WordSig(dictionary.get(temp).getWords(), signature));
				}
				temp++;

			}
		}

		String match;
		for (int i = 0; i < words.size(); i++) {
			match = (words.get(i)).getWords();
			matching.add(match);
		}
		return matching;
	}

	/**
	 * Method 'isValidWord' is a helper method to ignore lines with
	 * non-alphabetic characters. It doesn't accept any inputs outside of [a-z]
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
