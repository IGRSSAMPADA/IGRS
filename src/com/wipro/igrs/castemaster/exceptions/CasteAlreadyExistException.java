/**
 * 
 */
package com.wipro.igrs.castemaster.exceptions;

import java.io.Serializable;

import com.wipro.igrs.exception.IGRSException;

/**
 * @author HMOHAM
 *
 */
public class CasteAlreadyExistException extends IGRSException implements Serializable {

	public CasteAlreadyExistException() {
		// TODO Auto-generated constructor stub
	}
	
	public CasteAlreadyExistException(String message) {
		super(message);
	}
	
	public CasteAlreadyExistException(Exception wrapped) {
		super(wrapped);
	}
	
	public CasteAlreadyExistException(String message, String code) {
		super(message, code);
	}
	
	public CasteAlreadyExistException(String message, String code, Object value) {
		super(message, code, value);
	}
}
