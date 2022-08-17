package com.wipro.igrs.regcompletion.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regcompletion.form.ApplicantForm;

import java.io.*;
import java.sql.PreparedStatement;

/**
* @author Imran Shaik
*/

/**
 * File Upload Action Class.
 *
*/
public class FileUploadAction extends BaseAction 
{
  public ActionForward execute(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response,HttpSession session) throws Exception{
	  ApplicantForm myForm = (ApplicantForm)form;

	  File myFile = new File(myForm.getUploadFile());
	  //Get the file name
        String fileName    = myFile.getName();
    //Get the servers upload directory real path name
        String filePath = myFile.getAbsolutePath();
    //Set file name to the request object
        boolean status = upLoadDocIns("2","2","1",myFile.getName(),myFile);
        if(status)
        	request.setAttribute("fileName",myFile.getName());
        else
        	request.setAttribute("fileName","fail");
        return mapping.findForward("success");
  }
  public boolean upLoadDocIns(String docTxnId,String docId,String docExtId,String fileName,File file) throws Exception
  {
  boolean boo=false;
  try
  {
	  DBUtility dbUtil = new DBUtility();
	  String SQL="INSERT INTO NEWGEN_REG_DOC_DETAILS D (D.DOC_TXN_ID,D.DOC_TYPE_ID,D.DOC_EXTN_ID,D.DOC_NAME,D.DOC_OBJ,D.DOC_STATUS) VALUES (?,?,?,?,?,'A')";

	  FileInputStream fis = new FileInputStream(file);
	  PreparedStatement pst = dbUtil.returnPreparedStatement(SQL);
	  pst.setString(1, docId);
	  pst.setString(2,docTxnId);
	  pst.setString(3, docExtId);
	  pst.setString(4, fileName);
	  pst.setBinaryStream(5,fis,(int)fis.available());
	  boo=pst.execute();
 

  if (!boo) 
	  dbUtil.commit();
  else
	  dbUtil.rollback();

  }catch (Exception x) {
	  x.printStackTrace();
  }
    return boo;
  }
}