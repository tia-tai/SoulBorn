package com.example.soulborn;

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
        ArrayList<Weapon> weapons = Weapon.getWeapons();
        for (Weapon weapon : weapons) {

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
        newPlayer = new Player(name, 0, true, null, 0, 100, true, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
    }

    public void saveWeapon() {

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

            newPlayer.setIcon(img);
        }
        uploadButton.setDisable(false);
    }

    public void editStats() {
        int power = Integer.parseInt(powerField.getText());
        int dexterity = Integer.parseInt(dexterityField.getText());
        int faith = Integer.parseInt(faithField.getText());
        int armor = Integer.parseInt(armorField.getText());
        int intelligence = Integer.parseInt(intelligenceField.getText());

        int totalStat = power + dexterity + faith + armor + intelligence;
        int pointleft = 10 - totalStat;

        while (pointleft < 0) {
            if (power > 0) {
                powerField.setText("0");
                pointleft += power;
            } else if (dexterity > 0) {
                dexterityField.setText("0");
                pointleft += dexterity;
            } else if (faith > 0) {
                faithField.setText("0");
                pointleft += faith;
            } else if (armor > 0) {
                armorField.setText("0");
                pointleft += armor;
            } else if (intelligence > 0) {
                intelligenceField.setText("0");
                pointleft += intelligence;
            }
        }
        upgradePointsLabel.setText(Integer.toString(pointleft));
        newPlayer.setDefaultArmor(armor); // stopped here
    }
}
