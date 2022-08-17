package com.wipro.igrs.visitBooking.bo;
/**
 * ===========================================================================
 * File           :   VisitBO.java
 * Description    :   Represents  Business Object

 * Author         :   pavani Param
 * Created Date   :   Apr 08, 2008.

 * ===========================================================================
 */
import java.util.ArrayList;
import com.wipro.igrs.visitBooking.dao.VisitBookingDAO;
import com.wipro.igrs.visitBooking.form.VisitBookingForm;


/**
 * 
 * @author pavpapa
 *
 */
public class VisitBO {  


	VisitBookingDAO objDao;

	/** 
	  * Getting Country list
	  * @return list
	  */
	public ArrayList getCountry() throws Exception {
		objDao =new VisitBookingDAO();
		return  objDao.getCountry();
	}

	/** 
	  * Getting State list
	  * @return list
	  */
	public ArrayList getState(String countryId) throws Exception {
		objDao =new VisitBookingDAO();
		return  objDao.getState(countryId);
	}

	/** 
	  * Getting District list
	  * @return list
	  */
	public ArrayList getDistrict(String districtId) throws Exception {
		objDao =new VisitBookingDAO();
		return  objDao.getDistrict(districtId);
	}
	
	/**
	   * Getting Photo List
	   * @return list
	   */
	  
   public ArrayList getphotoIdList()  throws Exception
   {
	   objDao =new VisitBookingDAO();
	   return  objDao.getphotoIdList(); 
   }
   
   /**
    * Getting deed details
    * @return list
    */
       public ArrayList  getOptionalDeedType() throws Exception
       {
    	   objDao =new VisitBookingDAO();
    	   return  objDao.getOptionalDeedType(); 
       }
       /**
        * 
        * @param param
     * @param userId 
     * @param funId 
     * @param roleId 
        * @return
        * @throws Exception
        */
       
       
       public boolean visitDetIns(String param[], String roleId, String funId, String userId) throws Exception
	       {
    	   objDao =new VisitBookingDAO();
    	   return objDao.visitDetIns(param,roleId,funId,userId);
	    	   
	       }
       /**
   	 * getting the visit Remarks values.
   	 * 
   	 * @param param
     * @param userId 
     * @param funId 
     * @param roleId 
   	 * @return
   	 * @throws Exception
   	 */

   	public boolean visitRemIns(String param[], String roleId, String funId, String userId) throws Exception 
	   	{
   		objDao =new VisitBookingDAO();
   		return objDao.visitRemIns(param,roleId,funId,userId);
	   	}

   	/**
	 * getting  Visit Booking Reference Id.
	 * 
	 * @throws Exception
	 */
	public ArrayList getRefId() throws Exception 
	{
		objDao =new VisitBookingDAO();
		return objDao.getRefId();
	}
	
	
	/**
	 * getting visit booking print Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList printVisitBookDet(String visitRefId) throws Exception 
	{
		objDao =new VisitBookingDAO();
		return objDao.printVisitBookDet(visitRefId);
	}
	
	
	/**
	 * getting visit booking View Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getVisitBookViewDet(String visitRefId) throws Exception
	{
		objDao =new VisitBookingDAO();
		return objDao.getVisitBookViewDet(visitRefId);
	}
	
	/**
	 * getting visit booking Remarks Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getVisitBookRemDet(String visitRefId) throws Exception
	{
		objDao =new VisitBookingDAO();
		return objDao.getVisitBookRemDet(visitRefId);
	}
	
	/**
	 * getting visit booking View Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getVisitViewResult(VisitBookingForm _refForm) throws Exception
	{
		objDao =new VisitBookingDAO();
		return objDao.getVisitUpdateResult(_refForm);
	}
	
	/**
	 * getting Visit Booking Update Results.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public ArrayList getVisitUpdateResult(VisitBookingForm _refForm) throws Exception
	{
		objDao =new VisitBookingDAO();
		return objDao.getVisitUpdateResult(_refForm);
	}
	
	/**
	 * getting Visit Booking Fee Details.
	 * @param functionId 
	 * 
	 * @return list
	 * @throws Exception
	 */
	public ArrayList getVisitBookFeeVal(String functionId) throws Exception 
	{
		objDao =new VisitBookingDAO();
		return objDao.getVisitBookFeeVal(functionId);
	}
	
        
        /**
         * for calculating  othersFeeDuty
         * @param _refFunId
         * @throws Exception
         * @return othersFeeDuty
         * 
         */
         public  ArrayList getOthersFeeDuty(String _retFunId,String _serId,String _userType) throws Exception
         {
        	 objDao =new VisitBookingDAO();
        	 return     objDao.getOthersFeeDuty(_retFunId,_serId,_userType);
         }

   /**
	 * check application no
	 * @param reqApplNo
	 * @return status
	 */
	public String checkAppNo(String reqApplNo) {
		objDao =new VisitBookingDAO();
		return objDao.checkAppNo(reqApplNo);
	}

}
