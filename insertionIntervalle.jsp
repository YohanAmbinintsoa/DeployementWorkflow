<%@page import="java.util.Vector,model.Parametre" %>
<%
        Parametre param=Parametre.getParametre();
        Vector<float[]> intervalle=param.createIntervalle();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inserton Intervalle</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
</head>
<body>
    <div class="container text-center my-3">
        <form action="insertIntervalle" method="POST">
            <% for (float[] f:intervalle) {  %>
            <p><%=f[0] %> min  a  <%=f[1] %> min <input type="number" name="intervalle[]"></p>
            <input type="hidden" name="first[]" value="<%=f[0] %>">
            <input type="hidden" name="second[]" value="<%=f[1] %>">
            
            <% } %>
            <input type="submit" value="Valider">
        </form>
    </div>
    <script src="assets/js/bootstrap.bundle.js"></script>
</body>
</html>