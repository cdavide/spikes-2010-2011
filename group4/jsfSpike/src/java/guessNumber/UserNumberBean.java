/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Davide Concas
 * Lo scope di questo bean e' la sessione, viene istanziato 
 * nel momento della generazione del form.
 * Se avessi messo come scope RequestScoped ogni volta che una pagina fa una 
 * richiesta al bean viene istanziato l'oggetto
 * Si pu`o osservare guardando il server che stampa i numeri generati dal costruttore
 * 
 */
@ManagedBean(name="UserNumberBean")
@SessionScoped
public class UserNumberBean implements Serializable{
    Integer randomInt;
    Integer userNumber;
    String response;

    public String getResponse() {
         if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)) {
        //invalidate user session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();

        return "Yay! You got it!";
    } else {

        return "<p>Sorry, " + userNumber + " isn't it.</p>"
                + "<p>Guess again...</p>";
    }
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
    /** Creates a new instance of UserNumberBean */
    public UserNumberBean() {
        Random randomGr = new Random();
        randomInt = new Integer(randomGr.nextInt(10));
        System.out.println("Duke's number: "+randomInt);
    }
    
}
