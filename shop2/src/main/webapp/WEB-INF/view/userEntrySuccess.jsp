<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����� ��� ��� ȭ��</title>
</head>
<body><h2>����� ��� ��� ȭ��</h2>
<table border="1" style="border-collapse: collapse;">
	<tr><td>���̵�</td><td>${user.userid}</td></tr>
	<tr><td>�̸�</td><td>${user.username}</td></tr>
	<tr><td>�����ȣ</td><td>${user.postcode}</td></tr>
	<tr><td>�ּ�</td><td>${user.address}</td></tr>
	<tr><td>��ȭ��ȣ</td><td>${user.phoneno}</td></tr>
	<tr><td>�̸���</td><td>${user.email}</td></tr>
	<tr><td>�������</td>
	<td><fmt:formatDate value="${user.birthday}" pattern="yyyy�� MM�� dd��" /></td></tr>
</table>
</body>
</html>