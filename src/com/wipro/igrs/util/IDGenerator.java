package com.wipro.igrs.util;

public class IDGenerator {


	public static String generateSPLicenseID(String sp,String spType,String districtCode,String droCode,String month,String year,String serialNumber)
	{
		String id=new String();
		id=sp+spType+districtCode+droCode+month+year+serialNumber;
		return id;
	}
	
	public static String generateWILLDepositionID(String madhyaPradesh,String districtCode,String month,String year,String willReference,String serialNumber)
	{
		String id=new String();
		id=madhyaPradesh+districtCode+month+year+willReference+serialNumber;
		return id;
	}
	
	public static String generateEStampCode(String madhyaPradesh,String eStampType,String districtCode,String dateOfEStamp,String monthOfEStamp,String year,String serialNumber)
	{
		String id=new String();
		id=madhyaPradesh+eStampType+districtCode+dateOfEStamp+monthOfEStamp+year+serialNumber;
		return id;
	}
	
	public static String generateRegistrationNumber(String madhyaPradesh,String districtCode,String subRegistrarOfficeCode,String year,String bookType,String serialNumber)
	{
		String id=new String();
		id=madhyaPradesh+districtCode+subRegistrarOfficeCode+year+bookType+serialNumber;
		return id;
	}
	
	public static String generateAGMPReceiptAuditReportID(String agmpReceipt,String districtCode,String month,String year,String uniqueReportNumber)
	{
		String id=new String();
		id=agmpReceipt+districtCode+month+year+uniqueReportNumber;
		return id;
	}
	
	public static String generateAGMPExpenditureAuditID(String agmpExpenseAudit,String districtCode,String month,String year,String uniqueReportNumber)
	{
		String id=new String();
		id=agmpExpenseAudit+districtCode+month+year+uniqueReportNumber;
		return id;
	}
	
	public static String generateInternalAuditReportID(String internalAudit,String districtCode,String month,String year,String uniqueReportNumber)
	{
		String id=new String();
		id=internalAudit+districtCode+month+year+uniqueReportNumber;
		return id;
	}
	
	public static String generateInternalExpenditureAuditReportID(String internalExpenditureAudit,String districtCode,String month,String year,String uniqueReportNumber)
	{
		String id=new String();
		id=internalExpenditureAudit+districtCode+month+year+uniqueReportNumber;
		return id;
	}
	
	public static String generateInspectionReportID(String inspectionReport,String districtCode,String month,String year,String uniqueReportNumber)
	{
		String id=new String();
		id=inspectionReport+districtCode+month+year+uniqueReportNumber;
		return id;
	}
	
	public static String generatePublicOfficeInspectionReportID(String publicOfficeInspectionReport,String districtCode,String month,String year,String uniqueReportNumber)
	{
		String id=new String();
		id=publicOfficeInspectionReport+districtCode+month+year+uniqueReportNumber;
		return id;
	}
	
	public static String generateCaseNumber(String districtCode,String revenueCode,String month,String year,String uniqueCaseNumber)
	{
		String id=new String();
		id=districtCode+revenueCode+month+year+uniqueCaseNumber;
		return id;
	}
	
	public static String generateRevenueRecoveryCaseNumber(String revenueRecoveryCase,String districtCode,String revenueCode,String month,String year,String uniqueCaseNumber)
	{
		String id=new String();
		id=revenueRecoveryCase+districtCode+revenueCode+month+year+uniqueCaseNumber;
		return id;
	}

}
