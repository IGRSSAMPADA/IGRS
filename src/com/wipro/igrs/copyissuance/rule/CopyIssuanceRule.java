package com.wipro.igrs.copyissuance.rule;

/**
 * ===========================================================================
 * File           :   CopyIssuanceRule.java
 * Description    :   Represents the rule Class

 * Author         :   
 * Created Date   :   Jan 07, 2008

 * ===========================================================================
 */



import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.copyissuance.constant.CommonConstant;
import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;
import com.wipro.igrs.copyissuance.form.CertifiedActionForm;


public class CopyIssuanceRule
{

    private static Logger log = org.apache.log4j.Logger.getLogger(CopyIssuanceRule.class);

    private boolean error;
    PropertiesFileReader pr;

    public CopyIssuanceRule() {

    }

    protected boolean nullOrBlank(String str) {
        return((str == null) || (str.length() == 0));
    }


    /**
     * @param objForm
     * @return ArrayList
     */
    public ArrayList validateCopyIssuanceRule(Object objForm) {
        boolean flag = false;
        log.info("inside CopyIssuanceRule");
        CertifiedActionForm form = (CertifiedActionForm)objForm;
        CertifiedCopyDetailsDTO dto = form.getIssuanceDTO();

        log.info("*******" + dto.getAge());
        ArrayList errorList = new ArrayList();
        try
        {
            pr = PropertiesFileReader.getInstance("resources.igrs");
            errorList.add(pr.getValue("error.header"));
            if (CommonConstant.CERTIFIED_ACTION_FORM.equalsIgnoreCase(dto.getCopyIssuanceForm()))
            {
                if (CommonConstant.COPY_ISSUANCE_INSERT_ACTION.equalsIgnoreCase(dto.getCopyIssuanceInsertAction()))
                {


                    if (dto.getFirstName() == null)
                    {
                        flag = true;
                        errorList.add(pr.getValue("error.copyissuance.firstname"));
                    }

                    if (dto.getAge() == "0")
                    {
                        flag = true;
                        errorList.add(pr.getValue("error.copyissuance.testageequalzero"));
                    }
                    setError(flag);
                }
            }
        } catch (Exception x)
        {
            log.info("validateCopyIssuanceRule :-" + x);
        }
        return errorList;
    }



    /**
     * @param objForm
     * @return ArrayList
     */
    public ArrayList validateIssuanceRecord(Object objForm) {
        boolean flag = false;
        log.info("inside validateCopyIssuanceRule");
        ArrayList list = (ArrayList)objForm;
        ArrayList errorList = new ArrayList();
        try
        {
            pr = PropertiesFileReader.getInstance("resources.igrs");
            errorList.add(pr.getValue("error.header"));
            if (list == null)
            {
                flag = true;
                errorList.add(pr.getValue("error.copyissuance.recordnofound"));
            }
            setError(flag);
        } catch (Exception x)
        {
            log.info("validateCopyIssuanceRule :-" + x);
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
