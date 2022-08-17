package com.wipro.igrs.Seals.action;

import java.util.ArrayList;
import java.util.Map;

public class DataBeanList {
	ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();
	public static DataBean produce(Map parameters) {
	      DataBean dataBean = new DataBean();
	      dataBean.setLang((String) parameters.get("lang"));
	      dataBean.setPath((String)parameters.get("path"));
	      dataBean.setPlotFinancialYear((String)parameters.get("plotFinancialYear"));
	      dataBean.setPlotDistrict((String)parameters.get("plotDistrict"));
	      dataBean.setGuideID((String)parameters.get("guideID"));
	      return dataBean;
	   }
}
