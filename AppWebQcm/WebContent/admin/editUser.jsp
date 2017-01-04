<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Users Information</h1>
	<form action="./UserServlet" method="post" id="myform">
	<table>
		<tr>
			<td>Login</td>
			<td>${currentUser.login}</td>
			<td><input type="hidden" name="login" value="${currentUser.login}"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="text" name="password" value="${currentUser.password}"/></td>
		</tr>
		<tr>
			<td>Role</td>
			<td>Admin<input type="radio" name="role" value="1"/></td>
			<td>Enseignant<input type="radio" name="role" value="2"/></td>
			<td>Etudiant<input type="radio" name="role" value="3"/></td>
		</tr>
		
		<tr>
			<td>Nom</td>
			<td><input type="text" name="nom" value="${currentUser.nom}"/></td>
		</tr>
		
		<tr>
			<td>Prenom</td>
			<td><input type="text" name="prenom" value="${currentUser.prenom}"/></td>
		</tr>
		
		<tr>
			<td>
			<button type="submit" name="action" value="Edit" formaction="./UserServlet?page=edit">Edit</button>
			</td>
		</tr>
	</table>
	</form>
	
</body>
</html>