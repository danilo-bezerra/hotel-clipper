package clipper.hotel.dao;

import clipper.hotel.models.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeDAO extends DAO<Employee> {
    public EmployeeDAO(EntityManager em) {
        super(em);
    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    public Employee findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT e from Employee e WHERE e.username = :username AND e.password = :password";
        return em.createQuery(sql, Employee.class).setParameter("username", username).setParameter("password", password).getSingleResult();

    }

    public Employee findByUsername(String username) {
        String sql = "SELECT e from Employee e WHERE e.username = :username";
        return em.createQuery(sql, Employee.class).setParameter("username", username).getSingleResult();

    }
}
