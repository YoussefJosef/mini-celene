<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Modules Information</h1>
	<form action="./ModuleServlet" method="post" id="myform">
	<table>
		<tr>
			<td>titre</td>
			<td><input type="text" name="titre" value="${currentChapitre.titre}"/></td>
		</tr>
		<tr>
			<td>texte</td>
			<td><input type="text" name="texte" value="${currentChapitre.texte}"/></td>
		</tr>
		<tr>
			<td>niveau</td>
			<td><input type="text" name="niveau" value="${currentChapitre.niveau}"/></td>
		</tr>
		<tr>
			<td>score min</td>
			<td><input type="text" name="scoreMin" value="${currentChapitre.scoreMin}"/></td>
		</tr>
	
		<tr>
			<td>
			<button type="submit" name="action" value="Edit" formaction="./ChapitreServlet?idChapitre=${currentChapitre.id}&page=edit">Edit</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>