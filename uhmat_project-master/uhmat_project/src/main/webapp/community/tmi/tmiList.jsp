<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
<link href="css/tmi/tmiList.css" rel="stylesheet"/>
</head>
<body>

	<!-- 헤더 들어가는 곳 -->
	<jsp:include page="../../inc/header.jsp" />
	<!-- 헤더 들어가는 곳 -->

	<!-- 배너, 메뉴바 -->
	<div align="center" class="tmiBanner">
		<img alt="TMI 배너" src="image/tmi/tmi_banner.png">
	</div>

	<div align="center" id="menuBar">
		<button onclick="location.href='MateList.co'">mate</button>
		<button onclick="location.href='TmiList.co'">tmi</button>
		<button onclick="location.href='RecipeList.co'">recipe</button>
		<br>
	</div>
	<!-- 배너, 메뉴바 끝 -->


	<!-- 게시판리스트 -->
	<table class="tmiList">
			<tr id="font_front">
				<td scope="col">제목</td>
				<td scope="col">작성자</td>
				<td scope="col">조회수</td>
				<td scope="col">등록일</td>
			</tr>

			<c:choose>
				<c:when
					test="${not empty tmiBoardList and tmiPageInfo.listCount gt 0 }">
					<c:forEach var="tmi" items="${tmiBoardList }">
						<tr class="tmiListTable">
							<td class="link" width="350" height="50"
							onclick="location.href='TmiDetail.co?idx=${tmi.idx }&pageNum=${tmiPageInfo.pageNum}'">
							${tmi.subject }
							</td>
							<td>${tmi.nickname }</td>
							<td>${tmi.readcount }</td>
							<td>${tmi.date }</td>
						</tr>

					</c:forEach>
				</c:when>

				<c:when test="${not empty tmiPageInfo.listCount }">
					<c:forEach var="tmiBoardList" items="${tmiBoardList }">
						<tr>
							<td>${tmiBoardList.idx }</td>
							<td class="link" width="350" height="50" onclick="location.href='TmiDetail.co?idx=${tmiBoardList.idx }&pageNum=${tmiPageInfo.pageNum}&keyword=${param.keyword }'">
							${tmiBoardList.subject }</td>
							<td>${tmiBoardList.nickname }</td>
							<td>${tmiBoardList.date }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">게시물이 존재하지 않습니다</td>
					</tr>
				</c:otherwise>
			</c:choose>
	<!-- 버튼 창 -->
		<tr>
			<td id="plus"><i class='fas fa-plus-circle' style='position:static; font-size:48px; color: #6C757D;' onclick="location.href='TmiWriteForm.co'"/></i></td>
		</tr>
	</table>

	
	<!-- 검색 창 -->
	<div align="center">
		<form>
			<input class="search" type="text" placeholder="Search" name="keyword" value="${param.keyword }"> 
			<input class="search" id="searchIcon" type="submit" value="검색">	
		</form>
	</div>

	&nbsp;

	<!-- 
	현재 페이지 번호(pageNum)가 1보다 클 경우에만 [이전] 링크 동작
	=> 클릭 시 TmiList.jsp 로 이동하면서 
	   현재 페이지 번호(pageNum) - 1 값을 page 파라미터로 전달
	-->
	<div align="center">
		<c:choose>
			<c:when test="${tmiPageInfo.pageNum > 1 }">
				<input type="button" value="이전" class="before_next"
					onclick="location.href='TmiList.co?pageNum=${tmiPageInfo.pageNum - 1}&keyword=${param.keyword }'">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전" class="before_next"
					disabled="disabled">
			</c:otherwise>
		</c:choose>


		<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->

		<c:forEach var="i" begin="${tmiPageInfo.startPage }"
			end="${tmiPageInfo.endPage }">
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${tmiPageInfo.pageNum eq i}">
   					${i }
   				</c:when>
				<c:otherwise>
					<a href="TmiList.co?pageNum=${i}&keyword=${param.keyword }">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>


		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->

		<c:choose>
			<c:when test="${tmiPageInfo.pageNum lt tmiPageInfo.maxPage}">
				<input type="button" value="다음" class="before_next"
					onclick="location.href='TmiList.co?pageNum=${tmiPageInfo.pageNum + 1}&keyword=${param.keyword }'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음" class="before_next"
					disabled="disabled">
			</c:otherwise>
		</c:choose>
	</div>
	
	&nbsp;

	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp" />
	<!-- 푸터 들어가는 곳 -->

</body>
</html>