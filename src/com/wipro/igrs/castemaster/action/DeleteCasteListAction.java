package com.wipro.igrs.castemaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.castemaster.bd.CasteBD;
import com.wipro.igrs.castemaster.form.DeleteCastesForm;

public class DeleteCasteListAction extends BaseAction {
    

	CasteBD casteBD;
    
    public DeleteCasteListAction() {
    	try {
			casteBD = new CasteBD();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
        DeleteCastesForm bean = (DeleteCastesForm)form;
        
        // TODO UNCOMMENT        
//      UserDTO userDTO = (UserDTO)request.getSession().getAttribute(WebConstants.SES_USER_DO);
//      
//      
//      if(userDTO == null)
//      	throw new IGRSException("User is not logged in");
        
        
        String[] casteIdList = bean.getSelectedCasteIdList();
        //HttpSession session = request.getSession();
        String roleId = (String)session.getAttribute("role");
        String funId = (String)session.getAttribute("functionId");
        String userId = (String)session.getAttribute("UserId");
        
        for (int i = 0; i < casteIdList.length; i++) {
			String string = casteIdList[i];
			System.out.println(string);
		}
        
        casteBD.deleteCaste(casteIdList, "hos",roleId,funId,userId);
        
        return mapping.findForward("viewCastMaster");
        
    }

}