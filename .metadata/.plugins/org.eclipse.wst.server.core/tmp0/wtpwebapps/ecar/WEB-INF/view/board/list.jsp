<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="../css/main.css">
<script type="text/javascript">
function listpage(page){
   document.searchform.pageNum.value=page;
   document.searchform.submit();
}</script>

<script type="text/javascript">
function listpage(page){
   document.manform.btype.value=page;
   document.manform.submit();
}</script>
</head>
<body>
     <form action="list.shop?btype=${param.btype}" method="post" name="manform">
     <input type="hidden" name="btype" value="${param.btype}">

<c:if test="${param.btype==1}">
  <select name="man" style="width:100px;">
        <option value="">브랜드선택</option>
        <option value="1">현대</option>
        <option value="2">기아</option>
        <option value="3">르노삼성</option>
        <option value="4">BMW</option>
        <option value="5">한국GM</option>
        <option value="6">한불모터스</option>
        <option value="7">테슬라</option>
        <option value="8">재규어</option>
        <option value="9">벤츠코리아</option>
        <option value="10">쎄미시스코</option>
        <option value="11">기타</option>
        </select>
        <input type="submit" value="보기">
        <script type="text/javascript">
           document.manform.man.value="${param.man}";
        </script>
<h1>후기게시판</h1>
<p>자유롭게 전기차에 대해서 후기를 작성해주세요</p>
</c:if>

     <form action="list.shop?btype=${param.btype}" method="post" name="manform">
     <input type="hidden" name="btype" value="${param.btype}">
<c:if test="${param.btype==2}">
        <select name="man" style="width:100px;">
        <option value="">브랜드선택</option>
        <option value="1">현대</option>
        <option value="2">기아</option>
        <option value="3">르노삼성</option>
        <option value="4">BMW</option>
        <option value="5">한국GM</option>
        <option value="6">한불모터스</option>
        <option value="7">테슬라</option>
        <option value="8">재규어</option>
        <option value="9">벤츠코리아</option>
        <option value="10">쎄미시스코</option>
        <option value="11">기타</option>
        </select>
        <input type="submit" value="보기">
        <script type="text/javascript">
           document.manform.man.value="${param.man}";
        </script>
<h1>브랜드별 게시판</h1>
<p>브랜드별로 소개 및 궁금한 점 알려드려요</p>
</c:if></form>
<c:if test="${param.btype==3}">
<h1>NEWS</h1>
<p>뉴스 한번 보고가세요</p>
</c:if>
<table>
   <tr><td colspan="5">
    <div style="display : inline;">
     <form action="list.shop?btype="${param.btype} method="post" name="searchform">
        <input type="hidden" name="pageNum" value="1">
        <input type="hidden" name="btype" value="${param.btype}">
        <input type="hidden" name="man" value="${param.man}">
        <select name="searchtype" style="width:100px;">
        <option value="">선택하세요</option>
        <option value="title">제목</option>
        <option value="con">내용</option>
        </select>
        <script type="text/javascript">
           searchform.searchtype.value="${param.searchtype}";
        </script>
        <input type="text" name="searchcontent"
        value="${param.searchcontent}" style="width:250px;">
        <input type="submit" value="검색">
        <input type="button" value="전체게시물보기"
           onclick="location.href='list.shop?btype=${param.btype}'">
     </form></div></td></tr>
     
    <c:if test="${listcount > 0}"> <%--등록된 게시물이 있음 --%>
       <tr><td colspan="4">Ecar 게시판</td><td>글개수:${listcount}</td></tr>
       <tr><th>번호</th><th>제목</th><th>글쓴이</th><th>날짜</th><th>조회수</th></tr>
       <c:forEach var="board" items="${boardlist}">
          <tr><td>${listcount}</td>
             <c:set var="listcount" value="${listcount-1}" />
          <td style="text-align:left;">
          
          <c:if test="${! empty board.fileurl}"><%--첨부된 파일이 존재 --%>
          <a href="file/${board.fileurl}">@</c:if>
          <c:if test="${empty board.fileurl }">&nbsp;&nbsp;&nbsp;</c:if>
         
          <%--제목 부분 출력 --%>
          <a href="detail.shop?cnt=${board.cnt}&btype=${board.btype}">${board.title}</a></td>
             <td>${board.id}</td>
          <td><fmt:formatDate value="${board.date}" pattern="yyyyMMdd" var="rdate"/>
             <c:if test="${today == rdate}">
             <fmt:formatDate value="${board.date}" pattern="HH:mm:ss"/></c:if>
             <c:if test="${today != rdate}">
             <fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd HH:mm"/>
             </c:if></td><td>${board.readcnt}</td></tr>
       </c:forEach>
       
      <%-- 페이지 표시  --%>
          <tr><td colspan="5">
          <c:if test="${pageNum > 1}">
          <a href="javascript:listpage('${pageNum - 1}')">[이전]</a></c:if>
          <c:if test="${pageNum <= 1}">[이전]</c:if>
          <c:forEach var="a" begin="${startpage}" end="${endpage}">
             <c:if test="${a == pageNum}">[${a}]</c:if>
             <c:if test="${a != pageNum}">
             <a href="javascript:listpage('${a}')">[${a}]</a></c:if>
          </c:forEach>
          <c:if test="${pageNum < maxpage}">
             <a href="javascript:listpage('${pageNum +1}')">[다음]</a></c:if>
          <c:if test="${pageNum >= maxpage}">[다음]</c:if></td></tr>
    </c:if> <%-- 등록된 게시물이 있는 경우 끝 --%>
    
    <c:if test="${listcount < 0}"><%--등록된 게시물이 없는 경우 --%>
       <tr><td colspan="5">등록된 게시물이 없습니다.</td></tr></c:if>
    <tr><td colspan="5" align="right">
    <a href="write.shop?btype=${param.btype}">[글쓰기]</a></td></tr>
</table></body></html>