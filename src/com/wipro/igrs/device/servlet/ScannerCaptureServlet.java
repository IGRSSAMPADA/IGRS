package com.wipro.igrs.device.servlet;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Servlet implementation class ScannerCaptureServlet
 */
@SuppressWarnings("unused")
public class ScannerCaptureServlet extends HttpServlet {

	private static final long serialVersionUID = 6483007126334525391L;
	private static final String PROP_SUFFIX = "/WEB-INF/classes/resources/igrs.properties";
	private static Properties props;
	private static Logger logger;
private static String res;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScannerCaptureServlet() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
//			String[] files = new String[] { "D:/Upload/image1.gif",
//					"D:/Upload/image2.gif", "D:/Upload/image3.gif" };
//
//			ArrayList<BufferedImage> initial = new ArrayList<BufferedImage>();
//			ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
//			ArrayList<byte[]> arrays = new ArrayList<byte[]>();
//			for (String imageFile : files) {
//				BufferedImage img = ImageIO.read(new File(imageFile));
//				initial.add(img);
//			}
//			convertImages(arrays, initial);
//			createImages(arrays, images);
//			generatePDF(images, "D:/Upload/capture.pdf", arrays);
		} catch (Exception e) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			logger.info("Entering method doPost");
			StringBuilder builder = new StringBuilder();
			String guid;
			String parentPath;
			res="true";
			String fileName;
			ArrayList<BufferedImage> images;
			ArrayList<byte[]> arrays;
			File output;
			Object content;
			HashMap<String, Object> payload;
			ObjectInputStream ois;
			OutputStream os;
			BufferedOutputStream bos;
			ois = new ObjectInputStream(request.getInputStream());
			logger.info("Content Stream initialized");
			content = ois.readObject();
			logger.info("Content read by servlet");
			ois.close();
			logger.info("Content is hashmap : " + (content instanceof HashMap));
			if ((content instanceof HashMap)) {
				payload = (HashMap<String, Object>) content;
				if (payload != null) {
					ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(
							bufferStream);
					oos.writeObject(payload);
					oos.close();
					logger.info("Payload size is "
							+ bufferStream.toByteArray().length);
					guid = (String) payload.get("guid");
					parentPath = (String) payload.get("parentPath");
					//arrays = (ArrayList<byte[]>) payload.get("images");
					byte[] buffer = (byte[]) payload.get("images");
					//images = new ArrayList<BufferedImage>(arrays.size());
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
					//generatePDF(images, builder.toString(), arrays);
					os = new FileOutputStream(output, false);
					bos = new BufferedOutputStream(os);
					bos.write(buffer);
					logger.info("File contents written");
					bos.flush();
					logger.info("File contents flushed");
					os.close();
					logger.info("File generated at : "
							+ output.getAbsolutePath());
					payload.clear();
				}
				content = null;
				guid = null;
				parentPath = null;
				fileName = null;
			}
			builder.delete(0, builder.length());
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res="false";
		}
		//
		
        response.setContentType("application/x-java-serialized-object");
        ObjectOutputStream outputToApplet = new
ObjectOutputStream(response.getOutputStream());
        outputToApplet.writeObject(res);
        System.out.println ("Data transmission complete.");

        outputToApplet.flush();          
        outputToApplet.close();
//


		logger.info("Leaving method doPost");
		
	}

	private void generatePDF(ArrayList<BufferedImage> images, String fileName,
			ArrayList<byte[]> arrays) throws Exception {
		logger.info("Entering method generatePDF");
		try {
			Image pdfImage;
			logger.info("No. of arrays : " + arrays.size());
			Document document = new Document(PageSize.A4, 20, 20, 20, 20);
			OutputStream fos = new FileOutputStream(fileName, false);
			PdfWriter.getInstance(document, fos);
			document.open();
			for (byte[] bytes : arrays) {
				pdfImage = Image.getInstance(bytes);
				pdfImage.scalePercent(25.0f);
				document.newPage();
				document.add(pdfImage);
			}

			document.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res="false";
		}
		logger.info("Leaving method generatePDF");
	}

/*	private ArrayList<String> writeImages(String fileName,
			ArrayList<byte[]> arrays, ArrayList<BufferedImage> images) {
		ArrayList<String> files = new ArrayList<String>();
		try {
			BufferedImage image;
			String imagePath;
			createImages(arrays, images);
			File pdf = new File(fileName);
			logger.info("File : " + pdf.getAbsolutePath());
			File pdfParent = pdf.getParentFile();
			logger.info("Parent : " + pdfParent.getAbsolutePath());
			for (int iLoop = 0; iLoop < images.size(); iLoop++) {
				imagePath = pdfParent.getAbsolutePath() + "/image" + iLoop
						+ ".gif";
				image = images.get(iLoop);
				ImageIO.write(image, "GIF", new File(imagePath));
				files.add(imagePath);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			res="false";
		}
		return files;
	}
*/
/*	private void createImages(ArrayList<byte[]> arrays,
			ArrayList<BufferedImage> images) {
		logger.info("Entering method createImages");
		try {
			ByteArrayInputStream bais;
			BufferedImage temp;
			logger.info("No. of arrays : " + arrays.size());
			for (byte[] bytes : arrays) {
				bais = new ByteArrayInputStream(bytes);
				temp = ImageIO.read(bais);
				images.add(temp);
				bais.close();
				logger.info("Created on Buffered Image");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res="false";
		}
		logger.info("Leaving method createImages");
	}
*/
/*	private void convertImages(ArrayList<byte[]> arrays,
			ArrayList<BufferedImage> images) {
		logger.info("Converting images : " + images.size());
		try {
			ByteArrayOutputStream baos;
			byte[] bytes;

			for (BufferedImage bufferedImage : images) {
				baos = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "gif", baos);
				baos.flush();
				bytes = baos.toByteArray();
				baos.close();
				arrays.add(bytes);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res="false";
		}
	}
*/}
