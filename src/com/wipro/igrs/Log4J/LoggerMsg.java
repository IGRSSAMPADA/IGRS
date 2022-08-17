
/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of
 * IGRS, Govt. Of  MadhyaPradesh
 *
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 *==============================================================================
 *
 * File Name   :   LoggerMsg.java
 * Author      :   Nihar R Mishra 
 * Description :   This file contains the code for LoggerMsg.java
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  22nd Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.Log4J;

import java.util.Properties;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author nihraa
 *
 */
public class LoggerMsg {

	private static LoggerMsg loggerMsg = null;
	private Logger debugLogger;
	private Logger infoLogger;
	private Logger errorLogger;
	private LoggerMsg() {
		try {
			System.out.println("Initializing Logger ....... ");
			Configuration config = Configuration.getInstance();

			debugLogger = Logger.getLogger("DebugLogger");
			infoLogger = Logger.getLogger("InfoLogger");
			errorLogger = Logger.getLogger("ErrorLogger");
			System.out.println("debugLogger "+debugLogger);
			System.out.println("infoLogger "+infoLogger);
			System.out.println("errorLogger "+errorLogger);
			System.out.println("Logger instances created ");

			Properties properties = new Properties();
			properties.put("log4j.rootLogger", "OFF, A1");
			properties.put("log4j.appender.A1", "org.apache.log4j.ConsoleAppender");
			properties.put("log4j.appender.A1.layout", "org.apache.log4j.PatternLayout");
			properties.put("log4j.appender.A1.layout.conversionPattern", "%d{yyyyMMdd-HH:mm:ss} %m%n");
			PropertyConfigurator.configure(properties);

			PatternLayout layout = new PatternLayout("%d{yyyyMMdd-HH:mm:ss} %m%n");
			debugLogger.addAppender(new DailyRollingFileAppender(layout,config.getConfig("DebugLogFile"),"'.'dd-MM-yyyy") );
			infoLogger.addAppender(new DailyRollingFileAppender(layout,config.getConfig("InfoLogFile"),"'.'dd-MM-yyyy") );
			errorLogger.addAppender(new DailyRollingFileAppender(layout,config.getConfig("ErrorLogFile"),"'.'dd-MM-yyyy") );
			System.out.println("Appenders added");

			debugLogger.setLevel(Level.toLevel(config.getConfig("DebugLogLevel")));
			infoLogger.setLevel(Level.toLevel(config.getConfig("InfoLogLevel"))); 
			errorLogger.setLevel(Level.toLevel(config.getConfig("ErrorLogLevel")));
			System.out.println("Levels set. Logger initialized .... ");
		}
		catch ( Exception e ) {
			System.out.println("Exception in Logger "+e);
		}
		catch (Throwable t ) {
			System.out.println("Throwable in Logger "+t);
		}
	}

	private static LoggerMsg getInstance() {
		if(loggerMsg == null) {
			loggerMsg = new LoggerMsg();
		}
		return loggerMsg;
	}

	/**
	 * @param debugtxt
	 */
	public static void debug(String debugtxt)	{
		if(getInstance().debugLogger.getLevel().equals(Level.DEBUG)) {
			getInstance().debugLogger.debug(debugtxt);
		}
	}

	/**
	 * @param funcname
	 * @param classname
	 * @param methname
	 * @param methodctx
	 * @param debugtxt
	 */
	public static void debug(String funcname, String classname, String methname, String methodctx, String debugtxt) {
		if(getInstance().debugLogger.getLevel().equals(Level.DEBUG)) {
			getInstance().debugLogger.debug(funcname+"-"+classname+"-"+methname+"-"+methodctx+"-"+debugtxt);
		}
	}
	/**
	 * @param classname
	 * @param method
	 * @param errorcode
	 * @param exMessage
	 */
	public static void debug(String classname, String method, String errorcode, String exMessage){
		
		if(getInstance().debugLogger.getLevel().equals(Level.DEBUG)){
			getInstance().debugLogger.debug(classname+"-"+method+"-"+errorcode+"-"+exMessage);
		}
	}
	
	/**
	 * @param info
	 */
	public static void info ( String info ) {
		if(getInstance().infoLogger.getLevel().equals(Level.INFO)) {
			getInstance().infoLogger.info(info);
		}
	}

	/**
	 * @param funcname
	 * @param classname
	 * @param methname
	 * @param exptype
	 * @param errcode
	 * @param errmsg
	 */
	public static void error(String funcname, String classname, String methname, String exptype, String errcode, Exception errmsg) {
		if(getInstance().errorLogger.getLevel().equals(Level.ERROR)) {
			getInstance().errorLogger.error(funcname+"-"+classname+"-"+methname+"-"+exptype+"-"+errcode+"-"+errmsg, errmsg);
		}
	}

	/**
	 * @param details
	 */
	public static void error ( String details ) {
		if(getInstance().errorLogger.getLevel().equals(Level.ERROR)) {
			getInstance().errorLogger.error(details);
		}
	}

	/**
	 * @param exception
	 */
	public static void error ( Throwable exception ) {
		if(getInstance().errorLogger.getLevel().equals(Level.ERROR)) {
			getInstance().errorLogger.error(exception.getMessage(),exception);
		}
	}

}