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

    public HBox battleCharactersContainer;
    private ArrayList<AnchorPane> characterPanes = new ArrayList<>();
    private ArrayList<Label> hpLabels = new ArrayList<>();

    public HBox weaponCatalog;
    public Button uploadButton;
    public ImageView characterImage;

    private FileChooser fileChooser = new FileChooser();
    private File selectedFile;

    private Player newPlayer;

    private ArrayList<NPC> newNpcs = new ArrayList<>();

    private Game newGame;

    public void initialize() throws Exception{
        Weapon.generate();
        Game.restoreData();
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

        for (Game game : Game.getGameList()) {
            Label gameNameLabel = new Label(game.getGameName());
            gameNameLabel.setLayoutX(150.0);
            gameNameLabel.setLayoutY(434.0);
            gameNameLabel.setTextFill(javafx.scene.paint.Color.web("#87ceeb"));
            gameNameLabel.setFont(javafx.scene.text.Font.font("Georgia Bold", 50.0));
            gameNameLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.4, 0, 0);");

            Label infoLabel = new Label("Click to Load");
            infoLabel.setLayoutX(200.0);
            infoLabel.setLayoutY(500.0);
            infoLabel.setTextFill(javafx.scene.paint.Color.web("#4a4a6a"));
            infoLabel.setFont(javafx.scene.text.Font.font("Georgia", 24.0));

            Pane gamePane = new Pane(gameNameLabel, infoLabel);
            gamePane.setPrefHeight(600.0);
            gamePane.setPrefWidth(600.0);
            gamePane.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #2d2d3d, #1a1a2d); " +
                "-fx-background-radius: 20; " +
                "-fx-border-color: #4a4a6a; " +
                "-fx-border-width: 3; " +
                "-fx-border-radius: 20;"
            );

            javafx.scene.effect.DropShadow dropShadow = new javafx.scene.effect.DropShadow();
            dropShadow.setColor(javafx.scene.paint.Color.BLACK);
            dropShadow.setRadius(15);
            dropShadow.setSpread(0.4);
            gamePane.setEffect(dropShadow);

            Button gameButton = new Button();
            gameButton.setGraphic(gamePane);
            gameButton.setId(game.getGameName());
            gameButton.setStyle(
                "-fx-background-color: transparent; " +
                "-fx-cursor: hand; " +
                "-fx-padding: 0;"
            );
            gameButton.setOnAction(this::loadGame);

            savedGamesList.getChildren().add(gameButton);
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

    public void showGamePane() {
        hideAllPanes();

        if (newGame == null) {
            ArrayList<PlayableCharacter> characters = new ArrayList<>();
            characters.add(newPlayer);
            characters.addAll(newNpcs);
            newGame = new Game(characters, newPlayer, "Game" +  random.nextInt(0, 100000));
        }
        Game.saveData();
        startBattle();

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

    public void loadGame(ActionEvent actionEvent) {
        Button currentButton = (Button) actionEvent.getSource();
        for (Game game : Game.getGameList()) {
            if (game.getGameName().equals(currentButton.getId())) {
                newGame = game;
                newPlayer = (Player) newGame.getPlayers().getFirst();
            }
        }
        this.showGamePane();
    }

    public void startBattle() {
        characterPanes.clear();
        hpLabels.clear();
        if (battleCharactersContainer != null) {
            battleCharactersContainer.getChildren().clear();
        }


        // Create UI cards for each character
        for (PlayableCharacter character : newGame.getPlayers()) {
            createCharacterCard(character);
        }

    }

    private void createCharacterCard(PlayableCharacter character) {
        boolean isPlayer = character.isPlayer();

        ImageView icon = new ImageView();
        icon.setFitHeight(120.0);
        icon.setFitWidth(150.0);
        icon.setLayoutX(95.0);
        icon.setLayoutY(15.0);
        icon.setPickOnBounds(true);
        icon.setPreserveRatio(true);
        if (character.getIcon() != null) {
            icon.setImage(character.getIcon());
        }

        Label nameLabel = new Label(character.getUsername());
        nameLabel.setLayoutX(isPlayer ? 80.0 : 100.0);
        nameLabel.setLayoutY(145.0);
        nameLabel.setTextFill(javafx.scene.paint.Color.web(isPlayer ? "#ffd700" : "#ff6b6b"));
        nameLabel.setFont(javafx.scene.text.Font.font("Georgia Bold", 32.0));

        Label hpLabel = new Label("HP: " + character.getHp() + "/100");
        hpLabel.setStyle("-fx-font-weight: bold;");
        hpLabel.setTextFill(javafx.scene.paint.Color.web("#90ee90"));
        hpLabel.setFont(javafx.scene.text.Font.font(18.0));
        hpLabels.add(hpLabel);

        Label powerLabel = new Label("Power: " + character.getPower());
        powerLabel.setTextFill(javafx.scene.paint.Color.web("#ff6b6b"));
        powerLabel.setFont(javafx.scene.text.Font.font(16.0));

        Label dexLabel = new Label("Dex: " + character.getDexterity());
        dexLabel.setTextFill(javafx.scene.paint.Color.web("#90ee90"));
        dexLabel.setFont(javafx.scene.text.Font.font(16.0));

        Label faithLabel = new Label("Faith: " + character.getFaith());
        faithLabel.setTextFill(javafx.scene.paint.Color.web("#ffd700"));
        faithLabel.setFont(javafx.scene.text.Font.font(16.0));

        VBox leftStats = new VBox(5, hpLabel, powerLabel, dexLabel, faithLabel);
        leftStats.setLayoutX(20.0);
        leftStats.setLayoutY(190.0);

        Label armorLabel = new Label("Armor: " + character.getArmor());
        armorLabel.setTextFill(javafx.scene.paint.Color.web("#87ceeb"));
        armorLabel.setFont(javafx.scene.text.Font.font(16.0));

        Label intLabel = new Label("Int: " + character.getIntelligence());
        intLabel.setTextFill(javafx.scene.paint.Color.web("#dda0dd"));
        intLabel.setFont(javafx.scene.text.Font.font(16.0));

        String weaponName = character.getWeapon() != null ? character.getWeapon().getWeaponName() : "None";
        Label weaponLabel = new Label(weaponName);
        weaponLabel.setStyle("-fx-font-weight: bold;");
        weaponLabel.setTextFill(javafx.scene.paint.Color.web("#ffffff"));
        weaponLabel.setFont(javafx.scene.text.Font.font(16.0));

        VBox rightStats = new VBox(5, armorLabel, intLabel, weaponLabel);
        rightStats.setLayoutX(180.0);
        rightStats.setLayoutY(215.0);

        Label killsLabel = new Label("Kills: " + character.getKills());
        killsLabel.setStyle("-fx-background-color: #1a1a2d; -fx-padding: 3 8; -fx-background-radius: 5; -fx-border-color: #90ee90; -fx-border-radius: 5;");
        killsLabel.setTextFill(javafx.scene.paint.Color.web("#90ee90"));
        killsLabel.setFont(javafx.scene.text.Font.font(14.0));

        Label ammoLabel = new Label("Ammo: " + character.getBullets());
        ammoLabel.setStyle("-fx-background-color: #1a1a2d; -fx-padding: 3 8; -fx-background-radius: 5; -fx-border-color: #87ceeb; -fx-border-radius: 5;");
        ammoLabel.setTextFill(javafx.scene.paint.Color.web("#87ceeb"));
        ammoLabel.setFont(javafx.scene.text.Font.font(14.0));

        Label levelLabel = new Label("Lv: " + character.getLevel());
        levelLabel.setStyle("-fx-background-color: #1a1a2d; -fx-padding: 3 8; -fx-background-radius: 5; -fx-border-color: #ffd700; -fx-border-radius: 5;");
        levelLabel.setTextFill(javafx.scene.paint.Color.web("#ffd700"));
        levelLabel.setFont(javafx.scene.text.Font.font(14.0));

        HBox bottomInfo = new HBox(10, killsLabel, ammoLabel, levelLabel);
        bottomInfo.setLayoutX(20.0);
        bottomInfo.setLayoutY(285.0);

        AnchorPane charPane = new AnchorPane(icon, nameLabel, leftStats, rightStats, bottomInfo);
        charPane.setPrefHeight(340.0);
        charPane.setPrefWidth(340.0);

        if (isPlayer) {
            charPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #2d2d3d, #1a1a2d); " +
                "-fx-background-radius: 12; " +
                "-fx-border-color: #ffd700; " +
                "-fx-border-width: 3; " +
                "-fx-border-radius: 12;"
            );
            javafx.scene.effect.DropShadow dropShadow = new javafx.scene.effect.DropShadow();
            dropShadow.setColor(javafx.scene.paint.Color.web("#ffd700"));
            dropShadow.setRadius(12);
            dropShadow.setSpread(0.3);
            charPane.setEffect(dropShadow);
        } else {
            charPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #3d2d2d, #2d1a1a); " +
                "-fx-background-radius: 12; " +
                "-fx-border-color: #ff6b6b; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 12;"
            );
            javafx.scene.effect.DropShadow dropShadow = new javafx.scene.effect.DropShadow();
            dropShadow.setColor(javafx.scene.paint.Color.BLACK);
            dropShadow.setRadius(8);
            dropShadow.setSpread(0.2);
            charPane.setEffect(dropShadow);
        }

        characterPanes.add(charPane);
        battleCharactersContainer.getChildren().add(charPane);
    }

    private void startTurn() {
        PlayableCharacter current = newGame.getCurrentCharacter();

        if (!current.isPlayer()) {
            setPlayerButtonsDisabled(true);

            this.NPCAction(current);
        } else {
            setPlayerButtonsDisabled(false);
        }
    }

    private void setPlayerButtonsDisabled(boolean disabled) {
        attackButton.setDisable(disabled);
        healButton.setDisable(disabled);
        reloadButton.setDisable(disabled);
    }

    private void NPCAction(PlayableCharacter npc) {
        if (!npc.isAlive()) {
            endTurn();
            return;
        } else if (npc.getBullets() == 0) {
            reload(npc);
        }else {
            int nextMove = random.nextInt(0, 1);
            if (nextMove == 0) {
                int characterNum = random.nextInt(0, newGame.getPlayers().size());
                PlayableCharacter target = newGame.getPlayers().get(characterNum);
                while (!target.isAlive() && !target.getUsername().equals(npc.getUsername())) {
                    characterNum = random.nextInt(0, newGame.getPlayers().size());
                    target = newGame.getPlayers().get(characterNum);
                }

                if (target.isAlive()) {
                    attack(npc, target);
                    endTurn();
                }
            } else {
                heal(npc);
                endTurn();
            }

        }
    }

    public void attack(PlayableCharacter attacker, PlayableCharacter target) {
        int baseDamage = (attacker.getPower() * 10) + attacker.getWeapon().getDamage();
        attacker.setBullets(attacker.getBullets() - 1);

        boolean critical = random.nextInt(100) * ((attacker.getDexterity() / 10) + 1) < attacker.getWeapon().getAccuracy();
        if (critical) {
            baseDamage = (int)(baseDamage * 1.5);
        }

        int finalDamage = Math.max(1, baseDamage - (target.getArmor() * 5));

        target.setHp(Math.max(0, target.getHp() - finalDamage));

        if (!target.isAlive()) {
            target.setAlive(false);
            if (attacker.isPlayer()) {
                attacker.setKills(attacker.getKills() + 1);
            }
        }
        updateAllBattleStat();
    }

    public void heal(PlayableCharacter character) {
        if (!character.isAlive()) return;

        int healAmount = 20 + (character.getFaith() * 10);

        character.setHp(Math.min(100, character.getHp() + healAmount));

        updateAllBattleStat();
    }

    public void reload(PlayableCharacter character) {
        int ammoRestored = character.getWeapon().getMaxAmmo();
        character.setBullets(ammoRestored);
        updateAllBattleStat();
    }

    public void onAttackButton() {
        if (!newPlayer.isAlive()) {
            endTurn();
            return;
        }

        int characterNum = random.nextInt(0, newGame.getPlayers().size());
        PlayableCharacter target = newGame.getPlayers().get(characterNum);
        while (!target.isAlive() && !target.getUsername().equals(newPlayer.getUsername())) {
            characterNum = random.nextInt(0, newGame.getPlayers().size());
            target = newGame.getPlayers().get(characterNum);
        }
        attack(newPlayer, target);
        endTurn();
    }

    public void onHealButton () {
        if (!newPlayer.isAlive()) {
            endTurn();
            return;
        }

        heal(newPlayer);
        endTurn();
    }

    public void onReloadButton() {
        if (!newPlayer.isAlive()) {
            endTurn();
            return;
        }

        reload(newPlayer);
        endTurn();
    }

    private void endTurn() {
        PlayableCharacter currentPlayer = newGame.getCurrentCharacter();
        int characterNum = newGame.getPlayers().indexOf(currentPlayer);
        if (characterNum+1 <  newGame.getPlayers().size()) {
            newGame.setCurrentCharacter(newGame.getPlayers().get(characterNum+1));
        } else {
            newGame.setCurrentCharacter(newGame.getPlayers().getFirst());
        }
        startTurn();
    }

    private void updateAllBattleStat() {
        for (int i = 0; i < newGame.getPlayers().size() && i < hpLabels.size(); i++) {
            PlayableCharacter character = newGame.getPlayers().get(i);
            Label hpLabel = hpLabels.get(i);

            hpLabel.setText("HP: " + character.getHp() + "/" + 100);

            if (!character.isAlive() && i < characterPanes.size()) {
                characterPanes.get(i).setOpacity(0.5);
            }
        }
    }
}
