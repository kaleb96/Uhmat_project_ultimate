<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
<link href="css/adminManageMent.css" rel="stylesheet">
<style type="text/css">


</style>
</head>
<body>
   <form action="AdminManageMemberList.ad" method="get" class="search">
	    <table  class="memberList">
	        <tr id="font_front">
	        	<th>고객명</th>
	            <td><input type="text" name="keyword" value="${param.keyword }" class="search"></td>
	            <th><input type="submit" value="검색" class="before_next"></th>    
	        </tr>
	     </table>
    </form>
	<!--멤버 테이블  -->
	<table   class="memberList">
			<tr id="font_front">
<!--             <th><input type="checkbox" id="allCheck"></th> -->
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
			<c:forEach var="member" items="${list}" varStatus="status" >
				<tr id="font_front">
		            <td>${status.count }</td>
		            <td>${member.name}</td>
		            <td>${member.nickname}</td>
		            <td>${member.email }</td>
					<td>${member.boardCount}</td>
		            <td><a href="AdminManageMemberDetail.ad?nickname=${member.nickname }&keyword=${param.keyword}" class="link">보기</a></td>
		            <td><input class="before_next" type="button" value="회원삭제" onclick="location.href='AdminDeleteMember.ad?email=${member.email }'"></td>
		        </tr>
			</c:forEach>
		</table>
	<!-- 리스트 페이징 처리 -->
	<div align="center"> 
	<c:choose >
				<c:when test="${pageInfo.pageNum > 1}">
					<input class="before_next" type="button" value="이전" onclick="location.href='AdminManageMemberList.ad?pageNum=${pageInfo.pageNum - 1}&keyword=${param.keyword }'" >
				</c:when>
				<c:otherwise>
					<input class="before_next" type="button" value="이전" disabled="disabled" >
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
								<a href="AdminManageMemberList.ad?pageNum=${i}&keyword=${param.keyword }">${i} &nbsp;</a>
			
							</c:otherwise>
						</c:choose>
					</c:forEach>
				
			<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
			<c:choose>
				<c:when test="${pageInfo.pageNum lt pageInfo.maxPage}">
					<input class="before_next" type="button" value="다음" onclick="location.href='AdminManageMemberList.ad?pageNum=${pageInfo.pageNum + 1}&keyword=${param.keyword }'" class="before_next">
				</c:when>
				<c:otherwise>
					<input class="before_next" type="button" value="다음" disabled="disabled" class="before_next">
				</c:otherwise>
			</c:choose>
	</div>
	
	
	<!-- 회원정보 나중에 ajax - append로 처리해야함 -->
	<div align="center">
	<table  class="memberList search" >
	<tr>
		<td >닉네임</td><td><input type="text" name="nickname" value="${memberDetail.nickname }" readonly="readonly"></td>
	</tr>
	<tr>
		<td>이름</td><td><input type="text" name="name" value="${memberDetail.name }" readonly="readonly"></td>
	</tr>
	<tr>
		<td>이메일</td><td  colspan="3"><input type="text" name="email1" value="${memberDetail.email }" readonly="readonly"></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>이메일 수신</td><td  colspan="3"><input type="radio" name="emailaccept">예 <input type="radio" name="emaildeny">아니오</td> -->
<!-- 	</tr> -->
	<tr>
		<td rowspan="3">주소</td><td><input type="text" placeholder="우편번호" name="postcode" value="${memberDetail.postCode }" readonly="readonly"></td>
	</tr>
	<tr>
		<td  colspan="3"><input type="text" placeholder="상세주소1" name="address1" value="${memberDetail.address1 }" readonly="readonly"></td>
	</tr>
	<tr>
		<td  colspan="3"><input type="text" placeholder="상세주소2" name="address2" value="${memberDetail.address2 }" readonly="readonly"></td>
	</tr>
	
	</table>
	
	<input class="before_next" type="button" value="회원삭제" onclick="location.href='AdminDeleteMember.ad?email=${memberDetail.email }'" class="before_next memberList" style="width:300px;">
	</div>


</body>
</html>  