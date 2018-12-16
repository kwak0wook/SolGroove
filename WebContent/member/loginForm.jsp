<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<html>
<head>
<meta charset="UTF-8">
<title>SOLGROOVE. Login</title>

	<link rel="stylesheet" type="text/css" href="./css/member.css?ver=1">
	<link rel="stylesheet" type="text/css"  href="./css/down.css?ver=1">
	<link rel="stylesheet" type="text/css"  href="./css/main_css.css">
</head>
<body>
	<%@ include file="../board/header.jsp" %>
	<div class="xe">
		<div class="body">
			<section class="xm">
				<div class="signin">
					<div class="login-header">
						<h1>
							<i class="icon-user"></i> Login
						</h1>
					</div>
					<div class="login-body">
						<form name="loginform" action="./MemberLoginAction.me"
							method="post">
							<fieldset>
								<div class="control-group">
									<input type="text" name="THREAD_ID" /> <input type="password"
										name="THREAD_PW" />
								</div>
								<div class="control-group" style="height: 30px;">
									<input type="submit" value="로그인"
										class="submit btn btn-inverse"
										onclick='javascript:loginform.submit()' " />
								</div>
							</fieldset>
						</form>
					</div>
					<div class="login-footer">
						<a href="./MemberJoin.me">회원가입</a>
					</div>
				</div>
			</section>
		</div>
	</div>
	<%@ include file="../board/footer.jsp" %>
</body>
</html>


