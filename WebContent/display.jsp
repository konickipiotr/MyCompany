<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
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
		<h1 style="text-align: center">DISPLAY DATA BASE</h1>
		<div style="width: 600px; height: 60px; margin: auto; margin-bottom: 20px; background-color: #CCFFFF; border-radius: 5px; border: 2px solid; padding: 5px" >
			<form action="dispServlet" method="post">
				<table>
					<tr>
						<td><input type="checkbox" name="disp" value="all" checked="checked"> </td> <td>all  |</td>
						<td><input type="checkbox" name="disp" value="fname"></td><td>First Name  |</td>
						<td><input type="checkbox" name="disp" value="lname"></td><td>Last Name  |</td>
						<td><input type="checkbox" name="disp" value="dep"></td><td>Department  |</td>
						<td><input type="checkbox" name="disp" value="pos"></td><td>Position  |</td>
						<td><input type="checkbox" name="disp" value="salary"></td>	<td>Salary  |</td>
						<td><input type="submit" value="Filter"></td>
					</tr>
				</table>
				<div style="width: 400px; margin: auto"> Search   <input type="text" name="searchValue">  po:
				
				<select name="search">
					<option selected="selected">all</option>
					<option value="id">id</option>
					<option value="firstName">First name</option>
					<option value="lastName">Last name</option>
					<option value="department">Department</option>
					<option value="position">Position</option>
					<option value="salary">Salary</option>
				</select>
				</div> 
			</form>
		</div>
		<% if( request.getAttribute("message") != null){ %>
			<div class="messageblock"><%= request.getAttribute("message") %></div>
		<%
			}
		%>
		<div class="inner">
		<% List<Worker> workerlist = (List<Worker>)request.getAttribute("worekList");
		Boolean b[] = (Boolean[]) request.getAttribute("checkDep");
		if(workerlist != null){
			
		%>
		<div style="width: 800px; margin: auto">
		<table  rules="all" style="table-layout:fixed; border-collapse:collacpse; border: 1px ridge ; width: 100%; background: white">
			<tr style="background: #C0C0C0 ">
				<th style="width: 30px">id</th> 			
				<%if(b[1] == true ){ %><th>First name</th> <% } %>
				<%if(b[2] == true ){ %><th>Last name</th><% } %>
				<%if(b[3] == true){ %><th>Department</th><% } %>
				<%if(b[4] == true){ %><th>Position</th><% } %>
				<%if(b[5] == true){ %><th>Salary</th><% } %>
				<th style="width: 40px"></th>
				<th style="width: 60px"></th>
			</tr>

				<%
					for (Worker w : workerlist) {
				%>

			<tr>
				<td style="background: #EEEEEE "><%= w.getId() %></td>
				<%if(b[1] == true){ %><td><%= w.getFirstName() %></td><% } %>
				<%if(b[2] == true){ %><td><%= w.getLastName() %></td><% } %>
				<%if(b[3] == true){ %><td><%= w.getDepartment() %></td><% } %>
				<%if(b[4] == true){ %><td><%= w.getPosition() %></td><% } %>
				<%if(b[5] == true){ %><td><%= w.getSalary() %></td><% } %>
				<td >
					<form action="retEmplServlet" method="POST">
						<input type="hidden" name="id" value="<%= w.getId() %>" />
						<input type="submit" value="Edit">
					</form>
				</td>
				<td>
					<form action="deleteServlet" method="POST">
						<input type="hidden" name="id" value="<%= w.getId() %>" />
						<input type="submit" value="Delete">
					</form>
				</td>
			</tr>
				<%
				}
		}
				%>
			</table>
			</div>
					
		</div>
	</article>
	
	<footer class="footer">copyright: Piotr Konicki</footer>

</body>
</html>