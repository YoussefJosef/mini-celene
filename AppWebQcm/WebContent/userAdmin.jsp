<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users Informations</title>
</head>
<body>
	<h1>Users Information</h1>
	<form action="./UserServlet" method="post" >
	<table>
		<tr>
			<td>
			<input type="submit" name="action" value="Add" />
			<input type="submit" name="action" value="Edit" />
			<input type="submit" name="action" value="Delete" />
			<input type="submit" name="action" value="Search" />
			</td>
		</tr>
		<tr>
			<td>Login</td>
			<td><input type="text" name="login" value="${utilisateur.login}"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="text" name="password" value="${utilisateur.password}"/></td>
		</tr>
		<tr>
			<td>Role</td>
			<td><input type="text" name="role" value="${utilisateur.role}"/></td>
		</tr>
		
		<tr>
			<td>Nom</td>
			<td><input type="text" name="nom" value="${utilisateur.nom}"/></td>
		</tr>
		
		<tr>
			<td>Prenom</td>
			<td><input type="text" name="prenom" value="${utilisateur.prenom}"/></td>
		</tr>
		
	</table>
	</form>
	
	<br>
	<table>
			<tr>
				<th>LOGIN :</th>
				<th>PASSWORD : </th>
				<th>ROLE :</th>
				<th>NOM : </th>
				<th>PRENOM :</th>
			</tr>
		<c:forEach items="${allUsers}" var="u">
			<tr>
				<td>${u.login}</td>
				<td>${u.password}</td>
				<td>${u.role}</td>
				<td>${u.nom}</td>
				<td>${u.prenom}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>