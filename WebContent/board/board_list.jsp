<%@page import="net.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="net.board.db.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

	List boardList = (List) request.getAttribute("boardlist");
	int listcount = ((Integer) request.getAttribute("listcount")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
	int maxpage = ((Integer) request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer) request.getAttribute("startpage")).intValue();
	int endpage = ((Integer) request.getAttribute("endpage")).intValue();
	int eachlistcount = ((Integer) request.getAttribute("eachlistcount")).intValue();
	
	int bnum = ((Integer) request.getAttribute("bnum")).intValue();
	String bname = ((String) request.getAttribute("bname"));
	
	BoardDAO boarddao = new BoardDAO();
	MemberDAO memberdao = new MemberDAO();
	
%>


<!--  검색용 소스입니다. -->
<%
	String srchKey = (String) request.getAttribute("srchKey");
	if (srchKey == null) {
		srchKey = "";
	}
	String srchFlds = (String) request.getAttribute("srchFlds");
	if (srchFlds == null) {
		srchFlds = "";
	}
%>
<!--  검색용 소스입니다. -->

<!DOCTYPE html5>
<html lang="ko">
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
<script src="./jquery/ie-emulation-modes-warning.js"></script>


<link href="./css/font-awesome.min.css" rel="stylesheet">

<link href="./css/common.css" rel="stylesheet">

<link href="./css/thread-list.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./css/main_css.css" />

<script type="text/javascript">
	function submitSrchForm(){
		document.searchform.srchKey.value=document.searchform.srchKey.value.trim();
		document.searchform.submit();
	}


	function resetSrchForm() {
		document.searchform.srchFlds[0].selected = true;
		document.searchform.srchKey.value = "";
	}
</script>

<title>SOLGROOVE. - <%= bname %></title>
</head>
<body>
	<%@ include file="./header.jsp"%>


	<div class="col-xs-8">
		<div class="page-header">
			<h3>
				<%= bname %> <small>게시물 수 : <%= eachlistcount %></small>
				<% if (id != null) {  %>
				<a href="./BoardWrite.bo?bnum=<%=bnum %>" 	class='btn btn-link pull-right'> 
				<img src="./img/new.png" alt="등록" /> 스레드 등록	</a>
				<% } %>
			</h3>
		</div>
		<%
			for (int i = 0 ;  i < boardList.size() ; i ++) {
				System.out.println("boardList.size : " + boardList.size());
				BoardBean bl = (BoardBean) boardList.get(i);
		%>
		<div class="media thread-item">
			<div class="media-body">
				<a href="./BoardDetailAction.bo?num=<%= bl.getTHREAD_B_POSTNUM() %>&bnum=<%=bl.getTHREAD_B_BOARDNUM()%>"><%= bl.getTHREAD_B_SUBJECT() %>
					 <strong class="text-primary">[<%= boarddao.getReplyCount(bl.getTHREAD_B_POSTNUM())  %>]</strong>
				</a>
					<span class="pull-right"><%= memberdao.getNickName(bl.getTHREAD_B_NAME()) %> (<%= bl.getTHREAD_B_NAME() %>)</span>
			</div>
			<div>
				<h6 class="text-muted">
					<img src="./img/clock.png" alt="마지막 등록시간" /> 마지막 댓글 등록 <strong
						class="text-primary"><%= boarddao.getReplyDate(bl.getTHREAD_B_POSTNUM()) %></strong> 
						<span class="pull-right"><%= bl.getTHREAD_B_DATE() %></span>
				</h6>
			</div>
			<hr>
		</div>
<% } %>

		<nav aria-label="Page navigation" style="text-align: center">
			<ul class="pagination">
				<li>
					<a href="./BoardList.bo?bnum=<%= bnum %>&page=1" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span></a>
				</li>
				
				<% for (int a = startpage ; a <= endpage ; a++) {
						if (a == nowpage) { %>
				<li class="active">
				<% } else { %>
				<li>
				<% } %>		
					<a href="./BoardList.bo?bnum=<%= bnum %>&page=<%= a %>"><%= a %></a>
				</li>
				<% } %>
				<li><a href="./BoardList.bo?bnum=<%= bnum %>&page=<%= endpage+1 %>" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
	<!--  하단 검색폼 -->
<center>
	    <form name="searchform" action="./BoardList.bo?bnum=<%= bnum %>" method="post">
			<select name="srchFlds">
			    <option value="all"<%=srchFlds.equals("all")?"selected='seleted'":"" %>> 모두 </option>
			    <option value="sub"<%=srchFlds.equals("sub")?"selected='seleted'":"" %>> 제목 </option>
			    <option value="au"<%=srchFlds.equals("au")?"selected='seleted'":"" %>> 글쓴이 </option>
			    <option value="con"<%=srchFlds.equals("con")?"selected='seleted'":"" %>> 내용 </option>
			</select>
			<input type="text" name="srchKey" size="20" maxlength="50" value="<%=srchKey %>"/>
			<input type="button" value="검색" onclick="submitSrchForm()" class="btn btn-blue"/>
			<input type="button" value="리셋" onclick="resetSrchForm()" class="btn btn-blue"/>
		</form>
	</center>
    <!--  하단 검색폼 -->
	<!-- /container -->

	<%@ include file="./footer.jsp"%>

</body>
</html>