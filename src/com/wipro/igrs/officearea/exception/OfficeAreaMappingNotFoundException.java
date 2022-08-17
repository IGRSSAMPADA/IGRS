/**
 * 
 */
package com.wipro.igrs.officearea.exception;

import com.wipro.igrs.exception.IGRSException;

/**
 * @author HMOHAM
 *
 */
public class OfficeAreaMappingNotFoundException extends IGRSException {

	private static final String DEFAULT_MESSAGE = "attempting to edit an OfficeAreaMapping that does not exist"; 
	
	public OfficeAreaMappingNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
	
	public OfficeAreaMappingNotFoundException(Exception e) {
		super(e);
	}
}
