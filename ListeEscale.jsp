<%-- 
    Document   : ListeEscale
    Created on : 25 juin 2023, 14:36:30
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.Escale" %>
<%
    Vector<Escale> allEscale=Escale.getAllEscale();
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste escale</title>
     <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    </head>
    <body>
        
        <div class="container">
            <h1>Les Escales</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>idEscale</th>
                        <th>Bateau</th>
                        <th>Debut</th>
                        <th>Fin</th>
                    </tr>
                </thead>
                <tbody>
                    <%  for (Escale esc : allEscale) { %>
                    <tr>
                        <td><%=esc.getIdEscale() %></td>
                        <td><%=esc.getIdBateau() %></td>
                        <td><%=esc.getDateArrivee() %></td>
                        <td><%=esc.getDateFin() %></td>
                        <td><a href="ajoutPrestation.jsp?idescale=<%=esc.getIdEscale() %>">Ajouter prestation</a></td>
                        <td><a href="facturation.jsp">Facturation</a></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>

        </div>
    </body>
</html>
