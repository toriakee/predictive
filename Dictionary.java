package predictive;

import java.util.Set;

/**
 * Interface Dictionary defines the signatureToWords method header, a Set of type String.
 *
 */

public interface Dictionary {
	
	public Set <String> signatureToWords(String signature); 

}
