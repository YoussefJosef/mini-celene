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
<h1>ESPACE ETUDIANT</h1>
<h2> CHAPITRES DU MODULE</h2>
<br><a href="AuthServlet?logout=1"><button type="button"> Se déconnecter </button></a>
<form  method="post"  id="myform" >
	
	<h2>La liste des chapitres du module</h2>
	<table>
			<tr>
				<th>Titre :</th>
				<th>Niveau :</th>
			
			</tr>
		<c:forEach items="${allChapitres}" var="chapitre" >
			<tr>
				<td>${chapitre.titre} </td>
				<td>${chapitre.niveau}</td>
	
			 </tr>
		</c:forEach>
	</table>
	<br>
	
	<h3>Chaque validation du chapitre vous donne acces au chapitre suivant !</h3>
	<h2>Vous avez acces aux chapitres suivant :</h2>
	<table>
			<tr>
				<th>Titre :</th>
				<th>Niveau :</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		<c:forEach items="${accesChapters}" var="chapitre" >
			<tr>
				<td>${chapitre.titre} </td>
				<td>${chapitre.niveau}</td>
				<td><button type="submit" name="action" value="cours" formaction="./ChapitreEtudiantServlet?idChapitre=${chapitre.id}&textChapitre=${chapitre.texte}" form="myform">Voir cours</button></td>
				<td><button type="submit" name="action" value="qcm" formaction="./ChapitreEtudiantServlet?idChapitre=${chapitre.id}" form="myform">Repondre au Qcm</button></td>
				<td><button type="submit" name="action" value="resultat" formaction="./QcmServlet?idChapitre=${chapitre.id}" form="myform">Resultat</button></td>
			 </tr>
		</c:forEach>
	</table>
</form>
</body>
</html>