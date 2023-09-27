<%@page import="java.util.*,model.Quai" %>
<%
    Vector<Quai> allQuai=Quai.getAllQuai();
 %>
<%-- 
    Document   : newjspseePrevision
    Created on : 16 juin 2023, 09:50:44
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="POST" action="listPrevision_Controller">
            <select name="idQuai">
                <% for(Quai q:allQuai) { %>
                    <option value="<%=q.getIdQuai() %>"><%=q.getNom() %></option>
                <% } %>
            </select>
            <input type="submit" value="Valider">
        </form>
    </body>
</html>
