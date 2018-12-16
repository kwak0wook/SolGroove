<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<html>
<head>
	<meta charset="UTF-8">
	<title>SOLGROOVE. - Main</title>
	<link rel="stylesheet" href="./css/main_css.css" />
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="./jquery/main_js.js"></script>
	<style type="text/css">
		html, body { margin: 0; padding: 0; }
		ul.men { margin: 50px auto 0 auto; }
	</style>
</head>
<body>
	<div class="loader-moving">
		<span class="left"></span>
		<span class="right"></span>
	</div>

	<%@ include file="./header.jsp" %>
	
		<main class="main" align="center">
	<% if (id == null || !id.equals("admin")) { %>		
		<div style="display:inline-block; height: 600px; " >
			<img style="width:inherit; height: inherit;"src="./img/city.jpg">
		</div>
	<% } else if (id != null || id.equals("admin")) { %>
		<a href="./MemberListAction.me">회원정보 관리</a>
	<% }  %>
		</main>
	<br><br><br><br><br><br><br><br>
	<div style="width: max-content; margin: 0 auto;">
	<a href="https://www.facebook.com/"><img src="./img/icn_facebook.png" alt="" /></a>
	<a href="https://www.google.com"><img src="./img/icn_google.png" alt="" /></a>
	<a href="https://www.instagram.com"><img src="./img/icn_instagram.png" alt="" /></a>
	<a href="https://www.twitter.com"><img src="./img/icn_twitter.png" alt="" /></a>
	<a href="https://www.naver.com"><img src="./img/icn_naver.png" width="24" height="24" alt="" /></a>
	</div>
	
	<%@ include file="./footer.jsp" %>
	
</body>
</html>