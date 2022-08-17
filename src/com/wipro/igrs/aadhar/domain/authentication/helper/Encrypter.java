/*******************************************************************************
 * DISCLAIMER: The sample code or utility or tool described herein
 *    is provided on an "as is" basis, without warranty of any kind.
 *    UIDAI does not warrant or guarantee the individual success
 *    developers may have in implementing the sample code on their
 *    environment. 
 *    
 *    UIDAI does not warrant, guarantee or make any representations
 *    of any kind with respect to the sample code and does not make
 *    any representations or warranties regarding the use, results
 *    of use, accuracy, timeliness or completeness of any data or
 *    information relating to the sample code. UIDAI disclaims all
 *    warranties, express or implied, and in particular, disclaims
 *    all warranties of merchantability, fitness for a particular
 *    purpose, and warranties related to the code, or any service
 *    or software related thereto. 
 *    
 *    UIDAI is not responsible for and shall not be liable directly
 *    or indirectly for any direct, indirect damages or costs of any
 *    type arising out of use or any action taken by you or others
 *    related to the sample code.
 *    
 *    THIS IS NOT A SUPPORTED SOFTWARE.
 ******************************************************************************/
package com.wipro.igrs.aadhar.domain.authentication.helper;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;

/**
 * This class provides utility methods that can be used for
 * encryption of various data as per the UIDAI Authentication API.
 * 
 * It uses <a href="http://www.bouncycastle.org/">Bouncy Castle APIs</a>. 
 * 
 * @author UIDAI
 *
 */
public final class Encrypter {
	protected static Logger logger = Logger.getLogger(Encrypter.class);
	private static final String JCE_PROVIDER = "BC";

	private static final String ASYMMETRIC_ALGO = "RSA/ECB/PKCS1Padding";
	private static final int SYMMETRIC_KEY_SIZE = 256;

	private static final String CERTIFICATE_TYPE = "X.509";

	private PublicKey publicKey;
	private Date certExpiryDate;

	public Encrypter(String publicKeyAlias, String hsmPassword) {
		
	}

	/**
	 * Creates a AES key that can be used as session key (skey)
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public byte[] generateSessionKey() throws NoSuchAlgorithmException, NoSuchProviderException {
		KeyGenerator kgen = KeyGenerator.getInstance("AES", JCE_PROVIDER);
		kgen.init(SYMMETRIC_KEY_SIZE);
		SecretKey key = kgen.generateKey();
		byte[] symmKey = key.getEncoded();
		return symmKey;
	}

	/**
	 * Encrypts given data using session key
	 * @param skey Session key
	 * @param data Data to encrypt
	 * @return Encrypted data
	 * @throws InvalidCipherTextException
	 */
	public byte[] encryptUsingSessionKey(byte[] skey, byte[] data) throws InvalidCipherTextException {
		PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new AESEngine(), new PKCS7Padding());

		cipher.init(true, new KeyParameter(skey));

		int outputSize = cipher.getOutputSize(data.length);

		byte[] tempOP = new byte[outputSize];
		int processLen = cipher.processBytes(data, 0, data.length, tempOP, 0);
		int outputLen = cipher.doFinal(tempOP, processLen);

		byte[] result = new byte[processLen + outputLen];
		System.arraycopy(tempOP, 0, result, 0, result.length);
		return result;

	}	
}
