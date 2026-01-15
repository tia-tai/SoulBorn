package com.example.soulborn;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.*;
import javafx.embed.swing.SwingFXUtils;
import java.util.ArrayList;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<PlayableCharacter> players = new ArrayList<>();
    private PlayableCharacter currentCharacter;
    transient private String gameName = "default Game";
    static ArrayList<Game> gameList = new ArrayList<>();

    public Game(ArrayList<PlayableCharacter> players, PlayableCharacter currentCharacter, String gameName) {
        this.players = players;
        this.currentCharacter = currentCharacter;
        this.gameName = gameName;
        gameList.add(this);
    }

    public ArrayList<PlayableCharacter> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayableCharacter> players) {
        this.players = players;
    }

    public PlayableCharacter getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(PlayableCharacter currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public static ArrayList<Game> getGameList() {
        return gameList;
    }

    public static void setGameList(ArrayList<Game> gameList) {
        Game.gameList = gameList;
    }

    static void saveData()  {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/GameSavedData");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(Game.getGameList());
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            System.out.println("saveData: " + e);
        }
    }

    static void restoreData() {
        try {
            FileInputStream fileIn = new FileInputStream("src/main/GameSavedData");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Game.setGameList((ArrayList<Game>) objectIn.readObject());
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("restoreData: " + e);
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        for (PlayableCharacter character : this.players){
            if (character.getIcon() != null) {
                ImageIO.write(SwingFXUtils.fromFXImage(character.getIcon(), null), "png", s);
            }
        }
    }

    @Serial
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        for (PlayableCharacter character : this.players){
            Image savedImage = null;
            try {
                savedImage = SwingFXUtils.toFXImage(ImageIO.read(s), null);
            }
            catch (Exception ex) {
            }
            character.setIcon(savedImage);
        }
    }
}
