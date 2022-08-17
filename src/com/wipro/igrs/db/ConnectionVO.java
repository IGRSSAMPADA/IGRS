package com.wipro.igrs.db;

import java.sql.Connection;

public class ConnectionVO {

	/**
	 *  Holds connection object.
	 */
	private Connection connection;
	
	/**
	 *  TimeStamp when connection object was created.
	 */
	private long timeStamp;
	
	public ConnectionVO(Connection connection){
		setConnection(connection);
	}

	/**
	 * @return the connection.
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set.
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
		timeStamp = System.currentTimeMillis();
	}

	/**
	 * @return the timeStamp.
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	@Override
	public boolean equals(Object obj) {
		ConnectionVO connVO = null;
		
		if(obj instanceof ConnectionVO){
			connVO = (ConnectionVO)obj;
			return connection.equals(connVO.getConnection());
		}

		return false;
	}
	
	
}
