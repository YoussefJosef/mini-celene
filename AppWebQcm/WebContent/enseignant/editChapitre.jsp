<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()
%>/resources/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()
%>/resources/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()
%>/resources/css/local.css" />

    <script type="text/javascript" src="<%=request.getContextPath()
%>/resources/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()
%>/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div id="wrapper">
<%@ include file="../header_nav.jsp" %>
 <div id="page-wrapper">

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
			<td><input type="number" name="scoreMin" value="${currentChapitre.scoreMin}"/>${attentionEntier}</td>
		</tr>
	  <tr>
			<td> </td>
			<td>Afficher les bonnes reponses aux etudiants
			<input type="checkbox" name="printAnswers" value="true" /><br>
		</tr>
		<tr>
			<td>
			<button type="submit" name="action" value="Edit" formaction="./ChapitreServlet?idChapitre=${currentChapitre.id}&page=edit">Mettre a jour</button>
			</td>
		</tr>
	</table>
	<p>${attention}</p>
	</form>
	
	 </div> 
</div>
</body>
</html>