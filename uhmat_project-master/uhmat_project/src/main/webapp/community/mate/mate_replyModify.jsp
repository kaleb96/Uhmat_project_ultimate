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
	<style type="text/css">
   	  .form-control {
      font-family: 'lato';
   }
</style>
</head>
<body>
	<!-- 헤더 들어가는 곳 -->
		<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->
	<!-- 게시판 등록 -->
	 <!-- Contact Section-->
        <section class="page-section" id="contact">
            <div class="container" align="center">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">어맛 MATE 댓글 수정</h2>
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
	
	<form action="MateReplyModifyPro.co" name="MateReplyForm" method="post">
		<input type="hidden" name="idx" value="${param.idx }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="reply_idx" value="${param.reply_idx }">
		<input type="hidden" name="nickname" value="${param.nickname }">
		
			<div class="form-floating mb-3">
				<input type="text" id="nickname" name="nickname" value="${sessionScope.sNickName }" readonly="readonly"  class="form-control">
				<label for="nickname">어맛인</label>
			</div>	
			<div class="form-floating mb-3">	
				<textarea  class="form-control" rows="15" cols="40" id="content" name="content" style="height: 10rem" required="required"></textarea>
				<label for="message">내용</label>
			</div>		
				
			
			<div align="right">
				<input type="submit" value="수정" class="btn btn-secondary">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn btn-secondary">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()" class="btn btn-secondary">
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