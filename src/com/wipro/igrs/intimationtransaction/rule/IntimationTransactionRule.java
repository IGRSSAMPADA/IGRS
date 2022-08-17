
/**
 * ===========================================================================
 * File           :   IntimationTransactionRule.java
 * Description    :   Represe`nts the Action Class

 * Author         :   Nithya
 * Created Date   :   Feb 10, 2008

 * ===========================================================================
 */

package com.wipro.igrs.intimationtransaction.rule;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.intimationtransaction.constant.CommonConstant;
import com.wipro.igrs.intimationtransaction.dto.IntimationTxnDetailsDTO;
import com.wipro.igrs.intimationtransaction.form.IntimationTxnActionForm;
import com.wipro.igrs.intimationtransaction.util.PropertiesFileReader;

public class IntimationTransactionRule {

    private boolean error;
    private static Logger log = org.apache.log4j.Logger.getLogger(IntimationTransactionRule.class);
    PropertiesFileReader pr;

    //default constructor
    public IntimationTransactionRule() {

    }

    /**
     * @since      :  10-02-2008
     * Method      :  nullOrBlank
     * Description :  This method checks the String whether it is null or Blank 
     * @param str  :  String
     * @return     :  boolean
     */
    protected boolean nullOrBlank(String str) {
        return((str == null) || (str.length() == 0));
    }

    /**
     * @since            :  10-02-2008
     * Method            :  validateIntimationTransactionRule
     * Description       :  This method validates Intimation Transactio Form
     * @param objForm    :  Object
     * @return errorList :  ArrayList
     * @throws           :  Exception
     */
    public ArrayList validateIntimationTransactionRule(Object objForm) {
        boolean flag = false;
        log.info("inside IntimationTransactionRule");
        IntimationTxnActionForm form = (IntimationTxnActionForm)objForm;
        IntimationTxnDetailsDTO dto = form.getIntimationDTO();

        log.info("*******" + dto.getAge());
        ArrayList errorList = new ArrayList();
        try {
            try {
				//pr = PropertiesFileReader.getInstance("resources.igrs");
            	pr = PropertiesFileReader.getInstance("resources.igrs");
			} catch (Exception e) {
				e.printStackTrace();
			}
			errorList.add(pr.getValue("error.header"));
            if (CommonConstant.INTIMATION_TXN_ACTION_FORM.equalsIgnoreCase(dto.getIntimationTxnActionForm())) {
                if (CommonConstant.INTIMATION_TXN_SUBMIT_ACTION.equalsIgnoreCase(dto.getIntimationTxnSubmitAction())) {

                    if (dto.getFirstName() == null) {
                        flag = true;
                        errorList.add(pr.getValue("error.intimationtransaction.firstname"));
                    }
                    if (dto.getAge() == "0") {
                        flag = true;
                        //map.put("willDTO.age", "error.willdeposit.testageequalzero");
                    }

                    setError(flag);
                }
            }
        } catch (Exception x) {
            log.info("validateIntimationTransactionRule :-" + x);
        }
        return errorList;
    }

    /**
     * @since       :  10-02-2008
     * Method       :  setError
     * Description  :  This method sets boolean Error
     * @param error :  boolean
     * @return      :  void   
    */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * @since         :  10-02-2008
     * Method         :  isError
     * Description    :  This method returns value as boolean
     * @return error  :  boolean   
    */
    public boolean isError() {
        return error;
    }
}
