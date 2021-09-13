package com.mycompany.webapp.dto;

public class Ch14Member {
	private String mid;
	private String mname;
	private String mpassword;
	private int menabled;
	private String mrole;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public int getMenabled() {
		return menabled;
	}

	public void setMenabled(int menabled) {
		this.menabled = menabled;
	}

	public String getMrole() {
		return mrole;
	}

	public void setMrole(String mrole) {
		this.mrole = mrole;
	}
}
