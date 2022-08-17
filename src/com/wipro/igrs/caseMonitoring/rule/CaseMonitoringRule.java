/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.caseMonitoring.rule;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.PropertiesFileReader;
//import com.wipro.igrs.casemonitoring.constant.CommonConstant;
//import com.wipro.igrs.casemonitoring.dao.CaseMonitoringDAO;
//import com.wipro.igrs.casemonitoring.dto.CaseMonitoringDTO;
//import com.wipro.igrs.casemonitoring.form.CaseMonitoringForm;


public class CaseMonitoringRule {
    private boolean error;
    PropertiesFileReader pr;
    private  Logger logger = Logger.getLogger(CaseMonitoringRule.class);

    public CaseMonitoringRule() {

    }

    protected boolean nullOrBlank(String str) {
        return ((str == null) || (str.length() == 0));
    }

   /* public ArrayList validateCaseMonitoringRule(Object objForm) {
        boolean flag = false;
        System.out.println("inside casemonitoringRule");
        CaseMonitoringForm form = (CaseMonitoringForm)objForm;
        CaseMonitoringDTO dto = form.getWillDTO();

        //System.out.println("*******" + dto.getAge());
        ArrayList errorList = new ArrayList();
        try {
            pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
            errorList.add(pr.getValue("error.header"));
           // if (CommonConstant.WILL_DEPOSIT_FORM.equalsIgnoreCase(dto.getCaseMonitoringForm())) {
               // if (CommonConstant.WILL_DEPOSIT_INSERT_ACTION.equalsIgnoreCase(dto.getCaseMonitoringInsertAction())) {


                    if (dto.getFirstName() == null) {
                        flag = true;
                        errorList.add(pr.getValue("error.casemonitoring.firstname"));
                    }

                  if (dto.getMidName() == null) {
                        flag=true;
                        map.put("willDTO.midName", "error.casemonitoring.middlename");
                    }
                    if (dto.getLastName() == null) {
                        flag=true;
                        map.put("willDTO.lastName", "error.casemonitoring.lastname");
                    }
                    if (dto.getGender() == null) {
                        flag=true;
                        map.put("willDTO.gender", "error.casemonitoring.gender");
                    }*/

                    //if (dto.getAge() == 0) {
                        //errors.add("willDTO.age", new ActionError("error.casemonitoring.testageequalzero"));
                       // flag = true;
                       // errorList.add(pr.getValue("error.casemonitoring.testageequalzero"));

                        //map.put("willDTO.age", "error.casemonitoring.testageequalzero");
                   // }
                    /*
                    if (dto.getFatherName() == null) {
                        map.put("willDTO.fatherName", "error.casemonitoring.fatherName");
                    }

                    if (dto.getMotherName() == null) {
                        map.put("willDTO.motherName", "error.casemonitoring.motherName");
                    }

                    if (dto.getAddress() == null) {
                        map.put("willDTO.address", "error.casemonitoring.address");
                    }

                    if (dto.getCity() == null) {
                        map.put("willDTO.city", "error.casemonitoring.city");
                    }

                    if (dto.getEmail() == null) {
                        map.put("willDTO.email", "error.casemonitoring.email");
                    }
                    setError(flag);
                }
            }
        } catch (Exception x) {
            System.out.println("validatecasemonitoringRule :-" + x);
        }
        return errorList;
    }*/

    /*public ArrayList validateWillWithDrawal(CaseMonitoringDTO objForm) {
        String param[] = new String[2];
        ArrayList errorList = new ArrayList();
        boolean flag = false;

        try {
            pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
            if (objForm != null) {
                param[0] = objForm.getWillId();
                param[1] = CommonConstant.WILL_WITHDRAWN_FLAG;
                CaseMonitoringDAO dao = new CaseMonitoringDAO();
                ArrayList lst = dao.getCaseMonitoring(param);
                errorList.add(pr.getValue("error.header"));
                if (lst != null) {
                    if (lst.size() > 0) {
                        errorList.add(pr.getValue("error.casemonitoring.withdrawalheader") + 
                                      " " + param[0] + " " + 
                                      pr.getValue("error.casemonitoring.withdrawalfooter"));
                        flag = true;
                    }
                }

            }
        } catch (Exception x) {
            System.out.println(x);
        }
        setError(flag);
        return errorList;
    }*/

    public ArrayList validateWillRecord(Object objForm) {
        boolean flag = false;
        logger.debug("inside validateWillReterieveRule");


        ArrayList list = (ArrayList)objForm;
        ArrayList errorList = new ArrayList();
        try {
            pr = PropertiesFileReader.getInstance("resources.igrs");
            errorList.add(pr.getValue("error.header"));
            if (list == null) {
                flag = true;
                errorList.add(pr.getValue("error.casemonitoring.recordnofound"));
            }
            setError(flag);


        } catch (Exception x) {
            logger.debug("validatecasemonitoringRule :-" + x);
        }
        return errorList;
    }
    public ArrayList validateFileExtn(String fileName) {
        boolean flag = false;
        logger.debug("inside validateWillReterieveRule");


      
        ArrayList errorList = new ArrayList();
        try {
            pr = PropertiesFileReader.getInstance("resources.igrs");
            errorList.add(pr.getValue("error.header"));
            
                errorList.add(pr.getValue("error.audit.fileType"));
            
            setError(flag);


        } catch (Exception x) {
            logger.debug("validatecasemonitoringRule :-" + x);
        }
        return errorList;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }
}
