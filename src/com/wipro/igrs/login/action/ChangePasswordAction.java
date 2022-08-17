/*package com.gss.web.admin.action;

import com.gss.admin.bo.ResetPasswordBO;
import com.gss.admin.vo.UserDO;
import com.gss.admin.vo.UserPasswordDO;
import com.gss.common.constants.Constants;
import com.gss.common.constants.WebConstants;
import com.gss.common.exception.EMAARException;
import com.gss.web.admin.form.ChangePasswordForm;
import com.gss.web.admin.form.ResetPasswordForm;
import com.gss.web.common.action.BaseAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;


public class ChangePasswordAction extends BaseAction {
    public ChangePasswordAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response, 
                                 HttpSession session) {
        String forwardTo = "changePassword";
        System.out.println("ChangePasswordForm");
        ChangePasswordForm changePasswordForm;
        ActionMessages messages = new ActionMessages();
        if (form == null) {
            changePasswordForm = new ChangePasswordForm();
        } else {
            changePasswordForm = (ChangePasswordForm)form;
        }
        String actionType = changePasswordForm.getActionType();
        UserPasswordDO userPasswordDO = changePasswordForm.getUserPasswordDO();
        UserDO userDO = (UserDO)session.getAttribute(WebConstants.SES_USER_DO);
        userDO = new UserDO();
        if (actionType != null && actionType.equalsIgnoreCase("CHANGE_PWD")) {
            ResetPasswordBO resetPasswordBO = new ResetPasswordBO();
            try {
                resetPasswordBO.changePassword(userPasswordDO, userDO);
                forwardTo = "success";
            } catch (EMAARException e) {
                if (e.getErrorCode().startsWith("BE")) {
                    ActionMessage message = 
                        new ActionMessage(e.getErrorCode());
                    messages.add(ActionMessages.GLOBAL_MESSAGE, message);
                    saveMessages(request, messages);
                    forwardTo = "changePassword";
                }
            }
        } else {
            userPasswordDO.setUserId(userDO.getUserId());
        }

        return mapping.findForward(forwardTo);
    }
}
*/