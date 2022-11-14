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
	<h1>DeleteForm</h1>
	<section>
		<form action="ReviewDeleteProAction.re" method="post">
				<input type="hidden" name="idx" value="${param.idx }">
				<input type="hidden" name="fileName" value="${param.fileName }">
				<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<tr>
					<td><input type="password" name="pass" placeholder="비밀번호를 입력하세요" required="required"></td>
					<td><input type="submit" value="삭제"></td>
					<td><input type="button" value="뒤로 돌아가기"></td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>