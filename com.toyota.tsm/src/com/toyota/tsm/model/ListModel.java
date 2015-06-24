package com.toyota.tsm.model;

import com.google.gson.annotations.Expose;

public class ListModel {

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPlatenumber() {
		return platenumber;
	}

	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}

	public int getProcesstype() {
		return processtype;
	}

	public void setProcesstype(int processtype) {
		this.processtype = processtype;
	}

	public String getStartservice() {
		return startservice;
	}

	public void setStartservice(String startservice) {
		this.startservice = startservice;
	}

	@Expose
	private String barcode;
	@Expose
	private String platenumber;
	@Expose
	private int processtype;
	@Expose
	private String startservice;

}
