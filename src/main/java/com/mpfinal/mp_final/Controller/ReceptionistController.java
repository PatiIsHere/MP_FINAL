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
        MainMenu.setAnchor(0);
    }

    public void goToAppointmentWindow(){
        MainMenu.setAnchor(3);
    }
}
