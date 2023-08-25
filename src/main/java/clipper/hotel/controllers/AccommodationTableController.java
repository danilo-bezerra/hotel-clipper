package clipper.hotel.controllers;

import clipper.hotel.dao.AccommodationDAO;
import clipper.hotel.dao.GuestDAO;
import clipper.hotel.factory.ConnectionFactory;
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
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccommodationTableController {
    private AccommodationDAO dao;

    @FXML
    private TableColumn<Accommodation, LocalDate> colCheckIn;

    @FXML
    private javafx.scene.control.TableColumn<Accommodation, LocalDate> colCheckOut;

    @FXML
    private TableColumn<Accommodation, Long> colGuestId;

    @FXML
    private TableColumn<Accommodation, String> colGuestName;

    @FXML
    private TableColumn<Accommodation,Long> colId;

    @FXML
    private TableColumn<Accommodation, PaymentMethod> colPaymentMethod;

    @FXML
    private TableColumn<Accommodation, Double> colTotalValue;

    @FXML
    private TableView<Accommodation> table;

    public AccommodationTableController() {
        //super();
        dao = new AccommodationDAO(ConnectionFactory.getEntitymanager());

    }

    public TableView<Accommodation> getTable() {
        return table;
    }

    public void initialize() {
        colId.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        colCheckIn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckInDate()));
        colCheckOut.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckOutDate()));
        colGuestId.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getGuest().getId()).asObject());
        colGuestName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGuest().getfName()));
        colTotalValue.setCellValueFactory((cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalValue()).asObject()));
        colPaymentMethod.setCellValueFactory((cellData -> new SimpleObjectProperty<>(cellData.getValue().getPaymentMethod())));

        updateTable(getData());
    }

    private ObservableList<Accommodation> getData() {
        ObservableList<Accommodation> list = FXCollections.observableArrayList();
        List<Accommodation> accommodations = dao.findAll();
        list.addAll(accommodations);

        return list;
    }

    public void updateTable(ObservableList<Accommodation> data) {
        System.out.println("ATUALIZANDO tabela de acomodações");
        table.setItems(data);

        table.refresh();
    }

    public Accommodation getSelectedItem() {
        SelectionModel<Accommodation> selectionModel = table.getSelectionModel();


        if (!selectionModel.isEmpty()) {
            int selectedIndex = selectionModel.getSelectedIndex();

            Accommodation selectedItem = table.getItems().get(selectedIndex);

            System.out.println("Accommodation Selecionado " + selectedItem);

            return selectedItem;
        }

        return null;
    }

    public void clearSelectedItem() {
        table.getSelectionModel().clearSelection();
    }


}
