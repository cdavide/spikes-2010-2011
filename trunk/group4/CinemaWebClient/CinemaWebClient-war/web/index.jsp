<%-- 
    Document   : AggiungiFilm
    Created on : 5-mag-2011, 12.15.12
    Author     : dave
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head><title>Pagina di Aggiunta Films</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>
  <body>
      <h2>Aggiungi Film Tramite Web Service</h2>
      <form  name="Invoke" action="ServletClient" METHOD="GET" />
      <input type="hidden" name="metodo" value="insertFilm">
      <p>Titolo: <input type="text" name="titolo"/> </p>
      <p>Regista: <input type="text" name="regista"></p>
      <p><input type="submit" name="submit" value="OK"></p>
      </form>
  </body>
</html>