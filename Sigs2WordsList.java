package predictive;

import java.util.Set;

/**
 * @author Tori Keegan
 * @version 5th Feb 2018
 */

/**
 * Class 'Sigs2WordsList' is a command line program calling the signatureToWords
 * method. It accepts a list of Strings from the command line and calls the
 * signatureToWords method to do the conversion of signature to words.
 * 
 * @param args
 *            which is an array of Strings which is written in the command line.
 *
 */

public class Sigs2WordsList {

	public static void main(String[] args) {

		ListDictionary l = new ListDictionary("/usr/share/dict/words");

		for (String s : args) {
			System.out.print(s + " : ");

			Set<String> matching = (Set<String>) l.signatureToWords(s);

			for (String word : matching) {
				System.out.print(word + " ");
			}
			System.out.println();
		}

	}

}
