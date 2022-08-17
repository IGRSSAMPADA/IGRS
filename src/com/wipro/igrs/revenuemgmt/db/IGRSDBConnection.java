package com.wipro.igrs.revenuemgmt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class IGRSDBConnection {
    public IGRSDBConnection() {
    }

    public Connection getDBConnection() throws Exception {
        String userName = "igrsadmin";
        String password = "igrsadmin";
        String url = "jdbc:oracle:thin:@10.100.34.196:1521:orcl";
        Connection conn = null;

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("established the connection== " + conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(Connection conn, Statement stmt, 
                                ResultSet rs) {
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
