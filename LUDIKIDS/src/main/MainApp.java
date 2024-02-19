package main;

import entitites.LudiKidsApplication;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private LudiKidsApplication ludiKidsApplication;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.ludiKidsApplication = new LudiKidsApplication(primaryStage);

        ludiKidsApplication.startApp();
    }
}
