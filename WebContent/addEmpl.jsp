<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1 style="text-align: center">DODAJ PRACOWNIKA</h1>
		<% if(request.getAttribute("message") != null){ %>
		<div style="width:500px; height: 40px; margin: auto; background-color: #FFCCCC; padding: 5px; text-align: center; border-radius: 5px; border: 2px solid; margin-bottom: 30px"><%=request.getAttribute("message") %></div>
		<%
			}
		%>
		<div class="inner">
		<div style="width: 400px; margin: auto">
			<p style="text-align: center; font-size: 16px; color: red"><% request.getParameter("message"); %></p>
			<form action="addServlet" method="post">
				<table style="border: none">	
					<tr><td>Imie:</td><td><input type="text" name="firstName"></td></tr>
					<tr><td>Nazwisko:</td><td><input type="text" name="lastName"></td></tr>
					<tr><td>Dział:</td><td><input type="text" name="department"></td></tr>
					<tr><td>Stanowsko</td><td><input type="text" name="position"></td></tr>
					<tr><td>Płaca</td><td><input type="number" name="salary" value="0"></td></tr>
				</table>
					<input type="submit" value="Dodaj">
			</form>
		</div>
		</div>
	</article>
	
	<footer class="footer">copyright: Piotr Konicki</footer>

</body>
</html>