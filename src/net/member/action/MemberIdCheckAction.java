package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.member.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberIdCheckAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		
		String id = request.getParameter("id");
        MemberDAO memberdao = new MemberDAO();
        System.out.println("CID1="+id);
        
        boolean result = memberdao.IdCheck(id);
        
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter();
 
        if(result)    out.println("0"); // 아이디 중복
        else        out.println("1");
        
        out.close();
        
        return null;
    }
}