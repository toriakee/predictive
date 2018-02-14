package predictive;

/**
 * Class WordSig pairs words and signatures together. When the dictionary is
 * read new WordSig objects are created.
 * 
 * @author Tori Keegan
 * @version 5th Feb 2018
 *
 */

public class WordSig implements Comparable<WordSig> {
	private String words;
	private String signature;

	/**
	 * Two different constructors are defined depending on the level of
	 * information given when creating new objects. First constructor is based
	 * just on the word being given (no signature).
	 * 
	 * @param words
	 *            of type String is the word to be assigned to the WordSig
	 *            object. The method wordToSignature is then called on the word
	 *            to pair the correct signature up.
	 * 
	 */

	public WordSig(String words) {
		this.words = words;
		this.signature = ListDictionary.wordToSignature(words);
	}

	/**
	 * Second constructor is based on the word being given and a signature.
	 * 
	 * @param words,
	 *            signature of type String are the word and signature to be
	 *            assigned to the WordSig object.
	 */

	public WordSig(String words, String signature) {
		this.words = words;
		this.signature = signature;
	}

	/**
	 * Getter for the words
	 * 
	 * @return words
	 */

	public String getWords() {
		return words;
	}

	/**
	 * Getter for the signature
	 * 
	 * @return signature
	 */

	public String getSignature() {
		return signature;
	}

	/**
	 * Compares this signature with ws object signature and returns either -1,
	 * 0, 1 if the signature is less than, equal to or greater than the other
	 * signature.
	 * 
	 * @param ws
	 *            A WordSig Object
	 * @return -1, 0, or 1 depending on if the WordSig's signature is less than,
	 *         equal to or greater than the other object signature.
	 */

	public int compareTo(WordSig ws) {

		return this.signature.compareTo(ws.getSignature());
	}

	/**
	 * toString method defines the format off the word plus signature to be
	 * returned, in a readable manner.
	 */

	@Override
	public String toString() {
		return "Word = " + words + " Signature = " + signature + "\n";
	}

	public static void main(String[] args) {
	}

}
