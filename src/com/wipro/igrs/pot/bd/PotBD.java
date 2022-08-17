package com.wipro.igrs.pot.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sun.research.ws.wadl.Request;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.pot.action.GetDistrictNames;
import com.wipro.igrs.pot.bo.PotBo;


import com.wipro.igrs.pot.dao.PotDAO;
import com.wipro.igrs.pot.dto.PhysicalStampDTO;
import com.wipro.igrs.pot.dto.PotDistrictDTO;
import com.wipro.igrs.pot.dto.potDTO;
import com.wipro.igrs.pot.form.PotFORM;
import com.wipro.igrs.pot.constant.PotConstants;




public class PotBD
{
	
	private  Logger logger = 
		(Logger) Logger.getLogger(PotBD.class);
	public static String RID = null;
	PotDAO objDAO=null;
	
	/**
     * ===========================================================================
     * Method         :   getDistrictNames()
     * Description    :   Get All the Districts  from the igrs_district_master table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   4 feb,2008
     * ===========================================================================
     * 
     */
	
	public  void getDistrictNames(HttpServletRequest request,String language) throws IOException
	{
		ArrayList getPotDistrict = new ArrayList();
		HttpSession session = null;
		PotBo bo = null;
		
		try
		{
			session = request.getSession();
			bo = new PotBo();
			ArrayList list1 = bo.getDistrictNames(language);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						PotDistrictDTO names = new PotDistrictDTO();
						ArrayList list =(ArrayList)list1.get(i);
						names.setDistrictIdDTO((String)list.get(0));
						names.setDistrictNameDTO((String)list.get(1));
						getPotDistrict.add(names);
					}
		    }
			PotDistrictDTO dto= new PotDistrictDTO();
			dto.setList(getPotDistrict);
			session.setAttribute("districtNames",dto);
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getDistrictNames in BD" + e);
	    }
	}

	
	
	//
	public  void getDistrictId(HttpServletRequest request,String language) throws IOException
	{
		ArrayList getPotDistrict1 = new ArrayList();
		HttpSession session = null;
		PotBo bo = null;
		try
		{
			session = request.getSession();
			bo = new PotBo();
			ArrayList list1 = bo.getDistrictId(language);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						PotDistrictDTO names = new PotDistrictDTO();
						ArrayList list =(ArrayList)list1.get(i);
						names.setDistrictIdDTO((String)list.get(0));
						names.setDistrictNameDTO((String)list.get(1));
						getPotDistrict1.add(names);
					}
		    }
			PotDistrictDTO dto= new PotDistrictDTO();
			dto.setList(getPotDistrict1);
			session.setAttribute("districtNames",dto);
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getDistrictNames in BD" + e);
	    }
	}
	
	
	
	//
	
	
	/**
     * ===========================================================================
     * Method         :   getDocumentTypeId()
     * Description    :   Get All the Document Type Ids  from the potConstants. 
     * return type    :   ArrayList
     * Author         :   venkata Hari Krishna G.
     * Created Date   :   14 oct,2008
     * ===========================================================================
     * 
     */
	
	public  void getDocumentTypeId(HttpServletRequest request) throws IOException
	{
		ArrayList getPotDocTypeId = new ArrayList();
		HttpSession session = null;
		PotBo bo = null;
		try
		{
			session = request.getSession();			
			PotDistrictDTO regid = new PotDistrictDTO();
			PotDistrictDTO stamp = new PotDistrictDTO();			
			regid.setDocTypeId(PotConstants.REG_ID);			
			getPotDocTypeId.add(regid);
			stamp.setDocTypeId(PotConstants.STAMP_DUTY);			
			getPotDocTypeId.add(stamp);		
			PotDistrictDTO dto= new PotDistrictDTO();
			dto.setDocList(getPotDocTypeId);
			session.setAttribute("docTypeIdDTO",dto);
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getDocumentTypeId in BD" + e);
	    }
	}
	/**
     * ===========================================================================
     * Method         :   getBookId()
     * Description    :   Get All the book numbers  from the potConstants. 
     * return type    :   ArrayList
     * Author         :   venkata Hari Krishna G.
     * Created Date   :   14 oct,2008
     * ===========================================================================
     * 
     */
	
	public  void getBookId(HttpServletRequest request) throws IOException
	{
		ArrayList getPotBookId = new ArrayList();
		HttpSession session = null;
		PotBo bo = null;
		try
		{
			session = request.getSession();			
			PotDistrictDTO book01 = new PotDistrictDTO();
			PotDistrictDTO book02 = new PotDistrictDTO();
			PotDistrictDTO book03 = new PotDistrictDTO();
			PotDistrictDTO book04 = new PotDistrictDTO();
			PotDistrictDTO supply = new PotDistrictDTO();
			
			book01.setBookNo(PotConstants.BOOK_01);			
			getPotBookId.add(book01);
			book02.setBookNo(PotConstants.BOOK_02);			
			getPotBookId.add(book02);
			book03.setBookNo(PotConstants.BOOK_03);			
			getPotBookId.add(book03);
			book04.setBookNo(PotConstants.BOOK_04);			
			getPotBookId.add(book04);
			supply.setBookNo(PotConstants.SUPPLY);			
			getPotBookId.add(supply);
			PotDistrictDTO dto= new PotDistrictDTO();
			dto.setBookList(getPotBookId);
			session.setAttribute("bookIdDTO",dto);
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getBookId in BD" + e);
	    }
	}
	/**
     * ===========================================================================
     * Method         :   getDocumentTypeId()
     * Description    :   Get All the Document Type Ids  from the potConstants. 
     * return type    :   ArrayList
     * Author         :   venkata Hari Krishna G.
     * Created Date   :   14 oct,2008
     * ===========================================================================
     * 
     */
	
	public  void getPhysicalStampCodes(HttpServletRequest request) throws IOException
	{
		ArrayList physicalStampIdList = new ArrayList();
		HttpSession session = null;
		PotBo bo = null;
		try
		{
			session = request.getSession();			
			PotDistrictDTO stamp = new PotDistrictDTO();
			PotDistrictDTO challan = new PotDistrictDTO();
			stamp.setPhysicalStampId(PotConstants.STAMP_CODE);			
			physicalStampIdList.add(stamp);
			challan.setPhysicalStampId(PotConstants.Challan_ID);			
			physicalStampIdList.add(challan);		
			PotDistrictDTO dto= new PotDistrictDTO();
			dto.setPhysicalList(physicalStampIdList);
			session.setAttribute("physicalStampDTO",dto);
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getPhysicalStampCodes in BD" + e);
	    }
	}
	 public String getRID()
     {
         return RID;
     }
     
     private static String potDgenerator() 
	 {
    	 PotDAO dao = null;
		try {
			dao = new PotDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 String sequencenumber = dao.getsequenceNumber();
     //    String sequencenumber = "PO" + new Date().getTime();
        
         if(sequencenumber.length()==1){
 			
             sequencenumber="00000"+sequencenumber;  
             }else
             if(sequencenumber.length()==2){
                     sequencenumber="0000"+sequencenumber;  
             }else
             if(sequencenumber.length()==3){
                     sequencenumber="000"+sequencenumber;  
             }else
             if(sequencenumber.length()==4){
                     sequencenumber="00"+sequencenumber;  
             }
         if(sequencenumber.length()==5){
             sequencenumber="0"+sequencenumber;  
     }
         String sNum="PO" + sequencenumber;
 		return sNum;
         
     }   
	  
     public static void main(String[] args) {
		
    	PotBD.potDgenerator(); 
	}
     /**
      * ===========================================================================
      * Method         :   potCreate()
      * Description    :   Inserting all values into IGRS_PUBLIC_OFFICER_TOOL_TXN table. 
      * return type    :   boolean
      * Author         :   Gopi.C
      * Created Date   :   6 Feb,2008
      * ===========================================================================
      * 
      */
 	
	public boolean potCreate(PotFORM formData,ArrayList al) throws IOException
	{
		
		boolean flag = false;
		boolean flag1 = false;
		PotBo bo = null;
		try
		{
			RID = potDgenerator();
			bo = new PotBo();
			
		if(formData.getOldNew().equalsIgnoreCase("Old"))	 
			 
		{	 
			
				    String param2[] = new String[15];
					String fName2 = formData.getPoFirstName();
					String mName2 = formData.getPoMiddleName();
					String lName2 = formData.getPoLastName();
					String district2 = formData.getDistrict();
				
					String objDate2 = formData.getDateOfObjection();
					String txnId2 = RID;
					String docType2 = formData.getOldNew();
					String eStampDuty2 = formData.getStampFeeDoc();
					String reqStamp2 = formData.getStampRequired();
					String oldRegVolNo2 = formData.getOldRegVolumeNo();
					String oldBookNo2 = formData.getOldRegBookNo();
					String oldRegNo = formData.getOldRegNo();
					String RegData2 = formData.getOldRegDate();
					String impound2 = formData.getImpound();
					String poComments2 = formData.getPoComments();
					
			/*		System.out.println("in bd--<>fName--<>"+fName2+"==mName==<>"+mName2+"==lName==<>"+lName2+
					"==district=="+district2+"==objDate=="+objDate2+"==txnId=="+txnId2+"==docType=="+docType2+
					"==eStampDuty2=="+eStampDuty2+"==reqStamp2=="+reqStamp2+"==oldRegVolNo2=="+oldRegVolNo2+
					"==oldBookNo2=="+oldBookNo2+"==impound2=="+impound2+"==poComments=="+poComments2+
					"==oldRegNo=="+oldRegNo+"==RegData2=="+RegData2);	*/
					
					param2[0] = fName2;
					param2[1] = mName2;
					param2[2] = lName2;
					param2[3] = district2;
					//param2[4] = objDate2;
					param2[4] = txnId2;
					param2[5] = docType2;
					param2[6] = eStampDuty2;
					param2[7] = reqStamp2;
					param2[8] = oldRegVolNo2;
					param2[9] = oldBookNo2;
					param2[10] = oldRegNo;
				//	param2[11] = impound2;
					param2[11] = callImpound(impound2);
					param2[12] = poComments2;
					param2[13] = getDate(objDate2);
					param2[14] = getDate(RegData2);
					flag1 = bo.potCreateOld22(param2);					
						String temp = formData.getTemp();
						String temp_[] = temp.split(",");
						for(int i = 0; i <temp_.length; i++)
						{
							//System.out.println("mytemp="+temp_[i]);							
						}
						for(int i = 0; i <temp_.length;)
						{
							String param11[] = new String[3];
							param11[0] = temp_[i];
							//i++;
							param11[1] = temp_[i];
							param11[2] = RID;
							i++;
						    flag = bo.potCreateOld(param11);    
						}			 
		}
		if(formData.getOldNew().equalsIgnoreCase("New"))
		{
					String param1[] = new String[13];
					String fName = formData.getPoFirstName();
					String mName = formData.getPoMiddleName();
					String lName = formData.getPoLastName();
					String district = formData.getDistrict();
					String objDate = formData.getDateOfObjection();
					String txnId = RID;
					String docType = formData.getOldNew();
					String regNo= formData.getRegNumber();
					String eStampNo = formData.getEstampCode();
					String eStampDuty = formData.getStampFeeDoc();
					String reqStamp = formData.getStampRequired();
					String impound = formData.getImpound();
					String poComments = formData.getPoComments();
					formData.setTransactionId(RID);
					
					param1[0] = fName;
					param1[1] = mName;
					param1[2] = lName;
					param1[3] = district;
					param1[4] = txnId;
					param1[5] = docType;
					param1[6] = regNo;
					param1[7] = eStampNo;
					param1[8] = eStampDuty;
					param1[9] = reqStamp;
					param1[10] = callImpound(impound);
					param1[11] = poComments; 
					param1[12] = getDate(objDate); 
					flag = bo.potCreate(param1);  
				
		}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return flag;
			
	}

	public String callImpound(String str)
	{
		String imp = null;
		if(str.equals("Yes"))
		{
			imp="Y";
		}
		if(str.equals("No"))
		{
			imp="N";
		}
		return imp;
	}
	
	/**
     * ===========================================================================
     * Method         :   getPotList()
     * Description    :   Selecting few fields from the IGRS_PUBLIC_OFFICER_TOOL_TXN table. 
     * return type    :   Arraylist
     * Author         :   Gopi.C
     * Created Date   :   13 Feb,2008
     * ===========================================================================
	 * @param dis 
     * 
     */
	
	public ArrayList getPotList(PotFORM data,String language, String dis) throws Exception 
	{
		ArrayList list = new ArrayList();
		PotFORM pot = null;
		PotBo bo = null;
		try
		{
			bo = new PotBo();
			String[] param = new String[2];
			String[] param2 = new String[3];
			
			String actionID = data.getTransactionId();
			String durationFrom = data.getDurationFrom();
			String durationTo = data.getDurationTo();
			
			param[0] = actionID;
			param[1] = dis;
			param2[0] = durationFrom;
			param2[1] = durationTo;
			param2[2] = dis;
			
			ArrayList mainList = bo.getPotList(param,language);
			
			if(actionID != null && (!actionID.equals("")))
			{
				for(int i=0;i<mainList.size();i++)
				{
					ArrayList subList =(ArrayList) mainList.get(i);	
					 pot = new PotFORM();
					 pot.setTransactionId((String)subList.get(0));
					 pot.setOldNew((String)subList.get(1));
					 pot.setFirstName((String)subList.get(2));
					 pot.setLastName((String)subList.get(3));
					 pot.setDateOfObjection((String)subList.get(4));
					 // START | POT Tool Changes | santosh
					 //pot.setImpound((String)subList.get(5));
					// END | POT Tool Changes | santosh
					 pot.setDistrict((String)subList.get(5));
					 list.add(pot);
				}
			}
			else
			{
				//ArrayList mainList2 = bo.getPotList2(data);
				ArrayList mainList2 = bo.getPotList3(durationFrom,durationTo,data.getCreatedBy(),language);
				if(mainList2!=null)
				{
					
					for(int i=0;i<mainList2.size();i++)
					{
					ArrayList subList =(ArrayList) mainList2.get(i);	
					 pot = new PotFORM();
					 pot.setTransactionId((String)subList.get(0));
					 pot.setOldNew((String)subList.get(1));
					 pot.setFirstName((String)subList.get(2));
					 pot.setLastName((String)subList.get(3));
					 pot.setDateOfObjection((String)subList.get(4));
					// START | POT Tool Changes | santosh
					 //pot.setImpound((String)subList.get(5));
					// END | POT Tool Changes | santosh
					 pot.setDistrict((String)subList.get(5));
					 
					list.add(pot);
					}
				}
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList getPotList1(PotFORM data,String language, String dis) throws Exception 
	{
		ArrayList list = new ArrayList();
		PotFORM pot = null;
		PotBo bo = null;
		try
		{
			bo = new PotBo();
			String[] param = new String[2];
			String[] param2 = new String[3];
			
			String actionID = data.getTransactionId();
			
			String durationFrom = data.getDurationFrom();
			String durationTo = data.getDurationTo();
			
			param[0] = actionID;
			param[1] = dis;
			param2[0] = durationFrom;
			param2[1] = durationTo;
			param2[2] = dis;
			
			ArrayList mainList = bo.getPotListUp(param,language);
			
			if(actionID != null && (!actionID.equals("")))
			{
				for(int i=0;i<mainList.size();i++)
				{
					 ArrayList subList =(ArrayList) mainList.get(i);	
					 pot = new PotFORM();
					 pot.setTransactionId((String)subList.get(0));
					 pot.setOldNew((String)subList.get(1));
					 pot.setFirstName((String)subList.get(2));
					 pot.setLastName((String)subList.get(3));
					 pot.setDateOfObjection((String)subList.get(4));
					 // START | POT Tool Changes | santosh
					 //pot.setImpound((String)subList.get(5));
					 // END | POT Tool Changes | santosh
					 pot.setDistrict((String)subList.get(5));
					 list.add(pot);
				}
			}
			else
			{
				ArrayList mainList2 = bo.getPotList2(durationFrom,durationTo,dis,language);
				//ArrayList mainList2 = bo.getPotList3(durationFrom,durationTo,data.getCreatedBy());
				if(mainList2!=null)
				{
					
					for(int i=0;i<mainList2.size();i++)
					{
					 ArrayList subList =(ArrayList) mainList2.get(i);	
					 pot = new PotFORM();
					 pot.setTransactionId((String)subList.get(0));
					 pot.setOldNew((String)subList.get(1));
					 pot.setFirstName((String)subList.get(2));
					 pot.setLastName((String)subList.get(3));
					 pot.setDateOfObjection((String)subList.get(4));
					// START | POT Tool Changes | santosh
					 //pot.setImpound((String)subList.get(5));
					 // END | POT Tool Changes | santosh
					 pot.setDistrict((String)subList.get(5));
					 
					list.add(pot);
					}
				}
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	
	
	/**
     * ===========================================================================
     * Method         :   getPotDetails()
     * Description    :   Selecting  all records from igrs_public_officer_tool_txnin where between given dates . 
     * return type    :   Arraylist
     * Author         :   Gopi.C
     * Created Date   :   18 Feb,2008
     * ===========================================================================
	 * @param language 
     * 
     */
	
	// START | POT Tool Changes to fetch hit from DR page | santosh
	public  PotFORM getPotDetails(String potId, String language, String pageName) throws Exception
	{
		ArrayList getFullList = new ArrayList();   
		PotFORM pot = null;
		PotBo bo = null;
		try
		{
			bo = new PotBo();
			ArrayList list1 = bo.getPotDetails(potId);
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
						{
							pot = new PotFORM();
							//pot.getSelectedItems();
							ArrayList list =(ArrayList)list1.get(i);
							pot.setPoFirstName((String)list.get(0));
							pot.setPoMiddleName((String)list.get(1));
							pot.setPoLastName((String)list.get(2));
						
							pot.setDistrict(	new PotDAO().getDistrict((String)list.get(3),language));
							
							pot.setDateOfObjection((String)list.get(4));
							//created date changes for POT
							pot.setCreatedDate((String)list.get(20));
							pot.setTransactionId((String)list.get(5));
							
							String oldNew = (String)list.get(6);
							
							String check = (String)list.get(14);
							pot.setStampRequired((String)list.get(9));
							String estampCode = (String)list.get(7);
							if(check.equalsIgnoreCase("Y"))
								
							{
								String regId = (String)list.get(6);
								ArrayList<potDTO> dtos = searchRegIdView(regId);
								pot.setSelectedItems(dtos);
								
							}
							
							else
							{
								if(estampCode!=null && !estampCode.equalsIgnoreCase(""))
								{
									pot.setEstampCode(estampCode);
									//getEstampDetails(pot);
									fetchEstampDetails(pot,pageName);
								}
								else
								{
									getPstampDetails(potId,pot);
									//START | POT Tool Changes | santosh
									String drComments = (String)list.get(19);
									if(drComments!=null && drComments!="" && (drComments.equalsIgnoreCase("Yes") || drComments.equalsIgnoreCase("No"))){
										pot.setImpundCheck("N");
									}
									//END | POT Tool Changes | santosh
									
									
								}
								
								
							}
							
							/*if(oldNew.equalsIgnoreCase("NEW"))
							{
								if(language.equalsIgnoreCase("en"))
									oldNew = "New";
								else
									oldNew = "à¤¨à¤¯à¤¾";
							}
							else if((oldNew.equalsIgnoreCase("OLD")))
							{
								if(language.equalsIgnoreCase("en"))
									oldNew = "Old";
								else
									oldNew = "à¤ªà¥�à¤°à¤¾à¤¨à¤¾";
								
								
							}
							*/
							//START | POT Tool Changes | santosh
							/*String impound = (String)list.get(12);
							
							if(impound.equalsIgnoreCase("YES"))
							{
								if(language.equalsIgnoreCase("en"))
									impound = "Yes";
								else
									impound = "à¤¹à¤¾à¤�";
							}
							else if((impound.equalsIgnoreCase("NO")))
							{
								if(language.equalsIgnoreCase("en"))
									impound = "No";
								else
									impound = "à¤¨à¤¹à¥€à¤‚";
								
								
							}*/
							//END | POT Tool Changes | santosh
							
						//	pot.setOldNew(oldNew);
							String regS = (String)list.get(6);
							if(regS==null ||"".equalsIgnoreCase(regS) )
							{
								pot.setRegNumber("-");
							}
							else
							pot.setRegNumber((String)list.get(6));
						/*	pot.setOldRegVolumeNo((String)list.get(8));
							pot.setOldRegBookNo((String)list.get(9));
							pot.setOldRegNo((String)list.get(10));*/
							if(pot.getSelectedItems().size()>0)
							{
							potDTO d =  	(potDTO) pot.getSelectedItems().get(0);
							pot.setEstampCode(d.getEstampCode());
							pot.setStampFeeDoc(d.getEstampAmount());
						//	pot.setStampRequired((String)list.get(13));
							pot.setCaseStat("N/A");
							pot.setCaseNum("N/A");
							}
							
						
							//pot.setImpound((String)list.get(14));
							
							
							//START | POT Tool Changes | santosh
							if(pageName!=null && pageName.equalsIgnoreCase("potUpdate")){
								String impoundVal = (String)list.get(19);
								if(language.equalsIgnoreCase("en")){
									pot.setImpound(impoundVal);
								}else{
									if(impoundVal.equalsIgnoreCase("Yes")){
										pot.setImpound("हाँ");
									}else if(impoundVal.equalsIgnoreCase("No")){
										pot.setImpound("नहीं");	
									}else{
										pot.setImpound(impoundVal);
									}
								}
							}else{
								String impoundVal = (String)list.get(18);
								if(language.equalsIgnoreCase("en")){
									pot.setImpound(impoundVal);
								}else{
									if(impoundVal.equalsIgnoreCase("Yes")){
										pot.setImpound("हाँ");
									}else if(impoundVal.equalsIgnoreCase("No")){
										pot.setImpound("नहीं");	
									}else{
										pot.setImpound(impoundVal);
									}
								}
							}
							//START | POT Tool Changes | santosh
							pot.setPoComments((String)list.get(11));
						//	String caseOpened = (String)list.get(16);
							/*if(language.equalsIgnoreCase("en"))
						    pot.setCaseOpend((String)list.get(16));
							else
							{
								if(caseOpened.equalsIgnoreCase("YES"))
								{
									 pot.setCaseOpend("à¤¹à¤¾à¤�");
								}
								else
								{
									 pot.setCaseOpend("à¤¨à¤¹à¥€à¤‚");
								}
							}*/
						    pot.setDrComments((String)list.get(13));
							pot.setExdate(new Date().toString());
							pot.getPotDTO().setDocumentPath((String)list.get(16));
							pot.getPotDTO().setDocumentName((String)list.get(17));
							pot.setCaseOpend("YES");
						    getFullList.add(pot);
													
						}
			}
		}
		catch(Exception e)
		{
	        //logger.error("this is Exception in getPotDetails in BD" + e);
			System.out.println("this is Exception in getPotDetails in BD" + e);
			e.printStackTrace();
	    }
		
		return pot;
	}
	
	// END | POT Tool Changes to fetch hit from DR page | santosh
	
	private void getPstampDetails(String potId, PotFORM pot) {
		PotDAO d=null;
		HashMap<String, potDTO> hasP= null;
	//	String counterPStamp = String.valueOf(pot.getPotDTO().getCounterPStamp()+1);
		try {
			d = new PotDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList l = d.getPstampDetails(potId);
		if(l!=null && l.size()>0)
		{
			hasP = new HashMap<String, potDTO>();
			for(int i=0;i<l.size();i++)
			{
				ArrayList li = (ArrayList) l.get(i);
				potDTO da = new potDTO();
				da.setCodeStamps((String)li.get(0));
				da.setDenominationStamps(Double.valueOf((String)li.get(1)));
				String counterPStamp = String.valueOf(pot.getPotDTO().getCounterPStamp()+1);
				
				hasP.put(counterPStamp, da);
				pot.getPotDTO().setCounterPStamp(pot.getPotDTO().getCounterPStamp()+1);
				
			}
			
			
			
			
		}
		
		ArrayList party = d.getPartyPstampDetails(potId);
		if(party!=null && party.size()>0)
		{
			ArrayList lis = (ArrayList) party.get(0);
			
			pot.setPstampFirstName((String)lis.get(0));
			
			String middleName = (String)lis.get(1);
			
			if(middleName!=null  && !middleName.equalsIgnoreCase("") )
			pot.setPstampMiddleName((String)lis.get(1));
			else
				pot.setPstampMiddleName("-");
			
			pot.setPstampLastName((String)lis.get(2));
			String gender = (String)lis.get(3);
			if(gender.equalsIgnoreCase("M"))
			pot.setPstampGender("Male/पुरुष");
			else
				pot.setPstampGender("Female/महिला");	
			pot.setPstampAge((String)lis.get(4));
			pot.setPstampAddress((String)lis.get(5));
			pot.setPstampPincode((String)lis.get(6));
			
			String phoneNo = (String)lis.get(7);
			if(phoneNo!=null && !phoneNo.equalsIgnoreCase(""))
			pot.setPstampPhoneNo((String)lis.get(7));
			else
			pot.setPstampPhoneNo("-");
			
			pot.setPstampMobileNo((String)lis.get(8));
			pot.setPstampEmailId((String)lis.get(9));
			
			String motherName = (String)lis.get(10);
			
			if(motherName!=null && !motherName.equalsIgnoreCase(""))
			pot.setPstampMothersName((String)lis.get(10));
			else
			pot.setPstampMothersName("-");
			pot.setPstampFathersName((String)lis.get(11));
			pot.setPstampNoOfPersons((String)lis.get(12));
			
			ArrayList lis1 = (ArrayList) party.get(1);
			
pot.setPstampFirstName1((String)lis1.get(0));
			
			String middleName1 = (String)lis1.get(1);
			
			if(middleName!=null  && !middleName.equalsIgnoreCase("") )
			pot.setPstampMiddleName1((String)lis1.get(1));
			else
				pot.setPstampMiddleName1("-");
			
			pot.setPstampLastName1((String)lis1.get(2));
			String gender1 = (String)lis1.get(3);
			if(gender.equalsIgnoreCase("M"))
			pot.setPstampGender1("Male/पुरुष");
			else
				pot.setPstampGender1("Female/महिला");	
			pot.setPstampAge1((String)lis1.get(4));
			pot.setPstampAddress1((String)lis1.get(5));
			pot.setPstampPincode1((String)lis1.get(6));
			
			String phoneNo1 = (String)lis1.get(7);
			if(phoneNo!=null && !phoneNo.equalsIgnoreCase(""))
			pot.setPstampPhoneNo1((String)lis1.get(7));
			else
			pot.setPstampPhoneNo1("-");
			
			pot.setPstampMobileNo1((String)lis1.get(8));
			pot.setPstampEmailId1((String)lis1.get(9));
			
			String motherName1 = (String)lis1.get(10);
			
			if(motherName!=null && !motherName.equalsIgnoreCase(""))
			pot.setPstampMothersName1((String)lis1.get(10));
			else
			pot.setPstampMothersName1("-");
			pot.setPstampFathersName1((String)lis1.get(11));
			pot.setPstampNoOfPersons1((String)lis.get(12));
			
			
			
			
		}
		
		
		pot.setPstampDetails(hasP);
	}



	public String impound(String str)
	{
		String impound = null;
		if(str.equals("Y"))
		{
			impound="Yes";
		}
		if(str.equals("N"))
		{
			impound="No";
		}
		return  impound;
	}
	
	public ArrayList getPotPhysicalList(String txnId) throws Exception
	{
		ArrayList getFullList = new ArrayList();
		PotFORM pot2 = null;
		PotBo bo = null;
		try
		{
			bo = new PotBo();
			ArrayList list1 = bo.getPotPhysicalList(txnId);
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
						{
							pot2 = new PotFORM();
							
							ArrayList list =(ArrayList)list1.get(i);
						/*	pot2.setStampNumber((String)list.get(0));
							pot2.setStampFee((String)list.get(1));
							*/
							pot2.setStampFeeDoc((String)list.get(0));
							pot2.setStampRequired((String)list.get(1));
							getFullList.add(pot2);
													
						}
			}
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getPotPhysicalList in BD" + e);
	    }
		
		return getFullList;
		
	}
	

	/**
     * ===========================================================================
     * Method         :   potUpdate()
     * Description    :   Selecting  all records from igrs_public_officer_tool_txnin where between given dates . 
     * return type    :   boolean
     * Author         :   Gopi.C
     * Created Date   :   20 Feb,2008
     * ===========================================================================
     * 
     */
	
	
	public boolean potUpdate1(HttpServletRequest request,PotFORM data) throws Exception
	{
		return new PotDAO().potUpdate12(data);
	
	}
	public boolean potUpdate(HttpServletRequest request,PotFORM data) throws Exception
	{
		boolean flag = false;
		PotBo bo = null;
		try
		{
			bo = new PotBo();
			HttpSession session = request.getSession();
			String userId = (String)session.getAttribute("UserId");
			if(userId==null)
			{
				userId="prakash";
			}			
			String[] param = new String[5];
			String param2[] = new String[3];			
		
			
			
		
			
	
			String impound = data.getImpound();
			String poComments = data.getPoComments();
			
			String caseOpen = data.getCaseOpend();
		
			String drComments = data.getDrComments();
			
			String txnId = data.getTransactionId();
			
		
			String addPoComments = data.getPublicOfficerComments();
			String myArray[] = addPoComments.split(",");
			String rowCount = data.getRowCount();
			int noOfRows = Integer.parseInt(rowCount);
			
			
			param[0] = poComments;
			if(caseOpen.equalsIgnoreCase("YES"))
			param[1] = caseOpen;
			else
				param[1] = "NO";
			param[2] = drComments;
			param[3] = userId;
			param[4] = txnId;
			
			flag = bo.potUpdate1(param);
			
			
			if(noOfRows!=0)
			{
				for(int i=0;i<myArray.length;i++)
				{
					param2[0] = myArray[i];
					param2[1] = txnId;
					param2[2] = userId;
					flag = bo.potUpdate2(param2);				
				}
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
	
		}
		return flag;
		
	}
	
	
	
	
	
	public ArrayList getPotComments(String txnId) throws Exception
	{
		ArrayList getFullList = new ArrayList();
		PotFORM comments = null;
		PotBo bo = null;
		try
		{
			bo = new PotBo();
			ArrayList list1 = bo.getPotComments(txnId);
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
						{
							comments = new PotFORM();
							ArrayList list =(ArrayList)list1.get(i);
							comments.setPublicOfficerComments((String)list.get(0));
							getFullList.add(comments);
						}
			}
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getPotComments in BD" + e);
	    }
		
		return getFullList;
		
	}
	/**
	 * @param _fdate
	 * @return
	 */
	public String getDate(String _fdate) {

		StringTokenizer stoken = new StringTokenizer(_fdate, "/");
		String dd = stoken.nextToken();
		String mm = stoken.nextToken();
		String yy = stoken.nextToken();
		if (dd.length() == 2) {
			_fdate = dd + "-" + getMonthName(mm) + "-" + yy;
		}

		return _fdate;

	}  

	/**
	 * @param _month
	 * @return
	 */
	public String getMonthName(String _month) {
		HashMap hm = new HashMap();
		hm.put("01", "JAN");
		hm.put("02", "FEB");
		hm.put("03", "MAR");
		hm.put("04", "APR");
		hm.put("05", "MAY");
		hm.put("06", "JUN");
		hm.put("07", "JUL");
		hm.put("08", "AUG");
		hm.put("09", "SEP");
		hm.put("10", "OCT");
		hm.put("11", "NOV");
		hm.put("12", "DEC");
		return (String) hm.get(_month);
	}
	
	
	
	public ArrayList searchRegId(String _regNo) throws Exception {
		logger.debug("IN BD searchRegId");
		ArrayList dataSet, row;
		ArrayList retList = new ArrayList();
		try {
			PotBo bo = new PotBo();
			dataSet = bo.searchRegIdBO(_regNo);
			if (dataSet != null && dataSet.size() > 0) {
				potDTO dto;
				for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
					row = (ArrayList) dataSet.get(iLoop);
					dto = new potDTO();
					dto.setRegNumber(row.get(0).toString());
					if((row.get(1)!=null))
					{
					dto.setFirstName(row.get(1).toString());
					}
					else
					{
						dto.setFirstName("-");
					}
					if((row.get(2)!=null))
					{
					dto.setMiddleName(row.get(2).toString());
					}else
					{
						dto.setMiddleName("-");	
					}
					if((row.get(3)!=null))
					{
					dto.setLastName(row.get(3).toString());
					}
					else
					{
						dto.setLastName("-");
					}
					
					if((row.get(4)!=null))
					{
					dto.setCaseNum(row.get(4).toString());
					}
					else
					{
						dto.setCaseNum("N/A");
					}
					
					if((row.get(5)!=null))
					{
					dto.setCaseStat(row.get(5).toString());
					}
					else
					{
						dto.setCaseStat("N/A");
					}
					
					
					dto.setEstampCode(row.get(6).toString());
					dto.setEstampAmount(row.get(7).toString());
					/////	
					
					retList.add(dto);
					
				}
				dataSet.clear();
			}
			if(dataSet.size()==0)
			{
				dataSet = 	bo.searchRegIdBO1(_regNo);
				
				
				potDTO dto;
				for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
					row = (ArrayList) dataSet.get(iLoop);
					dto = new potDTO();
					dto.setRegNumber(row.get(0).toString());
					if((row.get(1)!=null))
					{
					dto.setFirstName(row.get(1).toString());
					}
					else
					{
						dto.setFirstName("-");
					}
					if((row.get(2)!=null))
					{
					dto.setMiddleName(row.get(2).toString());
					}else
					{
						dto.setMiddleName("-");	
					}
					if((row.get(3)!=null))
					{
					dto.setLastName(row.get(3).toString());
					}
					else
					{
						dto.setLastName("-");
					}
					
					
						dto.setCaseNum("N/A");
						dto.setCaseStat("N/A");
					
					
					
					dto.setEstampCode(row.get(4).toString());
					dto.setEstampAmount(row.get(5).toString());
					/////	
					
					retList.add(dto);
					
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return retList;
	}	
	///
	
	
	public ArrayList searchRegIdView(String _regNo) throws Exception {
		logger.debug("IN BD searchRegId");
		ArrayList dataSet, row;
		ArrayList retList = new ArrayList();
		try {
			PotBo bo = new PotBo();
			dataSet = bo.searchRegIdBO(_regNo);
			if (dataSet != null && dataSet.size() > 0) {
				potDTO dto;
				for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
					row = (ArrayList) dataSet.get(iLoop);
					dto = new potDTO();
					dto.setRegNumber(row.get(0).toString());
					if((row.get(1)!=null))
					{
					dto.setFirstName(row.get(1).toString());
					}
					else
					{
						dto.setFirstName("-");
					}
					if((row.get(2)!=null))
					{
					dto.setMiddleName(row.get(2).toString());
					}else
					{
						dto.setMiddleName("-");	
					}
					if((row.get(3)!=null))
					{
					dto.setLastName(row.get(3).toString());
					}
					else
					{
						dto.setLastName("-");
					}
					
					if((row.get(4)!=null))
					{
					dto.setCaseNum(row.get(4).toString());
					}
					else
					{
						dto.setCaseNum("N/A");
					}
					
					if((row.get(5)!=null))
					{
					dto.setCaseStat(row.get(5).toString());
					}
					else
					{
						dto.setCaseStat("N/A");
					}
					
					
					dto.setEstampCode(row.get(6).toString());
					dto.setEstampAmount(row.get(7).toString());
					/////	
					
					retList.add(dto);
					
				}
				dataSet.clear();
			}
			if(dataSet.size()==0)
			{
				dataSet = 	bo.searchRegIdBO1(_regNo);
				
				
				potDTO dto;
				for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
					row = (ArrayList) dataSet.get(iLoop);
					dto = new potDTO();
					dto.setRegNumber(row.get(0).toString());
					if((row.get(1)!=null))
					{
					dto.setFirstName(row.get(1).toString());
					}
					else
					{
						dto.setFirstName("-");
					}
					if((row.get(2)!=null))
					{
					dto.setMiddleName(row.get(2).toString());
					}else
					{
						dto.setMiddleName("-");	
					}
					if((row.get(3)!=null))
					{
					dto.setLastName(row.get(3).toString());
					}
					else
					{
						dto.setLastName("-");
					}
					
					
						dto.setCaseNum("N/A");
						dto.setCaseStat("N/A");
					
					
					
					dto.setEstampCode(row.get(4).toString());
					dto.setEstampAmount(row.get(5).toString());
					/////	
					
					retList.add(dto);
					
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return retList;
	}	
	
	public ArrayList getPendingApps(String userId)
	{
		
		try {
			objDAO= new PotDAO();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return objDAO.getPendingApps(userId);
	}
	
	///
	public ArrayList getPotNameBD(String userId) throws Exception {
		logger.debug("IN BD searchRegId");
		ArrayList dataSet, row;
		ArrayList retList = new ArrayList();
		try {
			PotBo bo = new PotBo();
			dataSet = bo.getPotNameBO(userId);
			if (dataSet != null && dataSet.size() > 0) {
				potDTO dto;
				for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
					row = (ArrayList) dataSet.get(iLoop);
					dto = new potDTO();
					
					if((row.get(0)!=null))
					{
					dto.setPoFirstName(row.get(0).toString());
					}
					
					else
					{
						dto.setPoFirstName("-");
					}
					if((row.get(1)!=null))
					{
					dto.setPoMiddleName(row.get(1).toString());
					}else
					{
						dto.setPoMiddleName("-");	
					}
					if((row.get(2)!=null))
					{
					dto.setPoLastName(row.get(2).toString());
					}
					else
					{
						dto.setPoLastName("-");
					}
				System.out.println(dto.getPoFirstName());	
				
/////
					dto.setDistrict(row.get(3).toString());
					retList.add(dto);
					
				}
				dataSet.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return retList;
	}



	public void getEstampDetails(PotFORM formData) {
	
		
		PotDAO dao = null;
		try {
			dao = new PotDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.getEstampDEtails(formData);
		
	}
	
public void fetchEstampDetails(PotFORM formData, String pageName) {
	
		
		PotDAO dao = null;
		try {
			dao = new PotDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.fetchEstampDEtails(formData,pageName);
		
	}
	
/*public void fetchEstampDetails(PotFORM formData) {
	
		
		PotDAO dao = null;
		try {
			dao = new PotDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.fetchEstampDEtails(formData);
		
	}*/



	public void updateEstamp(PotFORM formData) {
		PotDAO dao = null;
		try {
			dao = new PotDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.updateEstamp(formData.getEstampCode(), "Consumed");
		
	}	
	
	public void updateEstamp1(PotFORM formData) {
		PotDAO dao = null;
		try {
			dao = new PotDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.updateEstamp(formData.getEstampCode(), "Impound");
		
	}



	public boolean updatePOT(PotFORM formData) {
		PotDAO dao = null;
		String RIDs = potDgenerator();
		try {
			dao = new PotDAO();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.updatePOT(formData,RIDs);
		
	}



	public String getDistrict(String officeId) {
		PotDAO dao = null;
		
		try {
			dao = new PotDAO();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.getDistrict(officeId);
		
	}

	public String getDistrict1(String officeId) {
		PotDAO dao = null;
		
		try {
			dao = new PotDAO();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.getDistrict1(officeId);
		
	}

	public boolean insertRegNumberDetails(PotFORM formData) {
PotDAO dao = null;
		
		try {
			dao = new PotDAO();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.insertRegNumberDetailsDao(formData,potDgenerator());
	}



	public boolean insertEstampDetials(PotFORM formData) {
PotDAO dao = null;
		
		try {
			dao = new PotDAO();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.insertEstampDetialsDao(formData,potDgenerator());
	}



	public boolean insertPstampDetails(PotFORM formData) {
		PotDAO dao = null;
		
		try {
			dao = new PotDAO();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return dao.insertPstampDetailsDao(formData,potDgenerator());
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
		
	
	
	///
}
