package clipper.hotel.setup;

import clipper.hotel.dao.AccommodationDAO;
import clipper.hotel.dao.EmployeeDAO;
import clipper.hotel.dao.GuestDAO;
import clipper.hotel.models.Accommodation;
import clipper.hotel.models.Employee;
import clipper.hotel.models.Guest;
import clipper.hotel.models.PaymentMethod;


import javax.persistence.EntityManager;
import java.time.LocalDate;

public class DevSetup {
    private final EntityManager em;
    public DevSetup(EntityManager em) {
        this.em = em;
    }

    public  void populateDatabase() {
        EmployeeDAO employeeDAO = new EmployeeDAO(em);
        GuestDAO guestDAO = new GuestDAO(em);
        AccommodationDAO accommodationDAO = new AccommodationDAO(em);

        Employee e1 = new Employee("clipper", "password" );
        Guest g1 = new Guest("Alien", "Clipper", LocalDate.now().minusYears(20L), "Brazilian", "88 981882984");
        Guest g2 = new Guest("Zoe", "Groove", LocalDate.now().minusYears(19L), "Brazilian", "88 985968899");


        guestDAO.create(g1);
        guestDAO.create(g2);

        //System.out.println(g1+ " " + g2);
        //System.out.println(guestDAO.findAll());

        employeeDAO.create(e1);
        Accommodation a1 = new Accommodation(LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), 50.0, PaymentMethod.PIX, g1);
        accommodationDAO.create(a1);

       // System.out.println(e1);

        //System.out.println(a1);
    }
}
