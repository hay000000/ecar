<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인화면</title></head><body>
<script type="text/javascript">
function win_id(){
	var op = "width=500, height=500, left=50, top=150";
	open("idsearch.shop","",op);
}
function win_pw(){
	var op = "width=500, height=500, left=50, top=150";
	open("pwsearch.shop","",op);
}
</script>
<h2>사용자 로그인</h2>
<form:form modelAttribute="user" method="post" action="login.shop" name="loginform">
<input type="hidden" name="username" value="유효성검증울 위한 파라미터">
<input type="hidden" name="email" value="valid@aaa.bbb">
<input type="hidden" name="birthday" value="1111-11-11">
	<spring:hasBindErrors name="user">
		<font color="red"><c:forEach items="${errors.globalErrors}" var="error">
			<spring:message code="${error.code}" />
		</c:forEach></font></spring:hasBindErrors>
	<table border="1" style="border-collapse: collapse;">
		<tr height="40px"><td>아이디</td><td><form:input path="userid" />
			<font color="red"><form:errors path="userid" /></font></td></tr>
		<tr height="40px"><td>비밀번호</td><td><form:password path="password" />
			<font color="red"><form:errors path="password" /></font></td></tr>
		<tr height="40px"><td colspan="2" align="center">
		<input type="submit" value="로그인"><input type="button" value="회원가입"
			onclick="location.href='userEntry.shop'">
			<a href="javascript:win_id()">아이디찾기</a>
			<a href="javascript:win_pw()">비밀번호찾기</a>			
			</td></tr></table>
			</form:form></body></html>