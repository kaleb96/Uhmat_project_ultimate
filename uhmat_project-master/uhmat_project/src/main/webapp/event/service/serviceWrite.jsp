<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 -->
	
	<!-- 헤더 끝 -->
	
	<!-- 상단 디자인 -->
	
	<!-- 상단 디자인 끝 -->
	
	<!-- 이벤트 참여하기 -->
	<section id="writeForm">
	<h1>이벤트 참여 하기 :)</h1>
	<!-- 
		form 데이터 중 파일 정보가 포함될 경우
		<form> 태그 속성에 enctype="multipart/form-data" 명시 필수!
		(생략 시 enctype="application/x-www-form-urlencoded" 속성이 기본값으로 설정됨)
		-->
	<form action="ServiceWritePro.ev" name="serviceForm" method="post" enctype="multipart/form-data">
		<div>
		<!--게시판-->
			<div>
				<table>
				<tr>
					<th class="top">닉네임</th>
					<td class="top" colspan="3"><input type="text" name="nickname" required="required" placeholder="닉네임"/></td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3"><input type="text" name="subject" required="required" placeholder="제목"/></td>
				</tr>
				<tr>
					<th>이벤트 내용 설명</th>
					<td colspan="3">
						<textarea name="content" cols="40" rows="15" required="required" placeholder="내용을 입력하세요"></textarea>
					</td>
				</tr>
				<tr>
					<th>파일첨부</th>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td colspan="3">
					<input type="file" name="file1" multiple="multiple" required="required" /><br>
					
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<input type="file" name="file2" multiple="multiple"><br>
					
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<input type="file" name="file3" multiple="multiple" ><br>
					
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<input type="file" name="file4" multiple="multiple"><br>
					
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<input type="file" name="file5" multiple="multiple">
					</td>
					
				</tr>
				</table>
				
				<table>	
				<tr>
					<br>
					<td style="text-align:left;border:1px solid #e0e0e0;background-color:f9f9f9;padding:5px">
						<div style="padding-top:7px;padding-bottom:5px;font-weight:bold;padding-left:7px;font-family: Gulim,Tahoma,verdana;">※ 개인정보 수집 및 이용에 관한 안내</div>
						<div style="padding-left:10px;">
							<div style="width:97%;height:95px;font-size:11px;letter-spacing: -0.1em;border:1px solid #c5c5c5;background-color:#fff;padding-left:14px;padding-top:7px;"> 
								 1. 수집 개인정보 항목 : 회사명, 담당자명, 메일 주소, 전화번호, 홈페이지 주소, 팩스번호, 주소 <br />
								 2. 개인정보의 수집 및 이용목적 : 제휴신청에 따른 본인확인 및 원활한 의사소통 경로 확보 <br />
								 3. 개인정보의 이용기간 : 모든 검토가 완료된 후 3개월간 이용자의 조회를 위하여 보관하며, 이후 해당정보를 지체 없이 파기합니다. <br />
								 4. 그 밖의 사항은 개인정보취급방침을 준수합니다.
							</div>
						</div>
						<div style="padding-top:7px;padding-left:5px;padding-bottom:7px;font-family: Gulim,Tahoma,verdana;">
							<input type="checkbox" name="info" value="1" class="input_radio"> 개인정보 수집 및 이용에 대해 동의합니다.
						</div>
					</td>
				</tr>
				</table>
			</div>

			<div class="btn_area">
				<div class="align_left">			
					<input type="button" value="목록">
				</div>
				<div class="align_right">
					<input type="reset" value="다시쓰기">&nbsp;&nbsp;	
					<input type="submit" value="쓰기">					
				</div>	
			</div>	
			<!--//게시판-->
		</div>
	</form>
	</section>

	<!-- 이벤트 참여하기 끝 -->
	
	<!-- 푸터 -->
	
	<!-- 푸터 끝 -->
</body>
</html>