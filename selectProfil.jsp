<%-- 
    Document   : selectProfil
    Created on : 21 juin 2023, 20:52:47
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.Profil" %>
<%
    Vector<Profil> allProfil=Profil.getAllProfil();
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Selectionner le Profil!</h1>
        <form method="POST" action="profil_controller">
            <select name="profil">
        <% for(Profil prof : allProfil) { %>
            <option value="<%=prof.getIdProfil() %>"><%=prof.getNom() %></option>
        <% } %>
        </select>
        <input type="submit" value="OK">
        </form>
        
    </body>
</html>
