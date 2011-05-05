/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;

/**
 *
 * @author sp096226
 */
@Local
public interface GestoreFilmLocal {

    public void inserisciFilm(String s);

    public java.lang.String listaFilm();
    
}
