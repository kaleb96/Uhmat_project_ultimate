<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="food/review/reviewWriteForm.css" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">

	function checkHash(target) {
		
		var hash = fr.tag.value;
		hash = hash.replaceAll("#", " ");
		hash = hash.split(" ");
// 		alert(hash.length);
		for(var i = 1; i < hash.length; i++) {
			var tmp = hash[i];
			for(var j = i+ 1; j <= hash.length ; j++) {
				if(tmp == hash[j]) {
				alert("Tag가 중복됩니다.")
				return;
				} 
			}
		}	
	}
	//해쉬태그 검색 로직
// 	function checkHash(target) {
	
// 		$.ajax({
// 			type: "post",
// 			url: "CheckHash.re",
// 			data: {
// 				hash: $("#hash").val()
// 			},
// 			dataType: "text",
// 			success:
// 				function(response) {
// 		}
// 		});
// 	}
	
	
</script>
</head>
<body>
		<h1>새 게시물</h1>
		<nav>
			<input type="button" value="리스트로" onclick="history.back()">
		</nav>
	<section>
		<form  name="fr"action="ReviewWritePro.re" method="post" enctype="multipart/form-data">
			<table id="tr">
				<tr>
					<td><input type="text" name="nickname" placeholder="작성자(세션아이디)" required="required" ></td>
					<!--  회원가입 로그인이 연동되면 nickname을 세션값으로받습니다. -->
				</tr>
				<tr>
					<td><input type="text" name="subject" placeholder="제목을 입력하세요" required="required"></td>
				</tr>
				<tr>
					<td><input type="text" id="hash" name="tag" placeholder="#태그명 공백" onchange="checkHash(this)" required="required"></td> 
					<!-- select box 를 통한 추가 조사 
						DB에 추가해야함
					-->
				</tr>
				<tr>
					<td><input type="text" name="res_name" placeholder="음식점 위치 찾기" required="required">&nbsp;<button class="btn">찾기</button></td>
					
					<!-- 지도 api와 연동 -->
				</tr>
				<tr>
					<td>
						<div class="row">
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
				</tr>
				<tr>
					<td><textarea rows="20" cols="100" name="content" placeholder="내용을 입력하세요" required="required"></textarea></td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" class="btn" name="photo" required="required" /></td>
				</tr>
			</table>
		<section>
			<input type="submit" class="btn" value="제출해버렷">
		</section>
		</form>
	</section>
</body>
</html>