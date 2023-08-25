package clipper.hotel.components;

import clipper.hotel.models.Guest;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class GuestTable extends TableView<Guest> {
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
    private TableColumn<Guest, LocalDate> columnBirthDate;

    public GuestTable() {


    }

    public void initialize() {
        columnId.setText("Id");
        columnName.setText("Nome");
        columnLastName.setText("Sobrenome");
        columnBirthDate.setText("Data de Nascimento");
        columnPhone.setText("Telefone");
        columnNationality.setText("Nacionalidade");

    }
}
