package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.*;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.geometry.Pos.CENTER;


public class Main extends Application {

    // -------- ROOT OBJECT -------- //
    private Group root = new Group();

    // -------- MANA ICONS -------- //
    private ImageView whiteMana = new ImageView();
    private ImageView blueMana = new ImageView();
    private ImageView blackMana = new ImageView();
    private ImageView redMana = new ImageView();
    private ImageView greenMana = new ImageView();
    private ImageView colorlessMana = new ImageView();

    // -------- MANA LABELS -------- //
    private Label whiteCounter = new Label("0");
    private Label blueCounter = new Label("0");
    private Label blackCounter = new Label("0");
    private Label redCounter = new Label("0");
    private Label greenCounter = new Label("0");
    private Label colorlessCounter = new Label("0");
    private Label totalCounter = new Label("0");

    // -------- MANA TRANSITION -------- //
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;

    // -------- CARD SELECTION -------- //
    private Label newSpell = new Label();
    private ImageView discardCard = new ImageView();
    private ImageView addCard = new ImageView();
    private ImageView card = new ImageView();

    // -------- SPELL HISTORY -------- //
    private ImageView spellHistory = new ImageView();
    private ListView createdSpells = new ListView();

    // -------- SPELL INFO -------- //
    private ImageView spellInfo = new ImageView();
    private Label spellName = new Label();
    private Label whiteManaAdded = new Label("0");
    private Label blueManaAdded = new Label("0");
    private Label blackManaAdded = new Label("0");
    private Label redManaAdded = new Label("0");
    private Label greenManaAdded = new Label("0");
    private Label colorlessManaAdded = new Label("0");
    private Label totalManaAdded = new Label("0");


    // -------- MANA DRAGGED FLAGS -------- //
    private boolean manaIsDragged = false;
    private boolean whiteManaDragged = false;
    private boolean blueManaDragged = false;
    private boolean blackManaDragged = false;
    private boolean redManaDragged = false;
    private boolean greenManaDragged = false;
    private boolean colorlessManaDragged = false;

    // -------- OTHER FLAGS -------- //
    private boolean cardToggled = false;
    private boolean spellHistoryToggled = false;
    private boolean spellInfoToggled = false;
    private boolean spellInitialized = false;

    // -------- SPELL LIST -------- //
    ArrayList<Spells> spells = new ArrayList<Spells>();

    Spells pending; // This is for spells that are in the process of being created


    @Override
    public void start(Stage primaryStage) throws Exception{

        /*-------------------------------------------------- SETUP --------------------------------------------------*/

        // CANVAS //
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color:#204161;");
        canvas.setPrefSize(800,750);

        // AVAILABLE MANA STRIP //
        ImageView availableMana = new ImageView("/assets/Mana_Strip.png");
        availableMana.setFitWidth(460);
        availableMana.setFitHeight(130);
        availableMana.setLayoutX(170);
        availableMana.setLayoutY(5);
        availableMana.setPreserveRatio(true);
        availableMana.setSmooth(true);
        availableMana.setCache(true);

        ImageView totalMana = new ImageView("/assets/Total_Mana.png");
        totalMana.setFitWidth(100);
        totalMana.setFitHeight(50);
        totalMana.setLayoutX(350);
        totalMana.setLayoutY(125);
        totalMana.setPreserveRatio(true);
        totalMana.setSmooth(true);
        totalMana.setCache(true);

        // CARD ICON //
        card.setImage(new Image("/assets/Magic_Card_Template.png"));
        card.setFitWidth(283);
        card.setFitHeight(379);
        card.setLayoutX(258.5);
        card.setLayoutY(236);
        card.setPreserveRatio(true);
        card.setSmooth(true);
        card.setCache(true);

        // WHITE MANA ICON //
        whiteMana.setImage(new Image("/assets/White_Mana.png"));
        whiteMana.setFitWidth(50);
        whiteMana.setFitHeight(50);
        whiteMana.setLayoutX(185);
        whiteMana.setLayoutY(70);
        whiteMana.setPreserveRatio(true);
        whiteMana.setSmooth(true);
        whiteMana.setCache(true);

        // BLUE MANA ICON //
        blueMana.setImage(new Image("/assets/Blue_Mana.png"));
        blueMana.setFitWidth(50);
        blueMana.setFitHeight(50);
        blueMana.setLayoutX(261);
        blueMana.setLayoutY(70);
        blueMana.setPreserveRatio(true);
        blueMana.setSmooth(true);
        blueMana.setCache(true);

        // BLACK MANA ICON //
        blackMana.setImage(new Image("/assets/Black_Mana.png"));
        blackMana.setFitWidth(50);
        blackMana.setFitHeight(50);
        blackMana.setLayoutX(337);
        blackMana.setLayoutY(70);
        blackMana.setPreserveRatio(true);
        blackMana.setSmooth(true);
        blackMana.setCache(true);

        // RED MANA ICON //
        redMana.setImage(new Image("/assets/Red_Mana.png"));
        redMana.setFitWidth(50);
        redMana.setFitHeight(50);
        redMana.setLayoutX(413);
        redMana.setLayoutY(70);
        redMana.setPreserveRatio(true);
        redMana.setSmooth(true);
        redMana.setCache(true);

        // GREEN MANA ICON //
        greenMana.setImage(new Image("/assets/Green_Mana.png"));
        greenMana.setFitWidth(50);
        greenMana.setFitHeight(50);
        greenMana.setLayoutX(489);
        greenMana.setLayoutY(70);
        greenMana.setPreserveRatio(true);
        greenMana.setSmooth(true);
        greenMana.setCache(true);

        // COLORLESS MANA ICON //
        colorlessMana.setImage(new Image("/assets/Colorless_Mana.png"));
        colorlessMana.setFitWidth(50);
        colorlessMana.setFitHeight(50);
        colorlessMana.setLayoutX(565);
        colorlessMana.setLayoutY(70);
        colorlessMana.setPreserveRatio(true);
        colorlessMana.setSmooth(true);
        colorlessMana.setCache(true);

        // WHITE MANA COUNTER //
        whiteCounter.setTextFill(Color.WHITE);
        whiteCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        whiteCounter.setAlignment(CENTER);
        whiteCounter.setPrefSize(44,28);
        whiteCounter.setLayoutX(188);
        whiteCounter.setLayoutY(29);

        // BLUE MANA COUNTER //
        blueCounter.setTextFill(Color.WHITE);
        blueCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        blueCounter.setAlignment(CENTER);
        blueCounter.setPrefSize(44,28);
        blueCounter.setLayoutX(264);
        blueCounter.setLayoutY(29);

        // BLACK MANA COUNTER //
        blackCounter.setTextFill(Color.WHITE);
        blackCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        blackCounter.setAlignment(CENTER);
        blackCounter.setPrefSize(44,28);
        blackCounter.setLayoutX(340);
        blackCounter.setLayoutY(29);

        // RED MANA COUNTER //
        redCounter.setTextFill(Color.WHITE);
        redCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        redCounter.setAlignment(CENTER);
        redCounter.setPrefSize(44,28);
        redCounter.setLayoutX(416);
        redCounter.setLayoutY(29);

        // GREEN MANA COUNTER //
        greenCounter.setTextFill(Color.WHITE);
        greenCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        greenCounter.setAlignment(CENTER);
        greenCounter.setPrefSize(44,28);
        greenCounter.setLayoutX(492);
        greenCounter.setLayoutY(29);

        // COLORLESS MANA COUNTER //
        colorlessCounter.setTextFill(Color.WHITE);
        colorlessCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        colorlessCounter.setAlignment(CENTER);
        colorlessCounter.setPrefSize(44,28);
        colorlessCounter.setLayoutX(568);
        colorlessCounter.setLayoutY(29);
        
        // TOTAL MANA COUNTER //
        totalCounter.setTextFill(Color.WHITE);
        totalCounter.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        totalCounter.setAlignment(CENTER);
        totalCounter.setPrefSize(50,28);
        totalCounter.setLayoutX(375);
        totalCounter.setLayoutY(136);

        // NEW SPELL CREATION //
        newSpell.setText("NEW SPELL");
        newSpell.setTextFill(Color.WHITE);
        newSpell.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC, 24));
        newSpell.setAlignment(CENTER);
        newSpell.setPrefSize(150, 28);
        newSpell.setLayoutX(325);
        newSpell.setLayoutY(200);
        newSpell.setDisable(true);
        newSpell.visibleProperty().setValue(false);

        // DISCARD CARD //
        discardCard.setImage(new Image("/assets/Discard.png"));
        discardCard.setFitWidth(50);
        discardCard.setFitHeight(50);
        discardCard.setLayoutX(258.5);
        discardCard.setLayoutY(651);
        discardCard.setPreserveRatio(true);
        discardCard.setSmooth(true);
        discardCard.setCache(true);
        discardCard.setDisable(true);
        discardCard.visibleProperty().setValue(false);

        // ADD CARD //
        addCard.setImage(new Image("/assets/Add_Spell.png"));
        addCard.setFitWidth(50);
        addCard.setFitHeight(50);
        addCard.setLayoutX(491.5);
        addCard.setLayoutY(651);
        addCard.setPreserveRatio(true);
        addCard.setSmooth(true);
        addCard.setCache(true);
        addCard.setDisable(true);
        addCard.visibleProperty().setValue(false);

        // SPELL HISTORY //
        spellHistory.setImage(new Image("/assets/Spell_History.png"));
        spellHistory.setFitWidth(240);
        spellHistory.setFitHeight(379);
        spellHistory.setLayoutX(-190);
        spellHistory.setLayoutY(236);
        spellHistory.translateXProperty().set(0);
        spellHistory.setPreserveRatio(true);
        spellHistory.setSmooth(true);
        spellHistory.setCache(true);

        // SPELL INFO //
        spellInfo.setImage(new Image("/assets/Spell_Info.png"));
        spellInfo.setFitWidth(240);
        spellInfo.setFitHeight(379);
        spellInfo.setLayoutX(750);
        spellInfo.setLayoutY(236);
        spellInfo.translateXProperty().set(0);
        spellInfo.setPreserveRatio(true);
        spellInfo.setSmooth(true);
        spellInfo.setCache(true);

        // SPELL NAME //
        spellName.setText("PENDING");
        spellName.setTextFill(Color.WHITE);
        spellName.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC, 18));
        spellName.setAlignment(CENTER);
        spellName.setPrefSize(150, 28);
        spellName.setLayoutX(802);
        spellName.setLayoutY(256);
        spellName.translateXProperty().set(0);

        // SPELL INFO LABELS //
        whiteManaAdded.setTextFill(Color.WHITE);
        whiteManaAdded.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        whiteManaAdded.setAlignment(CENTER);
        whiteManaAdded.setPrefSize(44,28);
        whiteManaAdded.setLayoutX(856);
        whiteManaAdded.setLayoutY(308);
        whiteManaAdded.translateXProperty().set(0);

        blueManaAdded.setTextFill(Color.WHITE);
        blueManaAdded.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        blueManaAdded.setAlignment(CENTER);
        blueManaAdded.setPrefSize(44,28);
        blueManaAdded.setLayoutX(856);
        blueManaAdded.setLayoutY(336);
        blueManaAdded.translateXProperty().set(0);

        blackManaAdded.setTextFill(Color.WHITE);
        blackManaAdded.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        blackManaAdded.setAlignment(CENTER);
        blackManaAdded.setPrefSize(44,28);
        blackManaAdded.setLayoutX(856);
        blackManaAdded.setLayoutY(364);
        blackManaAdded.translateXProperty().set(0);

        redManaAdded.setTextFill(Color.WHITE);
        redManaAdded.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        redManaAdded.setAlignment(CENTER);
        redManaAdded.setPrefSize(44,28);
        redManaAdded.setLayoutX(856);
        redManaAdded.setLayoutY(392);
        redManaAdded.translateXProperty().set(0);

        greenManaAdded.setTextFill(Color.WHITE);
        greenManaAdded.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        greenManaAdded.setAlignment(CENTER);
        greenManaAdded.setPrefSize(44,28);
        greenManaAdded.setLayoutX(856);
        greenManaAdded.setLayoutY(420);
        greenManaAdded.translateXProperty().set(0);

        colorlessManaAdded.setTextFill(Color.WHITE);
        colorlessManaAdded.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        colorlessManaAdded.setAlignment(CENTER);
        colorlessManaAdded.setPrefSize(44,28);
        colorlessManaAdded.setLayoutX(856);
        colorlessManaAdded.setLayoutY(448);
        colorlessManaAdded.translateXProperty().set(0);

        totalManaAdded.setTextFill(Color.WHITE);
        totalManaAdded.setFont(Font.font("Franklin Gothic Demi", FontPosture.ITALIC,24));
        totalManaAdded.setAlignment(CENTER);
        totalManaAdded.setPrefSize(44,28);
        totalManaAdded.setLayoutX(888);
        totalManaAdded.setLayoutY(497);
        totalManaAdded.translateXProperty().set(0);

        // CREATED SPELLS //
        createdSpells.setPrefSize(178,352);
        createdSpells.setLayoutX(-178);
        createdSpells.setLayoutY(250);
        createdSpells.translateXProperty().set(0);
        createdSpells.setOrientation(Orientation.VERTICAL);
        createdSpells.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        /*------------------------------------------------ CONTROLS ------------------------------------------------*/
        // CARD ICON CONTROLS //
        card.addEventFilter(MouseEvent.ANY, cardSelection);

        // MANA ICON CONTROLS //
        whiteMana.addEventFilter(MouseEvent.ANY, determineClickAction);
        blueMana.addEventFilter(MouseEvent.ANY, determineClickAction);
        blackMana.addEventFilter(MouseEvent.ANY, determineClickAction);
        redMana.addEventFilter(MouseEvent.ANY, determineClickAction);
        greenMana.addEventFilter(MouseEvent.ANY, determineClickAction);
        colorlessMana.addEventFilter(MouseEvent.ANY, determineClickAction);

        // BACKGROUND CONTROLS //
        canvas.setOnMouseClicked(backgroundSelected);

        // SPELL HISTORY CONTROLS //
        spellHistory.addEventFilter(MouseEvent.ANY, toggleSpellHistory);
        spellInfo.addEventFilter(MouseEvent.ANY, toggleSpellInfo);

        /*-------------------------------------------------- STAGE --------------------------------------------------*/
        root.getChildren().addAll(canvas, availableMana, card, whiteMana, blueMana, blackMana, redMana, greenMana, colorlessMana,
                whiteCounter, blueCounter, blackCounter, redCounter, greenCounter, colorlessCounter, newSpell, discardCard,
                addCard, spellHistory, spellInfo, totalMana, totalCounter, spellName, createdSpells,
                whiteManaAdded, blueManaAdded, blackManaAdded, redManaAdded, greenManaAdded, colorlessManaAdded, totalManaAdded);
        primaryStage.setTitle("MTG Mana Tracker v.0.1.6");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 800, 750));
        primaryStage.show();
    }


    /*----------------------------------------------- EVENT FUNCTIONS -----------------------------------------------*/
    private EventHandler<MouseEvent> determineClickAction = new EventHandler<MouseEvent>() {
        boolean dragOccured = false;
        @Override
        public void handle(MouseEvent event) {
            if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
                if(event.getButton() == MouseButton.PRIMARY){
                    // Construct a copy of the icon on top of the old so when it does get dragged the original is right under it //
                    int layoutX = (int)((ImageView)event.getSource()).getLayoutX();

                    ImageView orgIcon = new ImageView();
                    orgIcon.setFitWidth(50);
                    orgIcon.setFitHeight(50);
                    orgIcon.setLayoutY(70);
                    orgIcon.setPreserveRatio(true);
                    orgIcon.setSmooth(true);
                    orgIcon.setCache(true);

                    switch (layoutX){
                        case 185:
                            orgIcon.setImage(new Image("/assets/White_Mana.png"));
                            orgIcon.setLayoutX(185);
                            break;
                        case 261:
                            orgIcon.setImage(new Image("/assets/Blue_Mana.png"));
                            orgIcon.setLayoutX(261);
                            break;
                        case 337:
                            orgIcon.setImage(new Image("/assets/Black_Mana.png"));
                            orgIcon.setLayoutX(337);
                            break;
                        case 413:
                            orgIcon.setImage(new Image("/assets/Red_Mana.png"));
                            orgIcon.setLayoutX(413);
                            break;
                        case 489:
                            orgIcon.setImage(new Image("/assets/Green_Mana.png"));
                            orgIcon.setLayoutX(489);
                            break;
                        case 565:
                            orgIcon.setImage(new Image("/assets/Colorless_Mana.png"));
                            orgIcon.setLayoutX(565);
                            break;
                    }
                    orgIcon.addEventFilter(MouseEvent.ANY, determineClickAction);
                    root.getChildren().add(orgIcon);
                    System.out.println("ICON CREATED");

                    orgSceneX = event.getSceneX();
                    orgSceneY = event.getSceneY();
                    orgTranslateX = ((ImageView)(event.getSource())).getTranslateX();
                    orgTranslateY = ((ImageView)(event.getSource())).getTranslateY();
                }
            }else if(event.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
                int counterValue = 0;
                int totalManaCount =  0;

                // Dragging Effects Happen Here //
                if(event.getButton() == MouseButton.PRIMARY && dragOccured){
                    System.out.println("DRAG ALERT");
                    double dimX = event.getSceneX();
                    double dimY = event.getSceneY();

                    // Check if within card bounds to drop mana //
                    if ((dimX > 258.5 && dimX < 541.5) && (dimY > 236 && dimY < 615)) {
                        totalManaCount = Math.max(0, Integer.parseInt(totalCounter.getText())-1);
                        totalCounter.setText(String.valueOf(totalManaCount));
                        int totalMana = 0;
                        // Make sure to initialize the spell if it isn't already //
                        if(!spellInitialized){
                            pending = new Spells();
                            spellInitialized = true;
                            totalManaAdded.setText(String.valueOf(totalMana));
                        }else{
                            totalMana = pending.getWhiteMana() + pending.getBlackMana() + pending.getBlackMana() +
                                    pending.getRedMana() + pending.getGreenMana() + pending.getColorlessMana();
                        }
                        if (whiteManaDragged) {
                            whiteManaDragged = false;
                            int availableWhite = Integer.parseInt(whiteCounter.getText());
                            counterValue = Math.max(0, availableWhite - 1);
                            whiteCounter.setText(String.valueOf(counterValue));
                            int addition = availableWhite > 0 ? 1 : 0;
                            pending.setWhiteMana(pending.getWhiteMana() +  addition);
                            // update the counter for it on the right panel //
                            whiteManaAdded.setText(String.valueOf(pending.getWhiteMana()));
                            totalManaAdded.setText(String.valueOf(totalMana + addition));
                        } else if (blueManaDragged) {
                            blueManaDragged = false;
                            int availableBlue = Integer.parseInt(blueCounter.getText());
                            counterValue = Math.max(0, availableBlue - 1);
                            blueCounter.setText(String.valueOf(counterValue));
                            int addition = availableBlue > 0 ? 1 : 0;
                            pending.setBlueMana(pending.getBlueMana() +  addition);
                            // update the counter for it on the right panel //
                            blueManaAdded.setText(String.valueOf(pending.getBlueMana()));
                            totalManaAdded.setText(String.valueOf(totalMana + addition));
                        } else if (blackManaDragged) {
                            blackManaDragged = false;
                            int availableBlack = Integer.parseInt(blackCounter.getText());
                            counterValue = Math.max(0, availableBlack - 1);
                            blackCounter.setText(String.valueOf(counterValue));
                            int addition = availableBlack > 0 ? 1 : 0;
                            pending.setBlackMana(pending.getBlackMana() +  addition);
                            // update the counter for it on the right panel //
                            blackManaAdded.setText(String.valueOf(pending.getBlackMana()));
                            totalManaAdded.setText(String.valueOf(totalMana + addition));

                        } else if (redManaDragged) {
                            redManaDragged = false;
                            int availableRed = Integer.parseInt(redCounter.getText());
                            counterValue = Math.max(0, availableRed - 1);
                            redCounter.setText(String.valueOf(counterValue));
                            int addition = availableRed > 0 ? 1 : 0;
                            pending.setRedMana(pending.getRedMana() +  addition);
                            // update the counter for it on the right panel //
                            redManaAdded.setText(String.valueOf(pending.getRedMana()));
                            totalManaAdded.setText(String.valueOf(totalMana + addition));
                        } else if (greenManaDragged) {
                            greenManaDragged = false;
                            int availableGreen = Integer.parseInt(greenCounter.getText());
                            counterValue = Math.max(0, availableGreen - 1);
                            greenCounter.setText(String.valueOf(counterValue));
                            int addition = availableGreen > 0 ? 1 : 0;
                            pending.setGreenMana(pending.getGreenMana() +  addition);
                            // update the counter for it on the right panel //
                            greenManaAdded.setText(String.valueOf(pending.getGreenMana()));
                            totalManaAdded.setText(String.valueOf(totalMana + addition));
                        } else if (colorlessManaDragged) {
                            colorlessManaDragged = false;
                            int availableColorless = Integer.parseInt(colorlessCounter.getText());
                            counterValue = Math.max(0, availableColorless - 1);
                            colorlessCounter.setText(String.valueOf(counterValue));
                            System.out.println(availableColorless);
                            int addition = availableColorless > 0 ? 1 : 0;
                            pending.setColorlessMana(pending.getColorlessMana() + addition);
                            // update the counter for it on the right panel //
                            colorlessManaAdded.setText(String.valueOf(pending.getColorlessMana()));
                            totalManaAdded.setText(String.valueOf(totalMana + addition));
                        }
                        if(!cardToggled){
                            newSpell.setDisable(false);
                            newSpell.visibleProperty().setValue(true);
                            discardCard.setDisable(false);
                            discardCard.visibleProperty().setValue(true);
                            addCard.setDisable(false);
                            addCard.visibleProperty().setValue(true);
                            cardToggled = true;
                            System.out.println("CARD TOGGLED");
                        }
                    }
                    manaIsDragged = false;
                    dragOccured = false;
                    root.getChildren().remove(event.getSource());
                    System.out.println("ICON DELETED");

                    // Card should also be toggled when dropping mana into it //
                }else if(event.getButton() == MouseButton.PRIMARY){
                    System.out.println("QUICK CLICK");
                    totalManaCount = Integer.parseInt(totalCounter.getText())+1;
                    totalCounter.setText(String.valueOf(totalManaCount));
                    // This means it was just a quick click and should just add 1 to the counter //
                    int layoutX = (int)((ImageView)event.getSource()).getLayoutX();

                    switch (layoutX){
                        case 185:
                            counterValue = Integer.parseInt(whiteCounter.getText()) + 1;
                            whiteCounter.setText(String.valueOf(counterValue));
                            break;
                        case 261:
                            counterValue = Integer.parseInt(blueCounter.getText()) + 1;
                            blueCounter.setText(String.valueOf(counterValue));
                            break;
                        case 337:
                            counterValue = Integer.parseInt(blackCounter.getText()) + 1;
                            blackCounter.setText(String.valueOf(counterValue));
                            break;
                        case 413:
                            counterValue = Integer.parseInt(redCounter.getText()) + 1;
                            redCounter.setText(String.valueOf(counterValue));
                            break;
                        case 489:
                            counterValue = Integer.parseInt(greenCounter.getText()) + 1;
                            greenCounter.setText(String.valueOf(counterValue));
                            break;
                        case 565:
                            counterValue = Integer.parseInt(colorlessCounter.getText()) + 1;
                            colorlessCounter.setText(String.valueOf(counterValue));
                            break;
                    }
                    // Still have to delete the copy we made when we first clicked the icon //
                    root.getChildren().remove(event.getSource());
                    System.out.println("ICON DELETED");
                }else if(event.getButton() == MouseButton.SECONDARY){
                    // This means we are reducing the counter by 1 //
                    int layoutX = (int)((ImageView)event.getSource()).getLayoutX();
                    totalManaCount = Math.max(0, Integer.parseInt(totalCounter.getText())-1);

                    switch (layoutX){
                        case 185:
                            counterValue = Math.max(0, Integer.parseInt(whiteCounter.getText()) - 1);
                            whiteCounter.setText(String.valueOf(counterValue));
                            totalCounter.setText(String.valueOf(totalManaCount));
                            break;
                        case 261:
                            counterValue = Math.max(0, Integer.parseInt(blueCounter.getText()) - 1);
                            blueCounter.setText(String.valueOf(counterValue));
                            totalCounter.setText(String.valueOf(totalManaCount));
                            break;
                        case 337:
                            counterValue = Math.max(0, Integer.parseInt(blackCounter.getText()) - 1);
                            blackCounter.setText(String.valueOf(counterValue));
                            totalCounter.setText(String.valueOf(totalManaCount));
                            break;
                        case 413:
                            counterValue = Math.max(0, Integer.parseInt(redCounter.getText()) - 1);
                            redCounter.setText(String.valueOf(counterValue));
                            totalCounter.setText(String.valueOf(totalManaCount));
                            break;
                        case 489:
                            counterValue = Math.max(0, Integer.parseInt(greenCounter.getText()) - 1);
                            greenCounter.setText(String.valueOf(counterValue));
                            totalCounter.setText(String.valueOf(totalManaCount));
                            break;
                        case 565:
                            counterValue = Math.max(0, Integer.parseInt(colorlessCounter.getText()) - 1);
                            colorlessCounter.setText(String.valueOf(counterValue));
                            totalCounter.setText(String.valueOf(totalManaCount));
                            break;
                    }
                    // Notice here we don't have to delete the copied icon since it was never created to begin with //
                }
            }else if(event.getEventType().equals(MouseEvent.MOUSE_DRAGGED) && event.getButton() == MouseButton.PRIMARY){
                int layoutX = (int)((ImageView)event.getSource()).getLayoutX();
                dragOccured = true;
                manaIsDragged = true;

                switch (layoutX){
                    case 185:
                        ((ImageView)event.getSource()).setImage(new Image("/assets/White_Mana_2.png"));
                        whiteManaDragged = true;
                        break;
                    case 261:
                        ((ImageView)event.getSource()).setImage(new Image("/assets/Blue_Mana_2.png"));
                        blueManaDragged = true;
                        break;
                    case 337:
                        ((ImageView)event.getSource()).setImage(new Image("/assets/Black_Mana_2.png"));
                        blackManaDragged = true;
                        break;
                    case 413:
                        ((ImageView)event.getSource()).setImage(new Image("/assets/Red_Mana_2.png"));
                        redManaDragged = true;
                        break;
                    case 489:
                        ((ImageView)event.getSource()).setImage(new Image("/assets/Green_Mana_2.png"));
                        greenManaDragged = true;
                        break;
                    case 565:
                        ((ImageView)event.getSource()).setImage(new Image("/assets/Colorless_Mana_2.png"));
                        colorlessManaDragged = true;
                        break;
                }

                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
                ((ImageView)(event.getSource())).setTranslateY(newTranslateY);
            }
        }
    };

    EventHandler<MouseEvent> cardSelection = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                if (event.getButton() == MouseButton.PRIMARY && !cardToggled) {
                    // Card should become toggled and icons should appear //
                    newSpell.setDisable(false);
                    newSpell.visibleProperty().setValue(true);

                    discardCard.setDisable(false);
                    discardCard.visibleProperty().setValue(true);

                    addCard.setDisable(false);
                    addCard.visibleProperty().setValue(true);

                    cardToggled = true;
                    System.out.println("CARD TOGGLED");
                }
            }
        }
    };

    private EventHandler<MouseEvent> backgroundSelected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if(cardToggled){
                System.out.println("CARD DESELECTED");
                newSpell.setDisable(true);
                discardCard.setDisable(true);
                addCard.setDisable(true);
                newSpell.visibleProperty().setValue(false);
                discardCard.visibleProperty().setValue(false);
                addCard.visibleProperty().setValue(false);

                cardToggled = false;
            }
        }
    };

    EventHandler<MouseEvent> toggleSpellHistory = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                double dimX = event.getSceneX();
                double dimY = event.getSceneY();

                if (event.getButton() == MouseButton.PRIMARY && !spellHistoryToggled && (dimX > 22 && dimX < 50) && (dimY > 390.5 && dimY < 460.5)) {
                    // Window should slide out //
                    Timeline timeline = new Timeline();
                    KeyValue kv = new KeyValue(((ImageView)event.getSource()).translateXProperty(), 178, Interpolator.EASE_IN);
                    KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
                    KeyValue kv2 = new KeyValue(createdSpells.translateXProperty(), 178, Interpolator.EASE_IN);
                    KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
                    timeline.getKeyFrames().add(kf);
                    timeline.getKeyFrames().add(kf2);
                    timeline.play();
                    ((ImageView)event.getSource()).translateXProperty().set(230);
                    createdSpells.translateXProperty().set(230);
                    spellHistoryToggled = true;
                }else if(event.getButton() == MouseButton.PRIMARY && (dimX > 200 && dimX < 228) && (dimY > 390.5 && dimY < 460.5)){
                    // Window should slide back in //
                    Timeline timeline = new Timeline();
                    KeyValue kv = new KeyValue(((ImageView)event.getSource()).translateXProperty(), 0, Interpolator.EASE_OUT);
                    KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
                    KeyValue kv2 = new KeyValue(createdSpells.translateXProperty(), 0, Interpolator.EASE_OUT);
                    KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
                    timeline.getKeyFrames().add(kf);
                    timeline.getKeyFrames().add(kf2);
                    timeline.play();
                    spellHistoryToggled = false;
                }
            }
        }
    };

    EventHandler<MouseEvent> toggleSpellInfo = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                double dimX = event.getSceneX();
                double dimY = event.getSceneY();

                if (event.getButton() == MouseButton.PRIMARY && !spellInfoToggled && (dimX > 750 && dimX < 778) && (dimY > 390.5 && dimY < 460.5)) {
                    // Window should slide out //
                    Timeline timeline = new Timeline();
                    KeyValue kv = new KeyValue(((ImageView)event.getSource()).translateXProperty(), -178, Interpolator.EASE_OUT);
                    KeyValue kv2 = new KeyValue(spellName.translateXProperty(), -178, Interpolator.EASE_OUT);
                    KeyValue kv3 = new KeyValue(whiteManaAdded.translateXProperty(), -178, Interpolator.EASE_OUT);
                    KeyValue kv4 = new KeyValue(blueManaAdded.translateXProperty(), -178, Interpolator.EASE_OUT);
                    KeyValue kv5 = new KeyValue(blackManaAdded.translateXProperty(), -178, Interpolator.EASE_OUT);
                    KeyValue kv6 = new KeyValue(redManaAdded.translateXProperty(), -178, Interpolator.EASE_OUT);
                    KeyValue kv7 = new KeyValue(greenManaAdded.translateXProperty(), -178, Interpolator.EASE_OUT);
                    KeyValue kv8 = new KeyValue(colorlessManaAdded.translateXProperty(), -178, Interpolator.EASE_OUT);
                    KeyValue kv9 = new KeyValue(totalManaAdded.translateXProperty(), -178, Interpolator.EASE_OUT);
                    KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
                    KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
                    KeyFrame kf3 = new KeyFrame(Duration.seconds(1), kv3);
                    KeyFrame kf4 = new KeyFrame(Duration.seconds(1), kv4);
                    KeyFrame kf5 = new KeyFrame(Duration.seconds(1), kv5);
                    KeyFrame kf6 = new KeyFrame(Duration.seconds(1), kv6);
                    KeyFrame kf7 = new KeyFrame(Duration.seconds(1), kv7);
                    KeyFrame kf8 = new KeyFrame(Duration.seconds(1), kv8);
                    KeyFrame kf9 = new KeyFrame(Duration.seconds(1), kv9);
                    timeline.getKeyFrames().add(kf);
                    timeline.getKeyFrames().add(kf2);
                    timeline.getKeyFrames().add(kf3);
                    timeline.getKeyFrames().add(kf4);
                    timeline.getKeyFrames().add(kf5);
                    timeline.getKeyFrames().add(kf6);
                    timeline.getKeyFrames().add(kf7);
                    timeline.getKeyFrames().add(kf8);
                    timeline.getKeyFrames().add(kf9);
                    timeline.play();
                    ((ImageView)event.getSource()).translateXProperty().set(230);
                    spellName.translateXProperty().set(230);
                    spellInfoToggled = true;
                }else if(event.getButton() == MouseButton.PRIMARY && (dimX > 572 && dimX < 600) && (dimY > 390.5 && dimY < 460.5)){
                    // Window should slide back in //
                    Timeline timeline = new Timeline();
                    KeyValue kv = new KeyValue(((ImageView)event.getSource()).translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyValue kv2 = new KeyValue(spellName.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyValue kv3 = new KeyValue(whiteManaAdded.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyValue kv4 = new KeyValue(blueManaAdded.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyValue kv5 = new KeyValue(blackManaAdded.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyValue kv6 = new KeyValue(redManaAdded.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyValue kv7 = new KeyValue(greenManaAdded.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyValue kv8 = new KeyValue(colorlessManaAdded.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyValue kv9 = new KeyValue(totalManaAdded.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
                    KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
                    KeyFrame kf3 = new KeyFrame(Duration.seconds(1), kv3);
                    KeyFrame kf4 = new KeyFrame(Duration.seconds(1), kv4);
                    KeyFrame kf5 = new KeyFrame(Duration.seconds(1), kv5);
                    KeyFrame kf6 = new KeyFrame(Duration.seconds(1), kv6);
                    KeyFrame kf7 = new KeyFrame(Duration.seconds(1), kv7);
                    KeyFrame kf8 = new KeyFrame(Duration.seconds(1), kv8);
                    KeyFrame kf9 = new KeyFrame(Duration.seconds(1), kv9);
                    timeline.getKeyFrames().add(kf);
                    timeline.getKeyFrames().add(kf2);
                    timeline.getKeyFrames().add(kf3);
                    timeline.getKeyFrames().add(kf4);
                    timeline.getKeyFrames().add(kf5);
                    timeline.getKeyFrames().add(kf6);
                    timeline.getKeyFrames().add(kf7);
                    timeline.getKeyFrames().add(kf8);
                    timeline.getKeyFrames().add(kf9);
                    timeline.play();
                    spellInfoToggled = false;
                }
            }
        }
    };

    public static void main(String[] args) {
        launch(args);
    }
}
