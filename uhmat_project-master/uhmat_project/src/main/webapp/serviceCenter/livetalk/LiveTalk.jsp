<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script language='javascript'>
// F5 작동하지 않게 하기!!!
function noEvent() {
if (event.keyCode == 116) {
event.keyCode= 2;
return false;
}
else if(event.ctrlKey && (event.keyCode==78 || event.keyCode == 82))
{
return false;
}
}
document.onkeydown = noEvent;

</script>
</head>
<body>
	<input type="button" value="홈" onclick="location.href='index.jsp'">
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
	<form action="LiveTalkPro.sc" method="get">
		<table>	
			<tr>
				<th>닉네임 : </th><td><input type="text" name="nickname"></td>
				<th>내용 : </th><td><input type="text" placeholder="입력하세요" name="talk"></td>
				<td><input type="submit" value="입력"></td>
			</tr>	
		</table>	
	</form>
</body>
</html>