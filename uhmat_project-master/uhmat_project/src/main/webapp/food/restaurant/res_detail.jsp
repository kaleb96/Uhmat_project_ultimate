<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="food/restaurant/res_detail.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body id="resDetailBody">
<jsp:include page="../../inc/header.jsp"></jsp:include>
	<section id="all">
    <h2>${resInfo.resName } <small style="font-size: 0.5em">별점 ${resInfo.rating } &nbsp; | &nbsp; <a href="ReviewList.re?resName=${resInfo.resName }">${resInfo.reviewCount }개의 리뷰</a></small></h2>
	<img src="upload/${resInfo.photo }" width="200">
	<!-- 식당 정보 출력 -->
        <section>
       			<p><i class="fa fa-phone-square" style="font-size:48px;color:red"></i> ${resInfo.phoneNumber }</p>
       			<p class="timeTable">월요일 | ${mon } </p>
       			<p class="timeTable">화요일 | ${tue } </p>
       			<p class="timeTable">수요일 | ${wed } </p>
       			<p class="timeTable">목요일 | ${thu } </p>
       			<p class="timeTable">금요일 | ${fri } </p>
       			<p class="timeTable">토요일 | ${sat } </p>
       			<p class="timeTable">일요일 | ${sun } </p>
       			<p class="timeTable"><strong>식당 상세정보 :</strong> <br>${resInfo.resInfo }</p>
            	<p class="timeTable"><strong>우편번호 : </strong><br>${resInfo.rPostcode}</p>
            	<p class="timeTable"><strong>도로명 : </strong><br>${resInfo.address }</p>
            <c:if test="${not empty resInfo.resLink }">
	       			<p class="timeTable"><strong>식당링크 : </strong><a href="${resInfo.resLink }">${resInfo.resLink }</a></p>
            </c:if>
        </section>
        
        <!-- 지도 정보 출력 -->
        <div id="map" style="width:100%;height:500px;"></div>
			<p><!-- 지도가 나오는 위치 --></p> 
			<div id="clickLatlng">
			</div>
				<input type="hidden" name="latitude" id="latitude">
				<input type="hidden" name="longitude" id="longitude">
			<br>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35185e429e5d9c68170c91b88e2d3a84"></script>
			<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(${map.latitude},${map.longitude}), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };
			var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
			// 지도를 클릭한 위치에 표출할 마커입니다
			var marker = new kakao.maps.Marker({ 
			    // 지도 중심좌표에 마커를 생성합니다 
			    position: map.getCenter() 
			}); 
			// 지도에 마커를 표시합니다
			marker.setMap(map);
			</script>
			
        <c:if test="${sessionScope.sNickName == 'admin' }">
	        <button onclick="location.href='restaurantDelete.re?resName=${resInfo.resName}'">글 삭제</button>
	        <button onclick="location.href='restaurantModifyForm.re?resName=${resInfo.resName}'">글 수정</button>
	        <button onclick="location.href='restaurantList.re'">글 전체 목록</button>
        </c:if>
        </section>
        <jsp:include page="../../inc/footer.jsp"></jsp:include>
</body>
</html>