/**
 * ServiceProviderRule.java
 */


package com.wipro.igrs.sp.rule;


import java.util.ArrayList;
import java.util.Date;

import com.wipro.igrs.empmgmt.dto.EmpmgmtUploadDTO;
import com.wipro.igrs.sp.dto.ServiceProviderDTO;
import com.wipro.igrs.util.CommonRoutines;
import com.wipro.igrs.util.CommonUtil;
import com.wipro.igrs.util.PropertiesFileReader;

import java.text.SimpleDateFormat;

public class ServiceProviderRule {
	private boolean error;

	PropertiesFileReader pr = null;

	ArrayList errorList = null;

	public ServiceProviderRule() {
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		} catch (Exception e) {
			System.out.println("Not Able to Initilize the property File" + e);
		}
	}

	protected boolean nullOrBlank(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isError() {
		return error;
	}

	/**
	 * Method name :validateServiceProvider
	 * 
	 * @param providerDTO
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList validateServiceProvider(ServiceProviderDTO providerDTO)
	throws Exception {
		
		System.out.println("Validate Service Provider ......................");
		
		boolean flag = false;
		
		errorList = new ArrayList();
		
		if (nullOrBlank(providerDTO.getSpuserid())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.userid.required"));
		}
		
		if (nullOrBlank(providerDTO.getSpaddress())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.adress.required"));
		} else if (providerDTO.getSpaddress().trim().length() > 100) {
			flag = true;
			errorList.add(pr.getValue("error.sp.adress.maxlength"));
		}
		
		if (nullOrBlank(providerDTO.getSppostalcode())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.postal.required"));
		} else if (!CommonRoutines.isNumber(providerDTO.getSppostalcode())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.postal.number"));
			providerDTO.setSppostalcode("");
		}

		//edited by shruti
		if (nullOrBlank(providerDTO.getSpusertypename()))
		{
			flag=true;
			errorList.add(pr.getValue("error.sp.sptypeid.required"));
		}
		//end of code
		
		if (nullOrBlank(providerDTO.getSpdistrctid())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.districtid.required"));
		}
		
		if (nullOrBlank(providerDTO.getSptehsilid())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.tehsil.required"));
		}
		
		if (nullOrBlank(providerDTO.getSplicencenumber())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.licence.required"));
		}
		
		if (nullOrBlank(providerDTO.getSplfromdate())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.fromdate.required"));
		}
		
		if (nullOrBlank(providerDTO.getSpltodate())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.todate.required"));
		} else if (compareDates(providerDTO.getSplfromdate(), providerDTO
				.getSpltodate())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.date.notvalid"));
		}
		
		if (nullOrBlank(providerDTO.getDrocomments())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.comments.required"));
		} else if (providerDTO.getDrocomments().trim().length() > 100) {
			flag = true;
			errorList.add(pr.getValue("error.sp.comments.maxlength"));
		}
		
		setError(flag);
		return errorList;

	}

	/**
	 * Method name :compareDates
	 * 
	 * @param fromdate
	 * @param todate
	 * @return
	 * 
	 */
	private boolean compareDates(String fromdate, String todate) {
		System.out.println(fromdate);
		boolean flag = true;
		double x = Date.parse(fromdate);
		double y = Date.parse(todate);
		double days = (x - y) / (24 * 60 * 60 * 1000);
		if (days <= 0) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * Method name :validateServiceProviderBank
	 * 
	 * @param providerDTO
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList validateServiceProviderBank(ServiceProviderDTO providerDTO)
	throws Exception {
		System.out.println("Validate Service Provider ......................");
		boolean flag = false;
		errorList = new ArrayList();
		if (nullOrBlank(providerDTO.getSpuserid())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.userid.required"));
		}
		if (nullOrBlank(providerDTO.getSpbankid())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.bank.required"));
		}
		/*if (nullOrBlank(providerDTO.getSpaddress())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.adress.required"));
		}*/
		if (nullOrBlank(providerDTO.getSpdistrctid())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.districtid.required"));
		}
		if (nullOrBlank(providerDTO.getSplicencenumber())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.licence.required"));
		}
		if (nullOrBlank(providerDTO.getSplfromdate())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.fromdate.required"));
		}
		if (nullOrBlank(providerDTO.getSpltodate())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.todate.required"));
		} else if (compareDates(providerDTO.getSplfromdate(), providerDTO
				.getSpltodate())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.date.notvalid"));
		}
		if (nullOrBlank(providerDTO.getDrocomments())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.comments.required"));
		} else if (providerDTO.getDrocomments().trim().length() > 100) {
			flag = true;
			errorList.add(pr.getValue("error.sp.comments.maxlength"));
		}
		setError(flag);
		return errorList;

	}
	public ArrayList validateUserDetails(ServiceProviderDTO providerDTO, ArrayList listFileNames)
	throws Exception {
		System.out.println("Validate Service Provider ......................");
		boolean flag = false;
		errorList = new ArrayList();
		try{
		if (nullOrBlank(providerDTO.getSptehsilid())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.teshil.required"));
		}
		if (nullOrBlank(providerDTO.getSplicencenumber())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.licenceNumber.required"));
		}
		if (nullOrBlank(providerDTO.getSpDurationFrom())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.durationFrom.required"));
		}
		if (nullOrBlank(providerDTO.getSpDurationTo())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.durationTo.required"));
		}
		//
		else if (compareDates(providerDTO.getSpDurationFrom(), providerDTO.getSpDurationTo())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.date.notvalid"));
		}
		//
		
		if (nullOrBlank(providerDTO.getSpaddress())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.address.required"));
		}
		if (nullOrBlank(providerDTO.getSpdistrctid())) {
			flag = true;
			errorList.add(pr.getValue("error.sp.district.required"));
		}
		/*		
		if (providerDTO.getSpDurationFrom().trim().length() > 0) {
			java.util.Date sysUtilDate = new java.util.Date();
			//int currDate=Integer.parseInt(sysUtilDate.toString());
			Date dateFrom = CommonUtil.getJavaUtilDate(providerDTO.getSpDurationFrom().trim());
			//int enteredDate=Integer.parseInt(dateFrom.toString());
			if(sysUtilDate.after(dateFrom)){
				
			   System.out.println("COMPARE :"+dateFrom.compareTo(sysUtilDate));
			   //if(enteredDate > currDate){
				flag = true;
				errorList.add(pr.getValue("error.sp.fromsysdate"));
				
			}
			
		}
		if (providerDTO.getSpDurationTo().trim().length() > 0) {
			java.util.Date sysUtilDate = new java.util.Date();
			Date dateto = CommonUtil.getJavaUtilDate(providerDTO.getSpDurationTo().trim());
			if(dateto.before(sysUtilDate)){		
				flag = true;
				errorList.add(pr.getValue("error.sp.tosysdate"));
			}
			
			
		}
		if (providerDTO.getSpDurationFrom().trim().length() > 0
				&& providerDTO.getSpDurationTo().trim().length() > 0) {
			if (CommonUtil.isGreater(providerDTO.getSpDurationFrom().trim(),providerDTO.getSpDurationTo().trim())) {

				flag = true;
				errorList.add(pr.getValue("error.sp.fromtodate"));
			}
		}
		if (providerDTO.getSpDateOfInsurance().trim().length() > 0) {
			java.util.Date sysUtilDate = new java.util.Date();
			Date dateto = CommonUtil.getJavaUtilDate(providerDTO.getSpDateOfInsurance().trim());
			if(dateto.before(sysUtilDate)){		
				flag = true;
				errorList.add(pr.getValue("error.sp.issuancesysdate"));
			}
			
			
		}
		if (providerDTO.getSpDateOfInsurance().trim().length() > 0 && providerDTO.getSpDurationFrom().trim().length() > 0) {
			if (CommonUtil.isGreater(providerDTO.getSpDateOfInsurance().trim(),providerDTO.getSpDurationFrom().trim())) {

				flag = true;
				errorList.add(pr.getValue("error.sp.fromissuancedate"));
			}	
						
		}*/
		
		}catch(Exception e){
			e.printStackTrace();
		}
		setError(flag);
		return errorList;

	}
	
	public ArrayList validateUserDetailsList(ServiceProviderDTO ProviderDTO) throws Exception{
		boolean flag = false;
		errorList = new ArrayList();
		if (ProviderDTO==null) {
			flag = true;
			errorList.add(pr.getValue("error.sp.userDetailsList"));
		}
		setError(flag);
		return errorList;

	}
	
	public ArrayList validateFileType(String fileExt,ArrayList arrayList) {
		errorList = new ArrayList();
		boolean flag = false;
		String[] arrFileExt = { "doc", "xls", "pdf", "txt", "jpg", "tiff",
				"gif", "rtf", "zip" };
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errorList.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
			for(int i=0;i<arrayList.size();i++){
				System.out.println("arraylist"+arrayList.size());
				ServiceProviderDTO providerDTO1=(ServiceProviderDTO)arrayList.get(i);
				
				
				for(int j=i;j<arrayList.size();j++){
					if(i!=j){
					ServiceProviderDTO uploadDTO1=(ServiceProviderDTO)arrayList.get(j);
					
					if(providerDTO1.getFileName().equalsIgnoreCase(uploadDTO1.getFileName()))
					{
						errorList.add(pr.getValue("error.audit.samefile"));
						flag = true;
					setError(flag);
					}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errorList;
	}
	public ArrayList validateFileType(String fileExt) {
		String[] arrFileExt = { "doc", "xls", "pdf", "txt", "jpg", "tiff",
				"gif", "rtf", "zip" };
		errorList = new ArrayList();
		boolean flagFile = false;
		boolean flag=false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errorList.add(pr.getValue("error.audit.fileType"));
				
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errorList;
	}
	
	//added
	public ArrayList validateUserName(ServiceProviderDTO providerDTO)
	throws Exception {
		System.out.println("Validate Service Provider Username ......................");
		boolean flag = false;
		errorList = new ArrayList();
		try{
			
			
			if (nullOrBlank(providerDTO.getSpuserid())) {
				flag = true;
				errorList.add(pr.getValue("error.sp.userid.required"));
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		setError(flag);
		return errorList;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
