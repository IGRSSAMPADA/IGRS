package com.wipro.igrs.functionDetailedHeadMap.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.functionDetailedHeadMap.bd.FunctionMapBD;
import com.wipro.igrs.functionDetailedHeadMap.dto.FunctionDetailedHeadMapDTO;

import com.wipro.igrs.functionDetailedHeadMap.form.FunctionMapEdit;
import com.wipro.igrs.baseaction.action.BaseAction;

public class FunctionMapPrepareEditPageAction extends BaseAction {
    


    
    public FunctionMapPrepareEditPageAction() {
    }
    
    public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session)throws Exception{
    	 FunctionMapEdit functionMapEdit=(FunctionMapEdit)form;
         FunctionMapBD refBd=new FunctionMapBD();
         ArrayList functionData=refBd.retrieveAllFunctions();
         ArrayList headData=refBd.retrieveAllHeads();
         
         functionMapEdit.setFunctions(functionData);
         functionMapEdit.setHeads(headData);
         String mapId=request.getParameter("mapId");
         ArrayList data=refBd.getHeadAndFunctionForCertainMapId(mapId);
         if(!data.isEmpty())
         {
        	 functionMapEdit.setSelectedFunc(((FunctionDetailedHeadMapDTO)data.get(0)).getFunctionId());
        	 functionMapEdit.setSelectedHead(((FunctionDetailedHeadMapDTO)data.get(0)).getHeadId());
         }
        // HttpSession session=request.getSession();
         session.setAttribute("mapId", mapId);

        
       // functionMapEdit.setSelectedFunc((String)request.getParameter("funcId"));
         //functionMapEdit.setSelectedHead((String)request.getParameter("headId"));
         return mapping.findForward("functionMap_edit");
    }

}