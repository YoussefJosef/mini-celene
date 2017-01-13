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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
function modif_champ(i) {
	var div = document.getElementsByName(i)[0];
	var style = div.getAttribute("style");
	if (style == "display: none;") {
		document.getElementsByName(i)[0].setAttribute("style", "");
	}
	else {
		document.getElementsByName(i)[0].setAttribute("style", "display: none;");
	}
}
</script>
</head>
<body>


<div id="wrapper">
<%@ include file="../header_nav.jsp" %>
 <div id="page-wrapper">
 
<h1>QUESTIONNAIRE A CHOIX MULTIPLE</h1>
	<p>${messageInformation}</p>
	<c:choose>
    <c:when test="${empty listquestions}">
        Aucun qcm disponible.
    </c:when>
    <c:otherwise>
      <form action="./QcmServlet" method="post"  id="myform" >
		<c:forEach items="${listquestions}"  var="q" varStatus="ctr">
		<c:set var="inc"  value="${inc + 1}" />
			<input type="hidden" name="q+${inc}" value="${q.id}">
			<h3>Question  <c:out value="${ctr.count}"/> : ${q.question}</h3> 
			<div><a href="javascript:modif_champ(${q.id})">Indication</a></div> <div name="${q.id}"  style="display: none;">${q.indication}</div>
						
				<c:forEach items="${listdelistReponses.get(ctr.index)}" var="reponse" >
					
					 <input type="checkbox" name="r+${reponse.id}" value="${reponse.rep}"> ${reponse.rep} <br>

				</c:forEach>
				
		</c:forEach>
		<br>
		<button class="btn btn-success" type="submit" name="action" value="verify" formaction="./QcmServlet" form="myform">Soumettre </button>		
	</form>
    </c:otherwise>
</c:choose>
	 </div> 
</div>
</body>
</html>
	