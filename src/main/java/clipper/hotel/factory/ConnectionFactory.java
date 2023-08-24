package clipper.hotel.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("challenge");

    public static EntityManager getEntitymanager() {
        return FACTORY.createEntityManager();
    }
}
