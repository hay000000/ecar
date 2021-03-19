<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>E-car</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<decorator:head />
<style>
html,body,h1,h2,h3,h4 {font-family:"Lato", sans-serif}
.w3-top {
    font-family: Verdana,sans-serif;
    font-size: 20px;
    line-height: 1.5;
}
.mySlides {display:none}
.w3-tag, .fa {cursor:pointer}
.w3-tag {height:15px;width:15px;padding:0;margin-top:6px}
</style>

</head>

<body>
<script type="text/javascript"
	src="http://cdn.ckeditor.com/4.5.7/standard/ckeditor.js"></script>
<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-card" id="myNavbar">
    <a href="/ecar/user/main.shop" class="w3-bar-item w3-button w3-wide">E-car</a>
         <c:if test="${empty sessionScope.login}">
        <a href="/ecar/member/loginForm.shop">로그인</a>
        <a href="/ecar/member/joinForm.shop">회원가입</a>
     </c:if>
    <!-- Right-sided navbar links -->
    <div class="w3-right w3-hide-small">
      <a href="/ecar/user/intro.shop" class="w3-bar-item w3-button"> 전기차소개</a>
      <a href="/ecar/user/oil.shop" class="w3-bar-item w3-button"> 전기차 충전소</a>
      <a href="/ecar/user/pricing.shop" class="w3-bar-item w3-button"> 보조지원금</a>
      <a href="${path}/board/list.shop?btype=1" class="w3-bar-item w3-button"> 후기 게시판</a>
      <a href="${path}/board/list.shop?btype=2" class="w3-bar-item w3-button"> 브랜드 게시판</a>
      <a href="${path}/board/list.shop?btype=3" class="w3-bar-item w3-button"> 관련 뉴스</a>

    </div>
    <!-- Hide right-floated links on small screens and replace them with a menu icon -->

    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="w3_open()">
      <i class="fa fa-bars"></i>
    </a>
  </div>
</div>

<!-- Content -->
<div class="w3-content" style="max-width:1100px;margin-top:80px;margin-bottom:80px"><decorator:body /></div>

<!--   <div class="w3-panel">
    <h1><b>MARKETING</b></h1>
    <p>Template by w3.css</p>
  </div> -->

 
</body>
</html>