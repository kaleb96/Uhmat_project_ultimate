<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 게시판</title>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
<link href="css/faq/faqlist.css" rel="stylesheet">


</head>
<body>
	
		<jsp:include page="../../inc/header.jsp"></jsp:include>
		
		<h2>FAQ</h2>

		<div id="menuBar" align="center">
				<input type="button"  class="topButton" value="전체" name="" onclick="location.href='FAQlistCategory.sc?name='+name">
				<input type="button"  class="topButton" value="오류신고" name="오류신고" onclick="location.href='FAQlistCategory.sc?name='+name">
				<input type="button"  class="topButton" value="음식점등록" name="음식점등록" onclick="location.href='FAQlistCategory.sc?name='+name">
				<input type="button"  class="topButton" value="지도 오류" name="지도 오류" onclick="location.href='FAQlistCategory.sc?name='+name">
		</div>
		
		<table class="faqList">
			<tr>
				<td>카테고리</td>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				<td>조회수</td>
				<td>첨부파일</td>
			</tr>
			
			<!-- 게시물 목록 출력(단, 게시물이 하나라도 존재할 경우에만 출력) -> JSTL과 EL 활용-->
			<!-- JSTL의 c:choose 태그를 사용하여 게시물 존재 여부 판별 -->
			<!--  조건 : boardList 객체가 비어있지 않고 pageInfo 객체의 listCount가 0보다 클 경우 -->
	 		<c:choose>
	 			<c:when test="${not empty list  }">
					<!-- c:foreach 태그를 사용하여 boardList 객체의 BoardDTO 객체를 꺼내서 출력 --> 				
					<c:forEach var="FAQ" items="${list}"> 
						<tr class="faqListTable">
							<td>${FAQ.category }</td>
							<td>${FAQ.idx }</td>
								<c:choose>
									<c:when test="${not empty sessionScope.sNickName }">
									<td class="link" width="350" height="50" onclick="location.href='FAQDetail.sc?idx=${FAQ.idx}&pageNum=${pageInfo.pageNum}&keyword=${param.keyword}'">
										${FAQ.subject }
									</td>
									</c:when>
									<c:otherwise>
										<td>${FAQ.subject }</td>
									</c:otherwise>
								</c:choose>
							<td>${FAQ.nickname }</td>
							<td>${FAQ.date }</td>
							<td>${FAQ.readcount }</td>
	 						<td>
	 						
		 						<c:if test="${not empty FAQ.real_File }">
		 							<i class='fas fa-link' style='font-size:20px; color:blue'></i>
		 						 </c:if>
	 						 </td>

								  
						</tr>
						</c:forEach>
	 			</c:when>
	 			<c:otherwise>
					<tr>
					<td colspan="7"> 게시물이 존재하지 않습니다</td>
					</tr> 			
	 			</c:otherwise>
	 		</c:choose>
			
		</table>
		
		<br>
		
		<!-- 검색하기 기능 -->
		<div align="center">
			<form action="FAQList.sc" class="search" method="get" id="keyword">
				<input type="text" placeholder="search" name="keyword" value=${param.keyword }>
				<input type="submit" class="searchBtn" value="검색" >
			
		
			<!-- 버튼 창 -->
			<c:if test="${not empty sessionScope.sNickName }"> 
			<div style="position: static; left: 800px;">
			<br>
			<button type="button" class="faqButton"
				onclick="location.href='FAQWriteForm.sc'">글쓰기</button>
			<button type="button" class="faqButton" onclick="location.href='./'">메인</button>
			</div>
			</c:if>
			</form>
		</div>
		
		<br>
		
		<!-- ---------------------------------------------- -->
		
		
		<div align="center">
			<c:choose>
				<c:when test="${pageInfo.pageNum > 1}">
					<input type="button" value="이전" class="before_next" onclick="location.href='FAQList.sc?pageNum=${pageInfo.pageNum - 1}&keyword=${param.keyword }'">
				</c:when>
				<c:otherwise>
					<input type="button" value="이전" class="before_next" disabled="disabled" >
				</c:otherwise>
			</c:choose>
				<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" >
					<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
					<c:choose>
						<c:when test="${pageInfo.pageNum eq i}">
							${i}
						</c:when>
						<c:otherwise>
							<a href="FAQList.sc?pageNum=${i}&keyword=${param.keyword }">${i} &nbsp;</a>
		
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
			<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
			<c:choose>
				<c:when test="${pageInfo.pageNum lt pageInfo.maxPage}">
					<input type="button" value="다음" class="before_next" onclick="location.href='FAQList.sc?pageNum=${pageInfo.pageNum + 1}&keyword=${param.keyword }'" id="bt">
				</c:when>
				<c:otherwise>
					<input type="button" value="다음" class="before_next" disabled="disabled">
				</c:otherwise>
			</c:choose>
		</div>
		

		
		<!-- Footer 부분 -->
			<jsp:include page="../../inc/footer.jsp"></jsp:include>
			
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>

	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>

	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>













