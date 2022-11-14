<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.js"></script>
<style type="text/css">
div{
font-size: 50px;}
</style>
</head>
<body>

<button type="button">클릭</button>
<!-- html의 Ajax에서 서블릿 접근해서 데이터 가져오기 -->
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div><div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div><div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div>dsadsad</div>
<div id="div"></div>
<script type="text/javascript">
$(function() {
	var count=0;
	$(window).scroll(function() {
		if($(window).scrollTop() == $(document).height() - $(window).height()) {
		$.ajax({
			url : "http://localhost:8080/uhmat_project/MemberDetailList.me",  // ./ 현재경로표시
			type : "get",
			data :  {email:"mms12@gmmksfd.com"}, // 이 값을 가지고 servlet으로 간다.
			dataType: "json",
			success : function(data) {
// 				alert("success");
// 				alert(data);
				count++;
				   $.each(data, function(key, val){
			             <!-- 로그 찍어주는 부분 -->
			             console.log('key:' + key + ' / ' + 'value:' + val['nickName']);
			             $('#div').append(val['nickName']+count+'<br>');
			         });
 			
			
  
			},
			errer : function() {
				alert('errer');
			}
		});
		}
	});
});
</script>

</body>
</html>