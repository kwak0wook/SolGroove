package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class PersonalDeleteAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		String id = (String) session.getAttribute("id");
		System.out.println("delid : " + id);
		
		if (id == null || id.equals("")) {
			
			forward.setRedirect(true);
			forward.setPath("./SolGrooveMain.sol");
			
			return forward;
		}
		
		int result = -1;
		
		member.setTHREAD_ID(id);
		member.setTHREAD_PW(request.getParameter("THREAD_PW"));
		
		result = memberdao.isMember(member);
		
		if (result == 0) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("location.href='./SolGrooveMain.sol'");
			out.println("</script>");
			out.close();
			
			return null;
			
		} else if (result == -1) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.');");
			out.println("location.href='./SolGrooveMain.sol'");
			out.println("</script>");
			out.close();
			
			return null;
			
		}
		
		boolean rs = false;
		
		String deleteId = id;
		rs = memberdao.deleteMember(deleteId);
		
		if (rs == false) {
			
			System.out.println("회원 정보 삭제 실패");
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('삭제 실패하였습니다.');");
			out.println("location.href='./SolGrooveMain.sol'");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		if (rs == true) {
			
			System.out.println("회원 정보 삭제 완료");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('삭제가 완료되었습니다.');");
			out.println("location.href='./board/logout.jsp'");
			out.println("</script>");
			out.close();
		}		
		
		forward.setRedirect(true);
		forward.setPath("./board/logout.jsp");
		
		return forward;
	}
}