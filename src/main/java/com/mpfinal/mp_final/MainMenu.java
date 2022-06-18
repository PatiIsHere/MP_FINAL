package com.mpfinal.mp_final;

import com.mpfinal.mp_final.Model.System.ExtensionManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Application {

    private final static String INPUT_DIRECTORY = System.getProperty("user.dir")+System.getProperty("file.separator")+"saveFile.txt";
    private final static String OUTPUT_DIRECTORY = System.getProperty("user.dir")+System.getProperty("file.separator")+"saveFile.txt";

    private static AnchorPane root;
    private static List<AnchorPane> anchorPanes = new ArrayList<>();
    private static int indexCurrent = 0;
    private static Stage stageMain;

    @Override
    public void start(Stage stage) throws IOException {
        ExtensionManager.readExtents(INPUT_DIRECTORY);
        stageMain = stage;

        root = FXMLLoader.load(getClass().getResource("baseOfAll.fxml"));



        anchorPanes.add(FXMLLoader.load(getClass().getResource("mainMenu-view.fxml")));
        anchorPanes.add(FXMLLoader.load(getClass().getResource("receptionist-view.fxml")));
        anchorPanes.add(FXMLLoader.load(getClass().getResource("vet-view.fxml")));
        anchorPanes.add(FXMLLoader.load(getClass().getResource("appointment-view.fxml")));

        root.getChildren().add(anchorPanes.get(0));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //Scene scene = new Scene(root, root.getWidth(), root.getHeight());
        Scene scene = new Scene(root, ((AnchorPane)root.getChildren().get(0)).getPrefWidth(), ((AnchorPane)root.getChildren().get(0)).getPrefHeight());
        stage.setTitle("Przychodnia");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            ExtensionManager.writeExtents(OUTPUT_DIRECTORY);
            Platform.exit();
            System.exit(0);
        });
    }
    public static void setAnchor(int index){
        root.getChildren().remove(anchorPanes.get(indexCurrent));
        root.getChildren().add(anchorPanes.get(index));
        stageMain.setWidth(anchorPanes.get(index).getPrefWidth());
        stageMain.setHeight(anchorPanes.get(index).getPrefHeight());
        indexCurrent = index;
    }

    public static void main(String[] args) {
        launch();
    }
}