<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
	
	function replyWrite(target) {
		
		
		alert("click");
		
			$.ajax({
				type: "post",
				url: "ReviewReplyWriteAction.re?idx=${param.idx}&nickname=${param.nickname}&pageNum=${param.pageNum}",
				data: {
					content: $("#content").val()
				},
				dataType: "text",
				success:
					function(response) {
						location.reload();
				}
				
			});
		}
</script>
</head>
<body>
	<header>
		<nav>
			<button onclick="location.href='ReviewDetail.re?idx=${param.idx}&nickname=${param.nickname}&pageNum=${param.pageNum}'">돌아가기</button>
		</nav>
	</header>
	<section>
		<table>
			<tr>
				<td>
				<span id="id"></span>
				<textarea rows="1" cols="100" id="content"></textarea>
				<input type="button" id="btn" onclick="replyWrite(this)" value="게시">
<!-- 				<button id="btn" onclick="replyWrite()">게시</button> -->
				</td>
			</tr>
		</table>
	</section>
	<section>
		<table>
			
				<c:choose>
					<c:when test="${not empty replyList }">
						<c:forEach var="reply" items="${replyList}">
						<tr><td colspan="2">${reply.nickname }  ${reply.content }</td>
						</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="2">게시글이 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			
		</table>
	</section>
</body>
</html>