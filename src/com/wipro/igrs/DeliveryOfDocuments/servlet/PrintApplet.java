package com.wipro.igrs.DeliveryOfDocuments.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wipro.igrs.DeliveryOfDocuments.dao.*;
import com.wipro.igrs.common.dao.CommonDAO;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;

/**
 * Servlet implementation class PrintApplet
 */
public class PrintApplet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintApplet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 PrintWriter out = response.getWriter();
	 String list="";
		out.println("DO post");
		String prop_data=request.getParameter("prop_data");
		String docid=request.getParameter("docid");
		request.setAttribute("prop_data", prop_data);
		request.setAttribute("docid", docid);
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		RegCompCheckerBD bd= new RegCompCheckerBD();
		try {
			 list = dao.getcheckFlag(prop_data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/IGRSPrintApplet.jsp??docid="+fileName+"&prop_data="+uniqueId);
		if(list.equalsIgnoreCase("P"))
		{
			boolean value=bd.updatePrintFlag(prop_data, "N");
			if(value){
				request.getRequestDispatcher("/IGRSPrintApplet.jsp").forward(request, response);
			}
			else{
				request.getRequestDispatcher("/jsp/DeliveryofDocuments/documentGenerationFail.jsp").forward(request, response);
			}
		}
		
		else if (list.equalsIgnoreCase("Y")) {
			request.getRequestDispatcher("/jsp/DeliveryofDocuments/FailurePrint.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/jsp/DeliveryofDocuments/documentGenerationFail.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
			out.println("DO post");
			String uniqueId=request.getParameter("prop_data");
			String fileName=request.getParameter("docid");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/IGRSPrintApplet.jsp??docid="+fileName+"&prop_data="+uniqueId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/areaManagement/FailureViewPDF.jsp");
			
					//+"?docid="+fileName+"&prop_data="+uniqueId);
		      dispatcher.forward(request, response);
	}

}
