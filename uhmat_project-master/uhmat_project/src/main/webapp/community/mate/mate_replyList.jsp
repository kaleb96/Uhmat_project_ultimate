<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 들어가는 곳 -->
	<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->
	
	<table border="1">
		<!-- 게시물 목록 출력(단, 게시물이 하나라도 존재할 경우에만 출력) -->
		<!-- 조건 : boardList 객체가 비어있지 않고 pageInfo 객체의 listCount 가 0보다 클 경우 -->
		<c:choose>
			<c:when test="${not empty mateReplyList and pageInfo.listCount gt 0 }">
				<c:forEach var="mateReply" items="${mateReplyList }">
					<tr>
						<td>${mateReply.nickname } | ${mateReply.content }
							<br>
							${mateReply.date }
						</td>
						
					</tr>
				</c:forEach>
			</c:when>
<%-- 			<c:otherwise> --%>
<!-- 				<tr><td colspan="5">게시물이 존재하지 않습니다.</td></tr> -->
<%-- 			</c:otherwise> --%>
		</c:choose>
	</table>
	
	<!-- 		푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 		푸터 들어가는 곳 -->
	
</body>
</html>