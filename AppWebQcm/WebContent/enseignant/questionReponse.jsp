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
document.getElementById('leschamps_'+i).innerHTML = '<tr><td>Reponse : </td><td><input type="text" name="reponse'+i+'" value="${questionReponse.reponse'+i+'}"/></td></tr><tr><td>Bonne Reponse</td><td><input type="checkbox" name="bonneReponse'+i+'" value="'+i+'"/></td></tr><tr id="leschamps_'+i2+'"><td><a href="javascript:create_champ('+i2+')">Ajouter une reponse</a></td></tr>';
var a = document.getElementById('numReponse');
var v = parseInt(a.getAttribute("value"))+1;
c.setAttribute("numReponse", v);
}
</script>

</head>
<body>
<h1>ESPACE ENSEIGNANT</h1>
<h2>GESTION QUESTIONNAIRE</h2>
<br><a href="AuthServlet?logout=1"> Se déconnecter </a>
<form action="./QuestionReponseServlet" method="post"  id="myform" >

	
	<table>
		
		<tr>
			<td>Question : </td>
			<td><input type="text" name="question" value="${questionReponse.question}"/></td>
		</tr>
		<tr>
			<td><input type="hidden" name="numReponse" value="1"/></td>
		</tr>
		<tr>
			<td>Reponse : </td>
			<td><input type="text" name="reponse0" value="${questionReponse.reponse0}"/></td>
		</tr>
		<tr>
			<td>Bonne Reponse</td>
			<td><input type="checkbox" name="bonneReponse0" value="0"/></td>
		</tr>
		<tr id="leschamps_1">
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
				<th>Bonne Reponse :</th>
				<th></th>
				<th></th>
			</tr>
		<c:forEach items="${allQuestionReponses}" var="qr" >
			<tr>
				<td>${qr.question} </td>
				<td>${qr.reponse}</td>
				<td>${qr.bonneReponse}</td>
				<td><button type="submit" name="action" value="Edit" formaction="./QuestionReponseServlet?idQuestionReponse=${qr.id}&page=module" form="myform">Modifier</button></td>
				<td><button type="submit" name="action" value="Delete" formaction="./QuestionReponseServlet?idQuestionReponse=${qr.id}" form="myform">Supprimer </button></td>
			    </tr>
		</c:forEach>
	</table>



</form> 

</body>
</html>