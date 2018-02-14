package predictive;

/**
 * @author Tori Keegan
 * @version 5th Feb 2018
 */

/**
 * Class 'Words2SigProto' is a command line program calling the wordToSignature
 * method. It accepts a list of Strings from the command line and calls the
 * wordsToSignature method to convert the word to signature.
 * 
 * @param args
 *            which is an array of Strings which is written in the command line.
 */

public class Words2SigProto {

	public static void main(String[] args) {

		for (String s : args) {
			System.out.println(s + " : " + PredictivePrototype.wordToSignature(s));
		}
	}

}
