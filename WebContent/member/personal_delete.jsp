<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String THREAD_PW = request.getParameter("THREAD_PW");
%>
<script>
	var con = confirm('정말 삭제하시겠습니까?');
	if (!con) {
		history.go(-1);
	} else {
		location.href='./PersonalDeleteAction.me?THREAD_PW=<%= THREAD_PW %>';
	}
</script>