/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author dave
 */
@MessageDriven(mappedName = "jms/FilmMsg", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MsgFilm implements MessageListener {
    @EJB
    private FilmFacadeLocal filmFacade;
    
   
    public MsgFilm() {
    }
    
    @Override
    public void onMessage(Message message) {
        Film e = null;
        if (message instanceof ObjectMessage){
           ObjectMessage msg = (ObjectMessage) message;
           try{
            e = (Film) msg.getObject();
           
           }
           catch(JMSException ex){
               
               
           }
           filmFacade.create(e);
        }
        
        
        
        /* questo metodo viene richiamato quando arriva un messaggio asincrono sul jms
         
         */
    }
}
