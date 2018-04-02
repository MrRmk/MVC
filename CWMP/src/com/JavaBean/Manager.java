package com.JavaBean;

public class Manager {//管理员表
	private String Mno;			//管理员账号
	private String Mname;		//姓名
	private String Msex;		//性别
	private String Mpassword;	//密码
	public String getMno() {
		return Mno;
	}
	public void setMno(String mno) {
		Mno = mno;
	}
	public String getMname() {
		return Mname;
	}
	public void setMname(String mname) {
		Mname = mname;
	}
	public String getMsex() {
		return Msex;
	}
	public void setMsex(String msex) {
		Msex = msex;
	}
	public String getMpassword() {
		return Mpassword;
	}
	public void setMpassword(String mpassword) {
		Mpassword = mpassword;
	}
	
}
