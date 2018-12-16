<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="net.member.db.*"  %>
<% 
	MemberBean member = (MemberBean) request.getAttribute("member");
%>

<!DOCTYPE html5>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원관리 시스템 관리자모드 (회원 정보 보기)</title>
	<style type="text/css">
		table {
			margin: 100 auto;
			width: 400px;
			border: 0px;
		}
		
		tr, td {
			padding: 2;
		}
		
		a:link {text-decoration: none; color: blue;}
		a:visited {text-decoration: none; color: blue;}
		a:active {text-decoration: none; color: blue;}
		a:hover {text-decoration: underline; color: red;}
		
	</style>
</head>
<body>
<%@ include file="../board/header.jsp" %>
	<table>
		<tr>
			<td colspan="2"  align="center">
				<h1><font size="8" color="blue" style="font-style: italic;"><%= member.getTHREAD_ID() %></font> 회원 정보</h1>
			</td>
		</tr>
		<tr>
			<td><b>아이디</b></td>
			<td align="right"><%= member.getTHREAD_ID() %></td>
		</tr>
		<tr>
			<td><b>비밀번호</b></td>
			<td align="right"><%= member.getTHREAD_PW() %></td>
		</tr>
		<tr>
			<td><b>이름</b></td>
			<td align="right"><%= member.getTHREAD_NAME() %></td>
		</tr>
		<tr>
			<td><b>이메일 주소</b></td>
			<td align="right"><%= member.getTHREAD_EMAIL() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<p></p>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="./MemberListAction.me">[리스트로 돌아가기]</a>&nbsp;&nbsp;
				<a href="./SolGrooveMain.sol">[메인화면으로 이동...]</a>
			</td>			
		</tr>
	</table>
	<%@ include file="../board/footer.jsp" %>
</body>
</html>