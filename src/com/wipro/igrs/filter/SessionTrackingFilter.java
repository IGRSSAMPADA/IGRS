package com.wipro.igrs.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.PropertiesFileReader;

public class SessionTrackingFilter implements Filter {

	private Logger logger = (Logger) Logger.getLogger(SessionTrackingFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req; // Get the IP
		// address of
		// client
		// machine.

		HttpSession session = ((HttpServletRequest) request).getSession();
		session.setAttribute("StartTimeStamp", System.currentTimeMillis());
		PropertiesFileReader proper;
		String requestParameter = request.getParameter("formRetained");

		if ("yes".equalsIgnoreCase(requestParameter)) {
			String sessionObjectRetained = "";
			try {
				proper = PropertiesFileReader.getInstance("resources.igrs");
				sessionObjectRetained = proper.getValue("sessionVariables");
				if (sessionObjectRetained != null && !sessionObjectRetained.isEmpty()) {
					String sessionArray[] = sessionObjectRetained.split(",");
					List<String> list = Arrays.asList(sessionArray);
					for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {
						String attribName = (String) e.nextElement();
						if (list.contains(attribName)) {
						} else {
							System.out.println("Attributes names are... "+attribName);
							session.removeAttribute(attribName);
						}
					}
				}

			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}

		}

		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
		logger.debug("Filter initialized method");
	}

	public void destroy() {
		logger.debug("Filter destroyed method");
	}

}
