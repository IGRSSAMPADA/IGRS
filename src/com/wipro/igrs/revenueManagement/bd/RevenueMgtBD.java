package com.wipro.igrs.revenueManagement.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

/*import com.wipro.igrs.propertylock.dao.PropertyLockDAO;
import com.wipro.igrs.propertylock.dto.PropertyLockDTO;*/
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.dao.RevenueMgtDAO;
import com.wipro.igrs.revenueManagement.dto.RevenueMgtDTO;
import com.wipro.igrs.revenueManagement.form.RevenueMgtForm;


public class RevenueMgtBD {
	private Logger logger = (Logger) Logger.getLogger(RevenueMgtBD.class);
	//RevenueMgtDAO dao=null;
	
	
	 /******************************************************************  
	  *   Method Name  :   getFunction()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getFunction(String languageLocale) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
        ArrayList subList = null;
        RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getFunction();
        
		RevenueMgtDTO dto = new RevenueMgtDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new RevenueMgtDTO();
            dto.setValue(subList.get(0).toString());
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            dto.setName(subList.get(1).toString());
            }else{
            	dto.setName(subList.get(2).toString());
            }
            mainList.add(dto);
        	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getService()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getService(String funid,String languageLocale,RevenueMgtDTO formDto) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
       ArrayList subList = null;
       RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getService(funid);
       
		RevenueMgtDTO dto = new RevenueMgtDTO();
       //for (int i = 0; i < list.size(); i++) 
       if(list!=null)
       {
           subList = (ArrayList)list.get(0);
           dto = new RevenueMgtDTO();
           dto.setValue(subList.get(0).toString());
           formDto.setCaptureFeeFlag(subList.get(3).toString());
           if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
           dto.setName(subList.get(1).toString());
           if(subList.get(4)!=null){
           formDto.setFeeBasedOnLabel(subList.get(4).toString());
           }else{
        	   formDto.setFeeBasedOnLabel("");
           }
           }else{
        	   if(subList.get(2)!=null){
        	   dto.setName(subList.get(2).toString());
        	   }else{
        		   dto.setName(subList.get(1).toString());
        	   }
        	   if(subList.get(5)!=null){
                   formDto.setFeeBasedOnLabel(subList.get(5).toString());
                   }else{
                	   formDto.setFeeBasedOnLabel("");
                   }
           }
           mainList.add(dto);
       	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	

	/******************************************************************  
	  *   Method Name  :   getMajorHead()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getMajorHead() throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
       ArrayList subList = null;
       RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getMajorHead();
       
		RevenueMgtDTO dto = new RevenueMgtDTO();
       for (int i = 0; i < list.size(); i++) {
           subList = (ArrayList)list.get(i);
           dto = new RevenueMgtDTO();
           dto.setValue(subList.get(0).toString());
           dto.setName(subList.get(1).toString());
           mainList.add(dto);
       	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	/******************************************************************  
	  *   Method Name  :   getSubMajorHead()
	  *   Arguments    :   major head id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	/*public ArrayList getSubMajorHead(String mjrHdId) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
      ArrayList subList = null;
      RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getSubMajorHead(mjrHdId);
      
		RevenueMgtDTO dto = new RevenueMgtDTO();
      for (int i = 0; i < list.size(); i++) {
          subList = (ArrayList)list.get(i);
          dto = new RevenueMgtDTO();
          dto.setValue(subList.get(0).toString());
          dto.setName(subList.get(1).toString());
          mainList.add(dto);
      	}
		}catch(Exception e){
			
		}
		return mainList;
	}*/
	public ArrayList getSubMajorHead() throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
      ArrayList subList = null;
      RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getSubMajorHead();
      
		RevenueMgtDTO dto = new RevenueMgtDTO();
      for (int i = 0; i < list.size(); i++) {
          subList = (ArrayList)list.get(i);
          dto = new RevenueMgtDTO();
          dto.setValue(subList.get(0).toString());
          dto.setName(subList.get(1).toString());
          mainList.add(dto);
      	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	/******************************************************************  
	  *   Method Name  :   getMinorHead()
	  *   Arguments    :   sub major head id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	/*public ArrayList getMinorHead(String mjrHdId) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
     ArrayList subList = null;
     RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getMinorHead(mjrHdId);
     
		RevenueMgtDTO dto = new RevenueMgtDTO();
     for (int i = 0; i < list.size(); i++) {
         subList = (ArrayList)list.get(i);
         dto = new RevenueMgtDTO();
         dto.setValue(subList.get(0).toString());
         dto.setName(subList.get(1).toString());
         mainList.add(dto);
     	}
		}catch(Exception e){
			
		}
		return mainList;
	}*/
	
	public ArrayList getMinorHead() throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
     ArrayList subList = null;
     RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getMinorHead();
     
		RevenueMgtDTO dto = new RevenueMgtDTO();
     for (int i = 0; i < list.size(); i++) {
         subList = (ArrayList)list.get(i);
         dto = new RevenueMgtDTO();
         dto.setValue(subList.get(0).toString());
         dto.setName(subList.get(1).toString());
         mainList.add(dto);
     	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	/******************************************************************  
	  *   Method Name  :   getDependList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getDependList() throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
      ArrayList subList = null;
      RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getDependList();
      
		RevenueMgtDTO dto = new RevenueMgtDTO();
      for (int i = 0; i < list.size(); i++) {
          subList = (ArrayList)list.get(i);
          dto = new RevenueMgtDTO();
          dto.setValue(subList.get(0).toString());
          dto.setName(subList.get(1).toString());
          mainList.add(dto);
      	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	/******************************************************************  
	  *   Method Name  :   getOperatorList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getOperatorList() throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
      ArrayList subList = null;
      RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getOperatorList();
      
		RevenueMgtDTO dto = new RevenueMgtDTO();
      for (int i = 0; i < list.size(); i++) {
          subList = (ArrayList)list.get(i);
          dto = new RevenueMgtDTO();
          dto.setValue(subList.get(0).toString());
          dto.setName(subList.get(1).toString());
          mainList.add(dto);
      	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	
	/******************************************************************  
	  *   Method Name  :   getOperatorList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getYearList() throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
     ArrayList subList = null;
     RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getYearList();
     
		RevenueMgtDTO dto = new RevenueMgtDTO();
     for (int i = 0; i < list.size(); i++) {
         subList = (ArrayList)list.get(i);
         dto = new RevenueMgtDTO();
         dto.setValue(subList.get(0).toString());
         dto.setName(subList.get(1).toString());
         mainList.add(dto);
     	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	/******************************************************************  
	  *   Method Name  :   getPriorityList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getPriorityList() throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
      ArrayList subList = null;
      RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getPriorityList();
      
		RevenueMgtDTO dto = new RevenueMgtDTO();
      for (int i = 0; i < list.size(); i++) {
          subList = (ArrayList)list.get(i);
          dto = new RevenueMgtDTO();
          dto.setValue(subList.get(0).toString());
          dto.setName(subList.get(1).toString());
          mainList.add(dto);
      	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	/******************************************************************  
	  *   Method Name  :   getDistrictList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getDistrictList(String languageLocale) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
     ArrayList subList = null;
     RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getDistrictList();
     
		RevenueMgtDTO dto = new RevenueMgtDTO();
     for (int i = 0; i < list.size(); i++) {
         subList = (ArrayList)list.get(i);
         dto = new RevenueMgtDTO();
         dto.setValue(subList.get(0).toString());
         if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
         dto.setName(subList.get(1).toString());
         }else{
        	 dto.setName(subList.get(2).toString()); 
         }
         mainList.add(dto);
     	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	
	/******************************************************************  
	  *   Method Name  :   getOfficeTypeList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 * @param disId 
	 *******************************************************************/  
	public ArrayList getOfficeTypeList(String disId,String languageLocale) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
    ArrayList subList = null;
    RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getOfficeTypeList(disId);
    
		RevenueMgtDTO dto = new RevenueMgtDTO();
    for (int i = 0; i < list.size(); i++) {
        subList = (ArrayList)list.get(i);
        dto = new RevenueMgtDTO();
        dto.setValue(subList.get(0).toString());
        if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        dto.setName(subList.get(1).toString());
        }else{
        	dto.setName(subList.get(2).toString());
        }
        mainList.add(dto);
    	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	/******************************************************************  
	  *   Method Name  :   getOfficeNameList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 * @param disId 
	 * @param offtyp 
	 *******************************************************************/  
	public ArrayList getOfficeNameList(String disId, String offtyp, String languageLocale) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
        ArrayList subList = null;
   RevenueMgtDAO dao = new RevenueMgtDAO();
		try{
		list = dao.getOfficeNameList(disId,offtyp);
   
		RevenueMgtDTO dto = new RevenueMgtDTO();
   for (int i = 0; i < list.size(); i++) {
       subList = (ArrayList)list.get(i);
       dto = new RevenueMgtDTO();
       dto.setValue(subList.get(0).toString());
       if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
       dto.setName(subList.get(1).toString());
       }else{
    	   dto.setName(subList.get(2).toString());
       }
       mainList.add(dto);
   	}
		}catch(Exception e){
			
		}
		return mainList;
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
	 * @param disId 
	 * @param offtyp 
	 *******************************************************************/  
	public ArrayList getreportDetails(RevenueMgtDTO dto, String radioDt, String userDt, String fmDt, String toDt, String mnth,String languageLocale) {
		 RevenueMgtDAO dao = new RevenueMgtDAO();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =dao.getreportDetails(dto,radioDt,userDt,fmDt,toDt,mnth,languageLocale);
		
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
		
     RevenueMgtDAO dao = new RevenueMgtDAO();
     String[] param=new String[9];
		try{
			
			
			
			param[0]=eForm.getUid();
			param[1]=eForm.getRevdto().getRevMjrHeadId();
			param[2]=eForm.getRevdto().getRevSubMjrHeadId();
			param[3]=eForm.getRevdto().getRevMnrHeadId();
			if(eForm.getRevdto().getCaptureFeeFlag().equalsIgnoreCase("Y")){
			param[4]=eForm.getRevdto().getRuBasicVal();
			param[5]=eForm.getRevdto().getSpBasicVal();
			param[6]=eForm.getRevdto().getDrBasicVal();
			}else{
				param[4]="";
				param[5]="";
				param[6]="";
			}
			param[7]=eForm.getRevdto().getFunctionId();
			param[8]=eForm.getRevdto().getServiceId();
			
			//call dao method for updation
			
		}catch(Exception e){
			
		}
		return dao.updateMatrix(param);
	}
	
	/******************************************************************  
	  *   Method Name  :   getMatrixView()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	   :   Exception
	  *   Author       :   ROOPAM MEHTA
	  *   Date         :   11 Nov 2013   
	 *******************************************************************/
	public ArrayList getMatrixView() {
		 RevenueMgtDAO dao = new RevenueMgtDAO();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =dao.getMatrixView();
		
		}catch(Exception e){
		}
		return desDtl;
	}
}
