package com.wipro.igrs.cybertehsil.forms.util;

import java.io.File;

public class FormVIUtil {
	
	  public File getFileName(File file) {
	        if (file.exists()){
	            String newFileName = file.getName();
	           // System.out.println("newFileName "+newFileName);
	            String simpleName = file.getName().substring(0,newFileName.indexOf("."));
	            String strDigit="";

	            try {
	                simpleName = (Integer.parseInt(simpleName)+1+"");
	                File newFile = new File(file.getParent()+"/"+simpleName+getExtension(file.getName()));
	                return getFileName(newFile);
	            }catch (Exception e){}

	            for (int i=simpleName.length()-1;i>=0;i--){
	                if (!Character.isDigit(simpleName.charAt(i))){
	                    strDigit = simpleName.substring(i+1);
	                    simpleName = simpleName.substring(0,i+1);
	                    break;
	                }
	            }

	            if (strDigit.length()>0){
	                simpleName = simpleName+(Integer.parseInt(strDigit)+1);
	            }else {
	                simpleName+="1";
	            }

	            File newFile = new File(file.getParent()+"/"+simpleName+getExtension(file.getName()));
	            return getFileName(newFile);
	        }
	        return file;
	    }

	  public String getExtension(String name) {
	        return name.substring(name.lastIndexOf("."));
	}
	
	  public File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }

	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}
	  
	  public float[] getCoordinatesForPDF(int filelength) {
		  
			float c=20; // gap
			float inc=75;
		    float w1=0;
		    float h1=75;
		    float w2=0;
		    float h2 = h1+75;
		    float decH = 100;
		    int quotient ;
	        int remainder ;
		    if(filelength>6 && filelength <48)
		    {	//addPage = true;
		    	quotient = filelength/6;
		    	remainder = filelength%6;
		    	
		    	h1=725;
		    	h2 = h1+75;
		    	
		    	for(int q=0;q<quotient;q++) {
		    		w1=0;w2=0;
		    		h1=725 - q*decH;
			    	h2 = h1+75;
		    		for(int f= 0;f<remainder; f++) {
		    			w1= w2+c;
		    			w2 = w1+inc;
		    		}
		    	}	
		    	
		    }else {
		    		for(int f= 0;f<filelength; f++) {
		    			w1= w2+c;
		    			w2 = w1+inc;
		    		}
		    }
		    
		    float[] ret =  {w1,h1,w2,h2};
		  return ret;
	  }

}
