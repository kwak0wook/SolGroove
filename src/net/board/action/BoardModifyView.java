package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardModifyView implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();		
		
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		int num = Integer.parseInt(request.getParameter("num"));
		boarddata = boarddao.getDetail(num);
		
		if (boarddata == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("boarddata", boarddata);
		request.setAttribute("bname", boarddao.getBoardName(boarddata.getTHREAD_B_BOARDNUM()));
		forward.setRedirect(false);
		forward.setPath("./board/board_modify.jsp");
		
		return forward;
	}
}
