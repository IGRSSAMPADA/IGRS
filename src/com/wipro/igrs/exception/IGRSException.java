/**
 * 
 */
package com.wipro.igrs.exception;

/**
 * @author nihraa
 *
 */
public class IGRSException extends Exception implements java.io.Serializable {

	private static final long serialVersionUID = 4337293720847117777L;
	private String errorCode = null;
	private Object value0;

	/**
	 * Constructor IGRSException
	 */
	public IGRSException() {
	}

	/**
	 * @param str
	 */
	public IGRSException(String str ) {
		super(str);
	}

	/**
	 * @param str
	 * @param code
	 */
	public IGRSException(String str, String code ) {
		super(str);
		errorCode = code;
	}

	/**
	 * @param e
	 */
	public IGRSException(Exception e) {
		super(e);
	}

	
	/**
	 * @param str
	 * @param code
	 * @param value0
	 */
	public IGRSException(String str, String code, Object value0 ) {
		super(str);
		errorCode = code;
		this.value0 = value0;
	}
	/**
	 * @return
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param string
	 */
	public void setErrorCode(String string) {
		errorCode = string;
	}

	/**
	 * @return
	 */
	public Object getValue0() {
		return value0;
	}

	/**
	 * @param object
	 */
	public void setValue0(Object object) {
		value0 = object;
	}

}
