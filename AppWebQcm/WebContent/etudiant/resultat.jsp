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
		
		<c:forEach items="${listquestions}"  var="q" varStatus="ctr">
		<c:set var="inc"  value="${inc + 1}" />
			<input type="hidden" name="q+${inc}" value="${q.id}">
			<h3>Question  <c:out value="${ctr.count}"/> : ${q.question}</h3> 
			<div>Indication</div> <div name="${q.id}">${q.indication}</div>
						
				<c:forEach items="${listdelistReponses.get(ctr.index)}" var="reponse" >
					
			 		${reponse.rep} : ${reponse.bonneRep} <br>

				</c:forEach>
				
		</c:forEach>		
	
		</c:if>
		
		
	</form>
<br><a href="./ChapitreEtudiantServlet?idChapitre=${chapitre.id}&action=qcm"><button type="submit">Réessayer</button></a>

<br><a href="./ChapitreEtudiantServlet?idModule=${module.id}"><button type="button"> Retour au chapitre </button></a>
 </div> 
</div>
</body>
</html>