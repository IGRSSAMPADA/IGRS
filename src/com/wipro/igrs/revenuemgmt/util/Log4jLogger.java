/*******************************************************************************
********************************************************************************
* File Name			: Log4jLogger.java
* Date              : 11-12-2007
* Author	        : Madan
* Description	    : This class is used for logging purpose.
*

*******************************************************************************/

//Package name
package com.wipro.igrs.revenuemgmt.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
//Project specific imports

/**
 *<dl>
 *  <dt><B>Brief Description of class:</B>
 *		<dd>This class is used for logging purpose.
 *</dl>
 */

public class Log4jLogger
{
	static public Logger logger ;
	static
	{
	    logger = Logger.getRootLogger();
	    logger.info("Starting Logger");
	}
	
	/** Creates new Logger */
	private Log4jLogger()
	{
    }
    

	/**
	 * Use this static method to set the log level.
	 * @param level New Log level
	 * @return void
	 */
	public static void setLevel( int level)
	{
        switch(level)
        {
            case 1:
                logger.setLevel(Level.FATAL);
                break;
            case 2:
                logger.setLevel(Level.ERROR);
                break;
            case 3:
                logger.setLevel(Level.WARN);
                break;
            case 4:
                logger.setLevel(Level.INFO);
                break;
            case 5:
                logger.setLevel(Level.DEBUG);
                break;
            default:
                logger.setLevel(Level.INFO);
                break;
        }
	}    

	/**
	 * Use this static method to write  a simple debug message in your code.
	 * It produces a screen dump immediately.
	 * @param message Message to be displayed
	 * @return void
	 */
	public static void debug( String message )
	{
		logger.debug(message);
	}

	/**
	 * Use this static method to write  a simple debug message in your code.
	 * It produces a screen dump immediately.
	 * @param message Message to be displayed
	 * @param t Throwable exception
	 * @return void
	 */
	public static void debug( Object message, Throwable t )
	{
		logger.debug(message, t);
	}
	

	/**
	 * Use this static method to write  a simple info message in your code.
	 * It produces a screen dump immediately.
	 * @param message Message to be displayed
	 * @return void
	 */
	public static void info( Object message )
	{
		logger.info(message);
	}

	/**
	 * Use this static method to write  a simple info message in your code.
	 * It produces a screen dump immediately.
	 * @param message Message to be displayed
	 * @param t Throwable exception
	 * @return void
	 */
	public static void info( Object message, Throwable t )
	{
		logger.info(message, t);
	}

	/**
	 * Use this static method to write  a simple error message in your code.
	 * It produces a screen dump immediately.
	 * @param message Message to be displayed
	 * @return void
	 */
	public static void error( Object message )
	{
		logger.error(message);
	}

	/**
	 * Use this static method to write  a simple error message in your code.
	 * It produces a screen dump immediately.
	 * @param message Message to be displayed
	 * @param t Throwable exception
	 * @return void
	 */
	public static void error( Object message, Throwable t )
	{
		logger.error(message, t);
	}
		
	/*
	     * This method is used to write the Exception to the log.
	 	 * @param log Message to be displayed
	 	 * @return void
	 */
 	public static void logException( Throwable t )
 	{
 		logger.error("Exception happened " + t.getMessage());
 		StackTraceElement[] elements = t.getStackTrace();
 		if(elements != null )
 		{
 			for(int i = 0; i <elements.length; i++)
 			{
				logger.error(elements[i].toString());
 			}
 		}
 	}
}
