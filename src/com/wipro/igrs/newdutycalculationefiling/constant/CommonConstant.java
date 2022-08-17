package com.wipro.igrs.newdutycalculationefiling.constant;


/**
 * @since 14 jan 2008
 * File Name: CommonConstant.java
 * @author Madan Mohan
 * @version 1.0
 * Created on 14 jan 2008
 *
 */
public class CommonConstant {
	 
	/**
	 * @author Madan Mohan
	 * 
	 * 
	 */
	public static final int MAX_FILE_SIZE = 500;
	public static final String lbl_reg_init_upload_msg3="File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।";
	public final static String FILE_NAME= "01.";
	public final static String FILE_UPLOAD_PATH = "//IGRS//EFILING//"; //EFILING
	public final static String FILE_UPLOAD_INITATE="/UploadForm.Pdf";
	public final static String FILE_UPLOAD_PAYMENT_PAGE="/AdditionalUpload.Pdf";
	public final static String PDF_LOGO_HEADER="header img.jpg";
	
	public final static String FILE_MERGE_PATH="/EfilingSigned.Pdf";
	public final static String FILE_NAME_EFILE="//Efiling.Pdf";
	public final static String FILE_NAME_BARCODE="/BarCode";
	public static final String MODULE_NAME="Efiling";
	public static final String SIGNED_ESTAMP_NAME="Efiling1_Signed.PDF";
	public static final String DUTY_HOME_PAGE = "dutyHomePage";
	
	public static final String NO_ACTION="NO_ACTION";
	public static final String FORWARD_PHYSICAL_MORE_NEXT="addMorePhyStamp";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_PAGE = "duty";
	/**
	 * @author Madan Mohan
	 */
	public static final String REGISTRATION_DUTY_PAGE = "RegistrationNonProperty";
	public static final String ESTAMP_PAGE = "duty";
	
	public static final String ESTAMP_MODULE = "eStamping";
	public static final String UPLOAD_FILE_ANGLE_1="UPLOAD_FILE_ANGLE_1"; 
	
	public static final String	UPLOAD_FILE_ANGLE_1_DASHBOARD="UPLOAD_FILE_ANGLE_1_DASHBOARD";
	public static final String UPLOAD_FILE_ANGLE_2="UPLOAD_FILE_ANGLE_2"; 
	public static final String UPLOAD_FILE_ANGLE_3="UPLOAD_FILE_ANGLE_3"; 

	/**
	 * @author RISHAB
	 */
	public static final String NON_DUTY_DEED = "D";
	/**
	 * @author RISHAB
	 */
	public static final String NON_DUTY_REGISTRATION_DEED = "NP";
	/**
	 * @author RISHAB
	 */
	
	public static final String DUTY_NEXT_PAGE = "dutyNextPage";
	
	public static final String  DEED_CHANGE_ACTION="dutyChange";
	public static final String  DEED_CATEGORY_CHANGE_ACTION="deedCategoryChange";
	
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_VIEW_PAGE = "dutyViewPage";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_CALCULATE_ACTION = "dutyCalc";
	
	public static final String DUTY_CALCULATE_ACTION_efiling = "dutyCalcefiling";
	public static final String DUTY_CALCULATE_ACTION_estamp = "estamp";
	
	public static final String DUTY_CALCULATE_ACTION_BANK = "bank";
	public static final String DUTY_CALCULATE_ACTION_NONBANK = "nonbank";
	public static final String DUTY_CALCULATE_ACTION_ZERODUTYBANK = "zeroDutyBank";
	
	public static final String DUTY_CALCULATE_ACTION_PHYSICAL_STAMPS ="physicalStamps";
	public static final String DUTY_CALCULATE_ACTION_PHYSICAL_STAMPS_ADD_MORE ="AddMorePhyStampCode";
	public static final String  DUTY_CALCULATE_ACTION_ADD_MORE_BUTTON="AddMorePhysicalStamp";
	
	//added for efiling number 
	public static final String DUTY_CALCULATE_ACTION_efilenumber = "efilenumber";
	public static final String DUTY_CALCULATE_ACTION_SROfficeDisplay =	"SROfficeDisplay";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_NEXT_ACTION = "nextPage";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_PREV_ACTION = "prevPage";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_SESSION = "dutySession";
	/**
	 * @author Madan Mohan
	 */
	public static final String INSTRUMENT_SESSION = "instSession";
	/**
	 * @author Madan Mohan
	 */
	public static final String EXEMPTION_SESSION = "exemSession";
	/**
	 * @author Madan Mohan
	 */
	public static final String INSTRUMENT_LIST_SESSION = "instListSession";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_PERCENTAGE = "%";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_RS = "Rs";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_YES = "Y";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_NO = "N";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_RADIO_ACTION = "radioAction";
	/**
	 * @author Madan Mohan
	 */
	public static final String FORWARD_PAGE_HOME = "dutyHome";
	
	public static final String FORWARD_PAGE_HOME1 ="reginitConfirm1";
	/**
	 * @author Madan Mohan
	 */
	public static final String FORWARD_PAGE_DISPLAY = "dutyDisplay";
	public static final String FORWARD_PAGE_DISPLAY_efiling = "dutyDisplayefiling";
	public static final String FORWARD_PAGE_DISPLAY_efiling_ZERO_PAYMENT = "dutyDisplayefilingPayment";
	public static final String FORWARD_PAGE_DISPLAY_efiling_ZERO_DUTY = "zeroDutyDisplayefilingPayment";
	public static final String FORWARD_PAGE_DISPLAY_efile_both_exemption = "bothexemptionDisplayefilingPayment";
	public static final String FORWARD_PAGE_DISPLAY_BANK="bankdisplay";
	
	public static final String FORWARD_PAGE_DISPLAY_estamp = "estamp";
	/**
	 * @author Madan Mohan
	 */
	public static final String FORWARD_NONREGINIT_DISPLAY = "regnonprop";
	/**
	 * @author Madan Mohan
	 */
	public static final String FORWARD_PAGE_NEXT = "dutyNext";
	/**
	 * @author Madan Mohan
	 */
	public static final String INSTRUMENT_STATUS = "A"; 
	/**
	 * @author Madan Mohan
	 */
	public static final String EXEMPTION_STATUS = "A";
	
	public static final String RESET_PAGE_ACTION = "resetPage";
	
	public static final String CANCEL_ACTION = "CANCEL_ACTION";
	
	public static final String LANGUAGE_ENGLISH = "en";
	public static final String LANGUAGE_HINDI = "hi";
	
	public static final String FORWARD_PROP_VAL = "propVal";
	
	public static final String RETURN_FROM_PROP_VAL = "returnPropVal";
	
	public static final String FORWARD_REGINIT_DISPLAY = "regprop";
	public static final String VALUATION_ID_VALIDATE = "validatePropId";
	public static final String ID_VALIDATE = "IdClickAction";
	public static final String NO_ID_VALIDATE = "NoIdClickAction";
	public static final String ADD_PROPERTY = "addMoreProperty";
	public static final String DELETE_PROPERTY = "deleteProperty";
	public static final String EXCHANGE_ADD_PROPERTY = "addExchangeProperty";
	public static final String EXCHANGE_DELETE_PROPERTY1 = "deletePropertyExchange1";

	public static final String EXCHANGE_DELETE_PROPERTY2 = "deletePropertyExchange2";
	
	public static final String ADD_LAND_REVENUE="addLandRevenue";
	public static final String DELETE_LAND_REVENUE="deleteLandRevenue";
	public static final String DIFF_RIN_PUSTIKA="diffirentPustika";
	public static final String MUNICIPAL_CHECK="municpalAction";
	
	public static final String FORWARD_PREMIUM_PAGE_ACTION="calculatePremimum";
	
	public static final String FORWARD_RENT_PAGE_ACTION="calculateRent";

	public static final String FORWARD_PREMIUM_PAGE="calculatePremimumPage";
	
	public static final String FORWARD_RENT_PAGE="calculateRentPage";

	public static final String CALCULATE_RENT="calculateRentAndClose";
	public static final String CALCULATE_PREMIUM="calculatePremiumAndClose";
	public static final String ADD_SLAB="addSlab";

	public static final String TO_ESTAMP="eStamp"; 

//added by Preeti
	public static final String FAILURE_MSG = "failureMsg";
	public static final String SUCCESS_MSG = "successMsg";
	
	
	
	public static final String SUCCESS_MSG_AMT = "successMsgAmt";
	
	public static final String SUCCESS_MSG_AMT1 = "successMsgAmt";
	public static final String SUCCESS_MSG_AMT4 = "successMsgAmt4";
	
	public static final String	SUCCESS_MSG_DEACT="successMsgDeact";
	
	public static final String	SUCCESS_MSG_TYPE="successMsgType";
	
	public static final String	SUCCESS_MSG_SOURCE_TYPE="successMsgSourceType";
	
	public static final String FAILURE_MSG_PRINT = "failureMsg";
	public static final String SUCCESS_MSG_PRINT = "successMsg";
	
	public static final String FAILURE_MSG_PRINT1 = "failureMsg1";
	public static final String SUCCESS_MSG_PRINT1 = "successMsg1";
	public static final String SUCCESS_MSG_PRINT2 = "successMsg2";
	
	public static final String SUCCESS_MSG_PHYSICAL="successMsg";
	public static final String FAILURE_MSG_PHYSICAL = "failureMsg";
	
	public static final String SUCCESS_MSG_PHYSICAL1="successMsg";
	public static final String FAILURE_MSG_PHYSICAL1 = "failureMsg";


	//added for upload
	public static final String FAILURE_MSG_UPLOAD = "failureMsg";
	public static final String SUCCESS_MSG_UPLOAD = "successMsg";
	
	//added for file type error
	public static final String	ERRORFILETYPE_MSG_UPLOAD="FailureFileType";
	
}
