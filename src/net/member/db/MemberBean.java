package net.member.db;

public class MemberBean {
	
	private String THREAD_NAME;
	private String THREAD_ID;
	private String THREAD_PW;
	private String THREAD_EMAIL;
	
	public String getTHREAD_NAME() {
		return THREAD_NAME;
	}
	public void setTHREAD_NAME(String tHREAD_NAME) {
		THREAD_NAME = tHREAD_NAME;
	}
	public String getTHREAD_ID() {
		return THREAD_ID;
	}
	public void setTHREAD_ID(String tHREAD_ID) {
		THREAD_ID = tHREAD_ID;
	}
	public String getTHREAD_PW() {
		return THREAD_PW;
	}
	public void setTHREAD_PW(String tHREAD_PW) {
		THREAD_PW = tHREAD_PW;
	}
	public String getTHREAD_EMAIL() {
		return THREAD_EMAIL;
	}
	public void setTHREAD_EMAIL(String tHREAD_EMAIL) {
		THREAD_EMAIL = tHREAD_EMAIL;
	}
}