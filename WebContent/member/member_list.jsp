<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*"  %>
<%@ page import="net.member.db.*"  %>
<% 
	List memberlist = (List) request.getAttribute("memberlist");
%>

<!DOCTYPE html5>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원관리 시스템 관리자 모드 (회원 목록 보기)</title>
	<style type="text/css">
		table {
			margin: 10 auto;
			width: 300px;
			border-spacing: 0px;
		}
		
		tr, td {
			border-spacing: 0px;
			text-align: center;
			padding: 2.5 5 2.5 5;
		}
		
		a:link {text-decoration: none; color: blue;}
		a:visited {text-decoration: none; color: blue;}
		a:active {text-decoration: none; color: blue;}
		a:hover {text-decoration: underline; color: red;}
		
		a#del:link {text-decoration: none; color: red;}
		a#del:visited {text-decoration: none; color: red;}
		a#del:active {text-decoration: none; color: red;}
		a#del:hover {text-decoration: none; color: red;}
		
	</style>
	<link rel="stylesheet" href="./css/main_css.css" />
</head>
<body>
	<%@ include file="../board/header.jsp" %>
	<table>
		<tr>
			<td colspan="2" align="center"><h1>회원 목록</h1></td>
		</tr>
	<% 
		for (int i = 0 ; i < memberlist.size() ; i++ ) {
			MemberBean member = (MemberBean) memberlist.get(i);
	%>
		<tr>
			<td>
				<b><a href="./MemberViewAction.me?mid=<%= member.getTHREAD_ID() %>"><%= member.getTHREAD_ID() %></a></b>
			</td>
			<td>
				<a href="./MemberDelete.me?mid=<%= member.getTHREAD_ID() %>"  id="del" style="font-style: italic;">삭제</a>
			</td>
		</tr>
	<% } %>
		<tr>
			<td colspan="2"><p></p></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="./SolGrooveMain.sol">[메인화면으로 이동...]</a>
			</td>
		</tr>		
	</table>
	<%@ include file="../board/footer.jsp" %>
</body>
</html>