<%@page import="com.base.mvc.Player"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Player List</title>

<link href="css/style.css" type="text/css" rel="stylesheet">

</head>
<%
List<Player> playerList = (List<Player>)request.getAttribute("playerList");
pageContext.setAttribute("playerList", playerList);
%>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Player List</h2>
		</div>
	</div>
	<div>
	<input type="button" value="Add Player" onclick="window.location.href='add-player.jsp';return false;">
	</div>
	<div id="container">
		<div id="content">
			<table border="1">
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>RANK</th>
					<th>action</th>
				</tr>
				<c:forEach var="tmpPlayer" items="${playerList}">
				<c:url var="tempURL" value="PlayerServlet">
				<c:param name="action" value="LOAD"></c:param>
				<c:param name="p_id" value="${tmpPlayer.id}"></c:param>
				</c:url>
					<tr>
						<td>${tmpPlayer.id}</td>
						<td>${tmpPlayer.name}</td>
						<td>${tmpPlayer.rank}</td>
						<td><a href="tempURL" /> update </td>
					</tr>
				</c:forEach>
				<%-- <% for (Player tmpPlayer : playerList) {%>
				<tr>
					<td><%=tmpPlayer.getId()%></td>
					<td><%=tmpPlayer.getName()%></td>
					<td><%=tmpPlayer.getRank()%></td>
				</tr>
				<%}%> --%>
			</table>
		</div>
	</div>



</body>
</html>