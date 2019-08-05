<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- 템플릿 플러그인 -->
<script type="text/javascript"
	src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>

<script type="text/x=jquery-tmpl" id="itemTemplate">
		<li data-num = "${num}" class = "comment_item"> ${num},${writer},${content},${datetime}
			<input type='button' class='delete_btn' value='삭제'>
		</li>
		
	</script>
<script type="text/javascript">
	function addNewItem(num, writer, content, datetime) {
		var li_data = {
			"num" : num,
			"writer" : writer,
			"content" : content,
			"datetime" : datetime

		};
		var new_li = $("#itemTemplate").tmpl(li_data);

		$("#comment_List").prepend(new_li);

	}
	$(function() {
		$.get("comment_List", {}, function(data) {

			$(data).find("comment").each(function() {
				var num = $(this).find("num").text();
				var writer = $(this).find("writer").text();
				var content = $(this).find("content").text();
				var datetime = $(this).find("datetime").text();

				addNewItem(num, writer, content, datetime);
			});

		}).fail(function() {

			alert("댓글 목록을 불러오는데 실패하였습니다");
		});

		$("#comment_form").submit(function() {
			if (!$("#user_name").val()) {
				alert("이름 넣어");
				return false;
			}
			if (!$("#comment").val()) {
				alert("내용 넣어");
				return false;

			}

			$.post("comment_write", $(this).serialize(), function(xml) {
				var result = $(xml).find("result").text();
				var message = $(xml).find("message").text();

				if (result) {
					alert(message);
					var num = $(xml).find("num").text();
					var writer = $(xml).find("writer").text();
					var content = $(xml).find("content").text();
					var datetime = $(xml).find("datetime").text();

					addNewItem(num, writer, content, datetime);

					$("#user_name").val("");
					$("#comment").val("");
				} else {
					alert(message);

				}
			}).fail(function() {
			});

			return false;
		});

		$(document).on('click', '.delete_btn', function() {
			if (confirm("정말로 삭제하시겠습니까?")) {

				var num = $(this).parent("li").attr("data-num");
				var target = $(this).parents(".comment_item");
				target.remove();
				$.post("deleteComment", {"num" : num}, function(xml) {
					

				}).fail(function() {

					alert("삭제 실패")
				});
			}

		});

	});
</script>
</head>
<body>
	<label>Ajax Comment</label>
	<form id="comment_form">
		<label for="user_name">작성자</label> <input type="text" name="user_name"
			id="user_name" /> <input type="submit" value="저장하기" /> <br />
		<br /> <label for="comment">덧글 내용</label>
		<textarea name="comment" id="comment" rows="10" cols="20">
		</textarea>

	</form>

	<ul id="comment_List">
		<!-- 여기에 동적 생성 요소 들어감 -->
	</ul>


</body>
</html>