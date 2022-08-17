package com.wipro.igrs.UserRegistration.action;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.*; 
import javax.crypto.spec.*; 

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Base64;


/*  T.J.Shanmgam.*/


public class CryptoLibrary 
{ 
		private Cipher encryptCipher; 
		private Cipher decryptCipher; 
		private sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder(); 
		private sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder(); 

		/*public CryptoLibrary(String uniqueKeyConstructor) throws SecurityException 
		{ 
			java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE()); 
			char[] pass = "CHANGE THIS TO A BUNCH OF RANDOM CHARACTERS".toCharArray(); 
			byte[] salt = { 
			(byte) 0xa3, (byte) 0x21, (byte) 0x24, (byte) 0x2c, 
			(byte) 0xf2, (byte) 0xd2, (byte) 0x3e, (byte) 0x19}; 
			int iterations = 3; 
			init(pass, salt, iterations); 
		}*/ 
		//above commented by Roopam
		//following added by roopam.
		public CryptoLibrary(){
			
		}

public void init(char[] pass, byte[] salt, int iterations) throws SecurityException 
{ 
	try 
	{ 
			PBEParameterSpec ps = new javax.crypto.spec.PBEParameterSpec(salt, 20); 
			SecretKeyFactory kf = SecretKeyFactory.getInstance("PBEWithMD5AndDES"); 
			SecretKey k = kf.generateSecret(new javax.crypto.spec.PBEKeySpec(pass)); 
			encryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding"); 
			encryptCipher.init(Cipher.ENCRYPT_MODE, k, ps); 
			decryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding"); 
			decryptCipher.init(Cipher.DECRYPT_MODE, k, ps); 
	} 
	catch (Exception e) 
	
	{ 
			throw new SecurityException("Could not initialize CryptoLibrary: " + e.getMessage()); 
	
	} 
} 


/** 

* convenience method for encrypting a string. 
* @param str Description of the Parameter 
* @return String the encrypted string. 
* @exception SecurityException Description of the Exception 
*/ 

public synchronized String encrypt(String str) throws SecurityException 
{ 
	try 
	{ 
		byte[] utf8 = str.getBytes("UTF8"); 
		byte[] enc = encryptCipher.doFinal(utf8); 
		return encoder.encode(enc); //uncommented by roopam
//		return str;                 //commented by roopam
	} 
	catch (Exception e) 
	{ 
		throw new SecurityException("Could not encrypt: " + e.getMessage()); 
	} 

} 


/** 

* convenience method for encrypting a string. 
** @param str Description of the Parameter 
* @return String the encrypted string. 
* @exception SecurityException Description of the Exception 
*/ 

	public synchronized String decrypt(String str) throws SecurityException 
	{ 
		try 
		{ 
			byte[] dec = decoder.decodeBuffer(str); 
			byte[] utf8 = decryptCipher.doFinal(dec); 
			return new String(utf8, "UTF8"); 
		} 
		catch (Exception e) 
		{ 
			throw new SecurityException("Could not decrypt: " + e.getMessage()); 
		} 
		
	} 
	
	/** 

	* SHA-512 one way encryption algorithm for encrypting a string. 
	** @param str password  
	* @return String encrypted password . 
	* @exception SecurityException Description of the Exception 
	* @author ROOPAM
	*/ 
	
	//Added by Roopam for one way encryption.
	public synchronized String SHAencryption(String password){
		String encryptedPswd="password not encrypted";
		try{
			MessageDigest mesgDigst=MessageDigest.getInstance("SHA-512");
			mesgDigst.update(password.getBytes());
			byte bytePaswrd[]=mesgDigst.digest();
			StringBuffer stringBufr=new StringBuffer();
			for(int i=0;i<bytePaswrd.length;i++){
				stringBufr.append(Integer.toString((bytePaswrd[i] & 0xff) + 0x100, 16).substring(1));
			}
			StringBuffer hexStringBufr=new StringBuffer();
			for(int i=0;i<bytePaswrd.length;i++){
				String hexString=Integer.toHexString(0xff & bytePaswrd[i]);
				if(hexString.length()==1){
					hexStringBufr.append('0');
				}
				hexStringBufr.append(hexString);
			}
			encryptedPswd=hexStringBufr.toString();
					}
		catch(Exception e){
			throw new SecurityException("Could not encrypt: " + e.getMessage());
		}
		return encryptedPswd;
	}
	
	
	
	public synchronized String SHAencryption512CryptoJS(String password)
	{
		
		String generatedPassword = null;
		
		  try {
			  
	            // Create MessageDigest instance for MD5
	            MessageDigest md = MessageDigest.getInstance("SHA-512");
	            //Add password bytes to digest
	            md.update(password.getBytes());
	            //Get the hash's bytes
	            byte[] bytes = md.digest();
	            //This bytes[] has bytes in decimal format;
	            //Convert it to hexadecimal format
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            //Get complete hashed password in hex format
	            generatedPassword = sb.toString();
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
	        System.out.println(generatedPassword);
			return generatedPassword;
	    
		
		
		
	}
    
	   public static String getSalt() throws NoSuchAlgorithmException
	    {
	        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	        byte[] salt = new byte[16];
	        sr.nextBytes(salt);
	        return salt.toString();
	    }
	

	
	
	} 


