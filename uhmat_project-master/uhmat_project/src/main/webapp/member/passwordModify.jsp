<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/button.css" rel="stylesheet" type="text/css">
<script src="./js/jquery-3.6.0.js"></script>
<script>
	$(function() {
		var passwdFlag = false;
		var passwdCheckFlag = false;

		$("#passwd").on(
				"keyup",
				function() {
					// 패스워드 검사 패턴 설정
					// 1. 길이 및 사용 가능 문자에 대한 규칙 : 8 ~ 16 자리 영문자, 숫자, 특수문자(!@#$%) 조합
					var lengthRegex = /^[A-Za-z0-9!@#$%]{8,16}$/;
					var password = $("#passwd").val();

					// 각 문자 형식이 포함되는지 여부를 각각 체크하기 위한 패턴 설정
					// => 주의! 부분 패턴 검사이므로 시작(^) 과 끝($)은 사용하면 안된다!
					// 2. 영문 대문자 규칙
					var engUpperRegex = /[A-Z]/;
					// 3. 영문 소문자 규칙
					var engLowerRegex = /[a-z]/;
					// 4. 숫자 규칙
					var numRegex = /[0-9]/;
					// 5. 특수문자 규칙
					var specRegex = /[!@#$%]/;
					if (lengthRegex.exec(password)) {
						var count = 0; // 검증 결과를 포인트화 할 변수 선언

						if (engUpperRegex.exec(password)) { // 대문자 검사
							count++;
						}

						// 주의! 각 조건마다 별도로 검사하므로 else if 가 아닌 각각 단일 if 문 사용
						if (engLowerRegex.exec(password)) { // 소문자 검사
							count++;
						}

						if (numRegex.exec(password)) { // 숫자 검사
							count++;
						}

						if (specRegex.exec(password)) { // 특수문자(!@#$%) 검사
							count++;
						}
					
						let passwd2 = $("#passwd2").val();
						let passwdAlter=$("#alterPassword").val();
						

						// 두 패스워드 비교
						if(password == passwdAlter){
							$("#checkPasswdResult").html("현재 비밀번호와 같습니다.");
							$("#checkPasswdResult").css("color", "RED");
							passwdFlag = false;
						}
							if (password == passwd2) {
								$("#confirmPasswdResult").html("일치 합니다");
								$("#confirmPasswdResult").css("color", "GREEN");
								passwdCheckFlag = true;

							} else {
								$("#confirmPasswdResult").html("일치 하지 않습니다");
								$("#confirmPasswdResult").css("color", "RED");
								passwdCheckFlag = false;

							}
						
						

						// 패턴 카운팅 결과를 사용하여 복잡도 판별 결과 출력(if 문 또는 switch-case 문 사용)
						if (count == 4) {
							$("#checkPasswdResult").html("사용 가능 : 안전");
							$("#checkPasswdResult").css("color", "GREEN");
							passwdFlag = true;
						} else if (count == 3) {
							$("#checkPasswdResult").html("사용 가능 : 보통");
							$("#checkPasswdResult").css("color", "blue");
							passwdFlag = true;
						} else if (count == 2) {
							$("#checkPasswdResult").html("사용 가능 : 위험");
							$("#checkPasswdResult").css("color", "ORANGE");
							passwdFlag = true;
						} else {
							$("#checkPasswdResult").html(
									"영문자, 숫자, 특수문자 중 2가지 이상 조합 필수!");
							$("#checkPasswdResult").css("color", "RED");
							passwdFlag = false;
						}

					} else { // 패스워드 길이 또는 사용 가능 문자 체크 부적합 시
						$("#checkPasswdResult").html(
								"8~16자리 영문자, 숫자, 특수문자 조합 필수!");
						$("#checkPasswdResult").css("color", "RED");
						passwdFlag = false;
						
					

					}
				});

		$("#passwd2").on("keyup", function() {
			// 비밀번호 & 비밀번호확인란이 같은지 판별
			let passwd = $("#passwd").val();
			let passwd2 = $("#passwd2").val();

			// 두 패스워드 비교
			if (passwd == passwd2) {
				$("#confirmPasswdResult").html("일치 합니다");
				$("#confirmPasswdResult").css("color", "GREEN");
				passwdCheckFlag = true;

			} else {
				$("#confirmPasswdResult").html("일치 하지 않습니다");
				$("#confirmPasswdResult").css("color", "RED");
				passwdCheckFlag = false;

			}

		});
		
		$('form').submit(function() {

			if (!passwdFlag) {
				$('#passwd').focus();
				return false

			}if(!passwdCheckFlag){
				$('#passwd2').focus();
				return false
				
			} else {
				return true
			}

		});

	});
</script>
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
		<h1>비밀번호 변경</h1>
		<form action="MemberPasswordModifyPro.me" method="post">

			<input type="hidden" id="email" name="email"
				value="${sessionScope.sNickName== null?param.email:sessionScope.sNickName }">
			<input type="hidden" id="nickname" name="nickname"
				value="${sessionScope.sNickName== null?param.email:sessionScope.sNickName }">
			<c:if test="${sessionScope.sNickName == null}">
				<div align="left">
					<h2>※이메일로 발송해드린 비밀번호가 임시 비밀번호 입니다.</h2>
					<h2>※회원님의 비밀번호를 변경해 주시길 바랍니다.</h2>
				</div>
				<div class="form-floating mb-3">

					<input class="form-control" type="password" name="alterPassword"
						id="alterPassword" placeholder="비밀번호"> <label>임시
						비밀번호</label>
				</div>
			</c:if>
			<c:if test="${sessionScope.sNickName != null}">
		
				<div class="form-floating mb-3">

					<input class="form-control" type="password" name="alterPassword"
						id="alterPassword" placeholder="비밀번호"> <label>현재
						비밀번호</label>
				</div>
			</c:if>


			<div class="form-floating mb-3">
				<!-- 패스워드 변화할 때마다 checkPasswd() 함수 호출 => 파라미터로 입력 패스워드 전달 -->
				<input class="form-control" type="password" name="passwd"
					id="passwd" maxlength="16" placeholder="비밀번호" required="required">
				<label>비밀번호</label> <span id="checkPasswdResult"></span>
			</div>
			<br>


			<div class="form-floating mb-3">
				<input class="form-control" type="password" name="passwd2"
					id="passwd2" maxlength="16" placeholder="비밀번호" required="required">
				<label>비밀번호확인</label> <span id="confirmPasswdResult"></span>
			</div>
			<br> <input class="w-btn-outline w-btn-green-outline"
				type="submit" value="변경하기">
		</form>
		<br> <a href="MemberFindPasswordForm.me"><button class="w-btn-outline w-btn-green-outline">비밀번호 찾기</button></a>

	</div>
	<!-- 푸터 들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 -->

</body>
</html>