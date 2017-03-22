package crypto;

/**
 * Demonstrate how to us Symmetrical encoding
 * @author Math
 *
 */
public class Symmetrical {

	static final char[] alphabet = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public static void main(String[] args) {

		String encryptedString = encrypt("CRYPTO", "MATHIEU", alphabet, false);
		System.out.println(encryptedString);
		String decryptedString = encrypt("CRYPTO", encryptedString, alphabet, true);

		if (decryptedString.compareTo("MATHIEU") == 0) {
			System.out.println("yay!");
		} else {
			System.out.println("Fail");
		}
	}

	/**
	 * Encrypt or decrypt a string using a key
	 * @param key
	 * @param text
	 * @param set
	 * @param decrypt
	 * @return
	 */
	public static String encrypt(String key, String text, char[] set, boolean decrypt) {

		String encrypt = "";
		
		//iterate over the text
		for (int i = 0; i < text.length(); i++) {
			
			//Fetch the corresponding key char 
			//and manage overflow
			int keyIndice = i;
			if (i > key.length() - 1) {
				keyIndice = i % key.length();
				System.out.println("Overflow i:" + i + " key:" + keyIndice);
			}
			
			char charKey = key.charAt(keyIndice);
			
			//instantiate two counters to 
			//get the indices of the letters
			int substitutionIndice = -1;
			int initialIndice = -1;
			
			//Explore the alphabet
			for (int j = 0; j < set.length; j++) {

				if (charKey == set[j]) {
					substitutionIndice = j;
				}

				if (text.charAt(i) == set[j]) {
					initialIndice = j;
				}
				
				//Both letter found, get out of there.
				if (substitutionIndice != -1 && initialIndice != -1) {
					break;
				}
			}
			
			//Handle moving indice forward or backward depending 
			//on the mode (decrypt / encrypt)
			int newIndice = -1;
			if (!decrypt) {
				newIndice = initialIndice + substitutionIndice;

				if (newIndice > set.length) {
					newIndice = newIndice % set.length;
					System.out.println("Overflow: " + (initialIndice + substitutionIndice) + " i:" + newIndice);
				}
			} else {
				newIndice = initialIndice - substitutionIndice;

				if (newIndice < 0) {

					newIndice = set.length + (newIndice % set.length);
					System.out.println("Overflow: " + (initialIndice - substitutionIndice) + " i:" + newIndice);
				}
			}

			encrypt += set[newIndice];
		}

		return encrypt;
	}
}
