/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Local;

/**
 *
 * @author dave
 */
@Local
public interface GestoreFilmLocal {
    
 void addFilm(String titolo, String regista);
 String listFilm();

}

