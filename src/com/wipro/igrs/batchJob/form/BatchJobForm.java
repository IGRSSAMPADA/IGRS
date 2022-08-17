package com.wipro.igrs.batchJob.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.batchJob.dto.BatchJobDTO;

public class BatchJobForm extends ActionForm
{
	BatchJobDTO batchDto = new BatchJobDTO();

	/**
	 * @return the batchDto
	 */
	public BatchJobDTO getBatchDto()
	{
		return batchDto;
	}

	/**
	 * @param batchDto the batchDto to set
	 */
	public void setBatchDto(BatchJobDTO batchDto)
	{
		this.batchDto = batchDto;
	}

}
