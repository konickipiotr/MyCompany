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
		<h1 style="text-align: center">Inne</h1>
		<div class="inner">
			<div style="width: 500px; margin:auto; padding: 20px">
				<%! 
					String path, dir, filename; %>
				<%  
					if(((String) request.getAttribute("filename") == null) ||  ((String) request.getAttribute("dir") == null)){

					dir = "F:\\"; 
					filename = "Empl.xls";
					
					}else{
						filename = (String) request.getAttribute("filename");
						dir = (String) request.getAttribute("dir");
					}
					path = dir + filename;

				%>
				<% if( request.getAttribute("message") != null){ %>
			<div class="messageblock"><%= request.getAttribute("message") %></div>
			<%
				}
			%>
				
				
				<div style="width: 400px; margin:auto; margin-bottom: 50px; padding: 10px; background-color: #99FFCC; border: 2px solid; border-radius: 5px; ">
				<h3 style="text-align: center">File path</h3>
				<form action="CorrectFile" method="post">
					<table style="width: 100%">
						<tr>
							<td>Filename (*.xls)</td><td><input type="text" value="<%=filename %>" name="filename"></td>
						</tr>
						<tr>
							<td>Path: </td><td><input type="text" value="<%=dir %>" name="dir"></td>
						</tr>
						<tr>
							<td></td><td><input type="submit" value="Change"></td>
						</tr>
					</table>					
				</form>
				</div>
				
				<div style="width: 400px; margin:auto; padding: 10px; background-color: #99FFCC; border: 2px solid; border-radius: 5px; ">
				<h3 style="text-align: center">Load database from file *.xls</h3>
				<form action="LoadServlet" method="post">
					<input type="hidden" name="path" value="<%=path %>">
					<table style="width:80%; margin:auto">
						<tr>
							<td style="width: 100px">Path</td><td><%=path %></td>
						</tr>
						<tr>
							<td> add <input type="radio" name="option" value="add"></td> <td>reload <input type="radio" name="option" value="update"></td>
						</tr>
						<tr>
							<td></td><td><input type="submit" value="Load"></td>
						</tr>
					</table>
				</form>
				</div>
				
			</div>
		</div>
	</article>
	
	<footer class="footer">copyright: Piotr Konicki</footer>

</body>
</html>