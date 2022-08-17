package com.wipro.igrs.Seals.bd;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.poi.util.IOUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.newgen.burning.Adjudicant;
import com.newgen.burning.Adjudication;
import com.newgen.burning.AdmissionSeal;
import com.newgen.burning.Attorney;
import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Consenter;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.Executant;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.OtherSeal;
import com.newgen.burning.Party;
import com.newgen.burning.PresentationSeal;
import com.newgen.burning.Presenter;
import com.newgen.burning.ReadPropertiesFile;
import com.newgen.burning.RegistrationSeal;
import com.newgen.burning.Witness;
import com.newgen.burning.WitnessSeal;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.Seals.constant.SealsConstant;
import com.wipro.igrs.Seals.dao.SealsDAO;
import com.wipro.igrs.Seals.dto.SealsDTO;
import com.wipro.igrs.Seals.form.SealsForm;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dao.RegCommonDAO;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.dao.RegCompCheckerDAO;
import com.wipro.igrs.regCompChecker.form.RegCompCheckerForm;



public class SealsBD {
	
	SealsDAO sealDAO = new SealsDAO();
	
	/**
	 * 
	 * @param sealId
	 * @return ArrayList
	 * @throws Exception
	 */
	private Logger logger = Logger.getLogger(SealsBD.class);
	public ArrayList getSubSealIds(String sealId, String regId,String language) throws Exception{
		
		ArrayList subList = new ArrayList();
		ArrayList subList1 = new ArrayList();
		ArrayList mainList = new ArrayList();
		SealsDTO dto = null;
		ArrayList list = sealDAO.getSubSealIds(sealId,language);
		ArrayList oldSealList = sealDAO.getSealDetailsForReg(regId);
		
		for(int i= 0 ; i < list.size() ; i ++)
		{
			dto = new SealsDTO();
			subList = (ArrayList)list.get(i);
			dto.setSealSubId((String)subList.get(0));
			dto.setSealSubName((String)subList.get(1));
			dto.setStatus("N");
			String subId = (String)subList.get(0);
			
			for(int j = 0 ; j < oldSealList.size() ; j++)
			{
				subList1 = (ArrayList)oldSealList.get(j);
				String subIdOld = (String)subList1.get(0);
				if(subId.equals(subIdOld))
				{
					dto.setStatus("Y");
					break;
				}
					
			}
			mainList.add(dto);
		}
		return mainList;
	}
	
	/**
	 *  for execuion display page - Rahul
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPresentationPartyDetails(String regNumber,String lang) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList subList = new ArrayList();
		//ArrayList list = sealDAO.getPresentationPartyDetails(regNumber); old code 1 parameter 
		ArrayList list = sealDAO.getPresentationPartyDetails(regNumber,regNumber);
		// for POA , Owner - RAhul
		RegCommonDAO commonDAO= new RegCommonDAO();
		RegCommonBO commonBO = new RegCommonBO();
		RegCommonBD commonBD = new RegCommonBD();
		RegCommonDTO mapDto =new RegCommonDTO();
		RegCommonForm form = new RegCommonForm();
	//	ArrayList poAOwnerList = commonDAO.getOwnerPoaDescDisplay(lang, regNumber);
		ArrayList poAOwnerListForOrg = new ArrayList();
		int i1=0;
		String OwnerRolePoA="";
		String partyId = "";
		String CommonDeedFlag = "";
	     CommonDeedFlag=sealDAO.getCommonFlowFlag(regNumber);
		
		
		for(int i= 0 ; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			SealsDTO sDTO = new SealsDTO();
			
			

			if(subList.get(4)!=null)
			{
				partyId = subList.get(4).toString();
			}
			
			
			String fName = (String)subList.get(1);
			String lName = (String)subList.get(2);
			fName = fName == null?"":fName;
			lName = lName == null?"":lName;
			// For POA , Owner Details- Rahul
			  int roleId = Integer.parseInt((String)subList.get(3));
			  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
			  mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
			  int ExecutantClaimantfromDB=0;
			  if (subList.get(15)!=null && !subList.get(15).toString().equalsIgnoreCase(""))
		   	  {
		        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
		   	  }	
			   //ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
			  // for will deed Authenticaed - RAhul
			  if(subList.get(8)!=null &&subList.get(8).toString().equalsIgnoreCase("2"))
			  {
			  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
					  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
				    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||
				    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5
				    	
				    	||
				    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
				    	
			  {
				  mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
					 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
					form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
         		 String RoleName = form.getExecutantClaimantName();
             	 System.out.println("Role Name " +RoleName); 
             	// to check hindi and english role 
            	 String HindiEnglishRole[] = RoleName.split(",");
            	 String hindesc = HindiEnglishRole[0];
            	 String EngDesc =HindiEnglishRole[1];
            	 
            	 
            	 String PoaName = null;
            	 if (EngDesc.contains("Authenticated")){ 
            		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
              		   PoaName =" By(Authenticated PoA Holder)";
           		  }
           		  else {
           			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
           		  }}    
            	 else {
            		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            			 PoaName =" By(PoA Holder)";
            		  }
            		  else {
            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
            		  }
            	 }
                 	 String OwnerDetail =null;
                 	 
                 	String   poAOwnerListNew = commonBD.getOwnerDetails(regNumber,partyId,lang);
                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


                 
             	        sDTO.setPartyFirstName(OwnerRolePoA);
	          	        sDTO.setPartyLastName("");
	          	        i1++;
             	        
			  }
			  // will deed Authenticaed POA finish - Rahul 
			  else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
			     {
			    	 
			     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
			     	 String  RoleName= null;
			     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
			     	    sDTO.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(3)));
			     	    RoleName = sDTO.getRoleName();
			     	   System.out.println("Role Name " +RoleName); 
	                 	
			     	  // to check hindi and english role 
                  	 String HindiEnglishRole[] = RoleName.split(",");
                  	 String hindesc = HindiEnglishRole[0];
                  	 String EngDesc =HindiEnglishRole[1];
                  	 
                  	 
                  	 String PoaName = null;
                  	 if (EngDesc.contains("Authenticated")){ 
                		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    		   PoaName =" By(Authenticated PoA Holder)";
                 		  }
                 		  else {
                 			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
                 		  }}    
                  	 else {
                  		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  			 PoaName =" By(PoA Holder)";
                		  }
                		  else {
                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
                		  }
				}


			     	   
	                 	 System.out.println("POA NAMe is "+PoaName);
	           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
	           	      //  System.out.println("PoAOwnerlistdisplay"+poAOwnerList.get(i));
	        	         
	         	        String OwnerDetail =null;
	         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regNumber,partyId,lang);
	                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
	                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
	                 	
	          	        sDTO.setPartyFirstName(OwnerRolePoA);
	          	        sDTO.setPartyLastName("");
	          	        
			    }
			  else
				{
	               // Minor name , Guardian set - Rahul
					int age=subList.get(10)!=null?Integer.parseInt((String)subList.get(10)):0;
					String Mname="";
					if( (subList.get(12)!=null) && (!subList.get(12).toString().equalsIgnoreCase(""))&& (!subList.get(12).toString().equalsIgnoreCase("null")))
					{
					 Mname=(String)subList.get(12);
					}
					if(Mname ==null || "null".equalsIgnoreCase(Mname)){
						sDTO.setPartyFirstName(fName);
						sDTO.setPartyLastName(lName);
						
					}
					//commented for minor
					
					else {
						sDTO.setPartyFirstName(fName+" "+Mname+" ");
						sDTO.setPartyLastName((String)subList.get(2));
					}
	               if(age<RegCompCheckerConstant.MINOR_AGE_LIMIT){
						
	  				 String relations = commonBO.getRelationshipName((subList.get(14).toString()), lang);

	            	   
						String MinorName =sDTO.getPartyFirstName();
						if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
						{
						sDTO.setPartyFirstName("(Minor)"+MinorName);
						sDTO.setPartyLastName((String)subList.get(2)+" "+relations+" "+(String)subList.get(11)+" By "+(String)subList.get(13));
						}
						else
						{
							sDTO.setPartyFirstName("(अवयस्‍क)"+MinorName);
							sDTO.setPartyLastName((String)subList.get(2)+" "+relations+" "+(String)subList.get(11)+" द्वारा  "+(String)subList.get(13));
							
						}	
					}
					
				}
			  } // end check of individual
			// End owner ,POA details- Rahul
	 else{	  
			if((fName).equals(""))
			{
				if(subList.get(8)!=null &&subList.get(8).toString().equalsIgnoreCase("3"))
				{	
					if(subList.get(9)!=null &&!subList.get(9).toString().equalsIgnoreCase(""))
					{	
						sDTO.setPartyFirstName((String)subList.get(9));
						sDTO.setPartyLastName("");
					}
					else
					{
						sDTO.setPartyFirstName("--");
						sDTO.setPartyLastName("");
					}
				}
				else
				{
					if(subList.get(8)!=null &&subList.get(8).toString().equalsIgnoreCase("1"))
					{
						 if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
							    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
							    	)
			                		
			               {
								//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
								mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
								 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			            		 String RoleName = form.getExecutantClaimantName();
			                	 System.out.println("Role Name " +RoleName); 
			                	 String PoaName = null;
			                	// to check hindi and english role 
			                	 String HindiEnglishRole[] = RoleName.split(",");
			                	 String hindesc = HindiEnglishRole[0];
			                	 String EngDesc =HindiEnglishRole[1];
			                	
			                	 if (EngDesc.contains("Authenticated")){ 
			                		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                    		   PoaName =" By(Authenticated PoA Holder)";
			                 		  }
			                 		  else {
			                 			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
			                 		  }}    
			                	 else {
			                		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			                		  }
			                	 }
			                	 
			                    	 String OwnerDetail =null;
			                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(regNumber,partyId,lang);
			                      	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                      	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                	        sDTO.setPartyFirstName(OwnerRolePoA);
			                	       System.out.println("printed name is "+sDTO.getPartyFirstName());
			                	       sDTO.setPartyLastName("");
			                	      
			               }
						
						 else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
					     {
							// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
					     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					     	 String  RoleName= null;
					     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
					     	    sDTO.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(3)));
					     	    RoleName = sDTO.getRoleName();
					     	   System.out.println("Role Name " +RoleName); 
			                 	
					     	  // to check hindi and english role 
		                  	 String HindiEnglishRole[] = RoleName.split(",");
		                  	 String hindesc = HindiEnglishRole[0];
		                  	 String EngDesc =HindiEnglishRole[1];
		                  	 
		                  	 
		                  	 String PoaName = null;
		                  	 if (EngDesc.contains("Authenticated")){ 
		                		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
		                 		  }
		                 		  else {
		                 			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
		                 		  }}    
		                  	 else {
		                  		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                  			 PoaName =" By(PoA Holder)";
		                		  }
		                		  else {
		                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		                		  }
						}


					     	   
			                 	 System.out.println("POA NAMe is "+PoaName);
			           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
			           	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
			        	         
			         	        String OwnerDetail =null;
			         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regNumber,partyId,lang);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			          	        sDTO.setPartyFirstName(OwnerRolePoA);
			          	        sDTO.setPartyLastName("");
			          	        i1++;
					    }
						else{
							   sDTO.setPartyFirstName((String)subList.get(7));
							   sDTO.setPartyLastName("");
								}
					}
					
				}
			}
			/*else
			{
               // Minor name , Guardian set - Rahul
				int age=subList.get(10)!=null?Integer.parseInt((String)subList.get(10)):0;
				String Mname="";
				if( (subList.get(12)!=null) && (!subList.get(12).toString().equalsIgnoreCase(""))&& (!subList.get(12).toString().equalsIgnoreCase("null")))
				{
				 Mname=(String)subList.get(12);
				}
				if(Mname ==null || "null".equalsIgnoreCase(Mname)){
					sDTO.setPartyFirstName(fName);
					sDTO.setPartyLastName(lName);
				}
				//commented for minor
				sDTO.setPartyFirstName(fName);
				sDTO.setPartyLastName(lName);
				else {
					sDTO.setPartyFirstName(fName+" "+Mname);
					sDTO.setPartyLastName((String)subList.get(2));
				}
               if(age<RegCompCheckerConstant.MINOR_AGE_LIMIT){
					
					String MinorName =sDTO.getPartyFirstName();
					if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
					{
					sDTO.setPartyFirstName("(Minor) "+MinorName);
					sDTO.setPartyLastName((String)subList.get(2)+"  "+(String)subList.get(14)+" "+(String)subList.get(11)+" By "+(String)subList.get(13));
					}
					else
					{
						sDTO.setPartyFirstName("(अवयस्‍क) "+MinorName);
						sDTO.setPartyLastName((String)subList.get(2)+"  "+(String)subList.get(16)+" "+(String)subList.get(11)+" द्वारा "+(String)subList.get(13));
						
					}	
				}
				
			}*/
	 }
			
			String partyRole = (String)subList.get(3);
			if(partyRole.equals("0"))
			{
				sDTO.setRoleName(sealDAO.getPartyRole((String)subList.get(4)));
			}
			else
			{
				sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
			}
			sDTO.setPartyId((String)subList.get(4));
			mainList.add(sDTO);
		}
		return mainList;
	}
	
	/**
	 * 
	 * @param regNumber
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWitnessDetails(String regNumber,String lang) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList subList = new ArrayList();
		ArrayList list = sealDAO.getWitnessDetails(regNumber);
		
		for(int i= 0 ; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			SealsDTO sDTO = new SealsDTO();
			
			String fName = (String)subList.get(1);
			String lName = (String)subList.get(2);
			fName = fName == null?"":fName;
			lName = lName == null?"":lName;
			
			sDTO.setWitFirstName(fName);
			sDTO.setWitLastName(lName);
			if("en".equalsIgnoreCase(lang))
				sDTO.setRoleName("Witness");
			else
				sDTO.setRoleName("गवाह");
			
			sDTO.setWitTxnId((String)subList.get(3));
			mainList.add(sDTO);
		}
		return mainList;
	}
	
	public int insertPresentationSealDetails(ArrayList partyDetails,SealsDTO sDTO, String officeId, String userId, String[] presentParties,SealsForm sForm, String lang) throws Exception
	{
		String SrName = sealDAO.getSrDetails(userId);
		String desig = sealDAO.getDesignationDetails(userId);
		String prrsentationDetails[] = new String[7];
		prrsentationDetails[1] = sDTO.getRegNumber();
		prrsentationDetails[2] = sDTO.getSelectedSubId();
		prrsentationDetails[3] = SrName;
		prrsentationDetails[4] = desig;
		prrsentationDetails[6] =officeId;
		String getFirstTimeStamp = sealDAO.getCurrTimeStamp();
		boolean flag =  sealDAO.insertPresentationSealDetails(partyDetails,prrsentationDetails,presentParties,sForm);
		//String getTimeStampAfterSealInsertion = sealDAO.getCurrTimeStamp();
		ArrayList completePartyDetails =  sealDAO.getPartyDetailsForPrsentationSeal(presentParties,lang);
		//String getTimeStampAftergettingPartyDetails = sealDAO.getCurrTimeStamp();
		int result = applyPresentationSeal(completePartyDetails, sForm);
		/*String getTimeStampAfterSeal = sealDAO.getCurrTimeStamp();
		System.out.println("first"+getFirstTimeStamp);
		System.out.println("after insertion"+getTimeStampAfterSealInsertion);
		System.out.println("get complete data"+getTimeStampAftergettingPartyDetails);
		System.out.println("seal"+getTimeStampAfterSeal);
		long diff1 = getTimeStampdiff(getFirstTimeStamp, getTimeStampAfterSeal);
		System.out.println("time diff in minutes total"+diff1);
		long diff2 = getTimeStampdiff(getTimeStampAftergettingPartyDetails, getTimeStampAfterSeal);
		System.out.println("time diff in minutes seal "+diff2);*/
		System.out.println("hello");
		logger.debug("result presentation seal------>"+result);
		
		if(result==0)
		{	
			RegCompCheckerDAO dao=new RegCompCheckerDAO();
			Date createdDate = new Date();
			SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
			String cdate = sdfrmt.format(createdDate);
			boolean flag1=false;
			flag1=sealDAO.updateSealApplied(sDTO.getRegNumber(),"1","Y");
			if(flag1)
			{	
				flag1=sealDAO.updateSealFlag(sDTO.getRegNumber(), sDTO.getSelectedSubId());
				if(flag1)
				{	
					flag1=sealDAO.updateBookId(sDTO.getRegNumber());
					if(flag1)
					{
						flag1=dao.UpdateRegistrationCompletionStatus(sDTO.getRegNumber(), "36", cdate, userId);
					}
				}
			}
			if(flag1)
			{
				
			}
			else
			{
				logger.debug("Presentation Seal Status update Failed for:"+sDTO.getRegNumber());
				flag1=sealDAO.updateSealApplied(sDTO.getRegNumber(),"1","N");
				throw new Exception();
			}
			
		}
		else if(result==-100){
			return result;
		}
		else
		{
			logger.debug("Presentation Seal Failed for:"+sDTO.getRegNumber());
			throw new Exception();
		}
		return result;
	}
	
	public int insertExecution1Details(ArrayList partyDetails,SealsDTO sDTO, String officeId, String userId, String[] presentParties,SealsForm sForm,String lang) throws Exception
	{
		String SrName = sealDAO.getSrDetails(userId);
		String desig = sealDAO.getDesignationDetails(userId);
		String prrsentationDetails[] = new String[11];
		prrsentationDetails[1] = sDTO.getRegNumber();
		prrsentationDetails[2] = sDTO.getSelectedSubId();
		prrsentationDetails[3] = SrName;
		prrsentationDetails[4] = desig;
		prrsentationDetails[10] =officeId;
		prrsentationDetails[6] =sDTO.getTransType();
		prrsentationDetails[7] =sDTO.getConsAmt();
		prrsentationDetails[8] =sDTO.getAmntSr();
		prrsentationDetails[9] =sDTO.getAmntToBePaid();
		sForm.getSealDTO().setDeedName(sealDAO.getDeedType(sDTO.getRegNumber()));
		
		boolean flag =  sealDAO.insertExecutionSeal1Details(partyDetails, prrsentationDetails, presentParties,sForm);
		ArrayList completePartyDetails = sealDAO.getPartyDetailsForAdmissionSeal(presentParties,lang);
		int result = applySealContentAdmissionSeal(completePartyDetails,sForm,null);
		logger.debug("Result------>"+result);
		return result;
	}

	public int insertWitnessDetails(ArrayList witnessDetailsList,SealsDTO sDTO, String officeId, String userId, String[] presentWitness,SealsForm sForm, String lang) throws Exception{
	
		String SrName = sealDAO.getSrDetails(userId);
		String desig = sealDAO.getDesignationDetails(userId);
		String witnessDetails[] = new String[7];
		witnessDetails[1] = sDTO.getRegNumber();
		witnessDetails[2] = sDTO.getSelectedSubId();
		witnessDetails[3] = SrName;
		witnessDetails[4] = desig;
		witnessDetails[6] =officeId;
		
		int result = -1;
		boolean flag =  sealDAO.insertWitnessSealDetails(witnessDetailsList, witnessDetails, presentWitness);
		//if(flag)
		//{
			ArrayList completeWitnessList = sealDAO.getWitnessDetailsWitnessSeal(presentWitness,lang);
			result = applySealContentWitnessSeal(null, sForm, completeWitnessList);
			return result;
	}
	/**
	 * 
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public ArrayList getConsenterDetails(String regNumber,String lang) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList subList = new ArrayList();
		ArrayList list = sealDAO.getConsenterDetails(regNumber);
		
		for(int i= 0 ; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			SealsDTO sDTO = new SealsDTO();
			
			String fName = (String)subList.get(1);
			String lName = (String)subList.get(2);
			fName = fName == null?"":fName;
			lName = lName == null?"":lName;
			
			sDTO.setConsenterFirstName(fName);
			sDTO.setConsenterLastName(lName);
			if(lang.equalsIgnoreCase("en"))
				sDTO.setRoleName("Consenter");
			else
				sDTO.setRoleName("सहमतिकर्ता");
			
			sDTO.setConsenterTxnId((String)subList.get(3));
			mainList.add(sDTO);
		}
		return mainList;
	}
	/**
	 * 
	 * @param consenterList
	 * @param sDTO
	 * @param officeId
	 * @param userId
	 * @param presentConsenter
	 * @return
	 * @throws Exception
	 */
	public boolean insertConsenterDetails(ArrayList consenterList,SealsDTO sDTO, String officeId, String userId, String[] presentConsenter) throws Exception{
		
		String SrName = sealDAO.getSrDetails(userId);
		String desig = sealDAO.getDesignationDetails(userId);
		String consenterDetails[] = new String[7];
		consenterDetails[1] = sDTO.getRegNumber();
		consenterDetails[2] = sDTO.getSelectedSubId();
		consenterDetails[3] = SrName;
		consenterDetails[4] = desig;
		consenterDetails[6] = officeId;
		
		
		return sealDAO.insertConsenterSealDetails(consenterList, consenterDetails, presentConsenter);
	}
	
	/**
	 * 
	 * @param sealForm
	 * @param sDTO
	 * @param officeId
	 * @param userId
	 * @param presentParties
	 * @return
	 * @throws Exception
	 */
	public int insertThumbSealDetails(SealsForm sealForm,SealsDTO sDTO, String officeId, String userId, String[] presentParties,String lang) throws Exception
	{
		String SrName = sealDAO.getSrDetails(userId);
		String desig = sealDAO.getDesignationDetails(userId);
		
		String prrsentationDetails[] = new String[7];
		prrsentationDetails[1] = sDTO.getRegNumber();
		prrsentationDetails[2] = sDTO.getSelectedSubId();
		prrsentationDetails[3] = SrName;
		prrsentationDetails[4] = desig;
		prrsentationDetails[6] =officeId;
		ArrayList partyDetailsList = sealForm.getPresentationPartyDetails();
		ArrayList witnessList = sealForm.getWitnessList();
		ArrayList consenterList = sealForm.getConsenterList();
		boolean flag = sealDAO.insertThumbSealDetails(sealForm, prrsentationDetails, presentParties);
		ArrayList partyThumbList = new ArrayList();
		ArrayList witnessThumbList = new ArrayList();
		ArrayList consenterThumbList = new ArrayList();
		for(int j = 0; j < presentParties.length; j ++ )
		{
			for(int i = 0 ; i < partyDetailsList.size() ; i++)
			{
				SealsDTO sealDTO = (SealsDTO)partyDetailsList.get(i);
				String partyId = sealDTO.getPartyId();
				if(partyId.equals(presentParties[j]))
				{
					partyThumbList.add(partyId);
					break;
				}
			}
		}
		
		for(int j = 0; j < presentParties.length; j ++ )
		{
			for(int i = 0 ; i < witnessList.size() ; i++)
			{
				SealsDTO sealDTO = (SealsDTO)witnessList.get(i);
				String witId = sealDTO.getWitTxnId();
				if(witId.equals(presentParties[j]))
				{
					witnessThumbList.add(witId);
					break;
				}
			}
		}
		
		for(int j = 0; j < presentParties.length; j ++ )
		{
			for(int i = 0 ; i < consenterList.size() ; i++)
			{
				SealsDTO sealDTO = (SealsDTO)consenterList.get(i);
				String consId = sealDTO.getConsenterTxnId();
				if(consId.equals(presentParties[j]))
				{
					consenterThumbList.add(consId);
					break;
				}
			}
		}
		
	int result =  applyThumbImpressionSeal(partyThumbList, witnessThumbList, consenterThumbList, sDTO.getRegNumber(),SrName,desig,sealForm.getSealDTO().getOfficeName(),sealForm,lang);	
	logger.debug("result------>"+result);
	return result;
	}
	
	public void getDutyDeedDetails(SealsForm sealForm,String regNum,String lang) throws Exception{
		
		ArrayList getDutySealDetls = sealDAO.getDutyDeedDetails(regNum);
		SealsDTO dto = sealForm.getSealDTO();
		for(int i = 0 ; i < getDutySealDetls.size(); i++)
		{
			ArrayList subList = (ArrayList)getDutySealDetls.get(i);
			if(lang.equalsIgnoreCase("en"))
				dto.setDeedName((String)subList.get(0));
			else
				dto.setDeedName((String)subList.get(2));
			dto.setStampduty((String)subList.get(1));
		}
		sealForm.setSealDTO(dto);
	}
	
	public int insertAdjudicationSealDetails(ArrayList partyDetails,SealsDTO sDTO, String officeId, String userId, String[] presentParties, SealsForm sForm,String lang) throws Exception{
		
		String SrName = sealDAO.getSrDetails(userId);
		String desig = sealDAO.getDesignationDetails(userId);
		String presentationDetails[] = new String[10];
		presentationDetails[1] = sDTO.getRegNumber();
		presentationDetails[2] = sDTO.getSelectedSubId();
		presentationDetails[3] = SrName;
		presentationDetails[4] = desig;
		presentationDetails[9] =officeId;
		presentationDetails[6] =sDTO.getNameOfPakshkaar();
		presentationDetails[7] =sDTO.getOtherDetails();
		presentationDetails[8] =sDTO.getDeedName();
	
		boolean flag =  sealDAO.insertAdjudicationSealDetails(partyDetails, presentationDetails, presentParties,sDTO.getRegNumber(),sForm);
		int result = AdjudicationSeal(presentParties, sForm,SrName,officeId,userId,lang);
		logger.debug("result------>"+result);
		return result;
	}
	
public int insertPOASealDetails(ArrayList witnessList,SealsDTO sDTO, String officeId, String userId, String[] presentWitness, SealsForm sForm,String lang) throws Exception{
		
		String SrName = sealDAO.getSrDetails(userId);
		String desig = sealDAO.getDesignationDetails(userId);
		String witnessDetails[] = new String[9];
		witnessDetails[1] = sDTO.getRegNumber();
		witnessDetails[2] = sDTO.getSelectedSubId();
		witnessDetails[3] = SrName;
		witnessDetails[4] = desig;
		witnessDetails[8] =officeId;
		witnessDetails[6] =sDTO.getName();
		witnessDetails[7] =sDTO.getAddress();
		
	sealDAO.insertPOASealDetails(witnessList, witnessDetails, presentWitness,sForm);
	ArrayList completeWitnessList = sealDAO.getWitnessDetailsWitnessSeal(presentWitness,lang);
	int result = putPOASealWitness(sForm, SrName, userId,completeWitnessList);
	return result;
	}

public int insertPOASealVisitDetails(SealsDTO sDTO, String officeId, String userId, SealsForm sForm) throws Exception{
	
	String SrName = sealDAO.getSrDetails(userId);
	String desig = sealDAO.getDesignationDetails(userId);
	String poaVisitDetails[] = new String[10];
	poaVisitDetails[1] = sDTO.getRegNumber();
	poaVisitDetails[2] = sDTO.getSelectedSubId();
	poaVisitDetails[3] = SrName;
	poaVisitDetails[4] = desig;
	poaVisitDetails[9] =officeId;
	poaVisitDetails[6] =sDTO.getName();
	poaVisitDetails[7] =sDTO.getAddress();
	poaVisitDetails[8] = sDTO.getSerialNumber();
	
	boolean flag =  sealDAO.insertPOASealVisitDetails(poaVisitDetails,sForm);
	int result = putPOASeal(sForm, SrName,userId);
	return result;
}

public int insertPOASealCommDetails(SealsDTO sDTO, String officeId, String userId, SealsForm sForm) throws Exception{
	
	String SrName = sealDAO.getSrDetails(userId);
	String desig = sealDAO.getDesignationDetails(userId);
	String poaCommDetails[] = new String[11];
	poaCommDetails[1] = sDTO.getRegNumber();
	poaCommDetails[2] = sDTO.getSelectedSubId();
	poaCommDetails[3] = SrName;
	poaCommDetails[4] = desig;
	poaCommDetails[10] =officeId;
	poaCommDetails[6] =sDTO.getName();
	poaCommDetails[7] =sDTO.getAddress();
	poaCommDetails[8] = sDTO.getPoaName();
	poaCommDetails[9] = sDTO.getSerialNumber();
	
	 sealDAO.insertPOASealCommDetails(poaCommDetails,sForm);
	int result = putPOASeal(sForm, SrName,userId);
	return result;
	
}

public int insertPOASealDetails(SealsDTO sDTO, String officeId, String userId,SealsForm sForm) throws Exception{
	
	String SrName = sealDAO.getSrDetails(userId);
	String desig = sealDAO.getDesignationDetails(userId);
	String poaCommDetails[] = new String[8];
	poaCommDetails[1] = sDTO.getRegNumber();
	poaCommDetails[2] = sDTO.getSelectedSubId();
	poaCommDetails[3] = SrName;
	poaCommDetails[4] = desig;
	poaCommDetails[7] =officeId;
	poaCommDetails[6] =sDTO.getPoaNumber();
	
	sealDAO.insertPOASealDetails(poaCommDetails,sForm);
	int result = putPOASeal(sForm, SrName,userId);
	return result;
}

public int insertExecution7Details(ArrayList partyDetails,SealsDTO sDTO, String officeId, String userId, String[] presentParties,SealsForm sForm,String lang) throws Exception
{
	String SrName = sealDAO.getSrDetails(userId);
	String desig = sealDAO.getDesignationDetails(userId);
	String prrsentationDetails[] = new String[10];
	prrsentationDetails[1] = sDTO.getRegNumber();
	prrsentationDetails[2] = sDTO.getSelectedSubId();
	prrsentationDetails[3] = SrName;
	prrsentationDetails[4] = desig;
	prrsentationDetails[9] = officeId;
	prrsentationDetails[6] = sDTO.getName();
	prrsentationDetails[7] = sDTO.getOrderNumber();
	prrsentationDetails[8] = sDTO.getOrderDate();
	
	boolean flag =  sealDAO.insertExecutionSeal7Details(partyDetails, prrsentationDetails, presentParties,sForm);	
	ArrayList completePartyDetails = sealDAO.getPartyDetailsForAdmissionSeal(presentParties,lang);
	logger.debug("size of patyList ---- > "+completePartyDetails.size());
	int result = applySealContentAdmissionSeal(completePartyDetails, sForm, null);  // witnessList empty -- for admission seal
	return result;
}
	
public boolean insertReportCommissionerDetails(ArrayList partyDetails,SealsDTO sDTO, String officeId, String userId, String[] presentParties,String sealType) throws Exception{
	
	String SrName = sealDAO.getSrDetails(userId);
	String desig = sealDAO.getDesignationDetails(userId);
	String presentationDetails[] = new String[8];
	presentationDetails[1] = sDTO.getRegNumber();
	presentationDetails[2] = sDTO.getSelectedSubId();
	presentationDetails[3] = SrName;
	presentationDetails[4] = desig;
	presentationDetails[7] = officeId;
	presentationDetails[6] = sDTO.getName();
	
	return sealDAO.insertReportCommissionerDetails(partyDetails, presentationDetails, presentParties,sealType);
}

public int insertExecution6Details(SealsForm sForm, String officeId, String userId) throws Exception
{
	//String sysDate = sealDAO.getSystemDate();
	String sysDate = sealDAO.getSealDatePartyMax(sForm.getSealDTO().getRegNumber());

	sForm.setSealsContent("इस बात पर अपना समाधान कर लेने के बाद कि दस्तावेज श्री "+sForm.getSealDTO().getName()+","+sForm.getSealDTO().getDesignation()+"(नाम व पद) ने अपनी पदीय हैसियत से निष्पादित किया था," +
			" उन्हें स्वयं उपस्थित होने और हस्ताक्षर करने से मुक्त किया जाता है तथा दस्तावेज पंजीयन के लिए प्रतिगृहीत किया जाता है। तारीख "+sysDate);
	insertCommonSealDetails(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
	int result = applyAdmissionSealGovt(sForm, userId);
	return result;
}

public void getStampDutyDetails(SealsForm sForm,String regNumber) throws Exception{

	ArrayList list = sealDAO.getStampDutyDetails(regNumber);
	ArrayList subList = null;
	SealsDTO sDTO = sForm.getSealDTO();
	for(int i = 0 ; i < list.size(); i++)
	{
		subList = (ArrayList)list.get(i);
		
		sDTO.setStampduty((String)subList.get(0));
		sDTO.setGramDuty((String)subList.get(1));
		sDTO.setNagarNigamDuty((String)subList.get(2));
		sDTO.setUpkarDuty((String)subList.get(3));
		sDTO.setTotalDuty((String)subList.get(4));
	}
	
}

public int insertExecutionSealWillDetails(ArrayList partyDetails,SealsDTO sDTO, String officeId, String userId,ArrayList witnessList, String[] presentWitness,SealsForm sForm,String lang) throws Exception{
	
	String SrName = sealDAO.getSrDetails(userId);
	String desig = sealDAO.getDesignationDetails(userId);
	String presenterName = sealDAO.getNameOfPresentParty(sDTO.getRegNumber());
	String presentationDetails[] = new String[8];
	presentationDetails[1] = sDTO.getRegNumber();
	presentationDetails[2] = sDTO.getSelectedSubId();
	presentationDetails[3] = SrName;
	presentationDetails[4] = desig;
	presentationDetails[7] = officeId;
	presentationDetails[6] = sDTO.getMinuteBookPage();
	
	sealDAO.insertExecutionSealWillDetails(partyDetails, presentationDetails, witnessList, presentWitness,presenterName,sForm);
	ArrayList completePartyDetails = sealDAO.getWitnessDetailsWitnessSeal(presentWitness,lang);
	logger.debug("size of patyList ---- > "+completePartyDetails.size());
	int result = applySealContentAdmissionSealWill(completePartyDetails, sForm, null);  // witnessList empty -- for admission seal
	return result;
}

public void getRegFeeDetails(String regNumber, SealsForm sForm) throws Exception{
	
	ArrayList list = sealDAO.getRegFeeDetails(regNumber);
	ArrayList subList = null;
	SealsDTO sDTO = sForm.getSealDTO();
	
	for(int i = 0 ; i < list.size() ; i++)
	{
		subList = (ArrayList)list.get(i);
		
		if(subList.get(0).toString().contains("-")){
			sDTO.setTotalDuty("0");
			
		}
		else{
		sDTO.setTotalDuty((String)subList.get(0));
		}
		
		if(subList.get(0).toString().contains("-")){
			
			sDTO.setRegFee("0");
		}
		else{
		sDTO.setRegFee((String)subList.get(1));
		}
		
		Double totalDuty = Double.parseDouble((String)subList.get(0));
		
		
		//Double regFee = Double.parseDouble((String)subList.get(1));
		
		Double regFee = Double.parseDouble(sDTO.getRegFee());
		
		Double total = regFee;
		
		sDTO.setTotal(String.valueOf(total));
		
	}
}

public int insertRegistrationSealDetails(SealsForm sForm,String userId,String officeId,HttpServletResponse response)
{
	try
	{
		getStampDutyDetails(sForm, sForm.getSealDTO().getRegNumber());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	int result = putRegistrationSeal(sForm,response);
	logger.debug("result---->"+result);
	if(result == 0)
	{
		//sForm.setCorrection("N");
		insertCommonSealDetails(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
	/*RegCompCheckerBD bd= new RegCompCheckerBD();
		RegCompCheckerForm regForm=new RegCompCheckerForm();
		regForm.setHeaderImg(sForm.getHeaderImage());
		regForm.getRegDTO().setRegInitNumber(sForm.getSealDTO().getRegNumber());
		regForm.getRegDTO().setRegCompleteId(sForm.getSealDTO().getRegCompNumber());
		regForm.setUserId(userId);
		try {
			
			bd.generateFinalCertificate(regForm, userId, officeId, sForm.getLangauge());
			} catch (Exception e) {
			logger.debug("error in generating document");
			logger.debug(e.getStackTrace());*/
//	}//
		
		//return true;
	}
		
	return result;
}

public int insertRefusalSeal(SealsForm sForm,String userId,String officeId,HttpServletResponse response)
{
	int result = putRefusalSeal(sForm,response);
	logger.debug("result---->"+result);
	if(result == 0)
	{
		insertCommonSealDetails(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
		RegCompCheckerBD bd= new RegCompCheckerBD();
		RegCompCheckerForm regForm=new RegCompCheckerForm();
		regForm.setHeaderImg(sForm.getHeaderImage());
		regForm.getRegDTO().setRegInitNumber(sForm.getSealDTO().getRegNumber());
		regForm.getRegDTO().setRegCompleteId(sForm.getSealDTO().getRegCompNumber());
		regForm.setUserId(userId);
		try {
			
			bd.generateFinalCertificateRefusal(regForm, userId, officeId, sForm.getLangauge());
			} catch (Exception e) {
			logger.debug("error in generating document");
			logger.debug(e.getStackTrace());
	}
		
	}
	return result;
}
public int insertCorrectionSealDetails(SealsForm sForm, String userId)
{
	String srName ="";
	try
	{
		srName = sealDAO.getSrDetails(userId);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	//int result = putCorrectionNirsanSeal(sForm,srName,userId);
	int result=0;
	logger.debug("result---->"+result);
	if(result == 0)
	{
		sForm.setCorrection("Y");
		insertCommonSealDetails(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
		updateSerialNumber(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId(),sForm.getSealDTO().getSerialNumber());
		//return true;
	}
	return result;
}

public int insertRule35Seal1(SealsForm sForm, String userId)
{
	String srName ="";
	try
	{
		srName = sealDAO.getSrDetails(userId);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	String sealContent = "इस दस्तावेज में पक्षकार ने सम्पत्ति का बाजार मूल्य  "+sForm.getSealDTO().getMarketValue()+" रूपये अंकित किया है, जबकि वह सम्पत्ति का सही " +
			" बाजार मूल्य "+sForm.getSealDTO().getCorrectMarketValue()+" रूपये स्वीकार करता है । फलस्वरूप स्टाम्प शुल्क के अंतर की राशि रूपये "+sForm.getSealDTO().getStampduty()+" का स्टाम्प लगाने को पक्षकार सहमत है ।" +
			" अतः भारतीय स्टाम्प अधिनियम, 1899 की धारा 35 के परन्तुक (च) के अनुसार पक्षकार द्वारा उक्त कम रकम के स्टाम्पों के बराबर स्टाम्प इस दस्तावेज के साथ जोड़े जाते हैं ।";
	sForm.setSealsContent(sealContent);
	int result = putRuleNumber35Seal1(sForm, srName,userId);
	logger.debug("result---->"+result);
	if(result == 0)
	{
		insertCommonSealDetails(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
		//return true;
	}
	return result;
}

public int insertRule35Seal2(SealsForm sForm, String userId)
{
	String srName ="";
	try
	{
		srName = sealDAO.getSrDetails(userId);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	String sealContent = "इस दस्तावेज में निष्पादक ने स्टाम्प शुल्क की राशि "+sForm.getSealDTO().getStampduty()+" रूपये चुकाई है, " +
			"जबकि भारतीय स्टाम्प अधिनियम, 1899 की अनुसूची-1-क, के अनुच्छेद "+sForm.getSealDTO().getArticle()+" के अनुसार" +
			" इस पर "+sForm.getSealDTO().getCorrectStampDuty()+" रूपये के स्टाम्प प्रभारित किये जाना थे । पक्षकार शेष राशि के स्टाम्प लगाने को तैयार है । अतः धारा 35 के परन्तुक (च) " +
			"के प्रावधानों के अनुसार पक्षकार द्वारा उक्त कम रकम के स्टाम्पों के बराबर स्टाम्प इस दस्तावेज के साथ जोड़े जाते हैं ।";
	sForm.setSealsContent(sealContent);
	int result=-1;
	boolean flag=sealDAO.updateBookIdRule(sForm.getSealDTO().getRegNumber(), sForm.getSealDTO().getBookId());
	if(flag)
	{	
		result = putRuleNumber35Seal1(sForm, srName,userId);
		logger.debug("result---->"+result);
		if(result == 0)
		{
			insertCommonSealDetails(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
		//return true;
		}
	}
	return result;
}

public int applyPresentationSeal(ArrayList presentPartiesList,SealsForm sForm){
	logger.debug("apply applyPresentationSeal seal content");
	int result = -1;
	try{
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		String hindiFont = pr.getValue("dms_hindi_font_path");
		String englishFont = pr.getValue("dms_english_font_path");
		//SealsBD sealBD=new SealsBD();
		boolean val=validateFont(hindiFont,englishFont);
		if(!val){
			throw new Exception();
		}
		BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
		PresentationSeal sealContent = new PresentationSeal();   
	//by vinay
		sealContent.setAuthName(sForm.getSealDTO().getUserName());
		sealContent.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
		sealContent.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
		sealContent.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
// end
	ArrayList<Presenter> presenterList = new ArrayList();
		//ArrayList<Claimant> claimantList = new ArrayList();   
	     
	// For PoA , Owner details Add - Rahul 
	     SealsDTO sealsDTO = new SealsDTO();
	     RegCommonBO commonBO = new RegCommonBO();
	     RegCommonDAO commonDAO = new RegCommonDAO();
	     RegCommonDTO mapDto =new RegCommonDTO();
	     RegCommonForm form = new RegCommonForm();
	   
	     String RegNum = sForm.getSealDTO().getRegNumber();
	     System.out.println("Reg no in Seal is"+RegNum);
	     String language="";
	     language=sForm.getLangauge();
	     System.out.println("Language set to from seal form "+language);
	    // if("English".equalsIgnoreCase(sForm.getLangauge()))
	     
	     if("English".equalsIgnoreCase(language))
	      { 
	    	 language="en";
	      }
	      else
	      {
	    	  language="hi";
	      }
	     String partyId = "";
	     System.out.println("Language set to for seal  "+language);
	    /// ArrayList poAOwnerList = commonDAO.getOwnerPoaDescDisplay(language, RegNum);
	     ArrayList poAOwnerListForOrg= new ArrayList();
	     RegCommonBD commonBD = new RegCommonBD();
	     String CommonDeedFlag = "";
	     CommonDeedFlag=sealDAO.getCommonFlowFlag(RegNum);
		 
	     int i1=0;
	 	String OwnerRolePoA="";
	   // end PoA   
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    System.out.println("atish-------------"+pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
	    String relations = "";
     for(int i = 0 ; i < presentPartiesList.size() ; i++ )
     {
    	 ArrayList subList  = (ArrayList)presentPartiesList.get(i);
    	 Presenter  presenterInfo1 = new Presenter();
    	 
    	 if(subList.get(14)!=null)
			{
				partyId = subList.get(14).toString();
			}	
    	 
    	 if(subList.get(10)!=null){
       		 
      		  relations = commonBO.getRelationshipName((subList.get(10).toString()), language);
      	 }
    	 
    	 String fname = (String)subList.get(0);
    	 String mName = (String)subList.get(1);
    	 fname = fname==null?"":fname;
    	 mName = mName == null?"":mName;
    	  // for POA , Owner desc - Rahul
    	 int roleId =Integer.parseInt((String)subList.get(18));
	   	  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
	      	
		     mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
		     int ExecutantClaimantfromDB=0;
		     if (subList.get(19)!=null && !subList.get(19).toString().equalsIgnoreCase(""))
		   	  {
		        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(19));
		   	  }	
		     //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(19));
		    	     
		   // end of POA   Rahul 
		    	 if(fname == "")
		    	 {
		    		 if(subList.get(11)!=null&&subList.get(11).toString().equalsIgnoreCase("3"))
		    		 {
    			 presenterInfo1.setType("Person");
    			 if(subList.get(12)!=null&&!subList.get(11).toString().equalsIgnoreCase(""))
        		 {
    			 presenterInfo1.setName((String)subList.get(12));
        		 }
    			 else
    			 {
    				 presenterInfo1.setName("--");	 
    			 }
    			 presenterInfo1.setFather_husbandName("--");
    		 }
    		 else
    		 {	 
    			 if(subList.get(11)!=null&&subList.get(11).toString().equalsIgnoreCase("1"))
    			 {
    				 presenterInfo1.setType("Organization");	
    				 presenterInfo1.setName((String)subList.get(4));
    		 // for organisation POA holder- Rahul
    				 if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
					  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
				    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4
				    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
				    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
               		
              {
					//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(language, RegNum);
					mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
					 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
					form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
           		 String RoleName = form.getExecutantClaimantName();
               	 System.out.println("Role Name " +RoleName); 
               	 String PoaName = null;
               	// to check hindi and english role 
               	 String HindiEnglishRole[] = RoleName.split(",");
               	 String hindesc = HindiEnglishRole[0];
               	 String EngDesc =HindiEnglishRole[1];
               	
               	 if (EngDesc.contains("Authenticated")){ 
            		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                		   PoaName =" By(Authenticated PoA Holder)";
             		  }
             		  else {
             			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
             		  }}    
               	 else {
               		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
               			 PoaName =" By(PoA Holder)";
            		  }
            		  else {
            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
            		  }
               	 }
               	 
                   	 String OwnerDetail =null;
                   /*	 if(i1<poAOwnerListForOrg.size())
                 	 {
                   	 ArrayList n = (ArrayList) poAOwnerListForOrg.get(i1);
               	        mapDto.setOwnerPartyDescForOrg(n.get(0).toString());
               	     
               	        OwnerDetail =mapDto.getOwnerPartyDescForOrg();
               	     System.out.println("Owner result from DB is "+OwnerDetail);
               	       
               	       String parts[] = OwnerDetail.split(",");
               	       String Owner = parts[0];
               	       String PoADesc =parts[1];
               	     
               	        OwnerRolePoA=Owner+PoaName+PoADesc;
               	        System.out.println("owner Role to display "+ OwnerRolePoA);
                 	 }*/
                   	String   poAOwnerListNew = commonBD.getOwnerDetails(RegNum,partyId,language);
					 String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
					 OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


					 
					  
                   	 
               	     presenterInfo1.setFather_husbandName(OwnerRolePoA);
               	     i1++;
               	     
              }
    				 else if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ){
    					 presenterInfo1.setType("Organization");
    			    	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
    			    	// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(language, RegNum);
    			    	 
    			    	 String RoleName= null;
    			    	 //RoleName= sealDAO.getPartyRoleDetailsLangDesc((String)subList.get(18));
    			    	  RoleName = commonBO.getRoleNameForPOA((String) subList.get(18));
    			    	 System.out.println("Role Name " +RoleName); 
    			    	 // to check hindi and english role 
                    	 String HindiEnglishRole[] = RoleName.split(",");
                    	 String hindesc = HindiEnglishRole[0];
                    	 String EngDesc =HindiEnglishRole[1];
                    	 
                    	 
                    	 String PoaName = null;
                    	 if (EngDesc.contains("Authenticated")){ 
                    		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                      		   PoaName =" By(Authenticated PoA Holder) ";
                   		  }
                   		  else {
                   			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
                   		  }}    
                    	 else {
                    		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    			 PoaName =" By(PoA Holder)";
                    		  }
                    		  else {
                    			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
                    		  }
    			}


    	             	
    	             	 System.out.println("POA NAMe is "+PoaName);
    	       	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
    	       	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
    	    	         
    	     	        String OwnerDetail =null;
    	     	 /*      if(i1<poAOwnerListForOrg.size())
    	     	       {
    	     	    	   ArrayList m = (ArrayList) poAOwnerListForOrg.get(i1);
    	     	    	   mapDto.setOwnerPartyDescForOrg(m.get(0).toString());
    	      	     
    	     	    	   OwnerDetail =mapDto.getOwnerPartyDescForOrg();
    	     	    	   System.out.println("Owner result from DB is "+OwnerDetail);
    	      	       
    	     	    	   String parts[] = OwnerDetail.split(",");
    	     	    	   String Owner = parts[0];
    	     	    	   String PoADesc =parts[1];
    	      	     
    	     	    	   OwnerRolePoA=Owner+PoaName+PoADesc;
    	     	    	   System.out.println("owner Role to display "+ OwnerRolePoA);
    	     	       }*/
    	     	       String   poAOwnerListNew = commonBD.getOwnerDetails(RegNum,partyId,language);
						 String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
						 OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


						 
						  
    	     	        
    	     	    	   presenterInfo1.setFather_husbandName(OwnerRolePoA);
    	     	    	   i1++;
    			    	 
    				 }
    		 
               	        else {
               	        	presenterInfo1.setFather_husbandName((String)subList.get(3));
               	        }
    		 }
    		 }
    	 }
		    	 // For Will Deed , Authenticaed- By Common Flow - Rahul
		    	 else if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)&& ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6){
		    		 presenterInfo1.setType("Person");
			    	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(19));
					 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
					form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
            		 String RoleName = form.getExecutantClaimantName();
                	 System.out.println("Role Name " +RoleName); 
                	// to check hindi and english role 
                	 String HindiEnglishRole[] = RoleName.split(",");
                	 String hindesc = HindiEnglishRole[0];
                	 String EngDesc =HindiEnglishRole[1];
                	 
                	 
                	 String PoaName = null;
                	 if (EngDesc.contains("Authenticated")){ 
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  		   PoaName =" By(Authenticated PoA Holder)";
               		  }
               		  else {
               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
               		  }}    
                	 else {
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                			 PoaName =" By(PoA Holder)";
                		  }
                		  else {
                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
                		  }
                	 }

                		 System.out.println("POA NAMe is "+PoaName);
                    	 System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
                    	 String OwnerDetail =null;
                    	/* if(i1<poAOwnerList.size())
                  	      {
                    		 ArrayList m = (ArrayList) poAOwnerList.get(i1);
                	        mapDto.setOwnerPartyDesc(m.get(0).toString());
                	     
                	        OwnerDetail =mapDto.getOwnerPartyDesc();
                	        System.out.println("Owner result from DB is "+OwnerDetail);
                	       
                	        String parts[] = OwnerDetail.split(",");
                	        String Owner = parts[0];
                	        String PoADesc =parts[1];
                	     
                	       OwnerRolePoA=Owner+PoaName+PoADesc;
                	        System.out.println("owner Role to display "+ OwnerRolePoA);
                  	      }*/
                    	 
                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(RegNum,partyId,language);
						 String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
						 OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


						 
						  
                	        presenterInfo1.setName(OwnerRolePoA);
                	       presenterInfo1.setFather_husbandName(relations+" "+(String)subList.get(5));
                	        logger.debug("POA  Name "+presenterInfo1.getName());
         	      	       logger.debug("POA Father Guardian  Name "+presenterInfo1.getFather_husbandName());
         	      	       i1++;
                	        
					
		    		 
		    	 }
		    	 // POA , Owner - Start Rahul
		    	 else if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ){
		    		 presenterInfo1.setType("Person");
			    	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
			    	 String RoleName= null;
			    	 //RoleName= sealDAO.getPartyRoleDetailsLangDesc((String)subList.get(18));
			    	 RoleName = commonBO.getRoleNameForPOA((String) subList.get(18));
			    	 System.out.println("Role Name " +RoleName); 
			    	 // to check hindi and english role 
                	 String HindiEnglishRole[] = RoleName.split(",");
                	 String hindesc = HindiEnglishRole[0];
                	 String EngDesc =HindiEnglishRole[1];
                	 
                	 
                	 String PoaName = null;
                	 if (EngDesc.contains("Authenticated")){ 
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  		   PoaName =" By(Authenticated PoA Holder)";
               		  }
               		  else {
               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
               		  }}    
                	 else {
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                			 PoaName =" By(PoA Holder)";
                		  }
                		  else {
                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
                		  }
			}


	             	
	             	 System.out.println("POA NAMe is "+PoaName);
	       	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
	       	       // System.out.println("PoAOwnerlistdisplay"+poAOwnerList.get(i));
	    	         
	     	        String OwnerDetail =null;
	     	    /*   if(i1<poAOwnerList.size())
	           	      {    
	     	    	   	ArrayList m = (ArrayList) poAOwnerList.get(i1);
	     	    	   	mapDto.setOwnerPartyDesc(m.get(0).toString());
	      	     
	     	    	   	OwnerDetail =mapDto.getOwnerPartyDesc();
	     	    	   	System.out.println("Owner result from DB is "+OwnerDetail);
	      	       
	     	    	   	String parts[] = OwnerDetail.split(",");
	     	    	   	String Owner = parts[0];
	     	    	   	String PoADesc =parts[1];
	      	     
	     	    	   	OwnerRolePoA=Owner+PoaName+PoADesc;
	     	    	   	System.out.println("owner Role to display "+ OwnerRolePoA);
	           	      }*/
	     	        
	     	       String   poAOwnerListNew = commonBD.getOwnerDetails(RegNum,partyId,language);
					 String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
					 OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


					 
					  
	     	    	   	presenterInfo1.setName(OwnerRolePoA);
	      	      //  presenterInfo1.setName("Vijay");
	     	    	   	presenterInfo1.setFather_husbandName(relations+" "+(String)subList.get(5));
	      	       //  presenterInfo1.setFather_husbandName("S/0 Deenanath Chauhan");
	     	    	   		logger.debug("POA  Name "+presenterInfo1.getName());
	      	        		logger.debug("POA Father Guardian  Name "+presenterInfo1.getFather_husbandName());
	      	       			i1++;
			    	 
			     }  
		    	 
		    	 // POA Owner End RAhul
    	 else
    	 { 
    		 //Modifed Presentation seal Add Guardian name in Minor - Rahul
    		 presenterInfo1.setType("Person");
    		 int age=(subList.get(15)!=null && !subList.get(15).toString().equalsIgnoreCase(""))?Integer.parseInt((String)subList.get(15)):0;
    		 logger.debug("age of party-"+age);
    		  	 
    	 presenterInfo1.setFather_husbandName(relations+" "+(String)subList.get(5));
    		 if(mName == "")
    			 presenterInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
    		 else
    			 presenterInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(1))+" "+((String)subList.get(2)));
    		 //Add minor name in Guardian
    		 if(age<RegCompCheckerConstant.MINOR_AGE_LIMIT){
    			 String MinorName = presenterInfo1.getName();
    			 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    				 presenterInfo1.setName("(Minor)"+MinorName);
    				 presenterInfo1.setFather_husbandName(relations+" "+(String)subList.get(5)+" By "+(String)subList.get(17));
    			 }
    			 else
    			 {
    				 presenterInfo1.setName("(अवयस्‍क)"+MinorName);
    				 presenterInfo1.setFather_husbandName(relations+" "+(String)subList.get(5)+" द्वारा  "+(String)subList.get(17));
    			 }
    			 logger.debug("Father Name --"+presenterInfo1.getFather_husbandName());
    			 
    			 
    			 logger.debug("Party Minor Name----"+presenterInfo1.getName());
    		 }
    			 
    	 }
		      // End of Minor , Guardian - Rahul 
         presenterInfo1.setPhotoLocation((String)subList.get(6));
         presenterInfo1.setSignatureLocation((String)subList.get(7));
         presenterInfo1.setThumbImpressionLocation((String)subList.get(8));
        logger.debug((String)subList.get(0)+"~"+(String)subList.get(1)+"~"+(String)subList.get(2)+"~"+(String)subList.get(3)+"~"+(String)subList.get(4)+"~"+(String)subList.get(5)+"~"+(String)subList.get(6)+"~"+(String)subList.get(7)+"~"+(String)subList.get(8)+"~");
        logger.debug("Father Name set to "+presenterInfo1.getFather_husbandName());
         presenterList.add(presenterInfo1);
     }
    
     sealContent.setPresenterList(presenterList);
    // sealContent.setClaimantList(claimantList);
     sealContent.setSeal(sForm.getSealsContent());
     
     logger.debug("apply seal content");
     String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
    
     // creating folder
     	File output;
		output = new File(outputPath.toString());
		
		if (output.exists() == false) {
			logger.debug("Parent not found creating directory....");
			output.mkdirs();
		}
		String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
		File output2;
		output2 = new File(downloadPath.toString());
		
		if (output2.exists() == false) {
			logger.debug("Parent not found creating directory....");
			output2.mkdirs();
		}
		// end creating folder
		logger.debug("header---->"+sForm.getHeaderImage());
		logger.debug("presenter list"+presenterList);
		logger.debug("download deed path----->"+SealsConstant.DOWNLOAD_DEED_PATH);
		logger.debug("outputPath----->"+outputPath);
	//	result = burnObj.putPresentationSeal(connDetails, metaDataInfo,downloadPath,SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(), sealContent, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC_PRESENTATION,SealsConstant.NAME_OF_SEALS_DOC,  SealsConstant.DMS_FOLDER,sForm.getLangauge());
		System.out.println("applying presentation seal ...................");
		Presenter AN =  sealContent.getPresenterList().get(0);
		System.out.println("father name"+ AN.getFather_husbandName());
		
		if (null==metaDataInfo.getUniqueNo()) {
			throw new Exception();
		}
		if (metaDataInfo.getUniqueNo().equals("")) {
			throw new Exception();
		}
		result = burnObj.putPresentationSeal(connDetails, metaDataInfo,downloadPath,SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(), sealContent, sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC,  SealsConstant.DMS_FOLDER,sForm.getLangauge());

		logger.debug("apply seal content");
		File deleteFileDownlaod=new File(downloadPath);
		File deleteFileOutPut=new File(outputPath);
		deleteFileDownlaod.delete();
		deleteFileOutPut.delete();
		
	}
	catch(Exception e)
	{
		logger.debug(e.getMessage(),e);
		
	}
	return result;
}


public int applySealContentAdmissionSeal(ArrayList presentPartiesList,SealsForm sForm,ArrayList witnessPresentList){
	logger.debug("apply seal content");
	 int result = -1;
	try
	{
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setType(downloadSealType);
	
	    String hindiFont = pr.getValue("dms_hindi_font_path");
		String englishFont = pr.getValue("dms_english_font_path");
		//SealsBD sealBD=new SealsBD();
		boolean val=validateFont(hindiFont,englishFont);
		if(!val){
			throw new Exception();
		}
		BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		 AdmissionSeal admissionSeal = new AdmissionSeal();
		//by vinay
		 admissionSeal.setAuthName(sForm.getSealDTO().getUserName());
		 admissionSeal.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
		 admissionSeal.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
		 admissionSeal.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
	// end
		 // POA , Owner Display - Rahul
		 
	     RegCommonBO commonBO = new RegCommonBO();
	     RegCommonDAO commonDAO = new RegCommonDAO();
	     RegCommonDTO mapDto =new RegCommonDTO();
	     RegCommonForm form = new RegCommonForm();
	   
	     String RegNum = sForm.getSealDTO().getRegNumber();
	     System.out.println("Reg no in Seal is"+RegNum);
	     String language = sForm.getLangauge();
	     System.out.println("Language set to " +language );
	     
	     String partyId = "";
	     if("English".equalsIgnoreCase(language))
	      { 
	    	 language="en";
	      }
	      else
	      {
	    	  language="hi";
	      }
	     //ArrayList poAOwnerList = commonDAO.getOwnerPoaDescDisplay(language, RegNum);
	     ArrayList poAOwnerListForOrg = new ArrayList();
	     int i1=0;
	 		String OwnerRolePoA="";
	     RegCommonBD commonBD = new RegCommonBD();
	     
	     String relations = "";
	   
	     // For Will Deed - Authenticaed POA - RAHUl
	     String CommonDeedFlag = "";
	     CommonDeedFlag=sealDAO.getCommonFlowFlag(RegNum);
		 
		 ArrayList<Executant> executantList = new ArrayList();
	        
	        if(presentPartiesList != null)
	        {
	        	for(int i = 0 ; i < presentPartiesList.size() ; i++ )
		        {
		       	 ArrayList subList  = (ArrayList)presentPartiesList.get(i);
		       	 if(subList.get(14)!=null){
		       		partyId = subList.get(14).toString();
		       		 
		       	 }
		       	 
		       	 if(subList.get(10)!=null){
		       		 
		       		  relations = commonBO.getRelationshipName((subList.get(10).toString()), language);
		       	 }
		       	 
		       	Executant executantInfo1 = new Executant();
		       	 String fname = (String)subList.get(0);
		       	 String mName = (String)subList.get(1);
		       	 fname = fname==null?"":fname;
		       	 mName = mName == null?"":mName;
		      // for POA , Owner Desc- Rahul
		        	int roleId =Integer.parseInt((String)subList.get(18));
			   	   String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
			      	
				     mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				     int ExecutantClaimantfromDB=0;
				     if (subList.get(19)!=null && !subList.get(19).toString().equalsIgnoreCase(""))
				   	  {
				        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(19));
				   	  }	
		       	 if(fname == "")
		       	 {
		       		if(subList.get(11)!=null &&subList.get(11).toString().equalsIgnoreCase("3"))
		       		{
		       			executantInfo1.setType("Person");
		       			executantInfo1.setFather_representedBy("--");
		       			String addrs = (String)subList.get(13);
				       	if(addrs!=null)
				       	{	
				       	addrs = addrs.replace(RegInitConstant.SPLIT_CONSTANT,",");
				       	}
				      if("English".equalsIgnoreCase(sForm.getLangauge()))
				      { 
				       	executantInfo1.setAddress("Address: "+addrs);
				      }
				      else
				      {
				    	  executantInfo1.setAddress("पता: "+addrs); 
				      }
		       			if(subList.get(12)!=null &&!subList.get(12).toString().equalsIgnoreCase(""))
		       			{
		       				executantInfo1.setName((String)subList.get(12));
		       			}
		       			else
		       			{
		       				executantInfo1.setName("--");
		       			}
		       		}
		       		else
		       		{	
		       			if(subList.get(11)!=null&&subList.get(11).toString().equalsIgnoreCase("1"))
		       			{	
		       		   executantInfo1.setType("Organization");
		       		    executantInfo1.setName((String)subList.get(4));
		       		   // for organisation POA holder
		       		 if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
							  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
						    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
						    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
		               		
		              {
							//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(language, RegNum);
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
							 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
							form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
		           		 String RoleName = form.getExecutantClaimantName();
		               	 System.out.println("Role Name " +RoleName); 
		               	 String PoaName = null;
		               	// to check hindi and english role 
		               	 String HindiEnglishRole[] = RoleName.split(",");
		               	 String hindesc = HindiEnglishRole[0];
		               	 String EngDesc =HindiEnglishRole[1];
		               	
		               	 if (EngDesc.contains("Authenticated")){ 
		            		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                		   PoaName =" By(Authenticated PoA Holder)";
		             		  }
		             		  else {
		             			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
		             		  }}    
		               	 else {
		               		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		               			 PoaName =" By(PoA Holder)";
		             		  }
		             		  else {
		             			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		             		  }
		               	 }
		               	 
		               	 
                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(sForm.getSealDTO().getRegNumber(),partyId,language);
                       	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
                       	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
                	       // sDTO.setPartyFirstName(OwnerRolePoA);


                    	 
		               	      executantInfo1.setFather_representedBy(OwnerRolePoA);
		               	     
		              }
		               	   
		       		 // for organisation  POA - RAhul
		       		 else if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ){
		       			 executantInfo1.setType("Organization");
			       		    executantInfo1.setName((String)subList.get(4));
			       		 //poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(language, RegNum);
				       		 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					    	 String RoleName= null;
					    	// RoleName= sealDAO.getPartyRoleDetailsLangDesc((String)subList.get(18));
					    	  RoleName = commonBO.getRoleNameForPOA((String) subList.get(18));
						    	 System.out.println("Role Name " +RoleName); 
						    	 // to check hindi and english role 
			                	 String HindiEnglishRole[] = RoleName.split(",");
			                	 String hindesc = HindiEnglishRole[0];
			                	 String EngDesc =HindiEnglishRole[1];
			                	 
			                	 
			                	 String PoaName = null;
			                	 if (EngDesc.contains("Authenticated")){ 
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                  		   PoaName =" By(Authenticated PoA Holder) ";
			               		  }
			               		  else {
			               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
			               		  }}    
			                	 else {
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                			 PoaName =" By(PoA Holder) ";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा (मुख्‍त्‍यार आम)";
			                		  }
						}
					    	
			             	 System.out.println("POA NAMe is "+PoaName);
			       	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
			       	       // System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
			    	         
			     	        String OwnerDetail =null;
			     	        
			     	       String   poAOwnerListNew = commonBD.getOwnerDetails(sForm.getSealDTO().getRegNumber(),partyId,language);
	                       	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
	                       	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
	                       	
			      	      executantInfo1.setFather_representedBy(OwnerRolePoA);
			      	     
		       		 }
		       		 
		       		 
		       		 else
		               	     {
		       		           executantInfo1.setFather_representedBy((String)subList.get(3));
		               	     }
		       		String addrs = (String)subList.get(9);
			       	if(addrs!=null)
			       	{	
			       	addrs = addrs.replace(RegInitConstant.SPLIT_CONSTANT,",");
			       	}
			        if("English".equalsIgnoreCase(sForm.getLangauge()))
			        {	
			       	executantInfo1.setAddress("Address: "+addrs);
			        }
			        else
			        {
			        executantInfo1.setAddress("पता: " +addrs);	
			        }
		       		}
		       		   }
		       	 }
		       	   // for will deed - Authenticaed - Rahul
		       	 else if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
		       			&& ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
				    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
				    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
				    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
		       	 {
		       		executantInfo1.setType("Person");
			    	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					// int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(19));
					 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
					form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
            		 String RoleName = form.getExecutantClaimantName();
                	 System.out.println("Role Name " +RoleName); 
                	 // to check hindi and english role 
                	 String HindiEnglishRole[] = RoleName.split(",");
                	 String hindesc = HindiEnglishRole[0];
                	 String EngDesc =HindiEnglishRole[1];
                	 
                	 
                	 String PoaName = null;
                	 if (EngDesc.contains("Authenticated")){ 
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  		   PoaName =" By(Authenticated PoA Holder)";
               		  }
               		  else {
               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
               		  }}    
                	 else {
                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                			 PoaName =" By(PoA Holder)";
                		  }
                		  else {
                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
                		  }
                	 }
                		 System.out.println("POA NAMe is "+PoaName);
                    	 System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
                    	 String OwnerDetail =null;
                    
                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(sForm.getSealDTO().getRegNumber(),partyId,language);
	                       	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
	                       	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
                    	 
                	        executantInfo1.setName(OwnerRolePoA);
                	        executantInfo1.setFather_representedBy(relations+" "+(String)subList.get(5));
        	      	        logger.debug("POA  Name "+executantInfo1.getName());
        	      	       logger.debug("POA Father Guardian  Name "+executantInfo1.getFather_representedBy());
		       	 		     i1++;
		       	 		     String addrs = (String)subList.get(9);
        			        	if(addrs!=null)
        			         	{	
        			       	  addrs = addrs.replace(RegInitConstant.SPLIT_CONSTANT,",");
        			          	}
        			       	if("English".equalsIgnoreCase(sForm.getLangauge()))
        			           {
        			         	executantInfo1.setAddress("Address: "+addrs);
        			          }
        			       	  else
        			         	{
        			       		executantInfo1.setAddress("पता: "+addrs);	
        			       	    }
                	        
         	      	       
		       	 }
		       	  // Value set for POA - Owner - Rahul
		       	 else if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ){
		       		executantInfo1.setType("Person");
		       		 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
			    	 String RoleName= null;
			    	// RoleName= sealDAO.getPartyRoleDetailsLangDesc((String)subList.get(18));
			    	  RoleName = commonBO.getRoleNameForPOA((String) subList.get(18));
				    	 System.out.println("Role Name " +RoleName); 
				    	 // to check hindi and english role 
	                	 String HindiEnglishRole[] = RoleName.split(",");
	                	 String hindesc = HindiEnglishRole[0];
	                	 String EngDesc =HindiEnglishRole[1];
	                	 
	                	 
	                	 String PoaName = null;
	                	 if (EngDesc.contains("Authenticated")){ 
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                  		   PoaName =" By(Authenticated PoA Holder)";
	               		  }
	               		  else {
	               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	               		  }}    
	                	 else {
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                			 PoaName =" By(PoA Holder)";
	                		  }
	                		  else {
	                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
	                		  }
				}
			    	
	             	 System.out.println("POA NAMe is "+PoaName);
	       	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
	       	       // System.out.println("PoAOwnerlistdisplay"+poAOwnerList.get(i));
	    	         
	     	        String OwnerDetail =null;
	     	     
	     	       String   poAOwnerListNew = commonBD.getOwnerDetails(sForm.getSealDTO().getRegNumber(),partyId,language);
                  	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
                  	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
	     	        
	     	       	executantInfo1.setName(OwnerRolePoA);
	      	        executantInfo1.setFather_representedBy(relations+" "+(String)subList.get(5));
	      	        logger.debug("POA  Name "+executantInfo1.getName());
	      	       logger.debug("POA Father Guardian  Name "+executantInfo1.getFather_representedBy());
	      	     
	      	       String addrs = (String)subList.get(9);
			        	if(addrs!=null)
			         	{	
			       	  addrs = addrs.replace(RegInitConstant.SPLIT_CONSTANT,",");
			          	}
			       	if("English".equalsIgnoreCase(sForm.getLangauge()))
			           {
			         	executantInfo1.setAddress("Address: "+addrs);
			          }
			       	  else
			         	{
			       		executantInfo1.setAddress("पता: "+addrs);	
			       	    }
			    	 
		       	   } // end of POA , Owner - Rahul
		       	 else
		       	 {
		       		 //modified for Minor , Guardian - RAhul
		       		executantInfo1.setType("Person");
		       		 /*if(mName == "")
		       			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
		       		 else
		       			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(1))+" "+((String)subList.get(2)));
		       		executantInfo1.setFather_representedBy((String)subList.get(10)+ " "+(String)subList.get(5));*/
		       		int age=(subList.get(15)!=null && !subList.get(15).toString().equalsIgnoreCase(""))?Integer.parseInt((String)subList.get(15)):0;
		       		logger.debug("age of party-"+age);
		       		executantInfo1.setFather_representedBy(relations+ " "+(String)subList.get(5));
		       		if(mName == "")
		       			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
		       		else
		       			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(1))+"  "+((String)subList.get(2)));
		       	//Add minor name in Guardian
		    		 if(age<RegCompCheckerConstant.MINOR_AGE_LIMIT){ 
		    			 String MinorName = executantInfo1.getName();
		    			 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
		    			 {
		    				 
		    			 executantInfo1.setName("(Minor)"+MinorName);
		    			 executantInfo1.setFather_representedBy(relations+" "+(String)subList.get(5)+" By "+(String)subList.get(17));
		    			 }
		    			 else
		    			 {
		    				 executantInfo1.setName("(अवयस्‍क)"+MinorName);
			    			 executantInfo1.setFather_representedBy(relations+" "+(String)subList.get(5)+" द्वारा  "+(String)subList.get(17));
			    			
		    			 }
		    			 logger.debug("Execution Father Name --"+executantInfo1.getFather_representedBy());
		    			
		    			
		    			 logger.debug("Execution Party Minor Name----"+executantInfo1.getName());
		    		 }
		       		
		       		
		     
		       		
		       		String addrs = (String)subList.get(9);
			       	if(addrs!=null)
			       	{	
			       	addrs = addrs.replace(RegInitConstant.SPLIT_CONSTANT,",");
			       	}
			       	if("English".equalsIgnoreCase(sForm.getLangauge()))
			        {
			       	executantInfo1.setAddress("Address: "+addrs);
			        }
			       	else
			       	{
			       		executantInfo1.setAddress("पता: "+addrs);	
			       	}
		       	 }
		       		 
		       	
		      
		       	
		        executantInfo1.setPhotoLocation((String)subList.get(6));
		        executantInfo1.setSignatureLocation((String)subList.get(7));
		        executantInfo1.setThumbImpressionLocation((String)subList.get(8));
		        
		        executantList.add(executantInfo1);
		        }
	        }
	        else
	        {
	        	 executantList.add(null);
	        }
	       
	      
	        
	        admissionSeal.setExecutantInfo(executantList);
	        admissionSeal.setSeal(sForm.getSealsContent());
	       
	        
	       
	        String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	     // creating folder
	     	File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			// end creating folder
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			 logger.debug("before");
			 if (null==metaDataInfo.getUniqueNo()) {
					throw new Exception();
				}
				if (metaDataInfo.getUniqueNo().equals("")) {
					throw new Exception();
				}
	       // result = burnObj.putAdmissionSeal(connDetails,metaDataInfo,downloadPath,SealsConstant.NAME_OF_SEALS_DOC,outputPath,sForm.getHeaderImage(),admissionSeal,null,SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC_EXECUTION,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER,sForm.getLangauge());
			 result = burnObj.putAdmissionSeal(connDetails,metaDataInfo,downloadPath,SealsConstant.NAME_OF_SEALS_DOC,outputPath,sForm.getHeaderImage(),admissionSeal,null,sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER,sForm.getLangauge());
		        
			 
			 if(result==0)
	        {
	        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
	        	if(!flag)
	        	{
	        		result=-1;
	        	}
	        	output.delete();
	        	output2.delete();
	        }
	        logger.debug("apply seal content");
	}
	catch(Exception e)
	{
		logger.debug("exception");
	}
	return result;
	       
     
}

public int applySealContentAdmissionSealWill(ArrayList presentPartiesList,SealsForm sForm,ArrayList witnessPresentList){
	logger.debug("apply seal content");
	 int result = -1;
	try
	{
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setType(downloadSealType);
	    //metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
	
	    String hindiFont = pr.getValue("dms_hindi_font_path");
		String englishFont = pr.getValue("dms_english_font_path");
		//SealsBD sealBD=new SealsBD();
		boolean val=validateFont(hindiFont,englishFont);
		if(!val){
			throw new Exception();
		}
		BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		 AdmissionSeal admissionSeal = new AdmissionSeal();
		//by vinay
		 admissionSeal.setAuthName(sForm.getSealDTO().getUserName());
		 admissionSeal.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
		 admissionSeal.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
		 admissionSeal.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
		// end
	        ArrayList<Executant> executantList = new ArrayList();
	        
	        if(presentPartiesList != null)
	        {
	        	for(int i = 0 ; i < presentPartiesList.size() ; i++ )
		        {
		       	 ArrayList subList  = (ArrayList)presentPartiesList.get(i);
		       	Executant executantInfo1 = new Executant();
		       	 String fname = (String)subList.get(0);
		       	 String mName = (String)subList.get(1);
		       	 fname = fname==null?"":fname;
		       	 mName = mName == null?"":mName;
		       	 
		       	 
		       		executantInfo1.setType("Person");
		       		 if(mName == "")
		       			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
		       		 else
		       			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(1))+" "+((String)subList.get(2)));
		       		executantInfo1.setFather_representedBy((String)subList.get(8)+" "+(String)subList.get(3));
		       	 
		       		 
		      /* 	String addrs = (String)subList.get(9);
		       	addrs = addrs.replace(RegInitConstant.SPLIT_CONSTANT,",");*/
		       		if("English".equalsIgnoreCase(sForm.getLangauge()))
			        {
		       			executantInfo1.setAddress("Address: "+(String)subList.get(4));
			        }
		       		else
		       		{
		       			executantInfo1.setAddress("पता: " +(String)subList.get(4));	
		       		}
		       	executantInfo1.setPhotoLocation((String)subList.get(5));
		        executantInfo1.setSignatureLocation((String)subList.get(6));
		        executantInfo1.setThumbImpressionLocation((String)subList.get(7));
		        
		        executantList.add(executantInfo1);
		        
		       
		        }
	        }
	        else
	        {
	        	 executantList.add(null);
	        }
	       
	      
	        
	        admissionSeal.setExecutantInfo(executantList);
	        admissionSeal.setSeal(sForm.getSealsContent());
	       
	        
	       
	        String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	     // creating folder
	     	File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			// end creating folder
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			 logger.debug("before");
			 if (null==metaDataInfo.getUniqueNo()) {
					throw new Exception();
				}
				if (metaDataInfo.getUniqueNo().equals("")) {
					throw new Exception();
				}
	     //   result = burnObj.putAdmissionSeal(connDetails,metaDataInfo,downloadPath,SealsConstant.NAME_OF_SEALS_DOC,outputPath,sForm.getHeaderImage(),admissionSeal,null,SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER,sForm.getLangauge());
		        result = burnObj.putAdmissionSeal(connDetails,metaDataInfo,downloadPath,SealsConstant.NAME_OF_SEALS_DOC,outputPath,sForm.getHeaderImage(),admissionSeal,null,sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER,sForm.getLangauge());
		        if(result==0)
		        {
		        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
		        	if(!flag)
		        	{
		        		result=-1;
		        	}
		        	output.delete();
		        	output2.delete();
		        }
			 logger.debug("result exe will----->"+result);
	}
	catch(Exception e)
	{
		logger.debug("exception");
	}
	return result;
	       
     
}

public int applySealContentWitnessSeal(ArrayList presentPartiesList,SealsForm sForm,ArrayList witnessPresentList){
	logger.debug("apply seal content");
	 int result = -1;
	try
	{
		String sysDate = sealDAO.getSealDateWitnessMax(sForm.getSealDTO().getRegNumber());
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setType(downloadSealType);
	    //   metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
	    
	    WitnessSeal witnessSeal = new WitnessSeal();
	  //by vinay
	    witnessSeal.setAuthName(sForm.getSealDTO().getUserName());
	    witnessSeal.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
	    witnessSeal.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
	    witnessSeal.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
		// end
        ArrayList<Witness> witnessList = new ArrayList();
	    for(int i = 0 ; i < witnessPresentList.size() ;i ++)
	    {
	    	
	        Witness witnessInfo = new Witness();
	        ArrayList subList  = (ArrayList)witnessPresentList.get(i);
	        String fname = (String)subList.get(0);
	       	String mName = (String)subList.get(1);
	       	 mName = mName == null?"":mName;
	       	if(mName == "")
	       		witnessInfo.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
       		 else
       			witnessInfo.setName(((String)subList.get(0))+" "+((String)subList.get(1))+" "+((String)subList.get(2)));
	       	
	        
	        witnessInfo.setFather_husbandName((String)subList.get(8)+" "+(String)subList.get(3));
	        if("English".equalsIgnoreCase(sForm.getLangauge()))
	        {
	        witnessInfo.setAddress("Address: "+(String)subList.get(4));
	        }
	        else
	        {
	        witnessInfo.setAddress("पता: "+(String)subList.get(4)); 	
	        }
	        witnessInfo.setPhotoLocation((String)subList.get(5));
	        witnessInfo.setSignatureLocation((String)subList.get(6));
	        witnessInfo.setThumbImpressionLocation((String)subList.get(7));
	        witnessList.add(witnessInfo);
	    }
	    witnessSeal.setWitnessInfo(witnessList);
	    witnessSeal.setSeal(" की जांच पूर्वोक्त निष्पादक / निष्पादकों की शिनाख्त के विषय में की गयी है । तारीख "+sysDate);
	    String hindiFont = pr.getValue("dms_hindi_font_path");
		String englishFont = pr.getValue("dms_english_font_path");
		//SealsBD sealBD=new SealsBD();
		boolean val=validateFont(hindiFont,englishFont);
		if(!val){
			throw new Exception();
		}
		BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
		String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	     // creating folder
	     	File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			// end creating folder
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
///			result = burnObj.putAdmissionSeal(connDetails,metaDataInfo,downloadPath,SealsConstant.NAME_OF_SEALS_DOC,outputPath,sForm.getHeaderImage(),null,witnessSeal,SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER,sForm.getLangauge());
			result = burnObj.putAdmissionSeal(connDetails,metaDataInfo,downloadPath,SealsConstant.NAME_OF_SEALS_DOC,outputPath,sForm.getHeaderImage(),null,witnessSeal,sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER,sForm.getLangauge());

			logger.debug("apply seal content");
	        if(result==0)
	        {
	        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
	        	if(!flag)
	        	{
	        		result=-1;
	        	}
	        	output.delete();
	        	output2.delete();
	        }
	}
	catch(Exception e)
	{
		logger.debug("exception");
	}
	return result;
	       
     
}

public int applyThumbImpressionSeal(ArrayList partyList, ArrayList witnessList, ArrayList consenterList, String regNum, String srName, String desig, String offcName, SealsForm sForm,String lang)
{
	logger.debug("apply thumbImpression Seal");
	int result = 0;
	try
	{
		//String sysdate = sealDAO.getSystemDate();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setUniqueNo(regNum);
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setType(downloadSealType);
	    // metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
	
	    String hindiFont = pr.getValue("dms_hindi_font_path");
		String englishFont = pr.getValue("dms_english_font_path");
		//SealsBD sealBD=new SealsBD();
		boolean val=validateFont(hindiFont,englishFont);
		if(!val){
			throw new Exception();
		}
		BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont); 
	ArrayList completePartyDetails =  sealDAO.getPartyDetailsForThumbSeal(partyList,lang);
	ArrayList completeWitnessList = sealDAO.getWitnessDetailsForThumbSeal(witnessList,lang);
	ArrayList completeConsenterList = new ArrayList();
	if(consenterList.size() > 0)
	{
		completeConsenterList = sealDAO.getConsenterDetailsForThumbSeal(consenterList,lang);
	}
	
	 AdmissionSeal admissionSeal = new AdmissionSeal();
	 //by vinay
	 admissionSeal.setAuthName(sForm.getSealDTO().getUserName());
	 admissionSeal.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
	 admissionSeal.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
	 admissionSeal.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
		// end
	ArrayList<Executant> executantList = new ArrayList();
	 // // For PoA , Owner details Add - Rahul 
    SealsDTO sealsDTO = new SealsDTO();
    RegCommonBO commonBO = new RegCommonBO();
    RegCommonDAO commonDAO = new RegCommonDAO();
    RegCommonDTO mapDto =new RegCommonDTO();
    RegCommonForm form = new RegCommonForm();
    String RegNum =sForm.getSealDTO().getRegNumber();
    //ArrayList poAOwnerList = commonDAO.getOwnerPoaDescDisplay(lang,RegNum);
    ArrayList poAOwnerListForOrg = new ArrayList();
    RegCommonBD commonBD = new RegCommonBD();
    
    String CommonDeedFlag = "";
    CommonDeedFlag=sealDAO.getCommonFlowFlag(RegNum);
    int i1=0;
	String OwnerRolePoA="";
	
	 for(int i = 0 ; i < completePartyDetails.size() ; i++ )
    {
	 boolean flag=true;
	 ArrayList subList  = (ArrayList)completePartyDetails.get(i);
   	 Executant executantInfo1 = new Executant();
   	
   	 String fname = (String)subList.get(0);
   	 String mName = (String)subList.get(1);
   	 fname = fname==null?"":fname;
   	 mName = mName == null?"":mName;
   	 String nameForSealContent = "";
   	 
   	 String relations = "";
   	 String partyID ="";
   	  // for POA , Owner desc - Rahul
	 int roleId =Integer.parseInt((String)subList.get(18));
  	  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
     	
	     mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
	     int ExecutantClaimantfromDB=0;
	     if (subList.get(19)!=null && !subList.get(19).toString().equalsIgnoreCase(""))
	   	  {
	        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(19));
	   	  }	
   	 
	     if (subList.get(14)!=null){
	    	 partyID = subList.get(14).toString();
	    	 
	     }
	     
	     if (subList.get(10)!=null){
	    	 relations = commonBO.getRelationshipName((subList.get(10).toString()), lang);
	    	 
	     }
	     
   	 if(fname == "")
   	 {
   		if(subList.get(11)!=null &&subList.get(11).toString().equalsIgnoreCase("3"))
   		{
   			executantInfo1.setFather_representedBy("--");
   			if("English".equalsIgnoreCase(sForm.getLangauge()))
	        {
   			executantInfo1.setAddress("Address: "+(String)subList.get(13));
	        }
   			else
   			{
   				executantInfo1.setAddress("पता: "+(String)subList.get(13));	
   			}
   			if(subList.get(12)!=null &&!subList.get(12).toString().equalsIgnoreCase(""))
   	   		{
   				executantInfo1.setName((String)subList.get(12));
   	   		}
   			else
   			{
   				executantInfo1.setName("--");
   			}
   		}
   		else
   		{	
   			if(subList.get(11)!=null&&subList.get(11).toString().equalsIgnoreCase("1"))	
   			{
   				String NameForSeal ="";
   		       executantInfo1.setName((String)subList.get(4));
   		      executantInfo1.setType("organization");
   		   if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
					  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
				    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4
				    ||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
				    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
            		
           {
   			//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, RegNum);
			mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
			// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
			 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
			form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
   		 String RoleName = form.getExecutantClaimantName();
       	 System.out.println("Role Name " +RoleName); 
       	 String PoaName = null;
       	// to check hindi and english role 
       	 String HindiEnglishRole[] = RoleName.split(",");
       	 String hindesc = HindiEnglishRole[0];
       	 String EngDesc =HindiEnglishRole[1];
       	
       	 if (EngDesc.contains("Authenticated")){ 
    		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
      		   PoaName =" By(Authenticated PoA Holder)";
   		  }
   		  else {
   			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
   		  }}    
       	 else {
       		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
       			 PoaName =" By(PoA Holder)";
    		  }
    		  else {
    			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
    		  }
       	 }
       	 
           	 String OwnerDetail =null;
             String   poAOwnerListNew = commonBD.getOwnerDetails(regNum,partyID,lang);
          	  String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyID);
          	  OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
       	     executantInfo1.setFather_representedBy(OwnerRolePoA);
   			   
           }
   		   // for Organisaiton Convenynce deed - Rahul
   		 else if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ){
   			 executantInfo1.setType("Organization");
       		    executantInfo1.setName((String)subList.get(4));
       		// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, RegNum);
	       		 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
		    	 String RoleName= null;
		    	// RoleName= sealDAO.getPartyRoleDetailsLangDesc((String)subList.get(18));
		    	 RoleName = commonBO.getRoleNameForPOA((String) subList.get(18));
			    	 System.out.println("Role Name " +RoleName); 
			    	 // to check hindi and english role 
                	 String HindiEnglishRole[] = RoleName.split(",");
                	 String hindesc = HindiEnglishRole[0];
                	 String EngDesc =HindiEnglishRole[1];
                	 
                	 
                	 String PoaName = null;
                	 if (EngDesc.contains("Authenticated")){ 
                		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  		   PoaName =" By(Authenticated PoA Holder)";
               		  }
               		  else {
               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
               		  }}    
                	 else {
                		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                			 PoaName =" By(PoA Holder)";
                		  }
                		  else {
                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
                		  }
			}
		    	
             	 System.out.println("POA NAMe is "+PoaName);
       	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
       	       // System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
    	         
     	        String OwnerDetail =null;
     	       String   poAOwnerListNew = commonBD.getOwnerDetails(regNum,partyID,lang);
           	  String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyID);
           	  OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
      	      executantInfo1.setFather_representedBy(OwnerRolePoA);
      	      i1++;
   		 }
   		   
   		   
   		   else{
   		      executantInfo1.setFather_representedBy((String)subList.get(3));
   		   }
   		nameForSealContent = executantInfo1.getFather_representedBy();
   		if("English".equalsIgnoreCase(sForm.getLangauge()))
        {
   		executantInfo1.setAddress("Address: "+(String)subList.get(9));
        }
   		else
   		{
   			executantInfo1.setAddress("पता: "+(String)subList.get(9));	
   		}
   			}
        }
   	 } // for Govt , Organisation 
   	   // for will Deed Authenticaed POA holder -Rahul
   	 

   	 else if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
   			&& ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
	    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
	    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
	    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
   	         {
   		 				executantInfo1.setType("person");
   				    	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
   						// int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(19));
   						 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
   						form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
   	            		 String RoleName = form.getExecutantClaimantName();
   	                	 System.out.println("Role Name " +RoleName); 
   	               // to check hindi and english role 
                    	 String HindiEnglishRole[] = RoleName.split(",");
                    	 String hindesc = HindiEnglishRole[0];
                    	 String EngDesc =HindiEnglishRole[1];
                    	 
                    	 
                    	 String PoaName = null;
                    	 if (EngDesc.contains("Authenticated")){ 
                    		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                      		   PoaName =" By(Authenticated PoA Holder)";
                   		  }
                   		  else {
                   			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
                   		  }}    
                    	 else {
                    		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    			 PoaName =" By(PoA Holder)";
                    		  }
                    		  else {
                    			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
                    		  }
                    	 }
   	                		 System.out.println("POA NAMe is "+PoaName);
   	                    	 System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
   	                    	 String OwnerDetail =null;
   	                      String   poAOwnerListNew = commonBD.getOwnerDetails(regNum,partyID,lang);
   	               	  String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyID);
   	               	  OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
   	                    	 executantInfo1.setName(OwnerRolePoA);
   	                    	 nameForSealContent = executantInfo1.getName();
   	            	     
   	            	       if("English".equalsIgnoreCase(sForm.getLangauge()))
   	                       {
   	            	    	   executantInfo1.setAddress("Address: "+(String)subList.get(9));
   	                       }
   	            	       else
   	            	       	{
   	             			executantInfo1.setAddress("पता: "+(String)subList.get(9));	
   	            	       	}
   	 }
   	   // for will deed - finish - RAhul 
   	     // TO Set value for POA , Owner - Rahul
   	     else if (Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ){
   	    	      executantInfo1.setType("person");
    	           mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
    	           String RoleName= null;
		    	
            	 RoleName = commonBO.getRoleNameForPOA((String) subList.get(18));
		    	 System.out.println("Role Name " +RoleName); 
		    	 // to check hindi and english role 
            	 String HindiEnglishRole[] = RoleName.split(",");
            	 String hindesc = HindiEnglishRole[0];
            	 String EngDesc =HindiEnglishRole[1];
            	 
            	 
            	 String PoaName = null;
            	 if (EngDesc.contains("Authenticated")){ 
            		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
              		   PoaName =" By(Authenticated PoA Holder)";
           		  }
           		  else {
           			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
           		  }}    
            	 else {
            		 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            			 PoaName =" By(PoA Holder)";
            		  }
            		  else {
            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
            		  }
		}
	    	
            	 
             	 System.out.println("POA NAMe is "+PoaName);
       	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
       	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerList.get(i));
    	         
     	        String OwnerDetail =null;
     	       String   poAOwnerListNew = commonBD.getOwnerDetails(regNum,partyID,lang);
           	  String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyID);
           	  OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
      	        executantInfo1.setName(OwnerRolePoA);
      	        nameForSealContent = executantInfo1.getName();
      	       
      	       if("English".equalsIgnoreCase(sForm.getLangauge()))
                 {
      	    	   executantInfo1.setAddress("Address: "+(String)subList.get(9));
                 }
      	       else
      	       	{
       			executantInfo1.setAddress("पता: "+(String)subList.get(9));	
      	       	}
   	     } // For POA , Owner desc - RAhul
   	  // for Minor Start
   	else
   	 {
   		
   		/*int age=(subList.get(15)!=null && !subList.get(15).toString().equalsIgnoreCase(""))?Integer.parseInt((String)subList.get(15)):0;
   		logger.debug("age of party-"+age);
   	if(age<RegCompCheckerConstant.MINOR_AGE_LIMIT){
   		executantInfo1.setName((String)subList.get(16));//guradian name in case of minor
   		logger.debug("minor-name of gaurdian in thumb seal.");
   	}
   	else{
   		 if(mName == "")
   			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
   		 else
   			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(1))+" "+((String)subList.get(2)));
   	}
   		 
   		 
   		 
   		executantInfo1.setType("person");
   		executantInfo1.setFather_representedBy((String)subList.get(10)+" "+(String)subList.get(5));*/
   		executantInfo1.setType("person");
   	//modified for Minor , Guardian - RAhul
   		
   		int age=(subList.get(15)!=null && !subList.get(15).toString().equalsIgnoreCase(""))?Integer.parseInt((String)subList.get(15)):0;
   		logger.debug("age of party-"+age);
   		executantInfo1.setFather_representedBy(relations+ " "+(String)subList.get(5));
   		if(mName == "")
   			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
   		else
   			executantInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(1))+"  "+((String)subList.get(2)));
   	//Add minor name in Guardian
		 if(age<RegCompCheckerConstant.MINOR_AGE_LIMIT){
			 String MinorName = executantInfo1.getName();
			 if(lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
			 {
			 executantInfo1.setName("(Minor)"+MinorName);
			 executantInfo1.setFather_representedBy(relations+" "+(String)subList.get(5)+" By "+(String)subList.get(17));
			 }
			 else
			 {
				 executantInfo1.setName("(अवयस्‍क)"+MinorName);
				 executantInfo1.setFather_representedBy(relations+" "+(String)subList.get(5)+" द्वारा  "+(String)subList.get(17));
			 }
			 logger.debug("Thumb seal Party Minor Name----"+executantInfo1.getName());
			 logger.debug("Thumb Seal Father Name --"+executantInfo1.getFather_representedBy());
		
			 
			 
		 }
   		
   		// Changed nameForSealContent - to display - Guardian Name 
   		nameForSealContent = executantInfo1.getName()+" "+executantInfo1.getFather_representedBy();//thumb name-- to be changed
   		if("English".equalsIgnoreCase(sForm.getLangauge()))
        {
   		executantInfo1.setAddress("Address: "+(String)subList.get(9));
        }
   		else
   		{
   			executantInfo1.setAddress("पता: "+(String)subList.get(9));	
   		}
   	 }
   		 
   if(subList.get(6)==null||subList.get(6).toString().equalsIgnoreCase(""))
   {
	   flag=false;
   }
   if(subList.get(7)==null||subList.get(7).toString().equalsIgnoreCase(""))
   {
	   flag=false;
   }
   if(subList.get(8)==null||subList.get(8).toString().equalsIgnoreCase(""))
   {
	   flag=false;
   }
   
    executantInfo1.setPhotoLocation((String)subList.get(6));
    executantInfo1.setSignatureLocation((String)subList.get(7));
    executantInfo1.setThumbImpressionLocation((String)subList.get(8));
    
    String partyId=(String)subList.get(14);
    String sysdate=sealDAO.getThumbSealDate(partyId, "P");
    String sealContent = "इस दस्तावेज के निष्पादक "+nameForSealContent+" के अंगूठे का निशान मेरे द्वारा/मेरी उपस्थिति में  दिनांक "+sysdate +" को लिया गया । ";
    executantInfo1.setThumbSealContent(sealContent);
    logger.debug("^^^^^^^^"+(String)subList.get(6)+"~"+(String)subList.get(7)+"~"+(String)subList.get(8));
   if(flag)
   { 
    executantList.add(executantInfo1);
   }
   }
	 admissionSeal.setExecutantInfo(executantList);
	 WitnessSeal witnessSeal = new WitnessSeal();
	 witnessSeal.setAuthName(sForm.getSealDTO().getUserName());
	 witnessSeal.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
	 witnessSeal.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
	 witnessSeal.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
	 ArrayList WitnessListNew = new ArrayList();
	 for(int i = 0 ; i < completeWitnessList.size() ; i++ )
	    {
		 	ArrayList subList  = (ArrayList)completeWitnessList.get(i);
	        Witness witnessInfo = new Witness();
	         String fname = (String)subList.get(0);
		   	 String mName = (String)subList.get(1);
		   	 fname = fname==null?"":fname;
		   	 mName = mName == null?"":mName;
		   	
	        if(mName == "")
	        	witnessInfo.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
	   		 else
	   			witnessInfo.setName(((String)subList.get(0))+" "+((String)subList.get(1))+"  "+((String)subList.get(2)));
	        	witnessInfo.setFather_husbandName((String)subList.get(8)+" "+(String)subList.get(3));
	        	if("English".equalsIgnoreCase(sForm.getLangauge()))
	            {
	        	witnessInfo.setAddress("Address: "+(String)subList.get(4));
	            }
	        	else
	        	{
	        		witnessInfo.setAddress("पता: "+(String)subList.get(4));	
	        	}
	        	witnessInfo.setPhotoLocation((String)subList.get(5));
	        	witnessInfo.setSignatureLocation((String)subList.get(6));
	        	witnessInfo.setThumbImpressionLocation((String)subList.get(7));
	        	String partyId=(String)subList.get(9);
	            String sysdate=sealDAO.getThumbSealDate(partyId, "W");
	        	String sealContent = "इस दस्तावेज के गवाह "+witnessInfo.getName()+" के अंगूठे का निशान मेरे द्वारा/मेरी उपस्थिति में  दिनांक"+sysdate +" को लिया गया ।";
	        	logger.debug("^^^^^^^^"+(String)subList.get(5)+"~"+(String)subList.get(6)+"~"+(String)subList.get(7));
	        	WitnessListNew.add(witnessInfo);
	        
	      }
	 witnessSeal.setWitnessInfo(WitnessListNew);
	 ArrayList consenterListNew = new ArrayList();
	 if(completeConsenterList.size() > 0)
	 {
		 for(int i = 0 ; i < completeConsenterList.size() ; i++ )
		    {
			 	ArrayList subList  = (ArrayList)completeConsenterList.get(i);
		        Consenter consenterInfo = new Consenter();
		        consenterInfo.setAuthName(sForm.getSealDTO().getUserName());
		        consenterInfo.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
		        consenterInfo.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
		        consenterInfo.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
		         String fname = (String)subList.get(0);
			   	 String mName = (String)subList.get(1);
			   	 String lname = (String)subList.get(2);
			   	 fname = fname==null?"":fname;
			   	 mName = mName == null?"":mName;
			   	lname = lname == null?"":lname;
		        if(mName == "")
		        	consenterInfo.setName(((String)subList.get(0))+" "+(lname));
		   		 else
		   		consenterInfo.setName(((String)subList.get(0))+" "+((String)subList.get(1))+" "+(lname));
		        String partyId=(String)subList.get(9);
	            String sysdate=sealDAO.getThumbSealDate(partyId, "C");
		        String sealContent = "इस दस्तावेज के सहमतिकर्ता "+consenterInfo.getName()+" के अंगूठे का निशान मेरे द्वारा/मेरी उपस्थिति में  दिनांक "+sysdate +" को लिया गया । \n ";
		        consenterInfo.setConsenterSealContent(sealContent);
		        consenterInfo.setPhotoLocation((String)subList.get(5));
		        consenterInfo.setSignatureLocation((String)subList.get(6));
		        consenterInfo.setThumbImpressionLocation((String)subList.get(7));
		        logger.debug("^^^^^^^^"+(String)subList.get(5)+"~"+(String)subList.get(6)+"~"+(String)subList.get(7));
		        consenterListNew.add(consenterInfo);
		        
		      }
	 }
	 else
	 {
		 consenterListNew = null;
	 }
	 /*Executant executantInfo5= new Executant();
     executantInfo5.setType("Organization");
     executantInfo5.setName("Newgen Software Technologies Ltd.");
     executantInfo5.setFather_representedBy("Mr. Diwakar Nigam");
     executantInfo5.setAddress("D-162,Okhla Ph-1,New Delhi Pin-110020");
     executantInfo5.setPhotoLocation("D:\\upload\\03\\191213000033\\8\\3347\\photo.jpg");
     executantInfo5.setSignatureLocation("D:\\upload\\03\\191213000010\\10\\3303\\signature.GIF");
     executantInfo5.setThumbImpressionLocation("D:\\upload\\03\\191213000010\\10\\3303\\signature.GIF");
     executantInfo5.setThumbSealContent("इस दस्तावेज के निष्पादक दिवाकर निगम के अगुंष्ठ का निशान आज दिनाॅक 24/11/2013 को लिया गया है। \n आकाश सेठी \n भोपाल \n उप पंजीयक कार्यालय भोपाल");
     executantList.add(executantInfo5);
     
     admissionSeal.setExecutantInfo(executantList);
     admissionSeal.setSeal("स्वीकार करते है कि तथाकथित रजिस्ट्री विलेख का निष्पादन किया गया था। तथा प्रतिफल के पूरे/अंशिक रू.2500 प्राप्त हो गये है, तथा रू. 5000 उन्हे मेरी उपस्थिति में चुकाये गये थे। और प्रतिफल की बकाया रकम यथा रू.3000 बच गई है, जो पंजीयन के बाद प्राप्त होगी। ");
     
     //Witness Seal Information
   
     WitnessSeal witnessSeal = new WitnessSeal();
     ArrayList<Witness> witnessList1 = new ArrayList();
     Witness witnessInfo1 = new Witness();
     witnessInfo1.setName("Ritu Raj");
     witnessInfo1.setFather_husbandName("D/o Rajeev Raj");
     witnessInfo1.setAddress("Wipro Technologies, Plot 480,481 Udyog Vihar Ph-3, Gurgaon");
     witnessInfo1.setPhotoLocation("D:\\upload\\03\\191213000033\\8\\3347\\photo.jpg");
     witnessInfo1.setSignatureLocation("D:\\upload\\03\\191213000010\\10\\3303\\signature.GIF");
     witnessInfo1.setThumbImpressionLocation("D:\\upload\\03\\191213000010\\10\\3303\\signature.GIF");
     witnessList1.add(witnessInfo1);
     witnessSeal.setWitnessInfo(witnessList1);
     witnessSeal.setSeal("aaaa");*/
	 String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+regNum;
	// creating folder
  	File output;
		output = new File(outputPath.toString());
		
		if (output.exists() == false) {
			logger.debug("Parent not found creating directory....");
			output.mkdirs();
		}
		String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
		File output2;
		output2 = new File(downloadPath.toString());
		
		if (output2.exists() == false) {
			logger.debug("Parent not found creating directory....");
			output2.mkdirs();
		}
		// end creating folder+
		if (null==metaDataInfo.getUniqueNo()) {
			throw new Exception();
		}
		if (metaDataInfo.getUniqueNo().equals("")) {
			throw new Exception();
		}
	 try{
	//	result = burnObj.putThumbImpressionSeal(connDetails, metaDataInfo, downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath,sForm.getHeaderImage(), admissionSeal, witnessSeal, consenterListNew, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC, SealsConstant.NAME_OF_SEALS_DOC, SealsConstant.DMS_FOLDER,sForm.getLangauge());
	    result = burnObj.putThumbImpressionSeal(connDetails, metaDataInfo, downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath,sForm.getHeaderImage(), admissionSeal, witnessSeal, consenterListNew, sForm.getSealDTO().getSelectedSubId(), SealsConstant.NAME_OF_SEALS_DOC, SealsConstant.DMS_FOLDER,sForm.getLangauge());
	
		 if(result==0)
        {
        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
        	if(!flag)
        	{
        		result=-1;
        	}
        	output.delete();
        	output2.delete();
        }
	 
	 }
	 catch(Throwable t)
		{
		 logger.debug(t.getMessage(),t);
		}
	 }
	catch(Exception e)
	{
		logger.debug(e.getStackTrace());
		logger.debug(e.getMessage(),e);
		logger.debug("thumb impression seal error");
	}
	logger.debug("thumb impression seal code:"+result);
	return result;
}

public int applyStampDutySeal(SealsForm sForm, String userId)
{
	logger.debug("apply thumbImpression Seal");
	
	int result = 0;
	try
	{
		String SrName = sealDAO.getSrDetails(userId);
		String desig = sealDAO.getDesignationDetails(userId);
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	//    metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
	    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setType(downloadSealType);  
	    String hindiFont = pr.getValue("dms_hindi_font_path");
		String englishFont = pr.getValue("dms_english_font_path");
		//SealsBD sealBD=new SealsBD();
		boolean val=validateFont(hindiFont,englishFont);
		if(!val){
			throw new Exception();
		}
	    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
	   /* String sealContent = "स्टाम्प शुल्क          "+sForm.getSealDTO().getStampduty()+" \n नगरीय शुल्क         "+sForm.getSealDTO().getNagarNigamDuty()+" \n जनपद पंचायत शुल्क   "+sForm.getSealDTO().getGramDuty()+"" +
	    		" \n उपकर               "+sForm.getSealDTO().getUpkarDuty()+" \n अतिरिक्त शुल्क        "+sForm.getSealDTO().getOthers()+" \n योग                 "+sForm.getSealDTO().getTotalDuty();
	*/   
	    String sealContent = "स्टाम्प शुल्क              "+sForm.getSealDTO().getStampduty()+" \n नगरीय शुल्क             "+sForm.getSealDTO().getNagarNigamDuty()+" \n जनपद पंचायत शुल्क       "+sForm.getSealDTO().getGramDuty()+"" +
		" \n उपकर                   "+sForm.getSealDTO().getUpkarDuty()+" \n अतिरिक्त शुल्क            "+sForm.getSealDTO().getOthers()+" \n चुकाया गया स्टाम्प शुल्क    "+sForm.getSealDTO().getTotalDuty();

	    logger.debug("seal Content----->"+sealContent);
	    OtherSeal othSealObj = new OtherSeal();
	    othSealObj.setSealContent(sealContent);
	  //  othSealObj.setAuthName(SrName);
	 //   othSealObj.setAuthDesignation(desig);
	 ////   othSealObj.setAuthSignatureLocation(null);
	    othSealObj.setPartySignatureLocation(null);
	    othSealObj.setPartyThumbImpressionLocation(null);
	    //by vinay
        othSealObj.setAuthName(sForm.getSealDTO().getUserName());
        othSealObj.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
        othSealObj.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
        othSealObj.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
				// end
	    othSealObj.setPartyAddress(null);
	    if("English".equalsIgnoreCase(sForm.getLangauge()))
		  {	
	    othSealObj.setSealName("Stamp Duty Seal:");
		  }
	    else
	    {
	    	 othSealObj.setSealName("स्टाम्प शुल्क मुद्रा:");   	
	    }
	    String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	    File output = new File(outputPath.toString());
	    if(output.exists() == false)
	    		{
	    			logger.debug("Parent not found creating directory....");
	    			output.mkdirs();
	    		}
	    String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
		File output2;
		output2 = new File(downloadPath.toString());
		
		if (output2.exists() == false) {
			logger.debug("Parent not found creating directory....");
			output2.mkdirs();
		}
		if (null==metaDataInfo.getUniqueNo()) {
			throw new Exception();
		}
		if (metaDataInfo.getUniqueNo().equals("")) {
			throw new Exception();
		}
	   // result = burnObj.putOtherSeals(connDetails, metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(),sForm.getLangauge(), othSealObj, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
	    result = burnObj.putOtherSeals(connDetails, metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(),sForm.getLangauge(), othSealObj, sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);

		if(result==0)
        {
        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
        	if(!flag)
        	{
        		result=-1;
        	}
        	output.delete();
        	output2.delete();
        }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return result;
}
public int applyDenotingDutySeal(SealsForm sForm, String userId,String sealContent)
{
	//logger.debug("apply thumbImpression Seal");
	
	int result = 0;
	try
	{
		String SrName = sealDAO.getSrDetails(userId);
		String desig = sealDAO.getDesignationDetails(userId);
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	//    metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
	    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
	    metaDataInfo.setType(downloadSealType);  
	    String hindiFont = pr.getValue("dms_hindi_font_path");
		String englishFont = pr.getValue("dms_english_font_path");
		//SealsBD sealBD=new SealsBD();
		boolean val=validateFont(hindiFont,englishFont);
		if(!val){
			throw new Exception();
		}
	    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
	   /* String sealContent = "स्टाम्प शुल्क          "+sForm.getSealDTO().getStampduty()+" \n नगरीय शुल्क         "+sForm.getSealDTO().getNagarNigamDuty()+" \n जनपद पंचायत शुल्क   "+sForm.getSealDTO().getGramDuty()+"" +
	    		" \n उपकर               "+sForm.getSealDTO().getUpkarDuty()+" \n अतिरिक्त शुल्क        "+sForm.getSealDTO().getOthers()+" \n योग                 "+sForm.getSealDTO().getTotalDuty();
	*/   
	    /*String sealContent = "स्टाम्प शुल्क              "+sForm.getSealDTO().getStampduty()+" \n नगरीय शुल्क             "+sForm.getSealDTO().getNagarNigamDuty()+" \n जनपद पंचायत शुल्क       "+sForm.getSealDTO().getGramDuty()+"" +
		" \n उपकर                   "+sForm.getSealDTO().getUpkarDuty()+" \n अतिरिक्त शुल्क            "+sForm.getSealDTO().getOthers()+" \n चुकाया गया स्टाम्प शुल्क    "+sForm.getSealDTO().getTotalDuty();

	    logger.debug("seal Content----->"+sealContent);*/
	    OtherSeal othSealObj = new OtherSeal();
	    othSealObj.setSealContent(sealContent);
	  //  othSealObj.setAuthName(SrName);
	 //   othSealObj.setAuthDesignation(desig);
	 ////   othSealObj.setAuthSignatureLocation(null);
	    othSealObj.setPartySignatureLocation(null);
	    othSealObj.setPartyThumbImpressionLocation(null);
	    //by vinay
        othSealObj.setAuthName(sForm.getSealDTO().getUserName());
        othSealObj.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
        othSealObj.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
        othSealObj.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
				// end
	    othSealObj.setPartyAddress(null);
	    if("English".equalsIgnoreCase(sForm.getLangauge()))
		  {	
	    othSealObj.setSealName("Endorsement for Denoting duty:");
		  }
	    else
	    {
	    	 othSealObj.setSealName("शुल्क द्योतक करने का पृष्ठांकन:");   	
	    }
	    String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	    File output = new File(outputPath.toString());
	    if(output.exists() == false)
	    		{
	    			logger.debug("Parent not found creating directory....");
	    			output.mkdirs();
	    		}
	    String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
		File output2;
		output2 = new File(downloadPath.toString());
		
		if (output2.exists() == false) {
			logger.debug("Parent not found creating directory....");
			output2.mkdirs();
		}
		if (null==metaDataInfo.getUniqueNo()) {
			throw new Exception();
		}
		if (metaDataInfo.getUniqueNo().equals("")) {
			throw new Exception();
		}
	   // result = burnObj.putOtherSeals(connDetails, metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(),sForm.getLangauge(), othSealObj, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
	    result = burnObj.putOtherSeals(connDetails, metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(),sForm.getLangauge(), othSealObj, sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);

		if(result==0)
        {
        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
        	if(!flag)
        	{
        		result=-1;
        	}
        	output.delete();
        	output2.delete();
        }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return result;
}

/*public void applyExtraSeals()
{ logger.debug("apply seal content");
	BurningImageAndText burnObj = new BurningImageAndText();  
	  String newSealContent = "Known for making controversial off-the-cuff remarks, Union home minister Sushilkumar Shinde on Sunday targeted the electronic media for its";
	  String outputPath = "D:\\images";
      int result = -1;
      logger.debug("apply seal content");
      result = burnObj.putExtraSeals(outputPath, "D:\\images\\ExtraSeals.pdf", "D:\\images\\header.jpg", false, 10, 50, newSealContent, "ExtraSeals_1.pdf");
      logger.debug(result);
      logger.debug("apply seal content");
}*/

public void generateEstampCertificate()
{ logger.debug("estamp");
	BurningImageAndText burnObj = new BurningImageAndText();  
	  String newSealContent = "Known for making controversial off-the-cuff remarks, Union home minister Sushilkumar Shinde on Sunday targeted the electronic media for its";
	  String outputPath = "D:\\images";
      int result = -1;
      logger.debug("estamp");
      result = burnObj.generateEStampWithBarcode(outputPath, "D:\\images\\header.jpg", "D:\\estamp\\E-Stamp_Certificate_new.pdf", "EStampPdf_new.pdf");
      logger.debug(result);
      logger.debug("estamp");
}

/*public void generateRegCertificate()
{
	try
	{
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setUniqueNo("1234567");
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		BurningImageAndText burnObj = new BurningImageAndText();  
		 int result = -1;
		  String outputPath = "D:\\images";
		  RegistrationCertificate  regCertObject = new RegistrationCertificate();
	      regCertObject.setRegistrationNo("MC10334FG4454");
	      regCertObject.setRegistrationDate("28/12/2013");
	      regCertObject.setRegistrationFees("400");
	      regCertObject.setTotalStampDuty("900");
	      regCertObject.setSubRegistrarName("Devendra Singh");
	      regCertObject.setSubRegistrarOfficeName("Bhopal");
		result = burnObj.generateRegistrationCertificate(connDetails,metaDataInfo,"D:\\upload\\IGRS\\1234567", "D:\\images\\header.jpg", regCertObject, "RegCert.pdf","IGRS"); 
	}
	catch(Exception e)
	{
		
	}
	
}*/

/*public void mergeRegCertAndEstampDoc()
{
	try
	{
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setUniqueNo("1234567");
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	BurningImageAndText burnObj = new BurningImageAndText();  
	 int result = -1;
	  String outputPath = "D:\\images";
	result = burnObj.mergeCertificateWithEStamp(connDetails,metaDataInfo,"D:\\upload\\IGRS","Seals","RegCert.PDF","Seals","Admission.PDF", "D:\\upload\\IGRS\\1234567","RegEstamp","Registration_Estamp.PDF","IGRS");
	logger.debug("result-------------------------->"+result);
	}
	catch(Exception e)
	{
		System.out.print("exception");
	}
}*/

/*public void mergeSealWithDeed()
{
	try
	{
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		DocumentOperations docOperations = new DocumentOperations();
	    ODServerDetails connDetails = new ODServerDetails();
	    Dataclass metaDataInfo = new Dataclass();
	    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
	    connDetails.setCabinetName(pr.getValue("CabinetName"));
	    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
	    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
	    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
	    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
	    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
	    metaDataInfo.setUniqueNo("1234567");
	    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	BurningImageAndText burnObj = new BurningImageAndText();  
	 int result = -1;
	  String outputPath = "D:\\images";
	  result = burnObj.mergeSealWithDeed(connDetails,metaDataInfo,"D:\\upload\\IGRS", "RegSeal.PDF","D:\\upload\\IGRS\\1234567","D:\\upload\\Estamp.PDF", "D:\\images\\header.jpg","SealDeed", "SealDeed.pdf","IGRS");
	}
	catch(Exception e)
	{
		logger.debug("exception");
	}
}*/
/*
public void mergeRegCertAndSealDeed()
{
	BurningImageAndText burnObj = new BurningImageAndText();  
	 int result = -1;
	  String outputPath = "D:\\images";
	result = burnObj.mergeStampWithFinalDeed("D:\\images\\EStampPdf.pdf", "D:\\images\\Burn_TestPdf.pdf", "MP020731102013000002", "FinalDeed.pdf");
}*/

public static  void uploadCall(ReadPropertiesFile propertiesFileObj){
    
    /** Upload Document with Metadata */
   /*
   DocumentOperations docOperations = new DocumentOperations();
   ODServerDetails connDetails = new ODServerDetails();
   Dataclass metaDataInfo = new Dataclass();
   connDetails.setAppServerIp(propertiesFileObj.appServerIp);
   connDetails.setCabinetName(propertiesFileObj.cabinetName);
   connDetails.setAppServerUserId(propertiesFileObj.appServerUserId);
   connDetails.setAppServerUserPass(propertiesFileObj.appServerUserPass);
   connDetails.setImageServerIp(propertiesFileObj.imgageServerIp);
   connDetails.setImageServerPort(propertiesFileObj.imageServerPort);
   File file = new File("D:\\images\\FinalDeed.pdf");
   metaDataInfo.setDataclassName(propertiesFileObj.igrsDataclass);
   metaDataInfo.setUniqueNo("EMCR220044");
   String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);*/
	try
	{
		ReadPropertiesFile prop = new ReadPropertiesFile();
		//DMS start
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					
					DocumentOperations dos = new DocumentOperations();
					ODServerDetails ods = new ODServerDetails();
					Dataclass dc = new Dataclass();
				
					
					ods.setAppServerIp(pr.getValue("AppServerIp"));
					ods.setCabinetName(pr.getValue("CabinetName"));
					ods.setAppServerUserId(pr.getValue("AppServerUserId"));
					ods.setAppServerUserPass(pr.getValue("AppServerUserPass"));
					ods.setImageServerIp(pr.getValue("ImageServerIP"));
					ods.setImageServerPort(pr.getValue("ImageServerPort"));
					dc.setUniqueNo("EMCR220044");
					File file = new File("D:\\images\\FinalDeed.pdf");
					dc.setDataclassName(pr.getValue("IGRSDataclass"));
					dos.uploadDocument(ods, file, "IGRS", true, dc);
					
	}
	catch(Exception e)
	{
		
	}
}

	private long getTimeStampdiff(String timeStamp, String getCurrTimeStamp) throws Exception {
		// TODO Auto-generated method stub
		long diffMinutes=0;
		long millisecondsOld=0;
		long millisecondsNew=0;
		long diffSeconds = 0;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			if(!(timeStamp.equalsIgnoreCase("")))
			{	
				Date tmStamp = sdf.parse(timeStamp);
				millisecondsOld = tmStamp.getTime();
			}
			
			Date currTmStamp = sdf.parse(getCurrTimeStamp);
			
			millisecondsNew=currTmStamp.getTime();
			logger.debug("miliseconds new    "+millisecondsNew);
			logger.debug("miliseconds   old "+millisecondsOld);
			long diff = millisecondsNew - millisecondsOld;
			logger.debug("miliseconds    "+diff);
			diffSeconds = diff / 1000;
			logger.debug("seconds    "+diffSeconds);
			diffMinutes = diff / (60 * 1000);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return diffSeconds;
	}
   
	
	/*public void PropertyImages()
	{
		try
		{
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo("1234567");
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		BurningImageAndText burnObj = new BurningImageAndText();  
		 int result = -1;
		  String outputPath = "D:\\images";
		  String propArr[] = {"D:\\images\\property1.jpg",
				  "D:\\images\\property1.jpg"};
		  result = burnObj.putProprtyImages(connDetails,metaDataInfo,"D:\\upload\\IGRS", "Admission.PDF","D:\\upload\\IGRS\\1234567", "D:\\images\\header.jpg",propArr,"deedProp","DeedDocProp.PDF","IGRS");
		}
		catch(Exception e)
		{
			logger.debug("exception");
		}
	}*/
	
	public int AdjudicationSeal(String[] presentParties, SealsForm sForm,String srName, String offcId,String userId,String lang)
	{
		int result = -1;
		String offcName = sealDAO.getLocationDetails(offcId,lang);
		try
		{
			String desig = sealDAO.getDesignationDetails(userId);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		    metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		
		  
		  ArrayList completePartyDetails =  sealDAO.getPartyDetailsForPrsentationSeal(presentParties,lang);
		  
		  Adjudication adjuObj = new Adjudication();
		  //by vinay
		  adjuObj.setAuthName(sForm.getSealDTO().getUserName());
		  adjuObj.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
		  adjuObj.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
		  adjuObj.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
		  if("English".equalsIgnoreCase(sForm.getLangauge()))
		  {	
		  adjuObj.setSealName("Endorsement for Denoting duty:");
		  }
		  else
		  {
			  adjuObj.setSealName("शुल्क द्योतक करने का पृष्ठांकन:");	  
		  }
		  // end
		  ArrayList<Adjudicant> adjudicantList = new ArrayList();
		  for(int i = 0 ; i < completePartyDetails.size() ; i++ )
		     {
		    	 ArrayList subList  = (ArrayList)completePartyDetails.get(i);
		    	 Adjudicant adjudicantInfo = new Adjudicant();
		    	 String fname = (String)subList.get(0);
		    	 String mName = (String)subList.get(1);
		    	 fname = fname==null?"":fname;
		    	 mName = mName == null?"":mName;
		    	 if(fname == "")
		    	 {
		    		 if(subList.get(11)!=null &&subList.get(11).toString().equalsIgnoreCase("3"))
			       		{
		    			 adjudicantInfo.setType("Person");
		    			 adjudicantInfo.setFather_representedBy("--");
		    			 if("English".equalsIgnoreCase(sForm.getLangauge()))
		    		        {
		    			 adjudicantInfo.setAddress("Address: "+(String)subList.get(13));
		    		        }
		    			 else
		    			 {
		    				 adjudicantInfo.setAddress("पता: "+(String)subList.get(13));	 
		    			 }
				       	 
			       			if(subList.get(12)!=null &&!subList.get(12).toString().equalsIgnoreCase(""))
			       			{
			       				adjudicantInfo.setName((String)subList.get(12));
			       			}
			       			else
			       			{
			       				adjudicantInfo.setName("--");
			       			}
			       		}
		    		 else
		    		 {		 
		    	   adjudicantInfo.setType("Organization");
		           adjudicantInfo.setName((String)subList.get(4));
		           adjudicantInfo.setFather_representedBy((String)subList.get(3));
		           if("English".equalsIgnoreCase(sForm.getLangauge()))
		           {
		           adjudicantInfo.setAddress("Address: "+(String)subList.get(9));
		           }
		           else
		           {
		        	   adjudicantInfo.setAddress("पता: " +(String)subList.get(9));   
		           }
		           }
		    	}
		    		 
		    	 else
		    	 {
		    		 adjudicantInfo.setType("Person");
		    		 if("English".equalsIgnoreCase(sForm.getLangauge()))
		    	        {
		    		 adjudicantInfo.setAddress("Address: "+(String)subList.get(9));
		    	        }
		    		 else
		    		 {
		    			 adjudicantInfo.setAddress("पता: "+(String)subList.get(9));	 
		    		 }
		    		 if(mName == "")
		    			 adjudicantInfo.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
		    		 else
		    			 adjudicantInfo.setName(((String)subList.get(0))+" "+((String)subList.get(1))+" "+((String)subList.get(2)));
		    		 adjudicantInfo.setFather_representedBy((String)subList.get(10)+" "+(String)subList.get(5));
		    	 }
		    		 
		    	  
		    	  adjudicantList.add(adjudicantInfo);
		     }
		  
		  	adjuObj.setSealContent(sForm.getSealsContent());
	        adjuObj.setDocumentKind(sForm.getSealDTO().getDeedName());
	        adjuObj.setRegistrationPlace(offcName);
	        adjuObj.setOtherDetails(sForm.getSealDTO().getOtherDetails());
	      //  adjuObj.setAuthSignatureLocation(null);
	        adjuObj.setWitnessesName(sForm.getSealDTO().getNameOfPakshkaar());
	        adjuObj.setAuthName(srName);
	        adjuObj.setAuthDesignation(desig);
	        adjuObj.setAdjudicantInfo(adjudicantList);
	        String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
	        String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	        File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
		  result = burnObj.putAdjudicationSeal(connDetails,metaDataInfo,downloadPath,SealsConstant.NAME_OF_SEALS_DOC,outputPath,sForm.getHeaderImage(),sForm.getLangauge(),adjuObj,SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
		}
		catch(Exception e)
		{
			logger.debug("exception");
		}
		return result;
	}
	
	
	public int putCorrectionNirsanSeal(SealsForm sForm,String srName,String userId)
	{
		int result = -1;
		try
		{
			String sysDate = sealDAO.getSystemDate();
			String desig = sealDAO.getDesignationDetails(userId);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
		    metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		    OtherSeal othSealObj = new OtherSeal();
	        othSealObj.setSealName("Correction Seal");
	        othSealObj.setSealContent("यह दस्तावेज पूर्व में पंजीकृत दस्तावेज क्रमांक "+sForm.getSealDTO().getRegCompNumber()+" दिनांक "+sysDate+" को संशोधित / निरसित करता है।");
	     //   othSealObj.setAuthSignatureLocation("");
	        othSealObj.setAuthName(srName);
	        othSealObj.setAuthDesignation(desig);
	        //by vinay
	        othSealObj.setAuthName(sForm.getSealDTO().getUserName());
	        othSealObj.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
	        othSealObj.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
	        othSealObj.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
					// end
	        String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
			File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
		  result = burnObj.putOtherSeals(connDetails,metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC,outputPath, sForm.getHeaderImage(),sForm.getLangauge(),othSealObj,SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
		}
		catch(Exception e)
		{
			logger.debug("exception");
		}
		return result;
	}
	
	public int putRegistrationSeal(SealsForm sForm,HttpServletResponse response)
	{
		logger.debug("put resistration Seal");
		int result = -1;
		try
		{
			if(mergeSealWithDeed(sForm,response))
			{
				
				result=0;
				/*	
			String sysDate = sealDAO.getSystemDate();
			ArrayList list = new RegCompCheckerDAO().getEstampCodeDetails(sForm.getSealDTO().getRegNumber());
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEAL_DEED);
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		 
		  
		  
		    RegistrationSeal registrationSeal = new RegistrationSeal();
		    //by vinay
		    registrationSeal.setAuthName(sForm.getSealDTO().getUserName());
		    registrationSeal.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
		    registrationSeal.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
		    registrationSeal.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
					// end
		    
	       if("Y".equalsIgnoreCase(sForm.getCorrection()))
	       {
	        registrationSeal.setIsCorrectionSeal(Boolean.TRUE);
	        registrationSeal.setCorrectionSealAuthName(sForm.getSealDTO().getUserName());
	        registrationSeal.setCorrectionSealAuthDesignation(sForm.getSealDTO().getUserDesignation());
	        registrationSeal.setCorrectionSealAuthPlace(sForm.getSealDTO().getUserOfficeName());
	        registrationSeal.setCorrectionSealAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
	        String serialNumber=sealDAO.getSerailNumber(sForm.getSealDTO().getRegNumber(), "21");
	        registrationSeal.setCorrectionSeal("यह दस्तावेज पूर्व में पंजीकृत दस्तावेज क्रमांक "+serialNumber+" दिनांक "+sysDate+" को संशोधित / निरसित करता है।");
	       }
	       else
	       {
	    	   registrationSeal.setIsCorrectionSeal(Boolean.FALSE);
	    	   sForm.setCorrection("N");
	       }
	       
	       registrationSeal.setType(Boolean.TRUE);  // true- registration seal , false- refusal seal
	        
	        registrationSeal.setRegistrationSealContentPartOne("इस दस्तावेज का इलेक्ट्रानिक पंजीयन दिनांक "+sysDate+" को क्रमांक "+sForm.getSealDTO().getRegCompNumber()+" दे कर किया गया है। जिसमें");
	        registrationSeal.setRegistrationSealContentPartTwo("पृष्ठ समाविष्ट हैं ");
	        registrationSeal.setFeesSealContent("स्टाम्प शुल्क        "+sForm.getSealDTO().getTotalDuty()+" \n \n \n पंजीयन शुल्क       "+sForm.getSealDTO().getRegFee()+" \n प्रतिलिपि " +
	        		"शुल्क      "+sForm.getSealDTO().getCopyFee()+" \n अधिक             "+sForm.getSealDTO().getOthers()+" \n योग               "+sForm.getSealDTO().getTotal());
	        //registrationSeal.setRefusalSealContent("This is a refusal Seal");
	        RegCompCheckerDAO regDao = new RegCompCheckerDAO();
	        String deedDocPath = regDao.getDeedDocPath(sForm.getSealDTO().getRegNumber());
	        logger.debug("deed doc path--->"+deedDocPath);
	        String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	        String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			//String sealConetent="इस दस्तावेज का इलेक्ट्रानिक पंजीयन दिनाक "+sysDate+"को क्रमांक "+sForm.getSealDTO().getRegCompNumber()+" को किया गया है। जिसमें "+list.size()+" पृष्ठ समाविष्ट है "+"\nस्टाम्प शुल्क      "+sForm.getSealDTO().getTotalDuty()+" \n पंजीयन शुल्क       "+sForm.getSealDTO().getRegFee()+" \n प्रतिलिपि " +
    		//"शुल्क     "+sForm.getSealDTO().getCopyFee()+" \n अधिक     "+sForm.getSealDTO().getOthers()+" \n योग     "+sForm.getSealDTO().getTotal();
		
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
	        result = burnObj.putRegistrationFinalSeal(connDetails,metaDataInfo,downloadPath, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC_INITIAL,outputPath,deedDocPath,sForm.getHeaderImage(),registrationSeal,list.size()*2,SealsConstant.UNIQUE_CONSTANT_FOR_SEAL_DEED,SealsConstant.NAME_OF_SEALS_DEED,SealsConstant.DMS_FOLDER,sForm.getLangauge());
	       //result =burnObj.putExtraSeals(connDetails, metaDataInfo, downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(), false, 1, 1, sealConetent, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC, SealsConstant.NAME_OF_SEALS_DOC, SealsConstant.DMS_FOLDER);
	        logger.debug("result of reg seal------->"+result);
		*/}
		}
		catch(Exception e)
		{
			logger.debug("exception");
			logger.debug("exception");

		}
		return result;
	}

	
	public int putRefusalSeal(SealsForm sForm,HttpServletResponse response)
	{
		logger.debug("put refusal Seal");
		int result = -1;
		try
		{
			if(mergeSealWithDeed(sForm,response))
		{
			String sysDate = sealDAO.getSystemDate();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		    metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		  
		  
		    RegistrationSeal registrationSeal = new RegistrationSeal();
		    //by vinay
		    registrationSeal.setAuthName(sForm.getSealDTO().getUserName());
		    registrationSeal.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
		    registrationSeal.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
		    registrationSeal.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
					// end
	        registrationSeal.setType(Boolean.FALSE);  // true- registration seal , false- refusal seal
	
	        //registrationSeal.setRegistrationSealContentPartOne("इस दस्तावेज का इलेक्ट्रानिक पंजीयन दिनाक "+sysDate+" को क्रमांक "+sForm.getSealDTO().getSerialNumber()+" पर किया गया है। जिसमें");
	       // registrationSeal.setRegistrationSealContentPartTwo("पृष्ठ समाविष्ट है ");
	       // registrationSeal.setFeesSealContent("स्टाम्प शुल्क      "+sForm.getSealDTO().getStampduty()+" \n पंजीयन शुल्क       "+sForm.getSealDTO().getRegFee()+" \n प्रतिलिपि " +
	        		//"शुल्क     "+sForm.getSealDTO().getCopyFee()+" \n अधिक     "+sForm.getSealDTO().getOthers()+" \n योग     "+sForm.getSealDTO().getTotal());
	        registrationSeal.setRefusalSealContent("यह दस्तावेज़ अस्वीकृत कर दिया गया है");
	        RegCompCheckerDAO regDAO = new RegCompCheckerDAO();
	        String deedDocPath = regDAO.getDeedDocPath(sForm.getSealDTO().getRegNumber());
	        String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
	        String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	        File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
	    //    result = burnObj.putRegistrationFinalSeal(connDetails,metaDataInfo,downloadPath, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC_INITIAL,outputPath,deedDocPath,sForm.getHeaderImage(),registrationSeal,2,SealsConstant.UNIQUE_CONSTANT_FOR_SEAL_DEED,SealsConstant.NAME_OF_SEALS_DEED,SealsConstant.DMS_FOLDER,sForm.getLangauge());

			// result = burnObj.putRegistrationFinalSeal(connDetails,metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC,outputPath,deedDocPath,sForm.getHeaderImage(),registrationSeal,1,SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
		}
		}
		catch(Exception e)
		{
			logger.debug("exception");
		}
		return result;
	}

	
	/*public void barcode()
	{
		try
		{
			BurningImageAndText burnObj = new BurningImageAndText();  
		   int result = burnObj.createBarcode("D:\\upload\\IGRS\\barcode\\E-Stamp Certificate (1).pdf", "D:\\upload\\IGRS\\","estamp_barcode.jpg");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	
	public int putPOASealWitness(SealsForm sForm, String srName,String userId,ArrayList completeWitnessList)
	{
		int result = -1;
		try
		{
			String sysDate = sealDAO.getSystemDate();
			String desig = sealDAO.getDesignationDetails(userId);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setType(downloadSealType);  
		    
		    // metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		    ArrayList<Party> partyList = new ArrayList();
		    for(int i = 0 ; i < completeWitnessList.size() ;i ++)
		    {
		    	  ArrayList subList  = (ArrayList)completeWitnessList.get(i);
		    	 Party partyInfo1 = new Party();
		    	 partyInfo1.setType("Person");
		    	 String fname = (String)subList.get(0);
			       	String mName = (String)subList.get(1);
			       	 mName = mName == null?"":mName;
			       	if(mName == "")
			       		partyInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(2)));
		       		 else
		       			partyInfo1.setName(((String)subList.get(0))+" "+((String)subList.get(1))+"  "+((String)subList.get(2)));
		    	
		    	 partyInfo1.setFather_representedBy((String)subList.get(8)+" "+(String)subList.get(3));
		    	 if("English".equalsIgnoreCase(sForm.getLangauge()))
		         {
		    	 partyInfo1.setAddress("Address: "+(String)subList.get(4));
		         }
		    	 else
		    	 {
		    		 partyInfo1.setAddress("पता: "+(String)subList.get(4));	 
		    	 }
		    	 partyList.add(partyInfo1);
		    	
			  }
		    
		    Attorney attrnObj = new Attorney();
		    //by vinay
		    attrnObj.setAuthName(sForm.getSealDTO().getUserName());
			  attrnObj.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
			  attrnObj.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
			  attrnObj.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
					// end
		    attrnObj.setSealName("Attorney Seal");
		    attrnObj.setPartyInfo(partyList);
	        attrnObj.setSealContent(sForm.getSealsContent());
	        attrnObj.setPartySignatureLocation("");
	        attrnObj.setPartyThumbImpressionLocation("");
	        attrnObj.setPartyAddress("");
	   //     attrnObj.setAuthSignatureLocation("");
	      //  attrnObj.setAuthName(srName);
	       // attrnObj.setAuthDesignation(desig);
	        String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	        String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
	      //  result = burnObj.putAttorneySeal(connDetails, metaDataInfo, downloadPath,SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(), sForm.getLangauge(), attrnObj, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC, SealsConstant.DMS_FOLDER);
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			result = burnObj.putAttorneySeal(connDetails, metaDataInfo, downloadPath,SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(), sForm.getLangauge(), attrnObj, sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC, SealsConstant.DMS_FOLDER);

			if(result==0)
	        {
	        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
	        	if(!flag)
	        	{
	        		result=-1;
	        	}
	        	output.delete();
	        	output2.delete();
	        }
		}
		catch(Exception e)
		{
			logger.debug("exception");
		}
		return result;
	}
	
	
	public int putPOASealVisit(SealsForm sForm, String srName,String userId)
	{
		int result = -1;
		try
		{
			String sysDate = sealDAO.getSystemDate();
			String desig = sealDAO.getDesignationDetails(userId);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		    metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		    Attorney attrnObj = new Attorney();
		    attrnObj.setSealName("Attorney Seal");
	        attrnObj.setPartyInfo(null);
	        attrnObj.setSealContent(sForm.getSealsContent());
	        attrnObj.setPartySignatureLocation("");
	        attrnObj.setPartyThumbImpressionLocation("");
	        attrnObj.setPartyAddress("");
	    //    attrnObj.setAuthSignatureLocation("");
	     //   attrnObj.setAuthName(srName);
	   //     attrnObj.setAuthDesignation(desig);
	        String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
	        String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
	        result = burnObj.putAttorneySeal(connDetails, metaDataInfo, downloadPath,SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(), sForm.getLangauge(), attrnObj, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC, SealsConstant.DMS_FOLDER);
		}
		catch(Exception e)
		{
			logger.debug("exception");
		}
		return result;
	}
	
	public int putRuleNumber35Seal1(SealsForm sForm,String srName,String userId)
	{
		int result = -1;
		try
		{
			String sysDate = sealDAO.getSystemDate();
			String desig = sealDAO.getDesignationDetails(userId);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		  //  metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		  
		    
		    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setType(downloadSealType);
		    OtherSeal othSealObj = new OtherSeal();
	      if("English".equalsIgnoreCase(sForm.getLangauge()))
	    		  {	  
		    othSealObj.setSealName("Rule Number 35 Seal");
	    		  }
	      else
	      {
	    	  othSealObj.setSealName("नियम 35 - मुद्रा ");
	      }
		    othSealObj.setSealContent(sForm.getSealsContent());
	     //   othSealObj.setAuthSignatureLocation("");
	    //    othSealObj.setAuthName(srName);
	     //   othSealObj.setAuthDesignation(desig);
	        //by vinay
	        othSealObj.setAuthName(sForm.getSealDTO().getUserName());
	        othSealObj.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
	        othSealObj.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
	        othSealObj.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
					// end
	        String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
			File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
		//  result = burnObj.putOtherSeals(connDetails,metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC,outputPath, sForm.getHeaderImage(),sForm.getLangauge(),othSealObj,SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}  
			result = burnObj.putOtherSeals(connDetails,metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC,outputPath, sForm.getHeaderImage(),sForm.getLangauge(),othSealObj,sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);

			if(result==0)
	        {
	        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
	        	if(!flag)
	        	{
	        		result=-1;
	        	}
	        	output.delete();
	        	output2.delete();
	        }
		}
		catch(Exception e)
		{
			logger.debug("exception");
		}
		return result;
	}
	
	public int putRuleNumber35Seal2(SealsForm sForm,String srName,String userId)
	{
		int result = -1;
		try
		{
			String sysDate = sealDAO.getSystemDate();
			String desig = sealDAO.getDesignationDetails(userId);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		    metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		    OtherSeal othSealObj = new OtherSeal();
	        othSealObj.setSealName("Rule Number 35 Seal:");
	        othSealObj.setSealContent(sForm.getSealsContent());
	      //  othSealObj.setAuthSignatureLocation("");
	       // othSealObj.setAuthName(srName);
	     //   othSealObj.setAuthDesignation(desig);
	        String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
			File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
		  result = burnObj.putOtherSeals(connDetails,metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC,outputPath, sForm.getHeaderImage(),sForm.getLangauge(),othSealObj,SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertCommonSealDetails(String regInitId, String sealId)
	{
		return sealDAO.insertCommonSealDetails(regInitId, sealId);
	}
	public boolean updateSerialNumber(String regInitId, String sealId, String serialNumber)
	{
		return sealDAO.updateSerialNumber(regInitId, sealId,serialNumber);
	}
	
	public int putPOASeal(SealsForm sForm,String srName,String userId)
	{
		int result = -1;
		try
		{
			String sysDate = sealDAO.getSystemDate();
			String desig = sealDAO.getDesignationDetails(userId);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);  
		    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setType(downloadSealType);
		    // metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		    OtherSeal othSealObj = new OtherSeal();
	        othSealObj.setSealName("POA Seal");
	        othSealObj.setSealContent(sForm.getSealsContent());
	    //    othSealObj.setAuthSignatureLocation("");
	     //   othSealObj.setAuthName(srName);
	    //.setAuthDesignation(desig);
	      //by vinay
	        othSealObj.setAuthName(sForm.getSealDTO().getUserName());
	        othSealObj.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
	        othSealObj.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
	        othSealObj.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
					// end
	        String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
			File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
		//  result = burnObj.putOtherSeals(connDetails,metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC,outputPath, sForm.getHeaderImage(),sForm.getLangauge(),othSealObj,SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			result = burnObj.putOtherSeals(connDetails,metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC,outputPath, sForm.getHeaderImage(),sForm.getLangauge(),othSealObj,sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);

			if(result==0)
	        {
	        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
	        	if(!flag)
	        	{
	        		result=-1;
	        	}
	        	output.delete();
	        	output2.delete();
	        }
		}
		catch(Exception e)
		{
			logger.debug("exception");
		}
		return result;
	}
	
	public int applyAdmissionSealGovt(SealsForm sForm, String userId)
	{
		logger.debug("apply thumbImpression Seal");
		
		int result = 0;
		try
		{
			String SrName = sealDAO.getSrDetails(userId);
			String desig = sealDAO.getDesignationDetails(userId);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		   
		    //metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setType(downloadSealType);
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
		    BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
		    /*String sealContent = "स्टाम्प शुल्क   "+sForm.getSealDTO().getStampduty()+" \n नगरीय शुल्क   "+sForm.getSealDTO().getNagarNigamDuty()+" \n जनपद पंचायत शुल्क   "+sForm.getSealDTO().getGramDuty()+"" +
		    		" \n उपकर    "+sForm.getSealDTO().getUpkarDuty()+" \n अतिरिक्त शुल्क   "+sForm.getSealDTO().getOthers()+" \n योग   "+sForm.getSealDTO().getTotalDuty();*/
		    logger.debug("seal Content----->"+sForm.getSealsContent());
		    OtherSeal othSealObj = new OtherSeal();
		    othSealObj.setSealContent(sForm.getSealsContent());
		//    othSealObj.setAuthName(SrName);
		//    othSealObj.setAuthDesignation(desig);
		//    othSealObj.setAuthSignatureLocation(null);
		  //by vinay
		    othSealObj.setAuthName(sForm.getSealDTO().getUserName());
		    othSealObj.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
		    othSealObj.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
		    othSealObj.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
		// end
		    othSealObj.setPartySignatureLocation(null);
		    othSealObj.setPartyThumbImpressionLocation(null);
		    othSealObj.setPartyAddress(null);
		    othSealObj.setSealName("Admission Seal:");
		    String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
		    File output = new File(outputPath.toString());
		    if(output.exists() == false)
		    		{
		    			logger.debug("Parent not found creating directory....");
		    			output.mkdirs();
		    		}
		    String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
		//    result = burnObj.putOtherSeals(connDetails, metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(),sForm.getLangauge(), othSealObj, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC,SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			result = burnObj.putOtherSeals(connDetails, metaDataInfo,downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(),sForm.getLangauge(), othSealObj, sForm.getSealDTO().getSelectedSubId(),SealsConstant.NAME_OF_SEALS_DOC,SealsConstant.DMS_FOLDER);
		    if(result==0)
	        {
	        	boolean flag=sealDAO.updateSealFlag(sForm.getSealDTO().getRegNumber(),sForm.getSealDTO().getSelectedSubId());
	        	if(!flag)
	        	{
	        		result=-1;
	        	}
	        	output.delete();
	        	output2.delete();
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public boolean mergeSealWithDeed(SealsForm sForm,HttpServletResponse response)
	{
		boolean flag = false;
		logger.debug("mergeSealWithDeed");
		try
		{
			//RegCompCheckerDTO rdto = regForm.getRegDTO();
			RegCompCheckerDAO regCompDAO =  new RegCompCheckerDAO();

			String deedDocPath = regCompDAO.getDeedDocPath(sForm.getSealDTO().getRegNumber());
			logger.debug("deed doc path---->"+deedDocPath);
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());
		  //  metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
		    String downloadSealType=sealDAO.getSealTypeFlag(sForm.getSealDTO().getRegNumber());
		    metaDataInfo.setType(downloadSealType);
		    
		    
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			//SealsBD sealBD=new SealsBD();
			boolean val=validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
			int result = -1;
			 String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+sForm.getSealDTO().getRegNumber();
				File output2;
				output2 = new File(downloadPath.toString());
				
				if (output2.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output2.mkdirs();
				}
			//String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+sForm.getSealDTO().getRegNumber();
			String outputPath=pr.getValue("igrs_upload_path")+File.separator+"REGSEAL"+File.separator+"IGRS"+File.separator+sForm.getSealDTO().getRegNumber();
			File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			//result = burnObj.mergeSealWithDeed(connDetails, metaDataInfo, outputPath, RegCompCheckerConstant.NAME_OF_SEALS_DOC, outputPath,deedDocPath, sForm.getHeaderImage(), RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, RegCompCheckerConstant.DMS_FOLDER,sForm.getLangauge());
			//for seal deed with map
			ArrayList imageList=regCompDAO.getPropertyImagesCertificate(sForm.getSealDTO().getRegNumber());
			if (null==metaDataInfo.getUniqueNo()) {
				return false;
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				return false;
			}
			if(imageList==null||imageList.size()==0)
			{
				result = burnObj.mergeSealWithDeed(connDetails, metaDataInfo, outputPath, RegCompCheckerConstant.NAME_OF_SEALS_DOC, outputPath,deedDocPath, sForm.getHeaderImage(), RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, RegCompCheckerConstant.DMS_FOLDER,sForm.getLangauge());
			
			
			}
			else
			{	
			
				//result = burnObj.mergeSealWithDeed(connDetails, metaDataInfo, outputPath, RegCompCheckerConstant.NAME_OF_SEALS_DOC, outputPath,deedDocPath, sForm.getHeaderImage(), RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC_INITIAL, RegCompCheckerConstant.DMS_FOLDER,sForm.getLangauge());
				ArrayList <byte[]> array= getByteArray(imageList);
				for(int i=0;i<array.size();i++)
				{
				String path=pr.getValue("igrs_upload_path")+File.separator+"REGSEAL"+File.separator+"IGRS"+File.separator+sForm.getSealDTO().getRegNumber()+File.separator+"01"+File.separator+i+".PDF"; 
				ArrayList imageList1 = new ArrayList();
				imageList1.add(array.get(i));
				generatePDF(path,imageList1);
				}
				
				mergeConsenterDeedPDF(outputPath+File.separator, response, imageList.size(), RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC_PROP,deedDocPath);
				
				
				File file = new File(outputPath+File.separator+RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC_PROP);
				if(file.exists())
				{

					deedDocPath=outputPath+File.separator+RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC_PROP;
					result = burnObj.mergeSealWithDeed(connDetails, metaDataInfo, outputPath, RegCompCheckerConstant.NAME_OF_SEALS_DOC, outputPath,deedDocPath, sForm.getHeaderImage(), RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, RegCompCheckerConstant.DMS_FOLDER,sForm.getLangauge());
					
				}
			
				else
				{
					logger.debug("Error in Digital Sign");
					result=-1;
				}
				
				
					
			}
			
			//end
			
			
			if(result == 0)
			{	
				
				flag = true;
			}
			logger.debug("result of merging seal with deed---->"+result);
		}
		catch(Exception e)
		{
			logger.debug("Exception in mergeSealWithDeed");
			e.printStackTrace();
		}
		return flag;
	}
	public boolean generatePDF(String fileName,
			ArrayList<byte[]> arrays) throws Exception {
		boolean ret = false;
		logger.info("Entering method generatePDF");
		try {
			
			File output = new File(fileName);
			if (output.exists()) {
				logger.info("file already exists deleting....");
				output.delete();
			}
			if (output.getParentFile().exists() == false) {
				logger.info("Parent not found creating directory....");
				output.getParentFile().mkdirs();
			}
			Image pdfImage;
			logger.info("No. of arrays : " + arrays.size());
			Document document = new Document(PageSize.A4, 100, 40, 144, 180);
		//	Rectangle rec=new Rectangle(40,40);
			
			OutputStream fos = new FileOutputStream(fileName, false);
			PdfWriter.getInstance(document, fos);
			document.open();
			for (byte[] bytes : arrays) {
				pdfImage = Image.getInstance(bytes);
				//pdfImage.scalePercent(45.0f);
				//pdfImage.rectangle(40, 40);
				//pdfImage.scaleAbsolute(1400,1400);
				pdfImage.scaleToFit(370,520);
				document.newPage();
				document.add(pdfImage);
			}

			document.close();
			ret=true;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			
		}
		logger.info("Leaving method generatePDF");
		return ret;
	}
	ArrayList <byte[]> getByteArray(ArrayList list)
	{
		ArrayList <byte[]> array= new ArrayList<byte[]>();
		try{
		for(int i=0;i<list.size();i++)
		{
			String path=(String) list.get(i);
			 File file = new File(path);
			 FileInputStream fis = new FileInputStream(file);
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 byte[] buf = new byte[1024];
			 for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                bos.write(buf, 0, readNum); 
	                }
			 byte[] bytes = bos.toByteArray();
			 array.add(bytes);
			 
		}
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		return array;
	}
	public String getLangauge(String txnId) {
		String paydtls=null;
		try{
			paydtls = sealDAO.getLangauge(txnId);
		
		}catch(Exception e){
			logger.debug(e.getStackTrace());
		}
		return paydtls;
	}
	public String getSealApplied(String txnId,String sealId) {
		String paydtls=null;
		try{
			paydtls = sealDAO.getSealAppliedFlag(txnId,sealId);
		
		}catch(Exception e){
			logger.debug(e.getStackTrace());
		}
		return paydtls;
	}
	public boolean updateSealApplied(String regNo,String subSealId,String status )
	{
		return sealDAO.updateSealApplied(regNo, subSealId,status);
	}
	public boolean updateCompTime(String regNo)
	{
		return sealDAO.updateCompTime(regNo);
	}
	public boolean updateSealFlag(String regNo,String sealFlag)
	{
		return sealDAO.updateSealFlag(regNo, sealFlag);
	}
	public String getSealTypeFlag(String regNo)
	{
		return sealDAO.getSealTypeFlag(regNo);
	}
	public byte[] mergeConsenterDeedPDF(String path,HttpServletResponse response,int noPage,String deedName,String fileOne) throws Exception{

		
		try{

			//String deedName=RegInitConstant.MERGED_DEED_DOC;
		//	String fileOne=path+RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC_INITIAL;
			FileInputStream fileone=new FileInputStream(fileOne);
			FileInputStream f[]=new FileInputStream[noPage];
			PDFMergerUtility ut = new PDFMergerUtility();
			ut.addSource(fileone);
			for(int i=0;i<noPage;i++){
				
				f[i]=new FileInputStream(path+File.separator+"01"+File.separator+(i)+".PDF");
				
				ut.addSource(f[i]);
			}
			ut.setDestinationFileName(path+deedName);
			ut.mergeDocuments();
			logger.debug("merging Done--->");
			System.out.println("merging done---->");
			byte os[]=DMSUtility.getDocumentBytes(fileOne);
			FileInputStream fis = new FileInputStream(new File(path+"/"+deedName));
			byte[] abc = IOUtils.toByteArray(fis);
			fis.close();
			return abc;	
		  		
		}catch(Exception e){
			logger.debug(e.getMessage(),e);
			//return null;
			throw e;
			
		}



		}
	
	public ArrayList getBookDetails() throws Exception
	{
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList bookDetails = new ArrayList();
		RegCompCheckerDAO regCompDAO =  new RegCompCheckerDAO();
		list = regCompDAO.getBookDetails();
		for(int i= 0;i<list.size();i++)
		{
			list1 = (ArrayList)list.get(i);
			SealsDTO rDTO = new SealsDTO();
			rDTO.setBookId((String)list1.get(0));
			rDTO.setBookName((String)list1.get(1));
			bookDetails.add(rDTO);
		}
		return bookDetails;
			
	}
	
	public  boolean validateFont(String hindiFontPath, String engFontPath){
		
		
		try{
		java.awt.Font hindiFont;
		hindiFont = java.awt.Font.createFont(0, new FileInputStream(hindiFontPath));		
		java.awt.Font engFont;
		engFont = java.awt.Font.createFont(0, new FileInputStream(engFontPath));
		
		//java.awt.GraphicsEnvironment.getAvailableFontFamilyNames();
		
		return true;
		
		}catch(Exception e){
			
			logger.debug("Font missing");
			logger.debug(e.getMessage(),e);
			return false;
			
		}
		
		
		
	}
}
