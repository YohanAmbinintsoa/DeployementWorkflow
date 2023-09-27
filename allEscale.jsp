<%-- 
    Document   : allEscale
    Created on : 21 juin 2023, 22:21:58
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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Toutes les factures!</h1>
        <table border="1">
            <thead>
                <th>idEscale</th>
                <th>idBateau</th>
                <th>Date debut</th>
                <th>Date fin</th>
            </thead>
            <tbody>
                <% for(Escale esc : allEscale) { %>
                <tr>
                         <td><%=esc.getIdEscale() %></td>
                         <td><%=esc.getIdBateau() %></td>
                         <td><%=esc.getDateArrivee() %></td>
                          <td><%=esc.getDateFin() %></td>
                          <td><a href="">Prestations</a></td>
                          <td><a href="">Listes Factures</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
