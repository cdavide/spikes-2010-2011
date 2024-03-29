/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webReview;

import ejb.FilmWebService_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Davide Concas
 */
@WebServlet(name = "ServletClient", urlPatterns = {"/ServletClient"})
public class ServletClient extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/FilmWebService/FilmWebService.wsdl")
    private FilmWebService_Service service;

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
            out.println("<title>Servlet FilmServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            String formAction = request.getParameter("metodo");
            out.println(formAction);
            if (formAction.equals("insertFilm")) {
                addFilm(request.getParameter("titolo"), request.getParameter("regista"));
                
                out.println("Film inserito correttamente");
                out.println("Lista Film Inseriti:");
            out.println(listFilm());
            out.println("</body>");
            out.println("</html>");
            }
            else if (formAction.equals("lista")) {
                out.println(listFilm());
                
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

    private String listFilm() {
        ejb.FilmWebService port = service.getFilmWebServicePort();
        return port.listFilm();
    }
    private void addFilm(java.lang.String titolo, java.lang.String regista) {
        ejb.FilmWebService port = service.getFilmWebServicePort();
        port.addFilm(titolo, regista);
    }
}
