<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ��� ȭ��</title>
</head>
<body>
<h2>ȯ�� �մϴ�. ${sessionScope.loginUser.username}���� �α��� �ϼ̽��ϴ�.</h2>
<table border="1" style="border-collapse: collapse;">
	<tr><td>���̵�</td><td>${sessionScope.loginUser.userid}</td ></tr>
	<tr><td>�̸�</td><td>${sessionScope.loginUser.username}</td></tr>
	<tr><td>�����ȣ</td><td>${sessionScope.loginUser.postcode}</td></tr>
	<tr><td>�ּ�</td><td>${sessionScope.loginUser.address}</td></tr>
	<tr><td>��ȭ��ȣ</td><td>${sessionScope.loginUser.phoneno}</td></tr>
	<tr><td>�̸���</td><td>${sessionScope.loginUser.email}</td></tr>
	<tr><td>�̸���</td>
	<td><fmt:formatDate value="${loginUser.birthday}" pattern="yyyy�� MM�� dd��" /></td></tr>
	</table></body></html>