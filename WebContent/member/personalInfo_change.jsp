<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="net.member.db.MemberBean"%>
<% 
	MemberBean member = new MemberBean();
	member = (MemberBean) request.getAttribute("member");
%>
<!DOCTYPE html5>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
    
        // 회원가입 화면의 입력값들을 검사한다.
        function checkValue()
        {
            var form = document.joinform;
        
            if(!form.THREAD_NAME.value){
                alert("닉네임을 입력하세요.");
                return false;
            }
            
            if(!form.THREAD_PW.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
            
            // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
            if(form.THREAD_PW.value != form.THREAD_RPW.value){
                alert("비밀번호를 동일하게 입력하세요.");
                return false;
            }    
            
            if(!form.THREAD_EMAIL.value){
                alert("메일 주소를 입력하세요.");
                return false;
            }
            
			var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        	
        	if(exptext.test(document.joinform.THREAD_EMAIL.value)==false){
        		//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우
        		
        		alert("이 메일형식이 올바르지 않습니다.");
        		document.joinform.THREAD_EMAIL.focus();
        		return false;
        	}            
        }
        
        
   </script>
<link rel="stylesheet" href="./css/down.css?ver=1">
<link rel="stylesheet" href="./css/member.css?ver=1">
<link rel="stylesheet" href="./css/main_css.css">
</head>
<body>
	<%@ include file="../board/header.jsp" %>
	<div class="xe">
		<div class="body">
			<section class="xm">
				<div class="login_box" style="text-align: center;">
					<h2><%= member.getTHREAD_ID() %> 정보 수정</h2>
					<form name="personalInfoChangeform" action="./PersonalInfoChangeAction.me" method="post" onsubmit="return checkValue()">
						<div class="control-group">
							<label for="user_id" class="control-label"><em
								style="color: red">*</em> 아이디</label>
							<div class="controls">
								<input type="text" readonly="readonly" name="THREAD_ID"  id="user_id"  value="<%=member.getTHREAD_ID() %>" required/>
							</div>
						</div>
						<div class="control-group">
							<label for="name" class="control-label"><em
								style="color: red">*</em> 닉네임</label>
							<div class="controls">
								<input type="text" name="THREAD_NAME" id="name"  value="<%=member.getTHREAD_NAME() %>"	required />
							</div>
						</div>
						<div class="control-group">
							<label for="password" class="control-label"><em
								style="color: red">*</em> 비밀번호</label>
							<div class="controls">
								<input type="password" name="THREAD_PW" id="password" 	required placeholder="새 비밀번호"/>
							</div>
						</div>
						<div class="control-group">
							<label for="password2" class="control-label"><em
								style="color: red">*</em> 비밀번호 확인</label>
							<div class="controls">
								<input type="password" name="THREAD_RPW" id="password2" 	required placeholder="새 비밀번호 확인"/>
							</div>
						</div>
						
						<div class="control-group">
							<label for="email_address" class="control-label"><em
								style="color: red">*</em> 이메일 주소</label>
							<div class="controls">
								<input type="text" name="THREAD_EMAIL" id="email_address" value="<%=member.getTHREAD_EMAIL() %>" required="required" />
							</div>
						</div>
						<div class="btnArea"
							style="border-top: 5px solid #292929; margin:40px 300px 30px; padding-top: 10px">
							<input type="submit" value="등록"
								class="btn btn-inverse pull-right" /> <a
								href="javascript:history.go(-1)" class="btn pull-left">취소</a>
						</div>
					</form>
				</div>

			</section>
		</div>
		</div>
	<%@ include file="../board/footer.jsp" %>
</body>
</html>
</html>