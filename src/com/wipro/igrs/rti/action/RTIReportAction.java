package com.wipro.igrs.rti.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.rti.bd.RTIBD;
import com.wipro.igrs.rti.form.RTIReportForm;

public class RTIReportAction extends BaseAction {
    public RTIReportAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws ServletException, 
                                                                      IOException {
        RTIReportForm rtiform = (RTIReportForm)form;
        RTIBD rbd = new RTIBD();
        rtiform.getRTIReportDTO().setDurationFrom(rtiform.getDurationFrom());
        rtiform.getRTIReportDTO().setDurationTo(rtiform.getDurationTo());
        String report = rtiform.getRTIReportDTO().getSearchReportType();
        String annual = rtiform.getRTIReportDTO().getSearchAnualType();
        ArrayList reportList = rbd.getRTIReport(rtiform.getRTIReportDTO());
        session.setAttribute("ReportList", reportList);


        if ("1".equals(report) && "0".equals(annual)) {
            session.setAttribute("report", "annual1");
            return mapping.findForward("annual1");
        }
        if ("1".equals(report) && "1".equals(annual)) {
            session.setAttribute("report", "annual2");
            return mapping.findForward("annual1");
        } else
            reportList = rbd.getRTIQuarterReport(rtiform.getRTIReportDTO());
        session.setAttribute("report", "quarter");
        session.setAttribute("ReportListQuarter", reportList);
        return mapping.findForward("annual1");


    }
}
