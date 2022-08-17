package com.wipro.igrs.device.servlet;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ImageCaptureServlet
 */
public class WebcameraServlet extends HttpServlet {

	private static final long serialVersionUID = 6483007126334525391L;
	// private static final String UPLOAD_BASE = "igrs_upload_stage_path";
	private static final String PROP_SUFFIX = "/WEB-INF/classes/resources/igrs.properties";
	private static Properties props;
	private static Logger logger;
	private static String retVal;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebcameraServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			logger = Logger.getLogger(this.getClass());
			String path = config.getServletContext().getRealPath("");
			logger.info("Servlet path : " + path);
			props = new Properties();
			File file = new File(path + PROP_SUFFIX);
			FileInputStream fis = new FileInputStream(file);
			props.load(fis);
			logger.info("Properties loaded");
			fis.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get called");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			logger.info("Entering method doPost");
			StringBuilder builder = new StringBuilder();
			String guid;
			String parentPath;
			String fileName;
			byte[] buffer;
			File output;
			retVal = "true";
			Object content;
			HashMap<String, Object> payload;
			ObjectInputStream ois;
			OutputStream os;
			BufferedOutputStream bos;
			ois = new ObjectInputStream(request.getInputStream());
			logger.info("Content Stream initialized");
			content = ois.readObject();
			logger.info("Content read by servlet is : " + content);
			ois.close();
			logger.info("Content is hashmap : " + (content instanceof HashMap));
			if ((content instanceof HashMap)) {
				// basicPath = props.getProperty(UPLOAD_BASE);
				payload = (HashMap<String, Object>) content;
				// printMap(payload);
				if (payload != null) {
					guid = (String) payload.get("guid");
					parentPath = (String) payload.get("parentPath");
					buffer = (byte[]) payload.get("binary");
					fileName = (String) payload.get("fileName");
					builder.append(parentPath);
					builder.append("/");
					builder.append(guid);
					builder.append("/");
					builder.append(fileName);
					logger.info("File path built : " + builder);
					output = new File(builder.toString());
					if (output.exists()) {
						logger.info("file already exists deleting....");
						output.delete();
					}
					if (output.getParentFile().exists() == false) {
						logger.info("Parent not found creating directory....");
						output.getParentFile().mkdirs();
					}
					os = new FileOutputStream(output, false);
					bos = new BufferedOutputStream(os);
					bos.write(buffer);
					logger.info("File contents written");
					bos.flush();
					logger.info("File contents flushed");
					os.close();
					logger.info("File generated at : " + output.getAbsolutePath());
					payload.clear();
				}
				content = null;
				buffer = null;
				guid = null;
				parentPath = null;
				buffer = null;
				fileName = null;
			}
			builder.delete(0, builder.length());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			retVal = "false";
		}

		//

		response.setContentType("application/x-java-serialized-object");
		ObjectOutputStream outputToApplet = new ObjectOutputStream(response.getOutputStream());
		outputToApplet.writeObject(retVal);
		System.out.println("Data transmission complete.");

		outputToApplet.flush();
		outputToApplet.close();
		//

		logger.info("Leaving method doPost");
	}

	public void printMap(HashMap<String, Object> payload) {
		Set<String> keys = payload.keySet();
		for (String key : keys) {
			Object entry = payload.get(key);
			System.out.println("Entry : " + entry + " Type " + entry.getClass().getName());
		}
	}
}
