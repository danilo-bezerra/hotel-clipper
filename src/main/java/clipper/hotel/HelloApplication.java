package clipper.hotel;

import clipper.hotel.controllers.MainController;
import clipper.hotel.factory.ConnectionFactory;
import clipper.hotel.setup.DevSetup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new DevSetup(ConnectionFactory.getEntitymanager()).populateDatabase();

        MainController.setPrimaryStage(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("auth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hotel Clipper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}