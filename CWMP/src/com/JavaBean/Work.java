package com.JavaBean;

public class Work {//作业表
	private String Wno;			//作业号
	private String Wtitle;		//主题
	private String Wcontent;	//内容
	private String Wdeadline;	//截止时间
	private String Tno;			//教师号
	private String CLno;		//班级号
	private String Cno;			//课程号
	public String getWno() {
		return Wno;
	}
	public void setWno(String wno) {
		Wno = wno;
	}
	public String getWtitle() {
		return Wtitle;
	}
	public void setWtitle(String wtitle) {
		Wtitle = wtitle;
	}
	public String getWcontent() {
		return Wcontent;
	}
	public void setWcontent(String wcontent) {
		Wcontent = wcontent;
	}
	public String getWdeadline() {
		return Wdeadline;
	}
	public void setWdeadline(String wdeadline) {
		Wdeadline = wdeadline;
	}
	public String getTno() {
		return Tno;
	}
	public void setTno(String tno) {
		Tno = tno;
	}
	public String getCLno() {
		return CLno;
	}
	public void setCLno(String cLno) {
		CLno = cLno;
	}
	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
}
