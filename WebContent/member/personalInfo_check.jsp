<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.member.action.*" %>
<%@ page import="net.member.db.*" %>
<%
	int aa = Integer.parseInt(request.getParameter("aa"));
	System.out.println(aa);
%>
<!DOCTYPE html5 >
<html>
<head>
	<meta charset="UTF-8">
	<title>SOLGROOVE. - 개인정보 수정 비밀번호 재확인</title>
	<link rel="stylesheet" type="text/css" href="./css/information_down.css" />
	<link rel="stylesheet" type="text/css" href="./css/information_member.css" />
	<link rel="stylesheet" type="text/css" href="./css/information_main_css.css" />
	<link rel="stylesheet" type="text/css" href="./css/main_css.css" />
</head>
<body>
	<%@ include file="../board/header.jsp"  %>
	<div class="xe">
		<div class="body">
			<section class="xm">
				<div class="login_box" style="text-align: center;">
					<h2>비밀번호 재확인</h2>
					<p><small>회원님의 정보를 보호하기 위해 비밀번호를 재확인합니다.</small></p>
					<% if (aa == 1) { %>
					<form action="./PersonalInfoChangeView.me"  name="personalinfoform"  method="post" style="font-size: 20px">
						<div class="control-group">
							<input type="password" name="THREAD_PW" /> 
						</div>
						<div class="btnArea"
							style="border-bottom: 5px solid #292929; margin:0px 300px 30px; padding-bottom: 10px">
							<a href="#" class="btn" onclick="javascript:personalinfoform.submit()">회원 정보 수정</a>
						</div>
					</form>
					<% } else if (aa == 2) { %>
					<form action="./PersonalDelete.me"  name="personaldeleteform"  method="post" style="font-size: 20px">
						<div class="control-group">
							<input type="password" name="THREAD_PW" /> 
						</div>
						<div class="btnArea"
							style="border-bottom: 5px solid #292929; margin:0px 300px 30px; padding-bottom: 10px">
							<a href="#" class="btn" onclick="javascript:personaldeleteform.submit()">탈퇴</a>
						</div>
					</form>
					<% } %>
				</div>
			</section>
		</div>
	</div>	
	<%@ include file="../board/footer.jsp"  %>
</body>
</html>