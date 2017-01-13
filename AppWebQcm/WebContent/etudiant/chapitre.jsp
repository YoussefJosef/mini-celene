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
 

<h2> CHAPITRES DU MODULE</h2>
<form  method="post"  id="myform" >
	
	<h2>La liste des chapitres du module</h2>
	<c:choose>
    <c:when test="${empty allChapitres}">
        Aucun chapitre disponible
    </c:when>
    <c:otherwise>
        <table>
			<tr>
				<th></th>
			
			</tr>
		<c:forEach items="${allChapitres}" var="chapitre"  varStatus="loop" >
			<tr>
				<td>Chapitre  <c:out value="${loop.count}"/> : ${chapitre.titre} </td>
	
			 </tr>
		</c:forEach>
	</table>
	
	<br>
	
	<h3>Important : Chaque validation de chapitre vous donne acces au chapitre suivant !</h3>
	<h2>Vous avez acces aux chapitres suivant :</h2>
	<table>
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		<c:forEach items="${accesChapters}" var="chapitre"  varStatus="loopy" >
			<tr>
				<td>Chapitre  <c:out value="${loopy.count}"/> : ${chapitre.titre} </td>
				<td><button  class="btn btn-success" type="submit" name="action" value="cours" formaction="./ChapitreEtudiantServlet?idChapitre=${chapitre.id}&textChapitre=${chapitre.texte}" form="myform">Consulter le cours</button></td>
				<td><button class="btn btn-warning" type="submit" name="action" value="qcm" formaction="./ChapitreEtudiantServlet?idChapitre=${chapitre.id}" form="myform">Repondre au Questionnaire</button></td>
			<!--	<td><button type="submit" name="action" value="resultat" formaction="./QcmServlet?idChapitre=${chapitre.id}" form="myform">Resultat du chapitre</button></td> -->
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