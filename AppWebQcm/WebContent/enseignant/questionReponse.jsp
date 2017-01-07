<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function create_champ(i) {
var i2 = i + 1;
var num = String(i);
nouvelleReponse = document.createElement("tr");
nouvelleReponse.innerHTML = '<td>Reponse : </td><td><input id="reponse'+i+'" type="text" name="reponse'+i+'"/></td>';
nouvelleBonneReponse = document.createElement("tr");
nouvelleBonneReponse.innerHTML = '<td>Bonne Reponse</td><td><input type="radio" name="bonneReponse'+i+'" value="vrai"/></td>';
document.getElementById('leschamps').insertAdjacentElement('beforebegin', nouvelleReponse);
document.getElementById('leschamps').insertAdjacentElement('beforebegin', nouvelleBonneReponse);
document.getElementById('leschamps').innerHTML = '<td><a href="javascript:create_champ('+i2+')">Ajouter une reponse</a></td>';
var a = document.getElementById('numReponse');
var v = parseInt(a.getAttribute("value"))+1;
a.setAttribute("value", v);

var c = "\${questionReponse.reponse"+String(i)+"}";
nouvelleReponse.setAttribute("value", c);

}
</script>

</head>
<body>
<h1>ESPACE ENSEIGNANT</h1>
<h2>GESTION QUESTIONNAIRE</h2>
<br><a href="AuthServlet?logout=1"><button type="button"> Se déconnecter </button></a>
<br><a href="./ChapitreServlet"><button type="button"> Retour </button></a>
<form action="./QuestionReponseServlet" method="post"  id="myform" >

	
	<table>
		
		<tr>
			<td>Question : </td>
			<td><input type="text" name="question" value="${questionReponse.question}"/></td>
		</tr>
		<tr>
			<td>Score : </td>
			<td><input id="score" type="text" name="score" value="${questionReponse.score}"/></td>
		</tr>
		<tr>
			<td><input type="hidden" id="numReponse" name="numReponse" value="1"/></td>
		</tr>
		<tr>
			<td>Reponse : </td>
			<td><input id="reponse0" type="text" name="reponse0" value="${questionReponse.reponse0}"/></td>
		</tr>
		<tr>
			<td>Bonne Reponse</td>
			<td><input type="radio" name="bonneReponse0" value="vrai"/></td>
		</tr>
		<tr id="leschamps">
			<td><a href="javascript:create_champ(1)">Ajouter une reponse</a></td>
		</tr>
		<tr>
			<td>
			<button type="submit" name="action" value="Add" formaction="./QuestionReponseServlet?idChapitre=${idC}" form="myform">Ajouter</button>
			
			</td>
		</tr>
	</table>

	<table>
			<tr>
				<th>Question :</th>
				<th>Reponse : </th>
				<th>Score</th>
				<th></th>
			</tr>
		<c:forEach items="${allQuestionReponses}" var="qr" >
			<tr>
				<td>${qr.question} </td>
				<td>${qr.reponse}</td>
				<td>${qr.score}</td>
				<td><button type="submit" name="action" value="Delete" formaction="./QuestionReponseServlet?idQuestionReponse=${qr.id}" form="myform">Supprimer </button></td>
		    </tr>
		</c:forEach>
	</table>

</form> 

</body>
</html>
