package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class PersonalInfoCheckAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		response.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		String id = (String) session.getAttribute("id");
		
		if (id == null || id.equals("")) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('로그인 정보가 없거나 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();

			return null;
		}
		member = memberdao.getDetailMember(id);
		
		if (member == null) {
			
			System.out.println("회원 정보 보기 실패");
			return null;
		}
		
		request.setAttribute("member", member);
		
		forward.setRedirect(false);
		forward.setPath("./member/personalInfo.jsp");
		
		return forward;
	}
}