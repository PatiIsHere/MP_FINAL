package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.MainMenu;
import com.mpfinal.mp_final.Model.Animals.Animal;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AppointmentController implements Initializable {

    @FXML
    private ChoiceBox clientID;
    @FXML
    private ChoiceBox dateOfVisit;
    @FXML
    private ChoiceBox hourOfVisit;
    @FXML
    private ChoiceBox animalID;
    @FXML
    private Button saveAppointmentButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //prepare clients id list
        try {
            clientID.setItems(FXCollections.observableArrayList (
                   ExtensionManager.getExtent(Client.class).stream()
                           .map(Client::getId)
                           .collect(Collectors.toList())));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        clientID.setOnAction(
                actionEvent -> getClientAnimalsIDs()
        );
        //prepare 7 days to choose
        dateOfVisit.setItems(FXCollections.observableArrayList(
                LocalDate.now().datesUntil(LocalDate.now().plusDays(7)).collect(Collectors.toList())
        ));


        //prepare acceptable hours of Appointment
        List<String> acceptableHours = new ArrayList<>();
        for (int i = Appointment.OPENING_HOUR; i < Appointment.CLOSING_HOUR; i++) {
            acceptableHours.add(String.valueOf(i));
        }

        hourOfVisit.setItems(FXCollections.observableArrayList(
                acceptableHours
        ));

        dateOfVisit.setOnAction(actionEvent -> checkPossibilityInClient());
        hourOfVisit.setOnAction(actionEvent -> checkPossibilityInClient());

    }

    private void getClientAnimalsIDs(){
        if (clientID.getValue() == null){
            return;
        }
        List<Client> tempClientList = null;
        try {
            tempClientList = ExtensionManager.getExtent(Client.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Client tempClient = tempClientList.stream()
                .filter(e -> e.getId() == Integer.valueOf(clientID.getValue().toString()).intValue())
                .findFirst().get();

        animalID.setItems(FXCollections.observableArrayList(
                tempClient.getClientAnimals().stream()
                        .map(Animal::getId)
                        .collect(Collectors.toList())));
    }

    private void checkPossibilityInClient(){
        if(dateOfVisit.getValue() == null || hourOfVisit.getValue() == null){
            return;
        }
        Client temp;
        try {
            //TODO Ask prowadzący - czemu robiac stream z ExtensionManager przy filtracji jest IllegalAccessError?
            //todo na razie wrzucanie 'kopii' dziala
            List<Client> tempClientList = ExtensionManager.getExtent(Client.class);

            Client tempClient = tempClientList.stream()
                    .filter(e -> e.getId() == Integer.valueOf(clientID.getValue().toString()).intValue())
                    .findFirst().get();

            if(tempClient.isDateAndHourOccupied((LocalDate) dateOfVisit.getValue(), Integer.valueOf(clientID.getValue().toString()).intValue())){
                popBadDateAndHour();
                return;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void goBack(){
        MainMenu.setWindow("receptionist-view");
    }

    @FXML
    public void popBadDateAndHour(){
        Alert noVetAvaible = new Alert(Alert.AlertType.ERROR);
        noVetAvaible.setTitle("UWAGA!");
        noVetAvaible.setHeaderText("Klient posiada umówioną wizytę w tym terminie!");
        noVetAvaible.setContentText(
                "Zmień datę / godzinę wizyty."
        );

        noVetAvaible.showAndWait();
    }

    @FXML
    public void popSuccessWindow(){
        Alert success = new Alert(Alert.AlertType.CONFIRMATION);
        success.setTitle("SUKCES!");
        success.setHeaderText("Rezerwacja wizyty została pomyślnie zapisana.");
        success.setContentText("Wizyta zostanie obsłużona przez: " + "Imie, Nazwisko");

        success.showAndWait();
    }

    @FXML
    public void popNoVetWindow(){
        Alert noVetAvaible = new Alert(Alert.AlertType.ERROR);
        noVetAvaible.setTitle("UWAGA!");
        noVetAvaible.setHeaderText("Brak dostępnych weterynarzy!");
        noVetAvaible.setContentText(
                "Podany termin jest zajęty, zmień datę / godzinę wizyty."
        );

        noVetAvaible.showAndWait();
    }

    @FXML
    public void popFindVetFirst(){
        Alert howToPlay = new Alert(Alert.AlertType.ERROR);
        howToPlay.setTitle("UWAGA!");
        howToPlay.setHeaderText("Nie kliknięto 'Znajdź weterynarza'!");
        howToPlay.setContentText(
                "Musisz wpierw znaleźć wolnego weterynarza w podanym terminie."
        );

        howToPlay.showAndWait();
        System.out.println("test");
    }


}
