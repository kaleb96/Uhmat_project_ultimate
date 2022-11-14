
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
<link href="css/adminManageMent.css" rel="stylesheet">
<style type="text/css">
#home >input,button{
font-size: 25px;
}
</style>
<script src="./js/jquery-3.6.0.js"></script>
<script type="text/javascript">
$(function(){
	 $("#view").attr("src", "AllBoardList.ad");
	
	$("#allBoard").on("click",function(){
		$('#view').empty();
		 $("#view").attr("src", "AllBoardList.ad");
	
		
	});
	
	$("#allMember").on("click",function(){
		$('#view').empty();
		 $("#view").attr("src", "AdminManageMemberList.ad");
		
		
	});
	
});

</script>
</head>
<body>
<div id="home" align="center">
	<input type="button" value="홈" onclick="location.href='main.jsp'" class="before_next">
	<button id="allBoard" class="before_next">모든게시물</button>
	<button id="allMember"  class="before_next">회원관리</button>
</div>	
<iframe id="view"  frameborder="0" border="0" cellspacing="0"
                style="border-style: none;width: 100%; height: 800px;"></iframe>
</body>
</html>