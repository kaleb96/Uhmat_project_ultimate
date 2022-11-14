<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./js/jquery-3.6.0.js"></script>

<script>
	var nicknameFlag = false;
	$(function() {
		$.ajax({
			url : "http://localhost:8080/uhmat_project/MemberBoardList.me", // ./ 현재경로표시
			type : "get",
			data : {
				title : $("#selectBox").val()
			},

			dataType : "text",
			success : function(data) {
				$('#boadlist').html(data);
			},
			errer : function() {
				alert('errer');
			}

		});

		$("#selectBox").on("change", function() {
			$.ajax({
				url : "http://localhost:8080/uhmat_project/MemberBoardList.me", // ./ 현재경로표시
				type : "get",
				data : {
					title : $("#selectBox").val()
				},

				dataType : "text",
				success : function(data) {
					$('#boadlist').html(data);
				},
				errer : function() {
					alert('errer');
				}

			});

		});
		$("input[type=reset]").on("click", function() {
			$("#EmailResult").html("");
			$("#checkNickNameResult").html("");
		});

		$("#emailCheck").on("change", function() {
			// 비밀번호 & 비밀번호확인란이 같은지 판별
			let email = $("#email").val();
			let email2 = $("#emailCheck").val();

			// 두 패스워드 비교
			if (email == email2) {
				$("#confirmEmailResult").html("일치 합니다");
				$("#confirmEmailResult").css("color", "GREEN");

			} else {
				$("#confirmEmailResult").html("일치 하지 않습니다");

				$("#confirmEmailResult").css("color", "RED");

			}

		});

		$("#newNickName")
				.on(
						"keyup",
						function() {

							var regex = /^[가-힣a-zA-Z][가-힣a-zA-Z0-9!@#$%()]{4,10}$/;

							if (!regex.exec($("#newNickName").val())
									|| $("#newNickName").val() == null) { // 부적합한 아이디일 경우

								$("#checkNickNameResult")
										.html(
												"한글, 영어로  4~16자리 필수! (주의 : 맨앞 특수문자,숫자 불가)");
								$("#checkNickNameResult").css("color", "RED");
								nicknameFlag = false

							} else {
								$
										.ajax({
											type : "post",
											url : "http://localhost:8080/uhmat_project/CheckDuplicateNickName.me",
											data : {
												nickName : $("#newNickName").val(),
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
													nicknameFlag = true
												} else {
													if($("#newNickName").val() ==$('#nickName').val()){
														nicknameFlag=true;
													}else{
													$('#checkNickNameResult')
															.text(
																	'이미 사용 중인 닉네임입니다.');
													$("#checkNickNameResult")
															.css("color", "RED");
													nicknameFlag = false
												}
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

							if (!regex.exec($("#email").val())
									|| $("#email").val() == null) { // 부적합한 아이디일 경우

								$("#EmailResult").html("잘못된 이메일 형식입니다. ");
								$("#EmailResult").css("color", "RED");

							} else {
								$
										.ajax({
											type : "post",
											url : "http://localhost:8080/uhmat_project/CheckDuplicateNickName.me",
											data : {
												nickName : $("#email").val(),
											},
											dataType : "text",
											success : function(data) {
												if (data === 'usable') {
													$('#EmailResult')
															.text(
																	'사용할 수 있는 email입니다.');
													$("#EmailResult").css(
															"color", "GREEN");
												} else {
													$('#EmailResult')
															.text(
																	'이미 사용 중인 email입니다.');
													$("#EmailResult").css(
															"color", "RED");
												}
											},
											error : function(data, textStatus) {
												console.log('error');
											}
										});
							}

						});

		$('form').submit(function() {
			if($("#newNickName").val() ==$('#nickName').val()){
				nicknameFlag=true;
			}

			if (!nicknameFlag) {
				$('#nickName').focus();
				return false
			} else {
				return true
			}

		})

	});

	$(function() {

		var imgList = [ "image/character/1-1.jpg", "image/character/2-1.jpg",
				"image/character/3-1.jpg", "image/character/4-1.jpg",
				"image/character/5-1.jpg", "image/character/6-1.jpg",
				"image/character/7-1.jpg" ];
		var i = 0;
		var selectjpg = "";

		$("#modal-make").click(function() {
			$("#imgSlide").attr("src", imgList[i]);
			$(".modal").fadeIn();
		});

		$("#imgSlide").click(function() {
			$("#myjpg").attr("src", imgList[i]);
			$(".modal").fadeOut();

		});

		$("#prev").click(function() {
			i--;
			$("#imgSlide").attr("src", imgList[i]);
			selectjpg = (i+1) + "-1.jpg";
			$("#icon").val(selectjpg);
			if (i < 0) {
				i = imgList.length - 1;
				$("#imgSlide").attr("src", imgList[i]);
				selectjpg = (i+1) + "-1.jpg";
				$("#icon").val(selectjpg);
			}

		});

		$("#next").click(function() {
			i++;
			$("#imgSlide").attr("src", imgList[i]);
			selectjpg = (i+1) + "-1.jpg";
			$("#icon").val(selectjpg);
			if (i >= imgList.length) {
				i = 0;
				$("#imgSlide").attr("src", imgList[i]);
				selectjpg = (i+1)+ "-1.jpg";
				$("#icon").val(selectjpg);
			}

		});

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
<style type="text/css">
.modal {
	position: absolute;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.8);
	top: 0;
	left: 0;
	display: none;
}

.modal_content {
	width: 300px;
	height: 300px;
	background: #fff;
	border-radius: 10px;
	top: 50%;
	left: 50%;
	margin-top: 100px;
	text-align: center;
	box-sizing: border-box;
	padding: 25px 0;
	line-height: 23px;
	cursor: pointer;
}

#container {
	/* 	border: 1px solid #ccc; */
	width: 800px;
	margin: 0 auto;
	/* 	display: flex; */
	padding-top: 20px;
	padding-bottom: 20px;
	font-family: 'Poor Story', cursive;
	font-size: 20px;
}

.joinFrom {
	width: 800px;
	align-items: center;
}

h1 {
	text-align: center;
}

.form-control {
	display: block;
	width: 50%;
	padding: 0.375rem 0.75rem;
	font-size: 1rem;
	font-weight: 700;
	line-height: 1.5;
	color: #212529;
	background-color: #fff;
	background-clip: padding-box;
	border: 2px solid #333;
	font-family: 'Poor Story', cursive;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	border-radius: 25px;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

label {
	font-family: 'Poor Story', cursive;;
}

.flex-container {
	display: flex;
}

.flex-container>.left {
	height: 100%;
	width: 80%;
	text-align: left;
}

.flex-container>.right {
	height: 100%;
	width: 20%;
	text-align: left;
}
 #selectBox {
  width: 100px;
  padding: .2em .1em;
  font-family: inherit;
  background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  font-family: 'Poor Story', cursive;
  font-size: 20px;
  border: 1px solid #999;
  border-radius: 0px;
}

</style>
</head>
<body>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../inc/header.jsp" flush="false" />
	<!-- 헤더 들어가는곳 -->
	<div id="container">

		<h1>내정보</h1>
		<div class="flex-container">
			<div class="left">
				<form action="MemberDetailModifyPro.me" method="post">


					<input type="hidden" id="icon" name="icon" value="${member.icon}">
					<input type="hidden" id="nickName" name="nickName"
						value="${member.nickname }"> <label>이름</label> <input
						class="form-control" type="text" id="name" name="name"
						value="${member.name }" required="required"> <br>
					<div>
						<label>닉네임</label> <input class="form-control" type="text"
							id="newNickName" name="newNickName" value="${member.nickname }">
						<span id="checkNickNameResult"></span>
					</div>
					<br>
					<div>
						<label>이메일</label> <input class="form-control" type="hidden"
							id="email" name="email" value="${member.email }"
							readonly="readonly">
						<div>
							<label class="form-control">${member.email }</label> <span
								id="EmailResult"></span>
						</div>

					</div>
					<br>
					<div>
						<label>생년월일</label><br> <input class="form-control"
							type="date" id="birth" name="birth" value="${member.birthdate }">
					</div>
					<br>
					<div>
						<label>비밀번호 변경</label><br> <input class="form-control"
							type="button" value="비밀번호 변경 하기"
							onclick="location.href='MemberPasswordModifyForm.me'"
							style="width: 20%">
					</div>
					<br> 
					<label>우편번호 주소</label>
					<div>
						<input class="form-control" type="button"
							onclick="execDaumPostcode()" value="우편번호 찾기" style="width: 20%"><br>
						<input class="form-control" type="text" id="sample4_postcode"
							name="postCode" placeholder="우편번호" required="required"
							readonly="readonly" onclick="execDaumPostcode()"
							value="${member.postCode }"> <input class="form-control"
							type="text" id="sample4_roadAddress" name="address1"
							value="${member.address1 }" placeholder="도로명주소"
							required="required" readonly="readonly"
							onclick="execDaumPostcode()"> <input class="form-control"
							type="text" value="${member.address2 }"
							id="sample4_detailAddress" name="address2" placeholder="상세주소"
							required="required">
					</div>
					<br> <input class="before_next" type="submit" value="수정하기">

				</form>
			</div>
			<div class="right">
				<div align="center">
					<img id="myjpg" alt="어맛캐릭터" src="image/character/${member.icon}"
						width="125px" height="125px"><br>
					<button class="before_next" id="modal-make">캐릭터 변경</button>
					<div class="modal">
						<div class="modal_content" title="클릭하면 창이 닫힙니다.">
							<h2>어맛 캐릭터</h2>
							<button  class="before_next" id="prev">이전</button>
							<img id="imgSlide" src="image/character/1-1.jpg" width="150"
								height="150" alt="물감칠한사진">
							<button class="before_next" id="next">다음</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<section>
			<div>
				보고싶은 리스트 <select id="selectBox" id="selectBox" name="title">
					<option value="Mate" selected="selected">Mate</option>
					<option value="Tmi">Tmi</option>
					<option value="Recipe">Recipe</option>
					<option value="FAQ">FAQ</option>
				</select>
			</div>


			<div id="boadlist"></div>

		</section>
	</div>
	<!-- 푸터 들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 -->
</body>
</html>