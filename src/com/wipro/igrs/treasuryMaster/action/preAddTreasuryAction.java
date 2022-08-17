package com.wipro.igrs.treasuryMaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.treasuryMaster.bd.TreasureBD;
import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;
import com.wipro.igrs.treasuryMaster.form.TreasuryForm;

public class preAddTreasuryAction extends BaseAction {
    
	private Logger logger = (Logger) Logger.getLogger(preAddTreasuryAction.class);
	private TreasureBD treasureBD;
	private TreasureDTO dto; 
	private TreasuryForm treasuryForm;
	ArrayList treasuryList=null;
    public preAddTreasuryAction() 
    {
    	
    }
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception 
			
	{
		treasureBD = new TreasureBD();
		treasuryForm = (TreasuryForm)form;
		logger.info("we are in TreasuryAction");
		dto = new TreasureDTO();
		dto.setTreasuryName(treasuryForm.getTreasuryName());
		dto.setTreasuryAddress(treasuryForm.getTreasuryAddress());
		dto.setTreasuryPhone(treasuryForm.getTreasuryPhone());
		treasuryForm.setTreasuryName("");
		treasuryForm.setTreasuryAddress("");
		treasuryForm.setTreasuryPhone("");
		
		return mapping.findForward("success");
	}
}