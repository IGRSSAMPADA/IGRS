package com.wipro.igrs.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ===========================================================================
 * File : IGRSDBConnection.java Description : Represents the Business Class
 * 
 * Author : Hari Krishna GV Created Date : Nov 28, 2007
 * 
 * ===========================================================================
 */

public class IGRSDBConnection {
	public IGRSDBConnection() {
	}

	public static Connection getDBConnection() throws SQLException {
		String userName = "igrsadmin";
		String password = "igrsadmin";
		String url = "jdbc:oracle:thin:@10.100.34.196:1521:orcl";
		Connection conn = null;

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection(url, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}