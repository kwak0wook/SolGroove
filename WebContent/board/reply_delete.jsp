<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	int num = Integer.parseInt(request.getParameter("num"));
	int bnum = Integer.parseInt(request.getParameter("bnum"));
	int rnum = Integer.parseInt(request.getParameter("rnum"));
	
	System.out.println("reply_delete.jsp num : " + num);
	System.out.println("reply_delete.jsp bnum : " + bnum);
	System.out.println("reply_delete.jsp rnum : " + rnum);
%>

<script>
	var con = confirm('정말 삭제하시겠습니까?');
	if (!con) {
		history.go(-1);
	} else {
		location.href='./BoardReplyDeleteAction.bo?num=<%= num %>&bnum=<%= bnum %>&rnum=<%= rnum %>';
	}
</script>