<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<title>Category Page</title>
<script src="js/jquery-3.6.0.js"></script>
<script>
	$(function(){
		$("#category_show a").on("click",function(){
			 var idx = $('#category_show a').index(this);
			 $("select>option").eq(idx+1).attr("selected","selected");
		})
	});
</script>
<style>
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
      background: #ffdaff;
      border-radius: 5px;
      margin: 5px 0;
      box-sizing: border-box;
      box-shadow: rgb(0 0 0 / 24%) 0px 3px 8px;
      padding: 10px 0 10px;
      display: flex;
      align-items: center;
      justify-content: center;
  }
  
  .K2-grid li a {
      display: block;
      text-decoration: none;
      color: #333;
  }
  
  .K2-grid li svg {
      margin: 3px 0;
      width: 35px;
      height: 35px;
      fill: #26ae61;
      display: inline-block;
  }
  
  .K2-grid li span {
      display: block;
      padding: 0 3px;
  }
  </style>
</head>

<body>
<section id="category_show">
	<div class='K2-grid coll-3 icon-p-2'>
       
	<ul>
		<li>
		    <a>
		    <span>족발·보쌈</span></a>
		</li>
		
		<li>
		    <a>
		    <span>찜·탕·찌개</span></a>
		</li>
		
		<li>
		    <a>
		    <span>돈까스·회·일식</span></a>
		</li>
		        
		<li>
		    <a>
		    <span>피자</span></a>
		</li>
		
		<li>
			 <a>
		    <span>고기·구이</span></a>
		</li>
		
		
		<li>
		     <a>
		    <span>치킨</span></a>
		</li>
		
		<li>
		     <a>
		    <span>중식</span></a>
		</li>
		
		<li>
		     <a>
		    <span>도시락</span></a>
		</li>
		
		<li>
		     <a>
		    <span>패스트푸드</span></a>
		</li>
		
		<li>
		     <a>
		    <span>분식1</span></a>
		</li>
		
		<li>
		     <a>
		    <span>분식2</span></a>
		</li>
		
		<li>
		    <a>
		    <span>카페·디저트</span></a>
		</li>
		
	</ul>
</div>
</section> 
</body>
</html>