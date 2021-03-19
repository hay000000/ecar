<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<%-- /webapp/WEB-INF/view/board/delete.jsp --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 삭제 화면</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
<form:form modelAttribute="board" action="delete.shop" method="post" name="f">
<input type="hidden" name="btype" value="${param.btype}">
<form:hidden path="cnt" />
<spring:hasBindErrors name="board" >
	<font color="red"><c:forEach items="${errors.globalErrors }"
	var="error"><spring:message code="${error.code }" /></c:forEach>
	</font></spring:hasBindErrors>
<input type="hidden" name="cnt" value="${board.cnt }">
<table><caption>Ecar 게시글 삭제 화면</caption>
	<tr><td>게시글비밀번호</td>
		<td><form:password path="pw" /></td></tr>
	<tr><td colspan="2">
<a href="javascript:document.f.submit()">[게시글삭제]</a></td></tr>
</table></form:form>
</body>
</html>