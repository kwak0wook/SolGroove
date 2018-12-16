<%@page import="net.member.db.MemberDAO"%>
<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="net.board.db.*"%>
<%
	MemberBean mb = new MemberBean();
	MemberDAO memberdao = new MemberDAO();
	BoardDAO boarddao = new BoardDAO();
	int bnum = Integer.parseInt(request.getParameter("bnum"));
	String bname = boarddao.getBoardName(bnum);
%>
<!DOCTYPE html5>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<!-- Bootstrap core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">

<link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!--[if lt IE 9]><script src="/assets/pc/js/common/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="./js/ie-emulation-modes-warning.js"></script>

<link href="./css/font-awesome.min.css" rel="stylesheet">

<link href="./css/common.css" rel="stylesheet">

<link href="./css/thread-list.css" rel="stylesheet">

<link href="./css/main_css.css" rel="stylesheet">
<title>SOLGROOVE. - 스레드 작성</title>
</head>

<body>
	<%@ include file="./header.jsp" %>
	<% 
		if (id == null || id.equals("")) { %>
	<script>
		alert('로그인 후 이용 가능합니다.');
		location.href='./BoardList.bo?bnum=<%= bnum %>';
	</script>	
	<% } %>	

	<div class="container">
		<div class="main-contents">
			<div class="row write-div div-center">
				<div class="col-xs-12 div-left">
					<div class="page-header">
						<h3>
							스레드 등록 - <%= bname %>
							<a href="./BoardList.bo?bnum=<%= bnum %>" class='btn btn-link pull-right'>
							<img src="./img/list.png" alt="목록" /> 스레드목록</a>
						</h3>
					</div>

					<div>
						<form action="./BoardAddAction.bo?bnum=<%=bnum %>" id="frmThread" enctype="multipart/form-data" method="post">
							<input type="hidden" id="BOARD_ID" value="<%= id %>" name="BOARD_ID" >
							<div class="form-group">
								<input type="text" class="form-control" id="nickname" value="<%= memberdao.getNickName(id) %> (<%= id %>)"
									name="nickname" maxlength="8" readonly="readonly">
							</div>

							<div class="form-group">
								<input type="text" class="form-control" id="BOARD_SUBJECT"
									name="BOARD_SUBJECT" placeholder="제목" maxlength="100" required="required">
							</div>
							<div class="form-group">
								<textarea class="form-control" rows="5" id="BOARD_CONTENT"
									name="BOARD_CONTENT" placeholder="내용" required="required"></textarea>
							</div>
							<div class="form-group">
								<label for="fMedia">이미지</label> <input type="file" id="BOARD_FILE"
									name="BOARD_FILE">
								<p id="prev_media"></p>
							</div>
							<p class="help-block text-danger"></p>
							<button type="submit" class="btn btn-default">스레드생성</button>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- /container -->
	<%@ include file="./footer.jsp" %>

</body>
</html>