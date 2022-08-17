package com.wipro.igrs.device.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.device.applet.ETokenDTO;
import com.wipro.igrs.eToken.bo.ETokenBO;
import com.wipro.igrs.eToken.dao.ETokenDAO;
import com.wipro.igrs.suppleDoc.action.SuppleDocAction;

/**
 * Servlet implementation class ETokenServlet
 */
public class ETokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ETokenDAO dao =null;
	private Logger logger = 
		(Logger) Logger.getLogger(ETokenServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ETokenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		HashMap<String, Object> payload;
		ObjectInputStream ois;
		Object content;
		ois = new ObjectInputStream(request.getInputStream());
		
		String regId="";
		String officeId="";
		String ip = "";
		String counterType="";
		content = ois.readObject();
		
		ois.close();
		
		if ((content instanceof HashMap)) {
			// basicPath = props.getProperty(UPLOAD_BASE);
			payload = (HashMap<String, Object>) content;
			// printMap(payload);
			if (payload != null) {
				
				ObjectOutputStream outputToApplet;
				HttpSession session = request.getSession();
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				String userIpAddress = httpRequest.getHeader("X-Forwarded-For");
				String work = (String) payload.get("work");
				outputToApplet = new ObjectOutputStream(response.getOutputStream());
				 ETokenBO bo = new ETokenBO();
				 
				 if(work.equalsIgnoreCase("initial"))
				 {
					 logger.info("Entering Getting initial DATA Etoken Servlet ");
					 String officeIds= (String) payload.get("ofc");
					 ip = (String) payload.get("ip");
						session.setAttribute(officeIds, ip);
						
						bo.setOfficeIp(officeIds,ip);
						
						System.out.println(session.getAttribute(officeIds));
						Vector<ETokenDTO> makerCounters = bo.getCounterIDS(officeIds);
						Vector<ETokenDTO> checkerCounters = bo.getCounterIDSChecker(officeIds);
						HashMap<String, Vector<ETokenDTO>> counterLists = new HashMap<String, Vector<ETokenDTO>>();
						counterLists.put("Maker", makerCounters);
						counterLists.put("Checker", checkerCounters);
						outputToApplet.writeObject(counterLists);
						outputToApplet.flush();
						outputToApplet.close();
					 logger.info("Exiting Getting initial DATA Etoken Servlet");
					 
				 }
				 
				 if(work.equalsIgnoreCase("manualMovement"))
				 {
					 logger.info("Entering Manual Movement DATA Etoken Servlet ");
					 String status= (String) payload.get("status");
					 String regID = (String) payload.get("regID");
					 boolean boo = false;
					 ETokenDAO dao = new ETokenDAO();
					 if (status.equalsIgnoreCase("13")) {
						 boo = 	dao.moveToChecker(regID, status);
						} else if (status.equalsIgnoreCase("11")) {

							boo =	dao.setMakerHoldDeactive(regID, status);

						} else if (status.equalsIgnoreCase("14")
								|| status.equalsIgnoreCase("17")) {

							boo =	dao.setCheckerHoldDeactivate(regID, status);

						}
						outputToApplet.writeObject(new Boolean(boo));
						outputToApplet.flush();
						outputToApplet.close();
					 logger.info("Exiting Manual Movement DATA Etoken Servlet");
					 
				 }
				 
				 if(work.equalsIgnoreCase("insertMaker"))
				 {
					String counterMappingId="";
					 logger.info("Entering insertMaker DATA Etoken Servlet ");
					 String regID = (String) payload.get("regId");
					 String counterNo = (String) payload.get("counterNo");
					 String Status = (String) payload.get("status");
					 String tokenNo = (String) payload.get("etokenNo");
					 officeId=(String) payload.get("ofc");
					 ETokenDAO dao = new ETokenDAO();
					  counterMappingId=dao.getTokenMappingId(counterNo,officeId);
						 
					 
					 boolean boo = false;
					boo =  dao.updateTokenDetails(regID, counterMappingId,tokenNo,Status);
					//boo =  dao.updateTokenDetails(regID, counterNo);
					outputToApplet.writeObject(new Boolean(boo));
					outputToApplet.flush();
					outputToApplet.close();
					 logger.info("Exiting insertMaker DATA Etoken Servlet");
					 
				 }
				 
				 if(work.equalsIgnoreCase("checkExistsEtoken"))
				 {
						logger.info("Checking  if Etoken Exists DATA Etoken Servlet");
						regId = (String) payload.get("regId");
						counterType = (String) payload.get("applicationType");
						ETokenDTO boo  = bo.checkExistsAlreadyToken(regId, counterType);
						if(boo!=null)
						{
							  outputToApplet.writeObject(boo);
							  outputToApplet.flush();
							  outputToApplet.close();
						}
				 
				 
				 }
				 
				if(work.equalsIgnoreCase("getInitialDetails"))
				{
					 bo = new ETokenBO();
					
					
				}
				else if("getDetails".equalsIgnoreCase(work))
				{
					logger.info("Entering Getting Details  DATA Etoken Servlet");
					regId = (String) payload.get("regID");
					officeId = (String)payload.get("ofc");
					ETokenDTO dto =  bo.checkRegInitId(officeId, regId);
					  outputToApplet.writeObject(dto);
					  outputToApplet.flush();
					  outputToApplet.close();
					
					
					
					
				}
				else if("checkExists".equalsIgnoreCase(work))
				{
					logger.info("Checking  if Etoken Exists DATA Etoken Servlet");
					regId = (String) payload.get("regId");
					counterType = (String) payload.get("applicationType");
					boolean boo  = bo.checkAlreadyToken(regId, counterType);
					if(boo)
					outputToApplet.writeObject("true");
					else
						outputToApplet.writeObject("false");
					outputToApplet.flush();
					  outputToApplet.close();
				}
				else if("CollectorCounter".equalsIgnoreCase(work))
				{
					logger.info("Getting Etoken Maker Counter Details ");
					regId = (String) payload.get("regId");
					//counterType = (String) payload.get("applicationType");
					String counterNo  = bo.getMakerCollectionCounterDetails(regId);
					
					outputToApplet.writeObject(counterNo);
					
					outputToApplet.flush();
					  outputToApplet.close();
				}
				else if ("printEtoken".equalsIgnoreCase(work))
				{
					
					
					
					ETokenDTO dto = (ETokenDTO) payload.get("dto");
					dto.getCollectCounter();
					dto.getCounterName();
					dto.getCounterNo();
					ETokenDTO boo = bo.generateTokenS(dto.getRegistrationId(), "1", dto.getOfficeId(),dto.getNoOfPersons() ,(String)session.getAttribute("UserId"), dto.getTimeFrom()+" to "+dto.getTimeTo(),dto.getTypeOfPerson(),dto,dto.getAppStatus());
					outputToApplet.writeObject(boo);
					outputToApplet.flush();
					  outputToApplet.close();
					
					
				}
					
			
				officeId = (String) payload.get("ofc");
				ip = (String) payload.get("ip");
				
				
				
			System.out.println(ip);
				session.setAttribute("OFC02", ip);
			
				  Enumeration<String> al =  request.getHeaderNames();
				  
				  
				  
				 
				
				
			}
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	
	
}
