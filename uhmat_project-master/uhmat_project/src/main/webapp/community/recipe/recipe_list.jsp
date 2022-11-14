<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
<link href="css/recipe/recipeList.css" rel="stylesheet">
</head>
<body>
	<!-- 		헤더 들어가는 곳 -->
		<jsp:include page="../../inc/header.jsp"/>
	<!-- 		헤더 들어가는 곳 -->
	
	<div align="center" class="recipeBanner">
		<img alt="recipe 배너" src="./image/recipe/recipe_banner.png">
		
	</div>
	
	<div align="center" id="menuBar">
		<button onclick="location.href='MateList.co'">mate</button>
		<button onclick="location.href='TmiList.co'">tmi</button>
		<button onclick="location.href='RecipeList.co'">recipe</button>
		<br>
	</div>
	
	<!-- ---------------------------------------------------------------------------- -->
	<main>
	<div class="main" align="center">
		<c:choose>
			<c:when test="${not empty recipeList and pageInfo.listCount gt 0 and empty recipeSearchPageInfo.listCount}">
				<c:forEach var="recipe" items="${recipeList }">
					<div class= "recipeList">
						<a href="RecipeDetail.co?idx=${recipe.idx }&pageNum=${pageInfo.pageNum}">
								<img class="recipeImg"  width="100" src="upload/recipe_upload/${recipe.real_File1 }" onerror="this.onerror=null; this.src='image/recipe/recipe_default_img.png'" >
						</a>
						
						<div>
							<a href="RecipeDetail.co?idx=${recipe.idx }&pageNum=${pageInfo.pageNum}">
								<b>${recipe.subject }</b>
							</a>
						</div>
						
						<span>${recipe.nickname }</span> <br>
						<span>${recipe.readcount }</span> <br>  
						<span>${recipe.date }</span>
						
					</div>
				</c:forEach>
			</c:when>
			
			<c:when test="${not empty recipeSearchPageInfo.listCount }">
	 			<c:forEach var="recipe" items="${recipeSearchList}">
	 			<div class= "recipeList"> 
						<a href="RecipeDetail.co?idx=${recipe.idx }&pageNum=${pageInfo.pageNum}">
							<img class="recipeImg" src="upload/recipe_upload/${recipe.real_File1 }" onerror="this.onerror=null; this.src='image/recipe/recipe_banner.png'" >
						</a>
					
					<div>
						<a href="RecipeDetail.co?idx=${recipe.idx }&pageNum=${pageInfo.pageNum}'">
						<b>${recipe.subject }</b>
						</a>
					</div>
					
						<span>${recipe.nickname }</span> <br>
						<span>${recipe.readcount }</span> <br>  
						<span>${recipe.date }</span>
					</div>
				</c:forEach>
	 		</c:when>
	 		
			<c:otherwise>
				<tr><td colspan="5">게시물이 존재하지 않습니다.</td></tr>
			</c:otherwise>
		</c:choose>
	</div>
	
	</main>	
	
	<!-- ---------------------------------------------------------------------------- -->
	
	<!-- 
		현재 페이지 번호(pageNum)가 1보다 클 경우에만 [이전] 링크 동작
		=> 클릭 시 mateList.mate 서블릿 주소 요청하면서 
		   현재 페이지 번호(pageNum) - 1 값을 page 파라미터로 전달
	-->
	<div align="center" style="clear: both;">
	
	
	<i id="plus" class='fas fa-plus-circle' style='position: relative; font-size:48px;color:#6C757D;' onclick="location.href='RecipeWriteForm.co'"/></i>
	
	<br>
	
	<!-- 	레시피 검색 -->
	<div align="center">
		<form action="RecipeSearch.co" method="get" class="search">
			<input type="text" placeholder="search" name="keyword" value=${param.keyword }>
			<input type="submit" value="검색" class="searchBtn">
    	</form>
	</div>
	
	<br>

	<section class="pageList">
	
		<c:choose>
			<c:when test="${pageInfo.pageNum > 1}">
				<input type="button" class="before_next" value="이전" onclick="location.href='RecipeList.co?pageNum=${pageInfo.pageNum - 1}'">
			</c:when>
			<c:when test="${recipeSearchPageInfo.pageNum > 1}">
					<input type="button" class="before_next" value="이전" onclick="location.href='RecipeSearch.co?pageNum=${recipeSearchPageInfo.pageNum - 1}&keyword=${param.keyword }'">
				</c:when>
			<c:otherwise>
				<input type="button" class="before_next" value="이전">
			</c:otherwise>
		</c:choose>
		
		<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
	<c:choose>
		<c:when test="${empty recipeSearchPageInfo }">
			<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
				<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
				<c:choose>
					<c:when test="${pageInfo.pageNum eq i}">
						${i }
					</c:when>
					<c:otherwise>
						<a href="RecipeList.co?pageNum=${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>
					<c:forEach var="i" begin="${recipeSearchPageInfo.startPage }" end="${recipeSearchPageInfo.endPage }" >
						<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
						<c:choose>
							<c:when test="${recipeSearchPageInfo.pageNum eq i}">
								${i}
							</c:when>
							<c:otherwise>
								<a href="RecipeSearch.co?pageNum=${i}&keyword=${param.keyword }">${i} &nbsp;</a>
			
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:otherwise>
		</c:choose>	
	<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
	<c:choose>
		<c:when test="${pageInfo.pageNum < pageInfo.maxPage}">
			<input type="button" class="before_next" value="다음" onclick="location.href='RecipeList.co?pageNum=${pageInfo.pageNum + 1}'">
		</c:when>
		<c:when test="${recipeSearchPageInfo.pageNum lt recipeSearchPageInfo.maxPage}">
			<input type="button" class="before_next" value="다음" onclick="location.href='RecipeSearch.co?pageNum=${recipeSearchPageInfo.pageNum + 1}&keyword=${param.keyword }'">
		</c:when>
		<c:otherwise>
			<input type="button" class="before_next" value="다음">
		</c:otherwise>
	</c:choose>
	</section>	
	</div>
	
	
	<!-- 		푸터 들어가는 곳 -->
		<jsp:include page="../../inc/footer.jsp"/>
	<!-- 		푸터 들어가는 곳 -->
</body>
</html>