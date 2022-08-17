
/**
 * ===========================================================================
 * File           :   WillDepositRule.java
 * Description    :   Represe`nts the Action Class

 * Author         :   Madan  Mohan
 * Created Date   :   Nov 28, 2007

 * ===========================================================================
 */

package com.wipro.igrs.willdeposit.rule;


import java.util.ArrayList;

import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dao.WillDepositDAO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.form.WillDepositForm;


public class WillDepositRule {

    private boolean error;
    PropertiesFileReader pr;

    public WillDepositRule() {

    }
    protected boolean nullOrBlank(String str) {
        return ((str == null) || (str.length() == 0));
    }
    public ArrayList validateWillDepositRule(Object objForm) {
        boolean flag = false;
        System.out.println("inside WillDepositRule");
        WillDepositForm form = (WillDepositForm)objForm;
        WillDepositDTO dto = form.getWillDTO();

        System.out.println("*******" + dto.getAge());
        ArrayList errorList = new ArrayList();
        try {
            pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
            errorList.add(pr.getValue("error.header"));
            if (CommonConstant.WILL_DEPOSIT_FORM.equalsIgnoreCase(dto
	            .getWillDepositForm())) {
                if (CommonConstant.WILL_DEPOSIT_INSERT_ACTION.equalsIgnoreCase
				(dto.getWillDepositInsertAction())) {


                    if (dto.getFirstName() == null) {
                        flag = true;
                        errorList.add(pr.getValue("error.willdeposit"
	                        + ".firstname"));
                    }

                    /*if (dto.getMidName() == null) {
                        flag=true;
                        map.put("willDTO.midName", "error.willdeposit"
	                        + ".middlename");
                    }
                    if (dto.getLastName() == null) {
                        flag=true;
                        map.put("willDTO.lastName", "error.willdeposit"
	                        + ".lastname");
                    }
                    if (dto.getGender() == null) {
                        flag=true;
                        map.put("willDTO.gender", "error.willdeposit.gender");
                    }*/

                    if (dto.getAge() == "0") {
                        //errors.add("willDTO.age", new ActionError("error
                        //.willdeposit.testageequalzero"));
                        flag = true;
                        errorList.add(pr.getValue("error.willdeposit"
	                        + ".testageequalzero"));

                        //map.put("willDTO.age", "error.willdeposit
                        //.testageequalzero");
                    }
                    /*
                    if (dto.getFatherName() == null) {
                        map.put("willDTO.fatherName", "error.willdeposit"
	                        + ".fatherName");
                    }

                    if (dto.getMotherName() == null) {
                        map.put("willDTO.motherName", "error.willdeposit"
	                        + ".motherName");
                    }

                    if (dto.getAddress() == null) {
                        map.put("willDTO.address", "error.willdeposit.address");
                    }

                    if (dto.getCity() == null) {
                        map.put("willDTO.city", "error.willdeposit.city");
                    }

                    if (dto.getEmail() == null) {
                        map.put("willDTO.email", "error.willdeposit.email");
                    }*/
                    setError(flag);
                }
            }
        } catch (Exception x) {
            System.out.println("validateWillDepositRule :-" + x);
        }
        return errorList;
    }
    public ArrayList validateWillWithDrawal(WillDepositDTO objForm) {
        String param[] = new String[2];
        ArrayList errorList = new ArrayList();
        boolean flag = false;

        try {
            pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
            if (objForm != null) {
                param[0] = objForm.getWillId();
                param[1] = CommonConstant.WILL_WITHDRAWN_FLAG;
                WillDepositDAO dao = new WillDepositDAO();
                ArrayList lst = dao.getWillDeposit(param);
                errorList.add(pr.getValue("error.header"));
                if (lst != null) {
                    if (lst.size() > 0) {
                        errorList.add(pr.getValue("error.willdeposit"
	                        + ".withdrawalheader") 
                                      + pr.getValue("error.willdeposit"
	                                      + ".withdrawalfooter"));
                        flag = true;
                    }
                }

            }
        } catch (Exception x) {
            System.out.println(x);
        }
        setError(flag);
        return errorList;
    }
    public ArrayList validateWillRecord(Object objForm) {
        boolean flag = false;
        System.out.println("inside validateWillReterieveRule");


        ArrayList list = (ArrayList)objForm;
        ArrayList errorList = new ArrayList();
        try {
            pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
            errorList.add(pr.getValue("error.header"));
            if (list == null) {
                flag = true;
                errorList.add(pr.getValue("error.willdeposit.recordnofound"));
            }
            setError(flag);


        } catch (Exception x) {
            System.out.println("validateWillDepositRule :-" + x);
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
