package com.wipro.igrs.batchJob.dto;

public class BatchJobDTO
{
		private String batchJobName;
		private String applicationErrorMessage;
		private String runDate;
		private String getJob;
		/**
		 * @return the batchJobName
		 */
		public String getBatchJobName()
		{
			return batchJobName;
		}
		/**
		 * @param batchJobName the batchJobName to set
		 */
		public void setBatchJobName(String batchJobName)
		{
			this.batchJobName = batchJobName;
		}
		/**
		 * @return the applicationErrorMessage
		 */
		public String getApplicationErrorMessage()
		{
			return applicationErrorMessage;
		}
		/**
		 * @param applicationErrorMessage the applicationErrorMessage to set
		 */
		public void setApplicationErrorMessage(String applicationErrorMessage)
		{
			this.applicationErrorMessage = applicationErrorMessage;
		}
		/**
		 * @return the runDate
		 */
		public String getRunDate()
		{
			return runDate;
		}
		/**
		 * @param runDate the runDate to set
		 */
		public void setRunDate(String runDate)
		{
			this.runDate = runDate;
		}
		/**
		 * @return the getJob
		 */
		public String getGetJob()
		{
			return getJob;
		}
		/**
		 * @param getJob the getJob to set
		 */
		public void setGetJob(String getJob)
		{
			this.getJob = getJob;
		}
}
