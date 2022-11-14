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
	
	<!-- 헤더 들어가는 곳 -->
	<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->
	
	<!-- Contact Section-->
        <section class="page-section" id="contact">
            <div class="container" align="center">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">RECIPE 대댓글 쓰기</h2>
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
		
		<c:if test="${sessionScope.sNickName eq null }">
				<script type="text/javascript">
					alert("로그인을 하시오.");
					history.back();
				</script>
			</c:if>
		<form action="RecipeRereplyWrite.co" name="RereplyForm" method="post">
		<input type="hidden" name="idx" value="${param.reply_idx }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="board_idx" value="${param.idx }">
		<!-- 답글에 대한 원본글 정보 담고 있는 re_ref, re_lev, re_seq 도 전달 -->
		<input type="hidden" name="re_ref" value="${recipeRereply.re_ref }">
		<input type="hidden" name="re_lev" value="${recipeRereply.re_lev }">
		<input type="hidden" name="re_seq" value="${recipeRereply.re_seq }">
		<div class="form-floating mb-3">
				<input type="text" name="nickname" class="form-control" value="${sessionScope.sNickName }" readonly="readonly">
				<label for="nickname">어맛인</label> 
			</div>
			
			<div class="form-floating mb-3">
				<textarea class="form-control" rows="10" cols="70" placeholder="댓글을 작성하세요" name="content" style="height: 10rem" maxlength="1000"></textarea>
				<label for="message">내용</label>
			</div>
			
			<div align="right">
				<input type="submit" value="댓글 전송" class="btn btn-secondary">
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