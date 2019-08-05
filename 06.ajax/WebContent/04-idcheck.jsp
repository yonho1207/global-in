<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>04-idcheck.html</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#checkid").click(function(){
				
				var input_val=$("#id").val();
				//alert(input_val);
				if(!input_val){
					alert("아이디를 입력하세요");
					return false;
					
				}
				var url = "idcheck";
				
				$.get(url,{"id":input_val},function(xml){
					
					var result = $(xml).find("result").text();
					
					$(".console").html(result);
				});
		});
		
		
	});
</script>
</head>
<body>
	<h1>아이디 중복체크</h1>
	<form method="post" action="idcheck">
	
		<input type="text" name="id" id="id"/>
		<input type="button" id="checkid" value="중복검사"/>	
	</form>
	<div class="console"></div>
</body>
</html>