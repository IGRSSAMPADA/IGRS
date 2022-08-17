package com.wipro.igrs.empsalcomp.rule;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.wipro.igrs.empsalcomp.dto.EmpSalaryDTO;
import com.wipro.igrs.empsalcomp.util.PropertiesFileReader;

 
public class BasicVerificationRule {

	
	private boolean error;
	private PropertiesFileReader pr;
	 
	private Logger logger = (Logger) Logger
	.getLogger(BasicVerificationRule.class);
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	public ArrayList getVerifiedBasic(EmpSalaryDTO empDTO) {
		ArrayList errorList = new ArrayList();
		boolean flag = false;
		logger.debug("inside BasicVerificationRule");
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			
			int min = empDTO.getMaxSal() == null 
					? 0 : Integer.parseInt(empDTO.getMinSal());
			int max = empDTO.getMaxSal() == null 
					? 0 : Integer.parseInt(empDTO.getMaxSal());
			int increment = empDTO.getIncrementSal() == null 
					? 0 : Integer.parseInt(empDTO.getIncrementSal());
			int basicValue = empDTO.getBasicValue() == null 
					? 0 : Integer.parseInt(empDTO.getBasicValue());
			int currentBasic = empDTO.getCurrentBasic() == null 
					? 0 : Integer.parseInt(empDTO.getCurrentBasic());
			int balance = max - min;
			logger.debug("min:-"+min+":max:-"+max+":increment:-"+increment);
			logger.debug("balance:-"+balance+":currentBasic:-"+currentBasic);
			
			
			
			
			
			
			if(basicValue > max) {
				errorList.add(pr.getValue("error.basic.max")
								+"&nbsp;"
								+max+"&nbsp;</font></li></br>");
				flag = true;
			}
			if(basicValue < min) {
				errorList.add(pr.getValue("error.basic.min")
								+"&nbsp"
								+min+"&nbsp;</font></li></br>");
				flag = true;
			}
			int incrementLoop = 0;
			int basicBalance = basicValue - currentBasic;
			int loop = Math.round(basicBalance/increment);
			
			logger.debug("basicValue:-"+basicValue+":loop:-"+loop);
			logger.debug("basicBalance:-"+basicBalance);
			
			String status = "";
			if(loop > 1) {
				for(int i = 0; i<loop; i++) {
					incrementLoop = incrementLoop + increment;
					logger.debug("incremementLoop:-"+incrementLoop);
					if(basicBalance == incrementLoop ){
						status = "";
						flag = false;
						break;
					}else {
						status = pr.getValue("error.basic.inc");
						logger.debug("status:-"+status);
						flag = true;
					}
				}
			}else if(loop < 1)  {
				status = pr.getValue("error.basic.inc");
				logger.debug("status:-"+status);
				flag = true;
			}
			if(!"".equals(status)) {
				errorList.add(status);
			}
			logger.debug("incremementLoop:-"+incrementLoop);
			setError(flag);
					 
			 
		}catch(Exception x) {
			logger.debug(x);
		}
		
		return errorList;
	}
	 
	 
}
