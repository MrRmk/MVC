package com.JavaBean;

public class Teacher {//教师表
	private String Tno;			//教师号
	private String Tname;		//教师名
	private String Tsex;		//性别
	private String Toffice;		//办公室
	private String Tpassword;	//密码
	private String Tdept;		//所在系
	public String getTno() {
		return Tno;
	}
	public void setTno(String tno) {
		Tno = tno;
	}
	public String getTname() {
		return Tname;
	}
	public void setTname(String tname) {
		Tname = tname;
	}
	public String getTsex() {
		return Tsex;
	}
	public void setTsex(String tsex) {
		Tsex = tsex;
	}
	public String getToffice() {
		return Toffice;
	}
	public void setToffice(String toffice) {
		Toffice = toffice;
	}
	public String getTpassword() {
		return Tpassword;
	}
	public void setTpassword(String tpassword) {
		Tpassword = tpassword;
	}
	public String getTdept() {
		return Tdept;
	}
	public void setTdept(String tdept) { 
		Tdept = tdept;
	}
	
}
