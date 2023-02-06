<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>현재 페이지는 empForm.do의 결과 emp.jsp 입니다.</h3>
<%
	Map<String, String> list = //
	   (Map<String, String>) request.getAttribute("jobList"); // Object -> (EmpVO)
%>

<form name="myFrm" action="employee.do" method="post">
	<table class="table">
		<tr>
			<th><label>사원번호:</label></th>
			<td><input type="number" name="eid"></td>
		</tr>
		<tr>
			<th><label>LastName:</label></th>
			<td><input type="text" name="last_name"></td>
		</tr>
		<tr>
			<th><label>이메일:</label></th>
			<td><input type="email" name="email"></td>
		</tr>
		<tr>
			<th><label>입사일자:</label></th>
			<td><input type="date" name="hire_date"></td>
		</tr>
		<tr>
			<th><label>직무:</label></th>
			<td><select name="job">
					<%
					for (Entry<String, String> ent : list.entrySet()) {
					%>
					<option value="<%=ent.getKey()%>"><%=ent.getValue()%></option>
					<%
					}
					%>
			</select></td>
		<tr>
			<td><input type="submit" value="전송"> <input
				type="button" value="조회" onclick="empGetFnc()"> <input
				type="button" value="다음" onclick="daumGetFnc()"></td>
		</tr>
	</table>
</form>
<a href="../result/empList.jsp">조회.</a>

<script>
	console.log(document.forms.myFrm);
	function empGetFnc() {
		document.forms.myFrm.method = 'get';
		document.forms.myFrm.action = 'empList.do';
		document.forms.myFrm.submit();
	}
	function daumGetFnc() {
		document.forms.myFrm.method = 'delete';
		document.forms.myFrm.submit();
	}
</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>
