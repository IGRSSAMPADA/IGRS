package com.wipro.igrs.aadhar.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AuthClientProperties {

	private static final Map<String, String> constant = new HashMap<String, String>();
	public static  String IS_NEW = null;

	static {
		try {
			List<String> bundleNames = new ArrayList<String>();
			bundleNames.add("resources.authclient");

			for (String bundleName : bundleNames) {
				ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
				Enumeration<String> bundleKeys = bundle.getKeys();

				while (bundleKeys.hasMoreElements()) {
					String key = bundleKeys.nextElement();
					String value = bundle.getString(key);
					constant.put(key, value);
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			// logger.error("Exception while reading authClient.properties", ex);
		}
	}
	
	public static String getProp(String key) {
		String value = "";
		if(key != null && key.length() > 0) {
			value = constant.get(key);
		}
		return value;
	}

	public static Map<String, String> getConstant() {
		return constant;
	}

}
