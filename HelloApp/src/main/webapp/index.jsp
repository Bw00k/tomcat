<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String msg = new String("Welcome");
		List<String>fruits = new ArrayList<>();
		fruits.add("banana");
		fruits.add("cherry");
	%>
	
	<h3>입력값은 <%=msg  %></h3>
	
	<ul>
		<li>Apple</li>
		<%
		for (String fruit : fruits){
			%>
			<li><%=fruit %></li>
			<%
		}
		%>
	</ul>

	<a href="service.do">사원목록의 json포맷</a>
</body>
</html>