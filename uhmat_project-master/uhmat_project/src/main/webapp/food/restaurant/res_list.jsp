<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="food/restaurant/res_list.css" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<script>
	var isRun = false;
	//스크롤 이벤트 발생
	window.onscroll = function() {
		if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
// 			alert("바닥에 닿음");
			if(${pageInfo.endPage} >= ${pageInfo.pageNum + 1}){
				$.ajax({
					type: "post",
					url: "restaurantList.re",
					data: {
						pageNum : ${pageInfo.pageNum +1},
						<%if(request.getParameter("category")!=null){ %>
						category : "${param.category}",
						<%} 
						if(request.getParameter("keyword")!=null){%>
						keyword : "${param.keyword}" ,
						<%} %>
						<%if(request.getParameter("windowOpen")!=null){ %>
						windowOpen : true
						<%}%>
					},
					dataType: "text",
					async : false,
					success: function(response) {
						isRun  = false;
// 						alert("실행됨");
						var content = $("#append").html(response).find(".append");
						if(content !=null)
// 						alert(content);
						if(${pageInfo.endPage} >= ${pageInfo.pageNum + 1}) {
							$("#repeat").append(content);
						}		
					},
				});
			}
		} 
	}
	
	$(function(){
		//키워드 검색시 서블릿으로 이동
		$("#keywordSelect").on("click",function(){
			<%if(request.getParameter("windowOpen")!=null){ %>
			location.href="restaurantList.re?windowOpen=true&keyword="+$("#keyword").val();
			<%}else{%>
			location.href="restaurantList.re?keyword="+$("#keyword").val();
			<%}%>
		});
		
		<%if(Boolean.parseBoolean(request.getParameter("windowOpen"))){%>
			$("header").remove();
			$("footer").remove();
		<%}%>
		
		//클릭시 부모창에 값을 전달
		$(".append").on("click",function(){
// 			alert($(this).closest("tr").html());
			var currentTr = $(this).closest("tr");
			var insertValue = currentTr.find("td:eq(1)").html();
// 			alert($(".append").eq(index));
// 			alert( $(".append").eq(index).find("td:eq(0)").html());
			var index = $(".append").index(this);
			window.opener.document.getElementById('searchRes').value = insertValue;
// 			window.opener.document.getElementById('searchRes').value = $(".append>td").eq(insertResValue).html();
			window.close();
		});
	});
	
	
</script>
</head>
<body class="windowChild">
	<jsp:include page="../../inc/header.jsp"></jsp:include>
	<!-- 식당 검색  -->
	<section id="mother">
	<h1>식당 찾기</h1>
		<section>
			<div class="dv"><span class="lv">음식점 찾기</span>
			<input type="text" placeholder="음식점을 검색하세요" id="keyword" name="keyword" size="15"> 
			<input class="btn" type="button" value="검색" id="keywordSelect">
			<input type="button" value="카테고리별로 보기" onclick="location.href='resCategory.re'" class="btn">
			</div>
		</section>
		
		<!-- 검색 리스트 -->
		
		<!-- 제목(th) 칸 -->
		
		<section>
			<table>
				<tr>
					<th> 카테고리 </th>
					<th> 식당이름 </th>
					<th> 별점 </th>
					<th> 리뷰 개수</th>
					<th> 사진 </th>
				</tr>
			</table>
		</section>
		
		<!-- 리스트 ajax 부분 -->
		<section id="bestResCall">
			<table id="repeat">
				<c:choose>
					<c:when test="${empty restaurantInfo }">
		<!-- 				<tr> -->
		<!-- 					<td colspan="4"> -->
		<!-- 						게시된 식당이 없습니다. -->
		<!-- 					</td> -->
		<!-- 				</tr> -->
					</c:when>
					<c:otherwise>
						<c:forEach items="${restaurantInfo }" var="resInfo">
							<tr onclick="location.href='restaurantDetail.re?resName=${resInfo.resName}'" class="append">
								<td>${resInfo.category }</td>
								<td>${resInfo.resName }</td>
								<td>${resInfo.rating }</td>
								<td>${resInfo.reviewCount }</td>
								<td><img width="200" src="upload/${resInfo.photo }"></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</section>
		<section id="append" style="display: none;">
		
		</section>
		
		<c:if test="${sessionScope.sNickName == 'admin' }">
			<section id="buttons">
				<button class="btn" onclick="location.href='restaurantWriteForm.re'">글쓰기</button>
				<button class="btn" onclick="location.href='resCategory.re'">카테고리 보기</button>
			</section>
		</c:if>
	</section>
	<jsp:include page="../../inc/footer.jsp"></jsp:include>
</body>
</html>