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
<style type="text/css">
#container {
/* 	border: 1px solid #ccc; */
	width: 995px;
	margin: 0 auto;
/* 	display: flex; */
	padding-top: 20px;
	padding-bottom: 20px;
	
}

.joinFrom{

	width: 995px;
	align-items: center; 
}

h1{
text-align: center;
}
</style>
<!-- 다음 우편번호 API 포함시키기 -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./js/jquery-3.6.0.js"></script>
<script>

	$(function() {
		var emailFlag=false;
		var emailCheckFlag=false;
		var nicknameFlag=false;
		var passwdFlag=false;
		var passwdCheckFlag=false;
		$("input[type=reset]").on("click", function() {
			$("#checkPasswdResult").html("");
			$("#confirmPasswdResult").html("");
			$("#confirmEmailResult").html("");
			$("#checkNickNameResult").html("");
			$("#confirmEmailResult").html("");
			$("#EmailResult").html("");
		});

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

						// 패턴 카운팅 결과를 사용하여 복잡도 판별 결과 출력(if 문 또는 switch-case 문 사용)
						if (count == 4) {
							$("#checkPasswdResult").html("사용 가능 : 안전");
							$("#checkPasswdResult").css("color", "GREEN");
							passwdFlag=true;
						} else if (count == 3) {
							$("#checkPasswdResult").html("사용 가능 : 보통");
							$("#checkPasswdResult").css("color", "blue");
							passwdFlag=true;
						} else if (count == 2) {
							$("#checkPasswdResult").html("사용 가능 : 위험");
							$("#checkPasswdResult").css("color", "ORANGE");
							passwdFlag=true;
						} else {
							$("#checkPasswdResult").html(
									"영문자, 숫자, 특수문자 중 2가지 이상 조합 필수!");
							$("#checkPasswdResult").css("color", "RED");
							passwdFlag=false;
						}

					} else { // 패스워드 길이 또는 사용 가능 문자 체크 부적합 시
						$("#checkPasswdResult").html(
								"8~16자리 영문자, 숫자, 특수문자 조합 필수!");
						$("#checkPasswdResult").css("color", "RED");
						passwdFlag=false;

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
				passwdCheckFlag=true;

			} else {
				$("#confirmPasswdResult").html("일치 하지 않습니다");
				$("#confirmPasswdResult").css("color", "RED");
				passwdCheckFlag=false;

			}

		});

		$("#emailCheck").on("keyup", function() {
			let email = $("#email").val();
			let email2 = $("#emailCheck").val();

			if (email == email2) {
				$("#confirmEmailResult").html("일치 합니다");
				$("#confirmEmailResult").css("color", "GREEN");
				emailCheckFlag=true;

			} else {
				$("#confirmEmailResult").html("일치 하지 않습니다");

				$("#confirmEmailResult").css("color", "RED");
				emailCheckFlag=false;

			}

		});

		$("#nickName")
				.on(
						"keyup",
						function() {

							var regex = /^[가-힣a-zA-Z][가-힣a-zA-Z0-9!@#$%()]{3,15}$/;


							if (!regex.exec($("#nickName").val())
									|| $("#nickName").val() == null) { // 부적합한 아이디일 경우

								$("#checkNickNameResult")
										.html(
												"한글, 영어로  4~16자리 필수! (주의 : 맨앞 특수문자,숫자 불가)");
								$("#checkNickNameResult").css("color", "RED");
								nicknameFlag=false;

							} else {
								$
										.ajax({
											type : "post",
											url : "http://localhost:8080/uhmat_project/CheckDuplicateNickName.me",
											data : {
												nickName : $("#nickName").val(),
											},
											dataType : "text",
											success : function(data) {
												
												if (data === 'usable') {
													$('#checkNickNameResult')
															.text(
																	'사용할 수 있는 닉네임입니다.');
													$("#checkNickNameResult")
															.css("color",
																	"GREEN");
													nicknameFlag=true;
												} else {
													$('#checkNickNameResult')
															.text(
																	'이미 사용 중인 닉네임입니다.');
													$("#checkNickNameResult")
															.css("color", "RED");
												nicknameFlag=false;
												}
											},
											error : function(data, textStatus) {
												console.log('error');
											}
										});
							}

						});
		$("#email")
				.on(
						"keyup",
						function() {

							var regex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
							;

							if (!regex.exec($("#email").val())
									|| $("#email").val() == null) { // 부적합한 아이디일 경우

								$("#EmailResult").html("잘못된 이메일 형식입니다. ");
								$("#EmailResult").css("color", "RED");
								emailFlag=false;

							} else {
								$
										.ajax({
											type : "post",
											url : "http://localhost:8080/uhmat_project/CheckDuplicateEmail.me",
											data : {
												email : $("#email").val(),
											},
											dataType : "text",
											success : function(data) {
												if (data === 'usable') {
													$('#EmailResult')
															.text(
																	'사용할 수 있는 email입니다.');
													$("#EmailResult").css(
															"color", "GREEN");
													emailFlag=true;
												} else {
													$('#EmailResult')
															.text(
																	'이미 사용 중인 email입니다.');
													$("#EmailResult").css(
															"color", "RED");
													emailFlag=false;
												}
											},
											error : function(data, textStatus) {
												console.log('error');
											}
										});
							}

						});
		
		$('form').submit(function(){

			if(!emailFlag){
				 $('#email').focus();
				return false
			}
			else if(!emailCheckFlag){
				 $('#emailCheck').focus();
				return false
			}
			else if(!passwdFlag){
				 $('#passwd').focus();
				return false
			}else if(!passwdCheckFlag){
				 $('#passwd2').focus();
					return false
				}
			else if(!nicknameFlag){
				 $('#nickName').focus();
				return false
			}else{
				return true
			}

			})
		
		
	});

	
	// 다음 우편번호 API
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById('sample4_roadAddress').value = roadAddr;
						//                 document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
					}

				}).open();
	}
	
</script>
</head>
<body>
<jsp:include page="../inc/header.jsp" flush="false" />
<div class="joinFrom" id="container">
	<h1>회원가입</h1>
	<br>
	<form action="MemberJoinPro.me" method="post" name="fr" >


	
		<div class="form-floating mb-3">
             <input class="form-control" type="text" name="name" id="name"  required="required"  placeholder="이름">
              <label for="name">이름</label> 
        </div>
        <br>
 		<div class="form-floating mb-3">
             <input class="form-control" type="text" name="nickName" id="nickName"  maxlength="10" required="required"  placeholder="닉네임">
              <label for="nickname">닉네임</label> 
              <div id="checkNickNameResult"></div>
        </div>

		<br>

		
	
			<!-- 패스워드 변화할 때마다 checkPasswd() 함수 호출 => 파라미터로 입력 패스워드 전달 -->
		<div class="form-floating mb-3">
             <input class="form-control" type="password" name="passwd" id="passwd"  maxlength="16" required="required"  placeholder="패스워드" >
              <label for="passwd">비밀번호</label> 
             	<span id="checkPasswdResult"></span>
        </div>
        <br>
        <div class="form-floating mb-3">
             <input class="form-control" type="password" name="passwd2" id="passwd2"  maxlength="16" required="required"   placeholder="패스워드">
              <label for="passwd2">비밀번호 확인</label> 
              <span id="confirmPasswdResult"></span>
        </div>
	
		<br>
		<div class="form-floating mb-3">
             <input class="form-control" type="text" name="email" id="email"  required="required"   placeholder="이메일">
              <label for="email">E-Mail</label> 
              <span id="EmailResult"></span>
        </div>
	
		<br>
			<div class="form-floating mb-3">
             <input class="form-control" type="text" name="emailCheck" id="emailCheck"  required="required"  placeholder="이메일" >
              <label for="emailCheck">E-Mail 확인</label> 
             <span id="confirmEmailResult"></span>
        </div>
	
	
		<br>
			<div class="form-floating mb-3">
             <input class="form-control" type="date" name="birth" id="birth"  required="required"   placeholder="생일">
              <label for="birth">생년월일</label> 
             <span id="confirmEmailResult"></span>
        </div>
	
		<br>
	<div>
		<input class="w-btn-outline w-btn-green-outline"  type="button" onclick="execDaumPostcode()" value="우편번호 찾기" placeholder="우편">
		</div>
		<br>
		<div class="form-floating mb-3">
             <input class="form-control" type="text" name="postCode" id="sample4_postcode"  readonly="readonly" required="required" onclick="execDaumPostcode()"  placeholder="우편">
              <label for="postCode">우편번호</label> 
        </div>
	
			
			
		<div class="form-floating mb-3">
             <input class="form-control" type="text" name="address1" id="sample4_roadAddress"  readonly="readonly" required="required" onclick="execDaumPostcode()" placeholder="우편" >
              <label for="address1">도로명주소</label> 
        </div>
        	<div class="form-floating mb-3">
             <input class="form-control" type="text" name="address2" id="sample4_roadAddress"   placeholder="우편">
              <label for="address2">상세주소</label> 
        </div>
			
	

		<div>
	
			<input class="w-btn-outline w-btn-green-outline" type="submit" value="가입" style="margin-right:  20px;"> 
			<input class="w-btn-outline w-btn-red-outline"  type="reset" value="초기화">

		</div>


	</form>
	</div>
		<!-- 푸터 들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 -->
</body>
</html>
















