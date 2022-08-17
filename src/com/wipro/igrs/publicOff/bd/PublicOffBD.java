package com.wipro.igrs.publicOff.bd;
/**
 * ===========================================================================
 * File           :   PublicOffBD.java
 * Description    :   Represents the Business Delegate Class

 * Author         :   pavani Param
 * Created Date   :   Aug 23, 2008

 * ===========================================================================
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.publicOff.bo.PublicOffBO;
import com.wipro.igrs.publicOff.dto.PublicOfficerDTO;
import com.wipro.igrs.publicOff.form.PublicOffForm;
/**
 * 
 * @author pavpapa
 *
 */
public class PublicOffBD {

boolean boo;
ArrayList list = null;
private Logger logger = 
	(Logger) Logger.getLogger(PublicOffBD.class);	
	
  public ArrayList getDeptList() throws Exception {
        PublicOffBO bo  =  new PublicOffBO();
        ArrayList ret  =  bo.getDeptList();
        
        list  =  new ArrayList();

        if (ret !=  null) {
            for (int i  =  0; i < ret.size(); i++) {
                ArrayList lst  =  (ArrayList)ret.get(i);
                PublicOfficerDTO  dto = new PublicOfficerDTO();
                dto.setValue((String)lst.get(0));
                dto.setName((String)lst.get(1));
                list.add(dto);
            }

        }
        logger.info("in estamping bd getDeptList() ret "+list);
        return list;
    }

public  PublicOffForm getUserDet(PublicOffForm form) throws Exception
{
	list = new ArrayList();
    ArrayList retList1 = new ArrayList();
	PublicOffBO bo = new PublicOffBO();
	 try
	 {
	//--public officer  Details.
	 list = bo.getUserDet(form.getRegId());
	 retList1  =  (ArrayList)list.get(0);
	 form.setFirstName((String)retList1.get(0));
	 form.setMiddleName((String)retList1.get(1));
	 form.setLastName((String)retList1.get(2));
	 form.setGender((String)retList1.get(3));
	 form.setFatherName((String)retList1.get(4));
	 form.setMotherName((String)retList1.get(5));
	 form.setAddress((String)retList1.get(6));
	 form.setCountry((String)retList1.get(7));
	 form.setState((String)retList1.get(8));
	 form.setDistrict((String)retList1.get(9));
	 form.setPostalCode((String)retList1.get(10));
	 form.setPhoneNumber((String)retList1.get(11));
	 form.setMobileNumber((String)retList1.get(12));
	 form.setEmailID((String)retList1.get(13));
	 form.setPhotoId((String)retList1.get(14)); 
	 form.setIdNo((String)retList1.get(15));
	 form.setBankName((String)retList1.get(16)); 
	 form.setBankAddr((String)retList1.get(17));
	 
	 //--public officer other Details.
	 list = new ArrayList();
	 retList1 = new ArrayList();
	 list = bo.getpublicOffDet(form.getRegId());
	 retList1  =  (ArrayList)list.get(0);
	 form.setDesignation((String)retList1.get(0));
	 form.setDept((String)retList1.get(1));
	 form.setSuFName((String)retList1.get(2));
	 form.setSuMName((String)retList1.get(3));
	 form.setSuLName((String)retList1.get(4));
	 form.setAccFrom((String)retList1.get(5));
	 form.setAccTo((String)retList1.get(6));
	 form.setActStatus((String)retList1.get(7));
	 form.setDrRemarks((String)retList1.get(8));
	 
	 /*Date dd = new Date();
	 SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy");
     String s = ss.format(dd);
     int b = s.compareTo(form.getAccTo());
     if(b<0)
     {
    	 form.setStatMsg("expired");
     }*/
	 logger.info("form.setStatMsg====" + form.getStatMsg());
	 logger.info("Wipro in Public Off BD - getUserDet() AFTER EXCUTIN QUERY"+list);
	 }
	  catch(Exception e)
	  {
		  logger.debug("this is Exception in getAllList in BD" + e);
	     }

    return form;
}





public boolean savePoDet(PublicOffForm pubForm)
{
	PublicOffBO bo = new PublicOffBO();
    String poDet[] = new String[11];
    poDet[0]  = pubForm.getRegId();
  	poDet[1]  = pubForm.getDept();
	poDet[2]  = pubForm.getDesignation();
  	poDet[3]  = pubForm.getSuFName();
  	poDet[4]  = pubForm.getSuMName();
  	poDet[5]  = pubForm.getSuLName();
  	poDet[6]  = pubForm.getDrRemarks();
  	poDet[7]  = pubForm.getDrStatus();
  	poDet[8]  = pubForm.getActionValue();
  	poDet[9]  = getOracleDate(pubForm.getAccFrom());
  	poDet[10] = getOracleDate(pubForm.getAccTo());
  	//poDet[11] = pubForm.getSuLName();
	
		  boo = bo.poDetInsert(poDet);
		  logger.info("PublicOffBD-- before insert savePoDet Details ");
	
	return boo;
}
public static String getOracleDate(String DateFormat) {
	String finalDate  =  "";
		if(DateFormat!= null || !DateFormat.equalsIgnoreCase("") )
		{
    	StringTokenizer st = new StringTokenizer(DateFormat,"/");
        String day = st.nextToken();
        String month = st.nextToken();
        String year = st.nextToken();
		String inputDate  =  day + "-" + month + "-" + year;
		SimpleDateFormat formatter  =  new SimpleDateFormat("dd-MM-yyyy");
		System.out.print("  " + inputDate + " parses as ");
		
		Date newDate ;
		try {
			newDate  =  formatter.parse(inputDate);
			SimpleDateFormat format  =  new SimpleDateFormat("dd/MMM/yyyy");
			finalDate  =  format.format(newDate);

			System.out.print(finalDate);
		}
		catch (Exception e) {
			System.out.print(e);
		}
		
		}
		return  finalDate;
	}

public boolean updateActDet(String actStatus, String drRemarks, String userId) {
PublicOffBO bo = new PublicOffBO();
	
    String actDet[] = new String[3];
    actDet[0]  = actStatus;
    actDet[1]  = drRemarks;
    actDet[2]  = userId;
	logger.info("PublicOffBD-- updateActDet ");
	boo = bo.updateActDet(actDet);
	return boo;
}

/*public static void main(String[] args)
{
	Date dd = new Date();
	 SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy");
    String s = ss.format(dd);
    logger.info("date"+"\t"+s);
    int b = s.compareTo("26/08/2008");
    logger.info("comparison valu3e"+"\t"+b);
}*/
}
