<%-- 
    Document   : index
    Created on : 4-mag-2011, 10.20.27
    Author     : sp096226
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>INSERIMENTO FILM</h1>
        <h2>Richiesta di informazioni</h2>
        <form action="/Cinema-war/FilmServlet" method="POST">
            <h2> Scegli l'azione che vuoi effettuare:</h2>
            <p>Titolo Film da inserire: <input type="text" name="titolo">
                <input type="submit" name="submit" value="Inserisci">
            </p>
            <p>Cancella DB1
            <input type="submit" name="submit" value="Cancella"></p>
    </body>
</html>
