/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitites;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author alexi
 */
public class AdminPane {

    private final StackPane pane;
    private final LudiKidsApplication ludiKidsApplication;
    private final Image fondordiImage = new Image(getClass().getResourceAsStream("/images/fondordi.jpg"));
    private final Image flecheImage = new Image(getClass().getResourceAsStream("/images/fleche.png"));

    public AdminPane(LudiKidsApplication ludiKidsApplication) {
        this.ludiKidsApplication = ludiKidsApplication;
        pane = new StackPane();
        setupGui();
        setupEvents();
    }

    private void setupGui() {
        ImageView fondordiImageView = new ImageView(fondordiImage);
        fondordiImageView.fitWidthProperty().bind(pane.widthProperty());
        fondordiImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(fondordiImageView);

        Text text = new Text();
        text.setText("LOGGING IN....\nLOGGED IN.\n\nYOU ARE NOW THE ADMIN !");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Verdana", 50));
        text.setFill(Color.WHITE);

        pane.getChildren().add(text);
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
    }

    public StackPane getPane() {
        return pane;
    }
}
