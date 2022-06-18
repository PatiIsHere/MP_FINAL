package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class VetController {

    @FXML
    private Button vetGoBackToLogin;

    public void goBackToLoginWindow(){
        MainMenu.setAnchor(0);
    }
}
