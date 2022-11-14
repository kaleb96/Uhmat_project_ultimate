<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="food/review/reviewWriteForm.css" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">

	function checkHash(target) {
		
		var hash = fr.tag.value;
		hash = hash.replaceAll("#", " ");
		hash = hash.split(" ");
// 		alert(hash.length);
		for(var i = 1; i < hash.length; i++) {
			var tmp = hash[i];
			for(var j = i+ 1; j <= hash.length; j++) {
				if(tmp == hash[j]) {
				alert("Tag가 중복됩니다.")
				return;
				} 
			}
		}	
	}
	
	$(function(){
		$("#find").on("click",function(){
// 			alert("음식점 찾기!");
			window.open("restaurantList.re?windowOpen=true","findRes","height=500,scrollbars");
			
		});
	});
	
	//해쉬태그 검색 로직
// 	function checkHash(target) {
	
// 		$.ajax({
// 			type: "post",
// 			url: "CheckHash.re",
// 			data: {
// 				hash: $("#hash").val()
// 			},
// 			dataType: "text",
// 			success:
// 				function(response) {
// 		}
// 		});
// 	}
	
	
</script>
</head>
<body>
	<c:if test="${ empty sessionScope.sNickName }">
		<script>
			alert("글쓰기는 로그인이 필요합니다!");
			location.href="MemberLogin.me";
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
	<hr>
	<section id="mother">
		<form name="fr"action="ReviewWritePro.re" method="post" enctype="multipart/form-data">
			
			<div class="dv"><span class="lv">닉네임</span>
				<input type="text" name="nickname" value="${sessionScope.sNickName }" readonly="readonly">
			</div>
					<!--  회원가입 로그인이 연동되면 nickname을 세션값으로받습니다. -->
			<div class="dv"><span class="lv">제목</span>
				<input type="text" name="subject" required="required">
			</div>
			
			<div class="dv"><span class="lv">태그명</span>
				<input type="text" id="hash" name="tag" placeholder="#태그명 공백" onchange="checkHash(this)" required="required">
			</div>
					<!-- select box 를 통한 추가 조사 
						DB에 추가해야함
					-->
			<div class="dv"><span class="lv">음식점 찾기</span>
				<input type="text" name="res_name" id="searchRes" readonly="readonly" required="required">&nbsp;
				<button type="button" class="btn" id="find">찾기</button>
			</div>
					
					<!-- 지도 api와 연동 -->
			<div class="line">
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
			
				<div><textarea rows="15" cols="80" name="content" placeholder="내용을 입력하세요" required="required"></textarea></div>
				<input type="file" class="btn" name="photo" required="required" />

		
			<section>
				<input type="submit" class="btn" value="제출해버렷">
			</section>
		</form>
	</section>
</body>
</html>