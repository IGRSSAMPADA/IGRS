/**
 * ===========================================================================
 * File           :   CommonConstant.java
 * Description    :   Represents the consant Class

 * Author         :  
 * Created Date   :   Nov 28, 2007

 * ===========================================================================
 */


package com.wipro.igrs.propertylock.constant;


public class CommonConstant
{
	public static final String PROPLOCKING_TXN_TABLE = "IGRS_PROPERTY_LOCKING_TXN";
	public static final String PROPLOCKING_TXN_ID_COLNAME="PROPERTY_TXN_ID";
	//public static final int MAX_FILE_SIZE = 10485760;
	public static final int   MAX_FILE_SIZE = 1048576;
    public static final String IGRS_AGENT_TYPE = "IGRS-AGENT-TYPE";
    public static final String PROPERTY_LOCK_FORM = "PropertyLockForm";
    public static final String PROPERTY_LOCK_INSERT_ACTION = "lockInsert";   
    public static final String IGRS_ID_TYPE = "IGRS-ID-TYPE";
    public static final String LOCK_TYPE_FLAG = "A";
    public static final String LOCK_PYMNT_SUCC_FLAG = "C";
    public static final String LOCK_REGISTRATION_FLAG = "D";
    public static final String PROPERTY_LOCK_PAYMENT = "lockPayment";
    public static final String PROPERTY_LOCK_ERROR_FLAG = "errorFlag";
    public static final String PROPERTY_LOCK_ERROR_LIST = "lockErrorList";
    public static final String PROPERTY_LOCK_DISPLAY = "propertyLockDisplay";
    public static final String PAYMENT_SUCCESS_ACTION = "paymentSuccess";
    public static final String PAYMENT_DISPLAY = "paymentDisplay";
    public static final String PREVIOUS_LOCK_ACTION = "previousLockAction";
    public static final String PROPERTY_LOCK_MODIFY_CHALLAN = "modifyChallan";
    public static final String PROPERTY_LOCK_SEARCH = "lockSearch";
    public static final String LOCK_REG_SEARCH = "lockRegSearch";

    public static final String PROPERTY_LOCK_SUCCESS_ACTION = "lockSuccess";
    public static final String PROPERTY_RELEASE_SUCCESS_ACTION = "propRelPymntInit";
    

    public static final String PROPERTY_LOCK_MODIFY_FORM = "lockModify";

    public static final String LOCK_VIEW_DETAILS = "lockViewForm";  
    public static final String LOCK_VIEW_ID_DETAILS = "lockViewID";

    public static final String LOCK_REFER_ID_DETAILS = "lockRetrieve";
    public static final String LOCK_RELEASE_VIEW_ACTION = "lockReleaseView";
    public static final String UNLOCK_REASON = "unLockReason";
    public static final String UN_LOCK_VIEW = "unLockView";
    public static final String PROPERTY_UNLOCK_SUCCESS = "unLockSuccess";
    public static final String PREVIOUS_RELEASE_ACTION = "previousReleaseAction";
    public static final String PROPERTY_RELEASE_MODIFY_FORM = "releaseModify";
    public static final String PROPERTY_UNLOCK_POPUP = "unLockPopup";

    //	Forward Mappings for LOCK
    public static final String PROPERTY_LOCKING = "propertylocking";
    public static final String POPUP_SEARCH = "popupSearch";
    public static final String SEARCH_REG_ID = "searchRegId";
    public static final String REGID_DETAILS = "regIdDetails";
    public static final String LOCKDISPLAY_SUCCESS = "lockDisplaySuccess";
    public static final String PROCEED_PAYMENT = "proceedPayment";
    public static final String PREVIOUS_LOCKPAGE = "previousLockPage";
    public static final String LOCKVIEW_DISPLAY = "lockViewDisplay";
    public static final String ERROR_PAYMENT = "error";
    public static final String MODIFY_CHALLAN = "modifyChallanPage";
    public static final String LOCK_SUCCESS = "lockSuccess";
    public static final String LOCK_MODIFY = "lockModify";
    public static final String LOCK_POPUP = "popup";
    public static final String INDIA = "INDIA";
    public static final String MP = "MP";
    
    //	Forward Mappings for VIEW
    public static final String LOCK_VIEW = "lockView";
    public static final String VIEW_IDSUBMIT = "viewDetailsIdSubmit";
    public static final String LOCK_VIEWSUCCESS = "lockViewSuccess";

    //  Forward Mappings for VIEW
    public static final String LOCK_RELEASE = "lockRelease";
    public static final String LOCK_RELEASESUCCESS = "lockReleaseSuccess";
    public static final String PREVIOUS_RELEASEPAGE = "previousReleasePage";
    public static final String RELEASE_IDDETAILS = "releaseDetailsId";
    public static final String UNLOCK_VIEWSUCCESS = "unLockViewSuccess";
    public static final String UNLOCK_SUCCESS = "unLockSuccess";
    public static final String POPUP_UNLOCK = "popupUnlock";
    public static final String RELEASE_MODIFY = "releaseModify";
	public static final String ERROR_PAGE = "errorPage";
	
	public static final String DEACTIVE_FLAG = "D";
	public static final String ACTIVE_FLAG = "A";

}
