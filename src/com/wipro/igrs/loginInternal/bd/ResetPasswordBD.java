package com.wipro.igrs.loginInternal.bd;
 
import org.apache.log4j.Logger;

import com.wipro.igrs.login.dao.LoginDAO;
 


public class ResetPasswordBD {
	private Logger logger = 
		(Logger) Logger.getLogger(ResetPasswordBD.class);
	public ResetPasswordBD() {
    }

    public void setUpdateLoginStatus(String userID) {
    	try {
    		LoginDAO dao = new LoginDAO();
    		String[] param = new String[2];
    		param[0] = "D";
    		param[1] = userID;
    		dao.updateLoginStatus(param);
    	}catch(Exception x) {
    		logger.debug(x);
    	}
    }
    /*

    public ArrayList getResetPasswordUserList(String searchUserId,
                                              UserDO userDO) {
        ArrayList resetPasswordDOList = new ArrayList();
        userDO.setUserId("Ravindra");
        UserPasswordDO resetPasswordDO = null;
        try {
            DBServices db = new DBServices();
            Connection con = db.getConnection("Test");

            CallableStatement cStmt =
                con.prepareCall("call xx_gbs_webportal_pkg.get_reset_pwd_search_dtls(?,?,?)");
            cStmt.setObject(1, searchUserId);
            cStmt.setObject(2, userDO.getUserId());
            cStmt.registerOutParameter(3, OracleTypes.CURSOR);
            cStmt.execute();
            ResultSet rs = (ResultSet)cStmt.getObject(3);
            while (rs.next())

            {
                resetPasswordDO = new UserPasswordDO();

                resetPasswordDO.setUserId(rs.getString(1));
                resetPasswordDO.setAgentLoginId(rs.getString(2));
                resetPasswordDO.setResourceName(rs.getString(3));
                resetPasswordDOList.add(resetPasswordDO);
            }
            System.out.println("resetPasswordDOList:" +
                               resetPasswordDOList.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return resetPasswordDOList;

    }

    public void changePassword(UserPasswordDO userPasswordDO,
                               UserDO userDO) throws EMAARException {

        String oldPassword = userPasswordDO.getOldPassword();
        String orgPassword = userDO.getPassword();
        if (!oldPassword.equalsIgnoreCase(orgPassword)) {
            throw new EMAARException("BE_OLD_PWD_WRONG");
        }
        String isPasswordRepeated = isPasswordRepeated(userPasswordDO);
        if (isPasswordRepeated.equalsIgnoreCase(Constants.YES_VALUE)) {

            throw new EMAARException("BE_PWD_ALREADY_EXISTS");
        }

        updateResetPassword(userPasswordDO);
    }


    public String isPasswordRepeated(UserPasswordDO userPasswordDO) throws EMAARException {

        String isPasswordRepeated = null;
        DBServices db = new DBServices();
        Connection con;


        try {
            con = db.getConnection("isPasswordRepeated");
            CallableStatement ocs =
                (CallableStatement)con.prepareCall("{call xx_gbs_webportal_pkg.check_password_repeated(?,?,?)}");

            ocs.setObject(1, userPasswordDO.getUserId());
            ocs.setObject(2, userPasswordDO.getPassword());
            ocs.registerOutParameter(3, OracleTypes.VARCHAR);
            ocs.execute();
            isPasswordRepeated = (String)ocs.getObject(3);
            System.out.println("isPasswordRepeated:" + isPasswordRepeated);
        } catch (DBException dbe) {
            dbe.printStackTrace();
            throw new EMAARException(dbe.getErrorCode());
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            throw new EMAARException("" + sqe.getErrorCode());
        } finally {

        }
        return isPasswordRepeated;
    }

    public void updateResetPassword(UserPasswordDO userPasswordDO) throws EMAARException {
        DBServices db = new DBServices();
        Connection con;
        WebDbStructs webDbStructs = new WebDbStructs();

        try {
            con = db.getConnection("updateResetPassword");

            StructDescriptor user_pwd_rec_type =
                StructDescriptor.createDescriptor("USER_PWD_REC_TYPE", con);
            userPasswordDO.setPasswordChangeReason("RESET PWD");
            userPasswordDO.setUpdatedBy("Narendra");


            STRUCT user_pwd_rec_type_struct =
                new STRUCT(user_pwd_rec_type, con,
                           webDbStructs.createWebUserPwdRecordType(userPasswordDO));

            CallableStatement ocs =
                (CallableStatement)con.prepareCall("{call xx_gbs_webportal_pkg.update_user_pwd(?,?,?)}");


            ocs.setObject(1, user_pwd_rec_type_struct);
            ocs.registerOutParameter(2, OracleTypes.VARCHAR);
            ocs.registerOutParameter(3, OracleTypes.VARCHAR);
            ocs.execute();

        } catch (DBException dbe) {
            dbe.printStackTrace();
            throw new EMAARException(dbe.getErrorCode());
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            throw new EMAARException("" + sqe.getErrorCode());
        } finally {

        }
    }*/
}
