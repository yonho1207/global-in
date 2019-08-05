<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page trimDirectiveWhitespaces="true" %>
  
    <?xml version="1.0" encoding="utf-8" ?>
   
<comments>
	 <c:forEach var="comment" items="${comments}">
	<comment>
		<num>${comment.num}</num>
		<writer>${comment.writer}</writer>
		<content>${comment.content}</content>
		<datetime>${comment.datetime}</datetime>
	</comment>
</c:forEach>
</comments>
