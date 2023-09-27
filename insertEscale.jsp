<%-- 
    Document   : insertEscale
    Created on : 21 juin 2023, 21:14:05
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.Bateau,model.Quai" %>
<%
    Vector<Bateau> bateau=Bateau.getAllBateau();
    Vector<Quai> allQuai=Quai.getAllQuai();
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inserer un escale!</h1>
        <form method="POST" action="insertEscale_controller">
            <select name="idBateau">
                <% for(Bateau bat: bateau) { %>
                <option value="<%=bat.getIdBateau() %>"><%=bat.getNom() %></option>
                <% } %>
            </select>
             <select name="idQuai">
                <% for(Quai q: allQuai) { %>
                <option value="<%=q.getIdQuai() %>"><%=q.getNom() %></option>
                <% } %>
            </select>
            <label>Date Arrivee</label>
            <input type="datetime-local" name="dateDebut">
            <input  type="submit" value="OK">
        </form>
            <a href="allEscale.jsp">Toutes les escales</a>
    </body>
</html>
