

package com.wipro.igrs.hrpayroll.hrpl.rule;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.HrpayrollConstant;
import com.wipro.igrs.hrpayroll.hrpl.dto.ArrearsDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.AttendanceReportDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.LeaveDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.MappingDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.PenalityDTO;
import com.wipro.igrs.util.CommonRoutines;
import com.wipro.igrs.util.CommonUtil;
import com.wipro.igrs.util.PropertiesFileReader;


/**
 * @author root
 *
 */
/**
 * @author root
 * 
 */
public class HrplinkingRule {
	private boolean error;

	PropertiesFileReader pr = null;

	ArrayList errorList = null;
	private Logger logger = (Logger) Logger.getLogger(HrplinkingRule.class);
	public HrplinkingRule() {
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.hrpayroll");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	protected boolean nullOrBlank(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}

	/**
	 * @param penalityDTO
	 * @return
	 */
	public ArrayList validatePenalityDTORule(PenalityDTO penalityDTO) {

		boolean flag = false;
		ArrayList errorList = new ArrayList();
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			if (penalityDTO.getPanEmpID().trim().length() == 0) {
				flag = true;
				errorList.add(pr.getValue("error.empid"));
			}

			setError(flag);
		}

		catch (Exception x) {
			logger.error("**validatePenalityDTORule :-" + x);
		}
		return errorList;
	}

	/**
	 * @param value
	 * @return
	 */
	public ArrayList validateEmpID(boolean value) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			if (!value) {
				errorList.add(pr.getValue("error.header"));
				errorList.add(pr.getValue("error.empidnotvalid"));
				flag = true;
			} else {
				flag = false;
			}
			setError(flag);
		}

		catch (Exception x) {
			x.printStackTrace();
		}
		return errorList;
	}

	public ArrayList validateLeaveDetail(boolean value) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			if (!value) {
				errorList.add(pr.getValue("error.header"));
				errorList.add(pr.getValue("error.empidnotvalid"));
				flag = true;
			} else {
				flag = false;
			}
			setError(flag);
		}

		catch (Exception x) {
			x.printStackTrace();
		}
		return errorList;
	}

	/**
	 * @param penalityDTO
	 * @return
	 */
	public ArrayList validatePenalityDTOSessionRule(PenalityDTO penalityDTO) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();

		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			if (penalityDTO == null) {
				flag = true;
				errorList.add(pr.getValue("error.penality.session"));
			} else {
				if (penalityDTO.getPanEmpID().trim().length() == 0) {
					flag = true;
					errorList.add(pr.getValue("error.empid"));
				}
				if (penalityDTO.getPanamt().trim().length() == 0) {
					flag = true;
					errorList.add(pr.getValue("error.penality.panamt"));
				} else if (!CommonRoutines.isAmount(penalityDTO.getPanamt()
						.trim())) {
					flag = true;
					errorList.add(pr.getValue("error.penality.panamt.number"));
				}
				if (penalityDTO.getPanreason().trim().length() == 0) {
					flag = true;
					errorList.add(pr.getValue("error.penality.panreason"));
				} else if (penalityDTO.getPanreason().trim().length() > 100) {
					flag = true;
					errorList.add(pr
							.getValue("error.penality.panreason.length"));
				}
			}
			setError(flag);
		}

		catch (Exception x) {
			x.printStackTrace();
		}
		return errorList;
	}

	boolean isInteger(String iNumber) {

		int i;
		boolean flag = true;
		for (i = 0; i < iNumber.length(); i++) {
			char c = iNumber.charAt(i);

			if (((c >= '0') && (c <= '9'))) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * @param arrearsDTO
	 * @return
	 */
	public ArrayList validateArrearsDTORule(ArrearsDTO arrearsDTO) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			if (HrpayrollConstant.HR_PAYROLL_ARREARS
					.equalsIgnoreCase(arrearsDTO.getArrearForm())) {
				if (arrearsDTO.getTxtEmpID().trim().length() == 0) {
					flag = true;
					errorList.add(pr.getValue("error.empid"));
				}

				setError(flag);
			}
			if (HrpayrollConstant.HR_PAYROLL_ARREARS_EDIT
					.equalsIgnoreCase(arrearsDTO.getArrearForm())) {
				if (arrearsDTO.getTxtEmpID().trim().length() == 0) {
					flag = true;
					errorList.add(pr.getValue("error.empid"));
				}

				if (arrearsDTO.getArrearsamt().trim().length() == 0) {
					flag = true;
					errorList.add(pr.getValue("error.Arrears.Amt"));
				} else if (!CommonRoutines.isAmount(arrearsDTO.getArrearsamt()
						.trim())) {
					flag = true;
					errorList.add(pr.getValue("error.Arrears.NotAmt"));
				}

				if (arrearsDTO.getReason().trim().length() == 0) {
					flag = true;
					errorList.add(pr.getValue("error.Arrears.Reason"));
				} else if (arrearsDTO.getReason().trim().length() > 100) {
					flag = true;
					errorList.add(pr.getValue("error.Arrears.Reason.length"));
				}
				setError(flag);
			}
		}

		catch (Exception x) {
			logger.error("**validatePenalityDTORule :-" + x);
		}
		return errorList;
	}

	/**
	 * @param result
	 * @return
	 */
	public ArrayList validate(boolean result) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();

		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			//errorList.add(pr.getValue("error.header"));
			if (!result) {
				flag = true;
				errorList.add(pr.getValue("error.database"));
			}

			setError(flag);
		} catch (Exception e) {
			logger.error("**validatePenalityDTORule :-" + e);
		}
		return errorList;
	}

	public ArrayList validateAttendanceRule(AttendanceReportDTO attendancedto) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();

		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			

			if (attendancedto.getEmpid().trim().length() == 0) {
				

				flag = true;
				errorList.add(pr.getValue("error.empid"));
			}

			if (attendancedto.getMonth().trim().equals("Select")) {
				flag = true;
				errorList.add(pr.getValue("error.Attendance.Month"));
				
			}
			if (attendancedto.getYear().trim().equals("Select")) {
				flag = true;
				errorList.add(pr.getValue("error.Attendance.Year"));
			}
			setError(flag);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return errorList;

	}

	/**
	 * @param attendancelist
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateList(ArrayList attendancelist) throws Exception {
		ArrayList errorList = new ArrayList();
		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		boolean flag = false;
		errorList.add(pr.getValue("error.header"));
		try {
			if (attendancelist.size() <= 0) {
				flag = true;
				errorList.add(pr.getValue("error.leave.Noleave"));
			}
			setError(flag);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	public ArrayList validateLeaveGradeCadre(ArrayList leavegradecadrelist)throws Exception{
		ArrayList errorList=new ArrayList();
		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		boolean flag = false;
		//errorList.add(pr.getValue("error.header"));
		try{
			
			if(leavegradecadrelist!=null && leavegradecadrelist.size()<=0){
				
				flag=true;
				errorList.add(pr.getValue("error.leavecadregrademappng.nodata"));
				setError(flag);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return errorList;
		
	}
	
	
	/**
	 * @param attendancelist
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateLeave(ArrayList attendancelist) throws Exception {
		ArrayList errorList = new ArrayList();
		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		boolean flag = false;
		try {
			if (attendancelist.size() <= 0) {
				flag = true;
				errorList.add(pr.getValue("error.Noresults"));
			}
			setError(flag);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	/**
	 * @param leaveDTO
	 * @return
	 */
	public ArrayList validateLeaveDTO(LeaveDTO leaveDTO) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();

		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));

			if (leaveDTO.getLeave_approved_reason().trim().length() >= 0) {
				flag = true;
				errorList.add(pr.getValue("error.leave.reason"));
			}

			setError(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return errorList;
	}

	/**
	 * @param mappingDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList vadidateMappingDTO(MappingDTO mappingDTO) throws Exception {
		boolean flag = false;
		ArrayList errorList = new ArrayList();

		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");

			if (mappingDTO.getMappingform().equalsIgnoreCase(
					HrpayrollConstant.HR_PAYROLL_MAPPING_LEAVE)) {
				//errorList.add(pr.getValue("error.header"));
				
				if (mappingDTO.getGradetypeid().trim().length() == 0) {
					flag = true;
					errorList.add(pr.getValue("error.leave.leavetypeid"));
				}
				if (mappingDTO.getCadretypeid().trim().length() == 0) {
					flag = true;
					errorList.add(pr.getValue("error.leave.leavetypeid"));
				}
			}
			setError(flag);
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return errorList;

	}

	/**
	 * @param leaveDTO
	 * @param list 
	 * @return
	 */
	public ArrayList validateLeaveDetails(LeaveDTO leaveDTO, ArrayList list) {
		boolean flag = false;
		errorList = new ArrayList();		
		try {
			errorList.add(pr.getValue("error.header"));
			if (HrpayrollConstant.HR_PAYROLL_LEAVE_DETAILS_LIST
					.equalsIgnoreCase(leaveDTO.getLeaveDetailForm())) {
				if (leaveDTO.getLeave_fromdate().trim().length() == 0) {

					flag = true;
					errorList.add(pr.getValue("error.fromDate"));
				}
				if (leaveDTO.getLeavetype().trim().length() == 0) {

					flag = true;
					errorList.add(pr.getValue("error.leave.leavetypeid"));
				}
				if (leaveDTO.getPhonenumberonleave().trim().length() > 0) {					
					if (!CommonRoutines.isNumber(leaveDTO
							.getPhonenumberonleave().trim())) {

						flag = true;
						errorList.add(pr.getValue("error.leave.phonenumber"));
					}
				}
				if (leaveDTO.getLeave_todate().trim().length() == 0) {

					flag = true;
					errorList.add(pr.getValue("error.toDate"));
				}
				if (leaveDTO.getLeave_fromdate().trim().length() > 0
						&& leaveDTO.getLeave_todate().trim().length() > 0) {
					//Date d1 = new Date(leaveDTO.getLeave_fromdate().trim());
					//Date d2 = new Date(leaveDTO.getLeave_todate().trim());

					if (CommonUtil.isGreater(leaveDTO.getLeave_fromdate(), leaveDTO.getLeave_todate())) {

						flag = true;
						errorList.add(pr.getValue("error.date"));
					}
				}
				if (leaveDTO.getLeave_reason().trim().length() == 0) {

					flag = true;
					errorList.add(pr.getValue("error.LeaveDetail.Reason"));
				}

				else if (leaveDTO.getLeave_reason().trim().length() > 100) {

					flag = true;
					errorList.add(pr
							.getValue("error.LeaveDetael.Reason.length"));
				}

				if (leaveDTO.getAddressonleave().trim().length() > 0) {
					if (leaveDTO.getAddressonleave().trim().length() > 100) {

						flag = true;
						errorList.add(pr.getValue("error.leave.address"));

					}
				}
				
				if(leaveDTO.getLeavetype().trim().length() >0 && (list==null || (list!=null && list.size()==0))){
					
					errorList.add(pr.getValue("error.header"));			
					errorList.add(pr.getValue("error.leave.notupdated"));
					
					flag = true;
					}
				
				
				setError(flag);
			}
		}

		catch (Exception x) {
			logger.error("**validatePenalityDTORule :-" + x);
		}
		return errorList;
	}

	/**
	 * @param leaveDTO
	 * @param _leaveDTO
	 * @return
	 */
	public ArrayList validateLeaveBalance(LeaveDTO leaveDTO, LeaveDTO _leaveDTO) {
		boolean flag = false;
		errorList = new ArrayList();

		try {
			errorList.add(pr.getValue("error.header"));
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			//SimpleDateFormat newformater = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date result1 = formater.parse(leaveDTO.getLeave_fromdate());
			java.util.Date result2 = formater.parse(leaveDTO.getLeave_todate());
			double diff = result2.getTime() - result1.getTime();
			int days=1+(int)(diff/86400000);			
			double leaveused = Double.parseDouble(_leaveDTO.getLeaveAvail())+ days;
			if (days > Double.parseDouble(_leaveDTO.getLeaveAvail())) {
				errorList.add(pr.getValue("error.leave.notavail"));
				errorList.add(pr.getValue("error.leave.notavailreason"));
				flag = true;
			}
			setError(flag);
		} catch (Exception x) {
			logger.error("**validatePenalityDTORule :-" + x);
		}
		return errorList;
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

	public ArrayList validateLeaveBalanceDetails(ArrayList leavelist) {
		boolean flag = false;
		errorList = new ArrayList();

		try {
			if(leavelist==null || (leavelist!=null && leavelist.size()==0)){
				
			errorList.add(pr.getValue("error.header"));			
			errorList.add(pr.getValue("error.leave.notupdated"));
			
			flag = true;
			}
			setError(flag);
		} catch (Exception x) {
			logger.error("**validatePenalityDTORule :-" + x);
		}
		return errorList;
	}
}
