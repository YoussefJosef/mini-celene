<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="AuthServlet?logout=1"><button type="button"> Se déconnecter </button></a>
<h1>ESPACE ENSEIGNANT</h1>
	<form action="./QcmServlet" method="post"  id="myform" >
		<c:forEach items="${listmodule}"  var="q" varStatus="ctr">
		<c:set var="inc"  value="${inc + 1}" />
			<input type="hidden" name="q+${inc}" value="${q.id}">
			<h3>Module  <c:out value="${ctr.count}"/> : ${q.nom}</h3> 
						
				<c:forEach items="${listdelistModule.get(ctr.index)}" var="reponse" >
					
					 ${reponse.user}${reponse.progression}
						 
						
				</c:forEach>
				
		</c:forEach>
		<br>
	</form>
</body>
</html>