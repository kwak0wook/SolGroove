package net.board.db;

import java.util.Date;

public class BoardBean {

	private String THREAD_B_NAME;
	private String THREAD_B_SUBJECT;
	private String THREAD_B_CONTENT;
	private Date THREAD_B_DATE;
	private String THREAD_B_FILE;
	private int THREAD_B_READCOUNT;
	private int THREAD_B_BOARDNUM;
	private int THREAD_B_POSTNUM;
	
	public String getTHREAD_B_FILE() {
		return THREAD_B_FILE;
	}
	public void setTHREAD_B_FILE(String tHREAD_B_FILE) {
		THREAD_B_FILE = tHREAD_B_FILE;
	}
	public String getTHREAD_B_NAME() {
		return THREAD_B_NAME;
	}
	public void setTHREAD_B_NAME(String tHREAD_B_NAME) {
		THREAD_B_NAME = tHREAD_B_NAME;
	}
	public String getTHREAD_B_SUBJECT() {
		return THREAD_B_SUBJECT;
	}
	public void setTHREAD_B_SUBJECT(String tHREAD_B_SUBJECT) {
		THREAD_B_SUBJECT = tHREAD_B_SUBJECT;
	}
	public String getTHREAD_B_CONTENT() {
		return THREAD_B_CONTENT;
	}
	public void setTHREAD_B_CONTENT(String tHREAD_B_CONTENT) {
		THREAD_B_CONTENT = tHREAD_B_CONTENT;
	}
	public Date getTHREAD_B_DATE() {
		return THREAD_B_DATE;
	}
	public void setTHREAD_B_DATE(Date tHREAD_B_DATE) {
		THREAD_B_DATE = tHREAD_B_DATE;
	}
	public int getTHREAD_B_READCOUNT() {
		return THREAD_B_READCOUNT;
	}
	public void setTHREAD_B_READCOUNT(int tHREAD_B_READCOUNT) {
		THREAD_B_READCOUNT = tHREAD_B_READCOUNT;
	}
	public int getTHREAD_B_BOARDNUM() {
		return THREAD_B_BOARDNUM;
	}
	public void setTHREAD_B_BOARDNUM(int tHREAD_B_BOARDNUM) {
		THREAD_B_BOARDNUM = tHREAD_B_BOARDNUM;
	}
	public int getTHREAD_B_POSTNUM() {
		return THREAD_B_POSTNUM;
	}
	public void setTHREAD_B_POSTNUM(int tHREAD_B_POSTNUM) {
		THREAD_B_POSTNUM = tHREAD_B_POSTNUM;
	}
}
