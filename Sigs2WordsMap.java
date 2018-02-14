package predictive;

import java.util.Set;

public class Sigs2WordsMap {

	/**
	 * 
	 * Class 'Sigs2WordsMap' is a command line program calling the
	 * signatureToWords method. It accepts a list of Strings from the command
	 * line and calls the signatureToWords method to do the conversion of
	 * signature to words.
	 * 
	 * See 'Timings.html' for more detail on timings of programs.
	 * 
	 * @param args
	 *            which is an array of Strings which is written in the command
	 *            line.
	 *
	 * 
	 * @author Tori Keegan
	 * @version 14th February
	 */

	public static void main(String[] args) {

		MapDictionary m = new MapDictionary("/usr/share/dict/words");

		for (String s : args) {
			System.out.print(s + " : ");

			// System.out.println(arg + " : "
			// + m.signatureToWords(arg).toString().replace(",",
			// "").replace("[", "").replace("]", "").trim());

			Set<String> matching = (Set<String>) m.signatureToWords(s);

			for (String word : matching) {
				System.out.print(word + " ");
			}
			System.out.println();

			// MapDictionary storedDictionary = new
			// MapDictionary("/usr/share/dict/words");
			// for (String arg : args) {
			// System.out.println(arg + " : " +
			// storedDictionary.signatureToWords(arg).toString().replace(",",
			// "").replace("[", "").replace("]", "").trim());
			// }
			// }

		}
	}
}