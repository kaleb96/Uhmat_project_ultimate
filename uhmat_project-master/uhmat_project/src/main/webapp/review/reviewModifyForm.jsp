<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReviewModifyForm</title>
<style>
@import url("//maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css");

/* Basic styles */

.row {
  text-align: left;
}

.rate {
  display: inline-block;
  margin: 0;
  padding: 0;
  border: none;
}

.starRow {
  display: none;
}

label {
  float: right;
  font-size: 0;
  color: #d9d9d9;
}

label:before {
  content: "\f005";
  font-family: FontAwesome;
  font-size: 40px;
}

label:hover,
label:hover ~ label {
  color: #fcd000;
  transition: 0.2s;
}

.starRow:checked ~ label {
  color: #fcd000;
}
/* #ccac00 */
input:checked ~ label:hover,
input:checked ~ label:hover ~ label {
  color: #fcd000;
  transition: 0.2s;
}


/* Half-star*/

.star-half {
  position: relative;
}

.star-half:before {
  position: absolute;
  content: "\f089";
  padding-right: 0;
}
</style>
</head>
<body>
	<h1>ReviewModifyForm</h1>
	<nav><input type="button" value="이전으로" onclick="history.back()"></nav>
	<section>
		<form  action="ReviewModifyProAction.re" method="post" enctype="multipart/form-data">
			<input type="hidden" name="idx" value="${dto.idx }">

			<input type="hidden" name="path" value="${originPath }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table border="1">

				<tr>
					<td>작성자&nbsp;<input type="text" name="nickname" value="${dto.nickname }" readonly="readonly"></td>
					<!--  회원가입 로그인이 연동되면 nickname을 세션값으로받습니다. -->
				</tr>
				<tr>

					<td><input type="text" name="subject" value="${dto.subject }" required="required"></td>

				</tr>
				<tr>
					<td><input type="text" name="Tag" value="${dto.tag_name }"></td> 
					<!-- select box 를 통한 추가 조사 
						DB에 추가해야함
					-->
				</tr>
				<tr>

					<td><input type="text" name="res_name" value="${dto.res_name }" required="required">&nbsp;<button>찾기</button></td>

					
					<!-- 지도 api와 연동 -->
				</tr>
				<tr>
					<td><div class="row">
						  <fieldset class="rate">
						    <input id="rate2-star5" class="starRow" type="radio" name="rating" value="5" />
						    <label for="rate2-star5" title="Awesome">5</label>
						
						    <input id="rate2-star5-half" class="starRow" type="radio" name="rating" value="4.5" />
						    <label class="star-half" for="rate2-star5-half" title="Excellent">4.5</label>
						
						    <input id="rate2-star4" class="starRow" type="radio" name="rating" value="4" />
						    <label for="rate2-star4" title="Very good">4</label>
						
						    <input id="rate2-star3-half" class="starRow" type="radio"name="rating" value="3.5" />
						    <label class="star-half" for="rate2-star3-half" title="Good">3.5</label>
						
						    <input id="rate2-star3" class="starRow" type="radio" name="rating" value="3" />
						    <label for="rate2-star3" title="Satisfactory">3</label>
						
						    <input id="rate2-star2-half" class="starRow" type="radio" name="rating" value="2.5" />
						    <label class="star-half" for="rate2-star2-half" title="Unsatisfactory">2.5</label>
						
						    <input id="rate2-star2" class="starRow" type="radio" name="rating" value="2" />
						    <label for="rate2-star2" title="Bad">2</label>
						
						    <input id="rate2-star1-half" class="starRow" type="radio" name="rating" value="1.5" />
						    <label class="star-half" for="rate2-star1-half" title="Very bad">1.5</label>
						
						    <input id="rate2-star1" class="starRow" type="radio" name="rating" value="1" />
						    <label for="rate2-star1" title="Awful">1</label>
						
						    <input id="rate2-star0-half" class="starRow" type="radio" name="rating" value="0.5" />
						    <label class="star-half" for="rate2-star0-half" title="Horrific">0.5</label>
						  </fieldset>
						</div>
					</td>
				</tr>
				<tr>
					<td><textarea rows="20" cols="100" name="content"required="required">${dto.content}</textarea></td>

				</tr>
				<tr>
				<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td width="200" height="300"><img width="90%" src="upload/${dto.photo }" alt="파일"><br>이전 이미지</td>
				</tr>
				<tr>
					<td><input type="file" name="photo" required="required" /></td>
				</tr>
			</table>
		<section>
			<input type="submit" value="수정해버렷">
		</section>
		</form>
	</section>
</body>
</html>