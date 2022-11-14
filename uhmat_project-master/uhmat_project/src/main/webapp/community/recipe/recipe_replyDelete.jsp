<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
	<!-- 헤더 들어가는 곳 -->
	<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->

	<!-- 삭제 시 닉네임 요청 -->
	<section class="page-section" id="contact">
            <div class="container" align="center">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">MATE 댓글 삭제</h2>
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

	<form action="RecipeReplyDeletePro.co" name="deleteRecipeReplyForm" method="post">
		<input type="hidden" name="idx" value="${param.idx }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="reply_idx" value="${param.reply_idx }">
		<input type="hidden" name="nickname" value="${param.nickname}">
	<h1>댓글 삭제 하시겠습니까?</h1>
		<div class="form-floating mb-3">
			<input type="text" class="form-control" name="nickname" id="nickname" value="${sessionScope.sNickName }" readonly="readonly">
			<label for="nickname">닉네임</label>
		</div>
		<div class="form-floating mb-3">
			<input type="submit" class="btn btn-secondary" value="삭제">&nbsp;&nbsp;
			<input type="button" class="btn btn-secondary" value="돌아가기" onclick="javascript:history.back()">
		</div>
	</form>
	</div>
	</div>
	</div>
	</section>
	
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 푸터 들어가는 곳 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>