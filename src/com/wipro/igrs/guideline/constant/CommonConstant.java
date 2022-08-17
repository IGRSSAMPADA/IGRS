/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   CommonConstant.java
 * Author      :   Nihar Ranjan Mishra 
 * Description :   Represents the Common Constants.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  28th February, 2008	 Initial Code 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.guideline.constant;

public class CommonConstant {   
   
	//Create GuideLine
	public static final String GUIDELINE_PREPARATION 				= "guidelinePrepForm";
	public static final String GUIDELINE_CREATION				    = "guidelineCreateForm";
	public static final String GUIDELINE_DERIVE                     =  "guidelineDeriveForm";
	public static final String RESET_PAGE 							= "mohallaDetailsDisplay";
	public static final String NEXT_PAGE_ACTION 					= "nextPageAction";
	public static final String MOHALLA_DETAILS_READABLE 			= "mohallaDetailsReadOnly";
	
	public static final String CREATE_GUIDELINE 					= "createGuideline";
	public static final String MOHALLA_DETAILS_SUCCESS 				= "mohallaDetailsSuccess";
	public static final String MOHALLA_DETAILS_INSERTION_SUCCESS 	= "mohallaDetailsInsertionSuccess";
	public static final String NEXT_PAGE 							= "nextPage";
	
	//View GuideLine
	public static final String GUIDELINE_VIEW_FORM 					= "viewGuideLineForm";
	public static final String VIEW_GUIDELINE 						= "viewGuideLine";
	public static final String READ_GUIDELINE 						= "guidelineReadOnly";
	public static final String MOHALLA_DETAILS_VIEW_SUCCESS 		= "mohallaViewSuccess";
	
	//View GUIDELINE For MAKER
	public static final String GUIDELINE_VIEW_FORM_MAKER					= "viewGuideLineFormMaker";
	public static final String VIEW_GUIDELINE_MAKER 						= "viewGuideLineMaker";
	public static final String MOHALLA_DETAILS_VIEW_MAKER_SUCCESS 		= "mohallaViewMakerSuccess";
	
	
	public static final String VIEW1                                       = "view1";
	public static final String VIEW2                                       = "view2";
	public static final String VIEW_DRAFT                                  = "viewDraft";
	public static final String VIEW_FINAL                                  = "viewFinal";
	public static final String VIEW_GUIDELINE_CITIZEN                        ="viewGuideLineCitizen";
	public static final String GUIDELINE_VIEW_FORM_CITIZEN					= "viewGuideLineFormCitizen";
	public static final String TOP_FINAL_VIEW                               = "topfinalview";
	public static final String TOP_DRAFT_VIEW                               = "topdraftview";
	//View Guideline - Final View
	public static final String VIEW_FINAL_GUIDELINE 				= "viewFinalGuideLine";
	public static final String FINAL_VIEW_SUCCESS	 				= "finalViewSuccess";
	
	
	
	//Approve GuideLine
	public static final String APPROVE_GUIDELINE 					= "approveGuideLine";
	public static final String GUIDELINE_APPROVAL_FORM 				= "guidelineAppForm";
	public static final String GUIDELINE_APP_MODIFY 				= "modifyApprove";
	public static final String MOHALLA_MODIFY_APPROVE 				= "mohallaDetailsModifyApprove";
	public static final String READ_ONLY_APPROVAl 					= "readOnlyApproval";
	public static final String MODIFY_APPROVAl 						= "modifyApproval";
	public static final String MOHALLA_MASTER_SAVE_ACTION 			= "mohallaMasterSaveAction";
	public static final String MODIFY_MOHALLA_DETAILS 				= "modifyMohallaDetails";
	public static final String SAVE_READONLY_PAGE 					= "saveReadOnlyPage";
	public static final String SUB_CLAUSE							= "subclause";
	public static final String SUB_CLAUSE_DERIVE					= "subclauseDerive";
	public static final String SUB_CLAUSE_READ_ONLY					= "subclausereadonly";
	
	public static final String SAVE_APPROVAL 						= "saveApproval";
	public static final String DISPLAY_APPROVAL 					= "displayApproval";
	
	
	//Attribute Constants
	public static final String DTO 									= "dto";
	public static final String MOHALLA_LIST 						= "mohallalist";
	public static final String PATWARI_LIST 						= "patwarilist";
	public static final String DATE_FORMAT_dd_mm_yy					= "dd/mm/yy";
	public static final String DURATION_DATE_INVALID				= "invalidDurationDate";
	public static final String DURATION_DATES_ERRORS				= "durationDatesError";
	public static final String BASE_PERIODS_NOTAVAILABLE			="noBasePeriods";
	public static final String BASE_PERIODS_ERROR					="baseperiodError";
	public static final String INDIVIDUAL_MOHALLA_ATTRIBUTE			="individualMohallaAttributes";
	public static final String APPROVED_ERROR   					="APPROVED_ERROR";
	public static final String APPROVED_ERROR_FLAG                  ="APPROVED_ERROR_FLAG";
	public static final String SUCCESS_MSG                          ="SUCCESS_MSG";
	public static final String FAILURE_MSG = "FAILURE_MSG";
	public static final String SUCCESS_MSG_1 = "SUCCESS_MSG_1";
	public static final String SUCCESS_MSG_2                          ="SUCCESS_MSG_2";
	
	
	//Action Constants
	public static final String TEHSIL_ACTION = "tehsilAction";
	
	
	//DATE FORMAT
	public static final String DATE_FORMAT2 = "mm/dd/yy";
	
	//Changed the date format from mm/dd/yyyy to dd/mm/yyyy according to Indian date format.
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	
	//Changed date format used in guideline to convert date fetched from database   
	public static final String DATE_FORMAT3 = "yyyy-MM-dd";
	
	
	public static final int GUIDELINESTATUS_PENDING = 1;
	public static final int GUIDELINESTATUS_DRAFT = 2;
	public static final int GUIDELINESTATUS_FINAL = 3;
	
	public static final String DSP_GUIDELINESTATUS_PENDING = "PENDING";
	public static final String DSP_GUIDELINESTATUS_DRAFT = "DRAFT";
	public static final String DSP_GUIDELINESTATUS_FINAL = "FINAL";
	
	public static final String H_DSP_GUIDELINESTATUS_PENDING = "लंबित";
	public static final String H_DSP_GUIDELINESTATUS_DRAFT = "प्रारूप्";
	public static final String H_DSP_GUIDELINESTATUS_FINAL = "फाइनल";
	
	public static final String  MAKER1                   ="makerChioce";
	public static final String  MAKER2                   ="create";
	public static final String  CHECKER1                   ="checkerChioce";
	public static final String  CHECKER2                   ="tehsilChoice";
	public static final String  CHECKER3                   ="wardChoice";
	public static final String  CHECKER4                   ="mohallaChoice";
	public static final String  APPROVE_CHECKER5           = "approveGuidelineNew";
	public static final String  APPROVE_CHECKER_READ_ONLY           = "checkerReadOnly";
	public static final String DRAFT_TO_FINAl1             = "draftToFinal";
	public static final String DRAFT_FINAl_FORM             = "guidelineDraftToFinalForm";
	public static final String WARNING_JSP            = "warningOverlapping";
	public static final String WARNING_JSP_FINAL            = "warningOverlappingFinal";
	public static final String WELCOME =             "welcomePage";
	public static final String FIRST_PAGE =           "create";
	public static final String VIEW_PROP_DETLS                   ="prop";
	public static final String VIEW_GUIDELINE_DETLS                   ="rates";
	public static final String MAKER_DERIVE             = "makerDerive";
	public static final String DERIVE_CONFIRMATION      ="deriveConfirmation";
	public static final String MAKER_DERIVE_DRAFT       ="makerDeriveDraft";
	public static final String MAKER_DERIVE_2             = "makerDerive2";
	public static final String MAKER_DERIVE_3             = "makerDerive3";
	public static final String MAKER_DERIVE_4             = "makerDerive4";
	public static final String MAKER_DERIVE_5             = "makerDerive5";
	
	public static final String PUBLISH_MAIN            ="publishPDF";
	public static final String PUBLISH_CONFIRMATION            ="publishConfirm";
	public static final String DISTRICT_WISE_VIEW_PAGE      ="viewDisttDetails";
	public static final String PRINT_GUIDELINE_PAGE         ="printGuideline";
	public static final String GUIDELINE_VIEW_PRAROOP      ="viewGuidelinePraroop";
	public static final String PDF_GENERATION_ACTION   	   = "pdfGeneration";
	
	public static final String EMAIL_SUBJECT                = "Feedback Entered by The Assessee for Guideline Draft";
	public static final String user_id						= "surajv";
	
	//*******************ADDED BY SIMRAN FOR REPORT GENERATION******************************//
	//********************************JSP*************************//
	public static final String GUIDELINE_SELECTION = "guidelineSelection";
	public static final String GUIDELINE_COMPARISON = "guidelineComparison";
	
	//************ACTION NAMES******************************//
	public static final String SHOW_AVIALABLE_GUDELINES = "SHOW_AVIALABLE_GUDELINES";
	public static final String NEXT_TO_COMPARISON = "NEXT_TO_COMPARISON";
	public static final String SHOW_FINAL_GUIDELINE = "SHOW_FINAL_GUIDELINE";
	public static final String CLOSE_ACTION = "CLOSE_ACTION";
	public static final String PRINT_ACTION = "PRINT_ACTION";
	public static final String RESET_ACTION = "RESET_ACTION";
	
	//***********Property Type Id and L1 Id for Industrial******************//
	public static final String PROPERTY_TYPE_FOR_PLOT_INDUSTRIAL = "1";
	public static final String PROPERTY_TYPE_L1_FOR_PLOT_INDUSTRIAL = "3";
	public static final String PROPERTY_TYPE_L1_FOR_PLOT_RESIDENTIAL = "1";
	
	//*****************ADDED BY SHREERAJ FOR GUIDELINE PUBLISH************************
	public static final String PDF_NAME_PRAROOP1 = "Praroop-1";
	public static final String PDF_NAME_PRAROOP2 = "Praroop-2";
	public static final String PDF_NAME_PRAROOP3 = "Praroop-3";
	public static final String PDF_NAME_PRAROOPALL = "Praroop-All";
	public static final String PDF_NAME_PRAROOP1_EXT = "Praroop-1.pdf";
	public static final String PDF_NAME_PRAROOP2_EXT = "Praroop-2.pdf";
	public static final String PDF_NAME_PRAROOP3_EXT = "Praroop-3.pdf";
	public static final String PDF_NAME_PRAROOPALL_EXT = "Praroop-All.pdf";
}
