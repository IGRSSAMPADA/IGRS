package com.wipro.igrs.hrpayroll.hrp.bd;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.hrpayroll.hrp.dao.HRPayrollDAO;
import com.wipro.igrs.hrpayroll.hrp.dto.CadreDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.GradeCadreDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.GradeDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.LeaveDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.SalaryDTO;

public class HRPayrollBD {
	HRPayrollDAO hrPayrollDAO = null;
	private Logger logger = (Logger) Logger.getLogger(HRPayrollBD.class);
	/**
	 * Method : HRPayrollBD Description : HRPayrollBD Constructor
	 * 
	 * @throws :
	 *             Exception
	 */
	public HRPayrollBD() throws Exception {
		// TODO Auto-generated constructor stub
		// logger.info("In CadreBD() Constructor");
		hrPayrollDAO = new HRPayrollDAO();
	}

	/**
	 * Method : displayCadres Description : Shows all the Cadres
	 * 
	 * @param :
	 * @return _cadreList : ArrayList
	 * @throws :
	 *             Exception
	 */
	public ArrayList displayCadres() throws Exception {
		ArrayList cadreList = hrPayrollDAO.displayCadres();
		ArrayList _cadreList = new ArrayList();
		logger.info("Size in BD: " + cadreList.size());
		if (cadreList != null) {
			for (int i = 0; i < cadreList.size(); i++) {
				ArrayList cadList = (ArrayList) cadreList.get(i);
				if (cadList != null) {
					CadreDTO cadreDTO = new CadreDTO();
					cadreDTO.setCadreId((String) cadList.get(0));
					cadreDTO.setCadreName((String) cadList.get(1));
					cadreDTO.setCadrePosts((String) cadList.get(2));
					// logger.info("->"+cadreDTO);
					_cadreList.add(cadreDTO);
				}
			}
		}
		return _cadreList;
	}

	/**
	 * Method : getCadrePostsCount Description : Get the total posts count
	 * 
	 * @param :
	 * @return count : int
	 * @throws :
	 *             Exception
	 */
	public int getCadrePostsCount() throws Exception {
		int count = hrPayrollDAO.getCadrePostsCount();
		return count;
	}

	/**
	 * Method : checkCadreNameExists Description : checks whethere cadre name
	 * exists or not in DB
	 * 
	 * @param cadreName :
	 *            String
	 * @return found : boolean
	 * @throws :
	 *             Exception
	 */
	public boolean checkCadreNameExists(String cadreName) throws Exception {
		boolean found = hrPayrollDAO.checkCadreNameExists(cadreName);
		return found;
	}

	/**
	 * Method : insertNewCadre Description : Inserts cadreDTO object into DB
	 * 
	 * @param cadreDTO :
	 *            CadreDTO
	 * @param userId :
	 *            String
	 * @return inserted : boolean
	 * @throws :
	 *             Exception
	 */
	public boolean insertNewCadre(CadreDTO cadreDTO, String userId)
			throws Exception {
		cadreDTO.setCadreId(getNewCadreId());
		boolean inserted = hrPayrollDAO.insertNewCadre(cadreDTO, userId);
		return inserted;
	}

	/**
	 * Method : getNewCadreId Description : Gets a new Cadre ID generated from
	 * DB Seqeunce
	 * 
	 * @param
	 * @return cadreId : String
	 * @throws :
	 *             Exception
	 */
	public String getNewCadreId() throws Exception {
		String cadreId = hrPayrollDAO.getNewCadreId();
		return cadreId;
	}

	/**
	 * Method : editCadres Description : Edits cadres
	 * 
	 * @param cadreDTO :
	 *            CadreDTO
	 * @param String :
	 *            userId
	 * @return edited : boolean
	 * @throws :
	 *             Exception
	 */
	public boolean editCadres(CadreDTO cadreDTO, String userId)
			throws Exception {
		String chkCadre[] = cadreDTO.getChkCadre();
		String editCadreIds[] = cadreDTO.getEditCadresIds();
		StringTokenizer st = new StringTokenizer(chkCadre[0], ",");
		int indexArray[] = new int[st.countTokens()];
		String cadreIdsArray[] = new String[st.countTokens()];
		int ind = 0;
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			for (int j = 0; j < editCadreIds.length; j++) {
				// logger.info(editGradeIds[j]);
				if (s.equals(editCadreIds[j])) {
					indexArray[ind] = j;
					cadreIdsArray[ind] = s;
					ind++;
				}
			}
		}
		boolean edited = hrPayrollDAO.editCadres(cadreDTO, userId,
				cadreIdsArray, indexArray);
		return edited;
	}

	// GradeBD.java

	/**
	 * Method : displayGrades Description : Shows all the Grades available in DB
	 * 
	 * @param :
	 * @return _gradeList : ArrayList
	 * @throws :
	 *             Exception
	 */
	public ArrayList displayGrades() throws Exception {
		ArrayList gradeList = hrPayrollDAO.displayGrades();
		logger.info("Size in BD: " + gradeList.size());
		ArrayList _gradeList = new ArrayList();
		if (gradeList != null) {
			for (int i = 0; i < gradeList.size(); i++) {
				ArrayList gList = (ArrayList) gradeList.get(i);
				if (gList != null) {
					GradeDTO gradeDTO = new GradeDTO();
					gradeDTO.setGradeId((String) gList.get(0));
					gradeDTO.setGradeName((String) gList.get(1));
					gradeDTO.setMinSalSlab((String) gList.get(2));
					gradeDTO.setMaxSalSlab((String) gList.get(3));
					gradeDTO.setIncreeAmount((String) gList.get(4));
					// logger.info("->"+gradeDTO);
					_gradeList.add(gradeDTO);
				}
			}
		}
		return _gradeList;

	}

	/**
	 * Method : checkGradeNameExists Description : checks whethere Grade Name
	 * exists or not in DB
	 * 
	 * @param gradeName :
	 *            String
	 * @return found : boolean
	 * @throws :
	 *             Exception
	 */
	public boolean checkGradeNameExists(String gradeName) throws Exception {
		boolean found = hrPayrollDAO.checkGradeNameExists(gradeName);
		return found;
	}

	/**
	 * Method : insertNewGrade Description : Inserts cadreDTO object into DB
	 * 
	 * @param gradeDTO :
	 *            GradeDTO
	 * @param userId :
	 *            String
	 * @return inserted : boolean
	 * @throws :
	 *             Exception
	 */
	public boolean insertNewGrade(GradeDTO gradeDTO, String userId)
			throws Exception {
		gradeDTO.setGradeId(getNewGradeId());
		boolean inserted = hrPayrollDAO.insertNewGrade(gradeDTO, userId);
		return inserted;
	}

	/**
	 * Method : getNewGradeId Description : Gets a new Cadre ID generated from
	 * DB Seqeunce
	 * 
	 * @param :
	 * @return _gradeId : String
	 * @throws :
	 *             Exception
	 */
	public String getNewGradeId() throws Exception {
		String _gradeId = hrPayrollDAO.getNewGradeId();
		return _gradeId;
	}

	/**
	 * Method : editGrades Description : Deletes the gradeIds in DB[Setting
	 * status A(Active) to D(De-Active)]
	 * 
	 * @param gradeDTO :
	 *            GradeDTO
	 * @return edited : boolean
	 * @throws :
	 *             Exception
	 */
	public boolean editGrades(GradeDTO gradeDTO, String userId)
			throws Exception {
		String chkGrade[] = gradeDTO.getChkGrade();
		String editGradeIds[] = gradeDTO.getEditGradeIds();
		StringTokenizer st = new StringTokenizer(chkGrade[0], ",");
		int indexArray[] = new int[st.countTokens()];
		String gradeIdsArray[] = new String[st.countTokens()];
		int ind = 0;
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			for (int j = 0; j < editGradeIds.length; j++) {
				// logger.info(editGradeIds[j]);
				if (s.equals(editGradeIds[j])) {
					indexArray[ind] = j;
					gradeIdsArray[ind] = s;
					ind++;
				}
			}
		}

		boolean edited = hrPayrollDAO.editGrades(gradeDTO, userId,
				gradeIdsArray, indexArray);
		return edited;
	}

	// GradeCadreBD.java
	public ArrayList getGradesList() throws Exception {
		ArrayList gradelist = hrPayrollDAO.getGradeList();

		ArrayList gradeList = new ArrayList();
		if (gradelist != null) {
			for (int i = 0; i < gradelist.size(); i++) {
				ArrayList cnttlist = (ArrayList) gradelist.get(i);
				if (cnttlist != null) {
					GradeCadreDTO gcdto = new GradeCadreDTO();
					gcdto.setGradeId((String) cnttlist.get(0));
					gcdto.setGradeName((String) cnttlist.get(1));
					gcdto.setGradeList(cnttlist);
					gradeList.add(gcdto);
					// logger.info(gradeList);
				}
			}
		}
		gradeList.trimToSize();
		return gradeList;
	}

	/**
	 * Method : getAllCadres Description : Shows all the Cadres
	 * 
	 * @param :
	 * @return _cadreList : ArrayList
	 * @throws :
	 *             Exception
	 */
	public ArrayList getAllCadres() throws Exception {
		ArrayList cadreList = hrPayrollDAO.getAllCadres();
		ArrayList _cadreList = new ArrayList();
		logger.info("Size in BD: " + cadreList.size());
		if (cadreList != null) {
			for (int i = 0; i < cadreList.size(); i++) {
				ArrayList cadList = (ArrayList) cadreList.get(i);
				if (cadList != null) {
					CadreDTO cadreDTO = new CadreDTO();
					cadreDTO.setCadreId((String) cadList.get(0));
					cadreDTO.setCadreName((String) cadList.get(1));
					cadreDTO.setCadrePosts((String) cadList.get(2));
					// logger.info("->"+cadreDTO);
					_cadreList.add(cadreDTO);
				}
			}
		}
		return _cadreList;
	}

	/**
	 * @param gcDTO
	 * @return
	 * @throws Exception
	 */
	public boolean saveGradeCadreMapping(GradeCadreDTO gcDTO) throws Exception {
		boolean saved = hrPayrollDAO.saveGradeCadreMapping(gcDTO);
		return saved;
	}

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSelectedCadres(String gradeId) throws Exception {
		ArrayList cadreList = hrPayrollDAO.getSelectedCadres(gradeId);
		ArrayList _cadreList = new ArrayList();
		logger.info("Size in BD: " + cadreList.size());
		if (cadreList != null) {
			for (int i = 0; i < cadreList.size(); i++) {
				ArrayList cadList = (ArrayList) cadreList.get(i);
				if (cadList != null) {
					GradeCadreDTO cadreDTO = new GradeCadreDTO();
					cadreDTO.setCadreId((String) cadList.get(0));
					cadreDTO.setCadreName((String) cadList.get(1));
					// logger.info("->"+cadreDTO);
					_cadreList.add(cadreDTO);
				}
			}
		}
		return _cadreList;
	}

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAvailableCadres(String gradeId) throws Exception {
		ArrayList cadreList = hrPayrollDAO.getAvailableCadres(gradeId);
		ArrayList _cadreList = new ArrayList();
		logger.info("Size in BD: " + cadreList.size());
		if (cadreList != null) {
			for (int i = 0; i < cadreList.size(); i++) {
				ArrayList cadList = (ArrayList) cadreList.get(i);
				if (cadList != null) {
					GradeCadreDTO cadreDTO = new GradeCadreDTO();
					cadreDTO.setCadreId((String) cadList.get(0));
					cadreDTO.setCadreName((String) cadList.get(1));
					// logger.info("->"+cadreDTO);
					_cadreList.add(cadreDTO);
				}
			}
		}
		return _cadreList;
	}

	/*
	 * public ArrayList displayAvailableCadresList() throws Exception {
	 * ArrayList cadreList = hrPayrollDAO.displayAvailableCadresList();
	 * ArrayList _cadreList = new ArrayList(); logger.info("Size in BD:
	 * "+cadreList.size()); if(cadreList != null) { for(int i=0;i<cadreList.size();i++) {
	 * ArrayList cadList=(ArrayList)cadreList.get(i); if(cadList!=null) {
	 * GradeCadreDTO gcdto=new GradeCadreDTO();
	 * gcdto.setCadreId((String)cadList.get(0));
	 * gcdto.setCadreName((String)cadList.get(1));
	 * //logger.info("->"+cadreDTO); _cadreList.add(gcdto); } } } return
	 * _cadreList; }
	 */

	// LeaveBD.java
	/**
	 * Method : checkLeaveTypeExists Description : Checks if the same Leave Type
	 * already exists in DB return true else false
	 * 
	 * @param leaveType :
	 *            String
	 * @return found : boolean
	 * @throws :
	 *             Exception
	 */
	public boolean checkLeaveTypeExists(String leaveType) throws Exception {
		boolean found = hrPayrollDAO.checkLeaveTypeExists(leaveType);
		return found;
	}

	/**
	 * Method : displayLeave Description : Gets All LeaveTypes from DB in
	 * ArrayList type
	 * 
	 * @param :
	 * @return leaveList : ArrayList
	 * @throws :
	 *             Exception
	 */
	public ArrayList displayLeave() throws Exception {
		ArrayList leaveList = hrPayrollDAO.getLeave();
		// logger.info("Inside bd");
		ArrayList leavList = new ArrayList();
		if (leaveList != null) {
			for (int i = 0; i < leaveList.size(); i++) {
				ArrayList leavvList = (ArrayList) leaveList.get(i);
				if (leavvList != null) {
					LeaveDTO leaveDTO = new LeaveDTO();
					leaveDTO.setLeave_type_id((String) leavvList.get(0));
					leaveDTO.setLeave_type_name((String) leavvList.get(1));
					leaveDTO.setGender((String) leavvList.get(2));
					leaveDTO.setMaximum_no_days((String) leavvList.get(3));
					leaveDTO.setLeave_type_desc((String) leavvList.get(4));
					leavList.add(leaveDTO);
				}// if
			}// for
		}// if
		return leavList;
	}

	/**
	 * Method : insertNewLeave Description : Inserts Leave Type Record into DB
	 * 
	 * @param leaveDTO :
	 *            LeaveDTO
	 * @param userId :
	 *            String
	 * @return inserted : boolean
	 * @throws :
	 *             Exception
	 */
	public boolean insertNewLeave(LeaveDTO leaveDTO, String userId)
			throws Exception {
		leaveDTO.setLeave_type_id(getNewLeaveId());
		boolean inserted = hrPayrollDAO.insertNewLeave(leaveDTO, userId);
		return inserted;
	}

	/**
	 * Method : editLeaveMaster Description : edits Leave Type Record in DB
	 * 
	 * @param leaveDTO :
	 *            LeaveDTO
	 * @return edited : boolean
	 * @throws :
	 *             Exception
	 */
	public boolean editLeaveMaster(LeaveDTO leaveDTO, String userId)
			throws Exception {
		String chkLeave[] = leaveDTO.getChkLeave();
		String editLeaveIds[] = leaveDTO.getEditLeaveIds();

		StringTokenizer st = new StringTokenizer(chkLeave[0], ",");
		int indexArray[] = new int[st.countTokens()];
		String leaveIdsArray[] = new String[st.countTokens()];
		int ind = 0;
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			for (int j = 0; j < editLeaveIds.length; j++) {
				logger.info(editLeaveIds[j]);
				if (s.equals(editLeaveIds[j])) {
					indexArray[ind] = j;
					leaveIdsArray[ind] = s;
					ind++;
				}
			}
		}

		boolean edited = hrPayrollDAO.editLeaveMaster(leaveDTO, userId,
				leaveIdsArray, indexArray);
		return edited;
	}

	/**
	 * Method : getNewLeaveId Description : Fetches New Leave Type ID genereated
	 * form DB Sequence
	 * 
	 * @param :
	 * @return leaveGenSeq : String
	 * @throws :
	 *             Exception
	 */
	public String getNewLeaveId() throws Exception {
		String leaveGenSeq = hrPayrollDAO.getNewLeaveId();
		return leaveGenSeq;
	}

	// SalaryBD.java

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public String getGradeName(String gradeId) throws Exception {
		String gradeName = hrPayrollDAO.getGradeName(gradeId);
		return gradeName;
	}

	/**
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public String getCadreName(String cadreId) throws Exception {
		String cadreName = hrPayrollDAO.getCadreName(cadreId);
		return cadreName;
	}

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList fetchCadresForGrade(String gradeId) throws Exception {
		logger.info("in fetchCadresforGrade(String gradeId())");
		ArrayList cadreList = hrPayrollDAO.fetchCadresForGrade(gradeId);
		logger.info("Size in BD: " + cadreList.size());
		ArrayList _cadreList = new ArrayList();
		if (cadreList != null) {
			for (int i = 0; i < cadreList.size(); i++) {
				ArrayList cList = (ArrayList) cadreList.get(i);
				if (cList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setCadreId((String) cList.get(0));
					salaryDTO.setCadreName((String) cList.get(1));
					_cadreList.add(salaryDTO);
				}
			}
		}
		return _cadreList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList fetchGrades() throws Exception {
		logger.info("in fetchGrades");
		ArrayList gradeList = hrPayrollDAO.fetchGrades();
		logger.info("Size in BD: " + gradeList.size());
		ArrayList _gradeList = new ArrayList();
		if (gradeList != null) {
			for (int i = 0; i < gradeList.size(); i++) {
				ArrayList gList = (ArrayList) gradeList.get(i);
				if (gList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setGradeId((String) gList.get(0));
					salaryDTO.setGradeName((String) gList.get(1));
					_gradeList.add(salaryDTO);
				}
			}
		}
		return _gradeList;
	}

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList displayGradeDetails(String gradeId) throws Exception {
		logger.info("in displayGradeDetails(gradeId)");
		ArrayList gradeList = hrPayrollDAO.displayGradeDetails(gradeId);
		logger.info("Size in BD: " + gradeList.size());
		ArrayList _gradeList = new ArrayList();
		
		if (gradeList != null && gradeList.size()>0) {
			ArrayList gList = (ArrayList) gradeList.get(0);
			if (gList != null) {
				SalaryDTO salaryDTO = new SalaryDTO();
				salaryDTO.setGradeId((String) gList.get(0));
				salaryDTO.setGradeName((String) gList.get(1));
				salaryDTO.setMinSalSlab((String) gList.get(2));
				salaryDTO.setMaxSalSlab((String) gList.get(3));
				salaryDTO.setIncreeAmount((String) gList.get(4));
				// logger.info("->"+gradeDTO);
				_gradeList.add(salaryDTO);
			}

		}
		return _gradeList;
	}

	// ---- PAYMENTS
	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public ArrayList fetchPaymentDetails(String gradeId, String cadreId)
			throws Exception {
		logger.info("in fetchPaymentDetails(gradeId, cadreId)");
		ArrayList payList = hrPayrollDAO.fetchPaymentDetails(gradeId, cadreId);
		logger.info("Size in BD: " + payList.size());
		ArrayList _payList = new ArrayList();
		if (payList != null) {
			for (int i = 0; i < payList.size(); i++) {
				ArrayList pList = (ArrayList) payList.get(i);
				if (pList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setComponentId((String) pList.get(0));
					salaryDTO.setComponentName((String) pList.get(1));
					//salaryDTO.setComponentValue((String) pList.get(2));
					_payList.add(salaryDTO);
				}
			}
		}
		return _payList;
	}

	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public String getPaymentsTotal(String gradeId, String cadreId)
			throws Exception {
		return hrPayrollDAO.getPaymentsTotal(gradeId, cadreId);
	}

	/**
	 * @param salDTO
	 * @return
	 * @throws Exception
	 */
	public boolean updatePaymentComponents(SalaryDTO salDTO) throws Exception {
		boolean flag = hrPayrollDAO.updatePaymentComponents(salDTO);
		return flag;
	}

	// /--- TREASURY
	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public ArrayList fetchTreasuryDetails(String gradeId, String cadreId)
			throws Exception {
		logger.info("in fetchPaymentDetails(gradeId, cadreId)");
		ArrayList payList = hrPayrollDAO.fetchTreasuryDetails(gradeId, cadreId);
		logger.info("Size in BD: " + payList.size());
		ArrayList _payList = new ArrayList();
		if (payList != null) {
			for (int i = 0; i < payList.size(); i++) {
				ArrayList pList = (ArrayList) payList.get(i);
				if (pList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setComponentId((String) pList.get(0));
					salaryDTO.setComponentName((String) pList.get(1));
					//salaryDTO.setComponentValue((String) pList.get(2));
					_payList.add(salaryDTO);
				}
			}
		}
		return _payList;
	}

	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public String getTreasuryTotal(String gradeId, String cadreId)
			throws Exception {
		return hrPayrollDAO.getTreasuryTotal(gradeId, cadreId);
	}

	/**
	 * @param salDTO
	 * @return
	 * @throws Exception
	 */
	public boolean updateTreasuryComponents(SalaryDTO salDTO) throws Exception {
		boolean flag = hrPayrollDAO.updateTreasuryComponents(salDTO);
		return flag;
	}

	// --- DEDUCTION
	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public ArrayList fetchDeductionDetails(String gradeId, String cadreId)
			throws Exception {
		logger.info("in fetchPaymentDetails(gradeId, cadreId)");
		ArrayList payList = hrPayrollDAO
				.fetchDeductionDetails(gradeId, cadreId);
		logger.info("Size in BD: " + payList.size());
		ArrayList _payList = new ArrayList();
		if (payList != null) {
			for (int i = 0; i < payList.size(); i++) {
				ArrayList pList = (ArrayList) payList.get(i);
				if (pList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setComponentId((String) pList.get(0));
					salaryDTO.setComponentName((String) pList.get(1));
				//	salaryDTO.setComponentValue((String) pList.get(2));
					_payList.add(salaryDTO);
				}
			}
		}
		return _payList;
	}

	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public String getDeductionTotal(String gradeId, String cadreId)
			throws Exception {
		return hrPayrollDAO.getDeductionTotal(gradeId, cadreId);
	}

	/**
	 * @param salDTO
	 * @return
	 * @throws Exception
	 */
	public boolean updateDeductionComponents(SalaryDTO salDTO) throws Exception {
		boolean flag = hrPayrollDAO.updateDeductionComponents(salDTO);
		return flag;
	}

	// SALARY COMPONENT MAPPING WITH GRADE & CADRE

	/**
	 * @param gradeId
	 * @param cadreId
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSelectedSalaryComponents(String gradeId,
			String cadreId, String typeId) throws Exception {
		System.out
				.println("in getSelectedSalaryComponents(gradeId, cadreId,typeId)");
		ArrayList payList = hrPayrollDAO.getSelectedSalaryComponents(gradeId,
				cadreId, typeId);
		
		ArrayList _payList = new ArrayList();
		if (payList != null) {
			for (int i = 0; i < payList.size(); i++) {
				ArrayList pList = (ArrayList) payList.get(i);
				if (pList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setComponentId((String) pList.get(0));
					salaryDTO.setComponentName((String) pList.get(1));
					_payList.add(salaryDTO);
				}
			}
		}
		return _payList;
	}

	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAvailableSalaryComponents(String gradeId, String cadreId)
			throws Exception // ,String typeId
	{
		System.out
				.println("in getAvaliableSalaryComponents(gradeId, cadreId,typeId)");
		ArrayList payList = hrPayrollDAO.getAvailableSalaryComponents(gradeId,
				cadreId); // ,typeId
		
		ArrayList _payList = new ArrayList();
		if (payList != null) {
			for (int i = 0; i < payList.size(); i++) {
				ArrayList pList = (ArrayList) payList.get(i);
				if (pList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setComponentId((String) pList.get(0));
					salaryDTO.setComponentName((String) pList.get(1));
					_payList.add(salaryDTO);
				}
			}
		}
		return _payList;
	}

	/**
	 * @param salaryDTO
	 * @return
	 * @throws Exception
	 */
	public boolean saveGradeCadreSalaryComponentMapping(SalaryDTO salaryDTO,String empID)
			throws Exception {

		StringTokenizer st = new StringTokenizer(salaryDTO.getComponentId(),
				",");
		String arr[] = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			arr[i] = st.nextToken();
			System.out.println("Components Values For Mapping"+arr[i]);
			i++;
		}

		salaryDTO.setComponentIdArr(arr);

		/*
		 * StringTokenizer slecom =new
		 * StringTokenizer(salaryDTO.getSelectedComponentId(),","); String
		 * arrselcom[] = new String[slecom.countTokens()]; int j=0;
		 * while(slecom.hasMoreTokens()) { arrselcom[j] =
		 * (String)slecom.nextToken();
		 * System.out.println("--------------OldElements----------"+arrselcom[j]);
		 * j++; }
		 */
		boolean saved = hrPayrollDAO
				.saveGradeCadreSalaryComponentMapping(salaryDTO,empID);
		return saved;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String getNewSalaryTxnId() throws Exception {

		String salaryTxnId = hrPayrollDAO.getNewSalaryTxnId();
		return salaryTxnId;
	}

	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPaymentComponentsForDisplay(String gradeId,
			String cadreId,String type) throws Exception {
		System.out
				.println("in fetchPaymentComponentsForDisplay(gradeId, cadreId)");
		ArrayList payList = hrPayrollDAO.fetchPaymentComponentsForDisplay(
				gradeId, cadreId,type);
		
		ArrayList _payList = new ArrayList();
		if (payList != null) {
			for (int i = 0; i < payList.size(); i++) {
				ArrayList pList = (ArrayList) payList.get(i);
				if (pList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setComponentId((String) pList.get(0));
					salaryDTO.setComponentName((String) pList.get(1));
					//salaryDTO.setComponentValue((String) pList.get(2));
					_payList.add(salaryDTO);
				}
			}
		}
		return _payList;
	}

	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAgDeductionComponentsForDisplay(String gradeId,
			String cadreId,String type) throws Exception {
		System.out
				.println("in fetchAgDeductionComponentsForDisplay(gradeId, cadreId)");
		ArrayList payList = hrPayrollDAO.fetchAgDeductionComponentsForDisplay(
				gradeId, cadreId,type);
		
		ArrayList _payList = new ArrayList();
		if (payList != null) {
			for (int i = 0; i < payList.size(); i++) {
				ArrayList pList = (ArrayList) payList.get(i);
				if (pList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setComponentId((String) pList.get(0));
					salaryDTO.setComponentName((String) pList.get(1));
					//salaryDTO.setComponentValue((String) pList.get(2));
					_payList.add(salaryDTO);
				}
			}
		}
		return _payList;
	}

	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getTreasuryComponentsForDisplay(String gradeId,
			String cadreId,String type) throws Exception {
		System.out
				.println("in fetchTreasuryComponentsForDisplay(gradeId, cadreId)");
		ArrayList payList = hrPayrollDAO.fetchTreasuryComponentsForDisplay(
				gradeId, cadreId,type);
		
		ArrayList _payList = new ArrayList();
		if (payList != null) {
			for (int i = 0; i < payList.size(); i++) {
				ArrayList pList = (ArrayList) payList.get(i);
				if (pList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setComponentId((String) pList.get(0));
					salaryDTO.setComponentName((String) pList.get(1));
					//salaryDTO.setComponentValue((String) pList.get(2));
					_payList.add(salaryDTO);
				}
			}
		}
		return _payList;
	}

	// EMPLOYEE GRADE CADRE SALARY MAPPING
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList fetchEmployees() throws Exception {
		
		ArrayList employeeList = hrPayrollDAO.fetchEmployees();
		
		ArrayList _employeeList = new ArrayList();
		if (employeeList != null) {
			for (int i = 0; i < employeeList.size(); i++) {
				ArrayList eList = (ArrayList) employeeList.get(i);
				if (eList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setEmpId((String) eList.get(0));
					_employeeList.add(salaryDTO);
				}
			}
		}
		return _employeeList;
	}

	/**
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public ArrayList fetchCadresGradeForEmp(String empId) throws Exception {
		
		ArrayList cadreList = hrPayrollDAO.fetchCadresGradeForEmp(empId);
		
		ArrayList _cadreList = new ArrayList();
		if (cadreList != null) {
			for (int i = 0; i < cadreList.size(); i++) {
				ArrayList cList = (ArrayList) cadreList.get(i);
				if (cList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setGradeName((String) cList.get(0));
					salaryDTO.setCadreName((String) cList.get(1));
					salaryDTO.setGradeId((String) cList.get(2));
					salaryDTO.setCadreId((String) cList.get(3));
					_cadreList.add(salaryDTO);
				}
			}
		}
		return _cadreList;
	}

	/**
	 * @param gradeId
	 * @param cadreId
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList fetchSalaryComponents(String gradeId, String cadreId,
			String typeId) throws Exception {
		
		ArrayList payList = hrPayrollDAO.fetchSalaryComponents(gradeId,
				cadreId, typeId);
		
		ArrayList _payList = new ArrayList();
		if (payList != null) {
			for (int i = 0; i < payList.size(); i++) {
				ArrayList pList = (ArrayList) payList.get(i);
				if (pList != null) {
					SalaryDTO salaryDTO = new SalaryDTO();
					salaryDTO.setComponentId((String) pList.get(0));
					salaryDTO.setComponentName((String) pList.get(1));
					salaryDTO.setComponentValue((String) pList.get(2));
					_payList.add(salaryDTO);
				}
			}
		}
		return _payList;
	}

	/**
	 * @param salDTO
	 * @return
	 * @throws Exception
	 */
	public boolean insertSalaryComponentAmount(SalaryDTO salDTO)
			throws Exception {
		boolean inserted = hrPayrollDAO.insertSalaryComponentAmount(salDTO);
		return inserted;

	}

}