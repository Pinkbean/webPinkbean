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
	<h1>���� ������Ʈ :: <%=request.getServerPort() %></h1>
	<p>���� Ű���尪: <%=request.getSession().getAttribute("keyWord") %></p>
	<a href="/">Ȩ���� ���ư���</a>
</body>
</html>