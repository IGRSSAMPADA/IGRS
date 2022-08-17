package com.wipro.igrs.device.action;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.misc.BASE64Decoder;

@SuppressWarnings("unused")
public class EPenAction extends Action {
	private static final String DEFAULT = "default";

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";

	private static Logger logger = Logger.getLogger(EPenAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("Entering method");
		try {
			String forwardPath = request.getParameter("forwardPath");
			String forwardName = request.getParameter("forwardName");
			String fileNameSign = request.getParameter("fileNameSign");
			String parentPathSign = request.getParameter("parentPathSign");
			String uploadGUID = request.getParameter("uploadGUID");
			logger.info("Request Params");
			logger.info("forwardPath : " + forwardPath);
			logger.info("fileNameSign : " + fileNameSign);
			logger.info("parentPathSign : " + parentPathSign);
			logger.info("uploadGUID : " + uploadGUID);
			logger.info("forwardName : " + forwardName);
			
			if ((request.getParameter("resultImg") != null)
					&& (request.getParameter("resultImg") != "")) {
				String dataUrl = request.getParameter("resultImg");
				logger.info("Image String size : " + dataUrl.length());
//				System.out.println("datUrl :" + dataUrl);

				BASE64Decoder decoder = new BASE64Decoder();
				byte[] imgBytes = decoder.decodeBuffer(dataUrl);

				logger.info("Byte decode size is : " + imgBytes.length);
				BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(
						imgBytes));
				logger.info("Created a java.awt.image.BufferedImage");
				File imgOutFile = new File(parentPathSign + "/" + uploadGUID + "/" + fileNameSign);
				if (imgOutFile.getParentFile().exists() == false) {
					imgOutFile.getParentFile().mkdirs();
				}
				ImageIO.write(bufImg, "gif", imgOutFile);
				logger.info("Created file : " + imgOutFile.getAbsolutePath());
				request.setAttribute("signCaptured", "true");
				request.setAttribute("signCapturePath", imgOutFile
						.getAbsolutePath());
				ActionForward returnPath = new ActionForward();
				returnPath.setName(forwardName);
				returnPath.setRedirect(true);
				returnPath.setPath(forwardPath);
				logger.info("Created Return Action Forward : " + returnPath);
				return returnPath;
//				BufferedImage bufferedImage = ImageIO.read(imgOutFile);
//
//				BufferedImage newBufferedImage = new BufferedImage(bufImg
//						.getWidth(), bufImg.getHeight(), 1);
//				newBufferedImage.createGraphics().drawImage(bufImg, 0, 0,
//						Color.WHITE, null);
//
//				ImageIO.write(newBufferedImage, "gif", imgOutFile);
				//return mapping.findForward("capture");
			}

			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
