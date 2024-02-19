package entitites;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class JeuQuizPane {

    private StackPane pane;
    private LudiKidsApplication ludiKidsApplication;
    private final Image flecheImage = new Image(getClass().getResourceAsStream("/images/fleche.png"));
    private final Image ecranImage = new Image(getClass().getResourceAsStream("/images/ecran.png"));

    public JeuQuizPane(LudiKidsApplication ludiKidsApplication) {
        this.ludiKidsApplication = ludiKidsApplication;
        pane = new StackPane();
        setupGui();
        setupEvents();
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
