<%-- 
    Document   : VisualizarRevistas
    Created on : 12 sept 2024, 23:39:01
    Author     : kevin-mushin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.Revista"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <jsp:include page="/includes/resources.jsp"/>

        <title> Revistas </title>
    </head>
<body>
    <c:if test="${empty revistasAsociadas}">
        <div class="alert alert-info text-center">AUN NO HAS PUBLICADO REVISTAS</div>
    </c:if>
        
        <!-- establecer carrusel dinamic -->
        <div class="container mt-5">
            
            <div class="d-grid" >
                      <button type="button" class="btn btn-primary" onclick="history.back()">Regresar</button>
            </div>
            <br>
            
            <c:if test="${not empty revistasAsociadas}">
                <c:if test="${usuario.rol == 'EDITOR'}">
                    <h2 class="text-center"> Tus publicaciones de revistas </h2>
                    <h4 class="text-center"> Numero de publicaciones: <c:out value="${fn: length(revistasAsociadas)}" /> </h4>
                </c:if>
                    
                <c:if test="${usuario.rol == 'SUSCRIPTOR'}">
                    <h2 class="text-center"> REVISTA</h2>
                </c:if>
            </c:if>
                
            <div id="revistaCarrusel" class="carousel slide" data-ride="carousel" data-interval="false">
                <div class="carousel-inner">

                    <c:if test="${not empty revistasAsociadas}">

                    <c:forEach var="revista" items="${revistasAsociadas}" varStatus="status">
                                    <!-- para cada revista asociasd -->
                                    <div class="carousel-item ${status.first ? 'active' : ''}" id="${revista.idRevista}" data-nombre="${revista.tituloRevista}">

                                        <img src="${pageContext.request.contextPath}/includes/EDITMOSQUITO.webp" class="d-block mx-auto w-50" alt="Editorial Mosquito">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5 class="bg-primary text-white d-inline-block p-1">${revista.tituloRevista}</h5>
                                        </div>
                                    </div>
                        </c:forEach>
                    </c:if>  
                </div>
            
                <!-- controles de navegacion -->
            <a class="carousel-control-prev ms-0" href="#revistaCarrusel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true" style="background-color: blue"></span>
            </a>

            <a class="carousel-control-next ms-0" href="#revistaCarrusel" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true" style="background-color: blue"></span>
            </a>
 
             <jsp:include page="/includes/OpcionesRevistasRol.jsp"/>
          </div>
        </div>
                
</body>
</html>
