<%@page contentType="text/html" pageEncoding="UTF-8" 
        import="twitter.TwitterPostSpike,
        twitter.twitteroauth.twitterresponse.*,
        com.sun.jersey.api.client.UniformInterfaceException" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Friends Statuses</h2>
        <%
            if (session.getAttribute("oauth_token") == null) {
                response.sendRedirect("OAuthLogin");
            } else {
                TwitterPostSpike twitter = new TwitterPostSpike("xml");
                twitter.initOAuth(request, response);
                try {
                    Statuses resp =
                        twitter.sendNewDirectMessageFromMe(Statuses.class,"Dave_27","Messaggio di prova da java" );
                    int i=0;
                    for (StatusType status : resp.getStatus()) {
                        out.println("<p>author: <b>"+status.getUser().getName()+
                                    "</b>("+status.getCreatedAt()+")</p>"+
                                    "<p><tt>"+status.getText()+"</tt></p>");
                    }
                } catch (UniformInterfaceException ex) {
                    System.out.println(
                         "Error = "+ex.getResponse().getEntity(String.class));
                }
                twitter.close();
            }
        %>
    </body>
</html>
