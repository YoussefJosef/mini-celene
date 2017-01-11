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
<h1>ESPACE ENSEIGNANT</h1>
<h2>GESTION MODULE</h2>
<h3>EDITION</h3>
	<form action="./ModuleServlet" method="post" id="myform">
	<table>
		<tr>
			<td>Nom du module</td>
			<td><input type="text" name="nom" value="${currentModule.nom}"/></td>
		</tr>
	
		<tr>
			<td>
			<button type="submit" name="action" value="Edit" formaction="./ModuleServlet?id=${currentModule.id}&page=edit">Mettre a jour</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>