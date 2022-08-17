package com.wipro.igrs.RegCompMaker.constants;

import java.io.File;

/**

 * ===========================================================================
 * File           :   RegCompConstant.java
 * Description    :   Represents the constant Class
 * Author         :   Piyush J Bokarvadia
 * Created Date   :   May 22, 2008
 * Updated By	  :   Imran Shaik
 * Updated Date	  :	  June 27, 2008 
 * ===========================================================================

 */
public class RegCompConstant {
	public static final String REGCOMP_STATE="State";
    public static final String REGCOMP_DISTRICT="District";
    /**
	 * @author Madan Mohan
	 */

    public static final String REGCOMP_FORM_MODIFY="partyModiForm";
    public static final String REGCOMP_FORM_ADD="partyAddForm";
    public static final String REGCOMP_FORM_LIST="partyListForm";
    
    
    //Start Session Name
    public static final String PROPERTY_DTO_SESSION 
    					= "PROPERTY_DTO_SESSION";
	public static final String PROPERTY_TYPE_SESSION = 
		"PROPERTY_TYPE_SESSION";
	public static final String ERROR_FLAG = "ERROR_FLAG";
	public static final String ERROR_LIST = "ERROR_LIST"; 
	public static final String HASH_SESSION = "HASH_SESSION";
	public static final String HASHMAP_DTO_SESSION = "HASHMAP_DTO_SESSION";

    //End Session Name
    /**
	 * @author Madan Mohan
	 */
	public final static String PLOT_ID = "PLOT_ID";
	/**
	 * @author Madan Mohan
	 */
	public final static String BUILDING_ID = "BUILDING_ID";
	/**
	 * @author Madan Mohan
	 */
	public final static String AGRICULTURELAND_ID = "AGRICULTURELAND_ID";
	 
	/**
	 * @author Madan Mohan
	 */
	
	public static final String DISPLAY_PROPERTY_LIST_PAGE = "valuationList";
	
	/**
	 * @author Madan Mohan
	 * EDITED BY ANKITA
	 * DATE 08-02-2013
	 */
	//Start Form Name
	public final static String VALUATION_PLOT_PAGE = "valuationPlot";
	public static final String DISPLAY_VALUATION_PAGE = "valuationDisplay";
	public static final String DISPLAY_PROPERTY_PAGE = "displayProperty";
	public static final String VALUATION_AGRI_PAGE = "valuationAgri";
	public static final String REGCOMP_SEARCH_FORM = "regInitSearch";
	public static final String DUTY_SEARCH_FORM = "dutySearch";
	public static final String VALUATION_BUILDING_PAGE = 
		"valuationBuilding";
	public static final String VALUATION_VIEW_POPUP_PAGE = 
		"valuationPopup";
	public static final String DEED_LIST_VIEW_PAGE = "deedList";
	public static final String DEED_PROPERTY_MAP_PAGE = "deedPropertyMap";
	public static final String DEED_PARTY_PROPERTY_MAP_PAGE 
		= "partyPropertyMap";
	public static final String PARTY_PROERTY_DISPLAY_PAGE = 
		"partyPropertyDisplay";
	public static final String COMPLIANCE_PAGE = "complianceForm";
	public static final String PIN_VERIFY_POPUP_PAGE = "pinVerifyPopup";
	public static final String STAMP_DUTY_PAGE="stampduty";
	public static final String LINKING_PAYMENT_PAGE="linkingPayment";
	public static final String LINKING_PAYMENT_POPUP_PAGE="linkingPaymentPopup";
	public static final String SAVE_LINKED_AMOUNTS="SAVE_ACTION";
	public static final String HOLD_ACTION ="HOLD_ACTION";
	public static final String SAVE_HOLD_ACTION="SAVE_HOLD_DATA";
	public static final String DELETE_WITNESS_ACTION ="DELETE_ACTION";
	//End Form Name
	
	//Start Action Name
	public static final String COMPLIANCE_SUBMIT = "complianceSubmit"; 
	public static final String NEXT_TO_PAYMENT_ACTION ="NEXT_TO_PAYMENT_ACTION";
	public final static String PLOT_SUBMIT_ACTION = "propertycalc";
	public static final String REGCOMP_ACTION_SAVEPARTY="saveParty";
	public static final String REGCOMP_ACTION_SAVEORGA="saveOrga";
	public static final String REGCOMP_ACTION_VIEWPARTY="viewParty";
	public static final String REGCOMP_ACTION_DELETEPARTY="deleteParty";
	public static final String REGCOMP_ACTION_ADDMORE="addMore";
	public static final String REGCOMP_ACTION_MODIFYPARTY="modifyParty";
	public static final String REGCOMP_ACTION_UPDATEPARTY="updateParty";
	public static final String REGCOMP_ACTION_AUDJ = "validateAudjTxnIdAction";
	public static final String REGCOMP_ACTION_POA = "validatePoaTxnIdAction";
	public static final String AGRI_USAGE_ACTION = "usageAgri";
	public static final String PROPERTY_TYPE_ACTION = "propertyAction";
	public static final String PLOT_USAGE_ACTION = "usagePlotAction";
	public static final String RESET_ACTION = "RESET_ACTION" ;
	public static final String BACK_ACTION ="BACK_ACTION";
	
	public static final String USAGE_BUILDING_ACTION = "usageBuildingAction";
	public static final String BUILDING_SUBMIT_ACTION = "buildingSubmitAction";
	public static final String FLOOR_ACTION = "floorAction";
	public static final String ADD_MORE_ACTION = "addMoreAction";
	public static final String DELETE_MORE_ACTION = "deleteMoreAction";
	public static final String STORE_ACTION = "STORE_ACTION";
	
	public static final String ADD_ACTION = "ADD_ACTION";
	
	public static final String VIEW_ACTION = "VIEW_ACTION";
	
	public static final String MODIFY_ACTION = "MODIFY_ACTION";
	
	public static final String DELETE_ACTION = "DELETE_ACTION";
	public static final String SEARCH_ACTION = "SEARCH_ACTION";
	public static final String DISPLAY_ACTION = "displayAction";
	public static final String NEXT_ACTION = "NEXT_ACTION";
	public static final String PAYMENT_NEXT_ACTION = "PAYMENT_NEXT_ACTION";
	public static final String SPOT_NEXT_ACTION = "SPOT_NEXT_ACTION";
	public static final String PREVIOUS_ACTION = "PREVIOUS_ACTION";
    public static final String CHANGE_ACTION = "CHANGE_ACTION";
    public static final String REGCOMP_ACTION_PROPERTY_SEARCH 
    					= "propertySearchAction";
    public static final String INSTRUMENT_ACTION = "INSTRUMENT_ACTION";
    
    public static final String REGCOMP_DISTRICT_ACTION 
    					= "REGCOMP_DISTRICT_ACTION";
    public static final String REGCOMP_WARDPATWARI_ACTION  
    					= "REGCOMP_WARDPATWARI_ACTION";
    public static final String REGCOMP_MOHALLA_ACTION 
    					= "REGCOMP_MOHALLA_ACTION";
    public static final String CANCEL_ACTION = "CANCEL_ACTION";
    public static final String DEED_CHANGE_ACTION = "DEED_CHANGE_ACTION";
    public static final String PARTY_CHANGE_ACTION = "PARTY_CHANGE_ACTION";
    public static final String VERIFY_ACTION = "VERIFY_ACTION";
    public static final String SEARCH_BY_REGNUM="SEARCH_ACTION_REG";
    public static final String SEARCH_BY_ESTAMP="SEARCH_ACTION_ESTAMP";
    public static final String SEARCH_BY_PHYSTAMP="SEARCH_ACTION_PHYSTAMP";
    public static final String SEARCH_BY_OLD_REG="SEARCH_ACTION_OLD_NUMBER";

    
    //added by simran
 
    public static final String BACK_TO_CHECKER = "CHECKER_ACTION";
    public static final String BACK_TO_CHECKER_WITHOUT_CHANGE = "CHECKER_ACTION_NO_CHANGE";
    public static final String HOLD_CHECKER = "HOLD_CHECKER_ACTION";
    public static final String PREVIOUS_CHECKER = "PREVIOUS_CHECKER_ACTION";
    public static final String MODIFY_LINK_HISTORY = "MODIFY_LINK_HISTORY";
    public static final String LINK_HISTORY_CHECKER = "LINK_HISTORY_CHECKER";
    public static final String NEXT_TO_CHECKER = "NEXT_TO_CHECKER";
    public static final String DELETE_CONSENTER_ACTION = "DELETE_CONSENTER_ACTION";
    public static final String ADD_CONSENTER_ACTION = "ADD_CONSENTER_ACTION";
    public static final String NEXT_TO_OTHER_DEED_DETAILS = "NEXT_TO_OTHER_DEED_DETAILS";
    
    
    public static final String CAVEAT_VIEW = "caveatView";
    
	//End Action Name
	
    
	
	/**
	 * @author Madan Mohan
	 */
	public static final String PROPERTY_TYPE_BUILDING =
		"PROPERTY_TYPE_BUILDING";
	/**
	 * @author Madan Mohan
	 */
	public static final String PROPERTY_TYPE_PLOT = 
		"PROPERTY_TYPE_PLOT";
	/**
	 * @author Madan Mohan
	 */
	public static final String PROPERTY_TYPE_AGRI_LAND = 
		"PROPERTY_TYPE_AGRI_LAND";
	 
	
	/**
	 * @author Madan Mohan
	 */
	public static final String THIRD_FLOOR = "TFA";
	/**
	 * @author Madan Mohan
	 */
	public static final String FOURTH_FLOOR = "FFF";
	/**
	 * @author Madan Mohan
	 */
	/**
	 * @author Madan Mohan
	 */
	
	/**
	 * @author Madan Mohan
	 */
	
    public static final String REGCOMP_FWDSUCCESS_MODIFYPARTY="modifyParty";
    public static final String REGCOMP_FWDSUCCESS_ADDPARTY="addParty";
    public static final String REGCOMP_FWDSUCCESS_FAILURE="failure";
    public static final String REGCOMP_FWDSUCCESS_VIEWPARTY="viewParty";
    public static final String REGCOMP_FWDSUCCESS_VIEWPARTY1="viewParty1";
    public static final String REGCOMP_FWDSUCCESS_VIEWORGA="viewOrga";
    public static final String REGCOM_SEARCH_FORM = "regCompletionSearch";
    public static final String REGCOM_PROPERTY_SEARCH  
    							= "regCompletionPropertySearch";
   
    
    
   //hari files 
	    public static final String SPLITPART_0 = "NORTH";
		public static final String SPLITPART_1 = "SOUTH";
		public static final String SPLITPART_2 = "EAST";
		public static final String SPLITPART_3 = "WEST";
		public static final String SPLITPART_4 = "NORTH-EAST";
		public static final String SPLITPART_5 = "NORTH-WEST";
		public static final String SPLITPART_6 = "SOUTH-EAST";
		public static final String SPLITPART_7 = "SOUTH-WEST";
		
		// Added By Aruna
		public static final String VIEW_PARTY_LIST="VIEW_PARTY_LIST";
		
		// JSPS added by SIMRAN
		
		public static final String CAVEAT_DISPLAY = "caveatDisplay";
		
		// ADDED BY SIMRAN --- MAKER
		
		public static final String FILE_UPLOAD_PATH = "D:/Upload/02/";  ///02 is for MAKER
	//	public static final String FILE_UPLOAD_PATH = "upload.location"+"02"+File.separator;
		public static final String UPLOAD_PATH_DEATH_CERT = "/01/";
		public static final String UPLOAD_PATH_RELN_PROOF = "/02/";
		public static final String UPLOAD_PATH_POA_DOC = "/03/";
		public final static String FILE_NAME_DTH_CERTIFICATE = "DeathCertificate.jpg";
		public final static String TIME_BARRED_DOCUMENT = "timeBarred.jpg";
		public final static String TIME_BARRED_FOLDER = "PENALITY";


		public final static String FILE_NAME_RELTN_PROOF = "RelationProof.jpg";
		public final static String FILE_NAME_POA_DOC = "poaDocument.jpg";
		public final static String UPLOAD_PATH_DEED_DOC = "/04/";
		public final static String FILE_NAME_DEED_DOC = "deedDocument.jpg";
		public static final String UPLOAD_PATH_SUPP_DOC = "/05/";
		
		public static final String POA_OUTSIDE_MP = "POA Outside MP";
		public static final String POA_FROM_MP = "POA From MP";
		
		//****************************PARTIAL SAVE*************************************///
		//**********PAGES**********************************//
		public static final String REG_INIT_CONFIRMATION = "regInitConfirmation";
		public static final String LINKING_PAYMENT = "linkingPayment";
		public static final String WITNESS_DETAILS = "viewWitness";
		public static final String LINKING_HISTORY = "linkHistory";
		public static final String PRESENTATION = "presentation";
		public static final String COMPLIANCE_LIST = "complianceList";
		public static final String CHECKLIST = "checklist";
		public static final String OTHER_DEED_DETLS = "otherDeedDetls";
		public static final String LINK_HISTORY_CHK = "linkHistoryChk";
		
	
		
		//*********************STATUS***********************************// all these status are defined in IGRS_REG_STATUS_MASTER as well
		public static final String REG_INIT_CONFIRMATION_STATUS = "21";
		public static final String LINKING_PAYMENT_STATUS = "22";
		public static final String WITNESS_DETAILS_STATUS = "23";
		public static final String TIME_BARRED_STATUS = "48";

		public static final String OTHER_DEED_DETLS_STATUS = "24";
		public static final String LINKING_HISTORY_STATUS = "25";
		public static final String PRESENTATION_STATUS = "26";
		public static final String COMPLIANCE_LIST_STATUS = "27";
		public static final String CHECKLIST_STATUS = "28";
		public static final String LINK_HISTORY_CHK_STATUS = "34";
		public static final String PHOTOGRAPH_STATUS = "42";
		public static final String CONSENTER_DETAILS_STATUS = "43";
		public static final String VERIFICATION_STATUS = "46";
		
		
		
		//************************CONSTANTS FOR DEEDS************************//
		//******************** 1. NO PV NO PROPERTY DETLS*****************// 
		public static final String ADOPTION_DEED = "1003";
		public static final String CANCELLATION_OF_WILL_POA = "1014";
		//public static final String TRUST_DEED_NO_P = "1048";
		public static final String AGREEMENT_OF_MEMORANDUM = "1005";
		public static final String AGREEMENT_OF_MEMORANDUM_LOAN = "1097";
		//****************2. PROP DETLS BT NO PV**************************//
		public static final String TITLE_DEED = "";
		public static final String SURRENDER_OF_LEASE = "1046";
		public static final String RECONVEYANCE_OF_MORTGAGE = "1041";
		public static final String MORGAGE_DEED = "1032";
		public static final String POWER_OF_ATTORNEY = "1038";
		public static final String TRANSFER_OF_LEASE = "1072";
		public static final String PARTITION_DEED = "1065";
		public static final String PARTNERSHIP_DEED = "1037";
		public static final String DISSOLUTION_OF_PARTNERSHIP = "";
		public static final String LEASE = "1029";
		public static final String WILL_DEED = "1084";
		public static final String TRUST_DEED = "1048";
		public static final String SETTLEMENT_DEED = "1071";
		
		
		//*********Instrument for TRUST*****************
		public static final String INSTRUMENT_TRUST_NO_P = "2083";
		public static final String INSTRUMENT_TRUST = "2082";
		
		
		
		
		
		
		
}