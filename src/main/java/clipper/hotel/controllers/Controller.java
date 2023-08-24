package clipper.hotel.controllers;

import clipper.hotel.factory.ConnectionFactory;
import javafx.fxml.FXML;

import javax.persistence.EntityManager;

public abstract class Controller {
    protected EntityManager em;

    public Controller() {
        em = ConnectionFactory.getEntitymanager();
    }

    @FXML
    public  void goBackToHome() {
        MainController.showMainView();
    }
}
