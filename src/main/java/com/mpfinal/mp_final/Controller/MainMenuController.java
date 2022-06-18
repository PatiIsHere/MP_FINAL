package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.MainMenu;
import com.mpfinal.mp_final.Model.Internal.Employee;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenuController implements Initializable {

    @FXML
    private ChoiceBox<Integer> employeeIDChoiseBox;

    @FXML
    private Button loginButton;


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

    @FXML
    public void doIt(){
        MainMenu.setAnchor(2);
    }

    public void doIt2(){
        System.out.println("x");
    }
}