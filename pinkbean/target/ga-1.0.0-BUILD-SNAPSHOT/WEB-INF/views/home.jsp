
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<script type="text/javascript" src="/resources/js/lib/jquery-3.2.0/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/lib/jquery-3.2.0/jquery.js"></script>

<html>
<head>
<title>Home</title>
<script>

$(function() {

    $("#submitBtn").on("click", function() {

    	console.log('ㅇㅅㅇ');
    	
        $.ajax({
            url: "/save", // 클라이언트가 요청을 보낼 서버의 URL 주소
            data: { keyWord: $('#keyWord').val() },                // HTTP 요청과 함께 서버로 보낼 데이터
            type: "POST",                             // HTTP 요청 방식(GET, POST)
            dataType: "html"                         // 서버에서 보내줄 데이터의 타입
        })
        .done(function(d) {

            alert("요청 성공");
			location.reload();
        })
        .fail(function(d) {

            alert("요청 실패" + d.value);
            location.reload();
        })
    });

});
</script>
</head>
<body>
	<h1>HOME</h1>
	<h2>접속 서버포트 :: <%=request.getServerPort() %></h2>
	<h2>연결DB :: <%=request.getAttribute("dbConnection")%></h2>
	
	<p>세션 키워드값: <%=request.getSession().getAttribute("keyWord") %> </p>
	<form action="save" method="post">
		<input type="text" id="keyWord" name="keyWord" value="세션에 넣을 키워드"><br> <input type="button" id="submitBtn" value="전송">
	</form>
	
	<a href="/menu01">다른 메뉴에서 확인</a>
</body>
</html>