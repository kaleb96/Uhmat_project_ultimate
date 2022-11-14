<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="food/review/reviewDetail.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Sunflower&display=swap" rel="stylesheet">


<script src="js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35185e429e5d9c68170c91b88e2d3a84"></script>
<script type="text/javascript">
	
	// 좋아요 추가
	function likeAdd(target) {
		
		$.ajax({
			type: "post",
			url: "ReviewLikeAction.re?idx=${dto.idx}&nickname='nickname2'&pageNum=${param.pageNum}",
			dataType: "text",
			success:
				function(response) {
					location.reload();
			}
			
		});
	}
	
	
	// 좋아요 개수에 따른 색깔변화
	$(document).ready(function () {
		
		var heart = Number($("#heart").text());
		if(heart > 0) {
			$("#likeBtn").css('color', 'red');
		}
		
	});
	
	//식당 정보를 아래에 합치는 ajax
	$(function(){
		$.ajax({
			type:"post",
			url:"restaurantDetail.re",
			data:{
				resName: "${dto.res_name}"
			},
			dataType:"text",
			success:function(response){
// 				alert("식당 정보 추가됨!");
				$("#restaurantInfo").html(response);
				$("#restaurantInfo header").remove();
				$("#restaurantInfo footer").remove();
			}
		});
	})
	
	$(document).ready(function() {
		$("#menu span").click(function() {
			var submenu = $(this).next("ul");
			
			if(submenu.is(":visible") ) {
				submenu.slideUp();
			} else {
				submenu.slideDown();
			}
		});
	})
</script>
<style>
	.star-rating {width:205px; }
	.star-rating,.star-rating span {display:inline-block; height:39px; overflow:hidden; background:url(image/star3.png)no-repeat; }
	.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }
</style>
</head>
<body>
	<jsp:include page="../../inc/header.jsp"></jsp:include>
		
	<!-- Aside bar -->
		
	<!-- Main article -->
		<aside>
		<ul>
			<li id="menu">
				<span>◾◾◾</span>
				<ul class="hide">
					<li><button class="atn" onclick="location.href='ReviewModifyForm.re?idx=${dto.idx}&pageNum=${param.pageNum}'">수정</button></li>
					<li><button class="atn" onclick="location.href='ReviewDeleteForm.re?idx=${dto.idx}&fileName=${dto.photo }&pageNum=${param.pageNum}'">삭제</button></li>
					<li><button class="atn" onclick="location.href='ReviewList.re'">메인 리뷰</button></li>
				</ul>
			</li>
		</ul>
		</aside>
		
	<section id="mother">
			<!--  이미지 박스 -->
			<section id="son">
				<!--  샘플이미지 -->
				<section id="imgBox">
					<p><img id="sample" src ="image/character/${dto.icon }" alt="default" ></p>
					<p class="name">작성자 : ${dto.nickname }</p>
<%-- 					<p class="name">작성일 : ${dto.date }</p> --%>
				</section>
				<section id="cntBox1">
					<p class="border">${dto.subject }</p>
					<p>
						<c:set var="tagList" value="${fn:split(dto.tag_name, '#')}"/>
						<c:forEach var="tag" items="${tagList }" varStatus="t">
							<c:if test="${t.count > 0 }">
								<button class="btn">#${tag }</button>
							</c:if>
						</c:forEach>
					</p>
						<p>${dto.res_name }</p>
						<!-- 지도 api와 연동 -->
					<div class='star-rating'><span style ="width:${dto.rating*20}%; "></span></div>(${dto.rating})<!--  CSS 로 구현 -->
			</section>
				<!-- 내용, 좋아요, 댓글작성, 공유 버튼 박스 -->
			<section id="cntBox2">	
				<p><img id="img" src="upload/${dto.photo }" alt="파일"></p>
				<p ><textarea id="cnt" rows="20" cols="100">${dto.content }</textarea></p>
				<p>
				<c:choose>
					<c:when test="${empty sessionScope.sNickName }">
						<button id="likeBtn">❤</button>&nbsp;<span id="heart">${dto.likes }</span>
					</c:when>
					<c:otherwise>
						<button id="likeBtn" onclick="likeAdd(this)" >❤</button>&nbsp;<span id="heart">${dto.likes }</span>
						<button id="msgBtn" onclick="location.href='ReviewReplyList.re?idx=${dto.idx}&nickname=${dto.nickname }&pageNum=${pageNum}'">💬</button>
					</c:otherwise>				
				</c:choose>
				</p>
				<p>
				<c:choose>
					<c:when test="${not empty replyList }">
						<c:forEach var="reply" items="${replyList}">
						<p>${reply.nickname }:  ${reply.content }</p>
					</c:forEach>
					</c:when>
				</c:choose>
				</p>
			</section>
		</section>
			<hr>
			<!--  식당 정보 박스 -->
			<section id="daughter">
				<p class="border">식당 정보</p>
				<section id="restaurantInfo">
					<!-- 식당정보가 추가되는 장소 -->
				</section>
			</section>
	
	</section>
	
	<jsp:include page="../../inc/footer.jsp"></jsp:include>
</body>
</html>