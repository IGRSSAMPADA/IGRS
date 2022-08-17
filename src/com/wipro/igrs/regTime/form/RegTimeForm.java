package com.wipro.igrs.regTime.form;

import org.apache.struts.action.ActionForm;

public class RegTimeForm extends ActionForm
{
	private String srtHor;
	private String srtMin;
	private String srtAmPm;
	private String endHor;
	private String endMin;
	private String endAmPm;
	private String status="A";
	
	private String module;
	private String subModule;
	private String function;
	
	private String startTime;
	private String endTime;
	
	
	
	
	
	
	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	/**
	 * @return the module
	 */
	public String getModule()
	{
		return module;
	}
	/**
	 * @param module the module to set
	 */
	public void setModule(String module)
	{
		this.module = module;
	}
	/**
	 * @return the subModule
	 */
	public String getSubModule()
	{
		return subModule;
	}
	/**
	 * @param subModule the subModule to set
	 */
	public void setSubModule(String subModule)
	{
		this.subModule = subModule;
	}
	
	/**
	 * @return the srtHor
	 */
	public String getSrtHor()
	{
		return srtHor;
	}
	/**
	 * @param srtHor the srtHor to set
	 */
	public void setSrtHor(String srtHor)
	{
		this.srtHor = srtHor;
	}
	/**
	 * @return the srtMin
	 */
	public String getSrtMin()
	{
		return srtMin;
	}
	/**
	 * @param srtMin the srtMin to set
	 */
	public void setSrtMin(String srtMin)
	{
		this.srtMin = srtMin;
	}
	/**
	 * @return the srtAmPm
	 */
	public String getSrtAmPm()
	{
		return srtAmPm;
	}
	/**
	 * @param srtAmPm the srtAmPm to set
	 */
	public void setSrtAmPm(String srtAmPm)
	{
		this.srtAmPm = srtAmPm;
	}
	/**
	 * @return the endHor
	 */
	public String getEndHor()
	{
		return endHor;
	}
	/**
	 * @param endHor the endHor to set
	 */
	public void setEndHor(String endHor)
	{
		this.endHor = endHor;
	}
	/**
	 * @return the endMin
	 */
	public String getEndMin()
	{
		return endMin;
	}
	/**
	 * @param endMin the endMin to set
	 */
	public void setEndMin(String endMin)
	{
		this.endMin = endMin;
	}
	/**
	 * @return the endAmPm
	 */
	public String getEndAmPm()
	{
		return endAmPm;
	}
	/**
	 * @param endAmPm the endAmPm to set
	 */
	public void setEndAmPm(String endAmPm)
	{
		this.endAmPm = endAmPm;
	}
	/**
	 * @return the function
	 */
	public String getFunction()
	{
		return function;
	}
	/**
	 * @param function the function to set
	 */
	public void setFunction(String function)
	{
		this.function = function;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime()
	{
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime()
	{
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

}
