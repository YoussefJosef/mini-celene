<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
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

<br><a href="./ModuleServlet"><button type="button"> Retour </button></a>
<form action="./ChapitreServlet" method="post"  id="myform" >
	
	<table class="table table-striped">
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
			<td><input type="number" name="scoreMin" min="1" max="199" value="${chapitre.scoreMin}">${attentionEntier}</td>
		</tr>
	    <tr>
			<td> </td>
			<td>Afficher les bonnes reponses aux etudiants
			<input type="checkbox" name="printAnswers" value="true" /><br>
		</tr>
		<tr>
			<td>
			<button class="btn btn-success" type="submit" name="action" value="Add" formaction="./ChapitreServlet?idModule=${idMS}" form="myform">Ajouter le chapitre</button>
			
			</td>
		</tr>
	</table>
	<p>${attention}</p>


<c:choose>
    <c:when test="${empty allChapitres}">
        Aucun chapitre disponible .
    </c:when>
    <c:otherwise>
        <table class="table table-striped">
			<tr>
				<th>Nom du chapitre </th>
				<th>Cours </th>
				<th>Score min </th>
				<th>Afficher le score et les bonnes reponses </th>
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
    </c:otherwise>
</c:choose>
	
</form>

	 </div> 
</div>
</body>
</html>