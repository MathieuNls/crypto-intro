package crypto;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrate how to encrypt/decrypt using transposition
 * 
 * @author Math
 *
 */
public class Transpo {

	public static void main(String[] args) {

		String clearText = "Je suis Mathieu.";
		int[] key = new int[] { 0, 2, 3, 1 };

		String crypted = cryptTranspo(clearText, key, clearText.length() / key.length);
		String decrypted = decryptTranspo(clearText.length() / key.length, key, crypted);

		if (clearText.compareToIgnoreCase(decrypted) == 0) {
			System.out.println("Yay!");
		}
	}

	/**
	 * Decrypt a string using transposition
	 * 
	 * @param length
	 * @param key
	 * @param text
	 * @return
	 */
	public static String decryptTranspo(int length, int[] key, String text) {

		List<String> cryptedSplit = splitIntoN(length, text);
		char[][] cryptedText = new char[length][key.length];

		// For each chunk of length
		for (int i = 0; i < cryptedSplit.size(); i++) {
			String tmp = cryptedSplit.get(i);

			// Write the chunk column wise using the key indication
			for (int j = 0; j < tmp.length(); j++) {
				cryptedText[j][key[i]] = tmp.charAt(j);
			}
		}

		String uncrypt = "";

		// Read the tab line by line and append to string
		for (int i = 0; i < cryptedText.length; i++) {
			for (int j = 0; j < cryptedText[i].length; j++) {
				uncrypt += cryptedText[i][j];
			}
		}

		return uncrypt;
	}

	/**
	 * Crypt a string using transposition
	 * 
	 * @param text
	 * @param key
	 * @param length
	 * @return
	 */
	public static String cryptTranspo(String text, int[] key, int length) {

		List<String> splittedText = splitIntoN(length, text);
		char[][] tabChar = new char[length][key.length];

		// for each length size chunk of text
		for (int i = 0; i < splittedText.size(); i++) {

			// get the chunk
			String tmp = splittedText.get(i);

			// Place the chunk in the tab
			for (int j = 0; j < tmp.length(); j++) {
				tabChar[i][j] = tmp.charAt(j);
			}
		}

		String crypted = "";

		// Shuffle the tab using the key and append to string
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < tabChar.length; j++) {
				crypted += tabChar[j][key[i]];
			}
		}

		return crypted;
	}

	/**
	 * Split a string into n block of length Incomplete blocks are filled with .
	 * 
	 * ONESTRING with length = 2 return ON-ES-TR-IN-G.
	 * 
	 * @param length
	 * @param text
	 * @return
	 */
	public static List<String> splitIntoN(int length, String text) {

		List<String> groups = new ArrayList<>();

		for (int i = 0; i < text.length(); i = i + length) {

			groups.add(text.substring(i, (i + length > text.length()) ? text.length() - 1 : i + length));
		}

		return groups;
	}

}
