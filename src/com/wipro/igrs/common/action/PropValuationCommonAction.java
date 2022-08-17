package com.wipro.igrs.common.action;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.common.bo.CommonBO;
import com.wipro.igrs.common.dto.CommonDTO;
import com.wipro.igrs.common.form.CommonForm;
import com.wipro.igrs.newPropvaluation.dto.SampadaKhasraClrDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;


public class PropValuationCommonAction extends BaseAction {
	private static Logger logger = org.apache.log4j.Logger.getLogger(PropValuationCommonAction.class);
	 String FORWARD_JSP = "";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		CommonForm commonForm=(CommonForm)form;
		CommonBO commonBO=new CommonBO();
		CommonDTO commonDTO=commonForm.getCommonDTO();
		String language = (String)session.getAttribute("languageLocale");
		String type=request.getParameter("type");
		String id=request.getParameter("valID");
		//String subId=request.getParameter("subvalID");
		// added by vinay Sharma
		String districtID=request.getParameter("districtID");
		commonDTO.setSubClauseChkd("");
		String userId = (String) session.getAttribute("UserId");
		
		String propTypeId;
		if(id.startsWith("a"))
		{
			propTypeId="2";
		}
		else
		{
			propTypeId=commonBO.getpropTypeId(language,id);
		}
		
		if("1".equalsIgnoreCase(propTypeId))
		{
			CommonDTO cdto=commonBO.getplotPropertyDetails(language,id);
			commonForm.setCommonDTO(cdto.getCmDTO());
			
			commonForm.getCommonDTO().setSubClauseList(commonBO.getallSubClauseList(language,id,commonDTO));
			
			
			if(commonBO.getallSubClauseList(language,id,commonDTO).size()!=0 && commonBO.getallSubClauseList(language,id,commonDTO)!=null)
			{
				
				commonForm.getCommonDTO().setSubClauseChkd("Y");
				FORWARD_JSP="plotView";
			}
			
			else
			{
				commonForm.getCommonDTO().setSubClauseChkd("N");
				FORWARD_JSP="plotView";
			}
			
			FORWARD_JSP="plotView";
		}
		
		
		else if("3".equalsIgnoreCase(propTypeId))
		{
			
			commonForm.getCommonDTO().setAgriAreaDetailsList(commonBO.getagriAreaDetailsList(language,id,commonForm));
			
			commonForm.getCommonDTO().setAgriPropDetailsList(commonBO.getagriPropDetailsList(language,id,commonDTO));
			
			commonForm.getCommonDTO().setSubClauseList(commonBO.getallSubClauseList(language,id,commonDTO));
			
			commonForm.getCommonDTO().setAgriTreeDetailsList(commonBO.getAgriTreeDetailsList(language,id,commonDTO));
		
			if(commonBO.getAgriTreeDetailsList(language,id,commonDTO).size()!=0 && commonBO.getAgriTreeDetailsList(language,id,commonDTO)!=null)
			{
				
				commonForm.getCommonDTO().setAgriTreeChkd("Y");
				
			}
			
			else
			{
				commonForm.getCommonDTO().setAgriTreeChkd("N");
				
			}
			
			
			if(commonBO.getallSubClauseList(language,id,commonDTO).size()!=0 && commonBO.getallSubClauseList(language,id,commonDTO)!=null)
			{
				
				commonForm.getCommonDTO().setSubClauseChkd("Y");
				
			}
			
			else
			{
				commonForm.getCommonDTO().setSubClauseChkd("N");
				
			}
			
			String clrFlag=commonBO.getClrFlag(id);			
			if (clrFlag != null) {

				if (clrFlag.equalsIgnoreCase("Y")) {

					// String proId=commonForm.getPropertyId();
					ArrayList propDtlsClr = commonBO
							.getAgriPropDetailsListClr(id);
					PropertiesFileReader pr = PropertiesFileReader
							.getInstance("resources.igrs");

					ArrayList subList;
					ArrayList<CommonDTO> paramList1 = new ArrayList<CommonDTO>();
					CommonDTO cDto;
					for (int i = 0; i < propDtlsClr.size(); i++) {
						subList = (ArrayList) propDtlsClr.get(i);
						logger.debug("subListSize:" + subList);
						// commonForm.setClrFlag((String)subList.get(0));
						cDto=new CommonDTO();
						//commonDTO1.setCommonAgriTxnid(subList.get(0)==null ?"-":subList.get(0).toString());
					//	cDto.setIrregatedAreaClr(subList.get(0)==null ?"-":subList.get(0).toString());
					//	cDto.setUnirreSingleCropClr(subList.get(1)==null ?"-":subList.get(1).toString());
					//	cDto.setUnirreDoubleCropClr(subList.get(2)==null ?"-":subList.get(2).toString());
						cDto.setKhasraAreaClr(subList.get(0)==null ?"-":subList.get(0).toString());
						cDto.setKhasraNoClr(subList.get(1)==null ?"-":subList.get(1).toString());
						commonForm.setRinPustikaCLr(subList.get(2)==null ?"-":subList.get(2).toString());
						// For Undiverted
						cDto.setSampdaTotalUndivertedArea(subList.get(3)==null ?"-":subList.get(3).toString());
						cDto.setSampadaSingleCropArea(subList.get(4)==null ?"-":subList.get(4).toString());
						cDto.setSampadaDoubleCropArea(subList.get(5)==null ?"-":subList.get(5).toString());
						cDto.setSampadaIrrigatedArea(subList.get(6)==null ?"-":subList.get(6).toString());
						// For Diverted
						cDto.setSampadaTotalDivertedArea(subList.get(7)==null ?"-":subList.get(7).toString());
						cDto.setSampadaDivertedResidentialArea(subList.get(8)==null ?"-":subList.get(8).toString());			
						cDto.setSampadaDivertedCommercialArea(subList.get(9)==null ?"-":subList.get(9).toString());
						cDto.setSampadaDivertedIndustrialArea(subList.get(10)==null ?"-":subList.get(10).toString());
						cDto.setSampadaDivertedEducationalArea(subList.get(11)==null ?"-":subList.get(11).toString());
						cDto.setSampadaDivertedHealthArea(subList.get(12)==null ?"-":subList.get(12).toString());
						cDto.setSampadaDivertedOthersArea(subList.get(13)==null ?"-":subList.get(13).toString());
						
						paramList1.add(cDto);
						/*if (subList.get(0) != null) {
							// commonForm.getClrDto().setIrrigatedArea(String.valueOf(subList.get(0)));
							commonDTO.setIrregatedAreaClr(String
									.valueOf(subList.get(0)));

						} else {
							commonDTO.setIrregatedAreaClr("Data Not Available");
						}

						if (subList.get(1) != null) {
							// commonForm.getClrDto().setNonIrrigatedArea(String.valueOf(subList.get(1)));
							commonDTO.setUnirreSingleCropClr(String
									.valueOf(subList.get(1)));
						} else {
							commonDTO.setUnirreSingleCropClr("Data Not Available");
						}
						if (subList.get(2) != null) {
							// commonForm.getClrDto().setDoubleCropArea((String)subList.get(2));
							commonDTO.setUnirreDoubleCropClr((String) subList
									.get(2));
						} else {
							commonDTO.setUnirreDoubleCropClr("Data Not Available");
						}*/
						if (subList.get(0) != null) {
							// commonForm.getClrDto().setKhasraTotalArea((String)subList.get(3));
							commonDTO.setKhasraAreaClr((String) subList.get(0));
						} else {
							commonDTO.setKhasraAreaClr("Data Not Available");
						}
						if (subList.get(1) != null) {
							// commonForm.getClrDto().setIrrigatedArea(String.valueOf(subList.get(0)));
							commonDTO.setKhasraNoClr((String)
									subList.get(1));

						} else {
							commonDTO.setKhasraNoClr("Data Not Available");
						}

					}	
				
					commonForm.setClrKhasraDetails(paramList1);
				
				
				FORWARD_JSP="agriViewClr";
			}else
			{
				FORWARD_JSP="agriView";
			}
		}
		else
		{
		FORWARD_JSP="agriView";
			
		}
			
		}
		else if("2".equalsIgnoreCase(propTypeId))
		{
			CommonDTO cdto=commonBO.getbuildingView(id, language);
			commonForm.setCommonDTO(cdto);


			

			ArrayList subclaueList;
			if(id.startsWith("a"))
			{
				subclaueList=commonBO.getallSubClauseListBuildAgri(language,id.substring(1),commonDTO);
	
			}
			else
			{
				subclaueList=commonBO.getallSubClauseList(language,id,commonDTO);

			}

			if(subclaueList!=null && subclaueList.size()!=0)

			{
				
				commonForm.getCommonDTO().setSubClauseChkd("Y");
				commonForm.getCommonDTO().setSubClauseList(subclaueList);
			}
			
			else
			{
				commonForm.getCommonDTO().setSubClauseChkd("N");
				
			}
			FORWARD_JSP="buildingView";	
			
		 }
		
		return mapping.findForward(FORWARD_JSP);
	}
	
	
	
}
	