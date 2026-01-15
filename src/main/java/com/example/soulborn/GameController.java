package com.example.soulborn;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private Random random = new Random();
    public AnchorPane homePane;
    public AnchorPane savedGamePane;
    public AnchorPane newGamePaneCH;
    public AnchorPane newGamePaneNPC;
    public AnchorPane gamePane;

    public Button newGameButton;
    public Button loadGameButton;

    public HBox savedGamesList;

    public TextField usernameField;
    public TextField powerField;
    public TextField dexterityField;
    public TextField faithField;
    public TextField armorField;
    public TextField intelligenceField;

    public Label upgradePointsLabel;

    public Label playerName;
    public Label powerStat;
    public Label dexterityStat;
    public Label faithStat;
    public Label armorStat;
    public Label intelligenceStat;
    public Label weaponStat;
    public ImageView playerIcon;

    public Button saveCharacterButton;
    public Button cancelCharacterButton;
    public Button exitSavedGameButton;
    public Button startGameButton;
    public Button generateNPCButton;
    public Label NPCName;
    public Label powerStatNPC;
    public Label dexterityStatNPC;
    public Label faithStatNPC;
    public Label armorStatNPC;
    public Label intelligenceStatNPC;
    public Label weaponStatNPC;
    public ImageView NPCIcon;
    private String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public HBox playersList;

    public Button addNPCButton;
    public Button exitBattleButton;
    public Button attackButton;
    public Button healButton;
    public Button reloadButton;
    public Button defendButton;

    public HBox weaponCatalog;
    public Button uploadButton;
    public ImageView characterImage;

    private FileChooser fileChooser = new FileChooser();
    private File selectedFile;

    private Player newPlayer;

    private ArrayList<NPC> newNpcs = new ArrayList<>();

    private Game newGame;

    public void initialize() {
        Weapon.generate();
        ArrayList<Weapon> weapons = Weapon.getWeapons();
        for (Weapon weapon : weapons) {
            Button newWeapon = new Button(weapon.getWeaponName());
            newWeapon.setId(weapon.getWeaponName());
            newWeapon.setOnAction(this::saveWeapon);
            newWeapon.setStyle(
                "-fx-background-color: #2d2d3d; " +
                "-fx-text-fill: #ffd700; " +
                "-fx-background-radius: 8; " +
                "-fx-border-color: #4a4a6a; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 8; " +
                "-fx-padding: 15 30; " +
                "-fx-font-size: 20; " +
                "-fx-cursor: hand;"
            );
            weaponCatalog.getChildren().add(newWeapon);
        }
        Game.restoreData();
        for (Game game : Game.getGameList()) {
            System.out.println(game.getGameName());
            Label newLabel = new Label(game.getGameName());
            Pane newPane = new Pane(newLabel);
            Button newButton = new Button("", newPane);
            newButton.setId(game.getGameName());

            savedGamesList.getChildren().add(newButton);
        }
    }

    public void hideAllPanes() {
        homePane.setVisible(false);
        homePane.setDisable(true);

        savedGamePane.setVisible(false);
        savedGamePane.setDisable(true);

        newGamePaneCH.setVisible(false);
        newGamePaneCH.setDisable(true);

        newGamePaneNPC.setVisible(false);
        newGamePaneNPC.setDisable(true);

        gamePane.setVisible(false);
        gamePane.setDisable(true);
    }

    public void showHomePane() throws Exception {
        Game.saveData();
        hideAllPanes();
        homePane.setVisible(true);
        homePane.setDisable(false);
    }

    public void showSavedGamePane() {
        hideAllPanes();
        savedGamePane.setVisible(true);
        savedGamePane.setDisable(false);
    }

    public void showCharacterCreationPane() {
        hideAllPanes();
        newGamePaneCH.setVisible(true);
        newGamePaneCH.setDisable(false);

        if (upgradePointsLabel != null) {
            upgradePointsLabel.setText("10");
        }
    }

    public void showNPCSetupPane() {
        if (newPlayer == null) {
            newPlayer = new Player("", 0, true, null, 0, 100, true, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
        }

        if (newPlayer.getWeapon() == null || newPlayer.getUsername().isBlank()) {
            // do something to indicate
        } else {
            hideAllPanes();
            newGamePaneNPC.setVisible(true);
            newGamePaneNPC.setDisable(false);
            playerName.setText(newPlayer.getUsername());
            playerIcon.setImage(newPlayer.getIcon());
            powerStat.setText("Power: " + newPlayer.getPower());
            dexterityStat.setText("Dexterity: " + newPlayer.getDexterity());
            armorStat.setText("Armor: " + newPlayer.getArmor());
            intelligenceStat.setText("Intelligence: " + newPlayer.getIntelligence());
            faithStat.setText("Faith: " + newPlayer.getFaith());
            weaponStat.setText(newPlayer.getWeapon().getWeaponName());
        }
    }

    public void showGamePane() throws Exception {
        hideAllPanes();

        if (newGame == null) {
            ArrayList<PlayableCharacter> characters = new ArrayList<>();
            characters.add(newPlayer);
            characters.addAll(newNpcs);
            newGame = new Game(characters, newPlayer, "Game" +  random.nextInt(0, 100000));
        }

        Game.saveData();

        gamePane.setVisible(true);
        gamePane.setDisable(false);
    }

    public void saveName() {
        String name = usernameField.getText();
        if (newPlayer == null) {
            newPlayer = new Player(name, 0, true, null, 0, 100, true, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
        } else {
            newPlayer.setUsername(name);
        }
    }

    public void saveWeapon( ActionEvent event ) {
        Button currentButton = (Button) event.getSource();
        Weapon playerWeapon = null;
        
        for (Weapon weapon : Weapon.getWeapons()) {
            if (weapon.getWeaponName().equals(currentButton.getId())) {
                playerWeapon = weapon;
            }
        }
        if (newPlayer == null) {
            newPlayer = new Player("", 0, true, playerWeapon, 0, 100, true, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
        } else {
            newPlayer.setWeapon(playerWeapon);
        }
        
    }

    public void upload () throws Exception {
        uploadButton.setDisable(true);
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            FileInputStream imgInput = new FileInputStream(selectedFile);
            Image img = new Image(imgInput);
            characterImage.setImage(img);

            if (newPlayer == null) {
                newPlayer = new Player("", 0, true, null, 0, 100, true, img, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
            } else {
                newPlayer.setIcon(img);
            }
        }
        uploadButton.setDisable(false);
    }

    public void editStats(ActionEvent event) {
        int power = Integer.parseInt(powerField.getText());
        int dexterity = Integer.parseInt(dexterityField.getText());
        int faith = Integer.parseInt(faithField.getText());
        int armor = Integer.parseInt(armorField.getText());
        int intelligence = Integer.parseInt(intelligenceField.getText());

        int totalStat = power + dexterity + faith + armor + intelligence;
        int pointleft = 10 - totalStat;

        if (pointleft < 0) {
            Label currentLabel = (Label) event.getSource();
            if (currentLabel.getId().equals("powerField")) {
                pointleft += power;
                power = 0;
                powerField.setText("0");
            } else if (currentLabel.getId().equals("dexterityField")) {
                pointleft += dexterity;
                dexterity = 0;
                dexterityField.setText("0");
            } else if (currentLabel.getId().equals("armorField")) {
                pointleft += armor;
                armor = 0;
                armorField.setText("0");
            } else if (currentLabel.getId().equals("faithField")) {
                pointleft += faith;
                faith = 0;
                faithField.setText("0");
            } else if (currentLabel.getId().equals("intelligenceField")) {
                pointleft += intelligence;
                intelligence = 0;
                intelligenceField.setText("0");
            }
        }

        upgradePointsLabel.setText(Integer.toString(pointleft));

        if (newPlayer == null) {
            newPlayer = new Player(null, 0, true, null, 0, 100, true, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
        }
        newPlayer.setDefaultArmor(armor);
        newPlayer.setDefaultFaith(faith);
        newPlayer.setDefaultDexterity(dexterity);
        newPlayer.setDefaultPower(power);
        newPlayer.setDefaultIntelligence(intelligence);

        newPlayer.setArmor(armor);
        newPlayer.setFaith(faith);
        newPlayer.setDexterity(dexterity);
        newPlayer.setPower(power);
        newPlayer.setIntelligence(intelligence);
    }

    public void generateNPC(ActionEvent event) {
        Button currentButton = (Button) event.getSource();
        NPC currentNPC = null;
        for (NPC npc : newNpcs) {
            if (npc.getUsername().equals(currentButton.getId())) {
                currentNPC = npc;
            }
        }
        StringBuilder result = new StringBuilder();

        for (int x = 0; x < 5; x++) {
            int characterPos = random.nextInt(0, CHARACTERS.length());
            result.append(CHARACTERS.charAt(characterPos));
        }
        String name = result.toString();
        Weapon weapon;

        int weaponPos = random.nextInt(0, Weapon.getWeapons().size());
        weapon = Weapon.getWeapons().get(weaponPos);

        int power = random.nextInt(0, 3);
        int dexterity = random.nextInt(0, 3);
        int faith = random.nextInt(0, 3);
        int armor = random.nextInt(0, 3);
        int intelligence = random.nextInt(0, 3);

        currentNPC.setUsername(name);
        currentNPC.setDefaultPower(power);
        currentNPC.setPower(power);
        currentNPC.setDefaultDexterity(dexterity);
        currentNPC.setDexterity(dexterity);
        currentNPC.setDefaultFaith(faith);
        currentNPC.setFaith(faith);
        currentNPC.setDefaultArmor(armor);
        currentNPC.setArmor(armor);
        currentNPC.setDefaultIntelligence(intelligence);
        currentNPC.setIntelligence(intelligence);
        currentNPC.setWeapon(weapon);

        AnchorPane currentPane = (AnchorPane) currentButton.getParent();

        Label NPCNameCurrent = (Label) currentPane.getChildren().get(1);
        VBox vBox1 = (VBox) currentPane.getChildren().get(2);
        VBox vBox2 = (VBox) currentPane.getChildren().get(3);
        Label NPCPowerCurrent = (Label) vBox1.getChildren().get(0);
        Label NPCDexterityCurrent = (Label) vBox1.getChildren().get(1);
        Label NPCFaithCurrent = (Label) vBox1.getChildren().get(2);
        Label NPCArmorCurrent = (Label) vBox2.getChildren().get(0);
        Label NPCIntelligenceCurrent = (Label) vBox2.getChildren().get(1);
        Label NPCWeaponCurrent = (Label) vBox2.getChildren().get(2);

        currentButton.setId(currentNPC.getUsername());
        NPCNameCurrent.setText(name);
        NPCPowerCurrent.setText("Power: " + power);
        NPCDexterityCurrent.setText("Dexterity: " + dexterity);
        NPCArmorCurrent.setText("Armor: " + armor);
        NPCIntelligenceCurrent.setText("Intelligence: " + intelligence);
        NPCFaithCurrent.setText("Faith: " + faith);
        NPCWeaponCurrent.setText(weapon.getWeaponName());
    }

    public void addNPC() {
        NPC newNPC = NPC.generate();
        newNpcs.add(newNPC);
        ImageView newIcon = new ImageView();
        newIcon.setId("NPCIcon");
        newIcon.setFitHeight(150.0);
        newIcon.setFitWidth(200.0);
        newIcon.setLayoutX(90.0);
        newIcon.setLayoutY(20.0);
        newIcon.setPickOnBounds(true);
        newIcon.setPreserveRatio(true);

        Label newName = new Label(newNPC.getUsername());
        newName.setId("NPCName");
        newName.setLayoutX(125.0);
        newName.setLayoutY(180.0);
        newName.setTextFill(javafx.scene.paint.Color.web("#87ceeb"));
        newName.setFont(javafx.scene.text.Font.font("Georgia Bold", 40.0));

        Label newPowerLabel = new Label("Power: " + newNPC.getDefaultPower());
        newPowerLabel.setId("powerStatNPC");
        newPowerLabel.setTextFill(javafx.scene.paint.Color.web("#ff6b6b"));
        newPowerLabel.setFont(javafx.scene.text.Font.font(20.0));

        Label newDexterityLabel = new Label("Dexterity: " + newNPC.getDefaultDexterity());
        newDexterityLabel.setId("dexterityStatNPC");
        newDexterityLabel.setTextFill(javafx.scene.paint.Color.web("#90ee90"));
        newDexterityLabel.setFont(javafx.scene.text.Font.font(20.0));

        Label newFaithLabel = new Label("Faith: " + newNPC.getDefaultFaith());
        newFaithLabel.setId("faithStatNPC");
        newFaithLabel.setTextFill(javafx.scene.paint.Color.web("#ffd700"));
        newFaithLabel.setFont(javafx.scene.text.Font.font(20.0));

        Label newArmorLabel = new Label("Armor: " + newNPC.getDefaultArmor());
        newArmorLabel.setId("armorStatNPC");
        newArmorLabel.setTextFill(javafx.scene.paint.Color.web("#87ceeb"));
        newArmorLabel.setFont(javafx.scene.text.Font.font(20.0));

        Label newIntelligenceLabel = new Label("Intelligence: " + newNPC.getDefaultIntelligence());
        newIntelligenceLabel.setId("intelligenceStatNPC");
        newIntelligenceLabel.setTextFill(javafx.scene.paint.Color.web("#dda0dd"));
        newIntelligenceLabel.setFont(javafx.scene.text.Font.font(20.0));

        Label newWeaponLabel = new Label(newNPC.getWeapon().getWeaponName());
        newWeaponLabel.setId("weaponStatNPC");
        newWeaponLabel.setTextFill(javafx.scene.paint.Color.web("#ffffff"));
        newWeaponLabel.setFont(javafx.scene.text.Font.font(20.0));

        VBox newVbox1 = new VBox(8, newPowerLabel, newDexterityLabel, newFaithLabel);
        newVbox1.setId("vBox1");
        newVbox1.setLayoutX(30.0);
        newVbox1.setLayoutY(240.0);

        VBox newVbox2 = new VBox(8, newArmorLabel, newIntelligenceLabel, newWeaponLabel);
        newVbox2.setId("vBox2");
        newVbox2.setLayoutX(220.0);
        newVbox2.setLayoutY(240.0);

        Button newButton = new Button("Generate");
        newButton.setId(newNPC.getUsername());
        newButton.setOnAction(this::generateNPC);
        newButton.setLayoutX(90.0);
        newButton.setLayoutY(320.0);
        newButton.setStyle(
            "-fx-background-color: #1a4d2e; " +
            "-fx-text-fill: #90ee90; " +
            "-fx-background-radius: 8; " +
            "-fx-border-color: #90ee90; " +
            "-fx-border-width: 2; " +
            "-fx-border-radius: 8; " +
            "-fx-cursor: hand; " +
            "-fx-padding: 8 35;"
        );
        newButton.setFont(javafx.scene.text.Font.font(22.0));

        AnchorPane newPane = new AnchorPane(newIcon, newName, newVbox1, newVbox2, newButton);
        newPane.setPrefHeight(380.0);
        newPane.setPrefWidth(380.0);
        newPane.setStyle(
            "-fx-background-color: linear-gradient(to bottom, #2d2d3d, #1a1a2d); " +
            "-fx-background-radius: 15; " +
            "-fx-border-color: #87ceeb; " +
            "-fx-border-width: 2; " +
            "-fx-border-radius: 15;"
        );

        javafx.scene.effect.DropShadow dropShadow = new javafx.scene.effect.DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.BLACK);
        dropShadow.setRadius(10);
        dropShadow.setSpread(0.2);
        newPane.setEffect(dropShadow);

        playersList.getChildren().add(playersList.getChildren().size()-1, newPane);
    }
}
