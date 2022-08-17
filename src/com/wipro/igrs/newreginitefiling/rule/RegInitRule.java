/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.newreginitefiling.rule;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.dto.SROCommentsDTO;
import com.wipro.igrs.auditinspection.dto.SROReportDetailsDTO;
import com.wipro.igrs.util.CommonUtil;
import com.wipro.igrs.util.PropertiesFileReader;

public class RegInitRule {
	PropertiesFileReader pr = null;

	boolean flag = false;
	

	private boolean error;

	ArrayList errors = new ArrayList();

	
	public RegInitRule() {

		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	/**
	 * @param error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return
	 */
	public boolean isError() {
		return error;
	}



	/**
	 * @param fileExt
	 * @return
	 */
	public ArrayList validateFileTypePropMapZip(String fileExt) {
		String[] arrFileExt = { "jpg", "jpeg" , "zip"};
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}
	public ArrayList validateFileTypePropUploadCompltn(String fileExt) {
		String[] arrFileExt = { "jpg","jpeg" };
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}
	
	public ArrayList validateFileTypePartyRelated(String fileExt) {
		/*String[] arrFileExt = { "tif", "pdf", "jpg", "tiff",
				"gif"};*/
		String[] arrFileExt = { "jpg" , "jpeg" };
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}
	
	public int getHeight(byte[] byteArr) {
		//byte[] picture = new byte[30];
try{
        InputStream in = new ByteArrayInputStream(byteArr);

        BufferedImage buf = ImageIO.read(in);
        //ColorModel model = buf.getColorModel();
        int height = buf.getHeight();
        return height;
	}catch(Exception e){
		return 0;
	}
	}
	public int getWidth(byte[] byteArr) {
		//byte[] picture = new byte[30];
		try{
        InputStream in = new ByteArrayInputStream(byteArr);

        BufferedImage buf = ImageIO.read(in);
        //ColorModel model = buf.getColorModel();
        int width = buf.getWidth();
        return width;
	}catch(Exception e){
		return 0;
	}
	}
	


}
