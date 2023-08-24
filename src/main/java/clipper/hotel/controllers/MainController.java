package clipper.hotel.controllers;

import clipper.hotel.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController {
    private static Stage primaryStage;

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
}
