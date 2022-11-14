<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.js"></script>
<script>
$("#reviewBestNext").on("click",function(){
	$("#list").empty();
	   $.ajax({
			type: "post",
			url: "ReviewBest.ma",
			data: {
				pageNum : ${pageInfo.pageNum +1},
				
			},
			dataType: "text",
			async : false,
			success: function(response) {
			
					alert(response);
				
					$("#list").append(response);
					
			},
		});
	
});
$("#reviewBestPrivew").on("click",function(){
	$("#list").empty();
	   $.ajax({
			type: "post",
			url: "ReviewBest.ma",
			data: {
				
					pageNum : ${pageInfo.pageNum - 1} 
			
			
			
				
				
			},
			dataType: "text",
			async : false,
			success: function(response) {
			
					alert(response);
				
					$("#list").append(response);
					
			},
		});
	
});
</script>
</head>
<body>
		<c:choose>
			<c:when test="${pageInfo.pageNum < pageInfo.maxPage}">
				<input id="reviewBestNext" type="button" value="다음"  class="before_next">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음" class="before_next">
			</c:otherwise>
		</c:choose>
	<c:choose>
			<c:when test="${pageInfo.pageNum > 1}">
				<input id="reviewBestPrivew" type="button" value="이전" class="before_next">
			</c:when>
			<c:otherwise>
				<input  type="button" value="이전" class="before_next">
			</c:otherwise>
		</c:choose>
	<c:choose>
			<c:when test="${not empty list and pageInfo.listCount gt 0}">
				<c:forEach var="board" items="${list}" varStatus="state">
					<!--  -->
					<section id="listView">
						<p id="subject" class="tdName">${board.subject }</p>
						<section id="photo">
						<p><img src="upload/${board.photo }" width="400px" height="250px"alt="파일" ></p>
						
						</section>
						<section id="tdName">

						
						<p class="tdName">
					
						</p>
						<p class="tdName"><div class='star-rating'><span style ="width:${board.rating*20}%"></span></div>(${board.rating }) <span><span id="heart">❤</span>${board.likes}</span></p>
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
</body>
</html>