package com.wipro.igrs.slotbooking.dao;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.slotbooking.form.SRSlotReportForm;
import com.wipro.igrs.slotbooking.sql.CommonSQL;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class SlotReportDAO
{

    public SlotReportDAO()
    {
    }

    public ArrayList getSRSlotReport(SRSlotReportForm sbform, String officeId)
    {
        ArrayList list = new ArrayList();
        try
        {
            dbutility = new DBUtility();
            String frmDt = sbform.getFromDate();
            String toDt = sbform.getToDate();
            String ofcId = officeId;
            dbutility.createPreparedStatement(CommonSQL.SLOT_REPORT);
            //dbutility.createPreparedStatement(CommonSQL.SLOT_REPORT_DETLS);
            list = dbutility.executeQuery(new String[] {
                ofcId, frmDt, toDt
            });
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
        	
        	try
            {
                dbutility.closeConnection();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
       /* try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }*/
        return list;
    }

    public ArrayList getPartyDetails(String regTxnId)
    {
        ArrayList list = new ArrayList();
        try
        {
            dbutility = new DBUtility();
            String regtxn = regTxnId;
            dbutility.createPreparedStatement(CommonSQL.PARTY_DETAILS);
            list = dbutility.executeQuery(new String[] {
                regtxn
            });
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList getDuties(String dutyID)
    {
        ArrayList list = new ArrayList();
        try
        {
            dbutility = new DBUtility();
            String dtyid = dutyID;
            dbutility.createPreparedStatement(CommonSQL.DUTIES_DETAILS);
            list = dbutility.executeQuery(new String[] {
                dtyid
            });
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList getpartyNamedetails(String partyID)
    {
        ArrayList list = new ArrayList();
        try
        {
            dbutility = new DBUtility();
            String prtyid = partyID;
            dbutility.createPreparedStatement(CommonSQL.PARTY_NAME_DETAILS);
            list = dbutility.executeQuery(new String[] {
                prtyid
            });
        }
            
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList getconsentorDetails(String regTxnId)
    {
        ArrayList list = new ArrayList();
        try
        {
            dbutility = new DBUtility();
            String reginit = regTxnId;
            dbutility.createPreparedStatement("SELECT a.CONSENTER_TXN_NUM, a.FATHER_NAME, a.CONSENTER_FIRST_NAME, a.REG_TXN_ID, a.WITH_CONSIDERATION, NVL(TO_CHAR(b.REG_FEE),'--'), NVL(TO_CHAR(b.TOTAL),'--'), NVL(TO_CHAR(b.REG_FEE_AFTER_EXEMP),'--'), NVL(TO_CHAR(b.TOTAL_AFTER_EXEMP),'--') FROM IGRS_REG_CONSENTER_DETAILS a, IGRS_CONSENTER_DUTY_DETAILS b WHERE a.REG_TXN_ID=? AND a.REG_TXN_ID  =b.reg_txn_id(+)");
            list = dbutility.executeQuery(new String[] {
                reginit
            });
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
        try
        {
            dbutility.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    DBUtility dbutility;
    
   
}