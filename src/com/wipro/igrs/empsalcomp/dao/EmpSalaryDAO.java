package com.wipro.igrs.empsalcomp.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empsalcomp.dto.EmpSalaryDTO;
import com.wipro.igrs.empsalcomp.sql.EmpSalarycreateCommonSQL;

public class EmpSalaryDAO {

	private Logger logger = (Logger) Logger.getLogger(EmpSalaryDAO.class);

	public EmpSalaryDTO getEmpData(String empID) {
		DBUtility utility = null;
		EmpSalaryDTO empDTO = new EmpSalaryDTO();
		String sql = EmpSalarycreateCommonSQL.GET_EMPL_DATA;

		try {
			utility = new DBUtility();
			String[] param = new String[1];
			param[0] = empID;
			utility.createPreparedStatement(sql);
			ArrayList result = utility.executeQuery(param);

			if (result != null) {
				if (result.size() != 0) {
					ArrayList subList = (ArrayList) result.get(0);
					if (subList != null && subList.size() != 0) {
						logger.debug("inside subList:-" + result.size() + ":"
								+ subList.size());
						empDTO.setEmpFName((String) subList.get(0));
						empDTO.setEmpMName((String) subList.get(1));
						empDTO.setEmpLName((String) subList.get(2));
						empDTO.setEmpID((String) subList.get(3));
						empDTO.setMinSal((String) subList.get(4));
						empDTO.setMaxSal((String) subList.get(5));
						empDTO.setIncrementSal((String) subList.get(6));
						empDTO.setCurrentBasic((String) subList.get(7));
						empDTO.setEffectiveDate((String) subList.get(8));
						empDTO.setSlabId((String) subList.get(9));
						empDTO.setEffectiveFlag((String) subList.get(10));
						empDTO.setIncrements((String) subList.get(11));

						empDTO.setEmpNotFound("FOUND");
						return empDTO;
					}
				}

			}
			if (result == null) {
				empDTO.setEmpNotFound("NOTFOUND");
			}

		}

		catch (Exception d) {
			d.printStackTrace();
			logger.debug(d);
		} finally {

			try {
				utility.closeConnection();
			} catch (Exception d) {
				d.printStackTrace();
			}
		}

		return null;

	}

	public String getBasicFORemp(String empID) {
		DBUtility utility = null;
		String sql = EmpSalarycreateCommonSQL.IS_EMP_HAS_BASIC;

		try {
			utility = new DBUtility();
			String[] param = new String[1];
			param[0] = empID;
			utility.createPreparedStatement(sql);
			ArrayList result = utility.executeQuery(param);
			if (!result.isEmpty()) {
				ArrayList subList = (ArrayList) result.get(0);
				if (subList.isEmpty()) {
					return "";
				} else {
					return (String) subList.get(2);
				}
			}

		}

		catch (Exception d) {
			d.printStackTrace();
		} finally {
			try {
				utility.closeConnection();
			} catch (Exception d) {
				d.printStackTrace();
			}
		}
		return "";
	}

	public int getCheckEmp(String empID) {
		DBUtility utility = null;
		String sql = EmpSalarycreateCommonSQL.CHECK_EMP;
		int returnCount = 0;

		try {
			utility = new DBUtility();
			String[] param = new String[1];
			param[0] = empID;
			utility.createPreparedStatement(sql);
			String count = utility.executeQry(param);

			returnCount = count == null ? 0 : Integer.parseInt(count);
		} catch (Exception d) {
			d.printStackTrace();
		} finally {
			try {
				utility.closeConnection();
			} catch (Exception d) {
				d.printStackTrace();
			}
		}
		return returnCount;
	}

	public boolean isEmpHasBasic(String empID) {
		DBUtility utility = null;
		String sql = EmpSalarycreateCommonSQL.IS_EMP_HAS_BASIC;

		try {
			utility = new DBUtility();
			String[] param = new String[1];
			param[0] = empID;
			utility.createPreparedStatement(sql);
			ArrayList result = utility.executeQuery(param);
			logger.debug("result size :" + result.size());
			if (result.size() != 0) {

				ArrayList subList = (ArrayList) result.get(0);
				if (subList.size() == 0) {
					logger.debug("empty");
					return false;
				} else {
					logger.debug("not empty");
					return true;
				}
			}

		}

		catch (Exception d) {
			d.printStackTrace();
		} finally {
			try {
				utility.closeConnection();
			} catch (Exception d) {
				d.printStackTrace();
			}
		}
		return false;
	}

	public boolean addNewEmpSalaryBasic(EmpSalaryDTO empDTO) {
		// logger.debug("from the DAO add"+empDTO.getBasicValue());
		String sql = EmpSalarycreateCommonSQL.ADD_NEW_EMP_SALARY_BASIC;
		String[] param = new String[7];
		DBUtility utility = null;
		try {
			logger.debug("before executing");
			utility = new DBUtility();
			utility.createPreparedStatement(sql);

			param[0] = empDTO.getEmpID();
			param[1] = empDTO.getBasicValue();
			param[2] = empDTO.getEffectiveDate();			
			param[3] = empDTO.getIncrementSal();
			param[4] = empDTO.getEffectiveFlag();
			param[5] = empDTO.getSlabId();
			param[6] = empDTO.getIncrements();

			boolean add = utility.executeUpdate(param);
			if (add) {
				utility.commit();

			} else {
				utility.rollback();
			}

			logger.debug("after executing");
			return add;
		} catch (Exception g) {
			g.printStackTrace();
		}

		finally {
			try {
				utility.closeConnection();
			} catch (Exception m) {
				m.printStackTrace();
			}
		}
		return false;
	}

	public boolean updateBasicVAlue(EmpSalaryDTO empDTO, String userId) {
		logger.debug("from the DAO update" + empDTO.getBasicValue());
		String sql = EmpSalarycreateCommonSQL.UPDATE_BASIC_VALUE;
		DBUtility utility = null;
		String[] param = new String[8];

		try {
			param[0] = empDTO.getBasicValue();
			param[1] = getOracleDate(empDTO.getEffectiveDate());
			param[2] = empDTO.getEffectiveFlag();
			param[3] = empDTO.getIncrementSal();
			param[4] = userId;
			param[5] = empDTO.getSlabId();
			param[6] = empDTO.getIncrements();
			param[7] = empDTO.getEmpID();

			utility = new DBUtility();
			utility.createPreparedStatement(sql);
			boolean updated = utility.executeUpdate(param);

			if (updated) {
				utility.commit();
			} else {
				utility.rollback();
			}
			logger.debug("from the DAO updated  " + updated);
			return updated;
		} catch (Exception f) {
			f.printStackTrace();
		} finally {
			try {
				utility.closeConnection();
			} catch (Exception d) {
				d.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList getSlabList(String empId) throws Exception {
		ArrayList slabList = new ArrayList();
		ArrayList subList = new ArrayList();
		EmpSalaryDTO dto = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = EmpSalarycreateCommonSQL.GET_ALL_SLABS;
			String[] param = new String[1];
			param[0] = empId;
			dbUtility.createPreparedStatement(sql);
			ArrayList mainList1 = dbUtility.executeQuery(param);

			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new EmpSalaryDTO();
				dto.setSlabId((String) subList.get(0));
				dto.setSlab((String) subList.get(1) + "-"
						+ (String) subList.get(2) + "-"
						+ (String) subList.get(3));

				slabList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}

		// System.out.println("slabList---> "+slabList);
		return slabList;

	}

	public static String getOracleDate(String DateFormat) {
		String finalDate = "";
		if (DateFormat != null || !DateFormat.equalsIgnoreCase("")) {
			StringTokenizer st = new StringTokenizer(DateFormat, "/");
			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();
			String inputDate = day + "-" + month + "-" + year;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			Date newDate;
			try {
				newDate = formatter.parse(inputDate);
				SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
				finalDate = format.format(newDate);

			} catch (Exception e) {
				System.out.print(e);
			}

		}
		return finalDate;
	}

}
