<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


<html>
<head>
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
    		$("#name").val(googleUser.getBasicProfile().getName());
    		$("#email").val(googleUser.getBasicProfile().getEmail());
    		$("#api_id").val(googleUser.getBasicProfile().getId());
    		document.gooleSubmit.submit();
    	
        
        }, function(error) { 
          alert(JSON.stringify(error, undefined, 2));
        });

  }

  
  </script>
  
  </head>
  <body>
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
  <input type="hidden" id="name" name="name">
  <input type="hidden" id="email" name="email">
  <input type="hidden" id="api_id" name="api_id">
  </form>
 
</body>
</html>
