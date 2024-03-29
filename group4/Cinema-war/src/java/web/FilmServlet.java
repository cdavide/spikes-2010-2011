/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.FilmFacade;
import ejb.GestoreFilmLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/* per completare lo spike creare aggiungifilm, dalla servlet invocare gestorefilm 
 * (session bean stateless) magari faceno una form per inserire un nuovo film
 * 
 
 
 */
/**
 *
 * @author dave
 */
@WebServlet(name = "FilmServlet", urlPatterns = {"/FilmServlet"})
public class FilmServlet extends HttpServlet {
    @EJB
    private GestoreFilmLocal gestoreFilm;

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
            out.println("questo non lo stampa");
            out.println("<html>");
            
            out.println("<head>");
            out.println("<title>Servlet FilmServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            String formAction = request.getParameter("metodo");
            out.println(formAction);
            if (formAction.equals("insertFilm")) {
                gestoreFilm.addFilm(request.getParameter("titolo"), request.getParameter("regista"));
                
                out.println("Film inserito correttamente");
                out.println("Lista Film Inseriti:");
            out.println(gestoreFilm.listFilm());
            out.println("</body>");
            out.println("</html>");
            }
            else if (formAction.equals("lista")) {
                out.println(gestoreFilm.listFilm());
                
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
