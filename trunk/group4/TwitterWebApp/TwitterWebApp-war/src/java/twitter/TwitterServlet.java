/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter.TweetFromJava.*;
import twitter.twitteroauth.twitterresponse.*;
import com.sun.jersey.api.client.UniformInterfaceException;

/**
 *
 * @author Davide Concas
 */
@WebServlet(name = "TwitterServlet", urlPatterns = {"/TwitterServlet"})
public class TwitterServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Twitter form poster </title>");  
            out.println("</head>");
            out.println("<body>");
            String formAction = request.getParameter("metodo");
            out.println(formAction);
            if (formAction.equals("getPermissions")) {
                response.sendRedirect("OAuthLogin");
                out.println("</body>");
                out.println("</html>");
            }
            else if (formAction.equals("newTweet")) {
                TweetFromJava twitter = new TweetFromJava("xml");
                twitter.initOAuth(request, response);
                try {
                    
                    Statuses resp =
                        twitter.updateStatus (Statuses.class, request.getParameter("tweet"), request.getParameter("status_id"));
                } catch (UniformInterfaceException ex) {
                    System.out.println(
                         "Error = "+ex.getResponse().getEntity(String.class));
                }
                twitter.close();
                out.print("Messaggio di stato impostato correttamente a:"+request.getParameter("tweet"));
                out.println("</body>");
                out.println("</html>");
            }
            
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
