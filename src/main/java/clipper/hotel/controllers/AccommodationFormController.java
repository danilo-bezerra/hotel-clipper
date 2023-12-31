package clipper.hotel.controllers;

import clipper.hotel.dao.GuestDAO;
import clipper.hotel.factory.ConnectionFactory;
import clipper.hotel.models.Accommodation;
import clipper.hotel.models.Guest;
import clipper.hotel.models.PaymentMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;




public class AccommodationFormController extends FormController<Accommodation> {
    @FXML
    private DatePicker inputCheckIn;

    @FXML
    private DatePicker inputCheckOut;

    @FXML
    private ChoiceBox<Guest> inputGuest;

    @FXML
    private TextField inputId;

    @FXML
    private ChoiceBox<PaymentMethod> inputPaymentMethod;

    @FXML
    private TextField inputTotalValue;

    @Override
    public void initialize() {
        inputCheckIn.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && inputCheckOut.getValue() !=  null) {
                double total = Accommodation.calcDaysDistance(newValue, inputCheckOut.getValue()) * Accommodation.dayPrice;
                inputTotalValue.setText(Double.toString(total));
            }
        });

        inputCheckOut.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && inputCheckIn.getValue() !=  null) {
                double total = Accommodation.calcDaysDistance(inputCheckIn.getValue(), newValue) * Accommodation.dayPrice;
                inputTotalValue.setText(Double.toString(total));
            }
        });

        ObservableList<PaymentMethod> paymentMethods = FXCollections.observableArrayList(PaymentMethod.values());
        inputPaymentMethod.setItems(paymentMethods);

        ObservableList<Guest> guests = FXCollections.observableArrayList(new GuestDAO(ConnectionFactory.getEntitymanager()).findAll());
        inputGuest.setItems(guests);
    }




    @Override
    public void setFormValue(Accommodation data) {
        inputId.setText(data.getId().toString());
        inputCheckIn.setValue(data.getCheckInDate());
        inputCheckOut.setValue(data.getCheckOutDate());
        inputTotalValue.setText(data.getTotalValue().toString());
        inputPaymentMethod.setValue(data.getPaymentMethod());
        inputGuest.setValue(data.getGuest());
    }

    @Override
    public Accommodation getFormValue() {
        Accommodation data = new Accommodation();
        try {
            data.setId(Long.parseLong(inputId.getText()));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        data.setCheckInDate(inputCheckIn.getValue());
        data.setCheckOutDate(inputCheckOut.getValue());
        data.setPaymentMethod(inputPaymentMethod.getValue());
        data.setGuest(inputGuest.getValue());

        return data;
    }

    @Override
    public void clear() {
        inputId.setText(null);
        inputCheckIn.setValue(null);
        inputCheckOut.setValue(null);
        inputPaymentMethod.setValue(null);
        inputTotalValue.setText(null);
        inputGuest.setValue(null);
    }


}
