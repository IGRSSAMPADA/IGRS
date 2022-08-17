package com.wipro.igrs.treasuryMaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.activitymaster.bd.ActivityBD;
import com.wipro.igrs.activitymaster.dto.ActivityDTO;
import com.wipro.igrs.activitymaster.form.ActivityForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.treasuryMaster.bd.TreasureBD;
import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;
import com.wipro.igrs.treasuryMaster.form.TreasuryForm;

public class deleteTreasuryAction extends BaseAction {
    
	private Logger logger = (Logger) Logger.getLogger(deleteTreasuryAction.class);
	private TreasureBD treasureBD;
	private TreasureDTO dto; 
	private TreasuryForm treasuryForm;
	
    public deleteTreasuryAction() 
    {
    	
    }
    
    //public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    //    // TODO: Write method body
    //    throw new UnsupportedOperationException("Method is not implemented");
   // }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception 
			
	{
		logger.info("we are in TreasuryAction");
		String target = "viewTreasuryMaster";
		treasureBD = new TreasureBD();
		treasuryForm = (TreasuryForm)form;
		dto = new TreasureDTO();
		String user = (String) session.getAttribute("UserId");
		//user = "aboTrika";
		
		String[] selected = treasuryForm.getSelected();
		for (int i = 0; i < selected.length; i++) {
			System.out.println("--------------->>>>>>>"+selected[i]);
		}
		treasureBD.deleteTreasurymaster(selected,user);
		
		
		ArrayList treasuryList = treasureBD.getTreasuryList();	
		request.setAttribute("treasuryList", treasuryList);
		
		return mapping.findForward(target);
	}
    
}