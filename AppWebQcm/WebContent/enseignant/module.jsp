<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>ESPACE ENSEIGNANT</h1>
<h2>GESTION MODULE</h2>
<br><a href="AuthServlet?logout=1"><button type="button"> Se déconnecter </button></a>
<form action="./ModuleServlet" method="post"  id="myform" >
	
	<table>

		<tr>
			<td>titre :</td>
			<td><input type="text" name="nom" value="${module.nom}"/></td>
		</tr>
		
		<tr>
			<td>
			<input type="submit" name="action" value="Add" />
			</td>
		</tr>
	</table>

	<table>
			<tr>
				<th>ID :</th>
				<th>NOM : </th>
				<th>user</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		<c:forEach items="${allModules}" var="module" >
			<tr>
				<td>${module.id} </td>
				<td>${module.nom}</td>
				<td>${module.user}</td>
				<td><button type="submit" name="action" value="Edit" formaction="./ModuleServlet?id=${module.id}&page=module" form="myform">Modifier le titre du module</button></td>
				<td><button type="submit" name="action" value="Delete" formaction="./ModuleServlet?id=${module.id}" form="myform">Supprimer </button></td>
				<td><button type="submit" name="action" value="Edit" formaction="./ChapitreServlet?idModule=${module.id}&page=module&action=module" form="myform">Gerer les chapitres du module</button></td>
			 </tr>
		</c:forEach>
	</table>
</form>
</body>
</html>