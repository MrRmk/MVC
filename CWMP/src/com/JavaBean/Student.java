package com.JavaBean;

public class Student {//学生表
	private String Sno;			//学号
	private String Sname;		//姓名
	private String Ssex;		//性别
	private String Spassword;	//密码
	private String Sdept;		//所在系
	private String CLno;		//班级号
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSsex() {
		return Ssex;
	}
	public void setSsex(String ssex) {
		Ssex = ssex;
	}
	public String getSpassword() {
		return Spassword;
	}
	public void setSpassword(String spassword) {
		Spassword = spassword;
	}
	public String getSdept() {
		return Sdept;
	}
	public void setSdept(String sdept) {
		Sdept = sdept;
	}
	public String getCLno() {
		return CLno;
	}
	public void setCLno(String cLno) {
		CLno = cLno;
	}
	
}
