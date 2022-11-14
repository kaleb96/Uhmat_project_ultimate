<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="food/review/reviewReplyList.css" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
	
	function replyWrite(target) {
		
			$.ajax({
				type: "post",
				url: "ReviewReplyWriteAction.re",
				data: {
					idx : ${param.idx},
					content: $("#content").val(),
					nickname : "${sessionScope.sNickName}",
					pageNum : ${param.pageNum}
				},
				dataType: "text",
				success:
					function(response) {
						location.reload();
				}
				
			});
		}
</script>
</head>
<body>
<div class="mainLogo">
    		<a href="/uhmat_project/"><img src="image/uhmatMainLogo.png"></a>
    	</div>
		<div class="loginPart">
		<c:choose>
			<c:when test="${empty sessionScope.sNickName}">
				<a href="MemberLogin.me">로그인</a>&nbsp; <a href="MemberJoinForm.me">회원가입</a>
			</c:when>
			<c:otherwise>
				<a href="MemberDetailForm.me?nickName=${sessionScope.sNickName }">${sessionScope.sNickName }
					님 </a>&nbsp; <a href="MemberLogout.me">로그아웃</a>
				<c:if test="${sessionScope.sNickName eq 'admin'}"> &nbsp; <a
						href="AdminMain.me">관리자페이지</a>
				</c:if>
			</c:otherwise>
		</c:choose>
		</div>
	<hr>
<section id="cntBox">
	<section>
		<table>
			<tr>
				<td>
				<span id="id"></span>
				<textarea rows="1" cols="100" id="content"></textarea>
				<input type="button" id="btn" class="btn" onclick="replyWrite(this)" value="게시">
<!-- 				<button id="btn" onclick="replyWrite()">게시</button> -->
				</td>
			</tr>
		</table>
	</section>
	<section id="cntList">
		<table>
			
				<c:choose>
					<c:when test="${not empty replyList }">
						<c:forEach var="reply" items="${replyList}">
						<tr><td colspan="2">${reply.nickname }: &nbsp;&nbsp; ${reply.content }</td>
						</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="2">게시글이 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			
		</table>
	</section>
</section>
</body>
</html>