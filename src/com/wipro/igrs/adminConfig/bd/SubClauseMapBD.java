package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.SubClauseMapDAO;
import com.wipro.igrs.adminConfig.dto.SubClauseMapDTO;
import com.wipro.igrs.adminConfig.form.SubClauseMapForm;

/**
 * ===========================================================================
 * File : SubClauseMapBD.java Description : Represents the SubClauseMap BD Class
 * Author : vengamamba P Created Date : Apr 28, 2008
 * 
 * ===========================================================================
 */
public class SubClauseMapBD {

	public SubClauseMapBD() {
	}

	/**
	 * ===========================================================================
	 * Method : getOperatorList() Description : Returns list of operatornames
	 * and its values. Arguments : return type : Arraylist Author : vengamamba P
	 * Created Date : Apr22,2008
	 * ===========================================================================
	 */

	public ArrayList getOperatorList() throws Exception {
		SubClauseMapDAO dao = new SubClauseMapDAO();
		ArrayList plist = dao.getOperatorList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				SubClauseMapDTO dto = new SubClauseMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getdISTRICTList() Description : Returns list of district names
	 * and its values. Arguments : return type : Arraylist Author : vengamamba P
	 * Created Date : Apr28,2008
	 * ===========================================================================
	 */

	public ArrayList getDistrictList() throws Exception {
		SubClauseMapDAO dao = new SubClauseMapDAO();
		ArrayList plist = dao.getDistrictList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				SubClauseMapDTO dto = new SubClauseMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getPropertyIdList() Description : Returns list of propertyids
	 * and its values. Arguments : return type : Arraylist Author : vengamamba P
	 * Created Date : Apr29,2008
	 * ===========================================================================
	 */

	public ArrayList getPropertyIdList() throws Exception {
		SubClauseMapDAO dao = new SubClauseMapDAO();
		ArrayList plist = dao.getPropertyIdList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				SubClauseMapDTO dto = new SubClauseMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getTehsilList Description : Returns list of TehsilIds and its
	 * names. Arguments : based on districtid return type : Arraylist Author :
	 * vengamamba P Created Date : Apr28, 2008
	 * ===========================================================================
	 */

	public ArrayList getTehsilList(String district)
			throws Exception {
		SubClauseMapDAO dao1 = new SubClauseMapDAO();
		ArrayList plist = dao1.getTehsilList(district);
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				SubClauseMapDTO dto = new SubClauseMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getWardPatwariList Description : Returns list of WardPatwariIds
	 * and its names. Arguments : based on tehsilid return type : Arraylist
	 * Author : vengamamba P Created Date : Apr28, 2008
	 * ===========================================================================
	 */

	public ArrayList getWardPatwariList(String tehsil)
			throws Exception {
		SubClauseMapDAO dao1 = new SubClauseMapDAO();
		ArrayList plist = dao1.getWardPatwariList(tehsil);
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				SubClauseMapDTO dto = new SubClauseMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getVillageIDList Description : Returns list of VillageIds and
	 * its names. Arguments : based on wardPatwariid return type : Arraylist
	 * Author : vengamamba P Created Date : Apr29, 2008
	 * ===========================================================================
	 */

	public ArrayList getVillageIDList(String wardpatid)
			throws Exception {
		SubClauseMapDAO dao1 = new SubClauseMapDAO();
		ArrayList plist = dao1.getVillageIDList(wardpatid);
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				SubClauseMapDTO dto = new SubClauseMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getPropertyL1IdList Description : Returns list of
	 * propertyL1Listof ids and its values. Arguments : propertyid return type :
	 * Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getPropertyL1IdList(String propertyid)
			throws Exception {
		SubClauseMapDAO dao1 = new SubClauseMapDAO();
		ArrayList plist = dao1.getPropertyL1IdList(propertyid);
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				SubClauseMapDTO dto = new SubClauseMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getPropertyL2IdList Description : Returns list of
	 * propertyL2Listof ids and its values. Arguments : propertyL1id return type :
	 * Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getPropertyL2IdList(String propertyL1id)
			throws Exception {
		SubClauseMapDAO dao1 = new SubClauseMapDAO();
		ArrayList plist = dao1.getPropertyL2IdList(propertyL1id);
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				SubClauseMapDTO dto = new SubClauseMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getSubClauseList() Description : Returns list of SUBCLAUSE LIST
	 * OF IDS and its values. Arguments : return type : Arraylist Author :
	 * vengamamba P Created Date : Apr28, 2008
	 * ===========================================================================
	 */

	public ArrayList getSubClauseList() throws Exception {
		SubClauseMapDAO dao1 = new SubClauseMapDAO();
		ArrayList plist = dao1.getSubClauseList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				SubClauseMapDTO dto = new SubClauseMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * Method : addingData Description : inserting deed master data into
	 * igrs_deed_param_mapping table
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param query :
	 *            form
	 * @throws :
	 *             Exception return Type :Boolean
	 */
	public boolean addingData(SubClauseMapForm form, String clauseid,
			String val, String oid, String fid, String roleId, String funId, String userId) throws Exception {
		SubClauseMapDAO dao = new SubClauseMapDAO();
		boolean insertflag = false;

		String param2[] = new String[11];

		param2[0] = form.getDistrictID();
		param2[1] = form.getTehsilID();
		param2[2] = form.getPatwariID();
		param2[3] = form.getVillageID();
		param2[4] = form.getPropertyID();
		param2[5] = form.getPropertyL1ID();
		param2[6] = form.getPropertyL2ID();
		param2[7] = clauseid;
		param2[8] = val;
		param2[9] = oid;
		param2[10] = fid;
		insertflag = dao.addingData(param2,roleId,funId,userId);
		return insertflag;
	}

	/**
	 * Method : commitingData Description : commiting whole trans success
	 * 
	 * @param query :
	 * @throws :
	 *             Exception return Type :void
	 */

	public void commitingData() throws Exception {
		SubClauseMapDAO dao8 = new SubClauseMapDAO();
		dao8.commitingData();
	}

	/**
	 * Method : cancellingData Description : rollback if whole trans fails
	 * 
	 * @param query :
	 * @throws :
	 *             Exception return Type :void
	 */

	public void cancellingData() throws Exception {
		SubClauseMapDAO dao7 = new SubClauseMapDAO();
		dao7.cancellingData();
	}

}
