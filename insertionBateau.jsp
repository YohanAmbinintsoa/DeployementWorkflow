<%@page import="java.util.*,model.Pavillon,model.TypeBateau" %>
<%
        Vector<Pavillon> pavillon=Pavillon.getAllPavillon();
        Vector<TypeBateau> type=TypeBateau.getAllTypeBateau();
        String message="";
        if(request.getAttribute("message")!=null){ 
            message=(String)request.getAttribute("message"); %>
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
    <title>Insertion Bateau</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/bateau.css">
</head>
<body>
    <div class="mainDiv container text-center">
        <h1>Insertion Bateau</h1>
        <div class="formContainer">
            <form action="insertBateau_Controller" method="post">
                <div class="form-group">
                    <label for="">Nom du bateau</label>
                    <input type="text" name="nom" id="">
                </div>
                <div class="form-group">
                    <label for="">Type du bateau</label>
                    
                    <select name="type" id="">
                        <% for(TypeBateau typeBat : type ) {  %>
                        <option value="<%=typeBat.getIdType() %>"><%=typeBat.getNom() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="">Pavillon</label>
                    <select name="pavillon" id="">
                        <% for(Pavillon pav : pavillon ) {  %>
                        <option value="<%=pav.getIdPavillon() %>"><%=pav.getNom() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="">Profondeur</label>
                    <input type="text" name="profondeur" id="">
                </div>
                <div class="form-group">
                    <label for="">Duree remorquage</label>
                    <input type="text" name="remorquage" id="">
                </div>
                <input type="submit" value="Inserer">
            </form>
        </div>
                    <a href="index.jsp">Prevision</a>
                        <div class="message"><%=message %></div>
    </div>
    <script src="assets/js/bootstrap.bundle.js"></script>
</body>
</html>