package clipper.hotel.dao;

import clipper.hotel.models.Accommodation;

import javax.persistence.EntityManager;
import java.util.List;

public class AccommodationDAO extends DAO<Accommodation> {
    public AccommodationDAO(EntityManager em) {
        super(em);
    }

    @Override
    public Accommodation findById(Long id) {
        return em.find(Accommodation.class, id);
    }

    @Override
    public List<Accommodation> findAll() {
        String query = "SELECT a FROM Accommodation a";
        return em.createQuery(query, Accommodation.class).getResultList();
    }


}
