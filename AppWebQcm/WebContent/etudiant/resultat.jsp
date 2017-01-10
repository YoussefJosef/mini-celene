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
<h1>RESULTAT QCM DU CHAPITRE</h1>
	<h1>idM : ${idMS}</h1>
	<p> ${messageInformation} </br></p>
	<form action="./InscriptionServlet" method="post"  id="myform" >
		<table>
				<tr>
					<th>chapitre :</th>
					<th>score </th>
					<th> date valdiation</th>
					<th>nombre essai</th>
					<th></th>
				</tr>
			
				<tr>
					<td>${chapitre} </td>
					<td>${resultatChapitre.score} </td>
					<td>${resultatChapitre.dateValidation} </td>
					<td>${resultatChapitre.nombreEssai} </td>	 </tr>
			
		</table>
	</form>

</body>
</html>