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
	
	
	<!-- 새로운 CSS 작업 -->
	 <!-- Contact Section-->
        <section class="page-section" id="contact">
            <div class="container" align="center">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">어맛 TMI 글쓰기</h2>
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
                        
                        <!-- 로그인시 댓글 작성 -->  
						<c:if test="${sessionScope.sNickName eq null }">
							<script type="text/javascript">
							alert("로그인을 해주세요 :)");
							history.back();
							</script>
						</c:if>
						<!-- 로그인시 댓글 작성 -->
                        
                        <form action="TmiWritePro.co" name=tmiBoardForm method="POST" >
                            <!-- 닉네임 -->
                            <div class="form-floating mb-3">
                                <input class="form-control" type="text" name="nickname" id="nickname" value="${sessionScope.sNickName }" readonly="readonly" placeholder="닉네임을 입력해달라!" >
                                <label for="nickname">어맛인</label> 
                            </div>
                            
                            <!-- 제목 -->
                            <div class="form-floating mb-3">
                                <input class="form-control" type="text" name="subject" id="subject" placeholder="제목을 입력해주세요!" maxlength="50">
                                <label for="subject">제목</label>
                            </div>
                            
                            <!-- 내용-->
                            <div class="form-floating mb-3">
                                <textarea class="form-control" name="content" id="content"  placeholder="내용을 입력해주세요 !" style="height: 10rem" maxlength="1000"></textarea>
                                <label for="content">내용</label>
                            </div>
                            
                            <div align="right" >
								<input type="submit" class="btn btn-secondary" value="등록">
								<input type="reset" class="btn btn-secondary" value="다시쓰기" >
								<input type="button" class="btn btn-secondary" value="메인" onclick="location.href='./'">
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