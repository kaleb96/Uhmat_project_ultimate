<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReviewModifyForm</title>
<link href="food/review/reviewModifyForm.css" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<script>
	$(function(){
		var rating = ${dto.rating};
		for(i=0;i<9;i++){
			if($("input:radio").eq(i).val()==rating){
				$("input:radio").eq(i).attr("checked",true);
			}
		}
	});
	
	// 음식점 찾기 
	$(function(){
		$("#find").on("click",function(){
// 			alert("음식점 찾기!");
			window.open("restaurantList.re?windowOpen=true","findRes","height=500,scrollbars");
		});
	});
</script>
</head>
<body>
	<c:if test="${ dto.nickname != sessionScope.sNickName }">
		<script>
			alert("글 수정 권한이  없습니다!");
			history.back();
		</script>
	</c:if>
	<div class="mainLogo">
    		<a href="/uhmat_project/"><img src="image/uhmatMainLogo.png"></a>
    	</div>
		<!-- 로고 들어가는 곳 끝 -->
		
		<!-- 로그인 및 회원가입 부분 시작 -->
		<div class="loginPart">
		<c:choose>
			<c:when test="${empty sessionScope.sNickName}">
				<a href="MemberLogin.me">로그인</a>&nbsp; <a href="MemberJoinForm.me">회원가입</a>
			</c:when>
			<c:otherwise>
				<a href="MemberDetailForm.me?nickName=${sessionScope.sNickName }">${sessionScope.sNickName }
					님 </a>&nbsp; <a href="MemberLogout.me">로그아웃</a>
				<c:if test="${sessionScope.sNickName eq 'admin'}"> &nbsp; <a
						href="AdminMain.me">관리자페이지</a>
				</c:if>
			</c:otherwise>
		</c:choose>
		</div>
	<hr id="hdLine">
	
	<section id="mother">
		<form  action="ReviewModifyProAction.re" method="post" enctype="multipart/form-data">
			<section id="son">
				<input type="hidden" name="idx" value="${dto.idx }">
				<input type="hidden" name="path" value="${originPath }">
				<input type="hidden" name="pageNum" value="${param.pageNum }">
				
				<div class="dv"><span class="lv">작성자</span>
				<input type="text" name="nickname" value="${sessionScope.sNickName }" readonly="readonly">
				</div>
				<div class="dv"><span class="lv">제목</span>
				<input type="text" name="subject" value="${dto.subject }" required="required" onfocus="this.select()">
				</div>
				<div class="dv"><span class="lv">태그명</span>
				<input type="text" name="tag" value="${dto.tag_name }" onfocus="this.select()">
				</div> 
				<div class="dv"><span class="lv">음식점 찾기</span>
				<input type="text" name="res_name" id="searchRes" value=${dto.res_name } readonly="readonly" required="required">&nbsp;
				<button type="button" class="btn" id="find">찾기</button>
			</div>
				
				<div class="row"><span class="lv">별점 재선택</span>
					  <fieldset class="rate">
					  
					    <input id="rate2-star5" class="starRow" type="radio" name="rating" value="5" />
					    <label for="rate2-star5" title="Awesome">5</label>
					
					    <input id="rate2-star5-half" class="starRow" type="radio" name="rating" value="4.5" />
					    <label class="star-half" for="rate2-star5-half" title="Excellent">4.5</label>
					
					    <input id="rate2-star4" class="starRow" type="radio" name="rating" value="4" />
					    <label for="rate2-star4" title="Very good">4</label>
					
					    <input id="rate2-star3-half" class="starRow" type="radio"name="rating" value="3.5" />
					    <label class="star-half" for="rate2-star3-half" title="Good">3.5</label>
					
					    <input id="rate2-star3" class="starRow" type="radio" name="rating" value="3" />
					    <label for="rate2-star3" title="Satisfactory">3</label>
					
					    <input id="rate2-star2-half" class="starRow" type="radio" name="rating" value="2.5" />
					    <label class="star-half" for="rate2-star2-half" title="Unsatisfactory">2.5</label>
					
					    <input id="rate2-star2" class="starRow" type="radio" name="rating" value="2" />
					    <label for="rate2-star2" title="Bad">2</label>
					
					    <input id="rate2-star1-half" class="starRow" type="radio" name="rating" value="1.5" />
					    <label class="star-half" for="rate2-star1-half" title="Very bad">1.5</label>
					
					    <input id="rate2-star1" class="starRow" type="radio" name="rating" value="1" />
					    <label for="rate2-star1" title="Awful">1</label>
					
					    <input id="rate2-star0-half" class="starRow" type="radio" name="rating" value="0.5" />
					    <label class="star-half" for="rate2-star0-half" title="Horrific">0.5</label>
					  </fieldset>
					</div>
					
						<p><span class="lv">이전이미지</span><img width="500px" src="upload/${dto.photo }" alt="파일" ></p>
						<p><input type="file" name="photo" required="required" class="btn" /></p>
						<div class="dv"><span class="lv">내용 수정</span>
						<p><textarea rows="15" cols="80" name="content"required="required" onfocus="this.select()">${dto.content}</textarea></p>
						</div>
					<section>
						<input class="btn" type="submit" value="수정해버렷">
					</section>
				</section>
		</form>
	</section>
</body>
</html>