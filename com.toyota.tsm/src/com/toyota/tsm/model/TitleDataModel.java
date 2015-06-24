package com.toyota.tsm.model;

public class TitleDataModel {
	public String getTitlename() {
		return titlename;
	}
	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}
	public int getResoureid() {
		return resoureid;
	}
	public void setResoureid(int resoureid) {
		this.resoureid = resoureid;
	}
	private String titlename;
	private int resoureid;

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	private String auth;
	
}
