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
<h2>RESULTAT QCM DU CHAPITRE</h2>
	<p> ${messageInformation} </p></br>
	
	<form action="./InscriptionServlet" method="post"  id="myform" >
		<table>
				<tr>
					<th>Chapitre </th>
					<th>Votre score </th>
					<th>Nombre d'essai</th>
				</tr>
			
				<tr>
					<td>${chapitre.titre} </td>
					<td>${resultatChapitre.score} </td>
					<td>${resultatChapitre.nombreEssai} </td>	
			 	</tr>
		</table>
		
		<c:if test="${printAnswers}">
		<h3>Les reponses : </h3>
			<table>
					<tr>
						<th>Question </th>
						<th>Indication </th>
						<th>Reponse  </th>
						
					</tr>
				<c:forEach items="${listQRD}" var="qr" >
					<tr>
						<td>${qr.question} </td>
						<td>${qr.indication}</td>
						<td>${qr.reponse}</td>
						
				    </tr>
				</c:forEach>
			</table>
		</c:if>
		
		
	</form>
<br><a href="./ChapitreEtudiantServlet?idChapitre=${chapitre.id}&action=qcm"><button type="submit">Réessayer</button></a>

<br><a href="./ChapitreEtudiantServlet?idModule=${module.id}"><button type="button"> Retour au chapitre </button></a>

</body>
</html>