<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/5382a0d7e0.js" crossorigin="anonymous"></script>
<link href="${pageContext.request.contextPath}/css/companyDetail.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- 헤더 들어가는곳 -->
		<jsp:include page="/inc/header.jsp" flush="false" />
	<!-- 헤더 들어가는곳 -->
	
	<!-- 본문 시작 -->
	<div id="companyDetailContainer">
	<div id="companyContainer">
		<h1>	
			<div id="companyLogo">
				<img src="image/incLogo.jpg">
			</div>
		</h1>
	</div>
	
	<section class="companyInformation">
		<table id="companyMenu">
			<tr>
				<th>회사명</th>
				<td>주식회사 여가그가</td>
			</tr>
			<tr>
				<th>영어표기</th>
				<td>Yeogageuga Inc.</td>
			</tr>
			<tr>
				<th>소재지</th>
				<td>부산광역시 부산진구 동천로 109 삼한골든게이트빌딩 7층(접수)</td>
			</tr>
			<tr>
				<th>자본금</th>
				<td>10,000,000</td>
			</tr>
			<tr>
				<th>총직원수</th>
				<td>7人</td>
			</tr>
			<tr>
				<th>임원</th>
					<td>대표 안승우</td>
				</th>
			</tr>
			<tr>
				<th rowspan="12">직원
					<td>지니어스 박영재</td>
					<tr>
						<td>끈기와 집념의 사나이 임주영</td>
					</tr>	
					<tr>
						<td>총대장 박기윤</td>
					</tr>
					<tr>
						<td>수석 디자이너 우수빈</td>
					</tr>
					<tr>
						<td>총리 최민석</td>
					</tr>	
					<tr>
						<td>찌끄래기 목민수</td>
					</tr>
				</th>
			</tr>
		</table>	
	</section>
	</div>
	<!-- 본문 끝 -->
	
	<!-- 푸터 들어가는곳 -->
		<jsp:include page="/inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 -->		
</body>
</html>