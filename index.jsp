<%@page import="java.util.*,model.Bateau" %>
<%
        Vector<Bateau> bateau=Bateau.getAllBateau();
        String message="";
        if(request.getAttribute("message")!=null){         
            message=(String)request.getAttribute("message"); 
%>
            <script>alert('<%=message %>')</script>
 <%           
        }
 %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prevision</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/prevision.css">
</head>
<body>
    <div class="mainDiv container text-center">
        <h1>Prevision</h1>
        <div class="formContainer">
            <form action="insertPrevision_controller" method="post">
                <div class="form-group">
                   <label>Liste des bateaux</label>
                   <select name="idBateau" id="">
                       <% for (Bateau bat : bateau ) { %>
                       <option value="<%=bat.getIdBateau() %>"><%=bat.getNom() %></option>
                        <% } %>
                   </select> 
                </div>
                <div class="form-group">
                    <label>Date Arrivee</label>
                    <input type="datetime-local" name="dateArrivee" id="">
                 </div>
                 <div class="form-group">
                    <label>Date Depart</label>
                    <input type="datetime-local" name="dateFin" id="">
                 </div>
                 <input type="submit" value="Valider">
            </form>
        </div>
                        <div class="message"><%=message %></div>
        <a href="insertionBateau.jsp">Insertion Bateau</a>
        <a href="allPrevision.jsp">Toutes les previsions</a>
        <a href="newjspseePrevision.jsp">Prevision par quai</a>
        <a href="selectProfil.jsp">Choisir profil</a>
        <a href="ListeEscale.jsp">Liste des escales</a>
    </div>
    <script src="assets/js/bootstrap.bundle.js"></script>
</body>
</html>