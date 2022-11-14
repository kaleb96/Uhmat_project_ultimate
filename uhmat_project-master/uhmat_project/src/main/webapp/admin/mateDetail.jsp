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
<link href="css/mate/mateView.css" rel="stylesheet">

</head>
<body>
	
	
	<div class="view">
		<table width="800">
			<tr>
				<td class="subject">${mate.subject } </td> 
			</tr>
			<tr>
				<td class="nickname_time_readcount"><i class='fas fa-user-alt'></i> ${mate.nickname } | <i class="fa fa-clock-o"></i> ${mate.date } | <i class='far fa-eye'></i> ${mate.readcount }</td> 
			</tr>
			<tr>
				<td>${mate.content }</td>
			</tr>
	
		</table>
	
	&nbsp;
	
	<div class="btn">
			<input type="button" value="댓글" onclick="location.href='MateReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'">
		<c:if test="${sessionScope.sNickName == 'admin' }">
			<input type="button" value="삭제" onclick="location.href='AllBoardDelete.ad?idx=${param.idx}&pageNum=${param.pageNum}&title=${param.title}'">
		</c:if>	
			<input type="button" value="목록" onclick="location.href='AllBoardList.ad?pageNum=${param.pageNum}'">
	
	</div>
	
	&nbsp;
	 
		<table>
		<c:forEach items="${mateReplyList }" var="mateReplyList">
			<tr>
				<td>
					<c:forEach begin="1" end="${mateReplyList.re_lev }">
						&nbsp;&nbsp;<i class="material-icons" style="font-size:20px;color:#FF1818">subdirectory_arrow_right</i>
					</c:forEach>
					${mateReplyList.nickname } | ${mateReplyList.date }
				</td>
				
			</tr>
			<tr>
				<td width="500" class="reply"> ${mateReplyList.content } </td>
				<td class="btn"><input type="button" value="대댓글" onclick="location.href='MateRereplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx} '"></td>
				<c:if test="${sessionScope.sNickName == 'admin' }">
					<td class="btn"><input type="button" value="댓글삭제" onclick="location.href='MateReplyDeleteForm.co?idx=${mate.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx}&nickname=${mateReplyList.nickname}'"> </td>
				</c:if>
			</tr>
		</c:forEach>
		</table>
	</div>
	
</body>
</html>