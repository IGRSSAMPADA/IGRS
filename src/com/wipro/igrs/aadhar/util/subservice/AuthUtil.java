package com.wipro.igrs.aadhar.util.subservice;

import java.security.ProviderException;
import java.security.Security;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.wipro.igrs.aadhar.domain.authentication.helper.Encrypter;
import com.wipro.igrs.aadhar.domain.authentication.helper.PidCreator;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Pid;
import com.wipro.igrs.aadhar.domain.authentication.subservice.EncPidResult;
import com.wipro.igrs.aadhar.util.AuthClientProperties;
import com.wipro.igrs.aadhar.util.Auth.AuthAUADataCreator;

public class AuthUtil {
	static {
	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	
	protected static Logger logger = Logger.getLogger(AuthUtil.class);
	
	public static EncPidResult createEncPid(com.wipro.igrs.aadhar.domain.authentication.subservice.Pid subservicePid, EncPidResult encPidResult) throws Exception  {

		byte[] rawPid = null;
		AuthAUADataCreator auaDataCreator = null;
		try {
			auaDataCreator = new AuthAUADataCreator(
					new Encrypter(AuthClientProperties.getProp("encPublicKeyAlias"),
							AuthClientProperties.getProp("hsmPassword")),
					"YES".equalsIgnoreCase(AuthClientProperties.getProp("useSSK")));
			Pid pid = PidCreator.createXmlPid(subservicePid);
			rawPid = auaDataCreator.createPidXML((Pid) pid).getBytes("UTF-8");	
			byte[] pidXmlBytes = rawPid;
			byte[] sessionKey = null;
			sessionKey = auaDataCreator.getEncrypter().generateSessionKey();
			byte[] encXMLPIDData = auaDataCreator.getEncrypter().encryptUsingSessionKey(sessionKey, pidXmlBytes);
			byte[] hmac = auaDataCreator.getHashGenerator().generateSha256Hash(pidXmlBytes);
			byte[] encryptedHmacBytes = auaDataCreator.getEncrypter().encryptUsingSessionKey(sessionKey, hmac);
			byte[] encTimeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(pid.getTs().toGregorianCalendar().getTime()).getBytes("UTF-8");
			encPidResult.setSkey(Base64.encode(sessionKey));
			encPidResult.setEncPid(Base64.encode(encXMLPIDData));
			encPidResult.setEncHmac(Base64.encode(encryptedHmacBytes));
			encPidResult.setEncTs(Base64.encode(encTimeStamp));
		} catch (ProviderException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();			
		}	
		
		return encPidResult;
	}	
}
