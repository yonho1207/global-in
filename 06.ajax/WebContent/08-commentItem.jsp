<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     
    <%@ page trimDirectiveWhitespaces="true" %>
  
    <?xml version="1.0" encoding="utf-8" ?>
   
<comments>
	<result>${result}</result>
	<message>${message}</message>
	<item>
		<num>${comments.num}</num>
		<writer>${comments.writer}</writer>
		<content>${comments.content}</content>
		<datetime>${comments.datetime}</datetime>
	</item>

</comments>
