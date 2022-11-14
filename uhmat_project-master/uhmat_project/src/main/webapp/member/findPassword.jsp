<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="css/styles.css" rel="stylesheet" />
	<link href="css/button.css" rel="stylesheet" type="text/css">
<style type="text/css">
#container {
/* 	border: 1px solid #ccc; */
	width: 995px;
	margin: 0 auto;
/* 	display: flex; */
	padding-top: 20px;
	padding-bottom: 20px;
	text-align: center;
	
}

</style>
</head>
<body>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../inc/header.jsp" flush="false" />
	<!-- 헤더 들어가는곳 -->
	<div id="container">
	
<form action="MemberFindPasswordPro.me" method="get">
<h3>찾으실 이메일을 적어주세요</h3><br>
<div class="form-floating mb-3">
<input class="form-control" type="text" id="email" name="email" placeholder="이메일">
<label>Email</label>
</div>

<input class="w-btn-outline w-btn-green-outline"   type="submit" value="비밀번호 찾기">

</form>

</div>
	<!-- 푸터 들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 -->
	
</body>
</html>