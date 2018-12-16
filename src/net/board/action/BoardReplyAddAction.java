package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.ReplyBean;

public class BoardReplyAddAction implements Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		System.out.println("boardReplyAddAction");
		BoardDAO boarddao = new BoardDAO();
		ReplyBean replydata = new ReplyBean();
		ActionForward forward = new ActionForward();
		
		int num = Integer.parseInt(request.getParameter("num"));
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		System.out.println("boardReplyAddAction의 num (게시글 고유번호) : " + num);

		boolean result = false;

		try {
		
			replydata.setTHREAD_R_NAME(request.getParameter("THREAD_R_NAME"));
			replydata.setTHREAD_R_CONTENT(request.getParameter("THREAD_R_CONTENT"));
			replydata.setTHREAD_R_BOARDNUM(num);
			
			result = boarddao.replyInsert(replydata);
			System.out.println("11111");
			if (result == false) {
				System.out.println("BoardReplyAddAction 실패");
				return null;
			}
			System.out.println("BoardReplyAddAction 성공");
			
			forward.setRedirect(true);
			//board_view에서 BoardReplyAddAction을 호출 시 num과 bnum을 받아서 아래 BoardDetailAction에 넘겨줘야 하는데 받아오는것이 아직 안되어 임의의 값 넘김
			
			forward.setPath("./BoardDetailAction.bo?num="+num+"&bnum="+bnum);
			
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("2222");
		return null;
	}

}
