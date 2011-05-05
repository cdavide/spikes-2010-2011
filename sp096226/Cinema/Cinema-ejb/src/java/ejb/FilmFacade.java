/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sp096226
 */
@Stateless
public class FilmFacade implements FilmFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Film film) {
        em.persist(film);
    }

    public void edit(Film film) {
        em.merge(film);
    }

    public void remove(Film film) {
        em.remove(em.merge(film));
    }

    public Film find(Object id) {
        return em.find(Film.class, id);
    }

    public List<Film> findAll() {
        return em.createQuery("select object(o) from Film as o").getResultList();
    }

}
