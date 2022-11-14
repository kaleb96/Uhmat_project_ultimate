<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/uhmatDetail.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- 헤더 들어가는곳 -->
		<jsp:include page="/inc/header.jsp" flush="false" />
	<!-- 헤더 들어가는곳 -->
	
	<!-- 본문 시작 -->
	<div id="uhmatDetailContainer">
	<div class="uhmatDetailMainLogo">
		<img src="/uhmat_project/image/uhmatMainLogo.png">
	</div>		
<!-- 		<h1>About Uhmat</h1> -->
	
	<!-- 본문 영역 -->
	<main class="pg-company">
	  <div class="company_wrapper">
	    <div class="title">
	
	      <div class="title-wrapper">
	        <p class="aboutUs">ABOUT US</p>
	        <!--<p class="line"></p>-->
	        <p class="small-word">맛있는 경험, 맛있는 삶.</p>
	      </div>
	    </div>
	    <div class="company-content-wrap">
	      <div class="vision">
	        <p class="orange-font">"믿을만한 맛집을 소개하고 추천하는 서비스?"</p>
	        <p>맛집을 사랑하는 공동 창업자 7명의 고민으로 시작된 어맛(어디가 맛집이고).</p>
	        <p>솔직한 리뷰와 정확한 정보를 바탕으로 맛집을 추천하는 서비스가 되고자 합니다.
	        </p>
	      </div>
	
	      <!-- product 영역 -->
	      <div class="product_wrapper">
	        <div class="company_section">
	          <p class="com_title">
	            <img src="https://mp-seoul-image-production-s3.mangoplate.com/web/resources/o3dd4csrbykbxw_g.png" alt="company font icon" width="70"/>
	          </p>
	        </div>
	        <p class="txt_white">위치 기반 맛집 검색 및 추천 서비스<br/> 모바일 앱과 웹에서 사용하실 수 있습니다.</p>
	        <div id="contact"></div>
	      </div>
			  
	      <p class="line"></p>
	      <p class="word">어맛은 항상 열려있습니다. <br/>궁금한 점이 있다면 언제든지 연락주세요!</p>
	      <p class="line bottom"></p>
	    </div>
	  </div>
	</main>
	
	
		
	
	</div>
	<!-- 본문 끝 -->
		
	<!-- 푸터 들어가는곳 -->
		<jsp:include page="/inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 -->	
</body>
</html>