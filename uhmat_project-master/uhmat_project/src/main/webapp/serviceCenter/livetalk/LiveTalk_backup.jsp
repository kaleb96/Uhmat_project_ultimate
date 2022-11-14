<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../../js/jquery-3.6.0.js"></script>
<script type="text/javascript">
// $(function(){
// 	$("#liveTalkButton").on("click", function(){
// 		$.ajax({
// 			type: "get",
// 			url: "LiveTalkPro.sc",
// 			data: $("form").serialize(),
// 			dataType: "text",
// 			success: function(response) {
// 				$("#liveTalk").html(response);
// 			}
// 		});
// 	})
// });

</script>
</head>
<body>
		<table border="1">
			<tr>
				<td colspan="5">
					<textarea id="liveTalk" cols="50" rows="20">
						<c:forEach var="list" items="${list }">
							${list.nickname} : ${list.talk}
						</c:forEach>
					</textarea>
				</td>
			</tr>
		</table>
	<form action="LiveTalkPro.sc" method="post">
		<table>	
			<tr>
				<th>닉네임 : </th><td><input type="text" name="nickname"></td>
				<th>내용 : </th><td><input type="text" placeholder="입력하세요" name="talk"></td>
				<td><input type="button" value="입력"></td>
			</tr>	
		</table>	
	</form>
</body>
</html>