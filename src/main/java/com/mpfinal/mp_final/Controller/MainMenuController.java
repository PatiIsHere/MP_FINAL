package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.Model.Internal.Employee;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenuController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private ChoiceBox<Integer> employeeIDChoiseBox;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            employeeIDChoiseBox.setItems(FXCollections.observableArrayList (
                    ExtensionManager.getExtent(Employee.class).stream()
                            .map(Employee::getId)
                            .collect(Collectors.toList())));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}