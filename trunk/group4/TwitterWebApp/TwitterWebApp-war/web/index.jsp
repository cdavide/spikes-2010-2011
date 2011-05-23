<%@page contentType="text/html" pageEncoding="UTF-8" 
        import="twitter.TweetFromJava,
        twitter.twitteroauth.twitterresponse.*,
        com.sun.jersey.api.client.UniformInterfaceException" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Message posting</h2>
        <form  name="Invoke" action="TwitterServlet" METHOD="GET" />
        <input type="hidden" name="metodo" value="newTweet">
        <p>Tweet <input type="text" name="tweet"/> </p>
        <p>Status id: <input type="text" name="status_id"></p>
        <p><input type="submit" name="submit" value="OK"></p>
        </form>
        <h2>Oauth Authentication</h2>
        <form  name="Invoke" action="TwitterServlet" METHOD="GET" />
        <input type="hidden" name="metodo" value="getPermissions">
        <p><input type="submit" name="submit" value="Oauth Authentication"></p>
        </form>

    </body>
</html>
