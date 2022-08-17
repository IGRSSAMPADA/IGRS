package com.wipro.igrs.propertyvaluation.bd;
/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  PropertyValuationBD.java 
 * Author      :  Madan Mohan 
 * Description :   
*/


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;


/**
 * @author Madan Mohan
 *
 */
public class PropertyValuationBD {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(PropertyValuationBD.class);

	/**
	 * @return ArrayList
	 */
	public ArrayList getPropertyType() {
		if (logger.isInfoEnabled()) {
			logger.info("getPropertyType() - inside getPropertyType");
		}
		ArrayList returnArrayList = new ArrayList();
		try {
			returnArrayList = new IGRSCommon()
				.getPropertyType();
			if (logger.isDebugEnabled()) {
				logger.debug("getPropertyType() - end");
			}
		}catch(Exception x) {
			logger.debug(x);
		}
		return returnArrayList;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getMunicipalBody() {
		if (logger.isDebugEnabled()) {
			logger.debug("getMunicipalBody() - start");
		}
		ArrayList returnArrayList = new ArrayList();
		try {
			returnArrayList = new IGRSCommon()
				.getMunicipalBody();
			if (logger.isDebugEnabled()) {
				logger.debug("getMunicipalBody() - end");
			}
		}catch(Exception x) {
			logger.debug(x);
		}
		return returnArrayList;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getAreaType() {
		if (logger.isDebugEnabled()) {
			logger.debug("getMunicipalBody() - start");
		}
		ArrayList returnArrayList = new ArrayList();
		try {
			returnArrayList = new IGRSCommon()
				.getAreaType();
			if (logger.isDebugEnabled()) {
				logger.debug("getMunicipalBody() - end");
			}
		}catch(Exception x) {
			logger.debug(x);
		}
		return returnArrayList;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getUsedPlot(int propertyId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getMunicipalBody() - start");
		}
		ArrayList returnArrayList = new ArrayList();
		try {
			returnArrayList = new IGRSCommon()
				.getUsedPlot(propertyId);
			if (logger.isDebugEnabled()) {
				logger.debug("getMunicipalBody() - end");
			}
		}catch(Exception  x) {
			logger.debug(x);
		}
		return returnArrayList;
	}

}
