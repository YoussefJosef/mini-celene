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
<br><a href="AuthServlet?logout=1"><button type="button"> Se déconnecter </button></a>
<h1>ESPACE ETUDIANT</h1>
<h1>INSCRIPTION AUX MODULES</h1>
	
	<form action="./InscriptionServlet" method="post"  id="myform" >
		<table>
				<tr>
					<th>Nom du module : </th>
					<th></th>
					
				</tr>
			<c:forEach items="${allModules}" var="module" >
				<tr>
					<td>${module.nom}</td>
					<td><button type="submit" name="action" value="Add" formaction="./InscriptionServlet?idModule=${module.id}" form="myform">S'inscrire au module.</button></td>
				 </tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>