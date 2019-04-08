
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
	
	// payco로그인 버튼
	$("#loginPaycoBtn").on("click", function(){
		location.href='/login/payco';
	});
	
	// naver로그인 버튼
	$("#loginNaverBtn").on("click", function(){
		location.href='/login/naver';
	});	
	
	// naver로그인 버튼
	$("#loginFacebookBtn").on("click", function(){
		location.href='/login/facebook';
	});		
	
	// 로그아웃
	$("#logoutBtn").on("click", function(){
		location.href='/logout';
	});	
});
</script>
</head>
<body>
	<h1>로그인 테스트 페이지</h1>

	<p>접근 토큰 	:: <%=request.getSession().getAttribute("access_token") %></p>
	<p>재발급 토큰 	:: <%=request.getSession().getAttribute("refresh_token") %></p>
	<p>만료 시간 	:: <%=request.getSession().getAttribute("expires_in") %></p>
	<p>유저아이디 	:: <%=request.getSession().getAttribute("user_id") %></p>

	<input type="button" id="loginPaycoBtn" value="payco로그인 테스트">
	<input type="button" id="loginNaverBtn" value="naver로그인 테스트">
	<input type="button" id="loginFacebookBtn" value="facebook로그인 테스트">
	<input type="button" id="logoutBtn" value="로그아웃">
</body>
</html>