package com.wipro.igrs.regCompChecker.constant;

import java.io.File;

import org.apache.log4j.Logger;

import com.wipro.igrs.RegCompMaker.constants.RegCompConstant;
import com.wipro.igrs.common.PropertiesFileReader;

public class RegCompCheckerConstant {
	
	/*
	 * JSP PAGES FOR REG COMPLETION
	 */

	public static final String APPLICATION_SEARCH = "applicatioSearch";
	public static final String REG_INIT_PROP_DETAILS = "regInitPropDetails";
	public static final String REG_INIT_PROP_DETAILS_READ_ONLY = "regInitPropDetailsReadOnly";
	public static final String PROPERTY_VIEW_DETAILS = "propertyView";
	public static final String PROPERTY_VIEW_DETAILS_CLR = "propertyViewClr";
	public static final String PARTY_VIEW_DETAILS = "partyView";
	public static final String MODIFY_PARTY_DETAILS = "partyModify";
	public static final String CONFIRMATION_SCREEN = "confirmScreen";
	public static final String REMAINING_SEAL = "remainingseal";

	public static final String LINKING_PAYMENT_PAGE = "linkingPayment"; 
	public static final String REDIRCT_TO_MAKER = "redirectToMaker"; 
	public static final String WITNESS_VIEW = "witnessView"; 
	public static final String LINK_HISTORY_VIEW = "linkHistoryView";
	public static final String PRESENT_PARTIES_VIEW = "viewPresentParties";
	public static final String PRESENTATION_ENDORSEMENT = "presentEndorsement";
	public static final String VIEW_COMPLIANCE_LIST = "viewComplianceList";
	public static final String VIEW_CHECKLIST = "viewCheckList";
	public static final String PROCEED_TO_PAYMENT = "proceedToPayment";
	public static final String PROCEED_TO_PAYMENT_REG_FEE = "proceedToPaymentRegFee";
	public static final String REG_COMP_CHECKER_PAYMENT = "regCompCheckerPayment";
	public static final String PARTIES_BIOMETRIC_DETAILS = "partiesDetails";
	public static final String BOOK_TYPE_SELECTION = "bookTypeSelection";
	public static final String EDIT_WITNESS_PAGE = "witnessEdit";
	public static final String VIEW_COMPLIANCE_LIST_NEW = "viewComplainceList";
	public static final String USER_LOGIN_CONFIRMATION = "userLoginConfirmation";
	public static final String DISPLAY_EXTRA_DETLS_JSP = "displayExtraDetls";
	public static final String REG_INIT_PROP_DETLS_NPV = "regInitPropDetlsNPV";
	public static final String REG_INIT_PROP_DETLS_NPV_READ_ONLY = "regInitPropDetlsNPVReadOnly";
	public static final String DISPLAY_PROP_DETLS_NPV = "propertyViewNonPV";
	public static final String EDIT_CONSENTER_PAGE = "consenterEdit";
	public static final String ADD_SEALS_PAGE = "addSealsPage"; 
	public static final String SR_SIGNATURE_PAGE = "srSign";
	
	
	
	/*
	 * FORM NAMES
	 */
	public static final String REG_INIT_SEARCH = "regInitSearch";
	public static final String REG_INIT_PROP_DETAILS_FORM = "regInitPropDetails";
	
	
	/*
	 * Action Names
	 */
	
	public static final String REG_INIT_SEARCH_ACTION = "SEARCH_ACTION";
	public static final String RESET_ACTION = "RESET_ACTION";
	public static final String REG_INIT_PROP_DETAILS_ACTION = "DISPLAY_PROPERTY";
	public static final String REG_INIT_PARTY_DETAILS_ACTION = "DISPLAY_PARTY";
	public static final String REG_INIT_PARTY_MODIFY_ACTION = "MODIFY_PARTY";
	public static final String REG_INIT_PARTY_MODIFY_SAVE_ACTION = "MODIFY_SAVE";
	public static final String REG_INIT_MODIFY_MARKET_VALUE = "MODIFY_MV";
	public static final String NEXT_TO_LINKING_PAGE = "LINKING_PROPERTY";
	public static final String NEXT_TO_LINKING_PROPERTY_NO_MODIFY = "LINKING_PROPERTY_NO_MODIFY";
	public static final String MODIFY_LINKING = "MODIFY_LINKING";
	public static final String VIEW_WITNESS = "VIEW_WITNESS";
	public static final String NEXT_TO_VIEW_WITNESS = "NEXT_TO_VIEW_WITNESS";
	public static final String HOLD_FROM_MAKER = "HOLD_LINKING_MAKER";
	public static final String HOLD_ACTION = "HOLD_ACTION";
	public static final String NEXT_TO_LINK_HISTORY = "NEXT_TO_LINK_HISTORY";
	public static final String MODIFY_LINK_HISTORY = "MODIFY_LINK_HISTORY";
	public static final String VIEW_PRESENT_PARTIES = "VIEW_PRESENT_PARTIES";
	public static final String RESET_EDITABLE_ACTION = "RESET_EDITABLE_ACTION";
	public static final String PROCEED_FOR_PARTIES_DETAILS = "PROCEED_FOR_PARTIES_DETAILS";
	public static final String HOLD_PRESENT_PARTIES = "HOLD_PRESENT_PARTIES";
	public static final String DEED_ENDORSE = "DEED_ENDORSE";
	public static final String NEXT_TO_COMPLIANCE_LIST = "NEXT_TO_COMPLIANCE_LIST";
	public static final String NEXT_TO_CHECK_LIST = "NEXT_TO_CHECK_LIST";
	public static final String NEXT_TO_PAYMENT = "NEXT_TO_PAYMENT";
	public static final String REDIRECT_TO_PAYMENT_ACTION ="REDIRECT_TO_PAYMENT_ACTION";
	public static final String PAY_LATER= "PAY_LATER";
	public static final String SAVE_HOLD_ACTION = "SAVE_HOLD_ACTION";
	public static final String REG_FEE_PAYMENT = "REG_FEE_PAYMENT";
	public static final String REDIRECT_TO_PAYMENT_ACTION_REG_FEE = "REDIRECT_TO_PAYMENT_ACTION_REG_FEE";
	public static final String NEXT_TO_FINAL_CONFIRMATION = "NEXT_TO_FINAL_CONFIRMATION";
	public static final String NEXT_TO_FINAL_CONFIRMATION_SEAL = "NEXT_TO_FINAL_CONFIRMATION_SEAL";
	public static final String NEXT_TO_FINAL_CONFIRMATION_SEAL_COMP = "NEXT_TO_FINAL_CONFIRMATION_SEAL_COMP";

	public static final String DELETE_WITNESS = "DELETE_WITNESS";
	public static final String EDIT_WITNESS = "EDIT_WITNESS";
	public static final String SAVE_WITNESS_DETAILS = "SAVE_WITNESS_DETAILS";
	public static final String ADD_MORE_WITNESS = "ADD_MORE_WITNESS";
	public static final String SAVE_WITNESS_DETAILS_NEW = "SAVE_WITNESS_DETAILS_NEW";
	public static final String IMPOUND_ACTION = "IMPOUND_ACTION";
	public static final String NEXT_TO_BIOMETRIC_DETAILS = "NEXT_TO_BIOMETRIC_DETAILS";
	public static final String PAY_LATER_REG_FEE = "PAY_LATER_REG_FEE";
	public static final String CANCEL_ACTION = "CancelAction";
	public static final String SAVE_LAST_DUE_DATE = "SaveLastDueDate";
	public static final String SEAL_ACTION = "sealAction";
	public static final String DISPLAY_EXTRA = "displayExtra";
	public static final String DISPLAY_PARTY_DETLS_NPV = "displayPropertyNonPV";
	public static final String LINKING_PROPERTY_NPV= "LINKING_PROPERTY_NPV";
	public static final String DISPLAY_PARTICULARS_OF_TRANSACTION = "DISPLAY_PARTICULARS_OF_TRANSACTION";
	public static final String REFUSAL_SEAL = "REFUSAL_SEAL";
	public static final String BIOMETRIC_DETLS = "BIOMETRIC_DETLS";
	public static final String PARTIES_PRESENT = "PARTIES_PRESENT";
	public static final String PARTY_PRESENT_THUMB = "PARTY_PRESENT_THUMB";
	public static final String BIOMETRIC_DETAILS_THUMB = "BIOMETRIC_DETAILS_THUMB";
	public static final String DELETE_CONSENTER = "DELETE_CONSENTER";
	public static final String EDIT_CONSENTER = "EDIT_CONSENTER";
	public static final String SAVE_CONSENTER_DETAILS = "SAVE_CONSENTER_DETAILS";
	public static final String ADD_MORE_CONSENTER = "ADD_MORE_CONSENTER";
	public static final String SAVE_CONSENTER_DETAILS_NEW = "SAVE_CONSENTER_DETAILS_NEW";
	public static final String GENDER_TRNS_ACTION = "GENDER_TRNS_ACTION";
	public static final String GENDER_CONS_ACTION = "GENDER_CONS_ACTION";
	public static final String RESET_ACTION_CONSENTER = "RESET_ACTION_CONSENTER";
	public static final String CANCEL_ACTION_CONSENTER = "CANCEL_ACTION_CONSENTER";
	public static final String RESET_ACTION_WITNESS = "RESET_ACTION_WITNESS";
	public static final String CANCEL_ACTION_WITNESS = "CANCEL_ACTION_WITNESS";
	public static final String GENERATE_PIN = "GENERATE_PIN";
	public static final String PRINT_PIN = "PRINT_PIN";
	public static final String SEAL_CONTENT = "SEAL_CONTENT";
	public static final String PRESENTATION_SEAL_COMPLETE = "PRESENTATION_SEAL_COMPLETE";
	public static final String ADD_SEALS_ACTION = "ADD_SEALS_ACTION";
	public static final String ADMISSION_CONSENTER_THUMB_SEAL_ACTION = "ADMISSION_ACTION";
	public static final String ADMISSION_SEAL_COMPLETE = "ADMISSION_SEAL_COMPLETE";
	public static final String STAMP_SEAL_COMPLETE = "STAMP_SEAL_COMPLETE";
	public static final String PRINT_FINAL_DOC = "PRINT_FINAL_DOC";
	public static final String GENERATE_OTP = "GENERATE_OTP";
	public static final String PRINT_PREVIEW = "PRINT_PREVIEW";

	public static final String VALIDATE_OTP = "VALIDATE_OTP";
	public static final String REFUSAL_SEAL_COMPLETE = "REFUSAL_SEAL_COMPLETE";
	public static final String PRINT_FINAL_DOC_PDF = "PRINT_FINAL_DOC_PDF";
	public static final String DISPLAY_DUTY_BREAKUP = "displayDuties";
	public static final String VALIDATE_DEED_DOC = "validateDeedDoc";
	public static final String CONSUME_DEED_DOC = "consumeDeedDoc";
	public static final String VIEW_DEED_DOC = "viewDeedDoc";
	public static final String CHANGE_DEED_DOC = "changeDeedDoc";
	/*
	 * MESSAGES
	 */
	public static final String SUCCESS_MSG = "successMsg";
	public static final String FAILURE_MSG = "failureMsg";
	public static final String INFO_MSG_HOLD = "infoMsg"; 
	public static final String FINAL_CONF_MSG = "cnfirmationMsg";
	public static final String FINAL_MSG = "finalMsg";
	public static final String FINAL_REF_MSG = "finalRefMsg";
	
	/*
	 * CONSTANTS
	 */
	
	public static final String MODIFY = "modify";
	public static final String ISADJ = "isadj";
	 
	//********UPLOAD CONSTANTS ********************//
	
	//Added BY Rupali to remove local disk space---start
	private static Logger logger = Logger.getLogger(RegCompCheckerConstant.class);
	public static String sharedPath;
	public static String sharedDrive;
	static{
		try {
			sharedPath = PropertiesFileReader.getInstance("resources.igrs").getValue("ddrive.temp.path");
			sharedDrive = PropertiesFileReader.getInstance("resources.igrs").getValue("shared.path");
		} catch (Exception e) {
			logger.error("Error :- "+e.getMessage());
			e.printStackTrace();
		}
	}
	//Added BY Rupali to remove local disk space---end
	public static final String FILE_UPLOAD_PATH = sharedPath+"/Upload/03/";  //03 is for checker
	public static final String UPLOAD_PATH_SUPP_DOC = "/01/";
	public final static String FILE_NAME_SUPP_DOC = "SupportingDoc.jpg";
	public static final String UPLOAD_PATH_PMT_RCPT = "/02/";
	public final static String FILE_NAME_PMT_RCPT = "paymentReceipt.jpg";
	public static final String UPLOAD_PATH_GOVT_LETTER = "/03/";
	public final static String FILE_NAME_GOVT_LETTER = "govtOffLetter.jpg";
	public static final String UPLOAD_PATH_PNALTY_RCPT = "/04/";
	public final static String FILE_NAME_PNALTY_RCPT = "penaltyReceipt.jpg";
	public static final String UPLOAD_PATH_DEATH_CERT = "/05/";
	public final static String FILE_NAME_DEATH_CERT = "DeathCertificate.jpg";
	public static final String UPLOAD_PATH_RELTN_PROOF = "/06/";
	public final static String FILE_NAME_RLTN_PROOF = "RelationProof.jpg";
	public static final String UPLOAD_PATH_POA_DOC = "/07/";
	public final static String FILE_NAME_POA_DOC = "poaDocument.jpg";
	public static final String UPLOAD_PATH_PROP_RELATED_COMPLAINCE = "/08/";
	public final static String FILE_NAME_ANGLE_1 = "01.jpg";
	public final static String FILE_NAME_ANGLE_2 = "02.jpg";
	public final static String FILE_NAME_ANGLE_3 = "03.jpg";
	public final static String FILE_NAME_PROP_MAP ="04.jpg";
	public final static String FILE_NAME_RIN    =  "05.jpg";
	public final static String FILE_NAME_KHASRA  = "06.jpg";
	public static final String UPLOAD_PATH_PARTY_RELATED_COMPLAINCE = "/09/";
	public final static String FILE_NAME_COLLECTOR_CERT = "01.jpg";
	public final static String FILE_NAME_PHOTO_PROOF = "02.jpg";
	public final static String FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT = "03.jpg";
    public final static String FILE_NAME_EXECUTANT_PHOTO = "04.jpg";
    public final static String FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY = "05.jpg";
    public final static String FILE_NAME_ATTORNEY_PHOTO = "06.jpg";
    public final static String FILE_NAME_CLAIMANT_PHOTO = "07.jpg";
    public final static String FILE_NAME_PAN_FORM_60 = "08.jpg";
    public final static String FILE_NAME_WIT_PHOTO = "witnessPhotoGraph.jpg";
    public static final String UPLOAD_PATH_PARTY_SIGNTAURE = "/10/";
    public static final String UPLOAD_PATH_PARTY_THUMB = "/11/";
    public static final String UPLOAD_PATH_PARTY_PHOTO = "/12/";
    public static final String UPLOAD_PATH_SR_SIGN = "/13"+File.separator;
    public static final String UPLOAD_PATH_DEED_DOC = "/14/";
    public final static String FILE_NAME_DEED_DOC = "deedDocument.PDF";
	
	///*******************ComplianceList Constants****************************///
	public static final String ANGLE_1_NAME = "ANGLE 1.JPG";
	public static final String ANGLE_2_NAME  = "ANGLE 2.JPG";
	public static final String ANGLE_3_NAME = "ANGLE 3.JPG";
	public static final String MAP_NAME = "MAP.JPG";
	public static final String KHASRA_NAME = "KHASRA.JPG";
	public static final String RIN_NAME = "RIN.JPG";
	public static final String COLLECTOR_CERT = "Collector's Certificate";
	public static final String NOTARIZED_AFFIDAVIT =  "Notarized Affidavit by the Executant";
	public static final String EXECUTANT_PHOTO ="Executant's Photograph";
	public static final String NOTARIZED_AFFIDAVIT_OF_ATTORNEY = "Notarized Affidavit of Attorney";
	public static final String ATTORNEY_PHOTOGRAPH ="Attorney Photograph";
	public static final String CLAIMANTS_PHOTO = "Claimants's Photograph";
	public static final String PAN_OR_FORM = "PAN or form 60/61";
	public static final String PHOTO_PROOF = "ID Proof(PAN,DL,Aadhar,Voter ID,Passport)";
	
	///*******************END--ComplianceList Constants****************************///
	
	//****************************PARTIAL SAVE*************************************///
	//**********PAGES**********************************//
	public static final String REG_INIT_CONFIRMATION = "regInitConfirmation";
	public static final String REG_INIT_CONFIRMATION_READ_ONLY = "regInitConfirmation";
	public static final String LINKING_PAYMENT = "linkingPayment";
	public static final String WITNESS_DETAILS = "viewWitness";
	public static final String LINKING_HISTORY = "linkHistory";
	public static final String PRESENTATION = "presentation";
	public static final String PRESENTATION_4_MNTH = "presentationFourMnth";
	public static final String PRESENTATION_11_MNTH = "presentationElvnMnth";
	public static final String COMPLIANCE_LIST = "complianceList";
	public static final String CHECKLIST = "checklist";
	public static final String BIOMETRIC_DETAILS = "biometricDetails";
	public static final String BOOK_TYPE = "bookTypeSelection";
	public static final String ADD_SEALS = "addSealsPage";
	
	//*********************STATUS***********************************// all these status are defined in IGRS_REG_STATUS_MASTER as well
	public static final String REG_INIT_CONFIRMATION_STATUS = "31";
	//public static final String REG_INIT_CONFIRMATION_READ_ONLY_STATUS = "";
	public static final String LINKING_PAYMENT_STATUS = "32";
	public static final String REG_FEE_SEAL_STATUS = "47";
	public static final String WITNESS_DETAILS_STATUS = "33";
	public static final String LINKING_HISTORY_STATUS = "34";
	public static final String PRESENTATION_STATUS = "35";
	public static final String PRESENTATION_4_MNTH_STATUS = "40";
	public static final String PRESENTATION_11_MNTH_STATUS = "41";
	public static final String COMPLIANCE_LIST_STATUS = "36";
	public static final String CHECKLIST_STATUS = "37";
	public static final String BIOMETRIC_DETAILS_STATUS = "38";
	public static final String BOOK_TYPE_STATUS = "39";
	public static final String REFUSAL_STATUS = "0";
	public static final String ADD_SEALS_STATUS = "44";
	
	//***********************SEAL CONSTANTS******************************//
	public static final String PRESENTATION_SEAl = "1";
	public static final String STAMPDUTY_SEAl = "2";
	public static final String WITNESS_SEAl = "3";
	public static final String EXECUTION_SEAl = "4";
	public static final String REGISTRATIONFEE_SEAl = "5";
	public static final String REGISTRATION_SEAl = "6";
	
	
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

	
	//****************email alerts**********************//
	
	public static final String PRESENTATION_EMAIL_SUB = "PRESENTATION DUE";
	public static final String REGISTRATION_COMPLETION_SUB = "Registration Completion";
	public static final String APPLICATION_ON_HOLD_SUB = "Application on Hold";
	
	public static final String WRONG_PIN_ENTETRED = "WrongPin";
	public static final String PIN_EXPIRED = "pinExpired";
	
	//**************deed and inst Ids for PIN GENERATION CHK***********************//
	
	public static final String CERTIFICATION_OF_SALE = "1052";
	public static final String CONVEYANCE_DEED = "1053";
	public static final String EXCHANGE_DEED = "1054";
	public static final String GIFT_DEED = "1055";
	public static final String LEASE_DEED = "1092";
	public static final String PARTITION_DEED_PROPERTY = "1058";
	public static final String RELEASE_DEED = "1060";
	
	public static final String SALE_DEED_INST_1 = "2089";
	public static final String CONVEYANCE_DEED_INST_1 = "2090";
	public static final String CONVEYANCE_DEED_INST_2 = "2091";
	public static final String CONVEYANCE_DEED_INST_3 = "2092";
	public static final String CONVEYANCE_DEED_INST_4 = "2093";
	public static final String CONVEYANCE_DEED_INST_5 = "2095";
	public static final String EXCAHNGE_DEED_INST_1 = "2096";
	public static final String GIFT_DEED_INST_1 = "2097";
	public static final String PARTITION_DEED_INST_1 = "2105";
	public static final String PARTITION_DEED_INST_2 = "";
	public static final String PARTITION_DEED_INST_3 = "2108";
	public static final String LEASE_DEED_INST_1 = "2218";
	/*public static final String LEASE_DEED_INST_2 = "2102";
	public static final String LEASE_DEED_INST_3 = "2101";
	public static final String LEASE_DEED_INST_4 = "2099";*/
	public static final String RELAESE_DEED_INST_1 = "2112";
	
	
	public static final String DOWNLOAD_DEED_PATH = sharedPath+File.separator+"upload"+File.separator+"IGRS";
	public static final String DMS_FOLDER = "IGRS";
	public static final String PATH_FOR_HEADER_FILE = sharedPath+"\\images\\header.jpg";
	public static final String NAME_OF_SEALS_DOC = "Seals.PDF";
	public static final String NAME_OF_SEALS_DOC_REG = "SealsReg.PDF";

	public static final String UNIQUE_CONSTANT_FOR_SEALS_DOC = "S";
	public static final String NAME_OF_REG_CERT = "RegCertificate.PDF";
	public static final String NAME_OF_REG_CERT_INITIAL = "RegCertificateInitial.PDF";

	public static final String UNIQUE_CONSTANT_FOR_REG_CERT = "R";
	public static final String UNIQUE_CONSTANT_FOR_REG_CERT_INITIAL = "RI";

	public static final String NAME_OF_SEAL_DEED_DOC = "SealDeed.PDF";
	public static final String NAME_OF_SEAL_DEED_DOC_PROP = "SealDeedProp.PDF";

	public static final String NAME_OF_SEAL_DEED_DOC_INITIAL = "SealDeedInitial.PDF";

	public static final String UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC = "SD";
	public static final String UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC_INITIAL = "SDI";

	public static final String NAME_OF_ESTAMP = "EStamp.PDF";
	public static final String UNIQUE_CONSTANT_FOR_ESTAMP = "E";
	public static final String NAME_OF_REG_ESTAMP = "RegEstamp.PDF";
	public static final String UNIQUE_CONSTANT_FOR_REG_ESTAMP = "RE";
	public static final String NAME_OF_FINAL_DOC = "Registration_Certificate.PDF";
	public static final String NAME_OF_FINAL_DOC_EXETENSION = ".PDF";

	public static final String NAME_OF_FINAL_DOC_INITIAL = "Registration_CertificateInitial.PDF";
	public static final String NAME_OF_FINAL_DOC_INITIAL1 = "Registration_CertificateInitial1.PDF";
	public static final String NAME_OF_FINAL_DOC_INITIAL_REG = "Registration_CertificateInitialREG.PDF";
	public static final String UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL_REG = "FR";
	public static final String UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL = "FI";
	public static final String UNIQUE_CONSTANT_FOR_FINAL_DOC = "F";

	public static final String DOWNLOAD_DEED_PATH_NEW = sharedPath + File.separator+"download"+ File.separator+"IGRS";
	public static final String FILE_PATH_FOR_OWM = File.separator+sharedDrive+File.separator+"Owmpdf"+File.separator;
	public static final String ANGLE_1_COLUMN = "ANGLE_1_PATH";
	public static final String ANGLE_2_COLUMN = "ANGLE_2_PATH";
	public static final String ANGLE_3_COLUMN = "ANGLE_3_PATH";
	public static final String MAP_COLUMN = "MAP_PATH";
	public static final String RIN_COLUMN = "RIN_PATH";
	public static final String KHASRA_COLUMN = "KHASRA_PATH";
	public static final String COLLECTER_CERTIFICATE_COLUMN = "COLLECTOR_CERTIFICATE_PATH";
	public static final String PHOTO_PROOF_COLUMN = "PHOTO_PROOF_PATH";
	public static final String NOT_AFFD_EXEC_COLUMN = "NOT_AFFD_EXEC_PATH";
	public static final String EXEC_PHOTO_COLUMN = "EXEC_PHOTO_PATH";
	public static final String NOT_AFFD_ATTR_PATH = "NOT_AFFD_ATTR_PATH";
	public static final String ATTR_PHOTO_PATH = "ATTR_PHOTO_PATH";
	public static final String CLAIMAINT_PHOTO_COLUMN = "CLAIMNT_PHOTO_PATH";
	public static final String PAN_FORM_COLUMN = "PAN_FORM_60_PATH";
	//akansha
	public static final int MINOR_AGE_LIMIT = 18;
	public static final String AADHAR_PARTY_DETAILS_ACTION = "DISPLAY_AADHAR_PARTY";   //to display aadhar details 
	
	public static final String CASHLESS_PAYMENT = "CASHLESS_PAYMENT";
	
	
}
