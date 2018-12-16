<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<footer class="footer">
<hr size="2" width="20%"/>
		<p>
			<!-- <a href="#">사이트소개</a><span></span>
			<a href="#">공지사항</a><span></span>
			<a href="#">개인정보처리방침</a> -->
		</p>
		<p>Copyright © SolGroove. All rights reserved.</p>
		<p>E-mail: solgroove@solgroove.com</p>
</footer>

<div>
	<img src="./img/chat.png" style="width: 50px; position: fixed; bottom: 20px; right: 20px;" onclick="openChat()"/>
</div>

<script>
function openChat(){
    
    window.name = "parentForm";
    window.open("board/broadcast.jsp",
            "chkForm", "width=500, height=300, resizable = no, scrollbars = no");
}
</script>