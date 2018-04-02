package com.JavaBean;

public class Course {//课程表
	private String Cno;		//课程号
	private String Cname;	//课程名
	private String Ccredit;	//学分
	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public String getCcredit() {
		return Ccredit;
	}
	public void setCcredit(String ccredit) {
		Ccredit = ccredit;
	}
}
