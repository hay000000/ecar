<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전기차 소개</title>
<link rel="stylesheet" href="${path}/css/main.css">
</head>
<body>
<h><font size=10>전기차 소개</font></h>
<table>
<tr>
<td><font size=10>></font>전기차는 고전압 배터리에서 전기에너지를 전기모터로 공급하여 구동력을 발생시키는 차량으로, 화석연료를 전혀 사용하지 않는 무공해 차량입니다.</td>
</tr>
<tr>
<td rowspan=3><div><img src="../img/ecar.png" style="width:100%"></div></td>
<td colspan=2>ㆍ내연기관차와 달리 엔진이 없이 배터리와 모터만으로 차량 구동<br>
ㆍ엔진이 없으므로 대기오열물질과 온실가스를 배출하지 않음<br>
ㆍ배터리 용량에 따라 주행가능 거리에 차이가 있음
</td>
</tr>
</table>
<table>
<tr>
<td><font size=8>전기차 특징</font></td>
</tr>
<tr>
<td><font size=5>친환경적</font><br>
- 주행시 화석연료를 사용하지 않아 CO2나 NOx를 배출하지 않음<br>
- 엔진 소음이 적고, 진동이 적음
</td>
</tr>
<tr>
<td><font size=5>경제적</font><br>
- 전기모터로만 구동할 경우 운행비용이 가장 저렴하고, 심야 전기를 이용할 경우 비용을 더 낮출 수 있음<br>
- 차량 수명이 상대적으로 김
</td>
</tr>
<tr>
<td><font size=5>안전성</font><br>
-사고 시 폭발의 위험성이 적음
</td>
</tr>
<tr>
<td><font size=5>편의성</font><br>
- 심야 전력으로 자택에서 충전 가능
- 기어를 바꿔줄 필요가 없어 운전 조작이 간편
</td>
</tr>
</table>
</body>
</html>