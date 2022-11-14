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
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
	<script src="https://apis.google.com/js/api:client.js"></script>
<script type="text/javascript" src="./js/jquery-3.6.0.js"></script>
<style type="text/css">
.flex-container {
	display: flex;
}

.flex-container>div {
	
	height: 100%;
	width: 50%;
	padding: 20px;
	font-size: 30px;
	text-align: center;
	border: 1px solid;
	border-bottom: 0;
}
#slmpleLog{
border-left: 0;

}

#log{
		border-right: 0;
}
.login-container {
	height: 650px;
	margin: 0 auto;
	padding-top:50px;
	width: 995px;
	font-size: 30px;
}

.buttonResult {
	
	width: 100%;
	height: 550px;
	 padding-top: 20px; 
	text-align: center;
}

.buttonResult2 {
	
	width: 100%;
	height: 550px;
	/* 	padding: 20px; */
	
	text-align: center;
}

#iframe01 {
	width: 400px;
	height: 75px;
	margin-top: 100px;
}

#iframe02 {
	width: 400px;
	height: 75px;
}

#iframe03 {
	width: 400px;
	height: 75px;
}

#iframe2 {
	height: 500px;
	margin-top: 100px;
}

    #customBtn {
      display: inline-block;
      background: white;
      color: #444;
      width: 300px;
      text-align: center;
      border-radius: 5px;
      border: thin solid #888;
      box-shadow: 1px 1px 1px grey;
      white-space: nowrap;
    }
    #customBtn:hover {
      cursor: pointer;
    }
    span.label {
      font-family: serif;
      font-weight: normal;
    }
    span.icon {
      background: url('/identity/sign-in/g-normal.png') transparent 5px 50% no-repeat;
      display: inline-block;
      vertical-align: middle;
      width: 42px;
      height: 42px;
    }
    span.buttonText {
      display: inline-block;
      vertical-align: middle;
      padding-right: 42px;
      font-size: 20px;
      font-weight: bold;
      /* Use the Roboto font that is loaded in the <head> */
      font-family: 'Roboto', sans-serif;
    }
</style>
<script type="text/javascript">
	$(function() {
		var div1 = document.getElementById('login');
		var div2 = document.getElementById('slmpl');
		$("#log").text("로그인");
		$("#slmpleLog").text("간편로그인");
		div2.style.display = 'none';
		div1.style.display = 'block';
		$("#log").click(function() {
			div2.style.display = 'none';
			div1.style.display = 'block';
			// btn1 숨기기 (display: none)
			if (div2.style.display !== 'none') {
				div2.style.display = 'none';
			}
			// btn` 보이기 (display: block)
			else {
				div1.style.display = 'block';
			}

		});
		$("#slmpleLog").click(function() {
			div1.style.display = 'none';
			div2.style.display = 'block';
			// btn1 숨기기 (display: none)
			if (div1.style.display !== 'none') {
				div1.style.display = 'none';
			}
			// btn` 보이기 (display: block)
			else {
				div2.style.display = 'block';
			}

		});
		// 토글 할 버튼 선택 (btn1)
	
		
		
	});
	
	

</script>
  <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
  <script src="https://apis.google.com/js/api:client.js"></script>
  <script src="./js/jquery-3.6.0.js"></script>
  <style type="text/css">
    #customBtn {
      display: inline-block;
      background: white;
      color: #444;
      width: 300px;
      text-align: center;
      border-radius: 5px;
      border: thin solid #888;
      box-shadow: 1px 1px 1px grey;
      white-space: nowrap;
    }
    #customBtn:hover {
      cursor: pointer;
    }
    span.label {
      font-family: serif;
      font-weight: normal;
    }
 
    span.buttonText {
      display: inline-block;
      vertical-align: middle;
      padding-right: 42px;
      font-size: 20px;
      font-weight: bold;
      /* Use the Roboto font that is loaded in the <head> */
      font-family: 'Roboto', sans-serif;
    }
  </style>
  <script>
 
	 
  var googleUser = {};
  var startApp = function() {
    gapi.load('auth2', function(){
      // Retrieve the singleton for the GoogleAuth library and set up the client.
      auth2 = gapi.auth2.init({
        client_id: '837887660613-421qj1q5bgrv1b2h0fbd3nk629l9ounm.apps.googleusercontent.com',
        cookiepolicy: 'single_host_origin',
        plugin_name: "uhmat",
        // Request scopes in addition to 'profile' and 'email'
        scope: 'profile email'
      });
      attachSignin(document.getElementById('customBtn'));
    });
  };
 	

function attachSignin(element) {
    console.log(element.id);
    auth2.attachClickHandler(element, {},
        function(googleUser) {
    		$("#name1").val(googleUser.getBasicProfile().getName());
    		$("#email1").val(googleUser.getBasicProfile().getEmail());
    		$("#api_id1").val(googleUser.getBasicProfile().getId());
    		document.gooleSubmit.submit();
    	
        
        }, function(error) { 
          alert(JSON.stringify(error, undefined, 2));
        });

  }

  
  </script>


</head>
<body>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../inc/header.jsp" flush="false" />
	<!-- 헤더 들어가는곳 -->

	<div class="login-container">
		<div class="flex-container">
			<div id="log"></div>
			<div id="slmpleLog"></div>
		</div>
		<div class="buttonResult" id="slmpl">
<!-- 			<iframe name="iframe1" id="iframe01" src="MemberNaverForm.me" -->
<!-- 				frameborder="0" cellspacing="0"></iframe> -->
		    <jsp:include   page="naverlogin.jsp"></jsp:include>
			<br>
			<br>
<!-- 			<iframe name="iframe1" id="iframe02" src="MemberKakaoForm.me" -->
<!-- 				frameborder="0" cellspacing="0"></iframe> -->
  			<jsp:include  page="kakao.jsp"></jsp:include>
  			
			<br>
			  <!-- In the callback, you would hide the gSignInWrapper element on a
  successful sign in -->
			  <div id="gSignInWrapper">
			    <div id="customBtn" class="customGPlusSignIn">
			      <span class="icon"></span>
			      <span class="buttonText">Google계정으로 로그인</span>
			    </div>
			  </div>
			 <script>startApp();</script>
			  
			  
			  <form action="MemberGoogleJoinPro.me" name="gooleSubmit" method="post">
			  <input type="hidden" id="name1" name="name">
			  <input type="hidden" id="email1" name="email">
			  <input type="hidden" id="api_id1" name="api_id">
			  </form>
  			
			<br>
		</div>
		<div class="buttonResult2" id="login">
			<form action="MemberLoginPro.me" method="post"  >
			
				<div class="form-floating mb-3" style="margin-top: 50px;">
					 <input class="form-control" type="text" name="email" placeholder="이메일"
						required="required">
						<label>email</label>
				</div >
				<div class="form-floating mb-3">
					 <input class="form-control"  type="password" name="passwd" placeholder="패스워드"
						required="required"><label>비밀번호</label>
				</div>
		
				<div>
				<input class="w-btn-outline w-btn-green-outline" type="submit" value="로그인" style="">
				<span style="margin: 40px 40px auto"></span>
				<button  class="w-btn-outline w-btn-green-outline"  onclick="location.href='MemberJoinForm.me'">회원가입</button>
				</div> 
			</form>

			<div style="text-align:center;">
			 <br>
			 <a href="MemberFindPasswordForm.me" ><button class="w-btn-outline w-btn-green-outline"> 비밀번호 찾기 </button></a>
			</div>
		</div>
	</div>

	<!-- 푸터 들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 -->
	
</body>
</html>