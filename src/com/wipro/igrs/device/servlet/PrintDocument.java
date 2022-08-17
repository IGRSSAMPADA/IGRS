//------------------------------------------------------------------------------ 
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
// CRIMSONLOGIC/PHIDELITY TEAM SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED
// AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
// DERIVATIES. PERMISSION TO USE, COPY, MODIFY AND DISTRIBUTE THE SOFTWARE
// AND ITS DOCUMENTATION FOR USE WITH PHIDELITY PRODUCT IS HEREBY GRANTED.
// 
// PrintDocument.java - Proxy Servlet
//------------------------------------------------------------------------------
package com.wipro.igrs.device.servlet;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.StringPart;

/**
 * Proxy Servlet that acts as an intermediary between
 * PCA -> Application -> Phidelity Server
 * 
 * @author rinosh
 *
 */
public class PrintDocument extends HttpServlet {

	private static final long serialVersionUID = -3525361931332603198L;
	public static String serverURL;
	public static String statusServlet; 

	/**
	 * init servlet 
	 */
	public void init() throws ServletException {		
		serverURL = this.getServletContext().getInitParameter(
				"phidelity.server.url");
		statusServlet = this.getServletContext().getInitParameter(
				"print.status.servlet");
		if (serverURL == null || statusServlet == null) {
			printLogMessage("Either {phidelity.server.url} or {print.status.servlet} not configured properly.");
			throw new ServletException ("Either {phidelity.server.url} or {print.status.servlet} not configured properly.");
		}
		printLogMessage("{phidelity.server.url} " + serverURL);
		printLogMessage("{print.status.servlet} " + statusServlet);
	}

	/**
	 * doGet
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cmd = request.getParameter("cmd");
		printLogMessage("<command> :: "+cmd);
		if (cmd == null || (!"pstatus".equalsIgnoreCase(cmd) && !"pdoc".equalsIgnoreCase(cmd)
				&& !"testprint".equalsIgnoreCase(cmd))) {
			printLogMessage("Invalid command for processing document");
			throw new ServletException(
					"Invalid command for processing document");
		}
		boolean testprint = "testprint".equalsIgnoreCase(cmd);
		if ("pstatus".equalsIgnoreCase(cmd)) {
			updatePrintStatus(request, response);
		} else if ("pdoc".equalsIgnoreCase(cmd) || testprint) {
			performPrintDoc(request, response, testprint);
		}
	}

	/**
	 * Perform Document Printing
	 * @param request
	 * @param response
	 * @param testprint
	 * @throws IOException
	 */
	private void performPrintDoc(HttpServletRequest request,
			HttpServletResponse response, boolean testprint) throws IOException {

		String fileName = request.getParameter("docid");
		String copies = request.getParameter("copies");
		String pmodel = request.getParameter("pmodel");
		String printerID = request.getParameter("printerID");
		String tval = request.getParameter("tval");
		String igrsId = getIGRSID(request);
		fileName = getFileName(fileName);

		String tv = (tval == null) ? "0" : tval;
		Float scaleval = (0.005f * Integer.parseInt(tv));

		String prop_data = (testprint == false) ? ("mp.horizontal.mp_1.txt="
				+ igrsId + "\n cm.item.cm_1.text=" + igrsId+"\now.item.2.page=ODD"): "";
		
		printLogMessage("<igrsid> :: " + igrsId);
		printLogMessage("<copies> :: " + copies);
		printLogMessage("<filename> :: " + fileName);
		printLogMessage("<pmodel> :: " + pmodel);
		printLogMessage("<printerid> :: " + printerID);
		printLogMessage("<extproperties> :: " + prop_data.replaceAll("\n", "#"));

		byte[] buffer = null;
		BufferedInputStream bufferedInputStream = null;
		OutputStream outputStream = response.getOutputStream();
		
		try {
			byte[] outputFile = processPrintRequest(serverURL, fileName,
					igrsId, pmodel, printerID, copies, prop_data, testprint,
					Float.toString(scaleval));

			printLogMessage("Size of processed document : " + outputFile.length);
			printLogMessage("Sending document in chunks to PCA");

			buffer = new byte[4096];
			bufferedInputStream = new BufferedInputStream(
					new ByteArrayInputStream(outputFile));
			if ("gzip".equalsIgnoreCase(request.getHeader("Accept-Encoding"))) {
				printLogMessage("Requested encoding: gzip");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Encoding", "gzip");
				GZIPOutputStream gzipStream = new GZIPOutputStream(outputStream);
				int len = 0;
				while ((len = bufferedInputStream.read(buffer, 0, 4096)) > 0) {
					gzipStream.write(buffer, 0, len);
				}
				gzipStream.close();
				printLogMessage("success :: written compressed stream to output");
			} else {
				printLogMessage("Requested encoding: (un-compressed)");
				response.setContentType("application/octet-stream");
				int datalen = 0;
				while ((datalen = bufferedInputStream.read(buffer, 0, 4096)) > 0) {
					outputStream.write(buffer, 0, datalen);
				}
				bufferedInputStream.close();				
				outputStream.flush();
				outputStream.close();
				printLogMessage("success :: written content to output stream");
			}
		} catch (Exception e) {
			printLogMessage("Error in connection :: " + e);
			e.printStackTrace();
		} finally {
			bufferedInputStream = null;
			buffer = null;
		}
	}

	/**
	 * Forward Print Status
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void updatePrintStatus(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String pstatus = request.getParameter("pstatus");
		String pcomment = request.getParameter("pcomment");
		String igrsId = getIGRSID(request);
		String fileName = getFileName(request.getParameter("docid"));

		OutputStream outputStream = response.getOutputStream();
		printLogMessage("received status message: " + pstatus + " , "
				+ pcomment);
		response.setContentType("applicaton/octet-stream");
		outputStream.write("OK".getBytes(), 0, 2);
		printLogMessage("written pstatus ack to output stream");

		request.setAttribute("igrsId", igrsId);
		request.setAttribute("fileName", fileName);

		printLogMessage("dispatching to forward servlet {"+statusServlet+"}");
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext
				.getRequestDispatcher(statusServlet);
		requestDispatcher.forward(request, response);

		printLogMessage("returned from forwarded servlet");

		outputStream.flush();
		outputStream.close();
		response.flushBuffer();
	}

	/**
	 * Parse IGRS ID if available
	 * 
	 * @param request
	 * @return
	 */
	private String getIGRSID(HttpServletRequest request) {
		String fileName = request.getParameter("docid");
		String igrsId = "";
		if (fileName != null) {
			if (fileName.contains("&")) {
				igrsId = fileName.substring(fileName.indexOf("&") + 5);
			} else if(request.getParameterMap().containsKey("uid")){
				igrsId = request.getParameter("uid");
			}
		}
		return igrsId;
	}

	/**
	 * Get File Name
	 * 
	 * @param fileName
	 * @return
	 */
	private String getFileName(String fileName) {
		if (fileName != null) {
			if (fileName.contains("&")) {
				fileName = fileName.substring(0, fileName.indexOf("&"));
			}
		}
		return fileName;
	}

	public void destroy() {
	}

	/**
	 * Compress if required
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static byte[] compress(byte[] input) throws IOException {
		byte[] buf = new byte[4096];
		GZIPOutputStream out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		out = new GZIPOutputStream(baos);
		ByteArrayInputStream in = new ByteArrayInputStream(input);
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.flush();
		out.close();

		return baos.toByteArray();
	}

	/**
	 * Decompress if required
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public byte[] decompress(byte[] input) throws IOException {
		byte[] buf = new byte[4096];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		GZIPInputStream in = new GZIPInputStream(
				new ByteArrayInputStream(input));
		int len;
		while ((len = in.read(buf)) > 0) {
			baos.write(buf, 0, len);
		}
		in.close();
		baos.close();

		return baos.toByteArray();
	}

	/**
	 * Post Request to Phidelity for processing
	 * 
	 * @param serverURL
	 * @param multipartParams
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public byte[] postHttpRequest(String serverURL, List multipartParams)
			throws IOException, ClassNotFoundException {
		HostConfiguration hostConfiguration = null;
		HttpClient httpClient = new HttpClient(
				new MultiThreadedHttpConnectionManager());
		int redirectCount = 0;
		PostMethod httpPost;
		int statusCode;
		org.apache.commons.httpclient.methods.multipart.Part[] parts = new org.apache.commons.httpclient.methods.multipart.Part[multipartParams
				.size()];
		parts = (org.apache.commons.httpclient.methods.multipart.Part[]) multipartParams
				.toArray(parts);

		for (;;) {
			try {
				httpPost = new PostMethod(serverURL);
				httpPost.setRequestHeader("Content-Encoding", "gzip");
				httpPost.addRequestHeader("Accept-Encoding", "gzip");
				MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(
						parts, httpPost.getParams());
				httpPost.setRequestEntity(multipartRequestEntity);
				statusCode = -1;
				if (hostConfiguration != null) {
					hostConfiguration.setHost(serverURL);
					statusCode = httpClient.executeMethod(hostConfiguration,
							httpPost);
				} else {
					statusCode = httpClient.executeMethod(httpPost);
				}
				printLogMessage("after httpClient.executeMethod");

			} catch (org.apache.commons.httpclient.HttpException he) {
				throw new IOException(he.getMessage());
			} catch (Exception e) {
				throw new IOException(e.getMessage());
			}
			if (redirectCount < 3
					&& (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
							|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY
							|| statusCode == HttpStatus.SC_SEE_OTHER || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
				redirectCount++;
				Header locationHeader = httpPost.getResponseHeader("location");
				if (locationHeader != null) {
					serverURL = locationHeader.getValue();
					httpPost.getResponseBody();
					httpPost.releaseConnection();
					continue;
				} else {
					// The response is invalid and did not provide the new
					// location for
					// the resource. Report an error or possibly handle the
					// response
					// like a 404 Not Found error.
				}
			}
			break;
		}

		if (statusCode != HttpStatus.SC_OK) {
			byte[] message = httpPost.getResponseBody();
			throw new IOException("Error from server: " + new String(message));
		}

		byte[] retVal = null;
		String headerName = "";
		httpPost.getResponseHeader(headerName);
		InputStream ois = httpPost.getResponseBodyAsStream();
		int length;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] temp = new byte[4096];
		while ((length = ois.read(temp, 0, 4096)) >= 0) {
			baos.write(temp, 0, length);
		}
		ois.close();
		retVal = baos.toByteArray();
		baos.close();
		httpPost.releaseConnection();
		httpPost = null;
		ois = null;
		baos = null;
		if (statusCode != HttpStatus.SC_OK) {
			printLogMessage("server error " + statusCode);
			throw new IOException("Error from server: " + statusCode);
		}
		return retVal;
	}

	/**
	 * Print log messages - console logging purpose only
	 * 
	 * @param message
	 */
	private void printLogMessage(String message) {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		System.out.println(ft.format(dNow) + " [INFO] "+this.getClass().getName()+" : "
				+ message);
	}

	/**
	 * Safe closing of file handle
	 * 
	 * @param fis
	 */
	public void safeClose(FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				printLogMessage("safeClose() error closing file " + e);
			}
		}
	}

	/**
	 * 
	 * @param serverURL
	 * @param fileName
	 * @param igrsId
	 * @param pmodel
	 * @param printerID
	 * @param copies
	 * @param prop_data
	 * @param testprint
	 * @param tval
	 * @return
	 * @throws Exception
	 */
	byte[] processPrintRequest(String serverURL, String fileName,
			String igrsId, String pmodel, String printerID, String copies,
			String prop_data, boolean testprint, String tval) throws Exception {
		byte[] fileData = (testprint == false) ? ((byte[]) null)
				: "CRIMSONLOGIC".getBytes();
		printLogMessage("processPrintRequest() fileName: " + fileName);
		printLogMessage("processPrintRequest() testPrint: " + testprint);

		if (!testprint) {
			FileInputStream fis = null;
			File file = new File(fileName);
			byte[] tbuffer = new byte[4096];
			try {
				// convert file into array of bytes
				fis = new FileInputStream(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int len;
				while ((len = fis.read(tbuffer)) > 0) {
					baos.write(tbuffer, 0, len);
				}
				fileData = baos.toByteArray();
				printLogMessage("file reading complete. File is of size "
						+ fileData.length);
				if (fileData.length < 50) {
					throw new Exception("file data too small");
				}
			} catch (Exception e) {

				printLogMessage("Error reading file " + e.getMessage());
				throw new Exception("file reading error");
			} finally {
				safeClose(fis);
			}
		}

		printLogMessage("processPrintRequest() init http client ");		
		HttpClient httpClient = new HttpClient(
				new MultiThreadedHttpConnectionManager());
		int connectionTimeOutValue = Integer.parseInt(System.getProperty(
				"httpclient.connection.timeout", "180000"));
		int readWriteTimeOut = Integer.parseInt(System.getProperty(
				"httpclient.readwrite.timeout", "180000"));
		// Set httpclient timeout settings
		httpClient.setConnectionTimeout(connectionTimeOutValue);
		httpClient.setTimeout(readWriteTimeOut);

		byte[] docAsBytes = null;
		try {
			Map param = new HashMap();
			param.put("cmd", "pdoc");
			param.put("app_id", "mpigrs");
			param.put("doc_type", (testprint == false) ? "registration"
					: "printcontrol");
			param.put("docid", (testprint == false) ? igrsId : "printcontrol");
			param.put("document_id", (testprint == false) ? igrsId
					: "printcontrol");
			param.put("document_type", (testprint == false) ? "registration"
					: "printcontrol");
			param.put("pmodel", pmodel);
			param.put("printerID", (testprint == false) ? printerID : "");
			param.put("copies", (testprint == false) ? copies : "1");
			param.put("printer_name", pmodel);
			param.put("prop_data", prop_data);
			printLogMessage("data length is " + fileData.length);
			param.put("data", fileData);
			param.put("tval", tval);
			param.put("doc_name", fileName);

			printLogMessage("processPrintRequest() param.put ");

			List multipartParams = new ArrayList();
			for (Iterator iterator = param.entrySet().iterator(); iterator
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				if (entry.getValue() instanceof String) {
					multipartParams.add(new StringPart((String) entry.getKey(),
							(String) entry.getValue()));
				} else if (entry.getValue() instanceof byte[] && !testprint) {
					byte[] compressedDoc = compress((byte[]) entry.getValue());
					printLogMessage("after compression, doc array is of length : "
							+ compressedDoc.length);
					if (compressedDoc.length < 50) {
						throw new Exception("compressed size too small!");
					}
					multipartParams.add(new FilePart((String) entry.getKey(),
							new ByteArrayPartSource((String) param
									.get("doc_name"), compressedDoc)));
				}
			}
			docAsBytes = (byte[]) postHttpRequest(serverURL, multipartParams);
			if (docAsBytes != null && docAsBytes.length > 2) {
				if (docAsBytes[0] == 0x1F && docAsBytes[1] == (byte) 0x8B) {
					docAsBytes = decompress(docAsBytes);
				}
			}
		} catch (Exception e) {
			printLogMessage("exception in processPrintRequest! " + e);
		}
		return docAsBytes;
	}
	
	private boolean isSessionValid(){		
		return true;
	}
}