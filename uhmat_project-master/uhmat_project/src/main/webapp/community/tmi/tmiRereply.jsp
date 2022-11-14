<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TMI 답글</title>
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
	<c:if test="${sessionScope.sNickName eq null }">
				<script type="text/javascript">
					alert("로그인을 하시오.");
					history.back();
				</script>
			</c:if>
	<!-- 헤더 들어가는 곳 -->
	<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->
	
	<!-- 게시판 답글 작성 -->
	 <!-- Contact Section-->
	      <section class="page-section" id="contact">
	          <div class="container" align="center">
	              <!-- Contact Section Heading-->
	              <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">TMI 답글</h2>
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
		<form action="TmiRereplyWritePro.co" name="TmiRereplyForm" method="post">
			<!-- 글번호와 페이지번호 전달 -->
			<input type="hidden" name="board_idx" value="${tmiRereply.board_idx }">
			<input type="hidden" name="idx" value="${tmiRereply.idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<!-- 답글에 대한 원본글 정보 담고 있는 re_ref, re_lev, re_seq 도 전달 -->
			<input type="hidden" name="re_ref" value="${tmiRereply.re_ref }">
			<input type="hidden" name="re_lev" value="${tmiRereply.re_lev }">
			<input type="hidden" name="re_seq" value="${tmiRereply.re_seq }">
				<div class="form-floating mb-3">
					<input class="form-control" type="text" name="nickname" id="nickname" value="${sessionScope.sNickName }" readonly="readonly" />
					<label for="nickname">어맛인</label>
				</div>
			
				<div class="form-floating mb-3">
					<textarea class="form-control" id="content" name="content" style="height: 10rem" required="required" maxlength="1000"></textarea>
					<label for="content">내용</label>
					
				</div>
				<div align="center" >
				<input type="submit" class="btn btn-secondary" value="답글등록">
				<input type="reset" class="btn btn-secondary" value="다시쓰기">
				<input type="button" class="btn btn-secondary" value="취소" onclick="history.back()">
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








