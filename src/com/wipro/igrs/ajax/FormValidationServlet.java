package com.wipro.igrs.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;

public class FormValidationServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            //Obtain value of Catalog Id field to ve validated.
            String catalogId = request.getParameter("catalogId");
      
                System.out.println("@@@@@@@@@@@@@@@catalogID@@@@@@@@@@@@@    "+ catalogId);
            
            

             OracleDataSource ds = new OracleDataSource();
             ds.setDriverType("thin");
                        ds.setServerName("10.100.34.196");
                        ds.setDatabaseName("orcl");
                        ds.setUser( "igrsadmin");
                        ds.setPassword("igrsadmin");    
            ds.setPortNumber(1521); 
            java.sql.Connection conn = ds.getConnection();
            //Obtain result set
            Statement stmt = conn.createStatement();
            System.out.println("******** "+catalogId);
            String query = "SELECT * from  Catalog WHERE catalogId='"+catalogId+"'";
            ResultSet rs = stmt.executeQuery(query);

            // set headers before accessing the Writer
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");

            PrintWriter out = response.getWriter();

            // then write the response
            //If result set is empty set valid element to true
            if (rs.next()) {
                    out.println("<catalog>" + "<valid>true</valid>" + "<journal>" +
                    rs.getString(2) + "</journal>" + "<publisher>" +
                    rs.getString(3) + "</publisher>" + "<edition>" +
                    rs.getString(4) + "</edition>" + "<title>" +
                    rs.getString(5) + "</title>" + "<author>" +
                    rs.getString(6) + "</author>" + "</catalog>");
            } else {
                out.println("<valid>false</valid>");
            }

            rs.close();
           stmt.close();
           conn.close();

        }   catch (SQLException e) {
        }
    }

    
    }

