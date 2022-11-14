<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />

	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="css/styles.css" rel="stylesheet" />
	
<style type="text/css">
	.form-control {
		font-family: 'lato';
	}
</style>

</head>
<body>
	<!-- 헤더 자리 -->
	<!-- 헤더 들어가는 곳 -->
	<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->
	
	<!-- 삭제 시 닉네임 요청 -->
	<section class="page-section" id="contact">
            <div class="container" align="center">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">TMI 글 삭제</h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Contact Section Form-->
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-7">
                     <!-- * * * * * * * * * * * * * * *-->
                     <!-- * * SB Forms Contact Form * *-->
                     <!-- * * * * * * * * * * * * * * *-->
                     <!-- This form is pre-integrated with SB Forms.-->
                     <!-- To make this form functional, sign up at-->
                     <!-- https://startbootstrap.com/solution/contact-forms-->
                     <!-- to get an API token!-->
		<form action="TmiDeletePro.co" name="deleteTmiForm" method="post">
			<!-- 입력받지 않은 글번호(board_num)와 페이지번호(pageNum)도 함께 포함시켜 전달 -->
			<input type="hidden" name="idx" value="${param.idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<div class="form-floating mb-3">
					<input type="text" class="form-control" name="nickname" id="nickname" value="${sessionScope.sNickName }" readonly="readonly">
					<label for="nickname">닉네임</label>
			</div>
				
			<div align="right" >
				<input type="submit" class="btn btn-secondary" value="삭제">&nbsp;&nbsp;
				<input type="button" class="btn btn-secondary"  value="돌아가기" onclick="javascript:history.back()">
			</div>
		</form>
		 </div>
    	 </div>
    	 </div>
	</section>
	
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 푸터 들어가는 곳 -->
	
</body>
</html>