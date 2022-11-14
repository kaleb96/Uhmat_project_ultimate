<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
   <form action="" method="get">
	    <table border="1">
	        <tr>
	        
	        	<th>고객명</th>
	            <td><input type="text" name="name"></td>
	            <th><input type="submit" value="검색"></th>    
	        </tr>
	     </table>
    </form>
        <!-- for문으로 여러개 출력하면 됩니다.(미구현) -->
   
     
	
	<!--멤버 테이블  -->
	<table border="1">
			<tr>
            <th><input type="checkbox" id="allCheck"></th>
            <th>번호</th>
            <th>이름</th>
            <th>닉네임</th>
            <th>이메일</th>
            <th>게시물수</th>
            <th>상세보기</th>
            <th>탈퇴</th>
            
            
        </tr>
			<!-- 게시물 목록 출력(단, 게시물이 하나라도 존재할 경우에만 출력) -> JSTL과 EL 활용-->
			<!-- JSTL의 c:choose 태그를 사용하여 게시물 존재 여부 판별 -->
			<!--  조건 : boardList 객체가 비어있지 않고 pageInfo 객체의 listCount가 0보다 클 경우 -->
			<tr>
            <td><input type="checkbox" id="check1" name="check" value="1"></td>
            <td>1</td>
            <td>홍길동</td>
            <td>hh</td>
            <td>hong@naver.com</td>
            <td>10개</td>
            <td>보기</td>
            <td><input type="button" value="회원삭제" onclick="location.href='회원삭제페이지'"></td>
        </tr>
<%-- 	 		<c:choose> --%>
<%-- 	 			<c:when test="${not empty list and pageInfo.listCount gt 0 }"> --%>
<!-- 					c:foreach 태그를 사용하여 boardList 객체의 BoardDTO 객체를 꺼내서 출력 				 -->
<%-- 					<c:forEach var="FAQ" items="${list}">  --%>
<!-- 						<tr> -->
<%-- 							<td>${FAQ.category }</td> --%>
<%-- 							<td>${FAQ.idx }</td> --%>
<!-- 							<td id="subject"> -->
<%-- 								<a href="FAQDetail.sc?idx=${FAQ.idx}&pageNum=${pageInfo.pageNum}"> --%>
<%-- 									${FAQ.subject } --%>
<!-- 								</a> -->
<!-- 							</td> -->
<%-- 							<td>${FAQ.nickname }</td> --%>
<%-- 							<td>${FAQ.date }</td> --%>
<%-- 							<td>${FAQ.readcount }</td> --%>
<!-- 						</tr> -->
<%-- 						</c:forEach> --%>
<%-- 	 			</c:when> --%>
<%-- 	 			<c:otherwise> --%>
<!-- 					<tr><td colspan="5"> 게시물이 존재하지 않습니다</td></tr> 			 -->
<%-- 	 			</c:otherwise> --%>
<%-- 	 		</c:choose> --%>
			
		</table>
	<!-- 리스트 페이징 처리 -->
	
	<c:choose>
				<c:when test="${pageInfo.pageNum > 1}">
					<input class="before_next" type="button" value="이전" onclick="location.href='FAQList.sc?pageNum=${pageInfo.pageNum - 1}&ment=${param.ment }'">
				</c:when>
				<c:otherwise>
					<input class="before_next" type="button" value="이전" disabled="disabled">
				</c:otherwise>
			</c:choose>
				
			<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
					<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" >
						<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
						<c:choose>
							<c:when test="${pageInfo.pageNum eq i}">
								${i}
							</c:when>
							<c:otherwise>
								<a href="FAQList.sc?pageNum=${i}&ment=${param.ment }">${i} &nbsp;</a>
			
							</c:otherwise>
						</c:choose>
					</c:forEach>
				
			<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
			<c:choose>
				<c:when test="${pageInfo.pageNum lt pageInfo.maxPage}">
					<input class="before_next" type="button" value="다음" onclick="location.href='FAQList.sc?pageNum=${pageInfo.pageNum + 1}&ment=${param.ment }'">
				</c:when>
				<c:otherwise>
					<input class="before_next" type="button" value="다음" disabled="disabled">
				</c:otherwise>
			</c:choose>
		</section>
	
	
	
	<!-- 회원정보 나중에 ajax - append로 처리해야함 -->
	<div>
	<table border="1">
	<tr>
		<td>닉네임</td><td><input type="text" name="nickname"></td>
	</tr>
	<tr>
		<td>이름</td><td><input type="text" name="name"></td>
	</tr>
	<tr>
		<td>이메일</td><td  colspan="3"><input type="text" name="email1"></td>
	</tr>
	<tr>
		<td>이메일 수신</td><td  colspan="3"><input type="radio" name="emailaccept">예 <input type="radio" name="emaildeny">아니오</td>
	</tr>
	<tr>
		<td rowspan="3">주소</td><td><input type="text" placeholder="우편번호" name="postcode"><input type="button" value="우편번호검색" onclick="우편번호 검색 링크"></td>
	</tr>
	<tr>
		<td  colspan="3"><input type="text" placeholder="상세주소1" name="address1"></td>
	</tr>
	<tr>
		<td  colspan="3"><input type="text" placeholder="상세주소2" name="address2"></td>
	</tr>
	
	</table>
	</div>
	
	<input class="before_next" type="button" value="회원삭제" onclick="location.href='회원삭제페이지'">


</body>
</html>  