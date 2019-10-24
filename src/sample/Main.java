package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;


public class Main extends Application {

    public Group root = new Group();

    // properties for transitioning white mana icons //
    public double orgWSceneX;
    public double orgWSceneY;
    public double orgWTranslateX;
    public double orgWTranslateY;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Set up the main pane
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color:#204161;");
        canvas.setPrefSize(800,750);

        // Mana strip with all available mana
        ImageView availableMana = new ImageView("/assets/Mana_Strip.png");
        availableMana.setFitWidth(460);
        availableMana.setFitHeight(130);
        availableMana.setLayoutX(170);
        availableMana.setLayoutY(5);
        availableMana.setPreserveRatio(true);
        availableMana.setSmooth(true);
        availableMana.setCache(true);

        // Card Template //
        ImageView card = new ImageView("/assets/Magic_Card_Template.png");
        card.setFitWidth(283);
        card.setFitHeight(379);
        card.setLayoutX(258.5);
        card.setLayoutY(236);
        card.setPreserveRatio(true);
        card.setSmooth(true);
        card.setCache(true);

        // White Mana Icon //
        ImageView whiteMana = new ImageView("/assets/White_Mana.png");
        whiteMana.setFitWidth(50);
        whiteMana.setFitHeight(50);
        whiteMana.setLayoutX(185);
        whiteMana.setLayoutY(70);
        whiteMana.setPreserveRatio(true);
        whiteMana.setSmooth(true);
        whiteMana.setCache(true);
        whiteMana.setOnMousePressed(whiteManaSelected);
        whiteMana.setOnMouseDragged(whiteManaDragged);
        whiteMana.setOnMouseReleased(whiteManaUnselected);

        // Blue Mana Icon //
        ImageView blueMana = new ImageView("/assets/Blue_Mana.png");
        blueMana.setFitWidth(50);
        blueMana.setFitHeight(50);
        blueMana.setLayoutX(261);
        blueMana.setLayoutY(70);
        blueMana.setPreserveRatio(true);
        blueMana.setSmooth(true);
        blueMana.setCache(true);

        // Black Mana Icon //
        ImageView blackMana = new ImageView("/assets/Black_Mana.png");
        blackMana.setFitWidth(50);
        blackMana.setFitHeight(50);
        blackMana.setLayoutX(337);
        blackMana.setLayoutY(70);
        blackMana.setPreserveRatio(true);
        blackMana.setSmooth(true);
        blackMana.setCache(true);

        // Red Mana Icon //
        ImageView redMana = new ImageView("/assets/Red_Mana.png");
        redMana.setFitWidth(50);
        redMana.setFitHeight(50);
        redMana.setLayoutX(413);
        redMana.setLayoutY(70);
        redMana.setPreserveRatio(true);
        redMana.setSmooth(true);
        redMana.setCache(true);

        // Green Mana Icon //
        ImageView greenMana = new ImageView("/assets/Green_Mana.png");
        greenMana.setFitWidth(50);
        greenMana.setFitHeight(50);
        greenMana.setLayoutX(489);
        greenMana.setLayoutY(70);
        greenMana.setPreserveRatio(true);
        greenMana.setSmooth(true);
        greenMana.setCache(true);

        // Colorless Mana Icon //
        ImageView colorlessMana = new ImageView("/assets/Colorless_Mana.png");
        colorlessMana.setFitWidth(50);
        colorlessMana.setFitHeight(50);
        colorlessMana.setLayoutX(565);
        colorlessMana.setLayoutY(70);
        colorlessMana.setPreserveRatio(true);
        colorlessMana.setSmooth(true);
        colorlessMana.setCache(true);

        // Here we include labels that serve as mana counters //
        Label whiteCounter = new Label("100");
        whiteCounter.setTextFill(Color.WHITE);
        whiteCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        whiteCounter.setAlignment(CENTER);
        whiteCounter.setPrefSize(44,28);
        whiteCounter.setLayoutX(188);
        whiteCounter.setLayoutY(29);

        Label blueCounter = new Label("100");
        blueCounter.setTextFill(Color.WHITE);
        blueCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        blueCounter.setAlignment(CENTER);
        blueCounter.setPrefSize(44,28);
        blueCounter.setLayoutX(264);
        blueCounter.setLayoutY(29);

        Label blackCounter = new Label("100");
        blackCounter.setTextFill(Color.WHITE);
        blackCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        blackCounter.setAlignment(CENTER);
        blackCounter.setPrefSize(44,28);
        blackCounter.setLayoutX(340);
        blackCounter.setLayoutY(29);

        Label redCounter = new Label("100");
        redCounter.setTextFill(Color.WHITE);
        redCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        redCounter.setAlignment(CENTER);
        redCounter.setPrefSize(44,28);
        redCounter.setLayoutX(416);
        redCounter.setLayoutY(29);

        Label greenCounter = new Label("100");
        greenCounter.setTextFill(Color.WHITE);
        greenCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        greenCounter.setAlignment(CENTER);
        greenCounter.setPrefSize(44,28);
        greenCounter.setLayoutX(492);
        greenCounter.setLayoutY(29);

        Label colorlessCounter = new Label("100");
        colorlessCounter.setTextFill(Color.WHITE);
        colorlessCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        colorlessCounter.setAlignment(CENTER);
        colorlessCounter.setPrefSize(44,28);
        colorlessCounter.setLayoutX(568);
        colorlessCounter.setLayoutY(29);

        // Drag an item //


        root.getChildren().addAll(canvas, availableMana, card, whiteMana, blueMana, blackMana, redMana, greenMana, colorlessMana,
                whiteCounter, blueCounter, blackCounter, redCounter, greenCounter, colorlessCounter);

        primaryStage.setTitle("MTG Mana Tracker v.0.1.0");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 800, 750));
        primaryStage.show();
    }

    EventHandler<MouseEvent> whiteManaSelected =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    System.out.println("NEW ICON CREATED");
                    ImageView oldIcon = new ImageView("/assets/White_Mana.png");
                    oldIcon.setFitWidth(50);
                    oldIcon.setFitHeight(50);
                    oldIcon.setLayoutX(185);
                    oldIcon.setLayoutY(70);
                    oldIcon.setPreserveRatio(true);
                    oldIcon.setSmooth(true);
                    oldIcon.setCache(true);
                    oldIcon.setOnMousePressed(whiteManaSelected);
                    oldIcon.setOnMouseDragged(whiteManaDragged);
                    oldIcon.setOnMouseReleased(whiteManaUnselected);

                    root.getChildren().add(oldIcon);

                    orgWSceneX = t.getSceneX();
                    orgWSceneY = t.getSceneY();
                    orgWTranslateX = ((ImageView)(t.getSource())).getTranslateX();
                    orgWTranslateY = ((ImageView)(t.getSource())).getTranslateY();
                    //((ImageView)t.getSource()).setImage(new Image("/assets/White_Mana.png"));
                }
            };

    EventHandler<MouseEvent> whiteManaUnselected =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    // If we didn't get it to the container then just throw it away
                    root.getChildren().remove(t.getSource());
                }
            };

    EventHandler<MouseEvent> whiteManaDragged =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    // Here we should create a new image //
                    ((ImageView)t.getSource()).setImage(new Image("/assets/White_Mana_2.png"));

                    double offsetX = t.getSceneX() - orgWSceneX;
                    double offsetY = t.getSceneY() - orgWSceneY;
                    double newTranslateX = orgWTranslateX + offsetX;
                    double newTranslateY = orgWTranslateY + offsetY;

                    ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
                    ((ImageView)(t.getSource())).setTranslateY(newTranslateY);
                }
            };


    public static void main(String[] args) {
        launch(args);
    }
}
