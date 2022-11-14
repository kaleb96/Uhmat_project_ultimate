<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
<link href="css/tmi/tmiDetail.css" rel="stylesheet">
<style type="text/css">

</style>
<title>TMI 글 상세내용</title>
</head>
<body>
	<!-- 헤더 들어가는 곳 -->
	<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->

	&nbsp;
	<div class="view">
		<table>
			<tr>
<!-- 				아이콘 넣는 곳 -->
				<td rowspan="2"><img alt="아이콘" src="image/character/${tmiBoard.icon }" width="70px"></td>
				<td>${tmiBoard.nickname }</td>
			</tr>
			<tr>
				<td class="time_readcount"> <i class="fa fa-clock-o"></i> ${tmiBoard.date } | <i class='far fa-eye'></i> ${tmiBoard.readcount }
				</td> 
			</tr>
		</table>
		<div id="subject">${tmiBoard.subject }</div>
		<div id="content">${tmiBoard.content }</div>
	&nbsp;
	
	<div class="btn">
		<input type="button" value="댓글" onclick="location.href='TmiReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'">
		<c:if test="${sessionScope.sNickName == tmiBoard.nickname }">	
			<input type="button" value="수정" onclick="location.href='TmiModifyForm.co?idx=${tmiBoard.idx}&pageNum=${param.pageNum}'">
			<input type="button" value="삭제" onclick="location.href='TmiDeleteForm.co?idx=${tmiBoard.idx}&pageNum=${param.pageNum}'">
		</c:if>
			<input type="button" value="목록" onclick="location.href='TmiList.co?pageNum=${param.pageNum}'">
	</div>
	
	&nbsp;
	
	<table class="replyTable">
		<c:forEach items="${tmiReplyList }" var="tmiReply">
			<tr>
				<td>
					<c:forEach begin="1" end="${tmiReply.re_lev }">
						<i class="material-icons" style="font-size:20px;color:#6C757D;">subdirectory_arrow_right</i>
					</c:forEach>
					<img alt="아이콘" src="image/character/${tmiReply.icon }" width="40px"> ${tmiReply.nickname } | ${tmiReply.date }
				</td>
				
			</tr>
			<tr>
			<td width="500" class="reply"> ${tmiReply.content } </td>
				<td class="btn"><input type="button" value="답글" onclick="location.href='TmiRereplyWriteForm.co?idx=${tmiReply.idx }&board_idx=${tmiReply.board_idx }&pageNum=${param.pageNum}'">
				</td>
				<c:if test="${sessionScope.sNickName == tmiReply.nickname }">
				<td class="btn"><input type="button" value="댓글수정" onclick="location.href='TmiReplyModifyForm.co?idx=${tmiReply.idx }&pageNum=${param.pageNum }'">
				</td>
				<td class="btn"><input type="button" value="댓글삭제" onclick="location.href='TmiReplyDeleteForm.co?idx=${tmiReply.idx }&board_idx=${tmiReply.board_idx }&nickname=${tmiReply.nickname }&pageNum=${param.pageNum }'">
				</td>
				</c:if>
			</tr>
		</c:forEach>
		</table>
		<br>
	</div>
	

	
	
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 푸터 들어가는 곳 -->
	
</body>
	
</body>
</html>