/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.pot.action;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.action.CaveatsViewSearchAction;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.pot.bd.PotBD;
import com.wipro.igrs.pot.dto.potDTO;
import com.wipro.igrs.pot.form.PotFORM;
import com.wipro.igrs.propertylock.dto.PropertyLockDTO;

/**
* 
* CaveatRegIdSearchAction.java <br>
* CaveatRegIdSearchAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class potRegIdSrch extends BaseAction {
    
	private Logger logger = 
		(Logger) Logger.getLogger(potRegIdSrch.class);

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
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException,Exception {
    	logger.debug("WE ARE IN potRegIdSrch Debug"); 
    	String FORWAD_SUCCESS="success";
		 try{
            PotFORM potfrm = (PotFORM) form;
           potDTO pdto = potfrm.getPotDTO();
            PotBD bd = new PotBD();
       
            pdto.setSelectedItems(null);
          // pdto.setRegNumber("");
            String RegNo=pdto.getRegNumber();
            String actionType = request.getParameter("actionType");
            System.out.println("ACTION TYPE-----"+actionType);
        	String actionName = pdto.getActionName();
        	  System.out.println("ACTION Name-----"+actionName);
        	  
        	  
        	  
        	  
         
           if("addPotDet".equals(actionType)) 
           {
            	
            	ArrayList searchResultList = potfrm.getSearchResultList();
            	String[] selectedIndexes = request.getParameterValues("chkProperty");
            	ArrayList selectedItems = null;
            	if(selectedIndexes != null && selectedIndexes.length>0) {
            		selectedItems = new ArrayList();
					for (String selectedIndex : selectedIndexes) {
						int index = Integer.parseInt(selectedIndex);
						selectedItems.add(searchResultList.get(index-1));
					}
					potfrm.setSearchResultList(null);
					pdto.setCloneSelectedItems(selectedItems);
					System.out.println(pdto.getCloneSelectedItems());
					potfrm.getPotDTO().setCloneSelectedItems(pdto.getCloneSelectedItems());
					//pdto.setCloneSelectedItems(null);
					//caveatDto.setSelectedItems(selectedItems);
					
            		//session.setAttribute("cvtSearchUpdate","yes");
            	} else {
            		request.setAttribute("error", "Please select at least one Registration Number/कम से कम एक पंजीकरण संख्या का चयन करें");
            	}
            	
            	//pdto.setErrorMsg("FLAG");
				FORWAD_SUCCESS = "success";
				
				
				
            }
            
           else if ("addPotDetls".equalsIgnoreCase(actionType)) {
       		
   			try {
   				
   			potfrm = (PotFORM) session.getAttribute("potfrm");
   				
   			System.out.println(potfrm.getPotDTO().getCloneSelectedItems().size());
   				
   				potfrm.getPotDTO().setSelectedItems(
						(ArrayList)pdto.getCloneSelectedItems());
   				System.out.println(pdto.getCloneSelectedItems());
   				
   				pdto.setCloneSelectedItems(null);
				potfrm.setSearchResultList(null);
		           pdto.setRegNumber("");


   				ArrayList searchList = pdto.getCloneSelectedItems();
   				
   				//potfrm.getPotDTO().getCloneSelectedItems().clear();
   				FORWAD_SUCCESS = "screen";
   			} catch (Exception e) {
   			e.printStackTrace();
   			}
   			
			FORWAD_SUCCESS = "screen";		
   		}
           else if ("cancel".equalsIgnoreCase(actionType)) {
       		
       		try {
       			potfrm = (PotFORM) session.getAttribute("potfrm");
       			potfrm.setSearchResultList(new ArrayList());
       			potfrm.getPotDTO().setCloneSelectedItems(new ArrayList());
       			pdto.setRegNumber("");
       			FORWAD_SUCCESS = "cancel";
       		//	session.removeAttribute("cvtSearchUpdate");
       		} catch (Exception e) {
       		}
       	} 
            	else     
            {
            		 potfrm.setSearchResultList(null);
            	 
            	   
				ArrayList searchResultList = bd.searchRegId(RegNo);
				if (searchResultList.size() > 0) {
					potfrm.setSearchResultList(searchResultList);
					potDTO a = (potDTO) searchResultList.get(0);
					potfrm.setEstampCodech(a.getEstampCode());
					potfrm.setEstampAmountch(a.getEstampAmount());
					potfrm.setCaseCh(a.getCaseStat());
					potfrm.setCaseIDCh(a.getCaseNum());
					//pdto.setErrorMsg("FLAG");
					FORWAD_SUCCESS = "success";
				} else {
					request.setAttribute("error", "This Registration is incomplete. Please try with another Registration Number./ यह पंजीकरण संख्या मौजूद नहीं  है");
					
					
					FORWAD_SUCCESS = "failure";
				}
				session.setAttribute("potfrm", potfrm);
	            return mapping.findForward(FORWAD_SUCCESS); 
            }
           // session.setAttribute("potfrm", potfrm);
          //  return mapping.findForward(FORWAD_SUCCESS); 
        
		 
           
	
	
	} 	
		 
	catch(Exception e)
    {
        logger.error(e);
        return mapping.findForward("failure");
    }
         return mapping.findForward(FORWAD_SUCCESS); 

	
	
}
}
