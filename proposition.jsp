<%@page import="java.util.*,model.Prevision" %>
<%
        Prevision prev=(Prevision)  request.getAttribute("prevision");
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proposition</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/proposition.css">
</head>
<body>
    <div class="mainDiv container text-center">
        <div class="formContainer">
            <table class="table">
                <thead>
                    <th>Id</th>
                    <th>Bateau</th>
                    <th>Date Debut</th>
                    <th>Date Fin</th>
                    <th>Numero Quai</th>
                    <th>Montant</th>
                </thead>
                <tbody>
                    <tr>
                        <td><%=prev.getIdPrevision() %></td>
                        <td><%=prev.getIdBateau() %></td>
                        <td><%=prev.getDateArrivee() %></td>
                        <td><%=prev.getDateDepart() %></td>
                        <td><%=prev.getQuai().getIdQuai() %></td>
                        <td><%=prev.getMontant() %></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <a href="insertionBateau.jsp">Insertion Bateau</a>
        <a href="index.jsp">Proposition</a>
    </div>
    <script src="assets/js/bootstrap.bundle.js"></script>
</body>
</html>