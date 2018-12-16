package net.board.db;

import java.util.Date;

public class ReplyBean {
	private String THREAD_R_NAME;
	private String THREAD_R_CONTENT;
	private Date THREAD_R_DATE;
	private int THREAD_R_BOARDNUM;
	private int THREAD_R_POSTNUM;
	
	public String getTHREAD_R_NAME() {
		return THREAD_R_NAME;
	}
	public void setTHREAD_R_NAME(String tHREAD_R_NAME) {
		THREAD_R_NAME = tHREAD_R_NAME;
	}
	public String getTHREAD_R_CONTENT() {
		return THREAD_R_CONTENT;
	}
	public void setTHREAD_R_CONTENT(String tHREAD_R_CONTENT) {
		THREAD_R_CONTENT = tHREAD_R_CONTENT;
	}
	public Date getTHREAD_R_DATE() {
		return THREAD_R_DATE;
	}
	public void setTHREAD_R_DATE(Date tHREAD_R_DATE) {
		THREAD_R_DATE = tHREAD_R_DATE;
	}
	public int getTHREAD_R_BOARDNUM() {
		return THREAD_R_BOARDNUM;
	}
	public void setTHREAD_R_BOARDNUM(int tHREAD_R_BOARDNUM) {
		THREAD_R_BOARDNUM = tHREAD_R_BOARDNUM;
	}
	public int getTHREAD_R_POSTNUM() {
		return THREAD_R_POSTNUM;
	}
	public void setTHREAD_R_POSTNUM(int tHREAD_R_POSTNUM) {
		THREAD_R_POSTNUM = tHREAD_R_POSTNUM;
	}	
}
