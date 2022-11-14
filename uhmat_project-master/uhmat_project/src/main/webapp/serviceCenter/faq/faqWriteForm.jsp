<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice 글 등록</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
 	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />

	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="css/styles.css" rel="stylesheet" />
<style type="text/css">
</style>
</head>
<body>
	<!-- 헤더 들어가는 곳 -->
		<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->
 <section class="page-section" id="contact">
            <div class="container">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">FAQ 글 쓰기</h2>
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
		<form action="FAQWritePro.sc" method="post" enctype="multipart/form-data" name="boardform">
				<!-- 닉네임 -->
				<div class="form-floating mb-3">
				    <input class="form-control" type="text" name="nickname" id="nickname" value="${sessionScope.sNickName }" readonly="readonly" placeholder="닉네임을 입력해달라!" >
				    <label for="nickname">어맛인</label> 
				</div>
				
				<!-- 제목 -->
				<div class="form-floating mb-3">
				    <input class="form-control" type="text" name="subject" id="subject" placeholder="제목을 입력해주세요!" maxlength="20">
				    <label for="subject">제목</label>
				</div>
				
				<!-- 내용-->
				<div class="form-floating mb-3">
				   <textarea class="form-control" name="content" id="content"  placeholder="내용을 입력해주세요 !" style="height: 10rem" maxlength="2000"></textarea>
				   <label for="content">내용</label>
 				 </div>
 				 
 				 <div class="form-floating mb-3">
				    <input class="form-control" type="file" name="file" id="file" placeholder="제목을 입력해주세요!" >
				    <label for="file">파일첨부</label>
				</div>
				
			<div class="form-floating sm-3">
				<select name="category" class="form-select form-select-sm" aria-label=".form-select-sm example">
					<option value="오류신고">오류신고</option>
					<option value="음식점등록">음식점등록</option>
					<option value="지도 오류">지도 오류</option>
				</select>			
				<label for="category">카테고리</label>
			</div>
			
			
	
			<div align="right" >
				<input type="submit" class="btn btn-secondary" value="등록">
				<input type="reset" class="btn btn-secondary" value="다시쓰기" >
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