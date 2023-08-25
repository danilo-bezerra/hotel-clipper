package clipper.hotel.controllers;

import clipper.hotel.models.Accommodation;
import clipper.hotel.models.Guest;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private TextField inputNationality;

    @FXML
    private TextField inputPhone;

    @Override
    public void initialize() {

    }

    @Override
    public void setFormValue(Guest data) {
        inputId.setText(data.getId().toString());
        inputName.setText(data.getfName());
        inputLastName.setText(data.getlName());
        inputBirthDate.setValue(data.getBirthDate());
        inputNationality.setText(data.getNationality());
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
        data.setNationality(inputNationality.getText());
        data.setPhone(inputPhone.getText());

        return data;
    }

    @Override
    public void clear() {
        inputId.setText(null);
        inputName.setText(null);
        inputLastName.setText(null);
        inputBirthDate.setValue(null);
        inputNationality.setText(null);
        inputPhone.setText(null);


    }
}
