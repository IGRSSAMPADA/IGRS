package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.DeedParamMapDAO;
import com.wipro.igrs.adminConfig.dto.DeedParamMapDTO;
import com.wipro.igrs.adminConfig.form.DeedParamMapForm;

/**
 * ===========================================================================
 * File : DeedMasterBD.java Description : Represents the Deed Master BD Class
 * Author : vengamamba P Created Date : Apr 21, 2008
 * 
 * ===========================================================================
 */
public class DeedParamMapBD {

	public DeedParamMapBD() {
	}

	/**
	 * ===========================================================================
	 * Method : getOperatorList() Description : Returns list of operatornames
	 * and its values. Arguments : return type : Arraylist Author : vengamamba P
	 * Created Date : Apr22,2008
	 * ===========================================================================
	 */

	public ArrayList getOperatorList() throws Exception {
		DeedParamMapDAO dao = new DeedParamMapDAO();
		ArrayList plist = dao.getOperatorList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				DeedParamMapDTO dto = new DeedParamMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getParamList() Description : Returns list of Paramnames and its
	 * values. Arguments : return type : Arraylist Author : vengamamba P Created
	 * Date : Apr22,2008
	 * ===========================================================================
	 */

	public ArrayList getParamList() throws Exception {
		DeedParamMapDAO dao = new DeedParamMapDAO();
		ArrayList plist = dao.getParamList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				DeedParamMapDTO dto = new DeedParamMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getDeedList() Description : Returns list of Deednames and its
	 * values. Arguments : return type : Arraylist Author : vengamamba P Created
	 * Date : Apr21,2008
	 * ===========================================================================
	 */

	public ArrayList getDeedList() throws Exception {
		DeedParamMapDAO dao = new DeedParamMapDAO();
		ArrayList plist = dao.getDeedList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				DeedParamMapDTO dto = new DeedParamMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getInstrumentList Description : Returns list of InstrumentIds
	 * and its names. Arguments : based on deedid return type : Arraylist Author :
	 * vengamamba P Created Date : Apr21, 2008
	 * ===========================================================================
	 */

	public ArrayList getInstrumentList(String deed)
			throws Exception {
		DeedParamMapDAO dao1 = new DeedParamMapDAO();
		ArrayList plist = dao1.getInstrumentList(deed);
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				DeedParamMapDTO dto = new DeedParamMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getExemptionList Description : Returns list of
	 * ExemptionListnames and its values. Arguments : based on instrument return
	 * type : Arraylist Author : vengamamba P Created Date : Apr21, 2008
	 * ===========================================================================
	 */

	public ArrayList getExemptionList(String instrument,
			String type) throws Exception {
		DeedParamMapDAO dao1 = new DeedParamMapDAO();
		ArrayList plist = dao1.getExemptionList(instrument, type);
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				DeedParamMapDTO dto = new DeedParamMapDTO();
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
	public boolean addingData(DeedParamMapForm form, String roleId, String funId, String userId) throws Exception {
		DeedParamMapDAO dao = new DeedParamMapDAO();
		boolean insertflag = false;

		String param2[] = new String[10];

		param2[0] = form.getDeedID();
		param2[1] = form.getInstrumentID();
		param2[2] = form.getExemptionID();
		param2[3] = form.getParamID();
		param2[4] = form.getParamValue();
		param2[6] = form.getPriority();
		param2[5] = "A";
		param2[7] = form.getParentID();
		param2[8] = form.getOperatorID();
		param2[9] = form.getFuncOperatorID();
		insertflag = dao.addingData(param2,roleId,funId,userId);
		return insertflag;
	}

}
