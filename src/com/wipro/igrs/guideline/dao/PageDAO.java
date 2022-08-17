package com.wipro.igrs.guideline.dao;

import java.util.ArrayList;


public interface PageDAO {
	public ArrayList getList(int start, int range);
	public int getTotalNumberOfResults();
}
