<%@page import="com.yedam.emp.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@page import="com.yedam.emp.service.EmpServiceImpl"%>
<%@page import="com.yedam.emp.service.EmpService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<jsp:include page="../includes/header.jsp"></jsp:include>

	<%
		List<EmpVO> list = (List<EmpVO>) request.getAttribute("empList");
	%>
	<h3>사원목록(EmpControl.do 의 결과 페이지.)</h3>
	<table class="table">
		<thead><tr>
			<th>사원번호</th>
			<th>LastName</th>
		</tr></thead>
		<tbody>
			<% for (EmpVO emp : list) { %>
				<tr>
					<td><a href="empDetail.do?eid=<%=emp.getEmployeeId() %>"><%=emp.getEmployeeId() %></a></td>
					<td><%=emp.getLastName() %></td>
					<td><%=emp.getEmail() %></td>
					<td><%=emp.getJobId() %></td>
					<td><%=emp.getHireDate() %></td>
				</tr>
			<%}%>
		</tbody>
	</table>
<jsp:include page="../includes/footer.jsp"></jsp:include>
