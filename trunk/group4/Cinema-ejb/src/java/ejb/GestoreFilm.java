/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * questo è un session bean, qui ci stanno dei metodi pubblici per gestire il tutto
 * @author dave
 */
@Stateless
public class GestoreFilm implements GestoreFilmLocal {
    @EJB
    private FilmFacadeLocal filmFacade;
    @Override
    public void addFilm(String titolo, String regista){
        Film f = new Film();
        f.setTitoloFilm(titolo);
        f.setRegistaFilm(regista);
        f.setAnno(1927);
        filmFacade.create(f);
    }
   @Override
    public String listFilm(){
        List<Film>  listaFilm = filmFacade.findAll();
        Film iterator;
        String ret = "";
        for(int i=0; i< listaFilm.size();i++){
            iterator = listaFilm.get(i);
            ret +=  iterator.titolo;
            ret +=  " -- ";
            ret += iterator.regista;
            ret +="</br>";
            
        }
        return ret;
    }
    
}
