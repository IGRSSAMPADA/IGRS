/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  DutyCalculationDAO.java
 * Author      :  Madan Mohan 
 * Description :   
*/


package com.wipro.igrs.newdutycalculation.dao;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newdutycalculation.dto.UserEnterableDTO;
import com.wipro.igrs.newdutycalculation.sql.CommonSQL;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;


//import org.apache.log4j.Logger;

//import com.wipro.igrs.db.DBUtility;


/**
 * @since 14 jan 2008 File Name: DutyCalculationDAO.java
 * @author Madan Mohan
 * @version 1.0 Created on 14 jan 2008
 * 
 */
public class DutyCalculationDAO {

	DBUtility dbUtility = null;
	Connection connTest;
	String sql = null;
	//CommonDTO dto = null;
	//ArrayList mainList = null;
	IGRSCommon igrsCommon = null;
	PreparedStatement pst = null;
	private  Logger logger = 
		(Logger) Logger.getLogger(DBUtility.class);

	/**
	 * @author Madan Mohan
	 * @throws Exception
	 */
	public DutyCalculationDAO() {
		
		
	}
	

	/*
	 * public static void main(String[] arg) {
	 * 
	 * Logger logger = (Logger) Logger.getLogger(DutyCalculationDAO.class);
	 * logger.debug("Inside DutyCalculationDAO");
	 *  }
	 */
	
	/**
	 * for getting property valuation flag corresponding to instrument id from db
	 * @param String id
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropValRequiredFlag(String id){

       
        String flag="";
        
        try {
        	String param[]={id};
        	dbUtility = new DBUtility();
        	
        	dbUtility.createPreparedStatement(CommonSQL.GET_PROP_VAL_FLAG);
        	
        	flag = dbUtility.executeQry(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}

        return flag;

}


	public ArrayList getMarketValue(String valId,String language) {
		ArrayList maketValuelList=null;
		 try {
	        	String param[]={valId};
	        	dbUtility = new DBUtility();
	        	if("en".equalsIgnoreCase(language))
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.GET_MARKET_VALUE_EN);

	        	}
	        	else
	        	{	
	        	dbUtility.createPreparedStatement(CommonSQL.GET_MARKET_VALUE_HI);
	        	}
	        	maketValuelList = dbUtility.executeQuery(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return maketValuelList;
	}


	public String getValuationIdLsit(String valId, String propertyID) {
		String valIdList=null;
		 try {
	        	String param[]={valId};
	        	dbUtility = new DBUtility();
	        	if("3".equalsIgnoreCase(propertyID))
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.GET_VALUATION_ID_AGI);

	        	}
	        	else
	        	{	
	        	dbUtility.createPreparedStatement(CommonSQL.GET_VALUATION_ID_PLOT_BUILD);
	        	}
	        	valIdList = dbUtility.executeQry(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return valIdList;
	}
	public ArrayList getUserEnterableField(String deedId,String instId,String language)
	{
		ArrayList userEnterableList=null;
		 try {
	        	String param[]={deedId,instId};
	        	dbUtility = new DBUtility();
	        	if("en".equalsIgnoreCase(language))
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.GET_USER_ENTERABLe_FIELD_EN);

	        	}
	        	else
	        	{	
	        	dbUtility.createPreparedStatement(CommonSQL.GET_USER_ENTERABLe_FIELD_HI);
	        	}
	        	userEnterableList = dbUtility.executeQuery(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return userEnterableList;
		
	}
	
	
	public ArrayList getUserEnterableFieldEstamp(String deedId,String instId,String language)
	{
		ArrayList userEnterableList=null;
		 try {
	        	String param[]={deedId,instId};
	        	dbUtility = new DBUtility();
	        	if("en".equalsIgnoreCase(language))
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.GET_USER_ENTERABLe_FIELD_ESTAMMP_EN);

	        	}
	        	else
	        	{	
	        	dbUtility.createPreparedStatement(CommonSQL.GET_USER_ENTERABLe_FIELD_ESTAMP_HI);
	        	}
	        	userEnterableList = dbUtility.executeQuery(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return userEnterableList;
		
	}
	public ArrayList getStampDutyDetails(String deedId,String instId)
	{
		ArrayList stampDetails=null;
		 try {
	        	String param[]={deedId,instId};
	        	dbUtility = new DBUtility();
	        	dbUtility.createPreparedStatement(CommonSQL.GET_STAMP_DUTY_DETAILS);//test	        	
	        	stampDetails = dbUtility.executeQuery(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return stampDetails;
	}
	public ArrayList getRegFeeDetails(String deedId,String instId)
	{
		ArrayList stampDetails=null;
		 try {
	        	String param[]={deedId,instId};
	        	dbUtility = new DBUtility();
	        	dbUtility.createPreparedStatement(CommonSQL.GET_REG_FEE_DETAILS);	        	
	        	stampDetails = dbUtility.executeQuery(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return stampDetails;
	}


	public String getPropertyNAme(String propId, String language) {
		String propName=null;
		 try {
	        	String param[]={propId};
	        	dbUtility = new DBUtility();
	        	if("en".equalsIgnoreCase(language))
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_EN);

	        	}
	        	else
	        	{	
	        	dbUtility.createPreparedStatement(CommonSQL.GET_PROPERTY_NAME_HI);
	        	}
	        	propName = dbUtility.executeQry(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return propName;
	}


	public String getUpkarFlag(int instId) {

		String upkarFlag=null;
		 try {
	        	String param[]={String.valueOf(instId)};
	        	dbUtility = new DBUtility();
	        		
	        	dbUtility.createPreparedStatement(CommonSQL.GET_UPKAR_FLAG);
	        	
	        	upkarFlag = dbUtility.executeQry(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return upkarFlag;
	
	}


	public String getUpkarValue() {
		String upkarValue=null;
		 try {
	        	
	        	dbUtility = new DBUtility();
	        		
	        	dbUtility.createStatement();
	        	
	        	upkarValue = dbUtility.executeQry(CommonSQL.GET_UPKAR_VALUE);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return upkarValue;
	}	
	public ArrayList janpadMunicipalDetails(String deedId,String instId)
	{

		ArrayList janpadDetails=null;
		 try {
	        	String param[]={deedId,instId};
	        	dbUtility = new DBUtility();
	        	dbUtility.createPreparedStatement(CommonSQL.GET_JANPAD_DETAILS);	        	
	        	janpadDetails = dbUtility.executeQuery(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return janpadDetails;
	
	}
	public ArrayList getMunicipalId(String  valuationId) {

		 ArrayList list=null;
		 try {
	        	String param[]={String.valueOf(valuationId)};
	        	dbUtility = new DBUtility();
	        		
	        	dbUtility.createPreparedStatement(CommonSQL.GET_MUNICIPAL_ID);
	        	
	        	list = dbUtility.executeQuery(param);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return list;
	
	}

	public String getSequenceID(String sql)
	{
		String id=null;
		 try {
	        	
	        	dbUtility = new DBUtility();
	        		
	        	dbUtility.createStatement();
	        	
	        	id = dbUtility.executeQry(sql);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return id;
	}

	public String submitDutyDetails(DutyCalculationDTO dto,
			InstrumentsDTO insDTO) {
		
		String transactionType="";
		if(insDTO.getInstId()==2007||insDTO.getInstId()==2030||insDTO.getInstId()==2060)
		{
			if("D".equalsIgnoreCase(dto.getTypeOfTransaction()))
			{
				transactionType="D";
			}
			else
			{
				transactionType="N";
			}
		}
		String mainDutyId=getSequenceID(CommonSQL.GET_MAIN_DUTY_ID);
		dto.setMainDutyTxnId(mainDutyId);
		String familyCheck="";
		String govCheck="";
		if("Y".equalsIgnoreCase(dto.getFamilyCheckFlag()))
		{
			familyCheck="Y";
		}
		else
		{
			familyCheck="N";
		}
		if("Y".equalsIgnoreCase(dto.getGovCheckFlag()))
		{
			govCheck="Y";
		}
		else
		{
			govCheck="N";
		}
		double payableStamp=0;
		double payablereg=0;
		double totalpayableStamp=0;
		double totalpayablereg=0;
		if(dto.getExemptionDescpList()==null||dto.getExemptionDescpList().size()==0)
		{
			payableStamp=dto.getPayableStampDuty();
			totalpayableStamp=dto.getTotalPayableStamp();
		}
		else
		{
			payableStamp=dto.getDutyafterExemption();
			totalpayableStamp=dto.getDutyafterExemptionTotal();

		}
		if(dto.getRegFeeExemptionDiscriptionList()==null||dto.getRegFeeExemptionDiscriptionList().size()==0)
		{
			payablereg=dto.getPayableRegFee();
			totalpayablereg=dto.getTotalPayableReg();
		}
		else
		{
			payablereg=dto.getRegFeeafterExemp();
			totalpayablereg=dto.getRegFeeafterExempTotal();

		}
		try
		{
			
		if("Y".equalsIgnoreCase(dto.getPropertyValuationRequired()))
		{
			if(dto.getDeedId()==1054)
			{
				if(dto.getAlreadyPaidRegFee()==null||dto.getAlreadyPaidRegFee().equalsIgnoreCase(""))
				{
					dto.setAlreadyPaidRegFee("0");
				}
				if(dto.getAlreadyPaidStampDuty()==null||dto.getAlreadyPaidStampDuty().equalsIgnoreCase(""))
				{
					dto.setAlreadyPaidStampDuty("0");
				}
				String []arr=new String[24];//24 BY ROOPAM-FOR EWS FLAG-REQ AT THE TIME OF CONSENTER DUTY CALCULATION
				arr[0]=mainDutyId;
				arr[1]=String.valueOf(dto.getStampDuty());
				arr[2]=String.valueOf(dto.getNagarPalikaDuty());
				arr[3]=String.valueOf(dto.getPanchayatDuty());
				arr[4]=String.valueOf(dto.getUpkarDuty());
				arr[6]=String.valueOf(dto.getTotalDisplay());
				arr[5]=String.valueOf(dto.getRegFee());
				arr[7]=String.valueOf(dto.getDeedId());
				arr[8]=String.valueOf(insDTO.getInstId());
				arr[9]=String.valueOf(dto.getAlreadyPaidRegFee());
				arr[10]=String.valueOf(dto.getAlreadyPaidStampDuty());
				arr[11]=dto.getUserId();
				arr[12]=dto.getCancellationFlag();
				if("Y".equalsIgnoreCase(dto.getCancellationFlag()))
				{	
				arr[13]="2025";
				}
				else
				{
				arr[13]="0";	
				}
				if(dto.getExchangeMV1()>dto.getExchangeMV2())
				{
					arr[14]=String.valueOf((dto.getExchangeMV1()));
				}
				else
				{
					arr[14]=String.valueOf((dto.getExchangeMV2()));

				}
				arr[15]=String.valueOf(payableStamp);
				arr[16]=String.valueOf(payablereg); 
				arr[17]=String.valueOf(dto.getMinusExempStamp());
				arr[18]=String.valueOf(dto.getMinusExempReg());	
				arr[19]=familyCheck;
				arr[20]=govCheck;
				arr[21]=String.valueOf(0);
				arr[22]=transactionType;
				arr[23]=dto.getEwsAppliedFlag();//BY ROOPAM-FOR EWS FLAG-REQ AT THE TIME OF CONSENTER DUTY CALCULATION
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.INSERT_DUTY_MAIN);//ews applied flag to be inserted
				boolean flag=dbUtility.executeUpdate(arr);
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.debug(e);				
				}
				if(flag)
				{
				ArrayList propertyList=dto.getExchangeList1();
				if(propertyList!=null && propertyList.size()>0)
				{
					for(int i=0;i<propertyList.size();i++)
					{	
					DutyCalculationDTO ddto=(DutyCalculationDTO) propertyList.get(i);
					String propDutyId=getSequenceID(CommonSQL.GET_PROP_DUTY_ID);
					String []arr1=new String[17];
					if(ddto.getAlreadyPaidRegFee()==null||ddto.getAlreadyPaidRegFee().equalsIgnoreCase(""))
					{
						ddto.setAlreadyPaidRegFee("0");
					}
					if(ddto.getAlreadyPaidStampDuty()==null||ddto.getAlreadyPaidStampDuty().equalsIgnoreCase(""))
					{
						ddto.setAlreadyPaidStampDuty("0");
					}
					if(ddto.getTotal()==null)
					{
						ddto.setTotal(0.0);
					}
					arr1[0]=propDutyId;
					arr1[1]=String.valueOf(ddto.getStampDuty());
					arr1[2]=String.valueOf(ddto.getNagarPalikaDuty());
					arr1[3]=String.valueOf(ddto.getPanchayatDuty());
					arr1[4]=String.valueOf(ddto.getUpkarDuty());
					arr1[5]=String.valueOf(ddto.getTotal());
					arr1[6]=String.valueOf(ddto.getRegFee());
					//arr1[7]=String.valueOf(dto.getDeedId());
				//	arr1[8]=String.valueOf(insDTO.getInstId());
					arr1[7]=String.valueOf(ddto.getAlreadyPaidRegFee());
					arr1[8]=String.valueOf(ddto.getAlreadyPaidStampDuty());
					arr1[9]=dto.getUserId();
					arr1[10]=ddto.getValuationId();
					arr1[11]=mainDutyId;
					arr1[12]=ddto.getMarketValue();
					arr1[13]="1";
					arr1[14]=String.valueOf("0");
					arr1[15]=String.valueOf(0);
					arr1[16]=String.valueOf(0);
					dbUtility = new DBUtility();
					dbUtility.createPreparedStatement(CommonSQL.INSERT_PROP_DUTY_DETAILS);
					boolean flag1=dbUtility.executeUpdate(arr1);
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						logger.debug(e);				
					}
					if(flag1)
					{	
					ArrayList userValueList=ddto.getUserValueList();
					if(userValueList!=null && userValueList.size()>0)
					{
						for(int j=0;j<userValueList.size();j++)
						{
							UserEnterableDTO idto=(UserEnterableDTO) userValueList.get(j);
							String arr2[]=new String[5];
							arr2[0]=mainDutyId;
							arr2[1]=propDutyId;      
							arr2[2]=idto.getId();
							arr2[3]=idto.getValue();
							arr2[4]=dto.getUserId();
							dbUtility = new DBUtility();
							dbUtility.createPreparedStatement(CommonSQL.INSERT_FIELD_VALUE);
							boolean flag2=dbUtility.executeUpdate(arr2);
							try {
								dbUtility.closeConnection();
							} catch (Exception e) {
								logger.debug(e);				
							}
							if(!flag2)
							{
								dbUtility=new DBUtility();
								dbUtility.rollback();
								try {
									dbUtility.closeConnection();
								} catch (Exception e) {
									logger.debug(e);				
								}
								return "fail";
							}
							
						}
					}
				}
				else
				{
					dbUtility=new DBUtility();
					dbUtility.rollback();
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						logger.debug(e);				
					}
					return "fail";
				}
					}
				}
				 propertyList=dto.getExchangeList2();
				if(propertyList!=null && propertyList.size()>0)
				{

					for(int i=0;i<propertyList.size();i++)
					{	
					DutyCalculationDTO ddto=(DutyCalculationDTO) propertyList.get(i);
					if(ddto.getAlreadyPaidRegFee()==null||ddto.getAlreadyPaidRegFee().equalsIgnoreCase(""))
					{
						ddto.setAlreadyPaidRegFee("0");
					}
					if(ddto.getAlreadyPaidStampDuty()==null||ddto.getAlreadyPaidStampDuty().equalsIgnoreCase(""))
					{
						ddto.setAlreadyPaidStampDuty("0");
					}
					if(ddto.getTotal()==null)
					{
						ddto.setTotal(0.0);
					}
					String propDutyId=getSequenceID(CommonSQL.GET_PROP_DUTY_ID);
					String []arr1=new String[17];
					arr1[0]=propDutyId;
					arr1[1]=String.valueOf(ddto.getStampDuty());
					arr1[2]=String.valueOf(ddto.getNagarPalikaDuty());
					arr1[3]=String.valueOf(ddto.getPanchayatDuty());
					arr1[4]=String.valueOf(ddto.getUpkarDuty());
					arr1[5]=String.valueOf(ddto.getTotal());
					arr1[6]=String.valueOf(ddto.getRegFee());
					arr1[7]=String.valueOf(ddto.getAlreadyPaidRegFee());
					arr1[8]=String.valueOf(ddto.getAlreadyPaidStampDuty());
					arr1[9]=dto.getUserId();
					arr1[10]=ddto.getValuationId();
					arr1[11]=mainDutyId;
					arr1[12]=ddto.getMarketValue();
					arr1[13]="2";
					arr1[14]=String.valueOf(0);
					arr1[15]=String.valueOf(0);
					arr1[15]=String.valueOf(16);
					dbUtility = new DBUtility();
					dbUtility.createPreparedStatement(CommonSQL.INSERT_PROP_DUTY_DETAILS);
					boolean flag1=dbUtility.executeUpdate(arr1);
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						logger.debug(e);				
					}
					if(flag1)
					{	
					ArrayList userValueList=ddto.getUserValueList();
					if(userValueList!=null && userValueList.size()>0)
					{
						for(int j=0;j<userValueList.size();j++)
						{
							UserEnterableDTO idto=(UserEnterableDTO) userValueList.get(j);
							String arr2[]=new String[5];
							arr2[0]=mainDutyId;
							arr2[1]=propDutyId;      
							arr2[2]=idto.getId();
							arr2[3]=idto.getValue();
							arr2[4]=dto.getUserId();
							dbUtility = new DBUtility();
							dbUtility.createPreparedStatement(CommonSQL.INSERT_FIELD_VALUE);
							boolean flag2=dbUtility.executeUpdate(arr2);
							try {
								dbUtility.closeConnection();
							} catch (Exception e) {
								logger.debug(e);				
							}
							if(!flag2)
							{
								dbUtility=new DBUtility();
								dbUtility.rollback();
								try {
									dbUtility.closeConnection();
								} catch (Exception e) {
									logger.debug(e);				
								}
								return "fail";
							}
							
						}
					}
				}
				else
				{
					dbUtility=new DBUtility();
					dbUtility.rollback();
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						logger.debug(e);				
					}
					return "fail";
				}
					}
				
				}
				
			}	
			
			}
			else{
			String []arr=new String[24];
			if(dto.getAlreadyPaidRegFee()==null||dto.getAlreadyPaidRegFee().equalsIgnoreCase(""))
			{
				dto.setAlreadyPaidRegFee("0");
			}
			if(dto.getAlreadyPaidStampDuty()==null||dto.getAlreadyPaidStampDuty().equalsIgnoreCase(""))
			{
				dto.setAlreadyPaidStampDuty("0");
			}
			arr[0]=mainDutyId;
			if(insDTO.getInstId()==2108||insDTO.getInstId()==2106||insDTO.getInstId()==2105||insDTO.getInstId()==2184)
			{
				arr[1]=String.valueOf(Math.ceil(dto.getStampDuty()));
				arr[2]=String.valueOf(Math.ceil(dto.getNagarPalikaDuty()));
				arr[3]=String.valueOf(dto.getPanchayatDuty());
				arr[4]=String.valueOf(Math.ceil(dto.getUpkarDuty()));
				arr[6]=String.valueOf(dto.getTotalDisplay());
				arr[5]=String.valueOf(Math.ceil(dto.getRegFee()));
				arr[7]=String.valueOf(dto.getDeedId());
				arr[8]=String.valueOf(insDTO.getInstId());
				arr[14]=String.valueOf(dto.getPartitionMV()-dto.getMaxPartitionMv());
				arr[15]=String.valueOf(Math.ceil(payableStamp));
				arr[16]=String.valueOf(Math.ceil(payablereg));
				arr[17]=String.valueOf(dto.getMinusExempStamp());
				arr[18]=String.valueOf(dto.getMinusExempReg());	
			}
			else
			{	
			arr[1]=String.valueOf(Math.ceil(dto.getTotalStampDuty()));
			arr[2]=String.valueOf(dto.getTotalNagarPalika());
			arr[3]=String.valueOf(dto.getTotalPanchyat());
			arr[4]=String.valueOf(Math.ceil(dto.getTotalUpkar()));
			arr[6]=String.valueOf(dto.getEntireTotalDisplay());
			arr[5]=String.valueOf(dto.getTotalregFee());
			arr[7]=String.valueOf(dto.getDeedId());
			arr[8]=String.valueOf(insDTO.getInstId());
			arr[14]="0";
			if(insDTO.getInstId()==2220||insDTO.getInstId()==2219){
				arr[15]=String.valueOf(Math.ceil(payableStamp));	
			}
			else{
			arr[15]=String.valueOf(Math.ceil(totalpayableStamp));
			}
			arr[16]=String.valueOf(Math.ceil(totalpayablereg));
			arr[17]=String.valueOf(dto.getMinusExempStampTotal());
			arr[18]=String.valueOf(dto.getMinusExempRegTotal());	
			}
			arr[19]=familyCheck;
			arr[20]=govCheck;
			arr[21]=String.valueOf(dto.getTotalGovValue());
			if(insDTO.getInstId()==2108||insDTO.getInstId()==2106)
			{
				arr[9]=String.valueOf(dto.getAlreadyPaidRegFee());
				arr[10]=String.valueOf(dto.getAlreadyPaidStampDuty());
			}
			else
			{	
			arr[9]=String.valueOf(dto.getTotalPaidReg());
			arr[10]=String.valueOf(dto.getTotalPaidStamp());
			}
			arr[11]=dto.getUserId();
			arr[12]=dto.getCancellationFlag();
			if("Y".equalsIgnoreCase(dto.getCancellationFlag()))
			{	
			arr[13]="2025";
			}
			else
			{
			arr[13]="0";	
			}
			arr[22]=transactionType;
			arr[23]=dto.getEwsAppliedFlag();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_DUTY_MAIN);
			boolean flag=dbUtility.executeUpdate(arr);
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);				
			}
			if(flag)
			{
			ArrayList propertyList=dto.getPropertyDetailsList();
			if(propertyList!=null && propertyList.size()>0)
			{
				for(int i=0;i<propertyList.size();i++)
				{	
				DutyCalculationDTO ddto=(DutyCalculationDTO) propertyList.get(i);
				if(ddto.getAlreadyPaidRegFee()==null||ddto.getAlreadyPaidRegFee().equalsIgnoreCase(""))
				{
					ddto.setAlreadyPaidRegFee("0");
				}
				if(ddto.getAlreadyPaidStampDuty()==null||ddto.getAlreadyPaidStampDuty().equalsIgnoreCase(""))
				{
					ddto.setAlreadyPaidStampDuty("0");
				}
				if(ddto.getTotal()==null)
				{
					ddto.setTotal(0.0);
				}
				String propDutyId=getSequenceID(CommonSQL.GET_PROP_DUTY_ID);
				String []arr1=new String[17];
				arr1[0]=propDutyId;
				arr1[1]=String.valueOf(ddto.getStampDuty());
				arr1[2]=String.valueOf(ddto.getNagarPalikaDuty());
				arr1[3]=String.valueOf(ddto.getPanchayatDuty());
				arr1[4]=String.valueOf(ddto.getUpkarDuty());
				arr1[6]=String.valueOf(ddto.getRegFee());
				arr1[7]=String.valueOf(ddto.getAlreadyPaidRegFee());
				arr1[8]=String.valueOf(ddto.getAlreadyPaidStampDuty());
				arr1[9]=dto.getUserId();
				arr1[10]=ddto.getValuationId();
				arr1[11]=mainDutyId;
				arr1[12]=ddto.getMarketValue();
				arr1[13]="";
				if(insDTO.getInstId()==2108||insDTO.getInstId()==2106||insDTO.getInstId()==2105||insDTO.getInstId()==2184)
				{
					arr1[5]=String.valueOf(0);
					arr1[14]=String.valueOf(0);
					arr1[15]=String.valueOf(0);
				}
				else
				{
				arr1[5]=String.valueOf(ddto.getDashTotal());
				arr1[14]=String.valueOf(ddto.getDashPayTotal());
				arr1[15]=String.valueOf(ddto.getDashPayReg());
				}
				if(ddto.getGovValue()==null||ddto.getGovValue().equalsIgnoreCase(""))
					
				{
				arr1[16]=String.valueOf(0);
				}
				else
				{
					arr1[16]=String.valueOf(ddto.getGovValue());
				}
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.INSERT_PROP_DUTY_DETAILS);
				boolean flag1=dbUtility.executeUpdate(arr1);
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.debug(e);				
				}
				String []arr9=new String[7];
				arr9[0]=mainDutyId;
				arr9[1]=String.valueOf(ddto.getValuationId());
				arr9[2]=ddto.getDevelopmentCharges();
				arr9[3]=ddto.getAdditionalPremium();
				arr9[4]=ddto.getPremium();
				arr9[5]=ddto.getOtherCharges();
				arr9[6]=ddto.getUserId();

				
				
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.INSERT_DUTY_PREMIUM_DETAILS);
				boolean flag9=dbUtility.executeUpdate(arr9);
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.debug(e);				
				}
				ArrayList rentList =ddto.getRentArrayList();
				for(int k=0;k<rentList.size();k++)
				{
					DutyCalculationDTO fdto=(DutyCalculationDTO) rentList.get(k);
					String []arr10=new String[7];
					arr10[0]=mainDutyId;
					arr10[1]=String.valueOf(ddto.getValuationId());
					arr10[2]=fdto.getMothlyRent();
					arr10[3]=fdto.getMaintence();
					arr10[4]=fdto.getPeriodYear();
					arr10[5]=fdto.getPeriodMonth();
					arr10[6]=fdto.getUserId();

					
					
					dbUtility = new DBUtility();
					dbUtility.createPreparedStatement(CommonSQL.INSERT_DUTY_RENT_DETAILS);
					boolean flag10=dbUtility.executeUpdate(arr10);
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						logger.debug(e);				
					}
				}
				
				
				if(flag1)
				{	
				ArrayList userValueList=ddto.getUserValueList();
				if(userValueList!=null && userValueList.size()>0)
				{
					for(int j=0;j<userValueList.size();j++)
					{
						UserEnterableDTO idto=(UserEnterableDTO) userValueList.get(j);
						String arr2[]=new String[5];
						arr2[0]=mainDutyId;
						arr2[1]=propDutyId;      
						arr2[2]=idto.getId();
						arr2[3]=idto.getValue();
						arr2[4]=dto.getUserId();
						dbUtility = new DBUtility();
						dbUtility.createPreparedStatement(CommonSQL.INSERT_FIELD_VALUE);
						boolean flag2=dbUtility.executeUpdate(arr2);
						try {
							dbUtility.closeConnection();
						} catch (Exception e) {
							logger.debug(e);				
						}
						if(!flag2)
						{
							dbUtility=new DBUtility();
							dbUtility.rollback();
							try {
								dbUtility.closeConnection();
							} catch (Exception e) {
								logger.debug(e);				
							}
							return "fail";
						}
						
					}
				}
			}
			else
			{
				dbUtility=new DBUtility(); 
				dbUtility.rollback();
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.debug(e);				
				}
				return "fail";
			}
				}
			}
			
		if(insDTO.getInstId()==2105||insDTO.getInstId()==2184)
		{
			String arr2[]=new String[5];
			arr2[0]=mainDutyId;
			arr2[1]="";      
			arr2[2]="64";
			arr2[3]=dto.getNoOfSperatedPart();
			arr2[4]=dto.getUserId();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_FIELD_VALUE);
			boolean flag2=dbUtility.executeUpdate(arr2);
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);				
			}
			if(!flag2)
			{
				dbUtility=new DBUtility();
				dbUtility.rollback();
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.debug(e);				
				}
				return "fail";
			}
		}
		else if(insDTO.getInstId()==2106||insDTO.getInstId()==2108)	
		{
			String arr2[]=new String[5];
			arr2[0]=mainDutyId;
			arr2[1]="";      
			if(insDTO.getInstId()==2106)
			{	
			arr2[2]="65";
			}
			else
			{
				arr2[2]="69";	
			}
			arr2[3]=dto.getNoOfSperatedPart();
			arr2[4]=dto.getUserId();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_FIELD_VALUE);
			boolean flag2=dbUtility.executeUpdate(arr2);
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);				
			}
			if(!flag2)
			{
				dbUtility=new DBUtility();
				dbUtility.rollback();
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.debug(e);				
				}
				return "fail";
			}
			arr2[0]=mainDutyId;
			arr2[1]="";      
			if(insDTO.getInstId()==2106)
			{	
			arr2[2]="66";
			}
			else
			{
				arr2[2]="70";
			}
			arr2[3]=dto.getAlreadyPaidStampDuty();
			arr2[4]=dto.getUserId();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_FIELD_VALUE);
			boolean flag3=dbUtility.executeUpdate(arr2);
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);				
			}
			if(!flag3)
			{
				dbUtility=new DBUtility();
				dbUtility.rollback();
				return "fail";
			}
		}
		}	
		}
		}
		else
		{
			if(dto.getAlreadyPaidRegFee()==null||dto.getAlreadyPaidRegFee().equalsIgnoreCase(""))
			{
				dto.setAlreadyPaidRegFee("0");
			}
			if(dto.getAlreadyPaidStampDuty()==null||dto.getAlreadyPaidStampDuty().equalsIgnoreCase(""))
			{
				dto.setAlreadyPaidStampDuty("0");
			}
			String []arr=new String[24];
			arr[0]=mainDutyId;
			arr[1]=String.valueOf(dto.getStampDuty());
			arr[2]=String.valueOf(dto.getNagarPalikaDuty());
			arr[3]=String.valueOf(dto.getPanchayatDuty());
			arr[4]=String.valueOf(dto.getUpkarDuty());
			arr[6]=String.valueOf(dto.getTotalDisplay());
			arr[5]=String.valueOf(dto.getRegFee());
			arr[7]=String.valueOf(dto.getDeedId());
			arr[8]=String.valueOf(insDTO.getInstId());
			arr[9]=String.valueOf(dto.getAlreadyPaidRegFee());
			arr[10]=String.valueOf(dto.getAlreadyPaidStampDuty());
			arr[11]=dto.getUserId();
			arr[12]=dto.getCancellationFlag();
			if("Y".equalsIgnoreCase(dto.getCancellationFlag()))
			{	
			arr[13]="2025";
			}
			else
			{
			arr[13]="0";	
			}
			if(insDTO.getInstId()==2107)
			{
				arr[14]=String.valueOf((dto.getTotalLandRevenue()-dto.getMaxLandRevenue()));
			}
			else
			{
				arr[14]="0";
			}
			arr[15]=String.valueOf(payableStamp);
			arr[16]=String.valueOf(payablereg);
			arr[17]=String.valueOf(dto.getMinusExempStamp());
			arr[18]=String.valueOf(dto.getMinusExempReg());
			arr[19]=familyCheck;
			arr[20]=govCheck;
			if(dto.getGovValue()==null||dto.getGovValue().equalsIgnoreCase(""))
				
			{
			arr[21]=String.valueOf(0);
			}
			else
			{
				arr[21]=String.valueOf(dto.getGovValue());
			}
			arr[22]=transactionType;
			arr[23]=dto.getEwsAppliedFlag();			
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_DUTY_MAIN);
			boolean flag=dbUtility.executeUpdate(arr);
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);				
			}
			String []arr9=new String[7];
			arr9[0]=mainDutyId;
			arr9[1]=String.valueOf(0);
			arr9[2]=dto.getDevelopmentCharges();
			arr9[3]=dto.getAdditionalPremium();
			arr9[4]=dto.getPremium();
			arr9[5]=dto.getOtherCharges();
			arr9[6]=dto.getUserId();

			
			
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_DUTY_PREMIUM_DETAILS);
			boolean flag9=dbUtility.executeUpdate(arr9);
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);				
			}
			ArrayList rentList =dto.getRentArrayList();
			for(int k=0;k<rentList.size();k++)
			{
				DutyCalculationDTO fdto=(DutyCalculationDTO) rentList.get(k);
				String []arr10=new String[7];
				arr10[0]=mainDutyId;
				arr10[1]=String.valueOf(0);
				arr10[2]=fdto.getMothlyRent();
				arr10[3]=fdto.getMaintence();
				arr10[4]=fdto.getPeriodYear();
				arr10[5]=fdto.getPeriodMonth();
				arr10[6]=fdto.getUserId();

				
				
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.INSERT_DUTY_RENT_DETAILS);
				boolean flag10=dbUtility.executeUpdate(arr10);
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.debug(e);				
				}
			}
			
			if(flag){
				if(insDTO.getInstId()==2107)
				{

					ArrayList userValueList=dto.getLandRevenueList();
					if(userValueList!=null && userValueList.size()>0)
					{
						for(int i=0;i<userValueList.size();i++)
						{
							DutyCalculationDTO idto=(DutyCalculationDTO) userValueList.get(i);
							String arr1[]=new String[5];
							arr1[0]=mainDutyId;
							arr1[1]="";       //propDuty id is null in dat case
							arr1[2]="68";
							arr1[3]=idto.getAvgLandRevenue();
							arr1[4]=dto.getUserId();
							dbUtility = new DBUtility();
							dbUtility.createPreparedStatement(CommonSQL.INSERT_FIELD_VALUE);
							boolean flag2=dbUtility.executeUpdate(arr1);
							try {
								dbUtility.closeConnection();
							} catch (Exception e) {
								logger.debug(e);				
							}
							if(!flag2)
							{
								dbUtility=new DBUtility();
								dbUtility.rollback();
								try {
									dbUtility.closeConnection();
								} catch (Exception e) {
									logger.debug(e);				
								}
								return "fail";
							}
							
						}
					}
					
				}
			else{
			ArrayList userValueList=dto.getUserValueList();
			if(userValueList!=null && userValueList.size()>0)
			{
				for(int i=0;i<userValueList.size();i++)
				{
					UserEnterableDTO idto=(UserEnterableDTO) userValueList.get(i);
					String arr1[]=new String[5];
					arr1[0]=mainDutyId;
					arr1[1]="";       //propDuty id is null in dat case
					arr1[2]=idto.getId();
					arr1[3]=idto.getValue();
					arr1[4]=dto.getUserId();
					dbUtility = new DBUtility();
					dbUtility.createPreparedStatement(CommonSQL.INSERT_FIELD_VALUE);
					boolean flag2=dbUtility.executeUpdate(arr1);
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						logger.debug(e);				
					}
					if(!flag2)
					{
						dbUtility=new DBUtility();
						dbUtility.rollback();
						try {
							dbUtility.closeConnection();
						} catch (Exception e) {
							logger.debug(e);				
						}
						return "fail";
					}
					
				}
			}
			}
			}
			else
			{
				dbUtility=new DBUtility();
				dbUtility.rollback();
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.debug(e);				
				}
				return "fail";
			}
			
		}
		
		// added by ankit for plant and machinery 
		//if("Y".equalsIgnoreCase(insDTO.getMovablePropFlag())){
		
		ArrayList propertyList=dto.getPropertyDetailsList();
		if(propertyList!=null && propertyList.size()>0)
		{
			for(int i=0;i<propertyList.size();i++)
			{	
				DutyCalculationDTO ddto=(DutyCalculationDTO) propertyList.get(i);
			
				if(ddto.getValueOfMovableProp()!=null && !ddto.getValueOfMovableProp().isEmpty()){
					if(Long.parseLong(ddto.getValueOfMovableProp())>0){
						String []arr =new String[9];
						arr[0] = mainDutyId;
						arr[1] = String.valueOf(ddto.getStampDutyOnMovProp());
						arr[2] = String.valueOf(ddto.getMunicipalDutyOnMovProp());
						arr[3] = String.valueOf(ddto.getUpkarDutyOnMovProp());
						arr[4] = String.valueOf(ddto.getJanpadDutyOnMovProp());
						arr[5] = String.valueOf(ddto.getRegFeeOnMovProp());
						arr[6] = String.valueOf(ddto.getTotalDutyOnMovProp());
						arr[7] = String.valueOf(ddto.getValueOfMovableProp());
						arr[8]=dto.getUserId();
						
						dbUtility = new DBUtility();
						dbUtility.createPreparedStatement(CommonSQL.INSERT_MV_PR_DUTY_TXN_DETAILS);
						boolean flag2=dbUtility.executeUpdate(arr);
						try {
							dbUtility.closeConnection();
						} catch (Exception e) {
							logger.debug(e);				
						}
					}
					
				}
			
			}
		}else{
				if(insDTO.getValueOfMovableProp()!=null && !insDTO.getValueOfMovableProp().isEmpty()){
					if(Long.parseLong(insDTO.getValueOfMovableProp())>0){
						String []arr =new String[9];
						arr[0] = mainDutyId;
						arr[1] = String.valueOf(dto.getStampDutyOnMovProp());
						arr[2] = String.valueOf(dto.getMunicipalDutyOnMovProp());
						arr[3] = String.valueOf(dto.getUpkarDutyOnMovProp());
						arr[4] = String.valueOf(dto.getJanpadDutyOnMovProp());
						arr[5] = String.valueOf(dto.getRegFeeOnMovProp());
						arr[6] = String.valueOf(dto.getTotalDutyOnMovProp());
						arr[7] = String.valueOf(insDTO.getValueOfMovableProp());
						arr[8]=dto.getUserId();
						
						dbUtility = new DBUtility();
						dbUtility.createPreparedStatement(CommonSQL.INSERT_MV_PR_DUTY_TXN_DETAILS);
						boolean flag2=dbUtility.executeUpdate(arr);
						try {
							dbUtility.closeConnection();
						} catch (Exception e) {
							logger.debug(e);				
						}
					}
					
				}
			
		}
		
		
		
		ArrayList exeList=dto.getExemptionDescpList();
		ArrayList regList=dto.getRegFeeExemptionDiscriptionList();
		for(int i=0;i<exeList.size();i++)
		{
			DutyCalculationDTO ddto=(DutyCalculationDTO) exeList.get(i);
			String []arr =new String[3];
			arr[0]=mainDutyId;
			arr[1]=ddto.getExempId();
			arr[2]=dto.getUserId();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_EXEMPTION_DETAILS);
			boolean flag2=dbUtility.executeUpdate(arr);
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);				
			}
		}
		for(int i=0;i<regList.size();i++)
		{
			DutyCalculationDTO ddto=(DutyCalculationDTO) regList.get(i);
			String []arr =new String[3];
			arr[0]=mainDutyId;
			arr[1]=ddto.getExempId();
			arr[2]=dto.getUserId();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_EXEMPTION_DETAILS);
			boolean flag2=dbUtility.executeUpdate(arr);
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);				
			}
		}
		
		}catch (Exception exception) {

            System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

    }finally{
		try {
			dbUtility=new DBUtility();
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
		}
	}	
	dto.setMainDutyTxnId(mainDutyId);
	 return "succ";
	}


	public String getValuationIdAGri(String valId) {

		String upkarValue=null;
		 try {
	        	
	        	dbUtility = new DBUtility();
	        		
	        	dbUtility.createPreparedStatement(CommonSQL.GET_VALUATION_ID_AGRI);
	        	String arr2[]=new String[1];
	        	arr2[0]=valId;
	        	upkarValue = dbUtility.executeQry(arr2);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return upkarValue;
	
	}


	public String getmuniciplaFlag(int id) {


		String upkarValue=null;
		 try {
	        	
	        	dbUtility = new DBUtility();
	        		
	        	dbUtility.createPreparedStatement(CommonSQL.GET_MUNICIPAL_CHECK_BOX);
	        	String arr2[]=new String[1];
	        	arr2[0]=String.valueOf(id);
	        	upkarValue = dbUtility.executeQry(arr2);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return upkarValue;
	
	
	}
	public String getFamilyFlag(int id) {


		String upkarValue=null;
		 try {
	        	
	        	dbUtility = new DBUtility();
	        		
	        	dbUtility.createPreparedStatement(CommonSQL.GET_FAMILY_CHECK_BOX);
	        	String arr2[]=new String[1];
	        	arr2[0]=String.valueOf(id);
	        	upkarValue = dbUtility.executeQry(arr2);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return upkarValue;
	
	
	}
	public ArrayList getInstLevelExemptionsNew(int[] deedId,String langauge) {

		ArrayList exempList=new ArrayList(); ;
		try
		{
			dbUtility = new DBUtility("");
			Connection conn=dbUtility.getDBConnection();
			String sql="";
			
			if("en".equalsIgnoreCase(langauge))
        	{
	        	sql=CommonSQL.GET_EXEMPTIONS;	        	

        	}
			else
			{
				sql=CommonSQL.GET_EXEMPTIONS_H;	
			}
			PreparedStatement pst=conn.prepareStatement(sql);
			logger.debug("exemption query is:"+sql);
			logger.debug("exemption parameters are:"+deedId[0]+":"+deedId[1]);
			pst.setInt(1, deedId[0]);
			pst.setInt(2, deedId[1]);
			ResultSet rset=pst.executeQuery();
			//ResultSetMetaData mata=rset.getMetaData();
			
			while(rset.next())
			{
				
				ArrayList subList=new ArrayList();
				String exempId=rset.getString("EXEMPTION_ID");
				String exemName="";
				if("en".equalsIgnoreCase(langauge))
				{
					exemName=rset.getString("EXEMPTION_NAME");
				}
				else
				{
					exemName=rset.getString("H_EXEMPTION_NAME");
				}
				String exempDisc="";
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String pathClob=pr.getValue("reg.duty_clob");
				if(!pathClob.equalsIgnoreCase("SIT")){
				Clob clob=null;
				if("en".equalsIgnoreCase(langauge))
				{
				clob=rset.getClob("EXEMPTION_DESC");
				}
				else
				{
					clob=rset.getClob("H_EXEMPTION_DESC");	
				}
				long length=clob.length();
				exempDisc=clob.getSubString(1, (int)length);
				}else{

					
					if("en".equalsIgnoreCase(langauge))
					{
						exempDisc=rset.getString("EXEMPTION_DESC");
					}
					else
					{
						exempDisc=rset.getString("H_EXEMPTION_DESC");	
					}
					
					
					
				}
				
				String applOn=rset.getString("APPLICABLE_ON");
				String exempPerc=rset.getString("EXEMPTED_PERCENT");
				// for  estamp
				String  exempEstmap =rset.getString("EXEMPTION_ESTAMP");
				subList.add(exempId);
				subList.add(exemName);
				subList.add(exempDisc);
				subList.add(applOn);
				subList.add(exempPerc);
				subList.add(exempEstmap);
				exempList.add(subList);
			}
			rset.close();
			pst.close();
			conn.close();
		}
		catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
			
		}
		if(exempList.size()>0)
		{	
		return exempList;
		}
		else
		{
			return	null;
		}
	
	}
	public ArrayList getInstLevelExemptions(int[] deedId,String langauge) {


		ArrayList janpadDetails=null;
		 try {
	        	dbUtility = new DBUtility();
	        	if("en".equalsIgnoreCase(langauge))
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.GET_EXEMPTIONS);	        	

	        	}
	        	else
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.GET_EXEMPTIONS_H);	        	

	        	}
	        	janpadDetails = dbUtility.executeQuery(deedId);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return janpadDetails;
	
	
	}
	public ArrayList getRegFeeExemptionsNew(int[] deedId,String langauge) {
		ArrayList exempList=new ArrayList();
		try
		{
			dbUtility = new DBUtility("");
			Connection conn=dbUtility.getDBConnection();
			String sql="";
			if("en".equalsIgnoreCase(langauge))
        	{
	        	sql=CommonSQL.GET_EXEMPTIONS_REG;	        	

        	}
			else
			{
				sql=CommonSQL.GET_EXEMPTIONS_REG_H;	
			}
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, deedId[0]);
			pst.setInt(2, deedId[1]);
			ResultSet rset=pst.executeQuery();
			while(rset.next())
			{
				ArrayList subList=new ArrayList();
				String exempId=rset.getString("EXEMPTION_ID");
				String exemName="";
				if("en".equalsIgnoreCase(langauge))
				{
					exemName=rset.getString("EXEMPTION_NAME");
				}
				else
				{
					exemName=rset.getString("H_EXEMPTION_NAME");
				}
				String exempDisc="";
				/*Clob clob=null;
				if("en".equalsIgnoreCase(langauge))
				{
				clob=rset.getClob("EXEMPTION_DESC");
				}
				else
				{
					clob=rset.getClob("H_EXEMPTION_DESC");	
				}
				long length=clob.length();
				exempDisc=clob.getSubString(1, (int)length);*/
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String pathClob=pr.getValue("reg.duty_clob");
				if(!pathClob.equalsIgnoreCase("SIT")){
				Clob clob=null;
				if("en".equalsIgnoreCase(langauge))
				{
				clob=rset.getClob("EXEMPTION_DESC");
				}
				else
				{
					clob=rset.getClob("H_EXEMPTION_DESC");	
				}
				long length=clob.length();
				exempDisc=clob.getSubString(1, (int)length);
				}else{

					
					if("en".equalsIgnoreCase(langauge))
					{
						exempDisc=rset.getString("EXEMPTION_DESC");
					}
					else
					{
						exempDisc=rset.getString("H_EXEMPTION_DESC");	
					}		
					
				}
				String applOn=rset.getString("APPLICABLE_ON");
				String exempPerc=rset.getString("EXEMPTED_PERCENT");
				// for  estamp
				String  exempEstmap =rset.getString("EXEMPTION_ESTAMP");
				subList.add(exempId);
				subList.add(exemName);
				subList.add(exempDisc);
				subList.add(applOn);
				subList.add(exempPerc);
				subList.add(exempEstmap);
				exempList.add(subList);
			}
			
		}
		catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
			
		}
		if(exempList.size()>0)
		{	
		return exempList;
		}
		else
		{
			return	null;
		}
	}
	
	
	public synchronized ArrayList getSelectedMineralList(String language,String mineralSelected) throws Exception {
		
		
	
		ArrayList returnArrayList = new ArrayList();
		try {

			dbUtility = new DBUtility();
			
			if("major".equalsIgnoreCase(mineralSelected))
        	{
				if("en".equalsIgnoreCase(language)){
	        	dbUtility.createPreparedStatement(CommonSQL.MAJOR_LIST_En);	       
				}
				else
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.MAJOR_LIST_Hn);	        	

	        	}
        	}
			else{

				if("en".equalsIgnoreCase(language)){
	        	dbUtility.createPreparedStatement(CommonSQL.MINOR_LIST_En);	       
				}
				else
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.MINOR_LIST_Hn);	        	

	        	}
        	
			}
			
			
			String [] arr =new String[1];
			arr[0] = mineralSelected;
			
			returnArrayList = dbUtility.executeQuery(arr);
		} catch (Exception x) {
			System.out.println(x);
			x.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		return returnArrayList;

	}	
	
	public ArrayList getRegFeeExemptions(int[] deedId,String langauge) {


		ArrayList janpadDetails=null;
		 try {
	        	dbUtility = new DBUtility();
	        	if("en".equalsIgnoreCase(langauge))
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.GET_EXEMPTIONS_REG);	        	

	        	}
	        	else
	        	{
		        	dbUtility.createPreparedStatement(CommonSQL.GET_EXEMPTIONS_REG_H);	        	

	        	}
	        	janpadDetails = dbUtility.executeQuery(deedId);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return janpadDetails;
	
	
	}

	public String getDeedDiscription(int deedId, String language) {

		String upkarValue=null;
		 try {
	        	
	        	dbUtility = new DBUtility();
	        		
	        	if("en".equalsIgnoreCase(language))
	        	{	
	        	dbUtility.createPreparedStatement(CommonSQL.GET_DEED_DISCRIPTION);
	        	}
	        	else
	        	{
		       dbUtility.createPreparedStatement(CommonSQL.GET_DEED_DISCRIPTION_H);

	        	}
	        	String arr2[]=new String[1];
	        	arr2[0]=String.valueOf(deedId);
	        	upkarValue = dbUtility.executeQry(arr2);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return upkarValue;
	
	}
	public String getInstDiscription(int deedId, String language) {

		String upkarValue=null;
		 try {
	        	
	        	dbUtility = new DBUtility();
	        		
	        	if("en".equalsIgnoreCase(language))
	        	{	
	        	dbUtility.createPreparedStatement(CommonSQL.GET_INST_DISCRIPTION);
	        	}
	        	else
	        	{
		       dbUtility.createPreparedStatement(CommonSQL.GET_INST_DISCRIPTION_H);

	        	}
	        	String arr2[]=new String[1];
	        	arr2[0]=String.valueOf(deedId);
	        	upkarValue = dbUtility.executeQry(arr2);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return upkarValue;
	
	}


	public ArrayList cancellationCategoryList(String langauge) {

		ArrayList stampDetails=null;
		 try {
	        	dbUtility = new DBUtility();
	        	dbUtility.createStatement();	        	
	        	if("en".equalsIgnoreCase(langauge))
	        	{	
	        	stampDetails = dbUtility.executeQuery(CommonSQL.DEED_CATEGORY);
	        	}
	        	else
	        	{
	        	stampDetails = dbUtility.executeQuery(CommonSQL.DEED_CATEGORY_HI);		
	        	}

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return stampDetails;
	
	}


	public ArrayList cancellationDeedList(String langauge) {


		ArrayList stampDetails=null;
		 try {
	        	dbUtility = new DBUtility();
	        	dbUtility.createStatement();	        	
	        	if("en".equalsIgnoreCase(langauge))
	        	{	
	        	stampDetails = dbUtility.executeQuery(CommonSQL.CANCELATION_DEED_LIST);
	        	}
	        	else
	        	{
	        	stampDetails = dbUtility.executeQuery(CommonSQL.CANCELATION_DEED_LIST_HI);		
	        	}

	        } catch (Exception exception) {

	                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
				}
			}
	        
		return stampDetails;
	
	
	}
	
	public synchronized ArrayList getDeedType(String option,String flag,String language,String module) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getDeedType(String) - start");
		}
		String SQL =null;
		if (option.equals("P") ) {
			SQL = CommonSQL.DEED_QUERY_PROPERTY;
		}
		else if (option.equals("R") )
		{
			SQL = CommonSQL.DEED_QUERY_REGISTRATION;
		}
		else if (option.equals("NP") )
		{
			//SQL = CommonSQL.DEED_QUERY_REGISTRATION_NON_PROPERTY;
			SQL = CommonSQL.DEED_QUERY_DC_COMMON;
		}
		else if (option.equals("D") )
		{
			// SQL = CommonSQL.DEED_QUERY;
			
			SQL = CommonSQL.DEED_QUERY_DC_COMMON;
		}else{
			
			if("Y".equalsIgnoreCase(flag))
			{
				
				if("en".equalsIgnoreCase(language))
				{
					SQL = CommonSQL.DEED_QUERY_DC_COMMON_CANCEL;
				}
				else
				{
					SQL = CommonSQL.DEED_QUERY_DC_COMMON_CANCEL_HI;
				}
			}
			else
			{	
				if("eStamping".equalsIgnoreCase(module))
				{
					if("en".equalsIgnoreCase(language))
					{	
					SQL = CommonSQL.DEED_QUERY_DC_ESTAMP;
					}
					else
					{
					SQL = CommonSQL.DEED_QUERY_DC_ESTAMP_HI;
					}
				}
				else
				{	
				if("en".equalsIgnoreCase(language))
				{	
				SQL = CommonSQL.DEED_QUERY_DC_COMMON;
				}
				else
				{
				SQL = CommonSQL.DEED_QUERY_DC_COMMON_HI;
				}
				}	
			}
		}
		ArrayList returnArrayList = new ArrayList();
		try {

			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			String [] arr =new String[1];
			arr[0]=option;
			returnArrayList = dbUtility.executeQuery(arr);
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		if (logger.isDebugEnabled()) {
			System.out.println("getDeedType(String) - end");
		}
		return returnArrayList;

	}	
	
	
public synchronized ArrayList getInstruments(int[] deedType,String cancellationFlag,String module)
	throws Exception {
if (logger.isDebugEnabled()) {
	logger.debug("getInstruments(String[]) - start");
}
ArrayList instrument = new ArrayList();
try {

	dbUtility = new DBUtility();
	//dbUtil.createPreparedStatement(CommonSQL.INSTRUMENTS_QUERY);
	if("Y".equalsIgnoreCase(cancellationFlag))
	{
		dbUtility.createPreparedStatement(CommonSQL.INSTRUMENTS_QUERY_HINDI_CANCEL);
	}
	else
	{	
	if("eStamping".equalsIgnoreCase(module))
	{
		dbUtility.createPreparedStatement(CommonSQL.INSTRUMENTS_QUERY_HINDI_ESTAMP);

	}
	else
	{	
	dbUtility.createPreparedStatement(CommonSQL.INSTRUMENTS_QUERY_HINDI);
	}
	}
	logger.debug("Inside getInstruments");
	instrument = dbUtility.executeQuery(deedType);
} catch (Exception x) {
	logger.debug(x);
} finally {
	if (dbUtility != null) {
		dbUtility.closeConnection();
	}
}
if (logger.isDebugEnabled()) {
	logger.debug("getInstruments(String[]) - end");
}
return instrument;
}
public String getCancelaationStamp(int deedId,int instId)
{

	String upkarValue=null;
	 try {
        	
        	dbUtility = new DBUtility();
        		
        	
	       dbUtility.createPreparedStatement(CommonSQL.CANCEL_STAMP_VALUE);

        	
        	String arr2[]=new String[2];
        	arr2[0]=String.valueOf(deedId);
        	arr2[1]=String.valueOf(instId);
        	upkarValue = dbUtility.executeQry(arr2);
        	

        } catch (Exception exception) {

                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
        
	return upkarValue;
}
public String getCancelaationRegFee(int deedId,int instId)
{

	String upkarValue=null;
	 try {
        	
        	dbUtility = new DBUtility();
        		
        	
	       dbUtility.createPreparedStatement(CommonSQL.CANCEL_REG_VALUE);

        	
        	String arr2[]=new String[2];
        	arr2[0]=String.valueOf(deedId);
        	arr2[1]=String.valueOf(instId);
        	upkarValue = dbUtility.executeQry(arr2);
        	

        } catch (Exception exception) {

                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
        
	return upkarValue;

	
}
public String getAreaAgri(String  id)
{

	String upkarValue=null;
	 try {
        	
        	dbUtility = new DBUtility();
        		
        	
	       dbUtility.createPreparedStatement(CommonSQL.AGRI_AREA);

        	
        	String arr2[]=new String[1];
        	arr2[0]=String.valueOf(id);
        	upkarValue = dbUtility.executeQry(arr2);
        	

        } catch (Exception exception) {

                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
        
	return upkarValue;

	
}


public String minRegFee(String deedId, String instId) {

	String upkarValue=null;
	 try {
        	
        	dbUtility = new DBUtility();
        		
        	
	       dbUtility.createPreparedStatement(CommonSQL.GET_MIN_REG_FEE);

        	
	       String arr2[]=new String[2];
       	arr2[0]=String.valueOf(deedId);
       	arr2[1]=String.valueOf(instId);
    	upkarValue = dbUtility.executeQry(arr2);
	

        } catch (Exception exception) {

                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
        
	return upkarValue;

	
}


public String minStampDuty(String deedId, String instId) {

	String upkarValue=null;
	 try {
        	
        	dbUtility = new DBUtility();
        		
        	
	       dbUtility.createPreparedStatement(CommonSQL.GET_MIN_STAMP_DUTY);

        	
        	String arr2[]=new String[2];
        	arr2[0]=String.valueOf(deedId);
        	arr2[1]=String.valueOf(instId);

        	upkarValue = dbUtility.executeQry(arr2);
        	

        } catch (Exception exception) {

                System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculatiocnDAO in dao start" + e.getStackTrace());
			}
		}
        
	return upkarValue;

	
}
public synchronized ArrayList getDeedCategoryList(String langauge) throws Exception {
	logger.debug("Start of getDeedCategoryList(String langauge)");
	String SQL =null;
	if("en".equalsIgnoreCase(langauge))
	{
		SQL= CommonSQL.DEED_QUERY_DEEDCATEGORY_COMMON;
	}
	else
	{
		SQL= CommonSQL.DEED_QUERY_DEEDCATEGORY_COMMON_HINDI;
	}
	ArrayList returnArrayList = new ArrayList();
	try {

		dbUtility = new DBUtility();
		dbUtility.createStatement();
		returnArrayList = dbUtility.executeQuery(SQL);
	} catch (Exception x) {
		System.out.println(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	if (logger.isDebugEnabled()) {
		System.out.println("getDeedType(String) - end");
	}
	return returnArrayList;

}


public String getcurrYear()  {
			
			String curryear = "";
			try {
				dbUtility = new DBUtility();
				dbUtility.createStatement();
				curryear=	dbUtility.executeQry(CommonSQL.CURRENT_YEAR);
				dbUtility.closeConnection();
			}catch (Exception exception) {
				logger.info("Exception in getSPusers-SPDAo" + exception);
			}
			return curryear;
		}
public String getcurrDate()  {
	
	String curryear = "";
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		curryear=	dbUtility.executeQry(CommonSQL.CURRENT_DATE);
		dbUtility.closeConnection();
	}catch (Exception exception) {
		logger.info("Exception in getSPusers-SPDAo" + exception);
	}
	return curryear;
}
public ArrayList deedInstDetails(String regInitId)
{


	 ArrayList list=null;
	 try {
       	String param[]={String.valueOf(regInitId)};
       	dbUtility = new DBUtility();
       		
       	dbUtility.createPreparedStatement(CommonSQL.DEED_INSTRUMENT_REG);
       	
       	list = dbUtility.executeQuery(param);
       	

       } catch (Exception exception) {

               System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

       }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
       
	return list;


}
public ArrayList consenterDetails(String regInitId)
{


	 ArrayList list=null;
	 try {
       	String param[]={String.valueOf(regInitId)};
       	dbUtility = new DBUtility();
       		
       	dbUtility.createPreparedStatement(CommonSQL.CONSNTER_DETAILS_DUTY);
       	
       	list = dbUtility.executeQuery(param);
       	

       } catch (Exception exception) {

               System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

       }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
       
	return list;


}
public ArrayList constFixDutyDetails()
{


	 ArrayList list=null;
	 try {
       	String param[]={String.valueOf("1")};
       	dbUtility = new DBUtility();
       		
       	dbUtility.createPreparedStatement(CommonSQL.CONSENTER_FIXED_DUTIES);
       	
       	list = dbUtility.executeQuery(param);
       	

       } catch (Exception exception) {

               System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

       }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
       
	return list;


}
public double getStampExemption(String dutyTxnId,String ExempType)
{
	double exempPercent=0;
boolean otherThanEWS=false;
boolean ewsSelected=false;
boolean ligSelected=false;
boolean migSelected=false;


	 ArrayList list=null;
	 try {
      	String param[]={ExempType,String.valueOf(dutyTxnId)};
      	dbUtility = new DBUtility();
      		
      	dbUtility.createPreparedStatement(CommonSQL.CONS_EXEMPTION);
      	
      	list = dbUtility.executeQuery(param);
      	

      } catch (Exception exception) {

              System.out.println("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

      }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
      
	if(list!=null&&list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
			ArrayList subList=(ArrayList) list.get(i);
			
			
			
			
			{
			double exemp=Double.parseDouble(subList.get(0).toString());
			if(!subList.get(1).toString().equalsIgnoreCase("131")//ews
					&& !subList.get(1).toString().equalsIgnoreCase("132")//lig
					&& !subList.get(1).toString().equalsIgnoreCase("133"))//mig
			{
				if(exemp>exempPercent)
				{
					exempPercent=exemp;
				}
				otherThanEWS=true;
			}else{			
				
				if(subList.get(1).toString().equalsIgnoreCase("131")){
					ewsSelected=true;
				}else if(subList.get(1).toString().equalsIgnoreCase("132")){
					ligSelected=true;
				}else if(subList.get(1).toString().equalsIgnoreCase("133")){
					migSelected=true;
				}				
				
			}
			
			}
		}
		
		if( (!otherThanEWS) && (ewsSelected || ligSelected || migSelected)){
			
			String applyEws="";
			
			try {
		      	String param[]={String.valueOf(dutyTxnId)};
		      	dbUtility = new DBUtility();
		      		
		      	dbUtility.createPreparedStatement(CommonSQL.GET_APPLY_EWS);
		      	
		      	applyEws = dbUtility.executeQry(param);
		      	

		      } catch (Exception exception) {

		    	  logger.error("Exception in propValRequiredFlag-DutyCalculationDAO" + exception);

		      }finally{
					try {
						dbUtility.closeConnection();
					} catch (Exception e) {
						logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
					}
				}
			
			
			
			if(applyEws.equalsIgnoreCase("Y")){
				
				if(ewsSelected || ligSelected){
					exempPercent=100;
				}else{
					exempPercent=25;
				}
				
				
				
				
			}
			
			
		}

	}


return exempPercent;
	
}
public boolean insertConsneterDuties(String dutyTxnId,String regInitId,String constId,String stamp,String janpad,String municipal,String upkar,String total,String regFee,String exemptedDuty,String exemptedReg,String finalduty,String finalRegFee)
{
	boolean flag=false;
	boolean flag1=false;
	boolean flag2=false;
	try{
		
		String arr[]=new String[11];
		arr[0]=stamp;
		arr[1]=janpad;
		arr[2]=municipal;
		arr[3]=upkar;
		arr[4]=	total;
		arr[5]=	finalduty;
		arr[6]=regFee;
		arr[7]=finalRegFee;
		arr[8]=exemptedDuty;
		arr[9]=exemptedReg;
		arr[10]=dutyTxnId;
		dbUtility = new DBUtility();
  		
      	dbUtility.createPreparedStatement(CommonSQL.CONS_DUTY_UPDATE);
      	flag1=dbUtility.executeUpdate(arr);
      	
      	dbUtility.createPreparedStatement(CommonSQL.CONS_DUTY_INSERT);
      	String arr1[]=new String[12];
      	arr1[0]=stamp;
		arr1[1]=janpad;
		arr1[2]=municipal;
		arr1[3]=upkar;
		arr1[4]=	total;
		arr1[5]=	regFee;
		arr1[6]=finalduty;
		arr1[7]=finalRegFee;
		arr1[8]=exemptedDuty;
		arr1[9]=exemptedReg;
		arr1[10]=constId;
		arr1[11]=regInitId;
		flag2=dbUtility.executeUpdate(arr1);
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		if(flag1&&flag2)
		{
			flag=true;
		}
		else
		{
			try {
				dbUtility.rollback();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	return flag;
}

public String getAgriApplicableSubClause(String colonyId)  {
	
	String curryear = "";
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String[] param={colonyId!=null?colonyId:""};
		dbUtility.createPreparedStatement(CommonSQL.GET_APP_SUBCLAUSE);
		curryear=dbUtility.executeQry(param);
		
		dbUtility.closeConnection();
	}catch (Exception exception) {
		logger.info("Exception in getAgriApplicableSubClause" + exception);
	}
	return curryear;
}


public String getPropType(String valId)  {
	
	String propType = "";
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String[] param={valId!=null?valId:""};
		dbUtility.createPreparedStatement(CommonSQL.GET_PROP_TYPE);
		propType=dbUtility.executeQry(param);
		
		dbUtility.closeConnection();
	}catch (Exception exception) {
		logger.info("Exception in getPropType" + exception);
	}
	return propType;
}
public ArrayList getPropFurtherDetls(String valId)  {
	
	ArrayList propType = new ArrayList();
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String[] param={valId!=null?valId:""};
		dbUtility.createPreparedStatement(CommonSQL.GET_PROP_TYPE_FURTHER_DETLS);
		propType=dbUtility.executeQuery(param);
		
		dbUtility.closeConnection();
	}catch (Exception exception) {
		logger.info("Exception in getPropFurtherDetls" + exception);
	}
	return propType;
}
public String getFloorArea(String valId)  {
	
	String area = "";
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String[] param={valId};
		dbUtility.createPreparedStatement(CommonSQL.GET_PROP_TYPE_FURTHER_FLOOR_DETLS);
		area=dbUtility.executeQry(param);
		
		dbUtility.closeConnection();
	}catch (Exception exception) {
		logger.info("Exception in getFloorArea" + exception);
	}
	return area;
}

public int getResiFloorCount(String valId)  {
	
	int count = 0;
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String[] param={valId};
		dbUtility.createPreparedStatement(CommonSQL.GET_RESI_FLOOR_COUNT);
		String cnt=dbUtility.executeQry(param);
		count=Integer.parseInt(cnt);
		dbUtility.closeConnection();
	}catch (Exception exception) {
		logger.info("Exception in getResiFloorCount" + exception);
	}
	return count;
}

//created by Rakesh fro clr rinPustika validation
public String getRinPustikaNo(String valTxnId) {
	String name = "";
	try {
		dbUtility = new DBUtility();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {

		dbUtility
				.createPreparedStatement(CommonSQL.GET_RIN_PUSTIKA_CLR);

		name = dbUtility.executeQry(new String[] { valTxnId });
		return name;

	} catch (Exception e) {
		logger.error(e);
		return null;

	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error(e);

		}

	}

}
//created by Rakesh fro clr rinPustika validation
public String getColonyId(String valTxnId) {
	String name = "";
	try {
		dbUtility = new DBUtility();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {

		dbUtility
				.createPreparedStatement(CommonSQL.GET_COLONY_ID_CLR);

		name = dbUtility.executeQry(new String[] { valTxnId });
		return name;

	} catch (Exception e) {
		logger.error(e);
		return null;

	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error(e);

		}

	}

}
public ArrayList getKhasraListClr(String valTxnId)  {
	
	ArrayList khasraList = new ArrayList();
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String[] param={valTxnId};
		dbUtility.createPreparedStatement(CommonSQL.GET_ALL_KHASRA_LIST_WRT_VALTXNID);
		khasraList=dbUtility.executeQuery(param);
		
		dbUtility.closeConnection();
	}catch (Exception exception) {
		logger.info("Exception in getKhasraListClr" + exception);
	}
	return khasraList;
}


public ArrayList getDutyDetails(String deedId,String instId,String dutyType)
{
	ArrayList dutyDetails=null;
	 try {
        	String param[]={deedId,instId,dutyType};
        	dbUtility = new DBUtility();
        	dbUtility.createPreparedStatement(CommonSQL.GET_DUTY_DETAILS);	        	
        	dutyDetails = dbUtility.executeQuery(param);
        	

        } catch (Exception exception) {

                System.out.println("Exception in getDutyDetails-DutyCalculationDAO" + exception);

        }finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("DutyCalculationDAO in dao start" + e.getStackTrace());
			}
		}
        
	return dutyDetails;
}

}
