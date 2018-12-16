<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html5>
<html>
<head>
	<meta charset="UTF-8">
    <title>익명 채팅</title>
</head>
<body>
    <fieldset>
        <textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
        <br/>
        <input id="inputMessage" type="text" onkeypress="enterkey()"/>
        <input type="submit" value="send" onclick="send()" />
    </fieldset>
</body>
    <script type="text/javascript">
        var textarea = document.getElementById("messageWindow");
        var webSocket = new WebSocket('ws://172.16.10.16:8088/solstu_ing/broadcasting');
        var inputMessage = document.getElementById('inputMessage');
        
		webSocket.onerror = function(event) {
		  onError(event)
		};
		webSocket.onopen = function(event) {
		  onOpen(event)
		};
		webSocket.onmessage = function(event) {
		  onMessage(event)
		};
		function onMessage(event) {
		    /* textarea.value += "상대 : " + event.data + "\n"; */
		    textarea.value += event.data + "\n";
		}
		function onOpen(event) {
		    textarea.value += "연결 성공\n";
		}
		function onError(event) {
		  alert(event.data);
		}
		function send() {
		    textarea.value += "나 : " + inputMessage.value + "\n";
		    webSocket.send(inputMessage.value);
		    inputMessage.value = "";
		}
    
	//  엔터키를 통해 send함
    function enterkey() {
        if (window.event.keyCode == 13) {
            send();
        }
    }
    // 채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게함
    window.setInterval(function() {
        var elem = document.getElementById('messageWindow');
        elem.scrollTop = elem.scrollHeight;
    }, 0);
  </script>
</html>