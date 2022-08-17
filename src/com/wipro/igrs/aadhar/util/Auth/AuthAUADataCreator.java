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
package com.wipro.igrs.aadhar.util.Auth;

import java.io.StringWriter;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.sun.star.sdbc.DataType;
import com.wipro.igrs.aadhar.domain.authentication.helper.Encrypter;
import com.wipro.igrs.aadhar.domain.authentication.helper.HashGenerator;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Pid;

/**
 * This class provides a method to collate all the data that needs to sent from
 * Auth Client to AUA server.
 * 
 * @author UIDAI
 * 
 */
public class AuthAUADataCreator {

	private static final int AES_256_KEY_SIZE = 32;
	private Encrypter encrypter;
	/**
	 * @return the encrypter
	 */
	public Encrypter getEncrypter() {
		return encrypter;
	}

	/**
	 * @param encrypter the encrypter to set
	 */
	public void setEncrypter(Encrypter encrypter) {
		this.encrypter = encrypter;
	}

	private HashGenerator hashGenerator;

	/**
	 * @return the hashGenerator
	 */
	public HashGenerator getHashGenerator() {
		return hashGenerator;
	}

	/**
	 * @param hashGenerator the hashGenerator to set
	 */
	public void setHashGenerator(HashGenerator hashGenerator) {
		this.hashGenerator = hashGenerator;
	}

	private static final String RANDOM_ALGORITH_NAME = "SHA1PRNG";

	//private Map<DataType, SynchronizedKey> skeyMap = new HashMap<DataType, SynchronizedKey>();

	private long expiryTime = 10 * 60 * 1000; // 10 minutes for testing purpose.

	private SecureRandom secureSeedGenerator;
	private boolean useSSK = false;

	/**
	 * Constructor
	 * 
	 * @param encrypter
	 *            For encryption of Pid
	 * @param useSynchronizedSesionKey
	 *            Flag indicating whether synchronized sesssion key should be
	 *            used.
	 */
	public AuthAUADataCreator(Encrypter encrypter, boolean useSynchronizedSesionKey) {
		this.hashGenerator = new HashGenerator();
		this.encrypter = encrypter;
		this.useSSK = useSynchronizedSesionKey;

		try {
			this.secureSeedGenerator = SecureRandom.getInstance(RANDOM_ALGORITH_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String createPidXML(Pid pid) {
		StringWriter pidXML = new StringWriter();

		try {
			JAXBContext.newInstance(Pid.class).createMarshaller()
					.marshal(pid, pidXML);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		System.out.println(pidXML.toString());

		return pidXML.toString();
	}	
}
