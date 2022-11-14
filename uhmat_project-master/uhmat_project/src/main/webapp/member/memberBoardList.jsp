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
<link href="css/mate/mateList.css" rel="stylesheet">

</head>
<body>


	<table class="mateList">
		<tr id="font_front">
			<td>글 번호</td>

			<td>제목</td>
			<td>닉네임</td>
			<td>작성일</td>

		</tr>
		<!-- 게시물 목록 출력(단, 게시물이 하나라도 존재할 경우에만 출력) -->
		<!-- 조건 : boardList 객체가 비어있지 않고 pageInfo 객체의 listCount 가 0보다 클 경우 -->
		<c:choose>
			<c:when test="${not empty list and pageInfo.listCount gt 0 }">
				<c:forEach var="AllList" items="${list }">
					<tr class="mateListTable">
						<tr>
						<c:if test="${param.title =='Tmi'}">
						<td class="link" width="350" height="50" ><a href="TmiDetail.co?idx=${AllList.idx }&pageNum=1"> ${AllList.idx }</a></td>
						</c:if>
						<c:if test="${param.title =='FAQ'}">
						<td class="link" width="350" height="50" ><a href="FAQDetail.sc?idx=${AllList.idx }&pageNum=1&keyword="> ${AllList.idx }</a></td>
						</c:if>
						<c:if test="${param.title =='Mate'}">
						<td class="link" width="350" height="50" ><a href="MateDetail.co?idx=${AllList.idx }&pageNum=1"> ${AllList.idx }</a></td>
						</c:if>
						<c:if test="${param.title =='Recipe'}">
						<td class="link" width="350" height="50" ><a href="RecipeDetail.co?idx=${AllList.idx }&pageNum=1"> ${AllList.idx }</a></td>
						</c:if>
							<td>${AllList.subject }</td>
							<td>${AllList.nickname }</td>
							<td>${AllList.date }</td>
						</tr>


				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td colspan="5">게시물이 존재하지 않습니다.</td></tr>
			</c:otherwise>
		</c:choose>

	</table>

	&nbsp;

	<div align="center">
		<c:choose>
			<c:when test="${pageInfo.pageNum > 1}">
				<input type="button" value="이전" onclick="location.href='MemberBoardList.me?pageNum=${pageInfo.pageNum - 1}'" class="before_next">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전" class="before_next">
			</c:otherwise>
		</c:choose>

		<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${pageInfo.pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="MateList.co?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
		<c:choose>
			<c:when test="${pageInfo.pageNum < pageInfo.maxPage}">
				<input type="button" value="다음" onclick="location.href='MemberBoardList.me?pageNum=${pageInfo.pageNum + 1}'" class="before_next">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음" class="before_next">
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>