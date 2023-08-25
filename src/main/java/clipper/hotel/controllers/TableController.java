package clipper.hotel.controllers;

import clipper.hotel.dao.AccommodationDAO;
import clipper.hotel.dao.DAO;
import clipper.hotel.models.Accommodation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableView;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class TableController<T> {


    @FXML
    protected TableView<T> table;



    public TableView<T> getTable() {
        return  table;
    }

    public void updateTable(ObservableList<T> data) {
        table.setItems(data);
        table.refresh();
    }

    public T getSelectedItem() {
        SelectionModel<T> selectionModel =  table.getSelectionModel();


        if (!selectionModel.isEmpty()) {
            int selectedIndex = selectionModel.getSelectedIndex();

            return table.getItems().get(selectedIndex);
        }

        return null;
    }

    public void clearSelectedItem() {
        table.getSelectionModel().clearSelection();
    }

    public  abstract void initialize();



}
