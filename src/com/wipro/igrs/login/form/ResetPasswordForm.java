/*package com.wipro.igrs.login.form;


import com.wipro.igrs.login.dto.UserPasswordDTO;

import java.util.ArrayList;


public class ResetPasswordForm extends BaseForm {
    public ResetPasswordForm() {
    }
    private String loginId;
    private ArrayList resetPasswordDOList = new ArrayList();
    private int resetPasswordDOListCnt;

    public UserPasswordDTO getResetPasswordDO(int index) {
        int i = resetPasswordDOList.size();
        if ((index + 1) > i) {
            for (int j = i; j < (index + 1); j++) {
                resetPasswordDOList.add(j, new UserPasswordDTO());
            }
        }
        return (UserPasswordDTO)resetPasswordDOList.get(index);
    }

    public void setResetPasswordDO(int index, 
                                   UserPasswordDTO resetPasswordDO) {
        if (index < resetPasswordDOList.size()) {
            resetPasswordDOList.set(index, resetPasswordDO);
        } else {
            resetPasswordDOList.add(index, resetPasswordDO);
        }
    }


    public void setResetPasswordDOList(ArrayList resetPasswordDOList) {
        this.resetPasswordDOList = resetPasswordDOList;
    }

    public ArrayList getResetPasswordDOList() {
        return resetPasswordDOList;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setResetPasswordDOListCnt(int resetPasswordDOListCnt) {
        this.resetPasswordDOListCnt = resetPasswordDOListCnt;
    }

    public int getResetPasswordDOListCnt() {
        return resetPasswordDOListCnt;
    }
}
*/