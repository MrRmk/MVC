package com.JavaBean;

public class Notice {//公告表
	private String NtNo;//公告号
	private String NtTitle;//主题
	private String NtContent;//内容
	private String NtTime;//发布时间
	private String NtCLno;//班级号
	private String NtTno;//教师号
	public String getNtNo() {
		return NtNo;
	}
	public void setNtNo(String ntNo) {
		NtNo = ntNo;
	}
	public String getNtTitle() {
		return NtTitle;
	}
	public void setNtTitle(String ntTitle) {
		NtTitle = ntTitle;
	}
	public String getNtContent() {
		return NtContent;
	}
	public void setNtContent(String ntContent) {
		NtContent = ntContent;
	}
	public String getNtTime() {
		return NtTime;
	}
	public void setNtTime(String ntTime) {
		NtTime = ntTime;
	}
	public String getNtCLno() {
		return NtCLno;
	}
	public void setNtCLno(String ntCLno) {
		NtCLno = ntCLno;
	}
	public String getNtTno() {
		return NtTno;
	}
	public void setNtTno(String ntTno) {
		NtTno = ntTno;
	}
}
