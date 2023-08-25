package clipper.hotel.controllers;

import clipper.hotel.HelloApplication;
import clipper.hotel.dao.AccommodationDAO;
import clipper.hotel.dao.GuestDAO;
import clipper.hotel.models.Accommodation;
import clipper.hotel.models.Guest;
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
    private TableColumn<Guest, LocalDate> columnBirthDate;

    @FXML
    private TableColumn<Guest, Long> columnId;

    @FXML
    private TableColumn<Guest, String> columnLastName;

    @FXML
    private TableColumn<Guest, String> columnName;

    @FXML
    private TableColumn<Guest, String> columnPhone;

    @FXML
    private TableColumn<Guest, String> columnNationality;

    @FXML
    private TableView<Guest> guestTable;



    @FXML
    private TabPane tabPane;

    private GuestDAO guestDAO;


    @FXML
    private DatePicker inputBirthDate;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputNationality;

    @FXML
    private TextField inputLastname;

    @FXML
    private TextField inputPhone;


    @FXML
    private AnchorPane accTablePane;

    @FXML
    public AnchorPane accFormPane;

    private AccommodationTableController accommodationTableController;

    private AccommodationFormController accommodationFormController;


    public SearchController() {
        super();
        accommodationDAO = new AccommodationDAO(em);
        guestDAO = new GuestDAO(em);
    }

    public void initialize() {


        columnId.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getfName()));
        columnLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getlName()));
        columnBirthDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBirthDate()));
        columnPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        columnNationality.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNationality()));

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("accommodation-table.fxml"));
            accTablePane.getChildren().add(loader.load());
            accommodationTableController = loader.getController();

            loader = new FXMLLoader(HelloApplication.class.getResource("accommodation-form.fxml"));
            accFormPane.getChildren().add(loader.load());
            accommodationFormController = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }

        fillTables();

        guestTable.setEditable(true);

        inputId.setEditable(false);



        guestTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, guest) -> {
            if (guest != null) {
                System.out.println("Linha selecionada: " + guest);
                inputId.setText(guest.getId().toString());
                inputName.setText(guest.getfName());
                inputLastname.setText(guest.getlName());
                inputPhone.setText(guest.getPhone());
                inputNationality.setText(guest.getNationality());
                inputBirthDate.setValue(guest.getBirthDate());
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
        guestTable.setItems(getGuests());

        guestTable.refresh();
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
        SelectionModel<Guest> guestSelectionModel = guestTable.getSelectionModel();

        String selectedTab = getSelectedTab();

        if (!guestSelectionModel.isEmpty() && Objects.equals(selectedTab, "Hospedes")) {
            int selectedIndex = guestSelectionModel.getSelectedIndex();

            Guest selectedItem = guestTable.getItems().get(selectedIndex);

            System.out.println("Guest Selecionado " + selectedItem);

            return selectedItem;
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

        System.out.println("Apagar: " +  getSelectedItem());

        fillTables();
    }

    public void saveItem() {
        Object selected = getSelectedItem();

        if (selected instanceof  Guest) {
            Guest g = new Guest(inputName.getText(), inputLastname.getText(),inputBirthDate.getValue(), inputNationality.getText(), inputPhone.getText() );
            g.setId(Long.parseLong(inputId.getText()));
            guestDAO.update(g);
        }

//        } else if (selected instanceof Accommodation) {
//            Accommodation a = accommodationFormController.getData();
//            if (a.getId() != null) {
//                accommodationDAO.update(a);
//            } else {
//                accommodationDAO.create(a);
//            }
//        }

        switch (getSelectedTab()) {
            case "Reservas":
                Accommodation a = accommodationFormController.getData();
                if (a.getId() != null) {
                    accommodationDAO.update(a);
                } else {
                    accommodationDAO.create(a);
                }
                break;

            case "Hospedes":
                break;
        }


        System.out.println("Salvar: " +  selected);
        clearForms();
        fillTables();

    }

    public void clearGuestForm() {
        inputBirthDate.setValue(null);
        inputId.setText("");
        inputName.setText("");
        inputLastname.setText("");
        inputPhone.setText("");
        inputNationality.setText("");

        guestTable.getSelectionModel().clearSelection();
    }

    public void clearForms() {
        accommodationFormController.clear();
        accommodationTableController.clearSelectedItem();
        clearGuestForm();
    }
}
