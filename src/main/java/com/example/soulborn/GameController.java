package com.example.soulborn;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
}
