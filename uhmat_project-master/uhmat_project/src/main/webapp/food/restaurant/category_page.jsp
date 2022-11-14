<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<title>Category Page</title>
<style>
  .categoryTitle {
  	color: white;
  	
  }
  
  .K2-grid {
      padding: 5px;
      text-align: center;
  }
  
  .K2-grid ul {
      clear: both;
      margin: 15px 0 20px;
      padding:0;
      width: 100%;
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
  }
  
  .coll-3 ul li {
      width: 30%;
  }
  
  .K2-grid li {
      font-size: 0.9em;
      font-weight: 500;
      line-height: 1.3em;
      text-align: center;
/*       background: #FF1818; */
	  background-color: transparent;
      border-radius: 5px;
      margin: 5px 0;
      box-sizing: border-box;
      box-shadow: rgb(0 0 0 / 24%) 0px 3px 8px;
      padding: 10px 0 10px;
      display: flex;
      align-items: center;
      justify-content: center;
	  border-bottom: 1px solid #8B4513;
	  border-right: 1px solid #8B4513;
/* 	  border-left: 1px solid #F5F5F5; */
  }
  
  .K2-grid li a {
      display: block;
      text-decoration: none;
      color: #ff1818;
  }
  
  .K2-grid li svg {
      margin: 3px 0;
      width: 35px;
      height: 35px;
      fill: #9ACD32;
      display: inline-block;
  }
  
  .K2-grid li span {
      display: block;
      padding: 0 3px;
  }
  </style>
</head>

<body>
	<div class='K2-grid coll-3 icon-p-2'>
<h2 class="categoryTitle"> 카테고리별로 식당보기 </h2>
        
	<ul>
		<li>
		    <a href='restaurantList.re?category=족발·보쌈'>
		    <span>족발·보쌈</span></a>
		</li>
		
		<li>
		    <a href='restaurantList.re?category=찜·탕·찌개'>
		    <span>찜·탕·찌개</span></a>
		</li>
		
		<li>
		    <a href='restaurantList.re?category=돈까스·회·일식'>
		    <span>돈까스·회·일식</span></a>
		</li>
		        
		<li>
		    <a href='restaurantList.re?category=피자'>
		    <span>피자</span></a>
		</li>
		
		<li>
			 <a href='restaurantList.re?category=고기·구이'>
		    <span>고기·구이</span></a>
		</li>
		
		
		<li>
		     <a href='restaurantList.re?category=치킨'>
		    <span>치킨</span></a>
		</li>
		
		<li>
		     <a href='restaurantList.re?category=중식'>
		    <span>중식</span></a>
		</li>
		
		<li>
		     <a href='restaurantList.re?category=도시락'>
		    <span>도시락</span></a>
		</li>
		
		<li>
		     <a href='restaurantList.re?category=패스트푸드'>
		    <span>패스트푸드</span></a>
		</li>
		
		<li>
		     <a href='restaurantList.re?category=분식1'>
		    <span>분식1</span></a>
		</li>
		
		<li>
		     <a href='restaurantList.re?category=분식2'>
		    <span>분식2</span></a>
		</li>
		
		<li>
		    <a href='restaurantList.re?category=돈까스·회·일식'>
		    <span>카페·디저트</span></a>
		</li>
		
	</ul>
</div>
</body>
</html>