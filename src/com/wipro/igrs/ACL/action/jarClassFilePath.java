package com.wipro.igrs.ACL.action;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.bouncycastle.asn1.pkcs.RSAPublicKey;

public class jarClassFilePath {
	public static void main(String args[]){

		System.out.println("RSA Public Key lib path --> "+RSAPublicKey.class.getProtectionDomain().getCodeSource().getLocation());
		try {
			System.out.println("RSA Public Key lib path -->  "+RSAPublicKey.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error message "+e.getMessage());
		}
		String classpath = System.getProperty("java.class.path");
		   String[] classPathValues = classpath.split(File.pathSeparator);
		   System.out.println(Arrays.toString(classPathValues));
		   System.out.println("Class Paths are --> ");
		   for(int i = 0; i<classPathValues.length;i++){
			   System.out.println(classPathValues[i]);
		   }
	
	}
}
