package entitites;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class BaseJeuPane {

    private final StackPane pane;
    private final LudiKidsApplication ludiKidsApplication;
    private final Image backgroundImage = new Image(getClass().getResourceAsStream("/images/fondstable.jpeg"));
    private final Image flecheImage = new Image(getClass().getResourceAsStream("/images/fleche.png"));
    private final Image nomImage = new Image(getClass().getResourceAsStream("/images/nom.png"));
    private final Image ecranImage = new Image(getClass().getResourceAsStream("/images/ecran.png"));
    private final Image flammeImage = new Image(getClass().getResourceAsStream("/images/flamme.png"));
    private final Image voleImage = new Image(getClass().getResourceAsStream("/images/vole.png"));
    private final Image bbgImage = new Image(getClass().getResourceAsStream("/images/bbg.png"));

    public BaseJeuPane(LudiKidsApplication ludiKidsApplication) {
        this.ludiKidsApplication = ludiKidsApplication;
        pane = new StackPane();
        setupGui();
        setupEvents();
    }

    private void setupGui() {
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(pane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(backgroundImageView);

        ImageView nomImageView = new ImageView(nomImage);
        nomImageView.setFitWidth(110);
        nomImageView.setFitHeight(39);

        StackPane.setAlignment(nomImageView, Pos.TOP_CENTER);
        pane.getChildren().add(nomImageView);

    }

    private void setupEvents() {

        ImageView flecheImageView = new ImageView(flecheImage);
        flecheImageView.setFitWidth(100);
        flecheImageView.setFitHeight(35);

        Button retourButton = new Button();
        retourButton.setGraphic(flecheImageView);
        retourButton.setStyle("-fx-background-color: transparent");
        retourButton.setOnAction(e -> ludiKidsApplication.toggleScreenAccueil());

        StackPane.setAlignment(retourButton, Pos.TOP_LEFT);
        pane.getChildren().add(retourButton);

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

        ImageView flammeImageView = new ImageView(flammeImage);
        flammeImageView.setFitWidth(100);
        flammeImageView.setPreserveRatio(true);

        Button flammeButton = new Button();
        flammeButton.setGraphic(flammeImageView);
        flammeButton.setStyle("-fx-background-color: transparent");
        flammeButton.setOnAction(e -> ludiKidsApplication.toggleScreenGameQuiz());

        StackPane.setAlignment(flammeButton, Pos.BOTTOM_CENTER);
        pane.getChildren().add(flammeButton);

        flammeButton.setOnMouseEntered(e -> {
            flammeButton.setScaleX(1.05);
            flammeButton.setScaleY(1.05);
        });

        flammeButton.setOnMouseExited(e -> {
            flammeButton.setScaleX(1.0);
            flammeButton.setScaleY(1.0);
        });

        ImageView voleImageView = new ImageView(voleImage);
        voleImageView.setFitWidth(250);
        voleImageView.setPreserveRatio(true);

        Button voleButton = new Button();
        voleButton.setGraphic(voleImageView);
        voleButton.setStyle("-fx-background-color: transparent");
        voleButton.setOnAction(e -> ludiKidsApplication.toggleScreenGameArdoise());

        StackPane.setAlignment(voleButton, Pos.TOP_RIGHT);
        pane.getChildren().add(voleButton);

        voleButton.setOnMouseEntered(e -> {
            voleButton.setScaleX(1.05);
            voleButton.setScaleY(1.05);
        });

        voleButton.setOnMouseExited(e -> {
            voleButton.setScaleX(1.0);
            voleButton.setScaleY(1.0);
        });

        ImageView bbgImageView = new ImageView(bbgImage);
        bbgImageView.setFitWidth(100);
        bbgImageView.setPreserveRatio(true);

        Button bbgButton = new Button();
        bbgButton.setGraphic(bbgImageView);
        bbgButton.setStyle("-fx-background-color: transparent");
        bbgButton.setOnAction(e -> ludiKidsApplication.toggleScreenGameAddition());

        StackPane.setAlignment(bbgButton, Pos.BOTTOM_LEFT);
        pane.getChildren().add(bbgButton);

        bbgButton.setOnMouseEntered(e -> {
            bbgButton.setScaleX(1.05);
            bbgButton.setScaleY(1.05);
        });

        bbgButton.setOnMouseExited(e -> {
            bbgButton.setScaleX(1.0);
            bbgButton.setScaleY(1.0);
        });

    }

    public StackPane getPane() {
        return pane;
    }
}
