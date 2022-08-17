package com.wipro.igrs.device.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.wipro.igrs.payment.util.AESEncrypt;
import com.wipro.igrs.util.PropertiesFileReader;
 
 
public class ImageCaptchaServlet extends HttpServlet {
 
 
    public void init(ServletConfig servletConfig) throws ServletException {
 
        super.init(servletConfig);
 
    }
 
 
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
 
       byte[] captchaChallengeAsJpeg = null;
    
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
    
        String captchaId = httpServletRequest.getSession().getId();
     
        String randoms = generateRandomString();
        httpServletRequest.getSession().setAttribute("QARFAD",randoms );
        
        //String captchaTxt = generateCaptchaText(captchaId);
      //  httpServletRequest.getLocale();
        
        BufferedImage challenge =null;
        
        String r = generateCaptchaText(randoms);
        if(!randoms.equalsIgnoreCase(""))
        {
        	challenge = 	generateCaptcha(r);
        }
        
        
 
            
            JPEGImageEncoder jpegEncoder =
                    JPEGCodec.createJPEGEncoder(jpegOutputStream);
            jpegEncoder.encode(challenge);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (CaptchaServiceException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
 
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
 
        // flush it in the response
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);

        responseOutputStream.flush();
        responseOutputStream.close();
    }
    
    public   BufferedImage generateCaptcha(String Captchatxt)
	{
		String text = Captchatxt;
		 BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		 BufferedImage img1 = new  BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = img.createGraphics();
	        Graphics2D g2d1= img1.createGraphics();
	        Font font = new Font("Arial", Font.PLAIN, 48);
	        g2d.setFont(font);
	        g2d1.setFont(font);
	        FontMetrics fm = g2d.getFontMetrics();
	        int width = fm.stringWidth(text);
	        int height = fm.getHeight();
	        g2d.dispose();
	        g2d1.dispose();
	        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        img1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        g2d = img.createGraphics();
	        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	        g2d.setFont(font);
	        
	        
	        g2d1 = img1.createGraphics();
	        g2d1.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	        g2d1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d1.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	        g2d1.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	        g2d1.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	        g2d1.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2d1.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        g2d1.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	        g2d1.setFont(font);
	        
	        
	        fm = g2d.getFontMetrics();
	        g2d.setColor(Color.BLACK);
	       
	        g2d.drawString(text, 0, fm.getAscent());
	        g2d.dispose();
	       
	        g2d1.setColor(Color.WHITE);
	        g2d1.fillRect(0, 0, width, height);
	        g2d1.dispose();
	        
	        
	        BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	     // paint both images, preserving the alpha channels
	     Graphics g = combined.getGraphics();
	     g.drawImage(img1, 0, 0, null);
	     g.drawImage(img, 0, 0, null);
	        
	      
	        return combined;

		
		
	}
    
    public String generateCaptchaText(String captchaId)
    {
    	PropertiesFileReader prObj = null;
    	String captchaTxt = "";
    		try
    	{
    			prObj = PropertiesFileReader.getInstance("resources.igrs");
    			AESEncrypt crypt = new AESEncrypt();
    			String keyPath=prObj.getValue("mp_treasury_key_ifmis");
    			
    			crypt.setSecretKey(keyPath);
    			
    			String id = crypt.encryptFile(captchaId);
    			
    			 captchaTxt = id.substring(4, 9);
    			 if(captchaTxt.contains("l"))
    			 {
    				 captchaTxt =	 captchaTxt.replaceAll("l", "P");
    			 }
    			 if(captchaTxt.contains("I"))
    			 {
    				 captchaTxt =	 captchaTxt.replaceAll("I", "L");
    			 }
    			return captchaTxt;
    			
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return captchaTxt;
    	}
    	
    		
    	
    	
    }
    
    public String generateRandomString()
    {
    	
    	String uuid = UUID.randomUUID().toString();
    	return uuid;
    	
    }
}
