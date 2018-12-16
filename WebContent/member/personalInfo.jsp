<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	MemberBean member = new MemberBean();
	member = (MemberBean) request.getAttribute("member");
%>
<!DOCTYPE html5>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SOLGROOVE. - <%= member.getTHREAD_ID() %> 개인정보</title>
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
					<h2>Information</h2>
					<form name="personalinfoform"  method="post" style="font-size: 20px">
						<div class="control-group">
							<label for="user_id" class="control-label" > 아이디 : <%=member.getTHREAD_ID() %></label>
						</div>
						<div class="control-group">
							<label for="name" class="control-label"  >닉네임 : <%=member.getTHREAD_NAME() %></label>
						</div>
						<div class="control-group">
							<label for="email_address" class="control-label"> 이메일 주소 : <%=member.getTHREAD_EMAIL() %></label>
						</div>
						<div class="btnArea"
							style="border-top: 5px solid #292929; margin:40px 300px 30px; padding-top: 10px">
							<a href="./PasswordCheck.me?aa=1" class="btn">회원정보 변경</a>
							<a href="./PasswordCheck.me?aa=2" class="btn">탈퇴</a>
						</div>
					</form>
				</div>
			</section>
		</div>
	</div>
	<%@ include file="../board/footer.jsp"  %>
</body>
</html>