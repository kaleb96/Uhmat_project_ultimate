<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- <c:when test="${sessionScope.sNickName eq}"></c:when> --%>
<link href="food/review/reviewList.css" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">

// 	window.onscroll = function() {
	
// 		if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
			
// 			if(${pageInfo.endPage} == ${pageInfo.pageNum + 1}) {
// 				$.ajax({
// 					async:false,
// 					type: "post",
// 					url: "ReviewList.re?pageNum=${pageInfo.pageNum + 1}",
// 					data: $("#list").serialize(),
// 					dataType: "text",
// 					success: 
// 						function(response) {
// 							if(${pageInfo.endPage} == ${pageInfo.pageNum + 1}) {
// 								$("#aside").remove()
// 								$("#header").remove();
// 								$("#footer").remove();
// 								var content = $("#append").html(response).find("#listView");
// 								$("#append").append(content);
							
// 						} 
// 					});
// 				};
// 			}
// 		}
	window.onscroll = function() {
		
			if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
				if(${pageInfo.endPage} >= ${pageInfo.pageNum + 1}){
					$.ajax({
						type: "post",
						url: "ReviewList.re?pageNum=${pageInfo.pageNum + 1}",
						data: $("#list").serialize(),
						dataType: "text",
						success: 
							function(response) {
								if(${pageInfo.endPage} >= ${pageInfo.pageNum + 1}) {
									var content = $("#append").html(response).find("#list");
									$("#mother").append(content);
	
								}
							}
					});
				}
			}
		}

	
	//content 글자 수 제한 코드 
	$(document).ready(function(){
	    $('p[id^=content]').each(function(){
	        if ($(this).text().length > 120) {
	        $(this).html($(this).text().substr(0,120)+"<br>" +"<span id='highlight' onclick='more()'>...더보기</span>");
	        }
	    });
	
	});
	
	
	// 해쉬 태그 클릭시 내용 조회 코드
	$(document).ready(function() {
		
		$('button[id^=targetTag]').each(function() {
			
		
			$(this).on("click", function() {
			
				var targetTag = $(this).text();
				targetTag = targetTag.replaceAll("#", "");
				location.href="ReviewList.re?targetTag="+targetTag +"&pageNum=${pageInfo.pageNum}"
			});
		});
	});
		
</script>
<style>
	.star-rating {width:205px; }
	.star-rating,.star-rating span {display:inline-block; height:39px; overflow:hidden; background:url(image/star3.png)no-repeat; }
	.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }
</style>
</head>
<body id="mainPage">
<% String name = (String)(session.getAttribute("sNickName")); %>
 <%=name%>
		<section id="hd"><jsp:include page="/inc/header.jsp" /></section>
		<aside id="aside">
		<button id="plus" onclick="location.href='ReviewWriteForm.re'">➕ 새 게시물</button><br>

		</aside>
	<section id="mother">
	<section id="list" >
		<c:choose>
			<c:when test="${not empty reviewList and pageInfo.listCount gt 0}">
				<c:forEach var="board" items="${reviewList}" varStatus="state">
					<!--  -->
					<section id="listView">
						<p id="subject" class="tdName">${board.subject }</p>
						<section id="photo">
						<p><img src="upload/${board.photo }" width="400px" height="250px"alt="파일" ></p>
						
						</section>
						<section id="tdName">

						
						<p class="tdName">
						<c:set var="tagList" value="${fn:split(board.tag_name, '#')}"/>
						<c:forEach var="tag" items="${tagList }" varStatus="t">
							<c:if test="${t.count > 0 }">
								<button class="btn" id="targetTag${t.count }">#${tag }</button>
							</c:if>
						</c:forEach>
						</p>
<%-- 						<tr><td id="tagName${state.count }"><span style="display:none;">${board.tag_name}</span></td></tr> --%>
<%-- 						<tr><td id="tagName${state.count }"><span style="display:none;">${board.tag_name}</span></td></tr> --%>
						<p class="tdName"><div class='star-rating'><span style ="width:${board.rating*20}%"></span></div>(${board.rating }) <span><span id="heart">❤</span>${board.likes}</span></p>
<%-- 						<p class="tdName"><span><span id="heart">❤</span>${board.likes}개</span></p>				 --%>
						</section>
						
						<section id="cnt">
						<p id="content${state.count }"onclick="location.href='ReviewDetail.re?idx=' + ${board.idx}+'&pageNum=' +${pageInfo.pageNum}">${board.content}</p> <!-- 이부분에서 나중에 댓글 항목 추가, 더보기 란 할 수 있도록 해야함 -->
						</section>
					</section>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="8">게시글이 존재하지 않습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</section>
	</section>
	<section id="append" style="display:none;">
	</section>
	
		<section id="ft"><jsp:include page="/inc/footer.jsp" /></section>
</body>
</html>