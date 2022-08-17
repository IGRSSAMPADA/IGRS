package com.wipro.igrs.regCompChecker.bd;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.Images;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.OtherSeal;
import com.newgen.burning.RegistrationCertificate;
import com.newgen.burning.RegistrationSeal;
import com.wipro.igrs.RegCompMaker.dao.RegCompMkrDAO;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.Seals.constant.SealsConstant;
import com.wipro.igrs.Seals.dao.SealsDAO;
import com.wipro.igrs.Seals.dto.SealsDTO;
import com.wipro.igrs.Seals.form.SealsForm;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.digitalSign.DigtalSignThread;
import com.wipro.igrs.digitalSign.DocumentService;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dao.RegCommonDAO;
import com.wipro.igrs.newreginit.dto.AadharDetailDTO;
import com.wipro.igrs.newreginit.dto.AadharRespDTO;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.dao.RegCompCheckerDAO;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;
import com.wipro.igrs.regCompChecker.form.RegCompCheckerForm;

public class RegCompCheckerBD {
	private Logger logger = (Logger) Logger.getLogger(RegCompCheckerBD.class);
	//RegCompCheckerDAO regCompDAO =  new RegCompCheckerDAO();
	RegCommonDAO commonDao = new RegCommonDAO();
	RegCommonBO commonBo = new RegCommonBO();
	RegCommonBD commonBd = new RegCommonBD();

	/**
	 * checkData for checking transaction completion at maker part
	 * 
	 * @param String
	 * @return boolean
	 * @author Simran
	 */
	public Boolean checkData(String regNumber) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.checkData(regNumber);
	}

	/**
	 * getDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return String array
	 * @author Simran
	 */
	public ArrayList getDutyDetls(String appId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getDutyDetls(appId);
		ArrayList finalList = new ArrayList();
		//String dutyList2 = null;
		//double stmp2 = 0;
		//double reg2 = 0;
		//String dutyList=list.toString();
		//dutyList=dutyList.substring(2, dutyList.length()-2);
		//String dutyListArr[]=dutyList.split(",");
		for (int i = 0; i < list.size(); i++) {
			ArrayList subList = (ArrayList) list.get(i);
			RegCompCheckerDTO rdto = new RegCompCheckerDTO();
			rdto.setStampduty1((String) subList.get(0));
			rdto.setPanchayatDuty((String) subList.get(1));
			rdto.setNagarPalikaDuty((String) subList.get(2));
			rdto.setUpkarDuty((String) subList.get(3));
			rdto.setRegistrationFee((String) subList.get(4));
			rdto.setTotalduty((String) subList.get(5));
			String stampDuty = (String) subList.get(6);
			stampDuty = stampDuty == null ? "" : stampDuty;
			if ((stampDuty).equals("")) {
				rdto.setStampdutyAlreadyPaid("0");
			} else {
				rdto.setStampdutyAlreadyPaid((String) subList.get(6));
			}
			String regFee = (String) subList.get(7);
			regFee = regFee == null ? "" : regFee;
			if ((regFee).equals("")) {
				rdto.setRegistrationFeeAlreadyPaid("0");
			} else {
				rdto.setRegistrationFeeAlreadyPaid((String) subList.get(7));
			}
			String exemStampDuty = (String) subList.get(8);
			exemStampDuty = exemStampDuty == null ? "" : exemStampDuty;
			String exemRegFee = (String) subList.get(9);
			exemRegFee = exemRegFee == null ? "" : exemRegFee;
			rdto.setExempReg(exemRegFee);
			rdto.setExempStamp(exemStampDuty);
			finalList.add(rdto);
		}
		/*ArrayList list2 = regCompDAO.getChangedDutyAtMaker(appId);
		if(list2.size()>0)
		{
			dutyList2 = list2.toString();
			dutyList2 = dutyList2.substring(2, dutyList2.length()-2);
			String tempArr[] = dutyList2.split(",");
			 stmp2 =  Double.parseDouble(tempArr[1].trim());
			 reg2 =  Double.parseDouble(tempArr[0].trim());
		}
		
		double stmp1 =  Double.parseDouble(dutyListArr[0].trim());
		double reg1 =  Double.parseDouble(dutyListArr[4].trim());
		
		double total1 = Double.parseDouble(dutyListArr[5].trim());
		Double stamp = stmp1+stmp2;
		Double regFee = reg1+reg2;
		Double total = total1+stmp2;
		dutyListArr[0] = stamp.toString();
		dutyListArr[4] = regFee.toString();
		dutyListArr[5] = total.toString();*/
		return finalList;
	}

	/**
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAdjudicatedDutyDetls(String appId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String dutyListArr[] = null;
		ArrayList list = regCompDAO.getAdjudicatedDutyDetls(appId);
		ArrayList finalList = new ArrayList();
		//if(list!=null && !(list.size()<1)){
		//String dutyList=list.toString();
		//dutyList=dutyList.substring(2, dutyList.length()-2);
		//dutyListArr=dutyList.split(",");
		//}
		for (int i = 0; i < list.size(); i++) {
			ArrayList subList = (ArrayList) list.get(i);
			RegCompCheckerDTO rdto = new RegCompCheckerDTO();
			rdto.setStampduty1((String) subList.get(0));
			rdto.setPanchayatDuty((String) subList.get(1));
			rdto.setNagarPalikaDuty((String) subList.get(2));
			rdto.setUpkarDuty((String) subList.get(3));
			rdto.setRegistrationFee((String) subList.get(4));
			rdto.setTotalduty((String) subList.get(5));
			String stampDuty = (String) subList.get(7);
			stampDuty = stampDuty == null ? "" : stampDuty;
			if ((stampDuty).equals("")) {
				rdto.setStampdutyAlreadyPaid("0");
			} else {
				rdto.setStampdutyAlreadyPaid((String) subList.get(7));
			}
			String regFee = (String) subList.get(8);
			regFee = regFee == null ? "" : regFee;
			if ((regFee).equals("")) {
				rdto.setRegistrationFeeAlreadyPaid("0");
			} else {
				rdto.setRegistrationFeeAlreadyPaid((String) subList.get(8));
			}
			String exemStampDuty = (String) subList.get(9);
			exemStampDuty = exemStampDuty == null ? "" : exemStampDuty;
			String exemRegFee = (String) subList.get(10);
			exemRegFee = exemRegFee == null ? "" : exemRegFee;
			rdto.setExempReg(exemRegFee);
			rdto.setExempStamp(exemStampDuty);
			//in case of adjudication already paid duties 
			finalList.add(rdto);
		}
		return finalList;
	}

	/**
	 * getDeedInstId for getting deed and instrument ids from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author Simran
	 */
	public String[] getDeedInstId(String appId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getDeedInstId(appId);
		String[] newArray = new String[5];
		if (list != null && list.size() > 0) {
			String str = list.toString();
			str = str.substring(2, str.length() - 2);
			String[] array = str.split(",");
			int size = array.length;
			newArray[0] = array[0]; //deed id
			newArray[1] = array[1]; //instrument id
			/*String var="";
			for(int i=2;i<array.length-1;i++){
			var=var+array[i]+"-";
			}*/
			newArray[2] = getExemptionIDList(appId); //exemptions
			newArray[3] = array[2]; //adjudication id
			newArray[4] = array[3]; //cancellation deed radio
			return newArray;
		} else {
			return null;
		}
	}

	/**
	 * getPropDetlsForDashboard for getting PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author Simran
	 */
	public HashMap getPropertyDetsForViewConfirm(String appId, String propId, String language) throws Exception {
		HashMap propMap = new HashMap();
		//valuation id
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String valuationId = regCompDAO.getPropValuationId(appId, propId);
		//details corresponding to valuation id
		ArrayList propList = new ArrayList();
		//propList=commonBd.getPropDetlsForDashboard(valuationId);
		//propList=commonBd.getPropDetlsForDashboard(propId);
		propList = getPropDetlsForDisplay(propId);
		//String propDetails=propList.toString().substring(2, (propList.toString().length()-2));
		//String propDetsArray[]=propDetails.split(",");
		RegCompCheckerDTO dto = new RegCompCheckerDTO();
		String propDetails = propList.toString();
		String propDetsArray[] = propDetails.split("&");
		dto.setPropertyTypeId(propDetsArray[13].trim());
		if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			dto.setPropertyTypeName(propDetsArray[14].trim());
			dto.setDistName(propDetsArray[1].trim());
			dto.setTehsilName(propDetsArray[3].trim());
			dto.setAreaTypeName(propDetsArray[5].trim());
			dto.setMunicipalBodyName(propDetsArray[12].trim());
			dto.setWardpatwarName(propDetsArray[7].trim());
			dto.setMohallaName(propDetsArray[10].trim());
		} else {
			dto.setPropertyTypeName(propDetsArray[43].trim());
			dto.setDistName(propDetsArray[37].trim());
			dto.setTehsilName(propDetsArray[38].trim());
			dto.setAreaTypeName(propDetsArray[39].trim());
			dto.setMunicipalBodyName(propDetsArray[42].trim());
			dto.setWardpatwarName(propDetsArray[40].trim());
			dto.setMohallaName(propDetsArray[41].trim());
		}
		if (propDetsArray[15].trim() != null && !propDetsArray[15].trim().equalsIgnoreCase("") && !propDetsArray[15].trim().equalsIgnoreCase("null")) {
			dto.setTotalSqMeter(Double.parseDouble(propDetsArray[15].trim()));
		} else {
			dto.setTotalSqMeter(0);
		}
		dto.setTotalSqMeterDisplay(getCurrencyFormat(dto.getTotalSqMeter()));
		if (propDetsArray[13].trim().equalsIgnoreCase("1") || //1 for plot
		propDetsArray[13].trim().equalsIgnoreCase("2"))//2 for building
		{
			//dto.setUnit("SQM");
			if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				dto.setUnit(RegInitConstant.SQM_ENGLISH);
			} else {
				dto.setUnit(RegInitConstant.SQM_HINDI);
			}
		} else {
			dto.setUnit(getUnitName(propDetsArray[16].trim(), language));
			/*String unit=propDetsArray[16].trim();
			if(unit.equalsIgnoreCase("2"))
			{
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setUnit(RegInitConstant.SQM_ENGLISH);
					}else{
						dto.setUnit(RegInitConstant.SQM_HINDI);
					}
			}
			else if(unit.equalsIgnoreCase("3"))
			{
				//dto.setUnit("HECTARE");
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setUnit(RegInitConstant.HECTARE_ENGLISH);
					}else{
						dto.setUnit(RegInitConstant.HECTARE_HINDI);
					}
			}*/
		}
		//dto.setUnit("");
		//dto.setMunicipalDutyName("");
		//dto.setMunicipalBodyName(propDetsArray[12].trim());
		String wardstatus = propDetsArray[8].trim();//////
		if (wardstatus.equalsIgnoreCase("1")) {
			//wardstatus="Ward";
			if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				wardstatus = RegInitConstant.WARD_ENGLISH;
			} else {
				wardstatus = RegInitConstant.WARD_HINDI;
			}
		} else {
			//wardstatus="Patwari/Halka";
			if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				wardstatus = RegInitConstant.PATWARI_ENGLISH;
			} else {
				wardstatus = RegInitConstant.PATWARI_HINDI;
			}
		}
		dto.setPatwariStatus(wardstatus);
		//no. of floors.
		if (propDetsArray[17].trim().equals("") || propDetsArray[17].trim().equals("null"))
			dto.setVikasId("-");
		else
			dto.setVikasId(propDetsArray[17].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		dto.setRicircle(propDetsArray[18].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		if (propDetsArray[19].trim().equals("") || propDetsArray[19].trim().equals("null"))
			dto.setLayoutdet("-");
		else
			dto.setLayoutdet(propDetsArray[19].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		if (propDetsArray[20].trim().equals("") || propDetsArray[20].trim().equals("null"))
			dto.setSheetnum("-");
		else
			dto.setSheetnum(propDetsArray[20].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		if (propDetsArray[21].trim().equals("") || propDetsArray[21].trim().equals("null"))
			dto.setPlotnum("-");
		else
			dto.setPlotnum(propDetsArray[21].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		dto.setPropAddress(propDetsArray[22].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		dto.setNorth(propDetsArray[23].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		dto.setSouth(propDetsArray[24].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		dto.setEast(propDetsArray[25].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		dto.setWest(propDetsArray[26].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		if (propDetsArray[27].trim().equals("") || propDetsArray[27].trim().equals("null"))
			dto.setPropLandmark("-");
		else
			dto.setPropLandmark(propDetsArray[27].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		if (!dto.getPropertyTypeName().equalsIgnoreCase("building")) {
			dto.setSelectedSubClauseList(getSubClauseListNonBuilding(valuationId));
		}
		//Below code for uploads
		dto.setPropImage1DocumentName("ANGLE 1.JPG");
		dto.setPropImage1FilePath(propDetsArray[28].trim());
		dto.setPropImage2DocumentName("ANGLE 2.JPG");
		dto.setPropImage2FilePath(propDetsArray[29].trim());
		dto.setPropImage3DocumentName("ANGLE 3.JPG");
		dto.setPropImage3FilePath(propDetsArray[30].trim());
		dto.setPropMapDocumentName("MAP.JPG");
		dto.setPropMapFilePath(propDetsArray[31].trim());
		if (dto.getPropertyTypeId().equalsIgnoreCase("3")) {
			dto.setPropRinDocumentName("RIN.JPG");
			dto.setPropRinFilePath(propDetsArray[32].trim());
			dto.setPropKhasraDocumentName("KHASRA.JPG");
			dto.setPropKhasraFilePath(propDetsArray[33].trim());
		}
		//other details corresponding to app id and prop id.
		//ArrayList otherPropList = new ArrayList();
		//otherPropList=commonBd.getOtherPropDetsForViewConfirm(appId,propId);
		//otherPropList=getPropKhasraDetlsForDisplay(propId);
		//String otherPropDetails=otherPropList.toString().substring(2, (otherPropList.toString().length()-2));
		//String otherPropDetsArray[]=otherPropDetails.split(",");
		//FOR GETTING PROPERTY KHASRA DETAILS
		String otherPropDetsArray[] = getPropKhasraDetlsForDisplay(propId);
		if (otherPropDetsArray != null) {
			String[] khasraNum = otherPropDetsArray[0].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] khasraArea = otherPropDetsArray[1].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] lagaan = otherPropDetsArray[2].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] rinPustika = otherPropDetsArray[3].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			int length = khasraNum.length;
			CommonDTO objDto;
			for (int i = 0; i < length; i++) {
				objDto = new CommonDTO();
				if (khasraNum[i].equalsIgnoreCase("null")) {
					objDto.setKhasraNum("-");
					objDto.setKhasraArea("-");
					objDto.setLagaan("-");
					objDto.setRinPustika("-");
				} else {
					objDto.setKhasraNum(khasraNum[i].trim());
					objDto.setKhasraArea(khasraArea[i].trim());
					objDto.setLagaan(lagaan[i].trim());
					objDto.setRinPustika(rinPustika[i].trim());
				}
				dto.getKhasraDetlsDisplay().add(objDto);
			}
		} else {
			CommonDTO objDto = new CommonDTO();
			objDto.setKhasraNum("-");
			objDto.setKhasraArea("-");
			objDto.setLagaan("-");
			objDto.setRinPustika("-");
			dto.getKhasraDetlsDisplay().add(objDto);
		}
		HashMap buildingMap = new HashMap();
		if (propDetsArray[14].trim().equalsIgnoreCase("Building")) {
			//following code for getting building floor details
			ArrayList buildingList = getGuildingFloorDetails(valuationId);
			if (buildingList.size() > 0) {
				dto.setFloorCount(buildingList.size());
				for (int j = 0; j < buildingList.size(); j++) {
					RegCompletDTO dto1 = new RegCompletDTO();
					ArrayList row_list = (ArrayList) buildingList.get(j);
					String str = row_list.toString();
					str = str.substring(1, str.length() - 1);
					String[] strArray = str.split(",");
					dto1.setUsageBuilding(getPropertyL1Name(strArray[1].trim(), language)); //l1
					dto1.setCeilingType(getPropertyL2Name(strArray[2].trim(), language)); //l2
					dto1.setTypeFloorDesc(commonDao.getFloorName(strArray[3].trim(), language)); //floor
					dto1.setConsiderAmt(Double.parseDouble(strArray[4]));
					dto1.setConsiderAmtDisplay(getCurrencyFormat(Double.parseDouble(strArray[4])));
					dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
					dto1.setTotalSqMeterDisplay(getCurrencyFormat(dto1.getTotalSqMeter()));
					dto1.setConstructedArea(strArray[6]);
					dto1.setSelectedSubClauseList(getSubClauseListBuilding(valuationId, strArray[0]));
					dto.getMapBuilding().put(strArray[0], dto1);
				}
			}
		} else
			dto.setFloorCount(0);
		propMap.put(propId, dto);
		return propMap;
	}

	/**
	 * @param propId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropDetlsForDisplay(String propId) throws Exception {
		return commonDao.getPropDetlsForDisplay(propId);
	}

	/**
	 * @param propId
	 * @return
	 * @throws Exception
	 */
	public String[] getPropKhasraDetlsForDisplay(String propId) throws Exception {
		ArrayList list = commonDao.getPropKhasraDetlsForDisplay(propId);
		ArrayList rowList;
		//ArrayList mainList=new ArrayList();
		String khasraNum = "";
		String khasraArea = "";
		String lagaan = "";
		String rinPustika = "";
		String[] finalArr = new String[4];
		String str;
		String[] strArr;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				rowList = (ArrayList) list.get(i);
				str = rowList.toString();
				str = str.substring(1, str.length() - 1);
				strArr = str.split(",");
				if (i == 0) {
					khasraNum = strArr[0] + RegInitConstant.SPLIT_CONSTANT_KHASRA;
					khasraArea = strArr[1] + RegInitConstant.SPLIT_CONSTANT_KHASRA;
					lagaan = strArr[2] + RegInitConstant.SPLIT_CONSTANT_KHASRA;
					rinPustika = strArr[3] + RegInitConstant.SPLIT_CONSTANT_KHASRA;
				} else {
					khasraNum = khasraNum + strArr[0] + RegInitConstant.SPLIT_CONSTANT_KHASRA;
					khasraArea = khasraArea + strArr[1] + RegInitConstant.SPLIT_CONSTANT_KHASRA;
					lagaan = lagaan + strArr[2] + RegInitConstant.SPLIT_CONSTANT_KHASRA;
					rinPustika = rinPustika + strArr[3] + RegInitConstant.SPLIT_CONSTANT_KHASRA;
				}
			}
			finalArr[0] = khasraNum;
			finalArr[1] = khasraArea;
			finalArr[2] = lagaan;
			finalArr[3] = rinPustika;
			return finalArr;
		} else {
			return null;
		}
	}

	/**
	 * @param valId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSubClauseListNonBuilding(String valId) throws Exception {
		ArrayList finalList = new ArrayList();
		ArrayList list = commonDao.getSubClauseListNonBuilding(valId);
		ArrayList row_list = new ArrayList();
		String str = "";
		String finalString = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				row_list = (ArrayList) list.get(i);
				str = row_list.toString();
				str = str.substring(1, str.length() - 1);
				finalString = finalString + "-" + str;
			}
			finalList = getSubClauseList(finalString);
		}
		return finalList;
	}

	/**
	 * @param valId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getGuildingFloorDetails(String valId) throws Exception {
		return commonDao.getGuildingFloorDetails(valId);
	}

	public ArrayList getSubClauseListBuilding(String valId, String floorId) throws Exception {
		ArrayList finalList = new ArrayList();
		String param[] = { valId, floorId };
		ArrayList list = commonDao.getSubClauseListBuilding(param);
		ArrayList row_list = new ArrayList();
		String str = "";
		String finalString = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				row_list = (ArrayList) list.get(i);
				str = row_list.toString();
				str = str.substring(1, str.length() - 1);
				finalString = finalString + "-" + str;
			}
			finalList = getSubClauseList(finalString);
		}
		return finalList;
	}

	/**
	 * @param subClause
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSubClauseList(String subClause) throws Exception {
		String[] subClauseArray = subClause.split("-");
		ArrayList subClauseList = new ArrayList();
		if (subClauseArray != null && subClauseArray.length > 0) {
			for (int i = 0; i < subClauseArray.length; i++) {
				RegCompCheckerDTO dto = new RegCompCheckerDTO();
				dto.setName(commonDao.getSubClauseName(subClauseArray[i].trim()));
				subClauseList.add(dto);
			}
		}
		return subClauseList;
	}

	/**
	 * getPartyDetsForViewConfirm for getting APPLICANT PARTY details from db.
	 * 
	 * @param String, String
	 * @return ArrayList
	 * @author Simran
	 */
	/*	public HashMap getPartyDetsForViewConfirm(String appId, String partyId,String language,int deedId, int instId, String propReqFlag) throws Exception {
	ArrayList list=commonBd.getPartyDetsForViewConfirm(appId,partyId);
			
			String listDetls=list.toString();
			listDetls=listDetls.substring(2, listDetls.length()-2);
			String listArr[]=listDetls.split(",");
			
			HashMap map=new HashMap();
			RegCompCheckerDTO mapDto =new RegCompCheckerDTO();
			if("3".equalsIgnoreCase(listArr[0].trim()))
			{
				mapDto.setGovtOfcCheck("Y");
			}
			else
			{
				mapDto.setGovtOfcCheck("N");
			}
			//mapDto.setGovtOfcCheck(listArr[59].trim());
			mapDto.setNameOfOfficial(listArr[60].trim());
			mapDto.setDesgOfOfficial(listArr[61].trim());
			mapDto.setAddressOfOfficial(listArr[62].trim());
	        mapDto.setListAdoptedPartyTrns(listArr[0].trim());
	        mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(listArr[0].trim(), language));
	        
	        mapDto.setApplicantOrTransParty("Applicant");
	        mapDto.setPartyRoleTypeId(listArr[29].trim());
	        mapDto.setPartyTxnId(listArr[24].trim());
	        
	       
	      	 if(listArr[27].trim().equals("") || listArr[27].trim().equals("null") || listArr[28].trim()==null)
	      		mapDto.setRelationWithOwnerTrns("-");
	      	 else
	   	    mapDto.setRelationWithOwnerTrns(listArr[27].trim());
	      	 if(listArr[28].trim().equals("") || listArr[28].trim().equals("null") || listArr[28].trim()==null){
	      		mapDto.setShareOfPropTrns("-");
	      		mapDto.setHdnDeclareShareCheck("N");
	      	 }
	      	 else{
	   	    mapDto.setShareOfPropTrns(listArr[28].trim());
	   	 mapDto.setHdnDeclareShareCheck("Y");
	      	 }
	        
	      	int roleId=Integer.parseInt(listArr[29].trim());
	      	
	      	String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
	 		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
	 		
	 		mapDto.setClaimantFlag(claimantFlag);
	        
	        if(listArr[0].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
	       	 //organization
	       	 mapDto.setOgrNameTrns(listArr[18].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	       	 mapDto.setAuthPerNameTrns(listArr[19].trim());
	       	 
	       	 mapDto.setFnameTrns("");
	      	 mapDto.setMnameTrns("");
	       	 mapDto.setLnameTrns("");
	       	// mapDto.setGendarTrns("");
	       	mapDto.setAuthPerGendarTrns(listArr[5].trim());
	      	 if(listArr[5].trim().equalsIgnoreCase("m")){
	      		// mapDto.setSelectedGender("Male");
	      		 
	      		if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	      			mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
	       		 
	       		 }else{
	       			mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
	       			 
	       		 }
	      		 
	      	 }
	      	 else{
	      		 //mapDto.setSelectedGender("Female");
	      		 
	      		if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	      			mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
	       		 
	       		 }else{
	       			mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
	       			 
	       		 }
	      		 
	      	 }
	      	 
	      	 
	       //	 mapDto.setSelectedGender("");
	       	 mapDto.setUserDOBTrns("");
	       	mapDto.setAgeTrns("");
	         mapDto.setAuthPerFatherNameTrns(listArr[20].trim());
	       	 mapDto.setMotherNameTrns("");
	      	 mapDto.setSpouseNameTrns("");
	         mapDto.setNationalityTrns("");
	       	 mapDto.setPostalCodeTrns("");
	      	 mapDto.setEmailIDTrns("");
	       	 mapDto.setSelectedPhotoId("");
	       	 mapDto.setSelectedPhotoIdName("");
	       	mapDto.setListIDTrns("");
	       	 mapDto.setIdnoTrns("");
	       	 mapDto.setSelectedCategoryName("");
	       	 mapDto.setIndCategoryTrns("");
	       	 mapDto.setIndDisabilityTrns("");
	       	 mapDto.setIndDisabilityDescTrns("");
	       	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
	       	 mapDto.setIndMinorityTrns("");
	       	 mapDto.setIndPovertyLineTrns("");
	       	 
	       	if(listArr[53].trim()!=null && !listArr[53].trim().equalsIgnoreCase("") && !listArr[53].trim().equalsIgnoreCase("null"))
	      	{
	      	mapDto.setRelationshipTrns(Integer.parseInt(listArr[53].trim()));
	      	mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(listArr[53].trim(),language));
	      	}else{

	          	mapDto.setRelationshipTrns(0);
	          	mapDto.setRelationshipNameTrns("");
	          	
	      	}
	       	
	       	
	       
	        mapDto.setAuthPerCountryTrns(listArr[55].trim());
	        mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName("1",language));
	        mapDto.setAuthPerStatenameTrns(listArr[56].trim());
	        mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName("1",language));
	        mapDto.setAuthPerDistrictTrns(listArr[57].trim());
	        mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(listArr[57].trim(),language));
	        
	    	mapDto.setAuthPerAddressTrns(listArr[58].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	       	 
	        }
	        if(listArr[0].trim().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
	       	 //individual
	       	 mapDto.setFnameTrns(listArr[2].trim());
	       	 if(listArr[3].trim().equals("") || listArr[3].trim().equals("null"))
	       		 mapDto.setMnameTrns("-");
	       	 else
	       	     mapDto.setMnameTrns(listArr[3].trim());
	       	 mapDto.setLnameTrns(listArr[4].trim());
	       	 mapDto.setGendarTrns(listArr[5].trim());
	       	 if(listArr[5].trim().equalsIgnoreCase("m")){
	       		// mapDto.setSelectedGender("Male");
	       		if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	      			mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
	       		 
	       		 }else{
	       			mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
	       			 
	       		 }
	       	 }
	       	 else{
	       		 //mapDto.setSelectedGender("Female");
	       		if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	      			mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
	       		 
	       		 }else{
	       			mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
	       			 
	       		 }
	       	 }
	       	 
	       	 
	       	 if(listArr[6].trim().equals("") || listArr[6].trim().equals("null")){
	       		 mapDto.setAgeTrns("-");
	       		 mapDto.setUserDayTrns("");
	       		 mapDto.setUserMonthTrns("");
	       		 mapDto.setUserYearTrns("");
	       		 
	       	 }
	       	 else{
	       	     //mapDto.setUserDOBTrns(convertDOB(listArr[6].trim())); 
	       	  mapDto.setAgeTrns(listArr[6].trim());
	       	     
	       	     String[] date=parseStringDOBfromDB(mapDto.getUserDOBTrns());
	       	     System.out.println("day-->"+date[0]+"\nmonth-->"+date[1]);
	       	     mapDto.setUserDayTrns(date[0]);
	    		 mapDto.setUserMonthTrns(date[1]);
	    		 mapDto.setUserYearTrns(date[2]);
	       	     
	       	     
	       	 }
	       	 
	       	 
	       	 
	       	 mapDto.setFatherNameTrns(listArr[20].trim());
	       	 if(listArr[21].trim().equals("") || listArr[21].trim().equals("null"))
	       		 mapDto.setMotherNameTrns("-");
	       	 else
	       	     mapDto.setMotherNameTrns(listArr[21].trim());
	       	 if(listArr[31].trim().equals("") || listArr[31].trim().equals("null"))
	       		 mapDto.setSpouseNameTrns("-");
	       	 else
	       	     mapDto.setSpouseNameTrns(listArr[31].trim());
	       	 mapDto.setNationalityTrns(listArr[7].trim());
	       	 
	       	 if(listArr[12].trim().equals("") || listArr[12].trim().equals("null"))
	       		 mapDto.setPostalCodeTrns("-");
	       	 else
	       	     mapDto.setPostalCodeTrns(listArr[12].trim());
	       	 
	       	 
	       	 
	       	 if(listArr[15].trim().equals("") || listArr[15].trim().equals("null"))
	       		 mapDto.setEmailIDTrns("-");
	       	 else
	       	     mapDto.setEmailIDTrns(listArr[15].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	       	
	       	 mapDto.setListIDTrns(listArr[16].trim());
	       	 mapDto.setSelectedPhotoIdName(commonBo.getPhotoIdProofName(listArr[16].trim(),language));               
	       	 if(listArr[17].trim().equals("") || listArr[17].trim().equals("null"))
	       		 mapDto.setIdnoTrns("-");
	       	 else
	       	     mapDto.setIdnoTrns(listArr[17].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	         mapDto.setOgrNameTrns("");
	       	 mapDto.setAuthPerNameTrns("");
	       	 
	          	 
	          	 
	          	 if(listArr[22].trim().equals("") || listArr[22].trim().equals("null"))
	          	mapDto.setIndCategoryTrns("-");
	          	 else
	       	    mapDto.setIndCategoryTrns(listArr[22].trim());
	          	 
	          	 
	          	 mapDto.setSelectedCategoryName(commonBo.getCategoryName(listArr[22].trim(),language));
	          	 if(listArr[25].trim().equals("") || listArr[25].trim().equals("null"))
	          	 mapDto.setIndDisabilityTrns("-");
	          	 else if(listArr[25].trim().equalsIgnoreCase("Y")){
	       	     mapDto.setIndDisabilityIdTrns("Y");
	       	  if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	      	    mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
	         		 }else{
	         			mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
	         		 }
	          	 }
	          	 else if(listArr[25].trim().equalsIgnoreCase("N")){
	          		mapDto.setIndDisabilityIdTrns("N");
	           	 
	           	if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	        	    mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
	           		 }else{
	           			mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
	           		 }
	           	 
	          	 }
	          	        
	          	if( listArr[25].trim().equalsIgnoreCase("N") &&(listArr[26].trim().equals("") || listArr[26].trim().equals("null")))
	          		mapDto.setIndDisabilityDescTrns("-");
	          	 else
	       	    mapDto.setIndDisabilityDescTrns(listArr[26].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	          	 
	          	 
	          	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
	          	
	          	mapDto.setSelectedOccupationName(commonBo.getOccupationName(listArr[33].trim(),language));
	          	mapDto.setOccupationIdTrns(listArr[33].trim());
	          //33 occupation id
	          	//34 occupation id
	          	//35 collector permission no.
	          	//36 collector certificate
	          	
	          	
	          	if(listArr[37].trim().equalsIgnoreCase("N")){
	          	//mapDto.setIndMinorityTrns("No");
	          	if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	        	    mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
	           		 }else{
	           			mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
	           		 }
	          	}
	          	else{
	          	//mapDto.setIndMinorityTrns("Yes");
	          	if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	        	    mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
	           		 }else{
	           			mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
	           		 }
	          	
	          	}	
	          	
	          	if(listArr[38].trim().equalsIgnoreCase("N")){
	           // mapDto.setIndPovertyLineTrns("No");
	            if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	        	    mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
	           		 }else{
	           			mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
	           		 }
	          	}
	            else{
	            //mapDto.setIndPovertyLineTrns("Yes");
	            if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	        	    mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
	           		 }else{
	           			mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
	           		 }
	            }	
	          	
	          	mapDto.setIndScheduleAreaTrns(listArr[39].trim());
	          	if(listArr[39].trim().equalsIgnoreCase("N")){
	          		//mapDto.setIndScheduleAreaTrnsDisplay("No");
	          		if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	            	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
	               		 }else{
	               			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
	               		 }
	          		if(listArr[35].trim().equalsIgnoreCase("") || listArr[35].trim().equals("null")){
	          			mapDto.setPermissionNoTrns("-");
	          		}else{
	          			mapDto.setPermissionNoTrns(listArr[35].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	          		}
	          		
	          		mapDto.setDocumentNameTrns("COLLECTOR'S CERTIFICATE");               //CERTIFICATE  40
	           		//mapDto.setDocumentSizeTrns(regForm.getDocumentSizeTrns());
	           		mapDto.setFilePathTrns(listArr[40].trim());
	           		if(listArr[40].trim()!=null && !listArr[40].trim().equalsIgnoreCase("") && !listArr[40].trim().equalsIgnoreCase("null")){
	               		mapDto.setDocContentsTrns(DMSUtility.getDocumentBytes(listArr[40].trim()));
	               		}else{
	                   		mapDto.setDocContentsTrns(new byte[0]);
	        	
	               		}
	           		mapDto.setUploadTypeTrns(listArr[40].trim().substring(listArr[40].trim().lastIndexOf(".")-2));
	           		
	           		
	          		
	          	}else{
	          		//mapDto.setIndScheduleAreaTrnsDisplay("Yes");
	          		
	          		if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	            	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
	               		 }else{
	               			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
	               		 }
	          		
	          	}
	          	//39 st schedule area
	          	
	          	if(listArr[53].trim()!=null && !listArr[53].trim().equalsIgnoreCase("") && !listArr[53].trim().equalsIgnoreCase("null"))
	          	{
	          	mapDto.setRelationshipTrns(Integer.parseInt(listArr[53].trim()));
	          	mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(listArr[53].trim(),language));
	          	}else{

	              	mapDto.setRelationshipTrns(0);
	              	mapDto.setRelationshipNameTrns("");
	              	
	          	}
	          	
	        }
	        
	        
	        //set in mapDto- anuj- 4 fields
	        
	        //uploads
	 
	        if(listArr[41]!=null && !listArr[41].trim().equalsIgnoreCase("") && !listArr[41].trim().equalsIgnoreCase("null")){
	      		 mapDto.setU2DocumentNameTrns("PHOTO PROOF");             //PHOTO PROOF    41
	       		mapDto.setU2FilePathTrns(listArr[41].trim());
	          		mapDto.setU2DocContentsTrns(DMSUtility.getDocumentBytes(listArr[41].trim()));
	          		mapDto.setU2UploadTypeTrns(listArr[41].trim().substring(listArr[41].trim().lastIndexOf(".")-2));
	          		}else{
	              		mapDto.setU2DocContentsTrns(new byte[0]);
	              		
	              		mapDto.setU2DocumentNameTrns("");             //PHOTO PROOF    41
	           		mapDto.setU2FilePathTrns("");
	              		//mapDto.setU2DocContentsTrns(DMSUtility.getDocumentBytes(listArr[41].trim()));
	              		mapDto.setU2UploadTypeTrns("");
	   	
	          		}

	   		     	
	   		if(deedId!=RegInitConstant.DEED_DEPOSIT_OF_TITLE && deedId!=RegInitConstant.DEED_LEASE_NPV &&
	   				deedId!=RegInitConstant.DEED_TRUST && deedId!=RegInitConstant.DEED_ADOPTION &&
	   				deedId!=RegInitConstant.DEED_CANCELLATION_OF_WILL_POA && deedId!=RegInitConstant.DEED_MORTGAGE_NPV &&
	   				deedId!=RegInitConstant.DEED_SETTLEMENT_NPV && deedId!=RegInitConstant.DEED_POA &&
	   				deedId!=RegInitConstant.DEED_WILL_NPV && deedId!=RegInitConstant.DEED_TRANSFER_LEASE_NPV &&
	   				deedId!=RegInitConstant.DEED_RECONV_MORTGAGE_NPV && deedId!=RegInitConstant.DEED_SURRENDER_LEASE_NPV &&
	   				deedId!=RegInitConstant.DEED_PARTNERSHIP_NPV && deedId!=RegInitConstant.DEED_PARTITION_NPV
	   				&& deedId!=RegInitConstant.DEED_AGREEMENT_MEMO_NPV && deedId!=RegInitConstant.DEED_COMPOSITION_NPV
	   				&& deedId!=RegInitConstant.DEED_LETTER_OF_LICENCE_NPV && deedId!=RegInitConstant.DEED_SECURITY_BOND_NPV
	   				&& deedId!=RegInitConstant.DEED_TRANSFER_NPV && deedId!=RegInitConstant.DEED_FURTHER_CHARGE_NPV)  //add deed id check
	   			
	   			
	   			
	   		if(propReqFlag.equalsIgnoreCase("Y"))	// 0 for deeds requiring property details
	   			
	   		{
	      //BELOW CODE FOR EXECUTANT
	   		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
			{
	   			if(listArr[42]!=null && !listArr[42].trim().equalsIgnoreCase("") && !listArr[42].trim().equalsIgnoreCase("null")){
	           		mapDto.setU3DocContentsTrns(DMSUtility.getDocumentBytes(listArr[42].trim()));
	           		mapDto.setU3DocumentNameTrns("NOTARIZED AFFIDAVIT OF EXECUTANT");         //NOT AFFD EXEC   42
	           		mapDto.setU3FilePathTrns(listArr[42].trim());
	           		mapDto.setU3UploadTypeTrns(listArr[42].trim().substring(listArr[42].trim().lastIndexOf(".")-2));
	           		}else{
	               		mapDto.setU3DocContentsTrns(new byte[0]);
	               		
	               		//mapDto.setU3DocContentsTrns(DMSUtility.getDocumentBytes(listArr[42].trim()));
	               		mapDto.setU3DocumentNameTrns("");         //NOT AFFD EXEC   42
	               		mapDto.setU3FilePathTrns("");
	               		mapDto.setU3UploadTypeTrns("");
	    	
	           		}
	       		
	       		mapDto.setU4DocumentNameTrns("EXECUTANT PHOTO");         //EXEC PHOTO       43
	       		mapDto.setU4FilePathTrns(listArr[43].trim());
	       		if(listArr[43].trim()!=null && !listArr[43].trim().equalsIgnoreCase("") && !listArr[43].trim().equalsIgnoreCase("null")){
	           		mapDto.setU4DocContentsTrns(DMSUtility.getDocumentBytes(listArr[43].trim()));
	           		}else{
	               		mapDto.setU4DocContentsTrns(new byte[0]);
	    	
	           		}
	       		mapDto.setU4UploadTypeTrns(listArr[43].trim().substring(listArr[43].trim().lastIndexOf(".")-2));
	       		
	if(listArr[0].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
	       			
	       			if(listArr[47]!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
	       			mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
	           		mapDto.setU10FilePathTrns(listArr[47].trim());
	           		if(listArr[47]!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
	               		mapDto.setU10DocContentsTrns(DMSUtility.getDocumentBytes(listArr[47].trim()));
	               		}else{
	                   		mapDto.setU10DocContentsTrns(new byte[0]);
	        	
	               		}
	           		mapDto.setU10UploadTypeTrns(listArr[47].trim().substring(listArr[47].trim().lastIndexOf(".")-2));
	       			}else{
	       				mapDto.setU10DocumentNameTrns("");
	       			}
	           		
	           		
	       		}else{
	       		if(!(RegInitConstant.PAN_CONSTANT).equalsIgnoreCase(mapDto.getListIDTrns())){
	       			
	       			if(listArr[47]!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
	       		mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
	       		mapDto.setU10FilePathTrns(listArr[47].trim());
	       		if(listArr[47]!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
	           		mapDto.setU10DocContentsTrns(DMSUtility.getDocumentBytes(listArr[47].trim()));
	           		}else{
	               		mapDto.setU10DocContentsTrns(new byte[0]);
	    	
	           		}
	       		mapDto.setU10UploadTypeTrns(listArr[47].trim().substring(listArr[47].trim().lastIndexOf(".")-2));
	       			}else{
	       				mapDto.setU10DocumentNameTrns("");
	       			}
	       		
	       		
	       		}
	       		}
	       	}
	   	//BELOW CODE FOR EXECUTANT POA HOLDER
			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
			{
				
	       		if(listArr[44]!=null && !listArr[44].trim().equalsIgnoreCase("") && !listArr[44].trim().equalsIgnoreCase("null")){
	           		mapDto.setU5DocContentsTrns(DMSUtility.getDocumentBytes(listArr[44].trim()));
	           		mapDto.setU5DocumentNameTrns("NOTARIZED AFFIDAVIT OF ATTORNEY"); 			//NOT AFFD ATTR		44
	           		mapDto.setU5FilePathTrns(listArr[44].trim());
	           		mapDto.setU5UploadTypeTrns(listArr[44].trim().substring(listArr[44].trim().lastIndexOf(".")-2));
	           		}else{
	               		mapDto.setU5DocContentsTrns(new byte[0]);               		
	               		//mapDto.setU5DocContentsTrns(DMSUtility.getDocumentBytes(listArr[44].trim()));
	               		mapDto.setU5DocumentNameTrns(""); 			//NOT AFFD ATTR		44
	               		mapDto.setU5FilePathTrns("");
	               		mapDto.setU5UploadTypeTrns("");
	    	
	           		}
	       		
	       		
	       		
	       		if(listArr[45]!=null && !listArr[45].trim().equalsIgnoreCase("") && !listArr[45].trim().equalsIgnoreCase("null")){
	           		mapDto.setU6DocContentsTrns(DMSUtility.getDocumentBytes(listArr[45].trim()));
	           		mapDto.setU6DocumentNameTrns("ATTORNEY PHOTO");			//ATTR PHOTO		45
	           		mapDto.setU6FilePathTrns(listArr[45].trim());
	           		mapDto.setU6UploadTypeTrns(listArr[45].trim().substring(listArr[45].trim().lastIndexOf(".")-2));
	           		}else{
	               		mapDto.setU6DocContentsTrns(new byte[0]);
	               		
	               		//mapDto.setU6DocContentsTrns(DMSUtility.getDocumentBytes(listArr[45].trim()));
	               		mapDto.setU6DocumentNameTrns("");			//ATTR PHOTO		45
	               		mapDto.setU6FilePathTrns("");
	               		mapDto.setU6UploadTypeTrns("");
	    	
	           		}
	       		
	       		
	       		mapDto.setU7DocumentNameTrns("EXECUTANT PHOTO");			//EXEC PHOTO		43
	       		mapDto.setU7FilePathTrns(listArr[43].trim());
	       		if(listArr[43].trim()!=null && !listArr[43].trim().equalsIgnoreCase("") && !listArr[43].trim().equalsIgnoreCase("null")){
	           		mapDto.setU7DocContentsTrns(DMSUtility.getDocumentBytes(listArr[43].trim()));
	           		}else{
	               		mapDto.setU7DocContentsTrns(new byte[0]);
	    	
	           		}
	       		mapDto.setU7UploadTypeTrns(listArr[43].trim().substring(listArr[43].trim().lastIndexOf(".")-2));
	       		
	       		mapDto.setSrOfficeNameTrns(listArr[48].trim());
	       		if(listArr[49]!=null && !listArr[49].trim().equalsIgnoreCase("") && !listArr[49].trim().equalsIgnoreCase("null")){
	       		mapDto.setPoaRegNoTrns(listArr[49].trim());
	       		}else{
	       			mapDto.setPoaRegNoTrns("-");
	       		}
	       		if(listArr[50]!=null && !listArr[50].trim().equalsIgnoreCase("") && !listArr[50].trim().equalsIgnoreCase("null")){
	       		mapDto.setDatePoaRegTrns(convertDOB(listArr[50].trim()));
	       		}else{
	       			mapDto.setDatePoaRegTrns("-");
	       		}
			}
	   		
			//BELOW CODE FOR CLAIMANT
			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
			{
				mapDto.setU8DocumentNameTrns("CLAIMANT PHOTO");			//CLAIMANT PHOTO	46
	       		mapDto.setU8FilePathTrns(listArr[46].trim());
	       		if(listArr[46].trim()!=null && !listArr[46].trim().equalsIgnoreCase("") && !listArr[46].trim().equalsIgnoreCase("null")){
	           		mapDto.setU8DocContentsTrns(DMSUtility.getDocumentBytes(listArr[46].trim()));
	           		}else{
	               		mapDto.setU8DocContentsTrns(new byte[0]);
	    	
	           		}
	       		mapDto.setU8UploadTypeTrns(listArr[46].trim().substring(listArr[46].trim().lastIndexOf(".")-2));
	       		
	       		if(listArr[0].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
	       			
	       			if(listArr[47]!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
	       			mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
	           		mapDto.setU11FilePathTrns(listArr[47].trim());
	           		if(listArr[47]!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
	               		mapDto.setU11DocContentsTrns(DMSUtility.getDocumentBytes(listArr[47].trim()));
	               		}else{
	                   		mapDto.setU11DocContentsTrns(new byte[0]);
	        	
	               		}
	           		mapDto.setU11UploadTypeTrns(listArr[47].trim().substring(listArr[47].trim().lastIndexOf(".")-2));
	       			}
	       			else
	       			{
	       				mapDto.setU11DocumentNameTrns("");
	       			}
	       			
	       			
	       			}else{
	       		if(!mapDto.getListIDTrns().equalsIgnoreCase(RegInitConstant.PAN_CONSTANT)){
	       			
	       			if(listArr[47]!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){	
	       		mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");		//PAN				47
	       		mapDto.setU11FilePathTrns(listArr[47].trim());
	       		if(listArr[47]!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
	           		mapDto.setU11DocContentsTrns(DMSUtility.getDocumentBytes(listArr[47].trim()));
	           		}else{
	               		mapDto.setU11DocContentsTrns(new byte[0]);
	    	
	           		}
	       		mapDto.setU11UploadTypeTrns(listArr[47].trim().substring(listArr[47].trim().lastIndexOf(".")-2));
	       			}
	       			else
	       			{
	       				mapDto.setU11DocumentNameTrns("");
	       			}
	       		}
	       		
	       		}
	       	}
	   		
			//BELOW CODE FOR CLAIMANT POA HOLDER
			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
			{
	   		
	   		
	   		if(listArr[45].trim()!=null && !listArr[45].trim().equalsIgnoreCase("") && !listArr[45].trim().equalsIgnoreCase("null")){
	       		mapDto.setU9DocContentsTrns(DMSUtility.getDocumentBytes(listArr[45].trim()));
	       		mapDto.setU9DocumentNameTrns("ATTORNEY PHOTO");				//ATTR PHOTO		45
	       		mapDto.setU9FilePathTrns(listArr[45].trim());
	       		mapDto.setU9UploadTypeTrns(listArr[45].trim().substring(listArr[45].trim().lastIndexOf(".")-2));
	       		}else{
	           		mapDto.setU9DocContentsTrns(new byte[0]);
	           		
	           		//mapDto.setU9DocContentsTrns(DMSUtility.getDocumentBytes(listArr[45].trim()));
	           		mapDto.setU9DocumentNameTrns("");				//ATTR PHOTO		45
	           		mapDto.setU9FilePathTrns("");
	           		mapDto.setU9UploadTypeTrns("");
	           		
		
	       		}
	   		
			}
	   		}
			//end of uploads
	   		
	   		
	   		//add additional uploads code Mohit
	   		ArrayList<CommonDTO> liDto = commonBd.getAdditonalUploads(appId,partyId);
	   		mapDto.setListDto(liDto);
	   		//
	        
	   		if(roleId!=0)
	   		{
	        if(listArr[30].trim().equals("") || listArr[30].trim().equals("null"))
	      		 mapDto.setRoleName("-");
	      	 else
	      	     mapDto.setRoleName(getRoleName(listArr[29].trim(),language, deedId, instId));
	   		}else{
	   			mapDto.setRoleName(listArr[52].trim());
	   		}
	        
	        mapDto.setOrgaddressTrns(listArr[11].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	      	mapDto.setSelectedCountryName(commonBo.getCountryName("1",language));
	        mapDto.setSelectedStateName(commonBo.getStateName("1",language));
	      	mapDto.setSelectedDistrictName(commonBo.getDistrictName(listArr[51].trim(),language));
	      	mapDto.setSelectedDistrict(listArr[51].trim());
	      	if(listArr[14].trim().equals("") || listArr[14].trim().equals("null"))
	      		 mapDto.setMobnoTrns("-"); 
	      	else
	      	     mapDto.setMobnoTrns(listArr[14].trim());
	      	if(listArr[13].trim().equals("") || listArr[13].trim().equals("null"))
	      		 mapDto.setPhnoTrns("-");
	      	else
	      	     mapDto.setPhnoTrns(listArr[13].trim());
	      	mapDto.setPartyTypeTrns(listArr[29].trim());
	      	
	      	
	      	int poaFlag=0;
	      
	      	if(listArr[54].trim()!=null && !listArr[54].trim().equalsIgnoreCase("") && !listArr[54].trim().equalsIgnoreCase("null")){
	      poaFlag=Integer.parseInt(listArr[54].trim());
	      	}
	      	
	      	
	        if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ||
	        		poaFlag==RegInitConstant.CLAIMANT_FLAG_2 ||
	        		poaFlag==RegInitConstant.CLAIMANT_FLAG_4
	        		)
	        {
	        	
	        	mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
	        	String ownerId=listArr[32].trim();
	        	
	        	mapDto.setOwnerIdTrns(ownerId);
	        	
	        	ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
	        	String ownerDetls=ownerList.toString();
	        	ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
	        	String ownerDetlsArr[]=ownerDetls.split(",");
	        	
	       	 if(ownerDetlsArr[9].trim().equals("") || ownerDetlsArr[9].trim().equals("null"))
	       	 {
	         mapDto.setOwnerOgrNameTrns("-");
	         mapDto.setOwnerNameTrns(ownerDetlsArr[0].trim());
	         }
	         else
	         {
	         mapDto.setOwnerOgrNameTrns(ownerDetlsArr[9].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	         mapDto.setOwnerNameTrns(ownerDetlsArr[10].trim());
	       	}
	       	 if(ownerDetlsArr[1].trim().equalsIgnoreCase("f")){
	       	 //mapDto.setOwnerGendarTrns("Female");
	       	if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	  			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
	   		 
	   		 }else{
	   			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
	   			 
	   		 }
	       	 }
	       	 else{
	            //mapDto.setOwnerGendarTrns("Male");
	        	if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	      			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
	       		 
	       		 }else{
	       			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
	       			 
	       		 }
	       	 }
	       	 mapDto.setOwnerNationalityTrns(ownerDetlsArr[3].trim());
	       	 mapDto.setOwnerAddressTrns(ownerDetlsArr[4].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	       	 mapDto.setOwnerPhnoTrns(ownerDetlsArr[5].trim());
	       	 if(ownerDetlsArr[6].trim().equals("") || ownerDetlsArr[6].trim().equals("null"))
	              		mapDto.setOwnerEmailIDTrns("-");
	              	 else
	           	    mapDto.setOwnerEmailIDTrns(ownerDetlsArr[6].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	       	
	       	 mapDto.setOwnerIdnoTrns(ownerDetlsArr[8].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
	       	 mapDto.setOwnerAgeTrns(ownerDetlsArr[2].trim());
	       	 //mapDto.setOwnerDOBTrns(convertDOBfromDb(ownerDetlsArr[2].trim()));   
	       	 mapDto.setOwnerListIDTrns(ownerDetlsArr[7].trim());
	       	 mapDto.setOwnerProofNameTrns(commonBd.getPhotoIdProofName(ownerDetlsArr[7].trim(),language));
	        }
	        else{
	        	mapDto.setPoaHolderFlag(0);
	        }
	        
	      	map.put(listArr[24].trim(), mapDto);
	      	
	      	
			return map;
		}*/
	public HashMap getPartyDetsForViewConfirm(String appId, String partyId, String languageLocale, int deedId, int inst, String propReqFlag) throws Exception {
		ArrayList list = commonBd.getPartyDetsForViewConfirm(appId, partyId);
		RegCommonBO bo = new RegCommonBO();
		list = (ArrayList) list.get(0);
		HashMap map = new HashMap();
		RegCommonDTO mapDto = new RegCommonDTO();
		try {
			return bo.getPartyDetsForViewConfirm(appId, partyId, deedId, propReqFlag, languageLocale, inst);
			/*
			
			String listDetls=list.toString();
			listDetls=listDetls.substring(2, listDetls.length()-2);
			String listArr[]=listDetls.split(",");
			
			
			mapDto.setListAdoptedPartyTrns(list.get(0)!=null?list.get(0).toString():"");
			mapDto.setListAdoptedPartyNameTrns(bo.getAppleteName(list.get(0)!=null?list.get(0).toString():"", languageLocale));
			
			mapDto.setApplicantOrTransParty("Applicant");
			mapDto.setPartyRoleTypeId(list.get(29)!=null?list.get(29).toString():"");
			mapDto.setPartyTxnId(list.get(24)!=null?list.get(24).toString():"");
			
			String relation=list.get(27)!=null?list.get(27).toString():"";
			 if(relation.equals("") || relation.equals("null") || relation==null)
				mapDto.setRelationWithOwnerTrns("-");
			 else
			   mapDto.setRelationWithOwnerTrns(relation);
			 if(listArr[28].trim().equals("") || listArr[28].trim().equals("null") || listArr[28].trim()==null){
				mapDto.setShareOfPropTrns("-");
				mapDto.setHdnDeclareShareCheck("N");
			 }
			 else{
			   mapDto.setShareOfPropTrns(listArr[28].trim());
			mapDto.setHdnDeclareShareCheck("Y");
			 }
			
			int roleId=Integer.parseInt(list.get(29)!=null?list.get(29).toString():"");
			
			String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
			int claimantFlag=Integer.parseInt(claimantArr[0].trim());
			
			mapDto.setClaimantFlag(claimantFlag);
			//regForm.setClaimantFlag(claimantFlag);
			if(list.get(0).toString().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)){
				
				//mapDto.setGovtOfcCheck(listArr[59].trim());
				if(list.get(60)!=null && !("").equalsIgnoreCase(list.get(60).toString()) && !("null").equalsIgnoreCase(list.get(60).toString())){
				mapDto.setNameOfOfficial(list.get(60).toString());
				}else{
					mapDto.setNameOfOfficial("-");
				}
				mapDto.setDesgOfOfficial(list.get(61)!=null?list.get(61).toString():"");
				mapDto.setAddressOfOfficial(list.get(62)!=null?list.get(62).toString():"");
				
				mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);
				
				mapDto.setAddressGovtOffclOutMpTrns(list.get(63)!=null && !("").equalsIgnoreCase(list.get(63).toString())?list.get(63).toString():"-");
				
			}else
			if(list.get(0).toString().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
			 //organization
			 mapDto.setOgrNameTrns(list.get(18)!=null?list.get(18).toString():"");
			 mapDto.setAuthPerNameTrns(list.get(19)!=null?list.get(19).toString():"");
			 
			 mapDto.setFnameTrns("");
			 mapDto.setMnameTrns("");
			 mapDto.setLnameTrns("");
			// mapDto.setGendarTrns("");
			mapDto.setAuthPerGendarTrns(list.get(5)!=null?list.get(5).toString():"");
			String gender=list.get(5)!=null?list.get(5).toString():"";
			 if(gender.equalsIgnoreCase("m")){
				// mapDto.setSelectedGender("Male");
				 
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
				 
				 }else{
					mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
					 
				 }
				 
			 }
			 else{
				 //mapDto.setSelectedGender("Female");
				 
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
				 
				 }else{
					mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
					 
				 }
				 
			 }
			 
			 
			//	 mapDto.setSelectedGender("");
			 mapDto.setUserDOBTrns("");
			mapDto.setAgeTrns("");
			 mapDto.setAuthPerFatherNameTrns(list.get(20)!=null?list.get(20).toString():"");
			 mapDto.setMotherNameTrns("");
			 mapDto.setSpouseNameTrns("");
			 mapDto.setNationalityTrns("");
			 mapDto.setPostalCodeTrns("");
			 mapDto.setEmailIDTrns("");
			 mapDto.setSelectedPhotoId("");
			 mapDto.setSelectedPhotoIdName("");
			mapDto.setListIDTrns("");
			 mapDto.setIdnoTrns("");
			 mapDto.setSelectedCategoryName("");
			 mapDto.setIndCategoryTrns("");
			 mapDto.setIndDisabilityTrns("");
			 mapDto.setIndDisabilityDescTrns("");
			 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
			 mapDto.setIndMinorityTrns("");
			 mapDto.setIndPovertyLineTrns("");
			 
			 relation=list.get(53)!=null?list.get(53).toString():"";
			if(relation!=null && !relation.equalsIgnoreCase("") && !relation.equalsIgnoreCase("null"))
			{
			mapDto.setRelationshipTrns(Integer.parseInt(relation));
			mapDto.setRelationshipNameTrns(getRelationshipName(relation,languageLocale));
			}else{

			  	mapDto.setRelationshipTrns(0);
			  	mapDto.setRelationshipNameTrns("");
			  	
			}
			
			
			
			mapDto.setAuthPerCountryTrns(list.get(55)!=null?list.get(55).toString():"");
			mapDto.setAuthPerCountryNameTrns(bo.getCountryName("1",languageLocale));
			mapDto.setAuthPerStatenameTrns(list.get(56)!=null?list.get(56).toString():"");
			mapDto.setAuthPerStatenameNameTrns(bo.getStateName("1",languageLocale));
			mapDto.setAuthPerDistrictTrns(list.get(57)!=null?list.get(57).toString():"");
			mapDto.setAuthPerDistrictNameTrns(getDistrictName(list.get(57)!=null?list.get(57).toString():"",languageLocale));
			
			mapDto.setAuthPerAddressTrns(list.get(58)!=null?list.get(58).toString():"");
			
			mapDto.setAddressOrgOutMpTrns(list.get(63)!=null && !("").equalsIgnoreCase(list.get(63).toString())?list.get(63).toString():"-");
			mapDto.setAddressAuthPerOutMpTrns(list.get(64)!=null && !("").equalsIgnoreCase(list.get(64).toString())?list.get(64).toString():"-");
			 
			}else
			if(list.get(0).toString().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
			 //individual
			 mapDto.setFnameTrns(list.get(2)!=null?list.get(2).toString():"");
			 String mName=list.get(3)!=null?list.get(3).toString():"";
			 if(mName.equals("") || mName.equals("null"))
				 mapDto.setMnameTrns("-");
			 else
			     mapDto.setMnameTrns(mName);
			 mapDto.setLnameTrns(list.get(4)!=null?list.get(4).toString():"");
			 mapDto.setGendarTrns(list.get(5)!=null?list.get(5).toString():"");
			String gender=list.get(5)!=null?list.get(5).toString():"";
			 if(gender.equalsIgnoreCase("m")){
				// mapDto.setSelectedGender("Male");
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
				 
				 }else{
					mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
					 
				 }
			 }
			 else{
				 //mapDto.setSelectedGender("Female");
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
				 
				 }else{
					mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
					 
				 }
			 }
			 
			 String age=list.get(6)!=null?list.get(6).toString():"";
			 if(age.equals("") || age.equals("null")){
				 mapDto.setAgeTrns("-");
				 mapDto.setUserDayTrns("");
				 mapDto.setUserMonthTrns("");
				 mapDto.setUserYearTrns("");
				 
			 }
			 else{
			     //mapDto.setUserDOBTrns(convertDOB(listArr[6].trim())); 
			  mapDto.setAgeTrns(age);
			     
			     String[] date=parseStringDOBfromDB(mapDto.getUserDOBTrns());
			     System.out.println("day-->"+date[0]+"\nmonth-->"+date[1]);
			     mapDto.setUserDayTrns(date[0]);
				 mapDto.setUserMonthTrns(date[1]);
				 mapDto.setUserYearTrns(date[2]);
			     
			     
			 }
			 
			 
			 
			 mapDto.setFatherNameTrns(list.get(20)!=null?list.get(20).toString():"");
			 String motherName=list.get(21)!=null?list.get(21).toString():"";
			 if(motherName.equals("") || motherName.equals("null"))
				 mapDto.setMotherNameTrns("-");
			 else
			     mapDto.setMotherNameTrns(motherName);
			 
			String spouseName=list.get(31)!=null?list.get(31).toString():"";
			 if(spouseName.equals("") || spouseName.equals("null"))
				 mapDto.setSpouseNameTrns("-");
			 else
			     mapDto.setSpouseNameTrns(spouseName);
			 mapDto.setNationalityTrns(list.get(7)!=null?list.get(7).toString():"");
			 
			 
			 if(listArr[12].trim().equals("") || listArr[12].trim().equals("null"))
				 mapDto.setPostalCodeTrns("-");
			 else
			     mapDto.setPostalCodeTrns((list.get(12)!=null && !list.get(12).toString().equalsIgnoreCase("") && !list.get(12).toString().equalsIgnoreCase("null"))?list.get(12).toString():"-");
			 
			 
			 
			 if(listArr[15].trim().equals("") || listArr[15].trim().equals("null"))
				 mapDto.setEmailIDTrns("-");
			 else
			     mapDto.setEmailIDTrns((list.get(15)!=null && !list.get(15).toString().equalsIgnoreCase("") && !list.get(15).toString().equalsIgnoreCase("null"))?list.get(15).toString():"-");
			
			 mapDto.setListIDTrns(list.get(16)!=null?list.get(16).toString():"");
			 mapDto.setSelectedPhotoIdName(bo.getPhotoIdProofName(list.get(16)!=null?list.get(16).toString():"",languageLocale));               
			 if(listArr[17].trim().equals("") || listArr[17].trim().equals("null"))
				 mapDto.setIdnoTrns("-");
			 else
			     mapDto.setIdnoTrns((list.get(17)!=null && !list.get(17).toString().equalsIgnoreCase("") && !list.get(17).toString().equalsIgnoreCase("null"))?list.get(17).toString():"-");
			 mapDto.setOgrNameTrns("");
			 mapDto.setAuthPerNameTrns("");
			 
			  	 
			  	 
			  	 if(listArr[22].trim().equals("") || listArr[22].trim().equals("null"))
			  	mapDto.setIndCategoryTrns("-");
			  	 else
			    mapDto.setIndCategoryTrns((list.get(22)!=null && !list.get(22).toString().equalsIgnoreCase("") && !list.get(22).toString().equalsIgnoreCase("null"))?list.get(22).toString():"-");
			  	 
			  	 
			  	 mapDto.setSelectedCategoryName(getCategoryName(list.get(22).toString(),languageLocale));
			  	 String disability=list.get(25)!=null?list.get(25).toString():"";
			  	 if(disability.equals("") || disability.equals("null"))
			  	 mapDto.setIndDisabilityTrns("-");
			  	 else if(disability.equalsIgnoreCase("Y")){
			     mapDto.setIndDisabilityIdTrns("Y");
			  if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			    mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
			 		 }else{
			 			mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
			 		 }
			  	 }
			  	 else if(disability.equalsIgnoreCase("N")){
			  		mapDto.setIndDisabilityIdTrns("N");
			   	 
			   	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				    mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
			   		 }else{
			   			mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
			   		 }
			   	 
			  	 }
			  	      
			  	 String disabilityDesc=list.get(26)!=null?list.get(26).toString():"";
			  	if( disability.equalsIgnoreCase("N") &&(disabilityDesc.equals("") || disabilityDesc.equals("null")))
			  		mapDto.setIndDisabilityDescTrns("-");
			  	 else
			    mapDto.setIndDisabilityDescTrns(disabilityDesc.replace(RegInitConstant.SPLIT_CONSTANT,","));
			  	 
			  	 
			  	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
			  	
			  	mapDto.setSelectedOccupationName(getOccupationName(list.get(33)!=null?list.get(33).toString():"",languageLocale));
			  	mapDto.setOccupationIdTrns(list.get(33)!=null?list.get(33).toString():"");
			  //33 occupation id
			  	//34 occupation id
			  	//35 collector permission no.
			  	//36 collector certificate
			  	
			  	String var=list.get(37)!=null?list.get(37).toString():"";
			  	if(var.equalsIgnoreCase("N")){
			  	//mapDto.setIndMinorityTrns("No");
			  	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				    mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
			   		 }else{
			   			mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
			   		 }
			  	}
			  	else{
			  	//mapDto.setIndMinorityTrns("Yes");
			  	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				    mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
			   		 }else{
			   			mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
			   		 }
			  	
			  	}	
			  	var=list.get(38)!=null?list.get(38).toString():"";
			  	if(var.equalsIgnoreCase("N")){
			   // mapDto.setIndPovertyLineTrns("No");
			    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				    mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
			   		 }else{
			   			mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
			   		 }
			  	}
			    else{
			    //mapDto.setIndPovertyLineTrns("Yes");
			    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				    mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
			   		 }else{
			   			mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
			   		 }
			    }	
			  	
			  	mapDto.setIndScheduleAreaTrns(list.get(39)!=null?list.get(39).toString():"");
			  	var=list.get(39)!=null?list.get(39).toString():"";
			  	if(var.equalsIgnoreCase("N")){
			  		//mapDto.setIndScheduleAreaTrnsDisplay("No");
			  		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			    	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
			       		 }else{
			       			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
			       		 }
			  		if(listArr[35].trim().equalsIgnoreCase("") || listArr[35].trim().equals("null")){
			  			mapDto.setPermissionNoTrns("-");
			  		}else{
			  			mapDto.setPermissionNoTrns((list.get(35)!=null && !list.get(35).toString().equalsIgnoreCase("") && !list.get(35).toString().equalsIgnoreCase("null"))?list.get(35).toString():"-");
			  		//}
			  		
			  		mapDto.setDocumentNameTrns("COLLECTOR'S CERTIFICATE");               //CERTIFICATE  40
			   		//mapDto.setDocumentSizeTrns(regForm.getDocumentSizeTrns());
			  		var=(list.get(40)!=null && !list.get(40).toString().equalsIgnoreCase("") && !list.get(40).toString().equalsIgnoreCase("null"))?list.get(40).toString():"";
			   		mapDto.setFilePathTrns(var);
			   		if(var!=null && !var.equalsIgnoreCase("") && !var.equalsIgnoreCase("null")){
			       		mapDto.setDocContentsTrns(DMSUtility.getDocumentBytes(var));
			       		}else{
			           		mapDto.setDocContentsTrns(new byte[0]);
				
			       		}
			   		mapDto.setUploadTypeTrns(var.substring(var.lastIndexOf(".")-2));
			   		
			   		
			  		
			  	}else{
			  		//mapDto.setIndScheduleAreaTrnsDisplay("Yes");
			  		
			  		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			    	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
			       		 }else{
			       			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
			       		 }
			  		
			  	}
			  	//39 st schedule area
			  	var=(list.get(53)!=null && !list.get(53).toString().equalsIgnoreCase("") && !list.get(53).toString().equalsIgnoreCase("null"))?list.get(53).toString():"";
			  	if(var!=null && !var.equalsIgnoreCase("") && !var.equalsIgnoreCase("null"))
			  	{
			  	mapDto.setRelationshipTrns(Integer.parseInt(var));
			  	mapDto.setRelationshipNameTrns(getRelationshipName(var,languageLocale));
			  	}else{

			      	mapDto.setRelationshipTrns(0);
			      	mapDto.setRelationshipNameTrns("");
			      	
			  	}
			  	mapDto.setAddressIndOutMpTrns(list.get(63)!=null && !("").equalsIgnoreCase(list.get(63).toString())?list.get(63).toString():"-");
			}
			
			
			//set in mapDto- anuj- 4 fields
			
			//uploads
			
			String varUploads=(list.get(41)!=null && !list.get(41).toString().equalsIgnoreCase("") && !list.get(41).toString().equalsIgnoreCase("null"))?list.get(41).toString():"";
			if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
			 mapDto.setU2DocumentNameTrns("PHOTO PROOF");             //PHOTO PROOF    41
				mapDto.setU2FilePathTrns(varUploads);
				mapDto.setU2DocContentsTrns(DMSUtility.getDocumentBytes(varUploads));
				mapDto.setU2UploadTypeTrns(varUploads.substring(varUploads.lastIndexOf(".")-2));
				}else{
			   		mapDto.setU2DocContentsTrns(new byte[0]);
			   		
			   		mapDto.setU2DocumentNameTrns("");             //PHOTO PROOF    41
					mapDto.setU2FilePathTrns("");
			   		//mapDto.setU2DocContentsTrns(DMSUtility.getDocumentBytes(listArr[41].trim()));
			   		mapDto.setU2UploadTypeTrns("");
			
				}
			

			     	
			if(deedId!=RegInitConstant.DEED_DEPOSIT_OF_TITLE && deedId!=RegInitConstant.DEED_LEASE_NPV &&
					deedId!=RegInitConstant.DEED_TRUST && deedId!=RegInitConstant.DEED_ADOPTION &&
					deedId!=RegInitConstant.DEED_CANCELLATION_OF_WILL_POA && deedId!=RegInitConstant.DEED_MORTGAGE_NPV &&
					deedId!=RegInitConstant.DEED_SETTLEMENT_NPV && deedId!=RegInitConstant.DEED_POA &&
					deedId!=RegInitConstant.DEED_WILL_NPV && deedId!=RegInitConstant.DEED_TRANSFER_LEASE_NPV &&
					deedId!=RegInitConstant.DEED_RECONV_MORTGAGE_NPV && deedId!=RegInitConstant.DEED_SURRENDER_LEASE_NPV &&
					deedId!=RegInitConstant.DEED_PARTNERSHIP_NPV && deedId!=RegInitConstant.DEED_PARTITION_NPV
					&& deedId!=RegInitConstant.DEED_AGREEMENT_MEMO_NPV && deedId!=RegInitConstant.DEED_COMPOSITION_NPV
					&& deedId!=RegInitConstant.DEED_LETTER_OF_LICENCE_NPV && deedId!=RegInitConstant.DEED_SECURITY_BOND_NPV
					&& deedId!=RegInitConstant.DEED_TRANSFER_NPV && deedId!=RegInitConstant.DEED_FURTHER_CHARGE_NPV)  //add deed id check
				
				
				
			if(propReqFlag.equalsIgnoreCase("Y"))	// 0 for deeds requiring property details
				
			{
			//BELOW CODE FOR EXECUTANT
			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
			{
				varUploads=(list.get(42)!=null && !list.get(42).toString().equalsIgnoreCase("") && !list.get(42).toString().equalsIgnoreCase("null"))?list.get(42).toString():"";
				if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
			   		mapDto.setU3DocContentsTrns(DMSUtility.getDocumentBytes(varUploads));
			   		mapDto.setU3DocumentNameTrns("NOTARIZED AFFIDAVIT OF EXECUTANT");         //NOT AFFD EXEC   42
			   		mapDto.setU3FilePathTrns(varUploads);
			   		mapDto.setU3UploadTypeTrns(varUploads.substring(varUploads.lastIndexOf(".")-2));
			   		}else{
			       		mapDto.setU3DocContentsTrns(new byte[0]);
			       		
			       		//mapDto.setU3DocContentsTrns(DMSUtility.getDocumentBytes(listArr[42].trim()));
			       		mapDto.setU3DocumentNameTrns("");         //NOT AFFD EXEC   42
			       		mapDto.setU3FilePathTrns("");
			       		mapDto.setU3UploadTypeTrns("");
			
			   		}
				
				
				mapDto.setU4DocumentNameTrns("EXECUTANT PHOTO");         //EXEC PHOTO       43
				mapDto.setU4FilePathTrns(listArr[43].trim());
				if(listArr[43].trim()!=null && !listArr[43].trim().equalsIgnoreCase("") && !listArr[43].trim().equalsIgnoreCase("null")){
			   		mapDto.setU4DocContentsTrns(DMSUtility.getDocumentBytes(listArr[43].trim()));
			   		}else{
			       		mapDto.setU4DocContentsTrns(new byte[0]);
			
			   		}
				mapDto.setU4UploadTypeTrns(listArr[43].trim().substring(listArr[43].trim().lastIndexOf(".")-2));
				
				//if(listArr[47]==null)
				//{
				//	listArr[47]="";
				//}
				
				if(list.get(0).toString().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
					
					varUploads=(list.get(47)!=null && !list.get(47).toString().equalsIgnoreCase("") && !list.get(47).toString().equalsIgnoreCase("null"))?list.get(47).toString():"";
					if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
					mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
			   		mapDto.setU10FilePathTrns(varUploads);
			   		if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
			       		mapDto.setU10DocContentsTrns(DMSUtility.getDocumentBytes(varUploads));
			       		}else{
			           		mapDto.setU10DocContentsTrns(new byte[0]);
				
			       		}
			   		mapDto.setU10UploadTypeTrns(varUploads.substring(varUploads.lastIndexOf(".")-2));
					}else{
						mapDto.setU10DocumentNameTrns("");
					}
			   		
			   		
				}else{
				if(mapDto.getListIDTrns()!=null && !mapDto.getListIDTrns().equalsIgnoreCase(RegInitConstant.PAN_CONSTANT)){
					varUploads=(list.get(47)!=null && !list.get(47).toString().equalsIgnoreCase("") && !list.get(47).toString().equalsIgnoreCase("null"))?list.get(47).toString():"";
					if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
				mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
				mapDto.setU10FilePathTrns(varUploads);
				if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
			   		mapDto.setU10DocContentsTrns(DMSUtility.getDocumentBytes(varUploads));
			   		}else{
			       		mapDto.setU10DocContentsTrns(new byte[0]);
			
			   		}
				mapDto.setU10UploadTypeTrns(varUploads.substring(varUploads.lastIndexOf(".")-2));
					}else{
						mapDto.setU10DocumentNameTrns("");
					}
				
				
				}
				}
			}
			//BELOW CODE FOR EXECUTANT POA HOLDER
			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
			{
				
				if(listArr[44]!=null && !listArr[44].trim().equalsIgnoreCase("") && !listArr[44].trim().equalsIgnoreCase("null")){
			   		mapDto.setU5DocContentsTrns(DMSUtility.getDocumentBytes(listArr[44].trim()));
			   		mapDto.setU5DocumentNameTrns("NOTARIZED AFFIDAVIT OF ATTORNEY"); 			//NOT AFFD ATTR		44
			   		mapDto.setU5FilePathTrns(listArr[44].trim());
			   		mapDto.setU5UploadTypeTrns(listArr[44].trim().substring(listArr[44].trim().lastIndexOf(".")-2));
			   		}else{
			       		mapDto.setU5DocContentsTrns(new byte[0]);               		
			       		//mapDto.setU5DocContentsTrns(DMSUtility.getDocumentBytes(listArr[44].trim()));
			       		mapDto.setU5DocumentNameTrns(""); 			//NOT AFFD ATTR		44
			       		mapDto.setU5FilePathTrns("");
			       		mapDto.setU5UploadTypeTrns("");
			
			   		}
				
				
				varUploads=(list.get(45)!=null && !list.get(45).toString().equalsIgnoreCase("") && !list.get(45).toString().equalsIgnoreCase("null"))?list.get(45).toString():"";
				if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
			   		mapDto.setU6DocContentsTrns(DMSUtility.getDocumentBytes(varUploads));
			   		mapDto.setU6DocumentNameTrns("ATTORNEY PHOTO");			//ATTR PHOTO		45
			   		mapDto.setU6FilePathTrns(varUploads);
			   		mapDto.setU6UploadTypeTrns(varUploads.substring(varUploads.lastIndexOf(".")-2));
			   		}else{
			       		mapDto.setU6DocContentsTrns(new byte[0]);
			       		
			       		//mapDto.setU6DocContentsTrns(DMSUtility.getDocumentBytes(listArr[45].trim()));
			       		mapDto.setU6DocumentNameTrns("");			//ATTR PHOTO		45
			       		mapDto.setU6FilePathTrns("");
			       		mapDto.setU6UploadTypeTrns("");
			
			   		}
				
				
				mapDto.setU7DocumentNameTrns("EXECUTANT PHOTO");			//EXEC PHOTO		43
				mapDto.setU7FilePathTrns(listArr[43].trim());
				if(listArr[43].trim()!=null && !listArr[43].trim().equalsIgnoreCase("") && !listArr[43].trim().equalsIgnoreCase("null")){
			   		mapDto.setU7DocContentsTrns(DMSUtility.getDocumentBytes(listArr[43].trim()));
			   		}else{
			       		mapDto.setU7DocContentsTrns(new byte[0]);
			
			   		}
				mapDto.setU7UploadTypeTrns(listArr[43].trim().substring(listArr[43].trim().lastIndexOf(".")-2));
				
				mapDto.setSrOfficeNameTrns(list.get(48)!=null?list.get(48).toString():"");
				//if(listArr[49]!=null && !listArr[49].trim().equalsIgnoreCase("") && !listArr[49].trim().equalsIgnoreCase("null")){
				mapDto.setPoaRegNoTrns((list.get(49)!=null && !list.get(49).toString().equalsIgnoreCase("") && !list.get(49).toString().equalsIgnoreCase("null"))?list.get(49).toString():"-");
				}else{
					mapDto.setPoaRegNoTrns("-");
				}
				String var=(list.get(50)!=null && !list.get(50).toString().equalsIgnoreCase("") && !list.get(50).toString().equalsIgnoreCase("null"))?list.get(50).toString():"";
				if(var!=null && !var.equalsIgnoreCase("") && !var.equalsIgnoreCase("null")){
				mapDto.setDatePoaRegTrns(convertDOB(var));
				}else{
					mapDto.setDatePoaRegTrns("-");
				}
			}
			
			//BELOW CODE FOR CLAIMANT
			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
			{
				mapDto.setU8DocumentNameTrns("CLAIMANT PHOTO");			//CLAIMANT PHOTO	46
				mapDto.setU8FilePathTrns(listArr[46].trim());
				if(listArr[46].trim()!=null && !listArr[46].trim().equalsIgnoreCase("") && !listArr[46].trim().equalsIgnoreCase("null")){
			   		mapDto.setU8DocContentsTrns(DMSUtility.getDocumentBytes(listArr[46].trim()));
			   		}else{
			       		mapDto.setU8DocContentsTrns(new byte[0]);
			
			   		}
				mapDto.setU8UploadTypeTrns(listArr[46].trim().substring(listArr[46].trim().lastIndexOf(".")-2));
				
				if(list.get(0).toString().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
					varUploads=(list.get(47)!=null && !list.get(47).toString().equalsIgnoreCase("") && !list.get(47).toString().equalsIgnoreCase("null"))?list.get(47).toString():"";
					if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
					mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
			   		mapDto.setU11FilePathTrns(varUploads);
			   		if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
			       		mapDto.setU11DocContentsTrns(DMSUtility.getDocumentBytes(varUploads));
			       		}else{
			           		mapDto.setU11DocContentsTrns(new byte[0]);
				
			       		}
			   		mapDto.setU11UploadTypeTrns(varUploads.substring(varUploads.lastIndexOf(".")-2));
					}
					else
					{
						mapDto.setU11DocumentNameTrns("");
					}
					
					
					}else{
				if(mapDto.getListIDTrns()!=null && !mapDto.getListIDTrns().equalsIgnoreCase(RegInitConstant.PAN_CONSTANT)){
					varUploads=(list.get(47)!=null && !list.get(47).toString().equalsIgnoreCase("") && !list.get(47).toString().equalsIgnoreCase("null"))?list.get(47).toString():"";
					if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){	
				mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");		//PAN				47
				mapDto.setU11FilePathTrns(varUploads);
				if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
			   		mapDto.setU11DocContentsTrns(DMSUtility.getDocumentBytes(varUploads));
			   		}else{
			       		mapDto.setU11DocContentsTrns(new byte[0]);
			
			   		}
				mapDto.setU11UploadTypeTrns(varUploads.substring(varUploads.lastIndexOf(".")-2));
					}
					else
					{
						mapDto.setU11DocumentNameTrns("");
					}
				}
				
				}
			}
			
			//BELOW CODE FOR CLAIMANT POA HOLDER
			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
			{
			
				varUploads=(list.get(45)!=null && !list.get(45).toString().equalsIgnoreCase("") && !list.get(45).toString().equalsIgnoreCase("null"))?list.get(45).toString():"";
			if(varUploads!=null && !varUploads.equalsIgnoreCase("") && !varUploads.equalsIgnoreCase("null")){
				mapDto.setU9DocContentsTrns(DMSUtility.getDocumentBytes(varUploads));
				mapDto.setU9DocumentNameTrns("ATTORNEY PHOTO");				//ATTR PHOTO		45
				mapDto.setU9FilePathTrns(varUploads);
				mapDto.setU9UploadTypeTrns(varUploads.substring(varUploads.lastIndexOf(".")-2));
				}else{
			   		mapDto.setU9DocContentsTrns(new byte[0]);
			   		
			   		//mapDto.setU9DocContentsTrns(DMSUtility.getDocumentBytes(listArr[45].trim()));
			   		mapDto.setU9DocumentNameTrns("");				//ATTR PHOTO		45
			   		mapDto.setU9FilePathTrns("");
			   		mapDto.setU9UploadTypeTrns("");
			   		
			
				}
			
			}
			}
			//end of uploads
			
			
			//add additional uploads code Mohit
			ArrayList<CommonDTO> liDto = commonBd.getAdditonalUploads(appId,partyId);
			mapDto.setListDto(liDto);
			//
			
			if(roleId!=0)
			{
				String var=(list.get(30)!=null && !list.get(30).toString().equalsIgnoreCase("") && !list.get(30).toString().equalsIgnoreCase("null"))?list.get(30).toString():"";
			if(var.equals("") || var.equals("null"))
				 mapDto.setRoleName("-");
			 else
			     mapDto.setRoleName(getRoleName(list.get(29)!=null?list.get(29).toString():"",languageLocale, deedId, inst));
			}else{
				mapDto.setRoleName(list.get(52)!=null?list.get(52).toString():"");
			}
			
			mapDto.setOrgaddressTrns(list.get(11)!=null?list.get(11).toString():"");
			mapDto.setSelectedCountryName(getCountryName("1",languageLocale));
			mapDto.setSelectedStateName(getStateName("1",languageLocale));
			mapDto.setSelectedDistrictName(getDistrictName(list.get(51)!=null?list.get(51).toString():"",languageLocale));
			mapDto.setSelectedDistrict(list.get(51)!=null?list.get(51).toString():"");
			if(listArr[14].trim().equals("") || listArr[14].trim().equals("null"))
				 mapDto.setMobnoTrns("-"); 
			else
			     mapDto.setMobnoTrns((list.get(14)!=null && !list.get(14).toString().equalsIgnoreCase("") && !list.get(14).toString().equalsIgnoreCase("null"))?list.get(14).toString():"-");
			if(listArr[13].trim().equals("") || listArr[13].trim().equals("null"))
				 mapDto.setPhnoTrns("-");
			else
			     mapDto.setPhnoTrns((list.get(13)!=null && !list.get(13).toString().equalsIgnoreCase("") && !list.get(13).toString().equalsIgnoreCase("null"))?list.get(13).toString():"-");
			mapDto.setPartyTypeTrns((list.get(29)!=null && !list.get(29).toString().equalsIgnoreCase("") && !list.get(29).toString().equalsIgnoreCase("null"))?list.get(29).toString():"");
			
			
			int poaFlag=0;
			
			//	if(listArr[54].trim()!=null && !listArr[54].trim().equalsIgnoreCase("") && !listArr[54].trim().equalsIgnoreCase("null")){
			poaFlag=Integer.parseInt((list.get(54)!=null && !list.get(54).toString().equalsIgnoreCase("") && !list.get(54).toString().equalsIgnoreCase("null"))?list.get(54).toString():"0");
			//}
			
			
			if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ||
					poaFlag==RegInitConstant.CLAIMANT_FLAG_2 ||
					poaFlag==RegInitConstant.CLAIMANT_FLAG_4
					)
			{
				
				mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
				mapDto.setTrnsOwnerList(getOwnersHashMap(partyId, appId));
				
				String ownerId=(list.get(32)!=null && !list.get(32).toString().equalsIgnoreCase("") && !list.get(32).toString().equalsIgnoreCase("null"))?list.get(32).toString():"";
				
				mapDto.setOwnerIdTrns(ownerId);
				
				ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
				ownerList=(ArrayList)ownerList.get(0);
				String ownerDetls=ownerList.toString();
				ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
				String ownerDetlsArr[]=ownerDetls.split(",");
				String  var=(ownerList.get(9)!=null && !ownerList.get(9).toString().equalsIgnoreCase("") && !ownerList.get(9).toString().equalsIgnoreCase("null"))?ownerList.get(9).toString():"";//owner organization name
			 if(var.equalsIgnoreCase("") )
			 {
			 mapDto.setOwnerOgrNameTrns("-");
			 mapDto.setOwnerNameTrns(ownerList.get(0).toString());
			 }
			 else
			 {
			 mapDto.setOwnerOgrNameTrns(var);
			 mapDto.setOwnerNameTrns(ownerList.get(10)!=null?ownerList.get(10).toString():"");
			}
			 if(ownerList.get(1).toString().equalsIgnoreCase("f")){
			 //mapDto.setOwnerGendarTrns("Female");
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
			 
			 }else{
				mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
				 
			 }
			 }
			 else{
			    //mapDto.setOwnerGendarTrns("Male");
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
				 
				 }else{
					mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
					 
				 }
			 }
			 mapDto.setOwnerNationalityTrns(ownerList.get(3).toString());
			 mapDto.setOwnerAddressTrns(ownerList.get(4).toString());
			 mapDto.setOwnerPhnoTrns(ownerList.get(5).toString());
			 if(ownerList.get(6)!=null && !ownerList.get(6).toString().equals("") && !ownerList.get(6).toString().equals("null"))
				mapDto.setOwnerEmailIDTrns(ownerList.get(6).toString());	
			      	 else
			      		mapDto.setOwnerEmailIDTrns("-");
			
			 mapDto.setOwnerIdnoTrns(ownerList.get(8)!=null?ownerList.get(8).toString():"-");
			 mapDto.setOwnerAgeTrns(ownerList.get(2).toString());
			 //mapDto.setOwnerDOBTrns(convertDOBfromDb(ownerDetlsArr[2].trim()));   
			 mapDto.setOwnerListIDTrns(ownerList.get(7)!=null?ownerList.get(7).toString():"");
			 mapDto.setOwnerProofNameTrns(ownerList.get(7)!=null?commonBd.getPhotoIdProofName(ownerList.get(7).toString(),languageLocale):"-");
			mapDto.setAddressOwnerOutMpTrns(ownerList.get(11)!=null && !("").equalsIgnoreCase(ownerList.get(11).toString())?ownerList.get(11).toString():"-");
			}
			else{
				mapDto.setPoaHolderFlag(0);
			}
			
			map.put(list.get(24)!=null?list.get(24).toString():"", mapDto);
			*/} catch (Exception e) {
			logger.debug("exception in regcommonbo" + e);
			logger.debug(e.getMessage(), e);
			//e.printStackTrace();
		}
		return map;
	}

	/**
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public String convertDOBfromDb(String date) throws Exception {
		//String sysdate=commonBd.getCurrDateTime();
		//String transDate1=paymentBD.gettranDate(eForm);
		SimpleDateFormat date1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat date2 = new SimpleDateFormat("dd/MMM/yyyy");
		Date d1 = date1.parse(date);
		String formatDate = date2.format(d1);
		System.out.println("formatted date=----->" + formatDate);
		return formatDate;
	}

	/**
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public String convertDOB(String date) throws Exception {
		//String sysdate=commonBd.getCurrDateTime();
		//String transDate1=paymentBD.gettranDate(eForm);
		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat date2 = new SimpleDateFormat("dd/MMM/yyyy");
		Date d1 = date1.parse(date);
		String formatDate = date2.format(d1);
		System.out.println("formatted date=----->" + formatDate);
		return formatDate;
	}

	/**
	 * for getting photo id proof name from db.
	 * 
	 * @param
	 * @return String
	 * @author Simran
	 */
	public String getPhotoIdProofName(String proofId) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getPhotoIdProofName(proofId);
	}

	/**
	 * for saving modified party details
	 * 
	 * @param
	 * @return boolean
	 * @author Simran
	 */
	public boolean savePartyDetails(RegCompCheckerDTO rDTO) throws Exception {
		if (rDTO.getEmailIDTrns() == "-") {
			rDTO.setEmailIDTrns("");
		}
		if (rDTO.getMobnoTrns() == "-") {
			rDTO.setMobnoTrns("");
		}
		if (rDTO.getIndReligionTrns() == "-") {
			rDTO.setIndReligionTrns("");
		}
		if (rDTO.getIndCasteTrns() == "-") {
			rDTO.setIndCasteTrns("");
		}
		if (rDTO.getIndDisabilityTrns() == "-") {
			rDTO.setIndDisabilityTrns("");
		}
		String[] partyDetails = { rDTO.getAgeTrns(), rDTO.getPhnoTrns(), rDTO.getMobnoTrns(), rDTO.getEmailIDTrns(), rDTO.getIndCasteTrns(), rDTO.getIndReligionTrns(), rDTO.getIndDisabilityTrns(), rDTO.getPartyTxnId(), rDTO.getRegInitNumber() };
		//	logger.debug("Size of Array of Parties in BD"+partyDetails.length);
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.savePartyDetails(partyDetails);
	}

	/**
	 * for checking adjudication data
	 * 
	 * @param
	 * @return boolean
	 * @author Simran
	 */
	public String getAdjDetails(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getAdjDetails(regInitId);
	}

	/**
	 * for modifying MV
	 * 
	 * @param
	 * @return boolean
	 * @author Simran
	 */
	public boolean modifyMV(ArrayList propDetls, RegCompCheckerDTO rDTO, String userId, String tDate) throws Exception {
		boolean modify = true;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String dutyDetails[] = new String[10];
		if (propDetls != null) {
			for (int i = 0; i < propDetls.size(); i++) {
				RegCompCheckerDTO gDTO = (RegCompCheckerDTO) propDetls.get(i);
				//		logger.debug("<----MVin BD"+gDTO.getMarketValueTrns());
				//		logger.debug("<----PRO ID IN BD"+gDTO.getPropertyId());
				String propDetails[] = { gDTO.getMarketValueTrns(), userId, tDate, gDTO.getPropertyId(), rDTO.getRegInitNumber() };
				modify = regCompDAO.modifyMarketValue(propDetails);
			}
			if (!modify) {
				modify = false;
			} else {////////////////////////////////COMMENTED BY SIMRAN 6 -JUNE, 2013///////////////////////////
				/*double stmp2 = 0;
				double reg2 = 0;
				String dutyList2 = null;
				ArrayList list2 = regCompDAO.getChangedDutyAtMaker(rDTO.getRegInitNumber());
				if(list2.size()>0)
				{
					dutyList2 = list2.toString();
					dutyList2 = dutyList2.substring(2, dutyList2.length()-2);
					String tempArr[] = dutyList2.split(",");
					 stmp2 =  Double.parseDouble(tempArr[1].trim());
					 reg2 =  Double.parseDouble(tempArr[0].trim());
				}
				Double stmp = Double.parseDouble(rDTO.getStampduty1())-stmp2;
				Double reg = Double.parseDouble(rDTO.getRegistrationFee())-reg2;
				Double total = Double.parseDouble(rDTO.getTotalduty())-stmp2;
				rDTO.setStampduty1(stmp.toString());
				rDTO.setRegistrationFee(reg.toString());
				rDTO.setTotalduty(total.toString());*/
				dutyDetails[0] = rDTO.getRegInitNumber();
				dutyDetails[1] = rDTO.getStampdutyM();
				dutyDetails[2] = rDTO.getPanchayatDutyM();
				dutyDetails[3] = rDTO.getNagarPalikaDutyM();
				dutyDetails[4] = rDTO.getUpkarDutyM();
				dutyDetails[5] = rDTO.getRegistrationFeeM();
				dutyDetails[6] = rDTO.getTotaldutyM();
				dutyDetails[7] = userId;
				dutyDetails[8] = tDate;
				dutyDetails[9] = rDTO.getTotalMarketValueM();
				logger.debug("size of array in bd" + dutyDetails.length);
			}
		}
		modify = regCompDAO.modifyDuties(dutyDetails, rDTO.getRegInitNumber());
		if (modify) {
			String arr[] = { rDTO.getRemarks(), RegCompCheckerConstant.FILE_NAME_SUPP_DOC, rDTO.getSupportingDocPath(), rDTO.getRegInitNumber() };
			modify = regCompDAO.modifyMVdetails(arr);
		}
		return modify;
	}

	/**
	 * This method is to store duties in CHECKER table in case user has not
	 * modified the same
	 * 
	 * @param dutyList
	 * @param regInitId
	 * @param userId
	 * @param tDate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertDutiesNoModify(ArrayList dutyList, String regInitId, String userId, String tDate, RegCompCheckerDTO rdto) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		boolean modify = false;
		Double stmpDuty = 0.0;
		Double regFee = 0.0;
		Double total = 0.0;
		String dutyDetails[] = new String[10];
		for (int i = 0; i < dutyList.size(); i++) {
			RegCompCheckerDTO rDTO = (RegCompCheckerDTO) dutyList.get(i);
			//stmpDuty = Double.parseDouble(rDTO.getStampduty1())+Double.parseDouble(rDTO.getStampDutyBalMkr());
			//regFee = Double.parseDouble(rDTO.getRegistrationFee())+Double.parseDouble(rDTO.getRegFeeDutyBalMkr());
			//total = Double.parseDouble(rDTO.getTotalduty())+Double.parseDouble(rDTO.getStampDutyBalMkr());
			dutyDetails[0] = regInitId;
			dutyDetails[1] = rDTO.getStampduty1();
			dutyDetails[2] = rDTO.getPanchayatDuty();
			dutyDetails[3] = rDTO.getNagarPalikaDuty();
			dutyDetails[4] = rDTO.getUpkarDuty();
			dutyDetails[5] = rDTO.getRegistrationFee();
			dutyDetails[6] = rDTO.getTotalduty();
			dutyDetails[7] = userId;
			dutyDetails[8] = tDate;
			dutyDetails[9] = rdto.getTotalMarketValue();
		}
		logger.debug("size of array in bd" + dutyDetails.length);
		modify = regCompDAO.modifyDuties(dutyDetails, regInitId);
		return modify;
	}

	/**
	 * @param regInitId
	 * @param eForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLinkingDuties(String regInitId, RegCompCheckerForm eForm) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = new ArrayList();
		String dutyInit = null;
		ArrayList dutyList = new ArrayList();
		Double stmpDutyChkr = 0.0;
		Double regFeeChkr = 0.0;
		Double stampdutyInit = 0.0;
		Double regFeeInit = 0.0;
		Double stmpDutyMkr = 0.0;
		Double regFeeMkr = 0.0;
		Double stampDutyAlreadyPaid = 0.0;
		Double regFeeAlreadyPaid = 0.0;
		Double stampDutyBal = 0.0;
		Double regFeeBal = 0.0;
		Double totalDuty;
		Double totalRegFee;
		Double totalStampDuty = 0.0;
		RegCompCheckerDTO rgDTO = new RegCompCheckerDTO();
		DecimalFormat dmFt = new DecimalFormat("#.##");
		//***********************************AMOUNT PAID DURING REG-INITIATION *******************//
		stampdutyInit = regCompDAO.getDutiesRegInit(regInitId);
		stampdutyInit = Double.valueOf(dmFt.format(stampdutyInit));//paid amount at initiation
		rgDTO.setDutyAtRegInit(BigDecimal.valueOf(stampdutyInit).toPlainString());
		regFeeInit = regCompDAO.getRegFeeRegInit(regInitId);
		regFeeInit = Double.valueOf(dmFt.format(regFeeInit));
		rgDTO.setRegFeeAtRegInit(BigDecimal.valueOf(regFeeInit).toPlainString());
		//***********************************AMOUNT PAID DURING REG-INITIATION *******************//
		//****************************(REBATE)ALREADY PAID DUTIES AT REG-INITIATION***********************//	
		ArrayList list7 = regCompDAO.getLinkingDutiesAlreadyPaid(regInitId);// already Paid amts at initiation
		for (int l = 0; l < list7.size(); l++) {
			ArrayList list8 = (ArrayList) list7.get(l);
			String regFee = (String) list8.get(0);
			regFee = regFee == null ? "" : regFee;
			if (regFee != "") {
				rgDTO.setRegistrationFeeAlreadyPaid((String) list8.get(0));
				regFeeAlreadyPaid = Double.parseDouble((String) list8.get(0));
			} else {
				rgDTO.setRegistrationFeeAlreadyPaid("0");
			}
			String stampDuty = (String) list8.get(1);
			stampDuty = stampDuty == null ? "" : stampDuty;
			if (stampDuty != "") {
				rgDTO.setStampdutyAlreadyPaid((String) list8.get(1));
				stampDutyAlreadyPaid = Double.parseDouble((String) list8.get(1));
			} else {
				rgDTO.setStampdutyAlreadyPaid("0");
			}
		}
		//****************************(REBATE)ALREADY PAID DUTIES AT REG-INITIATION***********************//	
		//****************************DUTIES AT REG INITIATION*****************************************//
		ArrayList regInit = regCompDAO.getDutyDetls(regInitId);
		for (int i = 0; i < regInit.size(); i++) {
			ArrayList list11 = (ArrayList) regInit.get(i);
			rgDTO.setTotalduty((String) list11.get(5));
			rgDTO.setRegistrationFee((String) list11.get(4));
		}
		//**********************************************************************************************//
		//*******************Duties Stored in REGISTRATION COMPLETION TABLE AFTER FIRST SCREEN**********//
		ArrayList regComp = regCompDAO.getLinkingDutiesChecker(regInitId);
		for (int k = 0; k < regComp.size(); k++) {
			ArrayList list12 = (ArrayList) regComp.get(k);
			stmpDutyChkr = Double.parseDouble((String) list12.get(5));
			stmpDutyChkr = Double.valueOf(dmFt.format(stmpDutyChkr));
			regFeeChkr = Double.parseDouble((String) list12.get(4));
			regFeeChkr = Double.valueOf(dmFt.format(regFeeChkr));
			totalStampDuty = Double.parseDouble((String) list12.get(0));
			rgDTO.setTotaldutyM((String) list12.get(5));
			rgDTO.setRegistrationFeeM((String) list12.get(4));
		}
		//********************************************************************************************//
		//********************REBATE VERIFIED DURING MAKER*******************************************//
		ArrayList regCompMkr = regCompDAO.getLinkDutiesAtMaker(regInitId);
		for (int j = 0; j < regCompMkr.size(); j++) {
			ArrayList list13 = (ArrayList) regCompMkr.get(j);
			RegCompCheckerDTO rcDTO = new RegCompCheckerDTO();
			rcDTO.setRegFeeAtRegMkr((String) list13.get(1));
			rcDTO.setStampDutyBalMkr((String) list13.get(0));
			rcDTO.setPropertyId((String) list13.get(2));
			//ADDED BY SIMRAN:- ESTAMP LINKING  24-DEC
			String regNumber = (String) list13.get(3);
			String estampNumber = (String) list13.get(4);
			String phycialStamp = (String) list13.get(5);
			String oldRegNumber = (String) list13.get(6);
			regNumber = regNumber == null ? "" : regNumber;
			estampNumber = estampNumber == null ? "" : estampNumber;
			phycialStamp = phycialStamp == null ? "" : phycialStamp;
			oldRegNumber = oldRegNumber == null ? "" : oldRegNumber;
			if ("".equalsIgnoreCase(oldRegNumber)) {
				if ("".equals(regNumber) && "".equals(phycialStamp)) {
					rcDTO.setRegInitEstampCode(estampNumber);
					rcDTO.setTypeFlag("1");
				} else if ("".equals(regNumber) && "".equals(estampNumber)) {
					rcDTO.setRegInitPhysicalStamp(phycialStamp);
					rcDTO.setTypeFlag("3");
				} else {
					rcDTO.setRegInitNumber((String) list13.get(3));
					rcDTO.setTypeFlag("2");
				}
			} else {
				rcDTO.setOldRegistrationNumber(oldRegNumber);
				rcDTO.setTypeFlag("4");
			}
			stmpDutyMkr = stmpDutyMkr + Double.parseDouble((String) list13.get(0));
			stmpDutyMkr = Double.valueOf(dmFt.format(stmpDutyMkr));
			regFeeMkr = regFeeMkr + Double.parseDouble((String) list13.get(1));
			regFeeMkr = Double.valueOf(dmFt.format(regFeeMkr));
			dutyList.add(rcDTO);
		}
		//*****************************************************************************************//
		//***********************************BALANCE*******************************************//
		stampDutyBal = stmpDutyChkr + stampDutyAlreadyPaid - stmpDutyMkr - stampdutyInit;
		stampDutyBal = Double.valueOf(dmFt.format(stampDutyBal));
		//	logger.debug("<----------"+regFeeMkr);
		//	logger.debug("<----------"+regFeeChkr);
		regFeeBal = regFeeChkr + regFeeAlreadyPaid - regFeeMkr - regFeeInit;
		regFeeBal = Double.valueOf(regFeeBal);
		//	logger.debug("<----------"+regFeeBal);
		totalDuty = stmpDutyChkr + stampDutyAlreadyPaid - stmpDutyMkr;
		totalRegFee = regFeeChkr + regFeeAlreadyPaid - regFeeMkr;
		totalStampDuty = totalStampDuty + stampDutyAlreadyPaid - stmpDutyMkr;
		eForm.setFinalDuty(totalDuty);
		eForm.setFinalRegFee(totalRegFee);
		eForm.setFinalStampDuty(totalStampDuty);
		if (stampDutyBal < 0) {//by roopam- 11 may 2015 - negative amount changed to zero 
			stampDutyBal = 0.0;
		}
		if (regFeeBal < 0) {//by roopam- 11 may 2015 - negative amount changed to zero 
			regFeeBal = 0.0;
		}
		rgDTO.setStampDutyPmt(BigDecimal.valueOf(stampDutyBal).toPlainString());
		rgDTO.setRegFeePmt(BigDecimal.valueOf(regFeeBal).toPlainString());
		//***********************************BALANCE********************************************//
		/* ArrayList amtPaidAtChecker = new ArrayList();
		 if(eForm.getRegDTO().isAdj())
		 {
			 amtPaidAtChecker = regCompDAO.getLinkingDutiesAdj(eForm.getRegDTO().getAdjNumber());
		 }
		 else
		 {
			amtPaidAtChecker = regCompDAO.getLinkingDutiesChecker(regInitId);
		 }
		
		 for(int k = 0; k<amtPaidAtChecker.size();k++)
		 {
			 ArrayList list2 = new ArrayList();
			 list2 = (ArrayList)amtPaidAtChecker.get(k);
			 regFee = Double.parseDouble((String)list2.get(1));
			 stmpDuty = Double.parseDouble((String)list2.get(0));
			 dutyList.
		 }*/
		// rgDTO.setDutyAtRegChkr((String)amtPaidAtChecker.get(0));
		// rgDTO.setRegFeeAtRegChkr((String)amtPaidAtChecker.get(1));
		/* list = regCompDAO.getLinkingDuties(regInitId); 
		
			 ArrayList list1 = new ArrayList();
			 ArrayList dutyList = new ArrayList();
				list1 = (ArrayList)list.get(i);
				duties = new RegCompCheckerDTO();
				
				
				if((String)list1.get(2)!="")
				{
					duties.setRegistrationFeeAlreadyPaid((String)list1.get(2));
				}
				else
				{
					duties.setRegistrationFeeAlreadyPaid("0");
				}
				if((String)list1.get(3)!="")
				{
					duties.setStampdutyAlreadyPaid((String)list1.get(3));
				}
				else
				{
					duties.setStampdutyAlreadyPaid("0");
				}
				if((String)list1.get(2)!="")
				{
					duties.setRegistrationFeeToBePaid((String)list1.get(2));
				}
				else
				{
					duties.setRegistrationFeeToBePaid("0");
				}
				if((String)list1.get(3)!="")
				{
					duties.setStampdutyToBePaid((String)list1.get(3));
				}
				else
				{
					duties.setStampdutyToBePaid("0");
				}*/
		//duties.setRegistrationFee((String)list1.get(4));
		//duties.setStampduty1((String)list1.get(5));
		//double regFeeBal = Double.parseDouble(list1.get(4).toString());
		//double stampDutyBal = Double.parseDouble(list1.get(5).toString());
		//DecimalFormat dmFt = new DecimalFormat("#.##");
		//dmFt.format(regFeeBal);
		//dmFt.format(stampDutyBal);
		//dmFt.format(regFee);
		//dmFt.format(stmpDuty);
		//dmFt.format(stampdutyInit);
		//dmFt.format(pAmt);
		//regFeeChkr = regFee+regFeeBal;
		//stmpDutyChkr = stmpDuty+stampDutyBal-stampdutyInit-pAmt;
		//regFeeChkr = Double.valueOf(dmFt.format(regFeeChkr));
		//stmpDutyChkr =Double.valueOf(dmFt.format(stmpDutyChkr));
		//if(regFeeChkr < 0)
		//regFeeChkr = 0;
		//if(stmpD)
		//total = b1+b2;
		//netBal = total-pAmt;
		//dutyList.add(duties);
		//Iterator itr = dutyList.iterator();
		//while(itr.hasNext())
		//{
		//RegCompCheckerDTO rdto =(RegCompCheckerDTO)itr.next();
		//rdto.getRegistrationFeeAlreadyPaid();
		//rdto.getStampdutyAlreadyPaid();
		//rdto.getRegistrationFeeToBePaid();
		//}
		//dutyDetails.put((String)list1.get(0)+"_"+(String)list1.get(1)+"_"+(String)list1.get(4)+"_"+(String)list1.get(5), dutyList);
		//dutyDetails.put((String)list1.get(0)+"_"+(String)list1.get(1), dutyList);
		// }
		//rgDTO.setDutyAtRegChkr(((Double)stmpDutyChkr).toString());
		//rgDTO.setRegFeeAtRegChkr(((Double)regFeeChkr).toString());
		ArrayList singleAmnts = new ArrayList();
		singleAmnts.add(rgDTO);
		eForm.setLinkingDutiesDisp(singleAmnts);
		logger.debug("Size of map in bd" + dutyList.size());
		return dutyList;
	}

	/**
	 * @param regInitId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean getLinkDetails(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getLinkDetails(regInitId);
	}

	/**
	 * @param regInit
	 * @return boolean
	 */
	public boolean saveLinkingPropertyPaymentDetails(String regInit, String userId, String date) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.saveLinkingPropertyPaymentDetails(regInit, userId, date);
	}

	/**
	 * @param regNum
	 * @return boolean
	 */
	public boolean isHoldData(String regNum, String fwdPage) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.isHoldData(regNum, fwdPage);
	}

	/**
	 * @param regNum
	 * @param hldFlag
	 * @param fwdPage
	 * @param date
	 * @param createdBy
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveHoldDataChecker(RegCompCheckerDTO rDTO, String hldFlag, String fwdPage, String date, String createdBy, boolean flag, String officeId, String reasonEnglish, String reasonHindi) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		if (!"7".equalsIgnoreCase(hldFlag)) {
			String mailContent = getEmailHoldContent(rDTO.getRegInitNumber(), rDTO.getRemarks(), officeId, "en", officeId, reasonEnglish, reasonHindi);
			String smsContent = getSMSContent(rDTO.getRegInitNumber(), "N");
			regCompDAO.sendMail(rDTO.getRegInitNumber(), mailContent, createdBy);
			regCompDAO.sendSMS(rDTO.getRegInitNumber(), smsContent, createdBy);
		}
		return regCompDAO.saveHoldDataChecker(rDTO, hldFlag, fwdPage, date, createdBy, flag);
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWitnessDetails(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list1 = regCompDAO.getWitnessDetails(regInitId);
		RegCompCheckerDTO rDTO;
		ArrayList list2 = new ArrayList();
		ArrayList witnessDetails = new ArrayList();
		for (int i = 0; i < list1.size(); i++) {
			list2 = (ArrayList) list1.get(i);
			rDTO = new RegCompCheckerDTO();
			rDTO.setWitnessTxnNummber((String) list2.get(0));
			rDTO.setFnameTrns((String) list2.get(1));
			rDTO.setLnameTrns((String) list2.get(2));
			rDTO.setIndaddressTrns((String) list2.get(3));
			rDTO.setAgeTrns((String) list2.get(4));
			rDTO.setWitnessphotoPath((String) list2.get(5));
			witnessDetails.add(rDTO);
		}
		return witnessDetails;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getLinkHstryDetails(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list1 = regCompDAO.getLinkHstryDetails(regInitId);
		ArrayList list2 = new ArrayList();
		ArrayList linkHistryDetails = new ArrayList();
		for (int i = 0; i < list1.size(); i++) {
			list2 = (ArrayList) list1.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setPropertyId((String) list2.get(0));
			rDTO.setOldRegInitNumber((String) list2.get(1));
			//String deedId = getDeedIdCompletion((String)list2.get(1));
			rDTO.setOldPropertyId((String) list2.get(2));
			String finalPin = "Not Generated";
			String pin = (String) list2.get(3);
			pin = pin == null ? "" : pin;
			if (pin != "") {
				//String pin = (String)list2.get(3);
				//int len = pin.length();
				//logger.debug("length of pin"+len);
				//for(int j = 0; j < pin.length();j++)
				//{
				//finalPin = finalPin+"*";
				//}
				finalPin = "Generated";
				//	logger.debug("Final Pin"+finalPin);
			}
			rDTO.setPinNumber(finalPin);
			rDTO.setTransactionType((String) list2.get(4));
			linkHistryDetails.add(rDTO);
		}
		return linkHistryDetails;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPartiesPresentDetails(String regInitId, String language) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList partyPresentDetails = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.getPartiesPresentDetails(regInitId, language);
		/*	for(int i= 0; i<list.size();i++)
			{
				list1 = (ArrayList)list.get(i);
				RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
				rDTO.setPartyTxnId((String)list1.get(0));
				rDTO.setFnameTrns((String)list1.get(1));
				rDTO.setLnameTrns((String)list1.get(2));
				rDTO.setIsGovtOfficial((String)list1.get(3));
				rDTO.setTypeOfGovtEmp((String)list1.get(4));
				rDTO.setStatus("N");
				partyPresentDetails.add(rDTO);
			}*/
		return list;
	}

	/**
	 * @param regInitId
	 * @return String
	 * @throws Exception
	 */
	public String getExecutionDate(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String date = regCompDAO.getExecutionDate(regInitId);
		String execDate = null;
		//logger.debug("^^^^^^^^^^^^"+date);
		SimpleDateFormat sdf2 = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		if (date.equals("") || date.equals(null)) {
			execDate = "";
		} else {
			Date d1 = sdf1.parse(date);
			execDate = sdf2.format(d1);
		}
		return execDate;
	}

	/**
	 * @param exDate
	 * @return boolean
	 * @throws Exception
	 */
	public String getExecDate(String regNumber) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getExecDate(regNumber);
	}

	public boolean dateComparison(String exDate) throws Exception {
		boolean flag = false;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		if (exDate.equals("") || exDate.equals(null)) {
			flag = true;
		} else {
			Date execDate = sdf1.parse(exDate);
			//Date sysDate = new Date();
			//logger.debug("sysdate"+sysDate);
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			String d1 = regCompDAO.getSystemDate();//sdf1.format(sysDate);
			Date sDate = sdf1.parse(d1);
			//		logger.debug("Final Sysdate"+sDate);
			//		logger.debug("final execDate"+execDate);
			logger.debug("<------getTime" + sDate.getTime());
			//		logger.debug("<------getTime"+execDate.getTime());
			long diff1 = (long) (sDate.getTime() - execDate.getTime());
			long diff = diff1 / (1000 * 60 * 60 * 24);
			logger.debug("diff" + diff1);
			logger.debug("diff" + diff);
			if (diff > 120) {
				flag = false;
			} else {
				flag = true;
			}
		}
		return flag;
	}

	public String getPenalityId(String regNo) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getPenalityId(regNo);
	}

	public String getPenalityIdOnly(String regNo) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getPenalityIdOnly(regNo);
	}

	public boolean upadteTimeBarredDetials(RegCompleteMakerDTO dto) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.upadteTimeBarredDetials(dto);
	}

	/**
	 * @param exDate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean dateComparisonElevenMonths(String exDate) throws Exception {
		boolean flag = false;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		if (exDate.equals("") || exDate.equals(null)) {
			flag = true;
		} else {
			Date execDate = sdf1.parse(exDate);
			Date sysDate = new Date();
			logger.debug("sysdate" + sysDate);
			//logger.debug("exec date"+exDate);
			String d1 = sdf1.format(sysDate);
			Date sDate = sdf1.parse(d1);
			logger.debug("sysdate" + sDate);
			//	logger.debug("Final Sysdate"+sDate);
			//	logger.debug("final execDate"+execDate);
			//	logger.debug("<------getTime"+sDate.getTime());
			//	logger.debug("<------getTime"+execDate.getTime());
			long diff1 = (long) (sDate.getTime() - execDate.getTime());
			long diff = diff1 / (1000 * 60 * 60 * 24);
			logger.debug("diff" + diff1);
			logger.debug("diff" + diff);
			if (diff > 330) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getComplianceDetails(String regInitId) throws Exception {
		String regArr[] = { regInitId };
		ArrayList list1 = new ArrayList();
		ArrayList complianceList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getComplianceDetails(regArr);
		for (int i = 0; i < list.size(); i++) {
			list1 = (ArrayList) list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setComplianceId((String) list1.get(0));
			rDTO.setComplianceName((String) list1.get(1));
			complianceList.add(rDTO);
		}
		return complianceList;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPartyDet(String regInitId, String lang) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getPartyDet(regInitId);
		ArrayList sublist = new ArrayList();
		ArrayList mainList = new ArrayList();
		String partyId = "";
		String CommonDeedFlag = "";
		RegCommonDTO mapDto = new RegCommonDTO();
		RegCommonForm form = new RegCommonForm();
		CommonDeedFlag = regCompDAO.getCommonFlowFlag(regInitId);
		String OwnerRolePoA = "";
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				sublist = (ArrayList) list.get(i);
				RegCompCheckerDTO dto = new RegCompCheckerDTO();
				if (sublist.get(4) != null) {
					partyId = sublist.get(4).toString();
					//added by ankit for Aadhar integration  - start
					AadharDetailDTO aadharDts = new RegCompCheckerBD().getAadharDetailsByPartyTxnId(partyId);
					if (aadharDts.getACKID_CHECKER() != null && aadharDts != null && !aadharDts.getACKID_CHECKER().isEmpty()) {
						dto.setIsAadharValidated("true");
					} else {
						dto.setIsAadharValidated("false");
					}
					//added by ankit for Aadhar integration  - end
				}
				String fName = (String) sublist.get(1);
				fName = fName == null ? "" : fName;
				int roleId = Integer.parseInt((String) sublist.get(14));
				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				dto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				int ExecutantClaimantfromDB = 0;
				if (sublist.get(26) != null && !sublist.get(26).toString().equalsIgnoreCase("")) {
					ExecutantClaimantfromDB = Integer.parseInt((String) sublist.get(26));
				}
				if ((fName).equals("")) {
					if (sublist.get(19) != null && sublist.get(19).toString().equalsIgnoreCase("3")) {
						if (sublist.get(20) != null && !sublist.get(20).toString().equalsIgnoreCase("")) {
							dto.setFnameTrns((String) sublist.get(20));
							dto.setLnameTrns("");
						} else {
							dto.setFnameTrns("--");
							dto.setLnameTrns("");
						}
					} else {
						if (sublist.get(19) != null && sublist.get(19).toString().equalsIgnoreCase("1")) {
							if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6) {
								//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
								mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
								System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
								form.setExecutantClaimantName(commonBo.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
								String RoleName = form.getExecutantClaimantName();
								System.out.println("Role Name " + RoleName);
								String PoaName = null;
								// to check hindi and english role 
								String HindiEnglishRole[] = RoleName.split(",");
								String hindesc = HindiEnglishRole[0];
								String EngDesc = HindiEnglishRole[1];
								if (EngDesc.contains("Authenticated")) {
									if (lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										PoaName = " By(Authenticated PoA Holder) ";
									} else {
										PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
									}
								} else {
									if (lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										PoaName = " By(PoA Holder) ";
									} else {
										PoaName = " द्वारा (मुख्‍त्‍यार आम)";
									}
								}
								String OwnerDetail = null;
								String poAOwnerListNew = commonBd.getOwnerDetails(regInitId, partyId, lang);
								String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
								OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
								dto.setFnameTrns(OwnerRolePoA);
								dto.setLnameTrns("");
							} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
								// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
								mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								String RoleName = null;
								// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
								mapDto.setRoleName(commonBo.getRoleNameForPOA((String) sublist.get(14)));
								RoleName = mapDto.getRoleName();
								System.out.println("Role Name " + RoleName);
								// to check hindi and english role 
								String HindiEnglishRole[] = RoleName.split(",");
								String hindesc = HindiEnglishRole[0];
								String EngDesc = HindiEnglishRole[1];
								String PoaName = null;
								if (EngDesc.contains("Authenticated")) {
									if (lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										PoaName = " By(Authenticated PoA Holder) ";
									} else {
										PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
									}
								} else {
									if (lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										PoaName = " By(PoA Holder) ";
									} else {
										PoaName = " द्वारा (मुख्‍त्‍यार आम)";
									}
								}
								System.out.println("POA NAMe is " + PoaName);
								System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
								//System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
								String OwnerDetail = null;
								String poAOwnerListNew = commonBd.getOwnerDetails(regInitId, partyId, lang);
								String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
								OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
								dto.setFnameTrns(OwnerRolePoA);
								dto.setLnameTrns("");
							} else {
								dto.setFnameTrns((String) sublist.get(7));
								dto.setLnameTrns("");
							}
						}
					}
				} else {
					if (sublist.get(19) != null && sublist.get(19).toString().equalsIgnoreCase("2")) {
						if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							//int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
							System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
							form.setExecutantClaimantName(commonBo.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
							String RoleName = form.getExecutantClaimantName();
							System.out.println("Role Name " + RoleName);
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = null;
							if (EngDesc.contains("Authenticated")) {
								if (lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(regInitId, partyId, lang);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							dto.setFnameTrns(OwnerRolePoA);
							dto.setLnameTrns("");
						}
						// will deed Authenticaed POA finish - akansha 
						else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							String RoleName = null;
							// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
							mapDto.setRoleName(commonBo.getRoleNameForPOA((String) sublist.get(14)));
							RoleName = mapDto.getRoleName();
							System.out.println("Role Name " + RoleName);
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = null;
							if (EngDesc.contains("Authenticated")) {
								if (lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							System.out.println("POA NAMe is " + PoaName);
							System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
							//  System.out.println("PoAOwnerlistdisplay"+poAOwnerList.get(i));
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(regInitId, partyId, lang);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							dto.setFnameTrns(OwnerRolePoA);
							dto.setLnameTrns("");
						}
						//individual
						//minor check to be implemented.
						// Minor Guardian and Middle Name - Rahul
						else {
							int age = sublist.get(22) != null ? Integer.parseInt((String) sublist.get(22)) : 0;
							String relations = "";
							if (sublist.get(25) != null) {
								relations = commonBo.getRelationshipName((sublist.get(25).toString()), lang);
							}
							String Mname = "";
							dto.setFnameTrns(fName);
							dto.setLnameTrns((String) sublist.get(2));
							/*if(age<RegCompCheckerConstant.MINOR_AGE_LIMIT){
								
								dto.setFnameTrns(fName);
								dto.setLnameTrns((String)sublist.get(2)+" C/O "+(String)sublist.get(23));
								
								
								
							}*/
							// minor check
							if (age < RegCompCheckerConstant.MINOR_AGE_LIMIT) {
								String MinorName = dto.getFnameTrns();
								if (lang.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									dto.setFnameTrns("( Minor ) " + MinorName);
									dto.setLnameTrns((String) sublist.get(2) + " " + relations + " " + (String) sublist.get(23) + " BY " + (String) sublist.get(24));
								} else {
									dto.setFnameTrns("( अवयस्‍क ) " + MinorName);
									dto.setLnameTrns((String) sublist.get(2) + " " + relations + " " + (String) sublist.get(23) + " द्वारा " + (String) sublist.get(24));
								}
								System.out.println("MINOR name " + dto.getFnameTrns() + " last name " + dto.getLnameTrns());
							}
						}
					}
				}
				dto.setPartyTxnId((String) sublist.get(4));
				String photoTypeId = (String) sublist.get(6);
				photoTypeId = photoTypeId == null ? "" : photoTypeId;
				if ((photoTypeId).equalsIgnoreCase("")) {
					dto.setPhotoTypeId("Not Available");
				} else {
					dto.setPhotoTypeId(photoTypeId);
				}
				String photoProofName = (String) sublist.get(5);
				photoProofName = photoProofName == null ? "" : photoProofName;
				if ((photoProofName).equalsIgnoreCase("")) {
					dto.setPhotoProofTypeName("Not Available");
				} else {
					String proofname = getPhotoIdProofName(photoProofName);
					dto.setPhotoProofTypeName(proofname);
				}
				String photoName = (String) sublist.get(8);
				String photoPath = (String) sublist.get(9);
				String signatureName = (String) sublist.get(10);
				String signaturePath = (String) sublist.get(11);
				String thumbName = (String) sublist.get(12);
				String thumbPath = (String) sublist.get(13);
				String thumbRem = (String) sublist.get(17);
				String partyRoleId = (String) sublist.get(14);
				if (partyRoleId.equals("0")) {
					dto.setRoleName((String) sublist.get(15));
				} else {
					if (lang.equalsIgnoreCase("en")) {
						dto.setRoleName((String) sublist.get(3));
					} else {
						dto.setRoleName((String) sublist.get(16));
					}
				}
				if (photoName != null && !photoName.equalsIgnoreCase("")) {
					dto.setPhotoName(photoName);
				} else {
					dto.setPhotoName("");
				}
				if (photoPath != null && !photoPath.equalsIgnoreCase("")) {
					dto.setPartyPicChk("Y");
					dto.setPhotoPath(photoPath);
				} else {
					dto.setPartyPicChk("N");
					dto.setPhotoPath("");
				}
				if (signatureName != null && !signatureName.equalsIgnoreCase("")) {
					dto.setSignatureName(signatureName);
				} else {
					dto.setSignatureName("");
				}
				if (signaturePath != null && !signaturePath.equalsIgnoreCase("")) {
					dto.setPartySignChk("Y");
					dto.setSignaturePath(signaturePath);
				} else {
					dto.setPartySignChk("N");
					dto.setSignaturePath("");
				}
				if (thumbName != null && !thumbName.equalsIgnoreCase("")) {
					dto.setThumbName(thumbName);
				} else {
					dto.setThumbName("");
				}
				if (thumbPath != null && !thumbPath.equalsIgnoreCase("")) {
					dto.setPartyThumbChk("Y");
					dto.setThumbPath(thumbPath);
				} else {
					dto.setPartyThumbChk("N");
					dto.setThumbPath("");
				}
				thumbRem = thumbRem == null ? "" : thumbRem;
				dto.setThumbRemarks(thumbRem);
				if (sublist.get(19) != null && sublist.get(19).toString().equalsIgnoreCase("3")) {
					dto.setGovtOfcCheck("Y");
				} else {
					dto.setGovtOfcCheck("N");
				}
				//dto.setGovtOfcCheck((String)sublist.get(18));
				String presentFlag = (String) sublist.get(21);
				if (presentFlag != null && presentFlag.equalsIgnoreCase("Y")) {} else {
					mainList.add(dto);
				}
			}
		}
		return mainList;
	}

	/**
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public ArrayList getWitnessDet(String regNumber, String language) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getWitnessDet(regNumber);
		ArrayList sublist = new ArrayList();
		ArrayList mainList = new ArrayList();
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				sublist = (ArrayList) list.get(i);
				RegCompCheckerDTO dto = new RegCompCheckerDTO();
				dto.setWitnessTxnNummber((String) sublist.get(0));
				dto.setFnameTrns((String) sublist.get(1));
				dto.setLnameTrns((String) sublist.get(2));
				if ("en".equalsIgnoreCase(language)) {
					dto.setRoleName("Witness");
				} else {
					dto.setRoleName("गवाह");
				}
				//dto.setPartyTxnId((String)sublist.get(3));
				/*if(((String)sublist.get(4)).equalsIgnoreCase(""))
				{
					dto.setPhotoTypeId("Not Available");
				}else
				{
				dto.setPhotoTypeId((String)sublist.get(4));
				}
				if(((String)sublist.get(3)).equalsIgnoreCase(""))
				{dto.setPhotoProofTypeName("Not Available");
				}
				else{
					String proofname=getPhotoIdProofName((String)sublist.get(3));
					dto.setPhotoProofTypeName(proofname);
					}*/
				dto.setPhotoTypeId("Not Available");
				dto.setPhotoProofTypeName("Not Available");
				String photoName = (String) sublist.get(4);
				String photoPath = (String) sublist.get(5);
				String thumbName = (String) sublist.get(6);
				String thumbPath = (String) sublist.get(7);
				String signName = (String) sublist.get(8);
				String signPath = (String) sublist.get(9);
				if (photoName != null && !photoName.equalsIgnoreCase("")) {
					dto.setPhotoName(photoName);
				} else {
					dto.setPhotoName("");
				}
				if (photoPath != null && !photoPath.equalsIgnoreCase("")) {
					dto.setWittPicChk("Y");
					dto.setPhotoPath(photoPath);
				} else {
					dto.setWittPicChk("N");
					dto.setPhotoPath("");
				}
				dto.setWittThumbChk("N");
				dto.setWittSignChk("N");
				if (thumbName != null && !thumbName.equalsIgnoreCase("")) {
					dto.setWittThumbChk("Y");
					dto.setThumbName(thumbName);
					dto.setThumbPath(thumbPath);
				}
				if (signName != null && !signName.equalsIgnoreCase("")) {
					dto.setWittSignChk("Y");
					dto.setSignatureName(signName);
					dto.setSignaturePath(signPath);
				}
				mainList.add(dto);
			}
		}
		return mainList;
	}

	/**
	 * @param regNumber
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCaveatDetails(String regNumber) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList caveatList = new ArrayList();
		list = regCompDAO.getLinkdRegNumber(regNumber);
		list = regCompDAO.getCaveatDetChecklist(regNumber, list);
		/*for(int i= 0;i<list.size();i++)
		{
			list1 = (ArrayList)list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setCaveatTxnId((String)list1.get(0));
			rDTO.setCaveatId((String)list1.get(1));
			rDTO.setRegInitNumber((String)list1.get(5));
			
			String caveatName = regCompDAO.getCaveatName(rDTO.getCaveatId());
			rDTO.setCaveatName(caveatName);
			caveatList.add(rDTO);
			
		}
		return caveatList;
		*/
		return list;
	}

	/**
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public ArrayList getBnkCaveatDet(String regNumber) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList caveatList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.getLinkdRegNumber(regNumber);
		list = regCompDAO.getBnkCaveatDet(regNumber, list);
		/*for(int i= 0;i<list.size();i++)
		{
			list1 = (ArrayList)list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setCaveatTxnId((String)list1.get(0));
			rDTO.setCaveatId((String)list1.get(1));
			rDTO.setRegInitNumber((String)list1.get(5));
			
			String caveatName = regCompDAO.getCaveatName(rDTO.getCaveatId());
			rDTO.setCaveatName(caveatName);
			caveatList.add(rDTO);
			
		}
		return caveatList;
		*/
		return list;
	}

	/**
	 * @param regNumber
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCheckListDetails(String regNumber, RegCompCheckerForm eForm) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList checkList = new ArrayList();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.getCheckListDetails(regNumber);
		for (int i = 0; i < list.size(); i++) {
			Map uploadDeathCert = new HashMap();
			Map uploadPOA = new HashMap();
			list1 = (ArrayList) list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			String checkListNo = (String) list1.get(0);
			rDTO.setCheckListTxnId((String) list1.get(0));
			String deedOutIndFlg = (String) list1.get(1);
			deedOutIndFlg = deedOutIndFlg == null ? "N" : deedOutIndFlg;
			rDTO.setDeedOutIndiaFlg(deedOutIndFlg);
			String deedOutIndia = (String) list1.get(2);
			deedOutIndia = (deedOutIndia == null ? "" : deedOutIndia);
			//logger.debug("deedOutIndia"+deedOutIndia);
			if ((deedOutIndia).equals("")) {
				rDTO.setDeedOutIndDate("");
			} else {
				String d = deedOutIndia;
				Date d1 = sdf1.parse(d);
				String fin = sdf2.format(d1);
				rDTO.setDeedOutIndDate(fin);
			}
			String courtDecFlg = (String) list1.get(3);
			courtDecFlg = courtDecFlg == null ? "N" : courtDecFlg;
			rDTO.setCourtDecFlg(courtDecFlg);
			String orderDate = (String) list1.get(4);
			orderDate = (orderDate == null ? "" : orderDate);
			//logger.debug("deedOutIndia"+orderDate);
			if ((orderDate).equals("")) {
				rDTO.setOrderDate("");
			} else {
				String d = orderDate;
				Date d1 = sdf1.parse(d);
				String fin = sdf2.format(d1);
				rDTO.setOrderDate(fin);
			}
			String courtDecAppFlg = (String) list1.get(5);
			courtDecAppFlg = courtDecAppFlg == null ? "N" : courtDecAppFlg;
			rDTO.setCourtDecAplFlg(courtDecAppFlg);
			String lastAplDate = (String) list1.get(6);
			lastAplDate = lastAplDate == null ? "" : lastAplDate;
			//logger.debug("deedOutIndia"+lastAplDate);
			if (lastAplDate.equals("")) {
				rDTO.setLastAplDate("");
			} else {
				String d = lastAplDate;
				Date d1 = sdf1.parse(d);
				String fin = sdf2.format(d1);
				rDTO.setLastAplDate(fin);
			}
			//rDTO.setLastAplDate((String)list1.get(6));
			String deathUploadFlg = (String) list1.get(7);
			deathUploadFlg = deathUploadFlg == null ? "N" : deathUploadFlg;
			if ((deathUploadFlg).equalsIgnoreCase("Y")) {
				ArrayList deathUploadList = regCompDAO.getCheckListDeathUploadDetails(checkListNo);
				for (int j = 0; j < deathUploadList.size(); j++) {
					ArrayList deathUploadCheckList = new ArrayList();
					ArrayList list2 = (ArrayList) deathUploadList.get(j);
					RegCompCheckerDTO rdto = new RegCompCheckerDTO();
					rdto.setCheckListTxnId(checkListNo);
					rdto.setDeatCertContents(rdto.getDeatCertContents());
					rdto.setUpldDeathCert((String) list2.get(0));
					rdto.setReltnProofContents(rdto.getReltnProofContents());
					rdto.setUplaReltnProof((String) list2.get(1));
					String comments = (String) list2.get(2);
					if (comments == null || comments.equals(""))
						rdto.setComments("-");
					else
						rdto.setComments(comments);
					rdto.setFilePath((String) list2.get(4));
					rdto.setFilePathRltn((String) list2.get(5));
					rdto.setCheck("N");
					//rdto.setPartyTxnId((String)list2.get(3));
					//rdto.setDocAftrDeathFlg((String)list1.get(7));
					deathUploadCheckList.add(rdto);
					uploadDeathCert.put((String) list2.get(3), deathUploadCheckList);
				}
				eForm.setDeathCertChk("Y");
			}
			rDTO.setDocAftrDeathFlg(deathUploadFlg);
			//rDTO.setDeathCert((String)list1.get(8));
			//rDTO.setDeathCert("abc.jpg");
			//rDTO.setRelationProof((String)list1.get(9));
			//rDTO.setRelationProof("def.jpg");
			//rDTO.setComments((String)list1.get(10));
			String poaUploadFlag = (String) list1.get(8);
			poaUploadFlag = poaUploadFlag == null ? "N" : poaUploadFlag;
			if ((poaUploadFlag).equalsIgnoreCase("Y")) {
				int count = 1;
				ArrayList poaUploadList = regCompDAO.getCheckListPOADetails(checkListNo);
				for (int j = 0; j < poaUploadList.size(); j++) {
					ArrayList poaChecklist = new ArrayList();
					ArrayList list2 = (ArrayList) poaUploadList.get(j);
					RegCompCheckerDTO rdto = new RegCompCheckerDTO();
					rdto.setCheckListTxnId(checkListNo);
					rdto.setUploadPoaDoc((String) list2.get(0));
					rdto.setPoaMpFlg((String) list2.get(1));
					rdto.setPoaAuthNo((String) list2.get(2));
					//rdto.setPoaId((String)list2.get(3));
					//rdto.setPoaFlg((String)list1.get(8));
					rdto.setFilePathPOA((String) list2.get(4));
					rdto.setPoaUploadContents(rdto.getPoaUploadContents());
					rdto.setPoaUploadSize(rdto.getPoaUploadSize());
					rdto.setCheck("N");
					poaChecklist.add(rdto);
					uploadPOA.put((String) list2.get(2), poaChecklist);
				}
				eForm.setPoaChk("Y");
			}
			rDTO.setPoaFlg(poaUploadFlag);
			//rDTO.setPoaMpFlg((String)list1.get(12));
			//rDTO.setPoaAuthNo((String)list1.get(13));
			checkList.add(rDTO);
			//			logger.debug("size of map 1"+uploadDeathCert.size());
			//		logger.debug("size of map 2"+uploadPOA.size());
			logger.debug("size of list" + checkList.size());
			eForm.setUploadDthList(uploadDeathCert);
			eForm.setUploadPOAList(uploadPOA);
		}
		return checkList;
	}

	/**
	 * @param regNum
	 * @param partyDetails
	 * @return boolean
	 * @throws Exception
	 */
	public boolean modifyPartiesPresentDetails(String regNum, ArrayList partyDetails, Map govtOffLetterMap, String userID, String date) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.modifyPartiesPresentDetails(regNum, partyDetails, govtOffLetterMap, userID, date);
	}

	/**
	 * @param rDTO
	 * @return boolean
	 */
	public boolean modifyCheckListDetails(RegCompCheckerDTO rDTO, String userId, String cdate, Map dthCertList, Map poaList) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		boolean flag1 = false;
		boolean flag2 = false;
		if (dthCertList.size() > 0) {
			rDTO.setDocAftrDeathFlg("Y");
		}
		if (poaList.size() > 0) {
			rDTO.setPoaFlag("Y");
		}
		boolean flag = regCompDAO.checkListData(rDTO.getRegInitNumber(), userId, cdate);
		String num = regCompDAO.saveChecklist(rDTO, cdate, userId);
		//		logger.debug("num"+num);
		if (dthCertList.size() != 0) {
			flag1 = regCompDAO.saveDthCertDetails(rDTO, cdate, userId, num, dthCertList);
		}
		if (poaList.size() != 0) {
			flag2 = regCompDAO.savePOADetails(rDTO, cdate, userId, num, poaList);
		}
		logger.debug("BD------->DEATH CERT DETAILS" + flag1);
		logger.debug("BD------->POA DETAILS" + flag2);
		if ("".equalsIgnoreCase(num) != true) {
			flag2 = true;
		}
		return flag2;
	}

	/**
	 * @param regNumber
	 * @param valueOf
	 * @param userId
	 * @param cdate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertPaymentDetails(String regNumber, String valueOf, String userId, String cdate, String tableName, String sourceMod, String pmtType, RegCompCheckerDTO rDTO) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.insertPaymentDetails(regNumber, valueOf, userId, cdate, tableName, sourceMod, pmtType, rDTO);
	}

	/**
	 * @param pkey
	 * @param userId
	 * @param cdate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updtStatus(String pkey, String userId, String cdate, String sourceMod, String pmntFlag) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.updtStatus(pkey, userId, cdate, sourceMod, pmntFlag);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getBookDetails() throws Exception {
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList bookDetails = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.getBookDetails();
		for (int i = 0; i < list.size(); i++) {
			list1 = (ArrayList) list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setBookId((String) list1.get(0));
			rDTO.setBookName((String) list1.get(1));
			bookDetails.add(rDTO);
		}
		return bookDetails;
	}

	/**
	 * @param userId
	 * @param cDate
	 * @return String
	 * @throws Exception
	 */
	public boolean updateFinalDetails(String regInitId, String regCompNum, String userId, String cDate, String officeId, String titleCertFlg, String SroName) {
		String officeCode = officeId;
		String bookId = regCompNum.substring(11, 13);
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String districtCode = "";
		try {
			districtCode = regCompDAO.getDistrictCodeChecker(officeCode);
		} catch (Exception e) {}
		String disttId = districtCode;
		try {
			boolean update = regCompDAO.insertFinalDetails(regInitId, regCompNum, userId, cDate, officeCode, titleCertFlg, districtCode, SroName, bookId, disttId);
			if (update) {
				ArrayList linkHistoryList = getLinkHstryDetails(regInitId);
				if (linkHistoryList != null && linkHistoryList.size() > 0) {
					update = upadtePinDetails(linkHistoryList);
				}
				logger.debug("updating status");
			}
			return update;
		} catch (Exception e) {
			return false;
		}
	}

	public String getRegCompletionNumber(String officeId, String userId, String cDate, String bookId, String regInitId, String titleCertFlg, String SroName, String deedId, String instId) throws Exception {
		String officeCode = officeId;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String districtCode = regCompDAO.getDistrictCodeChecker(officeCode);
		String disttId = districtCode;
		//String bookId = regCompDAO.getBookId(deedId);
		boolean flag = seqChk(cDate);
		String seqNum = regCompDAO.getSeqNum(flag);
		logger.debug("^^^seq^^" + seqNum);
		if (seqNum.length() == 1) {
			seqNum = "00000" + seqNum;
		} else if (seqNum.length() == 2) {
			seqNum = "0000" + seqNum;
		} else if (seqNum.length() == 3) {
			seqNum = "000" + seqNum;
		} else if (seqNum.length() == 4) {
			seqNum = "00" + seqNum;
		} else if (seqNum.length() == 5) {
			seqNum = "0" + seqNum;
		}
		//String offCodeArr[] = officeCode.split("_");
		String offCode = officeCode.substring(3);
		int lenDistt = districtCode.length();
		int lengthOffice = offCode.length();
		if (lenDistt != 2) {
			if (lenDistt < 2) {
				districtCode = "0" + districtCode;
			}
			if (lenDistt > 2) {
				districtCode = districtCode.substring(lenDistt - 2, lenDistt);
			}
		}
		if (lengthOffice != 3) {
			if (lengthOffice < 3) {
				for (int i = lengthOffice; i < 3; i++) {
					offCode = "0" + offCode;
				}
			}
			if (lengthOffice > 2) {
				offCode = offCode.substring(lengthOffice - 2, lengthOffice);
			}
		}
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		String currYear = year.toString();
		//	logger.debug("District Code"+districtCode);
		//	logger.debug("Office Code"+offCode);
		//logger.debug("Current Year"+currYear);
		//		logger.debug("Seq Num"+seqNum);
		String regCompNum = "MP" + districtCode + offCode + currYear + bookId + seqNum;
		//		logger.debug("RegCompNumber"+regCompNum);
		boolean insert = regCompDAO.updateRegCompNumber(regInitId, regCompNum);//regCompDAO.insertFinalDetails(regInitId, regCompNum, userId,cDate,officeCode, titleCertFlg,districtCode,SroName,bookId,disttId );
		if (insert) {
			String compStatus = "";
			/*if((deedId.equals(RegCompCheckerConstant.CERTIFICATION_OF_SALE)&& instId.equals(RegCompCheckerConstant.SALE_DEED_INST_1))||
					(deedId.equals(RegCompCheckerConstant.CONVEYANCE_DEED) && (instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_1) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_2) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_3) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_4) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_5))) ||
					(deedId.equals(RegCompCheckerConstant.EXCHANGE_DEED) && instId.equals(RegCompCheckerConstant.EXCAHNGE_DEED_INST_1)) ||
					(deedId.equals(RegCompCheckerConstant.GIFT_DEED) && instId.equals(RegCompCheckerConstant.GIFT_DEED_INST_1)) ||
					(deedId.equals(RegCompCheckerConstant.LEASE_DEED) && (instId.equals(RegCompCheckerConstant.LEASE_DEED_INST_1) )) ||
					(deedId.equals(RegCompCheckerConstant.RELEASE_DEED) && instId.equals(RegCompCheckerConstant.RELAESE_DEED_INST_1)) ||
					(deedId.equals(RegCompCheckerConstant.PARTITION_DEED) && (instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_1) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_2) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_3))))
			{
				compStatus = "19";
			}
			else
			{
				compStatus = "17";
			}*/
			compStatus = "47";
			boolean update = regCompDAO.updateFinalStatus(regInitId, userId, cDate, compStatus);
			/*if(update)
			{
				ArrayList linkHistoryList = getLinkHstryDetails(regInitId);
				if(linkHistoryList!=null&&linkHistoryList.size() > 0 )
				{
					update = upadtePinDetails(linkHistoryList);
				}
				
				logger.debug("updating status");
			}*/
			//else
			//{
			//	logger.debug("UPDATING STATUS FAILED");
			//}
		} else {
			logger.debug("INSERTING FINAL RECORDS FAILED");
		}
		return regCompNum;
	}

	public boolean isLinking(String regInit) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.isLinking2(regInit);
	}

	public ArrayList getFinalDutiesForPayment(String regInitId, RegCompCheckerForm eForm) throws Exception {
		/*ArrayList netBalance = new ArrayList();
		 ArrayList list = new ArrayList();
		 String dutyInit = null;
		// ArrayList list1 = new ArrayList();
		/// ArrayList dutyList = new ArrayList();
		 ArrayList dutyAtRegInit = new ArrayList();
		 RegCompCheckerDTO duties;
		 double total= 0;
		 double netBal = 0;
		 double pAmt = 0;
		 double stmpDutyChkr = 0;
		 double regFeeChkr = 0;
		 double stampdutyInit = 0;
		 double regFee = 0;
		 double stmpDuty = 0;
		 double regFeeBal = 0;
		double stampDutyBal=0;
		 HashMap dutyDetails = new HashMap();
		 RegCompCheckerDTO rgDTO = new RegCompCheckerDTO();
		 
		 
			 dutyInit = regCompDAO.getDutiesRegInit(regInitId);
			 if(dutyInit.length()==0)
			 {
				 dutyInit = "0";
				 rgDTO.setDutyAtRegInit("0");
				
				 stampdutyInit = 0;
			 }
			 else
			 {
				 rgDTO.setDutyAtRegInit(dutyInit);
				
				 stampdutyInit = (Double.parseDouble(dutyInit));
			 }
			 
			
			 rgDTO.setDutyAtRegInit(dutyInit);
			 rgDTO.setRegFeeAtRegInit("0");
			 
			 
			 String paidAmt = regCompDAO.getPaidDutiesduringMaker(regInitId);
			 if(paidAmt.length()==0)
			 {
				 paidAmt = "0";
				 rgDTO.setDutyAtRegMkr("0");
				rgDTO.setRegFeeAtRegMkr("0");
				 pAmt = 0;
			 }
			 else
			 {
				 rgDTO.setDutyAtRegMkr(paidAmt);
				 rgDTO.setRegFeeAtRegMkr("0");
				 pAmt = (Integer.parseInt(paidAmt));
			 }
			 
			 ArrayList amtPaidAtChecker = new ArrayList();
			 if(eForm.getRegDTO().isAdj())
			 {
				 amtPaidAtChecker = regCompDAO.getLinkingDutiesAdj(eForm.getRegDTO().getAdjNumber());
			 }
			 else
			 {
				amtPaidAtChecker = regCompDAO.getLinkingDutiesChecker(regInitId);
			 }
			 for(int k = 0; k<amtPaidAtChecker.size();k++)
			 {
				 ArrayList list2 = new ArrayList();
				 list2 = (ArrayList)amtPaidAtChecker.get(k);
				 regFee = Double.parseDouble((String)list2.get(1));
				 stmpDuty = Double.parseDouble((String)list2.get(0));
			 }
			 
			//rgDTO.setDutyAtRegChkr((String)amtPaidAtChecker.get(0));
			//rgDTO.setRegFeeAtRegChkr((String)amtPaidAtChecker.get(1));
			 ArrayList list7 = regCompDAO.getLinkingDutiesAlreadyPaid(regInitId);
			 list = regCompDAO.getFinalLinkingDuties(regInitId); 
			 for (int i = 0; i < list.size(); i++) {
				 ArrayList list1 = new ArrayList();
				 ArrayList dutyList = new ArrayList();
					list1 = (ArrayList)list.get(i);
					duties = new RegCompCheckerDTO();
					
					for(int l = 0; l<list7.size();l++)
					{
						ArrayList list8 = (ArrayList)list7.get(l);
						if((String)list8.get(0)!="")
						{
							duties.setRegistrationFeeAlreadyPaid((String)list8.get(0));
						}
						else
						{
							duties.setRegistrationFeeAlreadyPaid("0");
						}
						if((String)list8.get(1)!="")
						{
							duties.setStampdutyAlreadyPaid((String)list8.get(1));
						}
						else
						{
							duties.setStampdutyAlreadyPaid("0");
						}
						
					}*/
		/*if((String)list1.get(2)!="")
		{
			duties.setRegistrationFeeAlreadyPaid((String)list1.get(2));
		}
		else
		{
			duties.setRegistrationFeeAlreadyPaid("0");
		}
		if((String)list1.get(3)!="")
		{
			duties.setStampdutyAlreadyPaid((String)list1.get(3));
		}
		else
		{
			duties.setStampdutyAlreadyPaid("0");
		}*/
		/*if((String)list1.get(2)!="")
		{
			duties.setRegistrationFeeToBePaid((String)list1.get(2));
		}
		else
		{
			duties.setRegistrationFeeToBePaid("0");
		}
		if((String)list1.get(3)!="")
		{
			duties.setStampdutyToBePaid((String)list1.get(3));
		}
		else
		{
			duties.setStampdutyToBePaid("0");
		}
		
		
		
		
		//duties.setRegistrationFee((String)list1.get(4));
		//duties.setStampduty1((String)list1.get(5));
		regFeeBal = Double.parseDouble(list1.get(4).toString());
		 stampDutyBal = Double.parseDouble(list1.get(5).toString());
		
		//dmFt.format(regFeeBal);
		//dmFt.format(stampDutyBal);
		//dmFt.format(regFee);
		//dmFt.format(stmpDuty);
		//dmFt.format(stampdutyInit);
		//dmFt.format(pAmt);
		
		
		//if(regFeeChkr < 0)
			//regFeeChkr = 0;
		//if(stmpD)
		//total = b1+b2;
		//netBal = total-pAmt;
		dutyList.add(duties);
		Iterator itr = dutyList.iterator();
		
		dutyDetails.put((String)list1.get(0)+"_"+(String)list1.get(1)+"_"+(String)list1.get(4)+"_"+(String)list1.get(5), dutyList);
		//dutyDetails.put((String)list1.get(0)+"_"+(String)list1.get(1), dutyList);
		}
		DecimalFormat dmFt = new DecimalFormat("#.##");
		regFeeChkr = regFee+regFeeBal;
		stmpDutyChkr = stmpDuty+stampDutyBal-stampdutyInit-pAmt;
		regFeeChkr = Double.valueOf(dmFt.format(regFeeChkr));
		stmpDutyChkr =Double.valueOf(dmFt.format(stmpDutyChkr));
		rgDTO.setDutyAtRegChkr(((Double)stmpDutyChkr).toString());
		rgDTO.setRegFeeAtRegChkr(((Double)regFeeChkr).toString());
		
		
		netBalance.add(rgDTO);
		eForm.setLinkingDutiesDisp(netBalance);
		logger.debug("Size of map in bd"+netBalance.size());
		
		
		
		return dutyDetails;*/
		ArrayList list = new ArrayList();
		String dutyInit = null;
		ArrayList dutyList = new ArrayList();
		Double stmpDutyChkr = 0.0;
		Double regFeeChkr = 0.0;
		Double stampdutyInit = 0.0;
		Double regFeeInit = 0.0;
		Double stmpDutyMkr = 0.0;
		Double regFeeMkr = 0.0;
		Double stampDutyAlreadyPaid = 0.0;
		Double regFeeAlreadyPaid = 0.0;
		Double stampDutyBal = 0.0;
		Double regFeeBal = 0.0;
		Double totalDuty;
		Double totalRegFee;
		Double totalStampDuty = 0.0;
		RegCompCheckerDTO rgDTO = new RegCompCheckerDTO();
		DecimalFormat dmFt = new DecimalFormat("#.##");
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		//***********************************AMOUNT PAID DURING REG-INITIATION *******************//
		stampdutyInit = regCompDAO.getDutiesRegInit(regInitId);
		stampdutyInit = Double.valueOf(dmFt.format(stampdutyInit));//paid amount at initiation
		rgDTO.setDutyAtRegInit(stampdutyInit.toString());
		regFeeInit = regCompDAO.getRegFeeRegInit(regInitId);
		regFeeInit = Double.valueOf(dmFt.format(regFeeInit));
		rgDTO.setRegFeeAtRegInit(regFeeInit.toString());
		//***********************************AMOUNT PAID DURING REG-INITIATION *******************//
		//****************************(REBATE)ALREADY PAID DUTIES AT REG-INITIATION***********************//	
		ArrayList list7 = regCompDAO.getLinkingDutiesAlreadyPaid(regInitId);// already Paid amts at initiation
		for (int l = 0; l < list7.size(); l++) {
			ArrayList list8 = (ArrayList) list7.get(l);
			String regFee = (String) list8.get(0);
			regFee = regFee == null ? "" : regFee;
			if ("".equalsIgnoreCase(regFee) != true) {
				rgDTO.setRegistrationFeeAlreadyPaid((String) list8.get(0));
				regFeeAlreadyPaid = Double.parseDouble((String) list8.get(0));
			} else {
				rgDTO.setRegistrationFeeAlreadyPaid("0");
			}
			String stampDuty = (String) list8.get(1);
			stampDuty = stampDuty == null ? "" : stampDuty;
			if ("".equalsIgnoreCase(stampDuty) != true) {
				rgDTO.setStampdutyAlreadyPaid((String) list8.get(1));
				stampDutyAlreadyPaid = Double.parseDouble((String) list8.get(1));
			} else {
				rgDTO.setStampdutyAlreadyPaid("0");
			}
		}
		//****************************(REBATE)ALRAEDY PAID DUTIES AT REG-INITIATION***********************//	
		//****************************DUTIES AT REG INITIATION*****************************************//
		ArrayList regInit = regCompDAO.getDutyDetls(regInitId);
		for (int i = 0; i < regInit.size(); i++) {
			ArrayList list11 = (ArrayList) regInit.get(i);
			rgDTO.setTotalduty((String) list11.get(5));
			rgDTO.setRegistrationFee((String) list11.get(4));
		}
		//**********************************************************************************************//
		//*******************Duties Stored in REGISTRATION COMPLETION TABLE AFTER FIRST SCREEN**********//
		ArrayList regComp = regCompDAO.getLinkingDutiesChecker(regInitId);
		for (int k = 0; k < regComp.size(); k++) {
			ArrayList list12 = (ArrayList) regComp.get(k);
			stmpDutyChkr = Double.parseDouble((String) list12.get(5));
			stmpDutyChkr = Double.valueOf(dmFt.format(stmpDutyChkr));
			regFeeChkr = Double.parseDouble((String) list12.get(4));
			regFeeChkr = Double.valueOf(dmFt.format(regFeeChkr));
			totalStampDuty = Double.parseDouble((String) list12.get(0));
			rgDTO.setTotaldutyM((String) list12.get(5));
			rgDTO.setRegistrationFeeM((String) list12.get(4));
		}
		//********************************************************************************************//
		//********************REBATE VERIFIED DURING MAKER---FROM CHECKER'S TABLE*******************************************//
		ArrayList regCompMkr = new ArrayList();
		if ("Y".equalsIgnoreCase(getDutyUpdateFlag(regInitId))) {} else {
			regCompMkr = regCompDAO.getLinkDutiesAtChecker(regInitId);
		}
		for (int j = 0; j < regCompMkr.size(); j++) {
			ArrayList list13 = (ArrayList) regCompMkr.get(j);
			RegCompCheckerDTO rcDTO = new RegCompCheckerDTO();
			rcDTO.setRegFeeAtRegMkr((String) list13.get(1));
			rcDTO.setStampDutyBalMkr((String) list13.get(0));
			rcDTO.setPropertyId((String) list13.get(2));
			rcDTO.setRegInitNumber((String) list13.get(3));
			stmpDutyMkr = stmpDutyMkr + Double.parseDouble((String) list13.get(0));
			stmpDutyMkr = Double.valueOf(dmFt.format(stmpDutyMkr));
			regFeeMkr = regFeeMkr + Double.parseDouble((String) list13.get(1));
			regFeeMkr = Double.valueOf(dmFt.format(regFeeMkr));
			dutyList.add(rcDTO);
		}
		//*****************************************************************************************//
		//***********************************BALANCE*******************************************//
		stampDutyBal = stmpDutyChkr + stampDutyAlreadyPaid - stmpDutyMkr - stampdutyInit;
		stampDutyBal = Double.valueOf(dmFt.format(stampDutyBal));
		regFeeBal = regFeeChkr + regFeeAlreadyPaid - regFeeMkr - regFeeInit;
		regFeeBal = Double.valueOf(regFeeBal);
		if (stampDutyBal < 0) {//by roopam- 22 may 2015 - negative amount changed to zero 
			stampDutyBal = 0.0;
		}
		if (regFeeBal < 0) {//by roopam- 22 may 2015 - negative amount changed to zero 
			regFeeBal = 0.0;
		}
		rgDTO.setStampDutyPmt(BigDecimal.valueOf(stampDutyBal).toPlainString());
		rgDTO.setRegFeePmt(BigDecimal.valueOf(regFeeBal).toPlainString());
		totalDuty = stmpDutyChkr + stampDutyAlreadyPaid - stmpDutyMkr;
		totalRegFee = regFeeChkr + regFeeAlreadyPaid - regFeeMkr;
		totalStampDuty = totalStampDuty + stampDutyAlreadyPaid - stmpDutyMkr;
		eForm.setFinalDuty(totalDuty);
		eForm.setFinalRegFee(totalRegFee);
		eForm.setFinalStampDuty(totalStampDuty);
		//***********************************BALANCE********************************************//
		ArrayList singleAmnts = new ArrayList();
		singleAmnts.add(rgDTO);
		eForm.setLinkingDutiesDisp(singleAmnts);
		logger.debug("Size of map in bd" + dutyList.size());
		return dutyList;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getHoldData() throws Exception {
		ArrayList holdList = new ArrayList();
		ArrayList list = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.holdDetails();
		for (int i = 0; i < list.size(); i++) {
			ArrayList list1 = (ArrayList) list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setHoldId((String) list1.get(0));
			rDTO.setHoldName((String) list1.get(1));
			holdList.add(rDTO);
		}
		return holdList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getofficialList() throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getofficialList();
	}

	/**
	 * @param regInitId
	 * @return
	 */
	public ArrayList getHoldDetails(String regInitId) throws Exception {
		ArrayList holdList = new ArrayList();
		ArrayList list = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.getHoldDetails(regInitId);
		for (int i = 0; i < list.size(); i++) {
			ArrayList list1 = (ArrayList) list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			//rDTO.setHoldFlag((String)list1.get(0));  //commented by SIMRAN 
			rDTO.setHoldName((String) list1.get(0));
			holdList.add(rDTO);
		}
		return holdList;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public boolean checkStatus(String regInitId) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String status = regCompDAO.checkStatus(regInitId);
		if (status.equalsIgnoreCase("17")) {
			flag = true;
		}
		return flag;
	}

	public boolean checkEtokenFlag(String officeId) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String etokenflag = regCompDAO.checkEtokenFlag(officeId);
		if (etokenflag.equalsIgnoreCase("Y")) {
			flag = true;
		}
		return flag;
	}

	public String checkEtokenUserId(String regId, String userId) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String counterUserId = regCompDAO.checkEtokenUserId(regId, userId);
		return counterUserId;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public String getCompletionNumber(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getCompletionNumber(regInitId);
	}

	/**
	 * @param Comp
	 * @return String
	 * @throws Exception
	 */
	public String getInitiationNumber(String Comp) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getInitiationNumber(Comp);
	}

	/**
	 * @param regInitId
	 * @param witnessTxnId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCompleteWitnessDetails(String regInitId, String witnessTxnId) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList witnessList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.getCompleteWitnessDetails(regInitId, witnessTxnId);
		for (int i = 0; i < list.size(); i++) {
			ArrayList list1 = (ArrayList) list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setWitFirstName((String) list1.get(0));
			rDTO.setWitMiddleName((String) list1.get(1));
			rDTO.setWitLastName((String) list1.get(2));
			rDTO.setWitGender((String) list1.get(3));
			rDTO.setWitAge((String) list1.get(4));
			rDTO.setWitFatherName((String) list1.get(5));
			rDTO.setWitMotherName((String) list1.get(6));
			rDTO.setWitSpouseName((String) list1.get(7));
			rDTO.setWitNationality((String) list1.get(8));
			rDTO.setWitAddress((String) list1.get(9));
			rDTO.setWitPhoneNumber((String) list1.get(10));
			rDTO.setRelationshipWit((String) list1.get(11));
			//rDTO.setWitLoanInfo((String)list1.get(11));
			//rDTO.setWitPendingTaxDuties((String)list1.get(12));
			witnessList.add(rDTO);
		}
		return witnessList;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLinkDutiesAtMaker(String regInitId, RegCompCheckerDTO gdto) throws Exception {
		ArrayList list1 = new ArrayList();
		ArrayList list3 = new ArrayList();
		ArrayList list4 = new ArrayList();
		ArrayList finalList = new ArrayList();
		Double stampDutyAtMkr = 0.0;
		Double RegFeeAtMkr = 0.0;
		Double stampDutyAlreadyPaid = 0.0;
		Double RegFeeAlreadyPaid = 0.0;
		Double stampDutyBalAtMkr = 0.0;
		Double RegFeeBalAtMkr = 0.0;
		Double stampDutyFinal = 0.0;
		Double regFeeFinal = 0.0;
		Double totalFinal = 0.0;
		/****************** Getting Duties Stored During Linking *************************/
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list1 = regCompDAO.getLinkDutiesAtMaker(regInitId);
		if (list1.size() > 0) //if there is some data in linking table then only we proceed for calculations else empty list will be returned
		{
			for (int i = 0; i < list1.size(); i++) {
				ArrayList list2 = (ArrayList) list1.get(i);
				String stampDuty = (String) list2.get(0);
				stampDuty = stampDuty == null ? "" : stampDuty;
				if ("".equalsIgnoreCase(stampDuty) != true) {
					stampDutyAtMkr = stampDutyAtMkr + Double.parseDouble((String) list2.get(0)); // sum of all stamp duties Linked in LINKING TABLE OF MAKER
				}
				String regFee = (String) list2.get(1);
				regFee = regFee == null ? "" : regFee;
				if ("".equalsIgnoreCase(regFee) != true) {
					RegFeeAtMkr = RegFeeAtMkr + Double.parseDouble((String) list2.get(1)); //sum of all reg Fee Linked in LINKING TABLE OF MAKER
				}
			}
			/******************************************************************/
			/********************* ALREADY PAID DUTIES **************************/
			list3 = regCompDAO.getLinkingDutiesAlreadyPaid(regInitId);
			for (int j = 0; j < list3.size(); j++) {
				ArrayList list2 = (ArrayList) list3.get(j);
				String stampDuty = (String) list2.get(1);
				stampDuty = stampDuty == null ? "" : stampDuty;
				if ("".equalsIgnoreCase(stampDuty) != true) {
					stampDutyAlreadyPaid = Double.parseDouble((String) list2.get(1));
				}
				String regFee = (String) list2.get(0);
				regFee = regFee == null ? "" : regFee;
				if ("".equalsIgnoreCase(regFee) != true) {
					RegFeeAlreadyPaid = Double.parseDouble((String) list2.get(0));
				}
			}
			/*************************** BALANCE ********************************************/
			stampDutyBalAtMkr = stampDutyAlreadyPaid - stampDutyAtMkr;
			gdto.setStampDutyBalMkr(stampDutyBalAtMkr.toString());
			RegFeeBalAtMkr = RegFeeAlreadyPaid - RegFeeAtMkr;
			gdto.setRegFeeDutyBalMkr(RegFeeBalAtMkr.toString());
			/*************************** BALANCE ********************************************/
			/******************************** DUTIES IN REG INIT TABLE *******************************************/
			list4 = regCompDAO.getDutyDetls(regInitId);
			for (int k = 0; k < list4.size(); k++) {
				ArrayList list2 = (ArrayList) list4.get(k);
				if (stampDutyBalAtMkr > 0 || RegFeeBalAtMkr > 0) //if there is some balance than only we need to change duties
				{
					stampDutyFinal = Double.parseDouble((String) list2.get(0)) + stampDutyBalAtMkr;
					regFeeFinal = Double.parseDouble((String) list2.get(4)) + RegFeeBalAtMkr;
					totalFinal = Double.parseDouble((String) list2.get(5)) + stampDutyBalAtMkr;
					RegCompCheckerDTO rdto = new RegCompCheckerDTO();
					rdto.setStampduty1(BigDecimal.valueOf(stampDutyFinal).toPlainString());
					rdto.setPanchayatDuty((String) list2.get(1));
					rdto.setNagarPalikaDuty((String) list2.get(2));
					rdto.setUpkarDuty((String) list2.get(3));
					rdto.setRegistrationFee(BigDecimal.valueOf(regFeeFinal).toPlainString());
					rdto.setTotalduty(BigDecimal.valueOf(totalFinal).toPlainString());
					rdto.setStampdutyAlreadyPaid(BigDecimal.valueOf(stampDutyAtMkr).toPlainString());
					rdto.setRegistrationFeeAlreadyPaid(BigDecimal.valueOf(RegFeeAtMkr).toPlainString());
					finalList.add(rdto);
				} else {
					RegCompCheckerDTO rdto = new RegCompCheckerDTO();
					rdto.setStampduty1((String) list2.get(0));
					rdto.setPanchayatDuty((String) list2.get(1));
					rdto.setNagarPalikaDuty((String) list2.get(2));
					rdto.setUpkarDuty((String) list2.get(3));
					rdto.setRegistrationFee((String) list2.get(4));
					rdto.setTotalduty((String) list2.get(5));
					rdto.setStampdutyAlreadyPaid("0");
					rdto.setRegistrationFeeAlreadyPaid("0");
					finalList.add(rdto);
				}
			}
		}
		return finalList;
	}

	public ArrayList getLinkingDutiesAlreadyPaid(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getLinkingDutiesAlreadyPaid(regInitId);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAlreadyPaidDuties(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getLinkingDutiesAlreadyPaid(regInitId);
		double regduty = 0.0;
		double regfee = 0.0;
		for (int i = 0; i < list.size(); i++) {
			ArrayList list2 = (ArrayList) list.get(i);
			String regDuty = (String) list2.get(0);
			String regFee = (String) list2.get(1);
			regDuty = regDuty == null ? "" : regDuty;
			regFee = regFee == null ? "" : regFee;
			if (regDuty == "") {
				regduty = 0.0;
			} else {
				regduty = Double.parseDouble((String) list2.get(0));
			}
			if (regFee == "") {
				regfee = 0.0;
			} else {
				regfee = Double.parseDouble((String) list2.get(1));
			}
			if (regduty == 0.0 && regfee == 0.0) {
				list = new ArrayList();
			}
		}
		return list;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getTransPartyDets(String regInitId) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList partyDetlsList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.getTransPartyDets(regInitId);
		for (int i = 0; i < list.size(); i++) {
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			ArrayList subList = (ArrayList) list.get(i);
			String fName = (String) subList.get(0);
			fName = fName == null ? "" : fName;
			if (!(fName).equalsIgnoreCase("")) {
				rDTO.setPartyFirstName((String) subList.get(0));
			} else {
				rDTO.setPartyFirstName((String) subList.get(1));
			}
			partyDetlsList.add(rDTO);
		}
		return partyDetlsList;
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getTransPartyIds(String regInitId, String partyName) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList partyIdsList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.getTransPartyIds(regInitId, partyName);
		for (int i = 0; i < list.size(); i++) {
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			ArrayList subList = (ArrayList) list.get(i);
			rDTO.setPartyTxnId((String) subList.get(0));
			partyIdsList.add(rDTO);
		}
		return partyIdsList;
	}

	/**
	 * @param poaAuthNo
	 * @param regInit
	 * @return
	 * @throws Exception
	 */
	public boolean checkPOA(String poaAuthNo, String regInit) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String regNo = regCompDAO.checkPOA(poaAuthNo);
		if (regNo.equalsIgnoreCase(regInit)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @param rDTO
	 * @param witnessTxnId
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateAndSaveWitnessDetails(RegCompCheckerDTO rDTO, String witnessTxnId, String date, String userId) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		boolean flag1 = regCompDAO.updateWitnessStatus(witnessTxnId, date, userId);
		logger.debug("update flag" + flag1);
		if (flag1) {
			flag = regCompDAO.saveWitnessDetails(rDTO, date, userId);
		}
		return flag;
	}

	/**
	 * @param rDTO
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveWitnessDetails(RegCompCheckerDTO rDTO, String date, String userId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.saveWitnessDetails(rDTO, date, userId);
	}

	/**
	 * @param witnessTxnId
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateWitnessDetails(String witnessTxnId, String date, String userId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.updateWitnessStatus(witnessTxnId, date, userId);
	}

	/**
	 * @param rdto
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateCaseDetails(RegCompCheckerDTO rdto, String date, String userId, String officeId, String reasonEnglish, String reasonHindi) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String mailContent = getEmailHoldContent(rdto.getRegInitNumber(), rdto.getRemarks(), officeId, "en", officeId, reasonEnglish, reasonHindi);
		String smsContent = getSMSContent(rdto.getRegInitNumber(), "Y");
		regCompDAO.sendMail(rdto.getRegInitNumber(), mailContent, userId);
		regCompDAO.sendSMS(rdto.getRegInitNumber(), smsContent, userId);
		return regCompDAO.updateCaseDetails(rdto, date, userId);
	}

	public boolean updateRegSR(String regId, String usrName, String usrOffc) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.updateRegSR(regId, usrName, usrOffc);
	}

	/**
	 * @param regInitId
	 * @return
	 */
	public String getImpundDetails(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getImpundDetails(regInitId);
	}

	/**
	 * @param rdto
	 * @param cdate
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean modifyImpoundDetails(RegCompCheckerDTO rdto, String cdate, String userId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String pmtDetlsArr[] = { RegCompCheckerConstant.FILE_NAME_PMT_RCPT, rdto.getPaymentReceiptPath(), userId, cdate, rdto.getRegInitNumber() };
		return regCompDAO.modifyImpoundDetails(pmtDetlsArr);
	}

	/**
	 * @param regInitNo
	 * @return String
	 * @throws Exception
	 */
	public ArrayList checksBeforeCompletion(String regInitNo, RegCompCheckerDTO rDTO) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = new ArrayList();
		ArrayList regList = new ArrayList();
		String check = "";
		rDTO.setCompChcek("N");
		regList = regCompDAO.getLinkdRegNumber(regInitNo);
		if (regList.size() > 0) {
			list = regCompDAO.getCaveatDet(regInitNo, regList);
			if (list.size() > 0) {
				rDTO.setCompChcek("C");
			}
		}
		if (rDTO.getCompChcek().equals("N")) {
			list = regCompDAO.getPropLockDetails(regInitNo);
			if (list.size() > 0) {
				rDTO.setCompChcek("L");
			}
			if (regList.size() > 0) {
				if (rDTO.getCompChcek().equals("N")) {
					list = regCompDAO.getCaseOpenDetails(regInitNo, regList);
					if (list.size() > 0) {
						rDTO.setCompChcek("CS");
					}
				}
			}
		}
		return list;
	}

	/**
	 * @param appId
	 * @param sourceMod
	 * @param pmt_type
	 * @return
	 * @throws Exception
	 */
	public String[] getPaymentFlag(String appId, String sourceMod, String pmt_type) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getPaymentFlag(appId, sourceMod, pmt_type);
		//String arr[]=new String[list.size()];
		if (list.size() > 0) {
			String arr[] = new String[list.size()];
			String row = list.toString();
			row = row.substring(2, (row.length() - 2));
			arr = row.split(",");
			return arr;
		} else {
			return null;
		}
		//return arr;
	}

	/**
	 * @param regInitId
	 * @param sourceMod
	 * @param pmtType
	 * @return Double
	 */
	public Double getPaidAmtChecker(String regInitId, String sourceMod, String pmtType) {
		Double amountPaidAtChecker = 0.0;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getPaidAmtChecker(regInitId, sourceMod, pmtType);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList list2 = (ArrayList) list.get(i);
				if (list2.get(0) != null && !list2.get(0).toString().equalsIgnoreCase("")) {
					amountPaidAtChecker = amountPaidAtChecker + Double.parseDouble((String) list2.get(0));
				}
			}
		}
		//	logger.debug("Total Paid Amount------>"+amountPaidAtChecker);
		return amountPaidAtChecker;
	}

	/**
	 * @param rdto
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	/*public boolean updateTimeBarrdCaseDetails(RegCompCheckerDTO rdto, String date, String userId, String status) throws Exception{
		RegCompCheckerDAO regCompDAO =  new RegCompCheckerDAO();
		return regCompDAO.updateTimeBarrdCaseDetails(rdto, date, userId,status);
	}*/
	public String updateTimeBarrdCaseDetails(String regNumber, String date, String userId, String status) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.updateTimeBarrdCaseDetails(regNumber, date, userId, status);
	}

	/**
	 * @param rdto
	 * @param userId
	 * @param cdate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean modifyTimeBarrdCaseDetails(RegCompCheckerDTO rdto, String userId, String cdate) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String pnaltyReceiptArr[] = { RegCompCheckerConstant.FILE_NAME_PNALTY_RCPT, RegCompCheckerConstant.FILE_UPLOAD_PATH + rdto.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PNALTY_RCPT, userId, cdate, rdto.getRegInitNumber() };
		return regCompDAO.modifyTimeBarrdCaseDetails(pnaltyReceiptArr);
	}

	/**
	 * @param regInitId
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap getPropertyRelatedComplianeList(String regInitId) throws Exception {
		HashMap propertyRelatedComplianceMap = new HashMap();
		ArrayList propertyRelatedComplianceList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		propertyRelatedComplianceList = regCompDAO.getPropertyRelatedComplianeList(regInitId);
		for (int i = 0; i < propertyRelatedComplianceList.size(); i++) {
			ArrayList propertyRelatedComplianceList1 = new ArrayList();
			ArrayList list = (ArrayList) propertyRelatedComplianceList.get(i);
			RegCompCheckerDTO rdto = new RegCompCheckerDTO();
			String propType = (String) list.get(1);
			if (list.get(2) != null && !list.get(2).toString().equalsIgnoreCase("")) {
				rdto.setPropImage1DocumentName(RegCompCheckerConstant.ANGLE_1_NAME);
				rdto.setPropImage1FilePath((String) list.get(2));
			}
			if (list.get(3) != null && !list.get(3).toString().equalsIgnoreCase("")) {
				rdto.setPropImage2DocumentName(RegCompCheckerConstant.ANGLE_2_NAME);
				rdto.setPropImage2FilePath((String) list.get(3));
			}
			if (list.get(4) != null && !list.get(4).toString().equalsIgnoreCase("")) {
				rdto.setPropImage3DocumentName(RegCompCheckerConstant.ANGLE_3_NAME);
				rdto.setPropImage3FilePath((String) list.get(4));
			}
			if (list.get(5) != null && !list.get(5).toString().equalsIgnoreCase("")) {
				rdto.setPropMapDocumentName(RegCompCheckerConstant.MAP_NAME);
				rdto.setPropMapFilePath((String) list.get(5));
			}
			if (propType.equalsIgnoreCase("3")) {
				if (list.get(6) != null && !list.get(6).toString().equalsIgnoreCase("")) {
					rdto.setPropRinDocumentName(RegCompCheckerConstant.RIN_NAME);
					rdto.setPropRinFilePath((String) list.get(6));
				}
				if (list.get(7) != null && !list.get(7).toString().equalsIgnoreCase("")) {
					rdto.setPropKhasraDocumentName(RegCompCheckerConstant.KHASRA_NAME);
					rdto.setPropKhasraFilePath((String) list.get(7));
				}
			}
			rdto.setPropertyTypeId(propType);
			rdto.setIsUpload("");
			propertyRelatedComplianceList1.add(rdto);
			propertyRelatedComplianceMap.put((String) list.get(0), propertyRelatedComplianceList1);
		}
		logger.debug("size of map" + propertyRelatedComplianceMap.size());
		return propertyRelatedComplianceMap;
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public HashMap getPartyRelatedComplianeList(String regInitId, String language) throws Exception {
		HashMap partyRelatedComplianceMap = new HashMap();
		ArrayList partyRelatedComplianceList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		partyRelatedComplianceList = regCompDAO.getPartyRelatedComplianeList(regInitId);
		RegCommonDTO mapDto = new RegCommonDTO();
		RegCommonForm form = new RegCommonForm();
		RegCommonBO commonBO = new RegCommonBO();
		RegCommonBD commonBD = new RegCommonBD();
		String OwnerRolePoA = "";
		String partyId = "";
		String CommonDeedFlag = "";
		CommonDeedFlag = regCompDAO.getCommonFlowFlag(regInitId);
		for (int i = 0; i < partyRelatedComplianceList.size(); i++) {
			ArrayList finalList = new ArrayList();
			ArrayList list = (ArrayList) partyRelatedComplianceList.get(i);
			if (list.get(0) != null) {
				partyId = list.get(0).toString();
			}
			RegCompCheckerDTO rdto = new RegCompCheckerDTO();
			String firstName = (String) list.get(1);
			firstName = firstName == null ? "" : firstName;
			//	logger.debug("PARTY FIRST NAME"+firstName);
			int roleId = Integer.parseInt((String) list.get(3));
			String[] claimantArr = commonBO.getClaimantFlag(Integer.toString(roleId));
			mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
			int ExecutantClaimantfromDB = 0;
			if (list.get(20) != null && !list.get(20).toString().equalsIgnoreCase("")) {
				ExecutantClaimantfromDB = Integer.parseInt((String) list.get(20));
			}
			if (firstName != ("") && firstName != null) {
				rdto.setPartyFirstName((String) list.get(1));
				rdto.setPartyRoleId((String) list.get(3));
				rdto.setRole((String) list.get(4));
				rdto.setCollectorCertDocName(RegCompCheckerConstant.COLLECTOR_CERT);
				rdto.setCollectorCertDocPath((String) list.get(5));
				rdto.setPhotoProofDocName(RegCompCheckerConstant.PHOTO_PROOF);
				rdto.setPhotoProofDocPath((String) list.get(6));
				rdto.setNotAffdExecDocName(RegCompCheckerConstant.NOTARIZED_AFFIDAVIT);
				rdto.setNotAffdExecDocPath((String) list.get(7));
				rdto.setExecPhotoName(RegCompCheckerConstant.EXECUTANT_PHOTO);
				rdto.setExecPhotoPath((String) list.get(8));
				rdto.setNotAffdAttrName(RegCompCheckerConstant.NOTARIZED_AFFIDAVIT_OF_ATTORNEY);
				rdto.setNotAffdAttrPath((String) list.get(9));
				rdto.setAttrPhotoName(RegCompCheckerConstant.ATTORNEY_PHOTOGRAPH);
				rdto.setAttrPhotoPath((String) list.get(10));
				rdto.setClaimntPhotoName(RegCompCheckerConstant.CLAIMANTS_PHOTO);
				rdto.setClaimntPhotoPath((String) list.get(11));
				rdto.setPanForm60Name(RegCompCheckerConstant.PAN_OR_FORM);
				rdto.setPanForm60Path((String) list.get(12));
				rdto.setIsUpload("");
				if (list.get(15) != null && list.get(15).toString().equalsIgnoreCase("2")) {
					if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						//int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
						System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
						form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
						String RoleName = form.getExecutantClaimantName();
						System.out.println("Role Name " + RoleName);
						// to check hindi and english role 
						String HindiEnglishRole[] = RoleName.split(",");
						String hindesc = HindiEnglishRole[0];
						String EngDesc = HindiEnglishRole[1];
						String PoaName = null;
						if (EngDesc.contains("Authenticated")) {
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(Authenticated PoA Holder) ";
							} else {
								PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
							}
						} else {
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(PoA Holder) ";
							} else {
								PoaName = " द्वारा (मुख्‍त्‍यार आम)";
							}
						}
						String OwnerDetail = null;
						String poAOwnerListNew = commonBD.getOwnerDetails(regInitId, partyId, language);
						String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
						OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
						rdto.setPartyFirstName(OwnerRolePoA);
						//sDTO.setPartyLastName("");
						//  i1++;
					}
					// will deed Authenticaed POA finish - Rahul 
					else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						String RoleName = null;
						// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
						mapDto.setRoleName(commonBO.getRoleNameForPOA((String) list.get(3)));
						RoleName = mapDto.getRoleName();
						System.out.println("Role Name " + RoleName);
						// to check hindi and english role 
						String HindiEnglishRole[] = RoleName.split(",");
						String hindesc = HindiEnglishRole[0];
						String EngDesc = HindiEnglishRole[1];
						String PoaName = null;
						if (EngDesc.contains("Authenticated")) {
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(Authenticated PoA Holder) ";
							} else {
								PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
							}
						} else {
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(PoA Holder) ";
							} else {
								PoaName = " द्वारा (मुख्‍त्‍यार आम)";
							}
						}
						System.out.println("POA NAMe is " + PoaName);
						System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
						//  System.out.println("PoAOwnerlistdisplay"+poAOwnerList.get(i));
						String OwnerDetail = null;
						String poAOwnerListNew = commonBD.getOwnerDetails(regInitId, partyId, language);
						String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
						OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
						rdto.setPartyFirstName(OwnerRolePoA);
						// sDTO.setPartyLastName("");
						// i1++;
					} else {
						// Minor name , Guardian set - Rahul
						int age = list.get(16) != null ? Integer.parseInt((String) list.get(16)) : 0;
						if (age < RegCompCheckerConstant.MINOR_AGE_LIMIT) {
							String relations = commonBO.getRelationshipName((list.get(19).toString()), language);
							if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								//sDTO.setPartyFirstName("(Minor ) "+MinorName);
								rdto.setPartyFirstName("(Minor ) " + firstName + " " + (String) list.get(2) + "  " + relations + " " + (String) list.get(17) + " By " + (String) list.get(18));
							} else {
								//	sDTO.setPartyFirstName("(अवयस्‍क ) "+MinorName);
								//sDTO.setPartyLastName((String)subList.get(2)+"  "+relations+" "+(String)subList.get(11)+" द्वारा "+(String)subList.get(13));
								rdto.setPartyFirstName("(अवयस्‍क ) " + firstName + " " + (String) list.get(2) + "  " + relations + " " + (String) list.get(17) + " द्वारा " + (String) list.get(18));
							}
						}
					}
				}
				finalList.add(rdto);
				partyRelatedComplianceMap.put((String) list.get(0), finalList);
			} else {
				if (list.get(15) != null && list.get(15).toString().equalsIgnoreCase("3")) {
					if (list.get(14) != null && !list.get(14).toString().equalsIgnoreCase("")) {
						rdto.setPartyFirstName((String) list.get(14));
					} else {
						rdto.setPartyFirstName("--");
					}
				} else {
					rdto.setPartyFirstName((String) list.get(13));
					if (list.get(15) != null && list.get(15).toString().equalsIgnoreCase("1")) {
						if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6) {
							//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
							System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
							form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
							String RoleName = form.getExecutantClaimantName();
							System.out.println("Role Name " + RoleName);
							String PoaName = null;
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							if (EngDesc.contains("Authenticated")) {
								if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							String OwnerDetail = null;
							String poAOwnerListNew = commonBD.getOwnerDetails(regInitId, partyId, language);
							String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							rdto.setPartyFirstName(OwnerRolePoA);
						} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
							// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							String RoleName = null;
							// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
							mapDto.setRoleName(commonBO.getRoleNameForPOA((String) list.get(3)));
							RoleName = mapDto.getRoleName();
							System.out.println("Role Name " + RoleName);
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = null;
							if (EngDesc.contains("Authenticated")) {
								if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							System.out.println("POA NAMe is " + PoaName);
							System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
							//System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
							String OwnerDetail = null;
							String poAOwnerListNew = commonBD.getOwnerDetails(regInitId, partyId, language);
							String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							rdto.setPartyFirstName(OwnerRolePoA);
						} else {
							rdto.setPartyFirstName((String) list.get(13));
						}
					}
				}
				rdto.setPartyRoleId((String) list.get(3));
				rdto.setRole((String) list.get(4));
				rdto.setCollectorCertDocName(RegCompCheckerConstant.COLLECTOR_CERT);
				rdto.setCollectorCertDocPath((String) list.get(5));
				rdto.setPhotoProofDocName(RegCompCheckerConstant.PHOTO_PROOF);
				rdto.setPhotoProofDocPath((String) list.get(6));
				rdto.setNotAffdExecDocName(RegCompCheckerConstant.NOTARIZED_AFFIDAVIT);
				rdto.setNotAffdExecDocPath((String) list.get(7));
				rdto.setExecPhotoName(RegCompCheckerConstant.EXECUTANT_PHOTO);
				rdto.setExecPhotoPath((String) list.get(8));
				rdto.setNotAffdAttrName(RegCompCheckerConstant.NOTARIZED_AFFIDAVIT_OF_ATTORNEY);
				rdto.setNotAffdAttrPath((String) list.get(9));
				rdto.setAttrPhotoName(RegCompCheckerConstant.ATTORNEY_PHOTOGRAPH);
				rdto.setAttrPhotoPath((String) list.get(10));
				rdto.setClaimntPhotoName(RegCompCheckerConstant.CLAIMANTS_PHOTO);
				rdto.setClaimntPhotoPath((String) list.get(11));
				rdto.setPanForm60Name(RegCompCheckerConstant.PAN_OR_FORM);
				rdto.setPanForm60Path((String) list.get(12));
				rdto.setIsUpload("");
				finalList.add(rdto);
				partyRelatedComplianceMap.put((String) list.get(0), finalList);
			}
		}
		return partyRelatedComplianceMap;
	}

	/**
	 * @param eForm
	 * @param regInitId
	 * @param userId
	 * @param date
	 * @return boolean
	 */
	public boolean modifyComplianceListData(RegCompCheckerForm eForm, String regInitId, String userId, String date) {
		boolean flag1 = false;
		boolean flag2 = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		flag1 = true;//regCompDAO.modifyPropertyRelatedCompliance(eForm.getCompliancePropertyRelatedMap(), regInitId, userId, date);
		flag2 = true;//regCompDAO.modifyPartyRelatedCompliance(eForm.getCompliancePartyRelatedMap(), regInitId, userId, date);
		logger.debug("flag----property compliance" + flag1);
		logger.debug("flag----party compliance" + flag2);
		if (flag1 && flag2) {
			flag1 = true;
		}
		return flag1;
	}

	/**
	 * @param regNumber
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap getWitnessDetailsForCompliance(String regNumber, String language) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getWitnessDet(regNumber);
		ArrayList sublist = new ArrayList();
		HashMap mainMap = new HashMap();
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				sublist = (ArrayList) list.get(i);
				ArrayList mainList = new ArrayList();
				RegCompCheckerDTO dto = new RegCompCheckerDTO();
				dto.setWitnessTxnNummber((String) sublist.get(0));
				dto.setFnameTrns((String) sublist.get(1));
				dto.setLnameTrns((String) sublist.get(2));
				if ("en".equalsIgnoreCase(language)) {
					dto.setRoleName("Witness");
				} else {
					dto.setRoleName("गवाह");
				}
				dto.setWitnessDocContents(dto.getWitnessDocContents());
				dto.setWitnessDocSize("");
				String witPhotoDetls = (String) sublist.get(3);
				witPhotoDetls = witPhotoDetls == null ? "" : witPhotoDetls;
				if (!witPhotoDetls.equals("")) {
					dto.setWitnessDocPath(regCompDAO.getWitnessPhotoDet((String) sublist.get(3)));
					dto.setWitnessDocName("witnessPhotoGraph.jpg");
				} else {
					dto.setWitnessDocPath("");
					dto.setWitnessDocName("");
				}
				mainList.add(dto);
				mainMap.put((String) sublist.get(0), mainList);
			}
			logger.debug("SIZE in BD" + mainMap.size());
		}
		return mainMap;
	}

	/**
	 * @param witnessUploadMap
	 * @param regInitId
	 * @param userId
	 * @param cdate
	 * @return
	 * @throws Exception
	 */
	public boolean addWitnessPhotographDetails(Map witnessUploadMap, String regInitId, String userId, String cdate) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.addWitnessPhotographDetails(witnessUploadMap, regInitId, userId, cdate);
	}

	/**
	 * @param eStampTxnId
	 * @param regInitId
	 * @param userId
	 * @return
	 */
	public boolean insertEstampMappingDetls(String eStampTxnId, String regInitId, String userId, String pdfPath) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String[] param = { eStampTxnId, regInitId, userId, pdfPath };
		return regCompDAO.insertEstampMappingDetls(param);
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getEstampPurposeDetails(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getEstampPurposeDetails(regInitId);
	}

	public String getEstampFlag(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getEstampFlag(regInitId);
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getEstampStatus(String regInitId) throws Exception {
		ArrayList mainList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getEstampStatus(regInitId);
		for (int i = 0; i < list.size(); i++) {
			ArrayList list2 = (ArrayList) list.get(i);
			String status = (String) list2.get(1);
			status = status == null ? "" : status;
			String consmptionStatus = (String) list2.get(2);
			consmptionStatus = consmptionStatus == null ? "" : consmptionStatus;
			if (status.equalsIgnoreCase("D") || status.equalsIgnoreCase("E")) {
				RegCompCheckerDTO rdto = new RegCompCheckerDTO();
				rdto.setEstampTxnId((String) list2.get(0));
				rdto.setEstampStatus(status);
				mainList.add(rdto);
			} else if (consmptionStatus.equalsIgnoreCase("Consumed")) {
				RegCompCheckerDTO rdto = new RegCompCheckerDTO();
				rdto.setEstampTxnId((String) list2.get(0));
				rdto.setEstampStatus(consmptionStatus);
				mainList.add(rdto);
			}
		}
		return mainList;
	}

	/**
	 * @param regInitId
	 * @param status
	 * @return boolean
	 * @throws Exception
	 */
	public boolean UpdateRegistrationCompletionStatus(String regInitId, String status, String date, String userId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.UpdateRegistrationCompletionStatus(regInitId, status, date, userId);
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getCancelledDetails(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getCancelledDetails(regInitId);
	}

	/**
	 * @param regInitId
	 * @param userId
	 * @param date
	 * @return boolean
	 */
	/*public boolean deactivateEstampDetails(String regInitId, String userId, String date)
	{
		return regCompDAO.deactivateEstampDetails(regInitId, userId, date);
	}*/
	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getEstampRefundStatus(String regInitId) throws Exception {
		ArrayList mainList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getEstampRefundStatus(regInitId);
		for (int i = 0; i < list.size(); i++) {
			ArrayList list2 = (ArrayList) list.get(i);
			RegCompCheckerDTO rdto = new RegCompCheckerDTO();
			rdto.setEstampTxnId((String) list2.get(0));
			rdto.setEstampStatus("R");
			mainList.add(rdto);
		}
		return mainList;
	}

	/**
	 * @param regInitId
	 * @param eForm
	 * @param userId
	 * @param tDate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertFinalDuties(String regInitId, RegCompCheckerForm eForm, String userId, String tDate) throws Exception {
		if ("Y".equalsIgnoreCase(getDutyUpdateFlag(regInitId))) {
			return true;
		} else {
			ArrayList finalDuties = getFinalDutiesForPayment(regInitId, eForm);
			//	logger.debug("STAMP DUTY"+eForm.getFinalStampDuty()+"REG FEE"+eForm.getFinalRegFee()+"DUTY"+eForm.getFinalDuty());
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			String dutiesArr[] = { eForm.getFinalStampDuty().toString(), eForm.getFinalDuty().toString(), eForm.getFinalRegFee().toString(), userId, tDate, "Y", regInitId };
			return regCompDAO.insertFinalDuties(dutiesArr);
		}
	}

	public String getDutyUpdateFlag(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getDutyUpdateFlag(regInitId);
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getTimeBarrdStatus(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getTimeBarrdStatus(regInitId);
	}

	/**
	 * @param date
	 * @param regInitId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean modifyLastDueDate(String date, String regInitId) throws Exception {
		String arr[] = { date, regInitId };
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.modifyLastDueDate(arr);
	}

	/**
	 * @param regInitId
	 * @return boolean
	 */
	public String getLastDueDate(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getLastDueDate(regInitId);
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getDeedId(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getDeedId(regInitId);
	}

	public String getbookId(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getbookId(regInitId);
	}

	public String getbookName(String bookId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getbookName(bookId);
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getRejectionListDetls() throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getRejectionListDetls();
		ArrayList list2 = new ArrayList();
		ArrayList mainList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			list2 = (ArrayList) list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setRejectionId((String) list2.get(0));
			rDTO.setRejectionDesc((String) list2.get(1));
			mainList.add(rDTO);
		}
		return mainList;
	}

	/**
	 * @param sealID
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getSealSubTypeIds(String sealID) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getSealSubTypeIds(sealID);
		ArrayList list2 = new ArrayList();
		ArrayList mainList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			list2 = (ArrayList) list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setSealSubTypeId((String) list2.get(0));
			rDTO.setSealSubTypeName((String) list2.get(1));
			mainList.add(rDTO);
		}
		return mainList;
	}

	/**
	 * @param regInitId
	 * @param sealId
	 * @param sealSubId
	 * @return boolean
	 */
	public boolean insertSealsData(String regInitId, String sealId, String sealSubId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.insertSealsData(regInitId, sealId, sealSubId);
	}

	/*public void getExtraDetls(RegCommonForm regForm) throws Exception{
		//below code for getting extra details
		String[] extraArr=getBankDetails(regForm.getHidnRegTxnId());
		
		if(extraArr!=null){
			
			if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE){
			regForm.setBankName(extraArr[0].trim());
			regForm.setBranchName(extraArr[1].trim());
			regForm.setBankAddress(extraArr[2].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
			regForm.setBankAuthPer(extraArr[3].trim());
			regForm.setBankLoanAmt(Integer.parseInt(extraArr[4].trim()));
			regForm.setBankSancAmt(Integer.parseInt(extraArr[5].trim()));
			}else{

				regForm.setBankName("");
				regForm.setBranchName("");
				regForm.setBankAddress("");
				regForm.setBankAuthPer("");
				regForm.setBankLoanAmt(0);
				regForm.setBankSancAmt(0);
				
			}
			
			
			if(regForm.getDeedID()==RegInitConstant.DEED_TRUST || regForm.getDeedID()==RegInitConstant.DEED_TRUST_PV){
			regForm.setTrustName(extraArr[6].trim());
			if(extraArr[7].trim()!=null && !extraArr[7].trim().equalsIgnoreCase("")){
			regForm.setTrustDate(convertDOBfromDb(extraArr[7].trim()));
			}else{
				regForm.setTrustDate("");
			}
			}else{

				regForm.setTrustName("");
				regForm.setTrustDate("");
				
				
			}
			
			if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_PV || regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV){
				if(extraArr[8].trim()!=null && !extraArr[8].trim().equalsIgnoreCase("")){
				regForm.setRent(Double.parseDouble(extraArr[8].trim()));
				}
				regForm.setAdvance(Double.parseDouble(extraArr[9].trim()));
				regForm.setDevlpmtCharge(Double.parseDouble(extraArr[10].trim()));
				
				if(extraArr[11].trim()!=null && !extraArr[11].trim().equalsIgnoreCase("")){
				regForm.setOtherRecCharge(Double.parseDouble(extraArr[11].trim()));
				}else{
					regForm.setOtherRecCharge(0);
				}
				regForm.setPremium(Double.parseDouble(extraArr[12].trim()));
				regForm.setTermLease(Double.parseDouble(extraArr[13].trim()));
			}else{
				regForm.setRent(0);
				regForm.setAdvance(0);
				regForm.setDevlpmtCharge(0);
				regForm.setOtherRecCharge(0);
				regForm.setPremium(0);
				regForm.setTermLease(0);
			}
			
			if(regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_PV ){
				regForm.setWithPos(extraArr[14].trim());
				regForm.setSecLoanAmt(Double.parseDouble(extraArr[15].trim()));
			}else{
	            regForm.setWithPos("-");
			    regForm.setSecLoanAmt(0);
			}
			
			if(regForm.getDeedID()==RegInitConstant.DEED_AWARD_PV ){
				regForm.setFnameArb(extraArr[16].trim());
				if(extraArr[17].trim()!=null && !extraArr[17].trim().equalsIgnoreCase("")){
					regForm.setMnameArb(extraArr[17].trim());
				}else{
					regForm.setMnameArb("-");
				}
				
				regForm.setLnameArb(extraArr[18].trim());
				regForm.setGendarArb(extraArr[19].trim());
				if(extraArr[19].trim().equalsIgnoreCase("M")){
					regForm.setGendarNameArb("Male");
				}else{
					regForm.setGendarNameArb("Female");
				}
				regForm.setAgeArb(extraArr[20].trim());
				regForm.setFatherNameArb(extraArr[21].trim());
				
				if(extraArr[22].trim()!=null && !extraArr[22].trim().equalsIgnoreCase("")){
					regForm.setMotherNameArb(extraArr[22].trim());
				}else{
					regForm.setMotherNameArb("-");
				}
				
				
				if(extraArr[23].trim()!=null && !extraArr[23].trim().equalsIgnoreCase("")){
					regForm.setSpouseNameArb(extraArr[23].trim());
				}
				else{
					regForm.setSpouseNameArb("-");
				}
				
				regForm.setNationalityArb(extraArr[24].trim());
				regForm.setIndaddressArb(extraArr[25].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
				regForm.setIndcountryArb(extraArr[26].trim());
				regForm.setIndstatenameArb(extraArr[27].trim());
				regForm.setInddistrictArb(extraArr[28].trim());
				regForm.setInddistrictNameArb(commonDao.getDistrictName(extraArr[28].trim()));
				regForm.setIndstatenameNameArb(commonDao.getStateName(extraArr[27].trim()));
				regForm.setIndcountryNameArb(commonDao.getCountryName(extraArr[26].trim()));
				
				if(extraArr[29].trim()!=null && !extraArr[29].trim().equalsIgnoreCase("")){
					regForm.setIndphnoArb(extraArr[29].trim());
				}else{
					regForm.setIndphnoArb("-");
				}
				
				
				regForm.setIndmobnoArb(extraArr[30].trim());
				
				if(extraArr[31].trim()!=null && !extraArr[31].trim().equalsIgnoreCase("")){
					regForm.setEmailIDArb(extraArr[31].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
				}else{
					regForm.setEmailIDArb("-");
				}
				
				
				regForm.setIndCategoryArb(extraArr[32].trim());
				regForm.setIndCategoryNameArb(getCategoryName(extraArr[32].trim()));
				regForm.setIndDisabilityArb(extraArr[33].trim());
				if(extraArr[33].trim().equalsIgnoreCase("Y")){
					regForm.setIndDisabilityNameArb("Yes");
					regForm.setHdnIndDisabilityArb("Y");
				}else{
					regForm.setIndDisabilityNameArb("No");
					regForm.setHdnIndDisabilityArb("No");
				}
				regForm.setIndDisabilityDescArb(extraArr[34].trim());
				regForm.setListIDArb(extraArr[35].trim());
				regForm.setListIDNameArb(getPhotoIdProofName(extraArr[35].trim()));
				regForm.setIdnoArb(extraArr[36].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
				
			}else{
				regForm.setFnameArb("");
				regForm.setMnameArb("");
				regForm.setLnameArb("");
				regForm.setGendarArb("");
				regForm.setAgeArb("");
				regForm.setFatherNameArb("");
				regForm.setMotherNameArb("");
				regForm.setSpouseNameArb("");
				regForm.setNationalityArb("");
				regForm.setIndaddressArb("");
				regForm.setIndcountryArb("");
				regForm.setIndstatenameArb("");
				regForm.setInddistrictArb("");
				regForm.setIndphnoArb("");
				regForm.setIndmobnoArb("");
				regForm.setEmailIDArb("");
				regForm.setIndCategoryArb("");
				regForm.setIndDisabilityArb("");
				regForm.setIndDisabilityDescArb("");
				regForm.setListIDArb("");
				regForm.setIdnoArb("");
			}
			
			if(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_PV ){
				regForm.setAdvance(Double.parseDouble(extraArr[9].trim()));
				regForm.setAdvancePaymntDate(convertDOBfromDb(extraArr[37].trim()));
				regForm.setPossGiven(extraArr[38].trim());
				if(extraArr[38].trim().equalsIgnoreCase("N")){
					regForm.setPossGivenName("No");
				}else{
					regForm.setPossGivenName("Yes");
				}
			}else{
				//regForm.setAdvance(0);
				regForm.setAdvancePaymntDate("");
				regForm.setPossGiven("N");
				regForm.setPossGivenName("");
			}
			
			
		}else{
			regForm.setBankName("-");
			regForm.setBranchName("-");
			regForm.setBankAddress("-");
			regForm.setBankAuthPer("-");
			regForm.setBankLoanAmt(Integer.parseInt("0"));
			regForm.setBankSancAmt(Integer.parseInt("0"));
			regForm.setTrustName("-");
			regForm.setTrustDate("-");
			regForm.setWithPos("-");
			regForm.setSecLoanAmt(0);
			regForm.setFnameArb("");
			regForm.setMnameArb("");
			regForm.setLnameArb("");
			regForm.setGendarArb("");
			regForm.setAgeArb("");
			regForm.setFatherNameArb("");
			regForm.setMotherNameArb("");
			regForm.setSpouseNameArb("");
			regForm.setNationalityArb("");
			regForm.setIndaddressArb("");
			regForm.setIndcountryArb("");
			regForm.setIndstatenameArb("");
			regForm.setInddistrictArb("");
			regForm.setIndphnoArb("");
			regForm.setIndmobnoArb("");
			regForm.setEmailIDArb("");
			regForm.setIndCategoryArb("");
			regForm.setIndDisabilityArb("");
			regForm.setIndDisabilityDescArb("");
			regForm.setListIDArb("");
			regForm.setIdnoArb("");
			regForm.setAdvance(0);
			regForm.setAdvancePaymntDate("");
			regForm.setPossGiven("N");
			regForm.setPossGivenName("");
			
		}
	}
	*/
	/**
	 * @param appId
	 * @return String Array
	 * @throws Exception
	 */
	/*
	public String[] getBankDetails(String appId) throws Exception {
		
		
		ArrayList list=commonDao.getBankDetails(appId);
		
		if(list!=null && list.size()>0){
			
			String str=list.toString();
			str=str.substring(2, str.length()-2);
			String strArr[]=str.split(",");
			return strArr;
			
		}else{
			return null;
		}
		
		//RegCommonDAO dao = new RegCommonDAO();
		//return commonBd.getBankDetails(appId);
	}

	public String getCategoryName(String id) throws Exception {
		return commonDao.getCategoryName(id);
	}*/
	public RegCompCheckerForm getExtraDetls(RegCompCheckerForm regForm, String language) throws Exception {
		//below code for getting extra details
		/*RegCompCheckerDTO ddto = regForm.getRegDTO();
		String[] extraArr=getBankDetails(ddto.getRegInitNumber());
		
		if(extraArr!=null){
			
			if(ddto.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE){
			ddto.setBankName(extraArr[0].trim());
			ddto.setBranchName(extraArr[1].trim());
			ddto.setBankAddress(extraArr[2].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
			ddto.setBankAuthPer(extraArr[3].trim());
			ddto.setBankLoanAmt(Integer.parseInt(extraArr[4].trim()));
			ddto.setBankSancAmt(Integer.parseInt(extraArr[5].trim()));
			}else{

				ddto.setBankName("");
				ddto.setBranchName("");
				ddto.setBankAddress("");
				ddto.setBankAuthPer("");
				ddto.setBankLoanAmt(0);
				ddto.setBankSancAmt(0);
				
			}
			
			
			if(ddto.getDeedID()==RegInitConstant.DEED_TRUST || ddto.getDeedID()==RegInitConstant.DEED_TRUST_PV){
			ddto.setTrustName(extraArr[6].trim());
			if(extraArr[7].trim()!=null && !extraArr[7].trim().equalsIgnoreCase("") && !extraArr[7].trim().equalsIgnoreCase("null")){
			ddto.setTrustDate(convertDOBNew(extraArr[7].trim()));
			}else{
				ddto.setTrustDate("");
			}
			}else{

				ddto.setTrustName("");
				ddto.setTrustDate("");
				
				
			}
			
			if(ddto.getDeedID()==RegInitConstant.DEED_LEASE_PV || ddto.getDeedID()==RegInitConstant.DEED_LEASE_NPV){
				if(extraArr[8].trim()!=null && !extraArr[8].trim().equalsIgnoreCase("") && !extraArr[8].trim().equalsIgnoreCase("null")){
				ddto.setRent(Double.parseDouble(extraArr[8].trim()));
				}
				ddto.setAdvance(Double.parseDouble(extraArr[9].trim()));
				ddto.setDevlpmtCharge(Double.parseDouble(extraArr[10].trim()));
				
				if(extraArr[11].trim()!=null && !extraArr[11].trim().equalsIgnoreCase("") && !extraArr[11].trim().equalsIgnoreCase("null")){
				ddto.setOtherRecCharge(Double.parseDouble(extraArr[11].trim()));
				}else{
					ddto.setOtherRecCharge(0);
				}
				ddto.setPremium(Double.parseDouble(extraArr[12].trim()));
				ddto.setTermLease(Double.parseDouble(extraArr[13].trim()));
			}else{
				ddto.setRent(0);
				ddto.setAdvance(0);
				ddto.setDevlpmtCharge(0);
				ddto.setOtherRecCharge(0);
				ddto.setPremium(0);
				ddto.setTermLease(0);
			}
			
			if(ddto.getDeedID()==RegInitConstant.DEED_MORTGAGE_PV || ddto.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ){
				ddto.setWithPos(extraArr[14].trim());
				if(extraArr[15].trim()!=null && !extraArr[15].trim().equalsIgnoreCase("") && !extraArr[15].trim().equalsIgnoreCase("null")){
				ddto.setSecLoanAmt(Double.parseDouble(extraArr[15].trim()));
				}else{
					ddto.setSecLoanAmt(0);
			}
			}
			if(ddto.getDeedID()==RegInitConstant.DEED_AWARD_PV || ddto.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ){
				ddto.setFnameArb(extraArr[16].trim());
				if(extraArr[17].trim()!=null && !extraArr[17].trim().equalsIgnoreCase("") && !extraArr[17].trim().equalsIgnoreCase("null")){
					ddto.setMnameArb(extraArr[17].trim());
				}else{
					ddto.setMnameArb("-");
				}
				
				ddto.setLnameArb(extraArr[18].trim());
				ddto.setGendarArb(extraArr[19].trim());
				if(extraArr[19].trim().equalsIgnoreCase("M")){
					ddto.setGendarNameArb("Male");
				}else{
					ddto.setGendarNameArb("Female");
				}
				ddto.setAgeArb(extraArr[20].trim());
				ddto.setFatherNameArb(extraArr[21].trim());
				
				if(extraArr[22].trim()!=null && !extraArr[22].trim().equalsIgnoreCase("") && !extraArr[22].trim().equalsIgnoreCase("null")){
					ddto.setMotherNameArb(extraArr[22].trim());
				}else{
					ddto.setMotherNameArb("-");
				}
				
				
				if(extraArr[23].trim()!=null && !extraArr[23].trim().equalsIgnoreCase("") && !extraArr[23].trim().equalsIgnoreCase("null")){
					ddto.setSpouseNameArb(extraArr[23].trim());
				}
				else{
					ddto.setSpouseNameArb("-");
				}
				
				ddto.setNationalityArb(extraArr[24].trim());
				ddto.setIndaddressArb(extraArr[25].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
				ddto.setIndcountryArb(extraArr[26].trim());
				ddto.setIndstatenameArb(extraArr[27].trim());
				ddto.setInddistrictArb(extraArr[28].trim());
				ddto.setInddistrictNameArb(commonDao.getDistrictName(extraArr[28].trim(),language));
				ddto.setIndstatenameNameArb(commonDao.getStateName(extraArr[27].trim(),language));
				ddto.setIndcountryNameArb(commonDao.getCountryName(extraArr[26].trim(),language));
				
				if(extraArr[29].trim()!=null && !extraArr[29].trim().equalsIgnoreCase("") && !extraArr[29].trim().equalsIgnoreCase("null")){
					ddto.setIndphnoArb(extraArr[29].trim());
				}else{
					ddto.setIndphnoArb("-");
				}
				
				
				ddto.setIndmobnoArb(extraArr[30].trim());
				
				if(extraArr[31].trim()!=null && !extraArr[31].trim().equalsIgnoreCase("")){
					ddto.setEmailIDArb(extraArr[31].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
				}else{
					ddto.setEmailIDArb("-");
				}
				
				
				ddto.setIndCategoryArb(extraArr[32].trim());
				ddto.setIndCategoryNameArb(commonDao.getCategoryName(extraArr[32].trim(),language));
				ddto.setIndDisabilityArb(extraArr[33].trim());
				if(extraArr[33].trim().equalsIgnoreCase("Y")){
					ddto.setIndDisabilityNameArb("Yes");
					ddto.setHdnIndDisabilityArb("Y");
				}else{
					ddto.setIndDisabilityNameArb("No");
					ddto.setHdnIndDisabilityArb("No");
				}
				ddto.setIndDisabilityDescArb(extraArr[34].trim());
				ddto.setListIDArb(extraArr[35].trim());
				ddto.setListIDNameArb(getPhotoIdProofName(extraArr[35].trim()));
				ddto.setIdnoArb(extraArr[36].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
				
			}else{
				ddto.setFnameArb("");
				ddto.setMnameArb("");
				ddto.setLnameArb("");
				ddto.setGendarArb("");
				ddto.setAgeArb("");
				ddto.setFatherNameArb("");
				ddto.setMotherNameArb("");
				ddto.setSpouseNameArb("");
				ddto.setNationalityArb("");
				ddto.setIndaddressArb("");
				ddto.setIndcountryArb("");
				ddto.setIndstatenameArb("");
				ddto.setInddistrictArb("");
				ddto.setIndphnoArb("");
				ddto.setIndmobnoArb("");
				ddto.setEmailIDArb("");
				ddto.setIndCategoryArb("");
				ddto.setIndDisabilityArb("");
				ddto.setIndDisabilityDescArb("");
				ddto.setListIDArb("");
				ddto.setIdnoArb("");
			}
			
			if(ddto.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_PV ||
					ddto.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ){
				ddto.setAdvance(Double.parseDouble(extraArr[9].trim()));
				ddto.setAdvancePaymntDate(convertDOBNew(extraArr[37].trim()));
				ddto.setPossGiven(extraArr[38].trim());
				if(extraArr[38].trim().equalsIgnoreCase("N")){
					ddto.setPossGivenName("No");
				}else{
					ddto.setPossGivenName("Yes");
				}
			}else{
				//ddto.setAdvance(0);
				ddto.setAdvancePaymntDate("");
				ddto.setPossGiven("N");
				ddto.setPossGivenName("");
			}
			
			if(ddto.getDeedID()==RegInitConstant.DEED_POA ){
				ddto.setPoaWithConsid(extraArr[39].trim());
				ddto.setPoaPeriod(Double.parseDouble(extraArr[40].trim()));
				
			}else{
				ddto.setPoaWithConsid("");
				ddto.setPoaPeriod(0);
			}
			
			if(ddto.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ){
				
				ddto.setPaidLoanAmt(Double.parseDouble(extraArr[41].trim()));
			}else{
		       
			    ddto.setPaidLoanAmt(0);
			}
			
			if(ddto.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV ){
				
				if(extraArr[42].trim()!=null && !extraArr[42].trim().equalsIgnoreCase("")){
				ddto.setContriProp(extraArr[42].trim());
				}else{
					ddto.setContriProp("-");
				}
			}else{
		       
			    ddto.setContriProp("-");
			}
			
			if(ddto.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_PV){
				
				if(extraArr[43].trim()!=null && !extraArr[43].trim().equalsIgnoreCase("")){
				ddto.setDissolutionDate(convertDOBNew(extraArr[43].trim()));
				}else{
					ddto.setDissolutionDate("");
				}
				if(extraArr[44].trim()!=null && !extraArr[44].trim().equalsIgnoreCase("")){
					ddto.setRetirmentDate(convertDOBNew(extraArr[44].trim()));
					}else{
						ddto.setRetirmentDate("");
					}
				
			
			}else{

					ddto.setDissolutionDate("");
					ddto.setRetirmentDate("");
					
					
				}
			
			
		}else{
			ddto.setBankName("-");
			ddto.setBranchName("-");
			ddto.setBankAddress("-");
			ddto.setBankAuthPer("-");
			ddto.setBankLoanAmt(Integer.parseInt("0"));
			ddto.setBankSancAmt(Integer.parseInt("0"));
			ddto.setTrustName("-");
			ddto.setTrustDate("-");
			ddto.setWithPos("-");
			ddto.setSecLoanAmt(0);
			ddto.setFnameArb("");
			ddto.setMnameArb("");
			ddto.setLnameArb("");
			ddto.setGendarArb("");
			ddto.setAgeArb("");
			ddto.setFatherNameArb("");
			ddto.setMotherNameArb("");
			ddto.setSpouseNameArb("");
			ddto.setNationalityArb("");
			ddto.setIndaddressArb("");
			ddto.setIndcountryArb("");
			ddto.setIndstatenameArb("");
			ddto.setInddistrictArb("");
			ddto.setIndphnoArb("");
			ddto.setIndmobnoArb("");
			ddto.setEmailIDArb("");
			ddto.setIndCategoryArb("");
			ddto.setIndDisabilityArb("");
			ddto.setIndDisabilityDescArb("");
			ddto.setListIDArb("");
			ddto.setIdnoArb("");
			ddto.setAdvance(0);
			ddto.setAdvancePaymntDate("");
			ddto.setPossGiven("N");
			ddto.setPossGivenName("");
			ddto.setPoaWithConsid("");
			ddto.setPoaPeriod(0);
			ddto.setPaidLoanAmt(0);
			ddto.setContriProp("");
			ddto.setDissolutionDate("");
			ddto.setRetirmentDate("");
			
		}
		regForm.setRegDTO(ddto);
		return regForm;*/
		RegCompCheckerDTO ddto = regForm.getRegDTO();
		ArrayList extraArr = getBankDetails(ddto.getRegInitNumber());
		if (extraArr != null) {
			if (ddto.getDeedID() == RegInitConstant.DEED_DEPOSIT_OF_TITLE) {
				ddto.setBankName(extraArr.get(0).toString());
				ddto.setBranchName(extraArr.get(1).toString());
				ddto.setBankAddress(extraArr.get(2).toString().replace(RegInitConstant.SPLIT_CONSTANT, ","));
				ddto.setBankAuthPer(extraArr.get(3).toString());
				ddto.setBankLoanAmt(Double.parseDouble(extraArr.get(4).toString()));
				ddto.setBankSancAmt(Double.parseDouble(extraArr.get(5).toString()));
			} else {
				ddto.setBankName("");
				ddto.setBranchName("");
				ddto.setBankAddress("");
				ddto.setBankAuthPer("");
				ddto.setBankLoanAmt(0);
				ddto.setBankSancAmt(0);
			}
			if (ddto.getDeedID() == RegInitConstant.DEED_TRUST || ddto.getDeedID() == RegInitConstant.DEED_TRUST_PV) {
				ddto.setTrustName(extraArr.get(6).toString());
				//if(extraArr.get(7)!=null && !extraArr.get(7).toString().equalsIgnoreCase("") && !extraArr.get(7).toString().equalsIgnoreCase("null")){
				ddto.setTrustDate(convertDOB(extraArr.get(7).toString()));
				/*}else{
					ddto.setTrustDate("");
				}*/
			} else {
				ddto.setTrustName("");
				ddto.setTrustDate("");
			}
			if (ddto.getDeedID() == RegInitConstant.DEED_LEASE_PV || ddto.getDeedID() == RegInitConstant.DEED_LEASE_NPV) {
				if (extraArr.get(8) != null && !extraArr.get(8).toString().equalsIgnoreCase("") && !extraArr.get(8).toString().equalsIgnoreCase("null")) {
					ddto.setRent(Double.parseDouble(extraArr.get(8).toString()));
				} else {
					ddto.setRent(0);
				}
				ddto.setAdvance(Double.parseDouble(extraArr.get(9).toString()));
				ddto.setDevlpmtCharge(Double.parseDouble(extraArr.get(10).toString()));
				if (extraArr.get(11) != null && !extraArr.get(11).toString().equalsIgnoreCase("") && !extraArr.get(11).toString().equalsIgnoreCase("null")) {
					ddto.setOtherRecCharge(Double.parseDouble(extraArr.get(11).toString()));
				} else {
					ddto.setOtherRecCharge(0);
				}
				ddto.setPremium(Double.parseDouble(extraArr.get(12).toString()));
				ddto.setTermLease(Double.parseDouble(extraArr.get(13).toString()));
			} else {
				ddto.setRent(0);
				ddto.setAdvance(0);
				ddto.setDevlpmtCharge(0);
				ddto.setOtherRecCharge(0);
				ddto.setPremium(0);
				ddto.setTermLease(0);
			}
			if (ddto.getDeedID() == RegInitConstant.DEED_MORTGAGE_PV || ddto.getDeedID() == RegInitConstant.DEED_MORTGAGE_NPV) {
				ddto.setWithPos(extraArr.get(14).toString());
				if (extraArr.get(15) != null && !extraArr.get(15).toString().equalsIgnoreCase("") && !extraArr.get(15).toString().equalsIgnoreCase("null")) {
					ddto.setSecLoanAmt(Double.parseDouble(extraArr.get(15).toString()));
				} else {
					ddto.setSecLoanAmt(0);
				}
			} else {
				ddto.setWithPos("-");
				ddto.setSecLoanAmt(0);
			}
			if (ddto.getDeedID() == RegInitConstant.DEED_AWARD_PV || ddto.getDeedID() == RegInitConstant.DEED_SURRENDER_LEASE_NPV) {
				ddto.setFnameArb(extraArr.get(16).toString());
				if (extraArr.get(17) != null && !extraArr.get(17).toString().equalsIgnoreCase("") && !extraArr.get(17).toString().equalsIgnoreCase("null")) {
					ddto.setMnameArb(extraArr.get(17).toString());
				} else {
					ddto.setMnameArb("-");
				}
				ddto.setLnameArb(extraArr.get(18).toString());
				ddto.setGendarArb(extraArr.get(19).toString());
				if (extraArr.get(19).toString().equalsIgnoreCase("M")) {
					ddto.setGendarNameArb("Male");
				} else if (extraArr.get(19).toString().equalsIgnoreCase("F")) {
					ddto.setGendarNameArb("Female");
				} else {
					ddto.setGendarNameArb("Other");
				}
				ddto.setAgeArb(extraArr.get(20).toString());
				ddto.setFatherNameArb(extraArr.get(21).toString());
				if (extraArr.get(22) != null && !extraArr.get(22).toString().equalsIgnoreCase("") && !extraArr.get(22).toString().equalsIgnoreCase("null")) {
					ddto.setMotherNameArb(extraArr.get(22).toString());
				} else {
					ddto.setMotherNameArb("-");
				}
				if (extraArr.get(23) != null && !extraArr.get(23).toString().equalsIgnoreCase("") && !extraArr.get(23).toString().equalsIgnoreCase("null")) {
					ddto.setSpouseNameArb(extraArr.get(23).toString());
				} else {
					ddto.setSpouseNameArb("-");
				}
				ddto.setNationalityArb(extraArr.get(24).toString());
				ddto.setIndaddressArb(extraArr.get(25).toString().replace(RegInitConstant.SPLIT_CONSTANT, ","));
				ddto.setIndcountryArb(extraArr.get(26).toString());
				ddto.setIndstatenameArb(extraArr.get(27).toString());
				ddto.setInddistrictArb(extraArr.get(28).toString());
				ddto.setInddistrictNameArb(commonBo.getDistrictName(extraArr.get(28).toString(), language));
				ddto.setIndstatenameNameArb(commonBo.getStateName(extraArr.get(27).toString(), language));
				ddto.setIndcountryNameArb(commonBo.getCountryName(extraArr.get(26).toString(), language));
				if (extraArr.get(29) != null && !extraArr.get(29).toString().equalsIgnoreCase("") && !extraArr.get(29).toString().equalsIgnoreCase("null")) {
					ddto.setIndphnoArb(extraArr.get(29).toString());
				} else {
					ddto.setIndphnoArb("-");
				}
				ddto.setIndmobnoArb(extraArr.get(30).toString());
				if (extraArr.get(31) != null && !extraArr.get(31).toString().equalsIgnoreCase("") && !extraArr.get(31).toString().equalsIgnoreCase("null")) {
					ddto.setEmailIDArb(extraArr.get(31).toString().replace(RegInitConstant.SPLIT_CONSTANT, ","));
				} else {
					ddto.setEmailIDArb("-");
				}
				ddto.setIndCategoryArb(extraArr.get(32).toString());
				ddto.setIndCategoryNameArb(commonBo.getCategoryName(extraArr.get(32).toString(), language));
				ddto.setIndDisabilityArb(extraArr.get(33).toString());
				if (extraArr.get(33).toString().equalsIgnoreCase("Y")) {
					ddto.setIndDisabilityNameArb("Yes");
					ddto.setHdnIndDisabilityArb("Y");
				} else {
					ddto.setIndDisabilityNameArb("No");
					ddto.setHdnIndDisabilityArb("N");
				}
				if (extraArr.get(34) != null) {
					ddto.setIndDisabilityDescArb(extraArr.get(34).toString());
				} else {
					ddto.setIndDisabilityDescArb("-");
				}
				ddto.setListIDArb(extraArr.get(35).toString());
				ddto.setListIDNameArb(commonBo.getPhotoIdProofName(extraArr.get(35).toString(), language));
				ddto.setIdnoArb(extraArr.get(36).toString().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			} else {
				ddto.setFnameArb("");
				ddto.setMnameArb("");
				ddto.setLnameArb("");
				ddto.setGendarArb("");
				ddto.setAgeArb("");
				ddto.setFatherNameArb("");
				ddto.setMotherNameArb("");
				ddto.setSpouseNameArb("");
				ddto.setNationalityArb("");
				ddto.setIndaddressArb("");
				ddto.setIndcountryArb("");
				ddto.setIndstatenameArb("");
				ddto.setInddistrictArb("");
				ddto.setIndphnoArb("");
				ddto.setIndmobnoArb("");
				ddto.setEmailIDArb("");
				ddto.setIndCategoryArb("");
				ddto.setIndDisabilityArb("");
				ddto.setIndDisabilityDescArb("");
				ddto.setListIDArb("");
				ddto.setIdnoArb("");
			}
			if (ddto.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_PV || ddto.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_NPV) {
				/*	ddto.setAdvance(Double.parseDouble(extraArr.get(9).toString()));
					ddto.setAdvancePaymntDate(convertDOB(extraArr.get(37).toString()));*/
				if (ddto.getInstID() == RegInitConstant.INST_AGREEMENT_MEMO_MEDIA) {
					ddto.setAgreementDetails(extraArr.get(58) != null && !("").equalsIgnoreCase(extraArr.get(58).toString()) ? extraArr.get(58).toString() : "-");
				} else if (ddto.getInstID() == RegInitConstant.INST_AGREEMENT_MEMO_WORK_CONTRACT) {
					ddto.setContractDetails(extraArr.get(59) != null && !("").equalsIgnoreCase(extraArr.get(59).toString()) ? extraArr.get(59).toString() : "-");
				} else {
					ddto.setAdvancePaymntDetails(extraArr.get(56).toString());
					if (ddto.getInstID() != RegInitConstant.INST_AGREEMENT_MEMO_NPV_WITHOUTPOS) {
						ddto.setPossGiven(extraArr.get(38).toString());
						if (extraArr.get(38).toString().equalsIgnoreCase("N")) {
							ddto.setPossGivenName("No");
						} else {
							ddto.setPossGivenName("Yes");
						}
					}
				}
			} else {
				//ddto.setAdvance(0);
				ddto.setAdvancePaymntDate("");
				ddto.setPossGiven("N");
				ddto.setPossGivenName("");
				ddto.setAgreementDetails("");
				ddto.setContractDetails("");
			}
			if (ddto.getInstID() == RegInitConstant.INST_POA_1 || ddto.getInstID() == RegInitConstant.INST_POA_2) {
				if (ddto.getInstID() != RegInitConstant.INST_POA_1) {
					ddto.setPoaWithConsid((String) extraArr.get(39));
				}
				if (extraArr.get(40) != null && !extraArr.get(40).toString().equalsIgnoreCase("")) {
					ddto.setPoaPeriod(Integer.parseInt(extraArr.get(40).toString()));
				}
			} else {
				ddto.setPoaWithConsid("");
				ddto.setPoaPeriod(0);
			}
			if (ddto.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV) {
				ddto.setPaidLoanAmt(Double.parseDouble(extraArr.get(41).toString()));
			} else {
				ddto.setPaidLoanAmt(0);
			}
			if (ddto.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_PV && (ddto.getInstID() != RegInitConstant.INST_DISSOLUTION_NPV && ddto.getInstID() != RegInitConstant.INST_DISSOLUTION_2 && ddto.getInstID() != RegInitConstant.INST_PARTNERSHIP_EXCLUDING_CASH)) {
				if (extraArr.get(42) != null && !extraArr.get(42).toString().equalsIgnoreCase("") && !extraArr.get(42).toString().equalsIgnoreCase("null")) {
					ddto.setContriProp(extraArr.get(42).toString());
				} else {
					ddto.setContriProp("-");
				}
			} else {
				ddto.setContriProp("-");
			}
			if (ddto.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_PV && (ddto.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || ddto.getInstID() == RegInitConstant.INST_DISSOLUTION_2)) {
				//if(extraArr.get(43)!=null && !extraArr.get(43).toString().equalsIgnoreCase("") && !extraArr.get(43).toString().equalsIgnoreCase("null")){
				ddto.setDissolutionDate(convertDOB(extraArr.get(43).toString()));
				/*}else{
					ddto.setDissolutionDate("");
				}*/
				//if(extraArr.get(44)!=null && !extraArr.get(44).toString().equalsIgnoreCase("") && !extraArr.get(44).toString().equalsIgnoreCase("null")){
				ddto.setRetirmentDate(convertDOB(extraArr.get(44).toString()));
				/*}else{
					ddto.setRetirmentDate("");
				}*/
			} else {
				ddto.setDissolutionDate("");
				ddto.setRetirmentDate("");
			}
			if (ddto.getInstID() == RegInitConstant.INST_ACK_RECEIPT_1) {
				ddto.setExecutionDate(convertDOB(extraArr.get(45).toString()));
				ddto.setRegistrationDate(convertDOB(extraArr.get(46).toString()));
				ddto.setRegistrationNo(extraArr.get(47).toString());
				ddto.setReceiptAmount(Double.parseDouble(extraArr.get(48).toString()));
				ddto.setReceiptAmountDisp(BigDecimal.valueOf(Double.parseDouble(extraArr.get(48).toString())).toPlainString());
			} else {
				ddto.setExecutionDate("");
				ddto.setRegistrationDate("");
				ddto.setRegistrationNo("");
				ddto.setReceiptAmount(0);
				ddto.setReceiptAmountDisp("");
			}
			if (ddto.getInstID() == RegInitConstant.INST_BANK_GAURANTEE_1) {
				ddto.setBankName(extraArr.get(0).toString());
				ddto.setExecutionDate(convertDOB(extraArr.get(45).toString()));
				ddto.setReceiptAmount(Double.parseDouble(extraArr.get(48).toString()));
				ddto.setReceiptAmountDisp(BigDecimal.valueOf(Double.parseDouble(extraArr.get(48).toString())).toPlainString());
				if (extraArr.get(49) != null)
					ddto.setPropDetls(extraArr.get(49).toString());
				else
					ddto.setPropDetls("-");
			}/*else{
																																																																																																						ddto.setBankName("");
																																																																																																						ddto.setExecutionDate("");			
																																																																																																						ddto.setReceiptAmount(0);
																																																																																																						ddto.setReceiptAmountDisp("");
																																																																																																						ddto.setPropDetls("");
																																																																																																							
																																																																																																						}*/
			if (ddto.getInstID() == RegInitConstant.INST_CONSENT_1) {
				ddto.setRegistrationDate(convertDOB(extraArr.get(46).toString()));
				ddto.setRegistrationNo(extraArr.get(47).toString());
				ddto.setDeedNamePreReg(extraArr.get(50).toString());
				ddto.setDeedTypePreReg(extraArr.get(51).toString());
			}/*else{
																																																																																																						ddto.setRegistrationDate("");
																																																																																																						ddto.setRegistrationNo("");
																																																																																																						
																																																																																																						ddto.setDeedNamePreReg("");
																																																																																																						ddto.setDeedTypePreReg("");
																																																																																																							
																																																																																																						}*/
			if (ddto.getInstID() == RegInitConstant.INST_DEC_UNDER_MP_1) {
				ddto.setPropDetls(extraArr.get(49).toString());
				ddto.setMapOrderNo(extraArr.get(52).toString());
				ddto.setMapOrderDate(convertDOB(extraArr.get(53).toString()));
				ddto.setTcpOrderNo(extraArr.get(54).toString());
				ddto.setTcpOrderDate(convertDOB(extraArr.get(55).toString()));
			} else {
				//ddto.setPropDetls("");
				ddto.setMapOrderNo("");
				ddto.setMapOrderDate("");
				ddto.setTcpOrderNo("");
				ddto.setTcpOrderDate("");
			}
			if (ddto.getInstID() == RegInitConstant.INST_DOC_AMEND_1) {
				ddto.setRegistrationDate(convertDOB(extraArr.get(46).toString()));
				ddto.setRegistrationNo(extraArr.get(47).toString());
				ddto.setDeedNamePreReg(extraArr.get(50).toString());
				ddto.setDeedTypePreReg(extraArr.get(51).toString());
				ddto.setExecutionDate(convertDOB(extraArr.get(45).toString()));
				ddto.setPropDetls(extraArr.get(49).toString());
			}/*else{
																																																																																																						ddto.setRegistrationDate("");
																																																																																																						ddto.setRegistrationNo("");
																																																																																																						
																																																																																																						ddto.setDeedNamePreReg("");
																																																																																																						ddto.setDeedTypePreReg("");
																																																																																																						ddto.setExecutionDate("");			
																																																																																																						ddto.setPropDetls("");
																																																																																																							
																																																																																																						}*/
		} else {
			ddto.setBankName("-");
			ddto.setBranchName("-");
			ddto.setBankAddress("-");
			ddto.setBankAuthPer("-");
			ddto.setBankLoanAmt(Integer.parseInt("0"));
			ddto.setBankSancAmt(Integer.parseInt("0"));
			ddto.setTrustName("-");
			ddto.setTrustDate("-");
			ddto.setWithPos("-");
			ddto.setSecLoanAmt(0);
			ddto.setFnameArb("");
			ddto.setMnameArb("");
			ddto.setLnameArb("");
			ddto.setGendarArb("");
			ddto.setAgeArb("");
			ddto.setFatherNameArb("");
			ddto.setMotherNameArb("");
			ddto.setSpouseNameArb("");
			ddto.setNationalityArb("");
			ddto.setIndaddressArb("");
			ddto.setIndcountryArb("");
			ddto.setIndstatenameArb("");
			ddto.setInddistrictArb("");
			ddto.setIndphnoArb("");
			ddto.setIndmobnoArb("");
			ddto.setEmailIDArb("");
			ddto.setIndCategoryArb("");
			ddto.setIndDisabilityArb("");
			ddto.setIndDisabilityDescArb("");
			ddto.setListIDArb("");
			ddto.setIdnoArb("");
			ddto.setAdvance(0);
			ddto.setAdvancePaymntDate("");
			ddto.setPossGiven("N");
			ddto.setPossGivenName("");
			ddto.setPoaWithConsid("");
			ddto.setPoaPeriod(0);
			ddto.setPaidLoanAmt(0);
			ddto.setContriProp("");
			ddto.setDissolutionDate("");
			ddto.setRetirmentDate("");
			ddto.setExecutionDate("");
			ddto.setRegistrationDate("");
			ddto.setRegistrationNo("");
			ddto.setReceiptAmount(0);
			ddto.setReceiptAmountDisp("");
			ddto.setBankName("");
			ddto.setExecutionDate("");
			ddto.setReceiptAmount(0);
			ddto.setReceiptAmountDisp("");
			ddto.setPropDetls("");
			ddto.setRegistrationDate("");
			ddto.setRegistrationNo("");
			ddto.setDeedNamePreReg("");
			ddto.setDeedTypePreReg("");
			ddto.setPropDetls("");
			ddto.setMapOrderNo("");
			ddto.setMapOrderDate("");
			ddto.setTcpOrderNo("");
			ddto.setTcpOrderDate("");
		}
		return regForm;
		//end of code for getting extra details
	}

	public ArrayList getBankDetails(String appId) throws Exception {
		ArrayList list = commonBd.getBankDetails(appId);
		if (list != null && list.size() == 1) {
			/*String str=list.toString();
			str=str.substring(2, str.length()-2);
			String strArr[]=str.split(",");*/
			return (ArrayList) list.get(0);
		} else {
			return null;
		}
		//RegCommonDAO dao = new RegCommonDAO();
		//return commonBd.getBankDetails(appId);
	}

	public void landConfirmationScreen(RegCompCheckerForm regForm, String languageLocale) throws Exception {
		//add status for confirmation screen
		regForm.setMapPropertyTransParty(new HashMap());
		RegCompCheckerDTO rdto = regForm.getRegDTO();
		getCancellationLabel(Integer.toString(regForm.getDuty_txn_id()), regForm, languageLocale);
		String[] deedInstArray = commonBo.getDeedInstId(rdto.getRegInitNumber());
		String purpose = commonBo.getEStampPurpose(rdto.getRegInitNumber());
		if (purpose != null && !purpose.equalsIgnoreCase("")) {
			rdto.setPurpose(purpose.replace(RegInitConstant.SPLIT_CONSTANT, ","));
		} else {
			rdto.setPurpose("");
		}
		if (deedInstArray != null && deedInstArray.length > 0) {
			if (deedInstArray[3].trim() != null && !deedInstArray[3].trim().equalsIgnoreCase("")) {
				if (deedInstArray[3].trim().equalsIgnoreCase("A")) {
					regForm.setFromAdjudication(1);
				} else {
					regForm.setFromAdjudication(0);
				}
			} else {
				regForm.setFromAdjudication(0);
			}
			if (rdto.getDeedID() == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA) {
				regForm.setCancelDeedRadio(Integer.parseInt(deedInstArray[4].trim()));
			}
		}
		getExtraDetls(regForm, languageLocale);
		//NumberFormat obj=new DecimalFormat("#0.00");
		String dutyListArr[];
		if (regForm.getInitiateAdjuApp() == 0) {
			dutyListArr = commonBo.getDutyDetlsForConfirmation(rdto.getRegInitNumber());
			regForm.setAdjuRemarks("");
		} else {
			dutyListArr = commonBo.getDutyDetlsAdjuForConfirmation(rdto.getRegInitNumber());
			regForm.setAdjuRemarks(commonBo.getRemarks(rdto.getRegInitNumber()));
			//get adjudication remarks here...
		}
		int numberOfProperties;
		rdto.setTotalPayableAmount("0");
		double totalPaidAmt = 0;
		/*ArrayList paidAmtList=getAllPaidAmounts(rdto.getRegInitNumber());  // commented by SIMRAN
		for(int i=0;i<paidAmtList.size();i++){
			ArrayList row_list=new ArrayList();
			row_list=(ArrayList)paidAmtList.get(i);
			String amnts=row_list.toString();
			amnts=amnts.substring(1, amnts.length()-1);
			//logger.debug("paid amount list-->"+amnts);
			String amntsArr[]=amnts.split(",");
			
			totalPaidAmt=totalPaidAmt+Double.parseDouble(amntsArr[0].trim());
		}*/
		rdto.setTotalPaidAmount(String.valueOf(totalPaidAmt));
		if (regForm.getPvReqFlag().equalsIgnoreCase("Y") || regForm.getPropReqFlag().equalsIgnoreCase("Y")) {
			ArrayList propertyIdList = new ArrayList();
			propertyIdList = commonBo.getPropertyIdMarketVal(rdto.getRegInitNumber());
			//regForm.setShareOfPropSize(0);
			/*if(rdto.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
				ArrayList transPartyDets=getTransPartyDetsNonPropDeed(rdto.getRegInitNumber(),regForm.getCommonDeed(),languageLocale, rdto.getDeedID(), rdto.getInstID());
				regForm.setTransPartyListPVDeed(transPartyDets);
				regForm.setPropListPVDeed(getPropertyListPVDeed(propertyIdList));
			}else*/{
				regForm.setTransPartyListPVDeed(null);
				regForm.setPropListPVDeed(null);
				HashMap propMap = new HashMap();
				propMap = regForm.getMapPropertyTransParty();
				double totalMarketVal = 0;
				numberOfProperties = propertyIdList.size();
				if (numberOfProperties > 0) {
					for (int i = 0; i < propertyIdList.size(); i++) {
						ArrayList row_list = new ArrayList();
						row_list = (ArrayList) propertyIdList.get(i);
						String propIds = row_list.toString();
						propIds = propIds.substring(1, propIds.length() - 1);
						String propId[] = propIds.split(",");
						if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
							if (!propId[1].trim().equalsIgnoreCase("") && !propId[1].trim().equals(null) && !propId[1].trim().equalsIgnoreCase("null")) {
								totalMarketVal = totalMarketVal + Double.parseDouble(propId[1].trim());
							} else {
								totalMarketVal = 0;
							}
						}
						//Updated by Rakesh for PartyPropMappingDisplay: Start
						ArrayList transPartyDets = null;
						String clr_flag = commonBo.getClrFlagByPropId(propId[0].trim());
						if (clr_flag != null && !clr_flag.isEmpty()) {
							if (clr_flag.equalsIgnoreCase("Y")) {
								transPartyDets = getTransPartyDetsCLR(propId[0].trim(), rdto.getRegInitNumber(), languageLocale, rdto.getDeedID(), rdto.getInstID(), regForm);
							} else {
								transPartyDets = getTransPartyDets(propId[0].trim(), rdto.getRegInitNumber(), languageLocale, rdto.getDeedID(), rdto.getInstID(), regForm);
							}
						} else {
							transPartyDets = getTransPartyDets(propId[0].trim(), rdto.getRegInitNumber(), languageLocale, rdto.getDeedID(), rdto.getInstID(), regForm);
						}
						//Updated by Rakesh for PartyPropMappingDisplay: End
						//ArrayList transPartyDets=getTransPartyDets(propId[0].trim(),rdto.getRegInitNumber(),languageLocale, rdto.getDeedID(), rdto.getInstID(),regForm);
						if (propId[0].trim().length() == 15) {
							propId[0] = "0" + propId[0].trim();
						}
						if (regForm.getPvReqFlag().equalsIgnoreCase("Y"))
							propMap.put(propId[0].trim() + "," + propId[1].trim(), transPartyDets);
						else {
							propMap.put(propId[0].trim(), transPartyDets); // without market value where valuation is not required
						}
					}
					//double finalMVDouble=Double.parseDouble(finalMV);
					//finalMVDouble=Math.ceil(finalMVDouble);
					String totalMarketValString = BigDecimal.valueOf(totalMarketVal).toPlainString();
					rdto.setTotalMarketValue(totalMarketValString);
					regForm.setMapPropertyTransParty(propMap);
				} else {//for partition and poa deeds where property details will be captured based on user selection
					regForm.setMapPropertyTransParty(null);
					regForm.setParticularCount(0);
					ArrayList transPartyDets = commonBo.getTransPartyDetsNonPropDeed(rdto.getRegInitNumber(), 0, languageLocale, rdto.getDeedID(), rdto.getInstID());
					regForm.setTransPartyListNonPropDeed(transPartyDets);
				}
			}
		} else {
			//check whether particular present.
			ArrayList particluarList = commonBo.getParticularList(rdto.getRegInitNumber());
			HashMap propMap = new HashMap();
			propMap = regForm.getMapPropertyTransParty();
			//regForm.setParticularCount(particluarList.size());
			if (particluarList != null && particluarList.size() > 0) {
				regForm.setParticularCount(particluarList.size());
				for (int i = 0; i < particluarList.size(); i++) {
					CommonDTO dto = new CommonDTO();
					dto = (CommonDTO) particluarList.get(i);
					ArrayList transPartyDets = getTransPartyDetsForPaticular(dto.getId(), rdto.getRegInitNumber(), languageLocale, rdto.getDeedID(), rdto.getInstID(), regForm);
					propMap.put(dto.getId() + "," + dto.getName(), transPartyDets);
				}
				regForm.setMapPropertyTransParty(propMap);
			} else {
				regForm.setParticularCount(0);
				ArrayList transPartyDets = commonBo.getTransPartyDetsNonPropDeed(rdto.getRegInitNumber(), rdto.getCommonDeedFlag(), languageLocale, rdto.getDeedID(), rdto.getInstID());
				regForm.setTransPartyListNonPropDeed(transPartyDets);
			}
		}
		if (dutyListArr != null && dutyListArr[5] != null) {
			if (!dutyListArr[5].trim().equalsIgnoreCase("") && !dutyListArr[5].trim().equalsIgnoreCase("null")) {
				rdto.setTotalPayableAmount(dutyListArr[5].trim());
			} else {
				rdto.setTotalPayableAmount("0");
			}
		} else {
			rdto.setTotalPayableAmount("0");
		}
		//below code for retrieving estamp code if total payable amount is more than zero
		//if(Double.parseDouble(regForm.getTotalPayableAmount())>0.0)
		//{
		String eCode = commonBo.getEstampCode(rdto.getRegInitNumber());
		if (eCode != null && !eCode.equalsIgnoreCase("")) {
			regForm.setRegInitEstampCode(eCode);
		} else {
			regForm.setRegInitEstampCode(null);
		}
		//}
		double balance = Double.parseDouble(rdto.getTotalPayableAmount()) - Double.parseDouble(rdto.getTotalPaidAmount());
		rdto.setTotalBalanceAmount(String.valueOf(balance));
		if (Double.parseDouble(rdto.getTotalBalanceAmount()) <= 0) {
			rdto.setPaymentCompleteFlag(1);
			//regForm.setRegInitEstampCode(null);
		} else {
			//regForm.setRegInitEstampCode(null);
		}
		setDutyInForm(regForm, dutyListArr, languageLocale);
		if ((rdto.getDeedID() == RegInitConstant.DEED_EXCHANGE || rdto.getDeedID() == RegInitConstant.DEED_PARTITION_PV) && regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
			//String finalMV=getExchangeDeedFinalMV(rdto.getRegInitNumber());
			String finalMV = commonBo.getExchangeDeedFinalMV(Integer.toString(regForm.getDuty_txn_id()));
			//String FinalMarketValue=propBO.updateFinalMarketValue(dto,propForm,language,userId);
			double finalMVDouble = Double.parseDouble(finalMV);
			//finalMVDouble=Math.ceil(finalMVDouble);
			finalMV = BigDecimal.valueOf(finalMVDouble).toPlainString();
			rdto.setExchangeDeedMrktValue(String.valueOf(Double.parseDouble(finalMV)));
		}
		rdto.setRegInitPaymntTxnId(null);
		//get all user enterable fields here...
		getAllUserEnterables(regForm, languageLocale);
	}

	/*public void landConfirmationScreen(RegCompCheckerForm regForm,String language) throws Exception {	   //add status for confirmation screen
		
		regForm.setMapPropertyTransParty(new HashMap());
		 
		int adjuFlag=0;
		RegCompCheckerDTO rdto = regForm.getRegDTO();
		String[] deedInstArray=getDeedInstId(rdto.getRegInitNumber());
		String purpose=getEStampPurpose(rdto.getRegInitNumber());    
		//String purpose="";
		if(purpose!=null && !purpose.equalsIgnoreCase("")){
			rdto.setPurpose(purpose.replace(RegInitConstant.SPLIT_CONSTANT, ","));
		}else{
			rdto.setPurpose("");
		}
		
			
		if(deedInstArray!=null && deedInstArray.length>0){
			
			//request.setAttribute("deedId", deedInstArray[0].trim());
			//request.setAttribute("instId", deedInstArray[1].trim());
			rdto.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
			rdto.setInstID(Integer.parseInt(deedInstArray[1].trim()));
			rdto.setCommonDeedFlag(commonBo.getCommonDeedFlag(deedInstArray[0].trim()));
			if(deedInstArray[2].trim().equals("-") || deedInstArray[2].trim().equalsIgnoreCase("")){
			rdto.setExmpID("");
			rdto.setHdnExAmount("");
			}else{
				rdto.setExmpID(deedInstArray[2].trim());
				//rdto.setHdnExAmount(deedInstArray[2].trim());
			}
			if(deedInstArray[3].trim()!=null && deedInstArray[3].trim().equalsIgnoreCase("R")){
				adjuFlag=1;
				logger.debug("--------adjudication flag-----"+adjuFlag);
				//if(deedInstArray[3].trim().length()==13){
					//String adjudicationID="0"+deedInstArray[3].trim();
					rdto.setAdjNumber(rdto.getRegInitNumber());
				//}else{
				//rdto.setAdjNumber(deedInstArray[3].trim());
				//}
			}
			
			if(rdto.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
				rdto.setCancelDeedRadio(Integer.parseInt(deedInstArray[4].trim()));
				}
			
		}else {
			//request.setAttribute("deedId", 0);
			//request.setAttribute("instId", 0);
			rdto.setDeedID(0);
			rdto.setInstID(0);
			rdto.setExmpID("");
			rdto.setHdnExAmount("");
		}
		
		rdto.setDeedtype1(getDeedName(Integer.toString(rdto.getDeedID()),language));
		rdto.setInstType(getInstrumentName(Integer.toString(rdto.getInstID()),language));
		//below code for exemptions
		
		String exemptions = rdto.getExmpID();
		regForm.setSelectedExemptionList(getExemptionList(exemptions));
		
		getExtraDetls(regForm,language);
		
		NumberFormat obj=new DecimalFormat("#0.00");
		String dutyListArr[]=new String[9];
		int numberOfProperties;
		
		if(rdto.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE || 
				(rdto.getDeedID()==RegInitConstant.DEED_TRUST && rdto.getInstID()==RegInitConstant.INST_TRUST_NPV_NP) ||
				rdto.getDeedID()==RegInitConstant.DEED_ADOPTION ||
				rdto.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA ||
				rdto.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
				rdto.getDeedID()==RegInitConstant.DEED_PARTITION_NPV  ||
				(rdto.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV && 
						rdto.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
				(rdto.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV && 
						rdto.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
				(rdto.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV && 
						rdto.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
						rdto.getCommonDeedFlag() == 1
				){
			
				//rdto.setDeedTypeFlag(0);
			
		
		if(rdto.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
				rdto.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
				rdto.getDeedID()==RegInitConstant.DEED_PARTITION_NPV){      
		rdto.setDeedTypeFlag(0);          // deedTypeFlag 0 for deeds where property details are required.
		ArrayList propertyIdList=getPropertyListNonPropDeed(rdto.getRegInitNumber());
		numberOfProperties=propertyIdList.size();
		regForm.setPropListNonPropDeed(propertyIdList);
			}else if(rdto.getCommonDeedFlag()==1){                 // for common deeds
				
			      
				//regForm.setDeedTypeFlag("");         
				ArrayList propertyIdList=getParticularList(rdto.getRegInitNumber());
				regForm.setPropListNonPropDeed(propertyIdList);
				numberOfProperties=1;
				//regForm.setPropListNonPropDeed(new ArrayList());
					
				
				
			}
		
		else{
				rdto.setDeedTypeFlag(1);  // deedTypeFlag 1 for deeds where property details are not required.
				regForm.setPropListNonPropDeed(null);
				numberOfProperties=1;
			}
		
		

		ArrayList transPartyDets=getTransPartyDetsNonPropDeed(rdto.getRegInitNumber(),rdto.getCommonDeedFlag());
		regForm.setTransPartyListNonPropDeed(transPartyDets);
		
		for(int j=0;j<transPartyDets.size();j++){
			
			CommonDTO dto=new CommonDTO();
			dto=(CommonDTO)transPartyDets.get(j);
			logger.debug("transacting party list  role------>"+dto.getRole());
			logger.debug("transacting party list  name------>"+dto.getName());
			logger.debug("transacting party list  id------>"+dto.getId());
		
		}
		
		if(adjuFlag==1){
			dutyListArr=getAdjudicatedDutyDetlsArr(rdto.getAdjNumber());   //to be changed
		}
		else{
			dutyListArr=getDutyDetlsForConfirmation(rdto.getRegInitNumber());
		}
		
		//forward="reginitConfirmNonPV";
		
		}
		
		else{
			
			if(rdto.getDeedID()==RegInitConstant.DEED_LEASE_NPV || 
					rdto.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
					rdto.getDeedID()==RegInitConstant.DEED_POA || 
					rdto.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV || 
					rdto.getDeedID()==RegInitConstant.DEED_WILL_NPV || 
					rdto.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV || 
					rdto.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV || 
					rdto.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV || 
					rdto.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV || 
					rdto.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
					(rdto.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
								 (rdto.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || rdto.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))||
					(rdto.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
		  						&& rdto.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
		  			(rdto.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
		  		  				&& rdto.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
		  			(rdto.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
		  		  		  		&& rdto.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)){
				rdto.setDeedTypeFlag(0);                          //// deedTypeFlag 0 for deeds where property details are required.
			} 
			
			
		HashMap propMap =new HashMap();
		propMap=regForm.getMapPropertyTransParty();
		//logger.debug("in confirmation action----------------------->");
		ArrayList propertyIdList=new ArrayList();
		//if(adjuFlag==1){
		//	propertyIdList=commonDao.getPropertyIdMarketValAdju(rdto.getRegInitNumber());  //to be changed
		//}else{
		    propertyIdList=getPropertyIdMarketVal(rdto.getRegInitNumber());
		//}
		double totalMarketVal=0;
		
		numberOfProperties=propertyIdList.size();
		
		for(int i=0;i<propertyIdList.size();i++){
			
			ArrayList row_list=new ArrayList();
			row_list=(ArrayList)propertyIdList.get(i);
			String propIds=row_list.toString();
			propIds=propIds.substring(1, propIds.length()-1);
			
			String propId[]=propIds.split(",");
			
			if(rdto.getDeedID()!=RegInitConstant.DEED_LEASE_NPV &&
					rdto.getDeedID()!=RegInitConstant.DEED_MORTGAGE_NPV &&
					rdto.getDeedID()!=RegInitConstant.DEED_POA &&
					rdto.getDeedID()!=RegInitConstant.DEED_SETTLEMENT_NPV &&
					rdto.getDeedID()!=RegInitConstant.DEED_WILL_NPV &&
					rdto.getDeedID()!=RegInitConstant.DEED_TRANSFER_LEASE_NPV &&
					rdto.getDeedID()!=RegInitConstant.DEED_SURRENDER_LEASE_NPV &&
					rdto.getDeedID()!=RegInitConstant.DEED_AGREEMENT_MEMO_NPV ){
			if(!propId[1].trim().equalsIgnoreCase("") && !propId[1].trim().equals(null)){
			totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
			}else{
				totalMarketVal=0;
			}
			}
			
			if(rdto.getDeedID()!=RegInitConstant.DEED_PARTITION_PV)
			{
				regForm.setPropListPVDeed(null);
	    	ArrayList transPartyDets=getTransPartyDets(propId[0].trim(),rdto.getRegInitNumber());
			for(int j=0;j<transPartyDets.size();j++){
				CommonDTO dto=new CommonDTO();
				dto=(CommonDTO)transPartyDets.get(j);
			}
			if(propId[0].trim().length()==15){
				propId[0]="0"+propId[0].trim();
			}
			propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
			
			}
			else{
				regForm.setPropListPVDeed(getPropertyListPVDeed(propertyIdList));
			}
			
		}
		
		if(rdto.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
			ArrayList transPartyDets=getTransPartyDetsNonPropDeed(rdto.getRegInitNumber(),rdto.getCommonDeedFlag());
			regForm.setTransPartyListPVDeed(transPartyDets);
		}else{
			regForm.setTransPartyListPVDeed(null);
		}
		
		rdto.setTotalMarketValue(obj.format(totalMarketVal));
		regForm.setMapPropertyTransParty(propMap);
		if(adjuFlag==1){
			dutyListArr=getAdjudicatedDutyDetlsArr(rdto.getAdjNumber());   //to be changed
		}
		else{
			dutyListArr=getDutyDetlsForConfirmation(rdto.getRegInitNumber());
		}
		
		//forward="reginitConfirm";
		}
		
		
		
		//String dutyListArr[]=new String[9];
		
		if(adjuFlag==1){
			dutyListArr=commonBo.getAdjudicatedDutyDetls(rdto.getAdjudicationNumber());   //to be changed
		}
		else{
			dutyListArr=commonBo.getDutyDetlsForConfirmation(rdto.getHidnRegTxnId());
		}
		
		double totalPaidAmt=0;
		ArrayList paidAmtList=getAllPaidAmounts(rdto.getRegInitNumber());
		for(int i=0;i<paidAmtList.size();i++){
			ArrayList row_list=new ArrayList();
			row_list=(ArrayList)paidAmtList.get(i);
			String amnts=row_list.toString();
			amnts=amnts.substring(1, amnts.length()-1);
			//logger.debug("paid amount list-->"+amnts);
			String amntsArr[]=amnts.split(",");
			
			totalPaidAmt=totalPaidAmt+Double.parseDouble(amntsArr[0].trim());
		}
		rdto.setTotalPaidAmount(obj.format(totalPaidAmt));
		
		
		
			if(numberOfProperties==1)
			{
				rdto.setTotalPayableAmount(obj.format(Double.parseDouble(dutyListArr[5].trim())));
				
				//below code for retrieving estamp code if total payable amount is more than zero
				if(Double.parseDouble(rdto.getTotalPayableAmount())>0.0)
				{
					String eCode=getEstampCode(rdto.getRegInitNumber());
					
					if(eCode!=null && !eCode.equalsIgnoreCase("")){
						rdto.setRegInitEstampCode(eCode);	
					}
					else{
						rdto.setRegInitEstampCode(null);
					}
					
					
				}
				double balance=Double.parseDouble(rdto.getTotalPayableAmount())-Double.parseDouble(rdto.getTotalPaidAmount());
				rdto.setTotalBalanceAmount(obj.format(balance));
				
				if(Double.parseDouble(rdto.getTotalBalanceAmount())==0){
					rdto.setPaymentCompleteFlag(1);
					//rdto.setRegInitEstampCode(null);
				}else{
					//rdto.setRegInitEstampCode(null);
				}
				
				setDutyInForm(regForm,dutyListArr,language);
				
			rdto.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
			rdto.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
			rdto.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
			rdto.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
			rdto.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
			rdto.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
			            			
			rdto.setIsMultiplePropsFlag(0);
			rdto.setIsDutyCalculated(1);
			
			//if(adjuFlag==0){
				if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
				rdto.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
				else
					rdto.setMarketValueShares(Double.toString(0.0));	
				
				if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
				rdto.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
				else
					rdto.setDutyPaid(Double.toString(0.0));
				
				if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
				rdto.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
				else
					rdto.setRegPaid(Double.toString(0.0));
				
				if(rdto.getDeedID()!=RegInitConstant.DEED_POA){
				if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
					rdto.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
					else
						rdto.setConsiAmountTrns(Double.toString(0.0));
				}else if(rdto.getInstID()==RegInitConstant.INST_POA_2){

					if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
						rdto.setConsiAmountTrns(dutyListArr[9].trim());
						else
							rdto.setConsiAmountTrns("-");
					
				}
				}
				else{
					rdto.setMarketValueShares(Double.toString(0.0));	
					rdto.setDutyPaid(Double.toString(0.0));
					rdto.setRegPaid(Double.toString(0.0));
				}
			
			}else if(numberOfProperties>1 && (dutyListArr==null)){
				
				rdto.setIsDutyCalculated(0);
				rdto.setIsMultiplePropsFlag(1);
				
			}else if(numberOfProperties>1 && dutyListArr!=null){
				
				rdto.setTotalPayableAmount(obj.format(Double.parseDouble(dutyListArr[5].trim())));
				double balance=Double.parseDouble(rdto.getTotalPayableAmount())-Double.parseDouble(rdto.getTotalPaidAmount());
				rdto.setTotalBalanceAmount(obj.format(balance));
				
				//below code for retrieving estamp code if total payable amount is more than zero
				if(Double.parseDouble(rdto.getTotalPayableAmount())>0.0)
				{
					String eCode=getEstampCode(rdto.getRegInitNumber());
					
					if(eCode!=null && !eCode.equalsIgnoreCase("")){
						rdto.setRegInitEstampCode(eCode);	
					}
					else{
						rdto.setRegInitEstampCode(null);
					}
					
					
				}
				
				if(Double.parseDouble(rdto.getTotalBalanceAmount())==0){
					rdto.setPaymentCompleteFlag(1);
					//rdto.setRegInitEstampCode(null);
				}else{
					//rdto.setRegInitEstampCode(null);
				}
				
				rdto.setIsDutyCalculated(1);
				rdto.setIsMultiplePropsFlag(1);
				
				setDutyInForm(regForm,dutyListArr,language);
				rdto.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
				rdto.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
				rdto.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
				rdto.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
				rdto.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
				rdto.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
				
				//if(adjuFlag==0){
				if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
				rdto.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
				else
					rdto.setMarketValueShares(Double.toString(0.0));	
				
				if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
				rdto.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
				else
					rdto.setDutyPaid(Double.toString(0.0));
				
				if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
				rdto.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
				else
					rdto.setRegPaid(Double.toString(0.0));
				
				if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
					rdto.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
					else
						rdto.setConsiAmountTrns(Double.toString(0.0));
				if(rdto.getDeedID()!=RegInitConstant.DEED_POA){
					if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
						rdto.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
						else
							rdto.setConsiAmountTrns(Double.toString(0.0));
					}else if(rdto.getInstID()==RegInitConstant.INST_POA_2){

						if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
							rdto.setConsiAmountTrns(dutyListArr[9].trim());
							else
								rdto.setConsiAmountTrns("-");
						
					}
				}
				else{
					rdto.setMarketValueShares(Double.toString(0.0));	
					rdto.setDutyPaid(Double.toString(0.0));
					rdto.setRegPaid(Double.toString(0.0));
				}
				
			}
			if(rdto.getDeedID()==RegInitConstant.DEED_EXCHANGE ||
					rdto.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
				
				String finalMV=getExchangeDeedFinalMV(rdto.getRegInitNumber());
				
			rdto.setExchangeDeedMrktValue(obj.format(Double.parseDouble(finalMV)));
			}
			
			rdto.setRegInitPaymntTxnId(null);
			
			
		}*/
	/**
	 * for getting deed name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getDeedName(String deedId, String language) throws Exception {
		return commonDao.getDeedName(deedId, language);
	}

	/**
	 * for getting instrument name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getInstrumentName(String instId, String language) throws Exception {
		return commonDao.getInstrumentName(instId, language);
	}

	/**
	 * getExchangeDeedDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String[] getExchangeDeedFinalMV(String appId) throws Exception {
		//method for getting reference valuation id in case of exchange deed
		ArrayList list = commonBd.getExchangeDeedDutyDetls(appId);
		if (list != null && list.size() == 1) {
			String dutyList = list.toString();
			dutyList = dutyList.substring(2, dutyList.length() - 2);
			String dutyListArr[] = dutyList.split(",");
			return dutyListArr;
		} else {
			return null;
		}
	}

	/**
	 * getRefValIdExchngDeed for getting reference valuation id in case of
	 * exchange deed
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	/*public String getRefValIdExchngDeed(String appId) throws Exception {
		
		
		
		return commonDao.getRefValIdExchngDeed(appId);
		
	}*/
	public void setDutyInForm(RegCompCheckerForm regForm, String[] dutyListArr, String lang) throws Exception {
		RegCompCheckerDTO rdto = regForm.getRegDTO();
		NumberFormat obj = new DecimalFormat("#0.00");
		try {
			/*rdto.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
			rdto.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
			rdto.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
			rdto.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
			rdto.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
			rdto.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));*/
			if (dutyListArr != null) {
				rdto.setStampduty1(BigDecimal.valueOf(Double.parseDouble(dutyListArr[0].trim())).toPlainString());
				rdto.setNagarPalikaDuty(BigDecimal.valueOf(Double.parseDouble(dutyListArr[2].trim())).toPlainString());
				rdto.setPanchayatDuty(BigDecimal.valueOf(Double.parseDouble(dutyListArr[1].trim())).toPlainString());
				rdto.setUpkarDuty(BigDecimal.valueOf(Double.parseDouble(dutyListArr[3].trim())).toPlainString());
				rdto.setTotaldutyBeforeExemp(BigDecimal.valueOf(Double.parseDouble(dutyListArr[5].trim())).toPlainString());
				rdto.setRegistrationFeeBeforeExemp(BigDecimal.valueOf(Double.parseDouble(dutyListArr[4].trim())).toPlainString());
				//rdto.setTotalduty(BigDecimal.valueOf(Double.parseDouble(dutyListArr[5].trim())).toPlainString());
				//rdto.setRegistrationFee(BigDecimal.valueOf(Double.parseDouble(dutyListArr[4].trim())).toPlainString());
				if (dutyListArr[11] != null && !dutyListArr[11].trim().equalsIgnoreCase("") && !dutyListArr[11].trim().equalsIgnoreCase("null")) {
					rdto.setTotalduty(BigDecimal.valueOf(Double.parseDouble(dutyListArr[11].trim())).toPlainString());
				} else {
					rdto.setTotalduty(BigDecimal.valueOf(0.0).toPlainString());
				}
				if (dutyListArr[10] != null && !dutyListArr[10].trim().equalsIgnoreCase("") && !dutyListArr[10].trim().equalsIgnoreCase("null")) {
					rdto.setRegistrationFee(BigDecimal.valueOf(Double.parseDouble(dutyListArr[10].trim())).toPlainString());
				} else {
					rdto.setRegistrationFee(BigDecimal.valueOf(0.0).toPlainString());
				}
				if (dutyListArr[12] != null && !dutyListArr[12].trim().equalsIgnoreCase("") && !dutyListArr[12].trim().equalsIgnoreCase("null")) {
					rdto.setExempStamp(BigDecimal.valueOf(Double.parseDouble(dutyListArr[12].trim())).toPlainString());
				} else {
					rdto.setExempStamp(BigDecimal.valueOf(0.0).toPlainString());
				}
				if (dutyListArr[13] != null && !dutyListArr[13].trim().equalsIgnoreCase("") && !dutyListArr[13].trim().equalsIgnoreCase("null")) {
					rdto.setExempReg(BigDecimal.valueOf(Double.parseDouble(dutyListArr[13].trim())).toPlainString());
				} else {
					rdto.setExempReg(BigDecimal.valueOf(0.0).toPlainString());
				}
				//if(adjuFlag==0){
				if (dutyListArr[6] != null && !dutyListArr[6].trim().equalsIgnoreCase("") && !dutyListArr[6].trim().equalsIgnoreCase("null")) {
					rdto.setMarketValueShares(BigDecimal.valueOf(Double.parseDouble(dutyListArr[6].trim())).toPlainString());
				} else {
					rdto.setMarketValueShares(Double.toString(0.0));
				}
				if (dutyListArr[7] != null && !dutyListArr[7].trim().equalsIgnoreCase("") && !dutyListArr[7].trim().equalsIgnoreCase("null")) {
					rdto.setDutyPaid(BigDecimal.valueOf(Double.parseDouble(dutyListArr[7].trim())).toPlainString());
				} else {
					rdto.setDutyPaid(Double.toString(0.0));
				}
				if (dutyListArr[8] != null && !dutyListArr[8].trim().equalsIgnoreCase("") && !dutyListArr[8].trim().equalsIgnoreCase("null")) {
					rdto.setRegPaid(BigDecimal.valueOf(Double.parseDouble(dutyListArr[8].trim())).toPlainString());
				} else {
					rdto.setRegPaid(Double.toString(0.0));
				}
				/*if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
					rdto.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
					else
						rdto.setConsiAmountTrns(Double.toString(0.0));*/
				if (rdto.getDeedID() != RegInitConstant.DEED_POA || rdto.getInstID() == RegInitConstant.INST_POA_1) {
					if (dutyListArr[9].trim() != null && !dutyListArr[9].trim().equalsIgnoreCase("") && !dutyListArr[9].trim().equalsIgnoreCase("null"))
						rdto.setConsiAmountTrns(BigDecimal.valueOf(Double.parseDouble(dutyListArr[9].trim())).toPlainString()); //extra field set for all the deeds other than poa
					else
						rdto.setConsiAmountTrns(Double.toString(0.0));
				} else if (rdto.getInstID() == RegInitConstant.INST_POA_2) {
					if (dutyListArr[9].trim() != null && !dutyListArr[9].trim().equalsIgnoreCase("") && !dutyListArr[9].trim().equalsIgnoreCase("null"))
						rdto.setConsiAmountTrns(dutyListArr[9].trim()); //number of persons for inst 2
					else
						rdto.setConsiAmountTrns("-");
				}
			} else {
				rdto.setStampduty1("0.0");
				rdto.setNagarPalikaDuty("0.0");
				rdto.setPanchayatDuty("0.0");
				rdto.setUpkarDuty("0.0");
				rdto.setTotalduty("0.0");
				rdto.setRegistrationFee("0.0");
				rdto.setTotaldutyBeforeExemp("0.0");
				rdto.setRegistrationFeeBeforeExemp("0.0");
				rdto.setMarketValueShares(Double.toString(0.0));
				rdto.setDutyPaid(Double.toString(0.0));
				rdto.setRegPaid(Double.toString(0.0));
				rdto.setConsiAmountTrns(Double.toString(0.0));
			}
			//if(adjuFlag==0){
			//if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
			//rdto.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
			//else
			//rdto.setMarketValueShares(Double.toString(0.0));	
			//if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
			//rdto.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
			//else
			//rdto.setDutyPaid(Double.toString(0.0));
			//if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
			//rdto.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
			//else
			//rdto.setRegPaid(Double.toString(0.0));
			/*if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
				regForm.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
				else
					regForm.setConsiAmountTrns(Double.toString(0.0));*/
			//if(rdto.getDeedID()!=RegInitConstant.DEED_POA || rdto.getInstID()==RegInitConstant.INST_POA_1){
			//if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
			//	rdto.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));   //extra field set for all the deeds other than poa
			//	else
			//rdto.setConsiAmountTrns(Double.toString(0.0));
			//	}else if(rdto.getInstID()==RegInitConstant.INST_POA_2){
			//if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
			//	rdto.setConsiAmountTrns(dutyListArr[9].trim());                               //number fo persons for inst 2
			//else
			//rdto.setConsiAmountTrns("-");
			//}
			rdto.setExtraFieldLabel(getExtraFieldLabel(Integer.toString(rdto.getInstID()), lang));
			ArrayList list = new ArrayList();
			list.add(rdto);
			regForm.setDutyList(list);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
		}
	}

	/**
	 * for getting extra field label from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExtraFieldLabel(String instId, String lang) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		String val = commonDao.getExtraFieldLabel(instId, lang);
		if (val != null & !val.equalsIgnoreCase("")) {
			return val;
		} else {
			return RegInitConstant.EXTRA_FIELD_NOT_APPLICABLE;
		}
	}

	/**
	 * getEstampCode for getting eStamp code from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getEstampCode(String regId) throws Exception {
		return commonDao.getEstampCode(regId);
	}

	/**
	 * getPaidAmounts for getting all paid amounts details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPaidAmounts(String appId) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getAllPaidAmounts(appId);
	}

	/**
	 * getDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getDutyDetlsForConfirmation(String appId) throws Exception {
		String dutyListArr[] = null;
		ArrayList list = commonDao.getDutyDetlsForConfirmation(appId);
		if (list != null && !(list.size() < 1)) {
			for (int i = 0; i < 1; i++) {
				String dutyList = list.toString();
				dutyList = dutyList.substring(2, dutyList.length() - 2);
				dutyListArr = dutyList.split(",");
			}
		}
		return dutyListArr;
	}

	/**
	 * getTransPartyDetsNonPropDeed for getting transacting party details
	 * corresponding to a registration ID from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDetsNonPropDeed(String appId, int commonDeedFlag) throws Exception {
		ArrayList list;
		if (commonDeedFlag == 0) {
			list = commonDao.getTransPartyDetsNonPropDeed(appId);
		} else {
			list = commonDao.getTransPartyDetsCommonDeed(appId); // change for common deed	
		}
		ArrayList finalList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList row_list = (ArrayList) list.get(i);
				String row = row_list.toString();
				row = row.substring(1, row.length() - 1);
				String rowArr[] = row.split(",");
				CommonDTO dto = new CommonDTO();
				dto.setRole(rowArr[0].trim());
				if (!(rowArr[1].trim().equals("")) && !(rowArr[1].trim().equals("null"))) {
					dto.setName(rowArr[1].trim() + " " + rowArr[2].trim());
				} else if (!(rowArr[3].trim().equals("")) && !(rowArr[3].trim().equals("null"))) {
					dto.setName(rowArr[3].trim());
				}
				dto.setId(rowArr[4].trim());
				dto.setRoleId(rowArr[5].trim());
				finalList.add(dto);
			}
			return finalList;
		} else {
			return null;
		}
	}

	/**
	 * for getting e stamp purpose from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getEStampPurpose(String appId) throws Exception {
		return commonDao.getEStampPurpose(appId);
	}

	/**
	 * getPropertyIdMarketVal for getting PROPERTY ID AND MARKET VALUE details
	 * from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdMarketVal(String appId) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyIdMarketVal(appId);
	}

	/**
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyListNonPropDeed(String appId) throws Exception {
		//return commonBd.getPropertyListNonPropDeed(appId);
		ArrayList list = commonDao.getPropertyListNonPropDeed(appId);
		ArrayList finalList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList row_list = (ArrayList) list.get(i);
				String row = row_list.toString();
				row = row.substring(1, row.length() - 1);
				String rowArr[] = row.split(",");
				CommonDTO dto = new CommonDTO();
				if (rowArr[0].trim().length() == 15) {
					rowArr[0] = "0" + rowArr[0].trim();
				}
				if (rowArr[0].trim().length() == 15) {
					dto.setId("0" + rowArr[0].trim());
				} else {
					dto.setId(rowArr[0].trim());
				}
				/*if(!(rowArr[1].trim().equals("")) && !(rowArr[1].trim().equals("null"))){
					dto.setName(rowArr[1].trim()+" "+rowArr[2].trim());
				}
				else if(!(rowArr[3].trim().equals("")) && !(rowArr[3].trim().equals("null"))){
					dto.setName(rowArr[3].trim());
				}
				dto.setId(rowArr[4].trim());
				dto.setRoleId(rowArr[5].trim());*/
				finalList.add(dto);
			}
			return finalList;
		} else {
			return null;
		}
	}

	/**
	 * @param String[]
	 * @return ArrayList
	 */
	public ArrayList getExemptionList(String exemptions, String lang) throws Exception {
		if (exemptions != null && !exemptions.equalsIgnoreCase("")) {
			String[] exempArray = exemptions.split("-");
			ArrayList exemp = new ArrayList();
			if (exempArray != null && exempArray.length > 0) {
				for (int i = 0; i < exempArray.length; i++) {
					ExemptionDTO dto = new ExemptionDTO();
					dto.setExemType(commonDao.getExemptionName(exempArray[i], lang));
					exemp.add(dto);
				}
				return exemp;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * getPropertyListPVDeed for getting PROPERTY ID list from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyListPVDeed(ArrayList list) throws Exception {
		//return commonBd.getPropertyListNonPropDeed(appId);
		//ArrayList list=commonBd.getPropertyListNonPropDeed(appId);
		ArrayList finalList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList row_list = (ArrayList) list.get(i);
				String row = row_list.toString();
				row = row.substring(1, row.length() - 1);
				String rowArr[] = row.split(",");
				CommonDTO dto = new CommonDTO();
				if (rowArr[0].trim().length() == 15) {
					rowArr[0] = "0" + rowArr[0].trim();
				}
				if (rowArr[0].trim().length() == 15) {
					dto.setId("0" + rowArr[0].trim());
				} else {
					dto.setId(rowArr[0].trim());
				}
				dto.setMarketValue(rowArr[1].trim());
				finalList.add(dto);
			}
			return finalList;
		} else {
			return null;
		}
	}

	/**
	 * getTransPartyDets for getting transacting party details corresponding to
	 * a PROPERTY ID from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDets(String propId, String appId) throws Exception {
		ArrayList list = commonDao.getTransPartyDets(propId, appId);
		ArrayList finalList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList row_list = (ArrayList) list.get(i);
				String row = row_list.toString();
				row = row.substring(1, row.length() - 1);
				String rowArr[] = row.split(",");
				CommonDTO dto = new CommonDTO();
				dto.setRole(rowArr[0].trim());
				if (!(rowArr[1].trim().equals("")) && !(rowArr[1].trim().equals("null"))) {
					dto.setName(rowArr[1].trim() + " " + rowArr[2].trim());
				} else if (!(rowArr[3].trim().equals("")) && !(rowArr[3].trim().equals("null"))) {
					dto.setName(rowArr[3].trim());
				}
				dto.setId(rowArr[4].trim());
				dto.setRoleId(rowArr[5].trim());
				finalList.add(dto);
			}
			return finalList;
		} else {
			return null;
		}
	}

	/**
	 * getExemptionList for getting exemption ids from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String getExemptionIDList(String appId) throws Exception {
		String exemp = "";
		ArrayList list = commonDao.getExemptionList(appId);
		ArrayList rowList;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				rowList = (ArrayList) list.get(i);
				String str = rowList.toString();
				str = str.substring(1, str.length() - 1);
				exemp = exemp + str + "-";
			}
		}
		return exemp;
	}

	public boolean modifyMvNPV(RegCompCheckerDTO rDTO, String userId, String tDate) throws Exception {
		boolean modify = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String dutyDetails[] = new String[10];
		////////////////////////////////COMMENTED BY SIMRAN 6 -JUNE, 2013///////////////////////////
		/*double stmp2 = 0;
		double reg2 = 0;
		String dutyList2 = null;
		ArrayList list2 = regCompDAO.getChangedDutyAtMaker(rDTO.getRegInitNumber());
		if(list2.size()>0)
		{
			dutyList2 = list2.toString();
			dutyList2 = dutyList2.substring(2, dutyList2.length()-2);
			String tempArr[] = dutyList2.split(",");
			 stmp2 =  Double.parseDouble(tempArr[1].trim());
			 reg2 =  Double.parseDouble(tempArr[0].trim());
		}
		Double stmp = Double.parseDouble(rDTO.getStampduty1())-stmp2;
		Double reg = Double.parseDouble(rDTO.getRegistrationFee())-reg2;
		Double total = Double.parseDouble(rDTO.getTotalduty())-stmp2;
		rDTO.setStampduty1(stmp.toString());
		rDTO.setRegistrationFee(reg.toString());
		rDTO.setTotalduty(total.toString());*/
		dutyDetails[0] = rDTO.getRegInitNumber();
		double stamp = Math.ceil(Double.parseDouble(rDTO.getStampdutyM()));
		String stampDutyModi = Double.toString(stamp);
		rDTO.setStampdutyM(stampDutyModi);
		double panchayat = Math.ceil(Double.parseDouble(rDTO.getPanchayatDutyM()));
		String panchayatModi = Double.toString(panchayat);
		rDTO.setPanchayatDutyM(panchayatModi);
		double NagarPalika = Math.ceil(Double.parseDouble(rDTO.getNagarPalikaDutyM()));
		String NagarPalikaModi = Double.toString(NagarPalika);
		rDTO.setNagarPalikaDutyM(NagarPalikaModi);
		double Upkar = Math.ceil(Double.parseDouble(rDTO.getUpkarDutyM()));
		String UpkarModi = Double.toString(Upkar);
		rDTO.setUpkarDutyM(UpkarModi);
		double regFee = Math.ceil(Double.parseDouble(rDTO.getRegistrationFeeM()));
		String regFeeModi = Double.toString(regFee);
		rDTO.setRegistrationFeeM(regFeeModi);
		double totalDuty = Math.ceil(Double.parseDouble(rDTO.getTotaldutyM()));
		String totalDutyModi = Double.toString(totalDuty);
		rDTO.setTotaldutyM(totalDutyModi);
		dutyDetails[1] = rDTO.getStampdutyM();
		dutyDetails[2] = rDTO.getPanchayatDutyM();
		dutyDetails[3] = rDTO.getNagarPalikaDutyM();
		dutyDetails[4] = rDTO.getUpkarDutyM();
		dutyDetails[5] = rDTO.getRegistrationFeeM();
		dutyDetails[6] = rDTO.getTotaldutyM();
		dutyDetails[7] = userId;
		dutyDetails[8] = tDate;
		dutyDetails[9] = "";
		//	logger.debug("size of array in bd"+dutyDetails.length);
		modify = regCompDAO.modifyDuties(dutyDetails, rDTO.getRegInitNumber());
		if (modify) {
			String arr[] = { rDTO.getRemarks(), RegCompCheckerConstant.FILE_NAME_SUPP_DOC, rDTO.getSupportingDocPath(), rDTO.getRegInitNumber() };
			modify = regCompDAO.modifyMVdetails(arr);
		}
		return modify;
	}

	/**
	 * @param edto
	 * @param officeId
	 * @return
	 * @throws Exception
	 */
	public RegCompCheckerForm getLoggedInDistrictDetls(RegCompCheckerForm eForm, String officeId, String lang) throws Exception {
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();
		RegCompCheckerDTO edto = eForm.getRegDTO();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getLoggedInDistrictDetls(officeId, lang);
		for (int i = 0; i < list.size(); i++) {
			subList = (ArrayList) list.get(i);
			edto.setSroName((String) subList.get(0));
			edto.setDistrictName((String) subList.get(1));
			edto.setSroId(officeId);
			edto.setDistId((String) subList.get(2));
		}
		eForm.setRegDTO(edto);
		return eForm;
	}

	/**
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public String[] getAdjudicatedDutyDetlsArr(String appId) throws Exception {
		String dutyListArr[] = null;
		ArrayList list = commonDao.getDutyDetlsAdjuForConfirmation(appId);
		if (list != null && !(list.size() < 1)) {
			String dutyList = list.toString();
			dutyList = dutyList.substring(2, dutyList.length() - 2);
			dutyListArr = dutyList.split(",");
		}
		return dutyListArr;
	}

	/**
	 * @param regInitId
	 * @param officeCode
	 * @return
	 * @throws Exception
	 */
	public String emailAlertHold(String regInitId, String officeCode) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String districtCode = regCompDAO.getDistrictCodeChecker(officeCode);
		String emailSub = RegCompCheckerConstant.APPLICATION_ON_HOLD_SUB;
		String emailContent = "Execution of the registration document (Registration Initiation Id - " + regInitId + ") is kept on hold. Please acknowledge whether to proceed or not";
		return regCompDAO.emailAlert(regInitId, emailSub, emailContent, districtCode);
	}

	/**
	 * getParticularList for getting particular list from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getParticularList(String appId) throws Exception {
		//return commonBd.getPropertyListNonPropDeed(appId);
		ArrayList list = commonDao.getParticularList(appId);
		ArrayList finalList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList row_list = (ArrayList) list.get(i);
				String row = row_list.toString();
				row = row.substring(1, row.length() - 1);
				String rowArr[] = row.split(",");
				CommonDTO dto = new CommonDTO();
				/*if(rowArr[0].trim().length()==15){
					rowArr[0]="0"+rowArr[0].trim();
				}
				if(rowArr[0].trim().length()==15){
					dto.setId("0"+rowArr[0].trim());
				}else{*/
				dto.setId(rowArr[0].trim());
				dto.setName(rowArr[1].trim());
				//}
				/*if(!(rowArr[1].trim().equals("")) && !(rowArr[1].trim().equals("null"))){
					dto.setName(rowArr[1].trim()+" "+rowArr[2].trim());
				}
				else if(!(rowArr[3].trim().equals("")) && !(rowArr[3].trim().equals("null"))){
					dto.setName(rowArr[3].trim());
				}
				dto.setId(rowArr[4].trim());
				dto.setRoleId(rowArr[5].trim());*/
				finalList.add(dto);
			}
			return finalList;
		} else {
			return null;
		}
	}

	/**
	 * @param rdto
	 * @param regForm
	 * @throws Exception
	 */
	public void getParticularDetails(RegCompCheckerDTO rdto, RegCompCheckerForm regForm) throws Exception {
		ArrayList list = commonDao.getParticularDetails(rdto.getParticularTxnId());
		if (list != null && list.size() > 0) {
			String str = list.toString();
			str = str.substring(2, str.length() - 2);
			String strArr[] = str.split(",");
			rdto.setParticularName(strArr[0].trim());
			rdto.setParticularDesc(strArr[1].trim());
			//return strArr;
		} else {
			rdto.setParticularName("-");
			rdto.setParticularDesc("-");
			//return null;
		}
		//RegCommonDAO dao = new RegCommonDAO();
		//return commonBd.getBankDetails(appId);
		regForm.setRegDTO(rdto);
	}

	/**
	 * @param regInitId
	 * @return
	 * @throws Exception
	 */
	public boolean checkRefusalStatus(String regInitId) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String status = regCompDAO.checkStatus(regInitId);
		if (status.equalsIgnoreCase("0")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * convertDOBNew for convert dob from db into user readable form
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String convertDOBNew(String date) throws Exception {
		//String sysdate=commonBd.getCurrDateTime();
		//String transDate1=paymentBD.gettranDate(eForm);
		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = date1.parse(date);
		String formatDate = date2.format(d1);
		System.out.println("formatted date=----->" + formatDate);
		return formatDate;
	}

	/**
	 * @param eForm
	 * @param userId
	 * @param date
	 * @param regNum
	 * @return
	 * @throws Exception
	 */
	public boolean updateBiometricDetails(RegCompCheckerForm eForm, String userId, String date, String regNum) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.updateBiometricDetails(eForm, userId, date, regNum);
	}

	/**
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getConsenterDetails(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list1 = regCompDAO.getConsenterDetails(regInitId);
		RegCompCheckerDTO rDTO;
		ArrayList list2 = new ArrayList();
		ArrayList witnessDetails = new ArrayList();
		for (int i = 0; i < list1.size(); i++) {
			list2 = (ArrayList) list1.get(i);
			rDTO = new RegCompCheckerDTO();
			rDTO.setWitnessTxnNummber((String) list2.get(0));
			rDTO.setFnameTrns((String) list2.get(1));
			rDTO.setLnameTrns((String) list2.get(2));
			rDTO.setIndaddressTrns((String) list2.get(3));
			rDTO.setAgeTrns((String) list2.get(4));
			rDTO.setConsenterPhotoPath((String) list2.get(5));
			witnessDetails.add(rDTO);
		}
		return witnessDetails;
	}

	/**
	 * @param witnessTxnId
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateConsenterDetails(String witnessTxnId, String date, String userId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.updateConsenterStatus(witnessTxnId, date, userId);
	}

	/**
	 * @param regInitId
	 * @param witnessTxnId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCompleteConsenterDetails(String regInitId, String witnessTxnId) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList witnessList = new ArrayList();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		list = regCompDAO.getCompleteConsenterDetails(regInitId, witnessTxnId);
		for (int i = 0; i < list.size(); i++) {
			ArrayList list1 = (ArrayList) list.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			rDTO.setWitFirstName((String) list1.get(0));
			logger.debug("^^^^^^^^^^^^^in BD" + list1.get(1));
			rDTO.setWitMiddleName((String) list1.get(1));
			rDTO.setWitLastName((String) list1.get(2));
			rDTO.setWitGender((String) list1.get(3));
			rDTO.setWitAge((String) list1.get(4));
			rDTO.setWitFatherName((String) list1.get(5));
			rDTO.setWitMotherName((String) list1.get(6));
			rDTO.setWitSpouseName((String) list1.get(7));
			rDTO.setWitNationality((String) list1.get(8));
			rDTO.setWitAddress((String) list1.get(9));
			rDTO.setWitPhoneNumber((String) list1.get(10));
			rDTO.setRelationshipWit((String) list1.get(11));
			//rDTO.setWitLoanInfo((String)list1.get(11));
			//rDTO.setWitPendingTaxDuties((String)list1.get(12));
			witnessList.add(rDTO);
		}
		return witnessList;
	}

	/**
	 * @param rDTO
	 * @param witnessTxnId
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateAndSaveConsenterDetails(RegCompCheckerDTO rDTO, String witnessTxnId, String date, String userId) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		boolean flag1 = regCompDAO.updateConsenterStatus(witnessTxnId, date, userId);
		logger.debug("update flag" + flag1);
		if (flag1) {
			flag = regCompDAO.saveConsenterDetails(rDTO, date, userId);
		}
		return flag;
	}

	/**
	 * @param rDTO
	 * @param date
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveConsenterDetails(RegCompCheckerDTO rDTO, String date, String userId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.saveConsenterDetails(rDTO, date, userId);
	}

	/**
	 * @param regNumber
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getConsenterDet(String regNumber, String language) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.getConsenterDet(regNumber);
		ArrayList sublist = new ArrayList();
		ArrayList mainList = new ArrayList();
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				sublist = (ArrayList) list.get(i);
				RegCompCheckerDTO dto = new RegCompCheckerDTO();
				dto.setConsentorTxnNumber((String) sublist.get(0));
				dto.setFnameTrns((String) sublist.get(1));
				//dto.setLnameTrns((String)sublist.get(2));
				dto.setLnameTrns("");
				if ("en".equalsIgnoreCase(language)) {
					dto.setRoleName("Consenter");
				} else {
					dto.setRoleName("सहमतिकर्ता");
				}
				dto.setPhotoTypeId("Not Available");
				dto.setPhotoProofTypeName("Not Available");
				String photoName = (String) sublist.get(4);
				String photoPath = (String) sublist.get(5);
				String thumbName = (String) sublist.get(6);
				String thumbPath = (String) sublist.get(7);
				String signName = (String) sublist.get(8);
				String signPath = (String) sublist.get(9);
				dto.setSelectedPhotoId(sublist.get(10) != null ? sublist.get(10).toString() : "Not Available"); //added by ankit for Aadhar EKYC
				//added by ankit for Aadhar EKYC - Start
				AadharDetailDTO aadharDts = new RegCompCheckerBD().getAadharDetailsByConstTxnNum(dto.getConsentorTxnNumber());
				if (aadharDts.getACKID_CHECKER() != null && aadharDts != null && !aadharDts.getACKID_CHECKER().isEmpty()) {
					dto.setIsAadharValidated("true");
				} else {
					dto.setIsAadharValidated("false");
				}
				//added by ankit for Aadhar EKYC - end
				if (photoName != null && !photoName.equalsIgnoreCase("")) {
					dto.setPhotoName(photoName);
				} else {
					dto.setPhotoName("");
				}
				if (photoPath != null && !photoPath.equalsIgnoreCase("")) {
					dto.setCnsntrPicChk("Y");
					dto.setPhotoPath(photoPath);
				} else {
					dto.setCnsntrPicChk("N");
					dto.setPhotoPath("");
				}
				dto.setCnsntrThumbChk("N");
				dto.setCnsntrSignChk("N");
				if (thumbName != null && !thumbName.equalsIgnoreCase("")) {
					dto.setCnsntrThumbChk("Y");
					dto.setThumbName(thumbName);
					dto.setThumbPath(thumbPath);
				}
				if (signName != null && !signName.equalsIgnoreCase("")) {
					dto.setCnsntrSignChk("Y");
					dto.setSignatureName(signName);
					dto.setSignaturePath(signPath);
				}
				mainList.add(dto);
			}
		}
		return mainList;
	}

	/**
	 * @param rdto
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean insertSrSignDetails(RegCompCheckerDTO rdto, String userId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.insertSrSignDetails(rdto, userId);
	}

	/*public String getDeedIdCompletion(String regNum) throws Exception{
		return regCompDAO.getDeedIdCompletion(regNum);
	}
	
	public String getInstIdCompletion(String regNum) throws Exception{
		return regCompDAO.getInstIdCompletion(regNum);
	}*/
	public ArrayList getRelationshipList(String gender, String language) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getRelationshipList(gender, language);
	}

	/**
	 * @param linkHistoryDetails
	 * @return boolean
	 * @throws Exception
	 */
	public boolean upadtePinDetails(ArrayList linkHistoryDetails) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.upadtePinDetails(linkHistoryDetails);
	}

	/**
	 * @param regInitId
	 * @return String
	 */
	public String getInstId(String regInitId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getInstId(regInitId);
	}

	/**
	 * @param regInitId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkPinStatus(String regInitId) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String status = regCompDAO.checkStatus(regInitId);
		if (status.equalsIgnoreCase("19")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @param regInitNumber
	 * @return boolean
	 * @throws Exception
	 */
	public boolean pinGeneration(String regInitNumber, String deedId, String userId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.pinGeneration(regInitNumber, deedId, userId);
	}

	/**
	 * @param regInitNumber
	 * @throws Exception
	 */
	public void printPin(String regInitNumber, RegCompCheckerDTO rDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream os = response.getOutputStream();
		ZipOutputStream zos = new ZipOutputStream(os);
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList pinList = regCompDAO.pinDetails(regInitNumber);
		for (int i = 0; i < pinList.size(); i++) {
			RegCompCheckerDTO rrdto = (RegCompCheckerDTO) pinList.get(i);
			String fileFolderPath = RegCompCheckerConstant.FILE_UPLOAD_PATH + regInitNumber + "/";
			File folder = new File(fileFolderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String fileName = "PinDoc" + (i + 1) + "_" + rrdto.getPropertyId() + ".pdf";
			File fileToDownload = new File(fileFolderPath + fileName);
			FileOutputStream baos = new FileOutputStream(fileToDownload);
			Document document = new Document(PageSize.A4, 20, 20, 20, 20);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.open();
			Cell row = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			row.setHeader(true);
			row.setColspan(25);
			Table datatable = new Table(25);
			datatable.setWidth(100);
			datatable.setPadding(3);
			Cell title = new Cell(new Phrase("PIN DOCUMENT", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(25);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);
			datatable.setAlignment(1);
			datatable.addCell(row);
			Cell sectionheader1 = new Cell(new Phrase("E- REGISTRATION NUMBER - " + rDTO.getRegCompleteId(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
			sectionheader1.setColspan(25);
			sectionheader1.setBorder(Rectangle.NO_BORDER);
			datatable.addCell(sectionheader1);
			datatable.setAlignment(1);
			Cell line01 = new Cell(new Phrase("PROPERTY ID", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
			line01.setColspan(5);
			line01.setBorder(Rectangle.NO_BORDER);
			line01.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(line01);
			datatable.setAlignment(1);
			Cell line02 = new Cell(new Phrase("PROPERTY TYPE", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
			line02.setColspan(5);
			line02.setBorder(Rectangle.NO_BORDER);
			line02.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(line02);
			datatable.setAlignment(1);
			Cell line03 = new Cell(new Phrase("DISTRICT", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
			line03.setColspan(5);
			line03.setBorder(Rectangle.NO_BORDER);
			line03.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(line03);
			datatable.setAlignment(1);
			Cell line04 = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
			line04.setColspan(5);
			line04.setBorder(Rectangle.NO_BORDER);
			line04.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(line04);
			datatable.setAlignment(1);
			Cell line05 = new Cell(new Phrase("PIN NUMBER", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
			line05.setColspan(5);
			line05.setBorder(Rectangle.NO_BORDER);
			line05.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(line05);
			datatable.setAlignment(1);
			Cell line1 = new Cell(new Phrase(rrdto.getPropertyId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
			line1.setColspan(5);
			line1.setBorder(Rectangle.NO_BORDER);
			datatable.addCell(line1);
			datatable.setAlignment(1);
			Cell line2 = new Cell(new Phrase(rrdto.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
			line2.setColspan(5);
			line2.setBorder(Rectangle.NO_BORDER);
			datatable.addCell(line2);
			datatable.setAlignment(1);
			Cell line3 = new Cell(new Phrase(rrdto.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
			line3.setColspan(5);
			line3.setBorder(Rectangle.NO_BORDER);
			datatable.addCell(line3);
			datatable.setAlignment(1);
			Cell line4 = new Cell(new Phrase(rrdto.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
			line4.setColspan(5);
			line4.setBorder(Rectangle.NO_BORDER);
			datatable.addCell(line4);
			datatable.setAlignment(1);
			Cell line5 = new Cell(new Phrase(rrdto.getPinNumber(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
			line5.setColspan(5);
			line5.setBorder(Rectangle.NO_BORDER);
			datatable.addCell(line5);
			datatable.setAlignment(1);
			datatable.setCellsFitPage(true);
			document.add(datatable);
			document.close();
			baos.close();
			ZipEntry entry = new ZipEntry("PinDoc" + (i + 1) + "_" + rrdto.getPropertyId() + ".pdf");
			if (fileToDownload.getParentFile().exists() == false) {
				fileToDownload.getParentFile().mkdirs();
			}
			FileInputStream fileInputStream = new FileInputStream(fileToDownload);
			byte[] buf = new byte[(int) fileToDownload.length()];
			fileInputStream.read(buf);
			fileInputStream.close();
			zos.putNextEntry(entry);
			zos.write(buf);
			zos.closeEntry();
		}
		zos.close();
	}

	public boolean seqChk(String currDate) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.seqChk(currDate);
	}

	public boolean addHoldRemarks(RegCompCheckerDTO formDTO) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.addHoldRemark(formDTO);
	}

	public String getHoldRemarkls(String regNum, String holdId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getHoldRemarks(regNum, holdId);
	}

	public boolean UpdateRegistrationCompletionStatusRefusal(String regInitId, String status, String date, String userId, String remarks) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.UpdateRegistrationCompletionStatusRefusal(regInitId, status, date, userId, remarks);
	}

	public boolean checkImpound(String regNo) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.checkImpound(regNo);
	}

	public ArrayList cavetsCheck(String regInitNo) {
		ArrayList list = null;
		ArrayList regList = new ArrayList();
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			regList = regCompDAO.getLinkdRegNumber(regInitNo);
			if (regList.size() > 0) {
				list = regCompDAO.getCaveatDet(regInitNo, regList);
				if (list.size() > 0) {
					return list;
				}
			}
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
		}
		return list;
	}

	public boolean checkPropertyCaveats(String propId) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.checkPropertyCaveats(propId);
	}

	public boolean checkBookedSlot(String regNo, String officeId) {
		boolean flag = false;
		//if(regNo.length() == 11)
		//{
		//regNo = "0"+regNo;
		//}
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.checkBookedSlot(regNo);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList rowList = (ArrayList) list.get(i);
				String oid = (String) rowList.get(0);
				String slotDate = (String) rowList.get(1);
				String currDate = (String) rowList.get(2);
				if (oid.equalsIgnoreCase(officeId) && currDate.equalsIgnoreCase(slotDate)) {
					flag = true;
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}

	public String checkerMakerOffice(String regNo, String officeId) {
		String flag = "NOTBOOKED";
		////if(regNo.length() == 11)
		//{
		//	regNo = "0"+regNo;
		//}
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList list = regCompDAO.checkBookedSlot(regNo);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList rowList = (ArrayList) list.get(i);
				String oid = (String) rowList.get(0);
				if (oid.equalsIgnoreCase(officeId)) {
					flag = "BOOKEDPROPER";
				} else {
					flag = "NOTPROPER";
				}
			}
		}
		return flag;
	}

	public boolean checkRegNumber(String regNumber) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.checkRegNumber(regNumber);
	}

	public String getCurrencyFormat(double amount) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(amount).toString();
	}

	public String getUnitName(String proofId, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getUnitName(proofId, languageLocale);
	}

	public String getPropertyL1Name(String id, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyL1Name(id, languageLocale);
	}

	public String getPropertyL2Name(String id, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyL2Name(id, languageLocale);
	}

	public String[] getClaimantFlag(String roleId) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		ArrayList list = commonDao.getClaimantFlag(roleId);
		if (list != null & list.size() == 1) {
			String str = list.toString();
			str = str.substring(2, str.length() - 2);
			String[] strArr = str.split(",");
			return strArr;
		} else {
			String[] strArr = { "0", "0" };
			return strArr;
		}
	}

	public String getRoleName(String roleId, String languageLocale, int deed, int inst) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		String roleName = commonDao.getRoleName(roleId, languageLocale);
		int partyId = Integer.parseInt(roleId);
		if (deed == RegInitConstant.DEED_COMPOSITION_NPV || deed == RegInitConstant.DEED_LETTER_OF_LICENCE_NPV || inst == RegInitConstant.INST_TRANSFER_NPV_1 || inst == RegInitConstant.INST_TRANSFER_NPV_2)// for composition deed, letter of license deed & 2 instruments of transfer deed
		{
			if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				if (partyId == RegInitConstant.ROLE_EXECUTANT_SELF || partyId == RegInitConstant.ROLE_EXECUTANT_POA_HOLDER) {
					roleName = roleName + RegInitConstant.CREDITOR_ENGLISH;
				} else {
					roleName = roleName + RegInitConstant.DEBTOR_ENGLISH;
				}
			} else {
				if (partyId == RegInitConstant.ROLE_EXECUTANT_SELF || partyId == RegInitConstant.ROLE_EXECUTANT_POA_HOLDER) {
					roleName = roleName + RegInitConstant.CREDITOR_HINDI;
				} else {
					roleName = roleName + RegInitConstant.DEBTOR_HINDI;
				}
			}
		} else if (inst == RegInitConstant.INST_TRANSFER_NPV_4)// for 1 instrument of transfer deed
		{
			if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				if (partyId == RegInitConstant.ROLE_EXECUTANT_SELF || partyId == RegInitConstant.ROLE_EXECUTANT_POA_HOLDER) {
					//roleName=roleName;
				} else {
					roleName = roleName + RegInitConstant.BENEFICIARY_ENGLISH;
				}
			} else {
				if (partyId == RegInitConstant.ROLE_EXECUTANT_SELF || partyId == RegInitConstant.ROLE_EXECUTANT_POA_HOLDER) {
					//dto.setName(subList.get(2).toString());
				} else {
					roleName = roleName + RegInitConstant.BENEFICIARY_HINDI;
				}
			}
		}/*else{
																																																																		    	
																																																																		    	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
																																																																		    	dto.setName(subList.get(1).toString());
																																																																		    	}else{
																																																																		    		dto.setName(subList.get(2).toString());
																																																																		    	}
																																																																		    }*/
		return roleName;
	}
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

	public ArrayList getCompleteCaveatDetails(String transactionId) throws Exception {
		ArrayList caveatDetails = new ArrayList();
		RegCompMkrDAO regCompMkrDAO = new RegCompMkrDAO();
		ArrayList caveatRecord = regCompMkrDAO.getCompleteCaveatDetails(transactionId);
		for (int i = 0; i < caveatRecord.size(); i++) {
			ArrayList list1 = (ArrayList) caveatRecord.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			String instName = (String) list1.get(1);
			instName = instName == null ? "" : instName;
			if ((instName).equals("")) {
				rDTO.setInstituteName("-");
			} else {
				rDTO.setInstituteName((String) list1.get(1));
			}
			String repName = (String) list1.get(3);
			repName = repName == null ? "" : repName;
			if ((repName).equals("")) {
				rDTO.setRepName("-");
			} else {
				rDTO.setRepName((String) list1.get(3));
			}
			String caveatId = (String) list1.get(4);
			caveatId = caveatId == null ? "" : caveatId;
			if ((caveatId).equals("")) {
				rDTO.setCaveatId("-");
			} else {
				rDTO.setCaveatId((String) list1.get(4));
			}
			String stayOrderNumber = (String) list1.get(5);
			stayOrderNumber = stayOrderNumber == null ? "" : stayOrderNumber;
			if ((stayOrderNumber).equals("")) {
				rDTO.setStayOrdrNum("-");
			} else {
				rDTO.setStayOrdrNum((String) list1.get(5));
			}
			String stayOrderDate = (String) list1.get(6);
			stayOrderDate = stayOrderDate == null ? "" : stayOrderDate;
			if ((stayOrderDate).equals("")) {
				rDTO.setStayOrdrDet("-");
			} else {
				rDTO.setStayOrdrDet((String) list1.get(6));
			}
			String stayOrderStrtDate = (String) list1.get(7);
			stayOrderStrtDate = stayOrderStrtDate == null ? "" : stayOrderStrtDate;
			if ((stayOrderStrtDate).equals("")) {
				rDTO.setStayOrdrStrtDate("-");
			} else {
				//String date = (String)list1.get(7);
				//Date d1 = sdf1.parse(date);
				//String d2 = sdf2.format(d1);
				//logger.debug("<------STAY ORDER DATE IN BD"+d2);
				rDTO.setStayOrdrStrtDate(stayOrderStrtDate);
			}
			String stayOrderUptoDate = (String) list1.get(8);
			stayOrderUptoDate = stayOrderUptoDate == null ? "" : stayOrderUptoDate;
			if ((stayOrderUptoDate).equals("")) {
				rDTO.setStayOrdrUptoDate("-");
			} else {
				//String date = (String)list1.get(8);
				//Date d1 = sdf1.parse(date);
				//String d2 = sdf2.format(d1);
				//logger.debug("<------STAY ORDER UPTO DATE IN BD"+d2);
				rDTO.setStayOrdrUptoDate(stayOrderUptoDate);
			}
			String caveatDet = (String) list1.get(9);
			caveatDet = caveatDet == null ? "" : caveatDet;
			if ((caveatDet).equals("")) {
				rDTO.setCaveatDet("-");
			} else {
				rDTO.setCaveatDet((String) list1.get(9));
			}
			String regID = (String) list1.get(11);
			regID = regID == null ? "" : regID;
			if ((regID).equals("")) {
				rDTO.setRegId("-");
			} else {
				rDTO.setRegId((String) list1.get(11));
			}
			String caveatDate = (String) list1.get(12);
			caveatDate = caveatDate == null ? "" : caveatDate;
			if ((caveatDate).equals("")) {
				rDTO.setCaveatLoggedDate("-");
			} else {
				rDTO.setCaveatLoggedDate((String) list1.get(12));
			}
			String propID = (String) list1.get(16);
			propID = propID == null ? "" : propID;
			if ((propID).equals("")) {
				rDTO.setPropIdInit("-");
			} else {
				rDTO.setPropIdInit((String) list1.get(16));
			}
			caveatDetails.add(rDTO);
		}
		return caveatDetails;
	}

	/**
	 * @param transactionId
	 * @return
	 */
	public ArrayList getBankCaveatDetails(String transactionId, String lang) {
		ArrayList caveatDetails = new ArrayList();
		RegCompMkrDAO regCompMkrDAO = new RegCompMkrDAO();
		ArrayList caveatRecord = regCompMkrDAO.getBankCaveatDetails(transactionId);
		for (int i = 0; i < caveatRecord.size(); i++) {
			ArrayList list1 = (ArrayList) caveatRecord.get(i);
			RegCompCheckerDTO rDTO = new RegCompCheckerDTO();
			String instName = (String) list1.get(7);
			instName = instName == null ? "" : instName;
			if ((instName).equals("")) {
				rDTO.setInstituteName("-");
			} else {
				rDTO.setInstituteName((String) list1.get(7));
			}
			String repName = (String) list1.get(9);
			repName = repName == null ? "" : repName;
			if ((repName).equals("")) {
				rDTO.setRepName("-");
			} else {
				rDTO.setRepName((String) list1.get(9));
			}
			String caveatId = (String) list1.get(10);
			caveatId = caveatId == null ? "" : caveatId;
			if ((caveatId).equals("")) {
				if (lang.equalsIgnoreCase("en"))
					rDTO.setCaveatId("Protest By Bank");
				else
					rDTO.setCaveatId("Protest By Bank");
			} else {
				rDTO.setCaveatId((String) list1.get(10));
			}
			String regID = (String) list1.get(11);
			regID = regID == null ? "" : regID;
			if ((regID).equals("")) {
				rDTO.setRegId("-");
			} else {
				rDTO.setRegId((String) list1.get(11));
			}
			String caveatDate = (String) list1.get(12);
			caveatDate = caveatDate == null ? "" : caveatDate;
			if ((caveatDate).equals("")) {
				rDTO.setCaveatLoggedDate("-");
			} else {
				rDTO.setCaveatLoggedDate((String) list1.get(12));
			}
			String propID = (String) list1.get(14);
			propID = propID == null ? "" : propID;
			if ((propID).equals("")) {
				rDTO.setPropIdInit("-");
			} else {
				rDTO.setPropIdInit((String) list1.get(14));
			}
			//rDTO.setInstituteName((String)list1.get(7));
			//rDTO.setRepName((String)list1.get(9));
			//rDTO.setCaveatId((String)list1.get(10));
			//rDTO.setStayOrdrNum((String)list1.get(5));
			//rDTO.setStayOrdrDet((String)list1.get(6));
			//rDTO.setStayOrdrStrtDate((String)list1.get(7));
			//rDTO.setStayOrdrUptoDate((String)list1.get(8));
			//rDTO.setCaveatDet((String)list1.get(9));
			//rDTO.setRegId((String)list1.get(11));
			//rDTO.setCaveatLoggedDate((String)list1.get(12));
			//rDTO.setPropIdInit((String)list1.get(14));
			caveatDetails.add(rDTO);
		}
		return caveatDetails;
	}

	public boolean generateFinalCertificatePrint(RegCompCheckerForm regForm, String userId, String offcId, String lang, HttpServletResponse response) throws Exception {
		boolean flag = false;
		long start = 0, end = 0;
		start = System.currentTimeMillis();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String langauge = regCompDAO.getLangauge(regForm.getRegDTO().getRegInitNumber());
		if ("English".equalsIgnoreCase(langauge)) {
			lang = "en";
		} else {
			lang = "hi";
		}
		if (generateRegCertificatePrint(regForm, userId, offcId, lang))//registration certificate generation
		{
			if (generateEstampPdf(regForm.getRegDTO().getRegInitNumber(), regForm)) // generate estamp pdf and uploading it in DMS
			{
				//if(applyNoteOnFinalDoc(regForm.getRegDTO(), regForm, lang))
				//{
				if (true)//mergeSealWithDeed(regForm))// merge seal doc with deed doc
				{
					if (true)//putPropertyImages(regForm.getRegDTO().getRegInitNumber(),regForm))   // property images on seal doc
					{
						if (mergeRegCertWithEstamp(regForm)) // merge regCert and Estamp
						{
							if (mergeDocsToGenerateFinalCertPrint(regForm, lang, offcId))//sealDeed doc and regEstamp doc
							{
								/*
								flag = true;
								String guideId = GUIDGenerator.getGUID();
								String filePath = regForm.getFinalDocPath();
								regForm.setFileNameOwm(RegCompCheckerConstant.FILE_PATH_FOR_OWM+guideId+File.separator+RegCompCheckerConstant.NAME_OF_FINAL_DOC);
								logger.debug("guideid--------------->"+guideId);
								String outputPath = RegCompCheckerConstant.FILE_PATH_FOR_OWM+guideId;
								File output;
								output = new File(outputPath.toString());
								
								if (output.exists() == false) {
									logger.debug("Parent not found creating directory....");
									output.mkdirs();
									if(output.exists())
									{
										logger.debug("path created------------------>");
									}
								}
								try{
									//BurningImageAndText burnObj = new BurningImageAndText(); 
									PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
									String hindiFont = pr.getValue("dms_hindi_font_path");
									String englishFont = pr.getValue("dms_english_font_path");
									
									BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
									PresentationSeal sealContent = new PresentationSeal();   
									ArrayList<Presenter> presenterList = new ArrayList();
									//ArrayList<Claimant> claimantList = new ArrayList();   
									//PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
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
								    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
								    metaDataInfo.setUniqueNo(regForm.getRegDTO().getRegInitNumber());
								    metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
								    docOperations.downloadDocument(connDetails, metaDataInfo, outputPath, "F");
								    regForm.setOwmChk("Y");
							    
								}
								catch(Exception e){
									logger.debug(e.getStackTrace());
								}
							
							 response.setContentType("application/download");

		          			  
		          			   response.setHeader("Content-Disposition", "attachment; filename="
		          						+ URLEncoder.encode(filePath,"UTF-8"));
		          			   */
								//String filePath="";
								PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
								String path = pr.getValue("igrs_upload_path") + File.separator + "REGSEAL" + File.separator + "IGRS";
								String filePath = path + File.separator + regForm.getRegDTO().getRegInitNumber() + File.separator + RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG;
								response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filePath, "UTF-8"));
								File fileToDownload = new File(filePath);
								FileInputStream fileInputStream = new FileInputStream(fileToDownload);
								OutputStream out = response.getOutputStream();
								byte[] buf = new byte[(int) fileToDownload.length()];
								int readNum;
								while ((readNum = fileInputStream.read(buf)) != -1) {
									out.write(buf, 0, readNum);
								}
								fileInputStream.close();
								out.flush();
								out.close();
							}
						}
					}
				}
				//}
			}
		}
		end = System.currentTimeMillis();
		regForm.setTimeTaken((end - start) / 1000);
		return flag;
	}

	public boolean generateFinalCertificate(RegCompCheckerForm regForm, String userId, String offcId, String lang) throws Exception {
		boolean flag = false;
		long start = 0, end = 0;
		start = System.currentTimeMillis();
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String langauge = regCompDAO.getLangauge(regForm.getRegDTO().getRegInitNumber());
		if ("English".equalsIgnoreCase(langauge)) {
			lang = "en";
		} else {
			lang = "hi";
		}
		if (generateRegCertificate(regForm, userId, offcId, lang))//registration certificate generation
		{
			if (generateEstampPdf(regForm.getRegDTO().getRegInitNumber(), regForm)) // generate estamp pdf and uploading it in DMS
			{
				//if(applyNoteOnFinalDoc(regForm.getRegDTO(), regForm, lang))
				//{
				if (true)//mergeSealWithDeed(regForm))// merge seal doc with deed doc
				{
					if (true)//putPropertyImages(regForm.getRegDTO().getRegInitNumber(),regForm))   // property images on seal doc
					{
						if (mergeRegCertWithEstamp(regForm)) // merge regCert and Estamp
						{
							if (mergeDocsToGenerateFinalCert(regForm, lang, offcId))//sealDeed doc and regEstamp doc
							{
								return true;
								/*
								flag = true;
								String guideId = GUIDGenerator.getGUID();
								String filePath = regForm.getFinalDocPath();
								regForm.setFileNameOwm(RegCompCheckerConstant.FILE_PATH_FOR_OWM+guideId+File.separator+RegCompCheckerConstant.NAME_OF_FINAL_DOC);
								logger.debug("guideid--------------->"+guideId);
								String outputPath = RegCompCheckerConstant.FILE_PATH_FOR_OWM+guideId;
								File output;
								output = new File(outputPath.toString());
								
								if (output.exists() == false) {
									logger.debug("Parent not found creating directory....");
									output.mkdirs();
									if(output.exists())
									{
										logger.debug("path created------------------>");
									}
								}
								try{
									//BurningImageAndText burnObj = new BurningImageAndText(); 
									PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
									String hindiFont = pr.getValue("dms_hindi_font_path");
									String englishFont = pr.getValue("dms_english_font_path");
									
									BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
									PresentationSeal sealContent = new PresentationSeal();   
									ArrayList<Presenter> presenterList = new ArrayList();
									//ArrayList<Claimant> claimantList = new ArrayList();   
									//PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
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
								    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
								    metaDataInfo.setUniqueNo(regForm.getRegDTO().getRegInitNumber());
								    metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
								    docOperations.downloadDocument(connDetails, metaDataInfo, outputPath, "F");
								    regForm.setOwmChk("Y");
							    
								}
								catch(Exception e){
									logger.debug(e.getStackTrace());
								}
							
							 response.setContentType("application/download");

		          			  
		          			   response.setHeader("Content-Disposition", "attachment; filename="
		          						+ URLEncoder.encode(filePath,"UTF-8"));
		          			   
		          			   File fileToDownload = new File(filePath);
		          			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
		          			   OutputStream out = response.getOutputStream();
		          			   byte[] buf = new byte[2048];

		          			   int readNum;
		          			   while ((readNum=fileInputStream.read(buf))!=-1)
		          			   {
		          			      out.write(buf,0,readNum);
		          			   }
		          			   fileInputStream.close();
		          			   out.close();
							
							*/}
						}
					}
				}
				//}
			}
		}
		end = System.currentTimeMillis();
		regForm.setTimeTaken((end - start) / 1000);
		return flag;
	}

	public boolean generateFinalCertificateRefusal(RegCompCheckerForm regForm, String userId, String offcId, String lang) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String langauge = regCompDAO.getLangauge(regForm.getRegDTO().getRegInitNumber());
		if ("English".equalsIgnoreCase(langauge)) {
			lang = "en";
		} else {
			lang = "hi";
		}
		if (generateRegCertificateRefusal(regForm, userId, offcId, lang))//registration certificate generation
		{
			if (generateEstampPdf(regForm.getRegDTO().getRegInitNumber(), regForm)) // generate estamp pdf and uploading it in DMS
			{
				if (true)//mergeSealWithDeed(regForm))// merge seal doc with deed doc
				{
					if (true)//putPropertyImages(regForm.getRegDTO().getRegInitNumber(),regForm))
					{
						if (mergeRegCertWithEstamp(regForm)) // merge regCert and Estamp
						{
							if (mergeDocsToGenerateFinalCertRefusal(regForm, lang, offcId))//sealDeed doc and regEstamp doc
							{/*
																																																																																																																																																																																																																																														
																																																																																																																																																																																																																																														flag = true;
																																																																																																																																																																																																																																														String guideId = GUIDGenerator.getGUID();
																																																																																																																																																																																																																																														String filePath = regForm.getFinalDocPath();
																																																																																																																																																																																																																																														regForm.setFileNameOwm(guideId+File.separator+RegCompCheckerConstant.NAME_OF_FINAL_DOC);
																																																																																																																																																																																																																																														logger.debug("guideid--------------->"+guideId);
																																																																																																																																																																																																																																														String outputPath = RegCompCheckerConstant.FILE_PATH_FOR_OWM+guideId;
																																																																																																																																																																																																																																														File output;
																																																																																																																																																																																																																																														output = new File(outputPath.toString());
																																																																																																																																																																																																																																														
																																																																																																																																																																																																																																														if (output.exists() == false) {
																																																																																																																																																																																																																																															logger.debug("Parent not found creating directory....");
																																																																																																																																																																																																																																															output.mkdirs();
																																																																																																																																																																																																																																															if(output.exists())
																																																																																																																																																																																																																																															{
																																																																																																																																																																																																																																																logger.debug("path created------------------>");
																																																																																																																																																																																																																																															}
																																																																																																																																																																																																																																														}
																																																																																																																																																																																																																																														try{
																																																																																																																																																																																																																																															//BurningImageAndText burnObj = new BurningImageAndText(); 
																																																																																																																																																																																																																																															PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
																																																																																																																																																																																																																																															String hindiFont = pr.getValue("dms_hindi_font_path");
																																																																																																																																																																																																																																															String englishFont = pr.getValue("dms_english_font_path");
																																																																																																																																																																																																																																															
																																																																																																																																																																																																																																															BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
																																																																																																																																																																																																																																															PresentationSeal sealContent = new PresentationSeal();   
																																																																																																																																																																																																																																															ArrayList<Presenter> presenterList = new ArrayList();
																																																																																																																																																																																																																																															//ArrayList<Claimant> claimantList = new ArrayList();   
																																																																																																																																																																																																																																															//PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
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
							    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
							    metaDataInfo.setUniqueNo(regForm.getRegDTO().getRegInitNumber());
							    metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
							    docOperations.downloadDocument(connDetails, metaDataInfo, outputPath, "F");
							    regForm.setOwmChk("Y");
							    
							}
							catch(Exception e){
								logger.debug(e.getStackTrace());
							}
							String filePath = regForm.getFinalDocPath();
							 response.setContentType("application/download");

		          			  
		          			   response.setHeader("Content-Disposition", "attachment; filename="
		          						+ URLEncoder.encode(filePath,"UTF-8"));
		          			   
		          			   File fileToDownload = new File(filePath);
		          			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
		          			   OutputStream out = response.getOutputStream();
		          			   byte[] buf = new byte[2048];

		          			   int readNum;
		          			   while ((readNum=fileInputStream.read(buf))!=-1)
		          			   {
		          			      out.write(buf,0,readNum);
		          			   }
		          			   fileInputStream.close();
		          			   out.close();
							
						*/
								return true;
							}
						}
					}
				}
			}
		}
		flag = true;
		return flag;
	}

	public boolean generateRegCertificatePrint(RegCompCheckerForm regForm, String userId, String offcId, String lang) {
		boolean flag = false;
		String language = "";
		int result = -1;
		try {
			RegCompCheckerDTO rdto = regForm.getRegDTO();
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			String sysDate = regCompDAO.getSystemDate();
			String srName = regCompDAO.getSrDetails(userId);
			String offcName = regCompDAO.getofficeName(offcId, lang);
			String stampDuty = "0";
			String regFee = "0";
			ArrayList dutyDetls = regCompDAO.getRegStampDuty(rdto.getRegInitNumber());
			for (int i = 0; i < dutyDetls.size(); i++) {
				ArrayList subList = (ArrayList) dutyDetls.get(i);
				if (subList.get(2).toString().contains("-")) {
					stampDuty = "0";
				} else {
					stampDuty = (String) subList.get(2);
				}
				if (subList.get(1).toString().contains("-")) {
					regFee = "0";
				} else {
					regFee = (String) subList.get(1);
				}
			}
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
			metaDataInfo.setUniqueNo(rdto.getRegInitNumber());
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT);
			//   metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT_INITIAL);
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			RegistrationCertificate regCertObject = new RegistrationCertificate();
			regCertObject.setRegistrationNo(rdto.getRegCompleteId());
			SealsDAO sealDAO = new SealsDAO();
			String regDate = sealDAO.getSealDAte(rdto.getRegInitNumber(), "12");
			regCertObject.setRegistrationDate(regDate);
			regCertObject.setDateOfDelivery("NA");
			//	String marketValue = regCompDAO.getMarketValue(rdto.getRegInitNumber());
			//regCertObject.setMarketvalue(marketValue);
			regCertObject.setRegistrationFees(regFee);
			// Set Value for consideration amount - Registration Certficate Print Preview - 19 SEp
			if (lang.equalsIgnoreCase("en"))
				language = "English";
			else
				language = "Hindi";
			//added by ankit for plant and machinery - start
			int instId = Integer.parseInt(getInstId(rdto.getRegInitNumber()));
			//added by ankit for INC000000147473
			if (instId == 2096) {
				String marketValue = regCompDAO.getMarketValueExchangeDeed(rdto.getRegInitNumber());
				regCertObject.setMarketvalue(marketValue);
			} else {
				String marketValue = regCompDAO.getMarketValue(rdto.getRegInitNumber());
				regCertObject.setMarketvalue(marketValue);
			}
			if (instId == 2271) {
				logger.info(" inst with plant and machinery in reg certificate genaration 2271");
				//Long consdrAmt = regCompDAO.getConsiderAmount(rdto.getRegInitNumber()));
				//Long machAmt = Long.parseLong(regCompDAO.getMachineryAmount(rdto.getRegInitNumber()));
				if (lang.equalsIgnoreCase("en")) {
					regCertObject.setConsiderationAmount(regCompDAO.getConsiderAmount(rdto.getRegInitNumber()) + " (with machinery value)");
				} else {
					regCertObject.setConsiderationAmount(regCompDAO.getConsiderAmount(rdto.getRegInitNumber()) + " (मशीनरी मूल्य सहित)");
				}
			} else if (instId == 2272) {
				logger.info(" inst with plant and machinery in reg certificate genaration 2272");
				//Long consdrAmt = Long.parseLong(regCompDAO.getConsiderAmount(rdto.getRegInitNumber()));
				//Long machAmt = Long.parseLong(regCompDAO.getMachineryAmount(rdto.getRegInitNumber()));
				if (lang.equalsIgnoreCase("en")) {
					regCertObject.setConsiderationAmount(regCompDAO.getMachineryAmount(rdto.getRegInitNumber()) + " (machinery value)");
				} else {
					regCertObject.setConsiderationAmount(regCompDAO.getMachineryAmount(rdto.getRegInitNumber()) + " (मशीनरी मूल्य)");
				}
			} else {
				regCertObject.setConsiderationAmount(regCompDAO.getConsiderAmount(rdto.getRegInitNumber()));
			}
			//added by ankit for plant and machinery - end
			regCertObject.setTotalStampDuty(stampDuty);
			regCertObject.setSubRegistrarName(srName);
			regCertObject.setSubRegistrarOfficeName(offcName);
			regCertObject.setPartydetails(regCompDAO.getPartyDetailsRegCertificate(rdto.getRegInitNumber(), lang));
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + rdto.getRegInitNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber();
			File output;
			output = new File(outputPath.toString());
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			if (null==metaDataInfo.getUniqueNo()) {
				return false;
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				return false;
			}
			result = burnObj.generateRegistrationCertificate(connDetails, metaDataInfo, outputPath, regForm.getHeaderImg(), regCertObject, RegCompCheckerConstant.NAME_OF_REG_CERT, RegCompCheckerConstant.DMS_FOLDER, language);
			/*result = burnObj.generateRegistrationCertificate(connDetails,metaDataInfo,outputPath, regForm.getHeaderImg(), regCertObject,RegCompCheckerConstant.NAME_OF_REG_CERT_INITIAL ,RegCompCheckerConstant.DMS_FOLDER,language); 
			String documentPath=RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+rdto.getRegInitNumber()+File.separator+RegCompCheckerConstant.NAME_OF_REG_CERT_INITIAL;
			String signedDocumentPath=RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+rdto.getRegInitNumber()+File.separator+RegCompCheckerConstant.NAME_OF_REG_CERT;
			String certLabel=regCompDAO.getSubjectNameDao(regForm.getUserId());
			new DocumentService().sign(documentPath, signedDocumentPath, certLabel, 100, 100, 200, 200);
			 File file = new File(signedDocumentPath);
			 metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT);
			 String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
			 if(docId!="-1")
			 {
				 result=0;
				 logger.debug("Success in HSM");
			 }
			 else
			 {
				 logger.debug("Error in HSM");
			 }*/
		} catch (Exception e) {
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getStackTrace());
			logger.debug(e.getMessage(), e);
		}
		if (result == 0)
			flag = true;
		return flag;
	}

	public boolean generateRegCertificate(RegCompCheckerForm regForm, String userId, String offcId, String lang) {
		boolean flag = false;
		String language = "";
		int result = -1;
		try {
			RegCompCheckerDTO rdto = regForm.getRegDTO();
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			String sysDate = regCompDAO.getSystemDate();
			String srName = regCompDAO.getSrDetails(userId);
			String offcName = regCompDAO.getofficeName(offcId, lang);
			String stampDuty = "0";
			String regFee = "0";
			ArrayList dutyDetls = regCompDAO.getRegStampDuty(rdto.getRegInitNumber());
			for (int i = 0; i < dutyDetls.size(); i++) {
				ArrayList subList = (ArrayList) dutyDetls.get(i);
				if (subList.get(2).toString().contains("-")) {
					stampDuty = "0";
				} else {
					stampDuty = (String) subList.get(2);
				}
				if (subList.get(1).toString().contains("-")) {
					regFee = "0";
				} else {
					regFee = (String) subList.get(1);
				}
			}
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
			metaDataInfo.setUniqueNo(rdto.getRegInitNumber());
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT);
			//   metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT_INITIAL);
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			RegistrationCertificate regCertObject = new RegistrationCertificate();
			regCertObject.setRegistrationNo(rdto.getRegCompleteId());
			SealsDAO sealDAO = new SealsDAO();
			String regDate = sealDAO.getSealDAte(rdto.getRegInitNumber(), "12");
			regCertObject.setRegistrationDate(regDate);
			regCertObject.setDateOfDelivery(sysDate);
			int instId = Integer.parseInt(getInstId(rdto.getRegInitNumber()));
			//added by ankit for INC000000147473
			if (instId == 2096) {
				String marketValue = regCompDAO.getMarketValueExchangeDeed(rdto.getRegInitNumber());
				regCertObject.setMarketvalue(marketValue);
			} else {
				String marketValue = regCompDAO.getMarketValue(rdto.getRegInitNumber());
				regCertObject.setMarketvalue(marketValue);
			}
			// Set Value for consideration amount - Registration Certficate DOD - 14 SEp
			//added by ankit for plant and machinery - start
			if (instId == 2271) {
				logger.info(" inst with plant and machinery in reg certificate genaration 2271");
				//Long consdrAmt = Long.parseLong(regCompDAO.getConsiderAmount(rdto.getRegInitNumber()));
				//	Long machAmt = Long.parseLong(regCompDAO.getMachineryAmount(rdto.getRegInitNumber()));
				if (lang.equalsIgnoreCase("en")) {
					regCertObject.setConsiderationAmount(regCompDAO.getConsiderAmount(rdto.getRegInitNumber()) + " (with machinery value)");
				} else {
					regCertObject.setConsiderationAmount(regCompDAO.getConsiderAmount(rdto.getRegInitNumber()) + " (मशीनरी मूल्य सहित)");
				}
			} else if (instId == 2272) {
				logger.info(" inst with plant and machinery in reg certificate genaration 2272");
				//Long consdrAmt = Long.parseLong(regCompDAO.getConsiderAmount(rdto.getRegInitNumber()));
				//Long machAmt = Long.parseLong(regCompDAO.getMachineryAmount(rdto.getRegInitNumber()));
				if (lang.equalsIgnoreCase("en")) {
					regCertObject.setConsiderationAmount(regCompDAO.getMachineryAmount(rdto.getRegInitNumber()) + " (machinery value)");
				} else {
					regCertObject.setConsiderationAmount(regCompDAO.getMachineryAmount(rdto.getRegInitNumber()) + " (मशीनरी मूल्य)");
				}
			} else {
				regCertObject.setConsiderationAmount(regCompDAO.getConsiderAmount(rdto.getRegInitNumber()));
			}
			//added by ankit for plant and machinery - end
			regCertObject.setRegistrationFees(regFee);
			regCertObject.setTotalStampDuty(stampDuty);
			regCertObject.setSubRegistrarName(srName);
			regCertObject.setSubRegistrarOfficeName(offcName);
			regCertObject.setPartydetails(regCompDAO.getPartyDetailsRegCertificate(rdto.getRegInitNumber(), lang));
			if (lang.equalsIgnoreCase("en"))
				language = "English";
			else
				language = "Hindi";
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + rdto.getRegInitNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber();
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
			result = burnObj.generateRegistrationCertificate(connDetails, metaDataInfo, outputPath, regForm.getHeaderImg(), regCertObject, RegCompCheckerConstant.NAME_OF_REG_CERT, RegCompCheckerConstant.DMS_FOLDER, language);
			/*result = burnObj.generateRegistrationCertificate(connDetails,metaDataInfo,outputPath, regForm.getHeaderImg(), regCertObject,RegCompCheckerConstant.NAME_OF_REG_CERT_INITIAL ,RegCompCheckerConstant.DMS_FOLDER,language); 
			String documentPath=RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+rdto.getRegInitNumber()+File.separator+RegCompCheckerConstant.NAME_OF_REG_CERT_INITIAL;
			String signedDocumentPath=RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+rdto.getRegInitNumber()+File.separator+RegCompCheckerConstant.NAME_OF_REG_CERT;
			String certLabel=regCompDAO.getSubjectNameDao(regForm.getUserId());
			new DocumentService().sign(documentPath, signedDocumentPath, certLabel, 100, 100, 200, 200);
			 File file = new File(signedDocumentPath);
			 metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT);
			 String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
			 if(docId!="-1")
			 {
				 result=0;
				 logger.debug("Success in HSM");
			 }
			 else
			 {
				 logger.debug("Error in HSM");
			 }*/
		} catch (Exception e) {
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getStackTrace());
			logger.debug(e.getMessage(), e);
		}
		if (result == 0)
			flag = true;
		return flag;
	}

	public boolean generateRegCertificateRefusal(RegCompCheckerForm regForm, String userId, String offcId, String lang) {
		boolean flag = false;
		int result = -1;
		try {
			RegCompCheckerDTO rdto = regForm.getRegDTO();
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			String sysDate = regCompDAO.getSystemDate();
			String srName = regCompDAO.getSrDetails(userId);
			String offcName = regCompDAO.getofficeName(offcId, lang);
			String stampDuty = "0";
			String regFee = "0";
			ArrayList dutyDetls = regCompDAO.getRegStampDuty(rdto.getRegInitNumber());
			for (int i = 0; i < dutyDetls.size(); i++) {
				ArrayList subList = (ArrayList) dutyDetls.get(i);
				stampDuty = (String) subList.get(2);
				regFee = (String) subList.get(1);
			}
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
			metaDataInfo.setUniqueNo(rdto.getRegInitNumber());
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT);
			// metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT_INITIAL);
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			RegistrationCertificate regCertObject = new RegistrationCertificate();
			regCertObject.setRegistrationNo("NA");
			regCertObject.setRegistrationDate(sysDate);
			regCertObject.setRegistrationFees(regFee);
			regCertObject.setTotalStampDuty(stampDuty);
			regCertObject.setSubRegistrarName(srName);
			regCertObject.setSubRegistrarOfficeName(offcName);
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + rdto.getRegInitNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber();
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
			result = burnObj.generateRegistrationCertificate(connDetails, metaDataInfo, outputPath, regForm.getHeaderImg(), regCertObject, RegCompCheckerConstant.NAME_OF_REG_CERT, RegCompCheckerConstant.DMS_FOLDER, "English");
			/*result = burnObj.generateRegistrationCertificate(connDetails,metaDataInfo,outputPath, regForm.getHeaderImg(), regCertObject,RegCompCheckerConstant.NAME_OF_REG_CERT_INITIAL ,RegCompCheckerConstant.DMS_FOLDER,"English"); 
			String documentPath=RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+rdto.getRegInitNumber()+File.separator+RegCompCheckerConstant.NAME_OF_REG_CERT_INITIAL;
			String signedDocumentPath=RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+rdto.getRegInitNumber()+File.separator+RegCompCheckerConstant.NAME_OF_REG_CERT;
			String certLabel=regCompDAO.getSubjectNameDao(regForm.getUserId());
			new DocumentService().sign(documentPath, signedDocumentPath, certLabel, 100, 100, 200, 200);
			 File file = new File(signedDocumentPath);
			 metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT);
			 String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
			 if(docId!="-1")
			 {
				 result=0;
				 logger.debug("Success in HSM");
			 }
			 else
			 {
				 logger.debug("Error in HSM");
			 }*/
		} catch (Exception e) {
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getStackTrace());
		}
		if (result == 0)
			flag = true;
		return flag;
	}

	//merging all estamps and generating final estamp pdf and uploading
	public boolean generateEstampPdf(String regInitId, RegCompCheckerForm regForm) throws Exception {
		boolean flag = false;
		String estampPath1 = "";
		String estampPath2 = "";
		String estampPath3 = "";
		String estamp1 = "";
		String estamp2 = "";
		String estamp3 = "";
		int result = -1;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		ArrayList estampList = regCompDAO.getEstampForRegistration(regInitId);
		if (estampList.size() == 2) {
			ArrayList subList = (ArrayList) estampList.get(0);
			estampPath1 = (String) subList.get(0);
			estamp1 = (String) subList.get(1);
			ArrayList subList2 = (ArrayList) estampList.get(1);
			estampPath2 = (String) subList2.get(0);
			estamp2 = (String) subList2.get(1);
		} else if (estampList.size() == 3) {
			ArrayList subList = (ArrayList) estampList.get(0);
			estampPath1 = (String) subList.get(0);
			estamp1 = (String) subList.get(1);
			ArrayList subList2 = (ArrayList) estampList.get(1);
			estampPath2 = (String) subList2.get(0);
			estamp2 = (String) subList2.get(1);
			ArrayList subList3 = (ArrayList) estampList.get(1);
			estampPath3 = (String) subList3.get(0);
			estamp3 = (String) subList3.get(1);
		} else {
			ArrayList subList = (ArrayList) estampList.get(0);
			estampPath1 = (String) subList.get(0);
			estamp1 = (String) subList.get(1);
		}
		try {
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
			metaDataInfo.setUniqueNo(regInitId);
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_ESTAMP);
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + regInitId;
			File outPath;
			outPath = new File(outputPath.toString());
			if (outPath.exists() == false) {
				logger.debug("Parent not found creating directory....");
				outPath.mkdirs();
			}
			if (estampList.size() == 1) {
				File file = new File(estampPath1);
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId, regForm.getHeaderImg(),estampPath1 , "Estamp.PDF");
				String docId = docOperations.uploadDocument(connDetails, file, RegCompCheckerConstant.DMS_FOLDER, true, metaDataInfo);
				if (docId == "0")
					flag = true;
			} else if (estampList.size() == 2) {
				String outputPath1 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + estamp1;
				String outputPath2 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + estamp2;
				File output = new File(outputPath1.toString());
				if (output.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output.mkdirs();
				}
				File output2 = new File(outputPath2.toString());
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
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp1, regForm.getHeaderImg(),estampPath1 , "Estamp1.PDF");
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp2, regForm.getHeaderImg(),estampPath2 , "Estamp2.PDF");
				result = burnObj.mergeDocs(RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + regInitId, estampPath1, estampPath2, "EStamp.PDF");
				File file = new File(RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + regInitId + File.separator + "EStamp.PDF");
				String docId = docOperations.uploadDocument(connDetails, file, RegCompCheckerConstant.DMS_FOLDER, true, metaDataInfo);
				if (docId == "0")
					flag = true;
			} else if (estampList.size() == 3) {
				String outputPath1 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + estamp1;
				String outputPath2 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + estamp2;
				String outputPath3 = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + estamp3;
				File output = new File(outputPath1.toString());
				if (output.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output.mkdirs();
				}
				File output2 = new File(outputPath2.toString());
				if (output2.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output2.mkdirs();
				}
				File output3 = new File(outputPath3.toString());
				if (output3.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output3.mkdirs();
				}
				if (null==metaDataInfo.getUniqueNo()) {
					throw new Exception();
				}
				if (metaDataInfo.getUniqueNo().equals("")) {
					throw new Exception();
				}
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp1, regForm.getHeaderImg(),estampPath1 , "Estamp1.PDF");
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp2, regForm.getHeaderImg(),estampPath2 , "Estamp2.PDF");
				//result = burnObj.generateEStampWithBarcode(RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+estamp3, regForm.getHeaderImg(),estampPath3 , "Estamp3.PDF");
				result = burnObj.mergeDocs(RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + regInitId, estampPath1, estampPath2, "EstampTemp.PDF");
				result = burnObj.mergeDocs(RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + regInitId, RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + regInitId + File.separator + "EstampTemp.PDF", estampPath3, "EStamp.PDF");
				File file = new File(RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + regInitId + File.separator + "EStamp.PDF");
				String docId = docOperations.uploadDocument(connDetails, file, RegCompCheckerConstant.DMS_FOLDER, true, metaDataInfo);
				if (docId == "0")
					flag = true;
			}
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
		}
		return flag;
	}

	//Merging docs
	public boolean mergeSealWithDeed(RegCompCheckerForm regForm) {
		boolean flag = false;
		try {
			RegCompCheckerDTO rdto = regForm.getRegDTO();
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			String deedDocPath = regCompDAO.getDeedDocPath(rdto.getRegInitNumber());
			logger.debug("deed doc path---->" + deedDocPath);
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
			metaDataInfo.setUniqueNo(rdto.getRegInitNumber());
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			int result = -1;
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + rdto.getRegInitNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber();
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
			result = burnObj.mergeSealWithDeed(connDetails, metaDataInfo, outputPath, RegCompCheckerConstant.NAME_OF_SEALS_DOC, outputPath, deedDocPath, regForm.getHeaderImg(), RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC_INITIAL, RegCompCheckerConstant.DMS_FOLDER, "English");//to be checked
			if (result == 0) {
				flag = true;
			}
			logger.debug("result of merging seal with deed---->" + result);
		} catch (Exception e) {
			logger.debug("Exception in mergeSealWithDeed");
			logger.debug(e.getStackTrace());
		}
		return flag;
	}

	public boolean mergeRegCertWithEstamp(RegCompCheckerForm regForm) {
		boolean flag = false;
		try {
			RegCompCheckerDTO rdto = regForm.getRegDTO();
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			String deedDocPath = regCompDAO.getDeedDocPath(rdto.getRegInitNumber());
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
			metaDataInfo.setUniqueNo(rdto.getRegInitNumber());
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			int result = -1;
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + rdto.getRegInitNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber();
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
			
			result = burnObj.mergeCertificateWithEStamp(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT, RegCompCheckerConstant.NAME_OF_REG_CERT, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_ESTAMP, RegCompCheckerConstant.NAME_OF_ESTAMP, outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_ESTAMP, RegCompCheckerConstant.NAME_OF_REG_ESTAMP, RegCompCheckerConstant.DMS_FOLDER);
			logger.debug("merging estamp and reg cert-->" + result);
			if (result == 0)
				flag = true;
		} catch (Exception e) {
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getStackTrace());
		}
		return flag;
	}

	public boolean mergeDocsToGenerateFinalCert(RegCompCheckerForm regForm, String lang, String userId) {
		boolean flag = false;
		try {
			RegCompCheckerDTO rdto = regForm.getRegDTO();
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			String deedDocPath = regCompDAO.getDeedDocPath(rdto.getRegInitNumber());
			ArrayList list = regCompDAO.getEstampCodeDetails(rdto.getRegInitNumber());
			String stampCode = "";
			for (int i = 0; i < list.size(); i++) {
				ArrayList subList = (ArrayList) list.get(i);
				if ("".equals(stampCode))
					stampCode = (String) subList.get(0);
				else
					stampCode = stampCode + " | " + (String) subList.get(0);
			}
			stampCode = rdto.getRegCompleteId() + " | " + stampCode;
			logger.debug("final String ------->" + stampCode);
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
			metaDataInfo.setUniqueNo(rdto.getRegInitNumber());
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			int result = -1;
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + rdto.getRegInitNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber();
			File output;
			output = new File(outputPath.toString());
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			regForm.setFinalDocPath(outputPath + File.separator + RegCompCheckerConstant.NAME_OF_FINAL_DOC);
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			result = burnObj.mergeStampWithFinalDeed(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_ESTAMP, RegCompCheckerConstant.NAME_OF_REG_ESTAMP, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL, RegCompCheckerConstant.DMS_FOLDER, stampCode);
			logger.debug("result--->" + result);
			if (result != 0) {
				throw new Exception();
			}
			result = applyRegistartionSeal(rdto.getRegInitNumber(), rdto.getRegCompleteId(), lang, regForm.getHeaderImg(), stampCode);
			if (result != 0) {
				throw new Exception();
			}
			//ArrayList <Images> annexureList =regCompDAO.getAnnexureImages(rdto.getRegInitNumber(),lang,userId);
			//logger.debug("got the annxureList:"+annexureList.size()+":"+rdto.getRegInitNumber()+":"+lang+":"+userId+":"+regForm.getUserId());
			//	metaDataInfo.setType("FR");
			/*try{
				
			
			if("en".equalsIgnoreCase(lang))
			{
			result=burnObj.putProprtyImages(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL1, RegCompCheckerConstant.DMS_FOLDER, stampCode,"English");
			logger.debug(" put property images result:"+result);
			}
			else
			{
			result=burnObj.putProprtyImages(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL1, RegCompCheckerConstant.DMS_FOLDER, stampCode,"Hindi");
			logger.debug(" put property images result:"+result);
			}
			}
			catch(Throwable t)
			{
				logger.debug("error in put property images:");
				logger.debug(t.getMessage(),t);
			}
			if(result!=0)
			{
				throw new Exception(); 
			}*/
			//	logger.de("put property result:"+result);
			//String documentPath=RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+rdto.getRegInitNumber()+File.separator+RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL1;
			String path = pr.getValue("igrs_upload_path") + File.separator + "REGSEAL" + File.separator + "IGRS";
			String documentPath = path + File.separator + rdto.getRegInitNumber() + File.separator + RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG;
			String signedDocumentPath = path + File.separator + rdto.getRegInitNumber() + File.separator + rdto.getRegCompleteId().trim() + RegCompCheckerConstant.NAME_OF_FINAL_DOC_EXETENSION;
			String certLabel = regCompDAO.getSubjectNameDao(regForm.getUserId());
			//	new DocumentService().sign(documentPath, signedDocumentPath, certLabel, 100, 100, 200, 200);
			String signFlag = pr.getValue("digital_sign_flag");
			//String signFlag="N";
			if (signFlag.equalsIgnoreCase("Y")) {
				new DocumentService().sign(documentPath, signedDocumentPath, certLabel, 100, 100, 200, 200);
			} else {
				ExecutorService executor = Executors.newFixedThreadPool(1);
				List<Callable<Object>> todo = new ArrayList<Callable<Object>>();
				logger.debug("signed pathelse:" + signedDocumentPath);
				logger.debug("input pathelse:" + documentPath);
				todo.add(Executors.callable(new DigtalSignThread(documentPath, signedDocumentPath, "100", "100", "200", "200", certLabel)));
				logger.debug("input path:" + documentPath);
				executor.invokeAll(todo, 1, TimeUnit.MINUTES); // Timeout 
				executor.shutdown();
				//executor.invokeAll(Arrays.asList(new DigtalSignThread(outputPath+File.separator+ "EStamp1.PDF",outputPath+File.separator+ "EStamp.PDF",objEstampBO.getSubjectName(userId).toString(),"100","100","200","200")), 1, TimeUnit.MINUTES); // Timeout of 10 minutes.
			}
			logger.debug("signed path1:" + signedDocumentPath);
			logger.debug("input path1:" + documentPath);
			File file = new File(signedDocumentPath);
			if (file.exists()) {} else {
				logger.debug("Error in Digital Sign");
				throw new Exception();
			}
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
			String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
			if (docId != "-1" || !docId.equalsIgnoreCase("-1")) {
				result = 0;
				logger.debug("Success in HSM");
			} else {
				logger.debug("Error in HSM");
				throw new Exception();
			}
			/*if("en".equalsIgnoreCase(lang))
			{
			result=burnObj.putProprtyImages(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC, RegCompCheckerConstant.NAME_OF_FINAL_DOC, RegCompCheckerConstant.DMS_FOLDER, stampCode,"English");
			logger.debug(" put property images result:"+result);
			}
			else
			{
			result=burnObj.putProprtyImages(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC, RegCompCheckerConstant.NAME_OF_FINAL_DOC, RegCompCheckerConstant.DMS_FOLDER, stampCode,"Hindi");
			logger.debug(" put property images result:"+result);
			}*/
			logger.debug("result--->" + result);
			if (result == 0)
				flag = true;
		} catch (Exception e) {
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getMessage(), e);
		}
		return flag;
	}

	public boolean mergeDocsToGenerateFinalCertPrint(RegCompCheckerForm regForm, String lang, String userId) {
		boolean flag = false;
		try {
			RegCompCheckerDTO rdto = regForm.getRegDTO();
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			String deedDocPath = regCompDAO.getDeedDocPath(rdto.getRegInitNumber());
			ArrayList list = regCompDAO.getEstampCodeDetails(rdto.getRegInitNumber());
			String stampCode = "";
			for (int i = 0; i < list.size(); i++) {
				ArrayList subList = (ArrayList) list.get(i);
				if ("".equals(stampCode))
					stampCode = (String) subList.get(0);
				else
					stampCode = stampCode + " | " + (String) subList.get(0);
			}
			stampCode = rdto.getRegCompleteId() + " | " + stampCode;
			logger.debug("final String ------->" + stampCode);
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
			metaDataInfo.setUniqueNo(rdto.getRegInitNumber());
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			int result = -1;
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + rdto.getRegInitNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber();
			File output;
			output = new File(outputPath.toString());
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			regForm.setFinalDocPath(outputPath + File.separator + RegCompCheckerConstant.NAME_OF_FINAL_DOC);
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			result = burnObj.mergeStampWithFinalDeed(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_ESTAMP, RegCompCheckerConstant.NAME_OF_REG_ESTAMP, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL, RegCompCheckerConstant.DMS_FOLDER, stampCode);
			logger.debug("result--->" + result);
			if (result != 0) {
				throw new Exception();
			}
			result = applyRegistartionSeal(rdto.getRegInitNumber(), rdto.getRegCompleteId(), lang, regForm.getHeaderImg(), stampCode);
			if (result != 0) {
				throw new Exception();
			}
			//ArrayList <Images> annexureList =regCompDAO.getAnnexureImages(rdto.getRegInitNumber(),lang,userId);
			//logger.debug("got the annxureList:"+annexureList.size()+":"+rdto.getRegInitNumber()+":"+lang+":"+userId+":"+regForm.getUserId());
			//	metaDataInfo.setType("FR");
			/*try{
				
			
			if("en".equalsIgnoreCase(lang))
			{
			result=burnObj.putProprtyImages(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL1, RegCompCheckerConstant.DMS_FOLDER, stampCode,"English");
			logger.debug(" put property images result:"+result);
			}
			else
			{
			result=burnObj.putProprtyImages(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL1, RegCompCheckerConstant.DMS_FOLDER, stampCode,"Hindi");
			logger.debug(" put property images result:"+result);
			}
			}
			catch(Throwable t)
			{
				logger.debug("error in put property images:");
				logger.debug(t.getMessage(),t);
			}
			if(result!=0)
			{
				throw new Exception(); 
			}*/
			//	logger.de("put property result:"+result);
			//String documentPath=RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+rdto.getRegInitNumber()+File.separator+RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL1;
			//String path=pr.getValue("igrs_upload_path")+File.separator+"REGSEAL"+File.separator+"IGRS"; 
			//String documentPath=path+File.separator+rdto.getRegInitNumber()+File.separator+RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG;
			//String signedDocumentPath=path+File.separator+rdto.getRegInitNumber()+File.separator+rdto.getRegCompleteId()+RegCompCheckerConstant.NAME_OF_FINAL_DOC_EXETENSION;
			//String certLabel=regCompDAO.getSubjectNameDao(regForm.getUserId());
			//	new DocumentService().sign(documentPath, signedDocumentPath, certLabel, 100, 100, 200, 200);
			//	String signFlag=pr.getValue("digital_sign_flag");
			//String signFlag="N";
			//	if(signFlag.equalsIgnoreCase("Y"))
			//  {
			//	new DocumentService().sign(documentPath, signedDocumentPath, certLabel, 100, 100, 200, 200);        
			//	}
			//  else
			// {
			/*
			  ExecutorService executor = Executors.newFixedThreadPool(1);
			  List<Callable<Object>> todo = new ArrayList<Callable<Object>>();
			  logger.debug("signed pathelse:"+signedDocumentPath);
			  logger.debug("input pathelse:"+documentPath);
			  
			      todo.add(Executors.callable(new DigtalSignThread(documentPath, signedDocumentPath, "100", "100", "200", "200",certLabel))); 
			      logger.debug("input path:"+documentPath);

			  executor.invokeAll(todo,1, TimeUnit.MINUTES); // Timeout 
			  
			 
			  executor.shutdown();
			
			  //executor.invokeAll(Arrays.asList(new DigtalSignThread(outputPath+File.separator+ "EStamp1.PDF",outputPath+File.separator+ "EStamp.PDF",objEstampBO.getSubjectName(userId).toString(),"100","100","200","200")), 1, TimeUnit.MINUTES); // Timeout of 10 minutes.
			  
			*/
			//}
			//logger.debug("signed path1:"+signedDocumentPath);
			// logger.debug("input path1:"+documentPath);
			//File file = new File(signedDocumentPath);
			//if(file.exists())
			//	{
			//}
			//else
			//{
			//		logger.debug("Error in Digital Sign");
			//   	 throw new Exception();
			//	}
			//metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
			//   String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
			//   if(docId!="-1"||!docId.equalsIgnoreCase("-1"))
			//   {
			//  	 result=0;
			//  	 logger.debug("Success in HSM");
			//   }
			//   else
			//   {
			//  	 logger.debug("Error in HSM");
			// 	 throw new Exception();
			//  }
			/*if("en".equalsIgnoreCase(lang))
			{
			result=burnObj.putProprtyImages(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC, RegCompCheckerConstant.NAME_OF_FINAL_DOC, RegCompCheckerConstant.DMS_FOLDER, stampCode,"English");
			logger.debug(" put property images result:"+result);
			}
			else
			{
			result=burnObj.putProprtyImages(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC, RegCompCheckerConstant.NAME_OF_FINAL_DOC, RegCompCheckerConstant.DMS_FOLDER, stampCode,"Hindi");
			logger.debug(" put property images result:"+result);
			}*/
			logger.debug("result--->" + result);
			if (result == 0)
				flag = true;
		} catch (Exception e) {
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getMessage(), e);
		}
		return flag;
	}

	public boolean mergeDocsToGenerateFinalCertRefusal(RegCompCheckerForm regForm, String lang, String userId) {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		try {
			RegCompCheckerDTO rdto = regForm.getRegDTO();
			ArrayList list = regCompDAO.getEstampCodeDetails(rdto.getRegInitNumber());
			String stampCode = "";
			for (int i = 0; i < list.size(); i++) {
				ArrayList subList = (ArrayList) list.get(i);
				if ("".equals(stampCode))
					stampCode = (String) subList.get(0);
				else
					stampCode = stampCode + " | " + (String) subList.get(0);
			}
			String deedDocPath = regCompDAO.getDeedDocPath(rdto.getRegInitNumber());
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
			metaDataInfo.setUniqueNo(rdto.getRegInitNumber());
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			int result = -1;
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber();
			File output;
			output = new File(outputPath.toString());
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			regForm.setFinalDocPath(outputPath + File.separator + RegCompCheckerConstant.NAME_OF_FINAL_DOC);
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			//result = burnObj.mergeStampWithFinalDeed(connDetails, metaDataInfo,outputPath,RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_ESTAMP, RegCompCheckerConstant.NAME_OF_REG_ESTAMP, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC,RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC, RegCompCheckerConstant.NAME_OF_FINAL_DOC, RegCompCheckerConstant.DMS_FOLDER, stampCode);
			result = burnObj.mergeStampWithFinalDeed(connDetails, metaDataInfo, outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_ESTAMP, RegCompCheckerConstant.NAME_OF_REG_ESTAMP, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL, RegCompCheckerConstant.DMS_FOLDER, stampCode);
			logger.debug("result--->" + result);
			ArrayList<Images> annexureList = regCompDAO.getAnnexureImages(rdto.getRegInitNumber(), lang, userId);
			metaDataInfo.setType("FI");
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			if ("en".equalsIgnoreCase(lang)) {
				result = burnObj.putProprtyImages(connDetails, metaDataInfo, outputPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL1, RegCompCheckerConstant.DMS_FOLDER, stampCode, "English");
			} else {
				result = burnObj.putProprtyImages(connDetails, metaDataInfo, outputPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL, outputPath, regForm.getHeaderImg(), annexureList, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL1, RegCompCheckerConstant.DMS_FOLDER, stampCode, "Hindi");
			}
			String documentPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber() + File.separator + RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL1;
			String signedDocumentPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber() + File.separator + RegCompCheckerConstant.NAME_OF_FINAL_DOC;
			String certLabel = regCompDAO.getSubjectNameDao(regForm.getUserId());
			new DocumentService().sign(documentPath, signedDocumentPath, certLabel, 100, 100, 200, 200);
			File file = new File(signedDocumentPath);
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
			String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
			if (docId != "-1") {
				result = 0;
				logger.debug("Success in HSM");
			} else {
				logger.debug("Error in HSM");
			}
			logger.debug("result--->" + result);
			logger.debug("result--->" + result);
			if (result == 0)
				flag = true;
		} catch (Exception e) {
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getStackTrace());
		}
		return flag;
	}

	public ArrayList getTransPartyDets(String propId, String appId, String languageLocale, int deedId, int inst, RegCompCheckerForm form) throws Exception {
		ArrayList list = commonBd.getTransPartyDets(propId, appId);
		// For POA , Owner - RAhul
		ArrayList poAOwnerList = commonBd.getOwnerPoaDescDisplay(languageLocale, appId);
		ArrayList poAOwnerListForOrg = new ArrayList();
		ArrayList finalList = new ArrayList();
		ArrayList row_list = new ArrayList();
		String row;
		String rowArr[];
		CommonDTO dto;
		int i1 = 0;
		String OwnerRolePoA = "";
		RegCommonDTO mapDto = new RegCommonDTO();
		RegCommonBO commonBo = new RegCommonBO();
		// POA end
		RegCommonForm regCommonForm = new RegCommonForm();
		RegCompCheckerDAO checkerDAO = new RegCompCheckerDAO();
		String CommonDeedFlag = "";
		// CommonDeedFlag=sealDAO.getCommonFlowFlag(regNumber);
		CommonDeedFlag = checkerDAO.getCommonFlowFlag(appId);
		int count;
		String partyId = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				row_list = (ArrayList) list.get(i);
				int partyAge = 0;
				if (row_list.get(4) != null) {
					partyId = row_list.get(4).toString();
				}
				//Integer.parseInt((String)row_list.get(10));
				// For POA , Owner Display ,Mortgage Deed - RAhul checker First page
				int roleId = Integer.parseInt((String) row_list.get(5));
				String[] claimantArr = getClaimantFlag(Integer.toString(roleId));
				mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				dto = new CommonDTO();
				dto.setRole(getRoleName(row_list.get(5).toString(), languageLocale, deedId, inst));
				dto.setKhasraNo("-");
				int ExecutantClaimantfromDB = 0;
				if ((row_list.get(13) != null) && (!row_list.get(13).toString().equalsIgnoreCase("")) && (!row_list.get(13).toString().equalsIgnoreCase("null"))) {
					ExecutantClaimantfromDB = Integer.parseInt((String) row_list.get(13));
				}
				// for individual check type 
				if (row_list.get(8) != null && (row_list.get(8).toString().equalsIgnoreCase("2"))) {
					if (row_list.get(1) != null && !(row_list.get(1).toString().equals("")) && !(row_list.get(1).toString().equals("null"))) {
						if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && (ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6)) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
							System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
							regCommonForm.setExecutantClaimantName(commonBo.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
							String RoleName = regCommonForm.getExecutantClaimantName();
							System.out.println("Role Name " + RoleName);
							String PoaName = null;
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							dto.setName(OwnerRolePoA);
							System.out.println("printed name is " + dto.getName());
						} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							String RoleName = dto.getRole();
							dto.setRoleFullName(commonBo.getRoleNameForPOA((String) row_list.get(5)));
							RoleName = dto.getRoleFullName();
							System.out.println("Role Name " + RoleName);
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = null;
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							System.out.println("POA NAMe is " + PoaName);
							System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
							// System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							System.out.println("owner Role to display " + OwnerRolePoA);
							dto.setName(OwnerRolePoA);
							System.out.println("printed name is " + dto.getName());
						}
						//Changes for Minor Guardian and Middle name - Rahul
						else {
							if (row_list.get(1) != null && !(row_list.get(1).toString().equals("")) && !(row_list.get(1).toString().equals("null"))) {
								int age = Integer.parseInt((String) row_list.get(9));
								String relations = commonBo.getRelationshipName((row_list.get(12).toString()), languageLocale);
								String Mname = "";
								if ((row_list.get(14) != null) && (!row_list.get(14).toString().equalsIgnoreCase("")) && (!row_list.get(14).toString().equalsIgnoreCase("null"))) {
									Mname = row_list.get(14).toString();
								}
								if (Mname == null || "null".equalsIgnoreCase(Mname)) {
									dto.setName(row_list.get(1).toString() + " " + row_list.get(2).toString());
								} else {
									dto.setName(row_list.get(1).toString() + " " + Mname + " " + row_list.get(2).toString());
								}
								if (age < 18) {
									String MinorName = dto.getName();
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										dto.setName("(Minor) " + row_list.get(1).toString() + " " + row_list.get(2).toString() + " " + relations + " " + row_list.get(10).toString() + " " + "By " + row_list.get(11).toString());
									} else {
										dto.setName("(अवयस्‍क ) " + row_list.get(1).toString() + " " + row_list.get(2).toString() + " " + relations + " " + row_list.get(10).toString() + " " + "द्वारा  " + row_list.get(11).toString());
									}
								}
							}
							//dto.setName(row_list.get(1).toString()+" "+row_list.get(2).toString());
						}
					}
				} else if (row_list.get(3) != null && !(row_list.get(3).toString().equals("")) && !(row_list.get(3).toString().equals("null"))) {
					if (row_list.get(8) != null && (row_list.get(8).toString().equalsIgnoreCase("1"))) {
						if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && (ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6)) {
							poAOwnerListForOrg = commonBd.getOwnerPoaDescDisplayForOrg(languageLocale, appId);
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
							System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
							regCommonForm.setExecutantClaimantName(commonBo.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
							String RoleName = regCommonForm.getExecutantClaimantName();
							System.out.println("Role Name " + RoleName);
							String PoaName = null;
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							dto.setName(OwnerRolePoA);
							i1++;
							System.out.println("printed name is " + dto.getName());
						} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							String RoleName = dto.getRole();
							dto.setRoleFullName(commonBo.getRoleNameForPOA((String) row_list.get(5)));
							RoleName = dto.getRoleFullName();
							System.out.println("Role Name " + RoleName);
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = null;
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							System.out.println("POA NAMe is " + PoaName);
							System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
							// System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							System.out.println("owner Role to display " + OwnerRolePoA);
							dto.setName(OwnerRolePoA);
							System.out.println("printed name is " + dto.getName());
						} else {
							dto.setName(row_list.get(3).toString());
						}
					}
				} else {
					dto.setName(row_list.get(7).toString());
				}
				dto.setId(row_list.get(4).toString());
				dto.setRoleId(row_list.get(5).toString());
				if (row_list.get(6) != null) {
					dto.setShareOfProp(row_list.get(6).toString());
				} else {
					dto.setShareOfProp("");
				}
				finalList.add(dto);
				//count=form.getShareOfPropSize();
				//count++;
				//form.setShareOfPropSize(count);
			}
			return finalList;
		} else {
			return null;
		}
	}

	public ArrayList getTransPartyDetsCLR(String propId, String appId, String languageLocale, int deedId, int inst, RegCompCheckerForm form) throws Exception {
		ArrayList list = commonBd.getTransPartyDetsCLR(propId, appId);
		// For POA , Owner - RAhul
		ArrayList poAOwnerList = commonBd.getOwnerPoaDescDisplay(languageLocale, appId);
		ArrayList poAOwnerListForOrg = new ArrayList();
		ArrayList finalList = new ArrayList();
		ArrayList row_list = new ArrayList();
		String row;
		String rowArr[];
		CommonDTO dto;
		int i1 = 0;
		String OwnerRolePoA = "";
		RegCommonDTO mapDto = new RegCommonDTO();
		RegCommonBO commonBo = new RegCommonBO();
		// POA end
		RegCommonForm regCommonForm = new RegCommonForm();
		RegCompCheckerDAO checkerDAO = new RegCompCheckerDAO();
		String CommonDeedFlag = "";
		// CommonDeedFlag=sealDAO.getCommonFlowFlag(regNumber);
		CommonDeedFlag = checkerDAO.getCommonFlowFlag(appId);
		int count;
		String partyId = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				row_list = (ArrayList) list.get(i);
				int partyAge = 0;
				if (row_list.get(4) != null) {
					partyId = row_list.get(4).toString();
				}
				//Integer.parseInt((String)row_list.get(10));
				// For POA , Owner Display ,Mortgage Deed - RAhul checker First page
				int roleId = Integer.parseInt((String) row_list.get(5));
				String[] claimantArr = getClaimantFlag(Integer.toString(roleId));
				mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				dto = new CommonDTO();
				dto.setRole(getRoleName(row_list.get(5).toString(), languageLocale, deedId, inst));
				dto.setRoleFullName((commonBo.getRoleNameForPOA(row_list.get(5).toString())));
				dto.setKhasraNo(row_list.get(15) != null ? row_list.get(15).toString() : "-");
				int ExecutantClaimantfromDB = 0;
				if ((row_list.get(13) != null) && (!row_list.get(13).toString().equalsIgnoreCase("")) && (!row_list.get(13).toString().equalsIgnoreCase("null"))) {
					ExecutantClaimantfromDB = Integer.parseInt((String) row_list.get(13));
				}
				// for individual check type 
				if (row_list.get(8) != null && (row_list.get(8).toString().equalsIgnoreCase("2"))) {
					if (row_list.get(1) != null && !(row_list.get(1).toString().equals("")) && !(row_list.get(1).toString().equals("null"))) {
						if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && (ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6)) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
							System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
							regCommonForm.setExecutantClaimantName(commonBo.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
							String RoleName = regCommonForm.getExecutantClaimantName();
							System.out.println("Role Name " + RoleName);
							String PoaName = null;
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							dto.setName(OwnerRolePoA);
							System.out.println("printed name is " + dto.getName());
						} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							String RoleName = dto.getRole();
							dto.setRoleFullName(commonBo.getRoleNameForPOA((String) row_list.get(5)));
							RoleName = dto.getRoleFullName();
							System.out.println("Role Name " + RoleName);
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = null;
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							System.out.println("POA NAMe is " + PoaName);
							System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
							// System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							System.out.println("owner Role to display " + OwnerRolePoA);
							dto.setName(OwnerRolePoA);
							System.out.println("printed name is " + dto.getName());
						}
						// updated by rakesh for poa owner: Start
						else if (row_list.get(17).toString().equalsIgnoreCase("O")) {
							String firstName = "";
							if (row_list.get(1) != null && !row_list.get(1).toString().isEmpty()) {
								firstName = row_list.get(1).toString();
							} else if (row_list.get(3) != null && !row_list.get(3).toString().isEmpty()) {
								firstName = row_list.get(3).toString();
							} else {
								firstName = "";
							}
							String roleIdForOwner = commonBo.getRoleNameForPOACLR(row_list.get(4).toString());
							mapDto.setRoleNameForPOA(commonBo.getRoleNameForPOA(roleIdForOwner));
							String RoleName = dto.getRoleFullName();
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = "";
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							String poaNameForOwr = "";
							poaNameForOwr = commonBo.getOwnerNameCLR(row_list.get(4).toString());
							dto.setName(firstName + PoaName + poaNameForOwr);
							dto.setRole(dto.getRole() + "/" + commonBo.getRoleName(roleIdForOwner, languageLocale, deedId, inst));
							logger.debug("printed clr poa owner name is " + dto.getName());
						}
						//updated by rakesh for poa owner: End
						//Changes for Minor Guardian and Middle name - Rahul
						else {
							if (row_list.get(1) != null && !(row_list.get(1).toString().equals("")) && !(row_list.get(1).toString().equals("null"))) {
								int age = Integer.parseInt((String) row_list.get(9));
								String relations = commonBo.getRelationshipName((row_list.get(12).toString()), languageLocale);
								String Mname = "";
								if ((row_list.get(14) != null) && (!row_list.get(14).toString().equalsIgnoreCase("")) && (!row_list.get(14).toString().equalsIgnoreCase("null"))) {
									Mname = row_list.get(14).toString();
								}
								if (Mname == null || "null".equalsIgnoreCase(Mname)) {
									dto.setName(row_list.get(1).toString() + " " + row_list.get(2).toString());
								} else {
									dto.setName(row_list.get(1).toString() + " " + Mname + " " + row_list.get(2).toString());
								}
								if (age < 18) {
									String MinorName = dto.getName();
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										dto.setName("(Minor) " + row_list.get(1).toString() + " " + row_list.get(2).toString() + " " + relations + " " + row_list.get(10).toString() + " " + "By " + row_list.get(11).toString());
									} else {
										dto.setName("(अवयस्‍क ) " + row_list.get(1).toString() + " " + row_list.get(2).toString() + " " + relations + " " + row_list.get(10).toString() + " " + "द्वारा  " + row_list.get(11).toString());
									}
								}
							}
							//dto.setName(row_list.get(1).toString()+" "+row_list.get(2).toString());
						}
					}
				} else if (row_list.get(3) != null && !(row_list.get(3).toString().equals("")) && !(row_list.get(3).toString().equals("null"))) {
					if (row_list.get(8) != null && (row_list.get(8).toString().equalsIgnoreCase("1"))) {
						if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && (ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6)) {
							poAOwnerListForOrg = commonBd.getOwnerPoaDescDisplayForOrg(languageLocale, appId);
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
							System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
							regCommonForm.setExecutantClaimantName(commonBo.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
							String RoleName = regCommonForm.getExecutantClaimantName();
							System.out.println("Role Name " + RoleName);
							String PoaName = null;
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							dto.setName(OwnerRolePoA);
							i1++;
							System.out.println("printed name is " + dto.getName());
						} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							String RoleName = dto.getRole();
							dto.setRoleFullName(commonBo.getRoleNameForPOA((String) row_list.get(5)));
							RoleName = dto.getRoleFullName();
							System.out.println("Role Name " + RoleName);
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = null;
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							System.out.println("POA NAMe is " + PoaName);
							System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
							// System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
							String OwnerDetail = null;
							String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
							String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
							OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
							System.out.println("owner Role to display " + OwnerRolePoA);
							dto.setName(OwnerRolePoA);
							System.out.println("printed name is " + dto.getName());
						}
						// updated by rakesh for poa owner: Start
						else if (row_list.get(17).toString().equalsIgnoreCase("O")) {
							String firstName = "";
							if (row_list.get(1) != null && !row_list.get(1).toString().isEmpty()) {
								firstName = row_list.get(1).toString();
							} else if (row_list.get(3) != null && !row_list.get(3).toString().isEmpty()) {
								firstName = row_list.get(3).toString();
							} else {
								firstName = "";
							}
							String roleIdForOwner = commonBo.getRoleNameForPOACLR(row_list.get(4).toString());
							mapDto.setRoleNameForPOA(commonBo.getRoleNameForPOA(roleIdForOwner));
							String RoleName = dto.getRoleFullName();
							// to check hindi and english role 
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = "";
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " द्वारा (मुख्‍त्‍यार आम)";
								}
							}
							String poaNameForOwr = "";
							poaNameForOwr = commonBo.getOwnerNameCLR(row_list.get(4).toString());
							dto.setName(firstName + PoaName + poaNameForOwr);
							dto.setRole(dto.getRole() + "/" + commonBo.getRoleName(roleIdForOwner, languageLocale, deedId, inst));
							logger.debug("printed clr poa owner name is " + dto.getName());
						}
						//updated by rakesh for poa owner: End
						else {
							dto.setName(row_list.get(3).toString());
						}
					}
				} else {
					dto.setName(row_list.get(7).toString());
				}
				dto.setId(row_list.get(4).toString());
				dto.setRoleId(row_list.get(5).toString());
				if (row_list.get(6) != null) {
					dto.setShareOfProp(row_list.get(6).toString());
				} else {
					dto.setShareOfProp("");
				}
				finalList.add(dto);
				//count=form.getShareOfPropSize();
				//count++;
				//form.setShareOfPropSize(count);
			}
			return finalList;
		} else {
			return null;
		}
	}

	public ArrayList getTransPartyDetsForPaticular(String particularId, String appId, String languageLocale, int deedId, int inst, RegCompCheckerForm form) throws Exception {
		// modified for Govt offical 
		ArrayList list = commonBd.getTransPartyDetsForPaticular(particularId, appId);
		ArrayList poAOwnerList = commonBd.getOwnerPoaDescDisplay(languageLocale, appId);
		ArrayList poAOwnerListForOrg = new ArrayList();
		ArrayList finalList = new ArrayList();
		ArrayList row_list = new ArrayList();
		String row;
		String rowArr[];
		String OwnerRolePoA = "";
		CommonDTO dto;
		RegCommonDTO mapDto = new RegCommonDTO();
		RegCommonForm regCommonForm = new RegCommonForm();
		RegCompCheckerDAO checkerDAO = new RegCompCheckerDAO();
		String CommonDeedFlag = "";
		// CommonDeedFlag=sealDAO.getCommonFlowFlag(regNumber);
		CommonDeedFlag = checkerDAO.getCommonFlowFlag(appId);
		String partyId = "";
		int count;
		int i1 = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				row_list = (ArrayList) list.get(i);
				int partyAge = 0;
				//partyAge=	Integer.parseInt((String)row_list.get(10));
				if (row_list.get(4) != null) {
					partyId = row_list.get(4).toString();
				}
				dto = new CommonDTO();
				//dto.setRole(getRoleName(row_list.get(5).toString(),languageLocale,deedId,inst));
				dto.setRole(row_list.get(6).toString().toString());
				int roleId = Integer.parseInt((String) row_list.get(5));
				String[] claimantArr = getClaimantFlag(Integer.toString(roleId));
				mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				// for universal flag - rahul
				int ExecutantClaimantfromDB = 0;
				if ((row_list.get(13) != null) && (!row_list.get(13).toString().equalsIgnoreCase("")) && (!row_list.get(13).toString().equalsIgnoreCase("null"))) {
					ExecutantClaimantfromDB = Integer.parseInt((String) row_list.get(13));
				}
				// for individual pOA holder - Rahul
				if (row_list.get(7) != null && (row_list.get(7).toString().equalsIgnoreCase("2"))) {
					if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && (ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6)) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
						System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
						regCommonForm.setExecutantClaimantName(commonBo.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
						String RoleName = regCommonForm.getExecutantClaimantName();
						System.out.println("Role Name " + RoleName);
						String PoaName = null;
						// to check hindi and english role 
						String HindiEnglishRole[] = RoleName.split(",");
						String hindesc = HindiEnglishRole[0];
						String EngDesc = HindiEnglishRole[1];
						if (EngDesc.contains("Authenticated")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(Authenticated PoA Holder) ";
							} else {
								PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
							}
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(PoA Holder) ";
							} else {
								PoaName = " द्वारा (मुख्‍त्‍यार आम)";
							}
						}
						String OwnerDetail = null;
						String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
						String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
						OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
						dto.setName(OwnerRolePoA);
						System.out.println("printed name is " + dto.getName());
					} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						String RoleName = dto.getRole();
						dto.setRoleFullName(commonBo.getRoleNameForPOA((String) row_list.get(5)));
						RoleName = dto.getRoleFullName();
						System.out.println("Role Name " + RoleName);
						// to check hindi and english role 
						String HindiEnglishRole[] = RoleName.split(",");
						String hindesc = HindiEnglishRole[0];
						String EngDesc = HindiEnglishRole[1];
						String PoaName = null;
						if (EngDesc.contains("Authenticated")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(Authenticated PoA Holder) ";
							} else {
								PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
							}
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(PoA Holder) ";
							} else {
								PoaName = " द्वारा (मुख्‍त्‍यार आम)";
							}
						}
						System.out.println("POA NAMe is " + PoaName);
						System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
						// System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
						String OwnerDetail = null;
						String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
						String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
						OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
						System.out.println("owner Role to display " + OwnerRolePoA);
						dto.setName(OwnerRolePoA);
						System.out.println("printed name is " + dto.getName());
					}
					//Changes for Minor Guardian and Middle name - Rahul
					else {
						if (row_list.get(1) != null && !(row_list.get(1).toString().equals("")) && !(row_list.get(1).toString().equals("null"))) {
							int age = Integer.parseInt((String) row_list.get(9));
							String relations = commonBo.getRelationshipName((row_list.get(12).toString()), languageLocale);
							String Mname = "";
							if ((row_list.get(14) != null) && (!row_list.get(14).toString().equalsIgnoreCase("")) && (!row_list.get(14).toString().equalsIgnoreCase("null"))) {
								Mname = row_list.get(14).toString();
							}
							if (Mname == null || "null".equalsIgnoreCase(Mname)) {
								dto.setName(row_list.get(1).toString() + " " + row_list.get(2).toString());
							} else {
								dto.setName(row_list.get(1).toString() + " " + Mname + " " + row_list.get(2).toString());
							}
							if (age < 18) {
								String MinorName = dto.getName();
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									dto.setName("(Minor) " + row_list.get(1).toString() + " " + row_list.get(2).toString() + " " + relations + " " + row_list.get(10).toString() + " " + "By " + row_list.get(11).toString());
								} else {
									dto.setName("(अवयस्‍क) " + row_list.get(1).toString() + " " + row_list.get(2).toString() + " " + relations + " " + row_list.get(10).toString() + " " + "द्वारा  " + row_list.get(11).toString());
								}
							}
						}
						//dto.setName(row_list.get(1).toString()+" "+row_list.get(2).toString());
					}
				}
				// for org POA holder - Rahul
				else if (row_list.get(7) != null && (row_list.get(7).toString().equalsIgnoreCase("1"))) {
					if (CommonDeedFlag != null && "Y".equalsIgnoreCase(CommonDeedFlag) && (ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_2 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_4 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_5 || ExecutantClaimantfromDB == RegInitConstant.CLAIMANT_FLAG_6)) {
						poAOwnerListForOrg = commonBd.getOwnerPoaDescDisplayForOrg(languageLocale, appId);
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
						System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB);
						regCommonForm.setExecutantClaimantName(commonBo.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
						String RoleName = regCommonForm.getExecutantClaimantName();
						System.out.println("Role Name " + RoleName);
						String PoaName = null;
						// to check hindi and english role 
						String HindiEnglishRole[] = RoleName.split(",");
						String hindesc = HindiEnglishRole[0];
						String EngDesc = HindiEnglishRole[1];
						if (EngDesc.contains("Authenticated")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(Authenticated PoA Holder) ";
							} else {
								PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
							}
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(PoA Holder) ";
							} else {
								PoaName = " द्वारा (मुख्‍त्‍यार आम)";
							}
						}
						String OwnerDetail = null;
						String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
						String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
						OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
						dto.setName(OwnerRolePoA);
						i1++;
						System.out.println("printed name is " + dto.getName());
					} else if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						String RoleName = dto.getRole();
						dto.setRoleFullName(commonBo.getRoleNameForPOA((String) row_list.get(5)));
						RoleName = dto.getRoleFullName();
						System.out.println("Role Name " + RoleName);
						// to check hindi and english role 
						String HindiEnglishRole[] = RoleName.split(",");
						String hindesc = HindiEnglishRole[0];
						String EngDesc = HindiEnglishRole[1];
						String PoaName = null;
						if (EngDesc.contains("Authenticated")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(Authenticated PoA Holder) ";
							} else {
								PoaName = " द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
							}
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(PoA Holder) ";
							} else {
								PoaName = " द्वारा (मुख्‍त्‍यार आम)";
							}
						}
						System.out.println("POA NAMe is " + PoaName);
						System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
						// System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
						String OwnerDetail = null;
						String poAOwnerListNew = commonBd.getOwnerDetails(appId, partyId, languageLocale);
						String poaNameDetails = commonBd.getOwnerPOAnameDetails(partyId);
						OwnerRolePoA = poAOwnerListNew + PoaName + poaNameDetails;
						System.out.println("owner Role to display " + OwnerRolePoA);
						dto.setName(OwnerRolePoA);
						System.out.println("printed name is " + dto.getName());
					} else if (row_list.get(3) != null && !(row_list.get(3).toString().equals("")) && !(row_list.get(3).toString().equals("null"))) {
						dto.setName(row_list.get(3).toString());
					}
				}
				// previous code 
				/*else if(row_list.get(3)!=null && !(row_list.get(3).toString().equals("")) && !(row_list.get(3).toString().equals("null"))){
					dto.setName(row_list.get(3).toString());
				}*/
				else if (row_list.get(7) != null && (row_list.get(7).toString().equalsIgnoreCase("3"))) {
					dto.setName(row_list.get(8).toString());
				}
				dto.setId(row_list.get(4).toString());
				dto.setRoleId(row_list.get(5).toString());
				finalList.add(dto);
			}
			return finalList;
		} else {
			return null;
		}
	}

	public void getAllUserEnterables(RegCompCheckerForm form, String languageLocale) throws Exception {
		ArrayList list = commonBd.getAllUserEnterables(Integer.toString(form.getDuty_txn_id()), languageLocale);
		ArrayList rowList;
		CommonDTO dto;
		form.setUserEnterableList(new ArrayList());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				rowList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				if (rowList.get(0) != null) {
					dto.setName(rowList.get(0).toString());
				} else {
					dto.setName("-");
				}
				if (rowList.get(1) != null) {
					dto.setValue(rowList.get(1).toString());
				} else {
					dto.setValue("-");
				}
				form.getUserEnterableList().add(dto);
			}
		} else {
			form.setUserEnterableList(null);
		}
	}

	public String getDutyTxnId(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getDutyTxnId(regInitId);
	}

	/*public boolean putPropertyImages(String regInitId,RegCompCheckerForm regForm) throws Exception
	{
		ArrayList propImagesList = regCompDAO.getPropertyImages(regInitId);
		
		
		boolean flag = true;
		if(propImagesList.size() >0)
		{
			int len = propImagesList.size()*4;
			String propImages[] = new String[len];
			for(int i = 0 ; i < propImagesList.size();i++)
			{
				int j= i*4;
				ArrayList subList = (ArrayList)propImagesList.get(i);
				propImages[j] = (String)subList.get(0);
				propImages[j+1] = (String)subList.get(1);
				propImages[j+2] = (String)subList.get(2);
				propImages[j+3] = (String)subList.get(3);
			}
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
		    metaDataInfo.setUniqueNo(regInitId);
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		    metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC);
		   // BurningImageAndText burnObj = new BurningImageAndText();
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
		    int result = -1;
		    String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+regInitId;
			File output2;
			output2 = new File(downloadPath.toString());
			
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
		    String outputPath = SealsConstant.DOWNLOAD_DEED_PATH+File.separator+regInitId;
		    File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
		    result = burnObj.putProprtyImages(connDetails, metaDataInfo,downloadPath,RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, outputPath,regForm.getHeaderImg(),propImages , RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_SEALS_DEED_DOC, RegCompCheckerConstant.NAME_OF_SEAL_DEED_DOC, SealsConstant.DMS_FOLDER);
		    logger.debug("result--->"+result);
		    if (result != 0)
		    	flag = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	return flag;
}
*/
	public boolean putSrSgnOnFinalDoc(RegCompCheckerForm regForm, String userId, String offcId) {
		boolean flag = false;
		int result = -1;
		try {
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
			metaDataInfo.setUniqueNo(regForm.getRegDTO().getRegInitNumber());
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + regForm.getRegDTO().getRegInitNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH + File.separator + regForm.getRegDTO().getRegInitNumber();
			File output;
			output = new File(outputPath.toString());
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			logger.debug("sr sign path----->" + regForm.getRegDTO().getParentPathSrSign() + File.separator + regForm.getRegDTO().getFileNameSrSign());
			logger.debug("sr sign path----->" + regForm.getRegDTO().getParentPathSrSign());
			logger.debug("sr sign path----->" + regForm.getRegDTO().getFileNameSrSign());
			String srSignPath = regForm.getRegDTO().getParentPathSrSign() + regForm.getRegDTO().getFileNameSrSign();
			logger.debug("final path........................." + srSignPath);
			//result = burnObj.addSignatureToRegCert(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC,outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC, RegCompCheckerConstant.NAME_OF_FINAL_DOC, RegCompCheckerConstant.DMS_FOLDER, srSignPath);
		} catch (Exception e) {
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getStackTrace());
		}
		if (result == 0)
			flag = true;
		return flag;
	}

	public boolean updateFinalDocGenChk(String regNumber) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.updateFinalDocGenChk(regNumber);
	}

	public boolean checkFinalDocGen(String regInitId) throws Exception {
		boolean flag = false;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String status = regCompDAO.checkFinalDocGenFlag(regInitId);
		if (status.equalsIgnoreCase("Y")) {
			flag = true;
		}
		return flag;
	}

	public ArrayList getDeedDocDetails(String regNum) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getDeedDocDetails(regNum);
	}

	public void getCancellationLabel(String dutyTxnID, RegCompCheckerForm regForm, String languageLocale) throws Exception {
		ArrayList label = commonBd.getCancellationLabel(dutyTxnID);
		if (label != null && label.size() == 1) {
			ArrayList rowList = (ArrayList) label.get(0);
			if (rowList.get(3) != null && rowList.get(3).toString().equalsIgnoreCase("Y")) {
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					regForm.setCancellationLabel(RegInitConstant.CANCELLATION_LABEL_EN + rowList.get(1).toString());
				} else {
					regForm.setCancellationLabel(RegInitConstant.CANCELLATION_LABEL_HI + rowList.get(2).toString());
				}
			} else {
				regForm.setCancellationLabel("");
			}
		}
	}

	public boolean applyNoteOnFinalDoc(RegCompCheckerDTO rdto, RegCompCheckerForm regForm, String lang) {
		logger.debug("note on final doc");
		boolean flag = false;
		int result = 0;
		try {
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
			metaDataInfo.setUniqueNo(rdto.getRegInitNumber());
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);
			// BurningImageAndText burnObj = new BurningImageAndText();
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			String sealContent = "";
			if (lang.equalsIgnoreCase("hi"))
				sealContent = "नोट :- यह दस्तावेज पक्षकारों द्वारा प्रदत्त  परिवर्णन (Recitals) के आधार पर रजिस्ट्रीकरण अधिनियम 1908 के अन्तर्गत पंजीकृत किया गया है । " + "पक्षकारों की पहचान दो गवाहों द्वारा स्थापित की गई, यदि कोई छद्‌म प्रतिरूपण या असत्य जानकारी पाई जाती है तो संबंधित पक्षकार उत्तरदायी होगें एवं उनके विरूद्ध वैधानिक कार्यवाही की जायेगी " + "साथ ही ड्‌यूटी और फीस का अपवंचन पाये जाने पर अधिनियम अनुसार उनसे वसूली की जावेगी । ";
			else
				sealContent = "Note :- This document is registered as per the provisions of the Registration Act 1908 on the basis of the recitals given by the " + "parties to it ,whose identities have been established by two witnesses.If any impersonation or false recitals are found," + "the concerned parties will be held responsible and legal action will be taken against them.Deficit duty and fee (if any) will be " + "recovered from them as per the provisions of the Acts.";
			logger.debug("seal Content----->" + sealContent);
			OtherSeal othSealObj = new OtherSeal();
			othSealObj.setSealContent(sealContent);
			othSealObj.setAuthName("");
			othSealObj.setAuthDesignation("");
			othSealObj.setAuthSignatureLocation(null);
			othSealObj.setPartySignatureLocation(null);
			othSealObj.setPartyThumbImpressionLocation(null);
			othSealObj.setPartyAddress(null);
			String outputPath = SealsConstant.DOWNLOAD_DEED_PATH + File.separator + rdto.getRegInitNumber();
			File output = new File(outputPath.toString());
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + rdto.getRegInitNumber();
			File output2;
			output2 = new File(downloadPath.toString());
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			String langParam = "English";
			if (lang.equalsIgnoreCase("hi"))
				langParam = "Hindi";
			result = burnObj.putOtherSeals(connDetails, metaDataInfo, downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, regForm.getHeaderImg(), langParam, othSealObj, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC, SealsConstant.NAME_OF_SEALS_DOC, SealsConstant.DMS_FOLDER);
			if (result == 0)
				flag = true;
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return flag;
	}

	public Double getRegFeeRegInit(String regInitId) {
		Double regFee = 0.0;
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		try {
			regFee = regCompDAO.getRegFeeRegInit(regInitId);
		} catch (Exception e) {
			logger.debug("Error in getting Reg Fee PAid" + e);
		}
		return regFee;
	}

	public String checkRegSealApllied(String regInitNumber) {
		String flag = "N";
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		try {
			flag = regCompDAO.checkRegSealApllied(regInitNumber);
		} catch (Exception e) {
			logger.debug("Error in getting Reg Seal Apllied" + e);
		}
		return flag;
	}

	public String checkExecutionSealApllied(String regInitNumber) {
		String flag = "N";
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		try {
			flag = regCompDAO.checkExecutionSealApllied(regInitNumber);
		} catch (Exception e) {
			logger.debug("Error in getting Reg Seal Apllied" + e);
		}
		return flag;
	}

	public boolean updatePhotoDetailsConsenter(String partyId, String photoName, String photoPath) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updatePhotoDetailsConsenter(partyId, photoName, photoPath);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return false;
	}

	public boolean updatePhotoDetailsWitness(String partyId, String photoName, String photoPath) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updatePhotoDetailsWitness(partyId, photoName, photoPath);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return false;
	}

	public boolean updatePhotoDetails(String partyId, String photoName, String photoPath) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updatePhotoDetails(partyId, photoName, photoPath);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return false;
	}

	public boolean updateGovDetails(String partyId, String photoName, String photoPath) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updateGovDetails(partyId, photoName, photoPath);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return false;
	}

	public boolean updateThumbDetails(String partyId, String photoName, String photoPath, String remarks) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updateThumbDetails(partyId, photoName, photoPath, remarks);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return false;
	}

	public boolean updateThumbDetailsConsenter(String partyId, String photoName, String photoPath, String remarks) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updateThumbDetailsConsenter(partyId, photoName, photoPath, remarks);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return false;
	}

	public boolean updateThumbDetailsWitness(String partyId, String photoName, String photoPath, String remarks) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updateThumbDetailsWitness(partyId, photoName, photoPath, remarks);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//	e.printStackTrace();
		}
		return false;
	}

	public boolean updateSignDetailsConsenter(String partyId, String photoName, String photoPath) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updateSignDetailsConsenter(partyId, photoName, photoPath);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return false;
	}

	public boolean updateSignDetailsWitness(String partyId, String photoName, String photoPath) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updateSignDetailsWitness(partyId, photoName, photoPath);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return false;
	}

	public boolean updateSignDetails(String partyId, String photoName, String photoPath) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updateSignDetails(partyId, photoName, photoPath);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			//e.printStackTrace();
		}
		return false;
	}

	public String getConsenterFlag(String regInitId) {
		String flag = "";
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			flag = regCompDAO.getConsenterFlag(regInitId);
			if (flag == null || flag.equalsIgnoreCase("")) {
				flag = "N";
			}
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
		}
		return flag;
	}

	public String getConsenterAmount(String regInitId) {
		String flag = "";
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			flag = regCompDAO.getConsnterAmount(regInitId);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
		}
		return flag;
	}

	public boolean upadteConsenterDuties(RegCompCheckerForm eForm) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.upadteConsenterDuties(eForm);
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
		}
		return false;
	}

	public boolean insertTimeMakerStart(RegCompCheckerForm eForm, String userId, String sroid) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.insertTimeMakerStart(eForm, userId, sroid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getEmailHoldContent(String regNum, String holdRemarks, String officeId, String language, String ofcId, String reasonEnglish, String reasonHindi) {
		String eMailcontent = "";
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		String time = regCompDAO.getDateTIME();
		String officeName = "";
		try {
			officeName = regCompDAO.getofficeName(officeId, language);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("en".equalsIgnoreCase(language)) {
			eMailcontent = "Your application " + regNum + " which is being processed at " + officeName + " is put on hold at " + time + " due to following reason: \n " + reasonEnglish + holdRemarks;
		} else {
			eMailcontent = "आपकी आवेदन सन्ख्या " + regNum + "जो  की " + officeName + "मे पूरी की जा रही थी वो निम्न कारणो की वजह से इस समय" + "रोक दी गई हैः\n" + reasonHindi + holdRemarks;
		}
		return eMailcontent;
	}

	public String getSMSContent(String regNumber, String caseFlag) {
		String eMailcontent = "";
		if ("Y".equalsIgnoreCase(caseFlag)) {
			eMailcontent = "Case referred to DR against your application Number " + regNumber;
		} else {
			eMailcontent = "Your application " + regNumber + " has been put on hold.";
		}
		return eMailcontent;
	}

	public boolean checkPinRequired(String regNumber) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.checkPinRequired(regNumber);
	}

	public int applyRegistartionSeal(String regNo, String CompNumber, String langauge, String headerimg, String stampCode) {
		int result = -1;
		try {
			SealsDAO sealDAO = new SealsDAO();
			//String sysDate = sealDAO.getSystemDate();
			//ArrayList list = new RegCompCheckerDAO().getEstampCodeDetails(sForm.getSealDTO().getRegNumber());
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
			metaDataInfo.setUniqueNo(regNo);
			metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL);
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD = new SealsBD();
			boolean val = sealBD.validateFont(hindiFont, englishFont);
			if (!val) {
				throw new Exception();
			}
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
			SealsForm sForm = new SealsForm();
			SealsBD bd = new SealsBD();
			bd.getRegFeeDetails(regNo, sForm);
			sealDAO.signatureDetails(sForm, regNo, langauge);
			RegistrationSeal registrationSeal = new RegistrationSeal();
			//by vinay
			registrationSeal.setAuthName(sForm.getSealDTO().getUserName());
			registrationSeal.setAuthDesignation(sForm.getSealDTO().getUserDesignation());
			registrationSeal.setAuthPlace(sForm.getSealDTO().getUserOfficeName());
			registrationSeal.setAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
			logger.info("Auth Signature location : " + sForm.getSealDTO().getUserSignature());
			// end
			String serialNumber = sealDAO.getSerailNumber(regNo, "21");
			if (!"".equalsIgnoreCase(serialNumber)) {
				registrationSeal.setIsCorrectionSeal(Boolean.TRUE);
				registrationSeal.setCorrectionSealAuthName(sForm.getSealDTO().getUserName());
				registrationSeal.setCorrectionSealAuthDesignation(sForm.getSealDTO().getUserDesignation());
				registrationSeal.setCorrectionSealAuthPlace(sForm.getSealDTO().getUserOfficeName());
				registrationSeal.setCorrectionSealAuthSignatureLocation(sForm.getSealDTO().getUserSignature());
				String corrdDate = sealDAO.getSealDAte(regNo, "21");
				registrationSeal.setCorrectionSeal("यह दस्तावेज पूर्व में पंजीकृत दस्तावेज क्रमांक " + serialNumber + " दिनांक " + corrdDate + " को संशोधित / निरसित करता है।");
			}
			String regDate = new RegCompCheckerDAO().getRegistartionDateInit(regNo);
			registrationSeal.setType(Boolean.TRUE); // true- registration seal , false- refusal seal
			sForm.getSealDTO().setCopyFee("0");
			sForm.getSealDTO().setOthers("0");
			registrationSeal.setRegistrationSealContentPartOne("इस दस्तावेज का इलेक्ट्रानिक पंजीयन दिनांक " + regDate + " को क्रमांक " + CompNumber + " दे कर किया गया है। जिसमें");
			registrationSeal.setRegistrationSealContentPartTwo("पृष्ठ समाविष्ट हैं ");
			registrationSeal.setFeesSealContent("स्टाम्प शुल्क        " + sForm.getSealDTO().getTotalDuty() + " \n \n \n पंजीयन शुल्क       " + sForm.getSealDTO().getRegFee() + " \n प्रतिलिपि " + "शुल्क      " + sForm.getSealDTO().getCopyFee() + " \n अधिक             " + sForm.getSealDTO().getOthers() + " \n योग               " + sForm.getSealDTO().getRegFee());
			//registrationSeal.setRefusalSealContent("This is a refusal Seal");
			RegCompCheckerDAO regDao = new RegCompCheckerDAO();
			//String deedDocPath = regDao.getDeedDocPath(sForm.getSealDTO().getRegNumber());
			//logger.debug("deed doc path--->"+deedDocPath);
			String outputPath = SealsConstant.DOWNLOAD_DEED_PATH + File.separator + regNo;
			String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW + File.separator + regNo;
			String path = pr.getValue("igrs_upload_path") + File.separator + "REGSEAL" + File.separator + "IGRS";
			String documentPath = path + File.separator + regNo;
			File output2;
			//String sealConetent="इस दस्तावेज का इलेक्ट्रानिक पंजीयन दिनाक "+sysDate+"को क्रमांक "+sForm.getSealDTO().getRegCompNumber()+" को किया गया है। जिसमें "+list.size()+" पृष्ठ समाविष्ट है "+"\nस्टाम्प शुल्क      "+sForm.getSealDTO().getTotalDuty()+" \n पंजीयन शुल्क       "+sForm.getSealDTO().getRegFee()+" \n प्रतिलिपि " +
			//"शुल्क     "+sForm.getSealDTO().getCopyFee()+" \n अधिक     "+sForm.getSealDTO().getOthers()+" \n योग     "+sForm.getSealDTO().getTotal();
			output2 = new File(downloadPath.toString());
			if (output2.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output2.mkdirs();
			}
			File output;
			output = new File(documentPath.toString());
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
			
			if ("en".equalsIgnoreCase(langauge)) {
				result = burnObj.putRegistrationFinalSeal(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL, documentPath, headerimg, registrationSeal, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL_REG, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, SealsConstant.DMS_FOLDER, stampCode, "English");
			} else {
				result = burnObj.putRegistrationFinalSeal(connDetails, metaDataInfo, downloadPath, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL, documentPath, headerimg, registrationSeal, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC_INITIAL_REG, RegCompCheckerConstant.NAME_OF_FINAL_DOC_INITIAL_REG, SealsConstant.DMS_FOLDER, stampCode, "Hindi");
			}
			//result =burnObj.putExtraSeals(connDetails, metaDataInfo, downloadPath, SealsConstant.NAME_OF_SEALS_DOC, outputPath, sForm.getHeaderImage(), false, 1, 1, sealConetent, SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC, SealsConstant.NAME_OF_SEALS_DOC, SealsConstant.DMS_FOLDER);
			logger.debug("result of reg seal------->" + result);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return result;
	}

	public String getUsrId(String regNo) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getUsrId(regNo);
	}

	public String getOfcId(String regNo) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getOfcId(regNo);
	}

	public String getPrintFlag(String regNo) {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getPrintFlag(regNo);
	}

	public boolean updatePrintFlag(String regNo) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updatePrintFlag(regNo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updatePrintFlag(String regNo, String status) {
		try {
			RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
			return regCompDAO.updatePrintFlag(regNo, status);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updatePropertyUpload(String propid, String regNo, String columnNamePath, String docPath) {
		RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
		return regCheckDAO.updatePropertyUpload(propid, regNo, columnNamePath, docPath);
	}

	public boolean updatePartyUpload(String partyId, String regNo, String columnNamePath, String docPath) {
		RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
		return regCheckDAO.updatePartyUpload(partyId, regNo, columnNamePath, docPath);
	}

	public boolean updateSuccesPrint(String regCompNumber, String status) {
		RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
		return regCheckDAO.updateSuccesPrint(regCompNumber, status);
	}

	public boolean imageCheck(String path) {
		boolean flag = new BurningImageAndText().isCropable(path);
		logger.debug("flag is:" + flag);
		return flag;
	}

	public boolean checkImagesPresentation(String regInitId) {
		RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
		boolean flag = regCheckDAO.checkImagesPresentation(regInitId);
		//logger.debug("flag is:"+flag);
		return flag;
	}

	public boolean checkImagesPresentationSelected(String[] partyId) {
		RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
		return regCheckDAO.checkImagesPresentationSelected(partyId);
	}

	public String getPresentationSingleParty(String regId) {
		RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
		return regCheckDAO.getPresentationSingleParty(regId);
	}

	public boolean applywitnessThumbStamp(HttpServletRequest request, HttpSession session) {
		boolean sealFlag = false;
		try {
			RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
			String roleId = (String) session.getAttribute("role");
			String funId = (String) session.getAttribute("functionId");
			String userId = (String) session.getAttribute("UserId");
			String officeId = (String) session.getAttribute("loggedToOffice");
			String language = (String) session.getAttribute("languageLocale");
			MasterListDTO mDto = (MasterListDTO) session.getAttribute("userDetls");
			String regNumber = (String) request.getAttribute("regNumber");
			SealsDTO sealDTO = new SealsDTO();
			sealDTO.setRegNumber(regNumber);
			SealsForm eForm = new SealsForm();
			eForm.setHeaderImage((String) request.getAttribute("headerImagePath"));
			SealsBD bd = new SealsBD();
			String lang = bd.getLangauge(regNumber);
			if (mDto != null) {
				sealDTO.setUserName(mDto.getUserName());
				sealDTO.setUserDesignation(mDto.getDesignation());
				sealDTO.setUserSignature(mDto.getSignature());
				// to be commented
				//sealDTO.setUserSignature("D://signature.GIF");
				sealDTO.setUserOfficeName(mDto.getOfficeName());
				logger.debug("office Name:" + mDto.getOfficeName());
			}
			eForm.setLangauge(lang);
			// witness seal 
			{
				sealDTO.setSelectedSubId("8");
				ArrayList witnessList = bd.getWitnessDetails(sealDTO.getRegNumber(), language);
				logger.debug("witness list" + witnessList.size());
				eForm.setWitnessList(witnessList);
				String witness = regCheckDAO.getWitnessSeals(sealDTO.getRegNumber());
				String selectedSeal = sealDTO.getSelectedSubId();
				String selectedWitness[] = witness.split("~");
				logger.debug("size of array" + selectedWitness.length);
				String applyFlag = bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
				if ("Y".equalsIgnoreCase(applyFlag)) {
					sealFlag = true;
				} else {
					logger.debug("applying witness seal");
					bd.insertCommonSealDetails(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if (selectedSeal.equals("8")) {
						eForm.setSealDTO(sealDTO);
						int result = bd.insertWitnessDetails(eForm.getWitnessList(), sealDTO, officeId, userId, selectedWitness, eForm, language);
						//done
						if (result == 0) {
							sealFlag = bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal, "Y");
						} else {
							logger.debug("Execution Seal Failed----->" + result);
							throw new Exception();
						}
					}
				}
			}
			if (sealFlag) {
				sealDTO.setSelectedSubId("9");
				String selectedSeal = sealDTO.getSelectedSubId();
				ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(), language);
				logger.debug("Seal list" + partyList.size());
				eForm.setPresentationPartyDetails(partyList);
				ArrayList witnessList = bd.getWitnessDetails(sealDTO.getRegNumber(), language);
				logger.debug("witness list" + witnessList.size());
				eForm.setWitnessList(witnessList);
				ArrayList consenterList = bd.getConsenterDetails(sealDTO.getRegNumber(), language);
				logger.debug("consenterList: " + consenterList.size());
				eForm.setConsenterList(consenterList);
				logger.debug("****************************" + sealDTO.getSelectedSubId());
				String selected = regCheckDAO.getThumbSealsAll(sealDTO.getRegNumber());
				String selectedParty[] = selected.split("~");
				logger.debug("size of array" + selectedParty.length);
				String applyFlag = bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
				if ("Y".equalsIgnoreCase(applyFlag)) {
					sealFlag = true;
				} else {
					bd.insertCommonSealDetails(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if (selectedSeal.equals("9")) {
						eForm.setSealDTO(sealDTO);
						int result = bd.insertThumbSealDetails(eForm, sealDTO, officeId, userId, selectedParty, language);
						//done
						if (result == 0) {
							logger.debug("insertion----->" + result);
							sealFlag = bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal, "Y");
						} else {
							logger.debug("Execution Seal Failed----->" + result);
							throw new Exception();
						}
					}
				}
			}
			if (sealFlag) {
				sealDTO.setSelectedSubId("11");
				sealDTO.setOthers("0");//need to be changed by akansha
				bd.getStampDutyDetails(eForm, sealDTO.getRegNumber());
				String applyFlag = bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
				if ("Y".equalsIgnoreCase(applyFlag)) {
					sealFlag = true;
				} else {
					bd.insertCommonSealDetails(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					eForm.setSealDTO(sealDTO);
					int result = bd.applyStampDutySeal(eForm, userId);
					//done
					if (result == 0) {
						sealFlag = bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(), "Y");
					} else {
						logger.debug("Execution Seal Failed----->" + result);
						throw new Exception();
					}
				}
			}
			if (sealFlag) {
				ArrayList linkingList = regCheckDAO.checkLinking(sealDTO.getRegNumber());
				if (linkingList != null && linkingList.size() > 0) {
					String newRegDate = "";
					String newRegNumber = (String) linkingList.get(0);
					String ecode = (String) linkingList.get(1);
					String physicalStamp = (String) linkingList.get(2);
					String regFee = (String) linkingList.get(3);
					String stamp = (String) linkingList.get(4);
					String oldNumber = (String) linkingList.get(5);
					String oldDate = (String) linkingList.get(6);
					String denoting = (String) linkingList.get(7);
					String linking = (String) linkingList.get(8);
					if (newRegNumber != null && !newRegNumber.equalsIgnoreCase("")) {
						newRegDate = regCheckDAO.getRegistartionDate(newRegNumber);
					}
					if (denoting != null && denoting.equalsIgnoreCase("Y")) {
						sealDTO.setSelectedSubId("25");
						eForm.setSealDTO(sealDTO);
						String selectedSeal = sealDTO.getSelectedSubId();
						String applyFlag = bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
						String sealContent = "इस दस्तावेज के प्रस्तुतकर्ता द्वारा प्रस्तुत किये गए दस्तावेज के सम्बन्ध में प्रमाणित किया जाता है कि ";
						if ("Y".equalsIgnoreCase(applyFlag)) {
							sealFlag = true;
						} else {
							sealFlag = bd.insertCommonSealDetails(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
							if (sealFlag) {
								if (newRegNumber != null && !newRegNumber.equalsIgnoreCase("")) {
									sealContent = sealContent + "पूर्व दस्तावेज क्रमांक " + newRegNumber + " दिनांक " + newRegDate + " पर  " + stamp + " रूपए स्टाम्प शुल्क चुकाया  जा चुका है";
								}
								if (oldNumber != null && !oldNumber.equalsIgnoreCase("")) {
									sealContent = sealContent + "पूर्व दस्तावेज क्रमांक " + oldNumber + " दिनांक " + oldDate + " पर  " + stamp + " रूपए स्टाम्प शुल्क चुकाया  जा चुका है";
								}
								if (ecode != null && !ecode.equalsIgnoreCase("")) {
									sealContent = sealContent + "इस दस्तावेज पर " + stamp + " रूपए का स्टाम्प शुल्क पूर्व चुकाया गया है";
								}
								if (physicalStamp != null && !physicalStamp.equalsIgnoreCase("")) {
									sealContent = sealContent + "इस दस्तावेज पर " + stamp + " रूपए का स्टाम्प शुल्क पूर्व चुकाया गया है";
								}
								int result = bd.applyDenotingDutySeal(eForm, userId, sealContent);
								if (result == 0) {
									sealFlag = bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal, "Y");
								}
							}
						}
					}
					if (sealFlag) {
						if (linking != null && linking.equalsIgnoreCase("Y")) {
							sealDTO.setSelectedSubId("14");
							eForm.setSealDTO(sealDTO);
							String selectedSeal = sealDTO.getSelectedSubId();
							String applyFlag = bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
							String sealContent = "इस दस्तावेज के प्रस्तुतकर्ता द्वारा प्रस्तुत किये गए दस्तावेज के सम्बन्ध में प्रमाणित किया जाता है कि ";
							if ("Y".equalsIgnoreCase(applyFlag)) {
								sealFlag = true;
							} else {
								sealFlag = bd.insertCommonSealDetails(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
								if (sealFlag) {
									if (newRegNumber != null && !newRegNumber.equalsIgnoreCase("")) {
										sealContent = sealContent + "पूर्व दस्तावेज क्रमांक " + newRegNumber + " दिनांक " + newRegDate + " पर  " + stamp + " रूपए स्टाम्प शुल्क चुकाया  जा चुका है";
									}
									if (oldNumber != null && !oldNumber.equalsIgnoreCase("")) {
										sealContent = sealContent + "पूर्व दस्तावेज क्रमांक " + oldNumber + " दिनांक " + oldDate + " पर  " + stamp + " रूपए स्टाम्प शुल्क चुकाया  जा चुका है";
									}
									if (ecode != null && !ecode.equalsIgnoreCase("")) {
										sealContent = sealContent + "इस दस्तावेज पर " + stamp + " रूपए का स्टाम्प शुल्क पूर्व चुकाया गया है";
									}
									if (physicalStamp != null && !physicalStamp.equalsIgnoreCase("")) {
										sealContent = sealContent + "इस दस्तावेज पर " + stamp + " रूपए का स्टाम्प शुल्क पूर्व चुकाया गया है";
									}
									int result = bd.applyDenotingDutySeal(eForm, userId, sealContent);
									if (result == 0) {
										sealFlag = bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal, "Y");
									}
								}
							}
						}
					}
				} else {
					sealFlag = true;
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return sealFlag;
	}

	//Added for aadhar integration - start 
	public AadharDetailDTO getAadharDetailsByPartyTxnId(String partyTxnId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		AadharDetailDTO aadharDts = regCompDAO.getAadharDetailsByPartyTxnId(partyTxnId);
		return aadharDts;
	}

	public AadharDetailDTO getAadharDetailsByConstTxnNum(String constTxnNum) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getAadharDetailsByConstTxnNum(constTxnNum);
	}

	public String getImagePathByPartyTxnId(String partyTxnId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getImagePathByPartyTxnId(partyTxnId);
	}

	public boolean updateAadharDetails(AadharRespDTO adharRespDTO, String partyTxnId, String consenterTxnId, String imagePath) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.updateAadharDetails(adharRespDTO, partyTxnId, consenterTxnId, imagePath);
	}

	//Added for aadhar integration - end 
	//done by akansha for mutation
	public String getclrFlag(String regInitId) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getclrFlag(regInitId);
	}

	public boolean updateMutationStatus(String regInitId, String mutationStatus) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.updateMutationStatus(regInitId, mutationStatus);
	}
	/*public String  getRefusalRemarks(String regInitId) throws Exception{
		RegCompCheckerDAO regCompDAO =  new RegCompCheckerDAO();
		return regCompDAO.getRefusalRemarks(regInitId);
	}*/
	//Added by rustam
	public ArrayList getCyberTehsilFormDetails(String regNum) throws Exception {
		RegCompCheckerDAO regCompDAO = new RegCompCheckerDAO();
		return regCompDAO.getCyberTehsilFormDetails(regNum);
	}
}
