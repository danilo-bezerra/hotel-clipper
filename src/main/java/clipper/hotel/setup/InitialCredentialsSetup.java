package clipper.hotel.setup;

import at.favre.lib.crypto.bcrypt.BCrypt;
import clipper.hotel.dao.EmployeeDAO;
import clipper.hotel.models.Employee;

import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class InitialCredentialsSetup {
    private EntityManager em;


    public InitialCredentialsSetup(EntityManager em) {
        this.em = em;

    }
    public void execute() {


        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("./src/main/resources/config.properties")) {

            properties.load(fileInputStream);
            String initialUsername = properties.getProperty("initial.username");
            String initialPassword = properties.getProperty("initial.password");

            EmployeeDAO employeeDAO = new EmployeeDAO(em);

            Employee e1 = new Employee(initialUsername, BCrypt.withDefaults().hashToString(12,initialPassword.toCharArray()));
            employeeDAO.create(e1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
