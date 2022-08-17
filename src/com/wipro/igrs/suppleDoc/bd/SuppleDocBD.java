package com.wipro.igrs.suppleDoc.bd;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.suppleDoc.bo.SuppleDocBO;
import com.wipro.igrs.suppleDoc.dao.SuppleDocDAO;
import com.wipro.igrs.suppleDoc.dto.SuppleDocDTO;

public class SuppleDocBD {
 
	private Logger logger = 
		(Logger) Logger.getLogger(SuppleDocBD.class);
    public SuppleDocBD() {
    }
    SuppleDocDTO sdDTO=new SuppleDocDTO();
    public ArrayList<?>  countryStackBD(String _axn, String language)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (countryStackBD) Method");
    		listBD=refBO.countryStackBO(_axn,language);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    public ArrayList<?>  documentListBD(String langauge)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (documentList()) Method");
    		listBD=refBO.documentListBO(langauge);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    public ArrayList getPropKhasraDetlsForDisplay(String propId) throws Exception {
    	SuppleDocDAO commonDao = new SuppleDocDAO();
		return commonDao.getPropKhasraDetlsForDisplay(propId);

	}
    
    public ArrayList<?>  purposeListBD(String id, String language)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (purposeList()) Method");
    		listBD=refBO.purposeListBO(id,language);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    public ArrayList<?>  stateStackBD(String _countryId, String _axn, String language)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (stateStackBD) Method");
    		listBD=refBO.stateStackBO(_countryId,_axn,language);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList<?>  photoIdStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (photoIdStackBD) Method");
    		listBD=refBO.photoIdStackBO();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList<?>  casteStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (casteStackBD) Method");
    		listBD=refBO.casteStackBO();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList<?>  religionStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (religionStackBD) Method");
    		listBD=refBO.religionStackBO();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList<?>  districtStackBD(String _stateId, String _axn, String language)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (districtStackBD) Method");
    		listBD=refBO.districtStackBO(_stateId,_axn,language);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList<?>  loanPurposeStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (loanPurposeStackBD) Method");
    		listBD=refBO.loanPurposeStackBO();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList<?>  deedStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (deedStackBD) Method");
    		listBD=refBO.deedStackBO();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public SuppleDocDTO estampCheckBD(SuppleDocDTO sdDTO2, String language)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?> listBD=new ArrayList<Object>();
    	try{
    		logger.info("Inside (estampCheckBD) Method");
    		
    		listBD=refBO.estampCheckBO(sdDTO2.getEstampCodeSearch1());
    		if(listBD.isEmpty()){
    			if(language.equalsIgnoreCase("en"))
    			sdDTO2.setErrorMsg("E-Stamp doesn't exist");
    			else
    				sdDTO2.setErrorMsg("ई मुद्रांक मौजूद नहीं है");
    			sdDTO2.setEstampCodeSearch(sdDTO2.getEstampCodeSearch1());
    		}
    		else
    		{
    			
    			ArrayList list=(ArrayList)listBD.get(0);
    			String status = (String)list.get(3);
    			if(status.equalsIgnoreCase("D"))
    			{
    				if(language.equalsIgnoreCase("en"))
    				sdDTO2.setErrorMsg("E-Stamp is Deactivated");
    				else
    					sdDTO2.setErrorMsg("ई मुद्रांक निष्क्रिय है");
        			sdDTO2.setEstampCodeSearch(sdDTO2.getEstampCodeSearch1());
    			}
    			else if(status.equalsIgnoreCase("A"))
    			{
    				String consStatus = (String)list.get(4);
    				if(consStatus.equalsIgnoreCase("Consumed"))
    				{
    					if(language.equalsIgnoreCase("en"))
    					sdDTO2.setErrorMsg("E-Stamp is Already Consumed");
    					else
    					sdDTO2.setErrorMsg("ई मुद्रांक पहले से ही इस्तेमाल किया गया है");
            			sdDTO2.setEstampCodeSearch(sdDTO2.getEstampCodeSearch1());	
    				}
    				else
    				{
    					sdDTO2.setEstampCodeSearch((String)list.get(0));
    	    			sdDTO2.setEstampDuty((String)list.get(1));
    	    			logger.info("Inside (estampCheckBD) Method before estamp purpose");
    	    			sdDTO2.setEstampPurpose((String)list.get(2));
    	    			logger.info("Inside (estampCheckBD) Method afeter estamp purpose");
    	    			sdDTO2.setScannedCopyName("1");
    	    			sdDTO2.setErrorMsg("ERROR");
    				}
    			
    			}
    		}
    		return sdDTO2;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public boolean insertSuppliDetailsBD(SuppleDocDTO _sdDTO)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		IGRSCommon common=new IGRSCommon();
    	try{
    		logger.info("Inside (insertSuppliDetailsBD) Method");
    		String param[]=new String[45];
    		String suppTxnId=common.getSequenceNumber("IGRS_SUPPLEDOC_TXN_ID", "SDOC");
    		_sdDTO.setSuppTxnId(suppTxnId);
    		param[0]=""+suppTxnId;
    		param[1]=""+_sdDTO.getPropertyTxnId();
    		param[2]=""+'A';
    		param[3]=""+_sdDTO.getCurrentUserId();
    		param[4]=""; //Party Type Id
    		param[5]=""+_sdDTO.getFname();
    		param[6]=""+_sdDTO.getMname();
    		param[7]=""+_sdDTO.getLname();
    		param[8]=""+_sdDTO.getGender();
    		param[9]=""+_sdDTO.getAge(); // Age Int
    		//param[10]=""+_sdDTO.getNationality();
    		param[11]=""+_sdDTO.getCountryId();
    		param[12]=""+_sdDTO.getStateId();
    		param[13]=""+_sdDTO.getDistrictName();
    		param[14]=""+_sdDTO.getAddress();
    		param[15]=""+_sdDTO.getPostalCode();//Postal Code NUmber
    		param[16]=""+_sdDTO.getPhoneNo();
    		param[17]=""+_sdDTO.getMobNo();
    		param[18]=""+_sdDTO.getMailId();
    		//param[19]=""+_sdDTO.getPhotoProofTypeName();
    		//param[20]=""+_sdDTO.getPhotoId();
    		param[21]=""+_sdDTO.getFatherName();
    		param[22]=""+_sdDTO.getMotherName();
    		param[23]=""+common.getSequenceNumber("IGRS_SUPPLEDOC_PARTY_ID", "PRT");//
    	///	param[24]=""+_sdDTO.getOccupation();
    		//param[25]=""+_sdDTO.getPhyChallanged();
    		
    	//	param[26]=""+_sdDTO.getDeedTypeName();
    		param[27]=""+_sdDTO.getExecuteDate();
    		param[28]=""+_sdDTO.getPresentDate();
    		param[29]=""+_sdDTO.getStampDuty();
    		param[30]=""+_sdDTO.getDocNo();
    		param[31]=""+_sdDTO.getEstampCode();
    	///	param[32]=""+_sdDTO.getLoanPurposeName();
    	//	param[33]=""+_sdDTO.getLoanAmount();
    		
    		param[34]=""+_sdDTO.getBankName();
    		param[35]=""+_sdDTO.getBankAddress();
    		param[36]=""+_sdDTO.getBankDistrictName();
    		param[37]=""+_sdDTO.getBankPostalCode();
    		param[38]=""+_sdDTO.getBankPersonName();
    		param[39]=""+_sdDTO.getBankPersonDesi();
    		param[40]=""+_sdDTO.getBankPhone();
    		param[41]=""+_sdDTO.getBankPersonMob();
    		param[42]=""+_sdDTO.getBankPersonEmail();
    		//param[43]=""+_sdDTO.getCasteName();
    		//param[44]=""+_sdDTO.getReligionName();
    		
    		
    		return refBO.insertSuppliDetailsBO(param);
    	}catch (Exception e) {
    		logger.error(e);
    		return false;
    	}
    }
    
    
    public String getConfigParamter()
    {
    	String maxDays = "";
    	try {
    		SuppleDocDAO refDAO = new SuppleDocDAO();
    		maxDays = refDAO.getSpotMaxDay();
    		
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
    	
    	return maxDays;
    }
    
    
    public boolean insertSuppliPartyDetailsBD(SuppleDocDTO baseFormDTO,HashMap<String, SuppleDocDTO> entries)
    {
    	
    	logger.debug("WE ARE IN BD Debug");
		
    	boolean retVal = false;
		try{
			SuppleDocDTO[] values = entries.values().toArray(new SuppleDocDTO[]{});
    		SuppleDocBO refBO=new SuppleDocBO();
    		refBO.insertCompleteSupDetails(baseFormDTO, values);
//    		refBO.insertSuppliPartyDetailsBO(entries.values());
    		
    		
//    		checks.add(refBO.insertSuppliPartyDetailsBO(param));
    		
    	}catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return retVal;
    	}
		return retVal;
    }
    
    
    public boolean insertPartialSave2DetailsBD(SuppleDocDTO baseFormDTO,HashMap<String, SuppleDocDTO> entries, HashMap<String, SuppleDocDTO> mapEStamp)
    {
    	
    	logger.debug("WE ARE IN BD Debug");
		
    	boolean retVal = false;
		try{
			SuppleDocDTO[] valuesPStamp = entries.values().toArray(new SuppleDocDTO[]{});
			SuppleDocDTO[] valuesEStamp = mapEStamp.values().toArray(new SuppleDocDTO[]{});
    		SuppleDocBO refBO=new SuppleDocBO();
    		refBO.insertCompletePartial2Details(baseFormDTO, valuesPStamp,valuesEStamp);

    	}catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return retVal;
    	}
		return retVal;
    }
    
    
    public ArrayList<?>  searchListViewBD(String _supplitxnId,String _frDate,String _toDate, String officeId)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		ArrayList<?>  listBD=new ArrayList<Object> ();
    	try{
    		logger.info("Inside (searchListViewBD) Method");
    		
    		listBD=refBO.searchListViewBO(_supplitxnId,_frDate,_toDate,officeId);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public SuppleDocDTO getTotalDetailsBD(String _docNo, String language)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		SuppleDocDTO dto=new SuppleDocDTO();
		SuppleDocDTO sdDTO=null;
    	try{
    		logger.info("Inside (getTotalDetailsBD) Method");
    		
    		dto=refBO.getTotalDetailsBO(_docNo,language);
    	
    		return dto;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    public SuppleDocDTO getTotalDetailsReferenceBD(String _docNo, String language)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		SuppleDocDTO dto=new SuppleDocDTO();
		SuppleDocDTO sdDTO=null;
    	try{
    		logger.info("Inside (getTotalDetailsBD) Method");
    		
    		dto=refBO.getTotalDetailsReferenceBO(_docNo,language);
    	
    		return dto;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    public int dateComparison(String exDate,String exDate1) throws Exception
	{
    	
		int flag = 0;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		if(exDate.equals("")||exDate.equals(null))
		{
			flag = 0;
		}
		else
		{
			Date execDate = sdf1.parse(exDate);
			Date execDate1 = sdf1.parse(exDate1);
		
			//logger.debug("sysdate"+sysDate);
			//String d1 = sdf1.format(sysDate);
			//Date sDate = sdf1.parse(d1);
			//logger.debug("Final Sysdate"+sDate);
			//logger.debug("final execDate"+execDate);
			//logger.debug("<------getTime"+sDate.getTime());
			//logger.debug("<------getTime"+execDate.getTime());
			
			long diff1 = (long)(execDate.getTime() - execDate1.getTime());
			long diff = diff1/(1000*60*60*24);
			
			logger.debug("diff"+diff1);
			logger.debug("diff"+diff);
			flag=(int) diff;
			
		}
		return flag;
	}

	public boolean partialSave1(SuppleDocDTO sdDTO2,
			HashMap<String, SuppleDocDTO> map) {

		return insertSuppliPartyDetailsBD(sdDTO2, map);

	}
	
	public boolean partialSave2(SuppleDocDTO sdDTO2,
			HashMap<String, SuppleDocDTO> mapPStamp, HashMap<String, SuppleDocDTO> mapEStamp) {

		return insertPartialSave2DetailsBD(sdDTO2, mapPStamp,mapEStamp);

	}

	public ArrayList getTehsil(String districtIDProperty, String language) {
		SuppleDocBO refBo = new SuppleDocBO();
		try {
			return refBo.getTehsil(districtIDProperty,language);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
 
	public ArrayList getAreaType(String language) {
		SuppleDocBO refBo = new SuppleDocBO();
		try {
			return refBo.getAreaType(language);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	
	public ArrayList getPropTypeL1List(String propId,String language) throws Exception {

		SuppleDocBO refBo = new SuppleDocBO();
        return refBo.getPropTypeL1List(propId,language);

}
	
	public ArrayList getPropTypeL2List(String propL1Id, String language) throws Exception {
		SuppleDocBO refBo = new SuppleDocBO();
	    return refBo.getPropTypeL2List(propL1Id,language);

	}
	
	
	public ArrayList getUnitList(String propL1Id, String language) throws Exception {
		ArrayList list = new ArrayList();
		SuppleDocDAO refDao = new SuppleDocDAO();
		try{
			
			ArrayList ret=refDao.getUnitList(propL1Id,language);
			

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList lst = (ArrayList) ret.get(i);
					SuppleDocDTO dto = new SuppleDocDTO();
					logger.debug("unit:-" + lst.get(0) + ":" + lst.get(1));
					dto.setUnitTypeId((String) lst.get(0));
					dto.setUnitType((String) lst.get(1));
					
					list.add(dto);
				}

			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

        return list;

}

	public String getUserName(String userId) {
		SuppleDocDAO refDao = new SuppleDocDAO();
		String username = refDao.getUserName(userId);
		return username;
	}

	public String getSROName(String officeId, String language) {
		SuppleDocDAO refDao = new SuppleDocDAO();
		String officeName = refDao.getSROName(officeId,language);
		return officeName;
	}

	public String getCurrentDate() {
	
		return null;
	}

	public boolean insertLastPageDetails(SuppleDocDTO sdDTO2, String filingNo) {
		logger.debug("WE ARE IN BD Debug");
		SuppleDocBO refBO=new SuppleDocBO();
		
    	try{
    		logger.info("Inside (insertLastPageDetails) Method");
    		
    		
    		String param[]=new String[11];
    		
    		param[0]=""+sdDTO2.getPresentDate();
    		param[1]=""+sdDTO2.getSrName();
    		param[2]=""+sdDTO2.getSroName();
    		param[3]=""+sdDTO2.getScannedCopyName();
    		param[4]=""+sdDTO2.getScannedCopyPath();
    		param[5]=""+sdDTO2.getUserId();
    		param[6]=""+filingNo;
    		if(sdDTO2.getImpound().equalsIgnoreCase("R"))
    		{
    		param[7]=""+"R";
    		}
    		else
    		{
    			param[7]=""+"C";
    		}
    		param[8]=""+"4";
    		param[9]=""+sdDTO2.getBookTypeName();
    		param[10]=""+sdDTO2.getReferenceId();
    		return refBO.insertLastPageDetailsBO(param);
    	}catch (Exception e) {
    		logger.error(e);
    		return false;
    	}
		
	}

	public ArrayList getBookTypeListBD() {
		SuppleDocDAO refBo = new SuppleDocDAO();
		try {
			return	refBo.getBookTypeList();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public String generateFilingNumber(SuppleDocDTO sdDTO2) {
		String prefix="MP";
		String districtCode = sdDTO2.getDistrictIDProperty();
		
		if(districtCode.length()==1)
		{
			districtCode = "0"+districtCode;
		}
		
		String srocode = sdDTO2.getOfficeId();
		String code = srocode.substring(3);
		
		if(code.length()==2)
		{
			
			code= "0"+code;
			
		}
		else if(code.length()==1)
		{
			code = "00"+code;
		}
		
		String bookid = sdDTO2.getBookTypeName();
	
		Date date = new Date();
	
		Format yearformat = new SimpleDateFormat("yyyy");
		
		String year = yearformat.format(date);
		String sequenceNumber = "";
		SuppleDocDAO refDAO = new SuppleDocDAO();
		try {
			sequenceNumber = refDAO.getSequenceNumber(year,"IGRS_SUPPLEDOC_FILING_ID");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filingnumber = prefix+districtCode+code+year+bookid+sequenceNumber;
		
		return filingnumber;
	}
	
	public boolean insertCompletePropertyDetailsBD(SuppleDocDTO sdDTO2,
			HashMap mapFloor) {
	
logger.debug("WE ARE IN BD Debug");
		
    	boolean retVal = false;
		try{
			SuppleDocDTO[] valuesFloor = (SuppleDocDTO[]) mapFloor.values().toArray(new SuppleDocDTO[]{});
			
    		SuppleDocDAO obj = new SuppleDocDAO();
    	retVal	=  obj.insertCompletePropertyDetailsDAO(sdDTO2, valuesFloor);

    	}catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return retVal;
    	}
		return retVal;
	}

	public ArrayList getDtoData(String refernceid) throws Exception {
		SuppleDocDAO refDAO = new SuppleDocDAO();
		return refDAO.getDtoDataDAO(refernceid);
		
	}

	public ArrayList getDistrictId(String refernceid) throws Exception {
	
		SuppleDocDAO refDAO = new SuppleDocDAO();
		return refDAO.getDistrictIdDAO(refernceid);
		
	}
	
	 public SuppleDocDTO getTotalPartyDetailsBD(String combineData, SuppleDocDTO dto, String langauge) throws Exception
	    {
	    	String data[] = combineData.split("~");
	    	SuppleDocBO refBo = new SuppleDocBO();
	    	SuppleDocDAO refDAO = new SuppleDocDAO();
	    	ArrayList list1 = refBo.getTotalPartyDetailsBO(data);
	    	ArrayList list = (ArrayList) list1.get(0);
	    	if(list.size()>0 || !list.isEmpty())
	    	{
	    dto.setFname1((String)list.get(0));
	    	dto.setMname1((String)list.get(1));
	    	dto.setLname1((String)list.get(2));
	    	dto.setGender1((String)list.get(3));
	    	dto.setAge1(Integer.parseInt((String)list.get(4)));
	    	dto.setCountryId1((String)list.get(5));
	    	dto.setStateId1((String)list.get(6));
	    	dto.setDistrictId1((String)list.get(7));
	    	dto.setAddress1((String)list.get(8));
	    	dto.setPostalCode1(Integer.parseInt((String)list.get(9)));
	    	dto.setPhoneNo1((String)list.get(10));
	    	dto.setMobNo1((String)list.get(11));
	    	dto.setMailId1((String)list.get(12));
	    	dto.setFatherName1((String)list.get(13));
	    	dto.setMotherName1((String)list.get(14));
	    	dto.setRole1((String)list.get(15));
	    	dto.setAddress1((String)list.get(18));
	    	}
	    	
	    	String districtName =refDAO.getDistrictName(dto.getDistrictId1(),langauge);
	    	if(districtName!=null || !districtName.equalsIgnoreCase(""))
	    	dto.setDistrictName1(districtName);
	    	return dto;
	    	
	    	
	    }

	public String getreferenceID(String filingnuber) throws Exception {
		SuppleDocDAO refDAO = new SuppleDocDAO();
		
		return refDAO.getReferenceId(filingnuber);
	}

	public boolean updateFilingNumber(String referId, String remarks, String filingNo2) {
		SuppleDocDAO refDAO = new SuppleDocDAO();
		return refDAO.updateFilingNumberDAO(referId,remarks,filingNo2);
		
	}

	public void setStatusq(String estampDuty) {
		SuppleDocDAO refDAO = new SuppleDocDAO();
		refDAO.updateFilingNumberDAOs(estampDuty);
		
	}
	
	public void setStatusN(String estampDuty) {
		SuppleDocDAO refDAO = new SuppleDocDAO();
		refDAO.setStatusDaoN(estampDuty);
		
	}

	
}