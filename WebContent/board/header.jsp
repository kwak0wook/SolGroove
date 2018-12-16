<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<% 
	String id = null;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	} 
%>

<style>

#menubar {
	margin:10px auto;
	height: 50px;
	width: max-content;
}

#menubar ul {
	padding: 0;
}

#menubar ul li.bar {
	padding: 0px 10px 0px 10px;
	font-size: 26px;
    line-height: 1.1;
    font-weight: 600;
    color: #000;
    font-family: dogfish;
    text-shadow: 0px 1px #ffffff, 4px 4px 0px #dad7d7;
}

#menubar ul li {
	float:left;
	list-style-type:none;
}

#menubar a {
    font-size: 26px;
    line-height: 1.1;
    font-weight: 600;
    color: #000;
    font-family: dogfish;
    text-shadow: 0px 1px #ffffff, 4px 4px 0px #dad7d7;
	text-decoration:none;
}

#menubar .b {
    font-size: 16px;
    line-height: 1.1;
    font-weight: 600;
    color: #000;
    font-family: dogfish;
    text-shadow: 0px 1px #ffffff, 4px 4px 0px #dad7d7;
	text-decoration:none;
}

#menubar a:hover {
	color:#a5a5a5;
	/* border-bottom:3px solid #FAED7D; */
}

.b:hover {
	color:#a5a5a5;
	/* border-bottom:3px solid #FAED7D; */
}

#menubar ul ul {
	display:none;
	position:absolute;
	background-color: rgba(245,245,245,0.2);
	width: 70;
	text-align: left;
}

#menubar ul li:hover ul {
	display: block;
}

#menubar ul ul li {
	float:none;
    padding-bottom: 10;
}

header {
	position: relative;
	z-index: 2;
}

</style>

<header>
		<div class="logo" style="height: 30px;">
			<div align="right" >
			<font size="4" style="font-family: Rix오늘의만화; " >
			<%
				if (id != null) {
			%>
					<%= id %> 님 환영합니다.
				<a href="./board/logout.jsp" onclick="javascript:alert('로그아웃 되었습니다.');">
					<img src="./img/logout.png" alt="logout" id="logout" align="right" height="25" />
				</a>
			<%
				} else {
			%>
				<a href="./MemberLogin.me">
					<img src="./img/login.png" alt="login" id="login" align="right"  height="25"/>
				</a>
			<% }  %>
			</font>
			</div>
		</div>
		<div class="h1" style="display: inline-block;">
			<a href="./SolGrooveMain.sol"><img src="./img/logo.JPG" alt="logo" width="300px" align="middle"/></a>
		</div>
		<hr style="border: 1px solid #eaeaea;">
		<div id="menubar">
			<ul>
				<li ><a href="./BoardList.bo?bnum=10">LIFE.</a>
					<ul class="subbar" >
						<li><a href="./BoardList.bo?bnum=11" class="b">음식</a></li>
						<li><a href="./BoardList.bo?bnum=12" class="b">뷰티</a></li>
						<li><a href="./BoardList.bo?bnum=13" class="b">패션</a></li>
						<li><a href="./BoardList.bo?bnum=14" class="b">연애</a></li>
						<li><a href="./BoardList.bo?bnum=15" class="b">운동</a></li>
						<li><a href="./BoardList.bo?bnum=16" class="b">꿀팁</a></li>
					</ul>
				</li>
				<li class="bar">||</li>
				<li><a href="./BoardList.bo?bnum=20">HOBBY.</a>
					<ul class="subbar" >
						<li><a href="./BoardList.bo?bnum=21" class="b">게임</a></li>
						<li><a href="./BoardList.bo?bnum=22" class="b">영화</a></li>
						<li><a href="./BoardList.bo?bnum=23" class="b">연예인</a></li>
						<li><a href="./BoardList.bo?bnum=24" class="b">여행</a></li>
						<li><a href="./BoardList.bo?bnum=25" class="b">만화</a></li>
						<li><a href="./BoardList.bo?bnum=26" class="b">문학</a></li>						
					</ul>
				</li>
				<li class="bar">||</li>
				<li><a href="./BoardList.bo?bnum=30">STUDY.</a>
					<ul class="subbar" >
						<li><a href="./BoardList.bo?bnum=31" class="b">공부</a></li>
						<li><a href="./BoardList.bo?bnum=32" class="b">외국어</a></li>
						<li><a href="./BoardList.bo?bnum=33" class="b">취업</a></li>
						<li><a href="./BoardList.bo?bnum=34" class="b">알바</a></li>
					</ul>
				</li>
				<li class="bar">||</li>
				<li><a href="./BoardList.bo?bnum=40">CHAT.</a>
					<ul class="subbar" >
						<li><a href="./BoardList.bo?bnum=41" class="b">동아리</a></li>
						<li><a href="./BoardList.bo?bnum=42" class="b">고민</a></li>
					</ul>
				</li>
				<li class="bar">||</li>
				<li><a href="./PersonalInfoCheck.me">MY PAGE.</a>
					<!-- <ul class="subbar">
						<li><a href="./PersonalInfoCheck.me" class="b">회원정보</a></li>
						<li><a href="#" class="b">작성글</a></li>
						<li><a href="#" class="b">작성댓글</a></li>
					</ul> -->
				</li>
			</ul>
		</div>
	</header>