package com.mpfinal.mp_final;

import com.mpfinal.mp_final.Model.System.ExtensionManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainMenu extends Application {

    private final static String INPUT_DIRECTORY = System.getProperty("user.dir")+System.getProperty("file.separator")+"saveFile.txt";
    private final static String OUTPUT_DIRECTORY = System.getProperty("user.dir")+System.getProperty("file.separator")+"saveFile.txt";

    @Override
    public void start(Stage stage) throws IOException {
        ExtensionManager.readExtents(INPUT_DIRECTORY);
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("mainMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            ExtensionManager.writeExtents(OUTPUT_DIRECTORY);
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}