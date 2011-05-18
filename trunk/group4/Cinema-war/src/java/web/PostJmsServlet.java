/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.Film;
import ejb.GestoreFilmLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.ObjectMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Questa servlet rappresenta le chiamate JMS, in teoria dovrebbe essere una applicazione esterna 
 * @author dave
 */
@WebServlet(name = "PostJmsServlet", urlPatterns = {"/PostJmsServlet"})
public class PostJmsServlet extends HttpServlet {
    @EJB
    private GestoreFilmLocal gestoreFilm;
    @Resource(mappedName = "jms/FilmMsg")
    private Queue filmMsg;
    @Resource(mappedName = "jms/FilmMsgFactory")
    private ConnectionFactory filmMsgFactory;
    
    
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
                Film f = new Film();
                f.setTitoloFilm(request.getParameter("titolo"));
                f.setRegistaFilm(request.getParameter("regista"));
                f.setAnno(1927);
                try {
                sendJMSMessageToFilmMsg((Object) f);
                }catch(JMSException ex){
                    System.out.println("Exception!");
                    
                }
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

    private Message createJMSMessageForjmsFilmMsg(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        
        ObjectMessage tm = session.createObjectMessage();
        tm.setObject((Film) messageData);
        return tm;
    }

    private void sendJMSMessageToFilmMsg(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = filmMsgFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(filmMsg);
            messageProducer.send(createJMSMessageForjmsFilmMsg(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
