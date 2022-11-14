<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<form action="MemberLoginPro.me" method="post">
<div>
<label>email</label><br>
<input type="text" name="email" required="required">
</div>
<div>
<label>비밀번호</label><br>
<input type="password" name="passwd" required="required">
</div>
<br>
<input type="submit" value="로그인">
</form>

<br>
<a href="MemberFindPasswordForm.me">비밀번호 찾기</a>



</body>
</html>