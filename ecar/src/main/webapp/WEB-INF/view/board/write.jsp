<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<%-- /webapp/WEB-INF/view/board/write.jsp --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
<form:form modelAttribute="board" action="write.shop"
	enctype="multipart/form-data" name="f">
<input type="hidden" name="btype" value="${param.btype}">
	<table>
	<tr><td>글쓴이</td><td><form:input path="id" />
	   <font color="red"><form:errors path="id" /></font></td></tr>
	<tr><td>비밀번호</td><td><form:input path="pw" />
	   <font color="red"><form:errors path="pw" /></font></td></tr>
	<tr><td>제목</td><td><form:input path="title" />
	   <font color="red"><form:errors path="title" /></font></td></tr>
	   
	 <tr><td>차종 구분</td><td>
         <input type="radio" name="man" value="1" />현대
         <input type="radio" name="man" value="2" />기아
         <input type="radio" name="man" value="3" />르노삼성
         <input type="radio" name="man" value="4" />BMW
         <input type="radio" name="man" value="5" />한국GM
         <input type="radio" name="man" value="6" />한불모터스
         <input type="radio" name="man" value="7" />테슬라
         <input type="radio" name="man" value="8" />재규어
         <input type="radio" name="man" value="9" />벤츠코리아
         <input type="radio" name="man" value="10" />쎄미시스코
         <input type="radio" name="man" value="11" />기타
         </td></tr>  
	   
	<tr><td>내용</td><td><form:textarea path="con" rows="15" cols="80" />
	<script>CKEDITOR.replace("con",{
		filebrowserImageUploadUrl : "imgupload.shop" }); </script>
	<font color="red"><form:errors path="con" /></font></td></tr>
	<tr><td>첨부파일</td><td><input type="file" name="file1"></td></tr>
	<tr><td colspan="2"><a href="javascript:document.f.submit()">[게시글등록]</a>
	  <a href="list.shop?btype=${param.btype}">[게시글목록]</a></td></tr>
	   </table></form:form>
</body>
</html>