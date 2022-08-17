package com.wipro.igrs.templateDictionary.form;

import org.apache.struts.action.ActionForm;

public class TemplateForm extends ActionForm
{
	
	private String templateName;
	private String tempContent;
	private String tempHeader;
	private String tempFooter;
	
	private String module;
	private String subModule;
	private String function;
	private String status;
	private String templateId;
	/**
	 * @return the templateName
	 */
	public String getTemplateName()
	{
		return templateName;
	}
	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}
	/**
	 * @return the tempContent
	 */
	public String getTempContent()
	{
		return tempContent;
	}
	/**
	 * @param tempContent the tempContent to set
	 */
	public void setTempContent(String tempContent)
	{
		this.tempContent = tempContent;
	}
	/**
	 * @return the tempHeader
	 */
	public String getTempHeader()
	{
		return tempHeader;
	}
	/**
	 * @param tempHeader the tempHeader to set
	 */
	public void setTempHeader(String tempHeader)
	{
		this.tempHeader = tempHeader;
	}
	/**
	 * @return the tempFooter
	 */
	public String getTempFooter()
	{
		return tempFooter;
	}
	/**
	 * @param tempFooter the tempFooter to set
	 */
	public void setTempFooter(String tempFooter)
	{
		this.tempFooter = tempFooter;
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
	 * @return the templateId
	 */
	public String getTemplateId()
	{
		return templateId;
	}
	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}
}
