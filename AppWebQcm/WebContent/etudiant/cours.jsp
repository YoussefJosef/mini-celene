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
<br><a href="./ChapitreEtudiantServlet?idModule=${module.id}"><button type="button"> Retour </button></a>
<h1>ESPACE ETUDIANT</h1>
<h1>CONSULTATION DE COURS</h1>

<h2>Module : ${module.nom} </h2>
<br>
<h3>Chapitre : ${chapitre.titre}</h3>
<br>
Cours : ${chapitre.texte}

<br>
<a href="./ChapitreEtudiantServlet?idChapitre=${chapitre.id}&action=qcm"><button type="submit">Repondre au Questionnaire</button></a>
	

</body>
</html>