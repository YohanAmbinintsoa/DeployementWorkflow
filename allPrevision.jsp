<%-- 
    Document   : allPrevision
    Created on : 15 juin 2023, 07:18:59
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Prevision,java.util.Vector" %>
<%
        Vector<Prevision> prev=Prevision.getAllPrevision();
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="testController" method="POST">
            <select name="idPrev">
                <% for(Prevision p: prev) { %>
                <option value="<%=p.getIdPrevision() %>"><%=p.getIdPrevision() %></option>
                <% } %>
            </select>
            <input type="submit" value="Valider">
        </form>
    </body>
</html>
