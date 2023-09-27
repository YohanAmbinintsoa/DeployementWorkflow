<%-- 
    Document   : allQuaiPrevision
    Created on : 16 juin 2023, 09:58:53
    Author     : DELL
--%>
<%@page import="java.util.*,model.Prevision" %>
<%
    Vector<Prevision> allPrev=(Vector<Prevision>)request.getAttribute("list");
    
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <thead>
                <th>IdPrevision</th>
                <th>dateArrivee</th>
                <th>dateDepart</th>
            </thead>
            <tbody>
                <% for(Prevision prev:allPrev) { %>
                <tr>
                    <td><%=prev.getIdPrevision() %></td>
                    <td><%=prev.getDateArrivee() %></td>
                    <td><%=prev.getDateDepart() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        
    </body>
</html>
