package clipper.hotel.controllers;

import clipper.hotel.dao.AccommodationDAO;
import clipper.hotel.dao.GuestDAO;
import clipper.hotel.models.Accommodation;
import clipper.hotel.models.Guest;
import clipper.hotel.models.PaymentMethod;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LongStringConverter;


import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchController extends Controller  {  @FXML
    private TableColumn<Accommodation, LocalDate> accChecOut;

    @FXML
    private TableColumn<Accommodation, LocalDate> accCheckIn;

    @FXML
    private TableColumn<Accommodation, Long> accGuestId;

    @FXML
    private TableColumn<Accommodation, String> accGuestName;

    @FXML
    private TableColumn<Accommodation,Long> accId;

    @FXML
    private TableColumn<Accommodation, PaymentMethod> accPaymentMethod;

    @FXML
    private TableColumn<Accommodation, Double> accTotalValue;
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
    private TableView<Accommodation> tableAccommodations;

    @FXML
    private TabPane tabPane;

    private GuestDAO guestDAO;
    private AccommodationDAO accommodationDAO;

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
    private TextField inputTotalValue;

    @FXML
    private ChoiceBox<PaymentMethod> inputPaymentMethod;

    @FXML
    private DatePicker inputCheckInDate;

    @FXML
    private DatePicker inputCheckOutDate;

    @FXML
    private TextField inputGuestId;

    @FXML
    private TextField inputGuestName;

    @FXML
    private TextField inputAccId;
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
        //System.out.println(accommodations);
        //System.out.println("aaa");



        //guestTable.setItems(getGuests());
        //tableAccommodations.setItems(getAccommodations());

        accId.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        //accId.setCellFactory(TextFieldTableCell.forTableColumn());
       // accId.setOnEditCommit(event -> {
        //    Accommodation item = event.getRowValue();
        //    item.setId(event.getNewValue());
        //});

//        accId = new TableColumn<>("ID");
//        accId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        accId.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));


        accCheckIn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckInDate()));
        accChecOut.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckOutDate()));
        accGuestId.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getGuest().getId()).asObject());
        accGuestName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGuest().getfName()));
        accTotalValue.setCellValueFactory((cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalValue()).asObject()));
        accPaymentMethod.setCellValueFactory((cellData -> new SimpleObjectProperty<>(cellData.getValue().getPaymentMethod())));



        fillTables();
        tableAccommodations.setEditable(true);
        guestTable.setEditable(true);

        inputId.setEditable(false);

        ObservableList<PaymentMethod> paymentMethods = FXCollections.observableArrayList(PaymentMethod.values());
        inputPaymentMethod.setItems(paymentMethods);


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

        tableAccommodations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, accommodation) -> {
            if (accommodation != null) {
                System.out.println("Linha selecionada: " + accommodation);
                inputGuestId.setText(accommodation.getGuest().getId().toString());
                inputGuestName.setText(accommodation.getGuest().getfName());
                inputCheckInDate.setValue(accommodation.getCheckInDate());
                inputCheckOutDate.setValue(accommodation.getCheckOutDate());
                inputAccId.setText(accommodation.getId().toString());
                inputTotalValue.setText(accommodation.getTotalValue().toString());
                inputPaymentMethod.setValue(accommodation.getPaymentMethod());


            }

        });
    }

    private void fillTables() {
        guestTable.setItems(getGuests());
        tableAccommodations.setItems(getAccommodations());
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
        SelectionModel<Accommodation> accommodationSelectionModel = tableAccommodations.getSelectionModel();

        String selectedTab = getSelectedTab();

        if (!guestSelectionModel.isEmpty() && Objects.equals(selectedTab, "Hospedes")) {
            int selectedIndex = guestSelectionModel.getSelectedIndex();

            Guest selectedItem = guestTable.getItems().get(selectedIndex);

            System.out.println("Guest Selecionado " + selectedItem);

            return selectedItem;
        }

        if (!accommodationSelectionModel.isEmpty() && Objects.equals(selectedTab, "Reservas")) {
            int selectedIndex = accommodationSelectionModel.getSelectedIndex();

            Accommodation selectedItem = tableAccommodations.getItems().get(selectedIndex);

            System.out.println("Accommodation Selecionado " + selectedItem);

            return selectedItem;
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
        } else if (selected instanceof Accommodation a) {
            a.setCheckInDate(inputCheckInDate.getValue());
            a.setCheckOutDate(inputCheckOutDate.getValue());
            a.setPaymentMethod(inputPaymentMethod.getValue());

            accommodationDAO.update(a);
        }


        System.out.println("Salvar: " +  selected);

        fillTables();
    }

    public void clearGuestForm() {
        inputBirthDate.setValue(null);
        inputId.setText("");
        inputName.setText("");
        inputLastname.setText("");
        inputPhone.setText("");
        inputNationality.setText("");
    }

    public void clearAccForm() {
        inputAccId.setText(null);
        inputGuestId.setText(null);
        inputGuestName.setText(null);
        inputPaymentMethod.setValue(null);
        inputTotalValue.setText(null);
        inputCheckInDate.setValue(null);
        inputCheckOutDate.setValue(null);

    }
}
