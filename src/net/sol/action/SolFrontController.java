package net.sol.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SolFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = 1L;
	
	protected void doProcess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		Action action = null;
		
		System.out.println("SolFrontController.doProcess() : requestURI |" + requestURI + "|");
		System.out.println("SolFrontController.doProcess() : contextPath |" + contextPath + "|");
		System.out.println("SolFrontController.doProcess() : command |" + command + "|");
		
		// 1.
		if (command.equals("/Main.sol")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/mainF.jsp");
		}
		
		else if (command.equals("/SolGrooveMain.sol")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/main.jsp");
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
