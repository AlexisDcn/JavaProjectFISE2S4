package entitites;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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

    private void setupCanva() {
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Button blackButton = new Button("Noir");
        blackButton.setOnAction(e -> setCurrentColor(Color.BLACK));

        Button redButton = new Button("Rouge");
        redButton.setOnAction(e -> setCurrentColor(Color.RED));

        Button greenButton = new Button("Vert");
        greenButton.setOnAction(e -> setCurrentColor(Color.GREEN));

        Button blueButton = new Button("Bleu");
        blueButton.setOnAction(e -> setCurrentColor(Color.BLUE));

        Button clearButton = new Button("Effacer");
        clearButton.setOnAction(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        
        
        Slider sizeSlider = new Slider(1, 20, 2);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> brushSize = newValue.doubleValue());
        HBox hbox = new HBox(10, blackButton, redButton, greenButton, blueButton, clearButton, sizeSlider);
        hbox.setStyle("-fx-background-color: transparent");
        

        canvas.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                gc.setStroke(currentColor);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                gc.setStroke(Color.WHITE);
            }
            gc.beginPath();
            gc.lineTo(event.getX(), event.getY());
            gc.stroke();
        });

        canvas.setOnMouseDragged(event -> {
            gc.lineTo(event.getX(), event.getY());
            gc.stroke();
        });
        StackPane.setAlignment(hbox, Pos.CENTER);
        pane.getChildren().add(hbox);
        pane.getChildren().add(canvas);
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

    public StackPane getPane() {
        return pane;
    }
}