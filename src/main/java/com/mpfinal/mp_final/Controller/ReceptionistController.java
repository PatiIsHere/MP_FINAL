package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ReceptionistController {

    @FXML
    private Button goBackToLogin;
    @FXML
    private Button createAppointmentButton;

    public void goBackToLoginWindow(){
        MainMenu.setWindow("mainMenu-view");
    }

    public void goToAppointmentWindow(){
        MainMenu.setWindow("appointment-view");
    }
}
