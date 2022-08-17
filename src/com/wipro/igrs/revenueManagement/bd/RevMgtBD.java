/**
 * 
 */
package com.wipro.igrs.revenueManagement.bd;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.bo.RevMgtBO;
import com.wipro.igrs.revenueManagement.dao.RevMgtDAO;
import com.wipro.igrs.revenueManagement.dto.RevMgtDTO;

/**
 * @author RA836784
 *
 */
public class RevMgtBD {
	private Logger logger =(Logger)Logger.getLogger(RevMgtBD.class);
	
	RevMgtBO rmbo=new RevMgtBO();
	
	
	/*public static ArrayList getDetails(String txnId) throws Exception {
		
		ArrayList list=rmbo.getDetails(txnId);
		// TODO Auto-generated method stub
		return list;
	}
*/
	
	
	/******************************************************************  
	  *   Method Name  :   getFilteredresults()
	  *   Arguments    :   fromDate,toDate,paymentType,txnId
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	//for cash online challan Transaction Mapping
	public ArrayList getFilteredresults(String paymentType, String fromDate,
			String toDate, String txnId,String languageLocale,String purposeID)  throws Exception{
		
		ArrayList resultList = new ArrayList();
		String searchFields[] = new String[5];
		searchFields[0] = "" + paymentType;
		searchFields[1] = "" + fromDate;
		searchFields[2] = "" + toDate;
		searchFields[3] = "" + txnId;
		searchFields[4] = "" + purposeID;
		
		resultList = rmbo.getFilteredresults(searchFields,languageLocale);
		
		
		return resultList;
	}
//for credit limit Transaction Mapping
	public ArrayList getFilteredresults1(String paymentType, String fromDate,
			String toDate, String txnId, String languageLocale,String purposeID,String officeID)  throws Exception{
		
		ArrayList resultList = new ArrayList();
		String searchFields[] = new String[6];
		searchFields[0] = "" + paymentType;
		searchFields[1] = "" + fromDate;
		searchFields[2] = "" + toDate;
		searchFields[3] = "" + txnId;
		searchFields[4] = "" + purposeID;
		searchFields[5] = "" + officeID;
		
		resultList = rmbo.getFilteredresults1(searchFields,languageLocale);
		
		
		return resultList;
	}
	
	/******************************************************************  
	  *   Method Name  :   getFilteredresults2()
	  *   Arguments    :   radioSelectReceipt , receiptTextBox
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 * @param dto 
	 *******************************************************************/  
	//for Cash Receipt Search
	public ArrayList getFilteredresults2(String receiptTextBox, RevMgtDTO dto,String languageLocale)
			  throws Exception{
		
		ArrayList mainList = new ArrayList();
		 ArrayList list = new ArrayList();
		 RevMgtDAO dao = new RevMgtDAO();
		list =  dao.getFilteredresults2(receiptTextBox,dto);
		
		
		
		if(list.size()>0 && list !=null)
		{
			for(int i=0;i<list.size();i++)
			{
				ArrayList  subList = (ArrayList) list.get(i);
				
				
				
				
				
				
				String status="";
				String statusType=((String)(subList.get(10)));
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
				{
					//below for english status
				if(statusType != null)
				{
					
				 if(statusType.equalsIgnoreCase("D"))
				{
					status="Consumed";
				}
				else if(statusType.equalsIgnoreCase("A"))
				{
					status="Processed by Treasury";
				}
				}
				else
				{
					status="Created";
				}
			}else{
				
//below for hindi status
				if(statusType != null)
				{
					
				 if(statusType.equalsIgnoreCase("D"))
				{
					status=CommonConstant.STATUS_DEACTIVATED;
				}
				else if(statusType.equalsIgnoreCase("A"))
				{
					status=CommonConstant.STATUS_ACTIVATED;
				}
				}
				else
				{
					status=CommonConstant.STATUS_DOWNLOADED;
				}
			
				
			}
				/*rmdto.setCashId((String)(list.get(0)));
				rmdto.setGrossAmount((Double.parseDouble((String)list.get(1))));
			    rmdto.setCashDate((String)(list.get(2)));
				//rmdto.setCashStatus((String)list.get(6));
				rmdto.setCashPurpose((String)list.get(7));
				rmdto.setCashOfficeName((String)list.get(6));
				
				rmdto.setCashUserName((String)list.get(9));
				
		
				*/
				
				
				dto.setCashId((String)(subList.get(0)));
				//dto.setGrossAmount((Double.parseDouble((String)subList.get(1))));
				dto.setGrossAmount((String)(subList.get(1)));
			    dto.setCashDate((String)(subList.get(2)));
			    dto.setCashOfficeId((String)subList.get(3));
			    
			    dto.setCashStatus((String)subList.get(6));
			    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){//for english
			    dto.setCashPurpose((String)subList.get(7));
			    dto.setCashOfficeName((String)subList.get(6));
			    }else{                                                                //for hindi
			    	dto.setCashPurpose((String)subList.get(12));
				    dto.setCashOfficeName((String)subList.get(11));
			    }
				
			    dto.setCashUserName((String)subList.get(9));
			  //  dto.setCashStatus((String)(subList.get(10)));
		             dto.setCashStatus(status);
	           mainList.add(dto);
}			
	}
		
		 return mainList;
		
		
}

	/******************************************************************  
	  *   Method Name  :   getFilteredresults3()
	  *   Arguments    :   radioSelectReceipt , receiptTextBox
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getFilteredresults3(String receiptTextBox,String radioSelectReceipt,RevMgtDTO dto, String languageLocale)
			  throws Exception{
		
		ArrayList mainList = new ArrayList();
		 ArrayList list = new ArrayList();
		 RevMgtDAO dao = new RevMgtDAO();
		list =  dao.getFilteredresults3(receiptTextBox,radioSelectReceipt,dto);
		
		String mode="Challan";
		
		if(list.size()>0 && list !=null)
		{
			for(int i=0;i<list.size();i++)
			{
				ArrayList  subList = (ArrayList) list.get(i);
				
				
				
				String status="";
				String statusType=((String)(subList.get(10)));
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
				{
					//below for english status
				if(statusType != null)
				{
					
				 if(statusType.equalsIgnoreCase("D"))
				{
					//status="Deactive";
					 status="Consumed";
				}
				else if(statusType.equalsIgnoreCase("A"))
				{
					status="Processed by Treasury";
				}
				}
				else
				{
					status="Created";
				}
			}else{
				
//below for hindi status
				if(statusType != null)
				{
					
				 if(statusType.equalsIgnoreCase("D"))
				{
					status=CommonConstant.STATUS_DEACTIVATED;
				}
				else if(statusType.equalsIgnoreCase("A"))
				{
					status=CommonConstant.STATUS_ACTIVATED;
				}
				}
				else
				{
					status=CommonConstant.STATUS_DOWNLOADED;
				}
			
				
			}
				
				/*rmdto.setCashId((String)(list.get(0)));
				rmdto.setGrossAmount((Double.parseDouble((String)list.get(1))));
			    rmdto.setCashDate((String)(list.get(2)));
				//rmdto.setCashStatus((String)list.get(6));
				rmdto.setCashPurpose((String)list.get(7));
				rmdto.setCashOfficeName((String)list.get(6));
				
				rmdto.setCashUserName((String)list.get(9));
				
		
				*/
				
				
				dto.setCashId((String)(subList.get(0)));
				//dto.setGrossAmount((Double.parseDouble((String)subList.get(1))));
				dto.setGrossAmount((String)(subList.get(1)));
			    dto.setCashDate((String)(subList.get(2)));
			    dto.setCashOfficeId((String)subList.get(3));
			    
			    dto.setCashStatus((String)subList.get(6));
			    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){//for english
				    dto.setCashPurpose((String)subList.get(7));
				    dto.setCashOfficeName((String)subList.get(6));
				    }else{                                                                //for hindi
				    	dto.setCashPurpose((String)subList.get(12));
					    dto.setCashOfficeName((String)subList.get(11));
				    }
				
			    dto.setCashUserName((String)subList.get(9));
			 //  dto.setCashStatus((String)(subList.get(10)));
			   dto.setCashStatus(status);
		
	           mainList.add(dto);
}			
	}
		
		 return mainList;
		
		
}



	/******************************************************************  
	  *   Method Name  :   getFilteredresults4()
	  *   Arguments    :   radioSelectReceipt , receiptTextBox
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getFilteredresults4(String receiptTextBox,String radioSelectReceipt,RevMgtDTO dto,String languageLocale)
			  throws Exception{
		
		ArrayList mainList = new ArrayList();
		 ArrayList list = new ArrayList();
		 RevMgtDAO dao = new RevMgtDAO();
		list =  dao.getFilteredresults4(receiptTextBox,radioSelectReceipt,dto);
		
		String mode="Challan";
		
		if(list.size()>0 && list !=null)
		{
			for(int i=0;i<list.size();i++)
			{
				ArrayList  subList = (ArrayList) list.get(i);
				
				
				
				String status="";
				String statusType=((String)(subList.get(10)));
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
				{
					//below for english status
				if(statusType != null)
				{
					
				 if(statusType.equalsIgnoreCase("D"))
				{
					status="Deactive";
				}
				else if(statusType.equalsIgnoreCase("A"))
				{
					status="Active";
				}
				}
				else
				{
					status="Downlaoded";
				}
			}else{
				
//below for hindi status
				if(statusType != null)
				{
					
				 if(statusType.equalsIgnoreCase("D"))
				{
					status=CommonConstant.STATUS_DEACTIVATED;
				}
				else if(statusType.equalsIgnoreCase("A"))
				{
					status=CommonConstant.STATUS_ACTIVATED;
				}
				}
				else
				{
					status=CommonConstant.STATUS_DOWNLOADED;
				}
			
				
			}
				
				/*rmdto.setCashId((String)(list.get(0)));
				rmdto.setGrossAmount((Double.parseDouble((String)list.get(1))));
			    rmdto.setCashDate((String)(list.get(2)));
				//rmdto.setCashStatus((String)list.get(6));
				rmdto.setCashPurpose((String)list.get(7));
				rmdto.setCashOfficeName((String)list.get(6));
				
				rmdto.setCashUserName((String)list.get(9));
				
		
				*/
				
				
				dto.setCashId((String)(subList.get(0)));
				//dto.setGrossAmount((Double.parseDouble((String)subList.get(1))));
				dto.setGrossAmount((String)(subList.get(1)));
			    dto.setCashDate((String)(subList.get(2)));
			    dto.setCashOfficeId((String)subList.get(3));
			    
			    dto.setCashStatus((String)subList.get(6));
			    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){//for english
				    dto.setCashPurpose((String)subList.get(7));
				    dto.setCashOfficeName((String)subList.get(6));
				    }else{                                                                //for hindi
				    	dto.setCashPurpose((String)subList.get(12));
					    dto.setCashOfficeName((String)subList.get(11));
				    }
				
			    dto.setCashUserName((String)subList.get(9));
			  // dto.setCashStatus((String)(subList.get(10)));
			   dto.setCashStatus(status);
		
	           mainList.add(dto);
}			
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
    RevMgtDAO dao = new RevMgtDAO();
		try{
		list = dao.getDistrictList();
    
		RevMgtDTO dto = new RevMgtDTO();
    for (int i = 0; i < list.size(); i++) {
        subList = (ArrayList)list.get(i);
        dto = new RevMgtDTO();
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
   RevMgtDAO dao = new RevMgtDAO();
		try{
		list = dao.getOfficeTypeList(disId);
   
		RevMgtDTO dto = new RevMgtDTO();
   for (int i = 0; i < list.size(); i++) {
       subList = (ArrayList)list.get(i);
       dto = new RevMgtDTO();
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
	public ArrayList getOfficeNameList(String disId, String offtyp,String langugeLocale) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
       ArrayList subList = null;
  RevMgtDAO dao = new RevMgtDAO();
		try{
		list = dao.getOfficeNameList(disId,offtyp);
  
		RevMgtDTO dto = new RevMgtDTO();
  for (int i = 0; i < list.size(); i++) {
      subList = (ArrayList)list.get(i);
      dto = new RevMgtDTO();
      dto.setValue(subList.get(0).toString());
      if(langugeLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
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
	  *   Method Name  :   getRevenueCollectionCash()
	  *   Arguments    :   revenueDistrictId , revenueOfficeTypeName,revenueFromDate,revenueToDate , revenueDate
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	 public ArrayList getRevenueCollectionCash(String revenueDistrictId,String revenueOfficeTypeName,
			 String revenueFromDate,String revenueToDate ,RevMgtDTO dto,
			 String languageLocale) throws Exception{
		 
		
		ArrayList mainList = new ArrayList();
		 ArrayList list = new ArrayList();
		 RevMgtDAO dao = new RevMgtDAO();
		list =  dao.getRevenueCollectionCash(revenueDistrictId,revenueOfficeTypeName,revenueFromDate,revenueToDate,dto);
		
		String mode="";
		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		mode="Cash";
		}else{
			mode=CommonConstant.CASH_HINDI;
		}
		
		if(list.size()>0 && list !=null)
		{
			System.out.println("list size in BD is============"+list.size());
			for(int i=0;i<list.size();i++)
			{
				ArrayList  subList = (ArrayList) list.get(i);
				
				
				
				
				
				dto=new RevMgtDTO();
				
				
			
				
				/*rmdto.setCashId((String)(list.get(0)));
				rmdto.setGrossAmount((Double.parseDouble((String)list.get(1))));
			    rmdto.setCashDate((String)(list.get(2)));
				//rmdto.setCashStatus((String)list.get(6));
				rmdto.setCashPurpose((String)list.get(7));
				rmdto.setCashOfficeName((String)list.get(6));
				
				rmdto.setCashUserName((String)list.get(9));
				
		
				*/
				
				if(((String)subList.get(2)).equalsIgnoreCase("FUN_230") || ((String)subList.get(2)).equalsIgnoreCase("FUN_232")){// doc search download for a and b
					
					RevMgtDTO dto1=new RevMgtDTO();
					RevMgtDTO dto2=new RevMgtDTO();
					DecimalFormat myformatter=new DecimalFormat("###.##");
					double amount=Double.parseDouble((String)subList.get(6));
					double amountOneThird=amount  / 3;
					amountOneThird=Double.parseDouble(myformatter.format(amountOneThird));
						//Double.parseDouble(BigDecimal.valueOf(amountOneThird).toPlainString());
					double amountTwoThird=amount-amountOneThird;
					amountTwoThird=Double.parseDouble(myformatter.format(amountTwoThird));
					dto1.setCashTransactionId((String) (subList.get(0)));
					dto1.setCashCreatedDate((String) subList.get(1));
					dto1.setCashFunctionId((String) subList.get(2));

					if (languageLocale
							.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						dto1.setCashFunctionName((String) subList.get(3)
								+ " - Stamps");
					} else {
						dto1.setCashFunctionName((String) subList.get(7)
								+ " - स्टाम्प्स");// HINDI
					}
					dto1.setCashPaymentType(mode);// HINDI
					dto1.setCashUserCreatedBy((String) subList.get(5));

					dto1.setCashGrossAmt(Double.toString(amountOneThird));// stamp

					mainList.add(dto1);

					dto2.setCashTransactionId((String) (subList.get(0)));
					dto2.setCashCreatedDate((String) subList.get(1));
					dto2.setCashFunctionId((String) subList.get(2));

					if (languageLocale
							.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						dto2.setCashFunctionName((String) subList.get(3)
								+ " - Registration Fee");
					} else {
						dto2.setCashFunctionName((String) subList.get(7)
								+ " - पंजीकरण शुल्क");// HINDI
					}
					dto2.setCashPaymentType(mode);// HINDI
					dto2.setCashUserCreatedBy((String) subList.get(5));

					dto2.setCashGrossAmt(Double.toString(amountTwoThird));// reg
																			// fee

					mainList.add(dto2);
					
					
				}else{
				dto.setCashTransactionId((String)(subList.get(0)));
				 dto.setCashCreatedDate((String)subList.get(1));
				  dto.setCashFunctionId((String)subList.get(2));
				   
				  if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				    dto.setCashFunctionName((String)subList.get(3));
				  }else{
					  dto.setCashFunctionName((String)subList.get(7));//HINDI 
				  }
				    dto.setCashPaymentType(mode);//HINDI
				    dto.setCashUserCreatedBy((String)subList.get(5));
				    
				dto.setCashGrossAmt((String)subList.get(6));
			  
			  
	           mainList.add(dto);
				}
}			
	}
		
		 return mainList;
		
		
}

	 
	 /******************************************************************  
	  *   Method Name  :   getRevenueCollectionChallan()
	  *   Arguments    :   revenueDistrictId , revenueOfficeTypeName,revenueFromDate,revenueToDate , revenueDate
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	 public ArrayList getRevenueCollectionChallan(String radioMode,String revenueDistrictId,String revenueOfficeTypeName,
			 String revenueFromDate,String revenueToDate , RevMgtDTO dto, String languageLocale,Locale locale,String sroName) throws Exception{
		 
		
		ArrayList mainList = new ArrayList();
		 ArrayList list = new ArrayList();
		 RevMgtDAO dao = new RevMgtDAO();
		list =  dao.getRevenueCollectionChallan(radioMode,revenueDistrictId,revenueOfficeTypeName,revenueFromDate,revenueToDate,dto,locale,sroName);
		
		//String mode="Cash";
		
		if(list.size()>0 && list !=null)
		{
			System.out.println("list size in BD is============"+list.size());
			for(int i=0;i<list.size();i++)
			{
				ArrayList  subList = (ArrayList) list.get(i);
				
			
				
				dto=new RevMgtDTO();
				
				
			
				
				/*rmdto.setCashId((String)(list.get(0)));
				rmdto.setGrossAmount((Double.parseDouble((String)list.get(1))));
			    rmdto.setCashDate((String)(list.get(2)));
				//rmdto.setCashStatus((String)list.get(6));
				rmdto.setCashPurpose((String)list.get(7));
				rmdto.setCashOfficeName((String)list.get(6));
				
				rmdto.setCashUserName((String)list.get(9));
				
		
				*/
				if(((String)subList.get(2)).equalsIgnoreCase("FUN_230") || ((String)subList.get(2)).equalsIgnoreCase("FUN_232")){// doc search download for a and b
					
					RevMgtDTO dto1=new RevMgtDTO();
					RevMgtDTO dto2=new RevMgtDTO();
					DecimalFormat myformatter=new DecimalFormat("###.##");
					double amount=Double.parseDouble((String)subList.get(7));
					double amountOneThird=amount  / 3;
					amountOneThird=Double.parseDouble(myformatter.format(amountOneThird));
						//Double.parseDouble(BigDecimal.valueOf(amountOneThird).toPlainString());
					double amountTwoThird=amount-amountOneThird;
					amountTwoThird=Double.parseDouble(myformatter.format(amountTwoThird));
					
					
					double amount2=Double.parseDouble((String)subList.get(10));
					double amountOneThird2=amount2  / 3;
					amountOneThird2=Double.parseDouble(myformatter.format(amountOneThird2));
						//Double.parseDouble(BigDecimal.valueOf(amountOneThird).toPlainString());
					double amountTwoThird2=amount2-amountOneThird2;
					amountTwoThird2=Double.parseDouble(myformatter.format(amountTwoThird2));
					
					dto1.setCashTransactionId((String)(subList.get(0)));
					 dto1.setCashCreatedDate((String)subList.get(1));
					  dto1.setCashFunctionId((String)subList.get(2));
					   if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					    dto1.setCashFunctionName((String)subList.get(3)+ " - Stamps");
					    dto1.setCashPaymentType((String)subList.get(5));
					   }else{
						   dto1.setCashFunctionName((String)subList.get(8)+ " - स्टाम्प्स");
						    dto1.setCashPaymentType((String)subList.get(9)); 
					   }
					    dto1.setCashUserCreatedBy((String)subList.get(6));
					    
					dto1.setCashGrossAmt(Double.toString(amountOneThird));
					dto1.setAmtTreasury(Double.toString(amountOneThird2));
		           mainList.add(dto1);
		           
		           
		           dto2.setCashTransactionId((String)(subList.get(0)));
		           dto2.setCashCreatedDate((String)subList.get(1));
					 dto2.setCashFunctionId((String)subList.get(2));
					   if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
						   dto2.setCashFunctionName((String)subList.get(3)+ " - Registration Fee");
					    dto2.setCashPaymentType((String)subList.get(5));
					   }else{
						   dto2.setCashFunctionName((String)subList.get(8)+ " - पंजीकरण शुल्क");
						   dto2.setCashPaymentType((String)subList.get(9)); 
					   }
					   dto2.setCashUserCreatedBy((String)subList.get(6));
					    
					    dto2.setCashGrossAmt(Double.toString(amountTwoThird));
					dto2.setAmtTreasury(Double.toString(amountTwoThird2));
		           mainList.add(dto2);
					
					
				}
				else{
				dto.setCashTransactionId((String)(subList.get(0)));
				 dto.setCashCreatedDate((String)subList.get(1));
				  dto.setCashFunctionId((String)subList.get(2));
				   if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				    dto.setCashFunctionName((String)subList.get(3));
				    dto.setCashPaymentType((String)subList.get(5));
				   }else{
					   dto.setCashFunctionName((String)subList.get(8));
					    dto.setCashPaymentType((String)subList.get(9)); 
				   }
				    dto.setCashUserCreatedBy((String)subList.get(6));
				    
				dto.setCashGrossAmt((String)subList.get(7));
				dto.setAmtTreasury((String)subList.get(10));
	           mainList.add(dto);
				}
}			
	}
		
		 return mainList;
		
		
}
	 

	 /******************************************************************  
	  *   Method Name  :   getRevenueCollectionOnline()
	  *   Arguments    :   revenueDistrictId , revenueOfficeTypeName,revenueFromDate,revenueToDate , revenueDate
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	 public ArrayList getRevenueCollectionOnline(String radioMode,String revenueDistrictId,String revenueOfficeTypeName,
			 String revenueFromDate,String revenueToDate , RevMgtDTO dto,String languageLocale) throws Exception{
		 
		
		ArrayList mainList = new ArrayList();
		 ArrayList list = new ArrayList();
		 RevMgtDAO dao = new RevMgtDAO();
		list =  dao.getRevenueCollectionOnline(radioMode,revenueDistrictId,revenueOfficeTypeName,revenueFromDate,revenueToDate,dto);
		
		//String mode="Cash";
		
		if(list.size()>0 && list !=null)
		{
			
			for(int i=0;i<list.size();i++)
			{
				ArrayList  subList = (ArrayList) list.get(i);
				
				
				
				dto=new RevMgtDTO();
				
				
			
				
				/*rmdto.setCashId((String)(list.get(0)));
				rmdto.setGrossAmount((Double.parseDouble((String)list.get(1))));
			    rmdto.setCashDate((String)(list.get(2)));
				//rmdto.setCashStatus((String)list.get(6));
				rmdto.setCashPurpose((String)list.get(7));
				rmdto.setCashOfficeName((String)list.get(6));
				
				rmdto.setCashUserName((String)list.get(9));
				
		
				*/
				if(((String)subList.get(2)).equalsIgnoreCase("FUN_230") || ((String)subList.get(2)).equalsIgnoreCase("FUN_232")){// doc search download for a and b
					
					
					RevMgtDTO dto1=new RevMgtDTO();
					RevMgtDTO dto2=new RevMgtDTO();
					DecimalFormat myformatter=new DecimalFormat("###.##");
					double amount=Double.parseDouble((String)subList.get(10));
					double amountOneThird=amount  / 3;
					amountOneThird=Double.parseDouble(myformatter.format(amountOneThird));
						//Double.parseDouble(BigDecimal.valueOf(amountOneThird).toPlainString());
					double amountTwoThird=amount-amountOneThird;
					amountTwoThird=Double.parseDouble(myformatter.format(amountTwoThird));
					
					dto1.setCashTransactionId((String)(subList.get(0)));
					 dto1.setCashCreatedDate((String)subList.get(1));
					  dto1.setCashFunctionId((String)subList.get(2));
					   if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					    dto1.setCashFunctionName((String)subList.get(3)+ " - Stamps");
					    dto1.setCashPaymentType((String)subList.get(5));
					   }else{
						   dto1.setCashFunctionName((String)subList.get(8)+ " - स्टाम्प्स");
						    dto1.setCashPaymentType((String)subList.get(9)); 
					   }
					    dto1.setCashUserCreatedBy((String)subList.get(6));
					    
					dto1.setCashGrossAmt(Double.toString(amountOneThird));
					dto1.setAmtTreasury((String)subList.get(10));
		           mainList.add(dto1);
		           
		           
		           dto2.setCashTransactionId((String)(subList.get(0)));
		           dto2.setCashCreatedDate((String)subList.get(1));
					 dto2.setCashFunctionId((String)subList.get(2));
					   if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
						   dto2.setCashFunctionName((String)subList.get(3)+ " - Registration Fee");
					    dto2.setCashPaymentType((String)subList.get(5));
					   }else{
						   dto2.setCashFunctionName((String)subList.get(8)+ " - पंजीकरण शुल्क");
						   dto2.setCashPaymentType((String)subList.get(9)); 
					   }
					   dto2.setCashUserCreatedBy((String)subList.get(6));
					    
					    dto2.setCashGrossAmt((String)subList.get(7));
					dto2.setAmtTreasury(Double.toString(amountTwoThird));
		           mainList.add(dto2);
					
					
					
					
				}
				else{
				dto.setCashTransactionId((String)(subList.get(0)));
				 dto.setCashCreatedDate((String)subList.get(1));
				  dto.setCashFunctionId((String)subList.get(2));
				   if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				    dto.setCashFunctionName((String)subList.get(3));
				    dto.setCashPaymentType((String)subList.get(5));
				   }else{
					   dto.setCashFunctionName((String)subList.get(8));
					    dto.setCashPaymentType((String)subList.get(9));
				   }
				    dto.setCashUserCreatedBy((String)subList.get(6));
				    
				dto.setCashGrossAmt((String)subList.get(7));
				dto.setAmtTreasury((String)subList.get(10));
			  
	           mainList.add(dto);
				}
}			
	}
		
		 return mainList;
		
		
}
	 public String getOfficeName(String id,String languageLocale) throws Exception {
			
		 RevMgtDAO dao = new RevMgtDAO();
			return dao.getOfficeName(id,languageLocale);
		}
	 
	 /******************************************************************  
	  *   Method Name  :   getPurposeList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	  *   Author	   :  Shreeraj
	 *******************************************************************/  
	public ArrayList getPurposeList(String languageLocale) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
    ArrayList subList = null;
    RevMgtDAO dao = new RevMgtDAO();
		try{
		list = dao.getPurposeList();
    
		RevMgtDTO dto = new RevMgtDTO();
    for (int i = 0; i < list.size(); i++) {
        subList = (ArrayList)list.get(i);
        dto = new RevMgtDTO();
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
	  *   Method Name  :   getRevenueCollectionCombined()
	  *   Arguments    :   -
	  *   Return       :   int[]
	  *   Throws 	  :   Exception
	  *   Author	   :  Shreeraj
	 *******************************************************************/  
	 public double[] getRevenueCollectionCombined(String radioMode,String revenueDistrictId,String revenueOfficeTypeName,
			 String revenueFromDate,String revenueToDate ) throws Exception{
		 double arr[]=new double[4];
		 RevMgtDAO dao = new RevMgtDAO();
			arr =  dao.getRevenueCollectionCombined(radioMode,revenueDistrictId,revenueOfficeTypeName,revenueFromDate,revenueToDate);
		 return arr;
	 }
	
	 // START | changes to fetch credit limit param value by santosh
	    
	    public String getCreditLimitParam() throws Exception {
			String judLedgerParam = null;
			RevMgtDAO dao = new RevMgtDAO();
			judLedgerParam = dao.getCreditLimitParam();
			
			
			return judLedgerParam;
		}
	  
	 // END | changes to fetch credit limit param value by santosh
	    
// START | changes to fetch challan param value by santosh
	    
	    public String getChallanParam() throws Exception {
			String judLedgerParam = null;
			RevMgtDAO dao = new RevMgtDAO();
			judLedgerParam = dao.getChallanParam();
			
			
			return judLedgerParam;
		}
	  
	 // END | changes to fetch credit challan value by santosh
}
