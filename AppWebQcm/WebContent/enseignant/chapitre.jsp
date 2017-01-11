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
<h2>GESTION CHAPITRE</h2>
<br><a href="AuthServlet?logout=1"><button type="button"> Se déconnecter </button></a>
<br><a href="./ModuleServlet"><button type="button"> Retour </button></a>
<form action="./ChapitreServlet" method="post"  id="myform" >
	
	<table>
		<tr>
			<td>Nom du chapitre </td>
			<td><input type="text" name="titre" value="${chapitre.titre}"/></td>
		</tr>
		<tr>
			<td>Cours </td>
			<td><textarea rows="4" cols="50" name="texte" value="${chapitre.texte}"></textarea></td>
		</tr>
		<tr>
			<td>Score min  </td>
			<td><input type="text" name="scoreMin" value="${chapitre.scoreMin}"/></td>
		</tr>
	    <tr>
			<td> </td>
			<td>Afficher seulement le score aux etudiants
			<input type="radio" name="printAnswers" value="false" checked /><br>
			Afficher le score et les bonnes reponses
			<input type="radio" name="printAnswers" value="true"/></td>
		</tr>
		<tr>
			<td>
			<button type="submit" name="action" value="Add" formaction="./ChapitreServlet?idModule=${idMS}" form="myform">Ajouter le chapitre</button>
			
			</td>
		</tr>
	</table>

	<table>
			<tr>
				<th>Nom du chapitre :</th>
				<th>Cours : </th>
				<th>Score min :</th>
				<th>Afficher le score et les bonnes reponses :</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		<c:forEach items="${allChapitres}" var="chapitre" >
			<tr>
				<td>${chapitre.titre} </td>
				<td>${chapitre.texte}</td>
				<td>${chapitre.scoreMin}</td>
				<td>${chapitre.printAnswers}</td>
				<td><button type="submit" name="action" value="Edit" formaction="./ChapitreServlet?idChapitre=${chapitre.id}&page=chapitre" form="myform">Modifier le chapitre</button></td>
				<td><button type="submit" name="action" value="Delete" formaction="./ChapitreServlet?idChapitre=${chapitre.id}" form="myform">Supprimer le chapitre </button></td>
				<td><button type="submit" name="action" value="Edit" formaction="./QuestionReponseServlet?idChapitre=${chapitre.id}&page=chapitre&action=chapitre" form="myform">Gerer le QCM</button></td>
			 </tr>
		</c:forEach>
	</table>
</form>
</body>
</html>