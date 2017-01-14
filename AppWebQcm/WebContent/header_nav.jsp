<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
     
	<c:choose>
    	<c:when  test="${sessionScope.role == '1'}">
   
    <a class="navbar-brand" href="./AuthServlet">Espace Administrateur</a>
    
  		</c:when>
  		<c:when  test="${sessionScope.role == '2'}">
   
   <a class="navbar-brand" href="./AuthServlet">Espace Enseignant</a>
    
  		</c:when>
  		<c:when  test="${sessionScope.role == '3'}">
   
   <a class="navbar-brand" href="/.AuthServlet">Espace Etudiant</a>
    
  		</c:when>

 		 <c:otherwise>
 		 <a class="navbar-brand" href="#">mini-Celene</a>
  		</c:otherwise>
  </c:choose>
                
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
            
                <ul class="nav navbar-nav navbar-right navbar-user">
                   
                     
    <c:if test="${sessionScope.login != null}">
    <font color="white">${sessionScope.login} est connecté(e)</font>
	</c:if>
	<c:choose>
  <c:when  test="${sessionScope.login != null}">
   
    <a href="AuthServlet?logout=1"><button type="button" class="btn btn-danger"> Se déconnecter </button> </a>
    
  </c:when>

  <c:otherwise>
  <li class="dropdown user-dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Connexion  <b class="caret"></b></a>
   
                  <ul class="dropdown-menu">
                     <li><a href="AuthServlet"><i class="fa fa-user"></i> Se connecter </a></li>
                     
                      <li class="divider"></li>
                 </ul>
                 </li>
  </c:otherwise>
</c:choose>
   
                    
                    
                </ul>
            </div>
</nav>
