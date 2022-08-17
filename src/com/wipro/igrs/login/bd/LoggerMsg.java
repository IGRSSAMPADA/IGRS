/*package com.gss.common.util;

import java.util.Properties;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;


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
            System.out.println("debugLogger " + debugLogger);
            System.out.println("infoLogger " + infoLogger);
            System.out.println("errorLogger " + errorLogger);
            System.out.println("Logger instances created ");

            Properties properties = new Properties();
            properties.put("log4j.rootLogger", "OFF, A1");
            //properties.put("log4j.appender.A1", "org.apache.log4j.DailyRollingFileAppender");
            properties.put("log4j.appender.A1", 
                           "org.apache.log4j.ConsoleAppender");
            properties.put("log4j.appender.A1.layout", 
                           "org.apache.log4j.PatternLayout");
            properties.put("log4j.appender.A1.layout.conversionPattern", 
                           "%d{yyyyMMdd-HH:mm:ss} %m%n");
            //properties.put("log4j.appender.A1.datePattern", "'.'dd-MM-yyyy");
            //properties.put("log4j.appender.A1.file", config.getConfig("DebugLogFile"));
            PropertyConfigurator.configure(properties);

            PatternLayout layout = 
                new PatternLayout("%d{yyyyMMdd-HH:mm:ss} %m%n");
            debugLogger.addAppender(new DailyRollingFileAppender(layout, 
                                                                 config.getConfig("DebugLogFile"), 
                                                                 "'.'dd-MM-yyyy"));
            infoLogger.addAppender(new DailyRollingFileAppender(layout, 
                                                                config.getConfig("InfoLogFile"), 
                                                                "'.'dd-MM-yyyy"));
            errorLogger.addAppender(new DailyRollingFileAppender(layout, 
                                                                 config.getConfig("ErrorLogFile"), 
                                                                 "'.'dd-MM-yyyy"));
            System.out.println("Appenders added");

            debugLogger.setLevel(Level.toLevel(config.getConfig("DebugLogLevel")));
            infoLogger.setLevel(Level.toLevel(config.getConfig("InfoLogLevel")));
            errorLogger.setLevel(Level.toLevel(config.getConfig("ErrorLogLevel")));
            System.out.println("Levels set. Logger initialized .... ");
        } catch (Exception e) {
            System.out.println("Exception in Logger " + e);
        } catch (Throwable t) {
            System.out.println("Throwable in Logger " + t);
        }
    }

    private static LoggerMsg getInstance() {

        if (loggerMsg == null) {
            loggerMsg = new LoggerMsg();
        }
        return loggerMsg;
    }

    public static void debug(String debugtxt) {

        if (getInstance().debugLogger.getLevel().equals(Level.DEBUG)) {

            getInstance().debugLogger.debug(debugtxt);
        }
    }

    public static void debug(String funcname, String classname, 
                             String methname, String methodctx, 
                             String debugtxt) {

        if (getInstance().debugLogger.getLevel().equals(Level.DEBUG)) {

            getInstance().debugLogger.debug(funcname + "-" + classname + "-" + 
                                            methname + "-" + methodctx + "-" + 
                                            debugtxt);
        }
    }

    public static void info(String info) {

        if (getInstance().infoLogger.getLevel().equals(Level.INFO)) {

            getInstance().infoLogger.info(info);
        }
    }

    public static void error(String funcname, String classname, 
                             String methname, String exptype, String errcode, 
                             Exception errmsg) {

        if (getInstance().errorLogger.getLevel().equals(Level.ERROR)) {

            getInstance().errorLogger.error(funcname + "-" + classname + "-" + 
                                            methname + "-" + exptype + "-" + 
                                            errcode + "-" + errmsg, errmsg);
        }
    }

    public static void error(String details) {

        if (getInstance().errorLogger.getLevel().equals(Level.ERROR)) {

            getInstance().errorLogger.error(details);
        }
    }

    public static void error(Throwable exception) {

        if (getInstance().errorLogger.getLevel().equals(Level.ERROR)) {

            getInstance().errorLogger.error(exception.getMessage(), exception);
        }
    }
}
*/