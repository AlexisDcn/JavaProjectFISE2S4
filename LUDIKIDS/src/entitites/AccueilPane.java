package entitites;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccueilPane {

    private final StackPane pane;
    private final LudiKidsApplication ludiKidsApplication;
    private final String motDePasseAdmin = "mdpadmin";
    private boolean loggedIn = false;
    private int failedLoginAttempts = 0;
    private final Image backgroundImage = new Image(getClass().getResourceAsStream("/images/fondaccueil.jpeg"));
    private final Image logoImage = new Image(getClass().getResourceAsStream("/images/logo.png"));
    private final Image flecheImage = new Image(getClass().getResourceAsStream("/images/fleche.png"));
    private final Image jouerImage = new Image(getClass().getResourceAsStream("/images/jouer.png"));
    private final Image bmoImage = new Image(getClass().getResourceAsStream("/images/bmo.png"));
    private final Image ecranImage = new Image(getClass().getResourceAsStream("/images/ecran.png"));

    public AccueilPane(LudiKidsApplication ludiKidsApplication) {
        this.ludiKidsApplication = ludiKidsApplication;
        pane = new StackPane();
        setupGui();
        setupEvents();
    }

    private void setupGui() {
        Stage primaryStage = ludiKidsApplication.getPrimaryStage();
        ImageView backImageView = new ImageView(backgroundImage);
        backImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        backImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        pane.getChildren().add(backImageView);

        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setPreserveRatio(true);
        logoImageView.setFitWidth(300);
        StackPane.setAlignment(logoImageView, Pos.TOP_LEFT);
        pane.getChildren().add(logoImageView);

    }

    private void setupEvents() {
        ImageView jouerImageView = new ImageView(jouerImage);
        jouerImageView.setFitWidth(200);
        jouerImageView.setPreserveRatio(true);
        jouerImageView.setSmooth(true);

        Button jouerButton = new Button();
        jouerButton.setGraphic(jouerImageView);
        jouerButton.setStyle("-fx-background-color: transparent");
        jouerButton.setOnAction(e -> ludiKidsApplication.toggleScreenAccueilGame());

        StackPane.setAlignment(jouerButton, Pos.BOTTOM_CENTER);
        pane.getChildren().add(jouerButton);

        jouerButton.setOnMouseEntered(e -> {
            jouerButton.setScaleX(1.2);
            jouerButton.setScaleY(1.2);
        });

        jouerButton.setOnMouseExited(e -> {
            jouerButton.setScaleX(1.0);
            jouerButton.setScaleY(1.0);
        });

        ImageView bmoImageView = new ImageView(bmoImage);
        bmoImageView.setFitWidth(150);
        bmoImageView.setFitHeight(150);

        Button loginButton = new Button();
        loginButton.setGraphic(bmoImageView);
        loginButton.setStyle("-fx-background-color: transparent");
        loginButton.setOnAction(e -> {
            if (!loggedIn && failedLoginAttempts < 3) {
                showLoginDialog(loginButton);
            } else if (failedLoginAttempts >= 3) {
                showAlert("Vous avez atteint le nombre maximal de tentatives de connexion.");
            }
        });
        StackPane.setAlignment(loginButton, Pos.BOTTOM_LEFT);
        pane.getChildren().add(loginButton);
        loginButton.setOnMouseEntered(e -> {
            loginButton.setScaleX(1.1);
            loginButton.setScaleY(1.1);
        });

        loginButton.setOnMouseExited(e -> {
            loginButton.setScaleX(1.0);
            loginButton.setScaleY(1.0);
        });

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

    }

    private VBox createLoginBox(Button originalLoginButton) {
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Connexion");
        VBox loginBox = new VBox(10, passwordField, loginButton);
        loginBox.setAlignment(Pos.CENTER);

        loginButton.setOnAction(e -> {
            if (checkPassword(passwordField.getText())) {
                loggedIn = true;
                ludiKidsApplication.toggleScreenAccueilGame();
            } else {
                failedLoginAttempts++;
                if (failedLoginAttempts >= 3) {
                    ludiKidsApplication.showAlert("Vous avez atteint le nombre maximal de tentatives de connexion.");
                    originalLoginButton.setDisable(true);
                } else {
                    ludiKidsApplication.showAlert("Mot de passe incorrect.\n" + "Tentatives restantes : " + (3 - failedLoginAttempts));
                }
            }
        });

        originalLoginButton.setDisable(true);

        return loginBox;
    }

    private void showLoginDialog(Button originalLoginButton) {
        Stage dialogStage = new Stage();
        dialogStage.initOwner(ludiKidsApplication.getPrimaryStage());
        dialogStage.setTitle("Se connecter");
        VBox loginBox = createLoginBox(originalLoginButton);

        Scene dialogScene = new Scene(loginBox, 300, 200);
        dialogScene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        dialogStage.setScene(dialogScene);

        dialogStage.setOnCloseRequest(event -> originalLoginButton.setDisable(false));

        dialogStage.show();
    }

    private boolean checkPassword(String inputPassword) {
        return inputPassword.equals(motDePasseAdmin);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public StackPane getPane() {
        return pane;
    }
}
