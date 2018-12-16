<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String mid = request.getParameter("mid");
	
	System.out.println("member_delete.jsp id : " + mid);

%>

<script>
	var con = confirm('정말 삭제하시겠습니까?');
	if (!con) {
		history.go(-1);
	} else {
		location.href='./MemberDeleteAction.me?mid=<%= mid %>';
	}
</script>