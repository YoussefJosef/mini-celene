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
<a href="AuthServlet?logout=1"><button type="button"> Se déconnecter </button></a>
<h1>ESPACE ENSEIGNANT</h1>

	<p>Résultat de ${login} dans le module ${module}</p>
	<form action="./StatistiqueServlet" method="post"  id="myform" >
		<table>
			<tr>
				<th>Nom du chapitre</th>
				<th>Score </th>
				<th>Nombre d'essai</th>
				<th>Chapitre validé</th>
				<th>Date de validation</th>	
			</tr>
		<c:forEach items="${listResult}" var="result" >
			<tr>
				<td>${result.nomChapitre} </td>
				<td>${result.score}</td>
				<td>${result.nombreEssai}</td>
				<td>${result.validated}</td>
				<td>${result.dateValidation}</td>		
			</tr>
		</c:forEach>
	</table>
	<button type="submit" formaction="./StatistiqueServlet" form="myform">Retour</button>
	</form>
</body>
</html>