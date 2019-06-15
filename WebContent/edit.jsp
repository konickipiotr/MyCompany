<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="pl.mycompany.model.Worker" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyCompany</title>
<link rel="Stylesheet" href="style.css">
</head>
<body>
	<header class="header">		
		<p style="font-size: 28px; font-weight: bold; color: #ffffe6; Font-style: italice">MyCompany</p>
		<p style="text-align: right; font-size:13px; Font-style: italic; color: #990099">Software for your company   </p>
		<p style="text-align: right; font-size: 10px">My Company v0.1    </p>
	</header>
	<nav class="menu">
		<div style="margin:auto; width: 1000px">
			<div class="button"><a href="index.jsp">HOME PAGE</a></div>
			<div class="button"><a href="addEmpl.jsp">ADD EMPLOYEE</a></div>
			<div class="button"><a href="display.jsp">DISPLAY DATABASE</a></div>
			<div class="button"><a href="other.jsp">OTHER</a></div>
		</div>
	</nav>
	
	<article class="main">
	<h1 style="text-align: center">Edytuj</h1>
	
	<%  
		Worker worker = null;
		worker = (Worker)request.getAttribute("worker");
		if(worker != null){
	%>
	
		<div class="inner">
			<div style="width: 600px; margin: auto">
			<form action="UpdateRecordServlet" method="post">
				<input type="hidden" name="id" value="<%= worker.getId() %>">
				<table style="border: none">	
				<tr><td></td>		<td> <b>New data</b> </td>	<td style="width: 100px"></td><td></td><td><b>Old data</b></td></tr>
					<tr><td>First Name:</td>		<td><input type="text" name="firstName" value="<%= worker.getFirstName() %>" ></td>	<td style="width: 100px"></td><td>First Name:</td><td><%= worker.getFirstName() %> </td></tr>
					<tr><td>Last Name:</td>	<td><input type="text" name="lastName" value="<%= worker.getLastName() %>"></td>	<td style="width:100px"></td>	<td>Nazwisko:</td><td><%= worker.getLastName() %></td></tr>	
					<tr><td>Department:</td>		<td><input type="text" name="department" value="<%= worker.getDepartment() %>"></td><td style="width:100px"></td>	<td>Last Name:</td>	<td><%= worker.getDepartment() %></td></tr>	
					<tr><td>Position:</td>	<td><input type="text" name="position" value="<%= worker.getPosition() %>"></td>	<td style="width:100px"></td>	<td>Position:</td><td><%= worker.getPosition() %></td></tr>	
					<tr><td>Salary:</td>		<td><input type="number" name="salary" value="<%= worker.getSalary() %>"></td>		<td style="width: 100px "></td>	<td>Salary:</td><td><%= worker.getSalary() %></td></tr>	
				</table>
					<input type="submit" value="Edit">
			</form>
			</div>
		</div>
		<%
		}
		%>
	</article>
	
	<footer class="footer">copyright: Piotr Konicki</footer>

</body>
</html>