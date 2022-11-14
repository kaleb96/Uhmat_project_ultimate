<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
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
				<td class="subject">${notice.subject }</td> 
			</tr>
			<tr>
				<td class="nickname_time_readcount"><i class='fas fa-user-alt'></i> ${notice.nickname } | <i class="fa fa-clock-o"></i> ${notice.date } |
				</td> 
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
				<!-- 
				파일명은 원본 파일명을 표시하고, 다운로드 파일 대상은 실제 업로드 파일명,
				실제 다운로드 되는 파일명은 원본 파일명으로 변경하여 다운로드
				-->
					<a href="upload/${notice.real_File }" download="${notice.original_File }">
					${notice.real_File }
					</a>
				</td>
			</tr>
			<tr>
				<td>${notice.content }</td>
			</tr>
	
		</table>
	
	<div class="btn">
		<c:if test="${sessionScope.sNickName == 'admin' }">	
			<input type="button" value="삭제" onclick="location.href='AllBoardDelete.ad?idx=${param.idx}&pageNum=${param.pageNum}&title=${param.title}'">
		</c:if>
			<input type="button" value="목록" onclick="location.href='AllBoardList.ad?pageNum=${param.pageNum}'">
	</div>
		
	</div>
	

	

	
</body>
	
</body>
</html>