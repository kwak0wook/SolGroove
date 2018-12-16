<%@page import="net.member.db.MemberDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.board.db.*" %>
<% BoardBean board = (BoardBean) request.getAttribute("boarddata");  %>
<% ReplyBean reply = (ReplyBean) request.getAttribute("replydata");  %>

<%
	List replylist = (List) request.getAttribute("replylist");
	int replycount = ((Integer) request.getAttribute("replycount")).intValue();
	int num = Integer.parseInt(request.getParameter("num"));
	int bnum = Integer.parseInt(request.getParameter("bnum"));
	String bname = (String) request.getAttribute("bname");

	BoardDAO boarddao = new BoardDAO();
	MemberDAO memberdao = new MemberDAO();
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

    <link href="./css/font-awesome.min.css" rel="stylesheet">

    <link href="./css/common.css" rel="stylesheet">
    
    <link href="./css/thread.css" rel="stylesheet">
    <link href="./css/thread-reply.css" rel="stylesheet">
    <link href="./css/thread-list.css" rel="stylesheet">
	<link rel="stylesheet" href="./css/main_css.css" />

  </head>
<body>
<%@ include file="./header.jsp" %>

        <div class="col-xs-8">
            <div class="page-header">
                <h3><%= bname %> <small>댓글: <span class="text-primary"><%= replycount+1 %></span></small>
                    <a href="./BoardDetailAction.bo?num=<%=num %>&bnum=<%=bnum %>" class='btn btn-link pull-right'><img src="./img/reload.png" alt="새로고침" /> 새로고침</a>
                    <a href="./BoardList.bo?bnum=<%=bnum %>" class='btn btn-link pull-right'><img src="./img/list.png" alt="목록" /> 스레드 목록</a>
                    
                </h3>
            </div>

            <div class="panel panel-default">
                <div class="panel-body bg-warning">
                    <h4><%= board.getTHREAD_B_SUBJECT() %></h4>
                </div>
                <div class="panel-footer">마지막 댓글 업데이트 : <strong><%= boarddao.getReplyDate(num) %></strong><span class="pull-right">게시물 수정 일자 : <%= board.getTHREAD_B_DATE() %></span></div>
            </div>

            <div id="reply_list">
                
                    <div class="panel panel-default" id="reply" >
                        <div class="panel-heading"><strong>[1]</strong><%= memberdao.getNickName(board.getTHREAD_B_NAME()) %> (<%= board.getTHREAD_B_NAME() %>)
                        
                       <span
						class="pull-right"><%= board.getTHREAD_B_DATE()%></span>
                        
                        </div>
                        <div class="panel-body">
                            <p>
                            <%= board.getTHREAD_B_CONTENT().replace("\r\n", "</br>") %>
                            </p>
                            <% if (board.getTHREAD_B_FILE() != null) {  %>
                            <p>
                            	<img src="./boardupload/<%= board.getTHREAD_B_FILE() %>" alt="img" width="600px" />
                            </p>
                            <% } %>
                        </div>
                        <div class="panel-footer" align="right">
                        	<a href="./BoardModify.bo?num=<%=num %>&bnum=<%=bnum %>" ><img src="./img/edit.png" alt="수정" /> 수정하기</a>&nbsp;&nbsp; 
                        	<a href="./BoardDelete.bo?num=<%=num %>&bnum=<%=bnum %>" ><img src="./img/rubbish.png" alt="삭제" /> 삭제하기</a> 
                        </div>
                    </div>
                
            </div>
		<p></p>


		<div id="reply_list">
			<%
				for (int i = 0; i < replycount; i++) {
					ReplyBean rl = (ReplyBean) replylist.get(i);
			%>
			<div class="panel panel-default" id="reply" >
				<div class="panel-heading">
					<strong>[<%=i + 2%>]</strong><%= memberdao.getNickName(rl.getTHREAD_R_NAME()) %> (<%=rl.getTHREAD_R_NAME()%>)

					<span
						class="pull-right"><%=rl.getTHREAD_R_DATE()%></span>

				</div>
				<div class="panel-body">
					<p><%=rl.getTHREAD_R_CONTENT().replace("\r\n", "</br>")%></p>
				</div>
				<%-- rnum = <%=rl.getTHREAD_R_NUM() %> --%>
				<div class="panel-footer" align="right">
					<a href="./BoardReplyDelete.bo?num=<%=num%>&bnum=<%=bnum%>&rnum=<%=rl.getTHREAD_R_POSTNUM() %>">
					<img src="./img/rubbish.png" alt="삭제" /> 삭제하기</a>
				</div>
			</div>


			<%
				}
			%>
		</div>

		<div class="clearfix" style="height: 30px;"></div>

		<%
			if (id != null) {
		%>

            <div class="bg-info" style="padding: 10px;" id="divWrite">
                <form action="./BoardReplyAddAction.bo?num=<%= num %>&bnum=<%= bnum %>" id="frmReply" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" maxlength="8" readonly="readonly" value="<%= memberdao.getNickName(id) %> (<%= id %>)">
                        <input type="hidden"  id="THREAD_R_NAME" name="THREAD_R_NAME"  value="<%= id %>">
                    </div>
                    <div class="form-group">
                          <textarea class="form-control" rows="5" id="THREAD_R_CONTENT" name="THREAD_R_CONTENT" placeholder="내용"></textarea>
                    </div>
                    <p class="help-block text-danger"></p>
                    <button type="submit" class="btn btn-default">댓글달기</button>
                </form>
            </div>
            
            <% } else { %>
             <div class="bg-info" style="padding: 10px;" id="divWrite">
                <form id="frmReply">
                    <div class="form-group">
                        <input type="text" class="form-control" id="THREAD_R_NAME" name="THREAD_R_NAME"  maxlength="8" readonly="readonly" value="로그인 해주세요~!~!~!">
                    </div>
                    <p class="help-block text-danger"></p>
                </form>
            </div>
            <% } %>
               </div>
                
<!-- Bootstrap core JavaScript  ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
 
    <script src="./jquery/thread_reply.js"></script>
    
    <%@ include file="./footer.jsp" %>
            
</body>
</html>


