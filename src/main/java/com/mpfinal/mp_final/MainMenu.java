package com.mpfinal.mp_final;

import com.mpfinal.mp_final.Model.System.ExtensionManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainMenu extends Application {

    private final static String INPUT_DIRECTORY = System.getProperty("user.dir")+System.getProperty("file.separator")+"saveFile.txt";
    private final static String OUTPUT_DIRECTORY = System.getProperty("user.dir")+System.getProperty("file.separator")+"saveFile.txt";

    private static AnchorPane root;
    private static Map<String, AnchorPane> windowsMap = new HashMap<>();
    private static String currentWindow;
    private static Stage stageMain;

    @Override
    public void start(Stage stage) throws IOException {
        //redo all saved objects
        ExtensionManager.readExtents(INPUT_DIRECTORY);

        stageMain = stage;
        //set base root
        root = FXMLLoader.load(getClass().getResource("baseOfAll.fxml"));

        //add all views
        windowsMap.put("mainMenu-view", FXMLLoader.load(getClass().getResource("mainMenu-view.fxml")));
        windowsMap.put("receptionist-view",FXMLLoader.load(getClass().getResource("receptionist-view.fxml")));
        windowsMap.put("vet-view", FXMLLoader.load(getClass().getResource("vet-view.fxml")));
        windowsMap.put("appointment-view",FXMLLoader.load(getClass().getResource("appointment-view.fxml")));

        //set mainMenu welcome widow
        root.getChildren().add(windowsMap.get("mainMenu-view"));
        currentWindow = "mainMenu-view";

        Scene scene = new Scene(root, ((AnchorPane)root.getChildren().get(0)).getPrefWidth(), ((AnchorPane)root.getChildren().get(0)).getPrefHeight());
        stage.setResizable(false);
        stage.setTitle("Przychodnia");
        stage.setScene(scene);
        stage.setWidth(scene.getWidth());
        stage.setHeight(scene.getHeight());
        stage.show();

        //when application is closed - save all changes
        stage.setOnCloseRequest(e -> {
            ExtensionManager.writeExtents(OUTPUT_DIRECTORY);
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Updates screen with proper window.
     * Do nothing if window is not added is windowsMap <String, AnchorPane>
     * @param windowName String
     */
    public static void setWindow(String windowName){
        if(windowsMap.containsKey(windowName)) {
            root.getChildren().remove(windowsMap.get(currentWindow));
            root.getChildren().add(windowsMap.get(windowName));
            stageMain.setWidth(windowsMap.get(windowName).getPrefWidth());
            stageMain.setHeight(windowsMap.get(windowName).getPrefHeight());
            currentWindow = windowName;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}