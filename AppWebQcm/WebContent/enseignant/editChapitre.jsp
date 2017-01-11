<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>ESPACE ENSEIGNANT</h1>
<h2>GESTION CHAPITRE</h2>
<h3>EDITION</h3>
	<form action="./ModuleServlet" method="post" id="myform">
	<table>
		<tr>
			<td>Nom de chapitre</td>
			<td><input type="text" name="titre" value="${currentChapitre.titre}"/></td>
		</tr>
		<tr>
			<td>Cours :</td>
			<td><input type="text" name="texte" value="${currentChapitre.texte}"/></td>
		</tr>
		<tr>
			<td>Score min :</td>
			<td><input type="text" name="scoreMin" value="${currentChapitre.scoreMin}"/></td>
		</tr>
	  <tr>
			<td> </td>
			<td>Afficher seulement le score aux etudiants
			<input type="radio" name="printAnswers" value="false" checked /><br>
			afficher le score et les bonnes reponses
			<input type="radio" name="printAnswers" value="true"/></td>
		</tr>
		<tr>
			<td>
			<button type="submit" name="action" value="Edit" formaction="./ChapitreServlet?idChapitre=${currentChapitre.id}&page=edit">Mettre a jour</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>