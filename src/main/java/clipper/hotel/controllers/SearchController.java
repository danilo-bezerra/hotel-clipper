package clipper.hotel.controllers;

import clipper.hotel.HelloApplication;
import clipper.hotel.dao.AccommodationDAO;
import clipper.hotel.dao.GuestDAO;
import clipper.hotel.exceptions.EntityValidationException;
import clipper.hotel.models.Accommodation;
import clipper.hotel.models.Guest;
import clipper.hotel.validators.AccommodationValidator;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class SearchController extends Controller  {
    private AccommodationDAO accommodationDAO;

    @FXML
    private TabPane tabPane;

    private GuestDAO guestDAO;

    @FXML
    private AnchorPane accTablePane;

    @FXML
    public AnchorPane accFormPane;

    @FXML
    public AnchorPane guestTablePane;

    @FXML
    public AnchorPane guestFormPane;

    private AccommodationTableController accommodationTableController;

    private AccommodationFormController accommodationFormController;

    private GuestTableController guestTableController;

    private GuestFormController guestFormController;

    @FXML
    private Label errorLabel;


    public SearchController() {
        super();
        accommodationDAO = new AccommodationDAO(em);
        guestDAO = new GuestDAO(em);
    }

    public void initialize() {

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("accommodation-table.fxml"));
            accTablePane.getChildren().add(loader.load());
            accommodationTableController = loader.getController();

            loader = new FXMLLoader(HelloApplication.class.getResource("accommodation-form.fxml"));
            accFormPane.getChildren().add(loader.load());
            accommodationFormController = loader.getController();

            loader =  new FXMLLoader(HelloApplication.class.getResource("guest-table.fxml"));
            guestTablePane.getChildren().add(loader.load());
            guestTableController = loader.getController();

            loader =  new FXMLLoader(HelloApplication.class.getResource("guest-form.fxml"));
            guestFormPane.getChildren().add(loader.load());
            guestFormController = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }

        fillTables();

        guestTableController.getTable().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, guest) -> {
            if (guest != null) {
                guestFormController.setFormValue(guest);
            }

        });

        accommodationTableController.getTable().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, accommodation) -> {
            if (accommodation != null) {
                accommodationFormController.setFormValue(accommodation);
            }

        });

    }

    private void fillTables() {
        System.out.println("AUALIZANDO");
        guestTableController.updateTable(getGuests());
        accommodationTableController.updateTable(getAccommodations());
    }

    private ObservableList<Guest> getGuests() {
        ObservableList<Guest> list = FXCollections.observableArrayList();
        List<Guest> guests = guestDAO.findAll();
        list.addAll(guests);

        return list;
    }

    private ObservableList<Accommodation> getAccommodations() {
        ObservableList<Accommodation> list = FXCollections.observableArrayList();
        List<Accommodation> accommodations = accommodationDAO.findAll();
        list.addAll(accommodations);

        return list;
    }



    public Object getSelectedItem() {

        String selectedTab = getSelectedTab();

        if (Objects.equals(selectedTab, "Hospedes")) {
            return guestTableController.getSelectedItem();
        } else if (Objects.equals(selectedTab, "Reservas")) {
            return accommodationTableController.getSelectedItem();
        }

        return null;
    }

    public String getSelectedTab() {
        SingleSelectionModel<Tab    > selectionModel = tabPane.getSelectionModel();

        // Verifica se há alguma aba selecionada
        if (!selectionModel.isEmpty()) {
            // Obtém a aba selecionada
            Tab selectedTab = selectionModel.getSelectedItem();

            // Agora você pode trabalhar com a aba selecionada
            System.out.println("Aba atual: " + selectedTab.getText());

            return selectedTab.getText();
        } else {
            System.out.println("Nenhuma aba selecionada.");
        }

        return null;
    }


    public void deleteItem() {
        Object selected = getSelectedItem();

        if (selected instanceof  Guest) {
            guestDAO.remove((Guest) selected);
        } else if (selected instanceof  Accommodation) {
            accommodationDAO.remove((Accommodation) selected);
        }

        fillTables();
    }

    public void saveItem() {
        //errorLabel.setText("");
        try {
        switch (getSelectedTab()) {
            case "Reservas" -> {
                Accommodation a = accommodationFormController.getFormValue();


                    new AccommodationValidator().validate(a);

                    if (a.getId() != null) {
                        accommodationDAO.update(a);
                    } else {
                        accommodationDAO.create(a);
                    }



            }
            case "Hospedes" -> {
                Guest g = guestFormController.getFormValue();
                if (g.getId() != null) {
                    guestDAO.update(g);
                } else {
                    guestDAO.create(g);
                }
            }
        }

        clearForms();
        fillTables();
        } catch (EntityValidationException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
            errorLabel.setText(e.getMessage());
        }

    }



    public void clearForms() {
        accommodationFormController.clear();
        accommodationTableController.clearSelectedItem();

        guestTableController.clearSelectedItem();
        guestFormController.clear();

        errorLabel.setText("");
    }
}
