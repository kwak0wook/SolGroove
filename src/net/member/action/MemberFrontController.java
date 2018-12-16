package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = 1L;
	
	protected void doProcess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("member");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		System.out.println(command);
		
		// 1.
		if (command.equals("/MemberLogin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/loginForm.jsp");
		}
		
		// 2.
		else if (command.equals("/MemberJoin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/joinForm.jsp");
		}
		
		else if (command.equals("/PersonalDelete.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/personal_delete.jsp");
		}
		
		else if (command.equals("/PersonalDeleteAction.me")) {
			action = new PersonalDeleteAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 3.
		else if (command.equals("/MemberLoginAction.me")) {
			action = new MemberLoginAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 4.
		else if (command.equals("/MemberJoinAction.me")) {
			action = new MemberJoinAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 5.
		else if (command.equals("/MemberListAction.me")) {
			action = new MemberListAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 6.
		else if (command.equals("/MemberViewAction.me")) {
			action = new MemberViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	// 7.
		else if (command.equals("/MemberDeleteAction.me")) {
			action = new MemberDeleteAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 7-1.
		else if (command.equals("/MemberDelete.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/member_delete.jsp");
		}

		// 8.
		else if (command.equals("/MemberIdCheckAction.me")) {
			System.out.println("command id체크");
			action = new MemberIdCheckAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 9.
		else if (command.equals("/PersonalInfoCheck.me")) {
			System.out.println(command  + " 개인의 회원정보 관리");
			action = new PersonalInfoCheckAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 10.
		else if (command.equals("/PersonalInfoChangeView.me")) {
			System.out.println(command  + " 개인의 회원정보 관리");
			action = new PersonalInfoChangeView();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 11.
		else if (command.equals("/PersonalInfoChangeAction.me")) {
			System.out.println(command  + " 개인의 회원정보 바꾸기");
			action = new PersonalInfoChangeAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 12.
		else if (command.equals("/PasswordCheck.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/personalInfo_check.jsp");
		}
				
		if (forward != null) {
			
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		doProcess(request, response);
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		doProcess(request, response);
	}
}