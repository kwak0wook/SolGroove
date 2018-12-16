package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		
		boolean result = false;
		String id = (String) session.getAttribute("id");
		
		if (id == null) {
			
			forward.setRedirect(true);
			forward.setPath("./SolGrooveMain.sol");
			
			return forward;
		} else if (!id.equals("admin")) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('관리자가 아닙니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		String deleteId = request.getParameter("mid");
		result = memberdao.deleteMember(deleteId);
		
		if (result == false) {
			
			System.out.println("회원 정보 삭제 실패");
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('삭제 실패하였습니다.');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		if (result == true) {
			
			System.out.println("회원 정보 삭제 완료");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('삭제가 완료되었습니다.');");
			out.println("location.href='./MemberListAction.me'");
			out.println("</script>");
			out.close();
		}		
		
		forward.setRedirect(true);
		forward.setPath("./MemberListAction.me");
		
		return forward;
	}
}