<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
<link href="css/faq/faqDetail.css" rel="stylesheet">
<style type="text/css">

</style>
<title>TMI 글 상세내용</title>
</head>
<body>


	<div class="view">
		<table width="800">
			<tr>
				<td class="subject">${faq.subject }</td> 
			</tr>
			<tr>
				<td class="nickname_time_readcount"><i class='fas fa-user-alt'></i> ${faq.nickname } | <i class="fa fa-clock-o"></i> ${faq.date } | <i class='far fa-eye'></i> ${faq.readcount }
				</td> 
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
				<!-- 
				파일명은 원본 파일명을 표시하고, 다운로드 파일 대상은 실제 업로드 파일명,
				실제 다운로드 되는 파일명은 원본 파일명으로 변경하여 다운로드
				-->
					<a href="upload/${faq.real_File }" download="${faq.original_File }">
					${faq.real_File }
					</a>
				</td>
			</tr>
			<tr>
				<td>${faq.content }</td>
			</tr>
	
		</table>
	&nbsp;
	
	<div class="btn">
		<c:if test="${sessionScope.sNickName == 'admin' }">	
			<input type="button" value="삭제" onclick="location.href='AllBoardDelete.ad?idx=${faq.idx}&pageNum=${param.pageNum}&title=${param.title}'">
		</c:if>
			<input type="button" value="목록" onclick="location.href='AllBoardList.ad?pageNum=${param.pageNum}&keyword=${param.keyword }'">
	</div>
	
	&nbsp;
	
	<!-- 댓글 작성 -->
	<c:if test="${sessionScope.sNickName eq 'admin'}"> 
	<section class="section">
		<!-- insertForm 섹션(댓글 작성 영역)은 세션 아이디가 존재할 경우에만 출력 -->
		<section>
			<form action="FAQDetailReply.sc" style="position: relative; left:20%; top:50%;">
				<!-- 댓글 전송 시 현재 게시물 글번호(idx)도 함께 전송 -->
				<input type="hidden" name="idx" value="${param.idx }">
				<!-- 댓글 전송 시 현재 게시물 닉네임(nickname) 함께 전송 -->
				<input type="hidden" name="nickname" value="${faq.nickname }">
				<!-- 페이지번호도 함께 전송 -->
				<input type="hidden" name="pageNum" value="${param.pageNum}"> 
			
				<textarea rows="3" cols="50" name="answer"></textarea>
				<br>
				<input type="submit" class="section" value="등록" style="position: relative; left:20%;">
				
				<section id="replyViewArea" style="position: relative; left: 40%; top:50%;">
				<!-- ArrayList(replyList) 객체 크기만큼 for문 반복 -->
				<br>
			     <table>
			     	<tr>
				     	<td>답변 : </td><td> ${reply.answer }</td>
				     	<td><input type="button" value="삭제" onclick="location.href='AllBoardDelete.ad?idx=${param.idx}&pageNum=${param.pageNum}&title=${param.title}'"></td>
			     	</tr>
			     </table>
				<br>
			</section>
			</form>
		</section>
	</section>
	</c:if>
	
	
		
	</div>

</body>
	
</body>
</html>