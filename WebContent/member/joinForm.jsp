<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
        
            if(!form.THREAD_ID.value){
                alert("아이디를 입력하세요.");
                return false;
            }
            
            if(form.idDuplication.value != "idCheck"){
                alert("아이디 중복체크를 해주세요.");
                return false;
            }
            
            if(!form.THREAD_PW.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
            
            if(!form.THREAD_NAME.value) {
            	alert('닉네임을 입력하세요.');
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
        
        // 아이디 중복체크 화면open
        function openIdChk(){
        
            window.name = "parentForm";
            window.open("member/checkid.jsp",
                    "chkForm", "width=500, height=300, resizable = no, scrollbars = no");    
        }
 
        // 아이디 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
        // 이렇게 하는 이유는 중복체크 후 다시 아이디 창이 새로운 아이디를 입력했을 때
        // 다시 중복체크를 하도록 한다.
        function inputIdChk(){
            document.userInfo.idDuplication.value ="idUncheck";
        }
        
   </script>
<link rel="stylesheet" href="./css/down.css?ver=1">
<link rel="stylesheet" href="./css/member.css?ver=1">
<link rel="stylesheet" href="./css/main_css.css">
</head>

	<%@ include file="../board/header.jsp" %>
	<div class="xe">

		<div class="body">
			<section class="xm">
				<div class="login_box" style="text-align: center;">
					<h2>Membership</h2>
					<form name="joinform" action="./MemberJoinAction.me" method="post"
					onsubmit="return checkValue()">
						<div class="control-group">
							<label for="user_id" class="control-label"><em
								style="color: red">*</em> 아이디</label>
							<div class="controls">
								<input type="text" name="THREAD_ID" id="user_id" onkeydown="inputIdchk()" required style="margin-right: -16px;"/>
							    <input type="button" value="중복확인" onclick="openIdChk()" style="margin-right: -80px; margin-left: 20px;">
							    <input type="hidden" name="idDuplication" value="idUncheck">
							</div>
						</div>
						<div class="control-group">
							<label for="name" class="control-label"><em
								style="color: red">*</em> 닉네임</label>
							<div class="controls">
								<input type="text" name="THREAD_NAME"  id="name" 	required />
							</div>
						</div>
						<div class="control-group">
							<label for="password" class="control-label"><em
								style="color: red">*</em> 비밀번호</label>
							<div class="controls">
								<input type="password" name="THREAD_PW" id="password" 	required />
							</div>
						</div>
						<div class="control-group">
							<label for="password2" class="control-label"><em
								style="color: red">*</em> 비밀번호 확인</label>
							<div class="controls">
								<input type="password" name="THREAD_RPW" id="password2" 	required />
							</div>
						</div>
						
						<div class="control-group">
							<label for="email_address" class="control-label"><em
								style="color: red">*</em> 이메일 주소</label>
							<div class="controls">
								<input type="text" name="THREAD_EMAIL" id="email_address" required="required" />
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