package entitites;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ArdoiseMagique extends Application {

    private Color selectedColor = Color.BLACK;
    private double brushSize = 2.0;

    @Override
    public void start(Stage primaryStage) {
        // Créer un canevas pour le dessin
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Bouton pour effacer le dessin
        Button clearButton = new Button("Effacer");
        clearButton.setOnAction(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        // Sélecteur de couleur
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> selectedColor = colorPicker.getValue());

        // Contrôle de la taille du pinceau
        Label brushSizeLabel = new Label("Taille du pinceau:");
        Slider brushSizeSlider = new Slider(1, 20, 2);
        brushSizeSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                brushSize = newValue.doubleValue());

        // Conteneur pour les contrôles de couleur et de taille de pinceau
        HBox controlsBox = new HBox(10, colorPicker, brushSizeLabel, brushSizeSlider);
        controlsBox.setPadding(new Insets(10));

        // Conteneur pour les contrôles
        VBox controlsVBox = new VBox(10, controlsBox, clearButton);
        controlsVBox.setPadding(new Insets(10));

        // Empiler le canevas et les contrôles dans une VBox
        VBox mainVBox = new VBox(canvas, controlsVBox);

        // Empiler la VBox principale dans une StackPane
        StackPane root = new StackPane();
        root.getChildren().addAll(mainVBox);

        // Créer la scène
        Scene scene = new Scene(root, 400, 450);

        // Gestion du dessin sur le canevas
        canvas.setOnMouseDragged(e -> {
            gc.setFill(selectedColor);
            gc.fillOval(e.getX() - brushSize / 2, e.getY() - brushSize / 2, brushSize, brushSize);
        });

        // Configurer la scène et afficher la fenêtre
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ardoise Magique");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
