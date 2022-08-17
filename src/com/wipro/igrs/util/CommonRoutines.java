package  com.wipro.igrs.util;
public class CommonRoutines
{
	public static boolean isNumber(String s)
	{
	  System.out.println("String in isNumber(s) : "+s);
	  String validChars = "0123456789";
	  boolean isNumber = true;
	
	  for (int i = 0; i < s.length() && isNumber; i++) 
	  { 
	    char c = s.charAt(i); 
	    if (validChars.indexOf(c) == -1) 
	    {
	      isNumber = false;
	    }
	    else
	    {
	      isNumber = true;
	    }
	  }
	  System.out.println("isNumber :"+isNumber);
	  return isNumber;
	}
	
	public static boolean isAmount(String s)
	{
	  String validChars = ".0123456789";
	  boolean isAmount = true;
	   String pice=s.substring(s.indexOf(".")+1);
	    
		for (int i = 0; i < s.length() && isAmount; i++) 
		{ 
		    char c = s.charAt(i); 
		    if (validChars.indexOf(c) == -1) 
		    {
		    	isAmount = false;
		    }
		    else
		    {
		    	isAmount = true;
		    }
		    if(c == '.') 
		    	validChars="0123456789";
		}
	
		return isAmount;
	}

	
}