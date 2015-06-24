package com.toyota.tsm.model;

import com.google.gson.annotations.Expose;

public class UserModel {

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDlrcode() {
		return dlrcode;
	}

	public void setDlrcode(String dlrcode) {
		this.dlrcode = dlrcode;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	@Expose
	// 账户
	private String account;
	@Expose
	// 销售店代码
	private String dlrcode;
	@Expose
	// 权限
	private int auth;
	@Expose
	// 角色
	private String station;

}
