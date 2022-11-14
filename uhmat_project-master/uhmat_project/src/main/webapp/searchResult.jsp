<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainSearch</title>
</head>
<body>	

		<!-- header -->
			<jsp:include page="inc/header.jsp"></jsp:include>
		
		<h1>검색결과</h1>
		<!-- restaurantBoard -->
		<c:choose>
		<c:when test="${not empty restaurantBoard  }">
			<h1>RestaurantBoard</h1>
			<table >
				<c:forEach  var="resInfo" items="${restaurantBoard }">
<!-- 					<tr> -->
<!-- 						<td> -->
<%-- 							<a href="#" onclick="window.open('restaurantDetail.re?resName=${resInfo.resName}','레스토랑','width=1500px, height=1500px')">${resInfo.resName }</a> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<%-- 						<tr onclick="window.open('restaurantDetail.re?resName=${resInfo.resName}')"> --%>
<%-- 									<td><h1 style="color:blue">${resInfo.resName }</h1></td> --%>
<!-- 						</tr> -->
						<tr>
							<td><a href="restaurantDetail.re?resName=${resInfo.resName}">${resInfo.resName }</a></td>
						</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
		<h1>RestaurantBoard - 검색결과 게시물이 존재하지 않습니다.</h1>
		</c:otherwise>
		</c:choose>	
		<!-- reviewBoard -->
			<c:choose>
				<c:when test="${not empty reviewBoard  }">
				<h1>ReviewBoard</h1>
					<table>
							<c:forEach  var="review" items="${reviewBoard }">
<!-- 							<tr> -->
<!-- 								<td> -->
<%-- 									<a href="#" onclick="window.open('ReviewDetail.re?idx=${review.idx}&pageNum=${pageInfo.pageNum}','리뷰','width=1500px, height=1500px')">${review.subject }</a> --%>
<!-- 								</td> -->
<!-- 							</tr> -->
								<tr>
									<td><a href="ReviewDetail.re?idx=${review.idx}&pageNum=${pageInfo.pageNum}">${review.subject }</a></td>
								</tr>
							</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
				<h1>ReviewBoard - 검색결과 게시물이 존재하지 않습니다.</h1>
				</c:otherwise>
			</c:choose>	
		
		<!-- Footer 부분 -->
			<jsp:include page="inc/footer.jsp"></jsp:include>
</body>
</html>