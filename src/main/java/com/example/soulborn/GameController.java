package com.example.soulborn;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class GameController {
    public AnchorPane homePane;
    public AnchorPane savedGamePane;
    public AnchorPane newGamePaneCH;
    public AnchorPane newGamePaneNPC;
    public AnchorPane gamePane;

    public Button newGameButton;
    public Button loadGameButton;

    public TextField usernameField;
    public TextField powerField;
    public TextField dexterityField;
    public TextField faithField;
    public TextField armorField;
    public TextField intelligenceField;

    public Label upgradePointsLabel;

    public Button saveCharacterButton;
    public Button cancelCharacterButton;
    public Button exitSavedGameButton;
    public Button startGameButton;
    public Button generateNPCButton;
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

    public void initialize() {
        Weapon.generate();
        ArrayList<Weapon> weapons = Weapon.getWeapons();
        for (Weapon weapon : weapons) {
            Button newWeapon = new Button(weapon.getWeaponName());
            newWeapon.setId(weapon.getWeaponName());
            newWeapon.setOnAction(this::saveWeapon);
            weaponCatalog.getChildren().add(newWeapon);
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

    public void showHomePane() {
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
        hideAllPanes();
        newGamePaneNPC.setVisible(true);
        newGamePaneNPC.setDisable(false);
    }

    public void showGamePane() {
        hideAllPanes();
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
            newPlayer = new Player("", 0, true, null, 0, 100, true, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
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
}
