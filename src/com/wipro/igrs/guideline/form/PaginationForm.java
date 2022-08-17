package com.wipro.igrs.guideline.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;


public class PaginationForm extends ActionForm {
	private String start;
	private String range;
	private String results;
	private ArrayList list;
	/**
	 * Returns the list.
	 * @return ArrayList
	 */
	public ArrayList getList() {
		return list;
	}

	/**
	 * Returns the range.
	 * @return String
	 */
	public String getRange() {
		return range;
	}

	/**
	 * Returns the results.
	 * @return String
	 */
	public String getResults() {
		return results;
	}

	/**
	 * Returns the start.
	 * @return String
	 */
	public String getStart() {
		return start;
	}

	/**
	 * Sets the list.
	 * @param list The list to set
	 */
	public void setList(ArrayList list) {
		this.list = list;
	}

	/**
	 * Sets the range.
	 * @param range The range to set
	 */
	public void setRange(String range) {
		this.range = range;
	}

	/**
	 * Sets the results.
	 * @param results The results to set
	 */
	public void setResults(String results) {
		this.results = results;
	}

	/**
	 * Sets the start.
	 * @param start The start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

}
