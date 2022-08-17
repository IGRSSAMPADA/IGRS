package com.wipro.igrs.batchJob.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.batchJob.bd.BatchJobBD;
import com.wipro.igrs.batchJob.dto.BatchJobDTO;
import com.wipro.igrs.batchJob.form.BatchJobForm;


public class BatchJobViewAction extends Action
{
	
	private  Logger logger = 
		(Logger) Logger.getLogger(BatchJobViewAction.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response)
	{
		BatchJobForm formData = (BatchJobForm) form;
		BatchJobDTO jobDto = formData.getBatchDto();
		BatchJobBD bd = null;
		ArrayList list = new ArrayList();
		try
		{
			bd = new BatchJobBD();
			list = bd.getJobNames(jobDto);
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionError error = new ActionError("error.msg");
			errors.add("key1",error);
			saveErrors(request,errors);
			return mapping.getInputForward();
					
		}
		
		request.setAttribute("batchJobKey",list);
		return mapping.findForward("success");
	}

}
