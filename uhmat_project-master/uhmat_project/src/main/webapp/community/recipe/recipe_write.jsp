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

<script type="text/javascript">
function fileCheck(obj) {
    pathpoint = obj.value.lastIndexOf('.');
    filepoint = obj.value.substring(pathpoint+1,obj.length);
    filetype = filepoint.toLowerCase();
    if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp') {

        // 정상적인 이미지 확장자 파일일 경우 ...

    } else {
        alert('이미지 파일만 선택할 수 있습니다.');
		history.back();
        parentObj  = obj.parentNode
        node = parentObj.replaceChild(obj.cloneNode(true),obj);

        return false;
    }
    if(filetype=='bmp') {
        upload = confirm('BMP 파일은 웹상에서 사용하기엔 적절한 이미지 포맷이 아닙니다.\n그래도 계속 하시겠습니까?');
        if(!upload) return false;
    }
}

</script>
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
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">RECIPE 글쓰기</h2>
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
		<!-- 
		form 데이터 중 파일 정보가 포함될 경우
		<form> 태그 속성에 enctype="multipart/form-data" 명시 필수!
		(생략 시 enctype="application/x-www-form-urlencoded" 속성이 기본값으로 설정됨)
		-->
		<c:if test="${sessionScope.sNickName eq null }">
				<script type="text/javascript">
					alert("로그인을 하시오.");
					history.back();
				</script>
			</c:if>
		<form action="RecipeWritePro.co" name="recipeForm" method="post" enctype="multipart/form-data">
			<!-- 닉네임 -->
            <div class="form-floating mb-3">
            	<input class="form-control" type="text" name="nickname" id="nickname" placeholder="닉네임을 입력해달라!" value="${sessionScope.sNickName }" readonly="readonly">
                <label for="nickname">어맛인</label> 
            </div>
            <!-- 제목 -->
            <div class="form-floating mb-3">
                 <input class="form-control" type="text" name="subject" id="subject" placeholder="제목을 입력해주세요!" maxlength="50">
                 <label for="subject">제목</label>
            </div>
            <!-- 내용-->
            <div class="form-floating mb-3">
                 <textarea class="form-control" name="content" id="content" placeholder="내용을 입력해주세요 !" style="height: 10rem" maxlength="1000"></textarea>
                 <label for="message">내용</label>
            </div>
<!-- 			파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
			<div class="form-floating sm-3">
				 <input class="form-control" type="file" required="required" name="file1" multiple="multiple" accept="image/gif, image/jpg, image/png, image/jpeg" onchange="fileCheck(this)">
				 <label for="file">파일1</label>
			</div>	
			<div class="form-floating sm-3">
				 <input class="form-control" type="file" name="file2" multiple="multiple" accept="image/gif, image/jpg, image/png, image/jpeg" onchange="fileCheck(this)">
				 <label for="file">파일2</label>
			</div>
			<div class="form-floating sm-3">
				 <input class="form-control" type="file" name="file3" multiple="multiple" accept="image/gif, image/jpg, image/png, image/jpeg" onchange="fileCheck(this)">
				 <label for="file">파일3</label>
			</div>
			<div class="form-floating sm-3">
				 <input class="form-control" type="file" name="file4" multiple="multiple" accept="image/gif, image/jpg, image/png, image/jpeg" onchange="fileCheck(this)">
				 <label for="file">파일4</label>
			</div>
			<div class="form-floating sm-3">
				 <input class="form-control" type="file" name="file5" multiple="multiple" accept="image/gif, image/jpg, image/png, image/jpeg" onchange="fileCheck(this)">
				 <label for="file">파일5</label>
			</div>
			
			<br>
			
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