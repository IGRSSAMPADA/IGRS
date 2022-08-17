package com.wipro.igrs.revenueManagement.bo;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.bd.RevenueMgtBD;
import com.wipro.igrs.revenueManagement.dto.RevenueMgtDTO;
import com.wipro.igrs.revenueManagement.form.RevenueMgtForm;

public class revenueMgtBO {
	private Logger logger = (Logger) Logger.getLogger(revenueMgtBO.class);
	
	//RevenueMgtBD revBD=null;
	
	
	 /******************************************************************  
	  *   Method Name  :   getFunction()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getFunction(String languageLocale) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getFunction(languageLocale);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getService()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getService(String funId,String languageLocale,RevenueMgtDTO formDto) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getService(funId,languageLocale,formDto);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getMajorHead()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getMajorHead() throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getMajorHead();
		
		}catch(Exception e){
		}
		return desDtl;
	}
	
	/******************************************************************  
	  *   Method Name  :   getSubMajorHead()
	  *   Arguments    :  major head id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	/*public ArrayList getSubMajorHead(String mjrHdId) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getSubMajorHead(mjrHdId);
		
		}catch(Exception e){
		}
		return desDtl;
	}*/
	public ArrayList getSubMajorHead() throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getSubMajorHead();
		
		}catch(Exception e){
		}
		return desDtl;
	}
	
	/******************************************************************  
	  *   Method Name  :   getMinorHead()
	  *   Arguments    : sub major head id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	/*public ArrayList getMinorHead(String mjrHdId) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getMinorHead(mjrHdId);
		
		}catch(Exception e){
		}
		return desDtl;
	}*/
	public ArrayList getMinorHead() throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getMinorHead();
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getDependList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getDependList() throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getDependList();
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getOperatorList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getOperatorList() throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getOperatorList();
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getOperatorList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getYearList() throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getYearList();
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getPriorityList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getPriorityList() throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getPriorityList();
		
		}catch(Exception e){
		}
		return desDtl;
	}
	
	/******************************************************************  
	  *   Method Name  :   getDistrictList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getDistrictList(String languageLocale) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getDistrictList(languageLocale);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getOfficeTypeList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getOfficeTypeList(String disId,String languageLocale) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getOfficeTypeList(disId,languageLocale);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	
	/******************************************************************  
	  *   Method Name  :   getOfficeNameList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 * @param offtyp 
	 *******************************************************************/  
	public ArrayList getOfficeNameList(String disId, String offtyp, String languageLocale) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getOfficeNameList(disId, offtyp, languageLocale);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	
	/******************************************************************  
	  *   Method Name  :   getreportDetails()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 * @param mnth 
	 * @param toDt 
	 * @param fmDt 
	 * @param userDt 
	 * @param offtyp 
	 *******************************************************************/  
	public ArrayList getreportDetails(RevenueMgtDTO dto, String radioDt, String userDt, String fmDt, String toDt, String mnth,String languageLocale) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =revBD.getreportDetails(dto,radioDt,userDt,fmDt,toDt,mnth,languageLocale);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   updateMatrix()
	  *   Arguments    :   RevenueMgtForm
	  *   Return       :   boolean
	  *   Throws 	   :   Exception
	  *   Author       :   ROOPAM MEHTA
	  *   Date         :   11 Nov 2013   
	 *******************************************************************/  
	public boolean updateMatrix(RevenueMgtForm eForm) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		
		
		return revBD.updateMatrix(eForm);
	}
	/******************************************************************  
	  *   Method Name  :   getMatrixView()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	   :   Exception
	  *   Author       :   ROOPAM MEHTA
	  *   Date         :   11 Nov 2013   
	 *******************************************************************/
	public ArrayList getMatrixView(String languageLocale) throws Exception{
		RevenueMgtBD revBD = new RevenueMgtBD();
		ArrayList finalList=new ArrayList();
		ArrayList list=revBD.getMatrixView();
		ArrayList subList;
		RevenueMgtDTO dto = new RevenueMgtDTO();
		if(list!=null && list.size()>0){
			  for (int i = 0; i < list.size(); i++) {
			       subList = (ArrayList)list.get(i);
			       dto = new RevenueMgtDTO();
			       dto.setValue(subList.get(0).toString());  //mapping txn id
			       dto.setFunctionId(subList.get(1).toString());
			       //dto.setFunctionName(subList.get(2).toString());
			       dto.setServiceId(subList.get(3).toString());
			       //dto.setServiceName(subList.get(4).toString());
			       dto.setRevMjrHeadId(subList.get(5).toString());
			       dto.setRevSubMjrHeadId(subList.get(6).toString());
			       dto.setRevMnrHeadId(subList.get(7).toString());
			       
			       dto.setCaptureFeeFlag(subList.get(13).toString());
			       
			       if(subList.get(8).toString()!=null && !subList.get(8).toString().equalsIgnoreCase("")){
			       dto.setRuBasicVal(subList.get(8).toString());
			       }else{
			    	   dto.setRuBasicVal("-");
			       }
			       
			       if(subList.get(9).toString()!=null && !subList.get(9).toString().equalsIgnoreCase("")){
				       dto.setSpBasicVal(subList.get(9).toString());
				       }else{
				    	   dto.setSpBasicVal("-");
				       }
			       
			       if(subList.get(10).toString()!=null && !subList.get(10).toString().equalsIgnoreCase("")){
				       dto.setDrBasicVal(subList.get(10).toString());
				       }else{
				    	   dto.setDrBasicVal("-");
				       }
			       
			       //dto.setSpBasicVal(subList.get(9).toString());
			       //dto.setDrBasicVal(subList.get(10).toString());
			       
			       if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			    	   dto.setFunctionName(subList.get(2).toString());
			    	   dto.setServiceName(subList.get(4).toString());
			    	   dto.setFeeBasedOnLabel(subList.get(14).toString());
			       }else{
			    	   dto.setFunctionName(subList.get(11).toString());
			    	   dto.setServiceName(subList.get(12).toString());
			    	   dto.setFeeBasedOnLabel(subList.get(15).toString());
			       }
			      
			       finalList.add(dto);
			   	}
		}
		
		return finalList;
	}
}
