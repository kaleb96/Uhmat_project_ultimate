<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>닫기가 가능한 커스텀 오버레이</title>
<link href="css/map/map.css" rel="stylesheet">
<script type="text/javascript">
window.onload = function(){
	var mapContainer = document.getElementById('map'), // 지도의 중심좌표
    mapOption = { 
        center: new kakao.maps.LatLng(35.1584642,129.0620414), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    }; 
	
		 var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	

		$("#list").empty();
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		$.ajax({
			type: "get",
			url: "map.re",
			data : {keyword:$('#keyword').val()}, 
			dataType: "json",
			success : function(data){
				
				if(data==""){
					alert("존재 하지않는 음식점 이름 입니다.");
				}
				$.each(data, function(key, val){
		             <!-- 로그 찍어주는 부분 -->
		             var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		             <!-- 로그 찍어주는 부분 -->
		             // 지도에 마커를 표시합니다 
		             var marker = new kakao.maps.Marker({
		                 map: map, 
		                 position: new kakao.maps.LatLng(val['latitude'], val['longitude'])
		             });

		             // 커스텀 오버레이에 표시할 컨텐츠 입니다
		             // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		             // 별도의 이벤트 메소드를 제공하지 않습니다 
		            

		             // 마커 위에 커스텀오버레이를 표시합니다
		             // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		            content=  '<div class="wrap">' + 
		            '    <div class="info">' + 
		            '        <div class="title"><b>' + 
		        
		            val['resName']+ 
		            '        </b></div>' + 
		            '        <div>' + 
		            '            <div>' +
		            '                <img style="float:left;" src="upload/'+ val['photo'] +'" width="73" height="70">' +val['address']+'(우)'+ val['rPostcode'] + 
		            '           </div>' + 
// 		            '			<div><a href="'+ val['resLink'] +'" target="_blank" class="link">홈페이지</a></div>	'
// 		            '            <div>' +val['address']+
// 		            '                <div >(우)'+ val['rPostcode'] + 
// 		            '                <div><a href="'+ val['resLink'] +'" target="_blank" class="link">홈페이지</a></div>' + 
// 		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>';
		        var placePosition = new kakao.maps.LatLng(val['latitude'], val['longitude']);
		         
		        var infowindow = new kakao.maps.InfoWindow({
		            content:content // 인포윈도우에 표시할 내용
		        });


		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		        // LatLngBounds 객체에 좌표를 추가합니다

		        // 마커와 검색결과 항목에 mouseover 했을때
		        // 해당 장소에 인포윈도우에 장소명을 표시합니다
		        // mouseout 했을 때는 인포윈도우를 닫습니다
		       
		        kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow,val['resName']));
		        kakao.maps.event.addListener(marker, 'mouseover', makeOutListener(infowindow));
		         

		             // 커스텀 오버레이에 표시할 컨텐츠 입니다
		             // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		             // 별도의 이벤트 메소드를 제공하지 않습니다 
		            

		             // 마커 위에 커스텀오버레이를 표시합니다
		             // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		             
		             //왼쪽 영역에 입력되는 값
		            content=  '<div>' + 
		            '    <div>' + 
		            '        <div>' + 
		            val['resName']+ 
		            '        </div>' + 
		            '        <div>' + 
		            '            <div >' +
		            '                <img src="upload/'+ val['photo'] +'" width="100" height="70">' +
		            '           </div>' + 
		            '            <div>' + 
		            '                <div >'+val['address']+'</div>' + 
		            '                <div >(우)'+ val['rPostcode']  
		            '                <div><a href="'+ val['resLink'] +'" target="_blank" class="link">홈페이지</a></div>' + 
		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>';
		      		$("#list").append(content + "<hr>");
		         
		         });
				function makeOverListener(map, marker, infowindow,resName) {
				    return function() {
// 				    	alert(resName);
				    	 $.ajax({
					   			type: "post",
					   			url: "ReviewList.re",	//리뷰를 좋아요 순으로 가져오는 요청
					   			data:{
					   				resName : resName
					   			},
					   			dataType: "text",
					   			async : false,
					   			success: function(response) {
				  					var content = $("#list").html(response).find("#list");
//				   					alert(response);
				  					$("#list").html(content);
					   		   }
					   		});
				        infowindow.open(map, marker);
				    };
				}

				// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
				function makeOutListener(infowindow) {
				    return function() {
				        infowindow.close();
				    };
				}
},
	error : function() {
		alert('error');
	}
			
		});			
	
}
</script>
</head>
<body>

	<!-- 헤더 들어가는곳 -->
<%-- 	<jsp:include page="../../inc/header.jsp" flush="false" /> --%>
<div class="mainLogo">
    		<a href="/"><img src="image/uhmatMainLogo.png"></a>
    	</div>
		<!-- 로고 들어가는 곳 끝 -->
		
		<!-- 로그인 및 회원가입 부분 시작 -->
		<div class="loginPart">
		<c:choose>
			<c:when test="${empty sessionScope.sNickName}">
				<a href="MemberLogin.me">로그인</a>&nbsp; <a href="MemberJoinForm.me">회원가입</a>
			</c:when>
			<c:otherwise>
				<a href="MemberDetailForm.me?nickName=${sessionScope.sNickName }">${sessionScope.sNickName }
					님 </a>&nbsp; <a href="MemberLogout.me">로그아웃</a>
				<c:if test="${sessionScope.sNickName eq 'admin'}"> &nbsp; <a
						href="AdminMain.ad">관리자페이지</a>
				</c:if>
			</c:otherwise>
		</c:choose>
		</div>
	<hr id="hdLine">
	<!-- 헤더 들어가는곳 -->
	<!-- 전체 -->
	<div class="flex-container">
	<div class="left">
		<section id="searchTab">
<!-- 		<input type="button" value="홈" onclick="location.href='index.jsp'"> -->
			<section id="insideTab">
			<span>Uhmat Map </span><br>
			<input type="text" placeholder="음식적을 검색하세요" id="keyword"
				name="keyword" val size="15"> <input class="btn" type="button" value="검색"
				id="keywordSelect" >
			<input class="btn" type="button" value="음식점 전체 보기"
			onclick="location.href='mapForm.re'">
			</section>
		</section>
				<!-- 왼쪽 부분 -->
				<div id="list">
					<table>
					
					
					</table>
				</div>
	
	</div>
	<div class="right">
	<div id="map" style="width: 100%; height: 800px;"></div>
	
	</div>
	</div>
	
	
	<!-- 푸터 들어가는곳 -->
<%-- 	<jsp:include page="../../inc/footer.jsp" flush="false" /> --%>
	<!-- 푸터 들어가는곳 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35185e429e5d9c68170c91b88e2d3a84"></script>
	<script type="text/javascript" src="./js/jquery-3.6.0.js"></script>
	<script>
 

	
	
$(function(){
$("#keywordSelect").on("click", function() {
		$("#list").empty();
		var mapContainer = document.getElementById('map'), // 지도의 중심좌표
	    mapOption = { 
	        center: new kakao.maps.LatLng(35.1584642,129.0620414), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    }; 
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		$.ajax({
			type: "get",
			url: "map.re",
			data : {keyword:$('#keyword').val()}, 
			dataType: "json",
			success : function(data){
				
				if(data==""){
					alert("존재 하지않는 음식점 이름 입니다.");
				}
				$.each(data, function(key, val){
		             <!-- 로그 찍어주는 부분 -->
		             var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		             <!-- 로그 찍어주는 부분 -->
		             // 지도에 마커를 표시합니다 
		             var marker = new kakao.maps.Marker({
		                 map: map, 
		                 position: new kakao.maps.LatLng(val['latitude'], val['longitude'])
		             });

		             // 커스텀 오버레이에 표시할 컨텐츠 입니다
		             // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		             // 별도의 이벤트 메소드를 제공하지 않습니다 
		            

		             // 마커 위에 커스텀오버레이를 표시합니다
		             // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		            content=  '<div class="wrap">' + 
		            '    <div class="info">' + 
		            '        <div class="title">' + 
		        
		            val['resName']+ 
		            '        </div>' + 
		            '        <div>' + 
		            '            <div >' +
		            '                <img src="upload/'+ val['photo'] +'" width="73" height="70">' +
		            '           </div>' + 
		            '            <div>' + 
		            '                <div >'+val['address']+'</div>' + 
		            '                <div >(우)'+ val['rPostcode']  
		            '                <div><a href="'+ val['resLink'] +'" target="_blank" class="link">홈페이지</a></div>' + 
		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>';
		        var placePosition = new kakao.maps.LatLng(val['latitude'], val['longitude']);
		         
		        var infowindow = new kakao.maps.InfoWindow({
		            content:content // 인포윈도우에 표시할 내용
		        });


		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		        // LatLngBounds 객체에 좌표를 추가합니다

		        // 마커와 검색결과 항목에 mouseover 했을때
		        // 해당 장소에 인포윈도우에 장소명을 표시합니다
		        // mouseout 했을 때는 인포윈도우를 닫습니다
		       
		        kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow, val['resName']));
		        kakao.maps.event.addListener(marker, 'mouseover', makeOutListener(infowindow));
		         

		             // 커스텀 오버레이에 표시할 컨텐츠 입니다
		             // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		             // 별도의 이벤트 메소드를 제공하지 않습니다 
		            

		             // 마커 위에 커스텀오버레이를 표시합니다
		             // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		            content=  '<div>' + 
		            '    <div>' + 
		            '        <div><br> <b>' + 
		            val['resName']+ 
		            '        </b></div>' + 
		            '        <div>' + 
		            '            <div >' +
		            '                <img src="upload/'+ val['photo'] +'" width="73" height="70">' +
		            '           </div>' + 
		            '            <div>' + 
		            '                <div >'+val['address']+'</div>' + 
		            '                <div >(우)'+ val['rPostcode']  
		            '                <div><a href="'+ val['resLink'] +'" target="_blank" class="link">홈페이지</a><br></div>' + 
		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>';
		      		$("#list").append(content);
		         
		         });
				function makeOverListener(map, marker, infowindow,resName) {
				    return function() {
// 				    	alert(resName);
				    	 $.ajax({
					   			type: "post",
					   			url: "ReviewList.re",	//리뷰를 좋아요 순으로 가져오는 요청
					   			data:{
					   				resName : resName
					   			},
					   			dataType: "text",
					   			async : false,
					   			success: function(response) {
				  					var content = $("#list").html(response).find("#list");
//				   					alert(response);
				  					$("#list").html(content+"<hr>");
					   		   }
					   		});
				    	 
				        infowindow.open(map, marker);
				    };
				}

				// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
				function makeOutListener(infowindow) {
				    return function() {
				        infowindow.close();
				    };
				}
},
	error : function() {
		alert('error');
	}
			
		});			
	});
	
// 	$.AJAX({ 
// 		TYPE: "GET",
// 		URL: "HTTP://LOCALHOST:8080/UHMAT_PROJECT/MAP.RE",
// 		DATA : {KEYWORD:$('#KEYWORD').VAL()}, 
// 		DATATYPE: "JSON",
// 		SUCCESS : FUNCTION(DATA) {
			
		         
// 				$.EACH(DATA, FUNCTION(KEY, VAL){
				
						
// 			         });
		
// 	},
// 	ERROR : FUNCTION() {
// 		ALERT('ERROR');
// 	}
	
// 	});
});

</script>
<jsp:include page="../../inc/footer.jsp"></jsp:include>
</body>
</html>




