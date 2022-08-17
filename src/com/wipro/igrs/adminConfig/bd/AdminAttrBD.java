package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.AdminAttrDAO;
import com.wipro.igrs.adminConfig.dto.AdminAttrDTO;

/**
 * ===========================================================================
 * File : adminAttrBD.java Description : Represents the Admin configuration BD
 * Class Author : vengamamba P Created Date : Apr 9, 2008
 * 
 * ===========================================================================
 */
public class AdminAttrBD {

	public AdminAttrBD() {
	}

	/**
	 * ===========================================================================
	 * Method : getModuleList() Description : Returns list of Modulenames and
	 * its values. Arguments : return type : Arraylist Author : vengamamba P
	 * Created Date : Apr10, 2008
	 * ===========================================================================
	 */

	public ArrayList getModuleList() throws Exception {
		AdminAttrDAO dao1 = new AdminAttrDAO();
		ArrayList plist = dao1.getModuleList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				AdminAttrDTO dto = new AdminAttrDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getSubModuleList() Description : Returns list of subModulenames
	 * and its values. Arguments : based on moduleid return type : Arraylist
	 * Author : vengamamba P Created Date : Apr10, 2008
	 * ===========================================================================
	 */

	public ArrayList getSubModuleList(String module)
			throws Exception {
		AdminAttrDAO dao1 = new AdminAttrDAO();
		ArrayList plist = dao1.getSubModuleList(module);
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				AdminAttrDTO dto = new AdminAttrDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * ===========================================================================
	 * Method : getFunctionList() Description : Returns list of functionnames
	 * and its values. Arguments : based on submoduleid return type : Arraylist
	 * Author : vengamamba P Created Date : Apr10, 2008
	 * ===========================================================================
	 */

	public ArrayList getFunctionList(String submodule)
			throws Exception {
		AdminAttrDAO dao1 = new AdminAttrDAO();
		ArrayList plist = dao1.getFunctionList(submodule);
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				AdminAttrDTO dto = new AdminAttrDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

}
