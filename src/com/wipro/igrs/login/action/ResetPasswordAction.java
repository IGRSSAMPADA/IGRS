/*package com.gss.web.admin.action;

import com.gss.admin.bo.ResetPasswordBO;
import com.gss.admin.vo.UserPasswordDO;
import com.gss.common.constants.Constants;
import com.gss.common.exception.EMAARException;
import com.gss.web.admin.form.ResetPasswordForm;
import com.gss.web.common.action.BaseAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ResetPasswordAction extends BaseAction {
    public ResetPasswordAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response, 
                                 HttpSession session) {

        String forwardTo = "success";
        ResetPasswordForm resetPasswordForm;

        if (form == null) {
            resetPasswordForm = new ResetPasswordForm();
        } else {
            resetPasswordForm = (ResetPasswordForm)form;
        }

        ArrayList resetPasswordDOList = 
            resetPasswordForm.getResetPasswordDOList();
        ResetPasswordBO resetPasswordBO = new ResetPasswordBO();
        for (int i = 0; i < resetPasswordDOList.size(); i++) {

            UserPasswordDO resetPasswordDO = 
                (UserPasswordDO)resetPasswordDOList.get(i);
            String selectCheckBox = resetPasswordDO.getSelectedCheckBox();
            if (selectCheckBox != null && 
                selectCheckBox.equalsIgnoreCase(Constants.SELECTED_CHECK_BOX_VALUE)) {

                try {
                    resetPasswordBO.updateResetPassword(resetPasswordDO);
                } catch (EMAARException e) {
                    // TODO
                }
                System.out.println(resetPasswordDO.getSelectedCheckBox() + 
                                   ":" + resetPasswordDO.getUserId() + ":" + 
                                   resetPasswordDO.getPassword() + ":" + 
                                   resetPasswordDO.getConfirmPassword());
            }
        }
        return mapping.findForward(forwardTo);
    }
}
*/