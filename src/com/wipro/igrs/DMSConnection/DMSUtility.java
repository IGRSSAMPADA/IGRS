package com.wipro.igrs.DMSConnection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.reginit.action.CommonAction;
import com.wipro.igrs.reginit.constant.RegInitConstant;




public class DMSUtility{
	private  Logger logger = 
		(Logger) Logger.getLogger(DMSUtility.class);
	
	
	
	
	/**
	 * readImage
	 * for reading images from file system(currently)
	 * @param String
	 * @author ROOPAM
	 * @return 
	 * @throws Exception 
	 *
	 */
	
	public void readImage(String filePath)throws Exception {

		 try {
		
			 //BELOW CREATING OBJECT OF FILE TYPE USING FILEPATH
        	File newFile = new File(filePath);
        	
    		//CONVERTING FILE OBJECT TO BUFFEREDIMAGE TYPE
            BufferedImage originalImage=ImageIO.read(newFile);
            
            //BELOW CREATING OBJECT OF BYTEARRAYOUTPUTSTREAM TYPE
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            
            ImageIO.write(originalImage, "jpg", baos );
            
            //CREATING BYTE ARRAY OF ABOVE IMAGE
            byte[] imageInByte=baos.toByteArray();

            logger.debug("image in bytes--------->"+imageInByte);
             
            //AGAIN CONVERTING BYTE ARRAY INTO BUFFERED IMAGE TYPE.
            final BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageInByte));

            

            //BELOW CODE FOR DISPLAYING IMAGE ON SCREEN
            JFrame imgFrame = new JFrame();
            //Dimension dm=new Dimension(10,10);
            
            //imgFrame.setMaximumSize(dm);
            JLabel imgLabel = new JLabel(new ImageIcon(bufferedImage));
            imgFrame.getContentPane().add(imgLabel, BorderLayout.CENTER);
            imgFrame.pack();
            imgFrame.setVisible(true);
            
            //invoking garbage collector
            System.gc();
            
            } 
		 
		
		 
		 catch (Exception ex) {
        	
              ex.printStackTrace();
             
        }
       
  }
	/**
	 * getImageBytes
	 * Returns image as byte[]
	 * @param String
	 * @author ROOPAM
	 * @return byte[]
	 * @throws Exception 
	 *
	 */
	public byte[] getImageBytes(String filePath)throws Exception {
		
		//byte[] imageInByte=new byte();

		 try {
		
			 //BELOW CREATING OBJECT OF FILE TYPE USING FILEPATH
       	File newFile = new File(filePath);
       	
   		//CONVERTING FILE OBJECT TO BUFFEREDIMAGE TYPE
           BufferedImage originalImage=ImageIO.read(newFile);
           
           //BELOW CREATING OBJECT OF BYTEARRAYOUTPUTSTREAM TYPE
           ByteArrayOutputStream baos=new ByteArrayOutputStream();
           
           ImageIO.write(originalImage, "jpg", baos );
           
           //CREATING BYTE ARRAY OF ABOVE IMAGE
           byte[] imageInByte=baos.toByteArray();

           logger.debug("image in bytes--------->"+imageInByte);
            
           
           return imageInByte;
           
           
           } 
		 
		
		 
		 catch (Exception ex) {
			 
			 
       	
             ex.printStackTrace();
             return null;
       }
		 
		 
      
 }
	
	public static void downloadDocument(HttpServletResponse res, String fileName,byte[] bytes) {
        try {
        	/*File newFile = new File(fileName);
        	
        	FileInputStream fis = new FileInputStream(newFile);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[(int)newFile.length()];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum); 
                    //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                    System.out.println("read " + readNum + " bytes,");
                }
            } catch (IOException ex) {
                //Logger.getLogger(genJpeg.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] bytes = bos.toByteArray();*/
        	
              OutputStream os = res.getOutputStream();
              res.setContentType("application/octet-stream");
              res.setHeader("Content-Disposition", "attachment; filename="
                          + URLEncoder.encode(fileName,"UTF-8"));
              os.write(bytes);
              os.flush();
              os.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
  }
	
	public static byte[] getDocumentBytes(String fileName) {
        try {
        	File newFile = new File(fileName);
        	
        	FileInputStream fis = new FileInputStream(newFile);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[(int)newFile.length()];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum); 
                    //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                    System.out.println("read " + readNum + " bytes,");
                    if(readNum==0){
                    	break;
                    }
                }
            } catch (IOException ex) {
                //Logger.getLogger(genJpeg.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] bytes = bos.toByteArray();
            return bytes;
              
        } catch (Exception e) {
        	return null;
        }
        
        
  }

	
}