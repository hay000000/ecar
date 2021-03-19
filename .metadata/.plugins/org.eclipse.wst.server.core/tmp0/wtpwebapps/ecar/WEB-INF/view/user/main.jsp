<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>다음 지도 API</title>
<style>
    .wrap {position: absolute;left: 0;bottom: 40px;width: 400px;height: 100px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 400px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
    .info .close:hover {cursor: pointer;}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #5085BB;}
</style>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>

</head>

<body>
	<div id="map" style="width: 750px; height: 350px;"></div>

	<script
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a17f98afa9ca291cceb19b84cb97d30f"></script>
	<script>
	var mapContainer = document.getElementById('map'),
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
 

<!-- 다중마커 포지션 지정-->
var positions = [
<c:forEach var="a" begin="0" end="${fn:length(list)-1}" step="4" varStatus="stat">
	{		
		title :'dd',
		content:'<div class="wrap">'+
		'<div class="info">'+
		'<div class="title">' + 
		'${list.get(a)}'+
		'</div>	'+
		'<div class="body">'+
		'<div class="dec">'+
		'<div class="ellipsis">${list.get(a+1)}</div>'+
		'</div>'+
		'</div>'+
		'</div>'+
		'</div>',
		latlng: new kakao.maps.LatLng(${list.get(a+2)},${list.get(a+3)})
	},
</c:forEach>
		
];
<!-- 다중마커 생성 -->
for (var i = 0; i < positions.length; i ++) {
    // 마커를 생성합니다
    var data = positions[i];
    displayMarker1(data);
 
}
function displayMarker1(data){
	var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng // 마커의 위치
    });


    // 마커에 표시할 인포윈도우를 생성합니다 

    var overlay = new kakao.maps.CustomOverlay({
    	title: positions[i].title,
	    content: positions[i].content,
	    map: null,
	    position: marker.getPosition()       
	});

    var content = document.createElement('div');
    content.innerHTML =  positions[i].content;
    content.style.cssText = 'background: white; border: 1px solid black';
    
    var closeBtn = document.createElement('button');
    closeBtn.innerHTML = '닫기';
    closeBtn.onclick = function () {
        overlay.setMap(null);
    };
    content.appendChild(closeBtn);
    overlay.setContent(content);

    


	 // 마커에 클릭이벤트를 등록합니다

	 kakao.maps.event.addListener(marker, 'click', makeClick(map, marker, overlay));
}
<!-- 생성된 인포윈도우를 저장하기위한 배열 -->
var openmarkers = [];
<!-- GPS -->
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
        
        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">현재 위치</div>'; // 인포윈도우에 표시될 내용입니다
        
        // 마커와 인포윈도우를 표시합니다
        displayMarker(locPosition, message);
            
      });
    
} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    
    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = 'geolocation을 사용할수 없어요..'
        
    displayMarker(locPosition, message);
}
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; // 마커이미지의 주소입니다    
var imageSize = new kakao.maps.Size(24, 35); // 마커이미지의 크기입니다


var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize)
// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 

<!-- 다중마커 생성 및 열린마커 인포윈도우 닫기-->
 function makeClick(map,marker,overlay){
	 return function(){
		 openmarkers.forEach(function(each) { each.setMap(null); });
		 overlay.setMap(map);
		 openmarkers.push(overlay);
	 }
 }
 function closeOverlay() {
	 return function(){
		 openmarkers.forEach(function(each) { each.setMap(null); });
		 overlay.setMap(null);
		 openmarkers.push(overlay);
	 }
}
 <!--GPS 이미지 마커 생성 -->
 function displayMarker(locPosition, message) {

	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({  
	        map: map, 
	        position: locPosition,
	        image: markerImage
	    }); 
	    
	    var iwContent = message, // 인포윈도우에 표시할 내용
	        iwRemoveable = true;

	    // 인포윈도우를 생성합니다
	    var infowindow = new kakao.maps.InfoWindow({
	        content : iwContent,
	        removable : iwRemoveable
	    });
	    
	    // 인포윈도우를 마커위에 표시합니다 
	    infowindow.open(map, marker);
	    openmarkers.push(infowindow);
	    // 지도 중심좌표를 접속위치로 변경합니다
	    map.setCenter(locPosition);      
	}    
 

	</script>
	<img
		src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png">현재위치


	<!-- 바 차트 -->
	<div style="width: 75%">
		<canvas id="canvas"></canvas>
	</div>
	<script type="text/javascript">
var randomColorFactor = function(){
	return Math.round(Math.random() * 255);
}
var randomColor = function(opacity){
	return "rgba("+randomColorFactor()+","
			+ randomColorFactor() +","
			+ randomColorFactor() +","
			+ (opacity || '.3')+")";
};


var randomColor = function(opacity){
	return "rgba("+randomColorFactor()+","
			+ randomColorFactor() +","
			+ randomColorFactor() +","
			+ (opacity || '.3')+")";
};
var barChartData = {
		labels : [<c:forEach  var="m" begin="0" end="${fn:length(listmap)-1}" step="1">
		"${listmap.get(m).get("date")}",</c:forEach>],
		datasets : [
            <c:forEach var="m" items="${list1}">
			{
			label : "${m}",
			backgroundColor:randomColor(1),
			borderWidth : 1,
			data:[<c:forEach var="map" items="${listmap}">
			"${map[m]}",
	</c:forEach>]			
		},
			</c:forEach>
		]
}
window.onload=function(){
	var ctx = document.getElementById("canvas").getContext("2d")
	window.myBar = new Chart(ctx,{
		type : 'bar',
		data : barChartData,
		options : {
			responsive : true, //반응형 웹형태로
			//top,left,right,bottom
			legend : {position : 'top'}, //범례의 위치 지정
			title : {
				display : true,
				text : '막대 그래프 연습'
			}
		}
	})
}
</script>
</body>
</html>