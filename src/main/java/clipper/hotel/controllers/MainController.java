package clipper.hotel.controllers;

import clipper.hotel.HelloApplication;
import clipper.hotel.dao.AccommodationDAO;
import clipper.hotel.dao.GuestDAO;
import clipper.hotel.factory.ConnectionFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController extends Controller{
    private static Stage primaryStage;

    @FXML
    private Label lblAccommodationsCount;

    @FXML
    private Label lblGuestsCount;



    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }


    @FXML
    public  void register() {
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("register-view.fxml"));
            setScene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public  void search() {
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("search-view.fxml"));
            setScene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public  void signOut() {
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("auth-view.fxml"));
            setScene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showMainView() {
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("main-view.fxml"));
            setScene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setScene(Parent root) {
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

    }

    public void initialize() {
        AccommodationDAO accommodationDAO = new AccommodationDAO(em);
        GuestDAO guestDAO = new GuestDAO(em);

        lblAccommodationsCount.setText(String.format("%d", accommodationDAO.findAll().size()));
        lblGuestsCount.setText(String.format("%d", guestDAO.findAll().size()));
    }


}
