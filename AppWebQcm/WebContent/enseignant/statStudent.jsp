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

	<p>Résultat de ${login} dans le module ${module}</p>
	<form action="./StatistiqueServlet" method="post"  id="myform" >
		<table class="table table-striped">
			<tr>
				<th>Nom du chapitre</th>
				<th>Score </th>
				<th>Nombre d'essai</th>
				<th>Chapitre validé</th>
				<th>Date de validation</th>	
			</tr>
		<c:forEach items="${listResult}" var="result" >
			<tr>
				<td>${result.nomChapitre} </td>
				<td>${result.score}</td>
				<td>${result.nombreEssai}</td>
				<td>${result.validated}</td>
				<td>${result.dateValidation}</td>		
			</tr>
		</c:forEach>
	</table>
	<button type="submit" formaction="./StatistiqueServlet" form="myform">Retour</button>
	</form>
	
	 </div> 
</div>
</body>
</html>