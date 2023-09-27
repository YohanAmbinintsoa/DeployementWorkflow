<%-- 
    Document   : ajoutPrestation
    Created on : 22 juin 2023, 06:08:20
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.util.Vector,model.Prestation,model.Escale" %>
<%
    Escale esc=Escale.getEscaleById(request.getParameter("idescale"),null);
    Vector<Prestation> prest=Prestation.getAllPrestation();
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Ajouter prestations</h1>
            <% if(esc.getPrestation().size()!=0) {  %>
                <table class="table">
                <thead>
                    <tr>
                        <th>idPrestation</th>
                        <th>nom</th>
                        <th>Debut</th>
                        <th>Fin</th>
                    </tr>
                </thead>
                <tbody>
                    <%  for (Prestation p:esc.getPrestation()) { %>
                    <tr>
                            <td><%=p.getIdPrestation() %></td>
                            <td><%=p.getNom() %></td>
                            <td><%=p.getDateDebut() %></td>
                            <td><%=p.getDateFin() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } else { %>
            <h3>Aucune prestation</h3>
            <% } %>
            <form method="POST" action="insertPrestation_controller">
                <input type="hidden" value="<%=request.getParameter("idescale") %>" name="idescale">
                <input type="hidden" value="<%=esc.getIdQuai() %>" name="idquai">
                <input type="hidden" value="<%=esc.getIdBateau() %>" name="idbateau">
                <select name="idprestation">
                    <% for(Prestation prestation:prest ) { %>
                        <option value="<%=prestation.getIdPrestation() %>"><%=prestation.getNom() %></option>
                    <% } %>
                </select>
                <label>Date Debut</label>
                <input type="datetime-local" name="debut">
                <label>Date Fin</label>
                <input type="datetime-local" name="fin">
                <input type="submit" value="Inserer">
            </form>
        </div>
    </body>
</html>
