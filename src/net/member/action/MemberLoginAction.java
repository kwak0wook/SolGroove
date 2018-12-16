package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		int result = -1;
		
		member.setTHREAD_ID(request.getParameter("THREAD_ID"));
		member.setTHREAD_PW(request.getParameter("THREAD_PW"));
		result = memberdao.isMember(member);
		
		if (result == 0) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			
			return null;
			
		} else if (result == -1) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			
			return null;
			
		}
		
		// 로그인 성공
		session.setAttribute("id", member.getTHREAD_ID());
		System.out.println("로그인 성공");
		forward.setRedirect(true);
		// 값을 주지 않고 그냥 가서 보겠다.
		forward.setPath("./SolGrooveMain.sol");
		
		return forward;
		
	}
}