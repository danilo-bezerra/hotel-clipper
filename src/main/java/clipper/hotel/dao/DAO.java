package clipper.hotel.dao;


import javax.persistence.EntityManager;
import java.util.List;

public abstract class DAO<T extends Object> {
    protected EntityManager em;
    //private Class<?> classReference;

    public DAO(EntityManager em/*,  Class<T> classReference*/) {

        this.em = em;
        //this.classReference = classReference;

    }

    public void create(T obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public void update(T obj) {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public void remove(T obj) {
        em.getTransaction().begin();
        obj = em.merge(obj);
        em.remove(obj);
        em.getTransaction().commit();
    }



    public abstract T findById(Long id);



    public abstract List<T> findAll();
}

