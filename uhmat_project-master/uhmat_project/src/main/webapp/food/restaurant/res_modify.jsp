<%@page import="vo.MapDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당 글쓰기 </title>
<style type="text/css">
#writeForm{
	width:500px;
	border: 1px solid red;
	margin: auto;
}
h2{
	text-align: center;
}
table{
	margin:auto;
	width:450px;
}
.td_left{
	width:150px;
	background: orange;
}
.td_right{
	width: 300px;
	background: skyblue;
}
#commandCell{
	text-align: center;
}
</style>
<script src="js/jquery-3.6.0.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35185e429e5d9c68170c91b88e2d3a84"></script>
<script>
	//ready event
	$(function(){	//사진 변경 시 사진 미리보기 출력
		$('#photo').change(function(){
		    setImageFromFile(this, '#image');
		});
		function setImageFromFile(input, expression) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        reader.onload = function (e) {
		            $(expression).attr('src', e.target.result);
		        }
		        reader.readAsDataURL(input.files[0]);
		    }
		}
		
		//더보기 클릭 시 음식 카테고리 선택 가능
		$("#more").click(function(){
// 			alert("버튼 선택!");
			$.ajax({
				url:"food/restaurant/category_show.jsp",
				success: function(res){
					$("#showCategory").html(res);
				}
			})
		})
		
	});
	
	// 다음 우편번호 API
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById('sample4_roadAddress').value = roadAddr;
						//                 document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
					}

				}).open();
	}
	
	$(function(){	//휴무 버튼 선택시 시간 재조정
		$("#openTime button").on("click",function(){
// 			alert($("#openTime button").index(this));
			var index = $("#openTime button").index(this);
// 			alert($("input[name=opentime]").eq(0).prop("value"));
			$("input[name=opentime]").eq(index).attr("value","");
			$("input[name=closetime]").eq(index).attr("value","");
		});
	});
	
	//카테고리를 미리 선택해두기!
	window.onload = function(){
// 		alert($("select option").eq(0).text());
		$('select option[value="${resInfo.category}"]').attr('selected',true);
	}
	
</script>
</head>
<body>
	<!-- 
	private String resName;	//식당 이름
	private String rPostcode;	//우편번호
	private String address;	//상세 주소 
	private String resInfo; //식당 상세내용
	private String phoneNumber; //식당 번호
	private String opentime; //영업 시간
	private String resLink; //레스토랑의 웹사이트 링크
	private String photo; //식당 사진
	private float rating;	//별점
	private int reviewCount; //식당 리뷰 개수
	dto 객체 이름 : resInfo
	 -->
	 <jsp:include page="../../inc/header.jsp"></jsp:include>
	<c:if test="${sessionScope.sNickName ne 'admin'}">
		<script>
			alert("관리자가 아닙니다!");
			history.back();
		</script>
	</c:if>
	<section id="writeForm">
		<h2>식당 글 수정 ${resInfo.rPostcode }${resInfo.address }</h2>
		<div id="showCategory">
		</div>
		<form action="restaurantModifyPro.re" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${resInfo.photo }" name="originalPhoto">
			<table>
				<tr>
					<th><label for="category">카테고리 선택</label></th>
					<td>
						<select name="category" required="required">
							<option value="" selected="selected">선택</option>
							<option value="족발·보쌈">족발·보쌈</option>
							<option value="찜·탕·찌개">찜·탕·찌개</option>
							<option value="돈까스·회·일식">돈까스·회·일식</option>
							<option value="피자">피자</option>
							<option value="고기·구이">고기·구이</option>
							<option value="치킨">치킨</option>
							<option value="중식">중식</option>
							<option value="도시락">도시락</option>
							<option value="패스트푸드">패스트푸드</option>
							<option value="분식1">분식1</option>
							<option value="분식2">분식2</option>
							<option value="카페·디저트">카페·디저트</option>
						</select>
						<input type="button" id="more" value="카테고리 보기">
					</td>
				</tr>
				<tr>
					<th><label for="res_name">식당이름</label></th>
					<td><input type="text" name="res_name" id="res_name" required="required" value="${resInfo.resName }" readonly="readonly"></td>
					<!-- 식당 이름이 Foreign key 로 걸려 있기에 수정 불가로 설정! -->
				</tr>
<!-- 				<tr> -->
<!-- 					<th><label for="r_postcode">우편번호</label></th> -->
<%-- 					<td><input type="text" name="r_postcode" id="r_postcode" required="required" value="${resInfo.rPostcode }"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<th><label for="address">상세주소</label></th> -->
<%-- 					<td><input type="text" name="address" id="address" required="required" value="${resInfo.address }"></td> --%>
<!-- 				</tr> -->
				<!--  다음 API 로 교체함 -->
				<tr>
           		   	<th><label for="r_postcode">우편번호</label></th>
					<td>
	           		 	<input class="form-control" type="text" name="r_postcode" id="sample4_postcode" readonly="readonly" value="${resInfo.rPostcode }"  required="required" onclick="execDaumPostcode()" >
						<input class="w-btn-outline w-btn-green-outline"  type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
					</td>
				</tr>
				<tr>
            		<th><label for="address">도로명주소</label></th> 
           			<td><input class="form-control" type="text" name="address" id="sample4_roadAddress" value="${resInfo.address }"  readonly="readonly" required="required" onclick="execDaumPostcode()" ></td>
				</tr>
				<tr>
					<th><label for="phone_number">식당 전화번호</label></th>

					<td><input type="tel" name="phone_number" value="${resInfo.phoneNumber }"></td>

				</tr>
				
				<tr>
					<th><label for="res_info">식당 상세 정보</label></th>
					<td><textarea id="res_info" name="res_info" cols="40" rows="15" required="required">${resInfo.resInfo }</textarea></td>
				</tr>
				<tr>
					<th><label for="photo">식당 사진</label></th>

					<td><input name="photo" type="file" id="photo"  accept="image/*" value="${resInfo.photo }"></td>

				</tr>
					
				<tr>
					<th><label for="res_link">식당 웹사이트 링크</label></th>
					<td><textarea id="res_link" name="res_link" cols="40" rows="15">${resInfo.resLink }</textarea></td>
				</tr>
			</table>
			<h3>등록될 이미지 미리보기</h3>
			<img src="upload/${resInfo.photo }" alt="등록된 이미지 없음" id="image" width="300">
			<h3 style="text-align: center"> 영업 시간 등록</h3> 
					(휴무시 선택X)<br>
					<b>월</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <button type="button" class="rest">휴무</button> <br>
					<b>화</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <button type="button" class="rest">휴무</button> <br>
					<b>수</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <button type="button" class="rest">휴무</button> <br>
					<b>목</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <button type="button" class="rest">휴무</button> <br>
					<b>금</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <button type="button" class="rest">휴무</button> <br>
					<b>토</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <button type="button" class="rest">휴무</button> <br>
					<b>일</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <button type="button" class="rest">휴무</button> <br>
					<br>
					
			<!-- 
			private String resName;		//식당 이름
			private double longitude;	//경도
			private double latitude;	//위도
			 -->
			<h3>지도 위치 입력!</h3>
			<div id="map" style="width:100%;height:350px;"></div>
			<p><em>지도를 클릭해주세요!</em></p> 
			<div id="clickLatlng">
			</div>
				<input type="hidden" name="latitude" id="latitude" value=${map.latitude }>
				<input type="hidden" name="longitude" id="longitude" value=${map.longitude }>
			<br>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35185e429e5d9c68170c91b88e2d3a84"></script>
			<script>
			var latitude=35.1584642;
			var longitude=129.0620414;
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			<%
			MapDTO map = (MapDTO)request.getAttribute("map");
			if(map==null||(map.getLatitude())==0||map.getLongitude()==0){%>
				//위치정보가 존재하지 않을시 기본값을 출력
			<%}else{%>
				longitude = ${map.longitude}
				latitude = ${map.latitude}				
			<%}%>
		    mapOption = { 
		        center: new kakao.maps.LatLng(latitude,longitude), // 지도의 중심좌표
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
	
			// 지도에 클릭 이벤트를 등록합니다
			// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
			kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
			    
			    // 클릭한 위도, 경도 정보를 가져옵니다 
			    var latlng = mouseEvent.latLng; 
			    
			    // 마커 위치를 클릭한 위치로 옮깁니다
			    marker.setPosition(latlng);
			    
			    var message = '위도는 ' + latlng.getLat() + ' 이고, ';
			    message += '경도는 ' + latlng.getLng() + ' 입니다';
			    
			    var resultDiv = document.getElementById('clickLatlng'); 
			    resultDiv.innerHTML = message;
			    $("#latitude").val(latlng.getLat());
			    $("#longitude").val(latlng.getLng());
			    
			});
			</script>
			
			

			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
	<jsp:include page="../../inc/footer.jsp"></jsp:include>
</body>
</html>