package com.JavaBean;

public class Ccheck {//批改表
	private String Sno;		//学号
	private String Wno;		//作业号
	private int Chscore;	//得分
	private String Chremark;//评语
	private String Chtime;	//批改时间
	private String Uptime;	//提交时间
	private String Chanswer;//答案
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getWno() {
		return Wno;
	}
	public void setWno(String wno) {
		Wno = wno;
	}
	public int getChscore() {
		return Chscore;
	}
	public void setChscore(int chscore) {
		Chscore = chscore;
	}
	public String getChremark() {
		return Chremark;
	}
	public void setChremark(String chremark) {
		Chremark = chremark;
	}
	public String getChtime() {
		return Chtime;
	}
	public void setChtime(String chtime) {
		Chtime = chtime;
	}
	public String getUptime() {
		return Uptime;
	}
	public void setUptime(String uptime) {
		Uptime = uptime;
	}
	public String getChanswer() {
		return Chanswer;
	}
	public void setChanswer(String chanswer) {
		Chanswer = chanswer;
	}
	
}
