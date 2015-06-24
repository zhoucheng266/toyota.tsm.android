package com.toyota.tsm.model;

import java.util.List;

import com.google.gson.annotations.Expose;

public class ReturnDataModel {
	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public List<ListModel> getWorklist() {
		return worklist;
	}

	public void setWorklist(List<ListModel> worklist) {
		this.worklist = worklist;
	}

	@Expose
	private int pagecount;
	@Expose
	private int totalcount;
	@Expose
	private List<ListModel> worklist;

}
