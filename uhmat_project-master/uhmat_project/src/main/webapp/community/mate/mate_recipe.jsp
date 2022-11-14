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
	<table>
		<tr>
			<td rowspan="5"><img src="../img/김볶.jpg" width="100"></td>
			<th>제목 들어갈 곳</th>
		</tr>
		<tr>
			<td rowspan="4">글 들어갈 곳</td>
		</tr>
	</table>
	
	<!-- 		푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 		푸터 들어가는 곳 -->
	
</body>
</html>