package com.wipro.igrs.aadhar.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.newreginit.constant.RegInitConstant;

public final class AadharUtil {
	
	private static String AESKey = null;
	
	static{
			try {
					PropertiesFileReader pr  = PropertiesFileReader.getInstance("resources.igrs");
					AESKey = pr.getValue(RegInitConstant.AES_ENCRYPTION_KEY_AADHAR);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private AadharUtil(){
	}
	
	/**
	 * WIPRO is using this method for encryption.
	 * 
	 * @param original message to encrypt
	 * @param AESKey
	 * @return encrypted message.
	 * @throws Exception
	 */
	public static String encryptWithAES256(String messageToEncrypt) throws Exception {
		
		// checking the arguments.
		if(messageToEncrypt == null || messageToEncrypt.isEmpty() || AESKey == null || AESKey.isEmpty()){
			throw new Exception("message or key for encryption should not be null or empty.");
		}
		
		// check key length should be 32.
		if(AESKey.length() != 32){
			throw new Exception("key length should be 32");
		}
		
		// Instantiate the SecretKeySpec.
		SecretKeySpec skeySpec = new SecretKeySpec(AESKey.getBytes("UTF-8"), "AES");
		
		// Instantiate the Cipher.
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(messageToEncrypt.getBytes());
		
		return new String(Hex.encodeHex(encrypted));
	}
	
	/**
	 * WIPRO is expecting VELTI to use this method for decryption.
	 * 
	 * @param encryptedMessage
	 * @param AESKey
	 * @return original message.
	 * @throws Exception
	 */
	public static String decryptWithAES256(String encryptedMessage) throws Exception {
		
		// checking the arguments.
		if(encryptedMessage == null || encryptedMessage.isEmpty() || AESKey == null || AESKey.isEmpty()){
			throw new Exception("message or key for decryption should not be null or empty.");
		}
		
		// check key length should be 32.
		if(AESKey.length() != 32){
			throw new Exception("key length should be 32");
		}
		
		// Instantiate the SecretKeySpec.
		SecretKeySpec skeySpec = new SecretKeySpec(AESKey.getBytes("UTF-8"), "AES");
		
		byte[] encrypted = Hex.decodeHex(encryptedMessage.toCharArray()); 
		
		// Instantiate the Cipher.
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		
		return new String(decrypted);
	}
	

}
