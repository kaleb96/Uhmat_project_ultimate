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
<script src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
	function likeAdd(target) {
		alert("click!");
		
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
	
	$(document).ready(function () {
		
		var heart = Number($("#heart").text());
		if(heart > 0) {
			$("#likeBtn").css('color', 'red');
		}
		
	});
	
</script>
<style>
	.star-rating {width:205px; }
	.star-rating,.star-rating span {display:inline-block; height:39px; overflow:hidden; background:url(image/star3.png)no-repeat; }
	.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }
</style>
</head>
<body>
	<header id="header">
		<hr>
			<h1>헤더라인</h1>
		<hr>
	<nav>
		<details>
			<summary>∙∙∙(more button)</summary> <!-- 이 부분은 로그인 되어 있는 경우에만 사용 가능  세션이 없을 경우
			alert("로그인이 필요합니다") 처리-->

			<input type="button" value="수정" onclick="location.href='ReviewModifyForm.re?idx=${dto.idx}&pageNum=${param.pageNum}'">
			<input type="button" value="삭제" onclick="location.href='ReviewDeleteForm.re?idx=${dto.idx}&fileName=${dto.photo }&pageNum=${param.pageNum}'">
			<input type="button" value="리스트로" onclick="location.href='ReviewList.re'">
		</details>
	</nav>
	</header>
	<section>
		<section border ="1" id="content">
			<section id="imgBox">
				<p><img id="img" src="upload/${dto.photo }" alt="파일"></p>
				<p id="name">${dto.nickname }</p>
			</section>	
			
			<section id="cntBox">
				<p id="cntSubject">${dto.subject }</p>
				<!--  회원가입 로그인이 연동되면 nickname을 세션값으로받습니다. -->
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
				<p ><textarea id="cnt" rows="20" cols="100">${dto.content }</textarea></p>
				<p>
				<button id="likeBtn" onclick="likeAdd(this)" >❤</button>&nbsp;<span id="heart">${dto.likes }</span>
				<button id="msgBtn" onclick="location.href='ReviewReplyList.re?idx=${dto.idx}&nickname=${dto.nickname }&pageNum=${param.pageNum}'">💬</button>
				<button id="shrBtn" >🔗</button>
				</p>
				<p>
				<c:choose>
					<c:when test="${not empty replyList }">
						<c:forEach var="reply" items="${replyList}">
						<p>${reply.nickname }  ${reply.content }</p>
					</c:forEach>
					</c:when>
				</c:choose>
				</p>
			</section>
			
		</section>
	</section>
		<footer id="footer">
		<hr>
			<h1>푸터라인</h1>
		<hr>
	</footer>
</body>
</html>