package entitites;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class JeuArdoisePane {

    private StackPane pane;
    private LudiKidsApplication ludiKidsApplication;
    private final Image flecheImage = new Image(getClass().getResourceAsStream("/images/fleche.png"));
    private final Image ecranImage = new Image(getClass().getResourceAsStream("/images/ecran.png"));

    private Color currentColor = Color.BLACK;
    private double brushSize = 2.0;

    public JeuArdoisePane(LudiKidsApplication ludiKidsApplication) {
        this.ludiKidsApplication = ludiKidsApplication;
        pane = new StackPane();
        setupCanva();
        setupGui();
        setupEvents();
    }

    private void setCurrentColor(Color color) {
        currentColor = color;
    }

    private void setupGui() {

    }

    private void setupEvents() {
        ImageView ecranImageView = new ImageView(ecranImage);
        ecranImageView.setFitWidth(60);
        ecranImageView.setFitHeight(60);

        Button fullScreenButton = new Button();
        fullScreenButton.setGraphic(ecranImageView);
        fullScreenButton.setStyle("-fx-background-color: transparent");
        fullScreenButton.setOnAction(e -> ludiKidsApplication.toggleFullScreen());

        StackPane.setAlignment(fullScreenButton, Pos.BOTTOM_RIGHT);
        pane.getChildren().add(fullScreenButton);

        fullScreenButton.setOnMouseEntered(e -> {
            fullScreenButton.setScaleX(1.1);
            fullScreenButton.setScaleY(1.1);
        });

        fullScreenButton.setOnMouseExited(e -> {
            fullScreenButton.setScaleX(1.0);
            fullScreenButton.setScaleY(1.0);
        });

        ImageView flecheImageView = new ImageView(flecheImage);
        flecheImageView.setFitWidth(100);
        flecheImageView.setFitHeight(35);

        Button retourButton = new Button();
        retourButton.setGraphic(flecheImageView);
        retourButton.setStyle("-fx-background-color: transparent");
        retourButton.setOnAction(e -> ludiKidsApplication.toggleScreenAccueilGame());

        StackPane.setAlignment(retourButton, Pos.TOP_LEFT);
        pane.getChildren().add(retourButton);
    }

    private void setupCanva() {
        Canvas canvas = new Canvas(900, 540);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Button clearButton = new Button("Effacer");
        clearButton.setOnAction(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        Slider sizeSlider = new Slider(1, 20, 2);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            brushSize = newValue.doubleValue();
            gc.setLineWidth(brushSize); // Mettre à jour la taille du trait
        });
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> currentColor = colorPicker.getValue());
        
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.getChildren().addAll(sizeSlider, colorPicker, clearButton);

        StackPane.setAlignment(hbox, Pos.BOTTOM_CENTER);

        pane.getChildren().add(hbox); // Ajouter les deux au StackPane principal
        pane.getChildren().add(canvas);
        StackPane.setAlignment(canvas, Pos.CENTER); // Alignement du Canvas au centre

        canvas.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                gc.setStroke(currentColor);
                gc.beginPath();
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
        });

        canvas.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
        });

    }

    public StackPane getPane() {
        return pane;
    }
}
