package clipper.hotel.controllers;

import clipper.hotel.dao.GuestDAO;
import clipper.hotel.factory.ConnectionFactory;
import clipper.hotel.models.Accommodation;
import clipper.hotel.models.Guest;
import clipper.hotel.models.Nationality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class GuestFormController extends FormController<Guest> {
    @FXML
    private DatePicker inputBirthDate;

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputLastName;

    @FXML
    private TextField inputName;


    @FXML
    private ChoiceBox<Nationality> inputNationality;

    @FXML
    private TextField inputPhone;

    @Override
    public void initialize() {

        ObservableList<Nationality> nationalities = FXCollections.observableArrayList(Nationality.values());
        inputNationality.setItems(nationalities);
    }

    @Override
    public void setFormValue(Guest data) {
        inputId.setText(data.getId().toString());
        inputName.setText(data.getfName());
        inputLastName.setText(data.getlName());
        inputBirthDate.setValue(data.getBirthDate());
        inputNationality.setValue(data.getNationality());
        inputPhone.setText(data.getPhone());

    }

    @Override
    public Guest getFormValue() {
        Guest data = new Guest();
        try {
            data.setId(Long.parseLong(inputId.getText()));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        data.setfName(inputName.getText());
        data.setlName(inputLastName.getText());
        data.setBirthDate(inputBirthDate.getValue());
        data.setNationality(inputNationality.getValue());
        data.setPhone(inputPhone.getText());

        return data;
    }

    @Override
    public void clear() {
        inputId.setText(null);
        inputName.setText(null);
        inputLastName.setText(null);
        inputBirthDate.setValue(null);
        inputNationality.setValue(null);
        inputPhone.setText(null);


    }
}
