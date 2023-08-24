package clipper.hotel.controllers;

import clipper.hotel.dao.DAO;
import clipper.hotel.dao.EmployeeDAO;
import clipper.hotel.models.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController extends Controller {
    private EmployeeDAO dao;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;

    @FXML
    private Label errorLabel;

    public AuthController() {
        super();
        this.dao = new EmployeeDAO(em);
    }
    @FXML
    protected void onLogin() {
        System.out.println("LOGIN" + inputUsername.getText() + " " + inputPassword.getText());
        try {
            Employee e = dao.findByUsernameAndPassword(inputUsername.getText(), inputPassword.getText());
            System.out.println(dao.findAll());

            if (e != null) {
                MainController.showMainView();
            }

            System.out.println("SUCESSO NO LOGIN");
        } catch (Exception e) {
            errorLabel.setText("Usuário e/ou senha inválidos");
            System.out.println("ERRO onLogin(): " + e.getMessage());
            e.printStackTrace();
        }




    }
}
