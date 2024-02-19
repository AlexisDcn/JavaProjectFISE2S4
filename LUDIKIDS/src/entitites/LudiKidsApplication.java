package entitites;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LudiKidsApplication {

    private Stage primaryStage;
    private AccueilPane accueilPane;
    private BaseJeuPane gamePane;
    private JeuArdoisePane ardoisePane;
    private JeuQuizPane quizPane;
    private JeuAdditionPane additionPane;
    private StackPane currentPane;

    public LudiKidsApplication(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.accueilPane = new AccueilPane(this);
        this.gamePane = new BaseJeuPane(this);
        this.ardoisePane = new JeuArdoisePane(this);
        this.quizPane = new JeuQuizPane(this);
        this.additionPane = new JeuAdditionPane(this);
    }

    public void startApp() {
        primaryStage.setTitle("LudiKids");
        Scene scene = new Scene(accueilPane.getPane(), 1080, 720);
        primaryStage.setScene(scene);

        Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
        primaryStage.getIcons().add(logo);

        primaryStage.show();

        scene.widthProperty().addListener((obs, oldVal, newVal) -> handleSizeChange());
        scene.heightProperty().addListener((obs, oldVal, newVal) -> handleSizeChange());

        currentPane = accueilPane.getPane();
    }

    private void toggleScreen(StackPane screen) {
        primaryStage.getScene().setRoot(screen);
        currentPane = screen;
    }

    public void toggleScreenAccueil() {
        toggleScreen(accueilPane.getPane());
    }

    public void toggleScreenAccueilGame() {
        toggleScreen(gamePane.getPane());
    }

    public void toggleScreenGameArdoise() {
        toggleScreen(ardoisePane.getPane());
    }

    public void toggleScreenGameQuiz() {
        toggleScreen(quizPane.getPane());
    }

    public void toggleScreenGameAddition() {
        toggleScreen(additionPane.getPane());
    }

    private void handleSizeChange() {
        setFullScreen(primaryStage.isFullScreen());
    }

    public void toggleFullScreen() {
        setFullScreen(!primaryStage.isFullScreen());
    }

    private void setFullScreen(boolean fullScreen) {
        primaryStage.setFullScreen(fullScreen);
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /*
    public void showGamePane(StackPane stackPane) {
        primaryStage.setScene(new Scene(stackPane, 1080, 720));
    }

    public void showJeuArdoisePane() {
        if (ardoisePane == null) {
            ardoisePane = new JeuArdoisePane(this);
        }
        primaryStage.setScene(new Scene(ardoisePane.getPane(), 1080, 720));
        currentPane = ardoisePane.getPane();
    }
     */

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
