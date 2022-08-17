package com.wipro.igrs.hrpayroll.hrp.bd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.hrpayroll.hrp.dao.BasicSlabDAO;
import com.wipro.igrs.hrpayroll.hrp.dto.BasicSlabDTO;

public class BasicSlabBD {

	private Logger logger = (Logger) Logger.getLogger(BasicSlabBD.class);
	BasicSlabDAO basicSlabDAO = null;
	
	public BasicSlabBD(){		
	}
	
	public boolean createBasicSlab(BasicSlabDTO basicSlabDTO, String userId) throws Exception{
		boolean inserted=true;
		basicSlabDAO = new BasicSlabDAO();
		String [] slabArray = new String[8];
				  slabArray[0]=basicSlabDAO.basicSlabId();
		          slabArray[1]=basicSlabDTO.getBasicSlabMin();
		          slabArray[2]=basicSlabDTO.getBasicSlabMax();
		  		  slabArray[3]=basicSlabDTO.getBasicSlabIncrement();
		  		  slabArray[4]=getOracleDate(basicSlabDTO.getBasicSlabValidFrom());
		  		  slabArray[5]=getOracleDate(basicSlabDTO.getBasicSlabValidTo());		  		            
		  		  slabArray[6]=basicSlabDTO.getBasicSlabRemarks();		            
		  		  slabArray[7]=userId;
		 
		  		inserted = basicSlabDAO.createBasicSlabDetails(slabArray);
		return inserted;
	}
	public boolean updateBasicSlab(BasicSlabDTO basicSlabDTO, String userId) throws Exception{
		boolean inserted=true;
		//		"SET SLAB_MIN=?,SLAB_MAX=? ,SLAB_INCREMENT=?," +
		//"SLAB_VALID_FROM=?,SLAB_VALID_TO=?,SLAB_REMARKS=?,SLAB_STATUS=?,UPDATE_BY=?,UPDATE_DATE=SYSDATE WHERE SLAB_ID=?
		basicSlabDAO = new BasicSlabDAO();
		String [] slabArray = new String[9];
		          slabArray[0]=basicSlabDTO.getBasicSlabMin();
		          slabArray[1]=basicSlabDTO.getBasicSlabMax();
		  		  slabArray[2]=basicSlabDTO.getBasicSlabIncrement();
		  		  slabArray[3]=getOracleDate(basicSlabDTO.getBasicSlabValidFrom());
		  		  slabArray[4]=getOracleDate(basicSlabDTO.getBasicSlabValidTo());		  		            
		  		  slabArray[5]=basicSlabDTO.getBasicSlabRemarks();		            
		  		  slabArray[6]=basicSlabDTO.getStatus();
		  		  slabArray[7]=userId;
		  		  slabArray[8]=basicSlabDTO.getBasicSlabId();
		 
		  		inserted = basicSlabDAO.updateBasicSlabDetails(slabArray);
		return inserted;
	}
	
	public ArrayList retrieveBasicSlabList() throws Exception{
		basicSlabDAO = new BasicSlabDAO();
		ArrayList slabList = basicSlabDAO.retrieveBasicSlabList();
		
		logger.debug("ArrayList---->  "+slabList.size());
		return slabList;
	}
	public BasicSlabDTO retrieveBasicSlabDetails(String slabId) throws Exception{
		basicSlabDAO = new BasicSlabDAO();
		return basicSlabDAO.retrieveBasicSlabDetails(slabId);
	}
	
	public boolean deleteSlabMaster(String slabId) throws Exception{
		basicSlabDAO = new BasicSlabDAO();
		return basicSlabDAO.deleteSlabMaster(slabId);
	}
	
	public ArrayList getAllottedSlabList(String gradeId) throws Exception{
		basicSlabDAO = new BasicSlabDAO();
		return basicSlabDAO.getAllottedSlabList(gradeId);
	}
	public boolean updateOldSlabs(String oldSlabIds, String gradeId){
		basicSlabDAO = new BasicSlabDAO();
		return basicSlabDAO.updateOldSlabs(oldSlabIds,gradeId);
	}
	public boolean updateNewSlabs(String oldSlabIds, String gradeId){
		basicSlabDAO = new BasicSlabDAO();
		return basicSlabDAO.updateNewSlabs(oldSlabIds,gradeId);
	}
	
	public static String getOracleDate(String DateFormat) {
		String finalDate = "";
		if (DateFormat != null || !DateFormat.equalsIgnoreCase("")) {
			StringTokenizer st = new StringTokenizer(DateFormat, "/");
			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();
			String inputDate = day + "-" + month + "-" + year;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			Date newDate;
			try {
				newDate = formatter.parse(inputDate);
				SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
				finalDate = format.format(newDate);

			} catch (Exception e) {
				System.out.print(e);
			}

		}
		return finalDate;
	}
	
	/*public static void main(String ar[]) throws Exception{
		BasicSlabBD basicSlabBD = new BasicSlabBD();
		//String id = basicSlabDAO.basicSlabId();
		//ArrayList l1=basicSlabDAO.retrieveBasicSlabList();
		String dd= basicSlabBD.getOracleDate("01-07-2008");
		System.out.println("list--->  "+dd);
	}
*/
}
