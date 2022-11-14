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

<script type="text/javascript" src="./js/jquery-3.6.0.js"></script>
<script>
	
	$(function(){
		// 파일 업로드 버튼 비활성화
		$("#file1").attr("disabled", true);
		$("#file2").attr("disabled", true);
		$("#file3").attr("disabled", true);
		$("#file4").attr("disabled", true);
		$("#file5").attr("disabled", true);
		
		// 체크박스 클릭시 업로드 버튼 활성화
		$('#imgCheck1').click(function(){
		if($("#imgCheck1").is(":checked")){
			$("#file1").attr("disabled", false);
			$("#imgCheck1").val("true");
		} else {
			$("#file1").attr("disabled", true);
			$("#imgCheck1").val("false");
		}	

		});
		
		$('#imgCheck2').click(function(){
			if($("#imgCheck2").is(":checked")){
				$("#file2").attr("disabled", false);
				$("#imgCheck2").val("true");
			} else {
				$("#file2").attr("disabled", true);
				$("#imgCheck2").val("false");
			}	

			});
		
		$('#imgCheck3').click(function(){
			if($("#imgCheck3").is(":checked")){
				$("#file3").attr("disabled", false);
				$("#imgCheck3").val("true");
			} else {
				$("#file3").attr("disabled", true);
				$("#imgCheck3").val("false");
			}	

			});
		
		$('#imgCheck4').click(function(){
			if($("#imgCheck4").is(":checked")){
				$("#file4").attr("disabled", false);
				$("#imgCheck4").val("true");
			} else {
				$("#file4").attr("disabled", true);
				$("#imgCheck4").val("false");
			}	

			});
		
		$('#imgCheck5').click(function(){
			if($("#imgCheck5").is(":checked")){
				$("#file5").attr("disabled", false);
				$("#imgCheck5").val("true");
			} else {
				$("#file5").attr("disabled", true);
				$("#imgCheck5").val("false");
			}	

			});
	});
</script>

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
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">RECIPE 글 수정</h2>
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
	<!-- 게시판 글 수정 -->
		<form action="RecipeModifyPro.co" name="recipeForm" method="post" enctype="multipart/form-data">
			<!-- 게시물 수정에 필요한 글번호와 페이징 처리에 필요한 페이지번호도 함께 전달 -->
			<input type="hidden" name="idx" value="${recipe.idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<!-- 업로드에 사용된 파일이름을 불러옵니다. -->
			<input type="hidden" name="fileName" value="${param.fileName }">
			
				<div class="form-floating mb-3">
						<input type="text" class="form-control" id="nickname" name="nickname" value="${sessionScope.sNickName }" readonly="readonly">
						<label for="nickname">어맛인</label>
					</div>
					 <div class="form-floating mb-3">	
						<input type="text"  class="form-control" id="subject" name="subject" value="${recipe.subject }" required="required">
						<label for="subject">제목</label>
					</div>	
					 <div class="form-floating mb-3">
						<textarea class="form-control" id="content" name="content" style="height: 10rem" required="required"> ${recipe.content }</textarea>
						<label for="message">내용</label>
					</div>
				<table>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file1" id="file1" multiple="multiple" required="required" /></td>	
					<td width="200"><img src="./recipe_upload/${recipe.real_File1 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck1" name="imgCheck1" value="false"> </td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file2" id="file2" multiple="multiple" ></td>
					<td width="200"><img src="./recipe_upload/${recipe.real_File2 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck2" name="imgCheck2" value="false" > </td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file3" id="file3" multiple="multiple" ></td>
					<td width="200"><img src="./recipe_upload/${recipe.real_File3 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck3" name="imgCheck3" value="false"> </td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file4" id="file4" multiple="multiple" ></td>
					<td width="200"><img src="./recipe_upload/${recipe.real_File4 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck4" name="imgCheck4" value="false" width="50"> </td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file5" id="file5" multiple="multiple" ></td>
					<td width="200"><img src="./recipe_upload/${recipe.real_File5 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck5" name="imgCheck5" value="false"> </td>
				</tr>
			</table>
			
			<br>
			
			<div align="right" >
				<input type="submit" class="btn btn-secondary" value="수정">&nbsp;&nbsp;
				<input type="reset" class="btn btn-secondary" value="다시쓰기">&nbsp;&nbsp;
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