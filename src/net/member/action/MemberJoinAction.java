package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		boolean result = false;
		
		member.setTHREAD_NAME(request.getParameter("THREAD_NAME"));
		member.setTHREAD_ID(request.getParameter("THREAD_ID"));
		member.setTHREAD_PW(request.getParameter("THREAD_PW"));
		member.setTHREAD_EMAIL(request.getParameter("THREAD_EMAIL"));
		
		result = memberdao.joinMember(member);
		
		if (result == false) {
			System.out.println("회원 가입 실패");
			return null;
		}
		
		// 회원 가입 성공
		forward.setRedirect(true);
		forward.setPath("./MemberLogin.me");
		
		return forward;
	}
}