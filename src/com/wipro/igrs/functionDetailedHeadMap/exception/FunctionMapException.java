package com.wipro.igrs.functionDetailedHeadMap.exception;
import com.wipro.igrs.exception.IGRSException;
public class FunctionMapException extends IGRSException{

	public FunctionMapException()
	{
		super();
	}
	public FunctionMapException(String str)
	{
		super(str);
	}

	public FunctionMapException(String str, String code)
	{
		super( str,  code);
	}
	public FunctionMapException(String str, String code, Object value0 )
	{
		super( str,  code, value0 );
	}
	public FunctionMapException(Exception e) {
		super(e);
	}



}
