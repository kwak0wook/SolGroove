<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.board.db.*" %>

<% BoardBean board = (BoardBean) request.getAttribute("boarddata"); 

	int num = Integer.parseInt(request.getParameter("num"));
	int bnum = Integer.parseInt(request.getParameter("bnum"));
	String bname = ((String) request.getAttribute("bname"));
	System.out.println("modify 에서 bname : " + bname);
%> 
<!DOCTYPE html5>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>SOLGROOVE. - </title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!--[if lt IE 9]><script src="/assets/pc/js/common/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--[if lt IE 10]>
        <script src="/assets/pc/js/jquery/placeholders.min.js"></script>
    <![endif]-->
    <link href="./css/font-awesome.min.css" rel="stylesheet">

    <link href="./css/common.css" rel="stylesheet">
    
    <link href="./css/thread.css" rel="stylesheet">
    <link href="./css/thread-reply.css" rel="stylesheet">
    <link href="./css/thread-list.css" rel="stylesheet">
	<link rel="stylesheet" href="./css/main_css.css" />

  </head>
<body>
<%@ include file="./header.jsp" %>
	<% 
		if (id == null || !board.getTHREAD_B_NAME().equals(id)) {
	%>
	<script>
		alert('수정할 권한이 없습니다.');
		history.go(-1);
	</script>
	<% } %>
	<div class="container">
		<div class="main-contents">
			<div class="row write-div div-center">
				<div class="col-xs-12 div-left">
					<div class="page-header">
						<h3>
							스레드 수정 - <%= bname %>
							<a href="./BoardList.bo?bnum=<%= bnum %>" class='btn btn-link pull-right'>
							<i class="fa fa-angle-double-left" aria-hidden="true"></i> 스레드목록</a>
						</h3>
					</div>
					
					<div>
				<form action="./BoardModifyAction.bo?num=<%= num %>&bnum=<%= bnum %>" method="post">

					<div class="form-group">
						<input type="text" class="form-control" id="BOARD_SUBJECT" name="BOARD_SUBJECT"
						 placeholder="<%= board.getTHREAD_B_SUBJECT() %>" value="<%= board.getTHREAD_B_SUBJECT() %>">
					</div>
					<div class="form-group">
						<textarea class="form-control" rows="5" id="BOARD_CONTENT" name="BOARD_CONTENT" 
						placeholder="<%= board.getTHREAD_B_CONTENT() %>" cols="60" rows="30"><%= board.getTHREAD_B_CONTENT() %></textarea>
					</div>
					<div class="form-group">
						<label for="fMedia">이미지</label>
						<input type="file" id="BOARD_FILE" name="BOARD_FILE"><p id="prev_media"></p>
					</div>
				<button type="submit" class="btn btn-default">수정 완료</button>
			</form>
            </div>
	 	</div>
	 	</div>
	 	</div>
	 	</div>
	
	
	<%-- 
        <div class="col-xs-8">
            <div class="page-header">
                <h3><%= bname %>
                    <a href="./BoardList.bo?bnum=<%=bnum %>" class='btn btn-link pull-right'><i class="fa fa-angle-double-left" aria-hidden="true"></i> 스레드 목록</a>
                </h3>
            </div>

            <div class="panel panel-default">
				<form action="./BoardModifyAction.bo?" method="post">
					<input type="hidden" id="num" name="num" value="<%=num %>">
					<input type="hidden" id="bnum" name="bnum" value="<%=bnum %>">
					<div class="form-group">
					<input type="text" id="BOARD_SUBJECT" name="BOARD_SUBJECT">
					</div>
					<div class="form-group">
						<textarea class="form-control" rows="5" id="BOARD_CONTENT" name="BOARD_CONTENT" placeholder="내용" cols="60" rows="30"></textarea>
					</div>
					<div class="form-group">
						<label for="fMedia">이미지</label> <input type="file" id="BOARD_FILE" name="BOARD_FILE">
						<p id="prev_media"></p>
					</div>
				<button type="submit" class="btn btn-default">수정 완료</button>
			</form>
            </div>
	 	</div>
	 --%>
                <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
    <script src="./jquery/bootstrap.min.js"></script>
    <script src="./jquery/jquery.form.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./jquery/ie10-viewport-bug-workaround.js"></script>
    <script src="./jquery/g.js"></script>
    
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
    <script src="./jquery/bootstrap-waitingfor.min.js"></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./jquery/g.js"></script>
    <script src="./jquery/thread.js"></script>
    <script src="./jquery/thread_reply.js"></script>
    
    <%@ include file="./footer.jsp" %>
            
</body>
</html>


