/*package com.gss.web.admin.action;

import com.gss.admin.bo.ResetPasswordBO;
import com.gss.admin.vo.UserDO;
import com.gss.common.constants.Constants;
import com.gss.web.admin.form.ResetPasswordForm;
import com.gss.web.common.action.BaseAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ResetPasswordSearchAction extends BaseAction {
    public ResetPasswordSearchAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response, 
                                 HttpSession session) {

        String forwardTo = "resetPassword";
        ResetPasswordForm resetPasswordForm;

        if (form == null) {
            resetPasswordForm = new ResetPasswordForm();
        } else {
            resetPasswordForm = (ResetPasswordForm)form;
        }
        String actionType = resetPasswordForm.getActionType();

        if (actionType != null && 
            actionType.equalsIgnoreCase(Constants.RESET_PASSWORD_SEARCH)) {
            ResetPasswordBO resetPasswordBO = new ResetPasswordBO();
            ArrayList resetPasswordUserList = 
                resetPasswordBO.getResetPasswordUserList(resetPasswordForm.getLoginId(), 
                                                         new UserDO());
            resetPasswordForm.setResetPasswordDOList(resetPasswordUserList);
            resetPasswordForm.setResetPasswordDOListCnt(resetPasswordUserList.size());
        }

        return mapping.findForward(forwardTo);
    }
}
*/