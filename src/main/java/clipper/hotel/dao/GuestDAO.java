package clipper.hotel.dao;

import clipper.hotel.models.Guest;

import javax.persistence.EntityManager;
import java.util.List;

public class GuestDAO  extends DAO<Guest>{
    public GuestDAO(EntityManager em) {
    super(em);
}

    @Override
    public Guest findById(Long id) {
        return em.find(Guest.class, id);
    }

    @Override
    public List<Guest> findAll() {
        String query = "SELECT g FROM Guest g";
        return em.createQuery(query, Guest.class).getResultList();
    }
}
