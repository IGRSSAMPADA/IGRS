package com.wipro.igrs.newreginitefiling.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.device.applet.ETokenDTO;
import com.wipro.igrs.device.servlet.ETokenServlet;
import com.wipro.igrs.eToken.bo.ETokenBO;
import com.wipro.igrs.newreginitefiling.bo.RegCommonBO;

/**
 * Servlet implementation class GetMPCSTData
 */
public class GetMPCSTData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = 
		(Logger) Logger.getLogger(GetMPCSTData.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMPCSTData() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}
	
	public void service(HttpServletRequest request,
		       HttpServletResponse response) throws IOException
		{
		

			logger.debug("inside service method");

		try{
			
				
				//String encDataFromMpcst=request.getParameter("data");
			String encDataFromMpcst="YES";
				if(encDataFromMpcst!=null && !("").equalsIgnoreCase(encDataFromMpcst)){
					//logger.debug("VALUES received from MPCST.");
					//decrypt here
					/*String regTxnId="121114000010";
					String propertyId="43287201400003952";
					String availableFlag="Y";
					String hash1="AA2";
					String hash2="BB2";*/
					String regTxnId=request.getParameter("regTxnId");
					String propertyId=request.getParameter("propertyId");
					String availableFlag=request.getParameter("availableFlag");
					String hash1=request.getParameter("hash1");
					String hash2=request.getParameter("hash2");
					logger.debug("regTxnId from mpcst:"+regTxnId);
					logger.debug("propertyId from mpcst:"+propertyId);
					logger.debug("availableFlag from mpcst:"+availableFlag);
					logger.debug("hash1 from mpcst:"+hash1);
					logger.debug("hash2 from mpcst:"+hash2);
					
					//insert this data in database
					RegCommonBO bo=new RegCommonBO();
					
					if(regTxnId!=null && !("").equalsIgnoreCase(regTxnId) &&
							propertyId!=null && !("").equalsIgnoreCase(propertyId) &&
							availableFlag!=null && !("").equalsIgnoreCase(availableFlag)){
						
						
						if(("Y").equalsIgnoreCase(availableFlag) &&
								(   (hash1!=null && !("").equalsIgnoreCase(hash1))  &&  
										(hash2!=null && !("").equalsIgnoreCase(hash2)))){
							
							logger.debug("availableFlag received from MPCST is Y and hash also received");
							
							String[] param = { hash1,hash2,	"MPCST",availableFlag,propertyId,regTxnId};
							boolean update=bo.updateMPCSTdata(param);
							if(update){
								logger.debug("data updated from MPCST.Y");
							}else{
								logger.debug("UNABLE TO UPDATE data from MPCST.Y");
							}
							
						}else if(("N").equalsIgnoreCase(availableFlag)){
							
							logger.debug("availableFlag received from MPCST is N");
							String[] param = { null,null,	"MPCST",availableFlag,propertyId,regTxnId};
							boolean update=bo.updateMPCSTdata(param);
							if(update){
								logger.debug("data updated from MPCST.N");
							}else{
								logger.debug("UNABLE TO UPDATE data from MPCST.N");
							}
							
						}else{
							
							logger.debug("availableFlag received from MPCST is Y but hash not received");
							logger.debug("hash1 received from MPCST:"+hash1+":");
							logger.debug("hash2 received from MPCST:"+hash2+":");
							
						}
						
					}else{
						
						logger.debug("some null value received from MPCST");
						logger.debug("regTxnId received from MPCST:"+regTxnId+":");
						logger.debug("propertyId received from MPCST:"+propertyId+":");
						logger.debug("availableFlag received from MPCST:"+availableFlag+":");
						
						
					}
					
				}else{
					logger.debug("null received from MPCST");
				}
		
		}
		catch (Exception e) {
			//e.printStackTrace();
			logger.debug(e.getStackTrace());
		}
		
	
	
	
	
		
		
		}

}
