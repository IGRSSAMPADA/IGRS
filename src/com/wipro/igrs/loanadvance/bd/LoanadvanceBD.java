/*
 * LoanadvanceBD.java
 */


package com.wipro.igrs.loanadvance.bd;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.loanadvance.dao.LoanadvanceDAO;
import com.wipro.igrs.loanadvance.dto.LoanadvanceDTO;


/**
 * @author jagadish
 *Jun 26, 2008
 * 
 */
public class LoanadvanceBD {

	private LoanadvanceDAO	loanadvanceDAO	= new LoanadvanceDAO();
	private Logger logger = (Logger) Logger.getLogger(LoanadvanceBD.class);
	/**
	 * @return ArrayList
	 */
	public ArrayList retrieveAdvanceMasterDetails() {
		ArrayList _advanceMasterList = null;
		try {

			ArrayList advanceMasterList = loanadvanceDAO
					.retrieveAdvanceMasterDetails();
			_advanceMasterList = new ArrayList();
			logger.info("Size in BD: " + advanceMasterList.size());
			if (advanceMasterList != null) {
				for (int i = 0; i < advanceMasterList.size(); i++) {
					ArrayList avdList = (ArrayList) advanceMasterList.get(i);
					if (avdList != null) {
						LoanadvanceDTO loanadvanceDTO = new LoanadvanceDTO();
						loanadvanceDTO
								.setAdvancetypeid((String) avdList.get(0));
						loanadvanceDTO.setAdvancetypename((String) avdList
								.get(1));
						loanadvanceDTO
								.setAdvanceamount((String) avdList.get(2));
						loanadvanceDTO
								.setInstallmentno((String) avdList.get(3));
						loanadvanceDTO.setInterestRate((String) avdList.get(4));

						_advanceMasterList.add(loanadvanceDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _advanceMasterList;

	}

	/**
	 * @param advancetypeid
	 * @return ArrayList
	 */
	public LoanadvanceDTO getDetailsOfAdvanceType(String advancetypeid) {
		ArrayList list = new ArrayList();
		LoanadvanceDTO loanadvanceDTO = new LoanadvanceDTO();
		String[] sqlValues = new String[1];
		sqlValues[0] = advancetypeid;

		try {
			LoanadvanceDAO loanadvanceDAO = new LoanadvanceDAO();
			list = loanadvanceDAO.getDetailsOfAdvanceType(sqlValues);
			ArrayList rowList = (ArrayList) list.get(0);
			loanadvanceDTO.setAdvancetypeid((String) rowList.get(0));
			loanadvanceDTO.setAdvancetypename((String) rowList.get(1));
			loanadvanceDTO.setAdvanceamount((String) rowList.get(2));
			loanadvanceDTO.setInstallmentno((String) rowList.get(3));
			loanadvanceDTO.setAdvancedescription((String) rowList.get(4));

		} catch (Exception e) {
			e.printStackTrace();

		}

		return loanadvanceDTO;

	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getAdvanceTypeList() {
		ArrayList _advanceMasterList = null;
		try {

			ArrayList advanceMasterList = loanadvanceDAO.getAdvanceTypeList();
			_advanceMasterList = new ArrayList();
			logger.info("Size in BD: " + advanceMasterList.size());
			if (advanceMasterList != null) {
				for (int i = 0; i < advanceMasterList.size(); i++) {
					ArrayList avdList = (ArrayList) advanceMasterList.get(i);
					if (avdList != null) {
						LoanadvanceDTO loanadvanceDTO = new LoanadvanceDTO();
						loanadvanceDTO
								.setAdvancetypeid((String) avdList.get(0));
						loanadvanceDTO.setAdvancetypename((String) avdList
								.get(1));
						loanadvanceDTO
								.setAdvanceamount((String) avdList.get(2));

						_advanceMasterList.add(loanadvanceDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _advanceMasterList;

	}

	/**
	 * @param loanadvanceDTO
	 * @param userid
	 * @return boolean
	 */
	public boolean insertAdvanceMasterDetails(LoanadvanceDTO loanadvanceDTO,
			String userid) {
		boolean insert = false;
		try {
			insert = loanadvanceDAO.insertAdvanceMasterDetails(loanadvanceDTO,
					userid);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return insert;
	}

	/**
	 * @param loanadvanceDTO
	 * @param userid
	 * @return String
	 */
	public String insertAppliedAdvance(LoanadvanceDTO loanadvanceDTO,
			String userid) {
		String insert = "";
		try {
			insert = loanadvanceDAO
					.insertAppliedAdvance(loanadvanceDTO, userid);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return insert;
	}

	/**
	 * @param empId
	 * @return ArrayList
	 */
	public ArrayList getPendingAdvanceList(String empId) {

		ArrayList _advanceMasterList = null;
		try {

			ArrayList advanceMasterList = loanadvanceDAO
					.getPendingAdvanceList(empId);
			_advanceMasterList = new ArrayList();
			logger.info("Size in BD: " + advanceMasterList.size());
			if (advanceMasterList != null) {
				for (int i = 0; i < advanceMasterList.size(); i++) {
					ArrayList avdList = (ArrayList) advanceMasterList.get(i);
					if (avdList != null) {
						LoanadvanceDTO advanceDTO = new LoanadvanceDTO();
						advanceDTO.setAdvancetypeid((String) avdList.get(0));
						advanceDTO.setAdvanceamount((String) avdList.get(1));
						advanceDTO.setAdvancestatus((String) avdList.get(2));

						_advanceMasterList.add(advanceDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _advanceMasterList;
	}

	/**
	 * @param advancetypeid
	 * @return ArrayList
	 */
	public ArrayList getAdvanceTxnDetails(String advancetypeid) {
		ArrayList _advanceMasterList = null;
		try {

			ArrayList advanceMasterList = loanadvanceDAO
					.getAdvanceTxnDetails(advancetypeid);
			_advanceMasterList = new ArrayList();
			logger.info("Size in BD: " + advanceMasterList.size());
			if (advanceMasterList != null) {
				for (int i = 0; i < advanceMasterList.size(); i++) {
					ArrayList avdList = (ArrayList) advanceMasterList.get(i);
					if (avdList != null) {
						LoanadvanceDTO advanceDTO = new LoanadvanceDTO();
						advanceDTO.setEmpid((String) avdList.get(0));
						advanceDTO.setAdvancetypeid((String) avdList.get(1));
						advanceDTO.setAdvanceamount((String) avdList.get(2));
						advanceDTO.setAdvancedescription((String) avdList
								.get(3));
						advanceDTO.setAdvancetxnid((String) avdList.get(4));

						_advanceMasterList.add(advanceDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _advanceMasterList;
	}

	/**
	 * @param advancetypeid
	 * @return ArrayList
	 */
	public ArrayList getAdvanceTypeList(String advancetypeid) {
		ArrayList _advanceMasterList = null;
		try {

			ArrayList advanceMasterList = loanadvanceDAO
					.getAdvanceTypeList(advancetypeid);
			_advanceMasterList = new ArrayList();
			logger.info("Size in BD: " + advanceMasterList.size());
			if (advanceMasterList != null) {
				for (int i = 0; i < advanceMasterList.size(); i++) {
					ArrayList avdList = (ArrayList) advanceMasterList.get(i);
					if (avdList != null) {
						LoanadvanceDTO advanceDTO = new LoanadvanceDTO();
						advanceDTO.setAdvancetypename((String) avdList.get(0));

						_advanceMasterList.add(advanceDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _advanceMasterList;
	}

	/**
	 * @param list
	 * @param loanadvanceDTO
	 * @param userid
	 * @return boolean
	 */
	public boolean getAdvanceApproved(ArrayList list,
			LoanadvanceDTO loanadvanceDTO, String userid) {
		boolean update = false;
		try {

			update = loanadvanceDAO.getAdvanceApproved(list, loanadvanceDTO,
					userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	/**
	 * @param componentId
	 * @return ArrayList
	 */
	public ArrayList getAdvanceMasterComponentsList(String componentId) {
		ArrayList _advanceMasterList = null;
		try {

			ArrayList advanceMasterList = loanadvanceDAO
					.getAdvanceMasterComponentsList(componentId);
			_advanceMasterList = new ArrayList();
			logger.info("Size in BD: " + advanceMasterList.size());
			if (advanceMasterList != null) {
				for (int i = 0; i < advanceMasterList.size(); i++) {
					ArrayList avdList = (ArrayList) advanceMasterList.get(i);
					if (avdList != null) {
						LoanadvanceDTO advanceDTO = new LoanadvanceDTO();

						advanceDTO.setAdvancetypename((String) avdList.get(0));
						advanceDTO.setAdvanceamount((String) avdList.get(1));
						advanceDTO.setInstallmentno((String) avdList.get(2));
						advanceDTO.setAdvancedescription((String) avdList.get(3));
						advanceDTO.setAdvancetypeid((String) avdList.get(4));
						advanceDTO.setInterestRate((String) avdList.get(5));
						advanceDTO.setEmpClass((String) avdList.get(6));
						advanceDTO.setSalaryLimitation((String) avdList.get(7));
						advanceDTO.setCondition((String) avdList.get(8));
						_advanceMasterList.add(advanceDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _advanceMasterList;
	}

	/**
	 * @param advanceTxnId
	 * @return ArrayList
	 */
	public ArrayList getAdvanceDetails(String advanceTxnId) {
		ArrayList _advanceMasterList = null;
		try {

			ArrayList advanceMasterList = loanadvanceDAO
					.getAdvanceDetails(advanceTxnId);
			_advanceMasterList = new ArrayList();
			logger.info("Size in BD: " + advanceMasterList.size());
			if (advanceMasterList != null) {
				for (int i = 0; i < advanceMasterList.size(); i++) {
					ArrayList avdList = (ArrayList) advanceMasterList.get(i);
					if (avdList != null) {
						LoanadvanceDTO advanceDTO = new LoanadvanceDTO();
						advanceDTO.setEmpid((String) avdList.get(0));
						advanceDTO.setAdvancetypeid((String) avdList.get(1));
						advanceDTO.setAdvancestatus((String) avdList.get(2));
						advanceDTO.setRemarks((String) avdList.get(3));
						advanceDTO.setAdvanceamount((String) avdList.get(4));
						advanceDTO.setCreatedDate((String) avdList.get(5));
						advanceDTO.setAdvancedescription((String) avdList
								.get(6));

						_advanceMasterList.add(advanceDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _advanceMasterList;
	}

	/**
	 * @param advanceTxnId
	 * @return ArrayList
	 */
	public ArrayList callingProcedure(String advanceTxnId) {
		ArrayList _advanceMasterList = null;
		try {

			ArrayList advanceMasterList = loanadvanceDAO
					.callingProcedure(advanceTxnId);
			_advanceMasterList = new ArrayList();

			if (advanceMasterList != null) {
				LoanadvanceDTO advanceDTO = null;
				for (int i = 0; i < advanceMasterList.size(); i++) {

					advanceDTO = new LoanadvanceDTO();
					advanceDTO.setPaidInsallments((String) advanceMasterList
							.get(0));
					advanceDTO
							.setPaidAmounts((String) advanceMasterList.get(1));
					advanceDTO
							.setPendingInstallments((String) advanceMasterList
									.get(2));
					advanceDTO.setPendingAmounts((String) advanceMasterList
							.get(3));

				}
				_advanceMasterList.add(advanceDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _advanceMasterList;

	}

	/**
	 * @param advanceName
	 * @return boolean
	 */
	public boolean checkAdvanceName(String advanceName) {
		boolean found = false;
		try {
			found = loanadvanceDAO.checkAdvanceName(advanceName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return found;
	}

	/**
	 * @param loanadvanceDTO
	 * @param list
	 * @param userId
	 * @return boolean
	 */
	public boolean updateAdvanceMasterDetails(LoanadvanceDTO loanadvanceDTO,
			ArrayList list, String userId) {
		boolean update = false;
		try {
			update = loanadvanceDAO.updateAdvanceMasterDetails(loanadvanceDTO,
					list, userId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return update;

	}

	/**
	 * @param advancetypename
	 * @return ArrayList
	 */
	public ArrayList fetchAdvanceAmount(String advancetypename) {
		ArrayList _advanceMasterList = null;
		try {

			ArrayList advanceMasterList = loanadvanceDAO
					.fetchAdvanceAmount(advancetypename);

			_advanceMasterList = new ArrayList();

			if (advanceMasterList != null) {

				for (int i = 0; i < advanceMasterList.size(); i++) {
					ArrayList avdList = (ArrayList) advanceMasterList.get(i);
					if (avdList != null) {
						LoanadvanceDTO advanceDTO = new LoanadvanceDTO();
						advanceDTO.setAdvAmount((String) avdList.get(0));
						_advanceMasterList.add(advanceDTO);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _advanceMasterList;
	}

	/**
	 * @param advancetypename
	 * @return String
	 */
	public String checkAdvanceAmount(String advancetypename) {
		String amount = "";
		try {

			amount = loanadvanceDAO.cheakAdvanceAmount(advancetypename);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}

	/**
	 * @param empid
	 * @return boolean
	 */
	public boolean checkEmpId(String empid) {
		boolean result = false;
		try {
			result = loanadvanceDAO.checkEmpId(empid);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	/**
	 * @param refId
	 * @return boolean
	 */
	public boolean checkRefId(String refId) {
		boolean result = false;
		try {
			result = loanadvanceDAO.checkRefId(refId);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

}
