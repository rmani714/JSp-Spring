<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Player</title>
</head>
<body>
<h2>Registration</h2>
<form action="PlayerServlet" method="GET">
<input type="hidden" name="action" value="ADD">
	<table>
			<tr>
				<td> ID : </td>
				<td> <input type="text" name="id" ></input></td>
			</tr>
			<tr>
				<td> Name : </td>
				<td> <input type="text" name="name" ></input></td>
			</tr>
			<tr>
				<td> Rank : </td>
				<td> <input type="text" name="rank" ></input></td>
			</tr>
			<tr>
				<td></td>
				<td> <input type="submit"  value="Submit" class="save"></input></td>
			</tr>
	</table>
</form>
	<div style="clear: both;">
		<p>
			<a href="PlayerServlet"> Back to player</a>
		</p>
	</div>
</body>

</html>