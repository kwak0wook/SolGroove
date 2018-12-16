package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDAO;

public class BoardDeleteAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		System.out.println("BoardDeleteAction 에 들어옴");
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		boolean result = false;
		boolean usercheck = false;
		int num = Integer.parseInt(request.getParameter("num"));
		String id = (String) session.getAttribute("id");
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		
		BoardDAO boarddao = new BoardDAO();
		
		usercheck = boarddao.isBoardWriter(num, id);
		
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
		
		result = boarddao.boardDelete(num);
		
		if (result == false) {
			System.out.println("삭제 실패");
			return null;
		}
		
		System.out.println("삭제 완료");
		
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo?bnum=" + bnum);
		
		return forward;
	}
}
