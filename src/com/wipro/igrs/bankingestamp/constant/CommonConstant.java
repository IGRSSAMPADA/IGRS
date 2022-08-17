package com.wipro.igrs.bankingestamp.constant;

import java.io.File;

import com.wipro.igrs.common.PropertiesFileReader;


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
	 */
	public static final String DUTY_HOME_PAGE = "dutyHomePage";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_PAGE = "duty";
	/**
	 * @author Madan Mohan
	 */
	public static final String REGISTRATION_DUTY_PAGE = "RegistrationNonProperty";
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
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_VIEW_PAGE = "dutyViewPage";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_CALCULATE_ACTION = "dutyCalc";
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
	/**
	 * @author Madan Mohan
	 */
	public static final String FORWARD_PAGE_DISPLAY = "dutyDisplay";
	
	/**
	 * @author Aakriti
	 */
	public static final String FORWARD_PAGE_VIEW = "viewPage";
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
	public static final String STATE_LOAD = "stateload";
	/**
	 * @author Lavi
	 */
	public static final String FORWARD_PAGE_DASHBOARD = "dashboard";
	
	public static final String FORWARD_PAGE_FAILURE = "failure";
	
	public static final String FORWARD_PAGE_SUCCESS = "success";
	
	public static final String FORWARD_PAGE_SEARCH ="search";
	
	public static final String FORWARD_PAGE_ECODEVIEW="ecodeView";
	
	public static final String FORWARD_PAGE_SEARCH_DRS ="searchDRS";
	
	public static final String FORWARD_PAGE_ECODEVIEW_DRS="ecodeViewDRS";
	
	public static final String FORWARD_PAGE_DEACTIVATE_SEARCH ="searchDeactivate";
	
	public static final String FORWARD_PAGE_DEACTIVATE="deactivate";
	
	public static final String FORWARD_PAGE_DEACT_SUCCESS="success";
	
	public static final String FORWARD_PAGE_AFTR_PAY_LATER = "payLater";
	
	public static final String FORWARD_PAGE_ECODE_SUCCESS = "ecodePage";
	
	//added by simran
	//PropertiesFileReader pr1 = PropertiesFileReader.getInstance("resources.igrs");
	
	//String hindiFont=pr1.getValue("dms_hindi_font_path");
	
	public static final String ESTAMP_DOWNLOAD_PATH = "D:"+File.separator+"IGRS"+File.separator+"download"+File.separator;
	
	//public static final String ESTAMP_DOWNLOAD_PATH_LINUX = File.separator+"root"+File.separator+"shared"+File.separator+"Owmpdf"+File.separator;
	public static final String ESTAMP_DOWNLOAD_PATH_LINUX = File.separator+"root"+File.separator+"shared"+File.separator+"Owmpdf"+File.separator;

	public static final String DRIVE_PATH="D:";
	
	public static final String MODULE_NAME="Estamp";
	public static final String SIGNED_ESTAMP_NAME="EStamp1_Signed.PDF";
	public static final String FAILURE_MSG = "failureMsg";
	
	//added by saurav 
	public static final String FORWARD_PAGE_SEARCH_NU ="searchnu";
	public static final String FORWARD_PAGE_ECODEVIEW_NU="ecodeViewNU";
}
