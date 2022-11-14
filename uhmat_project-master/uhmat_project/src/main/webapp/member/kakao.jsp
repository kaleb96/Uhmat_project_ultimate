<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="./js/jquery-3.6.0.js"></script>
<script type='text/javascript'>
	Kakao.init('6daf74bc6bdb2ab6d0c28741ed94ae3a'); //★ 수정 할 것 : 내 애플리케이션>앱 설정>앱 키 : JAVASCRIPT KEY 입력하세요
	// SDK 초기화 여부를 판단합니다.
	console.log(Kakao.isInitialized());
</script>

<script type="text/javascript">
	function loginWithKakao() {
		Kakao.Auth.login({
			success : function(authObj) {
				alert("login :" + JSON.stringify(authObj));
				Kakao.API
				.request({
					url : '/v2/user/me',
					success : function(response) {
						
						$("#api_id").val(response.id);
						$("#nickname").val(response.kakao_account.profile.nickname);
						$("#email").val(response.kakao_account.email);
						document.kakaoLogin.submit();
					},
					fail : function(error) {
						console.log(error);
					}
				});
			},
			fail : function(err) {
				alert(JSON.stringify(err))
			},
		})
	}

	function logoutWithKakao() {
		if (!Kakao.Auth.getAccessToken()) {
			console.log('Not logged in.');
			return;
		}
		console.log(Kakao.Auth.getAccessToken()); //before Logout
		Kakao.Auth.logout(function() {
			alert("logout");
			//★ 추가 할 것 : 로그아웃 성공 후 처리 
		});
	}

	
</script>
</head>
<body>
	<a id="custom-login-btn" href="javascript:loginWithKakao()"> <img
		src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
		width="300" height="50"/>
	</a>
	 <form action="MemberKakaoJoinPro.me" name="kakaoLogin"method="post">
                <input type="hidden" id="api_id" name="api_id">
                <input type="hidden"  id="nickname" name="nickname">
                <input type="hidden"  id="email" name="email">
             
	 </form>
              
</body>
</html>