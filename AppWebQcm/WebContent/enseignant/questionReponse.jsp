<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
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
<script type="text/javascript">
function create_champ(i) {
var i2 = i + 1;
var num = String(i);
nouvelleReponse = document.createElement("tr");
nouvelleReponse.innerHTML = '<td>Reponse : </td><td><input id="reponse'+i+'" type="text" name="reponse'+i+'"/></td>';
nouvelleBonneReponse = document.createElement("tr");
nouvelleBonneReponse.innerHTML = '<td>Bonne Reponse</td><td><input type="checkbox" name="bonneReponse'+i+'" value="vrai"/></td>';
document.getElementById('leschamps').insertAdjacentElement('beforebegin', nouvelleReponse);
document.getElementById('leschamps').insertAdjacentElement('beforebegin', nouvelleBonneReponse);
document.getElementById('leschamps').innerHTML = '<td><a href="javascript:create_champ('+i2+')">Ajouter une autre reponse </a></td>';
var a = document.getElementById('numReponse');
var v = parseInt(a.getAttribute("value"))+1;
a.setAttribute("value", v);

var c = "\${questionReponse.reponse"+String(i)+"}";
nouvelleReponse.setAttribute("value", c);
}
</script>

</head>
<body>
<div id="wrapper">
<%@ include file="../header_nav.jsp" %>
 <div id="page-wrapper">
<h2>GESTION QUESTIONNAIRE</h2>
<br><a href="./ChapitreServlet"><button type="button"> Retour </button></a>
<form action="./QuestionReponseServlet" method="post"  id="myform" >

	
	<table class="table table-striped">
		
		<tr>
			<td>Question  </td>
			<td><input type="text" name="question" value="${questionReponse.question}"/></td>
		</tr>
		<tr>
			<td>Indication (Facultatif) </td>
			<td><input type="text" name="indication" value="${questionReponse.indication}"/></td>
		</tr>
		<tr>
			<td>Score  </td>
			<td><input id="score" type="number"  min="1" max="199" name="score" value="${questionReponse.score}"/>${attentionEntier}</td>
		</tr>
		<tr>
			<td><input type="hidden" id="numReponse" name="numReponse" value="1"/></td>
		</tr>
		<tr>
			<td>Reponses  </td>
			<td><input id="reponse0" type="text" name="reponse0" value="${questionReponse.reponse0}"/></td>
		</tr>
		<tr>
			<td>Bonne reponse </td>
			<td><input type="checkbox" name="bonneReponse0" value="vrai"/></td>
		</tr>
		<tr id="leschamps">
			<td><a href="javascript:create_champ(1)">Ajouter une autre reponse </a></td>
		</tr>
		<tr>
			<td>
			<button class="btn btn-success" type="submit" name="action" value="Add" formaction="./QuestionReponseServlet?idChapitre=${idC}" form="myform">Soumettre</button>
			
			</td>
		</tr>
	</table>
	<p>${attention}</p>

<c:choose>
    <c:when test="${empty allQuestionReponses}">
        Aucun Qcm disponible.
    </c:when>
    <c:otherwise>
       <table class="table table-striped">
			<tr>
				<th>Question </th>
				<th>Indication </th>
				<th>Reponse  </th>
				<th>Score </th>
				<th></th>
			</tr>
		<c:forEach items="${allQuestionReponses}" var="qr" >
			<tr>
				<td>${qr.question} </td>
				<td>${qr.indication}</td>
				<td>${qr.reponse}</td>
				<td>${qr.score}</td>
				<td><button type="submit" name="action" value="Delete" formaction="./QuestionReponseServlet?idQuestionReponse=${qr.id}" form="myform">Supprimer </button></td>
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
