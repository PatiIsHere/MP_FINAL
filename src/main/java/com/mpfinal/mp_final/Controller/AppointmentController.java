package com.mpfinal.mp_final.Controller;

import com.mpfinal.mp_final.MainMenu;
import com.mpfinal.mp_final.Model.Animals.Animal;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.CustomModelExceptions.*;
import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.Internal.Employee;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AppointmentController implements Initializable {

    private Employee assignedVet = null;

    @FXML
    private ChoiceBox clientID;
    @FXML
    private ChoiceBox dateOfVisit;
    @FXML
    private ChoiceBox hourOfVisit;
    @FXML
    private ChoiceBox animalID;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private Button saveAppointmentButton;
    @FXML
    private Button searchForVetButton;

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

            //adds calls on action to check if client has empty spot in this time
            dateOfVisit.setOnAction(actionEvent -> {
                clearAfterDateUpdate();
                checkPossibilityInClient();
            });
            hourOfVisit.setOnAction(actionEvent -> checkPossibilityInClient());
            clientID.setOnAction(actionEvent -> {
                clearAfterClientIdUpdate();
                getClientAnimalsIDs();
            });

            descriptionTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
                if (!newPropertyValue)
                {
                    //test();
                }
            });

    }

    /**
     * Plan - do customerId dac setOnAction by wyszukal i uzupelnil this.private.CustomerToProcess
     * jak bedzie customer to uzupelnic animals id
     * 
     *
     */

    /**
     * If all fields contains data - return true.
     * @return boolean
     */
    private boolean areAllFieldsFilled(){
        boolean clientIdIsFilled = clientID.getValue() != null;
        boolean dateIsFilled = dateOfVisit.getValue() != null;
        boolean hourIsFilled = hourOfVisit.getValue() != null;
        boolean animalIsFilled = animalID.getValue() != null;
        boolean descriptionIsFilled = descriptionTextField.getText() != null && descriptionTextField.getText() != "";

        return (clientIdIsFilled &&
                dateIsFilled &&
                hourIsFilled &&
                animalIsFilled &&
                descriptionIsFilled);
    }

    /**
     * Creates appointment based on provided data, makes associations and shows pop-up success window.
     */
    @FXML
    private void saveAppointment(){
        if(assignedVet == null){
            popFindVetFirst();
            return;
        }
        if(!areAllFieldsFilled()){
            popFillAllChoiseBoxes();
            return;
        }
        Client temp = null;
        try {
            List<Client> tempClientList = ExtensionManager.getExtent(Client.class);

                temp = tempClientList.stream()
                    .filter(e -> e.getId() == Integer.parseInt(clientID.getValue().toString()))
                    .findFirst().get();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Employee receptionist = null;
        try {
            List<Employee> tempEmpList = ExtensionManager.getExtent(Employee.class);

                receptionist = tempEmpList.stream()
                    .filter(e -> e.getId() == MainMenuController.employeeID)
                    .findFirst().get();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //create appointment and make associations
            Appointment appointment = new Appointment((LocalDate) dateOfVisit.getValue(), Integer.parseInt(hourOfVisit.getValue().toString()),descriptionTextField.getText());
            appointment.addReceptionist(receptionist);
            appointment.addVet(assignedVet);
            appointment.addClient(temp);
            try {
                appointment.addMedicalCard(
                        temp.getClientAnimals().stream()
                                .filter(e -> e.getId() == Integer.parseInt(animalID.getValue().toString()))
                                .findFirst().get().getMedicalCard()
                );
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
            popSuccessWindow();
            clearAllSelection();

        } catch (OpeningHoursException | EmployeeRoleException | DoubleAssignmentException e) {
            e.printStackTrace();
        }

        //region test podglad ekstencji
        try {
            List<Appointment> appointmentList = ExtensionManager.getExtent(Appointment.class);
            List<Employee> empList = ExtensionManager.getExtent(Employee.class);
            List<Client> clients = ExtensionManager.getExtent(Client.class);
            int abc = 1;
            if(abc == 1){
                System.out.println("x");
            }


            System.out.println("ccc");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //endregion test podglad ekstencji
    }

    /**
     * Clears all selections in gui.
     */
    private void clearAllSelection(){
        clientID.valueProperty().set(null);
        dateOfVisit.valueProperty().set(null);
        hourOfVisit.valueProperty().set(null);
        animalID.valueProperty().set(null);
        assignedVet = null;
        descriptionTextField.textProperty().set(null);
    }

    /**
     * Clears all selection beside clientId.
     */
    private void clearAfterClientIdUpdate(){
        dateOfVisit.valueProperty().set(null);
        hourOfVisit.valueProperty().set(null);
        animalID.valueProperty().set(null);
        assignedVet = null;
        descriptionTextField.textProperty().set(null);
    }

    /**
     * Clears hour selection after date update.
     */
    private void clearAfterDateUpdate(){
        hourOfVisit.valueProperty().set(null);
        assignedVet = null;
    }

    /**
     * Goes back to receptionist-view window.
     * @see MainMenu#setWindow(String)
     */
    @FXML
    private void goBackToPreviousWindow(){
        MainMenu.setWindow("receptionist-view");
    }


    /**
     * Searches for avaible vet and assigns it to static assignedVet.
     * If no vet is avaible - show pop-up window with appropriate information.
     */
    @FXML
    private void searchForVet(){
        assignedVet = null;
        if (!areAllFieldsFilled()){
            popFillAllChoiseBoxes();
            return;
        }
        List<Employee> tempEmpList = null;
        try {
            tempEmpList = ExtensionManager.getExtent(Employee.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assignedVet = tempEmpList.stream()
                .filter(Employee::isVet)
                .filter(e -> e.getId() != MainMenuController.employeeID)
                .filter(e -> e.isVetAvaible(
                        (LocalDate) dateOfVisit.getValue()
                        , Integer.parseInt(hourOfVisit.getValue().toString()))
                )
                .findFirst().orElse(null);
        if (assignedVet == null){
            popNoVetWindow();
        }

    }

    /**
     * Fills animalId choisebox with id of animals of selected client if animal has medicalCard.
     */
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
                .filter(e -> e.getId() == Integer.parseInt(clientID.getValue().toString()))
                .findFirst().get();

        animalID.setItems(FXCollections.observableArrayList(
                tempClient.getClientAnimals().stream()
                        .filter(e -> {
                            try {
                                return e.getMedicalCard() != null;
                            } catch (ObjectNotFoundException ex) {
                                return false;
                            }
                        })
                        .map(Animal::getId)
                        .collect(Collectors.toList())));
    }

    /**
     * Verifies if selected client doesn't have any appointment in selected date and hour.
     * If date/hour is occupied - shows pop-up window with appropriate information and clears selected hour.
     * @see Client#isDateAndHourOccupied(LocalDate, int)
     * {@link #popBadDateAndHour()}
     */
    private void checkPossibilityInClient(){
        if(dateOfVisit.getValue() == null || hourOfVisit.getValue() == null){
            return;
        }
        Client temp;
        try {
            //TODO Ask prowadzący - czemu robiac stream z ExtensionManager przy filtracji jest IllegalAccessError?
            //todo na razie wrzucanie 'kopii' dziala
            //retrive client
            List<Client> tempClientList = ExtensionManager.getExtent(Client.class);

                temp = tempClientList.stream()
                    .filter(e -> e.getId() == Integer.parseInt(clientID.getValue().toString()))
                    .findFirst().get();

                //check if date/hour is occupied
            if(temp.isDateAndHourOccupied((LocalDate) dateOfVisit.getValue(), Integer.parseInt(hourOfVisit.getValue().toString()))){
                popBadDateAndHour();
                //clear selected hour in GUI
                hourOfVisit.getSelectionModel().clearSelection();
                hourOfVisit.valueProperty().set(null);
                return;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates pop-up window when date/hour is occupied in client.
     */
    @FXML
    public void popBadDateAndHour(){
        Alert badDateAndHour = new Alert(Alert.AlertType.ERROR);
        badDateAndHour.setTitle("UWAGA!");
        badDateAndHour.setHeaderText("Klient posiada umówioną wizytę w tym terminie!");
        badDateAndHour.setContentText(
                "Zmień datę / godzinę wizyty."
        );

        badDateAndHour.showAndWait();
    }

    /**
     * Creates pop-up window when appointment creation is successful.
     */
    @FXML
    public void popSuccessWindow(){
        Alert success = new Alert(Alert.AlertType.CONFIRMATION);
        success.setTitle("SUKCES!");
        success.setHeaderText("Rezerwacja wizyty została pomyślnie zapisana.");
        success.setContentText("Wizyta zostanie obsłużona przez: " + assignedVet.getName()+" "+assignedVet.getSurname());

        success.showAndWait();
    }

    /**
     * Creates pop-up window when no vet is avaible in selected date/time.
     */
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

    /**
     * Creates pop-up window when find vet action wasn't performed.
     */
    @FXML
    public void popFindVetFirst(){
        Alert findVetFirst = new Alert(Alert.AlertType.ERROR);
        findVetFirst.setTitle("UWAGA!");
        findVetFirst.setHeaderText("Nie kliknięto 'Znajdź weterynarza'!");
        findVetFirst.setContentText(
                "Musisz wpierw znaleźć wolnego weterynarza w podanym terminie."
        );

        findVetFirst.showAndWait();
        System.out.println("test");
    }

    /**
     * Creates pop-up window when not all data is prided.
     */
    @FXML
    public void popFillAllChoiseBoxes(){
        Alert fillAllChoiseBoxes = new Alert(Alert.AlertType.ERROR);
        fillAllChoiseBoxes.setTitle("UWAGA!");
        fillAllChoiseBoxes.setHeaderText("Brakuje danych!");
        fillAllChoiseBoxes.setContentText(
                "Przed znalezieniem weterynarza musisz uzupełnić wszystkie pola."
        );

        fillAllChoiseBoxes.showAndWait();
        System.out.println("test");
    }


}
