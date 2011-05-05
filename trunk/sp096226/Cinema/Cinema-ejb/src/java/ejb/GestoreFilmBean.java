/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sp096226
 */
@Stateless
public class GestoreFilmBean implements GestoreFilmLocal {
    @EJB
    private FilmFacadeLocal filmFacade;

    public void inserisciFilm(String s) {
        Film film = new Film();
        film.setTitoloFilm(s);
        filmFacade.create(film);
    }

    public String listaFilm(){
        List<Film> films = filmFacade.findAll();
        String lista = "";
        for(int i=0;i<films.size();i++) {
            lista += films.get(i).getTitoloFilm()+"\n";
        }
        return lista;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
 
}
