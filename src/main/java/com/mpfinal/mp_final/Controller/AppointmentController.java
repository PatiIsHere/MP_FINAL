package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class AppointmentController {

    @FXML
    private Button saveAppointmentButton;

    public void goBack(){
        MainMenu.setAnchor(1);
    }

    @FXML
    public void popSuccessWindow(){
        Alert howToPlay = new Alert(Alert.AlertType.CONFIRMATION);
        howToPlay.setTitle("SUKCES!");
        howToPlay.setHeaderText("Rezerwacja wizyty została pomyślnie zapisana.");
        howToPlay.setContentText("Wizyta zostanie obsłużona przez: " + "Imie, Nazwisko");

        howToPlay.showAndWait();
    }

    @FXML
    public void popNoVetWindow(){
        Alert howToPlay = new Alert(Alert.AlertType.ERROR);
        howToPlay.setTitle("UWAGA!");
        howToPlay.setHeaderText("Brak dostępnych weterynarzy!");
        howToPlay.setContentText(
                "Podany termin jest zajęty, zmień datę / godzinę wizyty!"
        );

        howToPlay.showAndWait();
    }

    @FXML
    public void popFindVetFirst(){
        Alert howToPlay = new Alert(Alert.AlertType.ERROR);
        howToPlay.setTitle("UWAGA!");
        howToPlay.setHeaderText("Musisz wcześniej kliknąć 'Znajdź weterynarza'");
        howToPlay.setContentText(
                "Musisz wpierw znaleźć wolnego weterynarza w podanym terminie!"
        );

        howToPlay.showAndWait();
        System.out.println("test");
    }
}
