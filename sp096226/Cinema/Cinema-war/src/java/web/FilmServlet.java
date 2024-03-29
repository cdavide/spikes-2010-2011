/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ejb.GestoreFilmLocal;
import ejb.Film;

/**
 *
 * @author Stefano
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
        int i;
        try {
            //t è il titolo del film appena inserito in index.jsp
            String t = request.getParameter("titolo");
            gestoreFilm.inserisciFilm(t);
            String listaFilms = gestoreFilm.listaFilm();
            
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FilmServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ECCO DOVE SI TROVA LA SERVLET:</h1>");
            out.println("<h1>Servlet FilmServlet at " + request.getContextPath () + "</h1>");
            out.println("<p>TITOLO DEL FILM INSERITO: "+ t +"");
            out.println("<p>LISTA FILM INSERITI: "+ listaFilms +"");
            out.println("</p>"); 
            out.println("</body>");
            out.println("</html>");

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
