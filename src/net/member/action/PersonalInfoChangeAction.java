package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class PersonalInfoChangeAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		boolean result = false;
		
		member.setTHREAD_ID(request.getParameter("THREAD_ID"));
		member.setTHREAD_NAME(request.getParameter("THREAD_NAME"));
		member.setTHREAD_PW(request.getParameter("THREAD_PW"));
		member.setTHREAD_EMAIL(request.getParameter("THREAD_EMAIL"));
		
		result = memberdao.updatePersonalInfo(member);
		
		if (result == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println("회원 정보 수정 실패");
			
			out.println("<script>");
			out.println("alert('회원 정보 수정에 실패하였습니다.');");
			out.println("location.href='./SolGrooveMain.sol';");
			out.println("</script>");
			out.close();
			
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println("회원 정보 수정 성공 else 문 안에");
			
			out.println("<script>");
			out.println("alert('회원 정보 수정이 정상적으로 되었습니다.');");
			out.println("location.href='./SolGrooveMain.sol';");
			out.println("</script>");
			out.close();
		}
		
		// 회원 정보 수정 성공
		System.out.println("회원 정보 수정 성공");
		forward.setRedirect(true);
		forward.setPath("./SolGrooveMain.sol");
		
		return forward;
	}
}
