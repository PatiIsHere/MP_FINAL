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
    @FXML
    private Button loginVetButton;

    public static int employeeID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //fill employeeIDChoiseBox with avaible employees id in ExtensionManager
        try {
            employeeIDChoiseBox.setItems(FXCollections.observableArrayList (
                    ExtensionManager.getExtent(Employee.class).stream()
                            .map(Employee::getId)
                            .collect(Collectors.toList())));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates pop-up window when action is performed before employee id selection.
     */
    private void popNoEmployeeIdSelected(){
        Alert noIdSelected = new Alert(Alert.AlertType.ERROR);
        noIdSelected.setTitle("UWAGA!");
        noIdSelected.setHeaderText("Nie wybrano ID pracownika!");
        noIdSelected.setContentText(
                "Wybierz ID pracownika, następnie kliknij odpowiedni przycisk!"
        );

        noIdSelected.showAndWait();
    }

    /**
     * Assigns selected id to static employeeID value and updates main stage scene with receptionist-view.
     * If employee is not receptionist - shows pop-up window with appropriate information.
     * {@link #popEmployeeIsNotReceptionist()}
     */
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

    /**
     * Creates pop-up window informing user that selected employee is not a receptionist.
     */
    private void popEmployeeIsNotReceptionist(){
        Alert noVetAvaible = new Alert(Alert.AlertType.ERROR);
        noVetAvaible.setTitle("UWAGA!");
        noVetAvaible.setHeaderText("Brak uprawnień!");
        noVetAvaible.setContentText(
                "Podany pracownik nie jest recepcjonistą!"
        );

        noVetAvaible.showAndWait();
    }
    /**
     * Assigns selected id to static employeeID value and updates main stage scene with vet-view.
     * If employee is not vet - shows pop-up window with appropriate information.
     * {@link #popEmployeeIsNotVet()}
     */
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

    /**
     * Creates pop-up window informing user that selected employee is not a receptionist.
     */
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