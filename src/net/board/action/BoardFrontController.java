package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = 1L;

	protected void doProcess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		Action action = null;
		
		System.out.println("BoardFrontController.doProcess() : requestURI |" + requestURI + "|");
		System.out.println("BoardFrontController.doProcess() : contextPath |" + contextPath + "|");
		System.out.println("BoardFrontController.doProcess() : command |" + command + "|");
		
		// 1.
		if (command.equals("/BoardWrite.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/board_write.jsp");

		// 2.
/*		} else if (command.equals("/BoardReplyAction.bo")) {
			action = new BoardReplyAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
*/				
		//3.
		} else if (command.equals("/BoardDelete.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/board_delete.jsp");
			
			//3-1.
		} else if (command.equals("/BoardReplyDelete.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/reply_delete.jsp");
		
		// 4.	
		} else if (command.equals("/BoardModify.bo")) {
			action = new BoardModifyView();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 5.	
		} else if (command.equals("/BoardAddAction.bo")) {
			action = new BoardAddAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/BoardReplyAddAction.bo")) {
			action = new BoardReplyAddAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/BoardReplyDeleteAction.bo")) {
			action = new BoardReplyDeleteAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
/*			
		// 6. 	
		} else if (command.equals("/BoardReplyView.bo")) {
			action = new BoardReplyView();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
*/		
		// 7.	
		} else if (command.equals("/BoardModifyAction.bo")) {
			action = new BoardModifyAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 8.	
		} else if (command.equals("/BoardDeleteAction.bo")) {
			action = new BoardDeleteAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 9.	
		} else if (command.equals("/BoardList.bo")) {
			System.out.println(command);
			action = new BoardListAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 10.	
		} else if (command.equals("/BoardDetailAction.bo")) {
			action = new BoardDetailAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		// Action interface 의 execute 실행 후, 결과로 받은 forward 처리
		
		if (forward != null) {
			if (forward.isRedirect()) {
				// 단순 페이지 Redirection
				response.sendRedirect(forward.getPath());
				
			} else {
				// Action 이 request 에 Attribute 로 저장한 데이터를 활용하여 View 페이지를 작성한다.
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
