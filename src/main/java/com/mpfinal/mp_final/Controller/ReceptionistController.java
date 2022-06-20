package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ReceptionistController {

    @FXML
    private Button goBackToLogin;
    @FXML
    private Button createAppointmentButton;

    /**
     * Goes back to login window.
     * @see MainMenu#setWindow(String)
     */
    public void goBackToLoginWindow(){
        MainMenu.setWindow("mainMenu-view");
    }

    /**
     * Goes to appointment creation window.
     * @see MainMenu#setWindow(String)
     */
    public void goToAppointmentWindow(){
        MainMenu.setWindow("appointment-view");
    }
}
