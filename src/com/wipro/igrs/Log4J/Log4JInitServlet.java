package com.wipro.igrs.Log4J;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class Log4JInitServlet extends HttpServlet {

	private static final String LOG4J_APPENDER_FILE_NAME = "log4j.appender.File";
	private static final String LOG4J_APPENDER_FILE_VALUE = "org.apache.log4j.DailyRollingFileAppender";
	private static final String LOG4J_APPENDER_MAXBACKUP_NAME = "log4j.appender.File.MaxBackupIndex";
	private static final String LOG4J_LOGFILE_NAME = "log4j.appender.File.File";
	private static final int DAY_MILLISECOND = 86400000;
	private static final long serialVersionUID = -2606748293455719369L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			String log4jLocation = config
					.getInitParameter("log4j-properties-location");
			ServletContext srvCtxt = config.getServletContext();
			if (log4jLocation == null) {
				BasicConfigurator.configure();
			} else {
				String webAppPath = srvCtxt.getRealPath("/");
				String log4JPhyPath = webAppPath + log4jLocation;
				File loggerFile = new File(log4JPhyPath);
				Properties props = buildProperties(log4JPhyPath);
				if (loggerFile.exists()) {
					if (props != null) {
						fallBackMaxBackupIndex(props);
						PropertyConfigurator.configure(props);
					} else {
						BasicConfigurator.configure();
					}
				} else {
					BasicConfigurator.configure();
				}
			}
			super.init(config);
		} catch (Exception e) {
		}
	}

	private void fallBackMaxBackupIndex(Properties props) {
//		if(Math.random() > 100) {
//			//Temp skip
//			return;
//		}
		boolean isDailyRolling = false;
		boolean isMaxBackUpSet = false;
		String maxBackValue;
		File logFile;
		try {
			isDailyRolling = LOG4J_APPENDER_FILE_VALUE.equals(props
					.getProperty(LOG4J_APPENDER_FILE_NAME));
			isMaxBackUpSet = props.getProperty(LOG4J_APPENDER_MAXBACKUP_NAME) != null;
			if (isDailyRolling && isMaxBackUpSet) {
				logFile = new File(props.getProperty(LOG4J_LOGFILE_NAME));
				maxBackValue = props.getProperty(LOG4J_APPENDER_MAXBACKUP_NAME);
				deleteOldLogFiles(maxBackValue, logFile.getParentFile());
			}
		} catch (Exception e) {
		}

	}

	private void deleteOldLogFiles(String maxBackValue, File parentFile) {
		try {
			boolean isRoll = false;
			Date now = new Date();
			int maxBackUp = Integer.valueOf(maxBackValue);
			File[] files = parentFile.listFiles();
			for (File entry : files) {
				isRoll = ((now.getTime() - entry.lastModified()) > (DAY_MILLISECOND * maxBackUp));
				if(isRoll) {
					entry.delete();
				}
			}
		} catch (Exception e) {
		}

	}

	private Properties buildProperties(String log4jPhyPath) {
		Properties retVal = null;
		try {
			FileInputStream fileStrm = new FileInputStream(log4jPhyPath);
			try {
				retVal = new Properties();
				retVal.load(fileStrm);
			} catch (Exception e) {
				retVal = null;
			} finally {
				fileStrm.close();
			}
		} catch (Exception e) {
			retVal = null;
		}
		return retVal;
	}

}
