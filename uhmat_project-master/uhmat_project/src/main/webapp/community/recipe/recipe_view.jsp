<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
<title>Insert title here</title>
<link href="css/recipe/recipeView.css" rel="stylesheet">
</head>
<body>
	<!-- 		헤더 들어가는 곳 -->
		<jsp:include page="../../inc/header.jsp"/>
	<!-- 		헤더 들어가는 곳 -->
	<div class="view">
		<table>
			<tr>
<!-- 				아이콘 넣는 곳 -->
				<td rowspan="2"><img alt="아이콘" src="image/character/${recipe.icon }" width="70px"></td>
				<td>${recipe.nickname }</td>
			</tr>
			<tr>
				<td class="time_readcount"><i class="fa fa-clock-o"></i> ${recipe.date } | <i class='far fa-eye'></i> ${recipe.readcount }</td> 
			</tr>
			</table>
			<div id="subject">
				${recipe.subject }
			</div>
			
			<c:if test="${not empty recipe.real_File1 or not empty recipe.real_File2 or not empty recipe.real_File3 or not empty recipe.real_File4 or not empty recipe.real_File5 }">
			<div class="slider">
			<c:set var="plist">${recipe.real_File1 },${recipe.real_File2 },${recipe.real_File3 },${recipe.real_File4 },${recipe.real_File5 }</c:set>
			
			<c:forEach var="listCount" items="${plist }" varStatus="i">
				<c:if test="${fn:length(plist) > 1 }">
					<input type="radio" name="slide" id="slide${i.index + 1 }" checked>
				</c:if>
			</c:forEach>
    		
    			<ul id="imgholder" class="imgs">
    			<c:forEach var="photoList" items="${plist }">
    			<li><img src="./upload/recipe_upload/${photoList }" width="300px" height="300px" onerror="this.style.display='none';"></li>

			</c:forEach>
<!-- 			onerror="this.style.display='none';" img alt 아이콘 없애는 기능 -->
					
				</ul>
				<div class="bullets">
				<c:forEach var="listCount" items="${plist }" varStatus="i">
					 <label for="slide${i.index + 1 }">&nbsp;</label>
				</c:forEach>
    			   
      				
    			</div>
			</div>
			</c:if>
			
		<table>
			<tr>
				<td id="content">${recipe.content }</td> 
			</tr>
		</table>
	
	&nbsp;
	
	<div class="btn">
		
				<input type="button" value="댓글" onclick="location.href='RecipeReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'">
		<c:if test="${sessionScope.sNickName == recipe.nickname }">
			<input type="button" value="수정" onclick="location.href='RecipeModifyForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}'">
			<input type="button" value="삭제" onclick="location.href='RecipeDeleteForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&nickname=${recipe.nickname}&file1=${recipe.real_File1 }&file2=${recipe.real_File2 }&file3=${recipe.real_File3 }&file4=${recipe.real_File4 }&file5=${recipe.real_File5 }'">
		</c:if>
		<input type="button" value="목록" onclick="location.href='RecipeList.co?pageNum=${param.pageNum}'">
	</div>
	&nbsp;
		<table>
		<c:forEach items="${recipeReplyList }" var="recipeReplyList">
			<tr>
				<td>
					<c:forEach begin="1" end="${recipeReplyList.re_lev }">
						&nbsp;&nbsp;<i class="material-icons" style="font-size:20px;color:#6C757D;">subdirectory_arrow_right</i>
					</c:forEach>
<!-- 					댓글 아이콘 넣는 곳 -->
					<img alt="아이콘" src="image/character/${recipeReplyList.icon }" width="40px"> ${recipeReplyList.nickname } | ${recipeReplyList.date }
				</td>
			</tr>
			<tr>
				<td width="500" class="reply"> ${recipeReplyList.content } </td>
				<td class="btn"><input type="button" value="답글" onclick="location.href='RecipeRereplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}&reply_idx=${recipeReplyList.idx} '"></td>
				<c:if test="${sessionScope.sNickName == recipeReplyList.nickname }">
					<td class="btn"><input type="button" value="댓글수정" onclick="location.href='RecipeReplyModifyForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&reply_idx=${recipeReplyList.idx}&nickname=${recipeReplyList.nickname}'"></td>
					<td class="btn"><input type="button" value="댓글삭제" onclick="location.href='RecipeReplyDeleteForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&reply_idx=${recipeReplyList.idx}&nickname=${recipeReplyList.nickname}'"> </td>
				</c:if>
			</tr>
		</c:forEach>
		</table>
	</div>

	<!-- 		푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 		푸터 들어가는 곳 -->
	
</body>
</html>