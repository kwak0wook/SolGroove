<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	int num = Integer.parseInt(request.getParameter("num"));
	int bnum = Integer.parseInt(request.getParameter("bnum"));
	
	System.out.println("board_delete.jsp num : " + num);
	System.out.println("board_delete.jsp bnum : " + bnum);
%>

<script>
	var con = confirm('정말 삭제하시겠습니까?\n스레드 및 댓글 모두 삭제됩니다.');
	if (!con) {
		history.go(-1);
	} else {
		location.href='./BoardDeleteAction.bo?num=<%= num %>&bnum=<%= bnum %>';
	}
</script>