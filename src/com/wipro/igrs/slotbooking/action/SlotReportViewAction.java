package com.wipro.igrs.slotbooking.action;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.slotbooking.bo.SlotReportBO;
import com.wipro.igrs.slotbooking.dto.SRReportDTO;
import com.wipro.igrs.slotbooking.form.SRSlotReportForm;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

public class SlotReportViewAction extends BaseAction
{

    public SlotReportViewAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session)
        throws Exception
    {
        
        String forwardJsp = "srSlotReport";
        String language = (String)session.getAttribute("languageLocale");
        String officeId = (String)session.getAttribute("loggedToOffice");
        SRSlotReportForm sbform = (SRSlotReportForm)form;
        String lang = (String)session.getAttribute("languageLocale");
        String fwdPage = request.getParameter("fwdName");
        String regTxnId = request.getParameter("regisId");
        String dutyID = request.getParameter("dutyId");
        String deedPath = request.getParameter("path");
        String partyID = request.getParameter("partyId");
        Date date = new Date();
        SRReportDTO srDTO = new SRReportDTO();
        SimpleDateFormat yearformat = new SimpleDateFormat("MM/dd/yyyy");
        String yfmt = yearformat.format(date);
        srDTO.setFromDateSys(yfmt);
        if(sbform != null)
        {
            String actionID = sbform.getActionName();
            SRReportDTO dto = sbform.getReportDTO();
            
            if("SUBMIT_SLOT_REPORT_ACTION".equalsIgnoreCase(sbform.getActionName()))
            {
                ArrayList reportList = new ArrayList();
                SlotReportBO slotBO = new SlotReportBO();
                reportList = slotBO.getSlotReport(sbform, officeId);
                sbform.getReportDTO().setSroList(reportList);
                if(reportList.size() == 0)
                {
                    if(language.equalsIgnoreCase("en"))
                        request.setAttribute("failureMsg", "No record found");
                    else
                        request.setAttribute("failureMsg", "\u0915\u094B\u0908 \u0930\u093F\u0915\u0949\u0930\u094D\u0921 \u0928\u0939\u0940\u0902 \u092E\u093F\u0932\u093E");
                    forwardJsp = "srSlotReport";
                } else
                {
                    forwardJsp = "srSlotOfficeView";
                }
            }
            if(fwdPage != null && "registrationDetails".equalsIgnoreCase(fwdPage))
            {
                ArrayList reportList2 = new ArrayList();
                ArrayList reportList3 = new ArrayList();
                SlotReportBO slotBO2 = new SlotReportBO();
                reportList2 = slotBO2.getPartyDetails(regTxnId);
                sbform.getReportDTO().setSroList(reportList2);
                reportList3 = slotBO2.getconsentorDetails(regTxnId);
                sbform.getReportDTO().setRepoList(reportList3);
                if(reportList2.size() == 0)
                {
                    if(language.equalsIgnoreCase("en"))
                        request.setAttribute("failureMsg", "No record found");
                    else
                        request.setAttribute("failureMsg", "\u0915\u094B\u0908 \u0930\u093F\u0915\u0949\u0930\u094D\u0921 \u0928\u0939\u0940\u0902 \u092E\u093F\u0932\u093E");
                    forwardJsp = "partydetailsView";
                } else
                {
                    forwardJsp = "partydetailsView";
                }
            }
            if(fwdPage != null && "registrationDuties".equalsIgnoreCase(fwdPage))
            {
                ArrayList reportList2 = new ArrayList();
                SlotReportBO slotBO2 = new SlotReportBO();
                reportList2 = slotBO2.getDuties(dutyID);
                sbform.getReportDTO().setSroList(reportList2);
                if(reportList2.size() == 0)
                {
                    if(language.equalsIgnoreCase("en"))
                        request.setAttribute("failureMsg", "No record found");
                    else
                        request.setAttribute("failureMsg", "\u0915\u094B\u0908 \u0930\u093F\u0915\u0949\u0930\u094D\u0921 \u0928\u0939\u0940\u0902 \u092E\u093F\u0932\u093E");
                    forwardJsp = "partydutyView";
                } else
                {
                    forwardJsp = "partydutyView";
                }
            }
        }
        if(fwdPage != null && "downloadDeed".equalsIgnoreCase(fwdPage))
        {
            byte bytes[] = DMSUtility.getDocumentBytes(deedPath);
            if(bytes != null)
                DMSUtility.downloadDocument(response, deedPath, bytes);
        }
        if(request.getAttribute("actId") != null)
        {
            System.out.println(request.getAttribute("actId").toString());
            sbform.setActionName("");
            sbform.setActionID("");
            sbform.setFormName("");
        }
        sbform.setActionName("");
        sbform.setActionID("");
        sbform.setFormName("");
        sbform.setFromDate("");
        sbform.setToDate("");
        return mapping.findForward(forwardJsp);
    }
}