package crypto;

/**
 * Demonstrate how to use substitution encryption
 * @author Math
 *
 */
public class Substitution {

	public static void main(String[] args){
		
		String substitution = "AZERTYUIOPQSDFGHJKLMWXCVBN";
		String ordered = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		System.out.println(
				substitute(
						substitute("MATHIEU", substitution, ordered), 
						ordered, 
						substitution)
		);
		
	}
	
	/**
	 * Perform letter substitution with a key
	 * @param textToEncrypt
	 * @param substitution
	 * @param ordered
	 * @param key
	 * @return
	 */
	public static String substitueWithKey(String textToEncrypt, String substitution, String ordered, int[] key){
		
		String encrypted = "";
		for (int i = 0; i < textToEncrypt.length(); i++) {
			
			int offset = 0;
			
			for (int j = 0; j < key.length; j++) {
				
				for (int k = offset; k < ordered.length(); k++) {
					if(ordered.charAt(k+key[k]) == textToEncrypt.charAt(i)){
						encrypted += substitution.charAt(k);
					}
				}
				
				offset += key.length;
			}

		}
		
		return encrypted;
		
		
	}
	
	/**
	 * PErform letter subsitution
	 * @param textToEncrypt
	 * @param substitution
	 * @param ordered
	 * @return
	 */
	public static String substitute(String textToEncrypt, String substitution, String ordered){
		
		String encrypted = "";
		for (int i = 0; i < textToEncrypt.length(); i++) {
			
			for (int j = 0; j < ordered.length(); j++) {
				if(ordered.charAt(j) == textToEncrypt.charAt(i)){
					encrypted += substitution.charAt(j);
				}
			}
			
		}
		
		return encrypted;
	}
	

}
