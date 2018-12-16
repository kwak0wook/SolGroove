package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardModifyAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		boolean result = false;
		
		int num = Integer.parseInt(request.getParameter("num"));
		String id = (String) session.getAttribute("id");
		int bnum = Integer.parseInt(request.getParameter("bnum"));

		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		
		boolean usercheck = boarddao.isBoardWriter(num, id);
		
		if (usercheck == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		try {
			boarddata.setTHREAD_B_POSTNUM(num);
			boarddata.setTHREAD_B_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			boarddata.setTHREAD_B_CONTENT(request.getParameter("BOARD_CONTENT"));
			
			result = boarddao.boardModify(boarddata);
			if (result == false) {
				System.out.println("수정 실패");
				return null;
			}
			System.out.println("수정 완료");
			
			forward.setRedirect(true);
			forward.setPath("./BoardDetailAction.bo?num=" + boarddata.getTHREAD_B_POSTNUM()+"&bnum="+bnum);
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}