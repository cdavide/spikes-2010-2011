/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ejb.Stateless;

/**
 * Quando genera questo, tutti i metodi pubblici del enterprise java bean 
 * @author dave
 */
@WebService(serviceName = "FilmWebService")
@Stateless()
public class FilmWebService {
    @EJB
    private GestoreFilmLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "addFilm")
    @Oneway
    public void addFilm(@WebParam(name = "titolo")
    String titolo, @WebParam(name = "regista")
    String regista) {
        ejbRef.addFilm(titolo, regista);
    }

    @WebMethod(operationName = "listFilm")
    public String listFilm() {
        return ejbRef.listFilm();
    }
    
}
