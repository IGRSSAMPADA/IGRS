package com.wipro.igrs.regcompletion.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import com.wipro.igrs.regcompletion.action.RegCompMailAction;
import com.wipro.igrs.regcompletion.bo.RegCompMailBO;
import com.wipro.igrs.regcompletion.dto.RegCompMailDTO;
import com.wipro.igrs.regcompletion.form.RegCompMailForm;


public class RegCompMailBD
{
	
	private  Logger logger = 
		(Logger) Logger.getLogger(RegCompMailBD.class);
	public void getPartyTxnId(HttpServletRequest request) throws IOException
	{
		RegCompMailBO bo = null;
		ArrayList setList = new ArrayList();
		HttpSession session = null;
		try
		{
			bo = new RegCompMailBO();
			session = request.getSession();
			ArrayList fullList = bo.getPartyTxnId();
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					RegCompMailDTO vo = new RegCompMailDTO();
					ArrayList subList = (ArrayList)fullList.get(i);
					vo.setPartyTxnId((String)subList.get(0));
					setList.add(vo);
										
				}
			}
			RegCompMailDTO dto = new RegCompMailDTO();
				dto.setList1(setList);
				session.setAttribute("partyTxnIdKey",dto);
		}
		catch(Exception e)
		{
	       logger.error("this is Exception in  BD" + e);
	    }
				
	}
	
	 public boolean mailSendType(RegCompMailForm data) throws SQLException,Exception
		{
			boolean flag = false;
			RegCompMailBO bo = null;
			
			try
			{
				ArrayList setList = new ArrayList();
				HttpSession session = null;
			
					bo = new RegCompMailBO();
					String[] param1 = new String[2];
					String sendType = data.getMailSendType();
					String texId = data.getPartyTxnId();
					
					param1[0] = sendType;
					param1[1] = texId;
			
					flag = bo.mailSendType(param1);
		
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
		
			}
			return flag;
		}
	 
	 
	 
}
