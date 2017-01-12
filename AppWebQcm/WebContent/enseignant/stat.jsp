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
	<form action="./StatistiqueServlet" method="post"  id="myform" >
		<table>
			<tr>
				<th>Nom du Module :</th>
				<th>Etudiant : </th>
				<th>Progression :</th>
				<th></th>
			</tr>
		<c:forEach items="${listInscrit}" var="inscrit" >
			<tr>
				<td>${inscrit.module} </td>
				<td>${inscrit.login}</td>
				<td>${inscrit.progression}</td>
				<td><button type="submit" name="action" value="detail" formaction="./StatistiqueServlet?idInscrit=${inscrit.login}&idModule=${inscrit.idModule}" form="myform">Détail sur l'étudiant</button></td>
			</tr>
		</c:forEach>
	</table>
	</form>
</body>
</html>