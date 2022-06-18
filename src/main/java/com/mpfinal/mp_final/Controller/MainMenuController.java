package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.MainMenu;
import com.mpfinal.mp_final.Model.Internal.Employee;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenuController implements Initializable {

    @FXML
    private ChoiceBox<Integer> employeeIDChoiseBox;

    @FXML
    private Button loginReceptionistButton;
    private Button loginVetButton;

    public static int employeeID;

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

    private void popNoEmployeeIdSelected(){
        Alert noIdSelected = new Alert(Alert.AlertType.ERROR);
        noIdSelected.setTitle("UWAGA!");
        noIdSelected.setHeaderText("Nie wybrano ID pracownika!");
        noIdSelected.setContentText(
                "Wybierz ID pracownika, następnie kliknij odpowiedni przycisk!"
        );

        noIdSelected.showAndWait();
    }

    @FXML
    private void loginAsReceptionist(){
        if (employeeIDChoiseBox.getValue() == null){
            popNoEmployeeIdSelected();
            return;
        }
        boolean isReceptionist = false;
        try {
            isReceptionist = ExtensionManager.getExtent(Employee.class)
                            .stream()
                            .filter(e -> e.getId() == employeeIDChoiseBox.getValue())
                            .anyMatch(e -> e.isReceptionist() == true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (isReceptionist) {
            employeeID = employeeIDChoiseBox.getValue();
            MainMenu.setWindow("receptionist-view");
        }else {
            popEmployeeIsNotReceptionist();
        }
    }


    private void popEmployeeIsNotReceptionist(){
        Alert noVetAvaible = new Alert(Alert.AlertType.ERROR);
        noVetAvaible.setTitle("UWAGA!");
        noVetAvaible.setHeaderText("Brak uprawnień!");
        noVetAvaible.setContentText(
                "Podany pracownik nie jest recepcjonistą!"
        );

        noVetAvaible.showAndWait();
    }

    @FXML
    private void loginAsVeterarian(){
        if (employeeIDChoiseBox.getValue() == null){
            popNoEmployeeIdSelected();
            return;
        }
        boolean isReceptionist = false;
        try {
            isReceptionist = ExtensionManager.getExtent(Employee.class)
                    .stream()
                    .filter(e -> e.getId() == employeeIDChoiseBox.getValue())
                    .anyMatch(e -> e.isVet() == true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (isReceptionist) {
            employeeID = employeeIDChoiseBox.getValue();
            MainMenu.setWindow("vet-view");
        }else {
            popEmployeeIsNotVet();
        }
    }


    private void popEmployeeIsNotVet(){
        Alert noVetAvaible = new Alert(Alert.AlertType.ERROR);
        noVetAvaible.setTitle("UWAGA!");
        noVetAvaible.setHeaderText("Brak uprawnień!");
        noVetAvaible.setContentText(
                "Podany pracownik nie jest weterynarzem!"
        );

        noVetAvaible.showAndWait();
    }
}