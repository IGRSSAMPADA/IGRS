package com.wipro.igrs.caveatsMaster.exception;

import com.wipro.igrs.exception.IGRSException;

public class CaveatsException extends IGRSException {

	public CaveatsException() {
		super();
	}

	public CaveatsException(String str) {
		super(str);
	}

	public CaveatsException(String str, String code) {
		super(str, code);
	}

	public CaveatsException(String str, String code, Object value0) {
		super(str, code, value0);
	}

	public CaveatsException(Exception e) {
		super(e);
	}
}
