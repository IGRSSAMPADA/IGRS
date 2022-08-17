package com.wipro.igrs.regCompChecker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;


/**
 * Servlet implementation class PrintStatus
 */
public class PrintStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = (Logger) Logger.getLogger(PrintStatus.class);  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintStatus() {
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
	}
	
	public void service(HttpServletRequest request,
			       HttpServletResponse response) throws IOException
			{
				String compNumber=request.getParameter("igrsId");
				String status=request.getParameter("pstatus");
				logger.debug("Print Status of "+compNumber+" is"+status);
				RegCompCheckerBD bd= new RegCompCheckerBD();
				bd.updateSuccesPrint(compNumber, status);
				
			}
	

}
