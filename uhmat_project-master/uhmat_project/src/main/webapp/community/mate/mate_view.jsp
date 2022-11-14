<%@page import="vo.MateReplyDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
<link href="css/mate/mateView.css" rel="stylesheet">

</head>
<body>
	<!-- 		헤더 들어가는 곳 -->
		<jsp:include page="../../inc/header.jsp"/>
	<!-- 		헤더 들어가는 곳 -->
	
	<div class="view">
		<table>
			<tr>
<!-- 				아이콘 넣는 곳 -->
				<td rowspan="2"><img alt="아이콘" src="image/character/${mate.icon }" width="70px"></td>
				<td>${mate.nickname }</td>
			</tr>
			<tr>
				<td class="time_readcount"> <i class="fa fa-clock-o"></i> ${mate.date} | <i class='far fa-eye'></i> ${mate.readcount }</td> 
			</tr>
		</table>
			<div id="subject">${mate.subject }</div>
			<div id="content">${mate.content }</div>
	
	
	&nbsp;
	
	<div class="btn">
			<input type="button" value="댓글" onclick="location.href='MateReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'">
		<c:if test="${sessionScope.sNickName == mate.nickname }">
			<input type="button" value="수정" onclick="location.href='MateModifyForm.co?idx=${mate.idx}&pageNum=${param.pageNum}'">
			<input type="button" value="삭제" onclick="location.href='MateDeleteForm.co?idx=${mate.idx}&pageNum=${param.pageNum}'">
		</c:if>	
			<input type="button" value="목록" onclick="location.href='MateList.co?pageNum=${param.pageNum}'">
	
	</div>
	
	&nbsp;
	 
		<table>
		<c:forEach items="${mateReplyList }" var="mateReplyList">
			<tr>
				<td>
					<c:forEach begin="1" end="${mateReplyList.re_lev }">
						&nbsp;&nbsp;<i class="material-icons" style="font-size:20px;color:#6C757D;">subdirectory_arrow_right</i>
					</c:forEach>
					<img alt="아이콘" src="image/character/${mateReplyList.icon }" width="40px"> ${mateReplyList.nickname } | ${mateReplyList.date }
				</td>
				
			</tr>
			<tr>
				<td width="500" class="reply"> ${mateReplyList.content } </td>
				<td class="btn"><input type="button" value="답글" onclick="location.href='MateRereplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx} '"></td>
				<c:if test="${sessionScope.sNickName == mateReplyList.nickname }">
					<td class="btn"><input type="button" value="댓글수정" onclick="location.href='MateReplyModifyForm.co?idx=${mate.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx}&nickname=${mateReplyList.nickname}'"></td>
					<td class="btn"><input type="button" value="댓글삭제" onclick="location.href='MateReplyDeleteForm.co?idx=${mate.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx}&nickname=${mateReplyList.nickname}'"> </td>
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