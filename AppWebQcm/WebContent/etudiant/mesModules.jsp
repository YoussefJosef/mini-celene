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

<h1>LISTE DE MES MODULES</h1>
	
	<c:choose>
    <c:when test="${empty myModules}">
        Vous n'etes inscrit a aucun module.
    </c:when>
    <c:otherwise>
       <form action="./InscriptionServlet" method="post"  id="myform" >
	
		<table>
				<tr>
					<th>Nom du module </th>
					<th></th>
					<th></th>
				</tr>
			<c:forEach items="${myModules}" var="module" >
				<tr>
					<td><a href="ChapitreEtudiantServlet?idModule=${module.id}">${module.nom}</a></td>
					<td><button class="btn btn-danger" type="submit" name="action" value="Delete" formaction="./InscriptionServlet?idModule=${module.id}" form="myform">Se desinscrire du module </button></td>
				 </tr>
			</c:forEach>
		</table>
	</form>
    </c:otherwise>
</c:choose>
	
	<a class="btn btn-success" href="./InscriptionServlet?action=home">S'inscrire a un nouveau module </a>
 </div> 
</div>
</body>
</html>