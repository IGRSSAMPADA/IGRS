/**
 * PropertyAction.java
 */

package com.wipro.igrs.empmgmt.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.EmpmgmtConstant;
import com.wipro.igrs.empmgmt.bd.CommonBD;
import com.wipro.igrs.empmgmt.dto.AssetDTO;
import com.wipro.igrs.empmgmt.dto.PropertyDTO;
import com.wipro.igrs.empmgmt.form.PropertyForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;

/**
* 
* PropertyAction.java <br>
* PropertyAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class PropertyAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(PropertyAction.class);
	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)

	{
		String FORWARDPAGE = "New";
		try {
			String formName = request.getParameter("name");
			String realFormName = request.getParameter("formName");
			//	HttpSession session = request.getSession();
			String strEmployeeId = (String) session.getAttribute("employeeId");
			EmpMgmtRule empMgmtRule = null;
			CommonBD commonBD = new CommonBD();
			String locale="";
			Locale currentLocale;
			if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
					currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
					locale=currentLocale.toString();
					
				}
			ArrayList errorList = null;
			ArrayList countrylist = new ArrayList();
			ArrayList statelist = null;
			ArrayList propertylist = null;
			ArrayList districtlist = null;
			ArrayList assetlist = null;
			ArrayList assetTypeList = null;
			ArrayList allDistrictlist = new ArrayList();
			ArrayList allstatelist = new ArrayList();
			List finalStatelist = new ArrayList();
			List finaldistrictlist = new ArrayList();
			ArrayList statemap=null;
			ArrayList districtmap=new ArrayList();
			PropertyForm propertyForm = (PropertyForm) form;
			String update = request.getParameter("update");
			Object check = request.getAttribute("statelist");
			if(check == null) {
				allstatelist = commonBD.getStateList(locale);
				request.setAttribute("statelist", allstatelist);
			}
			check = request.getAttribute("districtlist");
			if(check == null) {
				allDistrictlist = commonBD.getDistrictList(locale);
				request.setAttribute("districtlist", allDistrictlist);
			}
			try {
				ArrayList propertyFormList = null;
				if (propertyForm.getPropertylist() != null) {
					propertyFormList = propertyForm.getPropertylist();
					propertylist = new ArrayList();
					for (int i = 0; i < propertyFormList.size(); i++) {
						PropertyDTO propertyDTO = (PropertyDTO) propertyFormList
								.get(i);
						if ((propertyDTO != null && propertyDTO
								.getPropertyaddress() == null)
								|| (propertyDTO != null && propertyDTO
										.getPropertyaddress().equals(""))) {
						} else {
							propertylist.add(propertyDTO);
						}

					}

				}

				if (propertyForm.getAssestlist() != null) {
					ArrayList assetList = propertyForm.getAssestlist();
					assetlist = new ArrayList();
					for (int i = 0; i < assetList.size(); i++) {
						AssetDTO assetDTO = (AssetDTO) assetList.get(i);
						if ((assetDTO != null && assetDTO.getAssettype() == null)
								|| (assetDTO != null && assetDTO.getAssettype()
										.equals(""))) {
						} else {
							assetlist.add(assetDTO);
						}
					}
				}
				if (propertyForm.getActionType() == null
						|| (propertyForm.getActionType() != null && propertyForm
								.getActionType().trim().length() == 0)) {
					propertyForm.setActionType("New");
				}
				if ("addPropertyRow".equals(realFormName)) {
					try {
						propertylist = (ArrayList) session
								.getAttribute("propertyList");
						if (propertylist == null) {
							propertylist = new ArrayList();							
						}else{
							propertylist=propertyForm.getPropertylist();
						}
						refreshPropertyValues(propertylist, request);
//						if(propertylist.size()>0){
//							PropertyDTO tempDTO=(PropertyDTO)propertylist.get(propertylist.size()-1);
//							tempDTO.setStateList(commonBD.getState(tempDTO.getPropertycountry()));
//							tempDTO.setDistrictList(commonBD.getDistrict(tempDTO.getPropertystate()));
//							//statemap.add(commonBD.getState(tempDTO.getCountryId()));							
//						}
						PropertyDTO tempPropertyDTO = new PropertyDTO();
						tempPropertyDTO.setCountryList(commonBD.getCountryList(locale));
						propertylist.add(tempPropertyDTO );
						statemap=(ArrayList) session
								.getAttribute("statemap");
//						if (statemap == null) {
//							statemap = new ArrayList();
//						}
						
//						session.setAttribute("statemap", statemap);
						propertyForm.setPropertylist(propertylist);
						session.setAttribute("propertyList", propertylist);
						return mapping.findForward("property");
					} catch (Exception e) {
					}
				} else if ("dellPropertyRow".equals(realFormName)) {
					try {
						int index = Integer.parseInt(request
								.getParameter("delPropIndex"));
						
						propertylist = (ArrayList) session
								.getAttribute("propertyList");
						refreshPropertyValues(propertylist, request);
						if (propertylist != null) {
							propertylist.remove(index);
						} else {
							propertylist = new ArrayList();
						}
						propertyForm.setPropertylist(propertylist);
						session.setAttribute("propertyList", propertylist);
						return mapping.findForward("property");
					} catch (Exception e) {
					}
				} else if ("addAssetRow".equals(realFormName)) {
					try {
						assetlist = (ArrayList) session
								.getAttribute("assetList");
						if (assetlist == null) {
							assetlist = new ArrayList();
						}else{
							assetlist=propertyForm.getAssestlist();
						}
						assetlist.add(new AssetDTO());
						propertyForm.setAssestlist(assetlist);
						session.setAttribute("assetList", assetlist);
						return mapping.findForward("property");
					} catch (Exception e) {
					}
				} else if ("dellAssetRow".equals(realFormName)) {
					try {
						assetlist = (ArrayList) session
								.getAttribute("assetList");
						int index = Integer.parseInt(request
								.getParameter("delAssetIndex"));
						if (assetlist != null) {
							assetlist.remove(index);
						} else {
							assetlist = new ArrayList();
						}
						propertyForm.setAssestlist(assetlist);
						session.setAttribute("assetList", assetlist);
						return mapping.findForward("property");
					} catch (Exception e) {
					}
				}
				if (propertyForm.getActionType() != null
						&& propertyForm.getActionType().equalsIgnoreCase("New")) {
					session.removeAttribute("propertyList");
					session.removeAttribute("assetList");
					session.removeAttribute("statemap");
					session.removeAttribute("districtmap");
					session.removeAttribute("empid");
					session.removeAttribute("propertyupdate");
					propertylist = commonBD.getPropertyList(strEmployeeId,locale);
					if(propertylist != null) {
						propertyForm.setPropertylist(propertylist);
						propertyFormList = propertylist;
					}
					assetlist = commonBD.getAssetList(strEmployeeId);
					if(assetlist != null) {
						propertyForm.setAssestlist(assetlist);
					}
					countrylist = commonBD.getCountryList(locale);
					statelist = commonBD.getStateList(locale);
					districtlist = commonBD.getDistrictList(locale);
					assetTypeList = commonBD.getAssetTypeList();
					if (update != null) {
						session.setAttribute("propertyupdate", update);
					}
					session.setAttribute("assetTypeList", assetTypeList);
					session.setAttribute("CountryList", countrylist);
					session.setAttribute("countryList", countrylist);
					session.setAttribute("propertyList", propertyFormList);
					session.setAttribute("assetList", assetlist);
					request.setAttribute("statelist", statelist);
					request.setAttribute("districtlist", districtlist);
					FORWARDPAGE = "New";
				} else if (propertyForm.getActionType() != null
						&& propertyForm.getActionType()
								.equalsIgnoreCase("save")) {
					empMgmtRule = new EmpMgmtRule();
					ArrayList errorpropertyList = new ArrayList();
					ArrayList errorassertList = new ArrayList();
					errorList = new ArrayList();
					errorpropertyList = empMgmtRule
							.validatePropertyList(propertylist);
					errorassertList = empMgmtRule.validateAssertList(assetlist);
					if (errorpropertyList != null) {
						for (int i = 0; i < errorpropertyList.size(); i++) {
							errorList.add(errorpropertyList.get(i));
						}
					}
					if (errorassertList != null) {
						for (int i = 0; i < errorassertList.size(); i++) {
							errorList.add(errorassertList.get(i));
						}
					}
					if (empMgmtRule.isError()) {
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
								Boolean.TRUE); // errorFlag
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_LIST, errorList); // ErrorList
						FORWARDPAGE = "property"; // "property"
					} else {
						
						boolean result = commonBD.addProperty(propertylist,
								assetlist,
								(String) session.getAttribute("UserId"),
								strEmployeeId);
						if (result
								&& session.getAttribute("propertyupdate") == null) {
							ArrayList list = null;
							if (session.getAttribute("tablist") != null) {
								list = (ArrayList) session
										.getAttribute("tablist");
							} else {
								list = new ArrayList();
							}
							if (!list.contains("TALENT")) {
								propertyForm.setActionType("");
								FORWARDPAGE = "talent";
							} else if (!list.contains("BANK")) {
								propertyForm.setActionType("");
								FORWARDPAGE = "bank";
							} else if (!list.contains("OFFICE")) {
								propertyForm.setActionType("");
								FORWARDPAGE = "office";
							} else {
								FORWARDPAGE = "success";
							}
							list.add("PROPERTY");
							session.setAttribute("tablist", list);
							if(locale.equalsIgnoreCase("hi_IN")){
								request.setAttribute("success",
										"<font color=green>संपत्ति की जानकारी सफलतापूर्वक सबमिट की गई है </font>");	
							}else{
								request.setAttribute("success",
										"<font color=green>Property Information submitted successfully!</font>");		
							}

							
							session.removeAttribute("propertyList");
							session.removeAttribute("assetList");
							session.removeAttribute("districtmap");
							// FORWARDPAGE = "talent";
						} else if (session.getAttribute("propertyupdate") != null
								&& session.getAttribute("propertyupdate")
										.toString().equals("true")) {

							FORWARDPAGE = "viewpersonal";
						} else {
							if(locale.equalsIgnoreCase("hi_IN")){
								request.setAttribute("failure",
										"<font color=red>संपत्ति की जानकारी सफलतापूर्वक सबमिट नहीं हो पाई है </font>");	
							}else{
								request.setAttribute("failure",
										"<font color=red>Property Information not submitted successfully!</font>");		
							}
							
							FORWARDPAGE = "property";
						}
					}
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward(FORWARDPAGE);
	}

	/**
	 * @param propertylist
	 * @param request
	 */
	private void refreshPropertyValues(ArrayList propertylist,
			HttpServletRequest request) {
		try {
			// com.wipro.igrs.empmgmt.dto.PropertyDTO
			// propertyArrDTO[0].propertyaddress
			// propertyArrDTO[0].propertycountry
			// propertyArrDTO[0].propertydistrict
			// propertyArrDTO[0].propertypostalcode
			// propertyArrDTO[0].propertyregdate
			// propertyArrDTO[0].propertyregid
			// propertyArrDTO[0].propertyshare
			// propertyArrDTO[0].propertystate

			String prefix = "propertyArrDTO[";
			String suffix = "].";
			String propertyaddress = "propertyaddress";
			String propertycountry = "propertycountry";
			String propertydistrict = "propertydistrict";
			String propertypostalcode = "propertypostalcode";
			String propertyregdate = "propertyregdate";
			String propertyregid = "propertyregid";
			String propertyshare = "propertyshare";
			String propertystate = "propertystate";
			String value = "";

			StringBuilder paramBldr = new StringBuilder();
			for (int iLoop = 0; iLoop < propertylist.size(); iLoop++) {
				PropertyDTO property = (PropertyDTO) propertylist.get(iLoop);
				// propertyaddress
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(propertyaddress);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					property.setPropertyaddress(value);
				}
				// propertycountry
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(propertycountry);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					property.setPropertycountry(value);
				}
				// propertydistrict
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(propertydistrict);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					property.setPropertydistrict(value);
				}
				// propertypostalcode
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(propertypostalcode);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					property.setPropertypostalcode(value);
				}
				// propertyregdate
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(propertyregdate);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					property.setPropertyregdate(value);
				}
				// propertyregid
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(propertyregid);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					property.setPropertyregid(value);
				}
				// propertyshare
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(propertyshare);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					property.setPropertyshare(value);
				}
				// propertystate
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(propertystate);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					property.setPropertystate(value);
				}

			}
			paramBldr.delete(0, paramBldr.length());
		} catch (Exception e) {
		}
		
	}
}
