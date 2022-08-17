package com.wipro.igrs.util;

import java.util.UUID;

public class GUIDGenerator {

	public static String getGUID() {
		return UUID.randomUUID().toString();
	}

}
