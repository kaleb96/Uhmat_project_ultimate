<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.6.0.js"></script>
<script src="https://kit.fontawesome.com/5382a0d7e0.js" crossorigin="anonymous"></script>
<!-- <link href="css/header.css" rel="stylesheet" type="text/css"> -->
<!-- <link href="css/footer.css" rel="stylesheet" type="text/css"> -->
<script type="text/javascript">
	
//content 글자 수 제한 코드 
$(document).ready(function(){
//	    $('p[id^=content]').each(function(){
//	        if ($(this).text().length > 120) {
//	        $(this).html($(this).text().substr(0,120)+"<br>" +"<span id='highlight' onclick='more()'>...더보기</span>");
//	        }
//	    });
    
    $("#nextBestReview").on("click",function(){
    	 $.ajax({
	   			type: "post",
	   			url: "ReviewBest.ma",	//리뷰를 좋아요 순으로 가져오는 요청
	   			data:{
	   				pageNum: ${pageInfo.pageNum}+1
	   			},
	   			dataType: "text",
	   			async : false,
	   			success: function(response) {
  					var content = $("#bestReview").html(response).find("#list");
//   					alert(response);
  					$("#bestReview").html(content);
	   		   }
	   		});
    });
    
  window.onload = function(){
	   $.ajax({
			type: "post",
			url: "ReviewBest.ma",	//리뷰를 좋아요 순으로 가져오는 요청
		
			dataType: "text",
			async : false,
			success: function(response) {
// 					var co ntent = $("#bestReview").html(response).find("#list");
// 					$("#bestReview").html(content);
				var result = $(response).find("#list");
				var title = $(response).find("#subject").eq(0);
// 				alert(title.text());
				var image = result.find("img:eq(0)");
				$("#bestReview img:eq(0)").attr('src',image.prop('src'));
				
				for(var i=0;i<4;i++){
					var image = result.find("img:eq("+i+")");
					var title = $(response).find("#subject").eq(i);
					$("#bestReview img:eq("+i+")").attr('src',image.prop('src'));
					$("#bestReview tr:eq(0) td:eq("+i+")").html(title);
				}
		   }
		});
   	   
   	$.ajax({
			type: "post",
			url: "ReviewRecent.ma",	//리뷰를 좋아요 순으로 가져오는 요청

			dataType: "text",
			async : false,
			success: function(response) {
// 				var image = $("#recentReview").html(response).find("#list");
// 				alert("반환 성공!");
				var result = $(response).find("#list");
				var image = result.find("img:eq(0)");
				$("#recentReview img:eq(0)").attr('src',image.prop('src'));
				
				for(var i=0;i<4;i++){
					var image = result.find("img:eq("+i+")");
					var title = $(response).find("#subject").eq(i);
					$("#recentReview img:eq("+i+")").attr('src',image.prop('src'));	
					$("#recentReview tr:eq(0) td:eq("+i+")").html(title);
				}
		   }
		});
   	   
   	$.ajax({
			type: "post",
			url: "restaurantList.re",	//식당 정보를 가져오는 요청
			data: {
				bestRes : true
			},
			dataType: "text",
			async : false,
			success: function(response) {
//				alert($(response).find("#repeat").html());
			var table = $(response).find("#repeat");
			var td = table.find("tr.append > td:eq(1)").text();
			var td2 = table.find("tr:eq(0) td:eq(4) img").prop('src');
// 			var inputTd = $("#bestRes td:eq(0)").text();
// 			alert(td);
// 			alert(td2);
// 			$("#bestRes td").eq(0).text(td.text());
// 			$("#bestRes img").eq(0).attr('src',td2);
			
			for(var i=0;i<4;i++){
				var td = table.find("tr:eq("+i+").append > td:eq(1)").text();
				var td2 = table.find("tr:eq("+i+") td:eq(4) img").prop('src');
// 				alert(td);
// 				alert(td2);
				$("#bestRes tr:eq(0) td:eq("+i+")").text(td);
				$("#bestRes img:eq("+i+")").attr('src',td2);
			}
				
		   }
		});
   	
	   
  }

});
	
</script>
</head>
<body>
	<!-- 헤더 들어가는곳 -->
	<header id="header">	
		<jsp:include page="inc/header.jsp" flush="false" />
	</header>	
	<!-- 헤더 들어가는곳 -->
	
	
<!-- 	<hr>		 -->
			 
	<!-- 메인페이지 영역 시작 -->
	<div class="mainContainer">	
	<!-- 검색창 시작 -->
	
<!-- 	<section id="sec01"></section> -->
<!-- 	<div id="mainImageContainer"> -->
<!-- 		<img class="mainImage" src="image/mainSample3.jpg"> -->
<!-- 	</div> -->
	<!-- 검색창 끝 -->
	
	<!-- 본문들어가는 곳 시작 -->
	<section id="mainSec01">
		<div class="mainSec01"></div>
	</section>

	
		<!-- 최고의 리뷰 순위 시작 -->
		<div class="mainViewContainer">	
			<div class="rankContainer">
				<div class = "imgContainer" ><h2>어맛 최다 좋아요 리뷰 <i class="fa-regular fa-heart"></i></h2>
				<p><div class="moreInformation01" onclick="location.href='ReviewList.re'" style="cursor:pointer;">모두 보기</div></p>
						<div id="bestReview">
							<table class="mainReviewTable">
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><img src="#" width="300" height="200"></td>
								<td><img src="#" width="300" height="200"></td>
								<td><img src="#" width="300" height="200"></td>
								<td><img src="#" width="300" height="200"></td>
							</tr>
						</table>	
						</div>
				</div>
			</div>
		<!-- 최고의 리뷰 순위 끝 -->
		<div class="clear"></div>
		<!-- 최신 리뷰 시작 -->
			<div class="rankReview">
				<div class = "imgContainer"><h2>어맛 최신 리뷰 <i class="fa-regular fa-clock"></i></h2>
				<p><div class="moreInformation02" onclick="location.href='ReviewList.re'" style="cursor:pointer;">모두 보기</div></p>
					<div>
						<div id="recentReview">
						<table class="mainReviewTable">
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><img src="#" width="300" height="200"></td>
								<td><img src="#" width="300" height="200"></td>
								<td><img src="#" width="300" height="200"></td>
								<td><img src="#" width="300" height="200"></td>
							</tr>
						</table>						
					</div>
					</div>
				</div>
			</div>
		<!-- 최신 리뷰 끝 -->
		
		<!-- 어맛 추천 리뷰 시작 -->
			<div class="rankReview" id="bestRes">
				<div class = "imgContainer"><h2>어맛 추천 식당 <i class="fa-regular fa-thumbs-up"></i></h2>
				<p><div class="moreInformation03" onclick="location.href='restaurantList.re'" style="cursor:pointer;">모두 보기</div></p>
					<!-- 별점, 댓글의 개수 순으로 내림차순 정렬된 식당의 값 -->
					<div id="bestRes">
						<table class="mainReviewTable">
							<tr>
								<td>gdgd</td>
								<td>das</td>
								<td>abc</td>
								<td>def</td>
							</tr>
							<tr>
								<td><img src="#" width="300" height="200"></td>
								<td><img src="#" width="300" height="200"></td>
								<td><img src="#" width="300" height="200"></td>
								<td><img src="#" width="300" height="200"></td>
							</tr>
						</table>						
					</div>
				</div>
			</div>
		<!-- 어맛 추천 리뷰 끝 -->
		</div>
		<!-- 사이드바 들어가는곳 -->
			<jsp:include page="inc/sidebar.jsp" flush="false" />
		<!-- 사이드바 들어가는곳 -->
		
	</div>
<!-- 메인페이지 영역 끝 -->		
<!-- <hr> -->
	
	<section id="mainSec02">
		<div class="categoryImageContainer">
			
		</div>
	</section>
	
	<!-- 식당 카테고리 페이지 시작 -->
	<div class="restaurantCategory">
		<jsp:include page="food/restaurant/category_page.jsp" />
	</div>	
	<!-- 식당 카테고리 페이지 끝 -->
	
	<!-- 푸터 들어가는곳 시작 -->
	<footer id="footer">
		<jsp:include page="inc/footer.jsp" flush="false" />
	</footer>	
	<!-- 푸터 들어가는곳 끝 -->		

</body>
</html>   