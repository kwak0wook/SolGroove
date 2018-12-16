package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDAO;

public class BoardReplyDeleteAction implements Action{
	
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		
		
		boolean result=false;
		boolean usercheck=false;
		int num = Integer.parseInt(request.getParameter("num"));
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		System.out.println("rnum : "+rnum);
		
		BoardDAO boarddao = new BoardDAO();
		usercheck=boarddao.isReplyWriter(rnum, id);
				
		if (usercheck == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		}
		
		result = boarddao.replyDelete(num, rnum);
		
		if (result == false) {
			System.out.println("삭제 실패");
			return null;
		}
		
		System.out.println("삭제 완료");
		
		forward.setRedirect(true);
		forward.setPath("./BoardDetailAction.bo?num="+num+"&bnum="+bnum);
		
		return forward;		
	}

}
