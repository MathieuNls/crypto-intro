package crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Demonstrates how to use hashing in Java
 * @author Math
 *
 */
public class Hash {
	
	public static void main(String[] arg) throws NoSuchAlgorithmException{

		String password = "MyAwesomePassword";
		byte[] bytesOfMessages = password.getBytes();
		
		
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		byte[] theDigest = md.digest(bytesOfMessages);
		
		System.out.println(theDigest);
		
		for (int i = 0; i < theDigest.length; i++) {
			System.out.print(theDigest[i]);
		}
		
		System.out.println();
		
		String hashedPassword = new String(theDigest);
		System.out.println(hashedPassword);
	}

}
