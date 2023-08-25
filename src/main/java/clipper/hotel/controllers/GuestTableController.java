package clipper.hotel.controllers;

import clipper.hotel.models.Guest;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;


public class GuestTableController extends TableController<Guest> {
    @FXML
    private TableColumn<Guest, LocalDate> ColBirthDate;

    @FXML
    private TableColumn<Guest, Long> colId;

    @FXML
    private TableColumn<Guest, String> colLastName;

    @FXML
    private TableColumn<Guest,String> colName;

    @FXML
    private TableColumn<Guest, String> colNationality;

    @FXML
    private TableColumn<Guest, String> colPhone;
    @Override
    public void initialize() {
        colId.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        colName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getfName()));
        colLastName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getlName()));
        colNationality.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNationality()));
        colPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhone()));
        ColBirthDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBirthDate()));
    }
}
