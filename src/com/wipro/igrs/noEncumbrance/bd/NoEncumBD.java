package com.wipro.igrs.noEncumbrance.bd;
import com.newgen.burning.Encumbrance;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.noEncumbrance.bo.NoEncumBO;
import com.wipro.igrs.noEncumbrance.dao.NoEncumDAO;
import com.wipro.igrs.noEncumbrance.dto.FloorDetailsDTO;
import com.wipro.igrs.noEncumbrance.dto.FloorTypeDTO;
import com.wipro.igrs.noEncumbrance.dto.KhasraDTO;
import com.wipro.igrs.noEncumbrance.dto.NoEncumDTO;
import com.wipro.igrs.noEncumbrance.form.NoEncumForm;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.copyissuance.dao.CertifiedCopyDetailsDAO;
import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;

import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * 
 * @author test
 *
 */
public class NoEncumBD {
 
	private Logger logger = 
		(Logger) Logger.getLogger(NoEncumBD.class);
    public NoEncumBD() {
    }
    NoEncumDTO sdDTO=new NoEncumDTO();
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  countryStackBD(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (countryStackBD) Method");
    		listBD=refBO.countryStackBO( languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  countryPropBD(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (countryStackBD) Method");
    		listBD=refBO.countryPropBO( languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  countryBD(String langauge)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (countryStackBD) Method");
    		listBD=refBO.countryBO(langauge);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _countryId
     * @return
     * @throws Exception
     */
    public ArrayList  stateBD(String _countryId,String language)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (stateStackBD) Method");
    		listBD=refBO.stateBO(_countryId,language);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _countryId
     * @return
     * @throws Exception
     */
    public ArrayList  stateStackBD(String _countryId,String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (stateStackBD) Method");
    		listBD=refBO.stateStackBO(_countryId,languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
   
    /**
     * 
     * @param _countryId
     * @return
     * @throws Exception
     */
    public ArrayList  statePropBD(String _countryId)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (stateStackBD) Method");
    		listBD=refBO.statePropBO(_countryId);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  photoIdStackBD(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (photoIdStackBD) Method");
    		listBD=refBO.photoIdStackBO(languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  casteStackBD(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (casteStackBD) Method");
    		listBD=refBO.casteStackBO(languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  religionStackBD(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (religionStackBD) Method");
    		listBD=refBO.religionStackBO(languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _stateId
     * @return
     * @throws Exception
     */
    public ArrayList  districtStackBD(String _stateId,String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBD) Method");
    		listBD=refBO.districtStackBO(_stateId,languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _stateId
     * @return
     * @throws Exception
     */
    public ArrayList  districtPropBD(String _stateId,String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBD) Method");
    		listBD=refBO.districtPropBO(_stateId,languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
   /**
    *  
    * @param _distId
    * @return
    * @throws Exception
    */
    public ArrayList  tehesilPropBD(String _distId,String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBD) Method");
    		listBD=refBO.tehesilPropBO(_distId,languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _tehsilId
     * @return
     * @throws Exception
     */
    public ArrayList  patwariPropBD(String _tehsilId)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBD) Method");
    		listBD=refBO.patwariPropBO(_tehsilId);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _patwariId
     * @return
     * @throws Exception
     */
    public ArrayList  gramPropBD(String _patwariId)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBD) Method");
    		listBD=refBO.gramPropBO(_patwariId);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _stateId
     * @return
     * @throws Exception
     */
    public ArrayList  districtBD(String _stateId,String language)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (districtStackBD) Method");
    		listBD=refBO.districtBO(_stateId,language);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _regNo
     * @return
     * @throws Exception
     */
    public NoEncumDTO regIdCheckBD(String _regNo)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList listBD=new ArrayList();
    	try{
    		logger.info("Inside (regIdCheckBD) Method");
    		
    		listBD=refBO.regIdCheckBO(_regNo);
    		if(listBD.isEmpty()){
    			sdDTO.setErrorMsg("Registration ID doesn't exist");
    			sdDTO.setRegNoSearch(_regNo);
    		}
    		else
    		{
    			ArrayList list=(ArrayList)listBD.get(0);
    			sdDTO.setRegNoSearch((String)list.get(0));
    			sdDTO.setCreatedDate((String)list.get(1));
    			sdDTO.setPropertyLockFlag((String)list.get(2));
    			sdDTO.setCaveatFlag((String)list.get(3));
    			sdDTO.setCaseFlag((String)list.get(4));
    			sdDTO.setPoaFlag((String)list.get(5));
    			sdDTO.setCourtOrderFlag((String)list.get(6));
    			sdDTO.setErrorMsg("ERROR");
    		}
    		return sdDTO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param regisId
     * @return
     * @throws Exception
     */
    
    public ArrayList regIdCheckInfo(String regisId,String language) throws Exception{
    	ArrayList dataSet, row;
		ArrayList retList = new ArrayList();
		try {
			NoEncumDAO dao=new NoEncumDAO();
			dataSet = dao.regIdCheckInfo(regisId); 
			if (dataSet != null && dataSet.size() > 0) {
				NoEncumDTO ndto;
				for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
					row = (ArrayList) dataSet.get(iLoop);
					ndto = new NoEncumDTO();
					ndto.setRegNoSearch(row.get(0).toString());
					ndto.setPropertyTxnId(row.get(1).toString());
					ndto.setPropertyTypeId(row.get(2).toString());
					if("en".equalsIgnoreCase(language))
					{	
					ndto.setPropertyTypeLabel(row.get(3).toString());
					}
					else
					{
						ndto.setPropertyTypeLabel(row.get(4).toString());
	
					}
					retList.add(ndto);
				}
				dataSet.clear();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return retList;
	}   
    
    
    /**
     * 
     * @param _txnId
     * @param _frDate
     * @param _toDate
     * @return
     * @throws Exception
     */
    public ArrayList  searchListViewBD(String _txnId,String _frDate,String _toDate)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (searchListViewBD) Method");
    		
    		listBD=refBO.searchListViewBO(_txnId,_frDate,_toDate);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    /**
     * 
     * @param _regUserId
     * @return
     * @throws Exception
     */
    public NoEncumDTO userIdCheckBD(String _regUserId)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
		NoEncumDTO dto=null;
    	try{
    		logger.info("Inside (userIdCheckBD) Method");
    		listBD=refBO.userIdCheckBO(_regUserId);
    		dto=new NoEncumDTO();
    		dto.setUserMode("Ex");
    		dto.setRegUserId(_regUserId);
    		if(!listBD.isEmpty())
    		{
    			ArrayList list=(ArrayList)listBD.get(0);
    			dto.setUseIdFlag("userExists");
    			
    			dto.setFname((String)list.get(1));
    			dto.setMname((String)list.get(2));
    			dto.setLname((String)list.get(3));
    			dto.setGender((String)list.get(4));
    			dto.setAge(String.valueOf(list.get(5)));
    			dto.setCountryId((String)list.get(6));
    			dto.setCountryName2((String)list.get(7));
    			dto.setStateId((String)list.get(8));
    			dto.setStateName2((String)list.get(9));
    			dto.setDistrictId((String)list.get(10));
    			dto.setDistrictName2((String)list.get(11));
    			dto.setAddress((String)list.get(12));
    			dto.setPostalCode(String.valueOf(list.get(13)));
    			dto.setPhoneNo((String)list.get(14));
    			dto.setMobNo((String)list.get(15));
    			dto.setMailId((String)list.get(16));
    			dto.setPhotoProofTypeId((String)list.get(17));
    			dto.setPhotoProofTypeName2((String)list.get(18));
    			dto.setPhotoId((String)list.get(19));
    			dto.setPhotoBankName((String)list.get(20));
    			dto.setPhotoBankAddress((String)list.get(21));
    			dto.setFatherName((String)list.get(22));
    			dto.setMotherName((String)list.get(23));
    			dto.setCasteId((String)list.get(24));
    			dto.setCasteName2((String)list.get(25));
    			dto.setReligionId((String)list.get(26));
    			dto.setReligionName2((String)list.get(27));
    			dto.setNationality((String)list.get(28));
    			return dto;
    		}
    		else {
    			dto.setUseIdFlag("doesnt");
    			return dto;
			}
    	}catch (Exception e) {
    		logger.error(e);
    	}
    	return dto;
    }
    /**
     * 
     * @param _sdDTO
     * @return
     * @throws Exception
     */
    public String insertNoEncumDetailsBD(NoEncumDTO _sdDTO,String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		IGRSCommon common=new IGRSCommon();
		String txnId="";
    	try{
    		if(_sdDTO.getTxnId()==null||_sdDTO.getTxnId().equalsIgnoreCase(""))
    		{
    			ArrayList district=getPaymentParameter(_sdDTO.getRegNo(),languageLocale);
            	String district_id="";
            	ArrayList list;
            	txnId=common.getAutoId("IGRS_CERCOPY_TXN_PARTY_DETAILS","CER_COPY_TXN_ID","NEC");
    			_sdDTO.setCerCopyTxnId(txnId);
    			if(refBO.insertSuppliDetailsBO(_sdDTO));
    			return txnId;
    		}
    		else
    		{
    			_sdDTO.setCerCopyTxnId(_sdDTO.getTxnId());
    			if(refBO.insertSuppliDetailsBO(_sdDTO));
    			return _sdDTO.getTxnId();
    		}
    		/*if(refBO.insertSuppliDetailsBO(_sdDTO));
			return txnId;*/
    		}
    		catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	/*if(_sdDTO.getSelectedItems().size()>0)
    		{
    			String param2[]=new String[7];
    			param2[0]=""+txnId;
    			param2[1]=""+_sdDTO.getFunId();
    			param2[2]=""+_sdDTO.getCurrentUserId();
    			param2[4]="No-Encumbrance";
    			param2[5]="U";
    			param2[6]=_sdDTO.getPurposeReq();
    		
    		}
    		logger.info("Inside (insertSuppliDetailsBD) Method");
    		String param1[]=new String[23];
    		param1[0]=""+txnId;
    		param1[1]=""+_sdDTO.getFname();
    		param1[2]=""+_sdDTO.getMname();
    		param1[3]=""+_sdDTO.getLname();
    		param1[4]=""+_sdDTO.getGender();
    		param1[5]=""+_sdDTO.getAge(); // Age Int
    		param1[6]=""+_sdDTO.getNationality();
    		param1[7]=""+_sdDTO.getCountryId();
    		param1[8]=""+_sdDTO.getStateId();
    		param1[9]=""+_sdDTO.getDistrictName();
    		param1[10]=""+_sdDTO.getAddress();
    		param1[11]=""+_sdDTO.getPostalCode();//Postal Code NUmber
    		param1[12]=""+_sdDTO.getPhoneNo();
    		param1[13]=""+_sdDTO.getMobNo();
    		param1[14]=""+_sdDTO.getMailId();
    		param1[15]=""+_sdDTO.getPhotoProofTypeName();
    		param1[16]=""+_sdDTO.getPhotoId();
    		param1[17]=""+_sdDTO.getPhotoBankName();
    		param1[18]=""+_sdDTO.getPhotoBankAddress();
    		param1[19]=""+_sdDTO.getFatherName();
    		param1[20]=""+_sdDTO.getMotherName();
    		param1[21]=""+_sdDTO.getCasteName();
    		param1[22]=""+_sdDTO.getReligionName();
    		
    		
    			String param2[]=new String[7];
    			param2[0]=param1[0];
    			param2[1]=""+_sdDTO.getRegNo();
    			param2[2]=""+_sdDTO.getCurrentUserId();
    			param2[3]=""+_sdDTO.getPayTxnId();
    			param2[4]="No-Encumbrance";
    			param2[5]="U";
    			param2[6]=_sdDTO.getPurposeReq();
    			
    		String param3[]=new String[23];	
    
    		if("3".equals(_sdDTO.getPropertyName()))
    		{
    		param3[0]=""+txnId;
    		param3[1]=""+_sdDTO.getPropDistrictId();
    		param3[2]=""+_sdDTO.getPropTehesilId();
    		param3[3]=""+_sdDTO.getPropPatwariName();
    		param3[4]=""+_sdDTO.getPropGramName();
    		param3[5]=""+_sdDTO.getPropertyName();
    		param3[6]=""+_sdDTO.getAgViKhand();
    		param3[7]=""+_sdDTO.getAgRicircle();
    		param3[8]=""+_sdDTO.getAgKhasraNo();
    		param3[9]=""+_sdDTO.getAgLayoutDtls();
    		param3[10]=""+_sdDTO.getAgPustikaNo();
    		param3[11]=""+_sdDTO.getAgEastboundryDtls();
    		param3[12]=""+_sdDTO.getAgWestboundryDtls();
    		param3[13]=""+_sdDTO.getAgNorthboundryDtls();
    		param3[14]=""+_sdDTO.getAgSouthboundryDtls();
    		param3[15]=""+_sdDTO.getAgArea();
    		param3[16]=""+_sdDTO.getRegNo();    		
    		param3[17]=""+_sdDTO.getIsSplit();
    		param3[18]="";
    		param3[19]="";
    		param3[20]="";
    		param3[21]="";
    		param3[22]="";
    		}
    		
    		if("2".equals(_sdDTO.getPropertyName()))
    		{
    		param3[0]=""+txnId;
    		param3[1]=""+_sdDTO.getPropDistrictId();
    		param3[2]=""+_sdDTO.getPropTehesilId();
    		param3[3]=""+_sdDTO.getPropPatwariName();
    		param3[4]=""+_sdDTO.getPropGramName();
    		param3[5]=""+_sdDTO.getPropertyName();
    		param3[6]=""+_sdDTO.getBuildViKhand();
    		param3[7]=""+_sdDTO.getBuildRicircle();
    		param3[8]=""+_sdDTO.getBuildKhasraNo();
    		param3[9]=""+_sdDTO.getBuildLayout();
    		param3[10]=""+_sdDTO.getBuildPlotNo();
    		param3[11]=""+_sdDTO.getBuildEastboundryDtls();
    		param3[12]=""+_sdDTO.getBuildWestboundryDtls();
    		param3[13]=""+_sdDTO.getBuildNorthboundryDtls();
    		param3[14]=""+_sdDTO.getBuildSouthboundryDtls();
    		param3[15]=""+_sdDTO.getBuildArea();
    		param3[16]=""+_sdDTO.getRegNo();
    		param3[17]=""+_sdDTO.getIsSplit();
    		param3[18]=""+_sdDTO.getPlotArea();
    		param3[19]=""+_sdDTO.getNoOfShop();
    		param3[20]=""+_sdDTO.getBuildingType();
    		param3[21]=""+_sdDTO.getNoOfConstFloor();
    		param3[22]=""+_sdDTO.getBuildNazSheetNo();
    		
    		}
    		if("1".equals(_sdDTO.getPropertyName()))
    		{
    		param3[0]=""+txnId;
    		param3[1]=""+_sdDTO.getPropDistrictId();
    		param3[2]=""+_sdDTO.getPropTehesilId();
    		param3[3]=""+_sdDTO.getPropPatwariName();
    		param3[4]=""+_sdDTO.getPropGramName();
    		param3[5]=""+_sdDTO.getPropertyName();
    		param3[6]=""+_sdDTO.getPltViKhand();
    		param3[7]=""+_sdDTO.getPltRicircle();
    		param3[8]=""+_sdDTO.getPltKhasraNo();
    		param3[9]=""+_sdDTO.getPltLayout();
    		param3[10]=""+_sdDTO.getPltPlotNo();
    		param3[11]=""+_sdDTO.getPltEastboundryDtls();
    		param3[12]=""+_sdDTO.getPltWestboundryDtls();
    		param3[13]=""+_sdDTO.getPltNorthboundryDtls();
    		param3[14]=""+_sdDTO.getPltSouthboundryDtls();
    		param3[15]=""+_sdDTO.getPltArea();
    		param3[16]=""+_sdDTO.getRegNo();
    		param3[17]=""+_sdDTO.getIsSplit();
    		param3[18]="";
    		param3[19]="";
    		param3[20]="";
    		param3[21]="";
    		param3[22]=""+_sdDTO.getPltNazSheetNo();
    		}
    		
    		
    		
    		
    		
    		
    		if("Ex".equalsIgnoreCase(_sdDTO.getUserMode()))
    		{
    			param1[9]=""+_sdDTO.getDistrictId();
    			param1[15]=""+_sdDTO.getPhotoProofTypeId();
    			param1[21]=""+_sdDTO.getCasteId();
    			param1[22]=""+_sdDTO.getReligionId();
    		}
    		//if(refBO.insertSuppliDetailsBO(param1,param2,param3,_sdDTO.getPropertyName()))
    		if(refBO.insertSuppliDetailsBO(_sdDTO));
    			return txnId;
    	}*/
    	
    	return txnId;
    }
    
    /**
     * 
     * @param _txnId
     * @return
     * @throws Exception
     */
    public NoEncumDTO getTotalDetailsBD(String _txnId)throws Exception
    {
    	String propertyName="";
		String buildViKhand="";
		String propDistrictName="";
		String propTehesilName="";
		String propPatwariName="";
		String propGramName="";
		String municipalty="";
		String areaType="";
		String regNo="";
		String regDate="";
		String riCircle="";
		String layout="";
		String khasra="";
		String nazool="";
		String rinPustika="";
		String east="";
		String west="";
		String north="";
		String south="";
		String totalArea="";
		String plotArea="";
		String noOFShop="";
		String buildingType="";
		String noOfConstFloors="";    			
		String splitPart="";
		
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList listBD=new ArrayList();
		NoEncumDTO dto=null;
    	try{
    		logger.info("Inside (getTotalDetailsBD) Method");
    		
    		listBD=refBO.getTotalDetailsBO(_txnId);
    		if(!listBD.isEmpty())
    		{
    			dto=new NoEncumDTO();
    			ArrayList list=(ArrayList)listBD.get(0);
    			dto.setCerCopyTxnId(_txnId);
    			dto.setFname((String)list.get(0));
    			dto.setMname((String)list.get(1));
    			dto.setLname((String)list.get(2));
    			dto.setGender((String)list.get(3));
    			if("M".equalsIgnoreCase((String)list.get(3))){dto.setViewGender("Male");}else{dto.setViewGender("Female");}    			
    			dto.setAge(String.valueOf(list.get(4)));
    			dto.setCountryId((String)list.get(5));
    			dto.setCountryName2((String)list.get(6));
    			dto.setStateId((String)list.get(7));
    			dto.setStateName2((String)list.get(8));
    			dto.setDistrictId((String)list.get(9));
    			dto.setDistrictName2((String)list.get(10));
    			dto.setAddress((String)list.get(11));
    			dto.setPostalCode(String.valueOf(list.get(12)));
    			dto.setPhoneNo((String)list.get(13));
    			dto.setMobNo((String)list.get(14));
    			dto.setMailId((String)list.get(15));
    			dto.setPhotoProofTypeId((String)list.get(16));
    			dto.setPhotoProofTypeName2((String)list.get(17));
    			dto.setPhotoId((String)list.get(18));
    			dto.setPhotoBankName((String)list.get(19));
    			dto.setPhotoBankAddress((String)list.get(20));
    			dto.setFatherName((String)list.get(21));
    			dto.setMotherName((String)list.get(22));
    			dto.setCasteId((String)list.get(23));
    			dto.setCasteName2((String)list.get(24));
    			dto.setReligionId((String)list.get(25));
    			dto.setReligionName2((String)list.get(26));
    			dto.setNationality((String)list.get(27));
    			dto.setCreatedDate((String)list.get(28));
    			dto.setPurposeReq((String)list.get(29));
    			propertyName=(String)list.get(30);
    			buildViKhand=(String)list.get(31);
    			propDistrictName=(String)list.get(32);
    			propTehesilName=(String)list.get(33);
    			propPatwariName=(String)list.get(34);
    			propGramName=(String)list.get(35);
    			municipalty=(String)list.get(36);
    			areaType=(String)list.get(37);
    			regNo=(String)list.get(38);
    			regDate=(String)list.get(39);
    			riCircle=(String)list.get(40);
    			layout=(String)list.get(41);
    			khasra=(String)list.get(42);
    			nazool=(String)list.get(43);
    			rinPustika=(String)list.get(44);
    			east=(String)list.get(45);
    			west=(String)list.get(46);
    			north=(String)list.get(47);
    			south=(String)list.get(48);
    			totalArea=(String)list.get(49);
    			plotArea=(String)list.get(50);
    			noOFShop=(String)list.get(51);
    			buildingType=(String)list.get(52);
    			noOfConstFloors=(String)list.get(53); 			
    			splitPart=(String)list.get(54);
    			
    			
    			dto.setPropertyName(propertyName);    			
    			dto.setPropDistrictName(propDistrictName);
    			dto.setPropTehesilName(propTehesilName);
    			dto.setPropPatwariName(propPatwariName);
    			dto.setPropGramName(propGramName);    			
    			dto.setPropGramName(propGramName);
    			dto.setMunicipalty(municipalty);
    			dto.setAreaType(areaType);
    			dto.setRegNo(regNo);
    			dto.setRegDate(regDate);
    			
    			if("BUILDING".equals(propertyName))
    			{
    				dto.setBuildViKhand(buildViKhand);
    				dto.setBuildRicircle(riCircle);
    				dto.setBuildKhasraNo(khasra);
    				dto.setBuildLayout(layout);
    				dto.setBuildMohalla(rinPustika);
    				dto.setBuildNazSheetNo(nazool);
    				dto.setBuildPlotNo(totalArea);
    				dto.setPlotArea(plotArea);
    				dto.setBuildingType(buildingType);
    				dto.setNoOfConstFloor(noOfConstFloors);
    				dto.setNoOfShop(noOFShop);
    				dto.setBuildCeilingType(buildingType);
    				dto.setBuildEastboundryDtls(east);
    				dto.setBuildWestboundryDtls(west);
    				dto.setBuildNorthboundryDtls(north);
    				dto.setBuildSouthboundryDtls(south);
    				ArrayList<FloorDetailsDTO> familyMembers = refBO.getFloorDetails(_txnId);
    				dto.setFloordetails(familyMembers);
    			}
    			if("AGRI LAND".equals(propertyName))
    			{
    				dto.setAgViKhand(buildViKhand);
    				dto.setAgRicircle(riCircle);
    				dto.setAgKhasraNo(khasra);
    				dto.setAgLayoutDtls(layout);
    				dto.setIsSplit(splitPart);
    				dto.setAgKhasraNo(khasra); 
    				dto.setAgEastboundryDtls(east);
    				dto.setAgWestboundryDtls(west);
    				dto.setAgNorthboundryDtls(north);
    				dto.setAgSouthboundryDtls(south);
    				dto.setAgSize(totalArea);
    				dto.setAgArea(plotArea);
    			}
    			if("PLOT".equals(propertyName))
    			{
    				dto.setPltViKhand(buildViKhand);
    				dto.setPltRicircle(riCircle);
    				dto.setPltKhasraNo(khasra);
    				dto.setPltLayout(layout);
    				dto.setIsSplit(splitPart);
    				dto.setPltMohala(rinPustika);
    				dto.setPltNazSheetNo(nazool);
    				dto.setPltPlotNo(totalArea);
    				dto.setPltNorthboundryDtls(north);
    				dto.setPltSouthboundryDtls(south);
    				dto.setPltEastboundryDtls(east);
    				dto.setPltWestboundryDtls(west);
    				dto.setPltSize(totalArea);
    				dto.setPltArea(plotArea);
    				//dto.setPltResiCom();
    				//dto.setPltCorner();
    				
    			}
    			
    			return dto;
    		}
    		return dto;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    /**
     * 
     * @param _retFunId
     * @param _serId
     * @param _userType
     * @return
     * @throws Exception
     */
    public ArrayList getOthersFeeDuty(String _retFunId,String _serId,String _userType)throws Exception{
    	ArrayList otherList=null;
    	try{
    		IGRSCommon common=new IGRSCommon();
    		logger.debug("in bd--<><>"+_retFunId+_serId+_userType);
    		otherList=common.getOthersFeeDuty(_retFunId, _serId, _userType);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return otherList;
    }
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  propertyBD(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (casteStackBD) Method");
    		listBD=refBO.propertyBO(languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  getMunicipalList()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (casteStackBD) Method");
    		listBD=refBO.getMunicipalList();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList  getAreaTypeList(String languageLocale)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		NoEncumBO refBO=new NoEncumBO();
		ArrayList  listBD=new ArrayList ();
    	try{
    		logger.info("Inside (casteStackBD) Method");
    		listBD=refBO.getAreaTypeList(languageLocale);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    
    
    
  /**
   * 
   * @return
   */
    public ArrayList<FloorTypeDTO> getFloorMasterList(String languageLocale) {
		ArrayList<FloorTypeDTO> retVal = new ArrayList<FloorTypeDTO>();
		ArrayList data, row;
		FloorTypeDTO floorType;
		NoEncumDAO nDao=new NoEncumDAO();
		try {
			//RELATIVE_TYPE_ID, RELATIVE_TYPE_NAME 
			data = nDao.getFloorMasterList(languageLocale);
			for (Object item : data) {
				floorType = new FloorTypeDTO();
				row = (ArrayList) item;
				floorType.setFloorID((String) row.get(0));
				floorType.setFloorType((String) row.get(1));
				retVal.add(floorType);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
    
    /**
     * 
     * @param funId
     * @return
     * @throws Exception
     */
    public NoEncumDTO getFunctionName(String funId,String languageLocale) throws Exception {
    	NoEncumDTO dto = new NoEncumDTO();
    	NoEncumDAO dao=new NoEncumDAO();

        String[] param = new String[1];
        param[0] = funId;
       
        ArrayList ret = dao.getFunctionName(param,languageLocale);
       
        if (ret!=null)
        {
            for (int i = 0; i < ret.size(); i++)
            {
                ArrayList list = (ArrayList)ret.get(0);
                String pFunName=(String)list.get(0);
                String pModName=(String)list.get(1);
                logger.debug("pFunName = "+pFunName+"pModName = "+pModName);
                dto.setParentFunName((String)list.get(0));
                dto.setParentModName((String)list.get(1));
                
            }
        }

        
        return dto;
    }

    /**
     * 
     * @param dto
     * @param pymntStatus
     * @return
     * @throws Exception
     */
    public String updateICopyInfo(NoEncumDTO dto,String pymntStatus) throws Exception {
    	NoEncumDAO dao=new NoEncumDAO();
        String refId ="";
        String retRefId ="";
        logger.debug("REFERENCE ID IS = "+refId);
        boolean retMsg = dao.updateICopyInfo(dto,pymntStatus);       
        if(retMsg){retRefId="succ";}
        else{retRefId="fail";}
        return retRefId;
    }
    
    /**
     * 
     * @param dto
     * @return
     * @throws Exception
     */
    public String updatePaymentInfo(NoEncumDTO dto) throws Exception {
    	NoEncumDAO dao=new NoEncumDAO();
        String refId ="";
        String retRefId ="";
        logger.debug("REFERENCE ID IS = "+refId);
        boolean retMsg = dao.updatePaymentInfo(dto);       
        if(retMsg){retRefId="succ";}
        else{retRefId="fail";}
        return retRefId;
    }

    public ArrayList getPendingNoEncumApps(String userId)
    {
    	NoEncumDAO dao=new NoEncumDAO();
    	return dao.getPendingNoEncumApps(userId);
    }
    public String getTehsilName (String ofcId,String languageLocale) throws Exception
    {
    	NoEncumDAO dao=new NoEncumDAO();
    	return dao.getTehsilName(ofcId,languageLocale);
    }
    public String getRegDate (String regNo) throws Exception
    {
    	NoEncumDAO dao=new NoEncumDAO();
    	RegCommonBO regBo = new RegCommonBO();
    	return regBo.convertDOB(dao.getRegDate(regNo));
    }
    public String getNEDate (String txnid) throws Exception
    {
    	NoEncumDAO dao=new NoEncumDAO();
    	RegCommonBO regBo = new RegCommonBO();
    	return regBo.convertDOB(dao.getNEDate(txnid));
    }
    public String setPaymentDetails(NoEncumDTO dto,String funId,String userId)
    {
    	String paymentId="";
    	try {
    		NoEncumDAO dao=new NoEncumDAO();
    		ArrayList list=dao.isPaymentEntry(dto.getTxnId());
    		if(false)
    		//if(list.size()>0)
    		{	
    		ArrayList rowlist=(ArrayList) list.get(0);
    		if(rowlist.get(0).toString().equalsIgnoreCase("I"))
    			{
    				return rowlist.get(1).toString();
    			}
    			else
    			{
    				return dao.setPaymentDetails(dto, funId, userId);
    			}
    		}
    		else
    		{
    		return dao.setPaymentDetails(dto, funId, userId);
    		}
    		} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return paymentId;
    }  
    public NoEncumDTO getDetailsOnId(String txnId,String languageLocale)
    {
    	NoEncumBO bo= new NoEncumBO();
    	boolean flag = false;
    	ArrayList list=bo.getDetailsOnId(txnId,languageLocale);
    	ArrayList rowlist;
    	NoEncumDTO dto= new NoEncumDTO();
    	if(list!=null&&list.size()>0)
    	{
    		
    		rowlist=(ArrayList ) list.get(0);
    		dto.setTxnId(rowlist.get(0)==null?"":rowlist.get(0).toString());
    		double bal=isCompletePayment(dto.getTxnId());
    		dto.setFname(rowlist.get(2)==null?"":rowlist.get(2).toString());
    		dto.setMname(rowlist.get(3)==null?"":rowlist.get(3).toString());
    		dto.setLname(rowlist.get(4)==null?"":rowlist.get(4).toString());
    		dto.setFatherName(rowlist.get(19)==null?"":rowlist.get(19).toString());
    		dto.setMotherName(rowlist.get(20)==null?"":rowlist.get(20).toString());
    		dto.setSpouseName(rowlist.get(5)==null?"":rowlist.get(5).toString());
    		if(rowlist.get(5)!=null)
    		{
    			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    				dto.setViewGender(rowlist.get(5).toString().equalsIgnoreCase("M")?RegInitConstant.MALE_ENGLISH:RegInitConstant.FEMALE_ENGLISH);
    			}    		
    			else{
    				dto.setViewGender(rowlist.get(5).toString().equalsIgnoreCase("M")?RegInitConstant.MALE_HINDI:RegInitConstant.FEMALE_HINDI);
    			}
    		}
    		dto.setDocumentSavePath(rowlist.get(42)==null?"":rowlist.get(42).toString());
    		dto.setAge(rowlist.get(6)==null?"":rowlist.get(6).toString());
    		dto.setCasteName2(rowlist.get(5)==null?"":rowlist.get(21).toString());
    		dto.setReligionName2(rowlist.get(5)==null?"":rowlist.get(22).toString());
    		dto.setNationality(rowlist.get(5)==null?"":rowlist.get(7).toString());
    		//dto.setPhyChallanged(rowlist.get(5)==null?"":rowlist.get(5).toString());
    		dto.setPhotoProofTypeName2(rowlist.get(17)==null?"":rowlist.get(17).toString());
    		dto.setPhotoId(rowlist.get(18)==null?"":rowlist.get(18).toString());
    		//dto.setOccupation(rowlist.get(5)==null?"":rowlist.get(5).toString());
    		dto.setAddress(rowlist.get(11)==null?"":rowlist.get(11).toString());
    		dto.setCountryName2(rowlist.get(8)==null?"":rowlist.get(8).toString());
    		dto.setStateName2(rowlist.get(9)==null?"":rowlist.get(9).toString());
    		dto.setDistrictName2(rowlist.get(10)==null?"":rowlist.get(10).toString());
    		dto.setPostalCode(rowlist.get(12)==null?"":rowlist.get(12).toString());
    		dto.setMobNo(rowlist.get(14)==null?"":rowlist.get(14).toString());
    		dto.setMailId(rowlist.get(15)==null?"":rowlist.get(15).toString());
    		dto.setPurposeReq(rowlist.get(33)==null?"":rowlist.get(33).toString());
    		dto.setUpdtdRemarks(rowlist.get(23)==null?"":rowlist.get(23).toString());
    		dto.setPhoneNo(rowlist.get(13)==null?"":rowlist.get(13).toString());
    		dto.setTotalFee(rowlist.get(24)==null?"":rowlist.get(24).toString());
    		double paid=Double.parseDouble(dto.getTotalFee())-bal;
    		dto.setBalanceAmount(String.valueOf(bal));
    		dto.setPaidAmount(String.valueOf(paid));
    		dto.setDateOfRequest(rowlist.get(37)==null?"":rowlist.get(37).toString());
    		if(rowlist.get(36)!=null && !rowlist.get(36).toString().equalsIgnoreCase("U"))
    		{
    			
    			if(rowlist.get(36).toString().equalsIgnoreCase("P"))
    			{
    				dto.setStatus("Pending At SR");
    				dto.setDisDate("Not Available");
    			}
    			else if(rowlist.get(36).toString().equalsIgnoreCase("C"))
    			{
    				dto.setStatus("Completed Not Dispatched ");
    				dto.setDisDate("Not Available");
    			}
    			else if(rowlist.get(36).toString().equalsIgnoreCase("D"))
    			{
    				flag= true;
    				if(rowlist.get(38)!=null && !rowlist.get(38).toString().equalsIgnoreCase(""))
    				{
    					flag=false;
    					dto.setStatus("Dispatched");
    					dto.setDisDate(rowlist.get(38).toString());
    					dto.setPostalTrakingNum(rowlist.get(39).toString());
    				}
    				else
    				{
    					if(rowlist.get(1)!=null&&!rowlist.get(1).toString().equalsIgnoreCase(""))
    		    		{
    						
    						dto.setStatus("Manual Collection At:"+getCollectionOffice(rowlist.get(1).toString(),languageLocale));
    		    		}
    					
    					dto.setDisDate("Not Available");
    				}
    			}
    		}
    		 RegCommonBO regBo = new RegCommonBO();
    		 try{
    		if(rowlist.get(40)!=null &&! rowlist.get(40).toString().equalsIgnoreCase(""))
    		{	
    		dto.setFromDate(regBo.convertDOB(rowlist.get(40).toString()));
    		}
    		else
    		{
    			dto.setFromDate("-");
    		}
    		if(rowlist.get(41)!=null &&! rowlist.get(41).toString().equalsIgnoreCase(""))
    		{
    		dto.setToDate(regBo.convertDOB(rowlist.get(41).toString()));
    		}
    		else
    		{
    			dto.setToDate("-");
    		}
    		//	dto.setFromDate("");
    	    //	dto.setToDate("");
    		 }
    		 catch(Exception e){
    			 dto.setFromDate("-");
    	    		dto.setToDate("-");
    		 }
    		if(rowlist.get(1)!=null&&!rowlist.get(1).toString().equalsIgnoreCase(""))
    		{
    			dto.setRegNo(rowlist.get(1).toString());
    			NoEncumBO objEncumBO = new NoEncumBO();
    			dto.setRegTxnId(objEncumBO.getRegTxn( dto.getRegNo()));
    			ArrayList propertymap=bo.getPropertyMappingList(txnId);
    			if(propertymap!=null&&propertymap.size()>0)
    			{
    				ArrayList propId=new ArrayList();
    				for(int i=0;i<propertymap.size();i++)
    				{
    					ArrayList rowlist1= (ArrayList) propertymap.get(i);
    					NoEncumDTO dto1=new NoEncumDTO();
    					dto1.setPropertyTxnId(rowlist1.get(0)==null?"":rowlist1.get(0).toString());
    					dto1.setPropertyTypeLabel(rowlist1.get(1)==null?"":rowlist1.get(1).toString());
    					propId.add(dto1);	
    				}
    				dto.setSelectedItems(propId);
    			}
    		}
    		else
    		{
    			ArrayList propertyDetailsList=bo.getPropertyDetails(txnId);
    			if(propertyDetailsList!=null&&propertyDetailsList.size()>0)
    				{
    					ArrayList propRowList=(ArrayList) propertyDetailsList.get(0);
    					dto.setPropDistrictId(propRowList.get(4)==null?"":propRowList.get(4).toString());
    					//dto.setShowpCountyName(getPropCountryName(propRowList.get(2).toString()));
    					dto.setShowpCountyName("");
    					dto.setShowpDistName(getPropDistrictName(propRowList.get(4)==null?"":propRowList.get(4).toString(),languageLocale));
    					if(flag==true)
    					{
    					dto.setStatus("Manual Collection At Any Sro office  :"+dto.getShowpDistName()+" District" );
    					}
    					dto.setShowpStateName(getPropStateName(propRowList.get(3)==null?"":propRowList.get(3).toString(),languageLocale));
    					dto.setShowpGramName(getPropGramName(propRowList.get(7)==null?"":propRowList.get(7).toString(),languageLocale));
    					dto.setShowpMuncpaltyName(getPropMCDName(propRowList.get(8)==null?"":propRowList.get(8).toString(),languageLocale));
    					dto.setShowpTehesilName(getPropTehsilName(propRowList.get(5)==null?"":propRowList.get(5).toString(),languageLocale));
    					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    					dto.setShowpUrbanName(propRowList.get(11).toString().equalsIgnoreCase("u")?"URBAN":"RURAL");
    					}else{
    						dto.setShowpUrbanName(propRowList.get(11).toString().equalsIgnoreCase("u")?"नगरीय क्षेत्र":"ग्राम क्षेत्र");
    					}
    					dto.setShowpPatwariName(getPropPatwariName(propRowList.get(6)==null?"":propRowList.get(6).toString(),languageLocale));
    					ArrayList khasraList=bo.getKhasraDetails(txnId);
    					if(khasraList!=null&&khasraList.size()>0)
    					{
    						ArrayList khasraId=new ArrayList();
    						for(int i=0;i<khasraList.size();i++)
    						{
    							ArrayList	khasraRowList=(ArrayList) khasraList.get(i);
    							KhasraDTO kdto=new KhasraDTO();
    							kdto.setKhasraNumber(khasraRowList.get(0)==null?"":khasraRowList.get(0).toString());
    							kdto.setRinPustika(khasraRowList.get(1)==null?"":khasraRowList.get(1).toString());
    							kdto.setKhasraArea(khasraRowList.get(2)==null?"":khasraRowList.get(2).toString());
    							kdto.setLagaan(khasraRowList.get(3)==null?"":khasraRowList.get(3).toString());
    							khasraId.add(kdto);
    						}
    						dto.setKhasraList(khasraId);
    					}
    					
    					if("2".equals(propRowList.get(1).toString()))//buildingDetails
    					{
    						dto.setPropertyName("2");
    						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    						dto.setShowPropName("BUILDING");
    						}else{
    							dto.setShowPropName("बिल्डिंग");
    						}
    						dto.setBuildViKhand(propRowList.get(9)==null?"":propRowList.get(9).toString());
    						dto.setBuildRicircle(propRowList.get(10)==null?"":propRowList.get(10).toString());
    						dto.setBuildLayout(propRowList.get(12)==null?"":propRowList.get(12).toString());
    						dto.setBuildMohalla(propRowList.get(5)==null?"":propRowList.get(5).toString());
    						dto.setBuildNazSheetNo(propRowList.get(20)==null?"":propRowList.get(20).toString());
    						dto.setBuildPlotNo(propRowList.get(21)==null?"":propRowList.get(21).toString());
    						dto.setPlotArea(propRowList.get(18)==null?"":propRowList.get(18).toString());
    						dto.setBuildingType(propRowList.get(24)==null?"":propRowList.get(24).toString());
    						dto.setNoOfShop(propRowList.get(25)==null?"":propRowList.get(25).toString());
    						dto.setBuildCeilingType(propRowList.get(26)==null?"":propRowList.get(26).toString());
    						dto.setBuildSouthboundryDtls(propRowList.get(14)==null?"":propRowList.get(14).toString());
    						dto.setBuildEastboundryDtls(propRowList.get(15)==null?"":propRowList.get(15).toString());
    						dto.setBuildNorthboundryDtls(propRowList.get(13)==null?"":propRowList.get(13).toString());
    						dto.setBuildWestboundryDtls(propRowList.get(16)==null?"":propRowList.get(16).toString());
    						ArrayList floorList=bo.getFloorDetail(txnId);
        					if(floorList!=null&&floorList.size()>0)
        					{
        						dto.setNoOfConstFloor(String.valueOf(floorList.size()));
        						ArrayList floorId=new ArrayList();
        						for(int i=0;i<floorList.size();i++)
        						{
        							ArrayList	floorRowList=(ArrayList) floorList.get(i);
        							FloorDetailsDTO fdto=new FloorDetailsDTO();
        							fdto.setFloorLabel(floorRowList.get(0)==null?"":floorRowList.get(0).toString());
        							fdto.setBuildingSize(floorRowList.get(1)==null?"":floorRowList.get(1).toString());
        							fdto.setBuildingSizeBreadth(floorRowList.get(2)==null?"":floorRowList.get(2).toString());
        							fdto.setBuildingArea(floorRowList.get(3)==null?"":floorRowList.get(3).toString());
        							floorId.add(fdto);
        						}
        						dto.setFloordetails(floorId);
        					}
    					
    					}
    					else if("3".equals(propRowList.get(1).toString()))
    					{
    						dto.setPropertyName("3");
    						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    						dto.setShowPropName("AGRICULTURAL");
    						}else{
    							dto.setShowPropName("कृषि भूमि");
    						}
    						dto.setAgViKhand(propRowList.get(9)==null?"":propRowList.get(9).toString());
    						dto.setAgRicircle(propRowList.get(10)==null?"":propRowList.get(10).toString());
    						dto.setAgLayoutDtls(propRowList.get(12)==null?"":propRowList.get(12).toString());
    						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    						dto.setIsSplit(propRowList.get(22).toString().equalsIgnoreCase("Y")?"YES":"NO");
    						}else{
    							dto.setIsSplit(propRowList.get(22).toString().equalsIgnoreCase("Y")?RegInitConstant.YES_HINDI:RegInitConstant.NO_HINDI);
    						}
    						dto.setAgNorthboundryDtls(propRowList.get(13)==null?"":propRowList.get(13).toString());
    						dto.setAgSouthboundryDtls(propRowList.get(14)==null?"":propRowList.get(14).toString());
    						dto.setAgEastboundryDtls(propRowList.get(15)==null?"":propRowList.get(15).toString());
    						dto.setAgWestboundryDtls(propRowList.get(16)==null?"":propRowList.get(16).toString());
    						dto.setAgSizeLength(propRowList.get(17)==null?"":propRowList.get(17).toString());
    						dto.setAgSizeBreadth(propRowList.get(32)==null?"":propRowList.get(32).toString());
    						dto.setAgArea(propRowList.get(18)==null?"":propRowList.get(18).toString());
    						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    						
    							dto.setAgIsIrigated("Y".equalsIgnoreCase((String)propRowList.get(19))?"YES":"NO");
    						}else{
    							dto.setAgIsIrigated("Y".equalsIgnoreCase((String)propRowList.get(19))?RegInitConstant.YES_HINDI:RegInitConstant.NO_HINDI);
    						}
    					}
    					else if("1".equals(propRowList.get(1).toString()))
    					{
    						dto.setPropertyName("1");
    						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    						dto.setShowPropName("PLOT");
    						}else{
    							dto.setShowPropName("भूखंड");
    						}
    						dto.setPltViKhand(propRowList.get(9)==null?"":propRowList.get(9).toString());
    						dto.setPltRicircle(propRowList.get(10)==null?"":propRowList.get(10).toString());
    						dto.setPltLayout(propRowList.get(12)==null?"":propRowList.get(12).toString());
    						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    						dto.setIsSplit(propRowList.get(22).toString().equalsIgnoreCase("Y")?"YES":"NO");
    						}else{
    							dto.setIsSplit(propRowList.get(22).toString().equalsIgnoreCase("Y")?RegInitConstant.YES_HINDI:RegInitConstant.NO_HINDI);
    						}
    						dto.setPltMohala(propRowList.get(5)==null?"":propRowList.get(5).toString());
    						dto.setPltNazSheetNo(propRowList.get(20)==null?"":propRowList.get(20).toString());
    						dto.setPltPlotNo(propRowList.get(21)==null?"":propRowList.get(21).toString());
    						dto.setPltNorthboundryDtls(propRowList.get(13)==null?"":propRowList.get(13).toString());
    						dto.setPltEastboundryDtls(propRowList.get(15)==null?"":propRowList.get(15).toString());
    						dto.setPltWestboundryDtls(propRowList.get(16)==null?"":propRowList.get(16).toString());
    						dto.setPltSouthboundryDtls(propRowList.get(14)==null?"":propRowList.get(14).toString());
    						dto.setPltSizeLength(propRowList.get(17)==null?"":propRowList.get(17).toString());
    						dto.setPltSizeBreadth(propRowList.get(32)==null?"":propRowList.get(32).toString());
    						dto.setPltArea(propRowList.get(18)==null?"":propRowList.get(18).toString());
    						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
    						dto.setPltResiCom("R".equalsIgnoreCase((String)propRowList.get(24))?"Residential":"Commercial");
    						}else{
    							dto.setPltResiCom("R".equalsIgnoreCase((String)propRowList.get(24))?"आवासीय":"व्यवसायिक");	
    						}
    						dto.setPltCorner(propRowList.get(23)==null?"":propRowList.get(23).toString());
    					
    					}
    				}
    		}
    	
    	}
    	return dto;
    }

    public double isCompletePayment(String certifiedId)
    {
    	String balance="";
    	double bal=0;
    	try {
    		NoEncumDAO  dao = new NoEncumDAO();
    		balance=dao.isCompletePayment(certifiedId);
    		if(balance.equalsIgnoreCase(""))
    		{
    			String total=dao.TotalPayment(certifiedId);
    			return Double.parseDouble(total);
    		}
    		else
    		{	
    		bal=Double.parseDouble(balance);
    		return bal;
    		}
    		} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return bal;
    }

public boolean deleteDetails(String txnid)
{
	
	try {
		NoEncumDAO  dao = new NoEncumDAO();
		return dao.deleteDetails(txnid);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return true;
	
}
public String getPropCountryName(String id,String languageLocale)
{
	NoEncumBO bo= new NoEncumBO();
	return bo.getPropCountryName(id,languageLocale);
}

public String getPropStateName(String id,String languageLocale)
{
	NoEncumBO bo= new NoEncumBO();
	return bo.getPropStateName(id,languageLocale);
}
public String getPropDistrictName(String id,String languageLocale)
{
	NoEncumBO bo= new NoEncumBO();
	return bo.getPropDistrictName(id,languageLocale);
}
public String getPropTehsilName(String id,String languageLocale)
{
	NoEncumBO bo= new NoEncumBO();
	return bo.getPropTehsilName(id,languageLocale);
}
public String getPropPatwariName(String id,String languageLocale)
{
	NoEncumBO bo= new NoEncumBO();
	return bo.getPropPatwariName(id,languageLocale);
}
public String getPropGramName(String id,String languageLocale)
{
	NoEncumBO bo= new NoEncumBO();
	return bo.getPropGramName(id,languageLocale);
}
public String getPropMCDName(String id,String languageLocale)
{
	NoEncumBO bo= new NoEncumBO();
	return bo.getPropMCDName(id,languageLocale);
}
public ArrayList getPaymentParameter(String regno,String languageLocale)
{
	
		try {
			NoEncumBO bo= new NoEncumBO();
			return bo.getPaymentParameter(regno,languageLocale);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
}
public boolean isDRO(String officeId)
{
	try {
		NoEncumBO bo= new NoEncumBO();
		ArrayList list=bo.isDRO(officeId);
		if(list!=null&&list.size()>0)
		{
			ArrayList rowlist=(ArrayList) list.get(0);
			if(rowlist.get(0).toString().equalsIgnoreCase("DRO"))
					{
						return true;
					}
			else
					{
						return false;
					}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return false;
}
public String getofficeName(String officeid,String languageLocale)
{
	try {
		NoEncumBO bo= new NoEncumBO();
		return bo.getofficenName(officeid,languageLocale);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public boolean UpdateDownloadStatus(NoEncumDTO  dto)
{
	
	try {
		NoEncumBO bo= new NoEncumBO();
		return bo.UpdateDownloadStatus(dto);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public boolean updateIssuance(NoEncumDTO dto, String roleId, String funId, String userId,String status) throws Exception {
	NoEncumBO bo= new NoEncumBO();
    boolean issuanceupdate = false;
    String[] param = new String[3];
    //param[0] = dto.getIssuanceRemark();
    //param[1] = dto.getCertifiedId();
    
    param[0] = dto.getUpdtdRemarks();       
    param[2] =dto.getTxnId();
    param[1]=dto.getUploaded_doc_path();
    String[] statusparam = new String[4];
 //   log.debug("STATUS IN BD="+dto.getAppStatus());

   
    statusparam[0] = "D";
    statusparam[1] = dto.getPostalTrakingNum();
    statusparam[2] = dto.getDisDate();
    statusparam[3] = dto.getTxnId();

    issuanceupdate = bo.updateIssuance(param,statusparam,roleId,funId,userId,dto);
    return issuanceupdate;
}
public String getCollectionOffice(String regNo,String languageLocale)
{
	String officeName="";
	NoEncumBO bo= new NoEncumBO();
	String officeId=bo.getCollectionOffice(regNo);
	officeName=getofficeName(officeId,languageLocale);
	return officeName;
}
public void PdfDetails(String propId,String language,Encumbrance encumbObjc, NoEncumForm ncForm)
{
	RegCommonForm regForm =new RegCommonForm();
	RegCommonBO commonBo = new RegCommonBO();
	NoEncumBO bo= new NoEncumBO();
	regForm.setHidnRegTxnId(bo.getRegTxn(ncForm.getNoEncumDTO().getRegNo()));
	String[] deedInstArray=null;
	try {
		deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
	//regForm.setCommonDeed(commonBo.getCommonDeedFlag(deedInstArray[0].trim()));   // setting common deed flag
	regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
	if(deedInstArray[2].trim().equals("-") || deedInstArray[2].trim().equals("")){
		regForm.setExmpID("");
		regForm.setHdnExAmount("");
		}else{
			regForm.setExmpID(deedInstArray[2].trim());
			regForm.setHdnExAmount(deedInstArray[2].trim());
		}
	
	if(deedInstArray[5]!=null){
	regForm.setDuty_txn_id(Integer.parseInt(deedInstArray[5].trim()));
	}else{
		regForm.setDuty_txn_id(0);
	}
	
	String flags[]=null;
	try {
		flags = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
		
		if(flags[2].trim().equalsIgnoreCase("Y"))
		{regForm.setCommonDeed(1);}
		else
		{regForm.setCommonDeed(0);}
		
		regForm.setPvReqFlag(flags[1].trim());
		regForm.setPropReqFlag(flags[0].trim());
		
		
	}else{
		regForm.setCommonDeed(0);
		regForm.setPvReqFlag("");
		regForm.setPropReqFlag("");
	}
	
	
	if(regForm.getPvReqFlag().equalsIgnoreCase("N"))
	{
	
	ArrayList list=	bo.getPdfDetails(propId,language);
	
	if(list !=null && list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
			ArrayList rowList=(ArrayList) list.get(i);
			if(rowList.get(0).toString().equalsIgnoreCase("2"))
			{
				if("en".equalsIgnoreCase(language))
				{	
				encumbObjc.setPropertyDetails(rowList.get(1).toString()+",Area-"+(String)rowList.get(8)+" sqm" );
				}
				else
				{
					encumbObjc.setPropertyDetails(rowList.get(1).toString()+",क्षेत्रफल-"+(String)rowList.get(8)+" वर्गमीटर" );
	
				}
			}
			else
			{
				if(rowList.get(0).toString().equalsIgnoreCase("3"))
				{
				
					if("en".equalsIgnoreCase(language))
					{	
					encumbObjc.setPropertyDetails(rowList.get(1).toString()+",Area-"+(String)rowList.get(2)+" hectare" );
					}
					else
					{
						encumbObjc.setPropertyDetails(rowList.get(1).toString()+",क्षेत्रफल-"+(String)rowList.get(2)+" हेक्टेयर" );	
					}
				}
				else
				{
					if("en".equalsIgnoreCase(language))
					{	
					encumbObjc.setPropertyDetails(rowList.get(1).toString()+",Area-"+(String)rowList.get(2)+" sqm" );
					}
					else
					{
						encumbObjc.setPropertyDetails(rowList.get(1).toString()+",क्षेत्रफल-"+(String)rowList.get(2)+" वर्गमीटर" );	
					}
				}
			}
			encumbObjc.setPropertyLocation(rowList.get(3).toString());
			
			ArrayList list1=bo.getAreaDetails(propId,language);
			
			if(list1!=null && list1.size()!=0)
			{
				for(int k=0;k<1;k++)
				{
					ArrayList rowList1=(ArrayList) list1.get(k);
					encumbObjc.setPropertyAreaEast(rowList1.get(2).toString());
					encumbObjc.setPropertyAreaWest(rowList1.get(3).toString());
					encumbObjc.setPropertyAreaNorth(rowList1.get(0).toString());
					encumbObjc.setPropertyAreaSouth(rowList1.get(1).toString());
				}
			}
			
			else
			{
				encumbObjc.setPropertyAreaEast("-");
				encumbObjc.setPropertyAreaWest("-");
				encumbObjc.setPropertyAreaNorth("-");
				encumbObjc.setPropertyAreaSouth("-");
			}
			
			
		}
	}
}
	else{
		
		ArrayList list=	bo.getPdfDetails(propId,language);
		if(list !=null && list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
				ArrayList rowList=(ArrayList) list.get(i);
				if(rowList.get(0).toString().equalsIgnoreCase("3"))
				{
				if("en".equalsIgnoreCase(language))
				{	
				encumbObjc.setPropertyDetails(rowList.get(1).toString()+", Area- "+bo.valArea((String)rowList.get(9), rowList.get(0).toString())+" hectare");
				}
				else
				{
				encumbObjc.setPropertyDetails(rowList.get(1).toString()+", क्षेत्रफल- "+bo.valArea((String)rowList.get(9), rowList.get(0).toString())+" हेक्टेयर");
	
				}
				}
				else
				{
					if("en".equalsIgnoreCase(language))
					{	
					encumbObjc.setPropertyDetails(rowList.get(1).toString()+", Area- "+bo.valArea((String)rowList.get(9), rowList.get(0).toString())+" sqm");
					}
					else
					{
					encumbObjc.setPropertyDetails(rowList.get(1).toString()+", क्षेत्रफल- "+bo.valArea((String)rowList.get(9), rowList.get(0).toString())+" वर्गमीटर");
		
					}
				}
				encumbObjc.setPropertyLocation(rowList.get(3).toString());
				
				ArrayList list1=	bo.getAreaDetails(propId,language);
				

				
				if(list1!=null && list1.size()!=0)
				{
					for(int k=0;k<1;k++)
					{
						ArrayList rowList1=(ArrayList) list1.get(k);
						encumbObjc.setPropertyAreaEast(rowList1.get(2).toString());
						encumbObjc.setPropertyAreaWest(rowList1.get(3).toString());
						encumbObjc.setPropertyAreaNorth(rowList1.get(0).toString());
						encumbObjc.setPropertyAreaSouth(rowList1.get(1).toString());
					}
				}
				
				else
				{
					encumbObjc.setPropertyAreaEast("-");
					encumbObjc.setPropertyAreaWest("-");
					encumbObjc.setPropertyAreaNorth("-");
					encumbObjc.setPropertyAreaSouth("-");
				}
			}
		}
	
		
		
		
	}

}
}