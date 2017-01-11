<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $(".indic").click(function(){
        $(".indica").toggle();
    });
});
</script>
</head>
<body>
<a href="AuthServlet?logout=1"><button type="button"> Se déconnecter </button></a>
<h1>ESPACE ETUDIANT</h1>
<h1>QUESTIONNAIRE A CHOIX MULTIPLE</h1>
	<p>${messageInformation}</p>
	<form action="./QcmServlet" method="post"  id="myform" >
		<c:forEach items="${listquestions}"  var="q" varStatus="ctr">
		<c:set var="inc"  value="${inc + 1}" />
			<input type="hidden" name="q+${inc}" value="${q.id}">
			<h3>Question  <c:out value="${ctr.count}"/> : ${q.question}</h3> 
			<div class="indic">Indication(cliquer ici)</div> <div class="indica"  style="display: none;">${q.indication}</div>
						
				<c:forEach items="${listdelistReponses.get(ctr.index)}" var="reponse" >
					
					 <input type="radio" name="${inc}" value="${reponse.rep}" checked> ${reponse.rep} <br>

				</c:forEach>
				
		</c:forEach>
		<br>
		<button type="submit" name="action" value="verify" formaction="./QcmServlet" form="myform">Soumettre </button>		
	</form>
</body>
</html>