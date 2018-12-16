package net.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;
import net.board.db.ReplyBean;

public class BoardDetailAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("boarddetailaction 들어왔움");
		request.setCharacterEncoding("UTF-8");
		
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		ReplyBean replydata = new ReplyBean();

		
		System.out.println("000");
		
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		System.out.println("001");
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		System.out.println(bnum);
		
		boarddao.setReadCountUpdate(num);
		boarddata = boarddao.getDetail(num);
		
		if (boarddata == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		//num = POSTNUM
		//bnum = BOARDNUM
		System.out.println(bnum);
		System.out.println("111");
		List replylist = new ArrayList();
		System.out.println("222");
		replylist = boarddao.getReplyList(num);
		System.out.println("333");
		int replycount = boarddao.getReplyCount(num);
		System.out.println("444");
		
		request.setAttribute("replylist", replylist);
		request.setAttribute("replycount", replycount);
		request.setAttribute("bname", boarddao.getBoardName(bnum));
		
		System.out.println("상세보기 성공");
		
		request.setAttribute("boarddata", boarddata);
		request.setAttribute("replyddata", replydata);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/board_view.jsp");
		
		return forward;		
	}
}
