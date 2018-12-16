package net.member.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class PersonalInfoChangeView implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		String id = (String) session.getAttribute("id");
		
		int result = -1;
		
		member.setTHREAD_ID(id);
		member.setTHREAD_PW(request.getParameter("THREAD_PW"));
		
		result = memberdao.isMember(member);
		
		if (result == 0) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
			
		} else if (result == -1) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
			
		}
		
		member = memberdao.getDetailMember(id);
		
		request.setAttribute("member", member);
		System.out.println("자기 정보 정보 보기 성공");
		
		forward.setRedirect(false);
		forward.setPath("./member/personalInfo_change.jsp");
		
		return forward;
	}
}