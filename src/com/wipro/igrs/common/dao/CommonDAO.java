package com.wipro.igrs.common.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.dto.CommonDTO;
import com.wipro.igrs.common.form.CommonForm;
import com.wipro.igrs.common.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;

public class CommonDAO {

	private DBUtility dbUtil;
	private static Logger logger = org.apache.log4j.Logger.getLogger(CommonDAO.class);
	public CommonDAO() throws Exception {
		dbUtil = new DBUtility();

	}
	public ArrayList getplotPropertyDetails(String language, String id) {

		ArrayList details = new ArrayList();

		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_PLOT_EN_VIEW_DETAILS);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.GET_PLOT_HI_VIEW_DETAILS);
				}
				details = dbUtil.executeQuery(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return details;

	}
	public ArrayList getallSubClauseList(String language, String id, CommonDTO commonDTO1) {
		ArrayList details = new ArrayList();

		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_EN_SUB_CLAUSES_LIST);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.GET_HI_SUB_CLAUSES_LIST);
				}
				details = dbUtil.executeQuery(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}
	public ArrayList getallSubClauseListBuildAgri(String language, String id, CommonDTO commonDTO1) {
		ArrayList details = new ArrayList();

		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_EN_SUB_CLAUSES_LIST_BUILD);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.GET_HI_SUB_CLAUSES_LIST_BUILD);
				}
				details = dbUtil.executeQuery(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}
	public ArrayList getagriAreaDetailsList(String language, String id, CommonForm commonForm) {
		ArrayList details = new ArrayList();

		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_AGRI_EN_VIEW_AREA_DETAILS);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.GET_AGRI_HI_VIEW_AREA_DETAILS);
				}
				details = dbUtil.executeQuery(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}
	public ArrayList getagriPropDetailsList(String language, String id, CommonDTO commonDTO) {
		ArrayList details = new ArrayList();

		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_AGRI_EN_VIEW_PROP_DETAILS);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.GET_AGRI_HI_VIEW_PROP_DETAILS);
				}
				details = dbUtil.executeQuery(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}

	// Added By Rakesh to get CLR Data: Start
	public String getClrFlag(String valID) {
		String clrFlag = "";
		try {
			dbUtil = new DBUtility();

			try {

				String[] param = {valID};

				dbUtil.createPreparedStatement(CommonSQL.GET_CLR_FLAG);

				clrFlag = dbUtil.executeQry(param);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return clrFlag;

	}

	// Added By Rakesh to display CLR property Data
	public ArrayList getAgriPropDetailsListClr(String valID) {
		ArrayList details = new ArrayList();

		try {
			dbUtil = new DBUtility();

			try {
				String[] param = {valID};

				dbUtil.createPreparedStatement(CommonSQL.GET_PROP_VAL_CLR);

				details = dbUtil.executeQuery(param);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}

	// Added By Rakesh to get CLR Data: End

	public ArrayList getBuildingDetails(String id, String language) {
		ArrayList details = new ArrayList();
		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_VIEW_DETAILS);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_VIEW_DETAILS_HINDI);
				}
				details = dbUtil.executeQuery(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}
	public ArrayList getBuildingDetailsAgri(String id, String language) {
		ArrayList details = new ArrayList();
		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_VIEW_DETAILS_AGRI);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_VIEW_DETAILS_AGRI_HINDI);
				}
				details = dbUtil.executeQuery(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}
	public ArrayList getFloorAreaList(String buildingTxId, String langauge) {
		ArrayList details = new ArrayList();
		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = buildingTxId; // TODO

				if ("en".endsWith(langauge))

				{
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_FLOOR_DETAILS);
				} else {
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_FLOOR_DETAILS_HINDI);

				}

				details = dbUtil.executeQuery(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}
	public String getpropTypeId(String language, String id) {

		String Id = null;

		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO

				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_PROP_TYPE_ID);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.GET_PROP_TYPE_ID);
				}
				Id = dbUtil.executeQry(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return Id;
	}
	public ArrayList getAgriTreeDetailsList(String language, String id, CommonDTO commonDTO) {
		ArrayList treedetails = new ArrayList();

		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_EN_AGRI_TREE_DETAILS);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.GET_HI_AGRI_TREE_DETAILS);
				}
				treedetails = dbUtil.executeQuery(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return treedetails;
	}
	public ArrayList getuserDetail(String userId, String JSESSIONID) {
		String arr[] = {userId, JSESSIONID};
		ArrayList l = null;
		try {
			//dbUtil = new DBUtility();
			dbUtil.createPreparedStatement("select USER_ID,LOGIN_TIME,LOGOUT_TIME,ACTIVE,IP,JSESSIONID from IGRS_LOGIN_DETAILS where USER_ID=? and JSESSIONID=?");
			l =	dbUtil.executeQuery(arr);
		}
		catch (Exception e) {
			logger.debug("Error while insertIntoLogin : "+e);
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e1) {
			}
		}
		return l;

	}
	public boolean insertIntoLogin(String our, String userId, String ip) {
		String arr[] = {userId, ip, our};
		boolean l = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement("insert into IGRS_LOGIN_DETAILS(USER_ID,LOGIN_TIME,ACTIVE,IP,JSESSIONID) values (?,systimestamp,'A',?,?)");
			l = dbUtil.executeUpdate(arr);
		} catch (Exception e) {
			logger.debug("Error while insertIntoLogin : " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e1) {
			}
		}
		return l;

	}
	public boolean updateIntoLogin(String thier, String userId) {
		String arr[] = {userId};
		boolean l = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement("update IGRS_LOGIN_DETAILS set ACTIVE='D',LOGOUT_TIME=systimestamp where user_id=? and LOGOUT_TIME is null");
			l = dbUtil.executeUpdate(arr);
		} catch (Exception e) {
			logger.debug("Error while updateIntoLogin : " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e1) {
			}
		}
		return l;
	}
	public ArrayList getSessionID(String thier, String userId) {
		String arr[] = {userId};
		boolean l = false;
		ArrayList result=new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement("select jsessionid from IGRS_LOGIN_DETAILS where USER_ID=? and LOGOUT_TIME is null");
			result = dbUtil.executeQuery(arr);
		} catch (Exception e) {
			logger.debug("Error while updateIntoLogin : " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e1) {
			}
		}
		return result;
	}
	public boolean updateLogoutDetails(String thier, String userId) {
		String arr[] = {thier};
		boolean l = false;
		try {
			// dbUtil = new DBUtility();
			dbUtil.createPreparedStatement("update IGRS_LOGIN_DETAILS set LOGOUT_TIME=systimestamp,ACTIVE='D' where  JSESSIONID=? ");
			l = dbUtil.executeUpdate(arr);
		} catch (Exception e) {
			logger.debug("Error while updateLogoutDetails : " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e1) {
			}
		}
		return l;
	}

	public String getBuildingOlderRebate(String id, String language) {
		String rebateName = "";
		ArrayList details = new ArrayList();
		try {
			dbUtil = new DBUtility();

			try {
				String arr[] = new String[1];

				arr[0] = id; // TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.olderRebateNameEng);
				}

				else {
					dbUtil.createPreparedStatement(CommonSQL.olderRebateNameHindi);
				}
				rebateName = dbUtil.executeQry(arr);

			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.debug(e);
		}
		return rebateName;
	}

}