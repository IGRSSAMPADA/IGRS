package com.wipro.igrs.baseaction.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.ACL.dto.OfficeDTO;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.login.sql.CommonSQL;

/**
 * ===========================================================================
 * File : User.java Description : Represents the dao for User Login
 * 
 * Author : Madan Mohan Created Date : Dec 11, 2007
 * 
 * ===========================================================================
 */
public class User {
	//DBUtility dbUtil;
	DBUtility dbUtil= new DBUtility();
	//DBUtils instance = DBUtils.getInstance(); //added by roopam
	CallableStatement clstmt;
	PreparedStatement pstmt;                  //added by roopam

	private Logger logger = (Logger) Logger.getLogger(User.class);

	public User() throws Exception {

	}

	public String[] authenticateUser(String userId, String pwd, String noattempt,String roleID)
			throws Exception {
		String status[] = new String[3];
		try {
			String roleFallBackQuery = "SELECT DISTINCT RM.ROLE_ID, RM.ROLE_NAME FROM   "+
										" IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM, IGRS_RGROUP_ROLE_MAPPING GP, IGRS_USER_ROLE_GROUP_MAPPING UG "+
							" ,IGRS_OFFICE_MASTER OFF, IGRS_ROLE_OFFICE_MAPPING OFFROL  "+
							" WHERE RM.ROLE_ID = OFFROL.ROLE_ID AND GM.ROLE_GROUP_ID = GP.ROLE_GROUP_ID AND  "+
							" OFFROL.ROLE_OFFICE_MAP_ID=GP.ROLE_OFFICE_MAP_ID "+
							" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  "+
							" UG.ROLE_GROUP_ID = GM.ROLE_GROUP_ID AND UG.USER_ID = ?  "; 
			//dbUtil = new DBUtility();
			//dbUtil.createCallableStatement("call IGRS_VALID_USER(?,?,?,?)");
			dbUtil.createCallableStatement("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)");
			status = dbUtil.authenticateUser(userId, pwd, noattempt,roleID);
			//clstmt=instance.createCallableStatement("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)"); //added by roopam
			//status=instance.authenticateUser(userId, pwd, noattempt, roleID, clstmt);         //added by roopam
			String userStatus = status[0]; 
			String loggedInRole = status[1];
			String roleName = status[2];
			try {
				if (LoginConstant.DB_SUCCESSFULLY.equals(userStatus)) {
					if(loggedInRole == null) {
						dbUtil.createPreparedStatement(roleFallBackQuery);
						ArrayList data = dbUtil.executeQuery(new String[] {userId});
						if(data != null || data.isEmpty() == false) {
							ArrayList row = (ArrayList)data.get(0);
							loggedInRole = (String) row.get(0);
							roleName = (String) row.get(1);
							
						}
					}
				}
				
			} catch (Exception e) {
				
			}
			loggedInRole = (loggedInRole == null)?"":loggedInRole;
			roleName = (roleName == null)?"":roleName;
			status[1] = loggedInRole;
			status[2] = roleName;
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return status;
	}
	
	
	
	
	
/*	public String[] authenticateUser(String userId, String pwd, String noattempt,String roleID, String ofcID)
	throws Exception {
String status[] = new String[3];
try {
	String roleFallBackQuery = "SELECT DISTINCT RM.ROLE_ID, RM.ROLE_NAME FROM   "+
								" IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM, IGRS_RGROUP_ROLE_MAPPING GP, IGRS_USER_ROLE_GROUP_MAPPING UG "+
					" ,IGRS_OFFICE_MASTER OFF, IGRS_ROLE_OFFICE_MAPPING OFFROL  "+
					" WHERE RM.ROLE_ID = OFFROL.ROLE_ID AND GM.ROLE_GROUP_ID = GP.ROLE_GROUP_ID AND  "+
					" OFFROL.ROLE_OFFICE_MAP_ID=GP.ROLE_OFFICE_MAP_ID "+
					" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  "+
					" UG.ROLE_GROUP_ID = GM.ROLE_GROUP_ID AND UG.USER_ID = ? AND offrol.OFFICE_ID=? "; 
	//dbUtil = new DBUtility();
	//dbUtil.createCallableStatement("call IGRS_VALID_USER(?,?,?,?)");
	dbUtil.createCallableStatement("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)");
	status = dbUtil.authenticateUser(userId, pwd, noattempt,roleID);
	//clstmt=instance.createCallableStatement("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)"); //added by roopam
	//status=instance.authenticateUser(userId, pwd, noattempt, roleID, clstmt);         //added by roopam
	String userStatus = status[0]; 
	String loggedInRole = status[1];
	String roleName = status[2];
	try {
		if ("IGRS_DB_USER_004".equals(userStatus)) {
			if(loggedInRole == null) {
				dbUtil.createPreparedStatement(roleFallBackQuery);
				ArrayList data = dbUtil.executeQuery(new String[] {userId,ofcID});
				if(data != null || data.isEmpty() == false) {
					ArrayList row = (ArrayList)data.get(0);
					loggedInRole = (String) row.get(0);
					roleName = (String) row.get(1);
					
				}
			}
		}
		
	} catch (Exception e) {
		
	}
	loggedInRole = (loggedInRole == null)?"":loggedInRole;
	roleName = (roleName == null)?"":roleName;
	status[1] = loggedInRole;
	status[2] = roleName;
} catch (Exception x) {
	logger.debug(x);
} finally {
	dbUtil.closeConnection();
	if(clstmt!=null)                                                                   //added by roopam
		clstmt.close();
}
return status;
}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@SuppressWarnings("unchecked")
	public ArrayList getRoleID(String userID) throws Exception {
		ArrayList list = new ArrayList();
		try {
			//dbUtil = new DBUtility();
			Map map1 = new HashMap();
			ResultSet rset;
			CallableStatement clstmt = (CallableStatement) dbUtil
					.returnCallableStatement("call igrs_valid_user_role_new(?,?,?)");

			//clstmt=instance.createCallableStatement("call IGRS_VALID_USER_ROLE(?,?,?)");        //added by roopam
			clstmt.setString(1, userID);

			clstmt.registerOutParameter(2, OracleTypes.CURSOR);
			clstmt.registerOutParameter(3, OracleTypes.CURSOR);

			clstmt.execute();
			rset = (ResultSet)clstmt.getObject(2);
//			while (rset.next()) {
//				map1.put(rset.getString(1), rset.getString(2));
//			}
			rset.close();
			rset = (ResultSet)clstmt.getObject(3);
			while (rset.next()) {
				map1.put(rset.getString(2), rset.getString(3));
			}
			rset.close();
			clstmt.close();
//			if (!clstmt.execute()) {
//				logger.debug(clstmt.getString(2) + ":" + clstmt.getString(3));
//				list.add(clstmt.getString(2));
//				list.add(clstmt.getString(3));
//			}
			list.add(map1);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getTypeOfUser(String usrId) {
		ArrayList list = new ArrayList();
		try {
			//dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(" select distinct users.USER_TYPE_ID, users.USER_ID from"
							+ " IGRS_USERS_LIST users where users.USER_ID = ?  ");
			//pstmt=instance.createPreparedStatement(" select distinct users.USER_TYPE_ID, users.USER_ID from"   //added by roopam
			//		+ " IGRS_USERS_LIST users where users.USER_ID = ?  ");
			String[] param = new String[1];
			param[0] = usrId;
			list = dbUtil.executeQuery(param);
			//list = instance.executeQuery(param, pstmt);   //added by roopam
		} catch (Exception x) {
			System.out.println("Exception in getTypeOfUser() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getUserLStatus(String usrType, String usrId) {
		ArrayList list = new ArrayList();
		try {
			//dbUtil = new DBUtility();
			if (usrType.equalsIgnoreCase("E")) {
				dbUtil
						.createPreparedStatement(" select distinct users.USER_STATUS, users.USER_ID from"
								+ " IGRS_USER_REG_DETAILS ureg, IGRS_USERS_LIST users where users.USER_ID = ? "
								+ " and users.USER_ID = ureg.USER_ID and ureg.USER_STATUS ='A' ");

				/*pstmt=instance.createPreparedStatement(" select distinct users.USER_STATUS, users.USER_ID from"    //added by roopam
						+ " IGRS_USER_REG_DETAILS ureg, IGRS_USERS_LIST users where users.USER_ID = ? "
						+ " and users.USER_ID = ureg.USER_ID and ureg.USER_STATUS ='A' ");
			*/
			
			
			} else if (usrType.equalsIgnoreCase("I")) {
				dbUtil
						.createPreparedStatement(" select distinct users.USER_STATUS, users.USER_ID from"
								+ " IGRS_EMP_ROLE_CADRE_GRADE_MAP empmap, IGRS_USERS_LIST users where users.USER_ID = ? "
								+ " and users.USER_ID = empmap.EMP_ID and empmap.EMP_STATUS ='A' ");
				
				/*pstmt=instance.createPreparedStatement(" select distinct users.USER_STATUS, users.USER_ID from"      //added by roopam
						+ " IGRS_EMP_ROLE_CADRE_GRADE_MAP empmap, IGRS_USERS_LIST users where users.USER_ID = ? "
						+ " and users.USER_ID = empmap.EMP_ID and empmap.EMP_STATUS ='A' ");
*/
			}

			String[] param = new String[1];
			param[0] = usrId;
			list = dbUtil.executeQuery(param);
			//list = instance.executeQuery(param,pstmt);    //added by roopam
	
		} catch (Exception x) {
			logger.debug("Exception in getUserLStatus() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}
	/**
	 * @return
	 */
	public String validateRole(String roleId,	
							   String funId) {
		String list = "";
		try {
			//dbUtil = new DBUtility();
			String SQL = "SELECT COUNT(*) FROM IGRS_ROLE_FN_ACTIVITY_MAPPING "
					+" WHERE FUNCTION_ID='"+funId+"' AND ROLE_ID='"+roleId+"'";
			logger.debug("SQL:-"+SQL);
			dbUtil.createStatement();
			//instance.createStatement();                  //added by roopam
			
			list = dbUtil.executeQry(SQL);
			//list = (String)instance.executeQry(SQL);     //added by roopam
		} catch (Exception x) {
			logger.debug("Exception in validateRole() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

	public String checkProbation(String empID) throws Exception{
		String check = "";
		String sql = CommonSQL.CHECK_PROBATION;
		if(empID !="" || !"".equals(empID)) {
			sql = sql +"AND (IGRS_EMP_OFFICIAL_DETAILS.EMP_ID='"+empID+"')";
		}
		logger.debug("SQL:-"+sql);
		
		try {
			//dbUtil = new DBUtility();
			dbUtil.createStatement();
			check = dbUtil.executeQry(sql);
			logger.debug("check :-"+check);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return check;
	}

	public void getUserEmpIDAndDesignation(String userID,
			HashMap<String, String> retVal) {

		try {
			ArrayList list, row;
			String firstName, middleName, lastName, fullName;
			Object tmp;
			String designationName;
			//DBUtility util = new DBUtility();
			try {
				//SP_GETCADRE( p_userid IN VARCHAR2, p_first_name OUT VARCHAR2 ,p_middle_name OUT VARCHAR2 ,p_last_name OUT VARCHAR2 ,p_cadre_name OUT VARCHAR2)
				CallableStatement callStmt = (CallableStatement) dbUtil
						.returnCallableStatement("CALL SP_GETCADRE(?,?,?,?,?)");
				callStmt.setString(1, userID);
				callStmt.registerOutParameter(2, OracleTypes.VARCHAR);
				callStmt.registerOutParameter(3, OracleTypes.VARCHAR);
				callStmt.registerOutParameter(4, OracleTypes.VARCHAR);
				callStmt.registerOutParameter(5, OracleTypes.VARCHAR);
				
				callStmt.execute();
				tmp = callStmt.getString(2); //first name
				firstName = tmp == null ? "" : tmp.toString().trim();
				fullName = firstName;
				tmp = callStmt.getString(3); //middle name
				middleName = tmp == null ? "" : tmp.toString().trim();
				fullName += middleName.equals("") ? "" : (" " + middleName);
				tmp = callStmt.getString(4); //last name
				lastName = tmp == null ? "" : tmp.toString().trim();
				fullName += lastName.equals("") ? "" : (" " + lastName);
				tmp = callStmt.getString(5); //cadre name
				designationName = tmp == null ? "" : tmp.toString().trim();
				
				retVal.put("name", fullName);
				retVal.put("designation", designationName);		
				callStmt.close();
//				util.createPreparedStatement(CommonSQL.GET_EMP_DESIGNATION);
//				list = util.executeQuery(new String[] { userID });
//				if (list != null && list.size() > 0) {
//					row = (ArrayList) list.get(0);
//					tmp = row.get(0);
//					firstName = tmp == null ? "" : tmp.toString().trim();
//					fullName = firstName;
//					tmp = row.get(1);
//					middleName = tmp == null ? "" : tmp.toString().trim();
//					fullName += middleName.equals("") ? "" : (" " + middleName);
//					tmp = row.get(2);
//					lastName = tmp == null ? "" : tmp.toString().trim();
//					fullName += lastName.equals("") ? "" : (" " + lastName);
//					tmp = row.get(3);
//					designationName = tmp == null ? "" : tmp.toString().trim();
//					retVal.put("name", fullName);
//					retVal.put("designation", designationName);
//
//				}
			} catch (Exception x) {
				logger.error(x.getMessage(), x);
			}
			//util.closeConnection();
		} catch (Exception x) {
			logger.error(x.getMessage(), x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}

	}
	
	public void offcAndActivityDetails(String userID,
			HashMap retVal) {

		try {
			String roleQuery = "SELECT DISTINCT RM.ROLE_ID FROM   "+
			" IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM, IGRS_RGROUP_ROLE_MAPPING GP, IGRS_USER_ROLE_GROUP_MAPPING UG "+
							" ,IGRS_OFFICE_MASTER OFF, IGRS_ROLE_OFFICE_MAPPING OFFROL  "+
					" WHERE RM.ROLE_ID = OFFROL.ROLE_ID AND GM.ROLE_GROUP_ID = GP.ROLE_GROUP_ID AND  "+
					" OFFROL.ROLE_OFFICE_MAP_ID=GP.ROLE_OFFICE_MAP_ID "+
					" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  "+
					" UG.ROLE_GROUP_ID = GM.ROLE_GROUP_ID AND UG.USER_ID = ?  ORDER BY RM.ROLE_ID "; 
			
			
			
				String offcQuery = "SELECT DISTINCT OFFROL.OFFICE_ID FROM   "+
				" IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM, IGRS_RGROUP_ROLE_MAPPING GP, IGRS_USER_ROLE_GROUP_MAPPING UG "+
								" ,IGRS_OFFICE_MASTER OFF, IGRS_ROLE_OFFICE_MAPPING OFFROL  "+
						" WHERE RM.ROLE_ID = OFFROL.ROLE_ID AND GM.ROLE_GROUP_ID = GP.ROLE_GROUP_ID AND  "+
						" OFFROL.ROLE_OFFICE_MAP_ID=GP.ROLE_OFFICE_MAP_ID "+
						" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  "+
						" UG.ROLE_GROUP_ID = GM.ROLE_GROUP_ID AND UG.USER_ID = ? and  RM.ROLE_ID=?  ORDER BY RM.ROLE_ID ";
			 
			String actQuery = " SELECT DISTINCT ACTIVITY_ID FROM IGRS_ROLE_FN_ACTIVITY_MAPPING WHERE ROLE_ID=? ";
			
			
			
			ArrayList rolelist=new ArrayList();	
			ArrayList activitylist =new ArrayList();	
			ArrayList officelist =new ArrayList();	
			String loggedInRole;
		
		
			//DBUtility util = new DBUtility();
			try {
			
				dbUtil.createPreparedStatement(roleQuery);
				rolelist = dbUtil.executeQuery(new String[] {userID});
				logger.debug("role size :-"+rolelist.size());
				if(rolelist!=null){
					for(int i=0;i<rolelist.size();i++)
					{  
						
						String  input[]=new String[2];
						ArrayList row = (ArrayList)rolelist.get(i);
						loggedInRole = (String) row.get(0);
						input[0]=userID;
						input[1]=loggedInRole;
						dbUtil.createPreparedStatement(actQuery);
						activitylist=dbUtil.executeQuery(new String[] {loggedInRole});
						dbUtil.createPreparedStatement(offcQuery);
						officelist=dbUtil.executeQuery(input);
						OfficeDTO ofcdto= new OfficeDTO();
						ofcdto.setActivityList(activitylist);
						ofcdto.setOfficeList(officelist);
						//logger.debug("Role "+loggedInRole+" activity count "+activitylist.size());
						//logger.debug("Role "+loggedInRole+" office count "+officelist.size());
						retVal.put(loggedInRole, ofcdto);	
					}
					/*	loggedInOffice = (String) row.get(1);
						logger.debug("role id:-"+loggedInRole);
						logger.debug("office id:-"+loggedInOffice);
						logger.debug("activity size :-"+activitylist.size());
						//logger.debug("If condition  :-"+checkRole.equalsIgnoreCase(loggedInRole));
					
						if(checkRole==null||checkRole.equalsIgnoreCase(loggedInRole)){                           	officelist.add(loggedInOffice);
							previousactivitylist=activitylist;
							checkRole=loggedInRole;
							if(count==rolelist.size())
							{
								OfficeDTO ofcdto= new OfficeDTO();
								ofcdto.setActivityList(activitylist);
								ofcdto.setOfficeList(officelist);
								logger.debug("Role "+checkRole+" activity count "+previousactivitylist.size());
								logger.debug("Role "+checkRole+" office count "+officelist.size());
								retVal.put(checkRole, ofcdto);	
							}
						}else{
							OfficeDTO ofcdto= new OfficeDTO();
							ofcdto.setActivityList(previousactivitylist);
							ofcdto.setOfficeList(officelist);
							logger.debug("Role "+checkRole+" activity count "+previousactivitylist.size());
							logger.debug("Role "+checkRole+" office count "+officelist.size());
							retVal.put(checkRole, ofcdto);	
							previousactivitylist=activitylist;
							officelist=new ArrayList();
							officelist.add(loggedInOffice);
							checkRole=loggedInRole;
							if(count==rolelist.size())
							{
								ofcdto.setActivityList(activitylist);
								ofcdto.setOfficeList(officelist);
								logger.debug("Role "+checkRole+" activity count "+activitylist.size());
								logger.debug("Role "+checkRole+" office count "+officelist.size());
								retVal.put(checkRole, ofcdto);	
							}
							
							
						}
					}	
				
				}*/
				}
			}catch (Exception x) {
				logger.error(x.getMessage(), x);
			}
			//util.closeConnection();
		} catch (Exception x) {
			logger.error(x.getMessage(), x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}

	}
	
}
