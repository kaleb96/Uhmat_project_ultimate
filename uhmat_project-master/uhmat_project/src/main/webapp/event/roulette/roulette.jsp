<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Uhmt Roulette</title>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet">
</head>
<style>
/* 		body {           */
/* 			width: 100%;  */
/* 	        height: 100vh;  */
/* 			overflow : hidden; */
/* 		}	 */

.container {
	display: grid;
	min-height: 100%;
	align-content: center;
	justify-content: center;
}

h1 {
	font-family: 'Poor Story', cursive;
}

#roulette {
	width: 400px;
	height: 400px;
	border-radius: 50%;
	background: white;
	border: 5px solid black;
	position: relative;
	font-family: 'Poor Story', cursive;
}

.panel {
	position: absolute;
	width: 400px;
	height: 400px;
	border-radius: 50%;
	-webkit-clip-path: polygon(50% 0%, 50% 50%, 0% 50%, 0% 0%);
	clip-path: polygon(50% 0%, 50% 50%, 0% 50%, 0% 0%);
}

.txt {
	font-size: 18px;
	letter-spacing: -0.2em;
	width: 80%;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	height: 260px;
	position: absolute;
	top: 60px;
	left: 30px;
	text-align: center;
	transform: rotate(-45deg);
	font-family: 'Poor Story', cursive;
}

.pallet {
	width: 295px;
	height: 180px;
}

.colorBox {
	width: 20px;
	height: 20px;
	float: left;
	margin: 1px 1px 0px 0px;
	cursor: pointer;
	font-family: 'Poor Story', cursive;
}

.showColorBox {
	float: left;
	margin: 1px 1px 0px 0px;
	text-align: center;
	font-family: 'Poor Story', cursive;
}

.popup {
	position: fixed;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	opacity: 0;
	visibility: hidden;
	transform: scale(1.1);
	transition: visibility 0s linear 0.25s, opacity 0.25s 0s, transform
		0.25s;
}

.popup-content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: white;
	padding: 1rem 1.5rem;
	border-radius: 0.5rem;
	font-family: 'Poor Story', cursive;
}

.startBtn {
	border: 2px solid #6C757D;
    background-color: #6C757D;
    color: white;
    padding: 5px;
    border-radius: 5px;
    font-family: 'Poor Story', cursive;
}

button {
	font-family: 'Poor Story', cursive;
	border: 2px solid #6C757D;
    background-color: #6C757D;
    color: white;
    padding: 5px;
    border-radius: 5px;
}

.btnArea {
	float: right;

}

.btnArea input {
	background-color: #1aa2c0;
	border: none;
	height: 32px;
	line-height: 1;
	padding-left: 15px;
	padding-right: 15px;
	color: #ffffff;
	font-family: 'Poor Story', cursive;
}

.show-popup {
	opacity: 1;
	z-index: 5;
	visibility: visible;
	transform: scale(1.0);
	transition: visibility 0s linear 0s, opacity 0.25s 0s, transform 0.25s;
}

#addDiv {
	position: absolute;
	top: 25%;
	left: 20%;
	font-family: 'Poor Story', cursive;
}

.arrow {
	left: 49.5%;
	position: sticky;
	z-index: 1;
	width: 0;
	height: 0;
	border-top: 30px solid purple; /* 화살표 */
	border-left: 10px solid transparent;
	border-right: 10px solid transparent;
}

#copyright {
	font-size: 7px;
}

table {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	margin: 20px 10px;
	font-family: 'Poor Story', cursive;
}

table th {
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: #fff;
	background: #ce4869;
}

table td {
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #eee;
}
</style>

<script type="text/javascript">
	window.onload = function() {
		initRoulette();
	}

	function initRoulette() {
		setRoulettePanel();
		initAddPop();

		document.getElementById("roulette").addEventListener("animationstart",
				function(e) {
					console.log("시작");
				}, false);

		document.getElementById("roulette").addEventListener("animationend",
				function(e) {
					console.log("종료");
				}, false);
	}

	// 룰렛 추가창 취소 함수
	function triggerAddPop() {
		document.querySelector(".popup").classList.toggle("show-popup");
	}

	// 각 룰렛 칸수 추가 버튼
	function openAddPop() {
		document.getElementById("btnArea").innerHTML = '<input type="button" id="cancelBtn" value="취소" onclick="triggerAddPop();">'
				+ '<input type="button" id="addBtn" value="추가" onclick="clickAddBtn();">';

		document.getElementById("addText").value = "";
		document.getElementById("fontColor").value = "";
		document.getElementById("color").value = "";
		document.getElementById("colorTypeBg").checked = "checked"

		document.querySelector(".popup").classList.toggle("show-popup");
	}

	// 각 룰렛 칸수 삭제 버튼
	// 	   	function openDelPop() {
	// 	   		setRoulettePanel();
	// 	   		document.querySelctor(".popup").classList.toggle("show-popup");

	// 	   		let targetPanel = document.querySelector(".panel_" + index);
	// 	   		targetPanel.remove();
	// 	   	}

	// 각 룰렛 칸 수정 버튼
	function openUpdatePop(index) {
		document.getElementById("btnArea").innerHTML = '<input type="button" id="cancelBtn" value="취소" onclick="triggerAddPop();">'
				+ '<input type="button" id="delBtn" value="삭제" onclick="clickDeleteBtn('
				+ index
				+ ');">'
				+ '<input type="button" id="saveBtn" value="저장" onclick="clickUpdateBtn('
				+ index + ');">';

		var target = document.getElementById("panelInfo" + index);
		document.getElementById("addText").value = target.innerHTML;
		document.getElementById("fontColor").value = target.style.color;
		document.getElementById("color").value = target.style.background;
		;
		document.getElementById("colorTypeBg").checked = "checked"
		document.querySelector(".popup").classList.toggle("show-popup");
	}

	// 룰렛 배경, 글자색 변경 함수
	function initAddPop() {
		//2차원 배열 파레트 데이터
		var pallet = [
				[ "#FF0000", "#FF5E00", "#FFBB00", "#FFE400", "#ABF200",
						"#1DDB16", "#00D8FF", "#0054FF", "#0100FF", "#5F00FF",
						"#FF00DD", "#FF007F", "#000000", "#FFFFFF" ],
				[ "#FFD8D8", "#FAE0D4", "#FAECC5", "#FAF4C0", "#E4F7BA",
						"#CEFBC9", "#D4F4FA", "#D9E5FF", "#DAD9FF", "#E8D9FF",
						"#FFD9FA", "#FFD9EC", "#F6F6F6", "#EAEAEA" ],
				[ "#FFA7A7", "#FFC19E", "#FFE08C", "#FAED7D", "#CEF279",
						"#B7F0B1", "#B2EBF4", "#B2CCFF", "#B5B2FF", "#D1B2FF",
						"#FFB2F5", "#FFB2D9", "#D5D5D5", "#BDBDBD" ],
				[ "#F15F5F", "#F29661", "#F2CB61", "#E5D85C", "#BCE55C",
						"#86E57F", "#5CD1E5", "#6799FF", "#6B66FF", "#A566FF",
						"#F361DC", "#F361A6", "#A6A6A6", "#8C8C8C" ],
				[ "#CC3D3D", "#CC723D", "#CCA63D", "#C4B73B", "#9FC93C",
						"#47C83E", "#3DB7CC", "#4374D9", "#4641D9", "#8041D9",
						"#D941C5", "#D9418C", "#747474", "#5D5D5D" ],
				[ "#980000", "#993800", "#997000", "#998A00", "#6B9900",
						"#2F9D27", "#008299", "#003399", "#050099", "#3F0099",
						"#990085", "#99004C", "#4C4C4C", "#353535" ],
				[ "#670000", "#662500", "#664B00", "#665C00", "#476600",
						"#22741C", "#005766", "#002266", "#030066", "#2A0066",
						"#660058", "#660033", "#212121", "#191919" ] ];
		var tag = "";
		for (i = 0; i < pallet.length; i++) {
			for (j = 0; j < pallet[i].length; j++) {
				tag += "<div id=" + pallet[i][j]
						+ " class='colorBox' onclick='colorSet(this)'></div>";
			}
		}
		//파레트 파싱
		document.getElementById("palletBox").innerHTML = tag;

		//색상 입히기
		var colorBox = document.getElementsByClassName("colorBox");
		for (i = 0; i < colorBox.length; i++) {
			colorBox[i].style.background = colorBox[i].id;
		}
	}

	// 각 룰렛의 칸의 추가 함수
	function setRoulettePanel() {
		var panelArr = document.querySelectorAll(".panel")
		let panelArrSize = panelArr.length;
		let rotate = 360 / panelArrSize;

		var i = 0
		var panelRotate = 0;
		var panelInfoTbody = [];
		while (i < panelArrSize) {
			panelRotate = panelRotate + rotate;
			panelArr[i].style.transform = "rotate(" + panelRotate + "deg)";
			i = i + 1;
			panelInfoTbody
					.push("<tr><td id=\"panelInfo"+i+"\" style=\"background:"+panelArr[i-1].style.background+"; color:"+panelArr[i-1].style.color+"\">"
							+ panelArr[i - 1].innerText + "</td>");
			panelInfoTbody.push("<td><button onclick=\"openUpdatePop('" + i
					+ "');\">바꾸기</button></td></tr>");

		}
		panelInfoTbody
		document.getElementById("panelInfoTbody").innerHTML = panelInfoTbody
				.join('');
	}

// 	// 룰렛의 칸 수를 추가하는 함수
// 	function clickAddBtn() {
// 		var roulette = document.getElementById("roulette");
// 		let size = document.querySelectorAll(".panel").length + 1;
// 		let addPanel = document.createElement("div");
// 		var panelText = document.getElementById("addText").value;

// 		addPanel.setAttribute("class", "panel panel_" + size);
// 		addPanel.innerHTML = "<strong class=\"txt\">" + panelText + "</strong>";

// 		addPanel.style.background = document.getElementById("color").value;
// 		addPanel.style.color = document.getElementById("fontColor").value;

// 		roulette.appendChild(addPanel);
// 		roulette.setAttribute("class", "eq" + size);
// 		setRoulettePanel();
// 		document.querySelector(".popup").classList.toggle("show-popup");
// 	}

// 	// 룰렛의 칸을 삭제하는 함수
// 	function clickDeleteBtn(index) {
// 		setRoulettePanel();
// 		document.querySelector(".popup").classList.toggle("show-popup");

// 		let targetPanel = document.querySelector(".panel_" + index);
// 		targetPanel.remove();
// 	}

	// 룰렛의 변경 값을 저장하는 함수
	function clickUpdateBtn(index) {
		let targetPanel = document.querySelector(".panel_" + index);
		var panelText = document.getElementById("addText").value;

		targetPanel.innerHTML = "<strong class=\"txt\">" + panelText
				+ "</strong>";

		targetPanel.style.background = document.getElementById("color").value;
		targetPanel.style.color = document.getElementById("fontColor").value;

		setRoulettePanel();
		document.querySelector(".popup").classList.toggle("show-popup");
	}

	// 룰렛이 자동으로 돌아가고, 멈추는 함수
	function clickStartBtn() {

		document.getElementById("roulette").style.animation = "";
		var randomRotate = (Math.random() * 3000) + 1000;
		var randomAnimation = (Math.random() * 7000) + 2000;

		//document.getElementById("roulette").style.animation = "rotation 7s forwards ease-in-out";
		document.getElementById("roulette").animate([ {
			transform : "rotate(0deg)"
		}, {
			transform : "rotate(" + randomRotate + "deg)"
		} ], {
			fill : 'forwards',
			duration : randomAnimation,
			easing : 'ease-in-out'
		});

	}

	function colorSet(target) {
		var colorType = document
				.querySelector('input[name="colorType"]:checked').value;
		if (colorType == "font") {
			document.getElementById("fontColor").value = target.id;
			document.getElementById("showColorBox").style.color = target.id;
		} else {
			document.getElementById("color").value = target.id;
			document.getElementById("showColorBox").style.background = target.id;
		}
	}
</script>

<body>

	<div class="container">
		<header class="header">
			<h1 class="title">Roulette</h1>
		</header>
		<div class="arrow"></div>
		<div class="eq" id="roulette">
			<div class="panel panel_1" style="background: #00D8FF;">
				<strong class="txt">메뉴</strong>
			</div>
			<div class="panel panel_2" style="background: #F2CB61;">
				<strong class="txt">메뉴</strong>
			</div>
			<div class="panel panel_3" style="background: #FAECC5;">
				<strong class="txt">메뉴</strong>
			</div>
			<div class="panel panel_4" style="background: #665C00;">
				<strong class="txt">메뉴</strong>
			</div>
			<!-- 				<div class="panel panel_5" style="background: #476600;"><strong class="txt">5</strong></div> -->
			<!-- 				<div class="panel panel_6" style="background: #2F9D27;"><strong class="txt">6</strong></div> -->
			<!-- 				<div class="panel panel_7" style="background: #003399;"><strong class="txt">7</strong></div> -->
			<!-- 				<div class="panel panel_8" style="background: #6B66FF;"><strong class="txt">8</strong></div> -->
		</div>
		<br>
		
		<button class="startBtn" onclick="clickStartBtn();">돌려 돌려 돌림판 !</button>
		<br>
		<button onclick="history.back();">뒤로 가기 !</button>

		<div id="addDiv">
			<table id="panelInfo">
				<thead>
					<tr>
						<th>메뉴를 바꿔요 !</th>
						<th>체인지 !</th>
					</tr>
				</thead>
				<tbody id="panelInfoTbody">
				</tbody>
			</table>
		</div>

		<p id="copyright">uhmat records</p>
		
	</div>

	<!-- 롤렛 패널 수정 팝업  -->
	<div class="popup">
		<div class="popup-content">
			<h1 class="title">panel edit</h1>
			<table>
				<tbody>
					<tr>
						<th><label for="addText">텍스트</label></th>
						<td><input type="text" id="addText"></td>
					</tr>
					<tr>
						<th><label for="showColorBox">색상</label></th>
						<td class="showColorBox" id="showColorBox">글자색</td>
					</tr>

					<tr>
						<td><input type="radio" id="colorTypeBg" name="colorType"
							value="background" checked="checked"> <label
							for="colorTypeBg">룰렛배경색</label> <br /> <input type="radio"
							id="colorTypeFont" name="colorType" value="font"> <label
							for="colorTypeFont">텍스트글자색</label></td>
						<td>
							<div id="palletBox" class="pallet"></div> <input type="hidden"
							id="fontColor"> <input type="hidden" id="color">
						</td>
					</tr>

				</tbody>

			</table>

			<!-- 텍스트 바꾸기 버튼 -->
			<div class="btnArea" id="btnArea">
				<input type="button" id="cancelBtn" value="취소"
					onclick="triggerAddPop();"> <input type="button"
					id="addBtn" value="추가" onclick="clickAddBtn();"> <input
					type="button" id="delBtn" value="삭제" onclick="clickDeleteBtn();">
				<input type="button" id="saveBtn" value="저장"
					onclick="clickUpdateBtn();">
			</div>
		</div>
	</div>

</body>
</html>