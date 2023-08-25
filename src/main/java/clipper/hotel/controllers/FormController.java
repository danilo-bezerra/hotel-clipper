package clipper.hotel.controllers;

import clipper.hotel.dao.GuestDAO;
import clipper.hotel.factory.ConnectionFactory;
import clipper.hotel.models.Accommodation;
import clipper.hotel.models.Guest;
import clipper.hotel.models.PaymentMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class FormController<T> {
    public abstract void initialize();

    public abstract void setFormValue(T data);
    public abstract T getFormValue();

    public abstract void clear();


}
