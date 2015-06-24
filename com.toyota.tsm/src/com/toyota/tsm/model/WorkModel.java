package com.toyota.tsm.model;

import com.google.gson.annotations.Expose;

public class WorkModel {
	public int getPlatenumberchecked() {
		return platenumberchecked;
	}
	public void setPlatenumberchecked(int platenumberchecked) {
		this.platenumberchecked = platenumberchecked;
	}
	public String getPlatenumber() {
		return platenumber;
	}
	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}
	public String getProcessType() {
		return this.processtype;
	}
	public void setProcessType(String processtype) {
		this.processtype=processtype;
	}
	@Expose
	private int platenumberchecked;
	@Expose
	private String platenumber;
	@Expose
	private String processtype;
}
