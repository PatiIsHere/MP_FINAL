package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class VetController {

    @FXML
    private Button vetGoBackToLogin;

    /**
     * Goes back to login window.
     * @see MainMenu#setWindow(String)
     */
    public void goBackToLoginWindow(){
        MainMenu.setWindow("mainMenu-view");
    }
}
