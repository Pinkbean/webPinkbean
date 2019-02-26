<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>MENU01</h1>
	<h1>접속 서버포트 :: <%=request.getServerPort() %></h1>
	<p>세션 키워드값: <%=request.getSession().getAttribute("keyWord") %></p>
	<a href="/">홈으로 돌아가기</a>
</body>
</html>