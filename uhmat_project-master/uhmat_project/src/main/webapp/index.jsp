<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript" src="./js/jquery-3.6.0.js"></script>
<style type="text/css">


.flex-container {
	display: flex;
}

.flex-container>div {
	background-color: #f1f1f1;
	height:100%;
	width: 50%;
	padding: 20px;
	font-size: 30px;
	text-align: center;
	border: 1px solid;
	border-radius: 25px;
}

.login-container{
	background-color: #f1f1f1;
	height:700px;
	
	width:100%;
	font-size: 30px;

}
.buttonResult{
	background-color: #f2f2f2;
	width:100%;
	height:550px;
	padding: 20px;
	text-align: center;
	border: 1px solid;

}
.buttonResult2{
	background-color: #f2f2f2;
	width:100%;
	height:550px;
	padding: 20px;
	text-align: center;
	border: 1px solid;

}
#iframe1{

width: 350px;
height: 75px;
}
#iframe2{
height: 500px;

}
</style>
<script type="text/javascript">
$(function(){
	var div1 = document.getElementById('login');
	var div2 = document.getElementById('slmpl');
	$("#log").text("로그인");
	$("#slmpleLog").text("간편로그인");
	  div2.style.display = 'none';
	  div1.style.display = 'block';
	$("#log").click(function(){
		  div2.style.display = 'none';
		  div1.style.display = 'block';
		  // btn1 숨기기 (display: none)
		  if(div2.style.display !== 'none') {
			  div2.style.display = 'none';
		  }
		  // btn` 보이기 (display: block)
		  else {
			  div1.style.display = 'block';
		  }
		  
		
		
	});
	$("#slmpleLog").click(function(){
		  div1.style.display = 'none';
		  div2.style.display = 'block';
		  // btn1 숨기기 (display: none)
		  if(div1.style.display !== 'none') {
			  div1.style.display = 'none';
		  }
		  // btn` 보이기 (display: block)
		  else {
			  div2.style.display = 'block';
		  }
		  
		
		
	});
		  // 토글 할 버튼 선택 (btn1)
		 
		 
	
	
	
});

</script>
</head>
<body>

	<div class="login-container">
		<div class="flex-container">
			<div id="log"></div>
			<div id="slmpleLog" ></div>
		</div>
		<div class="buttonResult" id="slmpl" >
			<iframe name="iframe1" id="iframe1" src="MemberNaverForm.me"
				frameborder="0" cellspacing="0"></iframe>
				<br>
			<iframe name="iframe1" id="iframe1" src="MemberKakaoForm.me"
				frameborder="0" cellspacing="0"></iframe><br>
			<iframe name="iframe1" id="iframe1" src="MemberGoogleForm.me"
				frameborder="0" cellspacing="0"></iframe><br>
		</div>
		<div class="buttonResult2" id="login">
		<iframe name="iframe2" id="iframe2" src="MemberLoginForm.me"
				frameborder="0" cellspacing="0"></iframe>
		
		</div>
	</div>

	<c:choose>
		<c:when test="${empty sessionScope.sNickName}">
			<a href="MemberLoginForm.me">로그인</a> | <a href="MemberJoinForm.me">회원가입</a>
		</c:when>
		<c:otherwise>
			<%-- 하이퍼링크에 자바스크립트 함수 연결 시 href 속성에 아무 경로도 지정하지 않는 방법 --%>
			<a href="MemberDetailForm.me?nickName=${sessionScope.sNickName }">${sessionScope.sNickName }
				님 </a>  | <a href="MemberLogout.me">로그아웃</a>
			<%-- 세션 아이디가 "admin" 일 때만 관리자페이지 링크("AdminMain.me") 표시 --%>
			<c:if test="${sessionScope.sNickName eq 'admin'}"> | <a
					href="AdminMain.me">관리자페이지</a>
			</c:if>
		</c:otherwise>
	</c:choose>
	<hr>
	<nav>
		<details>
			<!-- 음식카테고리 리스트 -->
			<summary>음식카테고리</summary>
			<a href="restaurantList.re">음식으로 보기</a>
			<a href="ReviewList.re">리뷰로 보기</a>
			<a href="mapForm.re">지도로 보기</a>
			<a href="RestaurantInfo.re">음식으로 보기</a>
		</details>
	</nav>
<hr>


<c:if test="${sessionScope.sNickName eq 'admin' }">
	<h3> <a href="AllBoardListForm.ad">admin</a></h3>
</c:if>
<h3> <a href="AllBoardListForm.ad">admin</a></h3>
<h3> <a href="NoticeList.sc">Notice</a></h3>
<h3> <a href="FAQList.sc">FAQ</a></h3>
<h3><a href="MateWriteForm.co">어맛메이트 글쓰기</a></h3>	
<h3><a href="MateList.co">어맛메이트 글목록</a></h3>
<h3><a href="RecipeWriteForm.co">어맛레시피 쓰기</a></h3>
<br>
<h3><a href="TmiList.co">어맛Tmi 글목록</a></h3>




</body>
</html>
