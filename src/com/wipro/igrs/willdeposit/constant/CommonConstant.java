/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   CommonConstant.java
 * Author      :   Madan Mohan Mohanty
 * Description :   Represents the Constant Class for Will Deposition.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra       28th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */
 
 
package com.wipro.igrs.willdeposit.constant;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;


/**
 * @author NJIHAR M.
 *
 */
public class CommonConstant {

    /**
     * IGRS_AGENT_TYPE
     */
    public static final String IGRS_AGENT_TYPE = "IGRS-AGENT-TYPE";
    
    
    /**
     * WILL_AGENT
     */
    public static final String WILL_AGENT = "Agent";
    
    
    /**
     * WILL_TESTATOR
     */
    public static final String WILL_TESTATOR = "Testator";
    
    
    /**
     * WILL_DEPOSIT_FORM
     */
    public static final String WILL_DEPOSIT_FORM = "willDepositForm";
    
    /**
     * IGRS_ID_TYPE
     */
    public static final String IGRS_ID_TYPE = "IGRS-ID-TYPE";
    
    /**
     * WILL_DEPOSIT_FLAG
     */
    public static final String WILL_DEPOSIT_FLAG = "D";
    
    /**
     * WILL_WITHDRAWN_FLAG
     */
    public static final String WILL_WITHDRAWN_FLAG = "W";
    
    /**
     * WILL_RETRIEVE_FLAG
     */
    public static final String WILL_RETRIEVE_FLAG = "R";
    
    /**
     * WILL_RETRIEVE_REP_FLAG
     */
    public static final String WILL_RETRIEVE_REP_FLAG = "R";
    /**
     * 
     */
    public static final String WILL_RETRIEVE_COURT_FLAG = "C";
    
    /**
     * WILL_RETRIEVE_CITIZEN
     */
    public static final String WILL_RETRIEVE_CITIZEN = "Citizen";
    
    /**
     * WILL_RETRIEVE_COURT
     */
    public static final String WILL_RETRIEVE_COURT = "Court";
    
    /**
     * WILL_DEPOSIT_INSERT_ACTION
     */
    public static final String WILL_DEPOSIT_INSERT_ACTION = "willInsert";
    
    /**
     * WILL_DEPOSIT_ERROR_FLAG
     */
    public static final String WILL_DEPOSIT_ERROR_FLAG = "errorFlag";
    
    /**
     * WILL_DEPOSIT_ERROR_LIST
     */
    public static final String WILL_DEPOSIT_ERROR_LIST = "depositErrorList";

    /**
     * WILL_WITHDRAWAL_FORM
     */
    public static final String WILL_WITHDRAWAL_FORM = "willWithdrawalForm";
    
    /**
     * WILL_DEPOSIT_RETRIEVAL_FORM
     */
    public static final String WILL_DEPOSIT_RETRIEVAL_FORM = "willRetrieve";
    
    /**
     * WIll_WITHDRAWAL_FORM
     */
    public static final String WIll_WITHDRAWAL_FORM = "willWithdrawForm";
    
    
    /**
     * WILL_WITHDRAWAL_SUBMIT_ACTION
     */
    public static final String WILL_WITHDRAWAL_SUBMIT_ACTION =
	     "willWithDrawalSubmit";
    /**
     * 
     */
    public static final String WILL_RETRIEVAL_ID_DETAILS = "willRetrievalID";
    
    /**
     * WILL_RETRIEVAL_ID_DETAILS
     */
    public static final String WILL_RETRIEVAL_DETAILS = "willRetrieveForm";
    
    /**
     * WILL_VIEW_DETAILS
     */
    public static final String WILL_VIEW_DETAILS = "willViewForm";
    
    /**
     * WILL_RETRIEVAL_SUBMIT
     */
    public static final String WILL_RETRIEVAL_SUBMIT = "willRetrievalSubmit";
    
    /**
     * WILL_VIEW_ID_DETAILS
     */
    public static final String WILL_VIEW_ID_DETAILS = "willViewlID";
    
    /**
     * WILL_DEPOSITE_READONLY_FORM
     */
    public static final String WILL_DEPOSITE_READONLY_FORM = "willDepositReadOnlyForm";
    
    /**
     * CHALLAN_PAYMENT
     */
    public static final String CHALLAN_PAYMENT = "Challan Payment";
    
    /**
     * WILL_DEPOSITED
     */
    public static final String WILL_DEPOSITED = "Deposited";
    
    /**
     * WILL_WITHDRAWN
     */
    public static final String WILL_WITHDRAWN = "Withdrawn";
    
    /**
     * WILL_ID_DETAILS_VIEW
     */
    public static final String WILL_ID_DETAILS_VIEW = "viewDetailsIdSubmit";
    
    
    /**
     * WILL_DEPOSITION_FORWARD_PAGE
     */
    public static final String WILL_DEPOSITION_FORWARD_PAGE = "depositeWill";
    
    /**
     * INDIA
     */
    public static final String INDIA = "india";
    
    /**
     * MP
     */
    public static final String MP = "MP";
    
    /**
     * NEXT_READ_ONLY
     */
    public static final String NEXT_READ_ONLY = "nextReadOnlySuccess";
    
    /**
     * PROCEED_PAYMENT_PAGE
     */
    public static final String PROCEED_PAYMENT_PAGE = "proceedPayment";
    
    /**
     * WILL_VIEW
     */
    public static final String WILL_VIEW  = "viewWill";
    
    /**
     * WILL_VIEW_FORM
     */
    public static final String WILL_VIEW_FORM = "willViewForm";
    
    /**
     * VIEW_SUCCESS
     */
    public static final String VIEW_SUCCESS = "viewSuccess";
    
    /**
     * WITHDRAWAL_VIEW_PAGE
     */
    public static final String WITHDRAWAL_VIEW_PAGE = "withDrawalViewPage";
    
    /**
     * WITHDRAWAL_ID_SUCCESS
     */
    public static final String WITHDRAWAL_ID_SUCCESS = "withdrawIdDetailsRetrievalSuccess";

    /**
     * WILL_WITHDRAW_PAGE
     */
    public static final String WILL_WITHDRAW_PAGE = "withdrawWill";
    
    
    /**
     * WITHDRWAL_IDDETAILS_READ_ONLY
     */
    public static final String WITHDRWAL_IDDETAILS_READ_ONLY = "withdrawIdDetailsReadOnly";
    
    /**
     * PROCEED_WITHDRAWAL_PAYMENT_PAGE
     */
    public static final String PROCEED_WITHDRAWAL_PAYMENT_PAGE = "proceedWithdrawalPaymentPage";
    
    
    /**
     * PROCEED_PAYMENT_READONLY
     */
    public static final String PROCEED_PAYMENT_READONLY = "proceedPaymentReadOnly";
    
    
    /**
     * TXN_SUCCESSFUL
     */
    public static final String TXN_SUCCESSFUL = "txnSuccessful";
    public static final String WITHDRAW_UPDATE_SUCCESS = "withdrawUpdateSuccess";
    
    /**
     * WILL_DELIVERY_UPDATE
     */
    public static final String WILL_DELIVERY_UPDATE = "UpdateWillDelivery";
    
    
    /**
     * WILL_UPDATE
     */
    public static final String WILL_UPDATE = "willUpdate";
    
    
    /**
     * WILL_UPDATE_NEXT
     */
    public static final String WILL_UPDATE_NEXT = "viewSuccess";
    
    
    /**
     * VIEW_IND_ID_DETAILS
     */
    public static final String VIEW_IND_ID_DETAILS = "viewIndividualIDDetails";
    
    /**
     * WILL_DELIVERYUPDATE_FLAG
     */
    public static final String WILL_DELIVERYUPDATE_FLAG = "DELV";
    
    /**
     * WILL_DELIVERY_UPDATED
     */
    public static final String WILL_DELIVERY_UPDATED = "deliveryUpdated";
    
    
    
    /**
     * RETRIEVE_WILL
     */
    public static final String RETRIEVE_WILL = "retrieveWill";
    
    
    /**
     * WILL_RETRIEVAL_FORM
     */
    public static final String WILL_RETRIEVAL_FORM = "WillRetrievalForm";
    
    
    /**
     * WILL_RETRIEVER_CONFIRM
     */
    public static final String WILL_RETRIEVER_CONFIRM = "willRetrieverConfirm";
    
    public static final String WILL_WITHDRAW_UPDATE = "updateWithdraw";
    
    public static final String WILL_WITHDRAW_UPDATE_VIEW = "withDrawalupdatePage";
  //Added BY Rupali to remove local disk space---start
	private static Logger logger = Logger.getLogger(CommonConstant.class);
	public static String sharedPath;
	static{
		try {
			sharedPath = PropertiesFileReader.getInstance("resources.igrs").getValue("ddrive.temp.path");
		} catch (Exception e) {
			logger.error("Error :- "+e.getMessage());
			e.printStackTrace();
		}
	}
	//Added BY Rupali to remove local disk space---end
    
    public static final String DEP_PATH_1=sharedPath+"/UPLOAD/14/01/";
    
    
    
}

